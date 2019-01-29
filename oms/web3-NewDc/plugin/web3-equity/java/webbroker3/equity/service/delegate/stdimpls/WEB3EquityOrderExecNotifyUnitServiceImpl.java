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
filename	WEB3EquityOrderExecNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������o���ʒm�ꌏ�T�[�r�XImpl(WEB3EquityOrderExecNotifyPartServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/10 �A�C��(���u) �V�K�쐬
                   2004/12/02 �������F(SRA) �c�Č��Ή��ɂ��C��
                   2004/12/29 �����a��(SRA) �R�����g�̏C��
                   2005/01/06 �����a��(SRA) JavaDoc�C��
                   2006/11/21 ������@@(���u)���f��No.1040
                   2006/11/28 �����F(���u) ���f�� 1071
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
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeAsset;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultFillOrderUnitSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultOrderFillMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultUndoOrderFillMarketResponseMessage;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3ExpirationReasonCodeDef;
import webbroker3.common.define.WEB3CloseNotifyTypeDef;
import webbroker3.common.define.WEB3HostStatusDef;

import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityOrderExecNotifyInterceptor;
import webbroker3.equity.data.HostEquityOrderExecNotifyParams;
import webbroker3.equity.data.HostEqtypeCloseOrderNotifyParams;
import webbroker3.equity.service.delegate.WEB3EquityExecutedMailSenderService;
import webbroker3.equity.service.delegate.WEB3EquityOrderExecNotifyUnitService;
import webbroker3.equity.service.delegate.WEB3EquityReceiveCloseOrderUnitService;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i���������o���ʒm�ꌏ�T�[�r�XImpl�j�B<BR>
 * <BR>
 * ���������o���ʒm���ꌏ����������T�[�r�X
 * @@version 1.0
 */
public class WEB3EquityOrderExecNotifyUnitServiceImpl
    implements WEB3EquityOrderExecNotifyUnitService
{
    /**
     * ���O���[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityOrderExecNotifyUnitServiceImpl.class);

    /**
     * �R���X�g���N�^�B<BR>
     */
    public WEB3EquityOrderExecNotifyUnitServiceImpl()
    {
    }

    /**
     * (notify���)<BR>
     * <BR>
     * [�����T�v]<BR>
     * �擾�����S�����܂��͈ꕔ���ł���o���ʒm�L���[�P���R�[�h�ɑ΂����菈�����s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�o���ʒm�ꌏ�T�[�r�X�jnotify���v�Q�ƁB<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �o���ʒm�L���[.���ʃR�[�h�ɊY������A�����P�ʃI�u�W�F�N�g�B<BR>
     * @@param l_equityExecNotifyQueueParams - 
     *  �����o���ʒm�L���[Params�I�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     */
    public void notifyExecute(
        EqTypeOrderUnit l_orderUnit,
        HostEquityOrderExecNotifyParams l_equityExecNotifyQueueParams)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "notifyExecute(EqTypeOrderUnit, HostEquityOrderExecNotifyParams)";
        log.entering(STR_METHOD_NAME);
        
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
        
        // 1.2. validate�������()
        this.validateOrderStatus(l_orderUnit);

        // 1.3. validate�S�����()
        this.validateAllOrderExecution(l_orderUnit);
    
        // 1.4. validate��d���()
        this.validateDoubleOrderExecution(
            l_orderUnit,
            l_equityExecNotifyQueueParams.getExecQuantity());

        // �����o���ʒm�C���^�Z�v�^�̐���
        WEB3EquityOrderExecNotifyInterceptor l_updateInterceptor =
            new WEB3EquityOrderExecNotifyInterceptor(
                l_equityExecNotifyQueueParams.getExecTimestamp(),
                l_equityExecNotifyQueueParams.getExecPrice(),
                l_equityExecNotifyQueueParams.getExecQuantity());

        // 1.6. setThreadLocalPersistenceEventInterceptor()
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
        
        // 1.7. DefaultFillOrderUnitSpec()
        DefaultFillOrderUnitSpec l_unitSpec = new DefaultFillOrderUnitSpec(
            l_orderUnit.getOrderUnitId(),
            l_equityExecNotifyQueueParams.getExecQuantity(),
            l_equityExecNotifyQueueParams.getExecPrice());

        // 1.8. DefaultOrderFillMarketResponseMessage()
        DefaultOrderFillMarketResponseMessage l_responseMessage = 
            new DefaultOrderFillMarketResponseMessage(
                l_orderUnit.getOrderId(),
                l_unitSpec);

        // 1.9. process(MarketResponseMessage)(EqTypeMarketResponseReceiverCallbackService::process)
        MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
        EqTypeMarketResponseReceiverCallbackService l_callbackService = 
            (EqTypeMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService();
        log.debug("������������ notify����process() �J�n ������������");
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
        log.debug("������������ notify����process() �I�� ������������");

        // 1.11. getOrderUnit()
        try
        {
            l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(
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

        // 1.12. isFullyExecuted()
        if (l_orderUnit.isFullyExecuted())
        {
            // 1.13. sendMailProcess()
            WEB3EquityExecutedMailSenderService l_mailSenderService = 
                (WEB3EquityExecutedMailSenderService)Services.getService(
                WEB3EquityExecutedMailSenderService.class);        
            l_mailSenderService.sendMailProcess(l_orderUnit, null);

            // notify���[���G���W���T�[�o(EqTypeOrderUnit, OrderManagerPersistenceContext)
            //�Y���̒������S�����̏ꍇ�A���[���G���W���T�[�o�ɒʒm����B
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B
            //
            //�����P�ʁF�@@�����P��
            //�����F�@@FILL_ORDER
            try
            {
                l_orderManager.notifyRLS(
                    l_orderUnit,
                    OrderManagerPersistenceContext.FILL_ORDER);
            }
            // (*)notify���[���G���W���T�[�o()�ɂċƖ��G���[���X���[���ꂽ�ꍇ
            //(*)catch���ď����𑱍s����B
            catch (WEB3BusinessLayerException l_ex)
            {
                log.debug("notify���[���G���W���T�[�o()�ɂċƖ��G���[���X���[���ꂽ�ꍇ, �����[���o�b�N���Ȃ��B");
            }
        }

        // 1.14. isPartiallyExecuted()
        if (l_orderUnit.isPartiallyExecuted())
        {
            // 1.15. �ꕔ��肩���Ώے����P��.�l�i������"���s�c�����"�̏ꍇ�̂݁A�����������������s�B
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            if (WEB3PriceConditionDef.PARTIALLY_CANCEL_ORDER.equals(l_orderUnitRow.getPriceConditionType()))
            {
                // 1.15.1. ���������ʒm�L���[Params�̃C���X�^���X�����A�y�уv���p�e�B�Z�b�g
                WEB3GentradeMainAccount l_account = null;
                try
                {
                    l_account =
                        (WEB3GentradeMainAccount)l_accountManager.getMainAccount(
                            l_orderUnitRow.getAccountId());
                }
                catch (NotFoundException l_nfe)
                {
                    log.error(l_nfe.getMessage(),l_nfe);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                        this.getClass().getName() + STR_METHOD_NAME, 
                        l_nfe.getMessage(),
                        l_nfe);
                }
                HostEqtypeCloseOrderNotifyParams l_hostCloseNotifyParams = new HostEqtypeCloseOrderNotifyParams();
                l_hostCloseNotifyParams.setRequestCode(WEB3HostRequestCodeDef.EQUITY_ORDER_CLOSE_NOTICE);
                l_hostCloseNotifyParams.setInstitutionCode(l_equityExecNotifyQueueParams.getInstitutionCode());
                l_hostCloseNotifyParams.setBranchCode(l_equityExecNotifyQueueParams.getBranchCode());
                l_hostCloseNotifyParams.setAccountCode(l_account.getAccountCode());
                l_hostCloseNotifyParams.setTraderCode(l_equityExecNotifyQueueParams.getTraderCode());
                l_hostCloseNotifyParams.setOrderRequestNumber(l_equityExecNotifyQueueParams.getOrderRequestNumber());
                l_hostCloseNotifyParams.setExecutedQuantity(l_orderUnitRow.getExecutedQuantity());
                l_hostCloseNotifyParams.setReasonCode(WEB3ExpirationReasonCodeDef.PARTIALLY_EXECUTE_CLOSE);
                l_hostCloseNotifyParams.setCloseNotifyType(WEB3CloseNotifyTypeDef.CLOSE);
                l_hostCloseNotifyParams.setErrorMessage(null);
                l_hostCloseNotifyParams.setStatus(WEB3HostStatusDef.NOT_STARTED_PROCESS);

                // 1.15.2. exec����()
                WEB3EquityReceiveCloseOrderUnitService l_unitService =
                    (WEB3EquityReceiveCloseOrderUnitService)Services.getService(WEB3EquityReceiveCloseOrderUnitService.class);
                l_unitService.execCloseOrder(l_hostCloseNotifyParams, l_orderUnit);
            }
        }

        // 1.16. call�����ʒm����()
        this.callExpirationNotify(l_orderUnit, l_equityExecNotifyQueueParams);

        // 1.17. call����ʒm����()
        this.callCancelNotify(l_orderUnit, l_equityExecNotifyQueueParams);

        // 1.18. �]�͍Čv�Z()
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            l_subAccount = (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                l_orderUnit.getAccountId(),
                l_orderUnit.getSubAccountId());
        }
        catch (NotFoundException l_nfe)
        {
            log.error(l_nfe.getMessage(),l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(), l_nfe);
        }
        WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
            (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
        l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (notify�����)<BR>
     * <BR>
     * [�����T�v]<BR>
     * �擾�����o���ʒm�L���[��������̏ꍇ�A������������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�o���ʒm�ꌏ�T�[�r�X�jnotify������v�Q�ƁB<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �o���ʒm�L���[.���ʃR�[�h�ɊY������A�����P�ʃI�u�W�F�N�g�B<BR>
     * @@param l_equityExecNotifyQueueParams - <BR>
     *   �����o���ʒm�L���[Params�I�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     */
    public void notifyExecuteCancel(
        EqTypeOrderUnit l_orderUnit,
        HostEquityOrderExecNotifyParams l_equityExecNotifyQueueParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyExecuteCancel(EqTypeOrderUnit, HostEquityOrderExecNotifyParams)";
        log.entering(STR_METHOD_NAME);
        
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
        
        // 1.1. validate�������()
        this.validateOrderStatus(l_orderUnit);
        
        // 1.2. get����Ώۖ��()
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
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

        // 1.4. �ꕔ���ɑ΂���������������Ώے����P��.�l�i����=="���s�c�����"
        // �@@�@@�@@�̏ꍇ�̂݁A����������������s�B
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        if (l_orderExecution.getExecutionQuantity() < l_orderUnit.getQuantity())
        {
            if (WEB3PriceConditionDef.PARTIALLY_CANCEL_ORDER.equals(l_orderUnitRow.getPriceConditionType()))
            {
                // 1.4.1. ���������ʒm�L���[Params�̃C���X�^���X�����A�y�уv���p�e�B�Z�b�g
                WEB3GentradeMainAccount l_account = null;
                try
                {
                    l_account =
                        (WEB3GentradeMainAccount)l_accountManager.getMainAccount(
                            l_orderUnitRow.getAccountId());
                }
                catch (NotFoundException l_nfe)
                {
                    log.error(l_nfe.getMessage(),l_nfe);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                        this.getClass().getName() + STR_METHOD_NAME, 
                        l_nfe.getMessage(),
                        l_nfe);
                }
                HostEqtypeCloseOrderNotifyParams l_hostCloseNotifyParams = new HostEqtypeCloseOrderNotifyParams();
                l_hostCloseNotifyParams.setRequestCode(WEB3HostRequestCodeDef.EQUITY_ORDER_CLOSE_NOTICE);
                l_hostCloseNotifyParams.setInstitutionCode(l_equityExecNotifyQueueParams.getInstitutionCode());
                l_hostCloseNotifyParams.setBranchCode(l_equityExecNotifyQueueParams.getBranchCode());
                l_hostCloseNotifyParams.setAccountCode(l_account.getAccountCode());
                l_hostCloseNotifyParams.setTraderCode(l_equityExecNotifyQueueParams.getTraderCode());
                l_hostCloseNotifyParams.setOrderRequestNumber(l_equityExecNotifyQueueParams.getOrderRequestNumber());
                l_hostCloseNotifyParams.setExecutedQuantity(l_orderUnitRow.getExecutedQuantity());
                l_hostCloseNotifyParams.setReasonCode(WEB3ExpirationReasonCodeDef.PARTIALLY_EXECUTE_CLOSE);
                l_hostCloseNotifyParams.setCloseNotifyType(WEB3CloseNotifyTypeDef.CLOSE_CANCEL);
                l_hostCloseNotifyParams.setErrorMessage(null);
                l_hostCloseNotifyParams.setStatus(WEB3HostStatusDef.NOT_STARTED_PROCESS);

                // 1.4.1.1. exec����()
                WEB3EquityReceiveCloseOrderUnitService l_unitService =
                    (WEB3EquityReceiveCloseOrderUnitService)Services.getService(WEB3EquityReceiveCloseOrderUnitService.class);
                l_unitService.execCloseOrder(l_hostCloseNotifyParams, l_orderUnit);
            }
        }
        
        // �����o���ʒm�C���^�Z�v�^�̐���
        WEB3EquityOrderExecNotifyInterceptor l_updateInterceptor =
            new WEB3EquityOrderExecNotifyInterceptor(
                l_equityExecNotifyQueueParams.getExecTimestamp(),
                l_equityExecNotifyQueueParams.getExecPrice(),
                l_equityExecNotifyQueueParams.getExecQuantity());
                
        // 1.5. setThreadLocalPersistenceEventInterceptor()
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
        
        // 1.6. DefaultUndoOrderFillMarketResponseMessage(long, long)
        DefaultUndoOrderFillMarketResponseMessage l_responseMessage = 
            new DefaultUndoOrderFillMarketResponseMessage(
                l_orderUnit.getOrderId(),
                l_orderExecution.getOrderExecutionId());
            
        // 1.7. process()
        EqTypeMarketResponseReceiverCallbackService l_callbackService = 
            (EqTypeMarketResponseReceiverCallbackService)l_tradingModule.getMarketAdapter().getMarketResponseReceiverCallbackService();
        log.debug("������������ notify�������process() �J�n ������������");
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
        log.debug("������������ notify�������process() �I�� ������������");

        // 1.8. get�ۗL���Y
        WEB3EquityPositionManager l_positionManager = 
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        EqTypeAsset l_asset = l_positionManager.getAsset(
            l_orderUnitRow.getAccountId(),
            l_orderUnitRow.getSubAccountId(),
            l_orderUnitRow.getProductId(),
            l_orderUnitRow.getTaxType());

        // 1.9. �����菈��()
        l_positionManager.shareContractExecution(l_orderUnit.getOrderUnitId(), l_asset.getAssetId(), true);

        // 1.11. getOrderUnit()
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
                l_nfe.getMessage(),
                l_nfe);
        }
        
        // 1.12. undoSendMail(EqTypeOrderUnit)(��胁�[�����M�T�[�r�X::undoSendMail)
        WEB3EquityExecutedMailSenderService l_mailSenderService =
            (WEB3EquityExecutedMailSenderService)Services.getService(
            WEB3EquityExecutedMailSenderService.class);
        l_mailSenderService.undoSendMail(l_orderUnit);

        // 1.13. �]�͍Čv�Z()
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            l_subAccount = (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                l_orderUnit.getAccountId(),
                l_orderUnit.getSubAccountId());
        }
        catch (NotFoundException l_nfe)
        {
            log.error(l_nfe.getMessage(),l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(), l_nfe);
        }
        WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
            (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
        l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (call�����ʒm����)<BR>
     * <BR>
     * �ꕔ��肩�ǂ������`�F�b�N���A<BR>
     * �y���������ʒm�L���[�e�[�u���z�ɊY������u�������v���R�[�h�����݂���ꍇ��<BR>
     * ���������ꌏ�T�[�r�X�v���R�[������B<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �o���ʒm�L���[.���ʃR�[�h�ɊY������A�����P�ʃI�u�W�F�N�g�B<BR>
     * @@param l_equityOrderExecNotifyParams - <BR>
     *   �����o���ʒm�L���[Params�I�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     */
    public void callExpirationNotify(
        EqTypeOrderUnit l_orderUnit,
        HostEquityOrderExecNotifyParams l_equityOrderExecNotifyParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "callExpirationNotify(EqTypeOrderUnit, HostEquityOrderExecNotifyParams)";
        log.entering(STR_METHOD_NAME);

        // 1.2. �����P��.�����敪��"������"�̏ꍇ�́A��������return����B
        if (!OrderExpirationStatusEnum.EXPIRING.equals(l_orderUnit.getExpirationStatus()))
        {
            log.debug("�����P��.�����敪��\"������\"�Ȃ̂ŁA�����������s��Ȃ��B");
            return;
        }

        // 1.3. getDefaultProcessor()
        QueryProcessor l_queryProcessor = null;
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();
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
        
        // 1.4. �����o���ʒmcall�����ʒmTransactionCallback()
        WEB3EquityCallCloseNotifyTransactionCallback l_transactionCallBack =
            new WEB3EquityCallCloseNotifyTransactionCallback(l_orderUnit, l_equityOrderExecNotifyParams);
        
        // 1.5. doTransaction()
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
     * <BR>
     * �ꕔ��肩�ǂ������`�F�b�N���A<BR>
     * �u�M�p�����������ʒm����ꌏ�T�[�r�X�v���R�[������B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p����o���ʒm�ꌏ�T�[�r�X�jcall����ʒm�����v�Q�ƁB<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �o���ʒm�L���[.���ʃR�[�h�ɊY������A�����P�ʃI�u�W�F�N�g�B<BR>
     * @@param l_equityOrderExecNotifyParams - <BR>
     *   �����o���ʒm�L���[Params�I�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     */
    public void callCancelNotify(
        EqTypeOrderUnit l_orderUnit,
        HostEquityOrderExecNotifyParams l_equityOrderExecNotifyParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "callCancelNotify(EqTypeOrderUnit, HostEquityOrderExecNotifyParams)";
        log.entering(STR_METHOD_NAME);

        // 1.2. �����P��.������ԁ��i"��t�ρi��������j" or "�������i��������j"�j == ������łȂ��j�̏ꍇ�A��������return����B
        if (!(OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderUnit.getOrderStatus()) ||
            OrderStatusEnum.CANCELLING.equals(l_orderUnit.getOrderStatus())))
        {
            log.debug("�����P��.������ԁ��i\"��t�ρi��������j\" or \"�������i��������j\"�j�Ȃ̂ŁA����������s��Ȃ��B");
            return;
        }

        // 1.3. getDefaultProcessor()
        QueryProcessor l_queryProcessor = null;
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();
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
        
        // 1.4. �����o���ʒmcall����ʒmTransactionCallback()
        WEB3EquityCallCancelNotifyTransactionCallback l_transactionCallBack =
            new WEB3EquityCallCancelNotifyTransactionCallback(l_orderUnit, l_equityOrderExecNotifyParams);
        
        // 1.5. doTransaction()
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
                WEB3ErrorCatalog.SYSTEM_ERROR_80078,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dex.getMessage(),
                l_dex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�S�����)<BR>
     * <BR>
     * [�����T�v]<BR>
     * ���łɁA�S�����ς݂��ǂ����`�F�b�N����B<BR>
     * <BR>
     * �i�`�F�b�N���e�j<BR>
     * �P�j�@@�����̒����P��.isFullyExecuted( )���R�[������B<BR>
     * <BR>
     * �Q�j�@@�P�j��true���ԋp���ꂽ�ꍇ�́A�u���Y�����͊��ɑS�����ρv�̗�O�i�Ɩ��G���[�j��<BR>
     * �@@�@@�@@throw����B<BR>
     * �@@�@@�@@�ȊO�A���̂܂�return����B<BR>
     * <BR>
     * ��true���ԋp���ꂽ�ꍇ�A�o���ʒm�L���[.�����敪��9�F�f�[�^�G���[�ɍX�V�A<BR>
     * ���G���[���O���o�͂��ꌏ�������I������B<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * @@throws WEB3BaseException
     * @@throws WEB3BaseException
     */
    protected void validateAllOrderExecution(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAllOrderExecution(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit.isFullyExecuted() == true)
        {
            log.error("���Y�����͊��ɑS�����ςł���B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00634,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �ivalidate��d���j<BR>
     * <BR>
     * �E���v��萔�ʂ����������𒴂��Ă��Ȃ����`�F�b�N����B<BR>
     * <BR>
     * �@@�����̒����P��.�s�ꂩ��m�F�ς݂̐��ʁ@@���@@���v��萔��(*1)�@@�̏ꍇ�A<BR>
     * �@@�u���v��萔�ʂ��A�������ʂ𒴉߁v�̗�O�i�Ɩ��G���[�j��throw����B<BR>
     * �@@�ȊO�A���̂܂�return����B<BR>
     * <BR>
     * (*1)<BR>
     * ���v��萔�ʁ@@���@@�����P��.��萔�ʁ@@�{�@@�����̖�芔��<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * @@param l_dblExecQuantity - (��芔��)<BR>
     * @@throws WEB3BaseException
     */
    protected void validateDoubleOrderExecution(
        EqTypeOrderUnit l_orderUnit,
        double l_dblExecQuantity)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateDoubleOrderExecution(EqTypeOrderUnit, double)";
        log.entering(STR_METHOD_NAME);
        
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        double l_dblTotalQuantity = l_orderUnitRow.getExecutedQuantity() + l_dblExecQuantity;

        if (l_orderUnit.getConfirmedQuantity() < l_dblTotalQuantity)
        {
            log.error("���v��萔�ʂ��A�������ʂ𒴉߂��Ă���B");
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
}
@
