head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.19.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsSettlementStatusTypeComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 決済状態区分Comparator(WEB3SettlementStatusTypeComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 李頴淵 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;


/**
 * (決済状態区分Comparator)<BR>
 * 決済状態区分Comparatorクラス<BR>
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3OptionsSettlementStatusTypeComparator implements Comparator
{
    /**
     * A：昇順<BR>
     * D：降順<BR>
     */
    private String orderBy;

    /**
     * 決済状態区分Comparatorのコンストラクタ。<BR>
     * <BR>
     * パラメータ.orderByをフィールドのorderByにセットする<BR>
     * @@param l_orderBy - ソートキーの昇順降順を示す。<BR>
     * <BR>
     * A：昇順<BR>
     * D：降順
     * @@return Void
     * @@roseuid 4085D0160399
     */
    public WEB3OptionsSettlementStatusTypeComparator(String l_strOrderBy)
    {
        if (l_strOrderBy == null ||
           (!l_strOrderBy.equals(WEB3AscDescDef.ASC) &&
            !l_strOrderBy.equals(WEB3AscDescDef.DESC)))
        {
            throw new IllegalArgumentException("パラメータの値が'A：昇順'、'D：降順'以外です。");
        }
        this.orderBy = l_strOrderBy;
    }

    /**
     * 昇順、降順の指定にもとづく決済状態区分の比較を行う。<BR>
     * <BR>
     * １）パラメータのオブジェクトの判定<BR>
     * <BR>
     * 　@instanceofにて、引数の明細1、明細2が以下のクラスのどれかを判定する。<BR>
     * <BR>
     *     株価指数オプション建玉照会明細クラス<BR>
     * <BR>
     * ２）比較<BR>
     * <BR>
     * 　@１）明細1と明細2の決済状態区分について比較を行う<BR>
     * <BR>
     * 　@[昇順指定の場合(this.orderBy == ”昇順”)]<BR>
     * <BR>
     * 　@・(明細1.決済状態区分 < 明細2.決済状態区分)の場合、負の整数(-1)を返却する<BR>
     * 　@・(明細1.決済状態区分 == 明細2.決済状態区分)の場合、0を返却する<BR>
     * 　@・(明細1.決済状態区分 > 明細2.決済状態区分)の場合、正の整数(1)を返却する<BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”)]<BR>
     * <BR>
     * 　@・(明細1.決済状態区分 < 明細2.決済状態区分)の場合、正の整数(1)を返却する<BR>
     * 　@・(明細1.決済状態区分 == 明細2.決済状態区分)の場合、0を返却する<BR>
     * 　@・(明細1.決済状態区分 > 明細2.決済状態区分)の場合、負の整数(-1)を返却する<BR>
     * @@param l_obj1<BR>
     * @@param l_obj2<BR>
     * @@return int<BR>
     * @@roseuid 4085D01603C8
     */
    public int compare(Object l_obj1, Object l_obj2)
    {
        String l_strVal1 = null;
        String l_strVal2 = null;
        
        if ((l_obj1 instanceof WEB3OptionsContractReferenceUnit)          //株価指数オプション建玉照会明細
         && (l_obj2 instanceof WEB3OptionsContractReferenceUnit))
        {
            l_strVal1 = ((WEB3OptionsContractReferenceUnit)l_obj1).settlementState;
            l_strVal2 = ((WEB3OptionsContractReferenceUnit)l_obj2).settlementState;
        }
        else if((l_obj1 instanceof WEB3OptionsCloseMarginGroup)          //株価指数オプション返済一覧行
              && (l_obj2 instanceof WEB3OptionsCloseMarginGroup))
        {
            l_strVal1 = ((WEB3OptionsCloseMarginGroup)l_obj1).settlementState;
            l_strVal2 = ((WEB3OptionsCloseMarginGroup)l_obj2).settlementState;
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

        if (l_strVal1 == l_strVal2)
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
     * @@roseuid 4085D01603D7
     */
    public boolean equals(Object l_obj)
    {
        if (l_obj instanceof WEB3OptionsSettlementStatusTypeComparator)
        {
            WEB3OptionsSettlementStatusTypeComparator l_comparator =
                (WEB3OptionsSettlementStatusTypeComparator) l_obj;
            if (this.orderBy.equals(l_comparator.orderBy))
            {
                return true;
            }
        }
        return false;
    }
}
@
