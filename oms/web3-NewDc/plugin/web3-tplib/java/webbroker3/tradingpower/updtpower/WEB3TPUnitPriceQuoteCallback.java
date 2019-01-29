head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.59.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPUnitPriceQuoteCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]���P��<����>Callback(WEB3TPQuoteUnitPriceCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/04 �V���@@�h�O (FLJ) �V�K�쐬
*/
package webbroker3.tradingpower.updtpower;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;

import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuationProduct;
import webbroker3.tradingpower.updtpower.contract.WEB3TPTargetContractDetail;

/**
 * (�]���P��<����>Callback)
 */
public class WEB3TPUnitPriceQuoteCallback extends WEB3TPUnitPriceStandardCallback
{

    /**
     * (�]���P��<����>Callback)<BR>
     * �R���X�g���N�^<BR>
     * @@param l_calcCondition
     */
    public WEB3TPUnitPriceQuoteCallback(WEB3TPCalcCondition l_calcCondition)
    {
        super(l_calcCondition);
    }

    /**
     * (get�]���P��<�Ώۖ���>)<BR>
     * �����^�C�v�������̏ꍇ�A������Ԃ��B<BR>
     * �����^�C�v�������łȂ��A���� �����Ŏ������擾�ł��Ȃ��ꍇ�A<BR>
     * �X�[�p�[�N���X�̃��\�b�h�̖߂�l��Ԃ��B<BR>
     * @@param l_productRow - (����Row)
     * @@return double
     */
    public double getUnitPrice(ProductRow l_productRow) 
    {
        
        //�v���_�N�g�^�C�v���擾
        ProductTypeEnum l_productTypeEnum = l_productRow.getProductType();

        //�]���P��
        double l_dblUnitPrice = 0.0;
        
        //�����̏ꍇ
        if(ProductTypeEnum.EQUITY.equals(l_productTypeEnum))
        {
            //����ID���擾
            long l_lngProdcutId = l_productRow.getProductId();
            
            //�D��s��ID�ɒl�����鎞�����������擾����
            if(! l_productRow.getPrimaryMarketIdIsNull())
            {
                //�s��ID���擾
                long l_lngMarketId = l_productRow.getPrimaryMarketId();
                
                //�������擾
                l_dblUnitPrice = calcCondition.getEqtypeQuote(l_lngProdcutId, l_lngMarketId);
            }
        }
        
        //�]���P���̒l�ɕύX���Ȃ��ꍇ(�����ȊO ���� ����(�~�j���܂�)�Ŏ������擾�ł��Ȃ��ꍇ)
        if(l_dblUnitPrice == 0.0)
        {
            //�W������
            l_dblUnitPrice = super.getUnitPrice(l_productRow);
        }
        
        return l_dblUnitPrice;
    }

    /**
     * (get�]���P��<��r>)<BR>
     * �Ώۖ���.�]���P����Ԃ��B<BR>
     * @@param l_double - (���l)
     * @@param l_product - (�Ώۖ���)
     * @@return double
     */
    public double getUnitPrice(double l_dblComp, WEB3TPSecurityValuationProduct l_product) 
    {
        //�Ώۖ����̕]���P�����������I�l���͊֌W�Ȃ��A��ɕ]���P����Ԃ�
        return l_product.getUnitPrice();
    }

    /**
     * (get�]���P��<��r�s�v>)<BR>
     * �Ώۖ���.�]���P����Ԃ��B<BR>
     * @@param l_double - (���l)
     * @@param l_product - (�Ώۖ���)
     * @@return double
     */
    public double getUnitPriceNotCompare(double l_dblComp, WEB3TPSecurityValuationProduct l_product) 
    {
        //�Ώۖ����̕]���P�����������I�l���͊֌W�Ȃ��A��ɕ]���P����Ԃ�
        return l_product.getUnitPrice();
    }

    /**
     * (get�]���P��<����>)<BR>
     * �������擾���A������Ԃ��B<BR>
     * �������擾�ł��Ȃ��ꍇ�A<BR>
     * �X�[�p�[�N���X�̃��\�b�h�̖߂�l��Ԃ��B<BR>
     * @@param l_targetContractDetail - (�Ώی��ʏڍ�)
     * @@return double
     */
    public double getUnitPrice(WEB3TPTargetContractDetail l_targetContractDetail) 
    {
        //����ID���擾
        long l_productId = l_targetContractDetail.getProductId();
        
        //�s��ID���擾
        long l_marketId = l_targetContractDetail.getMarketId();
        
        //�������擾
        double l_unitPrice = calcCondition.getEqtypeQuote(l_productId, l_marketId);
        
        //�������擾�ł��Ȃ��ꍇ
        if(l_unitPrice == 0.0)
        {
            //�W������
            l_unitPrice = super.getUnitPrice(l_targetContractDetail);
        }
        
        return l_unitPrice;
    }
}
@
