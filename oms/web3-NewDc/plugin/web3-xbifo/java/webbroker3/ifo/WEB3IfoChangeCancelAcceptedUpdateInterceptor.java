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
filename	WEB3IfoChangeCancelAcceptedUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright       : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name       : �敨OP���������t�X�V�C���^�Z�v�^(WEB3IfoChangeCancelAcceptedUpdateInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11   ���Ō� (Sinocom) �V�K�쐬 
                 : 2006/07/13 �s�p (���u) DB�X�V�d�l No.100
                   2006/11/29 ����� (���u) DB�X�V�d�l No.122,123
*/
package webbroker3.ifo;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;

import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.ifo.define.WEB3IfoOrderModifyCancelTypeDef;
import webbroker3.ifo.define.WEB3IfoWLimitEnableStatusDivDef;


/**
 * (�敨OP���������t�X�V�C���^�Z�v�^)<BR>
 * �敨OP���������t�X�V�C���^�Z�v�^�N���X<BR>
 *�i����G���[�^�����G���[�̏ꍇ�A�������͑O�̏�Ԃɒ������e��߂��j<BR>
 * @@author  ���Ō�
 * @@version 1.0
 */
public class WEB3IfoChangeCancelAcceptedUpdateInterceptor extends WEB3IfoOrderUpdateInterceptor 
{
    /**
    * ���O���[�e�B���e�B<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoChangeCancelAcceptedUpdateInterceptor.class);
               
    /**
     * �G���[�R�[�h<BR>
     */
    private String errorCode;
    
    /**
     * @@roseuid 40C07C09008C
     */
    public WEB3IfoChangeCancelAcceptedUpdateInterceptor() 
    {
     
    }
    
    /**
     * (�����t�X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �����̃G���[�R�[�h�����g�̃v���p�e�B�ɃZ�b�g�A���g�̃C���X�^���X��ԋp����B<BR>
     * @@param l_strErrorCode - �G���[�R�[�h
     * 
     * @@return webbroker3.ifo.WEB3IfoChangeCancelAcceptedUpdateInterceptor
     * @@roseuid 40838AEB0242
     */
    public WEB3IfoChangeCancelAcceptedUpdateInterceptor(String l_strErrorCode) 
    {
        this.errorCode = l_strErrorCode;
    }
    
    /**
     * (�X�V�l�ݒ�)<BR>
     *�imutate���\�b�h�̎����j<BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB<BR>
     * <BR>
     * �P�j �g�����ڃZ�b�g<BR>
     * �i������Ԃ��h�������s�i�ύX�����j�FNOT_MODIFIED�h �܂���<BR> 
     * �h�������s�i��������j�FNOT_CANCELLED�h �̏ꍇ<BR>
     * �̂ݏ��������{����B
     * �@@�ȊO�́A���������̂܂ܕԋp���������I������j<BR>
     * <BR>
     * �X�V���e�́A�u��OP���������t_�����P�ʃe�[�u��.xls�v��<BR>
     * �u�i��OP���������t[������t�G���[]�j�����P�ʃe�[�u���v�V�[�g�A<BR>
     * �u�i��OP���������t[�����t�G���[]�j�����P�ʃe�[�u���v�V�[�g�Q�ƁB<BR>
     * �u�iFutures���������t[������t�G���[]�j�����P�ʃe�[�u���v�V�[�g�A<BR>
     * �u�iFutures���������t[�����t�G���[]�j�����P�ʃe�[�u���v�V�[�g�Q�ƁB<BR>
     * @@param l_updateType - INSERT�܂��́AUPDATE�B<BR>
     * <BR>
     * OrderManagerPersistenceType�ɂĒ萔��`�B
     * 
     * @@param l_dealing - �iOrderManagerPersistenceContext�ɂĒ萔��`�j
     * @@param l_orderUnitParams - �����P��Params<BR>
     * ���������P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
     * @@return webbroker3.ifo.data.IfoOrderUnitParams
     * @@roseuid 40838AEB023E
     */
    public IfoOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_dealing, 
        IfoOrderUnitParams l_orderUnitParams) 
    {
        final String STR_METHOD_NAME = this.getClass().getName()  + 
            "mutate( l_updateType, l_dealing,l_orderUnitParams";
        log.entering( STR_METHOD_NAME);

        if(l_orderUnitParams == null)
        {        
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+ "." + STR_METHOD_NAME);
        }
        
        //���@@(*1)OP�����}�l�[�W��.is�X�g�b�v�����ؑ֒�()��true�̏ꍇ�A�X�g�b�v�����ؑ֒��B
        //�@@�@@�����ɐݒ肷�钍���P�ʂɂ́A�X�V�O�̒����P�ʂ��w�肷��
        //�@@�@@�iIfoOrderUnitParams��OP�����}�l�[�W��.toOrderUnit()�ɂĒ����P�ʌ^�ɂ���j
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_opOrderMgr = (WEB3OptionOrderManagerImpl)
            l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        IfoOrderUnitParams l_orderUnitParamsCopy = new IfoOrderUnitParams(l_orderUnitParams);
        IfoOrderUnit l_orderUnit = (IfoOrderUnit)
            l_opOrderMgr.toOrderUnit(l_orderUnitParamsCopy);
        boolean l_blnIsStopOrderChanging = false;
        try
        {
            l_blnIsStopOrderChanging = l_opOrderMgr.isStopOrderSwitching(l_orderUnit);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex); 
        }

        if (OrderStatusEnum.MODIFYING.equals(l_orderUnitParams.getOrderStatus()) ||
            OrderStatusEnum.CANCELLING.equals(l_orderUnitParams.getOrderStatus()))
        {            
            //���������E����敪
            //�X�g�b�v�����ؑ֒�(*1)�̏ꍇ�A
            if (l_blnIsStopOrderChanging)
            {
                //A�FW�w�l�����ؑ֒�
                l_orderUnitParams.setModifyCancelType(
                    WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFERING);
            }
        }
        else if (OrderStatusEnum.NOT_MODIFIED.equals(l_orderUnitParams.getOrderStatus()) )
        {
            log.debug("������Ԃ��h�������s�i�ύX�����j�FNOT_MODIFIED�h�̏ꍇ");
            
            //�s�ꂩ��m�F�ς̎��s����
            l_orderUnitParams.setExecutionConditionType(l_orderUnitParams.getConfirmedExecConditionType());

            if (l_blnIsStopOrderChanging)
            {
                //����������
                l_orderUnitParams.setOrgOrderConditionType(l_orderUnitParams.getOrderConditionType());

                //�������������Z�q
                l_orderUnitParams.setOrgOrderCondOperator(l_orderUnitParams.getOrderCondOperator());

                //���t�w�l��l�^�C�v
                l_orderUnitParams.setOrgStopPriceType(l_orderUnitParams.getStopPriceType());

                //���t�w�l��l
                if (!l_orderUnitParams.getStopOrderPriceIsNull())
                {
                    l_orderUnitParams.setOrgStopOrderPrice(l_orderUnitParams.getStopOrderPrice());
                }

                //���iW�w�l�j�����w�l
                if (!l_orderUnitParams.getWLimitPriceIsNull())
                {
                    l_orderUnitParams.setOrgWLimitPrice(l_orderUnitParams.getWLimitPrice());
                }

                //���iW�w�l�j���s����
                l_orderUnitParams.setOrgWLimitExecCondType(l_orderUnitParams.getWLimitExecCondType());

                //��������
                l_orderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);

                //�����������Z�q
                l_orderUnitParams.setOrderCondOperator(null);

                //�t�w�l��l�^�C�v
                l_orderUnitParams.setStopPriceType(null);

                //�t�w�l��l
                l_orderUnitParams.setStopOrderPrice(null);

                //�iW�w�l�j�����w�l
                l_orderUnitParams.setWLimitPrice(null);

				if (this.estimateDeliveryAmounCalcResult != null)
				{
				    //�����P��
				    //�X�g�b�v�����ؑ֒�(*1)�̏ꍇ ���� �T�Z����v�Z���ʂ�null�ȊO�̏ꍇ
				    //�T�Z����v�Z����.get�v�Z�P��
				    l_orderUnitParams.setPrice(this.estimateDeliveryAmounCalcResult.getCalcUnitPrice());

				    //�T�Z��n���
				    //�X�g�b�v�����ؑ֒�(*1)�̏ꍇ ���� �T�Z����v�Z���ʂ�null�ȊO�̏ꍇ
				    //�T�Z����v�Z����.get�T�Z��n���
				    l_orderUnitParams.setEstimatedPrice(this.estimateDeliveryAmounCalcResult.getEstimateDeliveryAmount());
				}
				else
				{
                    //�ȊO�A�s�ꂩ��m�F�ς݂̒����P��
					if (!l_orderUnitParams.getConfirmedOrderPriceIsNull())
				    {
                        l_orderUnitParams.setPrice(l_orderUnitParams.getConfirmedOrderPrice());
					}

                    //�ȊO�A�s�ꂩ��m�F�ς݂̊T�Z��n���
                    if (!l_orderUnitParams.getConfirmedEstimatedPriceIsNull())
                    {
                        l_orderUnitParams.setEstimatedPrice(l_orderUnitParams.getConfirmedEstimatedPrice());
                    }
			    }

                //�s�ꂩ��m�F�ς݂̒����P��
                if (!l_orderUnitParams.getPriceIsNull())
                {
                    l_orderUnitParams.setConfirmedOrderPrice(l_orderUnitParams.getPrice());
                }

                //�s�ꂩ��m�F�ς݂̊T�Z��n���
                if (!l_orderUnitParams.getEstimatedPriceIsNull())
                {
                    l_orderUnitParams.setConfirmedEstimatedPrice(l_orderUnitParams.getEstimatedPrice());
                }

                //���N�G�X�g�^�C�v5�F����
                l_orderUnitParams.setRequestType(WEB3RequestTypeDef.INVALIDATE);

                //�iW�w�l�j���s����
                l_orderUnitParams.setWLimitExecCondType(null);
            }

            else
            {
                //�ȊO�A�s�ꂩ��m�F�ς݂̒����P��
                if (!l_orderUnitParams.getConfirmedOrderPriceIsNull())
                {
                    l_orderUnitParams.setPrice(l_orderUnitParams.getConfirmedOrderPrice());
                }

                //�ȊO�A�s�ꂩ��m�F�ς݂̊T�Z��n���
                if (!l_orderUnitParams.getConfirmedEstimatedPriceIsNull())
                {
                    l_orderUnitParams.setEstimatedPrice(l_orderUnitParams.getConfirmedEstimatedPrice());
                }
            }

            //���������E����敪
            //�X�g�b�v�����ؑ֒�(*1)�̏ꍇ�A
            if (l_blnIsStopOrderChanging)
            {
                //D�FW�w�l�����ؑ֎��s
                l_orderUnitParams.setModifyCancelType(
                    WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFER_ERROR);
            }
            else
            {
                //�ȊO�A8:�������s
                l_orderUnitParams.setModifyCancelType(WEB3IfoOrderModifyCancelTypeDef.CHANGE_FAILED);
            }

            //�����G���[���R�R�[�h
            l_orderUnitParams.setErrorReasonCode(this.errorCode);
        }
        else if(OrderStatusEnum.NOT_CANCELLED.equals( l_orderUnitParams.getOrderStatus()))
        {
            log.debug("������Ԃ��h�������s�i��������j�FNOT_CANCELLED�h�̏ꍇ");

            //(*1)�敨OP�f�[�^�A�_�v�^.getW�w�l�p�L����ԋ敪()��"���~�b�g�����L��"��
            //�ꍇ�A���~�b�g�����L���B
            String l_strEnableStatusDiv = null;
            try
            {
                l_strEnableStatusDiv = WEB3IfoDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.toString(),
                    l_ex);
            }
            if (WEB3IfoWLimitEnableStatusDivDef.LIMIT_ENABLE.equals(l_strEnableStatusDiv))
            {
                //����������
                l_orderUnitParams.setOrgOrderConditionType(l_orderUnitParams.getOrderConditionType());

                //�������������Z�q
                l_orderUnitParams.setOrgOrderCondOperator(l_orderUnitParams.getOrderCondOperator());

                //���t�w�l��l�^�C�v
                l_orderUnitParams.setOrgStopPriceType(l_orderUnitParams.getStopPriceType());

                //���t�w�l��l
                if (!l_orderUnitParams.getStopOrderPriceIsNull())
                {
                    l_orderUnitParams.setOrgStopOrderPrice(l_orderUnitParams.getStopOrderPrice());
                }

                //���iW�w�l�j�����w�l
                if (!l_orderUnitParams.getWLimitPriceIsNull())
                {
                    l_orderUnitParams.setOrgWLimitPrice(l_orderUnitParams.getWLimitPrice());
                }

                //���iW�w�l�j���s����
                l_orderUnitParams.setOrgWLimitExecCondType(l_orderUnitParams.getWLimitExecCondType());

                //��������
                l_orderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);

                //�����������Z�q
                l_orderUnitParams.setOrderCondOperator(null);

                //�t�w�l��l�^�C�v
                l_orderUnitParams.setStopPriceType(null);

                //�t�w�l��l
                l_orderUnitParams.setStopOrderPrice(null);

                //�iW�w�l�j�����w�l
                l_orderUnitParams.setWLimitPrice(null);

				if (this.estimateDeliveryAmounCalcResult != null)
				{
					//�����P��
					//���~�b�g�����L��(*1)�̏ꍇ�A�����P��
					l_orderUnitParams.setPrice(this.estimateDeliveryAmounCalcResult.getCalcUnitPrice());

					//�T�Z��n���
					//���~�b�g�����L��(*1)�̏ꍇ�A�T�Z��n���
					l_orderUnitParams.setEstimatedPrice(this.estimateDeliveryAmounCalcResult.getEstimateDeliveryAmount());
				}

                //�s�ꂩ��m�F�ς݂̒����P��
                if (!l_orderUnitParams.getPriceIsNull())
                {
                    l_orderUnitParams.setConfirmedOrderPrice(l_orderUnitParams.getPrice());
                }

                //�s�ꂩ��m�F�ς݂̊T�Z��n���
                if (!l_orderUnitParams.getEstimatedPriceIsNull())
                {
                    l_orderUnitParams.setConfirmedEstimatedPrice(l_orderUnitParams.getEstimatedPrice());
                }

                //���N�G�X�g�^�C�v5�F����
                l_orderUnitParams.setRequestType(WEB3RequestTypeDef.INVALIDATE);

                //�iW�w�l�j���s����
                l_orderUnitParams.setWLimitExecCondType(null);
            }


            //���������E����敪   4:������s
            l_orderUnitParams.setModifyCancelType(
                WEB3IfoOrderModifyCancelTypeDef.CANCEL_FAILED);

            //�����G���[���R�R�[�h
            l_orderUnitParams.setErrorReasonCode(this.errorCode);
        }

        log.exiting( STR_METHOD_NAME);
        return l_orderUnitParams;
    }
    
    /**                             
     * (�X�V�l�ݒ�)<BR>                              
     *�imutate���\�b�h�̎����j<BR>                               
     * ��������Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>                               
     * <BR>                             
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>                              
     * <BR>                             
     * �P�j�@@�����P�ʃI�u�W�F�N�g�擾<BR>
     * �����̒����P��Params.����ID�A�����P�ʂh�c�ɊY�����钍���P�ʃI�u�W�F�N�g���擾����B                               
     * <BR>
     * 
     * �Q�j�@@super.mutate(IfoOrderActionParams)���R�[������B
     * 
     * �R�j�@@xTrade�W�����ڂ̍X�V�d�l���J�X�^�}�C�Y����B
     * �@@�@@��xTrade�W�������ł́A 
     * �@@�@@���������s���ɂ͒��������t���s���O�̒����P�ʂ̒l���ݒ肳��Ă��܂����߁B
     *  
     * @@param l_updateType - INSERT�܂��́AUPDATE�B<BR>                          
     * <BR>                         
     * OrderManagerPersistenceType�ɂĒ萔��`�B                           
     *                          
     * @@param l_dealing - �iOrderManagerPersistenceContext�ɂĒ萔��`�j                            
     * @@param l_orderActionParams - ��������Params<BR>                          
     * ���������������ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>                          
     * @@return webbroker3.ifo.data.IfoOrderActionParams                         
     * @@roseuid 40838AEB023E                            
     */                         
    public IfoOrderActionParams mutate(OrderManagerPersistenceType l_updateType,                            
        OrderManagerPersistenceContext l_dealing,                       
      IfoOrderActionParams l_orderActionParams)                             
    {                           
        final String STR_METHOD_NAME =                  
            ".mutate(OrderManagerPersistenceType l_updateType," +               
            " OrderManagerPersistenceContext l_dealing, " +             
            "IfoOrderActionParams l_orderActionParams) ";                          
                            
    log.entering(STR_METHOD_NAME);                          
                            
    if (l_orderActionParams == null)                            
    {                           
        log.debug("Enter the if path that l_orderActionParams is null.");                        
        log.error(STR_METHOD_NAME,new WEB3SystemLayerException(                     
            WEB3ErrorCatalog.SYSTEM_ERROR_80017,                    
            this.getClass().getName()+ STR_METHOD_NAME ));                  
        log.debug("Exit the if path that l_orderActionParams is null.");                     
        throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,this.getClass().getName()+ "." + STR_METHOD_NAME);                       
    }                                   
                            
      long l_orderUnitID;                           
      l_orderUnitID = l_orderActionParams.getOrderUnitId();                         
      FinApp l_finApp = (FinApp)Services.getService(FinApp.class);                          
      OrderUnit l_ifoOrderUnit = null;                          
      IfoOrderUnitParams l_params = null;                           
      try                           
      {                         
          l_ifoOrderUnit = l_finApp.getTradingModule(                       
              ProductTypeEnum.IFO).getOrderManager().getOrderUnit(l_orderUnitID);                   
          l_params = (IfoOrderUnitParams)l_ifoOrderUnit.getDataSourceObject();                      
      }                         
      catch (NotFoundException l_nfe)                           
      {                         
          log.error(STR_METHOD_NAME, l_nfe);                        
      }                         
                            
      super.mutate(l_updateType, l_dealing, l_orderActionParams);                           
                      
          l_orderActionParams.setPrice(l_params.getLimitPrice());                        
          l_orderActionParams.setQuantity(l_params.getQuantity());                      
          l_orderActionParams.setConfirmedQuantity(l_params.getConfirmedQuantity());                        
          l_orderActionParams.setConfirmedPrice(l_params.getConfirmedPrice());                      
          if (OrderStatusEnum.MODIFYING.equals(l_ifoOrderUnit.getOrderStatus()) ||
              OrderStatusEnum.CANCELLING.equals(l_ifoOrderUnit.getOrderStatus()))
          {
              l_orderActionParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
          }
        
        log.exiting(STR_METHOD_NAME);                       
        return l_orderActionParams;                     
    }                           

}
@
