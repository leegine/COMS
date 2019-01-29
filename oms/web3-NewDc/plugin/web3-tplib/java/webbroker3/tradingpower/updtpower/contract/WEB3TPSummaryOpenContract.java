head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.04.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSummaryOpenContract.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����ό��ʂ̏W�v(WEB3TPSummaryOpenContract.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/02 �V���@@�h�O (FLJ) �V�K�쐬
*/
package webbroker3.tradingpower.updtpower.contract;

import webbroker3.tradingpower.util.ToStringUtils;


/**
 * (�����ό��ʂ̏W�v)
 */
public class WEB3TPSummaryOpenContract extends WEB3TPContractBase 
{
    
    /**
     * (���������ʑ��)
     */
    private double unExecContractAmount;
    
    /**
     * (�������K�v�ۏ؋�)
     */
    private double unExecMarginDeposit;
    
    /**
     * (�����������K�v�ۏ؋�)
     */
    private double unExecCashMarginDeposit;
    
    /**
     * (�����ό��ʕ]����)
     */
    private double assetLoss;
    
    /**
     * (�����ό��ʕ]���v)
     */
    private double assetProfit;
    
    /**
     * (���萔��)
     */
    private double setupFee;
    
    /**
     * (�����E�t������)
     */
    private double interestLoss;
    
    /**
     * (�����E�t�����v)
     */
    private double interestProfit;
    
    /**
     * (���̑����ʏ��o��)
     */
    private double otherCost;
    
    /**
     * @@roseuid 4104AE3E034B
     */
    public WEB3TPSummaryOpenContract() 
    {
     
    }
    
    /**
     * (create�����ό��ʂ̏W�v)
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPSummaryOpenContract
     * @@roseuid 4100F0E100EA
     */
    public static WEB3TPSummaryOpenContract create() 
    {
        return new WEB3TPSummaryOpenContract();
    }
    
    /**
     * (get���������ʑ��)<BR>
     * ���������ʑ�����擾����B<BR>
     * @@return double
     */
    public double getUnExecContractAmount() 
    {
        return unExecContractAmount;
    }
    
    /**
     * (set���������ʑ��)<BR>
     * �����̔��������ʑ�����Z�b�g����B<BR>
     * @@param l_dblUnExecContractAmount - (���������ʑ��)
     */
    public void setUnExecContractAmount(double l_dblUnExecContractAmount) 
    {
        unExecContractAmount = l_dblUnExecContractAmount;
    }
    
    /**
     * (get�������K�v�ۏ؋�)<BR>
     * �������K�v�ۏ؋����擾����B<BR>
     * @@return double
     */
    public double getUnExecMarginDeposit() 
    {
        return unExecMarginDeposit;
    }
    
    /**
     * (set�������K�v�ۏ؋�)<BR>
     * �����̔������K�v�ۏ؋����Z�b�g����B<BR>
     * @@param l_dblUnExecMarginDeposit - (�������K�v�ۏ؋�)
     */
    public void setUnExecMarginDeposit(double l_dblUnExecMarginDeposit) 
    {
        unExecMarginDeposit = l_dblUnExecMarginDeposit;
    }
    
    /**
     * (get�����������K�v�ۏ؋�)<BR>
     * �����������K�v�ۏ؋����擾����B<BR>
     * @@return double
     */
    public double getUnExecCashMarginDeposit() 
    {
        return unExecCashMarginDeposit;
    }
    
    /**
     * (set�����������K�v�ۏ؋�)<BR>
     * �����̔����������K�v�ۏ؋����Z�b�g����B<BR>
     * @@param l_dblUnExecCashMarginDeposit - (�����������K�v�ۏ؋�)
     */
    public void setUnExecCashMarginDeposit(double l_dblUnExecCashMarginDeposit) 
    {
        unExecCashMarginDeposit = l_dblUnExecCashMarginDeposit;
    }
    
    /**
     * (get�����ό��ʕ]����)<BR>
     * �����ό��ʕ]�������擾����B<BR>
     * @@return double
     */
    public double getAssetLoss() 
    {
        return assetLoss;
    }
    
    /**
     * (set�����ό��ʕ]����)<BR>
     * �����̖����ό��ʕ]�������Z�b�g����B<BR>
     * @@param l_dblAssetLoss - (�����ό��ʕ]����)
     */
    public void setAssetLoss(double l_dblAssetLoss) 
    {
        assetLoss = l_dblAssetLoss;
    }
    
    /**
     * (get�����ό��ʕ]���v)<BR>
     * �����ό��ʕ]���v���擾����B<BR>
     * @@return double
     */
    public double getAssetProfit() 
    {
        return assetProfit;
    }
    
    /**
     * (set�����ό��ʕ]���v)<BR>
     * �����̖����ό��ʕ]���v���Z�b�g����B<BR>
     * @@param l_dblAssetProfit - (�����ό��ʕ]���v)
     */
    public void setAssetProfit(double l_dblAssetProfit) 
    {
        assetProfit = l_dblAssetProfit;
    }
    
    /**
     * (get���萔��)<BR>
     * ���萔�����擾����B<BR>
     * @@return double
     */
    public double getSetupFee() 
    {
        return setupFee;
    }
    
    /**
     * (set���萔��)<BR>
     * �����̌��萔�����Z�b�g����B<BR>
     * @@param l_dblSetupFee - (���萔��)
     */
    public void setSetupFee(double l_dblSetupFee) 
    {
        setupFee = l_dblSetupFee;
    }
    
    /**
     * (get�����E�t������)<BR>
     * �����E�t���������擾����B<BR>
     * @@return double
     */
    public double getInterestLoss() 
    {
        return interestLoss;
    }
    
    /**
     * (set�����E�t������)<BR>
     * �����̓����E�t���������Z�b�g����B<BR>
     * @@param l_dblInterestLoss - (�����E�t������)
     */
    public void setInterestLoss(double l_dblInterestLoss) 
    {
        interestLoss = l_dblInterestLoss;
    }
    
    /**
     * (get�����E�t�����v)<BR>
     * �����E�t�����v���擾����B<BR>
     * @@return double
     */
    public double getInterestProfit() 
    {
        return interestProfit;
    }
    
    /**
     * (set�����E�t�����v)<BR>
     * �����̓����E�t�����v���Z�b�g����B<BR>
     * @@param l_dblInterestProfit - (�����E�t�����v)
     */
    public void setInterestProfit(double l_dblInterestProfit) 
    {
        interestProfit = l_dblInterestProfit;
    }
    
    /**
     * (get���̑����ʏ��o��)<BR>
     * ���̑����ʏ��o����擾����B<BR>
     * @@return double
     */
    public double getOtherCost() 
    {
        return otherCost;
    }
    
    /**
     * (set���̑����ʏ��o��)<BR>
     * �����̂��̑����ʏ��o����Z�b�g����B<BR>
     * @@param l_dblOtherCost - (���̑����ʏ��o��)
     */
    public void setOtherCost(double l_dblOtherCost) 
    {
        otherCost = l_dblOtherCost;
    }
    
    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B
     */
    public String toString() 
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .appendSuper(super.toString())
            .append("unExecContractAmount", getUnExecContractAmount())
            .append("unExecMarginDeposit", getUnExecMarginDeposit())
            .append("unExecCashMarginDeposit", getUnExecCashMarginDeposit())
            .append("assetLoss", getAssetLoss())
            .append("assetProfit", getAssetProfit())
            .append("setupFee", getSetupFee())
            .append("interestLoss", getInterestLoss())
            .append("interestProfit", getInterestProfit())
            .append("otherCost", getOtherCost())
            .toString();
    }
}
@
