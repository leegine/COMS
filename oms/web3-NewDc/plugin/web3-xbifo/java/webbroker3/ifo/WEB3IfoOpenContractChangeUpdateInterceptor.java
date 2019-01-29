head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.44.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoOpenContractChangeUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�V�K�������X�V�C���^�Z�v�^�N���X(WEB3IfoOpenContractChangeUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/10 䈋� (���u) �V�K�쐬
Revesion History : 2006/07/13 �s�p (���u) DB�X�V�d�lNo.089,095
Revesion History : 2007/01/29 �đo�g (���u) DB�X�V�d�lNo.132,140
*/
package webbroker3.ifo;


import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import webbroker3.ifo.define.WEB3IfoOrderModifyCancelTypeDef;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;

/**
 * (�敨OP�V�K�������X�V�C���^�Z�v�^)<BR>
 * �敨OP�V�K�������X�V�C���^�Z�v�^�N���X<BR>
 * @@author  䈋�
 * @@version 1.0
 */
public class WEB3IfoOpenContractChangeUpdateInterceptor
    extends WEB3IfoOrderUpdateInterceptor
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoOpenContractChangeUpdateInterceptor.class);
    /**
     * �V�K���������e�I�u�W�F�N�g
     */
    private WEB3IfoOpenContractChangeSpec ifoOpenContractOrderSpec;

	/**
	 * �����ID
	 */
	private long traderId;
    
	/**
	 * �����o�H�敪
	 */
	private String orderRootDiv;
    
    /**
     * @@roseuid 40C07C030242
     */
    public WEB3IfoOpenContractChangeUpdateInterceptor()
    {

    }

    /**
     * (�敨OP�V�K�������X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �C���X�^���X�𐶐����A�����̐敨OP�V�K�������ʂ��v���p�e�B�ɃZ�b�g����B<BR>
     * @@param l_openContractChangeSpec - �V�K���������e<BR>
     * �V�K���������e�I�u�W�F�N�g<BR>
     * @@return webbroker3.ifo.WEB3IfoOpenContractChangeUpdateInterceptor
     * @@roseuid 4069578201A5
     */
    public WEB3IfoOpenContractChangeUpdateInterceptor(WEB3IfoOpenContractChangeSpec l_openContractChangeSpec)
    {
        ifoOpenContractOrderSpec = l_openContractChangeSpec;
    }
    
    /**
     * (set�����ID)<BR>
     */
    public void setTraderId (long l_lngTraderID)
    {
        traderId = l_lngTraderID;
    }
	/**
	 * (set�����o�H�敪)
	 */
	public void setOrderRootDiv(String l_strOrderRootDiv)
	{
		orderRootDiv = l_strOrderRootDiv;
	}
    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB<BR>
     * <BR>
     * �@@�v���p�e�B�̓��e���A�p�����[�^.�����P��Params�̊g�����ڂɒl���Z�b�g���A<BR>�E
     * �p����B<BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V<BR>
     *   �����P��.�敨�^�I�v�V�����敪 == "�敨"�̏ꍇ 
     *      �u�敨�V�K������_�����P�ʃe�[�u���d�l.xls�v�Q�� 
     *   �����P��.�敨�^�I�v�V�����敪 == "�I�v�V����"�̏ꍇ
     *      �uOP�V�K������_�����P�ʃe�[�u���d�l.xls�v�Q�� 
     * 
     * @@param l_orderManagerPersistenceType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * <BR>
     * OrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     * 
     * @@param l_orderManagerPersistenceContext - (����)<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * @@param l_ifoOrderUnitParams - (�����P��Params)<BR>
     * ���������P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
     * @@return webbroker3.ifo.data.IfoOrderUnitParams
     * @@roseuid 406957D2034B
     */
    public IfoOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        IfoOrderUnitParams l_ifoOrderUnitParams)
    {
        final String STR_METHOD_NAME = getClass().getName()
                + ".mutate(OrderManagerPersistenceType "
                + "l_orderManagerPersistenceType, "
                + "OrderManagerPersistenceContext "
                + "l_orderManagerPersistenceContext, "
                + "IfoOrderUnitParams l_ifoOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        if (l_ifoOrderUnitParams == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+  "." + STR_METHOD_NAME);          
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        OrderUnit l_orderUnit = null;

        //throws NotfoundException
        l_orderUnit = l_finApp.getTradingModule(
            ProductTypeEnum.IFO).getOrderManager().toOrderUnit(l_ifoOrderUnitParams);

        // ���s����<br>
        if (ifoOpenContractOrderSpec == null)
        {
            log.error(
                STR_METHOD_NAME,
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME));
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }
        IfoOrderExecutionConditionType l_IfoOrderExecutionConditionType =
            ifoOpenContractOrderSpec.getChangeExecCondType();
        l_ifoOrderUnitParams.setExecutionConditionType(l_IfoOrderExecutionConditionType);

        if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCond))
        {
            // (�����������Z�q)<BR>       
            l_ifoOrderUnitParams.setOrderCondOperator(null);

            // (�t�w�l��l�^�C�v)<BR>      
            l_ifoOrderUnitParams.setStopPriceType(null);

            // (�t�w�l��l)<BR>
            l_ifoOrderUnitParams.setStopOrderPrice(null);

            // �iW�w�l�j�����w�l)<BR>        
            l_ifoOrderUnitParams.setWLimitPrice(null);
        }
        else
        {
            // (�����������Z�q)<BR>       
            l_ifoOrderUnitParams.setOrderCondOperator(this.orderCondOperator);

            // (�t�w�l��l�^�C�v)<BR>      
            l_ifoOrderUnitParams.setStopPriceType(this.stopOrderBasePriceType);

            // (�t�w�l��l)<BR>
            l_ifoOrderUnitParams.setStopOrderPrice(this.stopOrderBasePrice);
            
            // �iW�w�l�j�����w�l)<BR>            
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCond))
            {
                l_ifoOrderUnitParams.setWLimitPrice(null);
            }
            else
            {
                l_ifoOrderUnitParams.setWLimitPrice(this.wLimitPriceChange);
            }                    
        }

        //�����������t 
        l_ifoOrderUnitParams.setExpirationDate(
        WEB3DateUtility.toDay(ifoOpenContractOrderSpec.getChangeExpirationDate()));

        //�����ID
        // �C���^�Z�v�^.�����ID
        if (traderId == 0)
        {   
            l_ifoOrderUnitParams.setTraderId(null);
        }
        else
        {
            l_ifoOrderUnitParams.setTraderId(traderId);
        }
        
        //�������
        if (l_ifoOrderUnitParams.getConfirmedQuantityIsNull())
        {
            //���������s�ꖢ���M(�s�ꂩ��m�F�ς݂̐��� == NaN)�̏ꍇ�F10:�����ς݁i�ύX�����j
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);
        }
        else
        {
            //���������s�ꑗ�M��(�s�ꂩ��m�F�ς݂̐��� != NaN)�̏ꍇ�F7:��t�ρi�ύX�����j
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
        }


        //�����P��
        double l_price;
        if (this.estimateDeliveryAmounCalcResult == null)
        {
            log.error(
                STR_METHOD_NAME,
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME));
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }
        l_price = this.estimateDeliveryAmounCalcResult.getCalcUnitPrice();
        l_ifoOrderUnitParams.setPrice(l_price);

        //�T�Z��n���
        double l_estimatePrice;
        l_estimatePrice =
            this.estimateDeliveryAmounCalcResult.getEstimateDeliveryAmount();
        l_ifoOrderUnitParams.setEstimatedPrice(l_estimatePrice);

        //���������E����敪
        //�������s�ꑗ�M��(�s�ꂩ��m�F�ς݂̐��� != NaN)�̏ꍇ�́A5:������    
        if (!l_ifoOrderUnitParams.getConfirmedQuantityIsNull())
        {
            l_ifoOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CHANGING);
        }
        else
        {
            if (l_orderUnit.isUnexecuted())
            {
                //this.isUnexecuted( )==true�̏ꍇ�́A7�F�S�����������B
                l_ifoOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CHANGED);
            }
            else
            {
                //this.isUnexecuted( )==false�̏ꍇ�́A6�F�ꕔ���������B
                l_ifoOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.PARTIALLY_CHANGED);
            }
        }

        //�C���^�Z�v�^���擾���������ڂ̒l
        String l_orderRootDiv = this.orderRootDiv;
        l_ifoOrderUnitParams.setOrderRootDiv(l_orderRootDiv);

        //0000�F����
        l_ifoOrderUnitParams.setErrorReasonCode(WEB3IfoOrderModifyCancelTypeDef.ERROR_STATUS_NORMAL);
        
        //�iW�w�l�j���s����
        //�V�K���������e.get�iW�w�l�j���s����( )
        //�������������i0�FDEFAULT�i�����w��Ȃ��j�A1�F�t�w�l�j�̏ꍇ�́Anull
        if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCond))
        {
            l_ifoOrderUnitParams.setWLimitExecCondType(null);
        }
        else
        {
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCond))
            {
                l_ifoOrderUnitParams.setWLimitExecCondType(null);
            }
            else
            {
                l_ifoOrderUnitParams.setWLimitExecCondType(
                    this.ifoOpenContractOrderSpec.getWLimitExecCondType());
            }
        }

        OrderUnit l_orderUnitAfter =
            l_finApp.getTradingModule(
                ProductTypeEnum.IFO).getOrderManager().toOrderUnit(l_ifoOrderUnitParams);
        WEB3IfoFrontOrderService l_ifoOrderService =
            (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
        try
        {
            //�����o�H�敪
            //�敨OP�����T�[�r�X.get��������������o�H�敪()�̖߂�l
            String l_strOrderRouteDiv =
                l_ifoOrderService.getChangeSubmitOrderRouteDiv((IfoOrderUnit)l_orderUnit);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv(l_strOrderRouteDiv);
    
            //����Rev.
            //�敨OP�����T�[�r�X.get����������Rev()�̖߂�l
            String l_strOrderRev = l_ifoOrderService.getChangeOrderRev((IfoOrderUnit)l_orderUnitAfter);
            l_ifoOrderUnitParams.setOrderRev(l_strOrderRev);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_ifoOrderUnitParams;
    }

    /**
     * (�X�V�l�ݒ�)<BR>
     * (mutate���\�b�h�̎����j<BR>
     * ��������Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB<BR>
     * �v���p�e�B�̓��e���A�p�����[�^.��������Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B<BR>
     * �P�j�X�[�p�[�N���X�̍X�V�l�ݒ�()���\�b�h���R�[������B<BR>
     * �Q�j�����C�x���g�^�C�v�Ɂh�Q�F�ύX�����h���Z�b�g����B<BR>
     *�y��Trade�z�⑫����.DB�X�V<BR>
     * �I�v�V�����̏ꍇ<BR>
     *  �uOP�V�K������_���������e�[�u���d�l.xls�v�Q�ƁB<BR>
     * �敨�̏ꍇ<BR>
     *  �u�敨�V�K������_���������e�[�u���d�l.xls�v�Q�ƁB<BR>
     * @@param l_orderManagerPersistenceType - (�X�V�^�C�v)<BR>
     *   INSERT�܂��́AUPDATE�B<BR>
     *   OrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     * @@param l_orderManagerPersistenceContext - (����)<BR>
     *  �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * @@param l_ifoOrderActionParams - (�����P��Params)<BR>
     *   ���������P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
     * @@return webbroker3.ifo.data.IfoOrderActionParams
     * @@roseuid 406957D2034B
     */
    public IfoOrderActionParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        IfoOrderActionParams l_ifoOrderActionParams)
    {
        final String STR_METHOD_NAME = "��������Params�Ɋg������:mutate()";
        log.entering(STR_METHOD_NAME);

        if (l_ifoOrderActionParams == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+  "." + STR_METHOD_NAME);          
        }

        super.mutate(l_orderManagerPersistenceType, l_orderManagerPersistenceContext, l_ifoOrderActionParams);
        l_ifoOrderActionParams.setOrderEventType(OrderEventTypeEnum.CHANGE);

        log.exiting(STR_METHOD_NAME);
        return l_ifoOrderActionParams;
    }

}
@
