head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityEstimatedContractPrice.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�Z������v�Z����(WEB3EquityEstimatedContractPrice.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/11/01 ������@@�V�K�쐬 �i���f���jNo.946,No.1026
*/

package webbroker3.equity;

import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�T�Z������v�Z����)<BR>
 * �g�����������}�l�[�W��.calc�����������()�̖߂�l��\������f�[�^�N���X�B<BR>
 * 
 * @@author ������
 * @@version 1.0
 */
public class WEB3EquityEstimatedContractPrice implements WEB3EquityEstimatedPrice
{

    /**
     * (�v�Z�P��) <BR>
     * �v�Z�P�� <BR>
     * (�S�����z�v�Z�Ɏg�p�����P���j<BR>
     */
    private double calculationUnitPrice;

    /**
     * (�T�Z�����)<BR>
     * �T�Z�����<BR>
     */
    private double estimatedContractPrice;

    /**
     * (�m�F���擾����)<BR>
     * �m�F���X�|���X.�m�F���P�� �ɃZ�b�g����l�i���������N�G�X�g�ł��炤�l�j���Z�b�g�B<BR>
     * �E�������擾�����ꍇ�́A�擾���������B<BR>
     * �E�������擾���Ă��Ȃ��ꍇ�́A0�i�����l�j�B<BR>
     */
    private double checkGetCurrentPrice;

    /**
     * (�T�Z������v�Z����) <BR>
     * �T�Z������v�Z���ʃN���X�R���X�g���N�^�B<BR>
     */
    public WEB3EquityEstimatedContractPrice()
    {

    }

    /**
     * (set�v�Z�P��) <BR>
     * �v�Z�P�����Z�b�g����B<BR>
     * @@param l_dblCalculationUnitPrice - �v�Z�P�� <BR>
     */
    public void setCalcUnitPrice(double l_dblCalculationUnitPrice)
    {
        this.calculationUnitPrice = l_dblCalculationUnitPrice;
    }

    /**
     * (get�v�Z�P��) <BR>
     * �v�Z�P�����擾����B<BR>
     * @@return double
     */
    public double getCalcUnitPrice()
    {
        return this.calculationUnitPrice;
    }

    /**
     * (set�T�Z�����) <BR>
     * �T�Z��������Z�b�g����B<BR>
     * @@param l_dblEstimatedContractPrice -  �T�Z����� <BR>
     */
    public void setEstimatedContractPrice(double l_dblEstimatedContractPrice)
    {
        this.estimatedContractPrice = l_dblEstimatedContractPrice;
    }

    /**
     * (get�T�Z�����) <BR>
     * �T�Z��������擾����B<BR>
     * @@return double
     */
    public double getEstimatedContractPrice()
    {
        return this.estimatedContractPrice;
    }

    /**
     * (set�m�F���擾���� ) <BR>
     * �m�F���擾�������Z�b�g����B<BR>
     * <BR>
     * �����̊m�F���擾����==�i0, NaN�j�̏ꍇ�́A0�i�����l�j���Z�b�g����B<BR>
     * �ȊO�A�����̊m�F���擾�����̒l�����̂܂܃Z�b�g����B<BR>
     * @@param l_dblCheckGetCurrentPrice -  �m�F���擾���� <BR>
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
     * @@return String
     */
    public String getCheckGetCurrentPrice()
    {
        return WEB3StringTypeUtility.formatNumber(this.checkGetCurrentPrice);
    }

    /**
     * (get�T�Z��n���) <BR>
     * this.get�T�Z�����()�ɏ������Ϗ�����B<BR>
     * @@return double
     */
    public double getEstimateDeliveryAmount()
    {
        return this.getEstimatedContractPrice();
    }
}
@
