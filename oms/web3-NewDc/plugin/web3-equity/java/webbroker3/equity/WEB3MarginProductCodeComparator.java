head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginProductCodeComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  信用銘柄コードComparator(WEB3MarginProductCodeComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/16 李松峰 (中訊) 新規作成
*/
package webbroker3.equity;

import java.util.Comparator;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.equity.message.WEB3MarginCloseMarginGroup;
import webbroker3.util.WEB3LogUtility;


/**
 * （信用銘柄コードComparator）。<BR>
 * <BR>
 * 信用銘柄コードComparator
 * @@version 1.0
 */
public class WEB3MarginProductCodeComparator implements Comparator 
{ 
    /**
    * Logger
    */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3MarginProductCodeComparator.class);    
    
    /**
     * 昇順（：asc）、降順（：desc）を指定するフラグ。 <BR>
     * <BR>
     * 　@A：昇順 <BR>
     * 　@D：降順 <BR>
     */
    private String orderBy;
    
    /**
     * @@roseuid 4142B32A0268
     */
    public WEB3MarginProductCodeComparator() 
    {
     
    }
    
    /**
     * (信用銘柄コードComparator)<BR>
     * 信用銘柄コードComparatorのコンストラクタ。<BR>
     * <BR>
     * 引数.orderByをthis.orderByにセットする。 <BR>
     * @@param l_strOrderBy - 昇順（：asc）、降順（：desc）を指定するフラグ。 <BR>
     * <BR>
     * 　@A：昇順  <BR>
     * 　@D：降順  <BR>
     * @@return WEB3MarginProductCodeComparator
     * @@roseuid 40F341650168
     */
    public WEB3MarginProductCodeComparator(String l_strOrderBy) 
    {
        final String STR_METHOD_NAME="WEB3MarginProductCodeComparator(String)";
        log.entering(STR_METHOD_NAME);
        if (l_strOrderBy == null || (!WEB3AscDescDef.ASC.equals(l_strOrderBy) && !WEB3AscDescDef.DESC.equals(l_strOrderBy)))
        {
            throw new IllegalArgumentException("引数の値が'A：昇順'、'D：降順'以外です。");
        }
        this.orderBy=l_strOrderBy;
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     *（compareの実装） <BR>
     * <BR>
     * 銘柄コードの比較を行う。 <BR>
     * <BR>
     * １）　@引数のオブジェクトの判定<BR>
     * instanceofにて、引数のオブジェクト建株情報1、<BR>
     * 建株情報2が以下のクラスのどちらかを判定する。 <BR>
     * <BR>
     * 信用取引建株情報クラス<BR>
     * 信用取引決済一覧行クラス<BR>
     * <BR>
     * ２）　@引数のcast <BR>
     * 　@引数の建株情報1、建株情報2を、１）で判定したクラスの型にcastする。<BR> 
     * <BR>
     * ３）　@比較 <BR>
     * 　@２）でcastした建株情報1、建株情報2について <BR>
     * <BR>
     * 　@[昇順指定の場合（this.orderBy == ”昇順”）] <BR>
     * 　@・（建株情報1.銘柄コード < 建株情報2.銘柄コード）の場合、<BR>
     * 負の整数（-1）を返却する。 <BR>
     * 　@・（建株情報1.銘柄コード == 建株情報2.銘柄コード）の場合、<BR>
     * 0を返却する。 <BR>
     * 　@・（建株情報1.銘柄コード > 建株情報2.銘柄コード）の場合、<BR>
     * 正の整数（1）を返却する。 <BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”）] <BR>
     * 　@・（建株情報1.銘柄コード < 建株情報2.銘柄コード）の場合、<BR>
     * 正の整数（1）を返却する。 <BR>
     * 　@・（建株情報1.銘柄コード == 建株情報2.銘柄コード）の場合、<BR>
     * 0を返却する。 <BR>
     * 　@・（建株情報1.銘柄コード > 建株情報2.銘柄コード）の場合、<BR>
     * 負の整数（-1）を返却する。 <BR>
     * @@param l_obj1 - (建株情報1)<BR>
     * 信用取引建株情報オブジェクト1、または、信用取引決済一覧行オブジェクト1
     * @@param l_obj2 - (建株情報2)<BR>
     * 信用取引建株情報オブジェクト2、または、信用取引決済一覧行オブジェクト2
     * @@return int
     * @@roseuid 40F34165016C
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME="compare(object,object)";
        log.entering(STR_METHOD_NAME);
    
        String l_strproductCode1;
        String l_strproductCode2;
        if ((l_obj1 instanceof WEB3MarginContractInfo) && (l_obj2 instanceof WEB3MarginContractInfo))
        {
            l_strproductCode1=((WEB3MarginContractInfo)l_obj1).productCode;
            l_strproductCode2=((WEB3MarginContractInfo)l_obj2).productCode;
        }else if ((l_obj1 instanceof WEB3MarginCloseMarginGroup ) && (l_obj2 instanceof WEB3MarginCloseMarginGroup))
        {
            l_strproductCode1=((WEB3MarginCloseMarginGroup)l_obj1).productCode;
            l_strproductCode2=((WEB3MarginCloseMarginGroup)l_obj2).productCode;
        }
        else
        {        
            String l_strErrorMessage = "引数の類型が不正";
            log.error(l_strErrorMessage);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80002,STR_METHOD_NAME);
        }
        if (WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            if (Double.parseDouble(l_strproductCode1) <Double.parseDouble(l_strproductCode2))
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }else if (Double.parseDouble(l_strproductCode1) == Double.parseDouble(l_strproductCode2))
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }else
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }    
        }
        if (WEB3AscDescDef.DESC.equals(this.orderBy))
        {
            if (Double.parseDouble(l_strproductCode1) < Double.parseDouble(l_strproductCode2))
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }else if (Double.parseDouble(l_strproductCode1) == Double.parseDouble(l_strproductCode2))
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }else
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }    
        }
        log.exiting(STR_METHOD_NAME);
     return 0;
    }
    
    /**
     *（equalsの実装） <BR>
     * <BR>
     * 未使用。 <BR>
     * falseを返却する。 <BR>
     * @@param l_arg0
     * @@return boolean
     * @@roseuid 40F34165016A
     */
    public boolean equals(Object l_arg0) 
    {
     return true;
    }
}
@
