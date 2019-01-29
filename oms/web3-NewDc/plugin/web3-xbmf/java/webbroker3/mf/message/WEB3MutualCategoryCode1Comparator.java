head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.00.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualCategoryCode1Comparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信銘柄カテゴリーコード１Comparator(WEB3MutualCategoryCode1Comparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/03 黄建 (中訊) 新規作成 
*/

package webbroker3.mf.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (投信銘柄カテゴリーコード１Comparator)<BR>
 *  投信銘柄カテゴリーコード１Comparator実装クラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3MutualCategoryCode1Comparator implements Comparator 
{    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualCategoryCode1Comparator.class);
    /**
     * (orderBy)<BR>
     * A：昇順<BR>
     * D：降順
     */
    private String orderBy;
    
    /**
     * (投信銘柄カテゴリーコード１Comparator)<BR>
     * 投信銘柄カテゴリーコード１Comparatorのコンストラクタ。<BR>
     * <BR>
     * パラメータ.orderByをフィールドのorderByにセットする
     * @@param l_strOrderBy - ソートキーの昇順降順を示す。<BR>
     * <BR>
     * A：昇順<BR>
     * D：降順
     * @@roseuid 4153C1AE0392
     */
    public WEB3MutualCategoryCode1Comparator(String l_strOrderBy)
    {
        final String STR_METHOD_NAME =
            "WEB3MutualCategoryCode1Comparator(String l_strOrderBy)";
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
     * (compare)<BR>
     * 昇順、降順の指定にもとづく投信銘柄カテゴリー１の比較を行う。<BR>
     * <BR>
     * １）パラメータを管理者銘柄表示順序登録一覧行クラスで引数をキャストする。<BR>
     * <BR>
     * ２）比較<BR>
     * <BR>
     * １）で判定したクラスのインスタンスobj1と<BR>
     *     obj2それぞれの投信銘柄カテゴリーコード１について<BR>
     * <BR>
     *     昇順指定の場合、<BR>
     *     パラメータ.obj1の投信銘柄カテゴリーコード１が、<BR>
     *     パラメータ.obj2の投信銘柄カテゴリーコード１より小さい場合は負の整数、<BR>
     *    両方が等しい場合は0、<BR>
     *    パラメータ.obj1の投信銘柄カテゴリーコード１が、<BR>
     *    パラメータ.obj2の投信銘柄カテゴリーコード１より大きい場合<BR>
     *    は正の整数を返却する<BR>
     * <BR>
     *    降順指定の場合、<BR>
     *    パラメータ.obj1の投信銘柄カテゴリーコード１が、<BR>
     *    パラメータ.obj2の投信銘柄カテゴリーコード１より小さい場合は正の整数、<BR>
     *    両方が等しい場合は0、<BR>
     *    パラメータ.obj1の投信銘柄カテゴリーコード１が、<BR>
     *    パラメータ.obj2の投信銘柄カテゴリーコード１より大きい場合<BR>
     *    は負の整数を返   却する<BR>
     * <BR>
     *    昇降順の判定はコンストラクタでセットされたorderByの値を用いる
     * @@param l_obj1
     * @@param l_obj2
     * @@return int
     * @@roseuid 4153C1AE03A2
     */
    public int compare(Object l_obj1, Object l_obj2)
    {
        final String STR_METHOD_NAME = "compare(Object l_obj1, Object l_obj2) ";
        log.entering(STR_METHOD_NAME);
        
        //１）パラメータを管理者銘柄表示順序登録一覧行クラスで引数をキャストする。
        String l_strVal1 = null;
        String l_strVal2 = null;
        if (l_obj1 instanceof WEB3MutualDisplayOrderGroup && 
            l_obj2 instanceof WEB3MutualDisplayOrderGroup)
        {
            l_strVal1 = 
                ((WEB3MutualDisplayOrderGroup) l_obj1).categoryCode1;
            l_strVal2 = 
                ((WEB3MutualDisplayOrderGroup) l_obj2).categoryCode1;
        }
        else
        {
            throw new IllegalArgumentException("パラメータの類型が不正、該当する" 
                + "'WEB3MutualDisplayOrderGroup'類型。");
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
            if (l_strVal1.equals(l_strVal2))
            {
                l_intReturn = 0;
            }
            else if (l_strVal1.compareTo(l_strVal2) > 0)
            {
                if (WEB3AscDescDef.ASC.equals(this.orderBy))      //昇順指定の場合
                {
                    l_intReturn = 1;
                }
                else                                             //降順指定の場合
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
        }

        log.exiting(STR_METHOD_NAME);
        return l_intReturn;
    }
    
    /**
     * パラメータのオブジェクトがこのコンパレータと等しいかどうかを判定する。<BR>
     * <BR>
     * スーパークラスのequalsをコールする。
     * @@param l_obj
     * @@return boolean
     * @@roseuid 4153C0820279
     */
    public boolean equals(Object l_obj)
    {
        return super.equals(l_obj);
    }
}
@
