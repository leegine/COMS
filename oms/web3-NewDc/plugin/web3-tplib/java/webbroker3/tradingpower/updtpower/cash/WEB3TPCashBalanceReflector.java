head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.35.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPCashBalanceReflector.java;


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
Revision History : 2009/12/15 �����F ���f��No.408 430
*/

package webbroker3.tradingpower.updtpower.cash;

import java.util.Date;

import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.updtpower.WEB3TPAssetReflector;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3LogUtility;

/**
 * (�m��a���)<BR>
 * �m��a����ϓ��i�ڋq����c���AMRF�c���j��\������B<BR>
 * <BR>
 */
public class WEB3TPCashBalanceReflector
    extends WEB3TPAssetReflector
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPCashBalanceReflector.class);

    /**
     * (MRF�t���O)<BR>
     */
    private boolean isMrf;
    

    /**
     * (�c���z)<BR>
     */
    private double amount;
    
    /**
     * (�ۏ؋��t���O)<BR>
     */
    private boolean isDeposit;

    /**
     * @@roseuid 41048ADC0221
     */
    public WEB3TPCashBalanceReflector()
    {

    }

    /**
     * (create�m��a���)<BR>
     * (static)(create�m��a���) <BR>
     * <BR>
     * �m��a����C���X�^���X�𐶐�����B <BR>
     * <BR>
     * 1)�m��a����C���X�^���X�𐶐�����B <BR>
     * �@@�|�f�t�H���g�R���X�g���N�^���R�[�� <BR>
     * <BR>
     * 2)���������m��a����C���X�^���X�̑����ɒl���Z�b�g <BR>
     * �@@�|this.set�]�͌v�Z����(:�]�͌v�Z���� = ����.�]�͌v�Z����) <BR>
     * �@@�|this.set�a����c��(:double = ����.�a����c��) <BR>
     * �@@�|this.setMRF(:boolean = ����.MRF�t���O) <BR>
     * �@@�|this.calc�ϓ����f��(:Date = ����.��n��) <BR>
     * �@@�|this.set�ۏ؋�(:boolean = ����.�ۏ؋��t���O) <BR>
     * <BR>
     * 3)�m��a����C���X�^���X��ԋp����B <BR>
     * @@param l_dblAmount - (�a���)
     * @@param l_isMrf - (MRF�t���O)
     * @@param l_datDeliveryDate - (��n��)
     * @@param l_blnIsDeposit - (�ۏ؋��t���O)
     * @@return WEB3TPCashBalanceReflector
     * @@roseuid 40E112360291
     */
    public static WEB3TPCashBalanceReflector create(WEB3TPCalcCondition l_calcCondition,
        double l_dblAmount, boolean l_isMrf, Date l_datDeliveryDate, boolean l_blnIsDeposit)
    {
        WEB3TPCashBalanceReflector l_instance = new WEB3TPCashBalanceReflector();
        l_instance.setCalcCondition(l_calcCondition);
        l_instance.setAmount(l_dblAmount);
        l_instance.setMrf(l_isMrf);
        l_instance.calcReflectDay(l_datDeliveryDate);
        l_instance.setDeposit(l_blnIsDeposit);
        return l_instance;
    }

    /**
     * (isMRF)<BR>
     * MRF�t���O��Ԃ��B<BR>
     * <BR>
     * @@return boolean
     * @@roseuid 40CD2EEC0180
     */
    public boolean isMrf()
    {
        return isMrf;
    }

    /**
     * (setMrf)<BR>
     * ������MRF�t���O�ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_isMrf - (MRF�t���O)
     * @@roseuid 40CD2EEF022C
     */
    public void setMrf(boolean l_isMrf)
    {
        isMrf = l_isMrf;
    }

    /**
     * (get�a����c��)<BR>
     * �c���z���擾����B<BR>
     * 
     * @@return double
     */
    public double getAmount() 
    {
        return amount;
    }

    /**
     * (set�a����c��)<BR>
     * �����̎c���z���Z�b�g����B<BR>
     * 
     * @@param l_dblAmount (�c���z)
     */
    public void setAmount(double l_dblAmount) 
    {
        amount = l_dblAmount;
    }

    /**
     * (calc�ϓ����f��)<BR>
     * �ϓ����f�J�n���A�ϓ����f�I�������v�Z��<BR>
     * �Z�b�g����B<BR>
     * <BR>
     * �P�jthis.isMrf()=true�̏ꍇ<BR>
     * �ϓ����f�J�n�� = �c�Ɠ�[0](T+0)<BR>
     * �ϓ����f�J�n�� = �c�Ɠ�[5](T+5)<BR>
     * <BR>
     * �Q�j�P�j�ȊO<BR>
     * �ϓ����f�J�n������n��<BR>
     * �ϓ����f�I��������n��<BR>
     * @@param l_dateDeliveryDate - (��n��)
     * @@return Date
     * @@roseuid 40CD147402D6
     */
    public void calcReflectDay(Date l_datDeliveryDate)
    {

        if (isMrf())
        {
            //T+0
            setReflectStartDay(getCalcCondition().getBizDate(0));
            //T+5
            setReflectEndDay(getCalcCondition().getBizDate(5));
        }
        else
        {
            setReflectStartDay(l_datDeliveryDate);
            setReflectEndDay(l_datDeliveryDate);
        }
    }

    /**
     * (is�ۏ؋�)<BR>
     * (is�ۏ؋�)<BR>
     * <BR>
     * this.�ۏ؋��t���O��ԋp���� <BR>
     * @@return boolean
     */
    public boolean isDeposit()
    {
        return isDeposit;
    }

    /**
     * (set�ۏ؋�)<BR>
     * (set�ۏ؋�)<BR>
     * <BR>
     * ����.�ۏ؋��t���O���Athis.�ۏ؋��t���O�ɃZ�b�g����B <BR>
     * @@param l_blnIsDeposit - (�ۏ؋��t���O)<BR>
     */
    public void setDeposit(boolean l_blnIsDeposit)
    {
        isDeposit = l_blnIsDeposit;
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
            .append("isMrf", this.isMrf())
            .append("amount", this.getAmount())
            .appendSuper(super.toString())
            .toString();
    }


}
@
