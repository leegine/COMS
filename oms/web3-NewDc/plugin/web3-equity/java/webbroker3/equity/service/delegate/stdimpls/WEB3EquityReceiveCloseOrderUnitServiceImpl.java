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
filename	WEB3EquityReceiveCloseOrderUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������ʒm�ꌏ�T�[�r�XImpl(WEB3EquityReceiveCloseOrderUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/24 ���j (���u) �V�K�쐬
                   2006/11/20 �����F(���u) ���f�� 1033 1041
                   2006/11/29 �����F(���u) ���f�� 1069
Revision History : 2008/03/20 ����(���u) ���f�� 1306�A1307
*/
package webbroker3.equity.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CloseNotifyTypeDef;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityEstimatedContractPrice;
import webbroker3.equity.WEB3EquityEstimatedDeliveryPrice;
import webbroker3.equity.WEB3EquityEstimatedPrice;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3EquityReceiveCloseOrderInterceptor;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.data.HostEqtypeCloseOrderNotifyParams;
import webbroker3.equity.define.WEB3EquityWlimitEnableStatusDivDef;
import webbroker3.equity.service.delegate.WEB3EquityExecutedMailSenderService;
import webbroker3.equity.service.delegate.WEB3EquityReceiveCloseOrderUnitService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultOrderInvalidatedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultUndoOrderInvalidatedMarketResponseMessage;

/**
 * �i���������ʒm�ꌏ�T�[�r�XImpl�j�B<BR>
 * <BR>
 * �ꌏ���Ƃ̎����ʒm���������{����B
 * @@author 羐�
 * @@version 1.0
 */
public class WEB3EquityReceiveCloseOrderUnitServiceImpl
    implements WEB3EquityReceiveCloseOrderUnitService
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityReceiveCloseOrderUnitServiceImpl.class);
    
    /**
     * (exec����)<BR>
     * <BR>
     * ���������ꌏ���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�����ʒm�T�[�r�X)process�v��exec����( )�̕������Q��<BR>
     * <BR>
     * @@param l_hostEqtypeCloseOrderNotifyParams - (���������ʒm�L���[Params)<BR>
     * �y���������ʒm�L���[�e�[�u���z�̂P���R�[�h�B<BR>
     * @@param l_orderUnit - (�����P�ʃI�u�W�F�N�g)<BR>
     * @@return WEB3EquityCloseOrderResponse
     * @@throws WEB3BaseException
     * @@roseuid 4137CFE8005B
     */
    public void execCloseOrder(
        HostEqtypeCloseOrderNotifyParams l_hostEqtypeCloseOrderNotifyParams,
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execCloseOrder(HostEqtypeCloseOrderNotifyParams, EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderMgr = 
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        try
        {
            l_orderUnit =
                (EqTypeOrderUnit)l_orderMgr.getOrderUnit(l_orderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_ne)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ne.getMessage(),
                l_ne);
        }
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        //1. reset�s��R�[�h()
        try
        {
            Market l_market = l_finApp.getFinObjectManager().getMarket(l_orderUnitRow.getMarketId());
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_market.getMarketCode());
        }
        catch (NotFoundException nfe)
        {
            log.error(STR_METHOD_NAME,nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                nfe.getMessage(),
                nfe);
        }

        //2. �o���ʒm�҂�(*1)�̏ꍇ�A�����P��.�����敪��"������"�ɍX�V����iupdate���𔭍s�j
        // <�����ʒm�L���[.�������ʂɒl���Z�b�g����Ă���ꍇ>
        // �����ʒm�L���[.�������� < (�����P��.�s�ꂩ��m�F�ς݂̐���-�����P��.��萔��)
        // <�����ʒm�L���[.�������ʂɒl���Z�b�g����Ă��Ȃ��ꍇ>
        // �����P��.��萔�� < �����ʒm�L���[.��萔��
        if ((!l_hostEqtypeCloseOrderNotifyParams.getCloseQuantityIsNull()
                && l_hostEqtypeCloseOrderNotifyParams.getCloseQuantity() < (l_orderUnitRow.getConfirmedQuantity() - l_orderUnitRow.getExecutedQuantity()))
            || (l_hostEqtypeCloseOrderNotifyParams.getCloseQuantityIsNull()
                && (l_orderUnitRow.getExecutedQuantity() < l_hostEqtypeCloseOrderNotifyParams.getExecutedQuantity())))
        {
            EqtypeOrderUnitParams l_orderUnitParams = new EqtypeOrderUnitParams();
            GtlUtils.copyRow2Params(l_orderUnitRow, l_orderUnitParams);
            l_orderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRING);
            try
            {
                QueryProcessor l_qp = Processors.getDefaultProcessor();
                l_qp.doUpdateQuery(l_orderUnitParams);
            }
            catch (DataException l_dex)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dex.getMessage(),
                    l_dex);
            }
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        // �T�Z��n����v�Z�y�сA�C���^�Z�v�^�̃Z�b�g���s��
        this.calcEstimatedPrice(
            l_hostEqtypeCloseOrderNotifyParams,
            l_orderUnit);
        
        String l_strCloseNotifyType = l_hostEqtypeCloseOrderNotifyParams.getCloseNotifyType();
        EqTypeMarketResponseReceiverCallbackService l_callbackService =
            (EqTypeMarketResponseReceiverCallbackService)
                l_tradingModule.getMarketAdapter().getMarketResponseReceiverCallbackService();
            
        //6. ���������ʒm�L���[�e�[�u��.�����ʒm�敪���h�����h����
        // �@@�o���ʒm�҂��Ȃ��̏ꍇ
        // <�����ʒm�L���[.�������ʂɒl���Z�b�g����Ă���ꍇ>
        // �����ʒm�L���[.�������� �� (�����P��.�s�ꂩ��m�F�ς݂̐���-�����P��.��萔��)
        // <�����ʒm�L���[.�������ʂɒl���Z�b�g����Ă��Ȃ��ꍇ>
        // �����P��.��萔�� �� �����ʒm�L���[.��萔��
        if (WEB3CloseNotifyTypeDef.CLOSE.equals(l_strCloseNotifyType))
        {
            if ((!l_hostEqtypeCloseOrderNotifyParams.getCloseQuantityIsNull() 
                    && (l_hostEqtypeCloseOrderNotifyParams.getCloseQuantity() >= l_orderUnitRow.getConfirmedQuantity() - l_orderUnitRow.getExecutedQuantity()))
                || (l_hostEqtypeCloseOrderNotifyParams.getCloseQuantityIsNull()
                    && (l_orderUnitRow.getExecutedQuantity() >= l_hostEqtypeCloseOrderNotifyParams.getExecutedQuantity())))
            {
                //6.1. DefaultOrderInvalidatedMarketResponseMessage()
                DefaultOrderInvalidatedMarketResponseMessage l_eqOrderInvalidatedMarketResponseMessage =
                    new DefaultOrderInvalidatedMarketResponseMessage(l_orderUnit.getOrderId());
                //6.2. process()
                ProcessingResult l_result =
                    l_callbackService.process(
                        l_eqOrderInvalidatedMarketResponseMessage);
                if (l_result.isFailedResult())
                {
                    throw new WEB3BaseException(
                        l_result.getErrorInfo(),
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_result.getErrorInfo().getErrorMessage());
                }
                try
                {
                    l_orderUnit = (EqTypeOrderUnit)l_orderMgr.getOrderUnit(l_orderUnit.getOrderUnitId());
                }
                catch (NotFoundException l_nfe)
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_nfe.getMessage(),
                        l_nfe);
                }
                //6.3. sendMailProcess()
                WEB3EquityExecutedMailSenderService l_eqExecutedMailSenderService = 
                    (WEB3EquityExecutedMailSenderService)Services.getService(WEB3EquityExecutedMailSenderService.class);
                l_eqExecutedMailSenderService.sendMailProcess(
                    l_orderUnit,
                    l_hostEqtypeCloseOrderNotifyParams.getReasonCode());
                if (l_orderUnit.isUnexecuted() == false)
                {
                    l_eqExecutedMailSenderService.sendMailProcess(l_orderUnit, null);
                }
                
                if (l_orderMgr.isReserveOrderConfirmRequire(l_orderUnit))
                {
                    WEB3ToSuccReservationEqTypeOrderUpdateService l_updateService =
                        (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                            WEB3ToSuccReservationEqTypeOrderUpdateService.class);
                    l_updateService.invalidateAllOrderUnit(l_orderUnit.getOrderId());
                }
                
                try
                {
                    //notify���[���G���W���T�[�o
                    l_orderMgr.notifyRLS(
                        l_orderUnit,
                        OrderManagerPersistenceContext.ORDER_INVALIDATED_BY_MKT);
                }
                catch (WEB3BusinessLayerException l_ex)
                {
                    log.debug("notify���[���G���W���T�[�o()�ɂċƖ��G���[���X���[���ꂽ�ꍇ�A�����[���o�b�N���Ȃ��B");
                }
            }
        }
        //7. ���������ʒm�L���[�e�[�u��.�����ʒm�敪���h��������h�̏ꍇ
        else
        {
            //7.1. DefaultUndoOrderInvalidatedMarketResponseMessage()
            DefaultUndoOrderInvalidatedMarketResponseMessage l_eqUndoOrderInvalidatedMarketResponseMessage =
                new DefaultUndoOrderInvalidatedMarketResponseMessage(l_orderUnit.getOrderId());
            //7.2. process()
            ProcessingResult l_result =
                l_callbackService.process(l_eqUndoOrderInvalidatedMarketResponseMessage);
            if (l_result.isFailedResult())
            {
                throw new WEB3BaseException(
                    l_result.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_result.getErrorInfo().getErrorMessage());
            }
            try
            {
                l_orderUnit = (EqTypeOrderUnit)l_orderMgr.getOrderUnit(l_orderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            //7.3. undoSendMail()
            WEB3EquityExecutedMailSenderService l_eqExecutedMailSenderService = 
                (WEB3EquityExecutedMailSenderService)Services.getService(WEB3EquityExecutedMailSenderService.class);
            l_eqExecutedMailSenderService.undoSendMail(l_orderUnit);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
	/**
	 * (notify����)<BR>
	 * <BR>
	 * ���������ꌏ���������{����B<BR>
	 * <BR>
	 * this.exec����()���R�[������B<BR>
	 * <BR>
	 * @@param l_params - (���������ʒm�L���[Params)<BR>
	 * �y���������ʒm�L���[�e�[�u���z�̂P���R�[�h�B<BR>
	 * @@param l_orderUnit - (�����P�ʃI�u�W�F�N�g)<BR>
	 * @@throws WEB3BaseException
	 */        
	public void notifyCloseOrder(
		HostEqtypeCloseOrderNotifyParams l_params,
		EqTypeOrderUnit l_orderUnit)
		throws WEB3BaseException 
	{
		final String STR_METHOD_NAME = "notifyCloseOrder(HostEqtypeCloseOrderNotifyParams, EqTypeOrderUnit)";
		log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderMgr = 
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        try
        {
            //�����P�ʃI�u�W�F�N�g���Ď擾����B
            l_orderUnit =
                (EqTypeOrderUnit)l_orderMgr.getOrderUnit(l_orderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_ne)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ne.getMessage(),
                l_ne);
        }

        //�����������^����������s���Ă悢��Ԃ��ǂ������`�F�b�N����B
        this.validateOrderStatus(l_orderUnit);
        //���������^����������s���B
		this.execCloseOrder(l_params, l_orderUnit);
		
		log.exiting(STR_METHOD_NAME);
	}

    /**
     * (��n����v�Z)<BR>
     * ��n������v�Z����B<BR>
     * �V�[�P���X�}�u�i�����ʒm�ꌏ�T�[�r�X�j�T�Z��n����v�Z�y�уC���^�Z�v�^�Z�b�g�v<BR>
     * @@param l_hostEqtypeCloseOrderNotifyParams - (���������ʒm�L���[Params)<BR>
     * @@param l_orderUnit - (�����P�ʃI�u�W�F�N�g)<BR>
     * @@throws WEB3BaseException
     */
    private void calcEstimatedPrice(
        HostEqtypeCloseOrderNotifyParams l_hostEqtypeCloseOrderNotifyParams,
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcEstimatedPrice(HostEqtypeCloseOrderNotifyParams, EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        double l_dblCalcPrice = 0.0D;
        double l_dblEstimatedPrice = 0.0D;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager = 
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        //1. �����i���������ʒm�L���[Params.�����ʒm�敪=="����"�̏ꍇ�j
        if (WEB3CloseNotifyTypeDef.CLOSE.equals(l_hostEqtypeCloseOrderNotifyParams.getCloseNotifyType()))
        {
            if (!l_orderUnit.isUnexecuted())
            {   
                l_dblEstimatedPrice = l_orderManager.getEstimateDeliveryAmountForClose(l_orderUnit);
            }
            else
            {
                l_dblEstimatedPrice = l_orderUnitRow.getEstimatedPrice();
            }
        }
        //2. ��������i���������ʒm�L���[Params.�����ʒm�敪=="�������"�̏ꍇ�j
        else
        {
            WEB3GentradeAccountManager l_accManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            // get�⏕����(����ID : long, �⏕����ID : long)
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

            WEB3EquityEstimatedPrice l_equityEstimatedPrice = null;
            //����t���[�F�@@�����f�[�^�A�_�v�^.get�v�w�l�p�L����ԋ敪��"���~�b�g�����L��" �̏ꍇ
            if (WEB3EquityWlimitEnableStatusDivDef.LIMIT_ENABLE.equals(
                WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnit)))
            {
                //get�X�g�b�v�����������T�Z����v�Z����(EqtypeOrderUnit, SubAccount)
                l_equityEstimatedPrice =
                    l_orderManager.getStopOrderExpireEstimatedPrice(l_orderUnit, l_subAccount);
            }

            if (l_equityEstimatedPrice == null)
            {
                WEB3EquityBizLogicProvider l_bizLogicProvider =
                    (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
                WEB3GentradeCommission l_commission =
                    l_bizLogicProvider.createCommission(l_orderUnitRow.getOrderId());
                WEB3EquityProductManager l_productManager =
                    (WEB3EquityProductManager)l_tradingModule.getProductManager();
                WEB3EquityTradedProduct l_tradedProduct = null;
                try
                {
                    l_tradedProduct =
                        (WEB3EquityTradedProduct)l_productManager.getTradedProduct(
                            l_orderUnitRow.getProductId(),
                            l_orderUnitRow.getMarketId());
                }
                catch (Exception l_exp)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00638,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_exp.getMessage(),
                        l_exp);
                }

                OrderCategEnum l_orderCateg = l_orderUnitRow.getOrderCateg();
                boolean l_isBuyOrder = SideEnum.BUY.equals(l_orderUnit.getSide());
                //��������Ώۂ̒����P��.�����J�e�S��=="��������"�̏ꍇ
                if (OrderCategEnum.ASSET.equals(l_orderCateg))
                {
                    WEB3EquityEstimatedDeliveryPrice l_deliveryPrice =
                        l_orderManager.calcEstimateDeliveryAmount(
                            l_commission,
                            l_orderUnitRow.getLimitPrice(),
                            l_orderUnitRow.getWLimitPrice(),
                            l_orderUnitRow.getStopOrderPrice(),
                            l_orderUnitRow.getExecutionConditionType(),
                            l_orderUnitRow.getWLimitExecCondType(),
                            l_orderUnitRow.getPriceConditionType(),
                            l_orderUnitRow.getOrderConditionType(),
                            null,
                            l_orderManager.isStopOrderValid(l_orderUnit),
                            l_subAccount,
                            (WEB3EquityTradedProduct)l_orderUnit.getTradedProduct(),
                            l_orderUnitRow.getQuantity(),
                            !l_isBuyOrder,
                            l_orderUnitRow.getExecutedQuantity(),
                            l_orderUnitRow.getExecutedAmount(),
                            true);
                    l_dblEstimatedPrice = l_deliveryPrice.getEstimateDeliveryAmount();
                    l_dblCalcPrice = l_deliveryPrice.getCalcUnitPrice();
                }
                //��������Ώۂ̒����P��.�����J�e�S��=="�V�K������"�̏ꍇ
                else if (OrderCategEnum.OPEN_MARGIN.equals(l_orderCateg))
                {
                    boolean l_orderType = false;
                    if (OrderTypeEnum.MARGIN_SHORT.equals(l_orderUnit.getOrderType()))
                    {
                        l_orderType = true;
                    }
                    else if (OrderTypeEnum.MARGIN_LONG.equals(l_orderUnit.getOrderType()))
                    {
                        l_orderType = false;
                    }
                    WEB3EquityEstimatedContractPrice l_contractAmountAtOrder =
                        l_orderManager.calcContractAmountAtOrder(
                            l_commission,
                            l_orderUnitRow.getLimitPrice(),
                            l_orderUnitRow.getWLimitPrice(),
                            l_orderUnitRow.getStopOrderPrice(),
                            l_orderUnitRow.getExecutionConditionType(),
                            l_orderUnitRow.getWLimitExecCondType(),
                            l_orderUnitRow.getPriceConditionType(),
                            l_orderUnitRow.getOrderConditionType(),
                            null,
                            l_orderManager.isStopOrderValid(l_orderUnit),
                            l_orderType,
                            l_subAccount,
                            (EqTypeTradedProduct)l_orderUnit.getTradedProduct(),
                            l_orderUnitRow.getQuantity(),
                            l_orderUnitRow.getExecutedQuantity(),
                            l_orderUnitRow.getExecutedAmount(),
                            true);
                    l_dblEstimatedPrice = l_contractAmountAtOrder.getEstimatedContractPrice();
                    l_dblCalcPrice = l_contractAmountAtOrder.getCalcUnitPrice();
                }
                //��������Ώۂ̒����P��.�����J�e�S��=="�ԍϒ���"�̏ꍇ
                else if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderCateg))
                {
                    WEB3EquityPositionManager l_positionManager =
                        (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
                    EqTypeSettleContractOrderEntry[] l_entry =
                        l_positionManager.createCloseMarginContractEntry(l_orderUnitRow.getOrderUnitId());
                    WEB3EquityRealizedProfitAndLossPrice l_profitAndLossPrice =
                        l_orderManager.calcEstimatedRealizedProfitAndLossAmount(
                            l_commission,
                            l_orderUnitRow.getLimitPrice(),
                            l_subAccount,
                            l_tradedProduct,
                            l_entry,
                            l_orderUnitRow.getQuantity(),
                            l_orderUnit,
                            0D,
                            0D,
                            true);
                    l_dblEstimatedPrice =
                        l_profitAndLossPrice.getEstimatedRealizedProfitAndLossAmount();
                    l_dblCalcPrice = l_profitAndLossPrice.getCalcUnitPrice();
                }
            }
            //get�X�g�b�v�����������T�Z����v�Z���ʂ̖߂�l��null�ȊO�̏ꍇ
            else
            {
                l_dblEstimatedPrice = l_equityEstimatedPrice.getEstimateDeliveryAmount();
                l_dblCalcPrice = l_equityEstimatedPrice.getCalcUnitPrice();
            }
        }
        
        log.debug("��n����F[" + l_dblEstimatedPrice + "]");
        
        // �������������C���^�Z�v�^()
        String l_strCloseNotifyType = l_hostEqtypeCloseOrderNotifyParams.getCloseNotifyType();
        boolean l_isCloseOrderCancel;
        double l_dblPrice = 0.0D;
        if (WEB3CloseNotifyTypeDef.CLOSE.equals(l_strCloseNotifyType))
        {
            l_isCloseOrderCancel = false;
            l_dblPrice = l_orderUnitRow.getPrice();
        }
        else if (WEB3CloseNotifyTypeDef.CLOSE_CANCEL.equals(l_strCloseNotifyType))
        {
            l_isCloseOrderCancel = true;
            l_dblPrice = l_dblCalcPrice;
        }
        else
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00130,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����ʒm�敪 = " + l_strCloseNotifyType);
        }
        WEB3EquityReceiveCloseOrderInterceptor l_equityCloseOrderInterceptor = 
            new WEB3EquityReceiveCloseOrderInterceptor(
                l_isCloseOrderCancel,
                l_hostEqtypeCloseOrderNotifyParams.getErrorMessage(),
                l_dblEstimatedPrice,
                l_dblPrice);
        
        // setThreadLocalPersistenceEventInterceptor(���������C���^�Z�v�^)
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_equityCloseOrderInterceptor);
                
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�������)<BR>
     * �Ώے������A�����^��������ɂ��X�V���s���ėǂ���Ԃł��邩�`�F�b�N����B<BR>
     * <BR>
     * �i�`�F�b�N���e�j<BR>
     * �P�j�@@�����œn���ꂽ�����P��.�s�ꂩ��m�F�ς̐��� == NaN�̏ꍇ�A<BR>
     * �@@�@@�u�Y�������͎�t���ρ^�ύX�̎�t�ρ^�������̏�ԁv�̗�O��throw����B<BR>
     * <BR>
     * �Q�j�@@�����œn���ꂽ�����P��.������Ԃ��ȉ��̂����ꂩ�ɊY������ꍇ�A<BR>
     * �@@�@@�u�Y�������͎�t���ρ^�ύX�̎�t�ρ^�������̏�ԁv�̗�O��throw����B<BR>
     * �@@�@@�@@�@@ACCEPTED�F��t�ρi�V�K�����j<BR>
     * �@@�@@�@@�@@ORDERING�F�������i�V�K�����j<BR>
     * �@@�@@�@@�@@MODIFY_ACCEPTED�F��t�ρi�ύX�����j<BR>
     * �@@�@@�@@�@@MODIFYING�F�������i�ύX�����j<BR>
     * �@@�@@�@@�@@CANCEL_ACCEPTED�F��t�ρi��������j<BR>
     * �@@�@@�@@�@@CANCELLING�F�������i��������j<BR>
     * <BR>
     * �ȊO�A���̂܂�return����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     */
    protected void validateOrderStatus(EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderStatus(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
            
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        if (l_orderUnitRow.getConfirmedQuantity() == 0.0D)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01975,
                this.getClass().getName() + STR_METHOD_NAME);
        }
            
        OrderStatusEnum l_orderStatus = l_orderUnitRow.getOrderStatus();
        if (OrderStatusEnum.ACCEPTED.equals(l_orderStatus) ||
            OrderStatusEnum.ORDERING.equals(l_orderStatus) ||
            OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus) ||
            OrderStatusEnum.MODIFYING.equals(l_orderStatus) ||
            OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderStatus) ||
            OrderStatusEnum.CANCELLING.equals(l_orderStatus))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01975,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
}
@
