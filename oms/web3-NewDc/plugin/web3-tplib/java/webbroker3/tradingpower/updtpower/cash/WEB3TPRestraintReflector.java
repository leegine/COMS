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
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : WEB3TPAccumulatedCapitalGainTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2004/08/02 xì aü(FLJ) VKì¬
*/

package webbroker3.tradingpower.updtpower.cash;

import webbroker3.tradingpower.updtpower.WEB3TPAssetReflector;
import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (S©àÏ®)<BR>
 * S©àÏ®ð\»·éB
 */
public abstract class WEB3TPRestraintReflector
    extends WEB3TPAssetReflector
{

    /**
     * (S©à)<BR>
     */
    private double amount;

    /**
      * (getS©à)<BR>
      *  S©àðÔ·B<BR>
      * @@return double 
      * @@roseuid 40D64CBA02AA
      */
     public double getAmount()
     {
         return amount;
     }

     /**
      * (setS©à)<BR>
      * øð S©àzÉZbg·éB<BR>
      * @@param l_dblAmount - (S©à)
      * @@roseuid 40D64CB6024C
      */
     public void setAmount(double l_dblAmount)
     {
         amount = l_dblAmount;
     }   

    /**
     * ±ÌIuWFNgÌ¶ñ\»ðÔ·B<BR>
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
