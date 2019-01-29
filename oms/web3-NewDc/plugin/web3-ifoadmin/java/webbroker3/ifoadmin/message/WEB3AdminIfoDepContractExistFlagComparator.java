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
filename	WEB3AdminIfoDepContractExistFlagComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (証拠金)建玉有無フラグComparator(WEB3AdminIfoDepContractExistFlagComparator.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/27　@劉剣(中訊) 新規作成 モデルNo.004
*/
package webbroker3.ifoadmin.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * ((証拠金)建玉有無フラグComparator)<BR>
 * (証拠金)建玉有無フラグComparatorクラス<BR>
 * <BR>
 * @@author 劉剣
 * @@version 1.0
 */
public class WEB3AdminIfoDepContractExistFlagComparator implements Comparator
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoDepContractExistFlagComparator.class);

    /**
     * A：昇順 <BR>
     * D：降順<BR>
     */
    private String orderBy;

    /**
     * @@roseuid 49A76E5200CB
     */
    public WEB3AdminIfoDepContractExistFlagComparator()
    {

    }

    /**
     * ((証拠金)建玉有無フラグComparator)<BR>
     * コンストラクタ。 <BR>
     * <BR>
     * パラメータ.orderByをフィールドのorderByにセットする <BR>
     * @@param l_strOrderBy - (orderBy)<BR>
     * orderBy<BR>
     * @@roseuid 499915C1000C
     */
    public WEB3AdminIfoDepContractExistFlagComparator(String l_strOrderBy)
    {
        final String STR_METHOD_NAME = "WEB3AdminIfoDepContractExistFlagComparator(String)";
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
     * @@roseuid 4999151802E5
     */
    public boolean equals(Object l_obj)
    {
        return super.equals(l_obj);
    }

    /**
     * （compareの実装） <BR>
     * <BR>
     * 昇順、降順の指定にもとづくの建玉有無フラグ比較を行う。 <BR>
     * <BR>
     * １）パラメータを証拠金不足状況情報にキャストする。 <BR>
     * <BR>
     * ２）比較 <BR>
     * 　@１）でキャストした明細1、明細2の建玉有無フラグを比較し、結果を返却する。 <BR>
     * <BR>
     * 　@昇順指定の場合、 <BR>
     * 　@　@明細1の建玉有無フラグがfalse、明細2の建玉有無フラグがtrue場合、負の整数（-1）を返却する。 <BR>
     * 　@　@両方が等しい場合は0を返却する。 <BR>
     * 　@　@明細1の建玉有無フラグがtrue、明細2の建玉有無フラグがfalse場合、正の整数（1）を返却する。 <BR>
     * <BR>
     * 　@降順指定の場合、 <BR>
     * 　@　@明細1の建玉有無フラグがfalse、明細2の建玉有無フラグがtrue場合、正の整数（1）を返却する。 <BR>
     * 　@　@両方が等しい場合は0を返却する。 <BR>
     * 　@　@明細1の建玉有無フラグがtrue、明細2の建玉有無フラグがfalse場合、負の整数（-1）を返却する。 <BR>
     * <BR>
     * 　@昇降順の判定はコンストラクタでセットされたorderByの値を用いる <BR>
     * <BR>
     * @@param l_unit1 - (明細1)<BR>
     * 明細1<BR>
     * @@param l_unit2 - (明細2)<BR>
     * 明細2<BR>
     * @@return int
     * @@roseuid 499915710077
     */
    public int compare(Object l_unit1, Object l_unit2)
    {
        final String STR_METHOD_NAME = "compare(Object, Object)";
        log.entering(STR_METHOD_NAME);

        //パラメータを証拠金不足状況情報にキャストする。
        boolean l_blnFlag1 = false;
        boolean l_blnFlag2 = false;
        if (l_unit1 instanceof WEB3IfoDepShortageInfo && l_unit2 instanceof WEB3IfoDepShortageInfo)
        {
            l_blnFlag1 = ((WEB3IfoDepShortageInfo)l_unit1).contractExistFlag;
            l_blnFlag2 = ((WEB3IfoDepShortageInfo)l_unit2).contractExistFlag;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException(
                "パラメータの類型が不正、該当する'WEB3IfoDepShortageInfo'類型。");
        }

        //比較
        //でキャストした明細1、明細2の建玉有無フラグを比較し、結果を返却する。
        int l_intReturn = 0;
        if (WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            if (!l_blnFlag1 && l_blnFlag2)
            {
                l_intReturn = -1;
            }
            else if (l_blnFlag1 == l_blnFlag2)
            {
                l_intReturn = 0;
            }
            else
            {
                l_intReturn = 1;
            }
        }
        else
        {
            if (!l_blnFlag1 && l_blnFlag2)
            {
                l_intReturn = 1;
            }
            else if (l_blnFlag1 == l_blnFlag2)
            {
                l_intReturn = 0;
            }
            else
            {
                l_intReturn = -1;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_intReturn;
    }
}@
