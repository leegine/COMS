head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.43.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoProductPublicOfferingDateComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO銘柄.公開日Comparator(WEB3IpoProductPublicOfferingDateComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 李頴淵 新規作成
*/

package webbroker3.ipo;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.ipo.data.IpoProductParams;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * IPO銘柄.公開日Comparator
 * 
 * @@author 李頴淵
 * @@version 1.0 
 */
public class WEB3IpoProductPublicOfferingDateComparator implements Comparator 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IpoProductPublicOfferingDateComparator.class);
    
    /**
     * 昇順（：asc）、降順（：desc）を指定するフラグ。<BR>
     * <BR>
     * 　@A：昇順 <BR>
     * 　@D：降順
     */
    private String orderBy;
    
    /**
     * @@roseuid 41130179029C
     */
    public WEB3IpoProductPublicOfferingDateComparator() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * <BR>
     * 引数の値をthis.orderByにセットする。
     * @@param l_strOrderBy - 昇順（：asc）、降順（：desc）を指定するフラグ。<BR>
     * <BR>
     * 　@A：昇順 <BR>
     * 　@D：降順
     * 
     * @@return webbroker3.ipo.WEB3IpoProductPublicOfferingDateComparator
     * @@roseuid 40FE2C0103B0
     */
    public WEB3IpoProductPublicOfferingDateComparator(String l_strOrderBy) 
    {
        final String STR_METHOD_NAME = " WEB3IpoProductPublicOfferingDateComparator(String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strOrderBy == null
                ||( !WEB3AscDescDef.ASC.equals(l_strOrderBy)
                && !WEB3AscDescDef.DESC.equals(l_strOrderBy)))
        {
            throw new IllegalArgumentException("パラメータの値が'A：昇順'、'D：降順'以外です。");
        }
        this.orderBy = l_strOrderBy;
        
        log.debug("orderBy = " + orderBy);
        
        log.exiting(STR_METHOD_NAME);
     
    }
    
    /**
     * （compareの実装）<BR>
     * <BR>
     * 公開日の比較を行う。 <BR>
     * <BR>
     * １）　@引数のcast<BR>
     * 　@引数のIPO銘柄１、IPO銘柄２をIPO銘柄行型（IPO銘柄Params）にcastする。<BR>
     * 　@※　@IPO銘柄ParamsはDDLより自動生成する。<BR>
     * <BR>
     * ２）　@比較 <BR>
     * 　@１）でcastしたIPO銘柄Params１、IPO銘柄Params２について <BR>
     * <BR>
     * 　@[昇順指定の場合（this.orderBy == ”昇順”）]<BR>
     * 　@・（IPO銘柄Params１.公開日 < IPO銘柄Params２.公開日）の場合、<BR>
     * 負の整数（-1）を返却する。<BR>
     * 　@・（IPO銘柄Params１.公開日 = IPO銘柄Params２.公開日）の場合、<BR>
     * 0を返却する。<BR>
     * 　@・（IPO銘柄Params１.公開日 > IPO銘柄Params２.公開日）の場合、<BR>
     * 正の整数（1）を返却する。<BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”）]<BR>
     * 　@・（IPO銘柄Params１.公開日 < IPO銘柄Params２.公開日）の場合、<BR>
     * 正の整数（1）を返却する。<BR>
     * 　@・（IPO銘柄Params１.公開日 = IPO銘柄Params２.公開日）の場合、<BR>
     * 0を返却する。<BR>
     * 　@・（IPO銘柄Params１.公開日 > IPO銘柄Params２.公開日）の場合、<BR>
     * 負の整数（-1）を返却する。<BR>
     * @@param l_product1 - IPO銘柄オブジェクト１
     * 
     * @@param l_product2 - IPO銘柄オブジェクト２
     * 
     * @@return int
     * @@roseuid 40FE2C0103AB
     */
    public int compare(Object l_product1, Object l_product2) 
    {
        final String STR_METHOD_NAME = " compare(Object,Object)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@引数のcast
        IpoProductRow l_ipoProductRow1 = null;
        IpoProductRow l_ipoProductRow2 = null;
         
        if((l_product1 instanceof IpoProductParams) && (l_product2 instanceof IpoProductParams))
        {          
            log.debug("パラメータの類型-------------------OK");
            l_ipoProductRow1 = (IpoProductRow)l_product1;
            
            l_ipoProductRow2 = (IpoProductRow)l_product2;                    
        }
        else
        {            
            throw new IllegalArgumentException(
                "パラメータの類型が不正、該当する'IpoProductParams'");                 
        }
        
        //昇順指定の場合
        if (WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            log.debug("l_ipoProductRow1.getPublicOfferingDate() = " + l_ipoProductRow1.getPublicOfferingDate());
            log.debug("l_ipoProductRow2.getPublicOfferingDate() = " + l_ipoProductRow2.getPublicOfferingDate());
            
            if (WEB3DateUtility.compareToDay(l_ipoProductRow1.getPublicOfferingDate(), l_ipoProductRow2.getPublicOfferingDate()) < 0)
            {
                log.debug("昇順指定の場合--------------------------１.公開日 < ２.公開日");
                log.exiting(STR_METHOD_NAME);
                return -1;
            }
            else if(WEB3DateUtility.compareToDay(l_ipoProductRow1.getPublicOfferingDate(),l_ipoProductRow2.getPublicOfferingDate()) == 0)
            {
                log.debug("昇順指定の場合--------------------------１.公開日 = ２.公開日");
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            else if(WEB3DateUtility.compareToDay(l_ipoProductRow1.getPublicOfferingDate(),l_ipoProductRow2.getPublicOfferingDate()) > 0)
            {
                log.debug("昇順指定の場合--------------------------１.公開日 > ２.公開日");
                log.exiting(STR_METHOD_NAME);
                return 1;
            }
        }
        //降順指定の場合
        else if (WEB3AscDescDef.DESC.equals(this.orderBy))
        {
            if (WEB3DateUtility.compareToDay(l_ipoProductRow1.getPublicOfferingDate(),l_ipoProductRow2.getPublicOfferingDate()) < 0)
            {
                log.debug("降順指定の場合--------------------------１.公開日 < ２.公開日");
                log.exiting(STR_METHOD_NAME);
                return 1;
            }
            else if(WEB3DateUtility.compareToDay(l_ipoProductRow1.getPublicOfferingDate(),l_ipoProductRow2.getPublicOfferingDate()) == 0)
            {
                log.debug("降順指定の場合--------------------------１.公開日 = ２.公開日");
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            else if(WEB3DateUtility.compareToDay(l_ipoProductRow1.getPublicOfferingDate(),l_ipoProductRow2.getPublicOfferingDate()) > 0)
            {
                log.debug("降順指定の場合--------------------------１.公開日 > ２.公開日");
                log.exiting(STR_METHOD_NAME);
                return -1;
            }
        }              
        log.exiting(STR_METHOD_NAME);
        return 0;
    }
    
    /**
     * （equalsの実装）<BR>
     * <BR>
     * 未使用。<BR>
     * falseを返却する。
     * @@param l_arg0
     * @@return boolean
     * @@roseuid 40FE2C0103AE
     */
    public boolean equals(Object l_arg0) 
    {
        final String STR_METHOD_NAME = " equals(Object)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return false;
    }
}
@
