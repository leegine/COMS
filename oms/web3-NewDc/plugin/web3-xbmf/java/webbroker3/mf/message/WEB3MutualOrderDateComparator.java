head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.02.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualOrderDateComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文日時Comparator実装クラス(WEB3MutualOrderDateComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/17 韋念瓊 (中訊) 新規作成
                   2004/08/24 黄建 (中訊) レビュー                          
*/

package webbroker3.mf.message;

import java.util.Comparator;
import java.util.Date;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * 注文日時Comparator実装クラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3MutualOrderDateComparator implements Comparator 
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualOrderDateComparator.class);
        
    /**
     * A：昇順<BR>
     * D：降順
     */
    private String orderBy;
    
    /**
     * 注文日時Comparatorのコンストラクタ。<BR>
     * <BR>
     * パラメータ.orderByをフィールドのorderByにセットする<BR>
     * @@roseuid 40B5A1250133
     */
    public WEB3MutualOrderDateComparator(String l_strOrderBy)
    {
        final String STR_METHOD_NAME =
            "WEB3OrderDateComparator(String l_strOrderBy)";
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
     * 昇順、降順の指定にもとづく注文日時の比較を行う。<BR>
     * <BR>
     * １）パラメータを投信注文照会注文単位クラスで引数をキャストする。<BR>
     * <BR>
     * ２）比較<BR>
     * <BR>
     * １）で判定したクラスのインスタンスobj1とobj2それぞれの注文日時について<BR>
     * <BR>
     * 昇順指定の場合、<BR>
     * パラメータ.obj1の注文日時が、パラメータ.obj2の<BR>
     * 注文日時より小さい場合は負の整数、<BR>
     * 両方が等しい場合は0、<BR>
     * パラメータ.obj1の注文日時が、パラメータ.obj2の<BR>
     * 注文日時より大きい場合は正の整数を返却する<BR>
     * <BR>
     * 降順指定の場合、<BR>
     * パラメータ.obj1の注文日時が、パラメータ.obj2の<BR>
     * 注文日時より小さい場合は正の整数、<BR>
     * 両方が等しい場合は0、<BR>
     * パラメータ.obj1の注文日時が、パラメータ.obj2の<BR>
     * 注文日時より大きい場合は負の整数を返却する<BR>
     * <BR>
     * 昇降順の判定はコンストラクタでセットされたorderByの値を用いる<BR>
     * @@param l_obj1
     * @@param l_obj2
     * @@return int
     * @@roseuid 40B5A1250142
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME =
            "compare(Object l_obj1, Object l_obj2) ";
        log.entering(STR_METHOD_NAME);  
         
        Date l_datVal1 = null;
        Date l_datVal2 = null;
        
        //１）パラメータを投信注文照会注文単位クラスで引数をキャストする。
        if ((l_obj1 instanceof WEB3MutualOrderGroup)    //投信注文照会注文単位
            && (l_obj2 instanceof WEB3MutualOrderGroup))
        {
            log.debug("WEB3MutualOrderGroup: ENTER");
            l_datVal1 = ((WEB3MutualOrderGroup)l_obj1).orderDate;
            l_datVal2 = ((WEB3MutualOrderGroup)l_obj2).orderDate;
            log.debug("WEB3MutualOrderGroup: END");
        }
        else
        {
            throw new IllegalArgumentException("パラメータの類型が不正、該当する" +
                        "'WEB3MutualOrderGroup'類型。");
        }
        //２）比較              
        int l_intReturn = 0;
        if (l_datVal1 == null || l_datVal2 == null)
        {
               
            if (l_datVal1 == null && l_datVal2 == null)
            {
                l_intReturn = 0;
            }
            else if (l_datVal1 == null)
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
            if (WEB3DateUtility.compareToSecond(l_datVal1, l_datVal2) == 0)
            {
                l_intReturn = 0;
            }
            else if (WEB3DateUtility.compareToSecond(l_datVal1, l_datVal2) > 0)
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
     * @@roseuid 40B5A1250152
     */
    public boolean equals(Object l_obj) 
    {
        return super.equals(l_obj);
    }
}
@
