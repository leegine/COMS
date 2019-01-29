head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginUpdateEventInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �X�V�C�x���g�C���^�Z�v�^(WEB3MarginUpdateEventInterceptor.java)
Author Name      : 2004/9/2�S   Ḗ@@��(���u) �V�K�쐬
Revesion History : 2004/11/1    �@@���@@�C��   
                 �@@2004/11/30   SRA�����@@�c�Č��Ή�
                 �@@2006/07/19 �юu�� (���u) �c�a�X�V�d�l156
                   2006/11/01 �đo�g (���u) �c�a�X�V�d�lNo.180
*/

package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.BatchedQuery;
import com.fitechlabs.xtrade.plugin.security.oplogin.IllegalSessionStateException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�X�V�C�x���g�C���^�Z�v�^�j�B<BR>
 * <BR>
 * �g�����������}�l�[�W���ɂ��A<BR>
 * �����P�ʁ^���������^���I�u�W�F�N�g�̍X�V�d�l�J�X�^�}�C�Y�̂��߂�<BR>
 * ���N���X�B
 * @@author �@@��
 * @@version 1.0
 */
public class WEB3MarginUpdateEventInterceptor implements EqTypeOrderManagerPersistenceEventInterceptor 
{
    
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3MarginUpdateEventInterceptor.class);
    
    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR>
     * ��������Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * <BR>
     * �P�j�@@�����P�ʃI�u�W�F�N�g�擾<BR>
     * <BR>
     * �@@�����̒����P��Params.����ID�A�����P�ʂh�c�ɊY�����钍���P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@�g�����ڃZ�b�g<BR>
     * �@@�������G���[���R�R�[�h<BR>
     * �@@�@@�p�����[�^.��������Params.�����G���[���R�R�[�h�Ɂh0000:����h���Z�b�g����B<BR>
     * �@@<BR>
     * �@@�����̑��̍��ځ@@<BR>�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@
     * �@@�@@�p�����[�^.��������Params�̊g�����ڂɁA�����P�ʃI�u�W�F�N�g�̓������ڂ���l���Z�b�g���A<BR>
     * �@@�@@�ԋp����B<BR>
     * <BR>
     * @@param l_updateType - �X�V�^�C�v<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * EqTypeOrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     * <BR>
     * @@param l_process - ����<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * <BR>
     * @@param l_orderHistoryParams - ������������Params<BR>
     * ���������������ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams
     * @@roseuid 40B1901D0176
     */
    public EqtypeOrderActionParams mutate(OrderManagerPersistenceType l_updateType, OrderManagerPersistenceContext l_process, EqtypeOrderActionParams l_orderHistoryParams) 
    {
        final String STR_METHOD_NAME = "mutate";
        log.entering(STR_METHOD_NAME);
        if (l_orderHistoryParams == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        long l_lngOrderUnitId = l_orderHistoryParams.getOrderUnitId();
        //get the order unit object
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderMgr = (WEB3EquityOrderManager) l_tradingMod.getOrderManager();                
        EqTypeOrderUnit l_orderUnit;
        
        try 
        {
            l_orderUnit = (EqTypeOrderUnit) l_orderMgr.getOrderUnit(l_lngOrderUnitId);
            log.debug("get the order Unit object susussfully!");
        } 
        catch (NotFoundException l_ex) 
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);  
        }
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();

        // ����҂h�c�i�����P�ʃe�[�u���D����҂h�c�j
        if (l_orderUnitRow.getTraderIdIsNull())
        {
            l_orderHistoryParams.setTraderId(null);
        }
        else
        {
            l_orderHistoryParams.setTraderId(l_orderUnitRow.getTraderId());
        }
        // set error reason code normal:0000
        l_orderHistoryParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
        //�l�i����
        l_orderHistoryParams.setPriceConditionType(l_orderUnitRow.getPriceConditionType());
        //��������
        l_orderHistoryParams.setOrderConditionType(l_orderUnitRow.getOrderConditionType());
        //�����������Z�q
        l_orderHistoryParams.setOrderCondOperator(l_orderUnitRow.getOrderCondOperator());
        
        double l_dblSPrice = l_orderUnitRow.getStopOrderPrice();
        if (l_orderUnitRow.getStopOrderPriceIsNull())
        {
            l_orderHistoryParams.setStopOrderPrice(null);
        }
        else
        {
            //�t�w�l��l
            log.debug("�t�w�l��l" + l_dblSPrice);
            l_orderHistoryParams.setStopOrderPrice(l_dblSPrice);            
        }

        double l_dblWPrice = l_orderUnitRow.getWLimitPrice();
        if (l_orderUnitRow.getWLimitPriceIsNull())
        {
            l_orderHistoryParams.setWLimitPrice(null);
        }
        else
        {
            //�iW�w�l�j�����w�l
            l_orderHistoryParams.setWLimitPrice(l_dblWPrice);
            log.debug("�iW�w�l�j�����w�l" + l_dblWPrice);            
        }

        //�����������t
        l_orderHistoryParams.setExpirationDate(l_orderUnitRow.getExpirationDate());
        //�T�Z��n���
        double l_dblEstimatePrice = l_orderUnitRow.getEstimatedPrice();
        if (l_orderUnitRow.getEstimatedPriceIsNull())
        {
            l_orderHistoryParams.setEstimatedPrice(null);            
        }
        else 
        {
            log.debug("�T�Z��n���" + l_dblEstimatePrice);
            l_orderHistoryParams.setEstimatedPrice(l_dblEstimatePrice);            
        }

        //���������E����敪
        l_orderHistoryParams.setModifyCancelType(l_orderUnitRow.getModifyCancelType());
        
        // �����o�H�敪�i�����P�ʃe�[�u���D�����o�H�敪�j
        l_orderHistoryParams.setOrderRootDiv(l_orderUnitRow.getOrderRootDiv());
        
        //���Ϗ����敪
        l_orderHistoryParams.setClosingOrderType(l_orderUnitRow.getClosingOrderType());
        
        //���N�G�X�g�^�C�v
        l_orderHistoryParams.setRequestType(l_orderUnitRow.getRequestType());
        if (l_orderUnitRow.getLimitPriceIsNull())
        {
            l_orderHistoryParams.setPrice(null);
        }
        else
        {
            l_orderHistoryParams.setPrice(l_orderUnitRow.getLimitPrice());
        }
        
        //IP�A�h���X
		OpLoginSecurityService l_securityService =
			(OpLoginSecurityService) Services.getService(
				OpLoginSecurityService.class);
		try
		{
			String l_strIpAddress = 
				l_securityService.getSessionProperty(
					WEB3SessionAttributeDef.IP_ADDRESS);
			l_orderHistoryParams.setIpAddress(l_strIpAddress);
		}
		catch (IllegalSessionStateException e)
		{
			l_orderHistoryParams.setIpAddress(null);
		}
        
        //����������
        l_orderHistoryParams.setOrgOrderConditionType(l_orderUnitRow.getOrgOrderConditionType());
        
        //�������������Z�q
        l_orderHistoryParams.setOrgOrderCondOperator(l_orderUnitRow.getOrgOrderCondOperator());
        
        //���t�w�l��l
        if (l_orderUnitRow.getOrgStopOrderPriceIsNull())
        {
            l_orderHistoryParams.setOrgStopOrderPrice(null);
        }
        else 
        {
            l_orderHistoryParams.setOrgStopOrderPrice(l_orderUnitRow.getOrgStopOrderPrice());
        }
        
        //���iW�w�l�j�����w�l
        if (l_orderUnitRow.getOrgWLimitPriceIsNull())
        {
            l_orderHistoryParams.setOrgWLimitPrice(null);
        }
        else 
        {
            l_orderHistoryParams.setOrgWLimitPrice(
                l_orderUnitRow.getOrgWLimitPrice());
        }
        
        //���iW�w�l�j���s����
        l_orderHistoryParams.setOrgWLimitExecCondType(
            l_orderUnitRow.getOrgWLimitExecCondType());
        
        //�iW�w�l�j���s����
        l_orderHistoryParams.setWLimitExecCondType(
            l_orderUnitRow.getWLimitExecCondType());
        
        //�iW�w�l�j�֑ؑO�w�l
        if (l_orderUnitRow.getWLimitBeforeLimitPriceIsNull())
        {
            l_orderHistoryParams.setWLimitBeforeLimitPrice(null);
        }
        else 
        {
            l_orderHistoryParams.setWLimitBeforeLimitPrice(
                l_orderUnitRow.getWLimitBeforeLimitPrice());
        }
        
        //�iW�w�l�j�֑ؑO���s����
        l_orderHistoryParams.setWLimitBeforeExecCondType(
            l_orderUnitRow.getWLimitBeforeExecCondType());

        // �s�ꂩ��m�F�ς݂̎��s����
        l_orderHistoryParams.setConfirmedExecConditionType(
            l_orderUnitRow.getConfirmedExecConditionType());

        log.exiting(STR_METHOD_NAME);
        return l_orderHistoryParams;
    }
    
    /**
     * (�X�V�C�x���g�C���^�Z�v�^)<BR>
     * �R���X�g���N�^�B<BR>
     * @@return webbroker3.margin.WEB3MarginUpdateEventInterceptor
     * @@roseuid 40B1901D017A
     */
    public WEB3MarginUpdateEventInterceptor() 
    {
     
    }
    
    /**
     * (�X�V�l�ݒ�)<BR>
     * @@param arg0
     * @@param arg1
     * @@param arg2
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams
     * @@roseuid 4142BBF60107
     */
    public EqtypeOrderUnitParams mutate(OrderManagerPersistenceType arg0, OrderManagerPersistenceContext arg1, EqtypeOrderUnitParams arg2) 
    {
        return arg2;
    }
    
    /**
     * (�X�V�l�ݒ�)<BR>
     * @@param arg0
     * @@param arg1
     * @@param arg2
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionParams
     * @@roseuid 4142BBF60139
     */
    public EqtypeOrderExecutionParams mutate(OrderManagerPersistenceType arg0, OrderManagerPersistenceContext arg1, EqtypeOrderExecutionParams arg2) 
    {
        return arg2;
    }
    
    /**
     * @@param arg0
     * @@param arg1
     * @@return BatchedQuery
     * @@roseuid 4142BBF60175
     */
    public BatchedQuery getQueryToExecute(OrderManagerPersistenceType arg0, Class arg1) 
    {
        return null;
    }
    

}
@
