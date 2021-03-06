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
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 概算受渡代金(WEB3MutualFundEstimatedPrice)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/05 韋念瓊 (中訊) 新規作成
                   2004/08/23 于美麗 (中訊) レビュー 
*/

package webbroker3.mf;


/**
 * (概算受渡代金)<BR>
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3MutualFundEstimatedPrice 
{
    public WEB3MutualFundEstimatedPrice()
    {
        
    }
    
    /**
     * (概算売買口数)<BR>
     */
    private double estimatedQty;
    
    /**
     * (概算売買代金)<BR>
     */
    private double estimatedTradeAmount;
    
    /**
     * (概算売買代金（外貨）)<BR>
     */
    private double foreignCurrencyEstimatedTradeAmount;
    
    /**
     * (概算受渡代金)<BR>
     */
    private double estimatedPrice;       
        
    /**
     * (手数料)<BR>
     */
    private double commission;
    
    /**
     * (手数料消費税)<BR>
     */
    private double commissionTax;
    
    /**
     * (所得税)<BR>
     */
    private double incomeTax;
    
    /**
     * (地方税)<BR>
     */
    private double localTax;
    
    /**
     * 概算売買口数をセットする。<BR>
     * @@roseuid 40B15D8A0323
     */
    public void setEstimatedQty(double l_dblEstimatedQty) 
    {
        estimatedQty = l_dblEstimatedQty;
    }
    
    /**
     * 概算売買口数を取得する<BR>
     * @@roseuid 40B15DA0013E
     */
    public double getEstimatedQty() 
    {
        return estimatedQty;
    }
    
    /**
     * 概算売買代金をセットする。<BR>
     * @@roseuid 40B15DAB0209
     */
    public void setEstimatedTradeAmount(double l_dblEstimatedTradeAmount) 
    {
        estimatedTradeAmount = l_dblEstimatedTradeAmount;
    }
    
    /**
     * 概算売買代金を取得する<BR>
     * @@roseuid 40B15DC201CB
     */
    public double getEstimatedTradeAmount() 
    {
         return estimatedTradeAmount;
    }
    
    /**
     * 概算売買代金（外貨）をセットする。<BR>
     * @@roseuid 40B31DAC02E8
     */
    public void setForeignCurrencyEstimatedTradeAmount(double l_dblForeignTradeAmount) 
    {
        foreignCurrencyEstimatedTradeAmount = l_dblForeignTradeAmount;
    }
    
    /**
     * 概算売買代金(外貨)を取得する<BR>
     * @@roseuid 40D785CD01FC
     */
    public double getForeignCurrencyEstimatedTradeAmount() 
    {
        return foreignCurrencyEstimatedTradeAmount;
    }
    
    /**
     * (set概算受渡代金)<BR>
     * @@roseuid 40B58EA1014F
     */
    public void setEstimatedPrice(double l_dblEstimatedPrice) 
    {
        estimatedPrice = l_dblEstimatedPrice;
    }
    
    /**
     * (get概算受渡代金)<BR>
     * @@roseuid 40B58EB10110
     */
    public double getEstimatedPrice() 
    {
        return estimatedPrice;
    }  
    
    /**
     * (set手数料)
     * 手数料をセットする。<BR>
     * @@roseuid 40B58EA1014F
     */
    public void setCommission(double l_dblCommission) 
    {
        this.commission = l_dblCommission;
    }
    
    /**
     * (get手数料)
     * 手数料を取得する。 <BR>
     * this.手数料を返却する。<BR>
     * @@roseuid 40B58EB10110
     */
    public double getCommission() 
    {
        return this.commission;
    }
    
    /**
     * (set手数料消費税)
     * 手数料消費税をセットする。<BR>
     * @@roseuid 40B58EA1014F
     */
    public void setCommissionTax(double l_dblCommissionTax) 
    {
        this.commissionTax = l_dblCommissionTax;
    }
    
    /**
     * (get手数料消費税)
     * 手数料消費税を取得する。 <BR>
     * this.手数料消費税を返却する。<BR>
     * @@roseuid 40B58EB10110
     */
    public double getCommissionTax() 
    {
        return this.commissionTax;
    }
    
    /**
     * (set所得税)
     * 所得税をセットする。<BR>
     * @@roseuid 40B58EA1014F
     */
    public void setIncomeTax(double l_dblIncomeTax) 
    {
        this.incomeTax = l_dblIncomeTax;
    }
    
    /**
     * (get所得税)
     * 所得税を取得する。 <BR>
     * this.所得税を返却する。<BR>
     * @@roseuid 40B58EB10110
     */
    public double getIncomeTax() 
    {
        return this.incomeTax;
    }
    
    /**
     * (set地方税)
     * 地方税をセットする。<BR>
     * @@roseuid 40B58EA1014F
     */
    public void setLocalTax(double l_dblLocalTax) 
    {
        this.localTax = l_dblLocalTax;
    }
    
    /**
     * (get地方税)
     * 地方税を取得する。 <BR>
     * this.地方税を返却する。<BR>
     * @@roseuid 40B58EB10110
     */
    public double getLocalTax() 
    {
        return this.localTax;
    }
}
@
