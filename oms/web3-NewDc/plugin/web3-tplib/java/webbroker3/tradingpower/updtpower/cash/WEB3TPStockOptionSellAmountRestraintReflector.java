head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.35.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPStockOptionSellAmountRestraintReflector.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �X�g�b�N�I�v�V�������t����S����(WEB3TPStockOptionSellAmountRestraintReflector)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/14 �Ԑi(���u)�@@�V�K�쐬
*/

package webbroker3.tradingpower.updtpower.cash;

import java.util.Date;

import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3LogUtility;

/**
 * (�X�g�b�N�I�v�V�������t����S����)<BR>
 * <BR>
 * �X�g�b�N�I�v�V�����c���̔��t����̎g�p��<BR>
 * �g�����U�N�V�����������i�������j�����܂�<BR>
 * ��������ړI�Ōv�シ��S����<BR>
 */
public class WEB3TPStockOptionSellAmountRestraintReflector 
    extends WEB3TPRestraintReflector 
{

    /**
     * ���O���[�e�B���e�B
     */
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3TPStockOptionSellAmountRestraintReflector.class);    
    
    /**
     * (�X�g�b�N�I�v�V�������t����S����)<BR>
     * (�f�t�H���g�R���X�g���N�^)<BR>
     * @@roseuid 44E1499D0396
     */
    public WEB3TPStockOptionSellAmountRestraintReflector() 
    {
     
    }
    
    /**
     * (create�X�g�b�N�I�v�V�������t����S����)<BR>
     * (static)(create�X�g�b�N�I�v�V�������t����S����)<BR>
     * <BR>
     * �X�g�b�N�I�v�V�������t����S�������쐬���A�ԋp����B<BR>
     * <BR>
     * 1)�X�g�b�N�I�v�V�������t����S�����C���X�^���X�𐶐�����B<BR>
     * �@@-�f�t�H���g�R���X�g���N�^���R�[��<BR>
     * <BR>
     * 2)���������X�g�b�N�I�v�V�������t����S�����C���X�^���X�̑����ɒl���Z�b�g<BR>
     * <BR>
     * �@@�|this.set�]�͌v�Z����(:�]�͌v�Z���� = ����.�]�͌v�Z����)<BR>
     * �@@�|this.set�S����(:double = ����.��n���)<BR>
     * �@@�|this.calc�ϓ����f��(:Date = ����.��n��)<BR>
     * <BR>
     * 3)�X�g�b�N�I�v�V�������t����S�����C���X�^���X��ԋp����B<BR>
     * @@param l_calcCondition - (�]�͌v�Z����)<BR>
     * (�]�͌v�Z����)<BR>
     * @@param l_dblAmount - (��n���)<BR>
     * (��n���)<BR>
     * @@param l_datDeliveryDate - (��n��)<BR>
     * (��n��)<BR>
     * @@return WEB3TPStockOptionSellAmountRestraintReflector
     * @@roseuid 44E149AE0377
     */
    public static WEB3TPStockOptionSellAmountRestraintReflector createStockOptionSellAmountRestraintReflector(
        WEB3TPCalcCondition l_calcCondition, 
        double l_dblAmount, 
        Date l_datDeliveryDate) 
    {
        final String METHOD_NAME = "createStockOptionSellAmountRestraintReflector(" +
            "WEB3TPCalcCondition, double, Date)";
        log.entering(METHOD_NAME);
        
       //1)�X�g�b�N�I�v�V�������t����S�����C���X�^���X�𐶐�����B
       // �@@-�f�t�H���g�R���X�g���N�^���R�[��
        WEB3TPStockOptionSellAmountRestraintReflector l_instance =  
            new WEB3TPStockOptionSellAmountRestraintReflector();
        
        //2)���������X�g�b�N�I�v�V�������t����S�����C���X�^���X�̑����ɒl���Z�b�g
        // �@@�|this.set�]�͌v�Z����(:�]�͌v�Z���� = ����.�]�͌v�Z����)
        // �@@�|this.set�S����(:double = ����.��n���)
        //�@@ �|this.calc�ϓ����f��(:Date = ����.��n��)
        l_instance.setCalcCondition(l_calcCondition);
        l_instance.setAmount(l_dblAmount);
        l_instance.calcReflectDay(l_datDeliveryDate);
        
        //3)�X�g�b�N�I�v�V�������t����S�����C���X�^���X��ԋp����B
        log.exiting(METHOD_NAME);
        return l_instance;
    }
    
    /**
     * (calc�ϓ����f��)<BR>
     * <BR>
     * �ϓ����f�J�n���A�ϓ����f�I�������Z�b�g����B<BR>
     * �|this.set�ϓ����f�J�n��(:Date = ����.��n��)<BR>
     * �|this.set�ϓ����f�I����(:Date = T+5)<BR>
     * <BR>
     * ��T+5 = this.get�]�͌v�Z����().get�c�Ɠ�(:int = 5)<BR>
     * @@param l_datDeliveryDate - (��n��)<BR>
     * (��n���j<BR>
     * @@roseuid 44E1494300B8
     */
    public void calcReflectDay(Date l_datDeliveryDate) 
    {
        final String METHOD_NAME = "calcReflectDay(Date l_datDeliveryDate)";
        log.entering(METHOD_NAME);
        
        //�ϓ����f�J�n���A�ϓ����f�I�������Z�b�g����B
        //�|this.set�ϓ����f�J�n��(:Date = ����.��n��)
        //�|this.set�ϓ����f�I����(:Date = T+5)
        Date l_datT5 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5);
        this.setReflectStartDay(l_datDeliveryDate);
        this.setReflectEndDay(l_datT5);

        log.exiting(METHOD_NAME);
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
