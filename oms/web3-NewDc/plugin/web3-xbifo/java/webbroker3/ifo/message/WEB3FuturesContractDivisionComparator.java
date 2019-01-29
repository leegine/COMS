head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.15.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesContractDivisionComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物建区分Comparator(WEB3FuturesContractDivisionComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20   王暁傑 (Sinocom) 新規作成 
*/

package webbroker3.ifo.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物建区分Comparator)<BR>
 */
public class WEB3FuturesContractDivisionComparator implements Comparator
{

    /**
    * ログユーティリティ<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FuturesContractDivisionComparator.class);

    /**
     * A：昇順<BR>
     * D：降順<BR>
     */
    private String orderBy;

    /**
     * @@roseuid 40F7AE14033C
     */
    public WEB3FuturesContractDivisionComparator()
    {

    }

    /**
     * (先物建区分Comparator)<BR>
     * 先物建区分Comparatorのコンストラクタ。<BR>
     * <BR>
     * パラメータ.orderByをフィールドのorderByにセットする<BR>
     * @@param l_strOrderBy - ソートキーの昇順降順を示す。<BR>
     * <BR>
     * A：昇順<BR>
     * D：降順<BR>
     * @@return Void
     * @@roseuid 40CFEC9301CC
     */
    public WEB3FuturesContractDivisionComparator(String l_strOrderBy)
    {
        if (!WEB3AscDescDef.ASC.equals(l_strOrderBy)
            && !WEB3AscDescDef.DESC.equals(l_strOrderBy))
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
     *     株価指数先物返済一覧行クラス<BR>
     *     株価指数先物建玉照会明細クラス<BR>
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
     * @@return int
     * @@roseuid 40CFEC9301CE
     */
    public int compare(Object l_obj1, Object l_obj2)
    {
        final String STR_METHOD_NAME = "compare(Object l_obj1, Object l_obj2)";
        log.entering(STR_METHOD_NAME);
        //obj1の建区分
        String l_obj1ContractType = null;
        //obj2の建区分
        String l_obj2ContractType = null;

        if (l_obj1 instanceof WEB3FuturesCloseMarginGroup
            && l_obj2 instanceof WEB3FuturesCloseMarginGroup)
        {
            l_obj1ContractType = ((WEB3FuturesCloseMarginGroup)l_obj1).contractType;
            l_obj2ContractType = ((WEB3FuturesCloseMarginGroup)l_obj2).contractType;
        }
        else if (l_obj1 instanceof WEB3FuturesContractReferenceUnit
            && l_obj2 instanceof WEB3FuturesContractReferenceUnit)
        {
            l_obj1ContractType = ((WEB3FuturesContractReferenceUnit)l_obj1).contractType;
            l_obj2ContractType = ((WEB3FuturesContractReferenceUnit)l_obj2).contractType;
        }
        else
        {
            throw new IllegalArgumentException("パラメータの類型が不正、該当する'WEB3FuturesCloseMarginGroup' または 'WEB3FuturesContractReferenceUnit' 類型。");
        }

        if (l_obj1ContractType == null || l_obj2ContractType == null)
        {
            int l_intResult;
               
            if (l_obj1ContractType == null && l_obj2ContractType == null)
            {
                l_intResult = 0;
            }
            else if (l_obj1ContractType == null)
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
            }
            else
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
            }
                        
            return l_intResult;            
        }

        if (WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            //昇順指定の場合
            return l_obj1ContractType.compareTo(l_obj2ContractType);
        }
        else
        {
            //降順指定の場合
            return - (l_obj1ContractType.compareTo(l_obj2ContractType));
        }
    }

    /**
     * パラメータのオブジェクトがこのコンパレータと等しいかどうかを判定する。<BR>
     * <BR>
     * スーパークラスのequalsをコールする。<BR>
     * @@param l_obj
     * @@return boolean
     * @@roseuid 40CFEC9301DE
     */
    public boolean equals(Object l_obj)
    {
        //スーパークラスのequalsをコールする。
        if (l_obj instanceof WEB3FuturesContractDivisionComparator)
            //        if (l_obj instanceof  WEB3OpenDateComparator)
        {
            WEB3FuturesContractDivisionComparator l_comparator =
                (WEB3FuturesContractDivisionComparator)l_obj;
            if (this.orderBy.equals(l_comparator.orderBy))
            {
                return true;
            }
        }
        return false;
    }
}
@
