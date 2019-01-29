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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�Z��n���(WEB3MutualFundEstimatedPrice)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/05 ��O�� (���u) �V�K�쐬
                   2004/08/23 ������ (���u) ���r���[ 
*/

package webbroker3.mf;


/**
 * (�T�Z��n���)<BR>
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3MutualFundEstimatedPrice 
{
    public WEB3MutualFundEstimatedPrice()
    {
        
    }
    
    /**
     * (�T�Z��������)<BR>
     */
    private double estimatedQty;
    
    /**
     * (�T�Z�������)<BR>
     */
    private double estimatedTradeAmount;
    
    /**
     * (�T�Z��������i�O�݁j)<BR>
     */
    private double foreignCurrencyEstimatedTradeAmount;
    
    /**
     * (�T�Z��n���)<BR>
     */
    private double estimatedPrice;       
        
    /**
     * (�萔��)<BR>
     */
    private double commission;
    
    /**
     * (�萔�������)<BR>
     */
    private double commissionTax;
    
    /**
     * (������)<BR>
     */
    private double incomeTax;
    
    /**
     * (�n����)<BR>
     */
    private double localTax;
    
    /**
     * �T�Z�����������Z�b�g����B<BR>
     * @@roseuid 40B15D8A0323
     */
    public void setEstimatedQty(double l_dblEstimatedQty) 
    {
        estimatedQty = l_dblEstimatedQty;
    }
    
    /**
     * �T�Z�����������擾����<BR>
     * @@roseuid 40B15DA0013E
     */
    public double getEstimatedQty() 
    {
        return estimatedQty;
    }
    
    /**
     * �T�Z����������Z�b�g����B<BR>
     * @@roseuid 40B15DAB0209
     */
    public void setEstimatedTradeAmount(double l_dblEstimatedTradeAmount) 
    {
        estimatedTradeAmount = l_dblEstimatedTradeAmount;
    }
    
    /**
     * �T�Z����������擾����<BR>
     * @@roseuid 40B15DC201CB
     */
    public double getEstimatedTradeAmount() 
    {
         return estimatedTradeAmount;
    }
    
    /**
     * �T�Z��������i�O�݁j���Z�b�g����B<BR>
     * @@roseuid 40B31DAC02E8
     */
    public void setForeignCurrencyEstimatedTradeAmount(double l_dblForeignTradeAmount) 
    {
        foreignCurrencyEstimatedTradeAmount = l_dblForeignTradeAmount;
    }
    
    /**
     * �T�Z�������(�O��)���擾����<BR>
     * @@roseuid 40D785CD01FC
     */
    public double getForeignCurrencyEstimatedTradeAmount() 
    {
        return foreignCurrencyEstimatedTradeAmount;
    }
    
    /**
     * (set�T�Z��n���)<BR>
     * @@roseuid 40B58EA1014F
     */
    public void setEstimatedPrice(double l_dblEstimatedPrice) 
    {
        estimatedPrice = l_dblEstimatedPrice;
    }
    
    /**
     * (get�T�Z��n���)<BR>
     * @@roseuid 40B58EB10110
     */
    public double getEstimatedPrice() 
    {
        return estimatedPrice;
    }  
    
    /**
     * (set�萔��)
     * �萔�����Z�b�g����B<BR>
     * @@roseuid 40B58EA1014F
     */
    public void setCommission(double l_dblCommission) 
    {
        this.commission = l_dblCommission;
    }
    
    /**
     * (get�萔��)
     * �萔�����擾����B <BR>
     * this.�萔����ԋp����B<BR>
     * @@roseuid 40B58EB10110
     */
    public double getCommission() 
    {
        return this.commission;
    }
    
    /**
     * (set�萔�������)
     * �萔������ł��Z�b�g����B<BR>
     * @@roseuid 40B58EA1014F
     */
    public void setCommissionTax(double l_dblCommissionTax) 
    {
        this.commissionTax = l_dblCommissionTax;
    }
    
    /**
     * (get�萔�������)
     * �萔������ł��擾����B <BR>
     * this.�萔������ł�ԋp����B<BR>
     * @@roseuid 40B58EB10110
     */
    public double getCommissionTax() 
    {
        return this.commissionTax;
    }
    
    /**
     * (set������)
     * �����ł��Z�b�g����B<BR>
     * @@roseuid 40B58EA1014F
     */
    public void setIncomeTax(double l_dblIncomeTax) 
    {
        this.incomeTax = l_dblIncomeTax;
    }
    
    /**
     * (get������)
     * �����ł��擾����B <BR>
     * this.�����ł�ԋp����B<BR>
     * @@roseuid 40B58EB10110
     */
    public double getIncomeTax() 
    {
        return this.incomeTax;
    }
    
    /**
     * (set�n����)
     * �n���ł��Z�b�g����B<BR>
     * @@roseuid 40B58EA1014F
     */
    public void setLocalTax(double l_dblLocalTax) 
    {
        this.localTax = l_dblLocalTax;
    }
    
    /**
     * (get�n����)
     * �n���ł��擾����B <BR>
     * this.�n���ł�ԋp����B<BR>
     * @@roseuid 40B58EB10110
     */
    public double getLocalTax() 
    {
        return this.localTax;
    }
}
@
