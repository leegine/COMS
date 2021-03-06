head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginAccountTypeComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用口座区分Comparator(WEB3MarginAccountTypeComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 凌建平 (中訊) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/

package webbroker3.equity;

import java.util.Comparator;
import webbroker3.util.WEB3LogUtility;
import webbroker3.equity.WEB3MarginContractInfo;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.equity.message.WEB3MarginCloseMarginGroup;

/**
 * （信用口座区分Comparator）。<BR>
 * <BR>
 * 信用口座区分Comparator
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginAccountTypeComparator implements Comparator 
{
    /**
     * (ログユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
    WEB3MarginAccountTypeComparator.class);
    
    /**
     * 昇順（：asc）、降順（：desc）を指定するフラグ。 <BR>
     * <BR>
     * 　@A：昇順 <BR>
     * 　@D：降順 <BR>
     */
    private String orderBy;
    
    /**
     * @@roseuid 4142B32802B5
     */
    public WEB3MarginAccountTypeComparator() 
    {
     
    }
    
    /**
     * (信用口座区分Comparator)<BR>
     * 信用口座区分Comparatorのコンストラクタ。<BR>
     * <BR>
     * 引数.orderByをthis.orderByにセットする。 <BR>
     * @@param l_strOrderBy - 昇順（：asc）、降順（：desc）を指定するフラグ。 <BR>
     * <BR>
     * 　@A：昇順 <BR>
     * 　@D：降順 <BR>
     * @@return WEB3MarginAccountTypeComparator
     * @@roseuid 40F33E9A005F
     */
    public WEB3MarginAccountTypeComparator(String l_strOrderBy) 
    {
        if (!WEB3AscDescDef.ASC.equals(l_strOrderBy)
            && !WEB3AscDescDef.DESC.equals(l_strOrderBy))
        {
            throw new IllegalArgumentException("パラメータの値が'A：昇順'、'D：降順'以外です。");
        }
        this.orderBy = l_strOrderBy;
    }
    
    /**
     * （compareの実装） <BR>
     * <BR>
     * 口座区分の比較を行う。 <BR>
     * <BR>
     * １）　@引数のオブジェクトの判定<BR>
     * instanceofにて、引数のオブジェクト建株情報1、建株情報2が以下の<BR>
     * クラスのどちらかを判定する。 <BR>
     * <BR>
     * 信用取引建株情報クラス<BR>
     * 信用取引決済一覧行クラス<BR>
     * <BR>
     * ２）　@引数のcast <BR>
     * 　@引数の建株情報1、建株情報2を、１）で判定したクラスの型にcastする。 <BR>
     * <BR>
     * ３）　@比較 <BR>
     * 　@２）でcastした建株情報1、建株情報2について <BR>
     * <BR>
     * 　@[昇順指定の場合（this.orderBy == ”昇順”）] <BR>
     * 　@・（建株情報1.口座区分 < 建株情報2.口座区分）の場合、<BR>
     * 負の整数（-1）を返却する。 <BR>
     * 　@・（建株情報1.口座区分 == 建株情報2.口座区分）の場合、0を返却する。<BR>
     * 　@・（建株情報1.口座区分 > 建株情報2.口座区分）の場合、<BR>
     * 正の整数（1）を返却する。 <BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”）] <BR>
     * 　@・（建株情報1.口座区分 < 建株情報2.口座区分）の場合、<BR>
     * 正の整数（1）を返却する。 <BR>
     * 　@・（建株情報1.口座区分 == 建株情報2.口座区分）の場合、<BR>
     * 0を返却する。 <BR>
     * 　@・（建株情報1.口座区分 > 建株情報2.口座区分）の場合、<BR>
     * 負の整数（-1）を返却する。 <BR>
     * @@param l_obj1 - (建株情報1)<BR>
     * 信用取引建株情報オブジェクト1、または、信用取引決済一覧行オブジェクト1
     * @@param l_obj2 - (建株情報2)<BR>
     * 信用取引建株情報オブジェクト2、または、信用取引決済一覧行オブジェクト2
     * @@return int
     * @@roseuid 40F33E9A006E
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME = "compare(Object l_obj1, Object l_obj2)";
        log.entering(STR_METHOD_NAME);
        //obj1の建日
        String l_obj1AccountType = null;
        //obj2の建日
        String l_obj2AccountType = null;
        
        //引数のcast
        if (l_obj1 instanceof WEB3MarginContractInfo && l_obj2 instanceof WEB3MarginContractInfo)
        {
            //信用取引建株情報クラス
            l_obj1AccountType = ((WEB3MarginContractInfo)l_obj1).accountType;
            l_obj2AccountType = ((WEB3MarginContractInfo)l_obj2).accountType;            
        }
        else if (l_obj1 instanceof WEB3MarginCloseMarginGroup && l_obj2 instanceof WEB3MarginCloseMarginGroup)
        {
            //信用取引決済一覧行クラス
            l_obj1AccountType = ((WEB3MarginCloseMarginGroup)l_obj1).taxType;
            l_obj2AccountType = ((WEB3MarginCloseMarginGroup)l_obj2).taxType;
        }
        else
        {
            throw new IllegalArgumentException("パラメータの類型が不正、該当する'WEB3MarginContractInfo' または 'WEB3MarginCloseMarginGroup' 類型。");
        }
        
        if (WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            //昇順指定の場合
            if (Double.parseDouble(l_obj1AccountType) < Double.parseDouble(l_obj2AccountType))
            {
                return -1;
            }
            else if (Double.parseDouble(l_obj1AccountType) == Double.parseDouble(l_obj2AccountType))
            {
                return 0;
            }
            else
            {
                return 1;
            }
        }
        else
        {
            //降順指定の場合
            if (Double.parseDouble(l_obj1AccountType) < Double.parseDouble(l_obj2AccountType))
            {
                return 1;
            }
            else if (Double.parseDouble(l_obj1AccountType) == Double.parseDouble(l_obj2AccountType))
            {
                return 0;
            }
            else
            {
                return -1;
            }
        }
    }
    
    /**
     * （equalsの実装） <BR>
     * <BR>
     * 未使用。 <BR>
     * falseを返却する。 <BR>
     * @@param l_arg0
     * @@return boolean
     * @@roseuid 40F33E9A0071
     */
    public boolean equals(Object l_arg0) 
    {
        //スーパークラスのequalsをコールする。
        if (l_arg0 instanceof  WEB3MarginAccountTypeComparator)
        {
            WEB3MarginAccountTypeComparator l_comparator =
                    ( WEB3MarginAccountTypeComparator) l_arg0;
            if (this.orderBy.equals(l_comparator.orderBy))
            {
                return true;
            }
        }
        return false; 
    }
}
@
