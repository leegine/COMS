head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFundEstimatedPrice.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (Š”)‘å˜a‘Œ¤ ØŒ”ƒ\ƒŠƒ…[ƒVƒ‡ƒ“ƒVƒXƒeƒ€‘æ“ñ•”
File Name        : ŠTZó“n‘ã‹à(WEB3MutualFundEstimatedPrice)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/05 èè”Oàù (’†u) V‹Kì¬
                   2004/08/23 ˜°”ü—í (’†u) ƒŒƒrƒ…[ 
*/

package webbroker3.mf;


/**
 * (ŠTZó“n‘ã‹à)<BR>
 * 
 * @@author èè”Oàù(’†u)
 * @@version 1.0
 */
public class WEB3MutualFundEstimatedPrice 
{
    public WEB3MutualFundEstimatedPrice()
    {
        
    }
    
    /**
     * (ŠTZ”„”ƒŒû”)<BR>
     */
    private double estimatedQty;
    
    /**
     * (ŠTZ”„”ƒ‘ã‹à)<BR>
     */
    private double estimatedTradeAmount;
    
    /**
     * (ŠTZ”„”ƒ‘ã‹àiŠO‰İj)<BR>
     */
    private double foreignCurrencyEstimatedTradeAmount;
    
    /**
     * (ŠTZó“n‘ã‹à)<BR>
     */
    private double estimatedPrice;       
        
    /**
     * (è”—¿)<BR>
     */
    private double commission;
    
    /**
     * (è”—¿Á”ïÅ)<BR>
     */
    private double commissionTax;
    
    /**
     * (Š“¾Å)<BR>
     */
    private double incomeTax;
    
    /**
     * (’n•ûÅ)<BR>
     */
    private double localTax;
    
    /**
     * ŠTZ”„”ƒŒû”‚ğƒZƒbƒg‚·‚éB<BR>
     * @@roseuid 40B15D8A0323
     */
    public void setEstimatedQty(double l_dblEstimatedQty) 
    {
        estimatedQty = l_dblEstimatedQty;
    }
    
    /**
     * ŠTZ”„”ƒŒû”‚ğæ“¾‚·‚é<BR>
     * @@roseuid 40B15DA0013E
     */
    public double getEstimatedQty() 
    {
        return estimatedQty;
    }
    
    /**
     * ŠTZ”„”ƒ‘ã‹à‚ğƒZƒbƒg‚·‚éB<BR>
     * @@roseuid 40B15DAB0209
     */
    public void setEstimatedTradeAmount(double l_dblEstimatedTradeAmount) 
    {
        estimatedTradeAmount = l_dblEstimatedTradeAmount;
    }
    
    /**
     * ŠTZ”„”ƒ‘ã‹à‚ğæ“¾‚·‚é<BR>
     * @@roseuid 40B15DC201CB
     */
    public double getEstimatedTradeAmount() 
    {
         return estimatedTradeAmount;
    }
    
    /**
     * ŠTZ”„”ƒ‘ã‹àiŠO‰İj‚ğƒZƒbƒg‚·‚éB<BR>
     * @@roseuid 40B31DAC02E8
     */
    public void setForeignCurrencyEstimatedTradeAmount(double l_dblForeignTradeAmount) 
    {
        foreignCurrencyEstimatedTradeAmount = l_dblForeignTradeAmount;
    }
    
    /**
     * ŠTZ”„”ƒ‘ã‹à(ŠO‰İ)‚ğæ“¾‚·‚é<BR>
     * @@roseuid 40D785CD01FC
     */
    public double getForeignCurrencyEstimatedTradeAmount() 
    {
        return foreignCurrencyEstimatedTradeAmount;
    }
    
    /**
     * (setŠTZó“n‘ã‹à)<BR>
     * @@roseuid 40B58EA1014F
     */
    public void setEstimatedPrice(double l_dblEstimatedPrice) 
    {
        estimatedPrice = l_dblEstimatedPrice;
    }
    
    /**
     * (getŠTZó“n‘ã‹à)<BR>
     * @@roseuid 40B58EB10110
     */
    public double getEstimatedPrice() 
    {
        return estimatedPrice;
    }  
    
    /**
     * (setè”—¿)
     * è”—¿‚ğƒZƒbƒg‚·‚éB<BR>
     * @@roseuid 40B58EA1014F
     */
    public void setCommission(double l_dblCommission) 
    {
        this.commission = l_dblCommission;
    }
    
    /**
     * (getè”—¿)
     * è”—¿‚ğæ“¾‚·‚éB <BR>
     * this.è”—¿‚ğ•Ô‹p‚·‚éB<BR>
     * @@roseuid 40B58EB10110
     */
    public double getCommission() 
    {
        return this.commission;
    }
    
    /**
     * (setè”—¿Á”ïÅ)
     * è”—¿Á”ïÅ‚ğƒZƒbƒg‚·‚éB<BR>
     * @@roseuid 40B58EA1014F
     */
    public void setCommissionTax(double l_dblCommissionTax) 
    {
        this.commissionTax = l_dblCommissionTax;
    }
    
    /**
     * (getè”—¿Á”ïÅ)
     * è”—¿Á”ïÅ‚ğæ“¾‚·‚éB <BR>
     * this.è”—¿Á”ïÅ‚ğ•Ô‹p‚·‚éB<BR>
     * @@roseuid 40B58EB10110
     */
    public double getCommissionTax() 
    {
        return this.commissionTax;
    }
    
    /**
     * (setŠ“¾Å)
     * Š“¾Å‚ğƒZƒbƒg‚·‚éB<BR>
     * @@roseuid 40B58EA1014F
     */
    public void setIncomeTax(double l_dblIncomeTax) 
    {
        this.incomeTax = l_dblIncomeTax;
    }
    
    /**
     * (getŠ“¾Å)
     * Š“¾Å‚ğæ“¾‚·‚éB <BR>
     * this.Š“¾Å‚ğ•Ô‹p‚·‚éB<BR>
     * @@roseuid 40B58EB10110
     */
    public double getIncomeTax() 
    {
        return this.incomeTax;
    }
    
    /**
     * (set’n•ûÅ)
     * ’n•ûÅ‚ğƒZƒbƒg‚·‚éB<BR>
     * @@roseuid 40B58EA1014F
     */
    public void setLocalTax(double l_dblLocalTax) 
    {
        this.localTax = l_dblLocalTax;
    }
    
    /**
     * (get’n•ûÅ)
     * ’n•ûÅ‚ğæ“¾‚·‚éB <BR>
     * this.’n•ûÅ‚ğ•Ô‹p‚·‚éB<BR>
     * @@roseuid 40B58EB10110
     */
    public double getLocalTax() 
    {
        return this.localTax;
    }
}
@
