head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.19.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsProfitAndLossComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 損益Comparator(WEB3OptionsProfitAndLossComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 李海波 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (損益Comparator)<BR>
 * 損益Comparatorクラス<BR>
 * @@author 李海波
 * @@version 1.0 
 */
public class WEB3OptionsProfitAndLossComparator implements Comparator
{
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(
            WEB3OptionsProfitAndLossComparator.class);
            
    /**
     * A：昇順<BR>
     * D：降順<BR>
     */
    private String orderBy;

    /**
     * 損益Comparatorのコンストラクタ。<BR>
     * <BR>
     * パラメータ.orderByをフィールドのorderByにセットする<BR>
     * @@param l_strOrderBy - ソートキーの昇順降順を示す。<BR>
     * <BR>
     * A：昇順<BR>
     * D：降順<BR>
     * @@return Void
     * @@roseuid 407BBC2902FD
     */
    public WEB3OptionsProfitAndLossComparator(String l_strOrderBy)
    {
        if(l_strOrderBy == null 
            || (!l_strOrderBy.equals(WEB3AscDescDef.ASC) 
            && !l_strOrderBy.equals(WEB3AscDescDef.DESC)))
        {
            throw new IllegalArgumentException("パラメータの値が'A：昇順'、'D：降順'以外です");
        }
        this.orderBy = l_strOrderBy;
    }

    /**
     * 昇順、降順の指定にもとづく損益の比較を行う。<BR>
     * <BR>
     * １）引数のオブジェクトの判定<BR>
     * <BR>
     * 　@instanceofにて、引数の明細1、明細2が以下のクラスのどれかを判定する。<BR>
     * <BR>
     *     株価指数オプション返済一覧行クラス<BR>
     *     株価指数オプション建玉照会明細クラス<BR>
     *     建玉明細クラス<BR>
     * <BR>
     * ２）比較<BR>
     * <BR>
     * 　@１）明細1と明細2の損益について比較を行う<BR>
     * <BR>
     * 　@[昇順指定の場合(this.orderBy == ”昇順”)]<BR>
     * <BR>
     * 　@・(明細1.損益 < 明細2.損益)の場合、負の整数(-1)を返却する<BR>
     * 　@・(明細1.損益 == 明細2.損益)の場合、0を返却する<BR>
     * 　@・(明細1.損益 > 明細2.損益)の場合、正の整数(1)を返却する<BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”)]<BR>
     * <BR>
     * 　@・(明細1.損益 < 明細2.損益)の場合、正の整数(1)を返却する<BR>
     * 　@・(明細1.損益 == 明細2.損益)の場合、0を返却する<BR>
     * 　@・(明細1.損益 > 明細2.損益)の場合、負の整数(-1)を返却する<BR>
     * @@param l_obj1 - obj1<BR>
     * @@param l_obj2 - obj2<BR>
     * @@return int<BR>
     * @@roseuid 407BBA6A0119
     */
    public int compare(Object l_obj1, Object l_obj2)
    {   
        String l_strMethodName = "compare(Object l_obj1, Object l_obj2)";
        log.entering(l_strMethodName);

        double l_dblVal1 = 0;
        double l_dblVal2 = 0;

        String l_strAttr1 = null;
        String l_strAttr2 = null; 

        if((l_obj1 instanceof WEB3OptionsContractReferenceUnit) 
            && (l_obj2 instanceof WEB3OptionsContractReferenceUnit))
        {
            l_strAttr1 = ((WEB3OptionsContractReferenceUnit)l_obj1).income;
            l_strAttr2 = ((WEB3OptionsContractReferenceUnit)l_obj2).income; 
        }
        else if((l_obj1 instanceof WEB3FuturesOptionsContractUnit) 
            && (l_obj2 instanceof WEB3FuturesOptionsContractUnit))
        {
            l_strAttr1 = ((WEB3FuturesOptionsContractUnit)l_obj1).income;
            l_strAttr2 = ((WEB3FuturesOptionsContractUnit)l_obj2).income; 
        }
        else if((l_obj1 instanceof WEB3OptionsCloseMarginGroup) 
            && (l_obj2 instanceof WEB3OptionsCloseMarginGroup))
        {
            l_strAttr1 = ((WEB3OptionsCloseMarginGroup)l_obj1).income;
            l_strAttr2 = ((WEB3OptionsCloseMarginGroup)l_obj2).income; 
        }
        else
        {
            throw new IllegalArgumentException(
                "パラメータの類型が不正、該当する'WEB3OptionsContractReferenceUnit'　@" +
                "または　@'WEB3FuturesOptionsContractUnit'　@" +
                "または　@'WEB3OptionsCloseMarginGroup'類型");
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
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
            }
            else
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
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
        if (l_obj instanceof WEB3OptionsProfitAndLossComparator) {
            WEB3OptionsProfitAndLossComparator l_comparator = 
                (WEB3OptionsProfitAndLossComparator)l_obj;
            if (this.orderBy.equals(l_comparator.orderBy))
            {            
                return true;            
            }            
        }
        return false;
    }
}
@
