head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.54.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToStopEquityOrderServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �t�w�l�������������X�V�C���^�Z�v�^(WEB3ToStopEquityOrderServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/12 ���V�@@��@@(SRA) �V�K�쐬
*/
package webbroker3.triggerorder;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams;
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
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3MarginUpdateEventInterceptor;
import webbroker3.util.WEB3LogUtility;

/**
 * (�t�w�l�������������X�V�C���^�Z�v�^)<BR>
 * Eqtype�t�w�l����������DB�X�V�d�l�J�X�^�}�C�Y�p�N���X<BR>
 * @@author ���V�@@��@@
 * @@version 1.0
 */
public class WEB3ToStopEquityOrderServiceInterceptor extends WEB3MarginUpdateEventInterceptor 
{    
    /**
     * (���O���[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToStopEquityOrderServiceInterceptor.class);
    
    /**
     * �����G���[���R�R�[�h<BR> 
     */
    private String errorCode = null;
    
    /**
     * �i�R���X�g���N�^�j<BR>
     * �����̒����G���[���R�R�[�h���A�v���p�e�B�ɐݒ肷��B
     * @@param l_errorCode
     */
    public WEB3ToStopEquityOrderServiceInterceptor(String l_errorCode)
    {
        errorCode = l_errorCode;
    }
    
    /**
     * �i�X�V�l�ݒ�j<BR>
     * �imutate���\�b�h�̎����j<BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB<BR>
     * <BR>
     * �P�j �g�����ڃZ�b�g<BR>
     * �X�V���e�́A�u�t�w�l��������(NG)_���������P�ʃe�[�u��.xls�v�Q�ƁB<BR>
     * <BR>
     * @@param l_updateType - �X�V�^�C�v<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * OrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     * <BR>
     * @@param l_process - ����<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * <BR>
     * @@param l_orderUnitParams - �����P��Params<BR>
     * ���������P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
     * <BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_process, 
        EqtypeOrderUnitParams l_orderUnitParams)
    {
        final String STR_METHOD_NAME = "mutate";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnitParams == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�����G���[���R�R�[�h
        l_orderUnitParams.setErrorReasonCode(this.getErrorReasonCode());
        //���N�G�X�g�^�C�v
        l_orderUnitParams.setRequestType(WEB3RequestTypeDef.ORDER_FAILURE);
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
    
    /**
     * �i�X�V�l�ݒ�j<BR>
     * �imutate���\�b�h�̎����j<BR>
     * ��������Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * <BR>
     * �P�j�@@�����P�ʃI�u�W�F�N�g�擾<BR>
     * <BR>
     * �����̒����P��Params.����ID�A�����P�ʂh�c�ɊY�����钍���P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@�g�����ڃZ�b�g<BR>
     * �@@�X�V���e�́A�u�t�w�l��������(NG)_�������������e�[�u��.xls�v�Q�ƁB<BR> 
     * <BR>
     * @@param l_updateType - �X�V�^�C�v<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * OrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     * <BR>
     * @@param l_process - ����<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * <BR>
     * @@param l_orderActionParams - ������������Params<BR>
     * ���������������ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
     * <BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams
     */
    public EqtypeOrderActionParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_process,
        EqtypeOrderActionParams l_orderActionParams)
    {
        final String STR_METHOD_NAME = "mutate";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderActionParams == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        long l_lngOrderUnitId = l_orderActionParams.getOrderUnitId();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderMgr = (WEB3EquityOrderManager) l_tradingMod.getOrderManager();                
        EqTypeOrderUnit l_orderUnit;
        
        try 
        {
            l_orderUnit = (EqTypeOrderUnit)l_orderMgr.getOrderUnit(l_lngOrderUnitId);
        } 
        catch (NotFoundException e) 
        {
            log.error(STR_METHOD_NAME, e);
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);  
        }
        EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        //����҂h�c
        if (l_row.getTraderIdIsNull())
        {
            l_orderActionParams.setTraderId(null);
        }
        else
        {
            l_orderActionParams.setTraderId(l_row.getTraderId());
        }
        //�l�i����
        l_orderActionParams.setPriceConditionType(l_row.getPriceConditionType());
        //��������
        l_orderActionParams.setOrderConditionType(l_row.getOrderConditionType());
        //�����������Z�q
        l_orderActionParams.setOrderCondOperator(l_row.getOrderCondOperator());
        //�t�w�l��l
        if (l_row.getStopOrderPriceIsNull())
        {
            l_orderActionParams.setStopOrderPrice(null);
        }
        else
        {
            l_orderActionParams.setStopOrderPrice(l_row.getStopOrderPrice());
        }
        //�iW�w�l�j�����w�l
        if (l_row.getWLimitPriceIsNull())
        {
            l_orderActionParams.setWLimitPrice(null);
        }
        else
        {
            l_orderActionParams.setWLimitPrice(l_row.getWLimitPrice());
        }
        //�����������t
        l_orderActionParams.setExpirationDate(l_row.getExpirationDate());
        //�T�Z��n���
        if (l_row.getEstimatedPriceIsNull())
        {
            l_orderActionParams.setEstimatedPrice(null);
        }
        else
        {
            l_orderActionParams.setEstimatedPrice(l_row.getEstimatedPrice());
        }
        //���������E����敪
        l_orderActionParams.setModifyCancelType(l_row.getModifyCancelType());
        //�����o�H�敪
        l_orderActionParams.setOrderRootDiv(l_row.getOrderRootDiv());
        //���Ϗ����敪
        l_orderActionParams.setClosingOrderType(l_row.getClosingOrderType());
        //�����G���[���R�R�[�h
        l_orderActionParams.setErrorReasonCode(this.getErrorReasonCode());
        //���N�G�X�g�^�C�v
        l_orderActionParams.setRequestType(l_row.getRequestType());
        
        log.exiting(STR_METHOD_NAME);
        return l_orderActionParams;
    }
    
    /**
     * �iget�����G���[���R�R�[�h�j<BR>
     * this.�����G���[���R�R�[�h��ԋp����B<BR>
     * <BR>
     * @@return String
     */
    public String getErrorReasonCode()
    {
        return this.errorCode;
    }    
}
@
