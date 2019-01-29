head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.59.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPUnitPriceStandardCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]���P��<�W��>Callback(WEB3TPStandardUnitPriceCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/04 �V���@@�h�O (FLJ) �V�K�쐬
Revesion History : 2008/01/22 �����F(���u) ���f��No.236�A248
Revesion History : 2009/09/22 �Ԑi (���u) ���f�� No.393
Revesion History : 2009/10/02 �юu�� (���u) ���f�� No.396,399
*/
package webbroker3.tradingpower.updtpower;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuationProduct;
import webbroker3.tradingpower.updtpower.contract.WEB3TPTargetContractDetail;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;

/**
 * (�]���P��<�W��>Callback)
 */
public class WEB3TPUnitPriceStandardCallback implements WEB3TPUnitPriceCallback
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPUnitPriceStandardCallback.class);
    protected WEB3TPCalcCondition calcCondition;
    
    /**
     * (�]���P��<�W��>Callback)<BR>
     * �R���X�g���N�^<BR>
     * @@param l_calcCondition
     */
    public WEB3TPUnitPriceStandardCallback(WEB3TPCalcCondition l_calcCondition)
    {
        calcCondition = l_calcCondition;
    }

    /**
     * (get�]���P��<�Ώۖ���>)<BR>
     * �����I�l�����݂���ꍇ�͓����I�l��Ԃ��B<BR>
     * �����I�l�����݂��Ȃ��ꍇ�͑O���I�l��Ԃ��B<BR>
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
            //�c�Ɠ�(T+0)���擾
            Date l_datBizDate0 = calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);
            
            //����ID���擾
            long l_productId = l_productRow.getProductId();
            
            //�s��ID���擾
            long l_marketId = l_productRow.getPrimaryMarketId();
            
            //�I�l���擾
            l_dblUnitPrice = calcCondition.getClosingPrice(l_productId, l_datBizDate0, l_marketId, true);
        }        

        //�����^�C�v���O�����̏ꍇ
        else if (ProductTypeEnum.FOREIGN_EQUITY.equals(l_productTypeEnum))
        {
            //getProductId
            long l_lngProdcutId = l_productRow.getProductId();

            //get�O���I�l
            //[����]
            //����ID = getProductId()�̖߂�l
            double l_dblFeqLastClosingPrice = this.calcCondition.getFeqLastClosingPrice(l_lngProdcutId);

            //�]���P��Temp = get�O���I�l
            l_dblUnitPrice = l_dblFeqLastClosingPrice;

            //�]���P��Temp��ԋp����
            return l_dblUnitPrice;
        }

        //�]���P���̒l�ɕύX���Ȃ��ꍇ(�����ȊO ���� ����(�~�j���܂�)�ŏI�l���擾�ł��Ȃ��ꍇ)
        if(l_dblUnitPrice == 0.0)
        {
            //����Row�̕]���P�����Z�b�g
            l_dblUnitPrice = l_productRow.getEstimationPrice();
        }
        
        return l_dblUnitPrice;
    }

    /**
     * (get�]���P��<��r>)<BR>
     * ����.���l�ƑΏۖ����̕]���P����r���ď���������Ԃ��B<BR>
     * @@param l_double - (���l)
     * @@param l_product - (�Ώۖ���)
     * @@return double
     */
    public double getUnitPrice(double l_dblComp, WEB3TPSecurityValuationProduct l_product) 
    {
        return Math.min(l_dblComp, l_product.getUnitPrice());
    }

    /**
     * (get�]���P��<��r�s�v>)<BR>
     * ����.���l��Ԃ��B<BR>
     * @@param l_double - (���l)
     * @@param l_product - (�Ώۖ���)
     * @@return double
     */
    public double getUnitPriceNotCompare(double l_dblComp, WEB3TPSecurityValuationProduct l_product) 
    {
        return l_dblComp;
    }

    /**
     * (get�]���P��<����>)<BR>
     * �m�茚���̏ꍇ<BR>
     * �E���������������̏ꍇ�͑O���I�l��Ԃ��B<BR>
     * �E�����I�l�����݂���ꍇ�͓����I�l��Ԃ��B<BR>
     * �E�����I�l�����݂��Ȃ��ꍇ�͑O���I�l��Ԃ��B<BR>
     * ���������̏ꍇ<BR>
     * �E�����I�l��Ԃ��B<BR>
     * @@param l_targetContractDetail - (�Ώی��ʏڍ�)
     * @@return double
     */
    public double getUnitPrice(WEB3TPTargetContractDetail l_targetContractDetail) 
    {
        final String STR_METHOD_NAME = " getUnitPrice(WEB3TPTargetContractDetail) ";

        //�c�Ɠ�(T+0)���擾
        Date l_datBizDate0 = calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);
        
        //�������擾
        Date l_datOpenDate = l_targetContractDetail.getOpenDate();
        
        //�����������擾
        Date l_datFirstOpenDate = l_targetContractDetail.getFirstOpenDate();
        
        //�]���P��
        double l_dblUnitPrice = 0.0;
        
        //����ID���擾
        long l_productId = l_targetContractDetail.getProductId();
        
        //�s��ID���擾
        long l_marketId = l_targetContractDetail.getMarketId();
        
        //�������������擾
        Date l_datRightsOffDate = calcCondition.getRightsOffDate(l_productId);

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

        //�m�茚���܂��́A�����V�����̏ꍇ
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
                //��l(�O���I�l)���̗p
                l_dblUnitPrice = l_targetContractDetail.getLastClosingPrice();
            }
            else
            {
                //�����I�l���擾
                l_dblUnitPrice = calcCondition.getClosingPrice(l_productId, l_datBizDate0, l_marketId, false);
                
                //�����I�l=0�̏ꍇ(�e�[�u���Ƀ��R�[�h���Ȃ� or ���0�������Ă���)
                if(l_dblUnitPrice == 0.0)
                {
                    //��l(�O���I�l)���̗p
                    l_dblUnitPrice = l_targetContractDetail.getLastClosingPrice();
                }
            }
        }
        //���������̏ꍇ
        else
        {
            //����������<>�c�Ɠ�(T+0)�̏ꍇ(�����������̎��́A�����l0���]���P���ɂȂ�)
            if(WEB3DateUtility.compareToDay(l_datBizDate0, l_datRightsOffDate) != 0
                || (WEB3DateUtility.compareToDay(l_datBizDate0, l_datRightsOffDate) == 0 && !l_blnIsDecimalDevide))
            {
                //�����I�l��]���P���ɃZ�b�g����(�I�l���Ȃ��ꍇ��0�ɂȂ�)
                l_dblUnitPrice = calcCondition.getClosingPrice(l_productId, l_datBizDate0, l_marketId, false);
            }
        }
        
        return l_dblUnitPrice;
    }

    /**
     * (is�����{����)<BR>
     * �����{�������ǂ������ʂ���B<BR> 
     *<BR>
     * �P�j�@@DB����<BR> �@@ 
     * �@@�]�̓f�[�^�\�[�X�A�N�Z�X�Ǘ�.get���������R�[������B<BR> 
     *<BR>
     *�@@[����]<BR> 
     *�@@�����hD = ����. ����ID<BR> 
     *<BR>
     * �Q�j�@@�����{�������ǂ������ʂ���B<BR>�@@ 
     * �@@�Q-�P�j�Ŏ擾������������Row�́u�����{�����t���O�v���h�P�F�����{�����h�̏ꍇ�́A<BR> 
     * �@@��rue��ԋp����B <BR>
     *<BR>
     *�@@�Q-�Q�j�@@��L�ȊO�̏ꍇ�́A<BR> 
     *�@@false��ԋp����B <BR>
     * @@param l_lngProductId - (����ID)
     * ����ID
     * @@return boolean
     * @@throws WEB3BaseException
     */	
    protected boolean isDecimalDevide(long l_lngProductId) throws WEB3BaseException
    {

    	EqtypeProductRow l_eqtypeProductRow =
            WEB3TPPersistentDataManager.getInstance().getEqtypeProduct(l_lngProductId);

        if (l_eqtypeProductRow != null)
        {
            //�P�j�Ŏ擾�����u�����{�����t���O�v���h�P�F�����{�����h�̏ꍇ��
            //��rue��ԋp����B
            if (BooleanEnum.TRUE.equals(l_eqtypeProductRow.getDecimalDevideFlag()))
            {
                return true;
            }
        }

        return false;
    }
}
@
