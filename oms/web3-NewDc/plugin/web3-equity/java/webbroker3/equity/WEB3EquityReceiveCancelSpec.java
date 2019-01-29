head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityReceiveCancelSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������ʒm���e(WEB3EquityReceiveCancelSepc.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/03 ���j (���u) �V�K�쐬
Revesion History : 2004/12/29 �����a�� (SRA) �p�����[�^�����C��
Revesion History : 2005/01/05 �����a�� (SRA) JavaDoc�C��
                   2006/11/28 �����F(���u) ���f�� 1065
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;

/**
 * �i��������ʒm���e�j�B<BR>
 * <BR>
 * ��������ʒm���e�B<BR>
 * ������������ʒm�T�[�r�X�ɂĎg�p����B
 * @@version 1.0
 */
public class WEB3EquityReceiveCancelSpec
{

    /**
     * (�����㎷�s����)<BR>
     * <BR>
     * �y������������ʒm�L���[�e�[�u���z�����㎷�s�������A<BR>
     * xTrade��EqTypeExecutionConditionType�ɕϊ������l<BR>
     */
    private EqTypeExecutionConditionType changeAfterExecCond;

    /**
     * (������l�i����)�B<BR>
     * ������̒l�i�����B�iWEB�V�̃R�[�h�̌n�j<BR>
     */
    private String changeAfterPriceConditionType;

    /**
     * (��n���)�B<BR>
     * ��n����B<BR>
     */
    private double estimatedPrice;

    /**
     * (�����P��)<BR>
     * �����P��<BR>
     */
    private double limitPrice;

    /**
     * (��������ʒm���e)<BR>
     * <BR>
     * �R���X�g���N�^<BR>
     * @@return �����iweb3-equity�j.�����E���G���e�B�e�B.��������ʒm���e)
     * @@roseuid 404583180290
     */
    public WEB3EquityReceiveCancelSpec()
    {

    }

    /**
     * (set�����㎷�s����)<BR>
     * @@param l_executionConditionType �����㎷�s����
     * @@roseuid 4045830C0000
     */
    public void setChangeAfterExecCond(EqTypeExecutionConditionType l_executionConditionType)
    {
        this.changeAfterExecCond = l_executionConditionType;
    }

    /**
     * (get�����㎷�s����)<BR>
     * @@return EqTypeExecutionConditionType
     * @@roseuid 4045830C0002
     */
    public EqTypeExecutionConditionType getChangeAfterExecCond()
    {
        return this.changeAfterExecCond;
    }

    /**
     * (set������l�i����)<BR>
     * ������̒l�i�������Z�b�g����B<BR>
     * @@param l_strChangeAfterPriceConditionType ������l�i����
     */
    public void setChangeAfterPriceConditionType(String l_strChangeAfterPriceConditionType) 
    {
        this.changeAfterPriceConditionType = l_strChangeAfterPriceConditionType;
    }
    
    /**
     * (get������l�i����)<BR>
     * ������l�i�������擾����B<BR>
     * @@return String
     */
    public String getChangeAfterPriceConditionType() 
    {
        return this.changeAfterPriceConditionType;
    }
    
    /**
     * (set��n���)<BR>
     * ��n������Z�b�g����B<BR>
     * @@param l_dblEstimatedPrice - (��n���)<BR>
     * ��n���
     */
    public void setEstimatedPrice(double l_dblEstimatedPrice)
    {
        this.estimatedPrice = l_dblEstimatedPrice;
    }
    
    /**
     * (get��n���)<BR>
     * ��n������擾����B<BR>
     * @@return ��n���
     */
    public double getEstimatedPrice()
    {
        return this.estimatedPrice;
    }

    /**
     * (get�����P��)<BR>
     * �����P�����擾����B<BR>
     * @@return double
     */
    public double getLimitPrice()
    {
        return this.limitPrice;
    }

    /**
     * (set�����P��)<BR>
     * �����P�����Z�b�g����B<BR>
     * @@param l_dblLmitPrice - (�����P��)<BR>
     */
    public void setLimitPrice(double l_dblLmitPrice)
    {
        this.limitPrice = l_dblLmitPrice;
    }
}
@
