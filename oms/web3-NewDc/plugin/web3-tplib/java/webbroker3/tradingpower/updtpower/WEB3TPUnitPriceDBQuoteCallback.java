head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.00.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPUnitPriceDBQuoteCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]���P��&lt;DB����&gt;Callback(WEB3TPUnitPriceDBQuoteCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/11/13 ꎉ�(���u) �V�K�쐬 ���f��No.078 - 082
Revesion History : 2009/09/22 �Ԑi (���u) ���f�� No.392
Revesion History : 2009/10/02 �юu�� (���u) ���f�� No.398
*/

package webbroker3.tradingpower.updtpower;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuationProduct;
import webbroker3.tradingpower.updtpower.contract.WEB3TPTargetContractDetail;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�]���P��&lt;DB����&gt;Callback)<BR>
 * 
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3TPUnitPriceDBQuoteCallback extends WEB3TPUnitPriceStandardCallback
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPUnitPriceDBQuoteCallback.class);

    
    /**
     * (�]���P��&lt;DB����&gt;Callback)<BR>
     * (�R���X�g���N�^) <BR>
     * <BR>
     * 1) �e�N���X�i�]���P��<�W��>Callback�j�̃R���X�g���N�^���R�[������B <BR>
     * �@@�@@-super(:�]�͌v�Z����)���R�[������B <BR>
     * <BR>
     * [����] <BR>
     * �@@�]�͌v�Z���� = ����.�]�͌v�Z���� <BR>
     * @@param l_calcCondition - (�]�͌v�Z����)<BR>
     */
    public WEB3TPUnitPriceDBQuoteCallback(WEB3TPCalcCondition l_calcCondition)
    {
        //�e�N���X�i�]���P��<�W��>Callback�j�̃R���X�g���N�^���R�[������B
        super(l_calcCondition);
    }
    
    /**
     * (get�]���P��<�Ώۖ���>) <BR>
     * <BR>
     * ���V�[�P���X�}�u(�]���P��&lt;DB����&gt;Callback)get�]���P��<�Ώۖ���>�v�Q��<BR>
     * @@param l_productRow - (����Row)<BR>
     * @@return double
     */
    public double getUnitPrice(ProductRow l_productRow) 
    {
        final String STR_METHOD_NAME = " getUnitPrice(ProductRow) ";
        log.entering(STR_METHOD_NAME);
        
        //�]���P��Temp = 0�@@���Z�b�g
        double l_dblUnitPriceTemp = 0.0;
        
        long l_lngPrimaryMarketId = 0;
        
        //�v���_�N�g�^�C�v���擾
        ProductTypeEnum l_productTypeEnum = l_productRow.getProductType();
        
        //�����̏ꍇ
        if (ProductTypeEnum.EQUITY.equals(l_productTypeEnum))
        {
            //get�c�Ɠ�
            //����(T+0)�c�Ɠ����擾  
            //[����]  
            //int = 0 
            Date l_datBizDate = this.calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);
            
            //����ID���擾
            long l_lngProdcutId = l_productRow.getProductId();
            
            //�s��ID���擾
            l_lngPrimaryMarketId = l_productRow.getPrimaryMarketId();
                       
            //get�I�l<DB����>
            // �I�l���擾����B  
            // [����]  
            // long = getProductId()�̖߂�l  
            // Date = get�c�Ɠ�()�̖߂�l  
            // long = getPrimaryMarketId()�̖߂�l  
            // boolean = true  
            l_dblUnitPriceTemp = this.calcCondition.getClosingPriceDBQuote(
                l_lngProdcutId, 
                l_datBizDate, 
                l_lngPrimaryMarketId, 
                true);
        }
        
        //�]���P��Temp = 0 �̏ꍇ
        if (l_dblUnitPriceTemp == 0)
        {
            //�]���P��Temp = ProductRow.�]���P��(�O���I�l)���Z�b�g
            l_dblUnitPriceTemp = l_productRow.getEstimationPrice();
           
        }
        
        //�v�Z�����]���P��Temp��ԋp����
        log.exiting(STR_METHOD_NAME);
        return l_dblUnitPriceTemp; 
    }
    
    /**
     * (get�]���P��<��r>)<BR>
     * <BR>
     * ���V�[�P���X�}�u(�]���P��&lt;DB����&gt;Callback)get�]���P��<��r>�v�Q��<BR>
     * @@param l_dblPrice - (�P��)<BR>
     * @@param l_product - (�Ώۖ���)<BR>
     * @@return double
     */
    public double getUnitPrice(double l_dblPrice, WEB3TPSecurityValuationProduct l_product) 
    {
        final String STR_METHOD_NAME = " getUnitPrice(double, WEB3TPSecurityValuationProduct) ";
        log.entering(STR_METHOD_NAME);
        
        double l_dblUnitPrice = l_product.getUnitPrice();
        
        //return MIN(����.�P�� , ����.�Ώۖ���.get�]���P��())
        double l_dblMin = Math.min(l_dblPrice, l_dblUnitPrice);
        
        log.exiting(STR_METHOD_NAME);
        return l_dblMin;
    }
    
    /**
     * (get�]���P��<��r�s�v>)<BR>
     * <BR>
     * ���V�[�P���X�}�u(�]���P��&lt;DB����&gt;Callback)get�]���P��<��r�s�v>�v�Q��<BR>
     * @@param l_dblPrice - (�P��)<BR>
     * @@param l_product - (�Ώۖ���)<BR>
     * @@return double
     */
    public double getUnitPriceNotCompare(double l_dblPrice, WEB3TPSecurityValuationProduct l_product) 
    {
        final String STR_METHOD_NAME = " getUnitPriceNotCompare(double, WEB3TPSecurityValuationProduct) ";
        log.entering(STR_METHOD_NAME);
        
        //return ����.�P��
        log.exiting(STR_METHOD_NAME);
        return l_dblPrice;
    }
    
    /**
     * (get�]���P��<����>))<BR>
     * <BR>
     * ���V�[�P���X�}�u(�]���P��&lt;DB����&gt;Callback)get�]���P��<����>�v�Q��<BR>
     * @@param l_targetContractDetail - (�Ώی��ʏڍ�)
     * @@return double
     */
    public double getUnitPrice(WEB3TPTargetContractDetail l_targetContractDetail) 
    {
        final String STR_METHOD_NAME = " getUnitPrice(WEB3TPTargetContractDetail) ";
        log.entering(STR_METHOD_NAME);
        
        //�]���P��Temp = 0�@@���Z�b�g
        double l_dblUnitPriceTemp = 0.0;
        double l_dblClosingPrice = 0.0;
        
        //get�c�Ɠ�
        Date l_datBizDate0 = this.calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);
        
        //get����
        Date l_datOpenDate = l_targetContractDetail.getOpenDate();
        
        //get��������
        Date l_datFirstOpenDate = l_targetContractDetail.getFirstOpenDate();
        
        //get����ID
        long l_productId = l_targetContractDetail.getProductId();
        
        //get�s��ID
        long l_marketId = l_targetContractDetail.getMarketId();
        
        //get����������
        Date l_datRightsOffDate = this.calcCondition.getRightsOffDate(l_productId);

        boolean l_blnIsDecimalDevide = false;
        try
        {
            //�����{�����̏ꍇ
            l_blnIsDecimalDevide = this.isDecimalDevide(l_productId);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //(���� < T+0 || (���� == T+0 && �������� != null && ���� != ��������) )
        if(WEB3DateUtility.compare(l_datOpenDate, l_datBizDate0) < 0
            || (WEB3DateUtility.compare(l_datOpenDate, l_datBizDate0) == 0
                && l_datFirstOpenDate != null 
                && WEB3DateUtility.compare(l_datOpenDate,l_datFirstOpenDate) != 0))
        {
            //����������=�c�Ɠ�(T+0)�̏ꍇ
            if(WEB3DateUtility.compareToDay(l_datBizDate0, l_datRightsOffDate) == 0
                && l_blnIsDecimalDevide)
            {
                //�]���P��Temp = ����.�Ώی��ʏڍ�.get��l<�O���I�l>
                l_dblUnitPriceTemp = l_targetContractDetail.getLastClosingPrice();
            }
            else
            {
                //�I�l���擾����B
                //long = get����ID()�̖߂�l  
                //Date = get�c�Ɠ�()�̖߂�l  
                //long = get�s��ID()�̖߂�l  
                //boolean = false 
                l_dblClosingPrice = this.calcCondition.getClosingPriceDBQuote(
                    l_productId, 
                    l_datBizDate0, 
                    l_marketId, 
                    false);
                
                //get�I�l=0�̏ꍇ
                if(l_dblClosingPrice == 0)
                {
                    //�]���P��Temp = ����.�Ώی��ʏڍ�.get��l<�O���I�l>
                    l_dblUnitPriceTemp = l_targetContractDetail.getLastClosingPrice();
                }
                //get�I�l!=0�̏ꍇ
                else
                {
                    //�]���P��Temp = get�I�l<DB����>()�̖߂�l
                    l_dblUnitPriceTemp = l_dblClosingPrice;
                }
            }
        }
        
        //�ȊO((�ʏ�) �c������)�̏ꍇ
        else
        {
            //����������=�c�Ɠ�(T+0)�̏ꍇ
            if(WEB3DateUtility.compareToDay(l_datBizDate0, l_datRightsOffDate) == 0 && l_blnIsDecimalDevide)
            {
                //�]���P��Temp = 0
                l_dblUnitPriceTemp = 0.0;
            }
            //����������!=�c�Ɠ�(T+0)�̏ꍇ
            else
            {
                //get�I�l<DB����>
                //long = get����ID()�̖߂�l  
                //Date = get�c�Ɠ�()�̖߂�l  
                //long = get�s��ID()�̖߂�l  
                //boolean = false 
                l_dblClosingPrice = this.calcCondition.getClosingPriceDBQuote(
                    l_productId, 
                    l_datBizDate0, 
                    l_marketId, 
                    false);
                
                //get�I�l=0�̏ꍇ
                if (l_dblClosingPrice == 0)
                {
                    //�]���P��Temp = 0
                    l_dblUnitPriceTemp = 0;                  
                }
                //get�I�l!=0�̏ꍇ
                else
                {
                    //�]���P��Temp = get�I�l<DB����>()�̖߂�l
                    l_dblUnitPriceTemp = l_dblClosingPrice;
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_dblUnitPriceTemp;
    }
}
@
