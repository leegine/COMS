head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.08.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSecurityChange.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �،��ϓ�(WEB3TPSecurityChange.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/07/29 �R�c�@@��i (FLJ) �V�K�쐬
                    2006/09/14 �Ԑi�@@     (���u)���f��No.36
 */
package webbroker3.tradingpower.updtpower.asset;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;

import webbroker3.tradingpower.updtpower.WEB3TPAssetReflector;
import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (�،��ϓ�)
 *
 * �a��c���ϓ���\������
 */
public abstract class WEB3TPSecurityChange
    extends WEB3TPAssetReflector
{

    /**
     * (��n��)
     */
    private Date deliveryDate;
    
    /**
     * (�a��敪)
     */
    private String depositType;

    /**
     * (��������t���O)
     */
    private boolean isSpecialAccountFlag;

    /**
     * (�ϓ�����)
     */
    private double quantity;

    /**
     * (�]���P��)
     */
    private double unitPrice;

    /**
     * (�]���|��)
     */
    private double valuationRatio;

    /**
     * (�]���z)
     */
    private double valuationPrice;

    /**
     * (�ŋ敪)
     */
    private TaxTypeEnum taxType;  
    
    /**
     * @@roseuid 41087D760060
     */
    public WEB3TPSecurityChange()
    {

    }

    /**
     * (get�a��敪)<BR>
     * �a��敪���擾����
     * @@return String
     * @@roseuid 40B2D37601FB
     */
    public String getDepositType()
    {
        return depositType;
    }

    /**
     * (set�a��敪)<BR>
     * �a��敪��ݒ肷��B
     * @@param l_strDepositType - (�a��敪)
     * @@roseuid 40B2D3EC02A7
     */
    public void setDepositType(String l_strDepositType)
    {
        depositType = l_strDepositType;
    }

    /**
     * (is��������敪)<BR>
     * ��������敪���擾����
     * @@return boolean
     * @@roseuid 40B2D38E0027
     */
    public boolean isSpecialAccount()
    {
        return isSpecialAccountFlag;
    }

    /**
     * (set��������敪)<BR>
     * ��������敪��ݒ肷��B
     * @@param l_isSpecialAccountFlag - (��������敪)
     * @@roseuid 40B2D3EC02C6
     */
    public void setSpecialAccountFlag(boolean l_isSpecialAccountFlag)
    {
        isSpecialAccountFlag = l_isSpecialAccountFlag;
    }

    /**
     * (get�ϓ�����)<BR>
     * �ϓ����ʂ��擾����
     * @@return double
     * @@roseuid 40B2D39F00E2
     */
    public double getQuantity()
    {
        return quantity;
    }

    /**
     * (set�ϓ�����)<BR>
     * �ϓ����ʂ�ݒ肷��B
     * @@param l_dblQuantity - (�ϓ�����)
     * @@roseuid 40B2D3EC02E6
     */
    public void setQuantity(double l_dblQuantity)
    {
        quantity = l_dblQuantity;
    }

    /**
     * (get�]���P��)<BR>
     * �]���P�����擾����B
     * @@return double
     * @@roseuid 40B2D3A803C0
     */
    public double getUnitPrice()
    {
        return unitPrice;
    }

    /**
     * (set�]���P��)<BR>
     * �]���P����ݒ肷��B
     * @@param l_dblUnitPrice - (�]���P��)
     * @@roseuid 40B2D3EC0315
     */
    public void setUnitPrice(double l_dblUnitPrice)
    {
        unitPrice = l_dblUnitPrice;
    }

    /**
     * (get�]���|��)<BR>
     * �]���|�ڂ��擾����B
     * @@return double
     * @@roseuid 40B2D3CA0007
     */
    public double getValuationRatio()
    {
        return valuationRatio;
    }

    /**
     * (set�]���|��)<BR>
     * �]���|�ڂ�ݒ肷��B
     * @@param l_dblValuationRatio - (�]���|��)
     * @@roseuid 40B2D3EC0334
     */
    public void setValuationRatio(double l_dblValuationRatio)
    {
        valuationRatio = l_dblValuationRatio;
    }

    /**
     * (get�]���z)<BR>
     * �]���z���擾����B
     * @@return double
     * @@roseuid 40B3329B02F5
     */
    public double getValuationPrice()
    {
        return valuationPrice;
    }

    /**
     * (set�]���z)<BR>
     * �]���z��ݒ肷��B
     * @@param l_dblValuationPrice - (�]���z)
     * @@roseuid 40B333E102B7
     */
    public void setValuationPrice(double l_dblValuationPrice)
    {
        valuationPrice = l_dblValuationPrice;
    }

    /**
     * (get��n��)<BR>
     * ��n�����擾����B
     * @@return deliveryDate ��߂��܂��B
     */
    public Date getDeliveryDate()
    {
        return deliveryDate;
    }

    /**
     * (set��n��)<BR>
     * ��n����ݒ肷��B
     * @@param deliveryDate deliveryDate ��ݒ�B
     */
    public void setDeliveryDate(Date l_datDeliveryDate)
    {
        this.deliveryDate = l_datDeliveryDate;
    }

    /**
     * (to��������敪)<BR>
     * �ŋ敪���������敪�֕ϊ�����
     * @@param l_taxType - (�ŋ敪)
     * @@return boolean
     */
    public boolean toSpecialAccountFlag(TaxTypeEnum l_taxType)
    {
        if (l_taxType != null && (l_taxType.equals(TaxTypeEnum.SPECIAL)
                                  || l_taxType.equals(TaxTypeEnum.SPECIAL_WITHHOLD)))
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    /**
     * (set�ŋ敪) <BR>
     * <BR>
     * ����.�ŋ敪���Athis.�ŋ敪�ɃZ�b�g����B<BR>
     * @@param l_taxType  - (�ŋ敪)
     */
    public void setTaxType(TaxTypeEnum l_taxType)
    {
        this.taxType = l_taxType;
    }    
    
    /**
     * (get�ŋ敪)<BR>
     * <BR>
     * this.�ŋ敪��ԋp����B <BR>
     * @@return taxType
     */
    public TaxTypeEnum getTaxType()
    {
        return taxType;
    }    
    
    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .appendSuper(super.toString())
            .append("depositType", getDepositType())
            .append("isSpecialAccount", isSpecialAccount())
            .append("quantity", getQuantity())
            .append("unitPrice", getUnitPrice())
            .append("valuationRatio", getValuationRatio())
            .append("valuationPrice", getValuationPrice())
            .append("deliveryDate", getDeliveryDate())
            .append("taxType", getTaxType())
            .toString();
    }
}
@
