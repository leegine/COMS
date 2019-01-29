head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.36.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPMutualFundCapitalGainTaxRestraintReflector.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3TPMutualFundCapitalGainTaxRestraintReflector.java
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
 * (�����M�����n�v�ōS����)<BR>
 * �����M�����n�v�ōS������\������B
 */
public class WEB3TPMutualFundCapitalGainTaxRestraintReflector
    extends WEB3TPRestraintReflector
{
    /** ���O�@@���[�e�B���e�B�@@*/
    private static final WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3TPMutualFundCapitalGainTaxRestraintReflector.class);
    
    public WEB3TPMutualFundCapitalGainTaxRestraintReflector()
    {

    }

    /**
     * (create�����M�����n�v�ōS����)<BR>
     * �����M�����n�v�ōS�����𐶐����A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�C���X�^���X����<BR>
     * <BR>
     * �Q�j�@@�l��ݒ�<BR>
     * 	�S����      ��  �����M������.���򒥎��S����<BR>
     * 	�ϓ����f�J�n���A�ϓ����f�I�����ݒ�Fcalc�ϓ����f��(��n��))<BR>
     * <BR>
     * �R�j�@@�C���X�^���X��ԋp<BR>
     * <BR>
     * @@param l_calcCondition (�v�Z����)
     * @@param l_dblAmount (���򒥎��S����)
     * @@param l_datDeliveryDate (��n��)
     * @@return WEB3TPMutualFundCapitalGainTaxRestraintReflector
     */
    public static WEB3TPMutualFundCapitalGainTaxRestraintReflector create(WEB3TPCalcCondition l_calcCondition,
        double l_dblAmount, Date l_datDeliveryDate)
    {
        WEB3TPMutualFundCapitalGainTaxRestraintReflector l_instance = new WEB3TPMutualFundCapitalGainTaxRestraintReflector();
        l_instance.setCalcCondition(l_calcCondition);
        l_instance.setAmount(l_dblAmount);
        l_instance.calcReflectDay(l_datDeliveryDate);
        return l_instance;
    }

    /**
     * (calc�ϓ����f��)<BR>
     * �ϓ����f�J�n���A�ϓ����f�I�������ȉ��̂悤�ɃZ�b�g����B<BR>
     * <BR>
     * [����.��n�� < �c�Ɠ�[5]�̏ꍇ]<BR>
     * �ϓ����f�J�n��������.��n��<BR>
     * [��L�ȊO]<BR>
     * �ϓ����f�J�n�����c�Ɠ�[5]<BR>
     * <BR> 
     * �ϓ����f�I�������c�Ɠ�[5]
      * <BR>
     * @@param l_datDeliveryDate - ��n��
     */
    public void calcReflectDay(Date l_datDeliveryDate)
    {
        final String STR_METHOD_NAME = "calcReflectDay(Date l_datDeliveryDate)";

        Date l_datT5 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5);

         //����.��n�� < �c�Ɠ�[5]�̏ꍇ]
         //�ϓ����f�J�n��������.��n��
        if(WEB3DateUtility.compareToDay(l_datDeliveryDate, l_datT5) < 0)
        {
            //�J�n��       
            setReflectStartDay(l_datDeliveryDate);
        }
        else
        {
            //�J�n��       
            setReflectStartDay(l_datT5);
        }

        //�I����
        setReflectEndDay(l_datT5);
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
            .appendSuper(super.toString())
            .toString();
    }

}
@
