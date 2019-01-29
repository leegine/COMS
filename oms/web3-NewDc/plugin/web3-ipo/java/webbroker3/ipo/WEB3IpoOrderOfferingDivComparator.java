head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.44.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoOrderOfferingDivComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO申告.購入申込区分Comparator(WEB3IpoOrderOfferingDivComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/11 斉麟 (中訊) 新規作成
*/

package webbroker3.ipo;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.util.WEB3LogUtility;


/**
 * IPO申告.購入申込区分Comparator
 *                                                               
 * @@author 斉麟
 * @@version 1.0
 */
public class WEB3IpoOrderOfferingDivComparator implements Comparator 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IpoOrderOfferingDivComparator.class);
    
    /**
     * 昇順（：asc）、降順（：desc）を指定するフラグ。<BR>
     * <BR>
     * 　@A：昇順 <BR>
     * 　@D：降順
     */
    private String orderBy;
    
    /**
     * @@roseuid 411305D902D9
     */
    public WEB3IpoOrderOfferingDivComparator() 
    {
     
    }
    
    /**
     * (IPO申告.購入申込区分Comparator)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * 引数の値をthis.orderByにセットする。
     * @@param l_strOrderBy - (orderBy)<BR>
     * 昇順（：asc）、降順（：desc）を指定するフラグ。<BR>
     * <BR>
     * 　@A：昇順 <BR>
     * 　@D：降順
     * 
     * @@return WEB3IpoOrderOfferingDivComparator
     * @@roseuid 40ECEB5A0093
     */
    public WEB3IpoOrderOfferingDivComparator(String l_strOrderBy) 
    {
        final String STR_METHOD_NAME = " WEB3IpoOrderOfferingDivComparator(String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strOrderBy == null
            || (!l_strOrderBy.equals(WEB3AscDescDef.ASC) && !l_strOrderBy.equals(WEB3AscDescDef.DESC)))
        {
            throw new IllegalArgumentException("パラメータの値が'A：昇順'、'D：降順'以外です。");
        }
        
        this.orderBy = l_strOrderBy;
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （compareの実装）<BR>
     * <BR>
     * 購入申込区分の比較を行う。 <BR>
     * <BR>
     * １）　@引数のcast<BR>
     * 　@引数のIPO申告１、IPO申告２をIPO申告型にcastする。<BR>
     * <BR>
     * ２）　@比較 <BR>
     * 　@１）でcastしたIPO申告１、IPO申告２について <BR>
     * <BR>
     * 　@[昇順指定の場合（this.orderBy == ”昇順”）]<BR>
     * 　@・（IPO申告１.IPO申告行.購入申込区分 <<BR>
     *  IPO申告２.IPO申告行.購入申込区分）の場合、負の整数（-1）を返却する。<BR>
     * 　@・（IPO申告１.IPO申告行.購入申込区分 =<BR>
     *  IPO申告２.IPO申告行.購入申込区分）の場合、0を返却する。<BR>
     * 　@・（IPO申告１.IPO申告行.購入申込区分 ><BR>
     *  IPO申告２.IPO申告行.購入申込区分）の場合、正の整数（1）を返却する。<BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”）]<BR>
     * 　@・（IPO申告１.IPO申告行.購入申込区分 < <BR>
     * IPO申告２.IPO申告行.購入申込区分）の場合、正の整数（1）を返却する。<BR>
     * 　@・（IPO申告１.IPO申告行.購入申込区分 =<BR>
     *  IPO申告２.IPO申告行.購入申込区分）の場合、0を返却する。<BR>
     * 　@・（IPO申告１.IPO申告行.購入申込区分 ><BR>
     *  IPO申告２.IPO申告行.購入申込区分）の場合、負の整数（-1）を返却する。<BR>
     * @@param l_ipoOrder1 - (IPO申告１)<BR>
     * IPO申告オブジェクト１
     * 
     * @@param l_ipoOrder2 - (IPO申告２)<BR>
     * IPO申告オブジェクト２
     * 
     * @@return int
     * @@roseuid 40ECEB5A008E
     */
    public int compare(Object l_ipoOrder1, Object l_ipoOrder2) 
    {
        final String STR_METHOD_NAME = " compare(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        String l_strOfferDiv1 = null;
        String l_strOfferDiv2 = null;
        
        if (!(l_ipoOrder1 instanceof WEB3IpoOrderImpl) || !(l_ipoOrder2 instanceof WEB3IpoOrderImpl))
        {
            throw new IllegalArgumentException("パラメータの類型が不正、該当する'WEB3IpoOrder' 類型。");
        }
        
        l_strOfferDiv1 = ((IpoOrderRow)(((WEB3IpoOrderImpl)l_ipoOrder1).getDataSourceObject())).getOfferingDiv();
        l_strOfferDiv2 = ((IpoOrderRow)(((WEB3IpoOrderImpl)l_ipoOrder2).getDataSourceObject())).getOfferingDiv();
                
        if (l_strOfferDiv1 == null && l_strOfferDiv2 == null)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        else if (WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            if (l_strOfferDiv2 == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }
            else if (l_strOfferDiv1 == null)
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }
            else if (l_strOfferDiv1.compareTo(l_strOfferDiv2) > 0) 
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }
            else if (l_strOfferDiv1.compareTo(l_strOfferDiv2) < 0)
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
        }
        else
        {
            if (l_strOfferDiv2 == null)
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }
            else if (l_strOfferDiv1 == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }
            else if (l_strOfferDiv1.compareTo(l_strOfferDiv2) > 0) 
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }
            else if (l_strOfferDiv1.compareTo(l_strOfferDiv2) < 0)
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
        }
    }
    
    /**
     * （equalsの実装）<BR>
     * <BR>
     * 未使用。<BR>
     * falseを返却する。
     * @@param l_arg0 - (arg0)
     * @@return boolean
     * @@roseuid 40ECEB5A0091
     */
    public boolean equals(Object l_arg0) 
    {
        return false;
    }
}
@
