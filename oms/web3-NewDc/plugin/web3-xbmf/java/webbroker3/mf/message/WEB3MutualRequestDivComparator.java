head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.01.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualRequestDivComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 請求方法@Comparator実装クラス(WEB3MutualRequestDivComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/05 李志強 (日本中訊) 新規作成　@　@U00367の暫定対応
*/

package webbroker3.mf.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;


/**
 * 請求方法@Comparator実装クラス<BR>
 *
 * @@author 李志強 (日本中訊)
 * @@version 1.0
 */
public class WEB3MutualRequestDivComparator implements Comparator
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualRequestDivComparator.class);

    /**
     * A：昇順<BR>
     * D：降順<BR>
     */
    private String orderBy;

    /**
     * (請求方法@Comparator)<BR>
     * 請求方法@Comparatorのコンストラクタ。<BR>
     * <BR>
     * パラメータ.orderByをフィールドのorderByにセットする
     * @@param l_strOrderBy - ソートキーの昇順降順を示す。<BR>
     * <BR>
     * A：昇順<BR>
     * D：降順
     * @@roseuid 418B2F3900ED
     */
    public WEB3MutualRequestDivComparator(String l_strOrderBy)
    {
        final String STR_METHOD_NAME =
            "WEB3MutualRequestDivComparator(String l_strOrderBy)";
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
     * 昇順、降順の指定にもとづく請求方法@の比較を行う。<BR>
     * <BR>
     * １）パラメータのオブジェクトの判定 <BR>
     * <BR>
     *    instanceofにて、パラメータのオブジェクトobj1、
     *    obj2が以下のクラスのどちらかを判定する。 <BR>
     * <BR>
     *    投信注文照会注文単位クラス <BR>
     * <BR>
     * ２）比較 <BR>
     * <BR>
     * １）で判定したクラスのインスタンスobj1とobj2それぞれの表示順について <BR>
     * <BR>
     *     昇順指定の場合、<BR>
     *     パラメータ.obj1の請求区分が、<BR>
     *     パラメータ.obj2の請求区分より小さい場合は負の整数、<BR>
     *     両方が等しい場合は0、 <BR>
     *     パラメータ.obj1の請求区分が、<BR>
     *     パラメータ.obj2の請求区分より大きい場合は正の整数を返却する <BR>
     * <BR> 
     *    降順指定の場合、 <BR>
     *    パラメータ.obj1の請求区分が、<BR>
     *    パラメータ.obj2の請求区分より小さい場合は正の整数、 <BR>
     *    両方が等しい場合は0、 <BR>
     *    パラメータ.obj1の請求区分が、<BR>
     *    パラメータ.obj2の請求区分より大きい場合は負の整数を返却する <BR>
     * <BR>
     *    昇降順の判定はコンストラクタでセットされたorderByの値を用いる<BR>
     *  ・public boolean equals (Object obj)<BR>
     *      （他のComparator実装クラス参照）<BR>
     * <BR>
     *    (*)<BR>
     *    －パラメータ.obj1の請求方法@=nullの場合、<BR>
     *       昇順指定の場合は負の整数を返却する。<BR>
     * 　@    パラメータ.obj1の請求方法@=nullの場合、<BR>
     *       降順指定の場合は正の整数を返却する。<BR>
     *    －パラメータ.obj2の請求方法@=nullの場合、<BR>
     *       昇順指定の場合は正の整数を返却する。<BR>
     * 　@    パラメータ.obj2の請求方法@=nullの場合、<BR>
     *       降順指定の場合は負の整数を返却する。<BR>
     *    －どちらも null の場合は、0を返却する。
     * @@param l_obj1
     * @@param l_obj2
     * @@return int
     * @@roseuid 418B2F3900FD
     */
    public int compare(Object l_obj1, Object l_obj2)
    {
        final String STR_METHOD_NAME =
            "compare(Object l_obj1, Object l_obj2) ";
        log.entering(STR_METHOD_NAME);

        String l_strVal1 = null;
        String l_strVal2 = null;        

        if ((l_obj1 instanceof WEB3MutualOrderGroup)  //投信注文照会注文単位
            && (l_obj2 instanceof WEB3MutualOrderGroup))
        {
            log.debug("WEB3MutualOrderGroup: ENTER");
            l_strVal1 = ((WEB3MutualOrderGroup)l_obj1).sellBuyDiv;
            l_strVal2 = ((WEB3MutualOrderGroup)l_obj2).sellBuyDiv;
            log.debug("WEB3MutualOrderGroup: END");
        }
        else
        {
            throw new IllegalArgumentException("パラメータの類型が不正、該当する" +
                "'WEB3MutualOrderGroup' 類型。");
        }
        
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
     * @@roseuid 418B2F39010D
     */
    public boolean equals(Object l_obj)
    {
        return super.equals(l_obj);
    }
}
@
