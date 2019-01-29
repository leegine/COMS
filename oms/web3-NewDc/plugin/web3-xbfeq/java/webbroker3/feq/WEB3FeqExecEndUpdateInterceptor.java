head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecEndUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������o���I���X�V�C�x���g�C���^�Z�v�^(WEB3FeqExecEndUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13  鰊](���u) �V�K�쐬
                 : 2005/07/25  ���I(���u) ���r���[
*/

package webbroker3.feq;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�������o���I���X�V�C�x���g�C���^�Z�v�^)<BR>
 * �O�������o���I���X�V�C�x���g�C���^�Z�v�^<BR>
 * 
 * @@ author 鰊]<BR> 
 * @@ version 1.0<BR>
 */
public class WEB3FeqExecEndUpdateInterceptor extends WEB3FeqUpdateInterceptor 
{
    
    /**
     * (���O���[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqExecEndUpdateInterceptor.class);
        
    /**
     * (�O�������o���I���X�V�C�x���g�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 42B6A39101C3
     */
    public WEB3FeqExecEndUpdateInterceptor() 
    {
     
    }
    
    /**
     * (�i�����P�ʁj�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j <BR>
     * <BR>
     * �P�j�@@�����iͯ�ށj�e�[�u���X�V<BR>
     * �@@super.mutate(OrderManagerPersistenceType, <BR>
     * �@@OrderManagerPersistenceContext, FeqOrderUnitParams)<BR>
     * �@@���R�[������B<BR>
     * <BR>
     * �Q�j�@@�����P�ʃe�[�u���X�V<BR>
     * �@@�����P��Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * �@@���ڐݒ���e�́A�yxTrade�z�⑫����.<BR>
     * �@@DB�X�V\\20.(��)�o���I��<BR>
     * �@@�u�O���o���I��_�O�������P�ʎd�l.xls�v�Q�ƁB<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * @@param l_updateType - (�X�V�^�C�v)<BR>
     * �X�V�^�C�v<BR>
     * @@param l_context - (����)<BR>
     * ����<BR>
     * @@param l_feqOrderUnitParams - (�����P�ʍs)<BR>
     * �����P�ʍs�i�F�����P��Params�j<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams
     * @@roseuid 42B6A32001C3
     */
    public FeqOrderUnitParams mutate(OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_context, 
        FeqOrderUnitParams l_feqOrderUnitParams) 
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, FeqOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        if (l_feqOrderUnitParams == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }        
        
        //�����iͯ�ށj�e�[�u���X�V
        l_feqOrderUnitParams = super.mutate(l_updateType, l_context, l_feqOrderUnitParams);
                
        //�o���I����������
        l_feqOrderUnitParams.setExecEndTimestamp(GtlUtils.getSystemTimestamp());
        FeqOrderRow l_feqOrderRow = null;
        
