head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqForeignCost.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���n���o��(WEB3FeqForeignCost.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/15 ���I (���u) �V�K�쐬
                 : 2005/07/26 ���U(���u) ���r���[
*/

package webbroker3.feq;


/**
 * (���n���o��) <BR>
 * ���n���o�� <BR>
 *  <BR>
 * @@ author ���I <BR>
 * @@ version 1.0 <BR>
 */
public class WEB3FeqForeignCost 
{
    
    /**
     * (���n�萔��) <BR>
     * ���n�萔��
     */
    private double foreignCommissionFee = 0;
    
    /**
     * (���n�����) <BR>
     * ���n�����
     */
    private double foreignTax = 0;
    
    /**
     * (���̑��R�X�g�P) <BR>
     * ���̑��R�X�g�P
     */
    private double foreignFeeExt1 = 0;
    
    /**
     * (���̑��R�X�g�Q) <BR>
     * ���̑��R�X�g�Q
     */
    private double foreignFeeExt2 = 0;
    
    /**
     * (���n���Z���) <BR>
     * ���n���Z���
     */
    private double balanceAmountFc = 0;
    
    /**
     * @@roseuid 42CE39E70222
     */
    public WEB3FeqForeignCost() 
    {
     
    }
    
    /**
     * (get���n�萔��) <BR>
     * ���n�萔�����擾����B<BR>
     * <BR>
     * this.���n�萔����ԋp����B<BR>
     * @@return double
     * @@roseuid 4282E7E6008F
     */
    public double getForeignCommissionFee() 
    {
        return foreignCommissionFee;
    }
    
    /**
     * (get���n�����) <BR>
     * ���n����ł��擾����B <BR>
     *  <BR>
     * this.���n����ł�ԋp����B <BR>
     * @@return double
     * @@roseuid 4282E80203DB
     */
    public double getForeignTax() 
    {
        return foreignTax;
    }
    
    /**
     * (get���̑��R�X�g�P) <BR>
     * ���̑��R�X�g�P���擾����B <BR>
     *  <BR>
     * this.���̑��R�X�g�P��ԋp����B <BR>
     * @@return double
     * @@roseuid 4282E811015A
     */
    public double getForeignFeeExt1() 
    {
        return foreignFeeExt1;
    }
    
    /**
     * (get���̑��R�X�g�Q) <BR>
     * ���̑��R�X�g�Q���擾����B <BR>
     *  <BR>
     * this.���̑��R�X�g�Q��ԋp����B <BR>
     * @@return double
     * @@roseuid 4282E82001B8
     */
    public double getForeignFeeExt2() 
    {
        return foreignFeeExt2;
    }
    
    /**
     * (get���n���Z���) <BR>
     * ���n���Z������擾����B <BR>
     *  <BR>
     * this.���n���Z�����ԋp����B <BR>
     * @@return double
     * @@roseuid 4283020702C2
     */
    public double getBalanceAmountFc() 
    {
        return balanceAmountFc;
    }
    
    /**
     * (set���n�萔��) <BR>
     * ���n�萔�����Z�b�g����B <BR>
     *  <BR>
     * this.���n�萔���Ɍ��n�萔�����Z�b�g����B <BR>
     * @@param l_dblForeignCommisionFee - (���n�萔��)
     * @@roseuid 4282E82A015A
     */
    public void setForeignCommissionFee(double l_dblForeignCommisionFee) 
    {
        foreignCommissionFee = l_dblForeignCommisionFee;
    }
    
    /**
     * (set���n�����) <BR>
     * ���n����ł��Z�b�g����B <BR>
     *  <BR>
     * this.���n����łɌ��n����ł��Z�b�g����B <BR>
     * @@param l_dblForeignTax - ���n�����
     * @@roseuid 4282E854038D
     */
    public void setForeignTax(double l_dblForeignTax) 
    {
        foreignTax = l_dblForeignTax; 
    }
    
    /**
     * (set���̑��R�X�g�P) <BR>
     * ���̑��R�X�g�P���Z�b�g����B <BR>
     *  <BR>
     * this.���̑��R�X�g�P�ɂ��̑��R�X�g�P���Z�b�g����B <BR>
     * @@param l_dblForeignFeeExt1 - ���̑��R�X�g�P
     * @@roseuid 4282E8700003
     */
    public void setForeignFeeExt1(double l_dblForeignFeeExt1) 
    {
        foreignFeeExt1 = l_dblForeignFeeExt1; 
    }
    
    /**
     * (set���̑��R�X�g�Q) <BR>
     * ���̑��R�X�g�Q���Z�b�g����B <BR>
     *  <BR>
     * this.���̑��R�X�g�Q�ɂ��̑��R�X�g�Q���Z�b�g����B <BR>
     * @@param l_dblForeignFeeExt2 - ���̑��R�X�g�Q
     * @@roseuid 4282E87000CE
     */
    public void setForeignFeeExt2(double l_dblForeignFeeExt2) 
    {
        foreignFeeExt2 = l_dblForeignFeeExt2;
    }
    
    /**
     * (set���n���Z���) <BR>
     * ���n���Z������Z�b�g����B <BR>
     *  <BR>
     * this.���n���Z����Ɍ��n���Z������Z�b�g����B <BR>
     * @@param l_dblBalanceAmountFc - (���n���Z���)
     * @@roseuid 4283022F03AC
     */
    public void setBalanceAmountFc(double l_dblBalanceAmountFc) 
    {
        balanceAmountFc = l_dblBalanceAmountFc; 
    }
}
@
