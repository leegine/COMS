head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityEstimatedDeliveryPrice.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�Z��n����v�Z����(WEB3EquityEstimatedDeliveryPrice.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/19 ��� (���u) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
                   2006/11/01 ������@@(���u)���f��No.945, No.1026
*/
package webbroker3.equity;

import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i�T�Z��n����v�Z���ʁj�B<BR>
 * <BR>
 * �g�����������}�l�[�W��.calc�T�Z��n���()<BR>
 * �̖߂�l��\������f�[�^�N���X�B
 * @@version 1.0
 */
public class WEB3EquityEstimatedDeliveryPrice implements WEB3EquityEstimatedPrice
{

    /**
     * (�v�Z�P��) <BR>
     * �@@�E�w�l�����̏ꍇ�͎w�l�B <BR>
     * �@@�E���s�����̏ꍇ�͎����B <BR>
     * <BR>
     */
    private double calculationUnitPrice;

    /**
     * (�S���������) <BR>
     */
    private double restraintTurnover;

    /**
     * (�T�Z��n���) <BR>
     */
    private double estimateDeliveryAmount;
    
    /**
     * (�ϑ��萔��) <BR>
     */
    private double commissionFee;
    
    /**
     * (�ϑ��萔�������) <BR>
     */
    private double commissionFeeTax;
    
    /**
     * (�m�F���擾����) <BR>
     * �m�F���X�|���X.�m�F���P�� �ɃZ�b�g����l�i���������N�G�X�g�ł��炤�l�j���Z�b�g�B<BR>
     * �E�������擾�����ꍇ�́A�擾���������B<BR>
     * �E�������擾���Ă��Ȃ��ꍇ�́A0�i�����l<BR>
     */
    private double checkGetCurrentPrice;

    /**
     * (�T�Z��n����v�Z���ʃN���X�R���X�g���N�^�B) <BR>
     * @@roseuid 40591BDF02CE
     */
    public WEB3EquityEstimatedDeliveryPrice()
    {

    }

    /**
     * (�v�Z�P�����Z�b�g����B) <BR>
     * @@param l_dblCalculationUnitPrice - �v�Z�P�� <BR>
     * @@roseuid 40591C87007C
     */
    public void setCalcUnitPrice(double l_dblCalculationUnitPrice)
    {
        this.calculationUnitPrice = l_dblCalculationUnitPrice;
    }

    /**
     * (�v�Z�P�����擾����B) <BR>
     * @@return double
     * @@roseuid 40591CB0004D
     */
    public double getCalcUnitPrice()
    {
        return this.calculationUnitPrice;
    }

    /**
     * (�S������������Z�b�g����B) <BR>
     * @@param l_dblRestraintTurnover - �S��������� <BR>
     * @@roseuid 40591CCB02BE
     */
    public void setRestraintTurnover(double l_dblRestraintTurnover)
    {
        this.restraintTurnover = l_dblRestraintTurnover;
    }

    /**
     * (�S������������擾����B)<BR>
     * @@return double
     * @@roseuid 40591CCB02DE
     */
    public double getRestraintTurnover()
    {
        return this.restraintTurnover;
    }

    /**
     * (�T�Z��n������Z�b�g����B) <BR>
     * @@param l_dblEstimateDeliveryAmount - �T�Z��n��� <BR>
     * @@roseuid 40591CCC03B8
     */
    public void setEstimateDeliveryAmount(double l_dblEstimateDeliveryAmount)
    {
        this.estimateDeliveryAmount = l_dblEstimateDeliveryAmount;
    }

    /**
     * (�T�Z��n������擾����B) <BR>
     * @@return double
     * @@roseuid 40591CCC03C8
     */
    public double getEstimateDeliveryAmount()
    {
        return this.estimateDeliveryAmount;
    }
    /**
     * (set�ϑ��萔��) <BR>
     * @@param l_dblCommissionFee - �ϑ��萔�� <BR>
     */
    public void setCommissionFee(double l_dblCommissionFee)
    {
        this.commissionFee = l_dblCommissionFee;
    }
    
    /**
     * (get�ϑ��萔��) <BR>
     * �ϑ��萔�����擾����
     * @@return double
     */
    public double getCommissionFee()
    {
        return this.commissionFee;
    }
    /**
     * (set�ϑ��萔�������) <BR>
     * �ϑ��萔������ł��Z�b�g����
     * @@param l_dblCommissionFeeTax - �ϑ��萔������� <BR>
     */
    public void setCommissionFeeTax(double l_dblCommissionFeeTax)
    {
        this.commissionFeeTax = l_dblCommissionFeeTax;
    }
    
    /**
     * (get�ϑ��萔�������) <BR>
     * �ϑ��萔������ł��擾����
     * @@return double
     */
    public double getCommissionFeeTax()
    {
        return this.commissionFeeTax;
    }

    /**
     * (set�m�F���擾����) <BR>
     * �m�F���擾�������Z�b�g����B<BR>
     * <BR>
     * �����̊m�F���擾����==�i0, NaN�j�̏ꍇ�́A0�i�����l�j���Z�b�g����B<BR>
     * �ȊO�A�����̊m�F���擾�����̒l�����̂܂܃Z�b�g����B<BR>
     * @@param l_dblCheckGetCurrentPrice - �m�F���擾���� <BR>
     */
    public void setCheckGetCurrentPrice(double l_dblCheckGetCurrentPrice)
    {
        if (l_dblCheckGetCurrentPrice == 0 || Double.isNaN(l_dblCheckGetCurrentPrice))
        {
            this.checkGetCurrentPrice = 0;
        }
        else
        {
            this.checkGetCurrentPrice = l_dblCheckGetCurrentPrice;
        }
    }

    /**
     * (get�m�F���擾����) <BR>
     * �m�F���擾�������擾����B�i�m�F���X�|���X.�m�F���P���ݒ�p�j<BR>
     * @@return double
     */
    public String getCheckGetCurrentPrice()
    {
        return WEB3StringTypeUtility.formatNumber(this.checkGetCurrentPrice);
    }
}
@
