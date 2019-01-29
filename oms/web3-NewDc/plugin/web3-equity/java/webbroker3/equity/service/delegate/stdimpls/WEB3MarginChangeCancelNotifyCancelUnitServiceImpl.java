head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginChangeCancelNotifyCancelUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p�����������ʒm����ꌏ�T�[�r�XImpl(WEB3MarginChangeCancelNotifyCancelUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 䈋� (���u) �V�K�쐬
Revesion History : 2006/11/27 �����F (���u) ���f��No.1032
Revesion History : 2006/11/28 ��іQ (���u) ���f�� No.1067
Revesion History : 2007/01/31 ������(���u) ���f�� 1116
*/
package webbroker3.equity.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderRejectedMarketResponseMessage;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CanmodReceiptTypeDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3ModifiedResultDef;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityEstimatedPrice;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityReceiveCancelInterceptor;
import webbroker3.equity.WEB3EquityReceiveCancelSpec;
import webbroker3.equity.data.HostEqtypeOrderClmdReceiptParams;
import webbroker3.equity.define.WEB3EquityWlimitEnableStatusDivDef;
import webbroker3.equity.service.delegate.WEB3EquityChangeCancelNotifyDataAdapter;
import webbroker3.equity.service.delegate.WEB3EquityExecutedMailSenderService;
import webbroker3.equity.service.delegate.WEB3MarginChangeCancelNotifyCancelUnitService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p�����������ʒm����ꌏ�T�[�r�XImpl�j�B<BR>
 * <BR>
 * �M�p�����������ʒm����ꌏ�T�[�r�X�����N���X
 * @@version 1.0
 */
public class WEB3MarginChangeCancelNotifyCancelUnitServiceImpl
    implements WEB3MarginChangeCancelNotifyCancelUnitService
{
    /**
     * �i���O�o�̓��[�e�B���e�B�j�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MarginChangeCancelNotifyCancelUnitServiceImpl.class);
    /**
     * @@roseuid 414006700243
     */
    public WEB3MarginChangeCancelNotifyCancelUnitServiceImpl()
    {

    }

    /**
     * (notify���)<BR>
     * ��������ʒm���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p�����������ʒm����ꌏ�T�[�r�X�jnotify����v�Q��<BR>
     * <BR>
     * �߂�l�́A����������������ʒm�L���[�e�[�u���X�V���ɁA<BR>
     * �����敪�ݒ�l�Ƃ��Ďg�p����B
     * @@param l_params (������������ʒm�L���[Params)<BR>
     * �@@�@@�@@������������ʒm�L���[Params�I�u�W�F�N�g�B
     * @@param l_orderUnit (�����P��)<BR>
     * �@@�@@�@@�����P�ʃI�u�W�F�N�g�B
     * @@return String
     * @@roseuid 40F3DA5702CD
     */
    public String notifyCancel(
        HostEqtypeOrderClmdReceiptParams l_params,
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyChange(HostEqtypeOrderClmdReceiptParams, EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
        try
        {
            l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_ne)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ne.getMessage(),
                l_ne);
        }
        
        //1.1. validate�������ʃR�[�h()
        validateChangeResultCode(l_params);
        
        //1.2. ���Y�����P�ʂɑ΂���o�����c���Ă���ꍇ
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        if ((WEB3CanmodReceiptTypeDef.CANCEL.equals(l_params.getCanmodReceiptType()) == true)
        && (l_params.getModifiedQuantity() > l_orderUnitRow.getExecutedQuantity()))
        {
            return WEB3StatusDef.DEALING;
        }
        
        //1.3. create()
        WEB3EquityChangeCancelNotifyDataAdapter l_dataAdapter =
            WEB3EquityChangeCancelNotifyDataAdapter.create(l_params);
        
        //1.4. ��������ʒm���e()
        WEB3EquityReceiveCancelSpec l_receiveCancelSpec =
            new WEB3EquityReceiveCancelSpec();
            
        //1.5. get���s����()
        EqTypeExecutionConditionType l_conditionType =
            l_dataAdapter.getExecCondType();
            
        //1.6. set�����㎷�s����()
        l_receiveCancelSpec.setChangeAfterExecCond(l_conditionType);
        
        //1.7. get�l�i����()
        String l_strPriceConditionType = l_dataAdapter.getPriceConditionType();
        
        //1.8. set������l�i����()
        l_receiveCancelSpec.setChangeAfterPriceConditionType(l_strPriceConditionType);
        
        WEB3EquityEstimatedPrice l_equityEstimatedPrice = null;
        //�ʒm�L���[.��������ʒm�敪��"������s"�A
        //���� �����f�[�^�A�_�v�^.get�v�w�l�p�L����ԋ敪��"���~�b�g�����L��" �̂�
        if (WEB3CanmodReceiptTypeDef.CANCEL_FAILED.equals(l_params.getCanmodReceiptType())
            && WEB3EquityWlimitEnableStatusDivDef.LIMIT_ENABLE.equals(
                WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnit)))
        {
            //get�⏕����(����ID : long, �⏕����ID : long)
            //���N�G�X�g�����P��.����ID
            //���N�G�X�g�����P��.�⏕����ID
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount = null;
            try
            {
                l_subAccount =
                    (SubAccount)l_accountManager.getSubAccount(
                        l_orderUnitRow.getAccountId(),
                        l_orderUnitRow.getSubAccountId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //get�X�g�b�v�����������T�Z����v�Z����(EqtypeOrderUnit, SubAccount)
            l_equityEstimatedPrice =
                l_orderManager.getStopOrderExpireEstimatedPrice(
                    l_orderUnit,
                    l_subAccount);
        }

        //set�����P��(Double)
        if (l_equityEstimatedPrice != null)
        {
            l_receiveCancelSpec.setLimitPrice(l_equityEstimatedPrice.getCalcUnitPrice());
        }
        else
        {
            l_receiveCancelSpec.setLimitPrice(l_orderUnitRow.getConfirmedOrderPrice());
        }

        //1.10. set��n���()
        double l_dblEstimatedPrice;
        if (WEB3CanmodReceiptTypeDef.CANCEL.equals(l_params.getCanmodReceiptType()))
        {
            if (!l_orderUnit.isUnexecuted())
            {
                l_dblEstimatedPrice =
                    l_orderManager.getEstimateDeliveryAmountForClose(l_orderUnit);
            }
            else
            {
                l_dblEstimatedPrice = l_orderUnitRow.getEstimatedPrice();
            }
        }
        else
        {
            if (l_equityEstimatedPrice != null)
            {
                l_dblEstimatedPrice = l_equityEstimatedPrice.getEstimateDeliveryAmount();
            }
            else
            {
                l_dblEstimatedPrice = l_orderUnitRow.getConfirmedEstimatedPrice();
            }
        }
        l_receiveCancelSpec.setEstimatedPrice(l_dblEstimatedPrice);
        
        //1.11. ������������ʒm�C���^�Z�v�^()
        WEB3EquityReceiveCancelInterceptor l_interceptor =
            new WEB3EquityReceiveCancelInterceptor(l_params);
            
        //1.12. set��������ʒm���e()
        l_interceptor.setReceiveCancelSpec(l_receiveCancelSpec);
        
        //1.13. setThreadLocalPersistenceEventInterceptor()

        l_orderManager.setThreadLocalPersistenceEventInterceptor(
            l_interceptor);
            
        //1.14. getMarketResponseReceiverCallbackService()
        MarketAdapter l_adapter = l_tradingMod.getMarketAdapter();
        MarketResponseReceiverCallbackService l_marketCallbackService =
            l_adapter.getMarketResponseReceiverCallbackService();
        
        //1.15. setBusinessTimestamp()
        WEB3GentradeTradingTimeManagement.setBusinessTimestamp();

        //isSONAR���(EqTypeOrderUnit)
        //SONAR����̎�����ǂ������ʂ���B
        //�����͈ȉ��̒ʂ�ɃZ�b�g����B
        //�����P�ʁF�@@����.�����P��
        boolean l_blnIsSONARCancel = l_orderManager.isSONARCancel(l_orderUnit);

        //1.16. ��������̏ꍇ
        if (WEB3CanmodReceiptTypeDef.CANCEL.equals(l_params.getCanmodReceiptType()))
        {
            log.debug("��������̏ꍇ");
            //1.16.1 DefaultCancelOrderAcceptedMarketResponseMessage()
            DefaultCancelOrderAcceptedMarketResponseMessage l_message =
                new DefaultCancelOrderAcceptedMarketResponseMessage(l_orderUnit.getOrderId());
            //1.16.2. process()
            ProcessingResult l_result = l_marketCallbackService.process(l_message);
            if (l_result.isFailedResult())
            {
                throw new WEB3BaseException(
                    l_result.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_result.getErrorInfo().getErrorMessage());
            }                
            
            EqTypeOrderUnit l_newOrderUnit = null;
            try
            {
                l_newOrderUnit =
                    (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_ne)
            {
                throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            }	
            
            //1.16.3. isUnexecuted()
	        boolean l_isUnExe = l_newOrderUnit.isUnexecuted();
	            
	        //1.16.4. ��肪���Ă���ꍇ
	        if (l_isUnExe == false)
	        {
	            WEB3EquityExecutedMailSenderService l_mailSenderService =
	                (WEB3EquityExecutedMailSenderService)Services.getService(
	                    WEB3EquityExecutedMailSenderService.class);
	            l_mailSenderService.sendMailProcess(l_newOrderUnit, null);
	        }
            
            if (l_orderManager.isReserveOrderConfirmRequire(l_newOrderUnit))
            {
                WEB3ToSuccReservationEqTypeOrderUpdateService l_updateService =
                    (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                        WEB3ToSuccReservationEqTypeOrderUpdateService.class);
                l_updateService.cancelAllOrderUnit(l_newOrderUnit.getOrderId());
            }

            //(*)SONAR���͂̎���iisSONAR���() == true�j�̏ꍇ
            if (l_blnIsSONARCancel)
            {
                try
                {
                    //notify���[���G���W���T�[�o(EqTypeOrderUnit, OrderManagerPersistenceContext)
                    //�����P�ʁF�@@�����P�ʃI�u�W�F�N�g
                    //�����F�@@ CANCEL_ORDER_CONFIRMED_BY_MKT
                    l_orderManager.notifyRLS(
                        l_newOrderUnit,
                        OrderManagerPersistenceContext.CANCEL_ORDER_CONFIRMED_BY_MKT);
                }
                //(*)notify���[���G���W���T�[�o()�ɂċƖ��G���[���X���[���ꂽ�ꍇ
                //(*)catch���ď����𑱍s����B�@@�����[���o�b�N���Ȃ��B
                catch (WEB3BusinessLayerException l_ex)
                {
                    log.debug("notify���[���G���W���T�[�o()�ɂċƖ��G���[���X���[���ꂽ�ꍇ, �����[���o�b�N���Ȃ��B");
                }
            }
	    }
	    
        //1.17. ������s�̏ꍇ
        else if (WEB3CanmodReceiptTypeDef.CANCEL_FAILED.equals(l_params.getCanmodReceiptType()))
        {
            log.debug("������s�̏ꍇ");
            //(*)SONAR���͂̎���iisSONAR���() == true�j�ꍇ
            if (l_blnIsSONARCancel)
            {
                // notify���[���G���W���T�[�o(EqTypeOrderUnit, OrderManagerPersistenceContext)
                //�����͈ȉ��̒ʂ�ɐݒ肷��B
                //[�����̐ݒ�]
                //�����P�ʁF�@@�����P�ʃI�u�W�F�N�g
                //�����F�@@ CANCEL_ORDER_REJECTED_BY_MKT
                EqTypeOrderUnit l_eqTypeOrderUnit = null;
                try
                {
                    l_eqTypeOrderUnit =
                        (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("�e�[�u���ɊY������f�[�^������܂���B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                try
                {
                    l_orderManager.notifyRLS(
                        l_eqTypeOrderUnit,
                        OrderManagerPersistenceContext.CANCEL_ORDER_REJECTED_BY_MKT);
                }
                //(*)notify���[���G���W���T�[�o()�ɂċƖ��G���[���X���[���ꂽ�ꍇ
                //(*)catch���ď����𑱍s����B�@@�����[���o�b�N���Ȃ��B
                catch (WEB3BusinessLayerException l_ex)
                {
                    log.debug("notify���[���G���W���T�[�o()�ɂċƖ��G���[���X���[���ꂽ�ꍇ, �����[���o�b�N���Ȃ��B");
                }
            }

            //1.17.1 DefaultCancelOrderRejectedMarketResponseMessage()
            DefaultCancelOrderRejectedMarketResponseMessage l_message =
                new DefaultCancelOrderRejectedMarketResponseMessage(
                    l_orderUnit.getOrderId());
            //1.17.2. process()
            ProcessingResult l_result = l_marketCallbackService.process(
            l_message);
            if (l_result.isFailedResult())
            {
                throw new WEB3BaseException(
                    l_result.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_result.getErrorInfo().getErrorMessage());
            }

        }
        
            
        //1.18. �]�͍Čv�Z
        WEB3GentradeSubAccount l_subAccount = null;
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        try
        {
            l_subAccount =
                (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                    l_orderUnit.getAccountId(),
                    l_orderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ne)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
            (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
        l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
        
        //1.19. �߂�l"������"��return����B
        log.exiting(STR_METHOD_NAME);
        return WEB3HostStatusDef.COMPLETE_PROCESS;
    }

    /**
     * (validate�������ʃR�[�h)<BR>
     * ������ɂ�����A�y��������ʒm�L���[�e�[�u���z��<BR>
     *    �u�������ʃR�[�h�v�̒l�̐������`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�R�[�h�l���h0?�h�i��������ɊY���j�̏ꍇ�A<BR>
     *    �ȉ��̎���P�[�X�̂����ꂩ��<BR>�Y�����邩�`�F�b�N����B<BR>
     * �@@�@@�@@������ɂ��Y�����Ȃ��ꍇ�́A��O��throw����B<BR>
     * �@@�@@�@@�@@�@@�@@01�F�S�����<BR>
     * �@@�@@�@@�@@�@@�@@04�F�ꕔ���<BR>
     * �@@�@@�@@�@@�@@�@@06�F�ꕔ����s�\�i���o���Ȃ��j<BR>
     * �@@�@@�@@�@@�@@�@@07�F�ꕔ����s�\�i���o������j<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_00128
     * @@param l_eqtypeChangeCancelNotifyQueueParams (������������ʒm�L���[Params)<BR>
     * @@roseuid 40F3DFBD03DD
     */
    protected void validateChangeResultCode(HostEqtypeOrderClmdReceiptParams l_eqtypeChangeCancelNotifyQueueParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateChangeResultCode(HostEqtypeOrderClmdReceiptParams l_eqtypeChangeCancelNotifyQueueParams)";
        log.entering(STR_METHOD_NAME);
        String l_modifyResult =
            l_eqtypeChangeCancelNotifyQueueParams.getModifiedResult();
        if((l_modifyResult.length() == 2) && l_modifyResult.startsWith("0"))
        {        
            if (WEB3ModifiedResultDef.ALL_CANCEL.equals(l_modifyResult)
                || WEB3ModifiedResultDef.PARTIALLY_CANCEL.equals(l_modifyResult)
                || WEB3ModifiedResultDef.PARTIALLY_NOT_CANCEL_NO_EXECUTED.equals(l_modifyResult)
                || WEB3ModifiedResultDef.PARTIALLY_NOT_CANCEL_EXECUTED.equals(
                    l_modifyResult))
            {
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00128,
                    STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
