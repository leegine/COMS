head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.13.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsContractDivisionComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数建区分Comparator(WEB3OptionsContractDivisionComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 張威 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (建区分Comparator)<BR>
 * 建区分Comparatorクラス<BR>                                                                    
 * @@author 張威
 * @@version 1.0
 */
public class WEB3OptionsContractDivisionComparator implements Comparator
{
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3OptionsContractDivisionComparator.class);

    /**
     * A：昇順<BR>
     * D：降順<BR>
     */
    private String orderBy;

    /**
     * 建区分Comparatorのコンストラクタ。<BR>
     * <BR>
     * パラメータ.orderByをフィールドのorderByにセットする<BR>
     * @@param l_orderBy - ソートキーの昇順降順を示す。<BR>
     * <BR>
     * A：昇順<BR>
     * D：降順<BR>
     * @@return Void
     * @@roseuid 407BC4E9002E
     */
    public WEB3OptionsContractDivisionComparator(String l_strOrderBy)
    {
        if (l_strOrderBy == null
            || (!l_strOrderBy.equals(WEB3AscDescDef.ASC) 
            && !l_strOrderBy.equals(WEB3AscDescDef.DESC)))
        {
            throw new IllegalArgumentException("パラメータの値が'A：昇順'、'D：降順'以外です。");
        }

        this.orderBy = l_strOrderBy;
    }

    /**
     * 昇順、降順の指定にもとづく建区分の比較を行う。<BR>
     * <BR>
     * １）パラメータのオブジェクトの判定<BR>
     * <BR>
     * 　@instanceofにて、引数の明細1、明細2が以下のクラスのどれかを判定する。<BR>
     * <BR>
     *     株価指数オプション返済一覧行クラス<BR>
     *     株価指数オプション建玉照会明細クラス<BR>
     * <BR>
     * ２）比較<BR>
     * <BR>
     * 　@１）明細1と明細2の建区分について比較を行う<BR>
     * <BR>
     * 　@[昇順指定の場合(this.orderBy == ”昇順”)]<BR>
     * <BR>
     * 　@・(明細1.建区分 < 明細2.建区分)の場合、負の整数(-1)を返却する<BR>
     * 　@・(明細1.建区分 == 明細2.建区分)の場合、0を返却する<BR>
     * 　@・(明細1.建区分 > 明細2.建区分)の場合、正の整数(1)を返却する<BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”)]<BR>
     * <BR>
     * 　@・(明細1.建区分 < 明細2.建区分)の場合、正の整数(1)を返却する<BR>
     * 　@・(明細1.建区分 == 明細2.建区分)の場合、0を返却する<BR>
     * 　@・(明細1.建区分 > 明細2.建区分)の場合、負の整数(-1)を返却する<BR>
     * @@param l_obj1<BR>
     * @@param l_obj2<BR>
     * @@return int<BR>
     * @@roseuid 407BC4E803D8
     */
    public int compare(Object l_obj1, Object l_obj2)
    {
        final String STR_METHOD_NAME = "compare(Object l_obj1, Object l_obj2)";
        log.entering(STR_METHOD_NAME);

        String l_strVal1 = null;
        String l_strVal2 = null;

        // 株価指数オプション建玉照会明細
        if ((l_obj1 instanceof WEB3OptionsContractReferenceUnit)    
            && (l_obj2 instanceof WEB3OptionsContractReferenceUnit))
        {
            l_strVal1 = ((WEB3OptionsContractReferenceUnit)l_obj1).contractType;
            l_strVal2 = ((WEB3OptionsContractReferenceUnit)l_obj2).contractType;
        }
        else if ((l_obj1 instanceof WEB3OptionsCloseMarginGroup) && (l_obj2 instanceof WEB3OptionsCloseMarginGroup)) //株価指数オプション返済一覧行
        {
            l_strVal1 = ((WEB3OptionsCloseMarginGroup)l_obj1).contractType;
            l_strVal2 = ((WEB3OptionsCloseMarginGroup)l_obj2).contractType;
        }
        else
        {
            throw new IllegalArgumentException("パラメータの類型が不正、該当する'WEB3OptionsContractReferenceUnit' または 'WEB3OptionsCloseMarginGroup' 類型。");
        }

        if (l_strVal1 == null || l_strVal2 == null)
        {
            int l_intResult;
               
            if (l_strVal1 == null && l_strVal2 == null)
            {
                l_intResult = 0;
            }
            else if (l_strVal1 == null)
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
            }
            else
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
            }
                        
            return l_intResult;            
        }

        if (l_strVal1.equals(l_strVal2))
        {
            return 0;
        }
        else if (l_strVal1.compareTo(l_strVal2) > 0)
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
     * @@param l_obj
     * @@return boolean
     * @@roseuid 407BC7300196
     */
    public boolean equals(Object l_obj)
    {
        if (l_obj instanceof WEB3OptionsContractDivisionComparator)
        {
            WEB3OptionsContractDivisionComparator l_comparator = 
                (WEB3OptionsContractDivisionComparator)l_obj;
            if (this.orderBy.equals(l_comparator.orderBy))
            {
                return true;
            }
        }
        return false;
    }
}
@
