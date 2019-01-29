head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.35.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPDayInterestAdjustmentRestraintReflector.java;


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

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����S����)<BR>
 * �����S������\������B<BR>
 */
public class WEB3TPDayInterestAdjustmentRestraintReflector
    extends WEB3TPRestraintReflector
{

    /**
     * (��n��������̕ԍϒ�������)<BR>
     */
    private int transactionCount;

    /**
     * (���������z)<BR>
     */
    private double adjustment;
    
    /** ���O�@@���[�e�B���e�B�@@*/
    private static final WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3TPDayInterestAdjustmentRestraintReflector.class);

    /**
     * @@roseuid 4104BDB00068
     */
    public WEB3TPDayInterestAdjustmentRestraintReflector()
    {

    }

    /**
     * (create�����S����)<BR>
     * �����S�����𐶐����A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�C���X�^���X����<BR>
     * <BR>
     * �Q�j�@@�l��ݒ�<BR>
     * 	��������			��  ��������<BR>
     * 	���������z        ���@@���������z<BR>
     * 	�S����				���@@calc�����S����(��n��)<BR>
     * 	�ϓ����f�J�n���A�ϓ����f�I�����ݒ�Fcalc�ϓ����f��(��n��)<BR>
     * <BR>
     * �R�j�@@�C���X�^���X��ԋp<BR>
     * <BR>
     * @@param l_intTransactionCount - (��������)
     * @@param l_dblAdjustment - (���������z)
     * @@param l_datDeliveryDate - (��n��)
     * @@return WEB3TPDayInterestRestraintReflector
     * @@roseuid 40D81603031C
     */
    public static WEB3TPDayInterestAdjustmentRestraintReflector create(
        WEB3TPCalcCondition l_calcCondition, int l_intTransactionCount,
        double l_dblAdjustment, Date l_datDeliveryDate)
    {
        WEB3TPDayInterestAdjustmentRestraintReflector l_instance = new
            WEB3TPDayInterestAdjustmentRestraintReflector();
        l_instance.setCalcCondition(l_calcCondition);
        l_instance.setTransactionCount(l_intTransactionCount);
        l_instance.setAdjustment(l_dblAdjustment);
        l_instance.setAmount(l_instance.calcDayInterestRestraint(l_datDeliveryDate));
        l_instance.calcReflectDay(l_datDeliveryDate);
        return l_instance;

    }

    /**
     * (get��������)<BR>
     * �ԍϒ���������Ԃ��B<BR>
     * @@return int
     * @@roseuid 40E00D990002
     */
    public int getTransactionCount()
    {
        return transactionCount;
    }

    /**
     * (set��������)<BR>
     * ������ԍϒ��������ɃZ�b�g����B<BR>
     * @@param l_intTransactionCount - (��������)
     * @@roseuid 40E00DA10282
     */
    public void setTransactionCount(int l_intTransactionCount)
    {
        transactionCount = l_intTransactionCount;

    }

    /**
     * (get���������z)<BR>
     * ���������z��Ԃ��B<BR>
     * @@return double
     * @@roseuid 40C5A6CC0128
     */
    public double getAdjustment()
    {
        return adjustment;
    }

    /**
     * (set���������z)<BR>
     * ��������������z�ɃZ�b�g����B<BR>
     * @@param l_dblAdjustment - ���������z
     * @@roseuid 40C5A6D40195
     */
    public void setAdjustment(double l_dblAdjustment)
    {
        adjustment = l_dblAdjustment;
    }

    /**
     * (calc�����S����)<BR>
     * ��n��������̓����S�������Z�o����B<BR>
     * �ԍϒ���1���ɑ΂��ē��������z�����̂�<BR>
     * �����������������킹�����̂���n��������̓����S�����ƂȂ�B<BR>
     * <BR>
     * �P�j�@@�����S�������v�Z����B<BR>
     * �@@�����S����(n)�@@= �ԍϒ�����(n) �~ ���������z<BR>
     * <BR>
     * �Q�j�@@�����S����(n)��ԋp����B<BR>
     * <BR>
     * @@param l_datDate�@@(�w���)
     * @@return double
     * @@roseuid 40DA30D0004D
     */
    public double calcDayInterestRestraint(Date l_datDate)
    {
        double l_dblAmount = transactionCount * adjustment;
        return l_dblAmount;
    }

    /**
     * (calc�ϓ����f��)<BR>
     * �ϓ����f�J�n���A�ϓ����f�I�������ȉ��̂悤�ɃZ�b�g����B<BR>
     * <BR>
     * �ϓ����f�J�n��������.��n��<BR>
     * �ϓ����f�I�������c�Ɠ�[5]<BR>
     * @@param l_datDeliveryDate - (��n��)
     * @@roseuid 40ECA12E01A5
     */
    public void calcReflectDay(Date l_datDeliveryDate)
    {
        final String STR_METHOD_NAME = "calcReflectDay(Date l_datDeliveryDate)";
        if (l_datDeliveryDate == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        setReflectStartDay(l_datDeliveryDate);
        setReflectEndDay(getCalcCondition().getBizDate(5));

    }

    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B<BR>
     * 
     * @@return String 
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .append("transactionCount", this.getTransactionCount())
            .append("adjustment", this.getAdjustment())
            .appendSuper(super.toString())
            .toString();
    }
}
@
