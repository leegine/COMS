head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoSettleContractChangeUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�ԍϒ����X�V�C���^�Z�v�^(WEB3IfoSettleContractChangeUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/11 Ḗ@@�� (���u) �V�K�쐬
Revesion History : 2006/07/13 �s�p (���u) DB�X�V�d�lNo.090,093,108
Revesion History : 2007/01/29 �đo�g (���u) DB�X�V�d�lNo.136,152
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
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
 * (�敨OP�ԍϒ����X�V�C���^�Z�v�^)<BR>
 * �敨OP�ԍϒ����X�V�C���^�Z�v�^�N���X<BR>
 * @@author Ḗ@@��
 * @@version 1.0 
 */
public class WEB3IfoSettleContractChangeUpdateInterceptor extends WEB3IfoOrderUpdateInterceptor 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoSettleContractChangeUpdateInterceptor.class);
  
    /**
     * (�ԍϒ������e)<BR>
     * �ԍϒ������e�I�u�W�F�N�g<BR>
     */
    private WEB3IfoChangeSettleContractOrderSpec SettleContractChangeSpc;
    
    /**
     * (���Ϗ���)
     * 0�F�����_���@@1�F�P���v���@@2�F�P�������@@3�F������<BR>
     * �ꊇ�ԍώ��̏ꍇ�ݒ�<BR>
     */
    private String settleSequence;
    
	/**
	 * �����o�H�敪
	 */
	private String orderRootDiv;
    
    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * 
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB<BR>
     * 
     * �@@�v���p�e�B�̓��e���A�p�����[�^.�����P��Params�̊g�����ڂɒl���Z�b�g���A<BR>�E
     * �p����B<BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V<BR>
     *   �����P��.�敨�^�I�v�V�����敪 == "�敨"�̏ꍇ<BR> 
     *      �u�敨�ԍϒ���_�����P�ʃe�[�u���d�l.xls�v�Q�� <BR>
     *   �����P��.�敨�^�I�v�V�����敪 == "�I�v�V����"�̏ꍇ<BR>
     *      �uOP�ԍϒ���_�����P�ʃe�[�u���d�l.xls�v�Q��<BR> 
     * @@param l_updateType - �X�V�^�C�v<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * <BR>
     * OrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     * 
     * @@param l_process - ����<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * @@param l_orderUnitParams - �����P��Params<BR>
     * ���������P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
     * @@return webbroker3.ifo.data.IfoOrderUnitParams
     * @@roseuid 40695893013F
     */
    public IfoOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_process, 
        IfoOrderUnitParams l_orderUnitParams) 
    {

        final String STR_METHOD_NAME =
            ".mutate(OrderManagerPersistenceType l_updateType," +
            "OrderManagerPersistenceContext l_process, " +
            "IfoOrderUnitParams l_orderUnitParams)"; 
        log.entering(STR_METHOD_NAME);
        
        if(l_orderUnitParams == null)
        {
            log.debug("Enter the path that the l_orderUnitParams is null.");
            log.error(STR_METHOD_NAME,new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+ STR_METHOD_NAME ));
            log.debug("Exit the path that the l_orderUnitParams is null.");
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,this.getClass().getName()+  "." + STR_METHOD_NAME);
        } 
        
        try
        {
            if (traderId == 0)
            {   
                l_orderUnitParams.setTraderId(null);
            }
            else
            {
                l_orderUnitParams.setTraderId(traderId);
            }

            //���s����
            l_orderUnitParams.setExecutionConditionType(SettleContractChangeSpc.getChangeExecCondType());
            
            //�C���^�Z�v�^.����������ݒ肷��
            l_orderUnitParams.setOrderConditionType(this.orderCond);
            
            if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCond))
            {               
                //�C���^�Z�v�^.�����������Z�q��ݒ肷��
                l_orderUnitParams.setOrderCondOperator(null);   
                
                //�C���^�Z�v�^.�t�w�l��l�^�C�v��ݒ肷��
                l_orderUnitParams.setStopPriceType(null);
                
                //�C���^�Z�v�^.�t�w�l��l��ݒ肷��
                l_orderUnitParams.setStopOrderPrice(null);
                //�iW�w�l�j�����w�l)
                l_orderUnitParams.setWLimitPrice(null);
            }
            else
            {                
                //�C���^�Z�v�^.�����������Z�q��ݒ肷��
                l_orderUnitParams.setOrderCondOperator(this.orderCondOperator);                
                log.debug("�����������Z�q:"+ this.orderCondOperator);
                
                //�C���^�Z�v�^.�t�w�l��l�^�C�v��ݒ肷��
                l_orderUnitParams.setStopPriceType(this.stopOrderBasePriceType);
                
                //�C���^�Z�v�^.�t�w�l��l��ݒ肷��
                l_orderUnitParams.setStopOrderPrice(this.stopOrderBasePrice);   
                
                // �iW�w�l�j�����w�l)           
                if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCond))
                {
                    l_orderUnitParams.setWLimitPrice(null);
                }
                else
                {
                    l_orderUnitParams.setWLimitPrice(this.wLimitPriceChange);
                }                   
            }                   
            //�����������t 
            l_orderUnitParams.setExpirationDate(WEB3DateUtility.toDay(this.SettleContractChangeSpc.getChangeExpirationDate()));
            log.debug("get the value:" + l_orderUnitParams.getExpirationDate().toString() + "is the same as the set value:" + this.SettleContractChangeSpc.getChangeExpirationDate().toString());                        
            
            //���������s�ꖢ���M(�s�ꂩ��m�F�ς݂̐��� == NaN)�̏ꍇ�F10:�����ς݁i�ύX�����j
            if (l_orderUnitParams.getConfirmedQuantityIsNull())
            {
                l_orderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);
            }
            //���������s�ꑗ�M��(�s�ꂩ��m�F�ς݂̐��� != NaN)�̏ꍇ�F7:��t�ρi�ύX�����j
            else
            {
                l_orderUnitParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
            }
            
            //�����G���[���R�R�[�h 
            l_orderUnitParams.setErrorReasonCode(WEB3IfoOrderModifyCancelTypeDef.ERROR_STATUS_NORMAL);
            log.debug("get the value:" + l_orderUnitParams.getErrorReasonCode() + "is the same as the set value:" + WEB3IfoOrderModifyCancelTypeDef.ERROR_STATUS_NORMAL);                        

            //�����P��              
            l_orderUnitParams.setPrice(this.estimateDeliveryAmounCalcResult.getCalcUnitPrice());
            log.debug("get the value:" + l_orderUnitParams.getPrice() + "is the same as the set value:" + this.estimateDeliveryAmounCalcResult.getCalcUnitPrice());                        
            //�T�Z��n��� 
            l_orderUnitParams.setEstimatedPrice(this.estimateDeliveryAmounCalcResult.getEstimateDeliveryAmount());
            log.debug("get the value:" + l_orderUnitParams.getEstimatedPrice() + "is the same as the set value:" + this.estimateDeliveryAmounCalcResult.getEstimateDeliveryAmount());                        
            //get �����o�H�敪                
            String l_strOrderRootDiv =
                this.orderRootDiv;
            //�����o�H�敪
            l_orderUnitParams.setOrderRootDiv(l_strOrderRootDiv);
            //���������E����敪                     
            //�擾�����P�ʃI�u�W�F�N�g
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            
            WEB3OptionOrderManagerImpl l_orderMgr = 
                (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();                       
            long l_lngOrderId = l_orderUnitParams.getOrderUnitId();
            OrderUnit l_orderUnit = l_orderMgr.getOrderUnit(l_lngOrderId);
            //���������E����敪���Z�b�g
            
            //�������s�ꑗ�M��(�s�ꂩ��m�F�ς݂̐��� != NaN)�̏ꍇ�́A5:������    
            if (!l_orderUnitParams.getConfirmedQuantityIsNull())
            {
                l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CHANGING);
            }
            else
            {
                //this.isUnexecuted( )==true�̏ꍇ�́A7�F�S�����������B
                if (l_orderUnit.isUnexecuted())
                {
                    l_orderUnitParams.setModifyCancelType(
                        WEB3ModifyCancelTypeDef.CHANGED);
                }
                
                //this.isUnexecuted( )==false�̏ꍇ�́A6�F�ꕔ���������B
                else
                {
                    l_orderUnitParams.setModifyCancelType(
                        WEB3ModifyCancelTypeDef.PARTIALLY_CHANGED);
                }
            }     
            
            //�iW�w�l�j���s����
            //�ԍϒ������e.get�iW�w�l�j���s����( )
            //�������������i0�FDEFAULT�i�����w��Ȃ��j�A1�F�t�w�l�j�̏ꍇ�́Anull
            if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCond))
            {
                l_orderUnitParams.setWLimitExecCondType(null);
            }
            else
            {
                if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCond))
                {
                    l_orderUnitParams.setWLimitExecCondType(null);
                }
                else
                {
                    l_orderUnitParams.setWLimitExecCondType(
                        this.SettleContractChangeSpc.getWLimitExecCondType());
                }
            }

            OrderUnit l_orderUnitAfter = l_orderMgr.toOrderUnit(l_orderUnitParams);
            WEB3IfoFrontOrderService l_ifoOrderService =
                (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
            //�����o�H�敪
            //�敨OP�����T�[�r�X.get��������������o�H�敪()�̖߂�l
            String l_strOrderRouteDiv =
                l_ifoOrderService.getChangeSubmitOrderRouteDiv((IfoOrderUnit)l_orderUnit);
            l_orderUnitParams.setSubmitOrderRouteDiv(l_strOrderRouteDiv);

            //����Rev.
            //�敨OP�����T�[�r�X.get����������Rev()�̖߂�l
            String l_strOrderRev = l_ifoOrderService.getChangeOrderRev((IfoOrderUnit)l_orderUnitAfter);
            l_orderUnitParams.setOrderRev(l_strOrderRev);

            log.debug("Exit the try path.");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch(Exception l_ex)
        {
             log.error(
                  "__an unexpected error__",
                   new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                   this.getClass().getName() + STR_METHOD_NAME,
                   l_ex.toString(),
                   l_ex));   
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
    
    /**
     *�imutate���\�b�h�̎����j<BR>
     * ��������Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB<BR>
     * �v���p�e�B�̓��e���A�p�����[�^.��������Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B<BR>
     * �P�j�X�[�p�[�N���X�̍X�V�l�ݒ�()���\�b�h���R�[������B<BR>
     * �Q�j�����C�x���g�^�C�v�Ɂh�Q�F�ύX�����h���Z�b�g����B<BR>
     *�y��Trade�z�⑫����.DB�X�V<BR>
     * �I�v�V�����̏ꍇ<BR>
     *  �uOP�ԍϒ���_���������e�[�u���d�l.xls�v�Q�ƁB<BR>
     * �敨�̏ꍇ<BR>
     *  �u�敨�ԍϒ���_���������e�[�u���d�l.xls�v�Q�ƁB<BR>
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
    
    /**
     * (�ԍϒ����X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �C���X�^���X�𐶐����A�����̕ԍϒ����ʃG���g�����v���p�e�B�ɃZ�b�g����B<BR>
     * @@param l_settleContractChangeSpec - �ԍϒ������e
     * �ԍϒ������e�I�u�W�F�N�g
     * @@return webbroker3.ifo.WEB3IfoSettleContractChangeUpdateInterceptor
     * @@roseuid 40695893015E
     */
    public WEB3IfoSettleContractChangeUpdateInterceptor(WEB3IfoChangeSettleContractOrderSpec l_settleContractChangeSpec) 
    {
        this.SettleContractChangeSpc = l_settleContractChangeSpec;    
    }
    
    /**
     * (set���Ϗ���)<BR>
     * ���Ϗ������Z�b�g����B<BR>
     * @@param l_strSettleSequence - ���Ϗ���<BR>
     * <BR>
     * 0�F�����_���@@1�F�P���v���@@2�F�P�������@@3�F������<BR>
     * �ꊇ�ԍώ��̏ꍇ�ݒ�<BR>
     * @@roseuid 40695893016D
     */
    public void setSettleSequence(String l_strSettleSequence) 
    {
        this.settleSequence = l_strSettleSequence; 
    }
    
    /**
     * (get���Ϗ���)<BR>
     * ���Ϗ������擾����B<BR>
     * @@return String
     * @@roseuid 4069589301AC
     */
    public String getSettleSequence() 
    {
        //return null;
        return this.settleSequence;
    }
    
    /**
     * �����ID
     */
    private long traderId;
    
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
}
@
