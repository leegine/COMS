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
filename	WEB3EquityReceiveCancelEventServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����������������ʒm����ꌏ�T�[�r�XImpl(WEB3EquityReceiveCancelEventServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/20 �������F(SRA) �V�K�쐬
Revesion History : 2006/11/06 �����F(���u) ���f�� 1032
Revesion History : 2006/11/28 �����F(���u) ���f�� 1064
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderRejectedMarketResponseMessage;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CanmodReceiptTypeDef;
import webbroker3.common.define.WEB3ModifiedResultDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityEstimatedPrice;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityReceiveCancelInterceptor;
import webbroker3.equity.WEB3EquityReceiveCancelSpec;
import webbroker3.equity.data.HostEqtypeOrderClmdReceiptParams;
import webbroker3.equity.define.WEB3EquityWlimitEnableStatusDivDef;
import webbroker3.equity.service.delegate.WEB3EquityChangeCancelNotifyDataAdapter;
import webbroker3.equity.service.delegate.WEB3EquityExecutedMailSenderService;
import webbroker3.equity.service.delegate.WEB3EquityReceiveCancelEventService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i����������������ʒm����ꌏ�T�[�r�XImpl�j�B<BR>
 * <BR>
 * ����������������ʒm����ꌏ�T�[�r�X�����N���X
 * @@version 1.0
 */
public class WEB3EquityReceiveCancelEventServiceImpl
    implements WEB3EquityReceiveCancelEventService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityReceiveCancelEventServiceImpl.class);
    
    /**
     * @@roseuid 40A435C90074
     */
    public WEB3EquityReceiveCancelEventServiceImpl()
    {
    }

    /**
     * (notify���)<BR>
     * <BR>
     * ��������ʒm�T�[�r�X���y������������ʒm�L���[�e�[�u���z�̎���f�[�^���ꌏ�󂯎��A<BR>
     * ������������^����������s�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i��������ʒm����ꌏ�T�[�r�X�jnotify����v�Q��<BR>
     * <BR>
     * �߂�l�́A����������������ʒm�L���[�e�[�u���X�V���ɁA<BR>
     * �����敪�ݒ�l�Ƃ��Ďg�p����B<BR>
     * <BR>
     * @@param l_params - (������������ʒm�L���[Params)<BR>
     * ������������ʒm�L���[�e�[�u���̂P���R�[�h<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * @@return String
     * @@roseuid 4036022D0259
     */
    public String notifyCancel(
        HostEqtypeOrderClmdReceiptParams l_params,
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "notifyCancel(HostEqtypeOrderClmdReceiptParams, EqTypeOrderUnit)";
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
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        //1.1. validate�������ʃR�[�h()
        validateChangeResultCode(l_params);

        //1.2. ���Y�����P�ʂɑ΂���o�����c���Ă���ꍇ
        if ((WEB3CanmodReceiptTypeDef.CANCEL.equals(l_params.getCanmodReceiptType()) == true)
        && (l_params.getModifiedQuantity() > l_orderUnitRow.getExecutedQuantity()))
        {
            return WEB3StatusDef.DEALING;
        }
        
        //1.3. create()
        WEB3EquityChangeCancelNotifyDataAdapter l_dataAdapter =
            WEB3EquityChangeCancelNotifyDataAdapter.create(l_params);

        //1.4. ��������ʒm���e()
        WEB3EquityReceiveCancelSpec l_receiveCancelSpec = new WEB3EquityReceiveCancelSpec();

        //1.5. get���s����()
        EqTypeExecutionConditionType l_conditionType =
            l_dataAdapter.getExecCondType();
            
        //1.6. set�����㎷�s����()
        l_receiveCancelSpec.setChangeAfterExecCond(l_conditionType);
        
        //1.7. get�l�i����()
        String l_strPriceConditionType = l_dataAdapter.getPriceConditionType();
        
        //1.8. set������l�i����()
        l_receiveCancelSpec.setChangeAfterPriceConditionType(l_strPriceConditionType);
        
        WEB3GentradeAccountManager l_accManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        double l_dblEstimatedPrice = 0.0;
        double l_dblLimitPrice = 0.0;
        WEB3EquityEstimatedPrice l_equityEstimatedPrice = null;
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
            //�s�ꂩ��m�F�ς݂̒����P��
            l_dblLimitPrice = l_orderUnitRow.getConfirmedOrderPrice();
        }
        //�ʒm�L���[.��������ʒm�敪��"������s"�A����
        //�����f�[�^�A�_�v�^.get�v�w�l�p�L����ԋ敪��"���~�b�g�����L��" �̂�
        else if (WEB3CanmodReceiptTypeDef.CANCEL_FAILED.equals(l_params.getCanmodReceiptType()))
        {
            if (WEB3EquityWlimitEnableStatusDivDef.LIMIT_ENABLE.equals(
                WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnit)))
            {
                //get�⏕����(����ID : long, �⏕����ID : long)
                WEB3GentradeSubAccount l_subAccount = null;
                try
                {
                    l_subAccount =
                        (WEB3GentradeSubAccount)l_accManager.getSubAccount(
                            l_orderUnit.getAccountId(),
                            l_orderUnit.getSubAccountId());
                }
                catch (NotFoundException l_nfe)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_nfe.getMessage(),
                        l_nfe);
                }

                //get�X�g�b�v�����������T�Z����v�Z����(EqtypeOrderUnit, SubAccount)
                l_equityEstimatedPrice =
                    l_orderManager.getStopOrderExpireEstimatedPrice(l_orderUnit, l_subAccount);
            }

            if (l_equityEstimatedPrice != null)
            {
                //get�X�g�b�v�����������T�Z����v�Z���ʂ̖߂�l��null�ȊO�̏ꍇ
                l_dblLimitPrice = l_equityEstimatedPrice.getCalcUnitPrice();
                l_dblEstimatedPrice = l_equityEstimatedPrice.getEstimateDeliveryAmount();
            }
            else
            {
                //�s�ꂩ��m�F�ς݂̊T�Z��n���
                l_dblEstimatedPrice = l_orderUnitRow.getConfirmedEstimatedPrice();
                //�s�ꂩ��m�F�ς݂̒����P��
                l_dblLimitPrice = l_orderUnitRow.getConfirmedOrderPrice();
            }
        }

        //set�����P��(Double)
        l_receiveCancelSpec.setLimitPrice(l_dblLimitPrice);

        //set��n���(double)
        l_receiveCancelSpec.setEstimatedPrice(l_dblEstimatedPrice);

        //1.11. ������������ʒm�C���^�Z�v�^()
        WEB3EquityReceiveCancelInterceptor l_interceptor =
            new WEB3EquityReceiveCancelInterceptor(l_params);

        //1.12. set��������ʒm���e()
        l_interceptor.setReceiveCancelSpec(l_receiveCancelSpec);

        //1.13. setThreadLocalPersistenceEventInterceptor()
        l_orderManager.setThreadLocalPersistenceEventInterceptor(
            l_interceptor);

        //1.14. setBusinessTimestamp()
        WEB3GentradeTradingTimeManagement.setBusinessTimestamp();
        
        //isSONAR���(EqTypeOrderUnit)
        boolean l_blnIsSONARCancel = l_orderManager.isSONARCancel(l_orderUnit);
        
        //1.15. �ʒm�L���[.��������ʒm�敪��"�������"�̏ꍇ
        MarketAdapter l_marketAdapter = l_tradingMod.getMarketAdapter();
        MarketResponseReceiverCallbackService l_callbackService =
            l_marketAdapter.getMarketResponseReceiverCallbackService();
        if (WEB3CanmodReceiptTypeDef.CANCEL.equals(l_params.getCanmodReceiptType()))
        {
            //1.15.1. DefaultCancelOrderAcceptedMarketResponseMessage()
            DefaultCancelOrderAcceptedMarketResponseMessage l_message =
                new DefaultCancelOrderAcceptedMarketResponseMessage(
                    l_orderUnit.getOrderId());
            //1.15.2. process()
            ProcessingResult l_result = l_callbackService.process(l_message);
            if (l_result.isFailedResult())
            {
                throw new WEB3BaseException(
                    l_result.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_result.getErrorInfo().getErrorMessage());
            }
            //1.15.3. getOrderUnit()
            try
            {
                l_orderUnit =
                    (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_ne)
            {
                throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            //1.15.4. isUnexecuted()
            boolean l_isUnexecuted = l_orderUnit.isUnexecuted();
            //1.15.5. �o�����t���Ă���i�����P��.isUnexecuted( )==false�j�̏ꍇ�̂�
            if (l_isUnexecuted == false)
            {
                //1.15.5.1. sendMailProcess()
                WEB3EquityExecutedMailSenderService l_mailSenderService =
                    (WEB3EquityExecutedMailSenderService)Services.getService(
                        WEB3EquityExecutedMailSenderService.class);
                l_mailSenderService.sendMailProcess(l_orderUnit, null);
            }
            
            if (l_orderManager.isReserveOrderConfirmRequire(l_orderUnit))
            {
                WEB3ToSuccReservationEqTypeOrderUpdateService l_updateService =
                    (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                        WEB3ToSuccReservationEqTypeOrderUpdateService.class);
                l_updateService.cancelAllOrderUnit(l_orderUnit.getOrderId());
            }
            //SONAR���͂̎���iisSONAR���()�̖߂�l == true�j�̏ꍇ
            if (l_blnIsSONARCancel)
            {
                log.debug("isSONAR���()�̖߂�l == true");
                //notify���[���G���W���T�[�o(EqTypeOrderUnit, OrderManagerPersistenceContext)
                try
                {
                    l_orderManager.notifyRLS(
                        l_orderUnit,
                        OrderManagerPersistenceContext.CANCEL_ORDER_CONFIRMED_BY_MKT);
                }
                catch (WEB3BusinessLayerException l_ex)
                {
                    log.debug("notify���[���G���W���T�[�o()�ɂċƖ��G���[���X���[���ꂽ�ꍇ�A�����[���o�b�N���Ȃ��B");
                }
            }
        }
        //1.16. �ʒm�L���[.��������ʒm�敪��"������s"�̏ꍇ
        else if (WEB3CanmodReceiptTypeDef.CANCEL_FAILED.equals(l_params.getCanmodReceiptType()))
        {
            //getOrderUnit()
            try
            {
                l_orderUnit =
                    (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_ne)
            {
                throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            // SONAR���͂̎���łȂ��iisSONAR���() == true�j�ꍇ
            if (l_blnIsSONARCancel)
            {
                //notify���[���G���W���T�[�o(EqTypeOrderUnit, OrderManagerPersistenceContext)
                try
                {
                    l_orderManager.notifyRLS(
                        l_orderUnit,
                        OrderManagerPersistenceContext.CANCEL_ORDER_REJECTED_BY_MKT);
                }
                catch (WEB3BusinessLayerException l_ex)
                {
                    log.debug("notify���[���G���W���T�[�o()�ɂċƖ��G���[���X���[���ꂽ�ꍇ�A�����[���o�b�N���Ȃ��B");
                }
            }

            //1.16.1. DefaultCancelOrderRejectedMarketResponseMessage()
            DefaultCancelOrderRejectedMarketResponseMessage l_message =
                new DefaultCancelOrderRejectedMarketResponseMessage(
                    l_orderUnit.getOrderId());
            //1.16.2. process()
            ProcessingResult l_result = l_callbackService.process(l_message);
            if (l_result.isFailedResult())
            {
                throw new WEB3BaseException(
                    l_result.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_result.getErrorInfo().getErrorMessage());
            }
        }
        
        //1.17. �]�͍Čv�Z
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
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ne.getMessage(),
                l_ne);
        }
        WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
            (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
        l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);

        //1.18. �߂�l"������"��return����B
        log.exiting(STR_METHOD_NAME);
        return WEB3StatusDef.DEALT;
    }

    /**
     * (validate�������ʃR�[�h)<BR>
     * <BR>
     * ������ɂ�����A�y��������ʒm�L���[�e�[�u���z�́u�������ʃR�[�h�v�̒l�̐������`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�R�[�h�l���h0?�h�i��������ɊY���j�̏ꍇ�A�ȉ��̎���P�[�X�̂����ꂩ�ɊY�����邩�`�F�b�N����B<BR>
     * �@@�@@�@@������ɂ��Y�����Ȃ��ꍇ�́A��O��throw����B<BR>
     * �@@�@@�@@�@@�@@�@@01�F�S�����<BR>
     * �@@�@@�@@�@@�@@�@@04�F�ꕔ���<BR>
     * �@@�@@�@@�@@�@@�@@06�F�ꕔ����s�\�i���o���Ȃ��j<BR>
     * �@@�@@�@@�@@�@@�@@07�F�ꕔ����s�\�i���o������j<BR>
     * <BR>
     * class    : WEB3BusinessLayerException<BR>
     * tag      : BUSINESS_ERROR_00128<BR>
     * <BR>
     * @@param l_params - (������������ʒm�L���[Params)
     * @@throws WEB3BusinessLayerException
     * @@roseuid 4039DC0303B7
     */
    public void validateChangeResultCode(HostEqtypeOrderClmdReceiptParams l_params)
        throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validateChangeResultCode(HostEqtypeOrderClmdReceiptParams)";
        log.entering(STR_METHOD_NAME);

        String l_modifiedResult = l_params.getModifiedResult();
        String l_strFirstChar = l_modifiedResult.substring(0, 1);
        if (l_strFirstChar.equals("0"))
        {
            if (WEB3ModifiedResultDef.ALL_CANCEL.equals(l_modifiedResult) ||
                WEB3ModifiedResultDef.PARTIALLY_CANCEL.equals(l_modifiedResult) ||
                WEB3ModifiedResultDef.PARTIALLY_NOT_CANCEL_NO_EXECUTED.equals(l_modifiedResult) ||
                WEB3ModifiedResultDef.PARTIALLY_NOT_CANCEL_EXECUTED.equals(l_modifiedResult))
            {
            }
            else
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00128,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
