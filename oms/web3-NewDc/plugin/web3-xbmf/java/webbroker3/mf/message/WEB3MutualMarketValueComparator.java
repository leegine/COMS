head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.10.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualMarketValueComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 評価額Comparator実装クラス(WEB3MutualMarketValueComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 周勇 (中訊) 新規作成
                   2004/08/23 于美麗 (中訊) レビュー 
*/
package webbroker3.mf.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * 評価額Comparator実装クラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3MutualMarketValueComparator implements Comparator 
{
    
    /**
     * A：昇順<BR>
     * D：降順<BR>
     */
    private String orderBy;
    
    /**
     * 評価額Comparatorのコンストラクタ。<BR>
     * <BR>
     * パラメータ.orderByをフィールドのorderByにセットする<BR>
     * @@param orderBy - ソートキーの昇順降順を示す。<BR>
     * <BR>
     * A：昇順<BR>
     * D：降順<BR>
     * @@roseuid 40B1B26000AA
     */
    public WEB3MutualMarketValueComparator(String l_strOrderBy) 
    {
        final String STR_METHOD_NAME =
            "WEB3MarketValueComparator(String l_strOrderBy)";
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
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualMarketValueComparator.class); 
               
    /**
     * 昇順、降順の指定にもとづく評価額の比較を行う。<BR>
     * <BR>
     * １）パラメータを投信解約乗換銘柄一覧行クラスで引数をキャストする。<BR>
     * <BR>
     * ２）比較<BR>
     * <BR>
     * １）で判定したクラスのインスタンスobj1とobj2それぞれの評価額について<BR>
     * <BR>
     * 昇順指定の場合、<BR>
     * パラメータ.obj1の評価額が、<BR>
     * パラメータ.obj2の評価額より小さい場合は負の整数、<BR>
     * 両方が等しい場合は0、<BR>
     * パラメータ.obj1の評価額が、<BR>
     * パラメータ.obj2の評価額より大きい場合は正の整数を返却する<BR>
     * <BR>
     * 降順指定の場合、<BR>
     * パラメータ.obj1の評価額が、パラメータ.obj2の評価額より小さい場合は正の整数、<BR>
     * 両方が等しい場合は0、<BR>
     * パラメータ.obj1の評価額が、<BR>
     * パラメータ.obj2の評価額より大きい場合は負の整数を返却する<BR>
     * <BR>
     * 昇降順の判定はコンストラクタでセットされたorderByの値を用いる<BR>
     * <BR>
     * @@param l_obj1
     * @@param l_obj2
     * @@return int
     * @@roseuid 40B1B26000AC
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME =
            "compare(Object l_obj1, Object l_obj2) ";
        log.entering(STR_METHOD_NAME);   
      
        WEB3MutualSellSwitchingProductGroup l_mutualSellSwitchingProductGroup1 = null;
        WEB3MutualSellSwitchingProductGroup l_mutualSellSwitchingProductGroup2 = null;
        if(l_obj1 instanceof WEB3MutualSellSwitchingProductGroup
                && l_obj2 instanceof WEB3MutualSellSwitchingProductGroup)
        {
            l_mutualSellSwitchingProductGroup1 = 
                (WEB3MutualSellSwitchingProductGroup)l_obj1;
            l_mutualSellSwitchingProductGroup2 = 
                (WEB3MutualSellSwitchingProductGroup)l_obj2;            
        } 
        else
        {
            throw new IllegalArgumentException("パラメータの類型が不正、該当する" 
                    + "'WEB3MutualSellSwitchingProductGroup'類型。");
        }

        //U00854対応
        //start
        //昇順指定の場合
        long l_longVal1 = Long.parseLong(l_mutualSellSwitchingProductGroup1.marketValue);
        long l_longVal2 = Long.parseLong(l_mutualSellSwitchingProductGroup2.marketValue);
        if(WEB3AscDescDef.ASC.equals(orderBy))
        {
            if(l_longVal1 < l_longVal2)
            {
                return -1;
            }
            else if(l_longVal1 == l_longVal2)
            {
                return 0;
            }
            else if(l_longVal1 > l_longVal2)
            {
                return 1;
            }            
        }
        // 降順指定の場合
        else if(WEB3AscDescDef.DESC.equals(orderBy))
        {
            if(l_longVal1 < l_longVal2)
            {
                return 1;
            }
            else if(l_longVal1 == l_longVal2)
            {
                return 0;
            }
            else if(l_longVal1 > l_longVal2)
            {
                return -1;
            }
        }        
        log.exiting(STR_METHOD_NAME); 
    return 0;
    }
    //end
    
    /**
     * パラメータのオブジェクトがこのコンパレータと等しいかどうかを判定する。<BR>
     * <BR>
     * スーパークラスのequalsをコールする。<BR>
     * @@param l_obj
     * @@return boolean
     * @@roseuid 40B1B26000AF
     */
    public boolean equals(Object l_obj) 
    {
        return super.equals(l_obj);
    }
}
@