        try
        {            
            l_feqOrderRow = (FeqOrderRow) FeqOrderDao.findRowByOrderId(l_feqOrderUnitParams.getOrderId());
            //�X�V�҃R�[�h = ����.�X�V�҃R�[�h
            l_feqOrderUnitParams.setLastUpdater(l_feqOrderRow.getLastUpdater());
        }
        catch (DataFindException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            this.getClass().getName() + "." + STR_METHOD_NAME,
            l_ex.getMessage(),
            l_ex);

        }
        
        log.exiting(STR_METHOD_NAME);   
        return l_feqOrderUnitParams;
    }
    
    /**
     * (�i���������j�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j <BR>
     * ��������Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * <BR>
     * �P�j�@@�����P�ʃI�u�W�F�N�g�擾 <BR>
     * <BR>
     * �����̒�������Params.����ID�A<BR> 
     * �����P�ʂh�c�ɊY�����钍���P�ʃI�u�W�F�N�g���擾����B<BR> 
     * <BR>
     * �Q�j�@@�g�����ڃZ�b�g <BR>
     * �@@�p�����[�^.��������Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B <BR>
     * �@@���ڐݒ���e�́A�yxTrade�z�⑫����.DB�X�V\\20.<BR>
     * �@@�i�ǁj�o���I���u�O���o���I��_�O�����������d�l.xls�v�Q�ƁB<BR>
     * @@param l_updateType
     * @@param l_context
     * @@param l_feqOrderActionParams
     * @@return com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionParams
     * @@roseuid 42B6A32001E2
     */
    public FeqOrderActionParams mutate(OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_context,
        FeqOrderActionParams l_feqOrderActionParams) 
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, FeqOrderActionParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_feqOrderActionParams == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }        
        
        long l_lngOrderUnitId = l_feqOrderActionParams.getOrderUnitId();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_orderManager = (WEB3FeqOrderManager) l_tradingMod.getOrderManager();
        FeqOrderUnit l_feqOrderUnit = null;
        
        try 
        {
            // �����P�ʃI�u�W�F�N�g�擾
            l_feqOrderUnit = (FeqOrderUnit) l_orderManager.getOrderUnit(l_lngOrderUnitId);
        } 
        catch (NotFoundException l_ex) 
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);  
        }
        
        FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject();
        //�������� = �����P��.��������
        l_feqOrderActionParams.setOrderConditionType(l_feqOrderUnitRow.getOrderConditionType());
        
        //�����������Z�q = �����P��.�����������Z�q
        l_feqOrderActionParams.setOrderCondOperator(l_feqOrderUnitRow.getOrderCondOperator());
        
        //�t�w�l��l = �����P��.�t�w�l��l
        if (l_feqOrderUnitRow.getStopOrderPriceIsNull())
        {
            l_feqOrderActionParams.setStopOrderPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setStopOrderPrice(l_feqOrderUnitRow.getStopOrderPrice());
        }
        
        //�iW�w�l�j�����w�l = �����P��.�iW�w�l�j�����w�l
        if (l_feqOrderUnitRow.getWLimitPriceIsNull())
        {
            l_feqOrderActionParams.setWLimitPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setWLimitPrice(l_feqOrderUnitRow.getWLimitPrice());
        }
        
        //�����������t
        l_feqOrderActionParams.setExpirationDate(l_feqOrderUnitRow.getExpirationDate());
        
        //������
        l_feqOrderActionParams.setExecTimestamp(null);
        
        //�T�Z��n���
        if (l_feqOrderUnitRow.getEstimatedPriceIsNull())
        {
            l_feqOrderActionParams.setEstimatedPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setEstimatedPrice(l_feqOrderUnitRow.getEstimatedPrice());
        }
        
        //�T�Z��n����i�O�݁j
        if (l_feqOrderUnitRow.getFEstimatedPriceIsNull())
        {
            l_feqOrderActionParams.setFEstimatedPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setFEstimatedPrice(l_feqOrderUnitRow.getFEstimatedPrice());
        }
        
        //���������E����敪 = �����P��.���������E����敪
        l_feqOrderActionParams.setModifyCancelType(l_feqOrderUnitRow.getModifyCancelType());
        
        //�����o�H�敪
        l_feqOrderActionParams.setOrderRootDiv(l_feqOrderUnitRow.getOrderRootDiv());
        
        //�s�ꂩ��m�F�ς݂̒����P�� = �����P��.�s�ꂩ��m�F�ς݂̒����P��
        if (l_feqOrderUnitRow.getConfirmedOrderPriceIsNull())
        {
            l_feqOrderActionParams.setConfirmedOrderPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setConfirmedOrderPrice(l_feqOrderUnitRow.getConfirmedOrderPrice());
        }
        
        //�s�ꂩ��m�F�ς݂̊T�Z��n��� = �����P��.�s�ꂩ��m�F�ς݂̊T�Z��n���
        if (l_feqOrderUnitRow.getConfirmedEstimatedPriceIsNull())
        {
            l_feqOrderActionParams.setConfirmedEstimatedPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setConfirmedEstimatedPrice(l_feqOrderUnitRow.getConfirmedEstimatedPrice());
        }
        
        //�s�ꂩ��m�F�ς݂̊T�Z��n����i�O�݁j= �����P��.�s�ꂩ��m�F�ς݂̊T�Z��n����i�O�݁j
        if (l_feqOrderUnitRow.getConfirmedFEstimatedPriceIsNull())
        {
            l_feqOrderActionParams.setConfirmedFEstimatedPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setConfirmedFEstimatedPrice(l_feqOrderUnitRow.getConfirmedFEstimatedPrice());
        }
        
        //�s�ꂩ��m�F�ς݂̎��s���� = �����P��.�s�ꂩ��m�F�ς݂̎��s����
        l_feqOrderActionParams.setConfirmedExecConditionType(l_feqOrderUnitRow.getConfirmedExecConditionType());
        
        //�����G���[���R�R�[�h = 0000:����
        l_feqOrderActionParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
        
        //�X�V�҃R�[�h = �����P��.�X�V�҃R�[�h
        l_feqOrderActionParams.setLastUpdater(l_feqOrderUnitRow.getLastUpdater());
        
        log.exiting(STR_METHOD_NAME);
        return l_feqOrderActionParams;
    }
}
@
