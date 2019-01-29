head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.55.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToWLimitIfoSwitchUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : W�w�l�����敨OP�ؑ֍X�V�C���^�Z�v�^(WEB3ToWLimitIfoSwitchUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/23�@@�юu��(���u) �V�K�쐬
Revesion History : 2007/01/30  ���� (���u) �g���K�[�����c�a�X�V�d�l039
Revesion History : 2008/04/10  ��іQ (���u) �g���K�[�����c�a�X�V�d�l045
*/
package webbroker3.triggerorder;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.ifo.WEB3IfoChangeSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractChangeSpec;
import webbroker3.ifo.WEB3IfoUpdateInterceptor;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;
import webbroker3.util.WEB3LogUtility;

/**
 * (W�w�l�����敨OP�ؑ֍X�V�C���^�Z�v�^)<BR>
 * W�w�l�����敨OP�ؑ֍X�V�C���^�Z�v�^<BR>
 *
 * @@author �юu��
 * @@version 1.0
 */
public class WEB3ToWLimitIfoSwitchUpdateInterceptor extends WEB3IfoUpdateInterceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3ToWLimitIfoSwitchUpdateInterceptor.class);

    /**
     * (����)<BR>
     * ���҃I�u�W�F�N�g<BR>
     */
    public WEB3GentradeTrader trader;

    /**
     * (�T�Z��n����v�Z����)<BR>
     * �T�Z��n����v�Z���ʃI�u�W�F�N�g<BR>
     */
    public WEB3IfoEstimateDeliveryAmountCalcResult estimateDeliveryAmounCalcResult;

    /**
     * (�������e)<BR>
     * �������e�I�u�W�F�N�g<BR>
     * ���ȉ��̂����ꂩ�B<BR>
     * �@@�E�V�K���������e<BR>
     * �@@�E�ԍϒ������e<BR>
     */
    public Object changeSpec;

    /**
     * @@roseuid 44E90ED7004E
     */
    public WEB3ToWLimitIfoSwitchUpdateInterceptor()
    {

    }

    /**
     * (W�w�l�����敨OP�ؑ֍X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �C���X�^���X�𐶐����A���������g�̃v���p�e�B�ɃZ�b�g����B<BR>
     * @@param l_trader - (����)<BR>
     * ���҃I�u�W�F�N�g
     * @@param l_estimateDeliveryAmountCalcResult - (�T�Z��n����v�Z����)<BR>
     * �T�Z��n����v�Z���ʃI�u�W�F�N�g
     * @@param l_changeSpec - (�������e)<BR>
     * �������e�I�u�W�F�N�g<BR>
     * ���ȉ��̂����ꂩ�B<BR>
     * �@@�E�V�K���������e<BR>
     * �@@�E�ԍϒ������e<BR>
     * @@roseuid 44926F460153
     */
    public WEB3ToWLimitIfoSwitchUpdateInterceptor(
        WEB3GentradeTrader l_trader,
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult,
        Object l_changeSpec)
    {
        this.trader = l_trader;
        this.estimateDeliveryAmounCalcResult
            = l_estimateDeliveryAmountCalcResult;
        this.changeSpec = l_changeSpec;
    }

    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR>
     * �����̒����P��Params�Ɋg������(*)��ݒ肵�ԋp����B<BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * <BR>
     * �P�j �g�����ڃZ�b�g<BR>
     * �@@�X�V���e�́ADB�X�V�d�l <BR>
     * �@@�uW�w�l�����ؑ�(OK)_�����P�ʃe�[�u��.xls�v�Q�ƁB<BR>
     * <BR>
     * @@param l_updateType - (�X�V�^�C�v)<BR>
     * @@param l_context - (�R���e�L�X�g)<BR>
     * @@param l_orderUnitParams - (�����P�ʍs)<BR>
     * �����P�ʍs�I�u�W�F�N�g<BR>
     * @@return IfoOrderUnitParams
     * @@roseuid 44926F460173
     */
    public IfoOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_context,
        IfoOrderUnitParams l_orderUnitParams)
    {
        final String STR_METHOD_NAME =
            " mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, IfoOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnitParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("�����P��Params == null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�����ID
        if (this.trader != null)
        {
            l_orderUnitParams.setTraderId(this.trader.getTraderId());
        }

        if (this.changeSpec instanceof WEB3IfoOpenContractChangeSpec)
        {
            WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec =
                (WEB3IfoOpenContractChangeSpec)this.changeSpec;

            //���s����
            l_orderUnitParams.setExecutionConditionType(
                l_ifoOpenContractChangeSpec.getChangeExecCondType());

        }
        else if (this.changeSpec instanceof WEB3IfoChangeSettleContractOrderSpec)
        {
            WEB3IfoChangeSettleContractOrderSpec l_ifoChangeSettleContractOrderSpec =
                (WEB3IfoChangeSettleContractOrderSpec)this.changeSpec;

            //���s����
            l_orderUnitParams.setExecutionConditionType(
                l_ifoChangeSettleContractOrderSpec.getChangeExecCondType());
        }

        //�������
        l_orderUnitParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);

        //�����P��
        l_orderUnitParams.setPrice(this.estimateDeliveryAmounCalcResult.getCalcUnitPrice());

        //�T�Z��n���
        l_orderUnitParams.setEstimatedPrice(
            this.estimateDeliveryAmounCalcResult.getEstimateDeliveryAmount());

        //���������E����敪
        l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFERING);

        //�����G���[���R�R�[�h
        l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

        //���N�G�X�g�^�C�v
        l_orderUnitParams.setRequestType(WEB3RequestTypeDef.QUOTE_SERVER);
        

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        IfoOrderManager l_ifoMgr=(IfoOrderManager)l_tradingModule.getOrderManager();

        IfoOrderUnit l_ifoOrderUnit = l_ifoOrderUnit = 
        	(IfoOrderUnit)l_ifoMgr.toOrderUnit(l_orderUnitParams);

        WEB3IfoFrontOrderService l_service = (WEB3IfoFrontOrderService) 
        						Services.getService(WEB3IfoFrontOrderService.class);
        try
		{
			// �����o�H�敪 submit_order_route_div
			l_orderUnitParams.setSubmitOrderRouteDiv(
					l_service.getChangeSubmitOrderRouteDiv(l_ifoOrderUnit));

			// ����Rev.order_rev
			l_orderUnitParams.setOrderRev(
					l_service.getChangeOrderRev(l_ifoOrderUnit));
		}
		catch (WEB3BaseException l_web3BaseException)
		{
			log.error(STR_METHOD_NAME, l_web3BaseException);
			throw new WEB3BaseRuntimeException(l_web3BaseException.getErrorInfo(), 
					STR_METHOD_NAME);
		}
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }

    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR>
     * ��������Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * <BR>
     * �P�j�@@�����P�ʃI�u�W�F�N�g�擾<BR>
     * <BR>
     * �����̒�������Params.����ID�A�����P�ʂh�c�ɊY�����钍���P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@�g�����ڃZ�b�g<BR>
     * �@@�X�V���e�́A�uW�w�l�����ؑցiOK�j_���������e�[�u���d�l.xls�v�Q�ƁB<BR>
     * @@param l_updateType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * <BR>
     * OrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     *
     * @@param l_context - (����)<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * @@param l_orderActionParams - (��������Params)<BR>
     * ���������s�I�u�W�F�N�g�B<BR>
     * @@return IfoOrderActionParams
     * @@roseuid 44963AB7025C
     */
    public IfoOrderActionParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_context,
        IfoOrderActionParams l_orderActionParams)
    {
        final String STR_METHOD_NAME =
            " mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, IfoOrderActionParams)";
        log.entering(STR_METHOD_NAME);

        if (l_orderActionParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("��������Params == null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        IfoOrderUnitRow l_ifoOrderUnitRow = null;
        try
        {
            l_ifoOrderUnitRow = IfoOrderUnitDao.findRowByPk(l_orderActionParams.getOrderUnitId());
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //���������h�c
        try
        {
            l_orderActionParams.setOrderActionId(IfoOrderActionDao.newPkValue());
        }
        catch (DataNetworkException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //�����C�x���g�^�C�v
        l_orderActionParams.setOrderEventType(OrderEventTypeEnum.CHANGE);

        //  ��������      order_condition_type
        //�����P�ʃe�[�u��.�����������ҏW�B
        l_orderActionParams.setOrderConditionType(l_ifoOrderUnitRow.getOrderConditionType());

        // �����������Z�q       order_cond_operator
        //�����P�ʃe�[�u��.�����������Z�q���ҏW�B
        l_orderActionParams.setOrderCondOperator(l_ifoOrderUnitRow.getOrderCondOperator());

        //  �t�w�l��l�^�C�v       stop_price_type
        //  �����P�ʃe�[�u��.�t�w�l��l�^�C�v���ҏW�B
        l_orderActionParams.setStopPriceType(l_ifoOrderUnitRow.getStopPriceType());

        //  �t�w�l��l        stop_order_price
        //  �����P�ʃe�[�u��.�t�w�l��l���ҏW�B
        if (l_ifoOrderUnitRow.getStopOrderPriceIsNull())
        {
            l_orderActionParams.setStopOrderPrice(null);
        }
        else
        {
            l_orderActionParams.setStopOrderPrice(l_ifoOrderUnitRow.getStopOrderPrice());
        }

        //  �iW�w�l�j�����w�l        w_limit_price
        // �����P�ʃe�[�u��.�iW�w�l�j�����w�l���ҏW�B
        if (l_ifoOrderUnitRow.getWLimitPriceIsNull())
        {
            l_orderActionParams.setWLimitPrice(null);
        }
        else
        {
            l_orderActionParams.setWLimitPrice(l_ifoOrderUnitRow.getWLimitPrice());
        }

        // �����������t           expiration_date
        //�����P�ʃe�[�u��.�����������t���ҏW�B
        l_orderActionParams.setExpirationDate(l_ifoOrderUnitRow.getExpirationDate());

        //  �T�Z��n���           estimated_price
        //  �����P�ʃe�[�u��.�T�Z��n������ҏW
        if (l_ifoOrderUnitRow.getEstimatedPriceIsNull())
        {
            l_orderActionParams.setEstimatedPrice(null);
        }
        else
        {
            l_orderActionParams.setEstimatedPrice(l_ifoOrderUnitRow.getEstimatedPrice());
        }

        // ���������E����敪        modify_cancel_type
        //�����P�ʃe�[�u��.���������E����敪���ҏW
        l_orderActionParams.setModifyCancelType(l_ifoOrderUnitRow.getModifyCancelType());

        //  ���Ϗ���             closing_order
        //�����P�ʃe�[�u��.���Ϗ������ҏW
        l_orderActionParams.setClosingOrder(l_ifoOrderUnitRow.getClosingOrder());

        //  �����G���[���R�R�[�h         error_reason_code
        //�����P�ʃe�[�u��.�����G���[���R�R�[�h���ҏW
        l_orderActionParams.setErrorReasonCode(l_ifoOrderUnitRow.getErrorReasonCode());

        //  ���N�G�X�g�^�C�v              request_type
        //�����P�ʃe�[�u��.���N�G�X�g�^�C�v���ҏW
        l_orderActionParams.setRequestType(l_ifoOrderUnitRow.getRequestType());

        // �����ID                 trader_id
        //�����P�ʃe�[�u��.�����ID���ҏW
        if (l_ifoOrderUnitRow.getTraderIdIsNull())
        {
            l_orderActionParams.setTraderId(null);
        }
        else
        {
            l_orderActionParams.setTraderId(l_ifoOrderUnitRow.getTraderId());
        }

        //  �����o�H�敪            order_root_div
        //�����P�ʃe�[�u��.�����o�H�敪���ҏW
        l_orderActionParams.setOrderRootDiv(l_ifoOrderUnitRow.getOrderRootDiv());

        //  ����������            org_order_condition_type
        //�����P�ʃe�[�u��.�������������ҏW�B
        l_orderActionParams.setOrgOrderConditionType(l_ifoOrderUnitRow.getOrgOrderConditionType());

        // �������������Z�q       org_order_cond_operator
        //�����P�ʃe�[�u��.�������������Z�q���ҏW�B
        l_orderActionParams.setOrgOrderCondOperator(l_ifoOrderUnitRow.getOrgOrderCondOperator());

        //  ���t�w�l��l�^�C�v          org_stop_price_type
        //  �����P�ʃe�[�u��.���t�w�l��l�^�C�v���ҏW�B
        l_orderActionParams.setOrgStopPriceType(l_ifoOrderUnitRow.getOrgStopPriceType());

        // ���t�w�l��l         org_stop_order_price
        //�����P�ʃe�[�u��.���t�w�l��l���ҏW�B
        if (l_ifoOrderUnitRow.getOrgStopOrderPriceIsNull())
        {
            l_orderActionParams.setOrgStopOrderPrice(null);
        }
        else
        {
            l_orderActionParams.setOrgStopOrderPrice(l_ifoOrderUnitRow.getOrgStopOrderPrice());
        }

        // ���iW�w�l�j�����w�l           org_w_limit_price
        //  �����P�ʃe�[�u��.�iW�w�l�j�����w�l���ҏW�B
        if (l_ifoOrderUnitRow.getOrgWLimitPriceIsNull())
        {
            l_orderActionParams.setOrgWLimitPrice(null);
        }
        else
        {
            l_orderActionParams.setOrgWLimitPrice(l_ifoOrderUnitRow.getOrgWLimitPrice());
        }

        //  ���iW�w�l�j���s����          org_w_limit_exec_cond_type
        //�����P�ʃe�[�u��.���iW�w�l�j���s�������ҏW�B
        l_orderActionParams.setOrgWLimitExecCondType(
            l_ifoOrderUnitRow.getOrgWLimitExecCondType());

        //�iW�w�l�j���s����           w_limit_exec_cond_type
        //�����P�ʃe�[�u��.�iW�w�l�j���s�������ҏW
        l_orderActionParams.setWLimitExecCondType(
            l_ifoOrderUnitRow.getWLimitExecCondType());

        // �iW�w�l�j�֑ؑO�w�l              w_limit_before_limit_price
        //�����P�ʃe�[�u��.�iW�w�l�j�֑ؑO�w�l���ҏW
        if (l_ifoOrderUnitRow.getWLimitBeforeLimitPriceIsNull())
        {
            l_orderActionParams.setWLimitBeforeLimitPrice(null);
        }
        else
        {
            l_orderActionParams.setWLimitBeforeLimitPrice(
                l_ifoOrderUnitRow.getWLimitBeforeLimitPrice());
        }

        //�iW�w�l�j�֑ؑO���s����         w_limit_before_exec_cond_type
        //�����P�ʃe�[�u��.�iW�w�l�j�֑ؑO���s�������ҏW
        l_orderActionParams.setWLimitBeforeExecCondType(
            l_ifoOrderUnitRow.getWLimitBeforeExecCondType());

        //�s�ꂩ��m�F�ς݂̎��s����         confirmed_exec_condition_type
        //�����P�ʃe�[�u��.�s�ꂩ��m�F�ς݂̎��s�������ҏW
        l_orderActionParams.setConfirmedExecConditionType(
            l_ifoOrderUnitRow.getConfirmedExecConditionType());

        //���������敪expiration_date_type
        //�����P�ʃe�[�u��.���������敪���ҏW�B
        l_orderActionParams.setExpirationDateType(
            l_ifoOrderUnitRow.getExpirationDateType());

        log.exiting(STR_METHOD_NAME);
        return l_orderActionParams;
    }
}
@
