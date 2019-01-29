head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.35.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAccumulatedCapitalGain.java;


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

import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (�ݐϏ��n���v)<BR>
 * �ݐϏ��n���v��\������B<BR>
 */
public class WEB3TPAccumulatedCapitalGain
{

    /**
     * (�������n���v)<BR>
     */
    private double currentTermAmount;

    /**
     * (�������n���v)<BR>
     */
    private double nextMonthAmount;

    /**
     * @@roseuid 4104915801B4
     */
    public WEB3TPAccumulatedCapitalGain()
    {

    }

    /**
     * (create�ݐϏ��n���v)<BR>
     * �ݐϏ��n���v�𐶐����A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�C���X�^���X����<BR>
     * <BR>
     * �Q�j�@@�l��ݒ�<BR>
     * 	�������n���v���������n���v<BR>
     * 	�������n���v���������n���v<BR>
     * <BR>
     * �R�j�@@�C���X�^���X��ԋp<BR>
     * @@param thisTermAmount - (�������n��)
     * @@param nextMonthAmount - (�������n��)
     * @@return WEB3TPAccumulatedCapitalGain
     * @@roseuid 40EE2D5501CE
     */
    public static WEB3TPAccumulatedCapitalGain create(double l_dblCurrentTermAmount,
        double l_dblNextMonthAmount)
    {
        WEB3TPAccumulatedCapitalGain l_instance = new WEB3TPAccumulatedCapitalGain();
        l_instance.setCurrentTermAmount(l_dblCurrentTermAmount);
        l_instance.setNextMonthAmount(l_dblNextMonthAmount);
        return l_instance;
    }

    /**
     * (get�������n���v)<BR>
     * �������n���v��Ԃ��B<BR>
     * @@return double 
     * @@roseuid 40EAA94C01F6
     */
    public double getCurrentTermAmount()
    {
        return currentTermAmount;
    }

    /**
     * (set�������n���v)<BR>
     * �����𓖊����n���v�ɃZ�b�g����B<BR>
     * @@param currentTermAmount �������n���v
     * @@roseuid 40EAA951030F
     */
    public void setCurrentTermAmount(double l_dblCurrentTermAmount)
    {
        currentTermAmount = l_dblCurrentTermAmount;
    }

    /**
     * (get�������n���v)<BR>
     * �������n���v��Ԃ��B<BR>
     * @@return double
     * @@roseuid 40EE73FC0364
     */
    public double getNextMonthAmount()
    {
        return nextMonthAmount;
    }

    /**
     * (set�������n���v)<BR>
     * �����𗂌����n���v�ɃZ�b�g����B<BR>
     * @@param nextMonthAmount - (�������n��)
     * @@roseuid 40EE741802E7
     */
    public void setNextMonthAmount(double l_dblNextMonthAmount)
    {
        nextMonthAmount = l_dblNextMonthAmount;
    }

    /**
     * (get�������n��)<BR>
     * �������n����Ԃ��B<BR>
     * @@return double
     */
    public double getCurrentTermLoss()
    {
        return Math.abs(Math.min(getCurrentTermAmount(), 0.0d));
    }

    /**
     * (get�������n��)<BR>
     * �������n����Ԃ��B<BR>
     * @@return double
     */
    public double geNextMonthLoss()
    {
        return Math.abs(Math.min(getNextMonthAmount(), 0.0d));
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
            .append("currentTermAmount", this.getCurrentTermAmount())
            .append("currentTermLoss", this.getCurrentTermLoss())
            .append("nextMonthAmount", this.getNextMonthAmount())
            .append("nextMonthLoss", this.geNextMonthLoss())
            .toString();
    }

}
@
