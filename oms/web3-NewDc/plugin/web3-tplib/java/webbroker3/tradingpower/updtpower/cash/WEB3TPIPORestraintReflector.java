head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.34.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPIPORestraintReflector.java;


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
 * (IPO�S����)<BR>
 * IPO�S������\������B
 */
public class WEB3TPIPORestraintReflector
    extends WEB3TPRestraintReflector
{
    /** ���O�@@���[�e�B���e�B�@@*/
    private static final WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3TPIPORestraintReflector.class);
    
    /**
     * (�g�����U�N�V����������)<BR>
     */
    private Date finTransactionDate;

    /**
     * @@roseuid 4104BBC402E9
     */
    public WEB3TPIPORestraintReflector()
    {

    }

    /**
     * (createIPO�S����)<BR>
     * IPO�S�����𐶐����A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�C���X�^���X����<BR>
     * <BR>
     * �Q�j�@@�l��ݒ�<BR>
     * 	�S����      ��  IPO����.IPO�w���\�����<BR>
     * 	�ϓ����f�J�n���A�ϓ����f�I�����ݒ�Fcalc�ϓ����f��(�q�����f��))<BR>
     * �@@�@@�i�q�����f���͍w���\�����ؓ��i�ژ_�����L�ځj���邢��null�j<BR>
     * <BR>
     * �R�j�@@�C���X�^���X��ԋp<BR>
     * <BR>
     * @@param l_calcCondition (�v�Z����)
     * @@param l_payAmount (�w���\�����)
     * @@param l_lotDate (���I��)
     * @@param l_accountReflectDate (�q�����f��)
     */
    public static WEB3TPIPORestraintReflector create(
            WEB3TPCalcCondition l_calcCondition,
            double l_payAmount,
            Date l_lotDate,
            Date l_accountReflectDate)
    {
        WEB3TPIPORestraintReflector l_instance = new WEB3TPIPORestraintReflector();
        l_instance.setCalcCondition(l_calcCondition);
        l_instance.setAmount(l_payAmount);
        l_instance.setFinTransactionDate(l_lotDate);
        l_instance.calcReflectDay(l_accountReflectDate);
        return l_instance;
    }

    /**
     * (calc�ϓ����f��)<BR>
     * �ϓ����f�J�n���A�ϓ����f�I�������ȉ��̂悤�ɃZ�b�g����B <BR>
     * <BR>
     * �ϓ����f�J�n���F = this.get�g�����U�N�V����������() <BR>
     * <BR>
     * [�����D�q�����f��=null�̏ꍇ] <BR>
     * �@@�ϓ����f�I����=�c�Ɠ�(T+5) <BR>
     * <BR>
     * [�ȊO�i�����D�q�����f�� �� null)�̏ꍇ] <BR>
     * �@@�ϓ����f�I����=�����D�q�����f��-1<BR>
     * <BR>
     * @@param l_accountReflectDate - �q�����f��
     */
    public void calcReflectDay(Date l_accountReflectDate)
    {
        final String STR_METHOD_NAME = "calcReflectDay";

        Date l_datT0 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);
        Date l_datT5 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5);

        //�J�n��   	
        setReflectStartDay(this.getFinTransactionDate());

        //�I����
        //�q�����f�����ݒ肳��Ă��Ȃ��ꍇ�i�w���\���O�̏ꍇ�j
        if (l_accountReflectDate == null)
        {
            setReflectEndDay(l_datT5);
        }
        else
        {
            //�w���\���ŏI����T+0�ȑO�ł���ꍇ���[�h����Ȃ��͂�
            if(WEB3DateUtility.compareToDay(l_datT0, l_accountReflectDate) >=0)
            {
                throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //�q�����f���i�w���\���ŏI���j��T+5�ȍ~�ł���ꍇ��T+4�܂ōS������
            if(WEB3DateUtility.compareToDay(l_datT5, l_accountReflectDate) <= 0)
            {
                setReflectEndDay(getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_4));
            }
            //����ȊO�͋q�����f���̑O���܂ōS��
            else
            {                
                setReflectEndDay(getCalcCondition().rollBizDate(l_accountReflectDate, -1));
            }
        }
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
