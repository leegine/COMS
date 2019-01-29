head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.04.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPContractBase.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ʊ�{���(WEB3TPContractBase.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/02 �V���@@�h�O (FLJ) �V�K�쐬
*/
package webbroker3.tradingpower.updtpower.contract;

import webbroker3.tradingpower.util.ToStringUtils;


/**
 * (���ʊ�{���)
 */
public abstract class WEB3TPContractBase 
{
    
    /**
     * (���ʑ��)
     */
    private double contractAmount;
    
    /**
     * (�K�v�ۏ؋�)
     */
    private double marginDeposit;
    
    /**
     * (�����K�v�ۏ؋�)
     */
    private double cashMarginDeposit;
    
    /**
     * (get���ʑ��)<BR>
     * ���ʑ�����擾����B<BR>
     * @@return double
     * @@roseuid 40DA7E190296
     */
    public double getContractAmount() 
    {
        return contractAmount;
    }
    
    /**
     * (set���ʑ��)<BR>
     * �����̌��ʑ�����Z�b�g����B<BR>
     * @@param l_dblContractAmount - (���ʑ��)
     * @@roseuid 40DA7E1902E4
     */
    public void setContractAmount(double l_dblContractAmount) 
    {
        contractAmount = l_dblContractAmount;
    }
    
    /**
     * (get�K�v�ۏ؋�)<BR>
     * �K�v�ۏ؋����擾����B<BR>
      * @@return double
     * @@roseuid 40DA7E1902A6
     */
    public double getMarginDeposit() 
    {
        return marginDeposit;
    }
    
    /**
     * (set�K�v�ۏ؋�)<BR>
     * �����̕K�v�ۏ؋����Z�b�g����B<BR>
     * @@param l_dblMarginDeposit - (�K�v�ۏ؋�)
     * @@roseuid 40DA7E190303
     */
    public void setMarginDeposit(double l_dblMarginDeposit) 
    {
        marginDeposit = l_dblMarginDeposit;
    }
    
    /**
     * (get�����K�v�ۏ؋�)<BR>
     * �����K�v�ۏ؋����擾����B<BR>
      * @@return double
     * @@roseuid 40DA7E1902C5
     */
    public double getCashMarginDeposit() 
    {
        return cashMarginDeposit;
    }
    
    /**
     * (set�����K�v�ۏ؋�)<BR>
     * �����̌����K�v�ۏ؋����Z�b�g����B<BR>
     * @@param l_dblCashMarginDeposit - (�����K�v�ۏ؋�)
     * @@roseuid 40DA7E190313
     */
    public void setCashMarginDeposit(double l_dblCashMarginDeposit) 
    {
        cashMarginDeposit = l_dblCashMarginDeposit;
    }

    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .append("contractAmount", getContractAmount())
            .append("marginDeposit", getMarginDeposit())
            .append("cashMarginDeposit", getCashMarginDeposit())
            .toString();
    }
}
@
