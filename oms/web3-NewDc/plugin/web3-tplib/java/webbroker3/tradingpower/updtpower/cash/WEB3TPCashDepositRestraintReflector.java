head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.36.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPCashDepositRestraintReflector.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �a����S�ۏo���S����(WEB3TPCashDepositRestraintReflector.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/11 ���G�� (���u) �V�K�쐬
*/
package webbroker3.tradingpower.updtpower.cash;

import java.util.Date;

import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3LogUtility;

/**
 * (�a����S�ۏo���S����) <BR>
 * <BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3TPCashDepositRestraintReflector extends WEB3TPRestraintReflector
{
    /**
     *  ���O���[�e�B���e�B�@@
     */
    private static final WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3TPCashDepositRestraintReflector.class);
    
    /**
     * (�f�t�H���g�R���X�g���N�^) 
     */
    public WEB3TPCashDepositRestraintReflector() 
    {
     
    }
    
    /**
     * (static)(create�a����S�ۏo���S����) <BR>
     * <BR>
     * �a����S�ۏo���S�������쐬���A�ԋp����B <BR>
     * <BR>
     * 1)�a����S�ۏo���S�����C���X�^���X�𐶐�����B <BR>
     * �@@�|�f�t�H���g�R���X�g���N�^���R�[�� <BR>
     * <BR>
     * 2)���������a����S�ۏo���S�����C���X�^���X(="�a����S�ۏo���S����")�̑����ɒl���Z�b�g<BR> 
     * <BR>
     * �@@�|"�a����S�ۏo���S����".set�]�͌v�Z����(:�]�͌v�Z���� = ����.�]�͌v�Z����)<BR> 
     * �@@�|"�a����S�ۏo���S����".set�S����(:double = ����.�o����~�z) <BR>
     * �@@�|"�a����S�ۏo���S����".calc�ϓ����f��(:Date = ����.������) <BR>
     * <BR>
     * 3)"�a����S�ۏo���S����"��ԋp����B <BR>
     * @@param l_calcCondition - (�]�͌v�Z����)
     * @@param l_dblPaymentStopAmount - (�o����~�z)
     * @@param l_datDealDate - (������)
     * @@return WEB3CashDepositRestraintReflector
     */
    public static WEB3TPCashDepositRestraintReflector createCashDepositRestraint(
        WEB3TPCalcCondition l_calcCondition, 
        double l_dblPaymentStopAmount,
        Date l_datDealDate)
    {
        final String STR_METHOD_NAME = "createCashDepositRestraint(WEB3TPCalcCondition, double, Date)";
        log.entering(STR_METHOD_NAME);
        
        //1)�a����S�ۏo���S�����C���X�^���X�𐶐�����B
        WEB3TPCashDepositRestraintReflector l_restraint = 
            new WEB3TPCashDepositRestraintReflector();
        
        // 2)���������a����S�ۏo���S�����C���X�^���X(="�a����S�ۏo���S����")�̑����ɒl���Z�b�g<BR> 
        // <BR>
        // �@@�|"�a����S�ۏo���S����".set�]�͌v�Z����(:�]�͌v�Z���� = ����.�]�͌v�Z����)<BR> 
        // �@@�|"�a����S�ۏo���S����".set�S����(:double = ����.�o����~�z) <BR>
        // �@@�|"�a����S�ۏo���S����".calc�ϓ����f��(:Date = ����.������) <BR>
        l_restraint.setCalcCondition(l_calcCondition);
        l_restraint.setAmount(l_dblPaymentStopAmount);
        l_restraint.calcReflectDay(l_datDealDate);
        
        //3)"�a����S�ۏo���S����"��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_restraint;
    }

    /**
     * (calc�ϓ����f��) <BR>
     * <BR>
     * �ϓ����f�J�n���A�ϓ����f�I�������Z�b�g����B <BR>
     * <BR>
     * �P�j����.��n���̗��c�Ɠ�(="��n��+1")���擾����B <BR>
     * �@@�|"��n��+1" = this.get�]�͌v�Z����().roll�c�Ɠ�(:Date = ����.��n��, :int = 1) <BR>
     * <BR>
     * �Q�j�ϓ����f�J�n���A�ϓ����f�I�������Z�b�g����B <BR>
     * �@@�|this.set�ϓ����f�J�n��(:Date = "��n��+1") <BR>
     * �@@�|this.set�ϓ����f�I����(:Date = T+5) <BR>
     * <BR>
     * ��T+5 = this.get�]�͌v�Z����().get�c�Ɠ�(:int = 5) <BR>
     * @@param l_datDeliveryDate - (��n��)
     */
    public void calcReflectDay(Date l_datDeliveryDate) 
    {
        final String STR_METHOD_NAME = "calcReflectDay(Date l_datDeliveryDate)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j����.��n���̗��c�Ɠ�(="��n��+1")���擾����B
        //�|"��n��+1" = this.get�]�͌v�Z����().roll�c�Ɠ�(:Date = ����.��n��, :int = 1)
        Date l_datNextBizDate = getCalcCondition().rollBizDate(l_datDeliveryDate, 1);
        
        // �Q�j�ϓ����f�J�n���A�ϓ����f�I�������Z�b�g����B <BR>
        // �@@�|this.set�ϓ����f�J�n��(:Date = "��n��+1") <BR>
        // �@@�|this.set�ϓ����f�I����(:Date = T+5) <BR>
        this.setReflectStartDay(l_datNextBizDate);
        this.setReflectEndDay(this.getCalcCondition().getBizDate(
            WEB3TPSpecifiedPointDef.T_5));
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B<BR>
     * <BR>
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
