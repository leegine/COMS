head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesChangeCancelNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨��������ʒmUnitService�����N���X(WEB3FuturesChangeCancelNotifyUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/21 Ḗ@@�� (���u) �V�K�쐬
                 : 2006/07/28 �ęԍg (���u)�@@�d�l�ύX�@@���f��502
                 : 2006/11/29 ����(���u) �d�l�ύX���f��No.577
Revesion History : 2008/03/17 �����F (���u)�d�l�ύX ���f��833
*/

package webbroker3.ifo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CanmodReceiptTypeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoBizLogicProvider;
import webbroker3.ifo.WEB3IfoChangeCancelNotifyUpdateInterceptor;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.define.WEB3IfoCanmodReceiptTypeDef;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeCancelNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3IfoExecutedMailSendService;

/**
 * (�敨��������ʒmUnitServiceImpl)<BR>
 * �����w���敨��������ʒmUnitService�����N���X<BR>
 * <BR>
 * �����P�����Ƃ̒�������ʒm���������{����B<BR>
 * <BR>
 * Plugin���Ɏ����g�����U�N�V����TransactionalInterceptor<BR>
 * (TransactionalInterceptor.TX_JOIN_EXISTING)���w�肷��B<BR>
 * @@author Ḗ@@��
 * @@version 1.0
 */
public class WEB3FuturesChangeCancelNotifyUnitServiceImpl
    implements WEB3FuturesChangeCancelNotifyUnitService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FuturesChangeCancelNotifyUnitServiceImpl.class);

    /**
     * @@roseuid 40F7A2D00261
     */
    public WEB3FuturesChangeCancelNotifyUnitServiceImpl()
    {

    }

    /**
     * (notify����)<BR>
     * �����ʒm�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨��������ʒm�jnotify�����v�Q�ƁB<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g<BR>
     * @@param l_ifoChangeCancelNotifyInterceptor - �敨OP��������ʒm�C���^�Z�v�^�I�u�W�F�N�g<BR>
     * @@roseuid 40A8A06F0266
     */
    public void notifyChange(
        OrderUnit l_orderUnit,
        WEB3IfoChangeCancelNotifyUpdateInterceptor l_ifoChangeCancelNotifyInterceptor)
        throws WEB3BaseException    
    {
        final String STR_METHOD_NAME = "notifyChange()";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null || l_ifoChangeCancelNotifyInterceptor == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.IFO);
            
        IfoOrderManager l_orderManager = (IfoOrderManager)l_tradingMod.getOrderManager();
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_ifoChangeCancelNotifyInterceptor);

		//1:�����P�ʂ��Ď擾����B�i�������b�N���������ꍇ���l���j
		OrderUnit l_reOrderUnit = l_orderUnit;
		try
		{
			l_reOrderUnit =  l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
		}
		catch (NotFoundException l_nfe)
		{
			log.error("�f�[�^�s�����G���[�B", l_nfe);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80006,
				this.getClass().getName() + STR_METHOD_NAME,
				l_nfe.getMessage(),
				l_nfe); 
		}
        
        //3:�擾������w�l
        double l_dblChangedLimitPrice =
            l_ifoChangeCancelNotifyInterceptor.getChangedLimitPrice();
        if (Double.isNaN(l_dblChangedLimitPrice))
        {
            l_dblChangedLimitPrice = 0;
        }
        log.debug("������w�l" + l_dblChangedLimitPrice);

        //4:�擾�����㐔��
        double l_dblChangedQuantity =
            l_ifoChangeCancelNotifyInterceptor.getChangedQuantity();
        if (Double.isNaN(l_dblChangedQuantity))
        {
            l_dblChangedQuantity = 0;
        }
        log.debug("�����㐔��" + l_dblChangedQuantity);
        //�擾�����O����
        double l_dblQuantity = l_reOrderUnit.getQuantity();
        if (Double.isNaN(l_dblQuantity))
        {
            l_dblQuantity = 0;
        }
        log.debug("�擾�����O����" + l_dblQuantity);

        //5:�擾�������
        TradedProduct l_tradedProduct = l_reOrderUnit.getTradedProduct();

        //6�F�����J�e�S�����擾����
        OrderCategEnum l_orderCateg = l_reOrderUnit.getOrderCateg();

        //7: ��萔��
        double l_dblExecutedQuantity = l_reOrderUnit.getExecutedQuantity();
        if (Double.isNaN(l_dblExecutedQuantity))
        {
            l_dblExecutedQuantity = 0;
        }
        log.debug("��萔��" + l_dblExecutedQuantity);

        //8: ���v�����z
        double l_dblExecutedAmount = l_reOrderUnit.getExecutedAmount();
        if (Double.isNaN(l_dblExecutedAmount))
        {
            l_dblExecutedAmount = 0;
        }
        log.debug(" ���v�����z" + l_dblExecutedAmount);
        
        //9: �萔���I�u�W�F�N�g�𐶐�����
        WEB3IfoBizLogicProvider l_bizLogicProvider = new WEB3IfoBizLogicProvider();
        WEB3GentradeCommission l_commision =
            l_bizLogicProvider.createCommission(
                l_reOrderUnit.getOrderUnitId(),
                l_dblChangedQuantity);

        //����UnitId
        long l_lngOrderUnitId = l_reOrderUnit.getOrderUnitId();    
        log.debug("����UnitId" + l_lngOrderUnitId );

        //����Id
        long l_lngOrderId = l_reOrderUnit.getOrderId();
        log.debug("����Id " + l_lngOrderId);

        //�ڋqId
        long l_lngMainAccountId = l_reOrderUnit.getAccountId();
        log.debug("�ڋqId" + l_lngMainAccountId);

        //SubAccountID
        long l_lngSubAccountId = l_reOrderUnit.getSubAccountId();
        log.debug("SubAccountID is:" + l_lngSubAccountId);

        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            //�擾�⏕����
            l_subAccount =
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                    l_lngMainAccountId,
                    l_lngSubAccountId);
        }
        catch (NotFoundException l_nfe)
        {
            log.error("�f�[�^�s�����G���[�B", l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe); 
        }
        
        WEB3FuturesOrderManagerImpl l_orderMgr =
            (WEB3FuturesOrderManagerImpl)l_tradingMod.getOrderManager();
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateCalResult = new WEB3IfoEstimateDeliveryAmountCalcResult();

        //10:�����J�e�S�����h�V�K�������h�̏ꍇ
        if (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_orderCateg))
        {
            // 11: calc�������T�Z�����()
            //�T�Z��������v�Z����B
            //  [calc�T�Z�����()�Ɏw�肷�����]
            //  �萔���F�@@�萔���I�u�W�F�N�g
            //  �w�l�F�@@get������w�l()�̖߂�l
            //  �⏕�����F�@@�����P��.�⏕�����h�c�ɊY������⏕�����I�u�W�F�N�g
            //  �敨OP��������F�@@�����P��.getTradedProduct()
            //  ���ʁF get�����㐔��()�̖߂�l
            //  ��萔�ʁF�@@�����P��.getExecutedQuantity()
            //  ���v�����z�F�@@�����P��.getExecutedAmount()
            //  isSkip���z�`�F�b�N�F�@@true
            l_estimateCalResult = l_orderMgr.calcChangeEstimatePrice(
                l_commision,
                l_dblChangedLimitPrice,
                l_subAccount,
                (WEB3IfoTradedProductImpl)l_tradedProduct,
                l_dblChangedQuantity,
                l_dblExecutedQuantity,
                l_dblExecutedAmount,
                true);
            log.debug("�������T�Z��n��� is:" + l_estimateCalResult.getCalcUnitPrice());

            //12: set�T�Z��n���
            //  [����]
            //  �T�Z��n����F
            //  �T�Z��n����v�Z���ʁicalc�������T�Z������̖߂�l�j.�T�Z��n���
            l_estimateCalResult.setEstimateDeliveryAmount(l_estimateCalResult.getEstimateDeliveryAmount());
        }

        //13:�����J�e�S�����h�ԍϒ����h�̏ꍇ
        // is�ԍϒ��� 92�F�敨�ԍϒ���
        if ((OrderCategEnum.IDX_FUTURES_CLOSE.equals(l_orderCateg)))
        {
            log.debug("�����J�e�S�����h�ԍϒ����h�̏ꍇ");
			//16: adjust�ԍώw����
			WEB3IfoPositionManagerImpl l_positionMgr =
				(WEB3IfoPositionManagerImpl)l_tradingMod.getPositionManager();
			l_positionMgr.adjustClosingContractSpecs(
				l_lngMainAccountId,
				l_lngSubAccountId,
				l_lngOrderId,
				l_lngOrderUnitId,
				l_dblQuantity,
				l_dblChangedQuantity);

            //17:create�ԍό��ʃG���g��
            WEB3IfoPositionManagerImpl l_ifoPositionManagerImpl = 
                (WEB3IfoPositionManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getPositionManager();
            SettleContractEntry[] l_entry = l_ifoPositionManagerImpl.createSettleContractEntry(l_lngOrderUnitId);

            //18:get side
            SideEnum l_side = null;
            if (SideEnum.BUY.equals(l_reOrderUnit.getSide()))
            {
                l_side = SideEnum.SELL;
            }
            else if (SideEnum.SELL.equals(l_reOrderUnit.getSide()))
            {
                l_side = SideEnum.BUY;
            }
            
            //19:calc�������T�Z���ϑ��v
            WEB3IfoTradedProductImpl l_ifoTradedProduct = null;

            l_ifoTradedProduct = (WEB3IfoTradedProductImpl)l_reOrderUnit.getTradedProduct();

            l_estimateCalResult =
                l_orderMgr.calcChangeEstimateSettlementIncome(
                    l_commision,
                    l_dblChangedLimitPrice,
                    (WEB3GentradeSubAccount)l_subAccount,
                    l_ifoTradedProduct,
                    l_entry,
                    l_dblChangedQuantity,
                    l_side,
                    l_dblExecutedQuantity,
                    l_lngOrderUnitId,
                    true);
        }

        //20:get�v�Z�P��
        double l_dblCalOrderUnit = l_estimateCalResult.getCalcUnitPrice();
        if (Double.isNaN(l_dblCalOrderUnit))
        {
            l_dblCalOrderUnit = 0;
        }
        log.debug("�v�Z�P��" + l_dblCalOrderUnit);
        //21:set�v�Z�P��
        l_ifoChangeCancelNotifyInterceptor.setCalcUnitPrice(l_dblCalOrderUnit);

        //22: get�T�Z��n���
        double l_dblEstimateAmount = l_estimateCalResult.getEstimateDeliveryAmount();
        if (Double.isNaN(l_dblEstimateAmount))
        {
            l_dblEstimateAmount = 0;
        }
        log.debug("�T�Z��n���" + l_dblEstimateAmount);
        //23:set�T�Z��n���
        l_ifoChangeCancelNotifyInterceptor.setEstimateDeliveryAmount(
            l_dblEstimateAmount);
        WEB3GentradeTradingTimeManagement.setBusinessTimestamp();

        //24: get��������ʒm�敪
        String l_strChangeCancelNotifyDivision =
            l_ifoChangeCancelNotifyInterceptor.getChangeCancelNotifyDivision();
        log.debug("��������ʒm�敪" + l_strChangeCancelNotifyDivision);

        //�擾MarketAdapter
        MarketAdapter l_marketAdapter = l_tradingMod.getMarketAdapter();
        IfoMarketResponseReceiverCallbackService l_receiverCallbackService =
            (IfoMarketResponseReceiverCallbackService)l_marketAdapter
                .getMarketResponseReceiverCallbackService();

        //25: ���������̏ꍇ�̂ݎ��{
        if (WEB3CanmodReceiptTypeDef.CHANGED.equals(l_strChangeCancelNotifyDivision))
        {
            //26:�������ʁi���������j�I�u�W�F�N�g�𐶐�
            DefaultChangeOrderAcceptedMarketResponseMessage l_marketAcceptedResponseMessage =
                new DefaultChangeOrderAcceptedMarketResponseMessage(l_lngOrderId);

            //27:���������𒍕��ɍX�V����
            ProcessingResult l_result = l_receiverCallbackService.process(l_marketAcceptedResponseMessage);
            if (l_result.isFailedResult())
            {
                throw new WEB3SystemLayerException(
                    l_result.getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //31: �S�����ς݂��𔻒肷��
            OrderUnit l_newOrderUnit = l_reOrderUnit;
            try
            {
                l_newOrderUnit =  l_orderManager.getOrderUnit(l_reOrderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_nfe)
            {
                log.error("�f�[�^�s�����G���[�B", l_nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe); 
            }
            
            //isFullyExecuted( )
            boolean l_isFullyExecuted = l_newOrderUnit.isFullyExecuted();
            //sendMailProcess  
            //9: �S�����ς݂��𔻒肷��
            if (l_isFullyExecuted)
            {   
                WEB3IfoExecutedMailSendService l_mailSendService = 
                    (WEB3IfoExecutedMailSendService) Services.
                    getService(WEB3IfoExecutedMailSendService.class);
            
                //27.1: ��胁�[�����M�e�[�u���ɖ�胁-���s��}������    
                l_mailSendService.sendMailProcess(l_newOrderUnit, null);
                
                //27.2 notify���[���G���W���T�[�o(IfoOrderUnit, OrderManagerPersistenceContext)
                try
                {
                    WEB3FuturesOrderManagerImpl l_optionOrderManager = (WEB3FuturesOrderManagerImpl)l_orderManager;
                    l_optionOrderManager.notifyRLS((IfoOrderUnit) l_newOrderUnit, OrderManagerPersistenceContext.FILL_ORDER);
                }
                catch (WEB3BusinessLayerException l_ex)
                {
                    log.debug("notify���[���G���W���T�[�o()�ɂċƖ��G���[���X���[���ꂽ�ꍇ�A�����[���o�b�N���Ȃ��B");
                }
            }
        }

        //28: �������s�̏ꍇ�̂ݎ��{
        if (WEB3CanmodReceiptTypeDef.CHANGE_FAILED.equals(l_strChangeCancelNotifyDivision))
        {
            //29:�������ʁi�������s�j�I�u�W�F�N�g�𐶐�
            DefaultChangeOrderRejectedMarketResponseMessage l_marketRejectedResponseMessage =
                new DefaultChangeOrderRejectedMarketResponseMessage(l_lngOrderId);

            //30:�������s�𒍕��ɍX�V����
            ProcessingResult l_result = l_receiverCallbackService.process(l_marketRejectedResponseMessage);
            if (l_result.isFailedResult())
            {
                throw new WEB3SystemLayerException(
                    l_result.getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }


        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (notify���)<BR>
     * ����ʒm�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨��������ʒm�jnotify����v�Q�ƁB<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g<BR>
     * @@param l_ifoChangeCancelNotifyInterceptor - �敨OP��������ʒm�C���^�Z�v�^�I�u�W�F�N�g<BR>
     * @@roseuid 40A8A06F0285
     */
    public String notifyCancel(
        OrderUnit l_orderUnit,
        WEB3IfoChangeCancelNotifyUpdateInterceptor l_ifoChangeCancelNotifyInterceptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyCancel(OrderUnit l_orderUnit, WEB3IfoChangeCancelNotifyUpdateInterceptor l_ifoChangeCancelNotifyInterceptor) ";
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.IFO);
            
        IfoOrderManager l_orderManager = (IfoOrderManager)l_tradingMod.getOrderManager();
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_ifoChangeCancelNotifyInterceptor);
  
        //�擾MarketAdapter
        MarketAdapter l_marketAdapter = l_tradingMod.getMarketAdapter();
        // get the service
        IfoMarketResponseReceiverCallbackService l_callbackServiceImpl =
            (IfoMarketResponseReceiverCallbackService)l_marketAdapter
                .getMarketResponseReceiverCallbackService();

        log.entering(STR_METHOD_NAME);
        if (l_orderUnit == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

		//�����P�ʂ��Ď擾����B�i�������b�N���������ꍇ���l���j
		OrderUnit l_reOrderUnit = l_orderUnit;
		try
		{
			l_reOrderUnit = l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
		}
		catch (NotFoundException l_nfe)
		{
			log.error("�f�[�^�s�����G���[�B", l_nfe);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80006,
				this.getClass().getName() + STR_METHOD_NAME,
				l_nfe.getMessage(),
				l_nfe); 
		}

        //1.3 �擾��������ʒm�敪
        String l_strDivision =
            l_ifoChangeCancelNotifyInterceptor.getChangeCancelNotifyDivision();
        //�擾OrderId
        long l_lngOrderId = l_reOrderUnit.getOrderId();
        //1.4 get����������ʃR�[�h
        String l_strResultCode = l_ifoChangeCancelNotifyInterceptor.getChangeCancelResultCode();

        double l_dblExecutedQuantity = l_reOrderUnit.getExecutedQuantity();
        if (Double.isNaN(l_dblExecutedQuantity))
        {
            l_dblExecutedQuantity = 0;
        }

        //1.5 get�����㐔��
        double l_dblChangedQuantity = l_ifoChangeCancelNotifyInterceptor.getChangedQuantity();
        if ((WEB3IfoCanmodReceiptTypeDef.CANCELED_COMPLETE.equals(l_strDivision))
        && (l_dblChangedQuantity > l_dblExecutedQuantity))
        {
            return WEB3StatusDef.DEALING;
        }
        log.debug("�����㐔�� = " + l_dblChangedQuantity);       
        WEB3GentradeTradingTimeManagement.setBusinessTimestamp();

        //�敨�����}�l�[�W�����擾
        WEB3FuturesOrderManagerImpl l_orderMgr =
            (WEB3FuturesOrderManagerImpl)l_tradingMod.getOrderManager();

        //1.8 �������
        if (WEB3IfoCanmodReceiptTypeDef.CANCELED_COMPLETE.equals(l_strDivision))
        {
            //1.8.1 (get�����㐔�� > 0)�̏ꍇ���{�B
            if (l_dblChangedQuantity > 0)
            {
                double l_dblNetAmount = 0D;

                //1.8.1.1 get��n���z���v(IfoOrderUnit)
                l_dblNetAmount = l_orderMgr.getNetAmount((IfoOrderUnit)l_reOrderUnit);

                //1.8.1.2 set�T�Z��n���(double)
                l_ifoChangeCancelNotifyInterceptor.setEstimateDeliveryAmount(l_dblNetAmount);
            }
            
            //1.8.2 isSONAR���(IfoOrderUnit)
            boolean l_blnIsSONARCancel = l_orderMgr.isSONARCancel((IfoOrderUnit)l_reOrderUnit);
            
            //1.8.3 ������ʁi��������j�I�u�W�F�N�g�𐶐�����
            DefaultCancelOrderAcceptedMarketResponseMessage l_acceptedResponseMessage =
                new DefaultCancelOrderAcceptedMarketResponseMessage(l_lngOrderId);

            //1.8.4 ��������𒍕��ɍX�V����
            ProcessingResult l_result = l_callbackServiceImpl.process(l_acceptedResponseMessage);
            if (l_result.isFailedResult())
            {
                throw new WEB3SystemLayerException(
                    l_result.getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //is�\�񒍕��m�F�v(IfoOrderUnit)
            boolean l_blnIsReserveOrderExist = l_orderMgr.isReserveOrderExist((IfoOrderUnit)l_reOrderUnit);

            //�\�񒍕��m�F�v�iis�\�񒍕��m�F�v() == true�j�̏ꍇ
            if (l_blnIsReserveOrderExist)
            {
                WEB3ToSuccReservationIfoOrderUpdateService l_ifoOrderUpdateService =
                    (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                        WEB3ToSuccReservationIfoOrderUpdateService.class);
                //cancelAll�\�񒍕��P��(�e�����̒���ID : long)
                l_ifoOrderUpdateService.cancelAllOrderUnit(((IfoOrderUnit)l_reOrderUnit).getOrderId());
            }

            OrderUnit l_newOrderUnit = l_reOrderUnit;
            try
            {
                l_newOrderUnit =  l_orderManager.getOrderUnit(l_reOrderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_nfe)
            {
                log.error("�f�[�^�s�����G���[�B", l_nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe); 
            }
            
            //SONAR���͂̎���iisSONAR���()�̖߂�l == true�j�̏ꍇ
            if(l_blnIsSONARCancel)
            {
                //1.8.5 notify���[���G���W���T�[�o(IfoOrderUnit, OrderManagerPersistenceContext)
                try
                {
                    l_orderMgr.notifyRLS((IfoOrderUnit) l_newOrderUnit, 
                        OrderManagerPersistenceContext.CANCEL_ORDER_CONFIRMED_BY_MKT);
                }
                catch (WEB3BusinessLayerException l_ex)
                {
                    log.debug("notify���[���G���W���T�[�o()�ɂċƖ��G���[���X���[���ꂽ�ꍇ�A�����[���o�b�N���Ȃ��B");
                }
            }
            
            //l_isUnexecuted = false( )
            boolean l_isUnexecuted = l_newOrderUnit.isUnexecuted();
            //sendMailProcess  
            //1.8.6 �S�����ς݂��𔻒肷��
            if (!l_isUnexecuted)
            {                   
                WEB3IfoExecutedMailSendService l_mailSendService = 
                    (WEB3IfoExecutedMailSendService) Services.
                    getService(WEB3IfoExecutedMailSendService.class);
            
                //1.8.7 ��胁�[�����M�e�[�u���ɖ�胁-���s��}������    
                l_mailSendService.sendMailProcess(l_newOrderUnit, null);
            }
        }

        //1.9 ������s
        else if (WEB3IfoCanmodReceiptTypeDef.CANCELED_FAILED.equals(l_strDivision))
        {
            try
            {
                l_reOrderUnit = l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_nfe)
            {
                log.error("�f�[�^�s�����G���[�B", l_nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe); 
            }
            
            //(*)SONAR���͂̎���iisSONAR���()�̖߂�l == true�j�̏ꍇ
            if (l_orderMgr.isSONARCancel((IfoOrderUnit)l_orderUnit))
            {
                //1.9.3 notify���[���G���W���T�[�o(IfoOrderUnit, OrderManagerPersistenceContext)
                try
                {
                    l_orderMgr.notifyRLS((IfoOrderUnit) l_reOrderUnit, 
                        OrderManagerPersistenceContext.CANCEL_ORDER_REJECTED_BY_MKT );
                }
                catch (WEB3BusinessLayerException l_ex)
                {
                    log.debug("notify���[���G���W���T�[�o()�ɂċƖ��G���[���X���[���ꂽ�ꍇ�A�����[���o�b�N���Ȃ��B");
                }
            }
            
            //1.9.1 ������ʁi������s�j�I�u�W�F�N�g�𐶐�����
            DefaultCancelOrderRejectedMarketResponseMessage l_rejectedResponseMessage =
                new DefaultCancelOrderRejectedMarketResponseMessage(l_lngOrderId);

            //1.9.2 ������s�𒍕��ɍX�V����
            ProcessingResult l_result = l_callbackServiceImpl.process(l_rejectedResponseMessage);
            if (l_result.isFailedResult())
            {
                throw new WEB3SystemLayerException(
                    l_result.getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
        }

        log.debug(STR_METHOD_NAME);
        return WEB3StatusDef.DEALT;
    }

    /**
     * (is�����X�V��)<BR>
     * �w�蒍���P�ʂ������X�V�ς݂��𔻒肷��B<BR>
     * <BR>
     * �|�����X�V�ς݂̏ꍇ��true��ԋp����B<BR>
     * �|�����X�V���Ă��Ȃ��iHOST����̒������́j�ꍇ��false��ԋp����B<BR>
     * <BR>
     * �����̒����P�ʃI�u�W�F�N�g.������Ԃ��ȉ��̏ꍇtrue��ԋp����B<BR>
     * �@@�h��t�ρi�ύX�����j�h�FOrderStatusEnum.MODIFY_ACCEPTED<BR>
     * �@@�h�������i�ύX�����j�h�FOrderStatusEnum.MODIFYING<BR>
     * <BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * @@param l_orderUnit - �����P��<BR>
     * @@return boolean
     * @@roseuid 40A8A06F0295
     */
    protected boolean isChangeUpdateEnd(OrderUnit l_orderUnit)
    {

        OrderStatusEnum l_orderStatus = l_orderUnit.getOrderStatus();
        if ((OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus))
            || (OrderStatusEnum.MODIFYING.equals(l_orderStatus)))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * (is����X�V��)<BR>
     * �w�蒍���P�ʂ�����X�V�ς݂��𔻒肷��B<BR>
     * <BR>
     * �|����X�V�ς݂̏ꍇ��true��ԋp����B<BR>
     * �|����X�V���Ă��Ȃ��iHOST����̎�����́j�ꍇ��false��ԋp����B<BR>
     * <BR>
     * �����̒����P�ʃI�u�W�F�N�g.������Ԃ��ȉ��̏ꍇtrue��ԋp����B<BR>
     * �@@�h��t�ρi��������j�h�FOrderStatusEnum.CANCEL_ACCEPTED<BR>
     * �@@�h�������i��������j�h�FOrderStatusEnum.CANCELLING<BR>
     * <BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * @@param l_orderUnit - �����P��<BR>
     * @@return boolean
     * @@roseuid 40A8A06F02B4
     */
    protected boolean isCancelUpdateEnd(OrderUnit l_orderUnit)
    {
        OrderStatusEnum l_orderStatus = l_orderUnit.getOrderStatus();
        if ((OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderStatus))
            || (OrderStatusEnum.CANCELLING.equals(l_orderStatus)))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
@
