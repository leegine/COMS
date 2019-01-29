head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoDepCurNonPayAmtComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (証拠金)現在未入金額Comparator(WEB3AdminIfoDepCurNonPayAmtComparator.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/27　@劉剣(中訊) 新規作成 モデルNo.004
*/
package webbroker3.ifoadmin.message;

import java.util.Comparator;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * ((証拠金)現在未入金額Comparator)<BR>
 * (証拠金)現在未入金額Comparatorクラス<BR>
 * <BR>
 * @@author 劉剣
 * @@version 1.0
 */
public class WEB3AdminIfoDepCurNonPayAmtComparator implements Comparator
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoDepCurNonPayAmtComparator.class);

    /**
     * A：昇順 <BR>
     * D：降順<BR>
     */
    private String orderBy;

    /**
     * @@roseuid 49A76E520167
     */
    public WEB3AdminIfoDepCurNonPayAmtComparator()
    {

    }

    /**
     * ((証拠金)現在未入金額Comparator)<BR>
     * コンストラクタ。 <BR>
     * <BR>
     * パラメータ.orderByをフィールドのorderByにセットする <BR>
     * @@param l_strOrderBy - (orderBy)<BR>
     * orderBy<BR>
     * @@roseuid 499915A600A7
     */
    public WEB3AdminIfoDepCurNonPayAmtComparator(String l_strOrderBy)
    {
        final String STR_METHOD_NAME = "WEB3AdminIfoDepCurNonPayAmtComparator(String)";
        log.entering(STR_METHOD_NAME);

        if (!WEB3AscDescDef.ASC.equals(l_strOrderBy) && !WEB3AscDescDef.DESC.equals(l_strOrderBy))
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException("パラメータの値が'A：昇順'、'D：降順'以外です。");
        }

        this.orderBy = l_strOrderBy;
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * パラメータのオブジェクトがこのコンパレータと等しいかどうかを判定する。 <BR>
     * <BR>
     * スーパークラスのequalsをコールする。 <BR>
     * @@param l_obj - (obj)<BR>
     * obj<BR>
     * @@return boolean
     * @@roseuid 4999150700F0
     */
    public boolean equals(Object l_obj)
    {
        return super.equals(l_obj);
    }

    /**
     * compareの実装<BR>
     * <BR>
     * 昇順、降順の指定にもとづく現在未入金額の比較を行う。 <BR>
     * <BR>
     * １）パラメータを証拠金不足状況情報にキャストする。 <BR>
     * <BR>
     * ２）比較 <BR>
     * 　@１）でキャストした明細1、明細2の現在未入金額を比較し、結果を返却する。 <BR>
     * <BR>
     * 　@昇順指定の場合、 <BR>
     * 　@　@明細1の現在未入金額が、明細2の現在未入金額より小さい場合、負の整数（-1）を返却する。 <BR>
     * 　@　@両方が等しい場合は0を返却する。 <BR>
     * 　@　@明細1の現在未入金額が、明細2の現在未入金額より大きい場合、正の整数（1）を返却する。 <BR>
     * <BR>
     * 　@降順指定の場合、 <BR>
     * 　@　@明細1の現在未入金額が、明細2の現在未入金額より小さい場合、正の整数（1）を返却する。 <BR>
     * 　@　@両方が等しい場合は0を返却する。 <BR>
     * 　@　@明細1の現在未入金額が、明細2の現在未入金額より大きい場合、負の整数（-1）を返却する。 <BR>
     * <BR>
     * 　@昇降順の判定はコンストラクタでセットされたorderByの値を用いる <BR>
     * <BR>
     * @@param l_unit1 - (明細1)<BR>
     * 明細1<BR>
     * @@param l_unit2 - (明細2)<BR>
     * 明細2<BR>
     * @@return int
     * @@roseuid 499915620384
     */
    public int compare(Object l_unit1, Object l_unit2)
    {
        final String STR_METHOD_NAME = "compare(Object, Object)";
        log.entering(STR_METHOD_NAME);

        //パラメータを証拠金不足状況情報にキャストする。
        String l_strVal1 = null;
        String l_strVal2 = null;
        if (l_unit1 instanceof WEB3IfoDepShortageInfo && l_unit2 instanceof WEB3IfoDepShortageInfo)
        {
            l_strVal1 = ((WEB3IfoDepShortageInfo)l_unit1).curNonPayAmt;
            l_strVal2 = ((WEB3IfoDepShortageInfo)l_unit2).curNonPayAmt;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException(
                "パラメータの類型が不正、該当する'WEB3IfoDepShortageInfo'類型。");
        }

        //比較
        //でキャストした明細1、明細2の現在未入金額を比較し、結果を返却する。
        int l_intReturn = 0;
        if (l_strVal1 == null || l_strVal2 == null)
        {
            if (l_strVal1 == null && l_strVal2 == null)
            {
                l_intReturn = 0;
            }
            else if (l_strVal1 == null)
            {
                if (WEB3AscDescDef.ASC.equals(this.orderBy))
                {
                    l_intReturn = -1;
                }
                else
                {
                    l_intReturn = 1;
                }
            }
            else
            {
                if (WEB3AscDescDef.ASC.equals(this.orderBy))
                {
                    l_intReturn = 1;
                }
                else
                {
                    l_intReturn = -1;
                }
            }
        }
        else
        {
            double l_dblVal1 = Double.parseDouble(l_strVal1);
            double l_dblVal2 = Double.parseDouble(l_strVal2);

            if (l_dblVal1 < l_dblVal2)
            {
                if (WEB3AscDescDef.ASC.equals(this.orderBy))
                {
                    l_intReturn = -1;
                }
                else
                {
                    l_intReturn = 1;
                }
            }
            else if (GtlUtils.Double.isEqual(l_dblVal1, l_dblVal2))
            {
                l_intReturn = 0;
            }
            else
            {
                if (WEB3AscDescDef.ASC.equals(this.orderBy))
                {
                    l_intReturn = 1;
                }
                else
                {
                    l_intReturn = -1;
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_intReturn;
    }
}@
