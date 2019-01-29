head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.40.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondBalanceReferenceComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券残高照会Comparator (WEB3BondBalanceReferenceComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/20 周捷 (中訊) 新規作成
*/
package webbroker3.bd.message;

import java.util.Comparator;
import java.util.Date;

import webbroker3.bd.define.WEB3BondBalanceReferenceDetailUnitDef;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (債券残高照会Comparator )<BR>
 * 債券残高照会Comparator 
 * 
 * @@author 周捷
 * @@version 1.0
 */
public class WEB3BondBalanceReferenceComparator implements Comparator
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondBalanceReferenceComparator.class);
    
    /**
     * (orderBy)<BR>
     * 昇順（：asc）、降順（：desc）を指定するフラグ。 <BR>  
     *<BR>
     *　@A：昇順 <BR>
     *　@D：降順 <BR>
     */
    private String orderBy;
    
    /**
     * (比較項目)<BR>
     * 比較項目 <BR>
     * 以下のいづれかの値がセットされる。 <BR>
     * 　@・債券残高照会明細.種別 <BR>
     * 　@・債券残高照会明細.銘柄コード <BR>
     * 　@・債券残高照会明細.回号 <BR>
     * 　@・債券残高照会明細.売却可能数量 <BR> 
     * 　@・債券残高照会明細.通貨  <BR>
     * 　@・債券残高照会明細.概算評価額（円貨）  <BR>
     * 　@・債券残高照会明細.概算評価額（外貨） <BR> 
     * 　@・債券残高照会明細.発行日  <BR>
     * 　@・債券残高照会明細.償還日  <BR>
     */
    private String compareItem;
    
    /**
     * コンストラクタ。<BR>
     */
    public WEB3BondBalanceReferenceComparator() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR> 
     * <BR>
     * 引数の値をthis.orderBy、比較項目にセットする。<BR>
     * @@param l_strOrderBy - (orderBy) <BR>
     * 昇順（：asc）、降順（：desc）を指定するフラグ。  <BR> 
     * <BR>
     * 　@A：昇順 <BR>
     * 　@D：降順 <BR>
     * @@param l_strCompareItem - (比較項目)<BR>
     * 比較項目 <BR>
     * <BR>
     * 以下のいづれかの値がセットされる。<BR> 
     * 　@・債券残高照会明細.種別 <BR>
     * 　@・債券残高照会明細.銘柄コード <BR>
     * 　@・債券残高照会明細.回号 <BR>
     * 　@・債券残高照会明細.売却可能数量 <BR> 
     * 　@・債券残高照会明細.通貨  <BR>
     * 　@・債券残高照会明細.概算評価額（円貨）  <BR>
     * 　@・債券残高照会明細.概算評価額（外貨） <BR> 
     * 　@・債券残高照会明細.発行日  <BR>
     * 　@・債券残高照会明細.償還日  <BR>
     * @@roseuid 41B80B1E0123<BR>
     */
    public WEB3BondBalanceReferenceComparator(String l_strOrderBy, String l_strCompareItem) 
    {
        final String STR_METHOD_NAME ="WEB3BondBalanceReferenceComparator(String, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strOrderBy == null || 
            (!WEB3AscDescDef.ASC.equals(l_strOrderBy) && !WEB3AscDescDef.DESC.equals(l_strOrderBy)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException("パラメータの値が'A：昇順'、'D：降順'以外です");
        }
        this.orderBy = l_strOrderBy;
        
        if (l_strCompareItem == null || 
            (!WEB3BondBalanceReferenceDetailUnitDef.BOND_CATEG_CODE.equals(l_strCompareItem)
            && !WEB3BondBalanceReferenceDetailUnitDef.PRODUCT_CODE.equals(l_strCompareItem)
            && !WEB3BondBalanceReferenceDetailUnitDef.PRODUCT_ISSUE_CODE.equals(l_strCompareItem)
            && !WEB3BondBalanceReferenceDetailUnitDef.SELL_ABLE_QTY.equals(l_strCompareItem)
            && !WEB3BondBalanceReferenceDetailUnitDef.CURRENCY_CODE.equals(l_strCompareItem)
            && !WEB3BondBalanceReferenceDetailUnitDef.YEN_ESTIMATED_ASSET.equals(l_strCompareItem)
            && !WEB3BondBalanceReferenceDetailUnitDef.FOREIGN_ESTIMATED_ASSET.equals(l_strCompareItem)
            && !WEB3BondBalanceReferenceDetailUnitDef.ISSUE_DATE.equals(l_strCompareItem)
            && !WEB3BondBalanceReferenceDetailUnitDef.MATURITY_DATE.equals(l_strCompareItem)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException(
                "パラメータの値が'種別'、'銘柄コード'、'回号'、'数量'、'概算評価額'以外です" );
        }
        
        compareItem = l_strCompareItem;         
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （compareの実装） <BR>
     * <BR>
     * 明細１，２について、this.比較項目で指定した項目の比較を行う。  <BR>
     * <BR>
     * １）　@引数のcast<BR>
     * パラメータ.明細1および2を、債券残高照会明細にcastする。<BR>
     * <BR>
     * ３）　@比較 <BR>
     * 　@２）でcastした明細1、明細2について <BR>
     * 　@this.比較項目に該当する項目の値を比較し、結果を返却する。<BR>
     * <BR>
     * 　@[昇順指定の場合（this.orderBy == ”昇順”）] <BR>
     * 　@・（明細1.比較項目 < 明細2.比較項目）の場合、負の整数（-1）を返却する。 <BR>
     * 　@・（明細1.比較項目 == 明細2.比較項目）の場合、0を返却する。 <BR>
     * 　@・（明細1.比較項目 > 明細2.比較項目）の場合、正の整数（1）を返却する。 <BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”）] <BR>
     * 　@・（明細1.比較項目 < 明細2.比較項目）の場合、正の整数（1）を返却する。 <BR>
     * 　@・（明細1.比較項目 == 明細2.比較項目）の場合、0を返却する。 <BR>
     * 　@・（明細1.比較項目 > 明細2.比較項目）の場合、負の整数（-1）を返却する。<BR>
     * @@param l_obj1 - (明細1) <BR>
     * 債券残高照会明細オブジェクト１
     * @@param l_obj2 - (明細2) 
     * 債券残高照会明細オブジェクト2
     * @@return int<BR>
     * @@roseuid 41B80B1E0133<BR>
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME = " compare(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        WEB3BondBalanceReferenceDetailUnit l_unit1 = null;
        WEB3BondBalanceReferenceDetailUnit l_unit2 = null;

        if ((l_obj1 instanceof WEB3BondBalanceReferenceDetailUnit) && (l_obj2 instanceof WEB3BondBalanceReferenceDetailUnit))
        {
            l_unit1 = (WEB3BondBalanceReferenceDetailUnit)l_obj1;
            l_unit2 = (WEB3BondBalanceReferenceDetailUnit)l_obj2;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException(
                "パラメータの型がWEB3BondBalanceReferenceDetailUnit以外です。"); 
        }
        
        // 比較項目 == 種別 or 銘柄コード or 回号 or 通貨の場合
        if (WEB3BondBalanceReferenceDetailUnitDef.BOND_CATEG_CODE.equals(compareItem)
            || WEB3BondBalanceReferenceDetailUnitDef.PRODUCT_CODE.equals(compareItem)
            || WEB3BondBalanceReferenceDetailUnitDef.PRODUCT_ISSUE_CODE.equals(compareItem)
            || WEB3BondBalanceReferenceDetailUnitDef.CURRENCY_CODE.equals(compareItem))
        {
            String l_strKeyItem1 = null;
            String l_strKeyItem2 = null;
            
            if (WEB3BondBalanceReferenceDetailUnitDef.PRODUCT_CODE.equals(compareItem))
            {
                // 比較項目 = 銘柄コード
                l_strKeyItem1 = l_unit1.productCode;
                l_strKeyItem2 = l_unit2.productCode;
            }
            else if (WEB3BondBalanceReferenceDetailUnitDef.BOND_CATEG_CODE.equals(compareItem))
            {
                // 比較項目 = 種別
                l_strKeyItem1 = l_unit1.bondCategCode;
                l_strKeyItem2 = l_unit2.bondCategCode;
            }
            else if (WEB3BondBalanceReferenceDetailUnitDef.CURRENCY_CODE.equals(compareItem))
            {
                // 比較項目 = 通貨
                l_strKeyItem1 = l_unit1.currencyCode;
                l_strKeyItem2 = l_unit2.currencyCode;
            }
            else
            {
                // 比較項目 = 回号
                l_strKeyItem1 = l_unit1.productIssueCode;
                l_strKeyItem2 = l_unit2.productIssueCode;
            }
            
            if (l_strKeyItem1 == null && l_strKeyItem2 == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            
            // 昇順ソートの場合
            if (WEB3AscDescDef.ASC.equals(orderBy))
            {
                if (l_strKeyItem2 == null || (l_strKeyItem1 != null && 
                    l_strKeyItem1.compareTo(l_strKeyItem2) > 0))
                {
                    log.exiting(STR_METHOD_NAME);
                    return 1;
                }
                else if (l_strKeyItem1 == null || (l_strKeyItem2 != null &&
                    l_strKeyItem1.compareTo(l_strKeyItem2) < 0))
                {
                    log.exiting(STR_METHOD_NAME);
                    return -1;
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return 0;
                }
            }
            // 降順ソートの場合
            else
            {
                if (l_strKeyItem2 == null || (l_strKeyItem1 != null &&
                    l_strKeyItem1.compareTo(l_strKeyItem2) > 0))
                {
                    log.exiting(STR_METHOD_NAME);
                    return -1;
                }
                else if (l_strKeyItem1 == null || (l_strKeyItem2 != null &&
                    l_strKeyItem1.compareTo(l_strKeyItem2) < 0))
                {
                    log.exiting(STR_METHOD_NAME);
                    return 1;
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return 0;
                }
            }
        }
        else if (WEB3BondBalanceReferenceDetailUnitDef.SELL_ABLE_QTY.equals(compareItem)
            || WEB3BondBalanceReferenceDetailUnitDef.YEN_ESTIMATED_ASSET.equals(compareItem)
            || WEB3BondBalanceReferenceDetailUnitDef.FOREIGN_ESTIMATED_ASSET.equals(compareItem))
        {
            String l_strKeyItem1 = "";
            String l_strKeyItem2 = "";
            if (WEB3BondBalanceReferenceDetailUnitDef.SELL_ABLE_QTY.equals(compareItem))
            {
                l_strKeyItem1 = l_unit1.sellAbleQty;
                l_strKeyItem2 = l_unit2.sellAbleQty;
            }
            else if (WEB3BondBalanceReferenceDetailUnitDef.FOREIGN_ESTIMATED_ASSET.equals(compareItem))
            {
                l_strKeyItem1 = l_unit1.foreignEstimatedAsset;
                l_strKeyItem2 = l_unit2.foreignEstimatedAsset;
            }
            else
            {
                l_strKeyItem1 = l_unit1.yenEstimatedAsset;
                l_strKeyItem2 = l_unit2.yenEstimatedAsset;
            }
            
            if (l_strKeyItem1 == null && l_strKeyItem2 == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            
            // 昇順ソートの場合
            if (l_strKeyItem1 == null || l_strKeyItem2 == null)
            {
                if (WEB3AscDescDef.ASC.equals(orderBy))
                {
                    if (l_strKeyItem2 == null || (l_strKeyItem1 != null && 
                        l_strKeyItem1.compareTo(l_strKeyItem2) > 0))
                    {
                        log.exiting(STR_METHOD_NAME);
                        return 1;
                    }
                    else if (l_strKeyItem1 == null || (l_strKeyItem2 != null &&
                        l_strKeyItem1.compareTo(l_strKeyItem2) < 0))
                    {
                        log.exiting(STR_METHOD_NAME);
                        return -1;
                    }
                }
                // 降順ソートの場合
                else
                {
                    if (l_strKeyItem2 == null || (l_strKeyItem1 != null &&
                        l_strKeyItem1.compareTo(l_strKeyItem2) > 0))
                    {
                        log.exiting(STR_METHOD_NAME);
                        return -1;
                    }
                    else if (l_strKeyItem1 == null || (l_strKeyItem2 != null &&
                        l_strKeyItem1.compareTo(l_strKeyItem2) < 0))
                    {
                        log.exiting(STR_METHOD_NAME);
                        return 1;
                    }
                }
            }
            
            double l_dblKeyItem1 = 0.0D;
            double l_dblKeyItem2 = 0.0D;
            
            l_dblKeyItem1 = Double.parseDouble(l_strKeyItem1);
            l_dblKeyItem2 = Double.parseDouble(l_strKeyItem2);
            
            if (l_dblKeyItem1 == l_dblKeyItem2)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            
            // 昇順ソートの場合
            if (WEB3AscDescDef.ASC.equals(orderBy))
            {
                if (l_dblKeyItem1 < l_dblKeyItem2)
                {
                    log.exiting(STR_METHOD_NAME);
                    return -1;
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return 1;
                }
            }
            // 降順ソートの場合
            else
            {
                if (l_dblKeyItem1 < l_dblKeyItem2)
                {
                    log.exiting(STR_METHOD_NAME);
                    return 1;
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return -1;
                }
            }
        }
        
        else
        {
            Date l_datKeyItem1 = null;
            Date l_datKeyItem2 = null;
            
            if (WEB3BondBalanceReferenceDetailUnitDef.ISSUE_DATE.equals(compareItem))
            {
                // 比較項目 = 発行日
                l_datKeyItem1 = l_unit1.issueDate;
                l_datKeyItem2 = l_unit2.issueDate;
            }
            else
            {
                // 比較項目 = 償還日
                l_datKeyItem1 = l_unit1.maturityDate;
                l_datKeyItem2 = l_unit2.maturityDate;
            }
            
            if (l_datKeyItem1 == null && l_datKeyItem2 == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            
            // 昇順ソートの場合
            if (WEB3AscDescDef.ASC.equals(orderBy))
            {
                if (l_datKeyItem2 == null || (l_datKeyItem1 != null && 
                    l_datKeyItem1.compareTo(l_datKeyItem2) > 0))
                {
                    log.exiting(STR_METHOD_NAME);
                    return 1;
                }
                else if (l_datKeyItem1 == null || (l_datKeyItem2 != null &&
                    l_datKeyItem1.compareTo(l_datKeyItem2) < 0))
                {
                    log.exiting(STR_METHOD_NAME);
                    return -1;
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return 0;
                }
            }
            // 降順ソートの場合
            else
            {
                if (l_datKeyItem2 == null || (l_datKeyItem1 != null &&
                    l_datKeyItem1.compareTo(l_datKeyItem2) > 0))
                {
                    log.exiting(STR_METHOD_NAME);
                    return -1;
                }
                else if (l_datKeyItem1 == null || (l_datKeyItem2 != null &&
                    l_datKeyItem1.compareTo(l_datKeyItem2) < 0))
                {
                    log.exiting(STR_METHOD_NAME);
                    return 1;
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return 0;
                }
            }
        }
    }
    
    /**
     * （equalsの実装） <BR>
     * <BR>
     * 未使用。 <BR>
     * falseを返却する。<BR>
     * @@param arg0<BR>
     * @@return boolean<BR>
     */
    public boolean equals(Object arg0) 
    {
        return false;
    }
    
}
@
