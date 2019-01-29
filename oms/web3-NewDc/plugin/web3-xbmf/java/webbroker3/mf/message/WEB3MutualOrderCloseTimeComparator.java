head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.04.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualOrderCloseTimeComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文受付締切時間Comparator実装クラス(WEB3MutualOrderCloseTimeComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/17 韋念瓊 (中訊) 新規作成
                   2004/08/24 黄建 (中訊) レビュー   
*/

package webbroker3.mf.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;


/**
 * 注文受付締切時間Comparator実装クラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3MutualOrderCloseTimeComparator implements Comparator 
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualOrderCloseTimeComparator.class);
    
    /**
     * A：昇順<BR>
     * D：降順
     */
    private String orderBy;
    
    /**
     * 注文受付締切時間Comparatorのコンストラクタ。<BR>
     * <BR>
     * パラメータ.orderByをフィールドのorderByにセットする
     * @@param l_strOrderBy - ソートキーの昇順降順を示す。<BR>
     * <BR>
     * A：昇順<BR>
     * D：降順<BR>
     * @@roseuid 40B1B860005C
     */
    public WEB3MutualOrderCloseTimeComparator(String l_strOrderBy)
    {
        final String STR_METHOD_NAME =
            "WEB3OrderCloseTimeComparator(String l_strOrderBy)";
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
     * 昇順、降順の指定にもとづく注文受付締切時間の比較を行う。<BR>
     * <BR>
     * １）パラメータを投信解約乗換銘柄一覧行クラスで引数をキャストする。<BR>
     * <BR>
     * ２）比較<BR>
     * <BR>
     * １）で判定したクラスのインスタンスobj1とobj2それぞれの<BR>
     * 注文受付締切時間について<BR>
     * <BR>
     * 昇順指定の場合、<BR>
     * パラメータ.obj1の注文受付締切時間が、パラメータ.obj2の<BR>
     * 注文受付締切時間より小さい場合は負の整数、<BR>
     * 両方が等しい場合は0、<BR>
     * パラメータ.obj1の注文受付締切時間が、パラメータ.obj2の<BR>
     * 注文受付締切時間より大きい場合は正の整数を返却する<BR>
     * <BR>
     * 降順指定の場合、<BR>
     * パラメータ.obj1の注文受付締切時間が、パラメータ.obj2の<BR>
     * 注文受付締切時間より小さい場合は正の整数、<BR>
     * 両方が等しい場合は0、<BR>
     * パラメータ.obj1の注文受付締切時間が、パラメータ.obj2の<BR>
     * 注文受付締切時間より大きい場合は負の整数を返却する<BR>
     * <BR>
     * 昇降順の判定はコンストラクタでセットされたorderByの値を用いる<BR>
     * @@param l_obj1
     * @@param l_obj2
     * @@return int
     * @@roseuid 40B1B860006C
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME =
            "compare(Object l_obj1, Object l_obj2) ";
        log.entering(STR_METHOD_NAME);  
        
        String l_strVal1 = null;
        String l_strVal2 = null;
        
        //１）パラメータを投信解約乗換銘柄一覧行クラスで引数をキャストする。
        if ((l_obj1 instanceof WEB3MutualSellSwitchingProductGroup)
            && (l_obj2 instanceof WEB3MutualSellSwitchingProductGroup))
        {
            log.debug("WEB3MutualOrderGroup: ENTER");
            l_strVal1 = ((WEB3MutualSellSwitchingProductGroup)l_obj1).orderCloseTime;
            l_strVal2 = ((WEB3MutualSellSwitchingProductGroup)l_obj2).orderCloseTime;
            log.debug("WEB3MutualOrderGroup: END");
        }
        else
        {
            throw new IllegalArgumentException("パラメータの類型が不正、該当する" +
                        "'WEB3MutualSellSwitchingProductGroup'類型。");
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
     * @@roseuid 40B1B860008B
     */
    public boolean equals(Object l_obj) 
    {
        return super.equals(l_obj);
    }
}
@
