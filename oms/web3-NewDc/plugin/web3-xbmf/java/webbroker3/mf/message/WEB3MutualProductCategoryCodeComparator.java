head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.07.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualProductCategoryCodeComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信銘柄カテゴリーComparator(WEB3MutualProductCategoryCodeComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/24 栄イ (中訊) 新規作成 
*/
package webbroker3.mf.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (投信銘柄カテゴリーComparator)<BR>
 *  投信銘柄カテゴリーComparator実装クラス
 * @@author 栄イ(中訊)
 * @@version 1.0 
 */
public class WEB3MutualProductCategoryCodeComparator implements Comparator
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualProductCategoryCodeComparator.class);
    
    /**
     * (orderBy)<BR>
     * A：昇順 <BR>
     * D：降順<BR>
     */
    private String orderBy;
    
    /**
     * (投信銘柄カテゴリーComparator)<BR>
     * 投信銘柄カテゴリーComparatorのコンストラクタ。<BR>
     * パラメータ.orderByをフィールドのorderByにセットする<BR>
     * @@param l_strOrderBy - (orderBy)<BR>
     */
    public WEB3MutualProductCategoryCodeComparator(String l_strOrderBy)
    {
        final String STR_METHOD_NAME = 
            "WEB3MutualProductCategoryCodeComparator(String)";
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
     * 昇順、降順の指定にもとづく投信銘柄カテゴリーコードの比較を行う。 <BR>
     * <BR>
     * １）パラメータを投信定時定額買付条件行クラスで引数をキャストする。 <BR>
     * <BR>
     * ２）比較 <BR>
     * <BR>
     * １）で判定したクラスのインスタンスobj1とobj2それぞれの投信銘柄カテゴリーコードについて <BR>
     * <BR>
     * 昇順指定の場合、 <BR>
     * パラメータ.obj1の投信銘柄カテゴリーコードが、<BR>
     * パラメータ.obj2の投信銘柄カテゴリーコードより小さい場合は負の整数、 <BR>
     * 両方が等しい場合は0、 <BR>
     * パラメータ.obj1の投信銘柄カテゴリーコードが、<BR>
     * パラメータ.obj2の投信銘柄カテゴリーコードより大きい場合は正の整数を返却する <BR>
     * <BR>
     * 降順指定の場合、 <BR>
     * パラメータ.obj1の投信銘柄カテゴリーコードが、<BR>
     * パラメータ.obj2の投信銘柄カテゴリーコードより小さい場合は正の整数、 <BR>
     * 両方が等しい場合は0、 <BR>
     * パラメータ.obj1の投信銘柄カテゴリーコードが、<BR>
     * パラメータ.obj2の投信銘柄カテゴリーコードより大きい場合は負の整数を返却する <BR>
     * <BR>
     * 昇降順の判定はコンストラクタでセットされたorderByの値を用いる <BR>
     * <BR>
     * @@param l_obj1 - (obj1)<BR>
     * @@param l_obj2 - (obj1)<BR>
     * @@return int<BR>
     */
    public int compare(Object l_obj1, Object l_obj2)
    {
        final String STR_METHOD_NAME = "compare(Object, Object) ";
        log.entering(STR_METHOD_NAME);
        
        //１）パラメータを投信定時定額買付条件行クラスで引数をキャストする。
        String l_strVal1 = null;
        String l_strVal2 = null;
        if (l_obj1 instanceof WEB3MutualFixedBuyConditionUnit && 
            l_obj2 instanceof WEB3MutualFixedBuyConditionUnit)
        {
            l_strVal1 = 
                ((WEB3MutualFixedBuyConditionUnit) l_obj1).categoryCode;
            l_strVal2 = 
                ((WEB3MutualFixedBuyConditionUnit) l_obj2).categoryCode;
        }
        else
        {
            throw new IllegalArgumentException("パラメータの類型が不正、該当する" 
                + "'WEB3MutualFixedBuyConditionUnit'類型。");
        }
        //２）比較
        int l_intReturn = 0;
        if (l_strVal1 == null || l_strVal2 == null)
        {
               
            if (l_strVal1 == null && l_strVal2 == null)
            {
                l_intReturn = 0;
            }
            else if (l_strVal1 == null)
            {
                l_intReturn = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
            }
            else
            {
                l_intReturn = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
            }            
                        
        }    
        else
        {
            //昇順指定の場合
            if(WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                //パラメータ.obj1の投信銘柄カテゴリーコードが、
                //パラメータ.obj2の投信銘柄カテゴリーコードより小さい場合は負の整数
                if(l_strVal1.compareTo(l_strVal2) < 0)
                {
                    l_intReturn = -1;
                }
                //両方が等しい場合は0
                else if(l_strVal1.equals(l_strVal2))
                {
                    l_intReturn = 0;
                }
                //パラメータ.obj1の投信銘柄カテゴリーコードが、
                //パラメータ.obj2の投信銘柄カテゴリーコードより大きい場合は正の整数を返却する
                else
                {
                    l_intReturn = 1;
                }
            }
            //降順指定の場合
            else
            {
                //パラメータ.obj1の投信銘柄カテゴリーコードが、
                //パラメータ.obj2の投信銘柄カテゴリーコードより小さい場合は正の整数
                if(l_strVal1.compareTo(l_strVal2) < 0)
                {
                    l_intReturn = 1;
                }
                //両方が等しい場合は0
                else if(l_strVal1.equals(l_strVal2))
                {
                    l_intReturn = 0;
                }
                //パラメータ.obj1の投信銘柄カテゴリーコードが、
                //パラメータ.obj2の投信銘柄カテゴリーコードより大きい場合は負の整数を返却する
                else
                {
                    l_intReturn = -1;
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_intReturn;
    }
    
    /**
     * パラメータのオブジェクトがこのコンパレータと等しいかどうかを判定する。<BR>
     * スーパークラスのequalsをコールする。<BR>
     * @@param l_obj - (obj)<BR>
     * @@return boolean<BR>
     */
    public boolean equals(Object l_obj)
    {
        return super.equals(l_obj);
    }
}
@
