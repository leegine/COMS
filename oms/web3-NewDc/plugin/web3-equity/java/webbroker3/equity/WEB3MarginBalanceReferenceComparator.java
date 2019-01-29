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
filename	WEB3MarginBalanceReferenceComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引残高照会Comparator(WEB3MarginBalanceReferenceComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 沢村　@仁士(SRA) 新規作成
*/

package webbroker3.equity;

import java.util.Comparator;
import java.util.Date;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.message.WEB3MarginBalanceReferenceDetailUnit;
import webbroker3.util.WEB3LogUtility;


/**
 * （信用取引残高照会Comparator）。<BR>
 * <BR>
 * 信用取引残高照会Comparator<BR>
 */
public class WEB3MarginBalanceReferenceComparator implements Comparator 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginBalanceReferenceComparator.class);

    /**
     * 昇順（：asc）、降順（：desc）を指定するフラグ。 <BR>
     * <BR>
     * 　@A：昇順  <BR>
     * 　@D：降順 <BR>
     */
    private String orderBy;
    
    /**
     * (比較項目)<BR>
     * <BR>
     * 比較項目<BR>
     * <BR>
     * 以下の項目が指定される。<BR>
     * 　@信用取引残高照会明細.銘柄コード<BR>
     * 　@信用取引残高照会明細.市場コード<BR>
     * 　@信用取引残高照会明細.口座区分<BR>
     * 　@信用取引残高照会明細.建区分<BR>
     * 　@信用取引残高照会明細.建日<BR>
     * 　@信用取引残高照会明細.期日<BR>
     * 　@信用取引残高照会明細.弁済.弁済区分<BR>
     * 　@信用取引残高照会明細.弁済.弁済期限値<BR>
     * 　@信用取引残高照会明細.評価損益<BR>
     * 　@信用取引残高照会明細.評価損益（諸経費考慮）<BR>
     * <BR>
     */
    private String compareItem;
    
    /**
     * @@roseuid 4206CDD503B5<BR>
     */
    public WEB3MarginBalanceReferenceComparator() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 引数の値をthis.orderBy，this.比較項目にセットする。<BR>
     * @@param l_strOrderBy - (orderBy) 昇順（：asc）、降順（：desc）を指定するフラグ。 <BR>
     * <BR>
     * 　@A：昇順 <BR>
     * 　@D：降順 <BR>
     * @@param l_strCompareItem - (比較項目) compareにて使用する比較項目<BR>
     * <BR>
     * 以下の項目が指定される。<BR>
     * 　@信用取引残高照会明細.銘柄コード<BR>
     * 　@信用取引残高照会明細.市場コード<BR>
     * 　@信用取引残高照会明細.口座区分<BR>
     * 　@信用取引残高照会明細.建区分<BR>
     * 　@信用取引残高照会明細.建日<BR>
     * 　@信用取引残高照会明細.期日<BR>
     * 　@信用取引残高照会明細.弁済.弁済区分<BR>
     * 　@信用取引残高照会明細.弁済.弁済期限値<BR>
     * 　@信用取引残高照会明細.評価損益<BR>
     * 　@信用取引残高照会明細.評価損益（諸経費考慮）<BR>
     * <BR>
     * @@return WEB3MarginBalanceReferenceComparator<BR>
     * @@roseuid 41C2995502C7<BR>
     */
    public WEB3MarginBalanceReferenceComparator(String l_strOrderBy, String l_strCompareItem) 
    {
        final String STR_METHOD_NAME ="WEB3MarginBalanceReferenceComparator(String, String)";
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
            && !WEB3EquityKeyItemDef.TRADEMARKET.equals(l_strCompareItem)
            && !WEB3EquityKeyItemDef.ACCOUNTTYPE.equals(l_strCompareItem)
            && !WEB3EquityKeyItemDef.CONTRACT_DIVISION.equals(l_strCompareItem)
            && !WEB3EquityKeyItemDef.OPEN_DATE.equals(l_strCompareItem)
            && !WEB3EquityKeyItemDef.CLOSEDATE.equals(l_strCompareItem)
            && !WEB3EquityKeyItemDef.REPAYMENT_DIV.equals(l_strCompareItem)
            && !WEB3EquityKeyItemDef.REPAYMENTNUM.equals(l_strCompareItem)
            && !WEB3EquityKeyItemDef.INCOME.equals(l_strCompareItem)
            && !WEB3EquityKeyItemDef.INCOME_COST.equals(l_strCompareItem)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException(
                "パラメータの値が'銘柄コード'、'市場コード'、'口座区分'、'建区分'、'建日'、'期日'、" +
                "'弁済区分'、'弁済期限値'、'評価損益'、'評価損益（諸経費考慮）'以外です" );
        }
        this.compareItem = l_strCompareItem;         
        
        log.debug("WEB3MarginBalanceReferenceComparator().....OK>>>>>");
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * （compareの実装） <BR>
     * <BR>
     * 明細１，２について、this.比較項目で指定した項目の比較を行う。  <BR>
     * <BR>
     * １）　@引数のcast<BR>
     * パラメータ.明細1および2を、信用取引残高照会明細型にcastする。<BR>
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
     * 　@・（明細1.比較項目 > 明細2.比較項目）の場合、負の整数（-1）を返却する。 <BR>
     * <BR>
     * @@param l_obj1 - (明細1) 信用取引残高照会明細オブジェクト1<BR>
     * @@param l_obj2 - (明細2) 信用取引照会明細オブジェクト2<BR>
     * @@return int<BR>
     * @@roseuid 41C2995502CA<BR>
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME = " compare(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginBalanceReferenceDetailUnit l_unit1 = null;
        WEB3MarginBalanceReferenceDetailUnit l_unit2 = null;

        if((l_obj1 instanceof WEB3MarginBalanceReferenceDetailUnit) && (l_obj2 instanceof WEB3MarginBalanceReferenceDetailUnit))
        {
            l_unit1 = (WEB3MarginBalanceReferenceDetailUnit)l_obj1;
            l_unit2 = (WEB3MarginBalanceReferenceDetailUnit)l_obj2;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException(
                "パラメータの型がWEB3MarginBalanceReferenceDetailUnit以外です。"); 
        }
        
        // 比較項目 == 銘柄コード or 口座区分 or 建区分 or 弁済区分の場合
        if (this.compareItem.equals(WEB3EquityKeyItemDef.PRODUCTCODE)
            || this.compareItem.equals(WEB3EquityKeyItemDef.ACCOUNTTYPE)
            || this.compareItem.equals(WEB3EquityKeyItemDef.CONTRACT_DIVISION)
            || this.compareItem.equals(WEB3EquityKeyItemDef.REPAYMENT_DIV))
        {
            String l_strKeyItem1 = null;
            String l_strKeyItem2 = null;
            
            if(WEB3EquityKeyItemDef.PRODUCTCODE.equals(this.compareItem))
            {
                // 比較項目 = 銘柄コード
                l_strKeyItem1 = l_unit1.productCode;
                l_strKeyItem2 = l_unit2.productCode;
            }
            else if (WEB3EquityKeyItemDef.ACCOUNTTYPE.equals(this.compareItem))
            {
                // 比較項目 = 口座区分
                l_strKeyItem1 = l_unit1.taxType;
                l_strKeyItem2 = l_unit2.taxType;
            }
            else if (WEB3EquityKeyItemDef.CONTRACT_DIVISION.equals(this.compareItem))
            {
                // 比較項目 = 建区分
                l_strKeyItem1 = l_unit1.contractType;
                l_strKeyItem2 = l_unit2.contractType;
            }
            else if (WEB3EquityKeyItemDef.REPAYMENT_DIV.equals(this.compareItem))
            {
                // 比較項目 = 弁済区分
                l_strKeyItem1 = l_unit1.repayment.repaymentDiv;
                l_strKeyItem2 = l_unit2.repayment.repaymentDiv;
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
        // 比較項目 == 建日 or 期日の場合
        else if (this.compareItem.equals(WEB3EquityKeyItemDef.OPEN_DATE)
            || this.compareItem.equals(WEB3EquityKeyItemDef.CLOSEDATE))
        {
            Date l_datKeyItem1 = null;
            Date l_datKeyItem2 = null;
            if (WEB3EquityKeyItemDef.OPEN_DATE.equals(this.compareItem))
            {
                l_datKeyItem1 = l_unit1.openDate;
                l_datKeyItem2 = l_unit2.openDate;
            }
            else
            {
                l_datKeyItem1 = l_unit1.closeDate;
                l_datKeyItem2 = l_unit2.closeDate;
            }
            if (l_datKeyItem1 == null && l_datKeyItem2 == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            
            // 昇順ソートの場合
            if(WEB3AscDescDef.ASC.equals(this.orderBy))
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
        // 比較項目 == 市場コード or 弁済期限値の場合
        else if (this.compareItem.equals(WEB3EquityKeyItemDef.TRADEMARKET)
		   	|| this.compareItem.equals(WEB3EquityKeyItemDef.REPAYMENTNUM))
        {
            int l_intKeyItem1 = 0;
            int l_intKeyItem2 = 0;
            if (WEB3EquityKeyItemDef.TRADEMARKET.equals(this.compareItem))
            {
                // 比較項目 == 市場コードの場合
                l_intKeyItem1 = Integer.parseInt(l_unit1.marketCode);
                l_intKeyItem2 = Integer.parseInt(l_unit2.marketCode);
            }
			else if (WEB3EquityKeyItemDef.REPAYMENTNUM.equals(this.compareItem))
			{
				// 比較項目 = 弁済期限値
				l_intKeyItem1 = Integer.parseInt(l_unit1.repayment.repaymentTimeLimit);
				l_intKeyItem2 = Integer.parseInt(l_unit2.repayment.repaymentTimeLimit);
			}
            
            if (l_intKeyItem1 == l_intKeyItem2)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            
            // 昇順ソートの場合
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                if (l_intKeyItem1 < l_intKeyItem2)
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
                if (l_intKeyItem1 < l_intKeyItem2)
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
        // 比較項目 == 評価損益 or 評価損益（諸経費考慮）の場合
        else
        {
            String l_strKeyItem1 = "";
            String l_strKeyItem2 = "";
            if (WEB3EquityKeyItemDef.INCOME.equals(this.compareItem))
            {
                // 比較項目 == 評価損益の場合
                l_strKeyItem1 = l_unit1.appraisalProfitLoss;
                l_strKeyItem2 = l_unit2.appraisalProfitLoss;
            }
            else if (WEB3EquityKeyItemDef.INCOME_COST.equals(this.compareItem))
            {
                // 比較項目 == 評価損益（諸経費考慮）の場合
                l_strKeyItem1 = l_unit1.appraisalProfitLossCost;
                l_strKeyItem2 = l_unit2.appraisalProfitLossCost;
            }
            
            if (l_strKeyItem1 == null && l_strKeyItem2 == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            
            if (l_strKeyItem1 == null || l_strKeyItem2 == null)
            {
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
     * falseを返却する。 <BR>
     * @@param arg0<BR>
     * @@return boolean<BR>
     * @@roseuid 41C2995502D6<BR>
     */
    public boolean equals(Object arg0) 
    {
        return false;
    }
}
@
