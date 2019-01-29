head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.42.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminBondBranchCodeComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (債券)部店コードComparator(WEB3AdminBondBranchCodeComparator.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/9 劉立峰(中訊) 新規作成   モデルNo.100
*/

package webbroker3.adminorderexecinquiry.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * ((債券)部店コードComparator)<BR>
 * (債券)部店コードComparator<BR>
 * <BR>
 * @@author 劉立峰(中訊)
 * @@version 1.0
 */
public class WEB3AdminBondBranchCodeComparator implements Comparator
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondBranchCodeComparator.class);

    /**
     * orderBy<BR>
     * A：昇順<BR>
     * D：降順
     */
    private String orderBy;

    /**
     * ((債券)部店コードComparator)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * パラメータ.orderByをフィールドのorderByにセットする
     * @@param l_strOrderBy - (orderBy)<BR>
     * ソートキーの昇順降順を示す。<BR>
     * <BR>
     * A：昇順<BR>
     * D：降順
     */
    public WEB3AdminBondBranchCodeComparator(String l_strOrderBy)
    {
        final String STR_METHOD_NAME =
            "WEB3AdminBondBranchCodeComparator(String)";
        log.entering(STR_METHOD_NAME);

        if(l_strOrderBy == null ||
            (!l_strOrderBy.equals(WEB3AscDescDef.ASC) &&
            !l_strOrderBy.equals(WEB3AscDescDef.DESC)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException(
            "パラメータの値が'A：昇順'、'D：降順'以外です。");
        }
        this.orderBy = l_strOrderBy;
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * パラメータのオブジェクトがこのコンパレータと等しいかどうかを判定する。<BR>
     * <BR>
     * スーパークラスのequalsをコールする。<BR>
     * @@param l_obj - (Object)<BR>
     * 比較対象の参照オブジェクト
     * @@return boolean
     */
    public boolean equals(Object l_obj)
    {
        return super.equals(l_obj);
    }

    /**
     * （compareの実装）<BR>
     * <BR>
     * 明細1，2について、部店コードの比較を行う。<BR>
     * <BR>
     * １）パラメータを債券管理者注文約定照会行にキャストする。<BR>
     * <BR>
     * ２）比較<BR>
     * 　@１）でキャストした明細1、明細2の部店コードを比較し、結果を返却する。<BR>
     * <BR>
     *  昇順指定の場合、<BR>
     *　@　@明細1の部店コードが、明細2の部店コードより小さい場合、負の整数（-1）を返却する。<BR>
     *　@　@両方が等しい場合、0を返却する。<BR>
     *　@　@明細1の部店コードが、明細2の部店コードより大きい場合、正の整数（1）を返却する。<BR>
     * <BR>
     *  降順指定の場合、<BR>
     *　@　@明細1の部店コードが、明細2の部店コードより小さい場合、正の整数（1）を返却する。<BR>
     *　@　@両方が等しい場合、0を返却する。<BR>
     *　@　@明細1の部店コードが、明細2の部店コードより大きい場合、負の整数（-1）を返却する。<BR>
     * <BR>
     *  昇降順の判定はコンストラクタでセットされたorderByの値を用いる<BR>
     * @@param l_obj1 - (明細1)<BR>
     * 債券管理者注文約定照会行オブジェクト１
     * @@param l_obj2 - (明細2)<BR>
     * 債券管理者注文約定照会行オブジェクト２
     * @@return int
     */
    public int compare(Object l_obj1, Object l_obj2)
    {
        final String STR_METHOD_NAME = "compare(Object, Object)";
        log.entering(STR_METHOD_NAME);

        //１）パラメータを債券管理者注文約定照会行にキャストする。
        String l_strVal1 = null;
        String l_strVal2 = null;
        if (l_obj1 instanceof WEB3AdminORBondExecRefUnit &&
           l_obj2 instanceof WEB3AdminORBondExecRefUnit)
        {
            l_strVal1 = ((WEB3AdminORBondExecRefUnit)l_obj1).branchCode;
            l_strVal2 = ((WEB3AdminORBondExecRefUnit)l_obj2).branchCode;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException(
                "パラメータの類型が不正、該当する'WEB3AdminORBondExecRefUnit'類型。");
        }

        // ２）比較
        int l_intReturn = 0;
        if (l_strVal1 == null || l_strVal2 == null)
        {
            if (l_strVal1 == null && l_strVal2 == null)
            {
                l_intReturn = 0;
            }
            else if (l_strVal1 == null)
            {
                if(WEB3AscDescDef.ASC.equals(this.orderBy))
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
            if (l_strVal1.equals(l_strVal2))
            {
                l_intReturn = 0;
            }
            else if (l_strVal1.compareTo(l_strVal2) > 0)
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
            else if (l_strVal1.compareTo(l_strVal2) < 0)
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
        }
        log.exiting(STR_METHOD_NAME);
        return l_intReturn;
    }
}
@
