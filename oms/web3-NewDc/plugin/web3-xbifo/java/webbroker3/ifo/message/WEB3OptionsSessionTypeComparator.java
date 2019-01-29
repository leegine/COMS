head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.11.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsSessionTypeComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 立会区分Comparator(WEB3OptionsSessionTypeComparator.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/11 張騰宇 (中訊) 新規作成 モデル692
Revision History : 2007/07/11 張騰宇 (中訊) モデル773
*/
package webbroker3.ifo.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (立会区分Comparator)<BR>
 * 立会区分Comparator
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3OptionsSessionTypeComparator implements Comparator
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionsSessionTypeComparator.class);

    /**
     * A：昇順<BR>
     * D：降順<BR>
     */
    private String orderBy;

    /**
     * 立会区分Comparatorのコンストラクタ。<BR>
     * <BR>
     * パラメータ.orderByをフィールドのorderByにセットする<BR>
     * @@param l_strOrderBy - ソートキーの昇順降順を示す。<BR>
     * <BR>
     * A：昇順<BR>
     * D：降順<BR>
     */
    public WEB3OptionsSessionTypeComparator(String l_strOrderBy)
    {
        if (!WEB3AscDescDef.ASC.equals(l_strOrderBy)
            && !WEB3AscDescDef.DESC.equals(l_strOrderBy))
        {
            throw new IllegalArgumentException("パラメータの値が'A：昇順'、'D：降順'以外です");
        }
        this.orderBy = l_strOrderBy;
    }

    /**
     * 昇順、降順の指定にもとづく建日の比較を行う。 <BR>
     * <BR>
     * １）パラメータのオブジェクトの判定 <BR>
     * <BR>
     * 　@instanceofにて、引数の明細1、明細2が以下のクラスのどれかを判定する。 <BR>
     * <BR>
     *     建玉明細クラス <BR>
     *     株価指数オプション建玉照会明細クラス <BR>
     *     株価指数先物オプション残高照会明細クラス<BR>
     * <BR>
     * 　@明細1.立会区分==NULLの場合 <BR>
     * 　@明細1.立会区分　@=　@0　@として以下比較を行なう。 <BR>
     * <BR>
     * 　@明細2.立会区分==NULLの場合 <BR>
     * 　@明細2.立会区分　@=　@0　@として以下比較を行なう。 <BR>
     * <BR>
     * <BR>
     * ２）比較 <BR>
     * <BR>
     * ※比較時、0と置き換えた明細.立会区分は <BR>
     * 返却時、元の値であるNULLに戻す。 <BR>
     * <BR>
     * 　@１）明細1と明細2の立会区分について比較を行う <BR>
     * <BR>
     * 　@[昇順指定の場合(this.orderBy == ”昇順”)] <BR>
     * <BR>
     * 　@・(明細1.立会区分 < 明細2.立会区分)の場合、負の整数(-1)を返却する <BR>
     * 　@・(明細1.立会区分 == 明細2.立会区分)の場合、0を返却する <BR>
     * 　@・(明細1.立会区分 > 明細2.立会区分)の場合、正の整数(1)を返却する <BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”)] <BR>
     * <BR>
     * 　@・(明細1.立会区分 < 明細2.立会区分)の場合、正の整数(1)を返却する <BR>
     * 　@・(明細1.立会区分 == 明細2.立会区分)の場合、0を返却する <BR>
     * 　@・(明細1.立会区分 > 明細2.立会区分)の場合、負の整数(-1)を返却する<BR>
     * @@param l_obj1 - obj1<BR>
     * @@param l_obj2 - obj2<BR>
     * @@return int<BR>
     */
    public int compare(Object l_obj1, Object l_obj2)
    {
        final String STR_METHOD_NAME = "compare(Object, Object)";
        log.entering(STR_METHOD_NAME);

        String l_strAttr1 = null;
        String l_strAttr2 = null;

        //　@instanceofにて、引数の明細1、明細2が以下のクラスのどれかを判定する。
        //建玉明細クラス
        //株価指数オプション建玉照会明細クラス
        //株価指数先物オプション残高照会明細クラス
        if ((l_obj1 instanceof WEB3FuturesOptionsContractUnit)
            && (l_obj2 instanceof WEB3FuturesOptionsContractUnit))
        {
            l_strAttr1 = ((WEB3FuturesOptionsContractUnit)l_obj1).sessionType;
            l_strAttr2 = ((WEB3FuturesOptionsContractUnit)l_obj2).sessionType;
        }
        else if ((l_obj1 instanceof WEB3OptionsContractReferenceUnit)
            && (l_obj2 instanceof WEB3OptionsContractReferenceUnit))
        {
            l_strAttr1 = ((WEB3OptionsContractReferenceUnit)l_obj1).sessionType;
            l_strAttr2 = ((WEB3OptionsContractReferenceUnit)l_obj2).sessionType;
        }
        else if ((l_obj1 instanceof WEB3FuturesOptionsDetailUnit)
            && (l_obj2 instanceof WEB3FuturesOptionsDetailUnit))
        {
            l_strAttr1 = ((WEB3FuturesOptionsDetailUnit)l_obj1).sessionType;
            l_strAttr2 = ((WEB3FuturesOptionsDetailUnit)l_obj2).sessionType;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException(
                "パラメータの類型が不正、該当する'WEB3FuturesOptionsContractUnit'　@" +
                "または　@'WEB3OptionsContractReferenceUnit' または 'WEB3FuturesOptionsDetailUnit'類型");
        }

        //　@明細1.立会区分==NULLの場合
        //　@明細1.立会区分　@=　@0　@として以下比較を行なう。
        //　@明細2.立会区分==NULLの場合
        //　@明細2.立会区分　@=　@0　@として以下比較を行なう。
        if (l_strAttr1 == null)
        {
            l_strAttr1 = "0";
        }
        if (l_strAttr2 == null)
        {
            l_strAttr2 = "0";
        }

        if (l_strAttr1.compareTo(l_strAttr2) == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        else if (l_strAttr1.compareTo(l_strAttr2) > 0)
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }
        }
        else
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }
        }
    }

    /**
     * パラメータのオブジェクトがこのコンパレータと等しいかどうかを判定する。<BR>
     * <BR>
     * スーパークラスのequalsをコールする。<BR>
     * @@param l_obj - obj
     * @@return boolean
     */
    public boolean equals(Object l_obj)
    {
        if (l_obj instanceof WEB3OptionsSessionTypeComparator)
        {
            WEB3OptionsSessionTypeComparator l_comparator =
                (WEB3OptionsSessionTypeComparator)l_obj;
            if (this.orderBy.equals(l_comparator.orderBy))
            {
                return true;
            }
        }
        return false;
    }
}
@
