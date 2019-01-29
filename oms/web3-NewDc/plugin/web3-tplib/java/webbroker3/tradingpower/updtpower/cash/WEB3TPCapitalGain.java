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
Copyright        : (Š”)‘å˜a‘Œ¤ ØŒ”ƒ\ƒŠƒ…[ƒVƒ‡ƒ“ƒVƒXƒeƒ€‘æ“ñ•”
File Name        : WEB3TPAccumulatedCapitalGainTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2004/08/02 –x–ì ˜a”ü(FLJ) V‹Kì¬
*/

package webbroker3.tradingpower.updtpower.cash;

import java.util.Date;

import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (÷“n‘¹‰v)<BR>
 * ó“n“ú‚Ì÷“n‘¹‰v—İŒv‚ğ•\Œ»‚·‚éB
 */
public class WEB3TPCapitalGain
{

    /**
     * (÷“n‘¹‰v)<BR>
     */
    private double amount;

    /**
     * (ó“n“ú)<BR>
     */
    private Date deliveryDate;

    /**
     * @@roseuid 41049158007B
     */
    public WEB3TPCapitalGain()
    {

    }

    /**
     * (create÷“n‘¹‰v)<BR>
     * ÷“n‘¹‰v‚ğ¶¬‚µA•Ô‹p‚·‚éB<BR>
     * <BR>
     * ‚Pj@@ƒCƒ“ƒXƒ^ƒ“ƒX¶¬<BR>
     * <BR>
     * ‚Qj@@’l‚ğİ’è<BR>
     * 	ó“n“úó“n“ú<BR>
     * <BR>
     * ‚Rj@@ƒCƒ“ƒXƒ^ƒ“ƒX‚ğ•Ô‹p<BR>
     * @@param ó“n“ú
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
     * (get÷“n‘¹‰v)<BR>
     * ÷“n‘¹‰vi—İŒvjŠz‚ğ•Ô‚·B<BR>
     * @@return double
     * @@roseuid 40EE75050019
     */
    public double getAmount()
    {
        return amount;
    }

    /**
     * (set÷“n‘¹‰v)<BR>
     * ˆø”‚ğ÷“n‘¹‰vi—İŒvjŠz‚ÉƒZƒbƒg‚·‚éB<BR>
     * @@param l_dblAmount - (÷“n‰v—İÏ)
     * @@roseuid 40EE750B01FD
     */
    public void setAmount(double l_dblAmount)
    {
        amount = l_dblAmount;

    }

    /**
     * (getó“n“ú)<BR>
     * ó“n“ú‚ğ•Ô‚·B<BR>
     * @@return Date
     * @@roseuid 40EE74E903E1
     */
    public Date getDeliveryDate()
    {
        return deliveryDate;
    }

    /**
     * (setó“n“ú)<BR>
     * ˆø”‚ğó“n“ú‚ÉƒZƒbƒg‚·‚éB<BR>
     * @@param deliveryDate - (ó“n“ú)
     * @@roseuid 40EE74EF0335
     */
    public void setDeliveryDate(Date l_datDeliveryDate)
    {
        deliveryDate = l_datDeliveryDate;
    }

    /**
     * (add÷“n‘¹‰v)<BR>
     * ÷“n‘¹‰vŠz‚Éˆø”‚Ì’l‚ğ‰ÁZ‚·‚éB<BR>
     * @@param amount - (÷“n‰v‹àŠz)
     * @@roseuid 40EE85E503BA
     */
    public void addAmount(double l_amount)
    {
        amount += l_amount;
    }

    /**
     * ‚±‚ÌƒIƒuƒWƒFƒNƒg‚Ì•¶š—ñ•\Œ»‚ğ•Ô‚·B
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
