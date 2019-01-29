head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.08.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSecurityValuationProduct.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �Ώۖ���(WEB3TPSecurityValuationProduct.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/07/29 �R�c�@@��i (FLJ) �V�K�쐬
 Revesion History : 2007/07/28 �����Q(���u) �d�l�ύX���f��No.117
 */
package webbroker3.tradingpower.updtpower.asset;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.define.WEB3TPDepositTypeDef;
import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (�Ώۖ���)<BR>
 * �����e�[�u������擾������������ێ�����B<BR>
 */
public class WEB3TPSecurityValuationProduct
{

    /**
     * (�����h�c)
     */
    private long productId;

    /**
     * (�����^�C�v)
     */
    private ProductTypeEnum productType;

    /**
     * (�v�Z�P��)
     */
    private double unitSize;

    /**
     * (�]���P��)
     */
    private double unitPrice;

    /**
     * (��p�]���|��)
     */
    private double substituteValuationRatio;

    /**
     * (�،��]���|��)
     */
    private double valuationRatio;

    /**
     * (�D��s��ID)
     */
    private long primaryMarketId;

    /**
     * (�]���ΏۊO�����t���O)
     */
//    private boolean isNotRequired;

    /**
     * (�~�j���敪)
     * �a�莑�Y�Ή��̂��ߒǉ�
     */
    private String miniStockDivDef;

    /**
     * (�O���P��)
     */
    private double prePrice;

    /**
     * @@roseuid 41087DBC0206
     */
    public WEB3TPSecurityValuationProduct()
    {

    }

    /**
     * (create�Ώۖ���)<BR>
     * �Ώۖ����̃C���X�^���X�𐶐�����B
     * @@return webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuationProduct
     * @@roseuid 40DA5AEE0342
     */
    public static WEB3TPSecurityValuationProduct create()
    {
        return new WEB3TPSecurityValuationProduct();
    }

    /**
     * (get�����h�c)<BR>
     * �����h�c���擾����B
     * @@return long
     * @@roseuid 40B4466300DE
     */
    public long getProductId()
    {
        return productId;
    }

    /**
     * (set����ID)<BR>
     * ����ID��ݒ肷��B
     * @@param l_lngProductId - (����ID)
     * @@roseuid 40B4466F011D
     */
    public void setProductId(long l_lngProductId)
    {
        productId = l_lngProductId;
    }

    /**
     * (get�����^�C�v)<BR>
     * �����^�C�v���擾����B
     * @@return ProductTypeEnum
     * @@roseuid 40B4464F0226
     */
    public ProductTypeEnum getProductType()
    {
        return productType;
    }

    /**
     * (set�����^�C�v)<BR>
     * �����^�C�v��ݒ肷��B
     * @@param l_productType - (�����^�C�v)
     * @@roseuid 40B44657038E
     */
    public void setProductType(ProductTypeEnum l_productType)
    {
        productType = l_productType;
    }

    /**
     * (get�v�Z�P��)<BR>
     * �v�Z�P�ʂ��擾����B
     * @@return double
     * @@roseuid 40E563170102
     */
    public double getUnitSize()
    {
        return unitSize;
    }

    /**
     * (set�v�Z�P��)<BR>
     * �v�Z�P�ʂ�ݒ肷��B
     * @@param l_unitSize - (�v�Z�P��)
     * @@roseuid 40E562FA00BA
     */
    public void setUnitSize(double l_dblUnitSize)
    {
        unitSize = l_dblUnitSize;
    }

    /**
     * (get�]���P��)<BR>
     * �]���P�����擾����B
     * @@return double
     * @@roseuid 40BABFAA035D
     */
    public double getUnitPrice()
    {
        return unitPrice;
    }

    /**
     * (set�]���P��)<BR>
     * �]���P����ݒ肷��B
     * @@param l_unitPrice - (�]���P��)
     * @@roseuid 40BABFC5009C
     */
    public void setUnitPrice(double l_dblUnitPrice)
    {
        unitPrice = l_dblUnitPrice;
    }

    /**
     * (get�O���P��)<BR>
     * �O���P����ԋp����B
     * @@return double
     */
    public double getPrePrice()
    {
        return prePrice;
    }

    /**
     * (set�O���P��)<BR>
     * ����.�O���P����O���P���ɃZ�b�g����B
     * @@param l_prePrice - (�O���P��)
     */
    public void setPrePrice(double l_dblPrePrice)
    {
        prePrice = l_dblPrePrice;
    }

    /**
     * (get��p�]���|��)<BR>
     * ��p�]���|�ڂ��擾����B
     * @@return double
     * @@roseuid 40B446C8010D
     */
    public double getSubstituteValuationRatio()
    {
        return substituteValuationRatio;
    }

    /**
     * (set��p�]���|��)<BR>
     * ��p�]���|�ڂ�ݒ肷��B
     * @@param l_substituteValuationRatio - (��p�]���|��)
     * @@roseuid 40B446CD0311
     */
    public void setSubstituteValuationRatio(double l_dblSubstituteValuationRatio)
    {
        substituteValuationRatio = l_dblSubstituteValuationRatio;
    }

    /**
     * (get�،��]���|��)<BR>
     * �،��]���|�ڂ��擾����B
     * @@return double
     * @@roseuid 40B446E00023
     */
    public double getValuationRatio()
    {
        return valuationRatio;
    }

    /**
     * (set�،��]���|��)<BR>
     * �،��]���|�ڂ�ݒ肷��B
     * @@param l_valuationRatio - (�،��]���|��)
     * @@roseuid 40B446E6016B
     */
    public void setValuationRatio(double l_dblValuationRatio)
    {
        valuationRatio = l_dblValuationRatio;
    }

    /**
     * (set�]���ΏۊO�����t���O)<BR>
     * �]���ΏۊO�����t���O��ݒ肷��B
     * @@param l_isNotRequiredFlag - (�]���ΏۊO�����t���O)
     * @@roseuid 40E014A50325
     */
//    public void setNotRequiredFlag(boolean l_isNotRequiredFlag)
//    {
//        isNotRequired = l_isNotRequiredFlag;
//    }

    /**
     * (is�]���ΏۊO����)<BR>
     * �]���ΏۊO�����t���O���擾����B
     * @@return boolean
     * @@roseuid 40E014AC0357
     */
//    public boolean isNotRequired()
//    {
//        return isNotRequired;
//    }

    /**
     * (get�،��]���|��)<BR>
     * �w�肵���a��敪�̏،��]���|�ڂ��擾����B
     * @@param l_strDepositType �a��敪
     * @@return double
     */
    public double getValuationRatio(String l_strDepositType)
    {
        final String STR_METHOD_NAME = "getValuationRatio(String l_strDepositType)";

        if (WEB3TPDepositTypeDef.TRUST.equals(l_strDepositType))
        {
            return getValuationRatio();
        }
        else if (WEB3TPDepositTypeDef.SUBSTITUTE.equals(l_strDepositType))
        {
            return getSubstituteValuationRatio();
        }
        else
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }

    /**
     * (get�D��s��ID)<BR>
     * �D��s��ID���擾����B
     * @@return long
     */
    public long getPrimaryMarketId()
    {
        return primaryMarketId;
    }

    /**
     * (set�D��s��ID)<BR>
     * �D��s��ID��ݒ肷��B
     * @@param l_lngPrimaryMarketId - (�D��s��ID)
     */
    public void setPrimaryMarketId(long l_lngPrimaryMarketId)
    {
        primaryMarketId = l_lngPrimaryMarketId;
    }

    /**
     * (get�~�j���敪)<BR>
     * �~�j���敪���擾����B
     * @@return ProductTypeEnum
     * @@author kikuchi
     */
    public String getMiniStockDivDef()
    {
        return miniStockDivDef;
    }

    /**
     * (set�~�j���敪)<BR>
     * �~�j���敪��ݒ肷��B
     * @@param l_miniStockDivDef - (�~�j���敪)
     * @@author kikuchi
     */
    public void setMiniStockDivDef(String l_miniStockDivDef)
    {
        miniStockDivDef = l_miniStockDivDef;
    }

    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .append("productId", getProductId())
            .append("productType", getProductType())
            .append("unitSize", getUnitSize())
            .append("unitPricee", getUnitPrice())
            .append("substituteValuationRatio", getSubstituteValuationRatio())
            .append("valuationRatio", getValuationRatio())
//            .append("isNotRequired", isNotRequired())
            .append("primaryMarketId", getPrimaryMarketId())
            .append("miniStockDivDef", getMiniStockDivDef())
            .toString();
    }

}
@
