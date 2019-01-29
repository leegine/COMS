head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.10.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualProductCodeComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 銘柄コードComparator(WEB3MutualProductCodeComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/03 黄建 (中訊) 新規作成 
                 : 2006/07/24 栄イ (中訊) 仕様変更モデル No.407
*/

package webbroker3.mf.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (銘柄コードComparator)<BR>
 *  銘柄コードComparator実装クラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3MutualProductCodeComparator implements Comparator 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualProductCodeComparator.class);
    
    /**
     * (orderBy)<BR>
     * A：昇順<BR>
     * D：降順
     */
    private String orderBy;
    
    /**
     * (銘柄コードComparator)<BR>
     * 銘柄コードComparatorのコンストラクタ。<BR>
     * <BR>
     * パラメータ.orderByをフィールドのorderByにセットする
     * @@param l_strOrderBy - ソートキーの昇順降順を示す。<BR>
     * <BR>
     * A：昇順<BR>
     * D：降順
     * @@roseuid 4153C01F02F6
     */
    public WEB3MutualProductCodeComparator(String l_strOrderBy)
    {
        final String STR_METHOD_NAME =
            "WEB3MutualProductCodeComparator(String l_strOrderBy)";
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
     * 昇順、降順の指定にもとづく銘柄コードの比較を行う。<BR>
     * <BR>
     * １）パラメータのオブジェクトの判定<BR>
     * <BR>
     *    instanceofにて、パラメータのオブジェクトobj1、<BR>
     *    obj2が以下のクラスのどちらかを判定する。<BR>
     * <BR>
     * 　@ 管理者銘柄表示順序登録一覧行クラス<BR>
     *    投信定時定額買付条件行クラス <BR>
     * <BR>
     * ２）比較<BR>
     * <BR>
     * １）で判定したクラスのインスタンスobj1とobj2それぞれの表示順について<BR>
     * <BR>
     *    昇順指定の場合、<BR>
     *    パラメータ.obj1の銘柄コードが、
     *    パラメータ.obj2の銘柄コードより小さい場合は負の整数、<BR>
     *    両方が等しい場合は0、<BR>
     *    パラメータ.obj1の銘柄コードが、<BR>
     *    パラメータ.obj2の銘柄コードより大きい場合は正の整数を返却する<BR>
     * <BR>
     *    降順指定の場合、<BR>
     *    パラメータ.obj1の銘柄コードが、<BR>
     *    パラメータ.obj2の銘柄コードより小さい場合は正の整数、<BR>
     *    両方が等しい場合は0、<BR>
     *    パラメータ.obj1の銘柄コードが、<BR>
     *    パラメータ.obj2の銘柄コードより大きい場合は負の整数を返却する<BR>
     * <BR>
     *    昇降順の判定はコンストラクタでセットされたorderByの値を用いる
     * @@param l_obj1
     * @@param l_obj2
     * @@return int
     * @@roseuid 4153C01F0305
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
                ((WEB3MutualDisplayOrderGroup) l_obj1).mutualProductCode;
            l_strVal2 = 
                ((WEB3MutualDisplayOrderGroup) l_obj2).mutualProductCode;
        }
        else if (l_obj1 instanceof WEB3MutualFixedBuyConditionUnit && 
            l_obj2 instanceof WEB3MutualFixedBuyConditionUnit)
        {
            l_strVal1 = 
                ((WEB3MutualFixedBuyConditionUnit) l_obj1).mutualProductCode;
            l_strVal2 = 
                ((WEB3MutualFixedBuyConditionUnit) l_obj2).mutualProductCode;
        }
        else
        {
            throw new IllegalArgumentException("パラメータの類型が不正、該当する" 
                + "'WEB3MutualDisplayOrderGroup'or'WEB3MutualFixedBuyConditionUnit'類型。");
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
