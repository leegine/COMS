head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.21.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOptionsBalanceReferenceComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP残高照会Comparator(WEB3FuturesOptionsBalanceReferenceComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/29 呉艶飛 新規作成         
*/
package webbroker3.ifo.message;

import java.util.Comparator;
import java.util.Date;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.ifo.define.WEB3IfoKeyItemDef;
import webbroker3.util.WEB3DateUtility;


/**
 * (先物OP残高照会Comparator)<BR>
 * 先物OP残高照会Comparator<BR>
 * @@author 呉艶飛
 * @@version 1.0  
 */
public class WEB3FuturesOptionsBalanceReferenceComparator implements Comparator
{
   
    /**
     * A：昇順<BR>
     * D：降順<BR>
     */
    private String orderBy;
   
    /**
     * 比較項目<BR>
     * <BR>
     * 以下の項目が指定される。<BR>
     * 　@先物OP残高照会明細.銘柄コード<BR>
     * 　@先物OP残高照会明細.建区分<BR>
     * 　@先物OP残高照会明細.建日<BR>
     * 　@先物OP残高照会明細.損益<BR>
     * 　@先物OP残高照会明細.損益(諸経費込）<BR>
     */
    private String compareItem;
   
    /**
     * (先物OP残高照会Comparator)<BR>
     * コンストラクタ。<BR>
     * 引数の値をthis.orderBy，this.比較項目にセットする。<BR>
     * @@param l_strOrderBy - ソートキーの昇順降順を示す。
     * <BR>
     * A：昇順<BR>
     * D：降順<BR>
     * @@param l_strCompareItem - 比較項目
     * <BR>
     * 以下の項目が指定される。<BR>
     * 　@先物OP残高照会明細.銘柄コード<BR>
     * 　@先物OP残高照会明細.建区分<BR>
     * 　@先物OP残高照会明細.建日<BR>
     * 　@先物OP残高照会明細.損益<BR>
     * 　@先物OP残高照会明細.損益(諸経費込）<BR>
     * @@roseuid 41B7F16403C3
     */
    public WEB3FuturesOptionsBalanceReferenceComparator(String l_strOrderBy, String l_strCompareItem) 
    {
        if (l_strOrderBy == null
            ||( !WEB3AscDescDef.ASC.equals(l_strOrderBy)
            && !WEB3AscDescDef.DESC.equals(l_strOrderBy)))
        {
            throw new IllegalArgumentException("パラメータの値が'A：昇順'、'D：降順'以外です。");
        }
        this.orderBy = l_strOrderBy;
       
        if (l_strCompareItem == null
            || (!WEB3IfoKeyItemDef.BR_PRODUCTCODE.equals(l_strCompareItem)
            && !WEB3IfoKeyItemDef.CONTRACT_DIVISION.equals(l_strCompareItem)
            && !WEB3IfoKeyItemDef.OPEN_DATE.equals(l_strCompareItem)
            && !WEB3IfoKeyItemDef.INCOME.equals(l_strCompareItem)
            && !WEB3IfoKeyItemDef.INCOME_COST.equals(l_strCompareItem)))
        {
            throw new IllegalArgumentException("パラメータの値が'銘柄コード、'建区分、建日'、損益、損益（諸経費込）以外です。");
        }
       
        this.compareItem = l_strCompareItem;
    }
   
    /**
     * （compareの実装）<BR> 
     * <BR>
     * 明細１，２について、this.比較項目で指定した項目の比較を行う。<BR>  
     * <BR>
     * １）　@引数のcast<BR>
     * パラメータ.明細1および2を、先物OP残高照会明細型にcastする。<BR>
     * <BR>
     * ３）　@比較<BR> 
     * 　@２）でcastした明細1、明細2について<BR> 
     * 　@this.比較項目に該当する項目の値を比較し、結果を返却する。<BR>
     * <BR>
     * 　@[昇順指定の場合（this.orderBy == ”昇順”）]<BR> 
     * 　@・（明細1.比較項目 < 明細2.比較項目）の場合、負の整数（-1）を返却する。<BR> 
     * 　@・（明細1.比較項目 == 明細2.比較項目）の場合、0を返却する。 <BR>
     * 　@・（明細1.比較項目 > 明細2.比較項目）の場合、正の整数（1）を返却する。<BR> 
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”）]<BR> 
     * 　@・（明細1.比較項目 < 明細2.比較項目）の場合、正の整数（1）を返却する。<BR> 
     * 　@・（明細1.比較項目 == 明細2.比較項目）の場合、0を返却する。 <BR>
     * 　@・（明細1.比較項目 > 明細2.比較項目）の場合、負の整数（-1）を返却する。<BR>
     * @@param l_obj1 - 先物OP残高照会明細オブジェクト1<BR>
     * @@param l_obj2 - 先物OP残高照会明細オブジェクト2<BR>
     * @@return int
     * @@roseuid 41B7F16403E2
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        int l_intReturnValue;
        String l_strVal1 = null;
        String l_strVal2 = null;
        
        if (l_obj1 instanceof WEB3FuturesOptionsDetailUnit 
            && l_obj2 instanceof WEB3FuturesOptionsDetailUnit)
        {
           // 比較項目 == 銘柄コードの場合
           if (WEB3IfoKeyItemDef.BR_PRODUCTCODE.equals(this.compareItem))
           {
               l_strVal1 = ((WEB3FuturesOptionsDetailUnit)l_obj1).productCode;
               l_strVal2 = ((WEB3FuturesOptionsDetailUnit)l_obj2).productCode;

               if (l_strVal1 == null || l_strVal2 == null)
               {
                   int l_intResult;
               
                   if (l_strVal1 == null && l_strVal2 == null)
                   {
                       l_intResult = 0;
                   }
                   else if (l_strVal1 == null)
                   {
                       l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
                   }
                   else
                   {
                       l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
                   }
                        
                   return l_intResult;            
               }

               l_intReturnValue = this.compare(l_strVal1,l_strVal2);               
               return l_intReturnValue;

           }
           // 比較項目 == 建区分の場合
           else if (WEB3IfoKeyItemDef.CONTRACT_DIVISION.equals(this.compareItem))
           {
               l_strVal1 = ((WEB3FuturesOptionsDetailUnit)l_obj1).contractType;
               l_strVal2 = ((WEB3FuturesOptionsDetailUnit)l_obj2).contractType;

               if (l_strVal1 == null || l_strVal2 == null)
               {
                   int l_intResult;
               
                   if (l_strVal1 == null && l_strVal2 == null)
                   {
                       l_intResult = 0;
                   }
                   else if (l_strVal1 == null)
                   {
                       l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
                   }
                   else
                   {
                       l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
                   }
                        
                   return l_intResult;            
               }

               l_intReturnValue = this.compare(l_strVal1,l_strVal2);               
               return l_intReturnValue;
           }
           // 比較項目 == 損益 or 損益（諸経費込）の場合
           else if (WEB3IfoKeyItemDef.INCOME.equals(this.compareItem)
           || WEB3IfoKeyItemDef.INCOME_COST.equals(this.compareItem))
           {
               //比較項目 == 損益の場合
               if (WEB3IfoKeyItemDef.INCOME.equals(this.compareItem))
               {
                   l_strVal1 = ((WEB3FuturesOptionsDetailUnit)l_obj1).income;
                   l_strVal2 = ((WEB3FuturesOptionsDetailUnit)l_obj2).income;
               }
               //比較項目 == 損益（諸経費考慮）の場合
               else
               {
                   l_strVal1 = ((WEB3FuturesOptionsDetailUnit)l_obj1).incomeCost;
                   l_strVal2 = ((WEB3FuturesOptionsDetailUnit)l_obj2).incomeCost;
               }
               
               if (l_strVal1 == null || l_strVal2 == null)
               {
                   int l_intResult;
               
                   if (l_strVal1 == null && l_strVal2 == null)
                   {
                       l_intResult = 0;
                   }
                   else if (l_strVal1 == null)
                   {
                       l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
                   }
                   else
                   {
                       l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
                   }
                        
                   return l_intResult;            
                }
                double l_dblVal1 = 0;
                double l_dblVal2 = 0;
                
                //比較項目 == 損益の場合
                if (WEB3IfoKeyItemDef.INCOME.equals(this.compareItem))
                {
                   l_dblVal1 = Double.parseDouble(((WEB3FuturesOptionsDetailUnit)l_obj1).income);
                   l_dblVal2 = Double.parseDouble(((WEB3FuturesOptionsDetailUnit)l_obj2).income);
                }
                //比較項目 == 損益（諸経費考慮）の場合
                else
                {
                   l_dblVal1 = Double.parseDouble(((WEB3FuturesOptionsDetailUnit)l_obj1).incomeCost);
                   l_dblVal2 = Double.parseDouble(((WEB3FuturesOptionsDetailUnit)l_obj2).incomeCost);
                }
                l_intReturnValue = this.compare(l_dblVal1,l_dblVal2);           
               
                return l_intReturnValue;
            }
            // 比較項目 == 建日の場合
            else
            {
                Date l_datOpenDate1 = ((WEB3FuturesOptionsDetailUnit)l_obj1).openDate;
                Date l_datOpenDate2 = ((WEB3FuturesOptionsDetailUnit)l_obj2).openDate;

                if (l_datOpenDate1 == null || l_datOpenDate2 == null)
                {
                    int l_intResult;
               
                    if (l_datOpenDate1 == null && l_datOpenDate2 == null)
                    {
                        l_intResult = 0;
                    }
                    else if (l_datOpenDate1 == null)
                    {
                        l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
                    }
                    else
                    {
                        l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
                    }
                        
                    return l_intResult;            
                }
               
                l_intReturnValue = this.compare(l_datOpenDate1, l_datOpenDate2);               
                return l_intReturnValue;
            }
        }
        else
        {
            throw new IllegalArgumentException("パラメータの型がWEB3FuturesOptionsDetailUnit以外です。");
        }
    }
   
    /**
     * （equalsの実装）  <BR>
     * <BR>
     * 未使用。<BR>  
     * falseを返却する。<BR>
     * @@param obj
     * @@return boolean
     * @@roseuid 41B7F1650019
     */
    public boolean equals(Object l_obj) 
    {
        if (l_obj instanceof WEB3FuturesOptionsBalanceReferenceComparator)
        {
            WEB3FuturesOptionsBalanceReferenceComparator l_comparator = (WEB3FuturesOptionsBalanceReferenceComparator)l_obj;
            if (this.orderBy.equals(l_comparator.orderBy))
            {
                return true;
            }
        }
        return false;
    }
   
    /**
     * （compareの実装）<BR> 
     * 昇順、降順の指定にもとづくString型の比較を行う。<BR>
     * <BR>
     * 昇順指定の場合、<BR>
     * パラメータ.l_strVal1がパラメータl_strVal2より小さい場合は負の整数、<BR>
     * 両方が等しい場合は0、<BR>
     * パラメータ.l_strVal1がパラメータ.l_strVal2より大きい場合は正の整数を返却する<BR>
     * <BR>
     * 降順指定の場合、<BR>
     * パラメータ.l_strVal1がパラメータ.l_strVal2より小さい場合は正の整数、<BR>
     * 両方が等しい場合は0、<BR>
     * パラメータ.l_strVal1がパラメータ.l_strVal2より大きい場合は負の整数を返却する<BR>
     * <BR>
     * 昇降順の判定はコンストラクタでセットされたorderByの値を用いる<BR>
     * @@param l_strVal1 - 比較項目1
     * @@param l_strVal2 - 比較項目2
     * @@return int
     * @@roseuid 407BC2700167
     */
    private int compare(String l_strVal1,String l_strVal2)
    {
        if (l_strVal1.equals(l_strVal2))
        {
            return 0;
        }
        else if (l_strVal1.compareTo(l_strVal2) > 0)
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
        else
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                return -1;
            }
            else
            {
                return 1;
            }
        }
    }
    
    /**
     * （compareの実装）<BR> 
     * 昇順、降順の指定にもとづくDate型の比較を行う。<BR>
     * <BR>
     * 昇順指定の場合、<BR>
     * パラメータ.l_datVal1がパラメータl_datVal2より小さい場合は負の整数、<BR>
     * 両方が等しい場合は0、<BR>
     * パラメータ.l_datVal1がパラメータ.l_datVal2より大きい場合は正の整数を返却する<BR>
     * <BR>
     * 降順指定の場合、<BR>
     * パラメータ.l_datVal1がパラメータ.l_datVal2より小さい場合は正の整数、<BR>
     * 両方が等しい場合は0、<BR>
     * パラメータ.l_datVal1がパラメータ.l_datVal2より大きい場合は負の整数を返却する<BR>
     * <BR>
     * 昇降順の判定はコンストラクタでセットされたorderByの値を用いる<BR>
     * @@param l_datVal1 - 比較項目1
     * @@param l_datVal2 - 比較項目2
     * @@return int
     * @@roseuid 407BC2700167
     */
    private int compare(Date l_datVal1 ,Date l_datVal2)
    {
        if (WEB3DateUtility.compareToDay(l_datVal1, l_datVal2) == 0)
        {
            return 0;
        }
        else if (WEB3DateUtility.compareToDay(l_datVal1,l_datVal2) > 0)
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
        else
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                return -1;        
            }
            else
            {
                return 1;
            }
        }
    }
    
    /**
     * （compareの実装）<BR> 
     * 昇順、降順の指定にもとづくdouble型の比較を行う。<BR>
     * <BR>
     * 昇順指定の場合、<BR>
     * パラメータ.l_dblVal1がパラメータl_dblVal2より小さい場合は負の整数、<BR>
     * 両方が等しい場合は0、<BR>
     * パラメータ.l_dblVal1がパラメータ.l_dblVal2より大きい場合は正の整数を返却する<BR>
     * <BR>
     * 降順指定の場合、<BR>
     * パラメータ.l_dblVal1がパラメータ.l_dblVal2より小さい場合は正の整数、<BR>
     * 両方が等しい場合は0、<BR>
     * パラメータ.l_dblVal1がパラメータ.l_dblVal2より大きい場合は負の整数を返却する<BR>
     * <BR>
     * 昇降順の判定はコンストラクタでセットされたorderByの値を用いる<BR>
     * @@param l_dblVal1 - 比較項目1
     * @@param l_dblVal2 - 比較項目2
     * @@return int
     * @@roseuid 407BC2700167
     */
    private int compare(double l_dblVal1, double l_dblVal2)
    {
                
        if (l_dblVal1 == l_dblVal2)
        {
            return 0;
             
        }
        else if (l_dblVal1 > l_dblVal2) 
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                return 1;
            }
            else 
            {
                return -1; 
            }
                          
        }
        else
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                return -1;
            }
            else 
            {
                return 1; 
            }                                     
        }         
    }
}
@
