head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.06.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFundTradeDivComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 売買区分(投信)Comparator実装クラス(WEB3MutualFundTradeDivComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 周勇 (中訊) 新規作成
                   2004/08/23 于美麗 (中訊) レビュー 
*/
package webbroker3.mf.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * 売買区分(投信)Comparator実装クラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3MutualFundTradeDivComparator implements Comparator 
{
    
    /**
     * A：昇順<BR>
     * D：降順<BR>
     */
    private String orderBy;
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualFundTradeDivComparator.class);    
    /**
     * 売買区分(投信)Comparatorのコンストラクタ。<BR>
     * <BR>
     * パラメータ.orderByをフィールドのorderByにセットする<BR>
     * @@param l_strOrderBy - ソートキーの昇順降順を示す。<BR>
     * <BR>
     * A：昇順<BR>
     * D：降順<BR>
     * @@roseuid 40B5A0D70317
     */
    public WEB3MutualFundTradeDivComparator(String l_strOrderBy) 
    {
        final String STR_METHOD_NAME =
            "WEB3MutualFundTradeDivComparator(String l_strOrderBy)";
        log.entering(STR_METHOD_NAME);  
        
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
     * 昇順、降順の指定にもとづく売買区分(投信)の比較を行う。<BR>
     * <BR>
     * １）パラメータを投信注文照会注文単位クラスで引数をキャストする。<BR>
     * <BR>
     * ２）比較<BR>
     * <BR>
     * １）で判定したクラスのインスタンスobj1とobj2それぞれの売買区分(投信)について<BR>
     * <BR>
     * 昇順指定の場合、<BR>
     * パラメータ.obj1の売買区分(投信)が、パラメータ.obj2の売買区分(投信)より小さい場合は負の整数、<BR>
     * 両方が等しい場合は0、<BR>
     * パラメータ.obj1の売買区分(投信)が、パラメータ.obj2の売買区分<BR>
     * (投信)より大きい場合は正の整数を返却する<BR>
     * <BR>
     * 降順指定の場合、<BR>
     * パラメータ.obj1の売買区分(投信)が、パラメータ.obj2の売買区分<BR>
     * (投信)より小さい場合は正の整数、<BR>
     * 両方が等しい場合は0、<BR>
     * パラメータ.obj1の売買区分(投信)が、パラメータ.obj2の売買区分<BR>
     * (投信)より大きい場合は負の整数を返却する<BR>
     * <BR>
     * 昇降順の判定はコンストラクタでセットされたorderByの値を用いる<BR>
     * @@param l_obj1
     * @@param l_obj2
     * @@return int
     * @@roseuid 40B5A0D70327
     */
    public int compare(Object l_obj1, Object l_obj2)
    {
        final String STR_METHOD_NAME =
            "compare(Object l_obj1, Object l_obj2) ";
        log.entering(STR_METHOD_NAME);       
        
        WEB3MutualOrderGroup l_mutualOrderGroupObj1 = null;
        WEB3MutualOrderGroup l_mutualOrderGroupObj2 = null;
        
        if(l_obj1 instanceof WEB3MutualOrderGroup 
                && l_obj2 instanceof WEB3MutualOrderGroup)
        {
            l_mutualOrderGroupObj1 = (WEB3MutualOrderGroup)l_obj1;
            l_mutualOrderGroupObj2 = (WEB3MutualOrderGroup)l_obj2;            
        }
        else
        {
            throw new IllegalArgumentException("パラメータの類型が不正、該当する" 
                    + "'WEB3MutualOrderGroup'類型。");
        }
        
        String l_strVal1 = l_mutualOrderGroupObj1.mutualDealingType;
        String l_strVal2 = l_mutualOrderGroupObj2.mutualDealingType;
        
        if (l_strVal1 == null && l_strVal2 == null)
        {
            return 0;
        }
        
        if (l_strVal1 == null)
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))    //昇順指定の場合
            {
                return -1;
            }
            else                                            //降順指定の場合
            {
                return 1;
            }
        }

        if (l_strVal2 == null)
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))    //昇順指定の場合
            {
                return 1;
            }
            else                                            //降順指定の場合
            {
                return -1;
            }
        }

        int l_intReturn = 0;
        if (l_strVal1.equals(l_strVal2))
        {
            l_intReturn = 0;
        }
        else if (l_strVal1.compareTo(l_strVal2) > 0)
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))    //昇順指定の場合
            {
                l_intReturn = 1;
            }
            else                                            //降順指定の場合
            {
                l_intReturn = -1;
            }
        }
        else
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))    //昇順指定の場合
            {
                l_intReturn = -1;
            }
            else                                            //降順指定の場合
            {
                l_intReturn = 1;
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_intReturn;
    }
    
    /**
     * パラメータのオブジェクトがこのコンパレータと等しいかどうかを判定する。<BR>
     * <BR>
     * スーパークラスのequalsをコールする。<BR>
     * @@param l_obj
     * @@return boolean
     * @@roseuid 40B5A0D70336
     */
    public boolean equals(Object l_obj) 
    {
        return super.equals(l_obj);
    }
}
@
