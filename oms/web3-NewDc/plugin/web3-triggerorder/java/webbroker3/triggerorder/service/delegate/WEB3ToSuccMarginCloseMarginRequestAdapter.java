head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.02.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginCloseMarginRequestAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p����ԍσ��N�G�X�g�A�_�v�^(WEB3ToSuccMarginCloseMarginRequestAdapter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/18�@@���@@��(���u) �V�K�쐬
Revesion History : 2007/01/11  �  ��(���u) �d�l�ύX���f��215
Revesion History : 2007/01/17  ���G��(���u) ���f��221
*/

package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.service.delegate.WEB3MarginCloseMarginRequestAdapter;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccCommonInfo;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseConfirmRequest;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�i�A���j�M�p����ԍσ��N�G�X�g�A�_�v�^)<BR>
 * �i�A���j�M�p����ԍσ��N�G�X�g�A�_�v�^�N���X<BR>
 * 
 * @@author ���@@��(���u)
 * @@version 1.0
 */
public class WEB3ToSuccMarginCloseMarginRequestAdapter extends WEB3MarginCloseMarginRequestAdapter 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginCloseMarginRequestAdapter.class);
    
    /**
     * (�e�����̒����P��)<BR>
     * �e�����̒����P�ʃI�u�W�F�N�g<BR>
     */
    public EqTypeOrderUnit parentOrderUnit;
    
    /**
     * (�P��)<BR>
     * �P���B<BR>
     * �i�w�l�^0�i�����s�j�^���s�P���i�}�w�l�j�j<BR>
     * �����s�����̏ꍇ�̊T�Z��n����v�Z�Ɏg�p���������́A<BR>
     * ���T�Z��n����v�Z����.get�v�Z�P��()�Ŏ擾����B<BR>
     */
    public double price;

    /**
     * @@roseuid 436ACF6900EA
     */
    public WEB3ToSuccMarginCloseMarginRequestAdapter() 
    {
     
    }
    
    /**
     * �i�A���j�M�p����ԍσ��N�G�X�g�A�_�v�^�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �P�j�@@�{�C���X�^���X�𐶐�������B<BR>
     * �Q�j�@@�e�����̒����P�ʃI�u�W�F�N�g���擾����B<BR>
     * �@@�@@�A�������}�l�[�W��.get�����e�����̒����P��(<BR>
     * �@@�@@���N�G�X�g.�A���������ʏ��.�i�e�����j����ID)���R�[������B<BR>
     * <BR>
     * �R�j�@@���������C���X�^���X�ɁA�����̃��N�G�X�g�A<BR>
     * �@@�@@�y�ю擾�����e�����̒����P�ʃI�u�W�F�N�g���Z�b�g����B<BR>
     * �@@�@@�v���p�e�B�̒P���ɂ�0���Z�b�g����B<BR>
     * <BR>
     * �S�j�@@�C���X�^���X��ԋp����B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3MarginCloseMarginRequestAdapter
     * @@roseuid 433246D203A3
     */
    public static WEB3MarginCloseMarginRequestAdapter create(WEB3GenRequest l_request) 
    {
        final String STR_METHOD_NAME = " create(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�{�C���X�^���X�𐶐�������B
        WEB3ToSuccMarginCloseMarginRequestAdapter l_adapter = new WEB3ToSuccMarginCloseMarginRequestAdapter();
 
        //2)�@@�e�����̒����P�ʃI�u�W�F�N�g���擾����B
        //�@@�@@�A�������}�l�[�W��Impl.get�����e�����̒����P��(
        //�@@�@@���N�G�X�g.�A���������ʏ��.�i�e�����j����ID)���R�[������B
        WEB3SuccCommonInfo l_succCommonInfo = null;
        if (l_request instanceof WEB3SuccMarginCloseConfirmRequest)
        {
            WEB3SuccMarginCloseConfirmRequest l_confirmRequest = 
                (WEB3SuccMarginCloseConfirmRequest) l_request;
            l_succCommonInfo = l_confirmRequest.succCommonInfo;
        }
        else if (l_request instanceof WEB3SuccMarginCloseCompleteRequest)
        {
            WEB3SuccMarginCloseCompleteRequest l_completeRequest = 
                (WEB3SuccMarginCloseCompleteRequest) l_request;
            l_succCommonInfo = l_completeRequest.succCommonInfo;
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                "WEB3ToSuccMarginCloseMarginRequestAdapter" + "." + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }
        WEB3ToSuccOrderManagerImpl l_orderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngParentOrderId = Long.parseLong(l_succCommonInfo.parentOrderId);
        EqTypeOrderUnit l_orderUnit = l_orderManager.getEqtypeParentOrderUnit(l_lngParentOrderId);
        
        //�R�j�@@���������C���X�^���X�ɁA�����̃��N�G�X�g�f�[�^�A
        //�@@�@@�y�ю擾�����e�����̒����P�ʃI�u�W�F�N�g���Z�b�g����B
        l_adapter.request = l_request;
        l_adapter.parentOrderUnit = l_orderUnit;
        l_adapter.price = 0;
        
        //�S�j�@@�C���X�^���X��ԋp����B
        log.exiting(STR_METHOD_NAME);   
        return l_adapter;
    }
    
    /**
     * (get�P��)<BR>
     * �P�������N�G�X�g�I�u�W�F�N�g���擾����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�m�F���N�G�X�g�̏ꍇ<BR>
     * <BR>
     * �@@this.�P�� == 0�̏ꍇ�́A <BR>
     * �@@�A�������}�l�[�W��.get�����\�񒍕����s�P��()���R�[�����A<BR>
     * �@@�߂�l��this.�P���ɃZ�b�g����B<BR>
     * �@@���e���������s�����̏ꍇ�A��������Ƃ������s�P�����v�Z���Ă��邽�߁A<BR>
     * �@@���������̎����̕ϓ����l�����A���s�P�����L�����Ă����B<BR>
     * <BR>
     * �@@---------------------------------------------------------<BR>
     * �@@��get�����\�񒍕����s�P��()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�e�����̒����P�ʁF�@@�e�����̒����P��<BR>
     * �@@�w�l�F�@@super.get�P��()�̖߂�l<BR>
     * �@@�P�������l�F�@@���N�G�X�g�f�[�^.�P�������l���==null�̏ꍇ�́Anull�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ȊO�A���N�G�X�g�f�[�^.�P�������l���.get�P�������l()�B<BR>
     * �@@������������F�@@�e�����̒����P��.getTradedProduct()<BR>
     * �@@---------------------------------------------------------<BR>
     * <BR>
     * �@@�P�|�Q�jthis.�P����ԋp����B <BR>
     * <BR>
     * �Q�j�@@�������N�G�X�g�̏ꍇ<BR>
     * <BR>
     * �@@���N�G�X�g.�P�������l���==null�i�w�l�^���s�j�̏ꍇ�́A<BR>
     * super.get�P��()�̖߂�l��ԋp����B <BR>
     * �@@���N�G�X�g.�P�������l���null�i�}�w�l�w��j�̏ꍇ�́A<BR>
     * ���N�G�X�g.������P����ԋp����B <BR>
     * �@@��������P����null�̏ꍇ�́A�u���s�P����null�v�̗�O��throw����B 
     * �@@ �@@ �@@class: WEB3BusinessLayerException<BR>
     * �@@ �@@ �@@tag:   BUSINESS_ERROR_02707<BR>
     * �@@��������P����0�ȉ��̏ꍇ�́A�u���s�P����0�ȉ��v�̗�O��throw����B<BR>
     * �@@ �@@ �@@class: WEB3BusinessLayerException<BR>
     * �@@ �@@ �@@tag:   BUSINESS_ERROR_02298<BR>
     * @@return double
     * @@roseuid 433246D203C2
     */
    public double getPrice() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getPrice()";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOrderManagerImpl l_orderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        if (super.request instanceof WEB3SuccMarginCloseConfirmRequest)
        {
            WEB3SuccMarginCloseConfirmRequest l_confirmRequest = 
                (WEB3SuccMarginCloseConfirmRequest) super.request;
            if (this.price == 0)
            {
                Double l_priceAdjustValue = null;
                if (l_confirmRequest.priceAdjustmentValueInfo != null)
                {
                    l_priceAdjustValue = new Double(
                        l_confirmRequest.priceAdjustmentValueInfo.getPriceAdjustmentValue());
                }  
                  
                this.price = l_orderManager.getReserveEqtypeOrderExecPrice(
                    this.parentOrderUnit,
                    super.getPrice(),
                    l_priceAdjustValue,
                    (WEB3EquityTradedProduct) this.parentOrderUnit.getTradedProduct());
            }
            log.exiting(STR_METHOD_NAME); 
            return this.price;
        }
        else if (super.request instanceof WEB3SuccMarginCloseCompleteRequest)
        {
            WEB3SuccMarginCloseCompleteRequest l_completeRequest = 
                (WEB3SuccMarginCloseCompleteRequest) super.request;
            
            if (l_completeRequest.priceAdjustmentValueInfo == null)
            {
                log.exiting(STR_METHOD_NAME); 
                // super.get�P��()�̖߂�l��ԋp����
                return super.getPrice();
            }      
            else
            {
                //�@@���N�G�X�g.�P�������l���null�i�}�w�l�w��j�̏ꍇ�́A���N�G�X�g.������P����ԋp����B
                //  ��������P����null�̏ꍇ�́A�u���s�P����null�v�̗�O��throw����B 
                if (l_completeRequest.afterAdjustmentPrice == null)
                {
                    log.debug("���s�P����null�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02707,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "���s�P����null�B");
                }
                double l_dblAfterAdjustmentPrice =
                    Double.parseDouble(l_completeRequest.afterAdjustmentPrice);

                if (l_dblAfterAdjustmentPrice <= 0)
                {
                    log.debug("���s�P����0�ȉ��B " + l_dblAfterAdjustmentPrice);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02298,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "���s�P����0�ȉ��B " + l_dblAfterAdjustmentPrice);
                }            
                log.exiting(STR_METHOD_NAME); 
                //���N�G�X�g.������P����ԋp����
                return l_dblAfterAdjustmentPrice;
            }    
        }
        else 
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                "WEB3ToSuccMarginCloseMarginRequestAdapter" + "." + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }
    }
    
    /**
     * (get����)<BR>
     * �����I�u�W�F�N�g��ԋp����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@this.is���Δ���() == true�̏ꍇ�A<BR>
     * �@@�A�������}�l�[�W��Impl.create����()���R�[�����A<BR>
     * �@@�߂�l��ԋp����B<BR>
     * <BR>
     * �@@[create����()�Ɏw�肷�����]<BR>
     * �@@�@@�����P�ʁF�@@this.�e�����̒����P��<BR>
     * �@@�@@<BR>
     * �Q�j�@@�����c�ɑ΂���ԍρi�P�j�ȊO�j�̏ꍇ�A<BR>
     * �@@super.get����()���R�[�����A�߂�l��ԋp����B<BR>
     * @@return WEB3EquityContract
     * @@throws WEB3BaseException
     * @@roseuid 433246D203E1
     */
    public WEB3EquityContract getContract() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getContract()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccOrderManagerImpl l_orderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        
        //1) this.is���Δ���() == true�̏ꍇ�A
        // �@@�A�������}�l�[�W��Impl.create����()���R�[�����A
        // �@@�߂�l��ԋp����B
        WEB3EquityContract l_equityContract = null;
        if (this.isReversingOrder())
        {
            l_equityContract = l_orderManager.createContract(this.parentOrderUnit);
        }
        else 
        {
            //2) �@@�����c�ɑ΂���ԍρi�P�j�ȊO�j�̏ꍇ�A
            // �@@super.get����()���R�[�����A�߂�l��ԋp����B
            l_equityContract = super.getContract();
        }
        log.exiting(STR_METHOD_NAME);
        return l_equityContract;
    }
    
    /**
     * (is���Δ���)<BR>
     * ���Δ������ǂ������ʂ���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.�A���������ʏ��.�A����������敪=="�M�p�ԍρi�O�񒍕��j"�̏ꍇ�́A<BR>
     * true��ԋp����B<BR>
     * <BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 4332486402B8
     */
    public boolean isReversingOrder() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " isReversingOrder()";
        log.entering(STR_METHOD_NAME);

        WEB3SuccCommonInfo l_succCommonInfo = null;
        if (super.request instanceof WEB3SuccMarginCloseConfirmRequest)
        {
            WEB3SuccMarginCloseConfirmRequest l_confirmRequest = 
                (WEB3SuccMarginCloseConfirmRequest) super.request;
            l_succCommonInfo = l_confirmRequest.succCommonInfo;
        }
        else if (super.request instanceof WEB3SuccMarginCloseCompleteRequest)
        {
            WEB3SuccMarginCloseCompleteRequest l_completeRequest = 
                (WEB3SuccMarginCloseCompleteRequest) super.request;
            l_succCommonInfo = l_completeRequest.succCommonInfo;
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                "WEB3ToSuccMarginCloseMarginRequestAdapter" + "." + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }
        if (WEB3ReserveOrderTradingTypeDef.SETTLE_CONTRACT_ASSUMPTION_ORDER.equals(
            l_succCommonInfo.succTradingType))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        log.exiting(STR_METHOD_NAME);
        return false;
    }
}
@
