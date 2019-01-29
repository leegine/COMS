head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityBalanceReferenceComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式残高照会Comparator(WEB3EquityBalanceReferenceComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 沢村　@仁士(SRA) 新規作成
*/

package webbroker3.equity;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.message.WEB3EquityBalanceReferenceDetailUnit;
import webbroker3.util.WEB3LogUtility;


/**
 * （現物株式残高照会Comparator）。<BR>
 * <BR>
 * 現物株式残高照会Comparator<BR>
 */
public class WEB3EquityBalanceReferenceComparator implements Comparator 
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3EquityBalanceReferenceComparator.class);
    
    /**
     * 昇順（：asc）、降順（：desc）を指定するフラグ。 <BR>
     * <BR>
     * 　@A：昇順  <BR>
     * 　@D：降順<BR>
     */
    private String orderBy;
    
    /**
     * (比較項目)<BR>
     * <BR>
     * 以下の項目が指定される。<BR>
     * 　@現物株式残高照会明細.銘柄コード<BR>
     * 　@現物株式残高照会明細.口座区分<BR>
     * 　@現物株式残高照会明細.概算評価額(残高株数)<BR>
     * 　@現物株式残高照会明細.概算評価損益(残高株数)<BR>
     */
    private String compareItem;
    
    /**
     * @@roseuid 4206CC4B00D7<BR>
     */
    public WEB3EquityBalanceReferenceComparator() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 引数の値をthis.orderBy，this.比較項目にセットする。<BR>
     * @@param l_strOrderBy - (昇順)（：asc）、降順（：desc）を指定するフラグ。 <BR>
     * <BR>
     * 　@A：昇順 <BR>
     * 　@D：降順 <BR>
     * @@param l_strCompareItem - (比較項目) compareにて使用する比較項目<BR>
     * <BR>
     * 以下の項目が指定される。<BR>
     * 　@現物株式残高照会明細.銘柄コード<BR>
     * 　@現物株式残高照会明細.口座区分<BR>
     * 　@現物株式残高照会明細.概算評価額(残高株数)<BR>
     * 　@現物株式残高照会明細.概算評価損益(残高株数)<BR>
     * <BR>
     * @@return <BR>
     * webbroker3.equity.株式サービスモデル.現物株式残高照会.現物株式残高照会Comparator<BR>
     * @@roseuid 41B80B1E0123<BR>
     */
    public WEB3EquityBalanceReferenceComparator(String l_strOrderBy, String l_strCompareItem) 
    {
        final String STR_METHOD_NAME ="WEB3EquityBalanceReferenceComparator(String, String)";
        log.entering(STR_METHOD_NAME);
        log.debug("引数1の値 = " + l_strOrderBy);
        log.debug("引数2の値 = " + l_strCompareItem);
        
        if(l_strOrderBy == null || (!WEB3AscDescDef.ASC.equals(l_strOrderBy) && !WEB3AscDescDef.DESC.equals(l_strOrderBy)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException("パラメータの値が'A：昇順'、'D：降順'以外です");
        }
        this.orderBy = l_strOrderBy;
        
        if(l_strCompareItem == null || 
            (!WEB3EquityKeyItemDef.PRODUCTCODE.equals(l_strCompareItem)
            && !WEB3EquityKeyItemDef.ACCOUNTTYPE.equals(l_strCompareItem)
            && !WEB3EquityKeyItemDef.ESTIMATED_ASSET_BALANCE_QUANTITY.equals(l_strCompareItem)
            && !WEB3EquityKeyItemDef.ESTIMATED_INCOME_BALANCE_QUANTITY.equals(l_strCompareItem)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException(
                "パラメータの値が'銘柄コード'、'口座区分'、'概算評価額（残高株数）'、'概算評価損益（残高株数）'以外です" );
        }
        this.compareItem = l_strCompareItem;         
        
        log.debug("WEB3EquityBalanceReferenceComparator().....OK>>>>>");
	    log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * （compareの実装） <BR>
     * <BR>
     * 明細１，２について、this.比較項目で指定した項目の比較を行う。  <BR>
     * <BR>
     * １）　@引数のcast<BR>
     * パラメータ.明細1および2を、現物株式残高照会明細型にcastする。<BR>
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
     * @@param l_obj1 - (明細1) 現物株式残高照会明細オブジェクト1<BR>
     * @@param l_obj2 - (明細2) 現物株式残高照会明細オブジェクト2<BR>
     * @@return int<BR>
     * @@roseuid 41B80B1E0133<BR>
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME = " compare(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityBalanceReferenceDetailUnit l_unit1 = null;
        WEB3EquityBalanceReferenceDetailUnit l_unit2 = null;

        if((l_obj1 instanceof WEB3EquityBalanceReferenceDetailUnit) && (l_obj2 instanceof WEB3EquityBalanceReferenceDetailUnit))
        {
            l_unit1 = (WEB3EquityBalanceReferenceDetailUnit)l_obj1;
            l_unit2 = (WEB3EquityBalanceReferenceDetailUnit)l_obj2;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException(
                "パラメータの型がWEB3EquityBalanceReferenceDetailUnit以外です。"); 
        }
        
        // 比較項目 == 銘柄コード or 口座区分の場合
        if (this.compareItem.equals(WEB3EquityKeyItemDef.PRODUCTCODE)
            || this.compareItem.equals(WEB3EquityKeyItemDef.ACCOUNTTYPE))
        {
	        String l_strKeyItem1 = null;
	        String l_strKeyItem2 = null;
	        
            if(WEB3EquityKeyItemDef.PRODUCTCODE.equals(this.compareItem))
            {
                // 比較項目 = 銘柄コード
                l_strKeyItem1 = l_unit1.productCode;
                l_strKeyItem2 = l_unit2.productCode;
            }
            else
            {
                // 比較項目 = 口座区分
                l_strKeyItem1 = l_unit1.taxType;
                l_strKeyItem2 = l_unit2.taxType;
            }
            
            if (l_strKeyItem1 == null && l_strKeyItem2 == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            
            // 昇順ソートの場合
            if(WEB3AscDescDef.ASC.equals(this.orderBy))
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
        // 比較項目 == 概算評価額（残高株数） or 概算評価損益（残高株数）の場合
	    else
        {
            String l_strKeyItem1 = "";
            String l_strKeyItem2 = "";
            if (WEB3EquityKeyItemDef.ESTIMATED_ASSET_BALANCE_QUANTITY.equals(this.compareItem))
            {
                l_strKeyItem1 = l_unit1.estimatedAssetBalanceQuantity;
                l_strKeyItem2 = l_unit2.estimatedAssetBalanceQuantity;
            }
            else if (WEB3EquityKeyItemDef.ESTIMATED_INCOME_BALANCE_QUANTITY.equals(this.compareItem))
            {
                l_strKeyItem1 = l_unit1.estimatedAppraisalProfitLossBalanceQuantity;
                l_strKeyItem2 = l_unit2.estimatedAppraisalProfitLossBalanceQuantity;
            }
            
            if (l_strKeyItem1 == null && l_strKeyItem2 == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            
            // 昇順ソートの場合
            if (l_strKeyItem1 == null || l_strKeyItem2 == null)
            {
	            if(WEB3AscDescDef.ASC.equals(this.orderBy))
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
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
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
    }
    
    /**
     * （equalsの実装） <BR>
     * <BR>
     * 未使用。 <BR>
     * falseを返却する。<BR>
     * @@param arg0<BR>
     * @@return boolean<BR>
     * @@roseuid 41B80B1E0152<BR>
     */
    public boolean equals(Object arg0) 
    {
        return false;
    }
}
@
