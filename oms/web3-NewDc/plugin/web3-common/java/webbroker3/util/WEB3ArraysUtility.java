head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ArraysUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 配列ユーティリティ(WEB3ArraysUtility.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 張宝楠 (中訊) 新規作成
*/

package webbroker3.util;

import java.util.Comparator;

/**
 * 配列のソート処理を行う関数を持つユーティリティクラス。<BR>
 * <BR>
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3ArraysUtility 
{
   
    /**
     * 指定されたコンパレータの配列にもとづいてオブジェクト配列のソートを行う。<BR>
     * <BR>
     * パラメータ.l_comに格納されているコンパレータ順に<BR>
     * パラメータ.l_objのソートを行う<BR>
     * <BR>
     * EX)<BR>
     * パラメータ.l_comが以下であった場合：<BR>
     * l_com[0] = 建区分Comparator<BR>
     * l_com[1] = 建年月日Comparator<BR>
     * l_com[2] = 損益Comparator<BR>
     * <BR>
     * 1.パラメータ.l_objを建区分でソートする<BR>
     * <BR>
     * 2.1でソートされたl_objに対して建区分が同じオブジェクトを建年月日でソートする<BR>
     * <BR>
     * 3.2でソートされたl_objに対して建年月日が同じオブジェクトを損益でソートする<BR>
     * @@param l_obj
     * @@param l_com
     * @@roseuid 407BDD7A02A3
     */
    public static void sort(Object[] l_obj, Comparator[] l_com) 
    {
        if (l_obj == null || l_com == null || l_com.length == 0) 
        {
            return;      
        }
             
        for (int i = 0; i < (l_obj.length - 1); i++) 
        {
            for (int j = i + 1; j < l_obj.length; j++) 
            {
                if (compare(l_obj[i], l_obj[j], l_com) > 0) 
                {                    
                    swap(l_obj, i, j);
                }
            }
        }
    }
    
    /**
     * Compare l_obj1 and l_obj2.
     * 
     * @@param l_obj1
     * @@param l_obj2
     * @@param l_com
     */
    private static int compare(Object l_obj1, Object l_obj2, Comparator[] l_com) 
    {
        
        int l_comResult = 0; 
        
        for (int i = 0; i < l_com.length; i++) 
        {
            if (l_com[i] == null) 
            {
                continue;
            }
               
            l_comResult = l_com[i].compare(l_obj1, l_obj2);
            if (l_comResult != 0) 
            {
                break;
            }
        }
        
        return l_comResult;
    }    
   
    /**
     * Swaps x[a] with x[b].
     * 
     * @@param l_x 配列
     * @@param l_a index1
     * @@param l_b index2
     */
    private static void swap(Object[] l_x, int l_a, int l_b) 
    {
        Object l_t = l_x[l_a];
        l_x[l_a] = l_x[l_b];
        l_x[l_b] = l_t;
    }
   
}
@
