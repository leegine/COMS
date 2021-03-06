head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.34.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPCapitalGain.java;


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

import java.util.Date;

import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (譲渡損益)<BR>
 * 受渡日の譲渡損益累計を表現する。
 */
public class WEB3TPCapitalGain
{

    /**
     * (譲渡損益)<BR>
     */
    private double amount;

    /**
     * (受渡日)<BR>
     */
    private Date deliveryDate;

    /**
     * @@roseuid 41049158007B
     */
    public WEB3TPCapitalGain()
    {

    }

    /**
     * (create譲渡損益)<BR>
     * 譲渡損益を生成し、返却する。<BR>
     * <BR>
     * １）　@インスタンス生成<BR>
     * <BR>
     * ２）　@値を設定<BR>
     * 	受渡日＝受渡日<BR>
     * <BR>
     * ３）　@インスタンスを返却<BR>
     * @@param 受渡日
     * @@return WEB3TPCapitalGain
     * @@roseuid 40EE857E0263
     */
    public static WEB3TPCapitalGain create(Date l_datDeliveryDate)
    {
        WEB3TPCapitalGain l_instance = new WEB3TPCapitalGain();
        l_instance.setDeliveryDate(l_datDeliveryDate);
        return l_instance;
    }

    /**
     * (get譲渡損益)<BR>
     * 譲渡損益（累計）額を返す。<BR>
     * @@return double
     * @@roseuid 40EE75050019
     */
    public double getAmount()
    {
        return amount;
    }

    /**
     * (set譲渡損益)<BR>
     * 引数を譲渡損益（累計）額にセットする。<BR>
     * @@param l_dblAmount - (譲渡益累積)
     * @@roseuid 40EE750B01FD
     */
    public void setAmount(double l_dblAmount)
    {
        amount = l_dblAmount;

    }

    /**
     * (get受渡日)<BR>
     * 受渡日を返す。<BR>
     * @@return Date
     * @@roseuid 40EE74E903E1
     */
    public Date getDeliveryDate()
    {
        return deliveryDate;
    }

    /**
     * (set受渡日)<BR>
     * 引数を受渡日にセットする。<BR>
     * @@param deliveryDate - (受渡日)
     * @@roseuid 40EE74EF0335
     */
    public void setDeliveryDate(Date l_datDeliveryDate)
    {
        deliveryDate = l_datDeliveryDate;
    }

    /**
     * (add譲渡損益)<BR>
     * 譲渡損益額に引数の値を加算する。<BR>
     * @@param amount - (譲渡益金額)
     * @@roseuid 40EE85E503BA
     */
    public void addAmount(double l_amount)
    {
        amount += l_amount;
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
            .append("deliveryDate", this.getDeliveryDate())
            .append("amount", this.getAmount())
            .toString();
    }

}
@
