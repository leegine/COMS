head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityRealizedProfitAndLossPrice.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�Z���ϑ��v����v�Z����(WEB3EquityRealizedProfitAndLossPrice.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/27 ���Ō� �V�K�쐬
Revesion History : 2005/01/05 �����a��(SRA) JavaDoc�C��
Revesion History : 2006/11/01 ������@@(���u)���f��No.1026
*/
package webbroker3.equity;


/**
 * �i�T�Z���ϑ��v����v�Z���ʁj�B<BR>
 * <BR>
 * �g�����������}�l�[�W��.calc�T�Z���ϑ��v���()�̖߂�l��\������f�[�^�N���X�B
 * @@version 1.0
 */
public class WEB3EquityRealizedProfitAndLossPrice implements WEB3EquityEstimatedPrice
{
    
    /**
     * (�v�Z�P��)<BR>
     * �@@�E�w�l�����̏ꍇ�͎w�l�B<BR>
     * �@@�E���s�����̏ꍇ�͎����B<BR>
     */
    private double calcUnitPrice;
    
    /**
     * (�������)
     */
    private double turnover;
    
    /**
     * (�T�Z���ϑ��v���)
     */
    private double estimatedRealizedProfitAndLossAmount;
    
    /**
     * (�T�Z���ϑ��v����v�Z����)<BR>
     * �T�Z���ϑ��v����v�Z���ʃN���X�R���X�g���N�^�B
     * @@return webbroker3.margin.WEB3EquityEstimatedCloseIncomeAmountDeliveryPrice
     * @@roseuid 40B473B30399
     */
    public WEB3EquityRealizedProfitAndLossPrice() 
    {
     
    }
    
    /**
     * (set�v�Z�P��)<BR>
     * �v�Z�P�����Z�b�g����B
     * @@param l_dblCalcUnitPrice - �v�Z�P��
     * @@roseuid 40B473B3039A
     */
    public void setCalcUnitPrice(double l_dblCalcUnitPrice) 
    {
        this.calcUnitPrice = l_dblCalcUnitPrice;
    }
    
    /**
     * (get�v�Z�P��)<BR>
     * �v�Z�P�����擾����B
     * @@return double
     * @@roseuid 40B473B3039C
     */
    public double getCalcUnitPrice() 
    {
        return this.calcUnitPrice;
    }
    
    /**
     * (set�������)<BR>
     * ����������Z�b�g����B
     * @@param l_dblRestraintTurnover - �S���������
     * @@roseuid 40B6C39802ED
     */
    public void setTurnover(double l_dblRestraintTurnover) 
    {
        this.turnover = l_dblRestraintTurnover;
    }
    
    /**
     * (get�������)<BR>
     * ����������擾����B
     * @@return double
     * @@roseuid 40B6C39802EF
     */
    public double getTurnover() 
    {
        return this.turnover;
    }
    
    /**
     * ( set�T�Z���ϑ��v���)<BR>
     * �T�Z���ϑ��v������Z�b�g����B
     * @@param l_dblEstimatedCloseIncomeAmount - �T�Z���ϑ��v���
     * @@roseuid 40B473B303A8
     */
    public void setEstimatedRealizedProfitAndLossAmount(double l_dblEstimatedCloseIncomeAmount) 
    {
        this.estimatedRealizedProfitAndLossAmount = l_dblEstimatedCloseIncomeAmount;
    }
    
    /**
     * (get�T�Z���ϑ��v���)<BR>
     * �T�Z���ϑ��v������擾����B
     * @@return double
     * @@roseuid 40B473B303AA
     */
    public double getEstimatedRealizedProfitAndLossAmount() 
    {
        return this.estimatedRealizedProfitAndLossAmount;
    }
    
    /**
     * (get�T�Z��n���)<BR>
     * this.get�T�Z���ϑ��v���()�ɏ������Ϗ�����B<BR>
     * @@return double
     */
    public double getEstimateDeliveryAmount()
    {
        return this.getEstimatedRealizedProfitAndLossAmount();
    }
}
@
