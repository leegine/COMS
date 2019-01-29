head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.14.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsOpenDateComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 建日Comparator(WEB3OptionsOpenDateComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 呉艶飛 新規作成
Revesion History : 2007/07/11 張騰宇 (中訊) モデル773
*/

package webbroker3.ifo.message;

import java.util.Comparator;
import java.util.Date;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3DateUtility;


/**
 * (建日Comparator)<BR>
 * 建日Comparatorクラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3OptionsOpenDateComparator implements Comparator
{
    /**
     * A：昇順<BR>
     * D：降順<BR>
     */
    private String orderBy;

    /**
     * (建日Comparator)<BR>
     * 建日Comparatorのコンストラクタ。<BR>
     * <BR>
     * パラメータ.orderByをフィールドのorderByにセットする<BR>
     * @@param l_strOrderBy - (ソートキーの昇順降順を示す。)<BR>
     * <BR>
     * A：昇順<BR>
     * D：降順<BR>
     * @@return Void
     * @@roseuid 407BC27001C4
     */
    public WEB3OptionsOpenDateComparator(String l_strOrderBy)
    {
        if (l_strOrderBy == null
            ||( !WEB3AscDescDef.ASC.equals(l_strOrderBy)
            && !WEB3AscDescDef.DESC.equals(l_strOrderBy)))
        {
            throw new IllegalArgumentException("パラメータの値が'A：昇順'、'D：降順'以外です。");
        }
        this.orderBy = l_strOrderBy;
    }

    /**
     * 
     * 昇順、降順の指定にもとづく建日の比較を行う。<BR>
     * <BR>
     * １）パラメータのオブジェクトの判定<BR>
     * <BR>
     * 　@instanceofにて、引数の明細1、明細2が以下のクラスのどれかを判定する。<BR>
     *     建玉明細クラス<BR>
     *     株価指数オプション建玉照会明細クラス<BR>
     *     株価指数先物オプション残高照会明細クラス<BR>
     * <BR>
     * ２）比較<BR>
     * <BR>
     * 　@１）明細1と明細2の建日について比較を行う<BR>
     * 　@[昇順指定の場合(this.orderBy == ”昇順”)]<BR>
     * <BR>
     * 　@・(明細1.建日 < 明細2.建日)の場合、負の整数(-1)を返却する<BR>
     * 　@・(明細1.建日 == 明細2.建日)の場合、0を返却する<BR>
     * 　@・(明細1.建日 > 明細2.建日)の場合、正の整数(1)を返却する<BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”)]<BR>
     * <BR>
     * 　@・(明細1.建日 < 明細2.建日)の場合、正の整数(1)を返却する<BR>
     * 　@・(明細1.建日 == 明細2.建日)の場合、0を返却する<BR>
     * 　@・(明細1.建日 > 明細2.建日)の場合、負の整数(-1)を返却する<BR>
     * @@param l_obj1 - obj1<BR>
     * @@param l_obj2 - obj2<BR>
     * @@return int<BR>
     */
    public int compare(Object l_obj1, Object l_obj2)
    {
        Date l_datVal1 = null;
        Date l_datVal2 = null;

        if (l_obj1 instanceof WEB3OptionsContractReferenceUnit 
            && l_obj2 instanceof WEB3OptionsContractReferenceUnit)
        {
            l_datVal1 = ((WEB3OptionsContractReferenceUnit)l_obj1).openDate;
            l_datVal2 = ((WEB3OptionsContractReferenceUnit)l_obj2).openDate;
        }
        else if (l_obj1 instanceof WEB3FuturesOptionsContractUnit 
            && l_obj2 instanceof WEB3FuturesOptionsContractUnit)
        {
            l_datVal1 = ((WEB3FuturesOptionsContractUnit)l_obj1).openDate;
            l_datVal2 = ((WEB3FuturesOptionsContractUnit)l_obj2).openDate;
        }
        else if (l_obj1 instanceof WEB3FuturesOptionsDetailUnit 
            && l_obj2 instanceof WEB3FuturesOptionsDetailUnit)
        {
            l_datVal1 = ((WEB3FuturesOptionsDetailUnit)l_obj1).openDate;
            l_datVal2 = ((WEB3FuturesOptionsDetailUnit)l_obj2).openDate;
        }
        else
        {
            throw new IllegalArgumentException("パラメータの類型が不正、該当する" +
                "'WEB3OptionsContractReferenceUnit' " +
                "または 'WEB3FuturesOptionsContractUnit' " +
                "または 'WEB3FuturesOptionsDetailUnit' 類型。");
        }

        if (l_datVal1 == null || l_datVal2 == null)
        {
            int l_intResult;
               
            if (l_datVal1 == null && l_datVal2 == null)
            {
                l_intResult = 0;
            }
            else if (l_datVal1 == null)
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
            }
            else
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
            }
                        
            return l_intResult;            
        }

        if (WEB3DateUtility.compareToDay(l_datVal1, l_datVal2) ==0)
        {
            return 0;
        }
        else if (WEB3DateUtility.compareToDay(l_datVal1, l_datVal2) > 0)
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
            if (WEB3AscDescDef.DESC.equals(this.orderBy))
            {
                return 1;        
            }
            else
            {
                return -1;
            }
        }
    }

    /**
     * パラメータのオブジェクトがこのコンパレータと等しいかどうかを判定する。<BR>
     * <BR>
     * スーパークラスのequalsをコールする。<BR>
     * @@param l_obj
     * @@return boolean
     * @@roseuid 407BC718034B
     */
    public boolean equals(Object l_obj)
    {
        //スーパークラスのequalsをコールする。
        if (l_obj instanceof  WEB3OptionsOpenDateComparator)
        {
            WEB3OptionsOpenDateComparator l_comparator =
                    ( WEB3OptionsOpenDateComparator) l_obj;
            if (this.orderBy.equals(l_comparator.orderBy))
            {
                return true;
            }
        }
        return false;
    }
}
@
