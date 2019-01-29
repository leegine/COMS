head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondEstimatedPriceCalcResult.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ����n����v�Z����(WEB3BondEstimatedPriceCalcResult.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/08/17  ꎉ� (���u) �V�K�쐬
 */

package webbroker3.bd;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * (����n����v�Z����)<BR>
 * ����n����v�Z���ʃN���X<BR>
 * <BR>
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3BondEstimatedPriceCalcResult 
{
    
    /**
     * (��������ʔ���)<BR>
     * ��������ʔ���<BR>
     */
    private WEB3BondOrderTypeJudge bondOrderTypeJudge;
    
    /**
     * (��������i�~�݁j)<BR>
     * ��������i�~�݁j<BR>
     */
    private BigDecimal tradingPrice;
    
    /**
     * (�o�ߗ��q�i�~�݁j)<BR>
     * �o�ߗ��q�i�~�݁j<BR>
     */
    private BigDecimal accruedInterest;
    
    /**
     * (��n����i�~�݁j)<BR>
     * ��n����i�~�݁j<BR>
     */
    private BigDecimal estimatedPrice;
    
    /**
     * (��������i�O�݁j)<BR>
     * ��������i�O�݁j<BR>
     */
    private BigDecimal foreignTradePrice;
    
    /**
     * (�o�ߗ��q�i�O�݁j)<BR>
     * �o�ߗ��q�i�O�݁j<BR>
     */
    private BigDecimal foreignAccruedInterest;
    
    /**
     * (��n����i�O�݁j)<BR>
     * ��n����i�O�݁j<BR>
     */
    private BigDecimal foreignEstimatedPrice;
    
    /**
     * (�o�ߓ���)<BR>
     * �o�ߓ���<BR>
     */
    private Integer elapsedDays;
    
    /**
     * (�����)<BR>
     * �����<BR>
     */
    private Integer calcBaseDays;
    
    /**
     * (�בփ��[�g)<BR>
     * �בփ��[�g<BR>
     */
    private BigDecimal fxRate;
    
    /**
     * (�P��)<BR>
     * �P��<BR>
     */
    private BigDecimal price;
    
    /**
     * (����)<BR>
     * ����<BR>
     */
    private BigDecimal quantity;
    
    /**
     * (�x���敪�ꗗ)<BR>
     * �x���敪�ꗗ<BR>
     * <BR>
     * �@@�x���敪��String�𕡐��ێ��\�Ƃ��郊�X�g<BR>
     */
    private List warningDivList;
    
    /**
     * @@roseuid 44E336120177
     */
    public WEB3BondEstimatedPriceCalcResult() 
    {
     
    }
    
    /**
     * (get��������i�~�݁j)<BR>
     * get��������i�~�݁j<BR>
     * @@return BigDecima��
     * @@roseuid 44C813D40251
     */
    public BigDecimal getTradingPrice() 
    {
        return this.tradingPrice;
    }
    
    /**
     * (set��������i�~�݁j)<BR>
     * set��������i�~�݁j<BR>
     * @@param l_bdTradingPrice - (��������i�~�݁j)<BR>
     * ��������i�~�݁j<BR>
     * @@roseuid 44C81432037A
     */
    public void setTradingPrice(BigDecimal l_bdTradingPrice) 
    {
        this.tradingPrice = l_bdTradingPrice;
    }
    
    /**
     * (get�o�ߗ��q�i�~�݁j)<BR>
     * get�o�ߗ��q�i�~�݁j<BR>
     * @@return BigDecima��
     * @@roseuid 44C813E60138
     */
    public BigDecimal getAccruedInterest() 
    {
        return this.accruedInterest;
    }
    
    /**
     * (set�o�ߗ��q�i�~�݁j)<BR>
     * set�o�ߗ��q�i�~�݁j<BR>
     * @@param l_bdAccruedInterest - (�o�ߗ��q�i�~�݁j)<BR>
     * �o�ߗ��q�i�~�݁j<BR>
     * @@roseuid 44C8144802DE
     */
    public void setAccruedInterest(BigDecimal l_bdAccruedInterest) 
    {
        this.accruedInterest = l_bdAccruedInterest;
    }
    
    /**
     * (get��n����i�~�݁j)<BR>
     * get��n����i�~�݁j<BR>
     * @@return BigDecima��
     * @@roseuid 44C813F101F4
     */
    public BigDecimal getEstimatedPrice() 
    {
        return this.estimatedPrice;
    }
    
    /**
     * (set��n����i�~�݁j)<BR>
     * set��n����i�~�݁j<BR>
     * @@param l_bdEstimatedPrice - (��n����i�~�݁j)<BR>
     * ��n����i�~�݁j<BR>
     * @@roseuid 44C8144C01E4
     */
    public void setEstimatedPrice(BigDecimal l_bdEstimatedPrice) 
    {
        this.estimatedPrice = l_bdEstimatedPrice;
    }
    
    /**
     * (get��������i�O�݁j)<BR>
     * get��������i�O�݁j<BR>
     * @@return BigDecima��
     * @@roseuid 44C8140103A9
     */
    public BigDecimal getForeignTradePrice() 
    {
        return this.foreignTradePrice;
    }
    
    /**
     * (set��������i�O�݁j)<BR>
     * set��������i�O�݁j<BR>
     * @@param l_bdForeignTradePrice - (��������i�O�݁j)<BR>
     * ��������i�O�݁j<BR>
     * @@roseuid 44C8221C009C
     */
    public void setForeignTradePrice(BigDecimal l_bdForeignTradePrice) 
    {
        this.foreignTradePrice = l_bdForeignTradePrice;
    }
    
    /**
     * (get�o�ߗ��q�i�O�݁j)<BR>
     * get�o�ߗ��q�i�O�݁j<BR>
     * @@return BigDecima��
     * @@roseuid 44C8140103B9
     */
    public BigDecimal getForeignAccruedInterest() 
    {
        return this.foreignAccruedInterest;
    }
    
    /**
     * (set�o�ߗ��q�i�O�݁j)<BR>
     * set�o�ߗ��q�i�O�݁j<BR>
     * @@param l_bdForeignAccruedInterest - (�o�ߗ��q�i�O�݁j)<BR>
     * �o�ߗ��q�i�O�݁j<BR>
     * @@roseuid 44C8221C00BB
     */
    public void setForeignAccruedInterest(BigDecimal l_bdForeignAccruedInterest) 
    {
        this.foreignAccruedInterest = l_bdForeignAccruedInterest;
    }
    
    /**
     * (get��n����i�O�݁j)<BR>
     * get��n����i�O�݁j<BR>
     * @@return BigDecima��
     * @@roseuid 44C8140103D8
     */
    public BigDecimal getForeignEstimatedPrice() 
    {
         return this.foreignEstimatedPrice;
    }
    
    /**
     * (set��n����i�O�݁j)<BR>
     * set��n����i�O�݁j<BR>
     * @@param l_bdForeignEstimatedPrice - (��n����i�O�݁j)<BR>
     * ��n����i�O�݁j<BR>
     * @@roseuid 44C8221C00DA
     */
    public void setForeignEstimatedPrice(BigDecimal l_bdForeignEstimatedPrice) 
    {
        this.foreignEstimatedPrice = l_bdForeignEstimatedPrice;
    }
    
    /**
     * (get�o�ߓ���)<BR>
     * get�o�ߓ���<BR>
     * @@return Integer
     * @@roseuid 44C81416008C
     */
    public Integer getElapsedDays() 
    {
        return this.elapsedDays;
    }
    
    /**
     * (set�o�ߓ���)<BR>
     * set�o�ߓ���<BR>
     * @@param l_intElapsedDays - (�o�ߓ���)<BR>
     * �o�ߓ���<BR>
     * @@roseuid 44C822510399
     */
    public void setElapsedDays(Integer l_intElapsedDays) 
    {
        this.elapsedDays = l_intElapsedDays;
    }
    
    /**
     * (get�����)<BR>
     * get�����<BR>
     * @@return Integer
     * @@roseuid 44C8142600CB
     */
    public Integer getCalcBaseDays() 
    {
        return this.calcBaseDays;
    }
    
    /**
     * (set�����)<BR>
     * set�����<BR>
     * @@param l_intCalcBaseDays - (�����)<BR>
     * �����<BR>
     * @@roseuid 44C8228901C5
     */
    public void setCalcBaseDays(Integer l_intCalcBaseDays) 
    {
        this.calcBaseDays = l_intCalcBaseDays;
    }
    
    /**
     * (calc��n����i�~�݁j)<BR>
     * calc��n����i�~�݁j<BR>
     * <BR>
     * ���̌v�Z���ʂ�Ԃ�<BR>
     * this.get��������i�~�݁j�@@�{�@@this.get�o�ߗ��q�i�~�݁j<BR>
     * @@return BigDecimal
     * @@roseuid 44CB177A014A
     */
    public BigDecimal calcEstimatedPrice() 
    {
        BigDecimal l_bdTemp = null;
        if (this.tradingPrice != null && this.accruedInterest != null)
        {    
            l_bdTemp = this.getTradingPrice().add(this.getAccruedInterest());
        }
        return l_bdTemp;
    }
    
    /**
     * (calc��n����i�O�݁j)<BR>
     * calc��n����i�O�݁j<BR>
     * <BR>
     * ���̌v�Z���ʂ�Ԃ�<BR>
     * this.get��������i�O�݁j�@@�{�@@this.get�o�ߗ��q�i�O�݁j<BR>
     * @@return BigDecimal
     * @@roseuid 44CB17CC033F
     */
    public BigDecimal calcForeignEstimatedPrice() 
    {
        BigDecimal l_bdTemp = null;
        if (this.getForeignTradePrice() != null && this.getForeignAccruedInterest() != null)
        {    
            l_bdTemp = this.getForeignTradePrice().add(this.getForeignAccruedInterest());
        }
        return l_bdTemp;
    }
    
    /**
     * (get��������ʔ���)<BR>
     * get��������ʔ���<BR>
     * @@return ��������ʔ���
     * @@roseuid 44CB260701E7
     */
    public WEB3BondOrderTypeJudge getBondOrderTypeJudge() 
    {
        return this.bondOrderTypeJudge;
    }
    
    /**
     * (set��������ʔ���)<BR>
     * set��������ʔ���<BR>
     * @@param l_bondOrderTypeJudge - (��������ʔ���)<BR>
     * ��������ʔ���<BR>
     * @@roseuid 44CB262201E7
     */
    public void setBondOrderTypeJudge(WEB3BondOrderTypeJudge l_bondOrderTypeJudge) 
    {
        this.bondOrderTypeJudge = l_bondOrderTypeJudge;
    }
    
    /**
     * (get�בփ��[�g)<BR>
     * get�בփ��[�g<BR>
     * @@return BigDecima��
     * @@roseuid 44CB2A5801F5
     */
    public BigDecimal getFxRate() 
    {
        return this.fxRate;
    }
    
    /**
     * (set�בփ��[�g)<BR>
     * set�בփ��[�g<BR>
     * @@param l_bdFxRate - (�בփ��[�g)<BR>
     * �בփ��[�g<BR>
     * @@roseuid 44CB2A580243
     */
    public void setFxRate(BigDecimal l_bdFxRate) 
    {
        this.fxRate = l_bdFxRate;
    }
    
    /**
     * (get�P��)<BR>
     * get�P��<BR>
     * @@return BigDecima��
     * @@roseuid 44CB2AAB0254
     */
    public BigDecimal getPrice() 
    {
        return this.price;
    }
    
    /**
     * (set�P��)<BR>
     * set�P��<BR>
     * @@param l_bdPrice - (�P��)<BR>
     * �P��<BR>
     * @@roseuid 44CB2AAB0292
     */
    public void setPrice(BigDecimal l_bdPrice) 
    {
        this.price = l_bdPrice;
    }
    
    /**
     * (get����)<BR>
     * get����<BR>
     * @@return BigDecima��
     * @@roseuid 44CB2B2B014C
     */
    public BigDecimal getQuantity() 
    {
        return this.quantity;
    }
    
    /**
     * (set����)<BR>
     * set����<BR>
     * @@param l_bdQuantity - (����)<BR>
     * ����<BR>
     * @@roseuid 44CB2B2B017B
     */
    public void setQuantity(BigDecimal l_bdQuantity) 
    {
        this.quantity = l_bdQuantity;
    }
    
    /**
     * (get�x���敪�ꗗ)<BR>
     * get�x���敪�ꗗ<BR>
     * <BR>
     * �����̌x���敪�ꗗ�̃R�s�[List��߂��B<BR>
     * @@return List
     * @@roseuid 44DA7CA40000
     */
    public List getWarningDivList() 
    {
        return this.warningDivList;
    }
    
    /**
     * (add�x���敪)<BR>
     * add�x���敪<BR>
     * <BR>
     * �@@�x���敪�ꗗ�Ɉ���.�x���敪�����݂��Ȃ��ꍇ�A<BR>
     * �@@�x���敪�ꗗ�Ɉ���.�x���敪��ǉ�����B<BR>
     * @@param l_strWarningDiv - (�x���敪)<BR>
     * �x���敪<BR>
     * @@roseuid 44DA7CEA0196
     */
    public void addWarningDiv(String l_strWarningDiv) 
    {
        int l_intFlag = 1;
        int l_intSize = 0;
        
        if (this.warningDivList == null)
        {
            this.warningDivList = new ArrayList();
        }
        
        if (this.warningDivList != null && !this.warningDivList.isEmpty())
        {
            l_intSize = this.warningDivList.size();
        }
        for (int i = 0; i < l_intSize; i++)
        {
            if (this.warningDivList.get(i).equals(l_strWarningDiv))
            {
                l_intFlag = 0;
                break;
            }
        }
        if (l_intFlag != 0)
        {

            this.warningDivList.add(l_strWarningDiv);
        }    
    }
}@
