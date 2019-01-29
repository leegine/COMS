head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.35.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPConsolidatedCommissionAdjustmentRestraintReflector.java;


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
import webbroker3.util.WEB3LogUtility;

/**
 * (�萔����������S����)<BR>
 * �萔����������S������\������B
 */
public class WEB3TPConsolidatedCommissionAdjustmentRestraintReflector
    extends WEB3TPRestraintReflector
{

    /**
     * (�萔�����i�R�[�h)<BR>
     */
    private String productCode;

    /**
     * (�萔��������������z)<BR>
     */
    private double adjustment;

    /** ���O�@@���[�e�B���e�B�@@*/
    private static final WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3TPConsolidatedCommissionAdjustmentRestraintReflector.class);


    /**
     * @@roseuid 4104915803C7
     */
    public WEB3TPConsolidatedCommissionAdjustmentRestraintReflector()
    {

    }

    /**
     * (create�萔����������S����)<BR>
     * �萔����������S�����𐶐����A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�C���X�^���X����<BR>
     * <BR>
     * �Q�j�@@�l��ݒ�<BR>
     * 	�萔�����i�R�[�h���萔�����i�R�[�h<BR>
     * 	�萔��������������z���萔��������������z<BR>
     *     �ϓ����f�J�n���A�ϓ����f�I�����ݒ�Fcalc�ϓ����f��(��n��)<BR>
     * <BR>
     * �R�j�@@�C���X�^���X��ԋp<BR>
     * @@param l_strProductCode - (�萔��������i�R�[�h)
     * @@param l_dblAdjustment - (�萔����������z)
     * @@param l_datDeliveryDate - (��n��)
     * @@roseuid 40D8D8C300FC
     */
    public static WEB3TPConsolidatedCommissionAdjustmentRestraintReflector
        create(WEB3TPCalcCondition l_calcCondition, String l_strProductCode,
               double l_dblAdjustment, Date l_datDeliveryDate)
    {
        WEB3TPConsolidatedCommissionAdjustmentRestraintReflector l_instance =
            new WEB3TPConsolidatedCommissionAdjustmentRestraintReflector();

        l_instance.setCalcCondition(l_calcCondition);
        l_instance.setProductCode(l_strProductCode);
        l_instance.setAdjustment(l_dblAdjustment);
        l_instance.setAmount(l_dblAdjustment);
        l_instance.calcReflectDay(l_datDeliveryDate);

        return l_instance;

    }

    /**
     * (get�萔�����i�R�[�h)<BR>
     * �萔��������i�R�[�h��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40E014C5037C
     */
    public String getProductCode()
    {
        return productCode;
    }

    /**
     * (set�萔�����i�R�[�h)<BR>
     * �������萔��������i�R�[�h�ɃZ�b�g����B<BR>
     * @@param l_strProductCode - (�萔�����i�R�[�h)
     * @@roseuid 40E014C900FC
     */
    public void setProductCode(String l_strProductCode)
    {
        productCode = l_strProductCode;
    }

    /**
     * (get�萔��������������z)<BR>
     * �萔��������������z��Ԃ��B<BR>
     * @@return double
     * @@roseuid 40F75F7B0325
     */
    public double getAdjustment()
    {
        return adjustment;
    }

    /**
     * (set�萔��������������z)<BR>
     * �������萔��������������z�ɃZ�b�g����B<BR>
     * @@param l_dblAdjustment - (�萔��������������z)
     * @@roseuid 40F75F8301BE
     */
    public void setAdjustment(double l_dblAdjustment)
    {
        adjustment = l_dblAdjustment;
    }

    /**
     * (calc�ϓ����f��)<BR>
     * �ϓ����f�J�n���A�ϓ����f�I�������ȉ��̂悤�ɃZ�b�g����B<BR>
     * <BR>
     *  �ϓ����f�J�n��������.��n��<BR>
     *  �ϓ����f�I�������c�Ɠ�[5]<BR>
     * @@param l_datDeliveryDate - (��n��)
     * @@roseuid 40ECA13803A8
     */
    public void calcReflectDay(Date l_datDeliveryDate)
    {
        final String STR_METHOD_NAME = "calcReflectDay(Date l_datDeliveryDate)";

        if (l_datDeliveryDate == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        setReflectStartDay(l_datDeliveryDate);
        setReflectEndDay(getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5));
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
            .append("productCode", this.getProductCode())
            .append("adjustment", this.getAdjustment())
            .appendSuper(super.toString())
            .toString();
    }
}
@
