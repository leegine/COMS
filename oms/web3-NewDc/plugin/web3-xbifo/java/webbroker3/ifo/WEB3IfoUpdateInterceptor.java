head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.44.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�X�V�C�x���g�C���^�Z�v�^(WEB3IfoUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/16 䈋� (���u) �V�K�쐬
                 : 2006/7/11 ���r (���u) �c�a�X�V�d�l 096
                 : 2006/7/13 �s�p (���u) DB�X�V�d�lNo.085,086,087,089,090,091,092,093,094,095,100,104,111              
Revesion History : 2008/03/17 �����F (���u) DB�X�V�d�lNo.197
*/
package webbroker3.ifo;

import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.BatchedQuery;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoPositionManagerPersistenceEventInterceptor;

import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨OP�X�V�C�x���g�C���^�Z�v�^)<BR>
 * �敨OP�X�V�C�x���g�C���^�Z�v�^�N���X�B<BR>
 * @@author  䈋�
 * @@version 1.0
 */
public class WEB3IfoUpdateInterceptor
    implements
        IfoOrderManagerPersistenceEventInterceptor,
        IfoPositionManagerPersistenceEventInterceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoUpdateInterceptor.class);

    /**
     * �R���X�g���N�^
     * @@roseuid 408CC33C01ED
     */
    public WEB3IfoUpdateInterceptor()
    {

    }

    /**
     * �imutate���\�b�h�̎����j<BR>
     * ��������Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * <BR>
     * �P�j�@@�����P�ʃI�u�W�F�N�g�擾<BR>
     * <BR>
     * �����̒����P��Params.����ID�A�����P�ʂh�c�ɊY������<BR>
     * �����P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * 
     * �Q�j�@@�g�����ڃZ�b�g<BR>
     * �@@�p�����[�^.��������Params�̊g�����ڂɁA<BR>
     * �����P�ʃI�u�W�F�N�g�̓������ڂ���l���Z�b�g���A�ԋp����B<BR>
     * 
     * @@param l_updateType - INSERT�܂��́AUPDATE�B<BR>
     * <BR>
     * OrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     * 
     * @@param l_context - �iOrderManagerPersistenceContext�ɂĒ萔��`�j
     * @@param l_ifoOrderActionParams - ���������������ێ����鍀�ڂ̃p�����[�^�N���X�B
     * @@return webbroker3.ifo.data.IfoOrderActionParams
     * @@roseuid 4084B89C0279
     */
    public IfoOrderActionParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_context,
        IfoOrderActionParams l_ifoOrderActionParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType l_updateType, "
                + "OrderManagerPersistenceContext l_context, "
                + "IfoOrderActionParams l_ifoOrderActionParams)";
        log.entering(STR_METHOD_NAME);

        if (l_ifoOrderActionParams == null)
        {
            log.error(
                STR_METHOD_NAME,
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME));
            return l_ifoOrderActionParams;
        }

        long l_orderUnitID;
        l_orderUnitID = l_ifoOrderActionParams.getOrderUnitId();
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

        // �����ID
        // �����P�ʃe�[�u��.�����ID���ҏW
        if (l_params.getTraderId() == 0)
        {   
            l_ifoOrderActionParams.setTraderId(null);
        }
        else
        {
            l_ifoOrderActionParams.setTraderId(l_params.getTraderId());
        }
        
        // �����o�H�敪
        // �����P�ʃe�[�u��.�����o�H�敪���ҏW
        l_ifoOrderActionParams.setOrderRootDiv(l_params.getOrderRootDiv());

        //��������
        l_ifoOrderActionParams.setOrderConditionType(l_params.getOrderConditionType());

        //�����������Z�q
        l_ifoOrderActionParams.setOrderCondOperator(l_params.getOrderCondOperator());

        //�t�w�l��l�^�C�v
        l_ifoOrderActionParams.setStopPriceType(l_params.getStopPriceType());
        
        if (l_params.getStopOrderPriceIsNull())
        {
            //�t�w�l��l
            l_ifoOrderActionParams.setStopOrderPrice(null);            
        }
        else
        {
            //�t�w�l��l
            l_ifoOrderActionParams.setStopOrderPrice(l_params.getStopOrderPrice());                
        }
                
        if (l_params.getWLimitPriceIsNull())
        {
            //�iW�w�l�j�����w�l
            l_ifoOrderActionParams.setWLimitPrice(null);            
        }
        else
        {
            //�iW�w�l�j�����w�l
            l_ifoOrderActionParams.setWLimitPrice(l_params.getWLimitPrice());                
        }

        //�����������t
        l_ifoOrderActionParams.setExpirationDate(WEB3DateUtility.toDay(l_params.getExpirationDate()));
      
        if (l_params.getEstimatedPriceIsNull())
        {
            //�T�Z��n���
            l_ifoOrderActionParams.setEstimatedPrice(null);    
        }
        else
        {
            //�T�Z��n���
            l_ifoOrderActionParams.setEstimatedPrice(l_params.getEstimatedPrice());              
        }
        
        //���������E����敪
        l_ifoOrderActionParams.setModifyCancelType(l_params.getModifyCancelType());

        //���Ϗ���
        l_ifoOrderActionParams.setClosingOrder(l_params.getClosingOrder());

        //�����G���[���R�R�[�h
        l_ifoOrderActionParams.setErrorReasonCode(l_params.getErrorReasonCode());

        //���N�G�X�g�^�C�v
        l_ifoOrderActionParams.setRequestType(l_params.getRequestType());
        
        //����������
        l_ifoOrderActionParams.setOrgOrderConditionType(l_params.getOrgOrderConditionType());
        
        //�������������Z�q
        l_ifoOrderActionParams.setOrgOrderCondOperator(l_params.getOrgOrderCondOperator());
        
        //���t�w�l��l�^�C�v
        l_ifoOrderActionParams.setOrgStopPriceType(l_params.getOrgStopPriceType());
        
        //���t�w�l��l
        if (l_params.getOrgStopOrderPriceIsNull())
        {
            l_ifoOrderActionParams.setOrgStopOrderPrice(null);
        }
        else
        {
            l_ifoOrderActionParams.setOrgStopOrderPrice(l_params.getOrgStopOrderPrice());
        }
        
        //���iW�w�l�j�����w�l
        if (l_params.getOrgWLimitPriceIsNull())
        {
            l_ifoOrderActionParams.setOrgWLimitPrice(null);
        }
        else
        {
            l_ifoOrderActionParams.setOrgWLimitPrice(l_params.getOrgWLimitPrice());
        }
        
        //���iW�w�l�j���s����
        l_ifoOrderActionParams.setOrgWLimitExecCondType(l_params.getOrgWLimitExecCondType());

        //�iW�w�l�j���s����
        l_ifoOrderActionParams.setWLimitExecCondType(l_params.getWLimitExecCondType());
       
        //�iW�w�l�j�֑ؑO�w�l
        if (l_params.getWLimitBeforeLimitPriceIsNull())
        {
            l_ifoOrderActionParams.setWLimitBeforeLimitPrice(null);
        }
        else
        {
            l_ifoOrderActionParams.setWLimitBeforeLimitPrice(l_params.getWLimitBeforeLimitPrice());
        }
        
        //�iW�w�l�j�֑ؑO���s����
        l_ifoOrderActionParams.setWLimitBeforeExecCondType(l_params.getWLimitBeforeExecCondType());

        //�s�ꂩ��m�F�ς݂̎��s����
        l_ifoOrderActionParams.setConfirmedExecConditionType(l_params.getConfirmedExecConditionType());

        //���������敪
        l_ifoOrderActionParams.setExpirationDateType(l_params.getExpirationDateType());
        
        log.exiting(STR_METHOD_NAME);
        return l_ifoOrderActionParams;
    }
    
    public IfoContractParams mutateBeforeInsert(IfoContractParams ifocontractparams)
    {
        return ifocontractparams;
    }

    public BatchedQuery getQueryToExecute(
        OrderManagerPersistenceType OrderManagerPersistenceType,
        Class class1)
    {
        return null;
    }

    public IfoOrderUnitParams mutate(
        OrderManagerPersistenceType OrderManagerPersistenceType,
        OrderManagerPersistenceContext OrderManagerPersistenceContext,
        IfoOrderUnitParams ifoorderunitparams)
    {
        return ifoorderunitparams;
    }
    public IfoOrderExecutionParams mutate(
        OrderManagerPersistenceType OrderManagerPersistenceType,
        OrderManagerPersistenceContext OrderManagerPersistenceContext,
        IfoOrderExecutionParams ifoorderexecutionparams)
    {
        return ifoorderexecutionparams;
    }
    public Map mutateBeforeUpdate(IfoContractParams ifocontractparams, Map map)
    {
        return map;
    }

    public Map mutateBeforeUpdate(
        IfoFinTransactionParams ifofintransactionparams,
        Map map)
    {
        return map;
    }
    public IfoFinTransactionParams mutateBeforeInsert(IfoFinTransactionParams ifofintransactionparams)
    {
        return ifofintransactionparams;
    }

}
@
