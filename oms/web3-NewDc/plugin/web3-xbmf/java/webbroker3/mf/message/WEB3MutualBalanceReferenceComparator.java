head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.00.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBalanceReferenceComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信残高照会Comparator(WEB3MutualBalanceReferenceComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/06 于美麗 (中訊) 新規作成
                   2006/03/08 鈴木 (SRA) 仕様変更（モデル）：403
*/

package webbroker3.mf.message;

import java.util.Comparator;
import java.util.Date;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.mf.define.WEB3MFSortkeyItemDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (投信残高照会Comparator)<BR>
 * 投信残高照会Comparator
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3MutualBalanceReferenceComparator implements Comparator 
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualBalanceReferenceComparator.class);
   
   /**
    * (orderBy)<BR>
    * A：昇順<BR>
    * D：降順
    */
   private String orderBy;
   
   /**
    * (比較項目)<BR>
    * 比較項目<BR>
    * <BR>
    * 以下の項目が指定される。<BR>
    * 　@投信残高照会明細.口座区分<BR>
    * 　@投信残高照会明細.評価額<BR>
    * 　@投信残高照会明細.評価損益<BR>
    * 　@投信残高照会明細.注文受付締切時間<BR>
    *   投信残高照会明細.銘柄ID<BR>
    */
   private String compareItem;
   
   /**
    * (投信残高照会Comparator)<BR>
    * コンストラクタ。<BR>
    * <BR>
    * 引数をthis.orderBy、this.比較項目にセットする。<BR>
    * @@param orderBy - ソートキーの昇順降順を示す。
    * <BR>
    * A：昇順 <BR>
    * D：降順 <BR>
    * <BR>
    * @@param compareItem - 比較項目 
    * <BR>
    * 以下の項目が指定される。<BR>
    * 　@投信残高照会明細.口座区分 <BR>
    * 　@投信残高照会明細.評価額 <BR>
    * 　@投信残高照会明細.評価損益 <BR>
    * 　@投信残高照会明細.注文受付締切時間 <BR>
    *   投信残高照会明細.銘柄ID<BR>
    * @@roseuid 41B7F69C0104
    */
   public WEB3MutualBalanceReferenceComparator(String l_strOrderBy, String l_strCompareItem) 
   {
       final String STR_METHOD_NAME =
           "WEB3MutualBalanceReferenceComparator(String l_strOrderBy, String l_strCompareItem)";
       log.entering(STR_METHOD_NAME);  
       
       if (l_strCompareItem == null || 
               (!l_strCompareItem.equals(WEB3MFSortkeyItemDef.TAX_TYPE) && 
               !l_strCompareItem.equals(WEB3MFSortkeyItemDef.MARKET_VALUE) && 
               !l_strCompareItem.equals(WEB3MFSortkeyItemDef.APPRAISAL_PROFIT_LOSS) && 
               !l_strCompareItem.equals(WEB3MFSortkeyItemDef.ORDER_CLOSE_TIME)&&
               !l_strCompareItem.equals(WEB3MFSortkeyItemDef.MUTUAL_PRODUCT_ID)))
       {
               throw new IllegalArgumentException(
                   "パラメータの値が'投信残高照会明細.口座区分'、'投信残高照会明細.評価額'、" +
                   "'投信残高照会明細.評価損益'、'投信残高照会明細.注文受付締切時間'、"+
                   "'投信残高照会明細.銘柄ID'以外です。");
       }  
       this.compareItem = l_strCompareItem;
       
       if (l_strOrderBy == null || 
               (!l_strOrderBy.equals(WEB3AscDescDef.ASC) && 
               !l_strOrderBy.equals(WEB3AscDescDef.DESC)))
       {
               throw new IllegalArgumentException(
                   "パラメータの値が'A：昇順'、'D：降順'以外です。");
       }       
       this.orderBy = l_strOrderBy;
       
       log.exiting(STR_METHOD_NAME);
   }
   
   /**
    * （compareの実装） <BR>
    * <BR>
    * 明細１，２について、this.比較項目で指定した項目の比較を行う。<BR>  
    * <BR>
    * １）　@引数のcast<BR>
    * パラメータ.明細1および2を、投信残高照会明細型にcastする。<BR>
    * <BR>
    * ３）　@比較 <BR>
    * 　@２）でcastした明細1、明細2について <BR>
    * 　@this.比較項目に該当する項目の値を比較し、結果を返却する。<BR>
    * <BR>
    * 　@[昇順指定の場合（this.orderBy == ”昇順”）] <BR>
    * 　@・（明細1.比較項目 < 明細2.比較項目）の場合、<BR>
    *        負の整数（-1）を返却する。 <BR>
    * 　@・（明細1.比較項目 == 明細2.比較項目）の場合、0を返却する。<BR> 
    * 　@・（明細1.比較項目 > 明細2.比較項目）の場合、<BR>
    *        正の整数（1）を返却する。 <BR>
    * <BR>
    * 　@[降順指定の場合（this.orderBy == ”降順”）] <BR>
    * 　@・（明細1.比較項目 < 明細2.比較項目）の場合、<BR>
    *        正の整数（1）を返却する。 <BR>
    * 　@・（明細1.比較項目 == 明細2.比較項目）の場合、0を返却する。 <BR>
    * 　@・（明細1.比較項目 > 明細2.比較項目）の場合、<BR>
    *        負の整数（-1）を返却する。 <BR>
    * @@param l_obj1 - 投信残高照会明細オブジェクト1
    * @@param l_obj2 - 投信残高照会明細オブジェクト2
    * @@return int
    * @@roseuid 41B7F69C0123
    */
   public int compare(Object l_obj1, Object l_obj2) 
   {
       final String l_strMethodName = "compare()";
       log.entering(l_strMethodName);
       
       WEB3MutualBalanceReferenceDetailUnit l_mfBalanceReferenceDetailUnit1 = null;
       WEB3MutualBalanceReferenceDetailUnit l_mfBalanceReferenceDetailUnit2 = null;
       
       if(l_obj1 instanceof WEB3MutualBalanceReferenceDetailUnit
               && l_obj2 instanceof WEB3MutualBalanceReferenceDetailUnit)
       {
           l_mfBalanceReferenceDetailUnit1 = (WEB3MutualBalanceReferenceDetailUnit)l_obj1;
           l_mfBalanceReferenceDetailUnit2 = (WEB3MutualBalanceReferenceDetailUnit)l_obj2;   
       }
       else
       {
           throw new IllegalArgumentException("パラメータの類型が不正、該当する" 
               + "'WEB3MutualBalanceReferenceDetailUnit'類型。");
       }

       Object l_compareItem1 = null;
       Object l_compareItem2 = null;     
       
       if(WEB3MFSortkeyItemDef.TAX_TYPE.equals(this.compareItem))
       {
           l_compareItem1 = l_mfBalanceReferenceDetailUnit1.taxType;
           l_compareItem2 = l_mfBalanceReferenceDetailUnit2.taxType;
       }
       
       else if(WEB3MFSortkeyItemDef.MARKET_VALUE.equals(this.compareItem))
       {
           if (l_mfBalanceReferenceDetailUnit1.marketValue != null)
           {
               l_compareItem1 = new Long(l_mfBalanceReferenceDetailUnit1.marketValue);
           }
           if (l_mfBalanceReferenceDetailUnit2.marketValue != null)
           {
               l_compareItem2 = new Long(l_mfBalanceReferenceDetailUnit2.marketValue);
           }
       }
       
       else if(WEB3MFSortkeyItemDef.APPRAISAL_PROFIT_LOSS.equals(this.compareItem))
       {
           if (l_mfBalanceReferenceDetailUnit1.appraisalProfitLoss != null)
           {
               l_compareItem1 = new Long(l_mfBalanceReferenceDetailUnit1.appraisalProfitLoss);
           }
           if (l_mfBalanceReferenceDetailUnit2.appraisalProfitLoss != null)
           {
               l_compareItem2 = new Long(l_mfBalanceReferenceDetailUnit2.appraisalProfitLoss);
           }
       }
       
       else if(WEB3MFSortkeyItemDef.ORDER_CLOSE_TIME.equals(this.compareItem))
       {
           if (l_mfBalanceReferenceDetailUnit1.orderCloseTime != null)
           {
               l_compareItem1 = WEB3DateUtility.getDate(l_mfBalanceReferenceDetailUnit1.orderCloseTime, "HH:mm");
           }
           if (l_mfBalanceReferenceDetailUnit2.orderCloseTime != null)
           {
               l_compareItem2 = WEB3DateUtility.getDate(l_mfBalanceReferenceDetailUnit2.orderCloseTime, "HH:mm");
           }
       }
       
       else if(WEB3MFSortkeyItemDef.MUTUAL_PRODUCT_ID.equals(this.compareItem))
       {
           if (l_mfBalanceReferenceDetailUnit1.mutualProductId != null)
           {
               l_compareItem1 = l_mfBalanceReferenceDetailUnit1.mutualProductId;
           }
           if (l_mfBalanceReferenceDetailUnit2.mutualProductId != null)
           {
               l_compareItem2 = l_mfBalanceReferenceDetailUnit2.mutualProductId;
           }
       }
       
       if (l_compareItem1 == null && l_compareItem2 == null)
       {
           return 0;
       }

       int l_intReturn ;
       if (l_compareItem1 == null || l_compareItem2 == null)
       {
           if (l_compareItem1 == null)
           {
               l_intReturn = 1;
           }
           else
           {
               l_intReturn = -1;
           }            
                        
       } 
       else
       {
           if (compareObj(l_compareItem1, l_compareItem2) < 0)
           {
               l_intReturn = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
           }
           else if (compareObj(l_compareItem1, l_compareItem2) > 0)
           {
               l_intReturn = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
           }
           else
           {
               l_intReturn = 0;
           }
                
       }

       log.exiting(l_strMethodName);
       return l_intReturn;
   }
   
   /**
    * （equalsの実装） <BR> 
    * <BR>
    * 未使用。  <BR>
    * falseを返却する。
    * @@param arg0
    * @@return Boolean
    * @@roseuid 41B7F69C0142
    */
   public boolean equals(Object arg0) 
   {
       return false;
   }
   
   /**
    * 二つのObjectの比較を行う。
    *
    * @@param l_obj1
    * @@param l_obj2
    * @@return 
    */
   private int compareObj(Object l_obj1, Object l_obj2)
   {        
       int l_intResult;
        
       if ((l_obj1 instanceof String) && (l_obj2 instanceof String))
       {
           l_intResult = ((String)l_obj1).compareTo((String)l_obj2);
       }
       else if ((l_obj1 instanceof Date) && (l_obj2 instanceof Date))
       {
           l_intResult = WEB3DateUtility.compareToMinute((Date)l_obj1, (Date)l_obj2);
       }
       else if ((l_obj1 instanceof Long) && (l_obj2 instanceof Long))
       {
           l_intResult = (((Long)l_obj1)).compareTo((Long)l_obj2);
       }
       else
       {
           throw new IllegalArgumentException("[Error]Parameter type is wrong! [l_obj1]=" + l_obj1 + " [l_obj2]" + l_obj2);
       }  
        
       return l_intResult;      
   }  
}
@
