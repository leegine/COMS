head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginRepaymentTypeComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  信用取引弁済区分Comparator(WEB3MarginRepaymentTypeComparator.java)
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
 * （信用取引弁済区分Comparator）。<BR>
 * <BR>
 * 信用取引弁済区分Comparator
 * @@version 1.0
 */
public class WEB3MarginRepaymentTypeComparator implements Comparator 
{ 
   /**
    * Logger
    */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3MarginRepaymentTypeComparator.class);    
    /**
     * 昇順（：asc）、降順（：desc）を指定するフラグ。 <BR>
     * <BR>
     * 　@A：昇順 <BR>
     * 　@D：降順 <BR>
     */
    private String orderBy;
    
    /**
     * @@roseuid 4142B32A03D0
     */
    public WEB3MarginRepaymentTypeComparator() 
    {
     
    }
    
    /**
     * (信用弁済期限区分Comparator)<BR>
     * 信用弁済区分Comparatorのコンストラクタ。<BR>
     * <BR>
     * 引数.orderByをthis.orderByにセットする。 <BR>
     * @@param l_strOrderBy - 昇順（：asc）、降順（：desc）を指定するフラグ。 <BR>
     * <BR>
     * 　@A：昇順 <BR>
     * 　@D：降順 <BR>
     * @@return WEB3MarginRepaymentTypeComparator
     * @@roseuid 40F3445601B6
     */
    public WEB3MarginRepaymentTypeComparator(String l_strOrderBy) 
    {
        final String STR_METHOD_NAME="WEB3MarginRepaymentTypeComparator(String)";
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
     * 弁済区分の比較を行う。 <BR>
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
     * 　@３－１）<BR>
     * 　@[昇順指定の場合（this.orderBy == ”昇順”）] <BR>
     * <BR>
     * 　@３－１－１）　@castした型が信用取引建株情報の場合<BR>
     * 　@・（建株情報1.弁済区分 < 建株情報2.弁済区分）の場合、<BR>
     * 負の整数（-1）を返却する。 <BR>
     * 　@・（建株情報1.弁済区分 == 建株情報2.弁済区分）の場合、<BR>
     * 0を返却する。 <BR>
     * 　@・（建株情報1.弁済区分 > 建株情報2.弁済区分）の場合、<BR>
     * 正の整数（1）を返却する。 <BR>
     * <BR>
     * 　@３－１－２）　@castした型が信用取引決済一覧行の場合(*)<BR>
     * 　@・（建株情報1.弁済.弁済区分 < 建株情報2.弁済.弁済区分）の場合、<BR>
     * 負の整数（-1）を返却する。 <BR>
     * 　@・（建株情報1.弁済.弁済区分 == 建株情報2.弁済.弁済区分）の場合、<BR>
     * 0を返却する。 <BR>
     * 　@・（建株情報1.弁済.弁済区分 > 建株情報2.弁済.弁済区分）の場合、<BR>
     * 正の整数（1）を返却する。 <BR>
     * <BR>
     * 　@３－２）<BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”）] <BR>
     * <BR>
     * 　@３－２－１）　@castした型が信用取引建株情報の場合<BR>
     * 　@・（建株情報1.弁済区分 < 建株情報2.弁済区分）の場合、<BR>
     * 正の整数（1）を返却する。 <BR>
     * 　@・（建株情報1.弁済区分 == 建株情報2.弁済区分）の場合、<BR>
     * 0を返却する。 <BR>
     * 　@・（建株情報1.弁済区分 > 建株情報2.弁済区分）の場合、<BR>
     * 負の整数（-1）を返却する。 <BR>
     * <BR>
     * 　@３－２－２）　@castした型が信用取引決済一覧行の場合(*)<BR>
     * 　@・（建株情報1.弁済.弁済区分 < 建株情報2.弁済.弁済区分）の場合、<BR>
     * 正の整数（1）を返却する。 <BR>
     * 　@・（建株情報1.弁済.弁済区分 == 建株情報2.弁済.弁済区分）の場合、<BR>
     * 0を返却する。 <BR>
     * 　@・（建株情報1.弁済.弁済区分 > 建株情報2.弁済.弁済区分）の場合、<BR>
     * 負の整数（-1）を返却する。 <BR>
     * <BR>
     * (*)信用取引決済一覧行は内部オブジェクトの信用取引弁済において<BR>
     * 弁済区分を保持している。(信用取引決済一覧行.弁済.弁済区分)<BR>
     * @@param l_obj1 - (建株情報1)<BR>
     * 信用取引建株情報オブジェクト1、または、信用取引決済一覧行オブジェクト1
     * @@param l_obj2 - (建株情報2)<BR>
     * 信用取引建株情報オブジェクト2、または、信用取引決済一覧行オブジェクト2
     * @@return int
     * @@roseuid 40F3445601BA
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME="compare(object,object)";
        log.entering(STR_METHOD_NAME);
    
        String l_strrepaymentType1;
        String l_strrepaymentType2;
        if ((l_obj1 instanceof WEB3MarginContractInfo ) && (l_obj2 instanceof WEB3MarginContractInfo))
        {
            l_strrepaymentType1=((WEB3MarginContractInfo)l_obj1).repaymentType;
            l_strrepaymentType2=((WEB3MarginContractInfo)l_obj2).repaymentType;
        }else if ((l_obj1 instanceof WEB3MarginCloseMarginGroup ) && (l_obj2 instanceof WEB3MarginCloseMarginGroup))
        {
            l_strrepaymentType1=((WEB3MarginCloseMarginGroup)l_obj1).repayment.repaymentDiv;
            l_strrepaymentType2=((WEB3MarginCloseMarginGroup)l_obj2).repayment.repaymentDiv;
        }
        else
        {        
            String l_strErrorMessage = "引数の類型が不正";
            log.error(l_strErrorMessage);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80002,STR_METHOD_NAME);
        }
        if (WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            if (Double.parseDouble(l_strrepaymentType1) <Double.parseDouble(l_strrepaymentType2))
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }else if (Double.parseDouble(l_strrepaymentType1) == Double.parseDouble(l_strrepaymentType2))
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
            if (Double.parseDouble(l_strrepaymentType1) < Double.parseDouble(l_strrepaymentType2))
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }else if (Double.parseDouble(l_strrepaymentType1) == Double.parseDouble(l_strrepaymentType2))
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
     * @@roseuid 40F3445601B8
     */
    public boolean equals(Object l_arg0) 
    {
        return true;
    }
}
@
