head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.34.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPCapitalGain.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3TPAccumulatedCapitalGainTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2004/08/02 �x�� �a��(FLJ) �V�K�쐬
*/

package webbroker3.tradingpower.updtpower.cash;

import java.util.Date;

import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (���n���v)<BR>
 * ��n���̏��n���v�݌v��\������B
 */
public class WEB3TPCapitalGain
{

    /**
     * (���n���v)<BR>
     */
    private double amount;

    /**
     * (��n��)<BR>
     */
    private Date deliveryDate;

    /**
     * @@roseuid 41049158007B
     */
    public WEB3TPCapitalGain()
    {

    }

    /**
     * (create���n���v)<BR>
     * ���n���v�𐶐����A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�C���X�^���X����<BR>
     * <BR>
     * �Q�j�@@�l��ݒ�<BR>
     * 	��n������n��<BR>
     * <BR>
     * �R�j�@@�C���X�^���X��ԋp<BR>
     * @@param ��n��
     * @@return WEB3TPCapitalGain
     * @@roseuid 40EE857E0263
     */
    public static WEB3TPCapitalGain create(Date l_datDeliveryDate)
    {
        WEB3TPCapitalGain l_instance = new WEB3TPCapitalGain();
        l_instance.setDeliveryDate(l_datDeliveryDate);
        return l_instance;
    }

    /**
     * (get���n���v)<BR>
     * ���n���v�i�݌v�j�z��Ԃ��B<BR>
     * @@return double
     * @@roseuid 40EE75050019
     */
    public double getAmount()
    {
        return amount;
    }

    /**
     * (set���n���v)<BR>
     * ���������n���v�i�݌v�j�z�ɃZ�b�g����B<BR>
     * @@param l_dblAmount - (���n�v�ݐ�)
     * @@roseuid 40EE750B01FD
     */
    public void setAmount(double l_dblAmount)
    {
        amount = l_dblAmount;

    }

    /**
     * (get��n��)<BR>
     * ��n����Ԃ��B<BR>
     * @@return Date
     * @@roseuid 40EE74E903E1
     */
    public Date getDeliveryDate()
    {
        return deliveryDate;
    }

    /**
     * (set��n��)<BR>
     * ��������n���ɃZ�b�g����B<BR>
     * @@param deliveryDate - (��n��)
     * @@roseuid 40EE74EF0335
     */
    public void setDeliveryDate(Date l_datDeliveryDate)
    {
        deliveryDate = l_datDeliveryDate;
    }

    /**
     * (add���n���v)<BR>
     * ���n���v�z�Ɉ����̒l�����Z����B<BR>
     * @@param amount - (���n�v���z)
     * @@roseuid 40EE85E503BA
     */
    public void addAmount(double l_amount)
    {
        amount += l_amount;
    }

    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B
     * 
     * @@return String
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .append("deliveryDate", this.getDeliveryDate())
            .append("amount", this.getAmount())
            .toString();
    }

}
@
