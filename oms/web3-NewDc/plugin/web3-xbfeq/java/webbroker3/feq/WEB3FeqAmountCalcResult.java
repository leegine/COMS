head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqAmountCalcResult.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���������z�v�Z����(WEB3FeqAmountCalcResult.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ���I (���u) �V�K�쐬
                   2005/07/26 �����F(���u) ���r���[
*/

package webbroker3.feq;


/**
 * (�O���������z�v�Z����) <BR>
 * �O���������z�v�Z���� <BR>
 * <BR>
 * @@ author ���I <BR> 
 * @@ version 1.0 <BR>
 */
public class WEB3FeqAmountCalcResult 
{
    
    /**
     * (���n���o��)<BR>
     * ���n���o��
     */
    private WEB3FeqForeignCost foreignCost;
    
    /**
     * (��������i�O�݁j)<BR>
     * ��������i�O�݁j
     */
    private double tradePriceFc = 0;
    
    /**
     * (��������i�~�݁j)<BR>
     * ��������i�~�݁j
     */
    private double tradePrice = 0;
    
    /**
     * (���n���Z����i�~�݁j)<BR>
     * ���n���Z����i�~�݁j
     */
    private double balanceAmount = 0;
    
    /**
     * (��n���)<BR>
     * ��n���
     */
    private double netAmount = 0;
    
    /**
     * (��n����i�O�݁j)<BR>
     * ��n����i�O�݁j
     */
    private double netAmountFc = 0;
    
    /**
     * (�ϑ��萔��)<BR>
     * �ϑ��萔��
     */
    private double commissionFee = 0;
    
    /**
     * (�ϑ��萔�������)<BR>
     * �ϑ��萔�������
     */
    private double commissionFeeTax = 0;
    
    /**
     * (�ϑ��萔���i�O�݁j) <BR>
     * �ϑ��萔���i�O�݁j
     */
    private double commissionFeeFc = 0;
    
    /**
     * (�ϑ��萔������Łi�O�݁j)<BR>
     * �ϑ��萔������Łi�O�݁j
     */
    private double commissionFeeTaxFc = 0;
    
    /**
     * (�萔��No) <BR>
     * �萔���v�Z�Ɏg�p�����萔��No�B <BR>
     * calc�ϑ��萔�����ԋp����B <BR>
     */
    private String commissionNumber;
    
    /**
     * (�萔��No�}��) <BR>
     * �萔���v�Z�Ɏg�p�����萔��No�}�ԁB <BR>
     * calc�ϑ��萔�����ԋp����B <BR>
     */
    private String commissionBranchNumber;
    
    /**
     * (������)<BR>
     * �萔���v�Z�Ɏg�p�����������B
     */
    private double chargeRatio;
    
    /**
     * (�O���������z�v�Z����) <BR>
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 4289A57E008C
     */
    public WEB3FeqAmountCalcResult() 
    {
        foreignCost = new WEB3FeqForeignCost();
    }
    
    /**
     * (get���n�萔��) <BR>
     * ���n�萔�����擾����B <BR>
     *  <BR>
     * this.���n���o��.���n�萔����ԋp����B <BR>
     * @@return double
     * @@roseuid 4289910A01A5
     */
    public double getForignCommissionFee() 
    {
        return foreignCost.getForeignCommissionFee();
    }
    
    /**
     * (���n�����) <BR>
     * ���n����ł��擾����B <BR>
     *  <BR>
     * this.���n���o��.���n����ł�ԋp����B <BR>
     * @@return double
     * @@roseuid 4289910A01C4
     */
    public double getForeignTax() 
    {
        return foreignCost.getForeignTax();
    }
    
    /**
     * (get���̑��R�X�g�P) <BR>
     * ���̑��R�X�g�P���擾����B <BR>
     *  <BR>
     * this.���n���o��.���̑��R�X�g�P��ԋp����B <BR>
     * @@return double
     * @@roseuid 4289910A01D4
     */
    public double getForeignFeeExt1() 
    {
        return foreignCost.getForeignFeeExt1();
    }
    
    /**
     * (get���̑��R�X�g�Q)<BR>
     * ���̑��R�X�g�Q���擾����B<BR>
     * <BR>
     * this.���n���o��.���̑��R�X�g�Q��ԋp����B<BR>
     * @@return double
     * @@roseuid 4289910A01F3
     */
    public double getForeignFeeExt2() 
    {
        return foreignCost.getForeignFeeExt2();
    }
    
    /**
     * (get���n���Z���) <BR>
     * ���n���Z������擾����B <BR>
     *  <BR>
     * this.���n���o��.���n���Z�����ԋp����B <BR>
     * @@return double
     * @@roseuid 4289910A0203
     */
    public double getBalanceAmountFc() 
    {
        return foreignCost.getBalanceAmountFc();
    }
    
    /**
     * (get���n���Z����i�~�݁j) <BR>
     * ���n���Z����i�~�݁j���擾����B <BR>
     *  <BR>
     * this.���n���Z����i�~�݁j��ԋp����B <BR>
     * @@return double
     * @@roseuid 4289915C0241
     */
    public double getBalanceAmount() 
    {
        return balanceAmount;
    }
    
    /**
     * (get��n���) <BR>
     * ��n������擾����B <BR>
     *  <BR>
     * this.��n�����ԋp����B <BR>
     * @@return double
     * @@roseuid 4289918F0157
     */
    public double getNetAmount() 
    {
        return netAmount;
    }
    
    /**
     * (get��n����i�O�݁j) <BR>
     * ��n����i�O�݁j���擾����B <BR>
     *  <BR>
     * this.��n����i�O�݁j��ԋp����B <BR>
     * @@return double
     * @@roseuid 428991B703E7
     */
    public double getNetAmountFc() 
    {
        return netAmountFc;
    }
    
    /**
     * (get�ϑ��萔��) <BR>
     * �ϑ��萔�����擾����B <BR>
     *  <BR>
     * this.�ϑ��萔����ԋp����B <BR>
     * @@return double
     * @@roseuid 428991FB02BE
     */
    public double getCommissionFee() 
    {
        return commissionFee;
    }
    
    /**
     * (get�ϑ��萔���i�O�݁j) <BR>
     * �ϑ��萔���i�O�݁j���擾����B <BR>
     *  <BR>
     * this.�ϑ��萔���i�O�݁j��ԋp����B <BR>
     * @@return double
     * @@roseuid 428991FB02CE
     */
    public double getCommissionFeeFc() 
    {
        return commissionFeeFc;
    }
    
    /**
     * (get�ϑ��萔�������) <BR>
     * �ϑ��萔������ł��擾����B <BR>
     *  <BR>
     * this.�ϑ��萔������ł�ԋp����B <BR>
     * @@return double
     * @@roseuid 4289925A00EA
     */
    public double getCommisionFeeTax() 
    {
        return commissionFeeTax;
    }
    
    /**
     * (get�ϑ��萔������Łi�O�݁j) <BR>
     * �ϑ��萔������Łi�O�݁j���擾����B <BR>
     *  <BR>
     * this.�ϑ��萔������Łi�O�݁j��ԋp����B <BR>
     * @@return double
     * @@roseuid 4289925A0109
     */
    public double getCommisionFeeTaxFc() 
    {
        return commissionFeeTaxFc;
    }
    
    /**
     * (get��������i�~�݁j) <BR>
     * ��������i�~�݁j���擾����B <BR>
     *  <BR>
     * this.��������i�~�݁j��ԋp����B <BR>
     * @@return double
     * @@roseuid 4289A4B3028F
     */
    public double getTradePrice() 
    {
        return tradePrice;
    }
    
    /**
     * (get��������i�O�݁j) <BR>
     * ��������i�O�݁j���擾����B <BR>
     *  <BR>
     * this.��������i�O�݁j��ԋp����B <BR>
     * @@return double
     * @@roseuid 4289A4B30290
     */
    public double getTradePriceFc() 
    {
        return tradePriceFc;
    }
    
    /**
     * (get�萔��No) <BR>
     * �萔��No���擾����B
     * @@return String
     * @@roseuid 4289AD73030E
     */
    public String getCommissionNumber() 
    {
        return commissionNumber;
    }
    
    /**
     * (get�萔��No�}��) <BR>
     * �萔��No�}�Ԃ��擾����B
     * @@return String
     * @@roseuid 4289AD73031E
     */
    public String getCommissionBranchNumber() 
    {
        return commissionBranchNumber;
    }
    
    /**
     * (set���n�萔��) <BR>
     * ���n�萔�����Z�b�g����B <BR>
     *  <BR>
     * this.���n���o��.���n�萔���Ɍ��n�萔�����Z�b�g����B <BR>
     * @@param l_dblForeignCommFee - (���n�萔��) <BR>
     * ���n�萔��
     * @@roseuid 4289910A0222
     */
    public void setForeignCommissionFee(double l_dblForeignCommFee) 
    {
        foreignCost.setForeignCommissionFee(l_dblForeignCommFee);
    }
    
    /**
     * (set���n�����) <BR>
     * ���n����ł��Z�b�g����B <BR>
     *  <BR>
     * this.���n���o��.���n����łɌ��n����ł��Z�b�g����B <BR>
     * @@param l_dblForeignTax - (���n�����) <BR>
     * ���n�����
     * @@roseuid 4289910A0241
     */
    public void setForeignTax(double l_dblForeignTax) 
    {
        foreignCost.setForeignTax(l_dblForeignTax); 
    }
    
    /**
     * (set���̑��R�X�g�P) <BR>
     * ���̑��R�X�g�P���Z�b�g����B <BR>
     *  <BR>
     * this.���n���o��.���̑��R�X�g�P�ɂ��̑��R�X�g�P���Z�b�g����B <BR>
     * @@param l_dblForeignFeeExt1 - (���̑��R�X�g�P) <BR>
     * ���̑��R�X�g�P
     * @@roseuid 4289910A0251
     */
    public void setForeignFeeExt1(double l_dblForeignFeeExt1) 
    {
        foreignCost.setForeignFeeExt1(l_dblForeignFeeExt1);   
    }
    
    /**
     * (set���̑��R�X�g�Q) <BR>
     * ���̑��R�X�g�Q���Z�b�g����B <BR>
     *  <BR>
     * this.���n���o��.���̑��R�X�g�Q�ɂ��̑��R�X�g�Q���Z�b�g����B <BR>
     * @@param l_dblForeignFeeExt2 - (���̑��R�X�g�Q) <BR>
     * ���̑��R�X�g�Q
     * @@roseuid 4289910A0270
     */
    public void setForeignFeeExt2(double l_dblForeignFeeExt2) 
    {
        foreignCost.setForeignFeeExt2(l_dblForeignFeeExt2);    
    }
    
    /**
     * (set���n���Z���) <BR>
     * ���n���Z������Z�b�g����B <BR>
     *  <BR>
     * this.���n���o��.���n���Z����Ɍ��n���Z������Z�b�g����B <BR>
     * @@param l_dblBalanceAmountFc - (���n���Z���) <BR>
     * ���n���Z���
     * @@return Void
     * @@roseuid 4289910A028F
     */
    public void setBalanceAmountFc(double l_dblBalanceAmountFc) 
    {
        foreignCost.setBalanceAmountFc(l_dblBalanceAmountFc);
    }
    
    /**
     * (set���n���Z����i�~�݁j) <BR>
     * ���n���Z����i�~�݁j���Z�b�g����B <BR>
     *  <BR>
     * this.���n���Z����i�~�݁j�Ɍ��n���Z����i�~�݁j���Z�b�g����B <BR>
     * @@param l_dblBalanceAmount - (���n���Z����i�~�݁j) <BR>
     * ���n���Z����i�~�݁j
     * @@roseuid 4289915C0261
     */
    public void setBalanceAmount(double l_dblBalanceAmount) 
    {
        balanceAmount = l_dblBalanceAmount;    
    }
    
    /**
     * (set��n���) <BR>
     * ��n������Z�b�g����B <BR>
     *  <BR>
     * this.��n����Ɏ�n������Z�b�g����B <BR>
     * @@param l_dblNetAmount - (��n���) <BR>
     * @@roseuid 4289918F0176
     */
    public void setNetAmount(double l_dblNetAmount) 
    {
        netAmount = l_dblNetAmount;
    }
    
    /**
     * (set��n����i�O�݁j) <BR>
     * ��n����i�O�݁j���Z�b�g����B <BR>
     *  <BR>
     * this.��n����i�O�݁j�Ɏ�n����i�O�݁j���Z�b�g����B <BR>
     * @@param l_dblNetAmountFc - ��n����i�O�݁j <BR>
     * @@roseuid 428991B8001E
     */
    public void setNetAmountFc(double l_dblNetAmountFc) 
    {
        netAmountFc = l_dblNetAmountFc; 
    }
    
    /**
     * (set�ϑ��萔��) <BR>
     * �ϑ��萔�����Z�b�g����B<BR>
     * <BR>
     * this.�ϑ��萔���Ɉϑ��萔�����Z�b�g����B<BR>
     * @@param l_dblCommisionFee - �ϑ��萔��
     * @@roseuid 428991FB02ED
     */
    public void setCommissionFee(double l_dblCommisionFee) 
    {
        commissionFee = l_dblCommisionFee;
    }
    
    /**
     * (set�ϑ��萔���i�O�݁j) <BR>
     * �ϑ��萔���i�O�݁j���Z�b�g����B <BR>
     *  <BR>
     * this.�ϑ��萔���i�O�݁j�Ɉϑ��萔���i�O�݁j���Z�b�g����B <BR>
     * @@param l_dblCommisionFeeFc - �ϑ��萔���i�O�݁j
     * @@roseuid 428991FB031C
     */
    public void setCommissionFeeFc(double l_dblCommisionFeeFc) 
    {
        commissionFeeFc = l_dblCommisionFeeFc; 
    }
    
    /**
     * (set�ϑ��萔�������) <BR>
     * �ϑ��萔������ł��Z�b�g����B <BR>
     *  <BR>
     * this.�ϑ��萔������łɈϑ��萔������ł��Z�b�g����B <BR>
     * @@param l_dblCommisionFeeTax - �ϑ��萔�������
     * @@roseuid 4289925A0128
     */
    public void setCommissionFeeTax(double l_dblCommisionFeeTax) 
    {
        commissionFeeTax = l_dblCommisionFeeTax;
    }
    
    /**
     * (set�ϑ��萔������Łi�O�݁j) <BR>
     * �ϑ��萔������Łi�O�݁j���Z�b�g����B <BR>
     *  <BR>
     * this.�ϑ��萔������Łi�O�݁j�Ɉϑ��萔������Łi�O�݁j���Z�b�g����B <BR>
     * @@param l_dblCommisionFeeTaxFc - �ϑ��萔������Łi�O�݁j
     * @@roseuid 4289925A0147
     */
    public void setCommissionFeeTaxFc(double l_dblCommisionFeeTaxFc) 
    {
        commissionFeeTaxFc = l_dblCommisionFeeTaxFc;    
    }
    
    /**
     * (set��������i�~�݁j) <BR>
     * ��������i�~�݁j���Z�b�g����B <BR>
     *  <BR>
     * this.��������i�~�݁j�ɔ�������i�~�݁j���Z�b�g����B <BR>
     * @@param l_dblTradePrice - ��������i�~�݁j
     * @@roseuid 4289A4B30291
     */
    public void setTradePrice(double l_dblTradePrice) 
    {
        tradePrice = l_dblTradePrice; 
    }
    
    /**
     * (set��������i�O�݁j) <BR>
     * ��������i�O�݁j���Z�b�g����B <BR>
     *  <BR>
     * this.��������i�O�݁j�ɔ�������i�O�݁j���Z�b�g����B <BR>
     * @@param l_dblTradePriceFc - ��������i�O�݁j
     * @@roseuid 4289A4B30293
     */
    public void setTradePriceFc(double l_dblTradePriceFc) 
    {
        tradePriceFc = l_dblTradePriceFc;
    }
    
    /**
     * (set���n���o��) <BR>
     * ���n���o����Z�b�g����B <BR>
     *  <BR>
     * this.���n���o��Ɍ��n���o����Z�b�g����B <BR>
     * @@param l_foreignCost - ���n���o��
     * @@roseuid 4289AB840118
     */
    public void setForeignCost(WEB3FeqForeignCost l_foreignCost) 
    {
        this.foreignCost = l_foreignCost;
    }
    
    /**
     * (set�萔��No) <BR>
     * �萔��No���Z�b�g����B
     * @@param l_strCommisionNumber - �萔��No
     * @@roseuid 4289AD73030C
     */
    public void setCommissionNumber(String l_strCommisionNumber) 
    {
        commissionNumber = l_strCommisionNumber;
    }
    
    /**
     * (set�萔��No�}��) <BR>
     * �萔��No�}�Ԃ��Z�b�g����B
     * @@param l_strCommBranchNumber - �萔��No�}��
     * @@roseuid 4289AD73031C
     */
    public void setCommissionBranchNumber(String l_strCommBranchNumber) 
    {
        commissionBranchNumber = l_strCommBranchNumber;  
    }
    
    /**
     * (set������)<BR>
     * ���������Z�b�g����B
     * @@param l_dblChargeRatio - ������
     */
    public void setChargeRatio(double l_dblChargeRatio)
    {
        this.chargeRatio = l_dblChargeRatio;
    }
    
    /**
     * (get������)<BR>
     * ���������擾����B
     * @@return double
     */
    public double getChargeRatio()
    {
        return this.chargeRatio;
    }
}
@
