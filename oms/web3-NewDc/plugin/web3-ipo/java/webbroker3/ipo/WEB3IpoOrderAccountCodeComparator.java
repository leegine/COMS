head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.45.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoOrderAccountCodeComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO申告.顧客コードComparator(WEB3IpoOrderAccountCodeComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/11 斉麟 (中訊) 新規作成
*/


package webbroker3.ipo;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * IPO申告.顧客コードComparator
 *                                                               
 * @@author 斉麟
 * @@version 1.0
 */
public class WEB3IpoOrderAccountCodeComparator implements Comparator 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IpoOrderAccountCodeComparator.class);
    
    /**
     * 昇順（：asc）、降順（：desc）を指定するフラグ。<BR>
     * <BR>
     * 　@A：昇順 <BR>
     * 　@D：降順
     */
    private String orderBy;
    
    /**
     * @@roseuid 411305DC012E
     */
    public WEB3IpoOrderAccountCodeComparator() 
    {
     
    }
    
    /**
     * （compareの実装）<BR>
     * <BR>
     * 顧客コードの比較を行う。 <BR>
     * <BR>
     * １）　@引数のcast<BR>
     * 　@引数のIPO申告１、IPO申告２をIPO申告型にcastする。<BR>
     * <BR>
     * ２）　@比較 <BR>
     * 　@１）でcastしたIPO申告１、IPO申告２について、<BR>
     * それぞれのIPO申告.get口座ＩＤ()に該当する顧客を取得する。<BR>
     * <BR>
     * 　@[昇順指定の場合（this.orderBy == ”昇順”）]<BR>
     * 　@・（IPO申告１.顧客.getAccountCode() < IPO申告２.顧客.getAccountCode()）<BR>
     * の場合、負の整数（-1）を返却する。<BR>
     * 　@・（IPO申告１.顧客.getAccountCode() = IPO申告２.顧客.getAccountCode()）<BR>の場合、0を返却する。<BR>
     * 　@・（IPO申告１.顧客.getAccountCode() > IPO申告２.顧客.getAccountCode()）<BR>の場合、正の整数（1）を返却する。<BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”）]<BR>
     * 　@・（IPO申告１.顧客.getAccountCode() < IPO申告２.顧客.getAccountCode()）<BR>の場合、正の整数（1）を返却する。<BR>
     * 　@・（IPO申告１.顧客.getAccountCode() = IPO申告２.顧客.getAccountCode()）<BR>の場合、0を返却する。<BR>
     * 　@・（IPO申告１.顧客.getAccountCode() > IPO申告２.顧客.getAccountCode()）<BR>の場合、負の整数（-1）を返却する。<BR>
     * @@param l_ipoOrder1 - (IPO申告１)<BR>
     * IPO申告オブジェクト１
     * 
     * @@param l_ipoOrder2 - (IPO申告２)<BR>
     * IPO申告オブジェクト２
     * 
     * @@return int
     * @@roseuid 40EB693A0391
     */
    public int compare(Object l_ipoOrder1, Object l_ipoOrder2) 
    {
        final String STR_METHOD_NAME = " compare(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        String l_strACode1 = null;
        String l_strACode2 = null;
        
        if (l_ipoOrder1 instanceof WEB3IpoOrderImpl && l_ipoOrder2 instanceof WEB3IpoOrderImpl)
        {
            l_strACode1 = ((WEB3IpoOrderImpl)l_ipoOrder1).getMainAccount().getAccountCode();
            l_strACode2 = ((WEB3IpoOrderImpl)l_ipoOrder2).getMainAccount().getAccountCode();
        }
        else
        {
            throw new IllegalArgumentException("パラメータの類型が不正、該当する'WEB3IpoOrder' 類型。");
        }
        
        if (l_strACode1.equals(l_strACode2))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        else if (l_strACode1.compareTo(l_strACode2) > 0)
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }
        }
        else
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
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
     * @@roseuid 40EB6B7E02B6
     */
    public boolean equals(Object l_arg0) 
    {
        return false;
    }
    
    /**
     * (IPO申告.顧客コードComparator)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * 引数の値をthis.orderByにセットする。
     * @@param l_strOrderBy - (orderBy)<BR>
     * 昇順（：asc）、降順（：desc）を指定するフラグ。<BR>
     * <BR>
     * 　@A：昇順 <BR>
     * 　@D：降順
     * 
     * @@roseuid 40EB6C670093
     */
    public WEB3IpoOrderAccountCodeComparator(String l_strOrderBy) 
    {
        final String STR_METHOD_NAME = " WEB3IpoOrderAccountCodeComparator(String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strOrderBy == null
            || (!l_strOrderBy.equals(WEB3AscDescDef.ASC) && !l_strOrderBy.equals(WEB3AscDescDef.DESC)))
        {
            throw new IllegalArgumentException("パラメータの値が'A：昇順'、'D：降順'以外です。");
        }
        
        this.orderBy = l_strOrderBy;
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
