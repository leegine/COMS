head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.36.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPCapitalGainTaxRestraintReflector.java;


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

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (÷“n‰vÅS‘©‹à)<BR>
 * ÷“n‰vÅS‘©‹à‚ğ•\Œ»‚·‚éB<BR>
 */
public class WEB3TPCapitalGainTaxRestraintReflector
    extends WEB3TPRestraintReflector
{

    /** ƒƒO@@ƒ†[ƒeƒBƒŠƒeƒB@@*/
    private static final WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3TPCapitalGainTaxRestraintReflector.class);

    /**
     * (÷“n‰vÅ—¦)<BR>
     */
    private double taxRate;

    /**
     * (ó“n“ú)<BR>
     */
    private Date deliveryDate;

    /**
     * (÷“n‘¹‰v<“–“ú>)<BR>
     */
    private webbroker3.tradingpower.updtpower.cash.WEB3TPCapitalGain[] capitalGain;

    /**
     * (—İÏ÷“n‘¹‰v<Šm’è>)<BR>
     */
    private WEB3TPAccumulatedCapitalGain accumuratedCapitalGain;

    /**
     * @@roseuid 41049159030B
     */
    public WEB3TPCapitalGainTaxRestraintReflector()
    {

    }

    /**
     * (create÷“n‰vÅS‘©‹à)<BR>
     * ÷“n‰vÅS‘©‹à‚ğ¶¬‚µA•Ô‹p‚·‚éB<BR>
     * <BR>
     * ‚Pj@@ƒCƒ“ƒXƒ^ƒ“ƒX¶¬<BR>
     * <BR>
     * ‚Qj@@’l‚ğİ’è<BR>
     * 	÷“n‰vÅ—¦÷“n‰vÅ—¦<BR>
     * 	ó“n“úó“n“ú<BR>
     * 	—İÏ÷“n‘¹‰v—İÏ÷“n‘¹‰v<BR>
     * 	÷“n‘¹‰v÷“n‘¹‰v<BR>
     * 	S‘©‹àcalc÷“n‘¹‰vÅ()<BR>
     * 	•Ï“®”½‰fŠJn“úA•Ï“®”½‰fI—¹“úİ’èFcalc•Ï“®”½‰f“úió“n“új<BR>
     * <BR>
     * ‚Rj@@ƒCƒ“ƒXƒ^ƒ“ƒX‚ğ•Ô‹p<BR>
     * <BR>
     * @@param l_dblTaxRate - (÷“n‰vÅ—¦)
     * @@param l_datDeliveryDate - (ó“n“ú)
     * @@param l_accumuratedCapitalGain - (—İÏ÷“n‘¹)
     * @@param l_capitalGain - (÷“n‘¹‰v)
     * @@return WEB3TPCapitalGainTaxRestraintReflector
     * @@roseuid 40DABDF8004C
     */
    public static WEB3TPCapitalGainTaxRestraintReflector create(WEB3TPCalcCondition
        l_calcCondition, double l_dblTaxRate, Date l_datDeliveryDate,
        WEB3TPAccumulatedCapitalGain l_accumuratedCapitalGain,
        WEB3TPCapitalGain[] l_capitalGain)
    {
        WEB3TPCapitalGainTaxRestraintReflector l_instance = new
            WEB3TPCapitalGainTaxRestraintReflector();
        l_instance.setCalcCondition(l_calcCondition);
        l_instance.setTaxRate(l_dblTaxRate);
        l_instance.setDeliveryDate(l_datDeliveryDate);
        l_instance.setAccumuratedCapitalGain(l_accumuratedCapitalGain);
        l_instance.setCapitalGain(l_capitalGain);
        l_instance.setAmount(l_instance.calcCapitalGainTax(l_datDeliveryDate));
        l_instance.calcReflectDay(l_datDeliveryDate);

        return l_instance;

    }

    /**
     * (get÷“n‰vÅ—¦)<BR>
     * ÷“n‰vÅ—¦‚ğ•Ô‚·B<BR>
     * @@return double
     * @@roseuid 40EE3525027A
     */
    public double getTaxRate()
    {
        return taxRate;
    }

    /**
     * (set÷“n‰vÅ—¦)<BR>
     * ˆø”‚ğ÷“n‰vÅ—¦‚ÉƒZƒbƒg‚·‚éB<BR>
     * @@param l_taxRate - (÷“n‰vÅ—¦)
     * @@roseuid 40EE353200C4
     */
    public void setTaxRate(double l_dblTaxRate)
    {
        taxRate = l_dblTaxRate;
    }

    /**
     * (getó“n“ú)<BR>
     * ó“n“ú‚ğ•Ô‚·B<BR>
     * @@return Date
     * @@roseuid 40EE7FA703DA
     */
    public Date getDeliveryDate()
    {
        return deliveryDate;
    }

    /**
     * (setó“n“ú)<BR>
     * ˆø”‚ğó“n“ú‚ÉƒZƒbƒg‚·‚éB<BR>
     * @@param l_datDeliveryDate - (ó“n“ú)
     * @@roseuid 40EE7F9F011A
     */
    public void setDeliveryDate(Date l_datDeliveryDate)
    {
        deliveryDate = l_datDeliveryDate;
    }

    /**
     * (get—İÏ÷“n‘¹‰v)<BR>
     * —İÏ÷“n‘¹‰v‚ğ•Ô‚·B<BR>
     * @@return webbroker3.tradingpower.updtpower.cash.WEB3TPAccumulatedCapitalGain
     * @@roseuid 40EE8185013A
     */
    public WEB3TPAccumulatedCapitalGain getAccumuratedCapitalGain()
    {
        return accumuratedCapitalGain;
    }

    /**
     * (set—İÏ÷“n‘¹‰v)<BR>
     * ˆø”‚ğ—İÏ÷“n‘¹‰v‚ÉƒZƒbƒg‚·‚éB<BR>
     * @@param l_accumuratedCapitalGain - (—İÏ÷“n‘¹‰v)
     * @@roseuid 40EE8AE9037C
     */
    public void setAccumuratedCapitalGain(WEB3TPAccumulatedCapitalGain
                                          l_accumuratedCapitalGain)
    {
        accumuratedCapitalGain = l_accumuratedCapitalGain;
    }

    /**
     * (get÷“n‘¹‰v)<BR>
     * ÷“n‘¹‰v‚Ì”z—ñ’†A<BR>
     * ÷“n‘¹‰v.getó“n“ú()ˆø”.ó“n“ú‚Ì<BR>
     * ÷“n‘¹‰v‚ğ•Ô‚·B<BR>
     * 
     * @@param l_datDeliveryDate (ó“n“ú)
     * @@return webbroker3.tradingpower.updtpower.cash.WEB3TPCapitalGain
     * @@throws WEB3BaseRuntimeException
     * @@roseuid 40EEA216032E
     */
    public WEB3TPCapitalGain getCapitalGain(Date l_datDeliveryDate)
    {
        final String STR_METHOD_NAME = "getCapitalGain(Date l_datDeliveryDate)";
        
        for (int i = 0; i < capitalGain.length; i++)
        {
            if (WEB3DateUtility.compareToDay(capitalGain[i].getDeliveryDate(), l_datDeliveryDate) == 0)
            {
                return capitalGain[i];
            }
        }
        
        throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + STR_METHOD_NAME);

    }

    /**
     * (get÷“n‘¹‰v)<BR>
     * ÷“n‘¹‰v‚Ì”z—ñ‚ğ•Ô‹p‚·‚éB<BR>
     * @@return WEB3TPCapitalGain[]
     */
    public WEB3TPCapitalGain[] getCapitalGain()
    {
        return capitalGain;
    }

    /**
     * (set÷“n‘¹‰v)<BR>
     * ˆø”‚ğ÷“n‘¹‰v‚ÉƒZƒbƒg‚·‚éB<BR>
     * @@param l_capitalGain - (÷“n‘¹‰v)
     * @@roseuid 40EE89D902EF
     */
    public void setCapitalGain(WEB3TPCapitalGain[] l_capitalGain)
    {
        capitalGain = l_capitalGain;
    }

    /**
     * (calc÷“n‰vÅ)<BR>
     * w’è“ú(n)‚É‚æ‚èŠY“–‚·‚éŒvZƒƒWƒbƒN‚ÅŒvZ‚·‚éB<BR>
     * <BR>  
     * 
     * ‚Pj@@‰c‹Æ“ú(T+0)‚©‚ç‰c‹Æ“ú (T+5)‚Ü‚Å“¯”N‚Ìê‡<BR>
     * <BR>
     * 	÷“n‰vÅ(T+3)  =  Max((÷“n‘¹‰v(T+3) - “–Šú÷“n‘¹), 0)<BR>
     * 	 ~@@÷“n‰vÅ—¦<BR>
     * 	÷“n‰vÅ(T+4)  =  Max((÷“n‘¹‰v(T+3)  +  ÷“n‘¹‰v(T+4) -@@“–Šú÷“n‘¹), 0)<BR>
     * 	 ~@@÷“n‰vÅ—¦<BR>
     * 	÷“n‰vÅ(T+5)  =  Max((÷“n‘¹‰v(T+3)  +  ÷“n‘¹‰v(T+4)  + ÷“n‘¹‰v(T+5) - “–Šú÷“n‘¹), 0)<BR>
     * 	 ~@@÷“n‰vÅ—¦<BR>
     * <BR>
     * ‚Qj@@‰c‹Æ“ú(T+3)ˆÈ‘O‚Å—‚”N‚É‚È‚éê‡<BR>
     * <BR>
     * 	÷“n‰vÅ(T+3)  =@@Max((÷“n‘¹‰v(T+3) - —‚Œ÷“n‘¹), 0)<BR>
     * 	 ~@@÷“n‰vÅ—¦<BR>
     * 	÷“n‰vÅ(T+4)  =@@Max((÷“n‘¹‰v(T+3) + ÷“n‘¹‰v(T+4) - —‚Œ÷“n‘¹), 0)<BR>
     * 	 ~@@÷“n‰vÅ—¦<BR>
     * 	÷“n‰vÅ(T+5)  =  Max((÷“n‘¹‰v(T+3) + ÷“n‘¹‰v(T+4) + ÷“n‘¹‰v(T+5) - —‚Œ÷“n‘¹), 0)<BR>
     * 	 ~@@÷“n‰vÅ—¦<BR>
     * <BR>
     * ‚Rj@@‰c‹Æ“ú(T+4)‚Å—‚”N‚É‚È‚éê‡<BR>
     * <BR>
     * 	÷“n‰vÅ(T+3)  =  Max((÷“n‘¹‰v(T+3) - “–Šú÷“n‘¹), 0)<BR>
     * 	 ~@@÷“n‰vÅ—¦<BR>
     * 	÷“n‰vÅ(T+4)  =  Max((÷“n‘¹‰v(T+4) - —‚Œ÷“n‘¹), 0)<BR>
     * 	 ~@@÷“n‰vÅ—¦<BR>
     * 	÷“n‰vÅ(T+5)  =  Max((÷“n‘¹‰v(T+4)  +  ÷“n‘¹‰v(T+5) - —‚Œ÷“n‘¹), 0)<BR>
     * 	 ~@@÷“n‰vÅ—¦<BR>
     * <BR>
     * ‚Sj@@‰c‹Æ“ú(T+5)‚Å—‚”N‚É‚È‚éê‡<BR>
     * <BR>
     * 	÷“n‰vÅ(T+3)  =  Max((÷“n‘¹‰v(T+3) - “–Šú÷“n‘¹), 0)<BR>
     * 	 ~@@÷“n‰vÅ—¦<BR>
     * 	÷“n‰vÅ(T+4)  =  Max((÷“n‘¹‰v(T+3) + ÷“n‘¹‰v(T+4) - “–Šú÷“n‘¹), 0)<BR>
     * 	 ~@@÷“n‰vÅ—¦<BR>
     * 	÷“n‰vÅ(T+5)  =  Max((÷“n‘¹‰v(T+5) - —‚Œ÷“n‘¹), 0)<BR>
     * 	 ~@@÷“n‰vÅ—¦<BR>
     * <BR>
     * ‚Tj@@ã‹L‚Ì‚æ‚¤‚É‹‚ß‚ç‚ê‚é÷“n‰vÅ(n)‚ğ•Ô‹p‚·‚éB<BR>
     * <BR>  
     *     ¦Še’l‚Ìæ“¾•û–@@<BR>
     * E	÷“n‘¹‰v(T+3)EEEthis.get÷“n‘¹‰v(ó“n“ú<T+3>)<BR>
     * E	÷“n‘¹‰v(T+4)EEEthis.get÷“n‘¹‰v(ó“n“ú<T+4>)<BR>
     * E	÷“n‘¹‰v(T+5)EEEthis.get÷“n‘¹‰v(ó“n“ú<T+5>)<BR>
     * E	“–Šú÷“n‘¹EEEMin(this.get—İÏ÷“n‘¹‰v().get“–Šú÷“n‘¹‰v(), 0)<BR>
     * E	—‚Œ÷“n‘¹EEEMin(this.get—İÏ÷“n‘¹‰v().get—‚Œ÷“n‘¹‰v(), 0)<BR>
     * <BR>
     * 
     * @@param l_datDate (w’è“ú)
     * @@return double
     * @@roseuid 40EE3F6C0076
     */
    public double calcCapitalGainTax(Date l_datDate)
    {
        Date l_datT0 = getCalcCondition().getBizDate(0);
        Date l_datT2 = getCalcCondition().getBizDate(2);
        Date l_datT3 = getCalcCondition().getBizDate(3);
        Date l_datT4 = getCalcCondition().getBizDate(4);
        Date l_datT5 = getCalcCondition().getBizDate(5);

        double l_capitalGain = 0.0d;        
        
        //‰c‹Æ“ú(T+0)‚©‚ç‰c‹Æ“ú(T+5)‚Ü‚Å“¯”N‚Ìê‡
        if (compareToYear(l_datT0, l_datT5) == 0)
        {   
            l_capitalGain = Math.max((calcTodaysCapitalGainTotal(l_datDate) -
                                    accumuratedCapitalGain.getCurrentTermLoss()), 0.0d);            
        }

        //‰c‹Æ“ú(T+0)‚©‚ç‰c‹Æ“ú(T+3)‚Å—‚”N‚É‚È‚éê‡<BR>
        else if (compareToYear(l_datT0, l_datT3) < 0)
        {
            l_capitalGain =
                Math.max( (calcTodaysCapitalGainTotal(l_datDate) -
                           accumuratedCapitalGain.geNextMonthLoss()), 0.0d);
                           
        }

        //‰c‹Æ“ú(T+4)‚Å—‚”N‚É‚È‚éê‡<BR>
        else if (compareToYear(l_datT3, l_datT4) < 0)
        {
            if (WEB3DateUtility.compareToDay(l_datT3, l_datDate) == 0)
            {
                l_capitalGain =
                    Math.max( (calcTodaysCapitalGainTotal(l_datDate) -
                               accumuratedCapitalGain.getCurrentTermLoss()), 0.0d);
                               
            }
            else if ((WEB3DateUtility.compareToDay(l_datT4, l_datDate) == 0) || 
                (WEB3DateUtility.compareToDay(l_datT5, l_datDate) == 0))
            {
                l_capitalGain =
                    Math.max( (calcTodaysCapitalGainTotal(l_datDate) -
                               accumuratedCapitalGain.geNextMonthLoss()), 0.0d);
                               
            }
        }

        //‰c‹Æ“ú(T+5)‚Å—‚”N‚É‚È‚éê‡<BR>
        else if (compareToYear(l_datT4, l_datT5) < 0)
        {
            if ((WEB3DateUtility.compareToDay(l_datT3, l_datDate) == 0) || 
                (WEB3DateUtility.compareToDay(l_datT4, l_datDate) == 0))
            {
                l_capitalGain =
                    Math.max( (calcTodaysCapitalGainTotal(l_datDate) -
                               accumuratedCapitalGain.getCurrentTermLoss()), 0.0d);
                               
            }
            else if (WEB3DateUtility.compareToDay(l_datT5, l_datDate) == 0)
            {
                l_capitalGain =
                    Math.max( (calcTodaysCapitalGainTotal(l_datDate) -
                               accumuratedCapitalGain.geNextMonthLoss()), 0.0d);
            }
        }
                
        return (l_capitalGain == 0.0d) ? 0.0d : Math.floor(l_capitalGain * taxRate);

    }
        
    /**
     * (calc•Ï“®”½‰f“ú)<BR>
     * •Ï“®”½‰fŠJn“úA•Ï“®”½‰fI—¹“ú‚ğˆÈ‰º‚Ì‚æ‚¤‚ÉƒZƒbƒg‚·‚éB<BR>
     * <BR>
     * •Ï“®”½‰fŠJn“ú		ˆø”.ó“n“ú<BR>
     * <BR>
     * [•Ï“®”½‰fŠJn“ú(ˆø”.ó“n“ú)‚Ì—‚‰c‹Æ“ú‚©‚ç—‚”N‚Æ‚È‚éê‡]<BR>
     * 	•Ï“®”½‰fI—¹“ú		‰c‹Æ“ú[5]<BR>
     * [ã‹LˆÈŠO]<BR>
     * 	•Ï“®”½‰fI—¹“ú		ˆø”.ó“n“ú
     * @@param l_datDeliveryDate - (ó“n“ú)
     * @@roseuid 40E520DA02A1
     */
    public void calcReflectDay(Date l_datDeliveryDate)
    {
        final String STR_METHOD_NAME = "calcReflectDay(Date l_datDeliveryDate)";
        
        //•Ï“®”½‰fŠJn“ú
        setReflectStartDay(l_datDeliveryDate);
                
        //•Ï“®”½‰fI—¹“ú       
        Date l_datNextBizDate = getCalcCondition().rollBizDate(l_datDeliveryDate, 1);
        
        //ó“n“ú‚Æó“n“ú—‚“ú(—‚‰c‹Æ“új‚Å”N‚ğ‚Ü‚½‚®ê‡
        if (compareToYear(l_datDeliveryDate, l_datNextBizDate) < 0)
        {
            //”½‰fI—¹“ú‚ÍT+5
            setReflectEndDay(getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5));
        }
        else
        {
            //“¯”N‚Ìê‡‚Íó“n“ú
            setReflectEndDay(l_datDeliveryDate);
        }

    }

    /**
     * (calc÷“n‘¹‰v—İŒv<“–“ú>)<BR>
     * ó“n“ú‚É‚¨‚¯‚é“–“úˆÈ~÷“n‘¹‰v‚Ì—İŒv‚ğ•Ô‹p‚·‚éB<BR>
     * @@param l_datDeliveryDate (ó“n“ú)
     * @@return double
     */
    private double calcTodaysCapitalGainTotal(Date l_datDeliveryDate)
    {
        double l_dblCapitalGainTotal = 0.0d;

        Date l_datT0 = getCalcCondition().getBizDate(0);
        Date l_datT3 = getCalcCondition().getBizDate(3);
        Date l_datT4 = getCalcCondition().getBizDate(4);
        Date l_datT5 = getCalcCondition().getBizDate(5);

        //T+3‚Ìê‡
        if (WEB3DateUtility.compareToDay(l_datT3, l_datDeliveryDate) == 0)
        {
            l_dblCapitalGainTotal = getCapitalGain(l_datT3).getAmount();
            return l_dblCapitalGainTotal;
        }
        //T+4 or T+5‚Ìê‡
        else
        {
            //T+0`T+5‚Ü‚Å“¯”N ‚Ü‚½‚Í T+3`T+5‚ÌŠÔ‚Å“¯”N
            if ((compareToYear(l_datT0, l_datT5) == 0) ||
                (compareToYear(l_datT0, l_datT3) < 0))
            {
                //T+4‚Ìê‡
                if (WEB3DateUtility.compareToDay(l_datT4, l_datDeliveryDate) == 0)
                {
                    l_dblCapitalGainTotal = getCapitalGain(l_datT3).getAmount() +
                        getCapitalGain(l_datT4).getAmount();
                }
                //T+5‚Ìê‡
                else if (WEB3DateUtility.compareToDay(l_datT5, l_datDeliveryDate) == 0)
                {
                    l_dblCapitalGainTotal = getCapitalGain(l_datT3).getAmount() +
                        getCapitalGain(l_datT4).getAmount() +
                        getCapitalGain(l_datT5).getAmount();
                }
            }

            //T+4‚Å—‚”N
            else if (compareToYear(l_datT3, l_datT4) < 0)
            {
                //T+4‚Ìê‡
                if (WEB3DateUtility.compareToDay(l_datT4, l_datDeliveryDate) == 0)
                {
                    l_dblCapitalGainTotal = getCapitalGain(l_datT4).getAmount();
                }
                //T+5‚Ìê‡
                else if (WEB3DateUtility.compareToDay(l_datT5, l_datDeliveryDate) == 0)
                {
                    l_dblCapitalGainTotal = getCapitalGain(l_datT4).getAmount() +
                        getCapitalGain(l_datT5).getAmount();
                }
            }

            else if (compareToYear(l_datT4, l_datT5) < 0)
            {
                //T+4‚Ìê‡
                if (WEB3DateUtility.compareToDay(l_datT4, l_datDeliveryDate) == 0)
                {
                    l_dblCapitalGainTotal = getCapitalGain(l_datT3).getAmount() +
                        getCapitalGain(l_datT4).getAmount();
                }
                //T+5‚Ìê‡
                else if (WEB3DateUtility.compareToDay(l_datT5, l_datDeliveryDate) == 0)
                {
                    l_dblCapitalGainTotal = getCapitalGain(l_datT5).getAmount();
                }
            }
        }

        //ã‹LˆÈŠO‚ÌƒP[ƒX‚Ìê‡0‚ğ•Ô‚·B
        return l_dblCapitalGainTotal;
    }

    /**
     * (compare”N)<BR>
     * “ñ‚Â‚Ì“ú•t‚ğ”äŠr‚·‚é(¸“x‚Í”N‚Ü‚Å‚Æ‚·‚é)B<BR>
     * <BR>
     * l_dat1‚ªl_dat2‚ÌŒã‚Ìê‡A‚O‚æ‚è‘å‚«‚¢®”‚ğ•Ô‹p‚·‚éB<BR>
     * l_dat1‚ªl_dat2‚Ì‘O‚Ìê‡A‚O‚æ‚è¬‚³‚¢®”‚ğ•Ô‹p‚·‚éB<BR>
     * l_dat1‚Æl_dat2‚ª“¯—l‚Ìê‡A‚O‚ğ•Ô‹p‚·‚éB<BR>
     * 
     * @@param l_dat1 (“ú•t1)
     * @@param l_dat2 (“ú•t2)
     * @@return int
     */
    private static int compareToYear(Date l_dat1, Date l_dat2) 
    {
        if (l_dat1 == null) 
        {
            l_dat1 = new Date(0);
        } 
        
        if (l_dat2 == null) 
        {
            l_dat2 = new Date(0);
        }

        SimpleDateFormat l_dateFormat = GtlUtils.getThreadSafeSimpleDateFormat("yyyy");
        String l_strDate1 = l_dateFormat.format(l_dat1);
        String l_strDate2 = l_dateFormat.format(l_dat2);

        return l_strDate1.compareTo(l_strDate2);
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
            .append("taxRate", this.getTaxRate())
            .append("capitalGain[]", this.getCapitalGain())
            .append("accumuratedCapitalGain", this.getAccumuratedCapitalGain())
            .append("deliveryDate", this.getDeliveryDate())
            .appendSuper(super.toString())
            .toString();
    }

}
@
