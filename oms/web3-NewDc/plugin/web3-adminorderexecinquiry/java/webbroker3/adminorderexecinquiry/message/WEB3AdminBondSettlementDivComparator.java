head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.43.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminBondSettlementDivComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (債券)決済区分Comparator(WEB3AdminBondSettlementDivComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 何文敏(中訊) 新規作成    
*/

package webbroker3.adminorderexecinquiry.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * ((債券)決済区分Comparator)<BR>
 * (債券)決済区分Comparator<BR>
 *   
 * @@author 何文敏(中訊)
 * @@version 1.0
 */

public class WEB3AdminBondSettlementDivComparator implements Comparator 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondSettlementDivComparator.class);
    
    /**
     * orderBy<BR>
     * A：昇順<BR>
     * D：降順
     */
    private String orderBy;
    
    /**
     * (債券)決済区分Comparator<BR>
     * コンストラクタ。<BR>
     * <BR>
     * パラメータ.orderByをフィールドのorderByにセットする
     * @@param l_strOrderBy - (orderBy)<BR>
     * ソートキーの昇順降順を示す。<BR>
     * <BR>
     * A：昇順<BR>
     * D：降順
     * @@roseuid 44C03DA40190
     */
    public WEB3AdminBondSettlementDivComparator(String l_strOrderBy) 
    {
        final String STR_METHOD_NAME =
            "WEB3AdminBondSettlementDivComparator(String l_strOrderBy)";
            log.entering(STR_METHOD_NAME);
        
        if(l_strOrderBy == null ||
            (!l_strOrderBy.equals(WEB3AscDescDef.ASC) &&
            !l_strOrderBy.equals(WEB3AscDescDef.DESC)))
        {
            throw new IllegalArgumentException(
            "パラメータの値が'A：昇順'、'D：降順'以外です。"); 
        }
        this.orderBy = l_strOrderBy;
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * パラメータのオブジェクトがこのコンパレータと等しいかどうかを判定する。<BR>
     * <BR>
     * スーパークラスのequalsをコールする。
     * @@param l_obj - (Object)<BR>
     * 比較対象の参照オブジェクト
     * @@return boolean
     * @@roseuid 44C04FA803A6
     */
    public boolean equals(Object l_obj) 
    {
        return super.equals(l_obj);
    }
    
    /**
     * （compareの実装）<BR>
     * <BR>
     * 明細1，2について、決済区分の比較を行う。<BR>
     * <BR>
     * １）パラメータを債券管理者注文約定照会行にキャストする。<BR>
     * <BR>
     * ２）比較<BR>
     * 　@１）でキャストした明細1、明細2の決済区分を比較し、結果を返却する。<BR> 
     * <BR>
     * 　@昇順指定の場合、<BR>
     * 　@　@明細1の決済区分が、明細2の決済区分より小さい場合、負の整数（-1）を返却する。<BR> 
     * 　@　@両方が等しい場合、0を返却する。<BR>
     * 　@　@明細1の決済区分が、明細2の決済区分より大きい場合、正の整数（1）を返却する。<BR>  
     * <BR>
     * 　@降順指定の場合、<BR>
     * 　@　@明細1の決済区分が、明細2の決済区分より小さい場合、正の整数（1）を返却する。<BR>
     * 　@　@両方が等しい場合、0を返却する。<BR>
     * 　@　@明細1の決済区分が、明細2の決済区分より大きい場合、負の整数（-1）を返却する。<BR> 
     * <BR>
     * 　@昇降順の判定はコンストラクタでセットされたorderByの値を用いる
     * @@param l_obj1 - (明細1)<BR>
     * 債券管理者注文約定照会行オブジェクト１
     * @@param l_obj2 - (明細2)<BR>
     * 債券管理者注文約定照会行オブジェクト２
     * @@return int
     * @@roseuid 44C05042039A
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME = "compare(Object l_obj1, Object l_obj2)";
        log.entering(STR_METHOD_NAME);
        
        
        //１）パラメータを債券管理者注文約定照会行にキャストする。     
        String l_strVal1 = null;
        String l_strVal2 = null;
        if(l_obj1 instanceof WEB3AdminORBondExecRefUnit && 
           l_obj2 instanceof WEB3AdminORBondExecRefUnit)
        {
            l_strVal1 = ((WEB3AdminORBondExecRefUnit) l_obj1).settleDiv;
            l_strVal2 = ((WEB3AdminORBondExecRefUnit) l_obj2).settleDiv;
        }
        else
        {

            throw new IllegalArgumentException(
                "パラメータの類型が不正、該当する'WEB3AdminORBondExecRefUnit'類型。");
        }
        
        
        // ２）比較
        int l_intReturn = 0;
        if(l_strVal1 == null || l_strVal2 == null)
        {
            if(l_strVal1 == null && l_strVal2 == null)
            {
                l_intReturn = 0;
            }
            else if(l_strVal1 == null)
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
                if(WEB3AscDescDef.ASC.equals(this.orderBy))
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
            if(l_strVal1.equals(l_strVal2))
            {
                l_intReturn = 0;
            }
            else if(l_strVal1.compareTo(l_strVal2) >0)
            {
                if(WEB3AscDescDef.ASC.equals(this.orderBy))
                {
                    l_intReturn = 1;
                }
                else
                {
                    l_intReturn = -1;
                }
            }
            else if(l_strVal1.compareTo(l_strVal2) < 0)
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
        }
        log.exiting(STR_METHOD_NAME);
        return l_intReturn;
    }
}
@
