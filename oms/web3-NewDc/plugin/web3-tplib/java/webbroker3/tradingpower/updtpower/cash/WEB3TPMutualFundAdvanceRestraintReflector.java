head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.35.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPMutualFundAdvanceRestraintReflector.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3TPMutualFundAdvanceRestraintReflector.java
Author Name      : Daiwa Institute of Research
Revision History : 2005/10/17 �V�� �h�O(FLJ) �V�K�쐬
*/

package webbroker3.tradingpower.updtpower.cash;

import java.util.Date;

import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����M���O��S����)<BR>
 * �����M���O��S������\������B
 */
public class WEB3TPMutualFundAdvanceRestraintReflector
    extends WEB3TPRestraintReflector
{

    /** ���O�@@���[�e�B���e�B�@@*/
    private static final WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3TPMutualFundAdvanceRestraintReflector.class);

    /**
     * (�g�����U�N�V����������)<BR>
     */
    private Date finTransactionDate;

    public WEB3TPMutualFundAdvanceRestraintReflector()
    {

    }

    /**
     * (create�����M���O��S����)<BR>
     * �����M���O��S�����𐶐����A�ԋp����B<BR>
     * <BR>
     * �P�j�C���X�^���X����<BR>
     * <BR>
     * �Q�j�l��ݒ�<BR>
     * 	�S����      ��  ��n���<BR>
     * 	�ϓ����f�J�n���A�ϓ����f�I�����ݒ�Fcalc�ϓ����f���i��n���j<BR>
     * <BR>
     * �R�j�C���X�^���X��ԋp<BR>
     * @@param l_dblAmount - (��n���)
     * @@param l_datDeliveryDate - (��n��)
     * @@return WEB3TPMutualFundAdvanceRestraintReflector
     */
    public static WEB3TPMutualFundAdvanceRestraintReflector create(WEB3TPCalcCondition
        l_calcCondition, double l_dblAmount, Date l_datFinTransactionDate, Date l_datDeliveryDate)
    {
        WEB3TPMutualFundAdvanceRestraintReflector l_instance = new
            WEB3TPMutualFundAdvanceRestraintReflector();
        l_instance.setCalcCondition(l_calcCondition);
        l_instance.setAmount(l_dblAmount);
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
     * [a. �g�����U�N�V�������������c�Ɠ�(T+0)�̏ꍇ]<BR>
     * �@@�ϓ����f�J�n�����c�Ɠ�(T+0)<BR>
     * [a. ��L�ȊO�̏ꍇ]<BR>
     * �@@�ϓ����f�J�n�����g�����U�N�V����������<BR>
     * <BR>
     * [a. �c�Ɠ�(T+5)����n���̏ꍇ]<BR>
     * �@@�ϓ����f�I�������c�Ɠ�(T+4)<BR>
     * [a. ��L�ȊO�̏ꍇ]<BR>
     * �@@�ϓ����f�I��������n��-1<BR>
     * @@param l_datDeliveryDate - (��n��)
     */
    public void calcReflectDay(Date l_datDeliveryDate)
    {
        final String STR_METHOD_NAME = "calcReflectDay(Date l_datDeliveryDate)";
        
        Date l_datT0 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);
        Date l_datT4 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_4);
        Date l_datT5 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5);
                       
        //�g�����U�N�V�������������c�Ɠ�(T+0)�̏ꍇ
        if (WEB3DateUtility.compareToDay(finTransactionDate, l_datT0) < 0)
        {
            //�c�Ɠ�(T+0)�ȍ~�S��
            setReflectStartDay(l_datT0);
        }
        //
        else
        {
            //�g�����U�N�V�����������ȍ~�S��
            setReflectStartDay(finTransactionDate);
        }
        
        //��n����null�̏ꍇ
        if(l_datDeliveryDate == null)
        {
            l_datDeliveryDate = l_datT5;
        }
        
        //�c�Ɠ�(T+5)����n���̏ꍇ
        if(WEB3DateUtility.compareToDay(l_datT5, l_datDeliveryDate) < 0)
        {
            //�c�Ɠ�(T+4)�܂ōS��
            setReflectEndDay(l_datT4);
        }
        //����ȊO
        else
        {
            //��n��-1�܂ōS��
            setReflectEndDay(getCalcCondition().rollBizDate(l_datDeliveryDate, -1));
        }
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
