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
filename	WEB3EquityReceiveChangeSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������ʒm���e(WEB3EquityReceiveChangeSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/19 ����� (���u) �V�K�쐬
Revesion History : 2004/12/29 �����a�� (SRA) �p�����[�^�����C��
Revesion History : 2005/01/05 �����a�� (SRA) JavaDoc�C��
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;

/**
 * �i���������ʒm���e�j�B<BR>
 * <BR>
 * ���������ʒm���e�B<BR>
 * ������������ʒm�T�[�r�X�ɂĎg�p����B
 * @@version 1.0
 */
public class WEB3EquityReceiveChangeSpec
{

    /**
     * (�����㎷�s����)<BR>
     * �y������������ʒm�L���[�e�[�u���z�����㎷�s�������A<BR>
     * xTrade��EqTypeExecutionConditionType�ɕϊ������l<BR>
     */
    private EqTypeExecutionConditionType changeAfterExecCondType;

    /**
     * (�����P��)<BR>
     */
    private double limitPrice;

    /**
     * (�T�Z��n���)<BR>
     */
    private double estimateDeliveryAmount;

    /**
     * (������l�i����)�B<BR>
     * ������̒l�i�����B�iWEB�V�̃R�[�h�̌n�j<BR>
     */
    private String changeAfterPriceConditionType;

    /**
     * (�����㒍��Rev,)<BR>
     * ������̒���Rev.�B<BR>
     */
    private String changeAfterOrderRev;
    
    /**
     * (���������ʒm���e)<BR>
     * �R���X�g���N�^<BR>
     * @@return webbroker3.equity.WEB3EquityReceiveChangeSpec<BR>
     * @@roseuid 40457CE002EE<BR>
     */
    public WEB3EquityReceiveChangeSpec()
    {

    }

    /**
     * (set�����㎷�s����)<BR>
     * @@param l_changeAfterExecCondType (�����㎷�s����)<BR>
     * �����㎷�s����<BR>
     * @@roseuid 40457A8E0158<BR>
     */
    public void setChangeAfterExecCondType(EqTypeExecutionConditionType l_changeAfterExecCondType)
    {
        this.changeAfterExecCondType = l_changeAfterExecCondType;
    }

    /**
     * (get�����㎷�s����)<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType<BR>
     * @@roseuid 40457A8E0168<BR>
     */
    public EqTypeExecutionConditionType getChangeAfterExecCondType()
    {
        return this.changeAfterExecCondType;
    }

    /**
     * (set�T�Z��n���)<BR>
     * �T�Z��n������Z�b�g����B<BR>
     * @@param l_dblEstimatedPrice (�T�Z���z)<BR>
     * @@roseuid 40457AC403D9<BR>
     */
    public void setEstimateDeliveryAmount(double l_dblEstimatedPrice)
    {
        this.estimateDeliveryAmount = l_dblEstimatedPrice;
    }

    /**
     * (get�T�Z��n���)<BR>
     * �T�Z��n������擾����B<BR>
     * @@return double<BR>
     * @@roseuid 40457AC50000<BR>
     */
    public double getEstimateDeliveryAmount()
    {
        return this.estimateDeliveryAmount;
    }

    /**
     * (set�����P��)<BR>
     * �����P�����Z�b�g����B<BR>
     * @@param l_dblLimitPrice (�����P��)<BR>
     * @@roseuid 40457AC50001<BR>
     */
    public void setLimitPrice(double l_dblLimitPrice)
    {
        this.limitPrice = l_dblLimitPrice;
    }

    /**
     * (get�����P��)<BR>
     * �����P�����擾����B<BR>
     * @@return String<BR>
     * @@roseuid 40457AC50010<BR>
     */
    public double getLimitPrice()
    {
        return this.limitPrice;
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
     * (set�����㒍��Rev)<BR>
     * ������̒���Rev���Z�b�g����B
     * @@param l_strChangeAfterOrderRev - (�����㒍��Rev)<BR>
     * �����㒍��Rev
     */
    public void setChangeAfterOrderRev(String l_strChangeAfterOrderRev)
    {
        this.changeAfterOrderRev = l_strChangeAfterOrderRev;
    }
    
    /**
     * (get�����㒍��Rev)<BR>
     * �����㒍��Rev���擾����B
     * @@return String
     */
    public String getChangeAfterOrderRev()
    {
        return this.changeAfterOrderRev;
    }
}
@
