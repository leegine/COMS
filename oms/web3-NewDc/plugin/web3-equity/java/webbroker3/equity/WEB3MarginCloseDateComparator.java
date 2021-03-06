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
filename	WEB3MarginCloseDateComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用期日Comparator(WEB3MarginCloseDateComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 凌建平 (中訊) 新規作成
                   2005/01/06 岡村和明 (SRA) JavaDoc修正
*/

package webbroker3.equity;

import java.util.Comparator;
import java.util.Date;
import webbroker3.util.WEB3LogUtility;
import webbroker3.equity.WEB3MarginContractInfo;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3DateUtility;

/**
 * （信用期日Comparator）。<BR>
 * <BR>
 * 信用期日Comparator
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginCloseDateComparator implements Comparator 
{
    /**
    * (ログユーティリティ)<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
    WEB3MarginCloseDateComparator.class);    
    
    /**
     * 昇順（：asc）、降順（：desc）を指定するフラグ。 <BR>
     * <BR>
     * 　@A：昇順 <BR>
     * 　@D：降順 <BR>
     */
    private String orderBy;
    
    /**
     * @@roseuid 4142B3280369
     */
    public WEB3MarginCloseDateComparator() 
    {
     
    }
    
    /**
     * (信用期日Comparator)<BR>
     * 信用期日Comparatorのコンストラクタ。<BR>
     * <BR>
     * 引数.orderByをthis.orderByにセットする。 <BR>
     * @@param l_strOrderBy - 昇順（：asc）、降順（：desc）を指定するフラグ。 <BR>
     * <BR>
     * 　@A：昇順 <BR>
     * 　@D：降順 <BR>
     * @@return WEB3MarginCloseDateComparator
     * @@roseuid 40F338890010
     */
    public WEB3MarginCloseDateComparator(String l_strOrderBy) 
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
     * 期日の比較を行う。 <BR>
     * <BR>
     * １）　@引数のcast <BR>
     * 　@引数の建株情報1、建株情報2を、信用取引建株情報の型にcastする。 <BR>
     * <BR>
     * ２）　@比較 <BR>
     * 　@１）でcastした建株情報1、建株情報2について<BR> 
     * <BR>
     * 　@[昇順指定の場合（this.orderBy == ”昇順”）] <BR>
     * 　@・（建株情報1.期日 < 建株情報2.期日）の場合、<BR>
     * 負の整数（-1）を返却する。 <BR>
     * 　@・（建株情報1.期日 == 建株情報2.期日）の場合、0を返却する。 <BR>
     * 　@・（建株情報1.期日 > 建株情報2.期日）の場合、<BR>
     * 正の整数（1）を返却する。 <BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”）] <BR>
     * 　@・（建株情報1.期日 < 建株情報2.期日）の場合、<BR>
     * 正の整数（1）を返却する。 <BR>
     * 　@・（建株情報1.期日 == 建株情報2.期日）の場合、0を返却する。 <BR>
     * 　@・（建株情報1.期日 > 建株情報2.期日）の場合、<BR>
     * 負の整数（-1）を返却する。 <BR>
     * @@param l_obj1 - (建株情報1)<BR>
     * 信用取引建株情報オブジェクト1
     * @@param l_obj2 - (建株情報2)<BR>
     * 信用取引建株情報オブジェクト2
     * @@return int
     * @@roseuid 40F28EBF01BE
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME = "compare(Object l_obj1, Object l_obj2)";
        log.entering(STR_METHOD_NAME);
        //obj1の建日
        Date l_obj1CloseDate = null;
        //obj2の建日
        Date l_obj2CloseDate = null;
        
        //引数のcast
        if (l_obj1 instanceof WEB3MarginContractInfo && l_obj2 instanceof WEB3MarginContractInfo)
        {
            //信用取引建株情報クラス
            l_obj1CloseDate = ((WEB3MarginContractInfo)l_obj1).closeDate;
            l_obj2CloseDate = ((WEB3MarginContractInfo)l_obj2).closeDate;            
        }
        else
        {
            throw new IllegalArgumentException("パラメータの類型が不正、該当する'WEB3MarginContractInfo' 類型。");
        }
        
        if (WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            //昇順指定の場合
            if (WEB3DateUtility.compareToDay(l_obj1CloseDate, l_obj2CloseDate) < 0)
            {
                return -1;
            }
            else if (WEB3DateUtility.compareToDay(l_obj1CloseDate, l_obj2CloseDate) == 0)
            {
                return 0;
            }
            else{
                return 1;
            }
        }
        else
        {
            //降順指定の場合
            if (WEB3DateUtility.compareToDay(l_obj1CloseDate, l_obj2CloseDate) < 0)
            {
                return 1;
            }
            else if (WEB3DateUtility.compareToDay(l_obj1CloseDate, l_obj2CloseDate) == 0)
            {
                return 0;
            }
            else{
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
     * @@roseuid 40F28EC403A2
     */
    public boolean equals(Object l_arg0) 
    {
        //スーパークラスのequalsをコールする。
        if (l_arg0 instanceof  WEB3MarginCloseDateComparator)
        {
            WEB3MarginCloseDateComparator l_comparator =
                    ( WEB3MarginCloseDateComparator) l_arg0;
            if (this.orderBy.equals(l_comparator.orderBy))
            {
                return true;
            }
        }
        return false; 
    }
}
@
