head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.34.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPElapsedDayExecuteCancelReflector.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ߓ���������(WEB3TPElapsedDayExecuteCancelReflector.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/09/25 �Ԑi  (���u)  �V�K�쐬
*/

package webbroker3.tradingpower.updtpower.cash;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;

import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.updtpower.WEB3TPAssetReflector;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�ߓ���������)<BR>
 * <BR>
 * �ߓ�����������̂����A�m��a�����<BR>
 * �����f�ł����������i�[����N���X<BR>
 * 
 * @@author �Ԑi(���u)
 * @@version 1.0
 */
public class WEB3TPElapsedDayExecuteCancelReflector extends WEB3TPAssetReflector
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPElapsedDayExecuteCancelReflector.class);

    /**
     * (�����^�C�v)<BR>
     */
    private ProductTypeEnum productType;

    /**
     * (����ID)<BR>
     */
    private long productId;

    /**
     * (�g�����U�N�V�����^�C�v)<BR>
     */
    private FinTransactionType finTransactionType;

    /**
     * (��n��)<BR>
     */
    private Date deliveryDate;

    /**
     * (�g�����U�N�V����������)<BR>
     */
    private Date finTransactionDate;

    /**
     * (�������)<BR>
     */
    private double cancelQuantity;

    /**
     * (������)<BR>
     */
    private double cancelAmount;

    /**
     * (�ŋ敪)<BR>
     */
    private TaxTypeEnum taxType;

    /**
     * (�ߓ���������)<BR>
     * (�f�t�H���g�R���X�g���N�^)<BR>
     * <BR>
     * @@roseuid 45052F0C0169
     */
    public WEB3TPElapsedDayExecuteCancelReflector()
    {

    }

    /**
     * (static)(create�ߓ���������)<BR>
     * <BR>
     * �ߓ�����������쐬���A�ԋp����B<BR>
     * <BR>
     * 1)�ߓ���������C���X�^���X(="�ߓ���������")�𐶐�����B<BR>
     * �@@-�f�t�H���g�R���X�g���N�^���R�[��<BR>
     * <BR>
     * 2)���������ߓ���������C���X�^���X�̑����ɒl���Z�b�g<BR>
     * <BR>
     * �@@�|"�ߓ���������".set�]�͌v�Z����(:�]�͌v�Z���� = ����.�]�͌v�Z����)<BR>
     * �@@�|"�ߓ���������".set�����^�C�v(:ProductionType = ����.�����^�C�v)<BR>
     * �@@�|"�ߓ���������".set����ID(:long = ����.����ID)<BR>
     * �@@�|"�ߓ���������".set�g�����U�N�V�����^�C�v(:FinTransactionType = ����.�g�����U�N�V�����^�C�v)<BR>
     * �@@�|"�ߓ���������".set�g�����U�N�V����������(:Date = ����.�g�����U�N�V����������)<BR>
     * �@@�|"�ߓ���������".set�������(:double = ����.�������)<BR>
     * �@@�|"�ߓ���������".set������(:double = ����.������)<BR>
     * �@@�|"�ߓ���������".set��n��(:Date = ����.��n��)<BR>
     * �@@�|"�ߓ���������".calc�ϓ����f��(:Date = ����.��n��)<BR>
     * �@@�|"�ߓ���������".set�ŋ敪(:TaxTypeEnum = ����.�ŋ敪)<BR>
     * <BR>
     * 3)�ߓ���������C���X�^���X��ԋp����B<BR>
     * <BR>
     * @@param l_calcCondition - (�]�͌v�Z����)
     * @@param l_productType - (�����^�C�v)
     * @@param l_lngProductId - (����ID)
     * @@param l_finTransactionType - (�g�����U�N�V�����^�C�v)
     * @@param l_finTransactionDate - (�g�����U�N�V����������)
     * @@param l_dblCancelQuantity - (�������)
     * @@param l_dblCancelAmount - (������)
     * @@param l_datDeliveryDate - (��n��)
     * @@param l_taxType - (�ŋ敪)
     * @@return WEB3TPElapsedDayExecuteCancelReflector
     * @@roseuid 45052F0C0188
     */
    public static WEB3TPElapsedDayExecuteCancelReflector createWEB3TPElapsedDayExecuteCancelReflector(
        WEB3TPCalcCondition l_calcCondition, 
        ProductTypeEnum l_productType, 
        long l_lngProductId, 
        FinTransactionType l_finTransactionType, 
        Date l_finTransactionDate, 
        double l_dblCancelQuantity, 
        double l_dblCancelAmount, 
        Date l_datDeliveryDate, 
        TaxTypeEnum l_taxType) 
    {
        final String STR_METHOD_NAME =
            "createWEB3TPElapsedDayExecuteCancelReflector(WEB3TPCalcCondition, " + 
            "ProductTypeEnum, long, FinTransactionType, Date, double, double, Date, TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);

        //1)�ߓ���������C���X�^���X(="�ߓ���������")�𐶐�����B
        WEB3TPElapsedDayExecuteCancelReflector l_instance = 
            new WEB3TPElapsedDayExecuteCancelReflector();

        //2)���������ߓ���������C���X�^���X�̑����ɒl���Z�b�g
        //"�ߓ���������".set�]�͌v�Z����(:�]�͌v�Z���� = ����.�]�͌v�Z����)
        l_instance.setCalcCondition(l_calcCondition);
        //"�ߓ���������".set�����^�C�v(:ProductionType = ����.�����^�C�v)
        l_instance.setProductType(l_productType);
        //"�ߓ���������".set����ID(:long = ����.����ID)
        l_instance.setProductId(l_lngProductId);
        //"�ߓ���������".set�g�����U�N�V�����^�C�v(:FinTransactionType = ����.�g�����U�N�V�����^�C�v)
        l_instance.setTransactionType(l_finTransactionType);
        //"�ߓ���������".set�g�����U�N�V����������(:Date = ����.�g�����U�N�V����������)
        l_instance.setTransactionDate(l_finTransactionDate);
        //"�ߓ���������".set�������(:double = ����.�������)
        l_instance.setCancelQuantity(l_dblCancelQuantity);
        //"�ߓ���������".set������(:double = ����.������)
        l_instance.setCancelAmount(l_dblCancelAmount);
        //"�ߓ���������".set��n��(:Date = ����.��n��)
        l_instance.setDeliveryDate(l_datDeliveryDate);
        //"�ߓ���������".calc�ϓ����f��(:Date = ����.��n��)
        l_instance.calcReflectDay(l_datDeliveryDate);
        //"�ߓ���������".set�ŋ敪(:TaxTypeEnum = ����.�ŋ敪)
        l_instance.setTaxType(l_taxType);

        //3)�ߓ���������C���X�^���X��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_instance;
    }

    /**
     * (get�����^�C�v)<BR>
     * <BR>
     * this.�����^�C�v��ԋp����B<BR>
     * <BR>
     * @@return ProductTypeEnum
     * @@roseuid 45052F0C01A8
     */
    public ProductTypeEnum getProductType()
    {
        final String STR_METHOD_NAME = "getProductType()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return this.productType;
    }

    /**
     * (set�����^�C�v)<BR>
     * <BR>
     * ����.�����^�C�v���Athis.�����^�C�v�ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_productType - (�����^�C�v)
     * @@roseuid 45052F0C01B7
     */
    public void setProductType(ProductTypeEnum l_productType)
    {
        final String STR_METHOD_NAME = "setProductType(ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);

        this.productType = l_productType;
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get����ID)<BR>
     * <BR>
     * this.����ID��ԋp����B<BR>
     * <BR>
     * @@return long
     * @@roseuid 45052F0C01D6
     */
    public long getProductId()
    {
        final String STR_METHOD_NAME = "getProductId()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return this.productId;
    }

    /**
     * (set����ID)<BR>
     * <BR>
     * ����.����ID���Athis.����ID�ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_lngProductId - (����ID)
     * @@roseuid 45052F0C01F6
     */
    public void setProductId(long l_lngProductId)
    {
        final String STR_METHOD_NAME = "setProductId(long)";
        log.entering(STR_METHOD_NAME);

        this.productId = l_lngProductId;
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�g�����U�N�V�����^�C�v)<BR>
     * <BR>
     * this.�g�����U�N�V�����^�C�v��ԋp����B<BR>
     * <BR>
     * @@return FinTransactionType
     * @@roseuid 45052F0C0215
     */
    public FinTransactionType getTransactionType()
    {
        final String STR_METHOD_NAME = "getTransactionType()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return this.finTransactionType;
    }

    /**
     * (set�g�����U�N�V�����^�C�v)<BR>
     * <BR>
     * ����.�g�����U�N�V�����^�C�v���Athis.�g�����U�N�V�����^�C�v�ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_finTransactionType - (�g�����U�N�V�����^�C�v)
     * @@roseuid 45052F0C0234
     */
    public void setTransactionType(FinTransactionType l_finTransactionType)
    {
        final String STR_METHOD_NAME = "setTransactionType(FinTransactionType)";
        log.entering(STR_METHOD_NAME);

        this.finTransactionType = l_finTransactionType;
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�g�����U�N�V����������)<BR>
     * <BR>
     * this.�g�����U�N�V������������ԋp����B<BR>
     * <BR>
     * @@return Date
     * @@roseuid 45052F0C0253
     */
    public Date getTransactionDate()
    {
        final String STR_METHOD_NAME = "getTransactionDate()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return this.finTransactionDate;
    }

    /**
     * (set�g�����U�N�V����������)<BR>
     * <BR>
     * ����.�g�����U�N�V�������������Athis.�g�����U�N�V�����������ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_finTransactionDate - (�g�����U�N�V����������)<BR>
     * <BR>
     * @@roseuid 45052F0C0273
     */
    public void setTransactionDate(Date l_finTransactionDate)
    {
        final String STR_METHOD_NAME = "setTransactionDate(Date)";
        log.entering(STR_METHOD_NAME);

        this.finTransactionDate = l_finTransactionDate;
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get��n��)<BR>
     * <BR>
     * this.��n����ԋp����B<BR>
     * <BR>
     * @@return Date
     * @@roseuid 45052F0C0292
     */
    public Date getDeliveryDate()
    {
        final String STR_METHOD_NAME = "getDeliveryDate()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return this.deliveryDate;
    }

    /**
     * (set��n��)<BR>
     * <BR>
     * ����.��n�����Athis.��n���ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_datDeliveryDate - (��n��)<BR>
     * @@roseuid 45052F0C02A2
     */
    public void setDeliveryDate(Date l_datDeliveryDate)
    {
        final String STR_METHOD_NAME = "setDeliveryDate(Date)";
        log.entering(STR_METHOD_NAME);

        this.deliveryDate = l_datDeliveryDate;
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�������)<BR>
     * <BR>
     * this.������ʂ�ԋp����B<BR>
     * <BR>
     * @@return double
     * @@roseuid 45052F0C02C1
     */
    public double getCancelQuantity()
    {
        final String STR_METHOD_NAME = "getCancelQuantity()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return this.cancelQuantity;
    }

    /**
     * (set�������)<BR>
     * <BR>
     * ����.������ʂ��Athis.������ʂɃZ�b�g����B<BR>
     * <BR>
     * @@param l_dblCancelQuantity - (�������)<BR>
     * @@roseuid 45052F0C02F0
     */
    public void setCancelQuantity(double l_dblCancelQuantity)
    {
        final String STR_METHOD_NAME = "setCancelQuantity(double)";
        log.entering(STR_METHOD_NAME);

        this.cancelQuantity = l_dblCancelQuantity;
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get������)<BR>
     * <BR>
     * this.��������ԋp����B<BR>
     * <BR>
     * @@return double
     * @@roseuid 45052F0C030F
     */
    public double getCancelAmount()
    {
        final String STR_METHOD_NAME = "getCancelAmount()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return this.cancelAmount;
    }

    /**
     * (set������)<BR>
     * <BR>
     * ����.���������Athis.�������ɃZ�b�g����B<BR>
     * @@param l_dblCancelAmount - (������)<BR>
     * @@roseuid 45052F0C036D
     */
    public void setCancelAmount(double l_dblCancelAmount)
    {
        final String STR_METHOD_NAME = "setCancelAmount(double)";
        log.entering(STR_METHOD_NAME);

        this.cancelAmount = l_dblCancelAmount;
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�ŋ敪)<BR>
     * <BR>
     * this.�ŋ敪��ԋp����B<BR>
     * @@return TaxTypeEnum
     * @@roseuid 45052F0D0040
     */
    public TaxTypeEnum getTaxType()
    {
        final String STR_METHOD_NAME = "getTaxType()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return this.taxType;
    }

    /**
     * (set�ŋ敪)<BR>
     * <BR>
     * ����.�ŋ敪���Athis.�ŋ敪�ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_taxType - (�ŋ敪)<BR>
     * <BR>
     * <BR>
     * @@roseuid 45052F0D005F
     */
    public void setTaxType(TaxTypeEnum l_taxType)
    {
        final String STR_METHOD_NAME = "setTaxType(TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);

        this.taxType = l_taxType;
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (calc�ϓ����f��)<BR>
     * <BR>
     * �ϓ����f�J�n���A�ϓ����f�I�������Z�b�g����B<BR>
     * <BR>
     * �P�j�ϓ����f�J�n�����Z�b�g����B<BR>
     * <BR>
     * �@@[a.T+5 > ����.��n���̏ꍇ]<BR>
     * �@@�@@�|this.set�ϓ����f�J�n��(:Date = ����.��n��)<BR>
     * �@@<BR>
     * �@@[a.�ȊO �̏ꍇ]<BR>
     * �@@�@@�|this.set�ϓ����f�J�n��(:Date = T+5)<BR>
     * <BR>
     * �Q�j�ϓ����f�I�������Z�b�g����B<BR>
     * �@@�@@�|this.set�ϓ����f�I����(:Date = T+5)<BR>
     * <BR>
     * ��T+5 = this.get�]�͌v�Z����().get�c�Ɠ�(:int = 5)<BR>
     * <BR>
     * @@param l_datDeliveryDate - (��n��)
     * @@roseuid 45052F0D00AE
     */
    public void calcReflectDay(Date l_datDeliveryDate)
    {
        Date l_datT5 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5);
        //T+5 > ����.��n���̏ꍇ
        //set�ϓ����f�J�n��(:Date = ����.��n��)
        if (WEB3DateUtility.compareToDay(l_datT5, l_datDeliveryDate) > 0)
        {
            setReflectStartDay(l_datDeliveryDate);
        }
        //�ȊO �̏ꍇ set�ϓ����f�J�n��(:Date = T+5)
        else
        {
            setReflectStartDay(l_datT5);
        }

        //�ϓ����f�I�������Z�b�g����B
        setReflectEndDay(l_datT5);
    }
}@
