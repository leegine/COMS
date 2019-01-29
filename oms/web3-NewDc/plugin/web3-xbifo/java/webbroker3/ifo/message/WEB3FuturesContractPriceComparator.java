head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.18.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesContractPriceComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物建単価Comparator(WEBFuturesContractPriceComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/04/06 呉艶飛 (中訊) 新規作成
*/
package webbroker3.ifo.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物建単価Comparator)<BR>
 * 先物建単価Comparatorクラス<BR>
 * @@author 呉艶飛
 * @@version 1.0 
 */
public class WEB3FuturesContractPriceComparator implements Comparator
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesContractPriceComparator.class);

    /**
     * A：昇順<BR>
     * D：降順<BR>
     */
    private String orderBy;

    /**
     * 先物建単価Comparatorのコンストラクタ。<BR>
     * <BR>
     * パラメータ.orderByをフィールドのorderByにセットする<BR>
     * @@param l_strOrderBy - ソートキーの昇順降順を示す。<BR>
     * <BR>
     * A：昇順<BR>
     * D：降順<BR>
     * @@return Void
     * @@roseuid 407BBC2902FD
     */
    public WEB3FuturesContractPriceComparator(String l_strOrderBy)
    {
        if (l_strOrderBy == null
            || (!l_strOrderBy.equals(WEB3AscDescDef.ASC)
                && !l_strOrderBy.equals(WEB3AscDescDef.DESC)))
        {
            throw new IllegalArgumentException("パラメータの値が'A：昇順'、'D：降順'以外です");
        }
        this.orderBy = l_strOrderBy;
    }

    /**
     * 昇順、降順の指定にもとづく建単価の比較を行う。<BR>
     * １）　@引数のキャスト<BR> 
     * 　@パラメータ.明細1および2を、株価指数先物建玉明細にキャストする。 <BR> 
     * <BR>
     * ２）　@比較<BR>
     * 　@１）明細1と明細2の建単価について比較を行う<BR>
     * 　@[昇順指定の場合(this.orderBy == ”昇順”)<BR>
     * 　@・(明細1.建単価 < 明細2.建単価)の場合、負の整数(-1)を返却する<BR>
     * 　@・(明細1.建単価 == 明細2.建単価)の場合、0を返却する<BR>
     * 　@・(明細1.建単価 > 明細2.建単価)の場合、正の整数(1)を返却する<BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”)]<BR>
     * 　@・(明細1.建単価 < 明細2.建単価)の場合、正の整数(1)を返却する<BR>
     * 　@・(明細1.建単価 == 明細2.建単価)の場合、0を返却する<BR>
     * 　@・(明細1.建単価 > 明細2.建単価)の場合、負の整数(-1)を返却する<BR>
     * @@param l_obj1 - obj1<BR>
     * @@param l_obj2 - obj2<BR>
     * @@return int<BR>
     */
    public int compare(Object l_obj1, Object l_obj2)
    {
        String l_strMethodName = "compare(Object l_obj1, Object l_obj2)";
        log.entering(l_strMethodName);

        double l_dblVal1 = 0;
        double l_dblVal2 = 0;

        String l_strAttr1 = null;
        String l_strAttr2 = null;

        if ((l_obj1 instanceof WEB3FuturesOptionsContractUnit)
            && (l_obj2 instanceof WEB3FuturesOptionsContractUnit))
        {
            l_strAttr1 =
                ((WEB3FuturesOptionsContractUnit) l_obj1).contractPrice;
            l_strAttr2 =
                ((WEB3FuturesOptionsContractUnit) l_obj2).contractPrice;
        }
        else
        {
            throw new IllegalArgumentException(
                "パラメータの類型が不正、該当する'WEB3FuturesOptionsContractUnit'　@" + "'類型");
        }

        if (l_strAttr1 == null || l_strAttr2 == null)
        {
            int l_intResult;

            if (l_strAttr1 == null && l_strAttr2 == null)
            {
                l_intResult = 0;
            }
            else if (l_strAttr1 == null)
            {
                l_intResult =
                    (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
            }
            else
            {
                l_intResult =
                    (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
            }

            return l_intResult;
        }

        l_dblVal1 = Double.parseDouble(l_strAttr1);
        l_dblVal2 = Double.parseDouble(l_strAttr2);

        if (l_dblVal1 == l_dblVal2)
        {
            return 0;
        }
        else if (l_dblVal1 > l_dblVal2)
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
        else
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                return -1;
            }
            else
            {
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
     * @@roseuid 407BBA72009C
     */
    public boolean equals(Object l_obj)
    {
        if (l_obj instanceof WEB3FuturesContractPriceComparator)
        {
            WEB3FuturesContractPriceComparator l_comparator =
                (WEB3FuturesContractPriceComparator) l_obj;
            if (this.orderBy.equals(l_comparator.orderBy))
            {
                return true;
            }
        }
        return false;
    }
}
@
