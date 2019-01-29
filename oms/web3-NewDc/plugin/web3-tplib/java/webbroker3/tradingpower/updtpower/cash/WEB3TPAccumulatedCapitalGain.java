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
Copyright        : (Š”)‘å˜a‘Œ¤ ØŒ”ƒ\ƒŠƒ…[ƒVƒ‡ƒ“ƒVƒXƒeƒ€‘æ“ñ•”
File Name        : WEB3TPAccumulatedCapitalGainTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2004/08/02 –x–ì ˜a”ü(FLJ) V‹Kì¬
*/
package webbroker3.tradingpower.updtpower.cash;

import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (—İÏ÷“n‘¹‰v)<BR>
 * —İÏ÷“n‘¹‰v‚ğ•\Œ»‚·‚éB<BR>
 */
public class WEB3TPAccumulatedCapitalGain
{

    /**
     * (“–Šú÷“n‘¹‰v)<BR>
     */
    private double currentTermAmount;

    /**
     * (—‚Œ÷“n‘¹‰v)<BR>
     */
    private double nextMonthAmount;

    /**
     * @@roseuid 4104915801B4
     */
    public WEB3TPAccumulatedCapitalGain()
    {

    }

    /**
     * (create—İÏ÷“n‘¹‰v)<BR>
     * —İÏ÷“n‘¹‰v‚ğ¶¬‚µA•Ô‹p‚·‚éB<BR>
     * <BR>
     * ‚Pj@@ƒCƒ“ƒXƒ^ƒ“ƒX¶¬<BR>
     * <BR>
     * ‚Qj@@’l‚ğİ’è<BR>
     * 	“–Šú÷“n‘¹‰v“–Šú÷“n‘¹‰v<BR>
     * 	—‚Œ÷“n‘¹‰v—‚Œ÷“n‘¹‰v<BR>
     * <BR>
     * ‚Rj@@ƒCƒ“ƒXƒ^ƒ“ƒX‚ğ•Ô‹p<BR>
     * @@param thisTermAmount - (“–Šú÷“n‘¹)
     * @@param nextMonthAmount - (—‚Œ÷“n‘¹)
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
     * (get“–Šú÷“n‘¹‰v)<BR>
     * “–Šú÷“n‘¹‰v‚ğ•Ô‚·B<BR>
     * @@return double 
     * @@roseuid 40EAA94C01F6
     */
    public double getCurrentTermAmount()
    {
        return currentTermAmount;
    }

    /**
     * (set“–Šú÷“n‘¹‰v)<BR>
     * ˆø”‚ğ“–Šú÷“n‘¹‰v‚ÉƒZƒbƒg‚·‚éB<BR>
     * @@param currentTermAmount “–Šú÷“n‘¹‰v
     * @@roseuid 40EAA951030F
     */
    public void setCurrentTermAmount(double l_dblCurrentTermAmount)
    {
        currentTermAmount = l_dblCurrentTermAmount;
    }

    /**
     * (get—‚Œ÷“n‘¹‰v)<BR>
     * —‚Œ÷“n‘¹‰v‚ğ•Ô‚·B<BR>
     * @@return double
     * @@roseuid 40EE73FC0364
     */
    public double getNextMonthAmount()
    {
        return nextMonthAmount;
    }

    /**
     * (set—‚Œ÷“n‘¹‰v)<BR>
     * ˆø”‚ğ—‚Œ÷“n‘¹‰v‚ÉƒZƒbƒg‚·‚éB<BR>
     * @@param nextMonthAmount - (—‚Œ÷“n‘¹)
     * @@roseuid 40EE741802E7
     */
    public void setNextMonthAmount(double l_dblNextMonthAmount)
    {
        nextMonthAmount = l_dblNextMonthAmount;
    }

    /**
     * (get“–Šú÷“n‘¹)<BR>
     * “–Šú÷“n‘¹‚ğ•Ô‚·B<BR>
     * @@return double
     */
    public double getCurrentTermLoss()
    {
        return Math.abs(Math.min(getCurrentTermAmount(), 0.0d));
    }

    /**
     * (get—‚Œ÷“n‘¹)<BR>
     * —‚Œ÷“n‘¹‚ğ•Ô‚·B<BR>
     * @@return double
     */
    public double geNextMonthLoss()
    {
        return Math.abs(Math.min(getNextMonthAmount(), 0.0d));
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
            .append("currentTermAmount", this.getCurrentTermAmount())
            .append("currentTermLoss", this.getCurrentTermLoss())
            .append("nextMonthAmount", this.getNextMonthAmount())
            .append("nextMonthLoss", this.geNextMonthLoss())
            .toString();
    }

}
@
