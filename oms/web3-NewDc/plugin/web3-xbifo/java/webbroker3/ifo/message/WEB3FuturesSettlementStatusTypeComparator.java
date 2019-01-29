head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.11.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesSettlementStatusTypeComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :先物決済状態区分Comparator(WEB3FuturesSettlementStatusTypeComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20   王暁傑 (Sinocom) 新規作成 
*/
package webbroker3.ifo.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物決済状態区分Comparator)
 */
public class WEB3FuturesSettlementStatusTypeComparator implements Comparator
{

    /**
    * ログユーティリティ<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FuturesSettlementStatusTypeComparator.class);
        
    /**
     * A：昇順<BR>
     * D：降順<BR>
     */
    private String orderBy;

    /**
     * @@roseuid 40C07547038A
     */
    public WEB3FuturesSettlementStatusTypeComparator()
    {

    }

    /**
     * 先物決済状態区分Comparatorのコンストラクタ。<BR>
     * <BR>
     * パラメータ.orderByをフィールドのorderByにセットする<BR>
     * @@param l_orderBy - ソートキーの昇順降順を示す。<BR>
     * <BR>
     * A：昇順<BR>
     * D：降順
     * @@return Void
     * @@roseuid 4085D0160399
     */
    public WEB3FuturesSettlementStatusTypeComparator(String l_strOrderBy)
    {
        if (!WEB3AscDescDef.ASC.equals(l_strOrderBy) 
            && !WEB3AscDescDef.DESC.equals(l_strOrderBy))
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
     *     株価指数先物建玉照会明細クラス<BR>
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
     * @@return int
     * @@roseuid 4085D01603C8
     */
    public int compare(Object l_obj1, Object l_obj2)
    {
        final String STR_METHOD_NAME = "compare(Object l_obj1, Object l_obj2)";
        log.entering(STR_METHOD_NAME);

        //obj1の建区分
        String l_obj1SettlementState = null;
        //obj2の建区分
        String l_obj2SettlementState = null;
        
        if (l_obj1 instanceof WEB3FuturesContractReferenceUnit 
            && l_obj2 instanceof WEB3FuturesContractReferenceUnit)
        {
            //株価指数先物建玉照会明細クラス
            l_obj1SettlementState = ((WEB3FuturesContractReferenceUnit)l_obj1).settlementState;
            l_obj2SettlementState = ((WEB3FuturesContractReferenceUnit)l_obj2).settlementState;
        }
        else
        {
            throw new IllegalArgumentException("パラメータの類型が不正、該当する'WEB3FuturesContractReferenceUnit'類型。");
        }     

        if (l_obj1SettlementState == null || l_obj2SettlementState == null)
        {
            int l_intResult;
               
            if (l_obj1SettlementState == null && l_obj2SettlementState == null)
            {
                l_intResult = 0;
            }
            else if (l_obj1SettlementState == null)
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
            return l_obj1SettlementState.compareTo(l_obj2SettlementState);
        }
        else
        {
            //降順指定の場合
            return -(l_obj1SettlementState.compareTo(l_obj2SettlementState));
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
        //スーパークラスのequalsをコールする。
        if (l_obj instanceof  WEB3FuturesSettlementStatusTypeComparator)
        {
            WEB3FuturesSettlementStatusTypeComparator l_comparator =
                (WEB3FuturesSettlementStatusTypeComparator) l_obj;
            if (this.orderBy.equals(l_comparator.orderBy))
            {
                return true;
            }
        }
        return false; 
    }
}
@
