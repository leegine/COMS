head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOrderExecNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����o���ʒm�ꌏ�T�[�r�XImpl(WEB3MarginExecNotifyUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/08 ������ (���u) �V�K�쐬
                   2006/11/28 ��іQ (���u) ���f�� No.1040 1072
*/

package webbroker3.equity.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderExecution;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultFillOrderUnitSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultOrderFillMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultUndoOrderFillMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;

import webbroker3.equity.WEB3EquityCancelOrderConfirmInterceptor;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3MarginOrderExecNotifyUpdateInterceptor;
import webbroker3.equity.data.HostEquityOrderExecNotifyParams;
import webbroker3.equity.service.delegate.WEB3EquityExecutedMailSenderService;
import webbroker3.equity.service.delegate.WEB3MarginOrderExecNotifyUnitService;
import webbroker3.equity.service.delegate.WEB3EquityReceiveCloseOrderUnitService;
import webbroker3.equity.data.HostEqtypeCloseOrderNotifyParams;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.define.WEB3ExpirationReasonCodeDef;
import webbroker3.common.define.WEB3CloseNotifyTypeDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;

/**
 * �i�M�p����o���ʒm�ꌏ�T�[�r�XImpl�j�B<BR>
 * <BR>
 * �M�p����o���ʒm�ꌏ�T�[�r�X�����N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginOrderExecNotifyUnitServiceImpl implements WEB3MarginOrderExecNotifyUnitService 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginOrderExecNotifyUnitServiceImpl.class);
    
    /**
     * @@roseuid 4140066E01E6
     */
    public WEB3MarginOrderExecNotifyUnitServiceImpl() 
    {
     
    }
    
    /**
     * (notify���)<BR>
     * ��菈�������s����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p����o���ʒm�ꌏ�T�[�r�X�jnotify���v�Q�ƁB<BR>
     * @@param l_ordeUnit - (�����P��)<BR>
     * �o���ʒm�L���[.���ʃR�[�h�ɊY������A�����P�ʃI�u�W�F�N�g�B<BR>
     * @@param l_equityExecNotifyQueueParams - 
     *  �����o���ʒm�L���[Params�I�u�W�F�N�g�B<BR>
     * @@roseuid 40CE82CF039D
     */
    public void notifyExecute(EqTypeOrderUnit l_orderUnit, 
        HostEquityOrderExecNotifyParams l_equityExecNotifyQueueParams)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "notifyExecute(EqTypeOrderUnit,HostEquityOrderExecNotifyParams)";
        log.entering(STR_METHOD_NAME); 
        
        log.debug("�V�[�P���X�}�u�i�M�p����o���ʒm�ꌏ�T�[�r�X�jnotify���v�Q��");
        //�M�p����o���ʒm / �i�M�p����o���ʒm�ꌏ�T�[�r�X�jnotify���
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
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
        
        //3 validate�������(OrderStatusEnum)(�M�p����o���ʒm�ꌏ�T�[�r�XImpl::validate�������)
        //�����͈ȉ��̒ʂ�ɐݒ肷��B 
        //  ������ԁF�@@�����̒����P��.�������
        this.validateOrderStatus(l_orderUnit);

        //4 validate�S�����(�����P��)(�M�p����o���ʒm�ꌏ�T�[�r�XImpl::validate�S�����)
        this.validateAllOrderExecution(l_orderUnit);
    
        //5 validate��d���(�����P��, double)(�M�p����o���ʒm�ꌏ�T�[�r�XImpl::validate��d���)
        //�����͈ȉ��̒ʂ�ɐݒ肷��B 
        //  �����P�ʁF�@@�����̒����P�� 
        //  ��芔���F�@@�����̊����o���ʒm�L���[Params.��芔��
        this.validateDoubleOrderExecution(l_orderUnit,l_equityExecNotifyQueueParams.getExecQuantity());
        
        // ------------------------------------------------------------------
        // �M�p�o���ʒm�X�V�C���^�Z�v�^�̐���
        // ------------------------------------------------------------------
        WEB3MarginOrderExecNotifyUpdateInterceptor l_updateInterceptor = new WEB3MarginOrderExecNotifyUpdateInterceptor(
            l_equityExecNotifyQueueParams.getExecTimestamp(),
            l_equityExecNotifyQueueParams.getExecPrice(),
            l_equityExecNotifyQueueParams.getExecQuantity());
        
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
        
        //6 DefaultFillOrderUnitSpec(long, double, double)
        //  (DefaultFillOrderUnitSpec::DefaultFillOrderUnitSpec)
        //�����͈ȉ��̒ʂ�ɐݒ肷��B 
        //  �����P��ID�F�@@�����̒����P��.�����P��ID 
        //  ��芔���F�@@�����̊����o���ʒm�L���[Params.��芔�� 
        //  ���P���F�@@�����̊����o���ʒm�L���[Params.���P��
        DefaultFillOrderUnitSpec l_unitSpec = new DefaultFillOrderUnitSpec(
        l_orderUnit.getOrderUnitId(),
            l_equityExecNotifyQueueParams.getExecQuantity(),
            l_equityExecNotifyQueueParams.getExecPrice());
        
        //7 DefaultOrderFillMarketResponseMessage(long, FillOrderUnitSpec)
        //  (DefaultOrderFillMarketResponseMessage::DefaultOrderFillMarketResponseMessage)
        //�@@�����͈ȉ��̒ʂ�ɐݒ肷��B 
        //  ����ID�F�@@�����̒����P��.����ID 
        //  FillOrderUnitSpec�F�@@��������DefaultFillOrderUnitSpec�I�u�W�F�N�g
        DefaultOrderFillMarketResponseMessage l_responseMessage = 
            new DefaultOrderFillMarketResponseMessage(
                l_orderUnit.getOrderId(),
                l_unitSpec);
       
        //8 process(MarketResponseMessage)(EqTypeMarketResponseReceiverCallbackService::process)
        //�@@�����͈ȉ��̒ʂ�ɐݒ肷��B 
        //  OrderFillMarketResponseMessage�F�@@ 
        //  ��������DefaultOrderFillMarketResponseMessage�I�u�W�F�N�g
        EqTypeMarketResponseReceiverCallbackService l_callbackService = 
            (EqTypeMarketResponseReceiverCallbackService)l_finApp.getTradingModule(
            ProductTypeEnum.EQUITY).getMarketAdapter().getMarketResponseReceiverCallbackService();
        log.debug("********** process(DefaultOrderFillMarketResponseMessage) START **********");
        ProcessingResult l_result = l_callbackService.process(l_responseMessage);
        if (l_result.isFailedResult())
        {
            WEB3BaseException l_baseException = new WEB3BaseException(
                l_result.getErrorInfo(), 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_result.getErrorInfo().getErrorMessage());
            log.error(STR_METHOD_NAME, l_baseException);
            throw l_baseException;
        }        
        log.debug("********** process(DefaultOrderFillMarketResponseMessage) EXIT **********");
        
        try
        {
            l_orderUnit = (EqTypeOrderUnit)l_finApp.getTradingModule(
                ProductTypeEnum.EQUITY).getOrderManager().getOrderUnit(
            l_orderUnit.getOrderUnitId());                       
        }
        catch (NotFoundException l_nfe)
        {
            log.error(l_nfe.getMessage(),l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(), l_nfe);
        }

        boolean l_blnFullyExecuted = l_orderUnit.isFullyExecuted();

        //21 isFullyExecuted( )==true�̏ꍇ�̂�
        if (l_blnFullyExecuted == true)
        {
            //22 sendMailProcess(�����P��, String)(��胁�[�����M�T�[�r�XImpl::sendMailProcess)
            WEB3EquityExecutedMailSenderService l_mailSenderService = 
                (WEB3EquityExecutedMailSenderService)Services.getService(
                WEB3EquityExecutedMailSenderService.class);
            //l_mailSenderService.sendMailProcess(l_ordeUnit,l_equityExecNotifyQueueParams.request_code);            
            l_mailSenderService.sendMailProcess(l_orderUnit, null);

            //notify���[���G���W���T�[�o(EqTypeOrderUnit, OrderManagerPersistenceContext)
            try
            {
                l_orderManager.notifyRLS(
                    l_orderUnit,
                    OrderManagerPersistenceContext.FILL_ORDER);
            }
            //notify���[���G���W���T�[�o()�ɂċƖ��G���[���X���[���ꂽ�ꍇ
            //catch���ď������p������B�i�����[���o�b�N���Ȃ��j
            catch (WEB3BusinessLayerException l_ex)
            {
                log.debug("notify���[���G���W���T�[�o()�ɂċƖ��G���[���X���[���ꂽ�ꍇ, ���[���o�b�N���Ȃ�");
            } 
        }        
        
        // 1.11. isPartiallyExecuted()
        if (l_orderUnit.isPartiallyExecuted())
        {
            // 1.12. �ꕔ��肩���Ώے����P��.�l�i������"���s�c�����"�̏ꍇ�̂݁A�����������������s�B
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            if (WEB3PriceConditionDef.PARTIALLY_CANCEL_ORDER.equals(l_orderUnitRow.getPriceConditionType()))
            {
                // 1.12.1. ���������ʒm�L���[Params�̃C���X�^���X�����A�y�уv���p�e�B�Z�b�g
                HostEqtypeCloseOrderNotifyParams l_hostCloseNotifyParams = new HostEqtypeCloseOrderNotifyParams();
                l_hostCloseNotifyParams.setRequestCode(WEB3HostRequestCodeDef.EQUITY_ORDER_CLOSE_NOTICE);
                l_hostCloseNotifyParams.setInstitutionCode(l_equityExecNotifyQueueParams.getInstitutionCode());
                l_hostCloseNotifyParams.setBranchCode(l_equityExecNotifyQueueParams.getBranchCode());
                AccountManager l_accountManager = l_finApp.getAccountManager();
                MainAccount l_mainAccount = null;
                try
                {
                    l_mainAccount = 
                        l_accountManager.getMainAccount(l_orderUnit.getAccountId());
                }
                catch (NotFoundException l_nfe)
                {
                    log.error(STR_METHOD_NAME, l_nfe);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                        this.getClass().getName() + STR_METHOD_NAME, 
                        l_nfe.getMessage(), l_nfe);
                }
                l_hostCloseNotifyParams.setAccountCode(l_mainAccount.getAccountCode());
                l_hostCloseNotifyParams.setTraderCode(l_equityExecNotifyQueueParams.getTraderCode());
                l_hostCloseNotifyParams.setOrderRequestNumber(l_equityExecNotifyQueueParams.getOrderRequestNumber());
                l_hostCloseNotifyParams.setExecutedQuantity(l_orderUnitRow.getExecutedQuantity());
                l_hostCloseNotifyParams.setReasonCode(WEB3ExpirationReasonCodeDef.PARTIALLY_EXECUTE_CLOSE);
                l_hostCloseNotifyParams.setCloseNotifyType(WEB3CloseNotifyTypeDef.CLOSE);
                l_hostCloseNotifyParams.setErrorMessage(null);
                l_hostCloseNotifyParams.setStatus(WEB3HostStatusDef.NOT_STARTED_PROCESS);

                // 1.12.2. exec����()
                WEB3EquityReceiveCloseOrderUnitService l_unitService =
                    (WEB3EquityReceiveCloseOrderUnitService)Services.getService(
                        WEB3EquityReceiveCloseOrderUnitService.class);
                l_unitService.execCloseOrder(l_hostCloseNotifyParams, l_orderUnit);
            }
        }
        
        // 1.13 call�����ʒm����()        
        this.callExpirationNotify(l_orderUnit, l_equityExecNotifyQueueParams);         
        
        // 1.14 call����ʒm����()
        this.callCancelNotify(l_orderUnit, l_equityExecNotifyQueueParams);
        
        // 1.15 �]�͍Čv�Z()
        try
        {        
            AccountManager l_accountManager = l_finApp.getAccountManager();
            MainAccount l_mainAccount = null;
            try
            {
                l_mainAccount = 
                    l_accountManager.getMainAccount(l_orderUnit.getAccountId());
            }
            catch (NotFoundException l_nfe)
            {
                log.error(STR_METHOD_NAME, l_nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_nfe.getMessage(), l_nfe);
            }
            //getSubAccount
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
                    
            log.debug("�]�͍Čv�Z���s��");
            WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
                (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
            l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(), l_nfe);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (notify�����)<BR>
     * ��������������s����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p����o���ʒm�ꌏ�T�[�r�X�jnotify������v�Q�ƁB<BR>
     * @@param l_ordeUnit - (�����P��)<BR>
     * �o���ʒm�L���[.���ʃR�[�h�ɊY������A�����P�ʃI�u�W�F�N�g�B<BR>
     * @@param l_equityExecNotifyQueueParams - <BR>
     *   �����o���ʒm�L���[Params�I�u�W�F�N�g�B<BR>
     * @@roseuid 40CE82EE03DC
     */
    public void notifyExecuteCancel(EqTypeOrderUnit l_orderUnit, 
        HostEquityOrderExecNotifyParams l_equityExecNotifyQueueParams)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "notifyExecuteCancel(EqTypeOrderUnit,HostEquityOrderExecNotifyParams)";
        log.entering(STR_METHOD_NAME); 
        
        log.debug("�V�[�P���X�}�u�i�M�p����o���ʒm�ꌏ�T�[�r�X�jnotify������v�Q��");
        //�M�p����o���ʒm / �i�M�p����o���ʒm�ꌏ�T�[�r�X�jnotify�����
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
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
        
        //1 validate�������()
        validateOrderStatus(l_orderUnit);
        
        //2 get����Ώۖ��(�����P��, double, double)(�g�����������}�l�[�W��::get����Ώۖ��)
        //�����͈ȉ��̒ʂ�ɐݒ肷��B 
        //  �����P�ʁF�@@�����̒����P�� 
        //  ��芔���F�@@�����̊����o���ʒm�L���[Params.��芔�� 
        //  ���P���F�@@�����̊����o���ʒm�L���[Params.���P��
        log.debug("================== ����Ώۖ�� �J�n ===================");
        log.debug("�����P��:" + l_orderUnit.getOrderUnitId());   
        log.debug("��芔��:" + l_equityExecNotifyQueueParams.getExecQuantity());
        log.debug("���P��:" + l_equityExecNotifyQueueParams.getExecPrice());
        log.debug("================== ����Ώۖ�� �I�� ===================");

        EqTypeOrderExecution l_orderExecution = l_orderManager.getCancelOrderExecution(
            l_orderUnit,
            l_equityExecNotifyQueueParams.getExecQuantity(),
            l_equityExecNotifyQueueParams.getExecPrice());
        if (l_orderExecution == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00676, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        // 1.10. �ꕔ���ɑ΂���������������Ώے����P��.�l�i����=="���s�c�����"
        // �@@�@@�@@�̏ꍇ�̂݁A����������������s�B
        if (l_orderExecution.getExecutionQuantity() < l_orderUnit.getQuantity())
        {
            if (WEB3PriceConditionDef.PARTIALLY_CANCEL_ORDER.equals(l_orderUnitRow.getPriceConditionType()))
            {
                // 1.10.1. ���������ʒm�L���[Params�̃C���X�^���X�����A�y�уv���p�e�B�Z�b�g
                HostEqtypeCloseOrderNotifyParams l_hostCloseNotifyParams = new HostEqtypeCloseOrderNotifyParams();
                l_hostCloseNotifyParams.setRequestCode(WEB3HostRequestCodeDef.EQUITY_ORDER_CLOSE_NOTICE);
                l_hostCloseNotifyParams.setInstitutionCode(l_equityExecNotifyQueueParams.getInstitutionCode());
                l_hostCloseNotifyParams.setBranchCode(l_equityExecNotifyQueueParams.getBranchCode());
                l_hostCloseNotifyParams.setAccountCode(l_equityExecNotifyQueueParams.getAccountCode());
                l_hostCloseNotifyParams.setTraderCode(l_equityExecNotifyQueueParams.getTraderCode());
                l_hostCloseNotifyParams.setOrderRequestNumber(l_equityExecNotifyQueueParams.getOrderRequestNumber());
                l_hostCloseNotifyParams.setExecutedQuantity(l_orderUnitRow.getExecutedQuantity());
                l_hostCloseNotifyParams.setReasonCode(WEB3ExpirationReasonCodeDef.PARTIALLY_EXECUTE_CLOSE);
                l_hostCloseNotifyParams.setCloseNotifyType(WEB3CloseNotifyTypeDef.CLOSE_CANCEL);
                l_hostCloseNotifyParams.setErrorMessage(null);
                l_hostCloseNotifyParams.setStatus(WEB3HostStatusDef.NOT_STARTED_PROCESS);

                // 1.10.2. exec����()
                WEB3EquityReceiveCloseOrderUnitService l_unitService =
                    (WEB3EquityReceiveCloseOrderUnitService)Services.getService(
                        WEB3EquityReceiveCloseOrderUnitService.class);
                l_unitService.execCloseOrder(l_hostCloseNotifyParams, l_orderUnit);
            }
        }
        
        // �M�p����o���ʒm�X�V�C���^�Z�v�^�̐���
        WEB3MarginOrderExecNotifyUpdateInterceptor l_updateInterceptor = new WEB3MarginOrderExecNotifyUpdateInterceptor(
            l_equityExecNotifyQueueParams.getExecTimestamp(),
            l_equityExecNotifyQueueParams.getExecPrice(),
            l_equityExecNotifyQueueParams.getExecQuantity());
            
        // �M�p����o���ʒm�X�V�C���^�Z�v�^���Z�b�g�B
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);

        //3 getOrderId()
        long l_lngOrderId = l_orderUnit.getOrderId();
        
        //4  DefaultUndoOrderFillMarketResponseMessage(long, long)
        //  (DefaultUndoOrderFillMarketResponseMessage::DefaultUndoOrderFillMarketResponseMessage)
        //�����͈ȉ��̒ʂ�ɐݒ肷��B 
        //  ����ID�F�@@getOrderId( )�̖߂�l�@@ 
        //  ���ID�Fget����Ώۖ��( )�̖߂�l�̖��I�u�W�F�N�g.getOrderExecutionId( )
        DefaultUndoOrderFillMarketResponseMessage l_responseMessage = 
            new DefaultUndoOrderFillMarketResponseMessage(
                l_lngOrderId,
                l_orderExecution.getOrderExecutionId());
            
        //5 process(MarketResponseMessage)(EqTypeMarketResponseReceiverCallbackService::process)
        //�����͈ȉ��̒ʂ�ɐݒ肷��B 
        //  UndoOrderFillMarketResponseMessage�F�@@ 
        //  ��������DefaultUndoOrderFillMarketResponseMessage�I�u�W�F�N�g
        EqTypeMarketResponseReceiverCallbackService l_callbackService = 
            (EqTypeMarketResponseReceiverCallbackService)l_finApp.getTradingModule(
            ProductTypeEnum.EQUITY).getMarketAdapter().getMarketResponseReceiverCallbackService();
        log.debug("********** process(DefaultUndoOrderFillMarketResponseMessage) START **********");
        ProcessingResult l_result = l_callbackService.process(l_responseMessage);
        if (l_result.isFailedResult())
        {
            WEB3BaseException l_baseException = new WEB3BaseException(
                l_result.getErrorInfo(), 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_result.getErrorInfo().getErrorMessage());
            log.error(STR_METHOD_NAME, l_baseException);
            throw l_baseException;
        } 
        log.debug("********** process(DefaultUndoOrderFillMarketResponseMessage) EXIT **********");
        
        try
        {
            l_orderUnit = (EqTypeOrderUnit)l_finApp.getTradingModule(
                ProductTypeEnum.EQUITY).getOrderManager().getOrderUnit(
                l_orderUnit.getOrderUnitId());                       
        }
        catch (NotFoundException l_nfe)
        {
            log.error(l_nfe.getMessage(),l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(), l_nfe);
        }
        
        //15 update�����f�[�^(EqTypeOrderExecution)(�����|�W�V�����}�l�[�W��::update�����f�[�^)
        WEB3EquityPositionManager l_positionManager = 
            (WEB3EquityPositionManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getPositionManager();
        l_positionManager.updateExecutedData(l_orderExecution);
        
        //17 undoSendMail(EqTypeOrderUnit)(��胁�[�����M�T�[�r�X::undoSendMail)
        //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
        //  �����P�ʁF�@@�����̒����P�� 
        WEB3EquityExecutedMailSenderService l_mailSenderService = 
            (WEB3EquityExecutedMailSenderService)Services.getService(
            WEB3EquityExecutedMailSenderService.class);
        l_mailSenderService.undoSendMail(l_orderUnit);


        try
        {
            l_orderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnit(
                l_orderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_nfe)
        {
            log.error(l_nfe.getMessage(),l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(), l_nfe);
        }

        //18 exec�������n�������(�����P��)(�M�p����o���ʒm�ꌏ�T�[�r�XImpl::exec�������n�������)
        //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
        //  �����P�ʁF�@@�����̒����P�� 
        log.debug("18 exec�������n�������()�𒲗p���J�n");
        this.execSwapMarginOrderCancel(l_orderUnit);
        log.debug("18 exec�������n�������()�𒲗p���I��");
        
        // 1.12. �]�͍Čv�Z()
        try
        {        
            AccountManager l_accountManager = l_finApp.getAccountManager();
            MainAccount l_mainAccount = null;
            try
            {
                l_mainAccount = 
                    l_accountManager.getMainAccount(l_orderUnit.getAccountId());
            }
            catch (NotFoundException l_nfe)
            {
                log.error(STR_METHOD_NAME, l_nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_nfe.getMessage(), l_nfe);
            }
            //getSubAccount
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
                    
            log.debug("�]�͍Čv�Z���s��");
            WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
                (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
            l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(), l_nfe);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (call�����ʒm����)<BR>
     * �o���҂��̎����ʒm�����݂��邩�ǂ������A�����P�ʂ̃v���p�e�B��蔻�肵�A <BR>
     * �����������K�v�ȏꍇ�́u���������ꌏ�T�[�r�X�v���R�[������B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�i�����o���ʒm���ʁj�o���ʒm�ꌏ�T�[�r�X�jcall�����ʒm�����v�Q�ƁB <BR>
     * @@param l_ordeUnit - (�����P��)<BR>
     * �o���ʒm�L���[.���ʃR�[�h�ɊY������A�����P�ʃI�u�W�F�N�g�B<BR>
     * @@param l_equityOrderExecNotifyParams - <BR>
     *   �����o���ʒm�L���[Params�I�u�W�F�N�g�B<BR>
     * @@roseuid 40D0DDD002C3
     */
    protected void callExpirationNotify(EqTypeOrderUnit l_orderUnit, 
        HostEquityOrderExecNotifyParams l_equityOrderExecNotifyParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "callExpirationNotify(EqTypeOrderUnit,HostEquityOrderExecNotifyParams)";
        log.entering(STR_METHOD_NAME); 

        log.debug("�V�[�P���X�}�u�i�M�p����o���ʒm�ꌏ�T�[�r�X�jcall�����ʒm�����v�Q��");

        // 1.2 �i����t���[�F�����P�ʁD�����敪���h�������h�̏ꍇ�A����������return����j
        if (!(l_orderUnit.getExpirationStatus().equals(OrderExpirationStatusEnum.EXPIRING)))
        {
            return;
        }

        //5 getDefaultProcessor( )(Processors::getDefaultProcessor)
        QueryProcessor l_queryProcessor = null;
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();
        }
        catch (DataFindException l_dfe)
        {
            log.error(l_dfe.getMessage(),l_dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_dfe.getMessage(), l_dfe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(),l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_dne.getMessage(), l_dne);
        }
        
        //6 �M�p���call�����ʒmTransactionCallback( )
        //  (�M�p���call�����ʒmTransactionCallback::�M�p���call�����ʒmTransactionCallback)
        WEB3EquityCallCloseNotifyTransactionCallback l_transactionCallBack =
            new WEB3EquityCallCloseNotifyTransactionCallback(l_orderUnit, l_equityOrderExecNotifyParams);
        
        //7 doTransaction(int, TransactionCallback)(QueryProcessor::doTransaction)
        try
        {
            l_queryProcessor.doTransaction(
                TransactionalInterceptor.TX_JOIN_EXISTING,
                l_transactionCallBack);
        }
        catch (DataCallbackException l_dce)
        {
            log.error(l_dce.getMessage(), l_dce);
            WEB3BaseException l_bex = (WEB3BaseException)l_dce.getDetails();
            throw l_bex;
        }
        catch (DataException l_dex)
        {
            log.error(l_dex.getMessage(),l_dex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80077,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dex.getMessage(),
                l_dex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    
    /**
     * (call����ʒm����)<BR>
     * ��������������ǂ������`�F�b�N���A <BR>
     * ����ʒm�������K�v�ȏꍇ�́u�M�p�����������ʒm����ꌏ�T�[�r�X�v���R�[������B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�i�����o���ʒm���ʁj�o���ʒm�ꌏ�T�[�r�X�jcall����ʒm�����v�Q�ƁB <BR>
     * @@param l_ordeUnit - (�����P��)<BR>
     * �o���ʒm�L���[.���ʃR�[�h�ɊY������A�����P�ʃI�u�W�F�N�g�B<BR>
     * @@param l_equityOrderExecNotifyParams - <BR>
     * �����o���ʒm�L���[Params�I�u�W�F�N�g�B<BR>
     */
    protected void callCancelNotify(EqTypeOrderUnit l_orderUnit, 
        HostEquityOrderExecNotifyParams l_equityOrderExecNotifyParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "callCancelNotify(EqTypeOrderUnit,HostEquityOrderExecNotifyParams)";
        log.entering(STR_METHOD_NAME); 

        log.debug("�u�i�M�p����o���ʒm�ꌏ�T�[�r�X�jcall����ʒm�����v�Q��");
        //�M�p����o���ʒm / �i�M�p����o���ʒm�ꌏ�T�[�r�X�jcall����ʒm����        

        // 1.2. �����P��.������ԁ��i"��t�ρi��������j" or "�������i��������j"�j == ������łȂ��j�̏ꍇ�A��������return����B
        if (!(OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderUnit.getOrderStatus()) ||
            OrderStatusEnum.CANCELLING.equals(l_orderUnit.getOrderStatus())))
        {
            log.debug("�����P��.������ԁ��i\"��t�ρi��������j\" or \"�������i��������j\"�j�Ȃ̂ŁA����������s��Ȃ��B");
            return;
        }
        
        // ������̏ꍇ�݈̂ȉ��̏������s���B        
        //1.3 getDefaultProcessor( )(Processors::getDefaultProcessor)
        QueryProcessor l_QueryProcessor = null;
        try
        {
            l_QueryProcessor = Processors.getDefaultProcessor();
        }
        catch (DataFindException l_dfe)
        {
            log.error(l_dfe.getMessage(),l_dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_dfe.getMessage(), l_dfe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(),l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_dne.getMessage(), l_dne);
        }
        
        //6 �����o���ʒmcall����ʒmTransactionCallback( )
        //  (�����o���ʒmcall����ʒmTransactionCallback::�����o���ʒmcall����ʒmTransactionCallback)
        WEB3EquityCallCancelNotifyTransactionCallback l_transactionCallBack = 
            new WEB3EquityCallCancelNotifyTransactionCallback(l_orderUnit, l_equityOrderExecNotifyParams);
        
        //7 doTransaction(int, TransactionCallback)(QueryProcessor::doTransaction)
        try
        {
            log.debug("�����o���ʒmcall����ʒmTransactionCallback()���J�n");
            l_QueryProcessor.doTransaction(
                TransactionalInterceptor.TX_JOIN_EXISTING,
                l_transactionCallBack);
            log.debug("�����o���ʒmcall����ʒmTransactionCallback()���I��");
        }
        catch (DataCallbackException l_dce)
        {
            log.error(l_dce.getMessage(), l_dce);
            WEB3BaseException l_bex = (WEB3BaseException)l_dce.getDetails();
            throw l_bex;
        }
        catch (DataException l_dex)
        {
            log.error(l_dex.getMessage(),l_dex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80078,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dex.getMessage(),
                l_dex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    
    /**
     * (validate�S�����)<BR>
     * ���łɁA�S�����ς݂��ǂ����`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�����̒����P��.isFullyExecuted( )���R�[������B<BR>
     * <BR>
     * �Q�j�@@�P�j��true���ԋp���ꂽ�ꍇ�́A<BR>
     *     �u���Y�����͊��ɑS�����ρv�̗�O�i�Ɩ��G���[�j��throw����B�ȊO�A<BR>
     *      ���̂܂�return����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00634<BR>
     * @@param l_ordeUnit - (�����P��)<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40DFC5E600B9
     */
    protected void validateAllOrderExecution(EqTypeOrderUnit l_ordeUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAllOrderExecution(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME); 
        
        //�P�j�@@�����̒����P��.isFullyExecuted( )���R�[������B
        boolean l_blnFullyExecuted = l_ordeUnit.isFullyExecuted();
        
        //�Q�j�@@�P�j��true���ԋp���ꂽ�ꍇ�́A<BR>
        //     �u���Y�����͊��ɑS�����ρv�̗�O�i�Ɩ��G���[�j��throw����B�ȊO�A<BR>
        //      ���̂܂�return����B<BR>
        //      class: WEB3BusinessLayerException<BR>
        //      tag:   BUSINESS_ERROR_00634<BR>
        if (l_blnFullyExecuted == true)
        {
            log.error("�u���Y�����͊��ɑS�����ρv�̗�O�i�Ɩ��G���[�j��throw����");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00634,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate��d���)<BR>
     * �E���v��萔�ʂ����������𒴂��Ă��Ȃ����`�F�b�N����B<BR>
     * <BR>
     * �@@�����̒����P��.�s�ꂩ��m�F�ς݂̐��ʁ@@���@@���v��萔��(*1)�@@�̏ꍇ�A<BR>
     * �@@�u���v��萔�ʂ��A�������ʂ𒴉߁v�̗�O�i�Ɩ��G���[�j��throw����B<BR>
     * �@@�ȊO�A���̂܂�return����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00635<BR>
     * <BR>
     * (*1)<BR>
     * ���v��萔�ʁ@@���@@�����P��.��萔�ʁ@@�{�@@�����̖�芔��<BR>
     * <BR>
     * @@param l_ordeUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@param l_dblExecQuantity - (��芔��)<BR>
     * ��肵�������B<BR>
     * @@roseuid 40DFC5E600CA<BR>
     */
    protected void validateDoubleOrderExecution(EqTypeOrderUnit l_ordeUnit, 
        double l_dblExecQuantity) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateDoubleOrderExecution(EqTypeOrderUnit,double)";
        log.entering(STR_METHOD_NAME);
        
        //(*1) ���v��萔�ʁ@@���@@�����P��.��萔�ʁ@@�{�@@�����̖�芔��
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_ordeUnit.getDataSourceObject();
        double l_dblTotalQuantity = l_orderUnitRow.getExecutedQuantity() + l_dblExecQuantity;
        
        // �����̒����P��.�s�ꂩ��m�F�ς݂̐��ʁ@@���@@���v��萔��(*1)�@@�̏ꍇ�A<BR>
        // �u���v��萔�ʂ��A�������ʂ𒴉߁v�̗�O�i�Ɩ��G���[�j��throw����B<BR>
        // �ȊO�A���̂܂�return����B<BR>
        //  class: WEB3BusinessLayerException<BR>
        //  tag:   BUSINESS_ERROR_00635<BR> 
        if (l_ordeUnit.getConfirmedQuantity() < l_dblTotalQuantity)
        {
            log.error("�u���v��萔�ʂ��A�������ʂ𒴉߁v�̗�O�i�Ɩ��G���[�j��throw����");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00635,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
	/**
	 * (validate�������)<BR>
	 * �Ώے������A���^������ɂ��X�V���s���ėǂ���Ԃł��邩�`�F�b�N����B<BR>
	 * <BR>
	 * �i�`�F�b�N���e�j<BR>
	 * �P�D�����œn���ꂽ�����P��.�s�ꂩ��m�F�ς̐���==null�̏ꍇ�A<BR>
	 * �@@�@@�u�Y�������͎�t���ρ^�ύX�̎�t�ρ^�������̏�ԁv�̗�O��throw����B<BR>
	 * <BR>
	 * �Q�D�����œn���ꂽ�����P��.������Ԃ��ȉ��̂����ꂩ�ɊY������ꍇ�A<BR>
	 * �@@�@@�P�D�Ɠ�����O��throw����B<BR>
	 * �@@�@@�@@�@@MODIFY_ACCEPTED�F��t�ρi�ύX�����j<BR>
	 * �@@�@@�@@�@@MODIFYING�F�������i�ύX�����j<BR>
	 * <BR>
	 * �R�D�����œn���ꂽ�����P��.�l�i���� == �i"���ݒl�w�l����", "�D��l�w�l����"�j��<BR>
	 * �@@�@@�����ꂩ�̏ꍇ�̂݁A <BR>
	 * �@@�@@�l�i�t�����������Ă��Ȃ��ꍇ(*1)�́A�P�D�Ɠ�����O��throw����B<BR>
	 * <BR>
	 * �@@�@@�@@(*1)�l�i�t�����������Ă��Ȃ��ꍇ<BR>
	 * �@@�@@�@@�@@�@@�����P��.�s�ꂩ��m�F�ς݂̎w�l == 0�i���l�i���m��j�@@����<BR>
	 * �@@�@@�@@�@@�@@�����P��.������ԁ��iMODIFIED�F�����ρi�ύX�����j or<BR>
	 * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@NOT_MODIFIED�F�������s�i�ύX�����j or<BR>
	 * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@NOT_CANCELLED�F�������s�i��������j�j<BR>
	 * <BR>
	 * �@@�@@�@@�@@�@@�����ʋC�z���ɂ́A�l�i�m��̒�������ʒm��������w�l==0�~�ŗ��邽�߁A<BR>
	 * �@@�@@�@@�@@�@@��������ԂƂ�AND�����Œl�i�t���̊����L���𔻒肷��B<BR>
	 * <BR>
	 * �ȊO�A���̂܂�return����B<BR>
	 * <BR>
	 * @@param l_orderUnit - (�����P��)<BR>
	 * �����P�ʃI�u�W�F�N�g�B
	 * @@throws WEB3BaseException
	 */
	protected void validateOrderStatus(EqTypeOrderUnit l_orderUnit)
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = "validateOrderStatus(OrderStatusEnum)";
		log.entering(STR_METHOD_NAME);
        
		EqtypeOrderUnitRow l_orderUnitRow =
			(EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
		if (l_orderUnitRow.getConfirmedQuantityIsNull())
		{
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01975,
				this.getClass().getName() + STR_METHOD_NAME);
		}
        
		OrderStatusEnum l_orderStatus = l_orderUnit.getOrderStatus();
		if (OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus) ||
			OrderStatusEnum.MODIFYING.equals(l_orderStatus))
		{
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01975,
				this.getClass().getName() + STR_METHOD_NAME);
		}
        
		String l_strPriceConditionType = l_orderUnitRow.getPriceConditionType();
		if (WEB3PriceConditionDef.PRESENT_VALUE_LIMIT_PRICE_ORDER.equals(l_strPriceConditionType) ||
			WEB3PriceConditionDef.PRIORITY_LIMIT_PRICE_ORDER.equals(l_strPriceConditionType))
		{
			if (l_orderUnitRow.getConfirmedPrice() == 0D &&
				!OrderStatusEnum.MODIFIED.equals(l_orderStatus) &&
				!OrderStatusEnum.NOT_MODIFIED.equals(l_orderStatus) &&
				!OrderStatusEnum.NOT_CANCELLED.equals(l_orderStatus))
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01975,
					this.getClass().getName() + STR_METHOD_NAME);
			}
		}

		log.exiting(STR_METHOD_NAME);
	}
    
    /**
     * (exec�������n�������)<BR>
     * �������n�����̎�����s���K�v�����邩�ǂ����𔻒肵�A<BR>
     * �K�v���ɂ͌������n�����̎�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p����o���ʒm�ꌏ�T�[�r�X�jexec�������n��������v�Q�ƁB<BR>
     * <BR>
     * @@param l_ordeUnit - (�����P��)<BR>
     * �o���ʒm�L���[.���ʃR�[�h�ɊY������A�����P�ʃI�u�W�F�N�g�B<BR>
     * @@roseuid 40F2405003A8
     */
    protected void execSwapMarginOrderCancel(EqTypeOrderUnit l_ordeUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execSwapMarginOrderCancel(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME); 
        
        log.debug("�V�[�P���X�}�u�i�M�p����o���ʒm�ꌏ�T�[�r�X�jexec�������n��������v�Q��");
        //�M�p����o���ʒm / �i�M�p����o���ʒm�ꌏ�T�[�r�X�jexec�������n�������
        //2 getOrderCateg()
        OrderCategEnum l_orderCateg = l_ordeUnit.getOrderCateg();
        
        //3 �i����t���[�F�������n�����ȊO�i��OrderCategEnum.SWAP_MARGIN�j�̏ꍇ�͉�������return����B�j
        if (!OrderCategEnum.SWAP_MARGIN.equals(l_orderCateg))
        {
            log.debug("�������n�����ȊO�̏ꍇ�͉�������return����!");
            return;
        }
        
        //4 ������������m��C���^�Z�v�^( )(������������m��C���^�Z�v�^::������������m��C���^�Z�v�^)
        WEB3EquityCancelOrderConfirmInterceptor l_confirmInterceptor = new WEB3EquityCancelOrderConfirmInterceptor();
        
        //5 setThreadLocalPersistenceEventInterceptor(
        //  ������������m��C���^�Z�v�^ : EqTypeOrderManagerPersistenceEventInterceptor)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)l_finApp.getTradingModule(
            ProductTypeEnum.EQUITY).getOrderManager();
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_confirmInterceptor);
        
        //6 DefaultCancelOrderAcceptedMarketResponseMessage(long)
        //  (DefaultCancelOrderAcceptedMarketResponseMessage::DefaultCancelOrderAcceptedMarketResponseMessage)
        //�����͈ȉ��̒ʂ�ɐݒ肷��B 
        //  ����ID�F�@@�����̒����P��.����ID
        DefaultCancelOrderAcceptedMarketResponseMessage l_responseMessage = 
            new DefaultCancelOrderAcceptedMarketResponseMessage(l_ordeUnit.getOrderId());
        
        //7 process(CancelOrderAcceptedMarketResponseMessage)(EqTypeMarketResponseReceiverCallbackService::process)
        //�����͈ȉ��̒ʂ�ɐݒ肷��B 
        //  arg0�F�@@��������DefaultCancelOrderAcceptedMarketResponseMessage
        EqTypeMarketResponseReceiverCallbackService l_callbackService = 
            (EqTypeMarketResponseReceiverCallbackService)l_finApp.getTradingModule(
            ProductTypeEnum.EQUITY).getMarketAdapter().getMarketResponseReceiverCallbackService();
        log.debug("������������ exec�������n���������process() �J�n������������");
        ProcessingResult l_result = l_callbackService.process(l_responseMessage);
        if (l_result.isFailedResult())
        {
            WEB3BaseException l_baseException = new WEB3BaseException(
                l_result.getErrorInfo(), 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_result.getErrorInfo().getErrorMessage());
            log.error(STR_METHOD_NAME, l_baseException);
            throw l_baseException;
        } 
        log.debug("������������ exec�������n���������process() �I��������������");
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
