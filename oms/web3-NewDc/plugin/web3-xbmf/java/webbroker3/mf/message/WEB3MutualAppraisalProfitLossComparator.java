head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.07.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualAppraisalProfitLossComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 評価損益Comparator実装クラス(WEB3MutualAppraisalProfitLossComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 周勇 (中訊) 新規作成
                   2004/08/23 于美麗 (中訊) レビュー 
*/
package webbroker3.mf.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;


/**
 * 評価損益Comparator実装クラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3MutualAppraisalProfitLossComparator implements Comparator 
{
    
    /**
     * A：昇順<BR>
     * D：降順<BR>
     */
    private String orderBy;
    
    /**
     * 評価損益Comparatorのコンストラクタ。<BR>
     * <BR>
     * パラメータ.orderByをフィールドのorderByにセットする<BR>
     * @@param l_strOrderBy - ソートキーの昇順降順を示す。<BR>
     * <BR>
     * A：昇順<BR>
     * D：降順<BR>
     * @@roseuid 40B1B86400AA
     */
    public WEB3MutualAppraisalProfitLossComparator(String l_strOrderBy) 
    {
        final String STR_METHOD_NAME =
            "WEB3AppraisalProfitLossComparator(String l_strOrderBy)";
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
            WEB3MutualAppraisalProfitLossComparator.class);   
              
    /**
     * 昇順、降順の指定にもとづく評価損益の比較を行う。<BR>
     * <BR>
     * １）パラメータを投信解約乗換銘柄一覧行クラスで引数をキャストする。<BR>
     * <BR>
     * ２）比較<BR>
     * <BR>
     * １）で判定したクラスのインスタンスobj1とobj2それぞれの評価損益について<BR>
     * <BR>
     * 昇順指定の場合、<BR>
     * パラメータ.obj1の評価損益が、<BR>
     * パラメータ.obj2の評価損益より小さい場合は負の整数、<BR>
     * 両方が等しい場合は0、<BR>
     * パラメータ.obj1の評価損益が、<BR>
     * パラメータ.obj2の評価損益より大きい場合は正の整数を返却する<BR>
     * <BR>
     * 降順指定の場合、<BR>
     * パラメータ.obj1の評価損益が、<BR>
     * パラメータ.obj2の評価損益より小さい場合は正の整数、<BR>
     * 両方が等しい場合は0、<BR>
     * パラメータ.obj1の評価損益が、<BR>
     * パラメータ.obj2の評価損益より大きい場合は負の整数を返却する<BR>
     * <BR>
     * 昇降順の判定はコンストラクタでセットされたorderByの値を用いる<BR>
     * <BR>
     * @@param l_obj1
     * @@param l_obj2
     * @@return int
     * @@roseuid 40B1B86400CA
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
            l_mutualSellSwitchingProductGroup1 = (WEB3MutualSellSwitchingProductGroup)l_obj1;
            l_mutualSellSwitchingProductGroup2 = (WEB3MutualSellSwitchingProductGroup)l_obj2;   
        }
        else
        {
            throw new IllegalArgumentException("パラメータの類型が不正、該当する" 
                    + "'WEB3MutualSellSwitchingProductGroup'類型。");
        }
        
        //U00889対応
        //srart
        if (l_mutualSellSwitchingProductGroup1.appraisalProfitLoss == null &&
              l_mutualSellSwitchingProductGroup2.appraisalProfitLoss == null)
        {
            return 0;
        }
        
        if (l_mutualSellSwitchingProductGroup1.appraisalProfitLoss == null)
        {
            return 1;
        }
        
        else if (l_mutualSellSwitchingProductGroup2.appraisalProfitLoss == null)
        {
            return -1;
        }
        
        //昇順指定の場合
        long l_longObj1 = Long.parseLong(l_mutualSellSwitchingProductGroup1.appraisalProfitLoss);
        long l_longObj2 = Long.parseLong(l_mutualSellSwitchingProductGroup2.appraisalProfitLoss);
        if(WEB3AscDescDef.ASC.equals(orderBy))
        {
            if(l_longObj1 < l_longObj2)
            {
                return -1;
            }
            else if(l_longObj1 == l_longObj2)
            {
                return 0;
            }
            else if(l_longObj1 > l_longObj2)
            {
                return 1;
            }            
        }
        //降順指定の場合
        else if(WEB3AscDescDef.DESC.equals(orderBy))
        {
            if(l_longObj1 < l_longObj2)
            {
                return 1;
            }
            else if(l_longObj1 == l_longObj2)
            {
                return 0;
            }
            else if(l_longObj1 > l_longObj2)
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
     * @@roseuid 40B1B86400D9
     */
    public boolean equals(Object l_obj) 
    {
        return super.equals(l_obj);
    }
}@
