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
filename	WEB3AdminIfoDepBranchCodeComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (証拠金)部店コードComparator(WEB3AdminIfoDepBranchCodeComparator.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/27 李玉玲(中訊) 新規作成 モデルNo.004
*/
package webbroker3.ifoadmin.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * ((証拠金)部店コードComparator)<BR>
 * (証拠金)部店コードComparatorクラス<BR>
 * <BR>
 * @@author 李玉玲(中訊)
 * @@version 1.0
 */
public class WEB3AdminIfoDepBranchCodeComparator implements Comparator
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoDepBranchCodeComparator.class);

    /**
     * A：昇順 <BR>
     * D：降順<BR>
     */
    private String orderBy;

    /**
     * @@roseuid 49A74855036B
     */
    public WEB3AdminIfoDepBranchCodeComparator()
    {

    }

    /**
     * ((証拠金)部店コードComparator)<BR>
     * コンストラクタ。 <BR>
     * <BR>
     * パラメータ.orderByをフィールドのorderByにセットする <BR>
     * @@param l_strOrderBy - (orderBy)<BR>
     * orderBy<BR>
     * @@roseuid 499915820394
     */
    public WEB3AdminIfoDepBranchCodeComparator(String l_strOrderBy)
    {
        final String STR_METHOD_NAME = "WEB3AdminIfoDepBranchCodeComparator(String)";
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
     * @@roseuid 499914EE011F
     */
    public boolean equals(Object l_obj)
    {
        return super.equals(l_obj);
    }

    /**
     * compareの実装<BR>
     * <BR>
     * 昇順、降順の指定にもとづく部店コードの比較を行う。 <BR>
     * <BR>
     * １）パラメータを証拠金不足状況情報にキャストする。 <BR>
     * <BR>
     * ２）比較 <BR>
     * 　@１）でキャストした明細1、明細2の部店コードを比較し、結果を返却する。 <BR>
     * <BR>
     * 　@昇順指定の場合、 <BR>
     * 　@　@明細1の部店コードが、明細2の部店コードより小さい場合、負の整数（-1）を返却する。 <BR>
     * 　@　@両方が等しい場合は0を返却する。 <BR>
     * 　@　@明細1の部店コードが、明細2の部店コードより大きい場合、正の整数（1）を返却する。 <BR>
     * <BR>
     * 　@降順指定の場合、 <BR>
     * 　@　@明細1の部店コードが、明細2の部店コードより小さい場合、正の整数（1）を返却する。 <BR>
     * 　@　@両方が等しい場合は0を返却する。 <BR>
     * 　@　@明細1の部店コードが、明細2の部店コードより大きい場合、負の整数（-1）を返却する。 <BR>
     * <BR>
     * 　@昇降順の判定はコンストラクタでセットされたorderByの値を用いる <BR>
     * @@param l_unit1 - (明細1)<BR>
     * 明細1<BR>
     * @@param l_unit2 - (明細2)<BR>
     * 明細2<BR>
     * @@return int
     * @@roseuid 499915550306
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
            l_strVal1 = ((WEB3IfoDepShortageInfo)l_unit1).branchCode;
            l_strVal2 = ((WEB3IfoDepShortageInfo)l_unit2).branchCode;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException(
                "パラメータの類型が不正、該当する'WEB3IfoDepShortageInfo'類型。");
        }

        //比較
        //でキャストした明細1、明細2の部店コードを比較し、結果を返却する。
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
            if (l_strVal1.compareTo(l_strVal2) < 0)
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
            else if (l_strVal1.equals(l_strVal2))
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
}
@
