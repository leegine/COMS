head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.35.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAccumulatedCapitalGain.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3TPAccumulatedCapitalGainTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2004/08/02 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpower.updtpower.cash;

import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (累積譲渡損益)<BR>
 * 累積譲渡損益を表現する。<BR>
 */
public class WEB3TPAccumulatedCapitalGain
{

    /**
     * (当期譲渡損益)<BR>
     */
    private double currentTermAmount;

    /**
     * (翌月譲渡損益)<BR>
     */
    private double nextMonthAmount;

    /**
     * @@roseuid 4104915801B4
     */
    public WEB3TPAccumulatedCapitalGain()
    {

    }

    /**
     * (create累積譲渡損益)<BR>
     * 累積譲渡損益を生成し、返却する。<BR>
     * <BR>
     * １）　@インスタンス生成<BR>
     * <BR>
     * ２）　@値を設定<BR>
     * 	当期譲渡損益＝当期譲渡損益<BR>
     * 	翌月譲渡損益＝翌月譲渡損益<BR>
     * <BR>
     * ３）　@インスタンスを返却<BR>
     * @@param thisTermAmount - (当期譲渡損)
     * @@param nextMonthAmount - (翌月譲渡損)
     * @@return WEB3TPAccumulatedCapitalGain
     * @@roseuid 40EE2D5501CE
     */
    public static WEB3TPAccumulatedCapitalGain create(double l_dblCurrentTermAmount,
        double l_dblNextMonthAmount)
    {
        WEB3TPAccumulatedCapitalGain l_instance = new WEB3TPAccumulatedCapitalGain();
        l_instance.setCurrentTermAmount(l_dblCurrentTermAmount);
        l_instance.setNextMonthAmount(l_dblNextMonthAmount);
        return l_instance;
    }

    /**
     * (get当期譲渡損益)<BR>
     * 当期譲渡損益を返す。<BR>
     * @@return double 
     * @@roseuid 40EAA94C01F6
     */
    public double getCurrentTermAmount()
    {
        return currentTermAmount;
    }

    /**
     * (set当期譲渡損益)<BR>
     * 引数を当期譲渡損益にセットする。<BR>
     * @@param currentTermAmount 当期譲渡損益
     * @@roseuid 40EAA951030F
     */
    public void setCurrentTermAmount(double l_dblCurrentTermAmount)
    {
        currentTermAmount = l_dblCurrentTermAmount;
    }

    /**
     * (get翌月譲渡損益)<BR>
     * 翌月譲渡損益を返す。<BR>
     * @@return double
     * @@roseuid 40EE73FC0364
     */
    public double getNextMonthAmount()
    {
        return nextMonthAmount;
    }

    /**
     * (set翌月譲渡損益)<BR>
     * 引数を翌月譲渡損益にセットする。<BR>
     * @@param nextMonthAmount - (翌月譲渡損)
     * @@roseuid 40EE741802E7
     */
    public void setNextMonthAmount(double l_dblNextMonthAmount)
    {
        nextMonthAmount = l_dblNextMonthAmount;
    }

    /**
     * (get当期譲渡損)<BR>
     * 当期譲渡損を返す。<BR>
     * @@return double
     */
    public double getCurrentTermLoss()
    {
        return Math.abs(Math.min(getCurrentTermAmount(), 0.0d));
    }

    /**
     * (get翌月譲渡損)<BR>
     * 翌月譲渡損を返す。<BR>
     * @@return double
     */
    public double geNextMonthLoss()
    {
        return Math.abs(Math.min(getNextMonthAmount(), 0.0d));
    }

    /**
     * このオブジェクトの文字列表現を返す。
     * 
     * @@return String
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .append("currentTermAmount", this.getCurrentTermAmount())
            .append("currentTermLoss", this.getCurrentTermLoss())
            .append("nextMonthAmount", this.getNextMonthAmount())
            .append("nextMonthLoss", this.geNextMonthLoss())
            .toString();
    }

}
@
