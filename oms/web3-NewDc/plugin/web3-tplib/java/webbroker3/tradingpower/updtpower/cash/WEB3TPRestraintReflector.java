head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.35.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPRestraintReflector.java;


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

import webbroker3.tradingpower.updtpower.WEB3TPAssetReflector;
import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (拘束金変動)<BR>
 * 拘束金変動を表現する。
 */
public abstract class WEB3TPRestraintReflector
    extends WEB3TPAssetReflector
{

    /**
     * (拘束金)<BR>
     */
    private double amount;

    /**
      * (get拘束金)<BR>
      *  拘束金を返す。<BR>
      * @@return double 
      * @@roseuid 40D64CBA02AA
      */
     public double getAmount()
     {
         return amount;
     }

     /**
      * (set拘束金)<BR>
      * 引数を 拘束金額にセットする。<BR>
      * @@param l_dblAmount - (拘束金)
      * @@roseuid 40D64CB6024C
      */
     public void setAmount(double l_dblAmount)
     {
         amount = l_dblAmount;
     }   

    /**
     * このオブジェクトの文字列表現を返す。<BR>
     * 
     * @@return String
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .append("amount", getAmount())
            .appendSuper(super.toString())
            .toString();
    }
      
}
@
