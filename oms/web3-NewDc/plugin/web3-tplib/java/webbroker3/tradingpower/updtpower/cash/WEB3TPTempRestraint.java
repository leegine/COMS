head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.34.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTempRestraint.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���S����(WEB3TPTempRestraint.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/11/05 �И���(���u) �V�K�쐬 ���f��No.219�ANo.220
*/
package webbroker3.tradingpower.updtpower.cash;

import java.util.Date;

import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (���S����)<BR>
 * ���S����<BR>
 * @@author �И���(���u)
 * @@version 1.0
 */
public class WEB3TPTempRestraint
    extends WEB3TPRestraintReflector
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTempRestraint.class);

    /**
     * (�g�����U�N�V����������)<BR>
     */
    private Date transactionDate;

    /**
     * (��n��)<BR>
     */
    private Date deliveryDate;

    /**
     * (�S�������)<BR>
     */
    private String restraintDiv;

    /**
     * (�f�t�H���g�R���X�g���N�^)<BR>
     */
    public WEB3TPTempRestraint()
    {

    }

    /**
     * (calc�ϓ����f��)<BR>
     * <BR>
     * �ϓ����f�J�n���A�ϓ����f�I�������Z�b�g����B<BR>
     * <BR>
     * �P�j�ϓ����f�J�n�����Z�b�g����B<BR>
     * <BR>
     * �@@�@@[a.����.��n�� < T+0 �̏ꍇ]<BR>
     * �@@�@@�@@�|this.set�ϓ����f�J�n��(:Date = T+0)<BR>
     * <BR>
     * �@@�@@[b.����.��n�� > T+5 �̏ꍇ]<BR>
     * �@@�@@�@@�|this.set�ϓ����f�J�n��(:Date = T+5)<BR>
     * <BR>
     * �@@�@@[c.���̑��̏ꍇ]<BR>
     * �@@�@@�@@�|this.set�ϓ����f�J�n��(:Date = ����.��n��)<BR>
     * <BR>
     * �Q�j�ϓ����f�I�������Z�b�g����B<BR>
     * <BR>
     * �@@�@@�|this.set�ϓ����f�I����(:Date = T+5)<BR>
     * <BR>
     * ��T+0 = this.get�]�͌v�Z����().get�c�Ɠ�(:int = 0)<BR>
     * ��T+5 = this.get�]�͌v�Z����().get�c�Ɠ�(:int = 5)<BR>
     * <BR>
     * @@param l_datDeliveryDate - (��n��)
     */
    public void calcReflectDay(Date l_datDeliveryDate)
    {
        final String STR_METHOD_NAME = "calcReflectDay(Date)";
        log.entering(STR_METHOD_NAME);

        //T+0 = this.get�]�͌v�Z����().get�c�Ɠ�(:int = 0)
        Date l_datBizDateT0 = this.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);
        //T+5 = this.get�]�͌v�Z����().get�c�Ɠ�(:int = 5)
        Date l_datBizDateT5 = this.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5);

        //�ϓ����f�J�n��
        //����.��n�� < T+0 �̏ꍇ
        if (WEB3DateUtility.compareToDay(l_datDeliveryDate, l_datBizDateT0) < 0)
        {
            //this.set�ϓ����f�J�n��(:Date = T+0)
            this.setReflectStartDay(l_datBizDateT0);
        }
        //����.��n�� > T+5 �̏ꍇ
        else if (WEB3DateUtility.compareToDay(l_datDeliveryDate, l_datBizDateT5) > 0)
        {
            //this.set�ϓ����f�J�n��(:Date = T+5)
            this.setReflectStartDay(l_datBizDateT5);
        }
        //���̑��̏ꍇ
        else
        {
            //this.set�ϓ����f�J�n��(:Date = ����.��n��)
            this.setReflectStartDay(l_datDeliveryDate);
        }

        //�ϓ����f�I����
        //this.set�ϓ����f�I����(:Date = T+5)
        this.setReflectEndDay(l_datBizDateT5);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create���S����)<BR>
     * (static)(create���S����)<BR>
     * <BR>
     * ���S�������쐬���A�ԋp����B<BR>
     * <BR>
     * 1)���S�����C���X�^���X�𐶐�����B<BR>
     * �@@-�f�t�H���g�R���X�g���N�^���R�[��<BR>
     * <BR>
     * 2)�����������S�����C���X�^���X�̑����ɒl���Z�b�g<BR>
     * <BR>
     * �@@�|this.set�]�͌v�Z����(:�]�͌v�Z���� = ����.�]�͌v�Z����)<BR>
     * �@@�|this.set�S����(:double = ����.�S����)<BR>
     * �@@�|this.set�g�����U�N�V����������(Date = ����.�g�����U�N�V����������)<BR>
     * �@@�|this.calc�ϓ����f��(:Date = ����.��n��)<BR>
     * �@@�|this.set�S�������(String = ����.�S�������)<BR>
     * <BR>
     * 3)���S�����C���X�^���X��ԋp����B<BR>
     * <BR>
     * @@param l_calcCondition - (�]�͌v�Z����)<BR>
     * @@param l_dbRestraint - (�S����)<BR>
     * @@param l_datTransactionDate - (�g�����U�N�V����������)<BR>
     * @@param l_datDeliveryDate - (��n��)<BR>
     * @@param l_strRestraintDiv - (�S�������)<BR>
     * @@return WEB3TPTempRestraint
     */
    public static WEB3TPTempRestraint createTempRestraint(
        WEB3TPCalcCondition l_calcCondition,
        double l_dbRestraint,
        Date l_datTransactionDate,
        Date l_datDeliveryDate,
        String l_strRestraintDiv)
    {
        final String STR_METHOD_NAME = "createTempRestraint(" +
            "WEB3TPCalcCondition, double, Date, Date, String)";
        log.entering(STR_METHOD_NAME);

        //���S�����C���X�^���X�𐶐�����B
        WEB3TPTempRestraint l_temp = new WEB3TPTempRestraint();

        //this.set�]�͌v�Z����(:�]�͌v�Z���� = ����.�]�͌v�Z����)
        l_temp.setCalcCondition(l_calcCondition);
        //this.set�S����(:double = ����.�S����)
        l_temp.setAmount(l_dbRestraint);
        //this.set�g�����U�N�V����������(Date = ����.�g�����U�N�V����������)
        l_temp.setTransactionDate(l_datTransactionDate);
        //this.calc�ϓ����f��(:Date = ����.��n��)
        l_temp.calcReflectDay(l_datDeliveryDate);
        //this.set�S�������(String = ����.�S�������)
        l_temp.setRestraintDiv(l_strRestraintDiv);

        //���S�����C���X�^���X��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_temp;
    }

    /**
     * (get�g�����U�N�V����������)<BR>
     * <BR>
     * this.�g�����U�N�V������������ԋp����B<BR>
     * @@return Date
     */
    public Date getTransactionDate()
    {
        return this.transactionDate;
    }

    /**
     * (set�g�����U�N�V����������)<BR>
     * <BR>
     * ����.�g�����U�N�V�������������Athis.�g�����U�N�V�����������ɃZ�b�g����B<BR>
     * @@param l_datTransactionDate - (�g�����U�N�V����������)<BR>
     */
    public void setTransactionDate(Date l_datTransactionDate)
    {
        this.transactionDate = l_datTransactionDate;
    }

    /**
     * (get��n��)<BR>
     * <BR>
     * this.��n����ԋp����B<BR>
     * @@return Date
     */
    public Date getDeliveryDate()
    {
        return this.deliveryDate;
    }

    /**
     * (set��n��)<BR>
     * <BR>
     * ����.��n�����Athis.��n���ɃZ�b�g����B<BR>
     * @@param l_datDeliveryDate - (��n��)<BR>
     */
    public void setDeliveryDate(Date l_datDeliveryDate)
    {
        this.deliveryDate = l_datDeliveryDate;
    }

    /**
     * (get�S�������)<BR>
     * <BR>
     * this.�S������ʂ�ԋp����B<BR>
     * @@return String
     */
    public String getRestraintDiv()
    {
        return this.restraintDiv;
    }

    /**
     * (set�S�������)<BR>
     * <BR>
     * ����.�S������ʂ��Athis.�S������ʂɃZ�b�g����B<BR>
     * <BR>
     * @@param l_strRestraintDiv - (�S�������)<BR>
     */
    public void setRestraintDiv(String l_strRestraintDiv)
    {
        this.restraintDiv = l_strRestraintDiv;
    }
}
@
