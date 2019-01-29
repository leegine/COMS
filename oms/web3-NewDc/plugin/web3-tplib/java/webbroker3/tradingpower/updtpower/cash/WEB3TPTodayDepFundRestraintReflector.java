head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.34.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTodayDepFundRestraintReflector.java;


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
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�������������S����)<BR>
 * �������������S������\������B
 */
public class WEB3TPTodayDepFundRestraintReflector
    extends WEB3TPRestraintReflector
{

    /** ���O�@@���[�e�B���e�B�@@*/
    private static final WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3TPTodayDepFundRestraintReflector.class);

    /**
     * (�g�����U�N�V����������)<BR>
     */
    private Date finTransactionDate;


    /**
     * @@roseuid 410491590165
     */
    public WEB3TPTodayDepFundRestraintReflector()
    {

    }

    /**
     * (create�������������S����)<BR>
     * �������������S�����𐶐����A�ԋp����B<BR>
     * <BR>
     * �P�j�C���X�^���X����<BR>
     * <BR>
     * �Q�j�l��ݒ�<BR>
     * 	�S����      ��  ��n���<BR>
     * 	�ϓ����f�J�n���A�ϓ����f�I�����ݒ�Fcalc�ϓ����f���i��n���j<BR>
     * <BR>
     * �R�j�C���X�^���X��ԋp<BR>
     * @@param l_dblDeliveryAmount - (��n���z)
     * @@param l_datDeliveryDate - (��n��)
     * @@return WEB3TPTodayDepFundRestraintReflector
     * @@roseuid 40D829C70186
     */
    public static WEB3TPTodayDepFundRestraintReflector create(WEB3TPCalcCondition
        l_calcCondition, double l_dblDeliveryAmount, Date l_datFinTransactionDate, Date l_datDeliveryDate)
    {
        WEB3TPTodayDepFundRestraintReflector l_instance = new
            WEB3TPTodayDepFundRestraintReflector();
        l_instance.setCalcCondition(l_calcCondition);
        l_instance.setAmount(l_dblDeliveryAmount);
        l_instance.setFinTransactionDate(l_datFinTransactionDate);
        l_instance.calcReflectDay(l_datDeliveryDate);
        return l_instance;
    }

    /**
     * (get�g�����U�N�V����������)<BR>
     * �g�����U�N�V�����^�C�v��Ԃ��B<BR>
     * @@return Date
     */
    public Date getFinTransactionDate()
    {
        return finTransactionDate;
    }

    /**
     * (set�g�����U�N�V����������)<BR>
     * �������g�����U�N�V�����������ɃZ�b�g����B<BR>
     * @@param l_finTransactionDate - (�g�����U�N�V����������)
     */
    public void setFinTransactionDate(Date l_finTransactionDate)
    {
        finTransactionDate = l_finTransactionDate;
    }


    /**
     * (calc�ϓ����f��)<BR>
     * �ϓ����f�J�n���A�ϓ����f�I�������ȉ��̂悤�ɃZ�b�g����B<BR>
     * <BR>
     * [a. �g�����U�N�V��������������������i���������j�̏ꍇ]
     * �@@�ϓ����f�J�n�����g�����U�N�V����������(T+1)<BR>
     * [a. �g�����U�N�V��������������������i���������j�̏ꍇ]
     * �@@�ϓ����f�J�n����T+0<BR>
     * <BR>
     * �ϓ����f�I����������.��n��-1<BR>
     * @@param l_datDeliveryDate - (��n��)
     * @@roseuid 40D66F180172
     */
    public void calcReflectDay(Date l_datDeliveryDate)
    {
        final String STR_METHOD_NAME = "calcReflectDay(Date l_datDeliveryDate)";
        
        Date l_datT0 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);
                       
        //T+0�ȑO�̃f�[�^�̓��[�h����Ȃ��͂�
        if (WEB3DateUtility.compareToDay(l_datT0, l_datDeliveryDate) >= 0)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //���f�J�n��
        
        //����(�ȍ~)�����̏ꍇ
        if(WEB3DateUtility.compareToDay(l_datT0, finTransactionDate) < 0)
        {
            setReflectStartDay(finTransactionDate);
        }
        //����ȊO
        else
        {
            setReflectStartDay(l_datT0);
        }
        
        //���f�I����
        setReflectEndDay(getCalcCondition().rollBizDate(l_datDeliveryDate, -1));

    }

    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B
     * 
     * @@return String
     */
    public String toString()
    {
        String l_strYYYYMMDDFormat = "yyyy/MM/dd";

        return ToStringUtils
            .newToStringBuilder(this)
            .append("finTransactionDate", WEB3DateUtility.formatDate(this.getFinTransactionDate(), l_strYYYYMMDDFormat))
            .appendSuper(super.toString())
            .toString();
    }

}
@
