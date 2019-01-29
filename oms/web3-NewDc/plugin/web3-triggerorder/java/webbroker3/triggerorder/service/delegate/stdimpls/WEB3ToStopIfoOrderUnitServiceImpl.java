head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.54.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToStopIfoOrderUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �t�w�l�����敨OP�����ꌏ�T�[�r�XImpl(WEB3ToStopIfoOrderUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/1/23 ������(���u) �V�K�쐬
Revesion History : 2006/8/24 ������(���u) ���f��No.164 ���� 
Revesion History : 2007/1/30 �����Q(���u) ���f��No.212 ����
Revesion History : 2007/1/31 �����Q(���u) ���f��No.218 ����
Revesion History : 2007/1/31 �����Q(���u) DB�X�V�d�lNo.036 ����
Revesion History : 2007/6/30 �Ј���(���u) ���f��No.239
Revesion History : 2008/4/10 ��іQ(���u) ���f��No.275
Revesion History : 2009/11/19 �Ԑi  (���u) �y�g���K�[�����E���Ǘ��ҁz�t�w�l����������Q�Ή�

*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultOrderInvalidatedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderSwitching;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoBizLogicProvider;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractChangeSpec;
import webbroker3.ifo.WEB3IfoOpenContractChangeUpdateInterceptor;
import webbroker3.ifo.WEB3IfoOpenContractOrderSpec;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;
import webbroker3.mqgateway.WEB3MQGatewayService;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToStopIfoOrderUpdateInterceptor;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.triggerorder.service.delegate.WEB3ToStopIfoOrderUnitService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�t�w�l�����敨OP�����ꌏ�T�[�r�XImpl)<BR>
 * �t�w�l�����敨OP�����ꌏ�T�[�r�X�����N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3ToStopIfoOrderUnitServiceImpl implements
        WEB3ToStopIfoOrderUnitService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToStopIfoOrderUnitServiceImpl.class);
    
    public WEB3ToStopIfoOrderUnitServiceImpl() 
    {
     
    }

    /**
     * (submit�敨�V�K���t�w�l����)<BR>
     * �敨�V�K���t�w�l�����𔭒�����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�t�w�l�����敨OP�����ꌏ�T�[�r�X�jsubmit�敨�V�K���t�w�l�����v�Q�ƁB<BR>
     * @@param l_orderUnit - (�敨OP�����P��)<BR>
     * �敨OP�����P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     */
    public void submitFuturesOpenContractStopOrder(IfoOrderUnit l_orderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " submitFuturesOpenContractStopOrder(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("�敨OP�����P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�敨OP�����P�� = null�B");
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.IFO);
        WEB3FuturesOrderManagerImpl l_orderMgr =
            (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        
        //�����œn���ꂽ�����P�ʂ͍X�V����Ă���\��������̂ŁA�Ď擾���s�����ƁB        
        try
        {
            l_orderUnit = (IfoOrderUnit) l_orderMgr.getOrderUnit(l_orderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�����P�ʃe�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        try
        {
            //1.1:is�����Ώ�(IfoOrderUnit)
            boolean l_blnIsProcessObj = this.isProcessObject(l_orderUnit);
            
            //1.2:�i����t���[�F�@@is�����Ώہ�false�i�����ΏۊO�����j�̏ꍇ�j
            if (!l_blnIsProcessObj)
            {
                //1.2.1:�����ΏۊO�̏ꍇ�A�����������̂܂�return����B
                //�i����X�e�[�^�X�ŏI���j
                log.exiting(STR_METHOD_NAME);
                return;
            }
            
            //1.3:get������(�m�F�������� : Date,����敪 : String)
            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            WEB3GentradeTradingTimeManagement.getOrderBizDate(
                WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd"),
                l_orderUnitRow.getSessionType());
                
            //1.4:getSubAccount(arg0 : long, arg1 : long)
            WEB3GentradeAccountManager l_accountMgr =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            
            WEB3GentradeSubAccount l_subAccount = null;            
            try
            {
                l_subAccount = (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(
                    l_orderUnit.getAccountId(), 
                    l_orderUnit.getSubAccountId());//NotFoundException
            }
            catch (NotFoundException l_ex)
            {
                log.error("�⏕�����e�[�u���ɊY������f�[�^������܂���B", l_ex);
    
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            WEB3GentradeFinObjectManager l_finObjectMgr =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                
            //1.5: �i����t���[�F�����P��.�����ID��null�̏ꍇ�̂݁j
            Trader l_trader = null;
            if (!l_orderUnitRow.getTraderIdIsNull())
            {
                //1.5.1: getTrader(arg0 : long)
                try
                {
                    l_trader = l_finObjectMgr.getTrader(l_orderUnit.getTraderId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("���҃e�[�u���ɊY������f�[�^������܂���B", l_ex);
    
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }
            
            //1.6:create�V�K���������e(�،���ЃR�[�h : String, ���� : ����, 
            //is���� : boolean, �s��R�[�h : String, ���� : �敨OP����, 
            //���� : double, �w�l : double, ���s���� : IfoOrderExecutionConditionType,
            // ���������� : Date, �������� : String, (W�w�l)�����w�l : double, ���������敪 : 
            //String, ���񒍕��̒����P��ID : Long, �[��O�J�z�Ώۃt���O : boolean)
            
            //is�����F�@@
            //  �����P��.getSide()��SideEnum.BUY(��)�̏ꍇ�Atrue���Z�b�g�B
            //  �����P��.getSide()��SideEnum.SELL(��)�̏ꍇ�Afalse���Z�b�g�B
            boolean l_blnIsBuy = false;
            
            if (SideEnum.BUY.equals(l_orderUnit.getSide()))
            {
                l_blnIsBuy = true;
            }
            
            //�s��R�[�h�F�@@�����P��.�s��ID�ɊY������s��I�u�W�F�N�g.�s��R�[�h
            String l_strMarketCode = null;
            
            if (!l_orderUnitRow.getMarketIdIsNull())
            {
                try
                {
                    Market l_market = l_finObjectMgr.getMarket(l_orderUnitRow.getMarketId());//NotFoundException
                    l_strMarketCode = l_market.getMarketCode();
                }
                catch (NotFoundException l_ex)
                {
                    log.error("�s��e�[�u���ɊY������f�[�^������܂���B", l_ex);
    
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }
            
            //�����F�@@�����P��.����ID�ɊY������敨OP�����I�u�W�F�N�g
            WEB3IfoProductManagerImpl l_productMgr = 
                (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();
            WEB3IfoProductImpl l_product = null;
                
            try
            {
                l_product = (WEB3IfoProductImpl) l_productMgr.getProduct(l_orderUnitRow.getProductId());//NotFoundException
            }
            catch (NotFoundException l_ex)
            {
                log.error("�����e�[�u���ɊY������f�[�^������܂���B", l_ex);
    
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //���������敪 = �敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)
            String l_strOrderExpirationType = WEB3IfoDataAdapter.getExpirationDateType(l_orderUnit);
            //���񒍕��̒����P��ID = �����P��.���񒍕��̒����P��ID
            long l_lngFirstOrderUnitId = l_orderUnitRow.getFirstOrderUnitId();
            //�[��O�J�z�Ώۃt���O = �敨OP�f�[�^�A�_�v�^.get�[��O�J�z�Ώۃt���O�iPR�w�j(�����P��)
            boolean l_blnEveningSessionCarryoverFlag =
                WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(l_orderUnit);

            Long l_firstOrderUnitId = null;
            if (!l_orderUnitRow.getFirstOrderUnitIdIsNull())
            {
                l_firstOrderUnitId = new Long(l_lngFirstOrderUnitId);
            }
            Institution l_institution = l_subAccount.getInstitution();
            WEB3IfoOpenContractOrderSpec l_openContractOrderSpec = WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                l_institution.getInstitutionCode(),
                l_trader,
                l_blnIsBuy,
                l_strMarketCode,
                l_product,
                l_orderUnit.getQuantity(), 
                l_orderUnit.getLimitPrice(),
                l_orderUnit.getExecutionConditionType(),
                l_orderUnitRow.getExpirationDate(),
                l_orderUnitRow.getOrderConditionType(),
                0,
                0,
                null,
                l_strOrderExpirationType,
                l_firstOrderUnitId,
                l_blnEveningSessionCarryoverFlag);
                
            //1.7:validate�敨�V�K������(�⏕���� : SubAccount, �V�K���������e : IfoOpenContractOrderSpec, �����P�� : IfoOrderUnit)                
            NewOrderValidationResult l_validationResult = l_orderMgr.validateFuturesOpenContractOrder(
                l_subAccount,
                l_openContractOrderSpec,
                l_orderUnit);
                
            if (l_validationResult.getProcessingResult().isFailedResult())
            {
                log.debug("validate�敨�V�K�����������s�̏ꍇ�B");
                    
                throw new WEB3BaseException(
                    l_validationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            //1.8:create�萔��(�����P��ID : long)            
            WEB3IfoBizLogicProvider l_ifoBizLogicProvider = 
                (WEB3IfoBizLogicProvider) l_tradingModule.getBizLogicProvider();
            WEB3GentradeCommission l_commission = l_ifoBizLogicProvider.createCommission(l_orderUnit.getOrderUnitId());
            
            //1.9:get�������(�،���� : Institution, �����R�[�h : String, �s��R�[�h : String)
            WEB3IfoTradedProductImpl l_tradedProduct = null;
            try
            {
                l_tradedProduct = l_productMgr.getIfoTradedProduct(
                    l_institution,
                    l_product.getProductCode(),
                    l_strMarketCode);//NotFoundException
            }
            catch (NotFoundException l_ex)
            {
                log.error("��������e�[�u���ɊY������f�[�^������܂���B", l_ex);
    
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            //1.10:calc�T�Z�����(�萔�� : �萔��, �w�l : double, �⏕���� : SubAccount, �敨OP������� : �敨OP�������, 
            //���� : double, isSkip���z�`�F�b�N : boolean)
            WEB3IfoEstimateDeliveryAmountCalcResult l_caclResult = 
                l_orderMgr.calcEstimatePrice(
                    l_commission,
                    l_openContractOrderSpec.getLimitPrice(),
                    l_subAccount,
                    l_tradedProduct,
                    l_openContractOrderSpec.getQuantity(),
                    false);

            //1.11:create�V�K���������e(�����P�� : IfoOrderUnit)
            WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec = this.createOpenContractChangeSpec(l_orderUnit);

			
            //1.12:�敨OP�V�K�������X�V�C���^�Z�v�^(�V�K���������e : �V�K���������e�C�����o�H�敪�F�����P��.�����o�H�敪)
            WEB3IfoOpenContractChangeUpdateInterceptor l_ifoOpenContractChangeUpdateInterceptor
                = new WEB3IfoOpenContractChangeUpdateInterceptor(l_ifoOpenContractChangeSpec);

            //1.13:�v���p�e�B�Z�b�g
            //�萔�����Z�b�g����
            l_ifoOpenContractChangeUpdateInterceptor.setCommision(l_commission);

            //�T�Z��n����v�Z���ʂ��Z�b�g����
            l_ifoOpenContractChangeUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_caclResult);

            //�����������Z�b�g����
            l_ifoOpenContractChangeUpdateInterceptor.setOrderCond(l_orderUnitRow.getOrderConditionType());

            //�����������Z�q���Z�b�g����
            l_ifoOpenContractChangeUpdateInterceptor.setOrderCondOperator(l_orderUnitRow.getOrderCondOperator());

            //�t�w�l��l�^�C�v���Z�b�g����
            l_ifoOpenContractChangeUpdateInterceptor.setStopOrderBasePriceType(l_orderUnitRow.getStopPriceType());

            //�t�w�l��l���Z�b�g����
            l_ifoOpenContractChangeUpdateInterceptor.setStopOrderBasePrice(l_orderUnitRow.getStopOrderPrice());

            //�iW�w�l�j�����w�l
            double l_wLimitPrice = 0D;
            l_ifoOpenContractChangeUpdateInterceptor.setWLimitPriceChange(l_wLimitPrice);

            //�����ID
            long l_traderId = 0L;
            if (l_trader != null)
            {
                l_traderId = l_trader.getTraderId();
            }
            l_ifoOpenContractChangeUpdateInterceptor.setTraderId(l_traderId);

            //�����o�H�敪���Z�b�g����
			l_ifoOpenContractChangeUpdateInterceptor.setOrderRootDiv(l_orderUnitRow.getOrderRootDiv());
			
            WEB3TPTradingPowerService l_tradingPowerService = 
                (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

            Object[] l_interceptorObject = new Object[1];
            l_interceptorObject[0] = l_ifoOpenContractChangeUpdateInterceptor;

            Object[] l_specObject = new Object[1];
            l_specObject[0] = l_ifoOpenContractChangeSpec;

            //1.14:validate����]��(�⏕���� : �⏕����, �������e�C���^�Z�v�^ : Object[], �������e : Object[], ������� : OrderTypeEnum, �]�͍X�V�t���O : boolean)
            WEB3TPTradingPowerResult l_result = 
                l_tradingPowerService.validateTradingPower
                (l_subAccount,
                 l_interceptorObject,
                 l_specObject,
                 l_orderUnitRow.getOrderType(),
                 true);

            //1.15:throw�]�̓G���[���(����]�͌��� : ����]�͌���, �⏕���� : �⏕����)(�敨�����}�l�[�W��::throw�]�̓G���[���)
            l_orderMgr.throwTpErrorInfo(l_result,l_subAccount);

            //1.16:(���s���ʂɉ����Ē����n�f�[�^��UPDATE����)
            //1.16.1:(*)����I�������ꍇ
            //1.16.1.1:update�����f�[�^(IfoOrderUnit, double, double, String)
            WEB3IfoFrontOrderService l_ifoOrderService = (WEB3IfoFrontOrderService) Services
                .getService(WEB3IfoFrontOrderService.class);
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch)l_accountMgr.getBranch(l_orderUnitRow.getBranchId());
            WEB3GentradeMarket l_market = (WEB3GentradeMarket)l_finObjectMgr.getMarket(l_orderUnitRow.getMarketId());

            this.updateOrderData(
                l_orderUnit,
                l_caclResult.getCalcUnitPrice(),
                l_caclResult.getEstimateDeliveryAmount(),
                l_ifoOrderService.getSubmitOrderRouteDiv(l_branch.getInstitution().getInstitutionCode(),
                l_market.getMarketCode()));

            //1.16.1.2:insert�V�K�������L���[(����ID : long)
            l_orderMgr.insertOpenContractHostOrder(l_orderUnit.getOrderId());
        
            //1.16.1.3:sendMQ�g���K(IfoOrderUnit)
            try
            {
                l_orderUnit = (IfoOrderUnit) l_orderMgr.getOrderUnit(l_orderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("�����P�ʃe�[�u���ɊY������f�[�^������܂���B", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        
            this.sendMQTrigger(l_orderUnit);
        }
        //1.16.2:(*)�������ɗ�O���X���[���ꂽ�ꍇ
        catch (Exception l_ex)
        {
            //1.16.2.1:get�����G���[���R�R�[�h(ErrorInfo)
            log.error(l_ex.getMessage(), l_ex);
            ErrorInfo l_errorInfo = null;
            if (l_ex instanceof WEB3BaseException)
            {
                l_errorInfo = ((WEB3BaseException)l_ex).getErrorInfo();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_errorInfo = ((WEB3BaseRuntimeException)l_ex).getErrorInfo();
            }
            String l_errorReasonCode = l_orderMgr.getErrorReasonCode(l_errorInfo.getErrorCode());

            //1.16.2.2:�t�w�l�����敨OP�����X�V�C���^�Z�v�^(�����G���[���R�R�[�h : String)
            WEB3ToStopIfoOrderUpdateInterceptor l_updateInterceptor = 
                new WEB3ToStopIfoOrderUpdateInterceptor(l_errorReasonCode);

            //1.16.2.3:setThreadLocalPersistenceEventInterceptor(arg0 : IfoOrderManagerPersistenceEventInterceptor)
            l_orderMgr.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
            
            //1.16.2.4:DefaultOrderInvalidatedMarketResponseMessage(arg0 : long)
            DefaultOrderInvalidatedMarketResponseMessage l_responseMessage = 
                new DefaultOrderInvalidatedMarketResponseMessage(l_orderUnit.getOrderId());
                
            //1.16.2.5:process(arg0 : OrderInvalidatedMarketResponseMessage)
            MarketAdapter l_markertAdapter = l_tradingModule.getMarketAdapter();
                            
            IfoMarketResponseReceiverCallbackService l_callBackService =
                (IfoMarketResponseReceiverCallbackService) l_markertAdapter.getMarketResponseReceiverCallbackService();            
            
            try
            {
                ProcessingResult l_result = l_callBackService.process(l_responseMessage);
                if (l_result.isFailedResult())
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        l_result.getErrorInfo(),
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
            catch (Exception l_ex2)
            {
                String l_strErrMsg = "�����R���G���[�ƂȂ����t�w�l�����̎��������Ɏ��s���܂����B����ID�F[" + l_orderUnit.getOrderId() + "]";
                log.error(l_strErrMsg, l_ex2);
                if (l_errorInfo != null)
                {
                    if (l_errorInfo.error_tag.startsWith("SYSTEM_"))
                    {
                        throw new WEB3SystemLayerException(
                            l_errorInfo,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_strErrMsg);
                    }
                    else
                    {
                        throw new WEB3BusinessLayerException(
                            l_errorInfo,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_strErrMsg);
                    }
                }
                else
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_strErrMsg);
                }
            }
            
            // is�\�񒍕��m�F�v(�����P�� : IfoOrderUnit)
            //�����P�ʁF�@@�����P��
            boolean l_blnIsReserveOrderExist = l_orderMgr.isReserveOrderExist(l_orderUnit);

            //�\�񒍕��m�F�v�iis�\�񒍕��m�F�v() == true�j�̏ꍇ
            if (l_blnIsReserveOrderExist)
            {
                //invalidateAll�\�񒍕��P��(long)
                //�e�����̒���ID�F�@@�����P��.����ID
                WEB3ToSuccReservationIfoOrderUpdateService l_updateService =
                    (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                        WEB3ToSuccReservationIfoOrderUpdateService.class);

                l_updateService.invalidateAllOrderUnit(l_orderUnit.getOrderId());
            }
        }    
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submit�敨�ԍϋt�w�l����)<BR>
     * �敨�ԍϋt�w�l�����𔭒�����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�t�w�l�����敨OP�����ꌏ�T�[�r�X�jsubmit�敨�ԍϋt�w�l�����v�Q�ƁB<BR>
     *  ================================================================================ <BR>
     * 1.6.1�ԍω\���ʃ`�F�b�N�F�@@<BR>
     * �R�j �ԍϐ���(*1) �� �ԍω\���ʎc��(*2)�@@�̏ꍇ�A�u�ԍω\�c�����ʒ��߃G���[�v<BR>
     * �̗�O��throw����B<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00299<BR>
     *  ================================================================================ <BR>
     * @@param l_orderUnit - (�敨OP�����P��)<BR>
     * �敨OP�����P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     */
    public void submitFuturesSettleContractStopOrder(IfoOrderUnit l_orderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " submitFuturesSettleContractStopOrder(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("�敨OP�����P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�敨OP�����P�� = null�B");
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.IFO);
        WEB3FuturesOrderManagerImpl l_orderMgr =
            (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        
        //�����œn���ꂽ�����P�ʂ͍X�V����Ă���\��������̂ŁA�Ď擾���s�����ƁB        
        try
        {
            l_orderUnit = (IfoOrderUnit) l_orderMgr.getOrderUnit(l_orderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�����P�ʃe�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        try
        {
            //1.1:is�����Ώ�(IfoOrderUnit)
            boolean l_blnIsProcessObj = this.isProcessObject(l_orderUnit);
            
            //1.2:�i����t���[�F�@@is�����Ώہ�false�i�����ΏۊO�����j�̏ꍇ�j
            if (!l_blnIsProcessObj)
            {
                //1.2.1:�����ΏۊO�̏ꍇ�A�����������̂܂�return����B
                //�i����X�e�[�^�X�ŏI���j
                log.exiting(STR_METHOD_NAME);
                return;
            }
            
            //1.3:get������(�m�F�������� : Date,����敪 : String)
            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            WEB3GentradeTradingTimeManagement.getOrderBizDate(
                WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd"),
                l_orderUnitRow.getSessionType());
             
            //1.4:getContractsToClose( )    
            if (!(l_orderUnit instanceof IfoContractSettleOrderUnit))
            {
                log.debug("�敨OP�����P�ʂ�TYPE��IfoContractSettleOrderUnit�ȊO�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    "�敨OP�����P�ʂ�TYPE��IfoContractSettleOrderUnit�ȊO�ł��B");
            }
            
            IfoContractSettleOrderUnit l_ifoContractSettleOrderUnit = 
                (IfoContractSettleOrderUnit) l_orderUnit;
            IfoClosingContractSpec[] l_contractSpecs = 
                l_ifoContractSettleOrderUnit.getContractsToClose();
            
            //1.5: ArrayList()
            List l_lisSettleContractEntries = new ArrayList();
            
            int l_intCnt = 0;
            if (l_contractSpecs != null && l_contractSpecs.length > 0)
            {
                l_intCnt = l_contractSpecs.length;
            }
            
            //1.6:getContractsToClose( )�̖߂�l�i�����ʕԍώw����j�v�f��(index)���ALoop�j
            for (int i = 0; i < l_intCnt; i++)
            {            
                IfoClosingContractSpec l_contractSpec = l_contractSpecs[i];
                //1.6.1: (*)�ԍω\���ʃ`�F�b�N            
                try
                {
                    //�ԍω\���ʃ`�F�b�N�F
                    //�P�j�ԍϐ���(*1)���擾����B
                    //    �ԍϐ��� �� ���ʕԍώw����[index].�ԍϒ�������
                    double l_dblClosingContractQuantity = l_contractSpec.getQuantity();
                    
                    //�Q�j�ԍω\���ʎc��(*2)���Z�o����B�i���ʂ́A���ʕԍώw����[index].����ID�ɊY�����錚�ʃI�u�W�F�N�g���g�p�j
                    //�@@�@@�ԍω\���ʎc�� �� ����.getQuantity()�i�����ʐ��ʁj �| ����.getLockedQuantity()�i�����b�N�����ʁj �{ 
                    //    ����.get���b�N������(�����P��ID)�i�����Y�������b�N�����ʁj
                    
                    //1.6.1.1:�敨OP����(���ʂh�c : long)
                    long l_lngContractId = l_contractSpec.getContractId();
                    WEB3IfoContractImpl l_contract = 
                        new WEB3IfoContractImpl(l_lngContractId);//DataNetworkException, DataQueryException
                    
                    //1.6.1.2: getQuantity( )
                    double l_dblContractQuantity = l_contract.getQuantity();
                    
                    //1.6.1.3:getLockedQuantity( )
                    double l_dblContractLockedQuantity = l_contract.getLockedQuantity();
                    
                    //1.6.1.4:get���b�N������(�����P�ʂh�c : long)
                    double l_dblContracLockedQuantityForOrderUnit = l_contract.getLockedQuantity(l_orderUnit.getOrderUnitId());
                    
                    double l_dblValue = l_dblContractQuantity - l_dblContractLockedQuantity + l_dblContracLockedQuantityForOrderUnit;
                    
                    //�R�j �ԍϐ���(*1) �� �ԍω\���ʎc��(*2)�@@�̏ꍇ�A�u�ԍω\�c�����ʒ��߃G���[�v�̗�O��throw����B
                    if (l_dblClosingContractQuantity > l_dblValue)
                    {
                        String l_strErrorMsg = "�ԍϐ��� �� �ԍω\���ʎc��(" + 
                            l_dblClosingContractQuantity + " > " + l_dblValue + ")";
                        log.debug(l_strErrorMsg);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00299,
                            this.getClass().getName() + STR_METHOD_NAME,
                            l_strErrorMsg);
                    }                                
                    
                    //1.6.1.5: SettleContractEntry(arg0 : long, arg1 : double)
                    SettleContractEntry l_entry = new SettleContractEntry(
                        l_lngContractId, l_dblClosingContractQuantity);
                    
                    //1.6.1.6: add(arg0 : Object)
                    l_lisSettleContractEntries.add(l_entry);
                }
                catch (DataNetworkException l_ex)
                {
                    log.error(STR_METHOD_NAME, l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataQueryException l_ex)
                {
                    log.error(STR_METHOD_NAME, l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }
            
            SettleContractEntry[] l_settleContractEntries = new SettleContractEntry[l_lisSettleContractEntries.size()];
            l_lisSettleContractEntries.toArray(l_settleContractEntries);
            
            //1.7:getSubAccount(arg0 : long, arg1 : long)
            WEB3GentradeAccountManager l_accountMgr =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            
            WEB3GentradeSubAccount l_subAccount = null;            
            try
            {
                l_subAccount = (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(
                    l_orderUnit.getAccountId(), 
                    l_orderUnit.getSubAccountId());//NotFoundException
            }
            catch (NotFoundException l_ex)
            {
                log.error("�⏕�����e�[�u���ɊY������f�[�^������܂���B", l_ex);
    
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            WEB3GentradeFinObjectManager l_finObjectMgr =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                
            //1.8: �i����t���[�F�����P��.�����ID��null�̏ꍇ�̂݁j
            Trader l_trader = null;
            if (!l_orderUnitRow.getTraderIdIsNull())
            {
                //1.8.1: getTrader(arg0 : long)
                try
                {
                    l_trader = l_finObjectMgr.getTrader(l_orderUnit.getTraderId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("���҃e�[�u���ɊY������f�[�^������܂���B", l_ex);
    
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }
            
            //1.9:create�ԍϒ������e(�،���ЃR�[�h : String, ���� : ����, 
            //�w�l : double, ���s���� : IfoOrderExecutionConditionType, 
            //���������� : Date, �ԍό��ʃG���g�� : SettleContractOrderEntry[], 
            //�������� : String, (W�w�l)�����w�l : double, ���������敪 : String, 
            //���񒍕��̒����P��ID : Long, �[��O�J�z�Ώۃt���O : boolean)                
            
            //���������敪 = �敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)
            String l_strOrderExpirationType = WEB3IfoDataAdapter.getExpirationDateType(l_orderUnit);
            //���񒍕��̒����P��ID = �����P��.���񒍕��̒����P��ID
            long l_lngFirstOrderUnitId = l_orderUnitRow.getFirstOrderUnitId();
            //�[��O�J�z�Ώۃt���O = �敨OP�f�[�^�A�_�v�^.get�[��O�J�z�Ώۃt���O�iPR�w�j(�����P��)
            boolean l_blnEveningSessionCarryoverFlag =
                WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(l_orderUnit);

            Long l_firstOrderUnitId = null;
            if (!l_orderUnitRow.getFirstOrderUnitIdIsNull())
            {
                l_firstOrderUnitId = new Long(l_lngFirstOrderUnitId);
            }
            Institution l_institution = l_subAccount.getInstitution();
            WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec = WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                l_institution.getInstitutionCode(),
                l_trader,
                l_orderUnit.getLimitPrice(),
                l_orderUnit.getExecutionConditionType(),
                l_orderUnitRow.getExpirationDate(),
                l_settleContractEntries,
                l_orderUnitRow.getOrderConditionType(),
                0,
                0,
                null,
                l_strOrderExpirationType,
                l_firstOrderUnitId,
                l_blnEveningSessionCarryoverFlag);
                
            //1.10: validate�敨�ԍϒ���(�⏕���� : SubAccount, �ԍϒ������e : IfoSettleContractOrderSpec)                
            NewOrderValidationResult l_validationResult = l_orderMgr.validateFuturesSettleContractOrder(
                l_subAccount,
                l_settleContractOrderSpec);
                
            if (l_validationResult.getProcessingResult().isFailedResult())
            {
                log.debug("validate�敨�ԍϒ��������s�̏ꍇ�B");
                    
                throw new WEB3BaseException(
                    l_validationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            //1.11:create�萔��(�����P��ID : long)
            WEB3IfoBizLogicProvider l_ifoBizLogicProvider = 
                (WEB3IfoBizLogicProvider) l_tradingModule.getBizLogicProvider();
            WEB3GentradeCommission l_commission = l_ifoBizLogicProvider.createCommission(l_orderUnit.getOrderUnitId());
            
            //1.12:get�������(�،���� : Institution, �����R�[�h : String, �s��R�[�h : String)
            //�����F�@@�����P��.����ID�ɊY������敨OP�����I�u�W�F�N�g
            WEB3IfoProductManagerImpl l_productMgr = 
                (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();
                
            WEB3IfoProductImpl l_product = null;            
            try
            {
                l_product = (WEB3IfoProductImpl) l_productMgr.getProduct(l_orderUnitRow.getProductId());//NotFoundException
            }
            catch (NotFoundException l_ex)
            {
                log.error("�����e�[�u���ɊY������f�[�^������܂���B", l_ex);
    
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            //�s��R�[�h�F�@@�����P��.�s��ID�ɊY������s��I�u�W�F�N�g.�s��R�[�h
            String l_strMarketCode = null;        
            if (!l_orderUnitRow.getMarketIdIsNull())
            {
                try
                {
                    Market l_market = l_finObjectMgr.getMarket(l_orderUnitRow.getMarketId());//NotFoundException
                    l_strMarketCode = l_market.getMarketCode();
                }
                catch (NotFoundException l_ex)
                {
                    log.error("�s��e�[�u���ɊY������f�[�^������܂���B", l_ex);
    
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }                
            
            WEB3IfoTradedProductImpl l_tradedProduct = null;
            try
            {
                l_tradedProduct = l_productMgr.getIfoTradedProduct(
                    l_institution,
                    l_product.getProductCode(),
                    l_strMarketCode);//NotFoundException
            }
            catch (NotFoundException l_ex)
            {
                log.error("��������e�[�u���ɊY������f�[�^������܂���B", l_ex);
    
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            //1.13:calc�T�Z���ϑ��v(�萔�� : �萔��, �w�l : double, �⏕���� : SubAccount, 
            //�敨OP������� : �敨OP�������, �ԍό��ʃG���g��[] : SettleContractEntry[], 
            //���� : double, ���� : SideEnum, isSkip���z�`�F�b�N : boolean)
            
            //����:        
            //�����P��.getSide()��SideEnum.BUY(��)�i=�������ԍρj�̏ꍇ�A�h���h���Z�b�g�B
            //�����P��.getSide()��SideEnum.SELL(��)�i=�������ԍρj�̏ꍇ�A�h���h���Z�b�g�B
            SideEnum l_side = null;        
            if (SideEnum.BUY.equals(l_orderUnit.getSide()))
            {
                l_side = SideEnum.SELL;
            }
            else if (SideEnum.SELL.equals(l_orderUnit.getSide()))
            {
                l_side = SideEnum.BUY;
            }
            WEB3IfoEstimateDeliveryAmountCalcResult l_caclResult = 
                l_orderMgr.calcEstimateSettlementIncome(
                    l_commission,
                    l_settleContractOrderSpec.getLimitPrice(),
                    l_subAccount,
                    l_tradedProduct,
                    l_settleContractEntries,
                    l_settleContractOrderSpec.getTotalQuantity(),
                    l_side,
                    false);
                    
            //1.14:(���s���ʂɉ����Ē����n�f�[�^��UPDATE����)
            //1.14.1:(*)����I�������ꍇ
            //1.14.1.1:update�����f�[�^(IfoOrderUnit, double, double)
            WEB3IfoFrontOrderService l_ifoOrderService = (WEB3IfoFrontOrderService) Services
                .getService(WEB3IfoFrontOrderService.class);
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch) l_accountMgr.getBranch(l_orderUnitRow.getBranchId());
            WEB3GentradeMarket l_market = (WEB3GentradeMarket) l_finObjectMgr.getMarket(l_orderUnitRow.getMarketId());

            this.updateOrderData(
                l_orderUnit,
                l_caclResult.getCalcUnitPrice(),
                l_caclResult.getEstimateDeliveryAmount(),
                l_ifoOrderService.getSubmitOrderRouteDiv(l_branch.getInstitution().getInstitutionCode(),
                l_market.getMarketCode()));

            //1.14.1.2:insert�ԍϒ����L���[(����ID : long)
            l_orderMgr.insertSettleContractHostOrder(l_orderUnit.getOrderId());
        
            //1.14.1.3:sendMQ�g���K(IfoOrderUnit)
            try
            {
                l_orderUnit = (IfoOrderUnit) l_orderMgr.getOrderUnit(l_orderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("�����P�ʃe�[�u���ɊY������f�[�^������܂���B", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        
            this.sendMQTrigger(l_orderUnit);
        }
        //1.14.2:(*)�������ɗ�O���X���[���ꂽ�ꍇ
        catch (Exception l_ex)
        {
            //1.14.2.1:get�����G���[���R�R�[�h(ErrorInfo)
            log.error(l_ex.getMessage(), l_ex);
            ErrorInfo l_errorInfo = null;
            if (l_ex instanceof WEB3BaseException)
            {
                l_errorInfo = ((WEB3BaseException)l_ex).getErrorInfo();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_errorInfo = ((WEB3BaseRuntimeException)l_ex).getErrorInfo();
            }
            String l_errorReasonCode = l_orderMgr.getErrorReasonCode(l_errorInfo.getErrorCode());

            //1.14.2.2:�t�w�l�����敨OP�����X�V�C���^�Z�v�^(�����G���[���R�R�[�h : String)
            WEB3ToStopIfoOrderUpdateInterceptor l_updateInterceptor = 
                new WEB3ToStopIfoOrderUpdateInterceptor(l_errorReasonCode);

            //1.14.2.3:setThreadLocalPersistenceEventInterceptor(arg0 : IfoOrderManagerPersistenceEventInterceptor)
            l_orderMgr.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
            
            //1.14.2.4:DefaultOrderInvalidatedMarketResponseMessage(arg0 : long)
            DefaultOrderInvalidatedMarketResponseMessage l_responseMessage = 
                new DefaultOrderInvalidatedMarketResponseMessage(l_orderUnit.getOrderId());
                
            //1.14.2.5:process(arg0 : OrderInvalidatedMarketResponseMessage)
            MarketAdapter l_markertAdapter = l_tradingModule.getMarketAdapter();
                            
            IfoMarketResponseReceiverCallbackService l_callBackService =
                (IfoMarketResponseReceiverCallbackService) l_markertAdapter.getMarketResponseReceiverCallbackService();            
            
            try
            {
                ProcessingResult l_result = l_callBackService.process(l_responseMessage);
                if (l_result.isFailedResult())
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        l_result.getErrorInfo(),
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
            catch (Exception l_ex2)
            {
                String l_strErrMsg = "�����R���G���[�ƂȂ����t�w�l�����̎��������Ɏ��s���܂����B����ID�F[" + l_orderUnit.getOrderId() + "]";
                log.error(l_strErrMsg, l_ex2);
                if (l_errorInfo != null)
                {
                    if (l_errorInfo.error_tag.startsWith("SYSTEM_"))
                    {
                        throw new WEB3SystemLayerException(
                            l_errorInfo,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_strErrMsg);
                    }
                    else
                    {
                        throw new WEB3BusinessLayerException(
                            l_errorInfo,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_strErrMsg);
                    }
                }
                else
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_strErrMsg);
                }
            }

            // is�\�񒍕��m�F�v(�����P�� : IfoOrderUnit)
            //�����P�ʁF�@@�����P��
            boolean l_blnIsReserveOrderExist = l_orderMgr.isReserveOrderExist(l_orderUnit);

            //�\�񒍕��m�F�v�iis�\�񒍕��m�F�v() == true�j�̏ꍇ
            if (l_blnIsReserveOrderExist)
            {
                //invalidateAll�\�񒍕��P��(long)
                //�e�����̒���ID�F�@@�����P��.����ID
                WEB3ToSuccReservationIfoOrderUpdateService l_updateService =
                    (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                        WEB3ToSuccReservationIfoOrderUpdateService.class);

                l_updateService.invalidateAllOrderUnit(l_orderUnit.getOrderId());
            }
        }   
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submitOP�V�K���t�w�l����)<BR>
     * �I�v�V�����V�K���t�w�l�����𔭒�����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�t�w�l�����敨OP�����ꌏ�T�[�r�X�jsubmitOP�V�K���t�w�l�����v�Q�ƁB<BR>
     * @@param l_orderUnit - (�敨OP�����P��)<BR>
     * �敨OP�����P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     */
    public void submitOptionOpenContractStopOrder(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " submitOptionOpenContractStopOrder(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("�敨OP�����P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�敨OP�����P�� = null�B");
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderMgr =
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            
        WEB3GentradeAccountManager l_accountMgr =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        WEB3GentradeSubAccount l_subAccount = null;            

        //�����œn���ꂽ�����P�ʂ͍X�V����Ă���\��������̂ŁA�Ď擾���s�����ƁB        
        try
        {
            l_orderUnit = (IfoOrderUnit) l_orderMgr.getOrderUnit(l_orderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�����P�ʃe�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        try
        {     
			//getSubAccount(arg0 : long, arg1 : long)
			try
			{
				l_subAccount = (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(
					l_orderUnit.getAccountId(), 
					l_orderUnit.getSubAccountId());//NotFoundException
			}
			catch (NotFoundException l_ex)
			{
				log.error("�⏕�����e�[�u���ɊY������f�[�^������܂���B", l_ex);
    
				log.exiting(STR_METHOD_NAME);
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80005,
					this.getClass().getName() + STR_METHOD_NAME,
					l_ex.getMessage(),
					l_ex);
			}
            //1.1:is�����Ώ�(IfoOrderUnit)
            boolean l_blnIsProcessObj = this.isProcessObject(l_orderUnit);
            
            //1.2:�i����t���[�F�@@is�����Ώہ�false�i�����ΏۊO�����j�̏ꍇ�j
            if (!l_blnIsProcessObj)
            {
                //1.2.1:�����ΏۊO�̏ꍇ�A�����������̂܂�return����B
                //�i����X�e�[�^�X�ŏI���j
                return;
            }
            
            //1.3:get������(�m�F�������� : Date,����敪 : String)
            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            WEB3GentradeTradingTimeManagement.getOrderBizDate(
                WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd"),
                l_orderUnitRow.getSessionType());
                
            
            WEB3GentradeFinObjectManager l_finObjectMgr =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                
            //1.5: �i����t���[�F�����P��.�����ID��null�̏ꍇ�̂݁j
            Trader l_trader = null;
            if (!l_orderUnitRow.getTraderIdIsNull())
            {
                //1.5.1: getTrader(arg0 : long)
                try
                {
                    l_trader = l_finObjectMgr.getTrader(l_orderUnit.getTraderId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("���҃e�[�u���ɊY������f�[�^������܂���B", l_ex);
    
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }
            
            //1.6:create�V�K���������e(�،���ЃR�[�h : String, ���� : ����, 
            //is���� : boolean, �s��R�[�h : String, ���� : �敨OP����, 
            //���� : double, �w�l : double, ���s���� : IfoOrderExecutionConditionType,
            // ���������� : Date, �������� : String, (W�w�l)�����w�l : double, ���������敪 : 
            //String, ���񒍕��̒����P��ID : Long, �[��O�J�z�Ώۃt���O : boolean)
            
            //is�����F�@@
            //  �����P��.getSide()��SideEnum.BUY(��)�̏ꍇ�Atrue���Z�b�g�B
            //  �����P��.getSide()��SideEnum.SELL(��)�̏ꍇ�Afalse���Z�b�g�B
            boolean l_blnIsBuy = false;
            
            if (SideEnum.BUY.equals(l_orderUnit.getSide()))
            {
                l_blnIsBuy = true;
            }
            
            //�s��R�[�h�F�@@�����P��.�s��ID�ɊY������s��I�u�W�F�N�g.�s��R�[�h
            String l_strMarketCode = null;
            
            if (!l_orderUnitRow.getMarketIdIsNull())
            {
                try
                {
                    Market l_market = l_finObjectMgr.getMarket(l_orderUnitRow.getMarketId());//NotFoundException
                    l_strMarketCode = l_market.getMarketCode();
                }
                catch (NotFoundException l_ex)
                {
                    log.error("�s��e�[�u���ɊY������f�[�^������܂���B", l_ex);
    
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }
            
            //�����F�@@�����P��.����ID�ɊY������敨OP�����I�u�W�F�N�g
            WEB3IfoProductManagerImpl l_productMgr = 
                (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();
            WEB3IfoProductImpl l_product = null;
                
            try
            {
                l_product = (WEB3IfoProductImpl) l_productMgr.getProduct(l_orderUnitRow.getProductId());//NotFoundException
            }
            catch (NotFoundException l_ex)
            {
                log.error("�����e�[�u���ɊY������f�[�^������܂���B", l_ex);
    
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //���������敪 = �敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)
            String l_strOrderExpirationType = WEB3IfoDataAdapter.getExpirationDateType(l_orderUnit);
            //���񒍕��̒����P��ID = �����P��.���񒍕��̒����P��ID
            long l_lngFirstOrderUnitId = l_orderUnitRow.getFirstOrderUnitId();
            //�[��O�J�z�Ώۃt���O = �敨OP�f�[�^�A�_�v�^.get�[��O�J�z�Ώۃt���O�iPR�w�j(�����P��)
            boolean l_blnEveningSessionCarryoverFlag =
                WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(l_orderUnit);

            Long l_firstOrderUnitId = null;
            if (!l_orderUnitRow.getFirstOrderUnitIdIsNull())
            {
                l_firstOrderUnitId = new Long(l_lngFirstOrderUnitId);
            }
            Institution l_institution = l_subAccount.getInstitution();
            WEB3IfoOpenContractOrderSpec l_openContractOrderSpec = WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                l_institution.getInstitutionCode(),
                l_trader,
                l_blnIsBuy,
                l_strMarketCode,
                l_product,
                l_orderUnit.getQuantity(), 
                l_orderUnit.getLimitPrice(),
                l_orderUnit.getExecutionConditionType(),
                l_orderUnitRow.getExpirationDate(),
                l_orderUnitRow.getOrderConditionType(),
                0,
                0,
                null,
                l_strOrderExpirationType,
                l_firstOrderUnitId,
                l_blnEveningSessionCarryoverFlag);
                
            //1.7:validate�V�K�������̔����R�����s���B(�⏕���� : SubAccount, �V�K���������e : IfoOpenContractOrderSpec, �����P�� : IfoOrderUnit)                
            NewOrderValidationResult l_validationResult = l_orderMgr.validateOpenContractOrder(
                l_subAccount,
                l_openContractOrderSpec,
                l_orderUnit);
                
            if (l_validationResult.getProcessingResult().isFailedResult())
            {
                log.debug("validate�I�v�V�����V�K�����������s�̏ꍇ�B");
                    
                throw new WEB3BaseException(
                    l_validationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            //1.8:create�萔��(�����P��ID : long)
            WEB3IfoBizLogicProvider l_ifoBizLogicProvider = 
                (WEB3IfoBizLogicProvider) l_tradingModule.getBizLogicProvider();
            WEB3GentradeCommission l_commission = l_ifoBizLogicProvider.createCommission(l_orderUnit.getOrderUnitId());
            
            //1.9:get�������(�،���� : Institution, �����R�[�h : String, �s��R�[�h : String)
            WEB3IfoTradedProductImpl l_tradedProduct = null;
            try
            {
                l_tradedProduct = l_productMgr.getIfoTradedProduct(
                    l_institution,
                    l_product.getProductCode(),
                    l_strMarketCode);//NotFoundException
            }
            catch (NotFoundException l_ex)
            {
                log.error("��������e�[�u���ɊY������f�[�^������܂���B", l_ex);
    
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            //1.10:�T�Z��n������v�Z����B
            WEB3IfoEstimateDeliveryAmountCalcResult l_caclAmountCalcResult = 
                l_orderMgr.calcEstimateDeliveryAmount(
                    l_commission,
                    l_openContractOrderSpec.getLimitPrice(),
                    l_subAccount,
                    l_tradedProduct,
                    l_openContractOrderSpec.getQuantity(),
                    l_orderUnit.getSide(),
                    false,
                    false);

            //1.11:create�V�K���������e(�����P�� : IfoOrderUnit)
            WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec = this.createOpenContractChangeSpec(l_orderUnit);

            //1.12:�敨OP�V�K�������X�V�C���^�Z�v�^(�V�K���������e : �V�K���������e)
            WEB3IfoOpenContractChangeUpdateInterceptor l_ifoOpenContractChangeUpdateInterceptor
                = new WEB3IfoOpenContractChangeUpdateInterceptor(l_ifoOpenContractChangeSpec);

            //1.13:�v���p�e�B�Z�b�g
            //�萔�����Z�b�g����
            l_ifoOpenContractChangeUpdateInterceptor.setCommision(l_commission);

            //�T�Z��n����v�Z���ʂ��Z�b�g����
            l_ifoOpenContractChangeUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_caclAmountCalcResult);

            //�����������Z�b�g����
            l_ifoOpenContractChangeUpdateInterceptor.setOrderCond(l_orderUnitRow.getOrderConditionType());

            //�����������Z�q���Z�b�g����
            l_ifoOpenContractChangeUpdateInterceptor.setOrderCondOperator(l_orderUnitRow.getOrderCondOperator());

            //�t�w�l��l�^�C�v���Z�b�g����
            l_ifoOpenContractChangeUpdateInterceptor.setStopOrderBasePriceType(l_orderUnitRow.getStopPriceType());

            //�t�w�l��l���Z�b�g����
            l_ifoOpenContractChangeUpdateInterceptor.setStopOrderBasePrice(l_orderUnitRow.getStopOrderPrice());

            //�iW�w�l�j�����w�l
            double l_wLimitPrice = 0D;
            l_ifoOpenContractChangeUpdateInterceptor.setWLimitPriceChange(l_wLimitPrice);

            //�����ID
            long l_traderId = 0L;
            if (l_trader != null)
            {
                l_traderId = l_trader.getTraderId();
            }
            l_ifoOpenContractChangeUpdateInterceptor.setTraderId(l_traderId);
			
			//�����o�H�敪���Z�b�g����
			l_ifoOpenContractChangeUpdateInterceptor.setOrderRootDiv(l_orderUnitRow.getOrderRootDiv());			

            WEB3TPTradingPowerService l_tradingPowerService = 
                (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

            Object[] l_interceptorObject = new Object[1];
            l_interceptorObject[0] = l_ifoOpenContractChangeUpdateInterceptor;

            Object[] l_specObject = new Object[1];
            l_specObject[0] = l_ifoOpenContractChangeSpec;

            //1.14:validate����]��(�⏕���� : �⏕����, �������e�C���^�Z�v�^ : Object[], �������e : Object[], ������� : OrderTypeEnum, �]�͍X�V�t���O : boolean)
            WEB3TPTradingPowerResult l_result = 
                l_tradingPowerService.validateTradingPower
                (l_subAccount,
                 l_interceptorObject,
                 l_specObject,
                 l_orderUnitRow.getOrderType(),
                 true);

            //1.15:throw�]�̓G���[���(����]�͌��� : ����]�͌���, �⏕���� : �⏕����)(�敨�����}�l�[�W��::throw�]�̓G���[���)
            l_orderMgr.throwTpErrorInfo(l_result,l_subAccount);

            //1.16:(���s���ʂɉ����Ē����n�f�[�^��UPDATE����)
            //1.16.1:(*)����I�������ꍇ

            // 1.16.1.1:update�����f�[�^(IfoOrderUnit, double, double)
            WEB3IfoFrontOrderService l_ifoOrderService = (WEB3IfoFrontOrderService) Services
                .getService(WEB3IfoFrontOrderService.class);
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch) l_accountMgr.getBranch(l_orderUnitRow.getBranchId());
            WEB3GentradeMarket l_market = (WEB3GentradeMarket) l_finObjectMgr.getMarket(l_orderUnitRow.getMarketId());

            this.updateOrderData(l_orderUnit,
                l_caclAmountCalcResult.getCalcUnitPrice(),
                l_caclAmountCalcResult.getEstimateDeliveryAmount(),
                l_ifoOrderService.getSubmitOrderRouteDiv(l_branch.getInstitution()
                .getInstitutionCode(), l_market.getMarketCode()));

            // 1.16.1.2:insert�V�K�������L���[(����ID : long)
            l_orderMgr.insertOpenContractHostOrder(l_orderUnit.getOrderId());
        
            //1.16.1.3:sendMQ�g���K(IfoOrderUnit)
            try
            {
                l_orderUnit = (IfoOrderUnit) l_orderMgr.getOrderUnit(l_orderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("�����P�ʃe�[�u���ɊY������f�[�^������܂���B", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        
            this.sendMQTrigger(l_orderUnit);
        }
        //1.16.2:(*)�������ɗ�O���X���[���ꂽ�ꍇ
        catch (Exception l_ex)
        {
            //1.16.2.1:get�����G���[���R�R�[�h(ErrorInfo)
            log.error(l_ex.getMessage(), l_ex);
            ErrorInfo l_errorInfo = null;
            if (l_ex instanceof WEB3BaseException)
            {
                l_errorInfo = ((WEB3BaseException)l_ex).getErrorInfo();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_errorInfo = ((WEB3BaseRuntimeException)l_ex).getErrorInfo();
            }
            String l_errorReasonCode = l_orderMgr.getErrorReasonCode(l_errorInfo.getErrorCode());

            //1.16.2.2:�t�w�l�����敨OP�����X�V�C���^�Z�v�^(�����G���[���R�R�[�h : String) 
            WEB3ToStopIfoOrderUpdateInterceptor l_updateInterceptor = 
                new WEB3ToStopIfoOrderUpdateInterceptor(l_errorReasonCode);

            //1.16.2.3:setThreadLocalPersistenceEventInterceptor(arg0 : IfoOrderManagerPersistenceEventInterceptor)
            l_orderMgr.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
            
            //1.16.2.4:DefaultOrderInvalidatedMarketResponseMessage(arg0 : long)
            DefaultOrderInvalidatedMarketResponseMessage l_responseMessage = 
                new DefaultOrderInvalidatedMarketResponseMessage(l_orderUnit.getOrderId());
                
            //1.16.2.5:process(arg0 : OrderInvalidatedMarketResponseMessage)
            MarketAdapter l_markertAdapter = l_tradingModule.getMarketAdapter();
                            
            IfoMarketResponseReceiverCallbackService l_callBackService =
                (IfoMarketResponseReceiverCallbackService) l_markertAdapter.getMarketResponseReceiverCallbackService();            
            
            try
            {
                ProcessingResult l_result = l_callBackService.process(l_responseMessage);
                if (l_result.isFailedResult())
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        l_result.getErrorInfo(),
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
            catch (Exception l_ex2)
            {
                String l_strErrMsg = "�����R���G���[�ƂȂ����t�w�l�����̎��������Ɏ��s���܂����B����ID�F[" + l_orderUnit.getOrderId() + "]";
                log.error(l_strErrMsg, l_ex2);
                if (l_errorInfo != null)
                {
                    if (l_errorInfo.error_tag.startsWith("SYSTEM_"))
                    {
                        throw new WEB3SystemLayerException(
                            l_errorInfo,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_strErrMsg);
                    }
                    else
                    {
                        throw new WEB3BusinessLayerException(
                            l_errorInfo,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_strErrMsg);
                    }
                }
                else
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_strErrMsg);
                }
            }

            // is�\�񒍕��m�F�v(�����P�� : IfoOrderUnit)
            //�����P�ʁF�@@�����P��
            boolean l_blnIsReserveOrderExist = l_orderMgr.isReserveOrderExist(l_orderUnit);

            //�\�񒍕��m�F�v�iis�\�񒍕��m�F�v() == true�j�̏ꍇ
            if (l_blnIsReserveOrderExist)
            {
                //invalidateAll�\�񒍕��P��(long)
                //�e�����̒���ID�F�@@�����P��.����ID
                WEB3ToSuccReservationIfoOrderUpdateService l_updateService =
                    (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                        WEB3ToSuccReservationIfoOrderUpdateService.class);

                l_updateService.invalidateAllOrderUnit(l_orderUnit.getOrderId());
            }

            //1.16.2.6:�I�v�V�������������i�⏕����.�⏕�����^�C�v��"�����I�v�V������������i�敨�؋����j�j�̏ꍇ
            if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
            {
                //1.16.2.6.1:�]�͍Čv�Z(�⏕���� : �⏕����)(����]�̓T�[�r�X::�]�͍Čv�Z)
                WEB3TPTradingPowerService l_tpTradingPowerService =
                    (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);
                        l_tpTradingPowerService.reCalcTradingPower(l_subAccount);
            }
        }  

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submitOP�ԍϋt�w�l����)<BR>
     * �I�v�V�����ԍϋt�w�l�����𔭒�����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�t�w�l�����敨OP�����ꌏ�T�[�r�X�jsubmit�I�v�V�����ԍϋt�w�l�����v�Q�ƁB<BR>
     *  ================================================================================ <BR>
     * 1.6.1�ԍω\���ʃ`�F�b�N�F�@@<BR>
     * �R�j �ԍϐ���(*1) �� �ԍω\���ʎc��(*2)�@@�̏ꍇ�A�u�ԍω\�c�����ʒ��߃G���[�v<BR>
     * �̗�O��throw����B<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00299<BR>
     *  ================================================================================ <BR>
     * @@param l_orderUnit - (�敨OP�����P��)<BR>
     * �敨OP�����P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     */
    public void submitOptionSettleContractStopOrder(IfoOrderUnit l_orderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " submitOptionSettleContractStopOrder(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("�敨OP�����P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�敨OP�����P�� = null�B");
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderMgr =
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();

        WEB3GentradeAccountManager l_accountMgr =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        WEB3GentradeSubAccount l_subAccount = null;            

        //�����œn���ꂽ�����P�ʂ͍X�V����Ă���\��������̂ŁA�Ď擾���s�����ƁB        
        try
        {
            l_orderUnit = (IfoOrderUnit) l_orderMgr.getOrderUnit(l_orderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�����P�ʃe�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        try
        {
			//getSubAccount(arg0 : long, arg1 : long)
			try
			{
				l_subAccount = (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(
					l_orderUnit.getAccountId(), 
					l_orderUnit.getSubAccountId());//NotFoundException
			}
			catch (NotFoundException l_ex)
			{
				log.error("�⏕�����e�[�u���ɊY������f�[�^������܂���B", l_ex);
    
				log.exiting(STR_METHOD_NAME);
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80005,
					this.getClass().getName() + STR_METHOD_NAME,
					l_ex.getMessage(),
					l_ex);
			}
            
            //1.1:is�����Ώ�(IfoOrderUnit)
            boolean l_blnIsProcessObj = this.isProcessObject(l_orderUnit);
            
            //1.2:�i����t���[�F�@@is�����Ώہ�false�i�����ΏۊO�����j�̏ꍇ�j
            if (!l_blnIsProcessObj)
            {
                //1.2.1:�����ΏۊO�̏ꍇ�A�����������̂܂�return����B
                //�i����X�e�[�^�X�ŏI���j
                return;
            }
            
            //1.3:get������(�m�F�������� : Date,����敪 : String)
            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            WEB3GentradeTradingTimeManagement.getOrderBizDate(
                WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd"),
                l_orderUnitRow.getSessionType());
             
            //1.4:getContractsToClose( )   
            if (!(l_orderUnit instanceof IfoContractSettleOrderUnit))
            {
                log.debug("�敨OP�����P�ʂ�TYPE��IfoContractSettleOrderUnit�ȊO�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    "�敨OP�����P�ʂ�TYPE��IfoContractSettleOrderUnit�ȊO�ł��B");
            }
            IfoContractSettleOrderUnit l_ifoContractSettleOrderUnit = 
                (IfoContractSettleOrderUnit) l_orderUnit;
            IfoClosingContractSpec[] l_contractSpecs = 
                l_ifoContractSettleOrderUnit.getContractsToClose();
            
            //1.5: ArrayList()
            List l_lisSettleContractEntries = new ArrayList();
            
            int l_intCnt = 0;
            if (l_contractSpecs != null && l_contractSpecs.length > 0)
            {
                l_intCnt = l_contractSpecs.length;
            }
            
            //1.6:getContractsToClose( )�̖߂�l�i�����ʕԍώw����j�v�f��(index)���ALoop�j
            for (int i = 0; i < l_intCnt; i++)
            {            
                IfoClosingContractSpec l_contractSpec = l_contractSpecs[i];
                //1.6.1: (*)�ԍω\���ʃ`�F�b�N            
                try
                {
                    //�ԍω\���ʃ`�F�b�N�F
                    //�P�j�ԍϐ���(*1)���擾����B
                    //    �ԍϐ��� �� ���ʕԍώw����[index].�ԍϒ�������
                    double l_dblClosingContractQuantity = l_contractSpec.getQuantity();
                    
                    //�Q�j�ԍω\���ʎc��(*2)���Z�o����B�i���ʂ́A���ʕԍώw����[index].����ID�ɊY�����錚�ʃI�u�W�F�N�g���g�p�j
                    //�@@�@@�ԍω\���ʎc�� �� ����.getQuantity()�i�����ʐ��ʁj �| ����.getLockedQuantity()�i�����b�N�����ʁj �{ 
                    //����.get���b�N������(�����P��ID)�i�����Y�������b�N�����ʁj
                    
                    //1.6.1.1:�敨OP����(���ʂh�c : long)
                    long l_lngContractId = l_contractSpec.getContractId();
                    WEB3IfoContractImpl l_contract = 
                        new WEB3IfoContractImpl(l_lngContractId);//DataNetworkException, DataQueryException
                    
                    //1.6.1.2: getQuantity( )
                    double l_dblContractQuantity = l_contract.getQuantity();
                    
                    //1.6.1.3:getLockedQuantity( )
                    double l_dblContractLockedQuantity = l_contract.getLockedQuantity();
                    
                    //1.6.1.4:get���b�N������(�����P�ʂh�c : long)
                    double l_dblContracLockedQuantityForOrderUnit = l_contract.getLockedQuantity(l_orderUnit.getOrderUnitId());
                    
                    double l_dblValue = l_dblContractQuantity - l_dblContractLockedQuantity + l_dblContracLockedQuantityForOrderUnit;
                    
                    //�R�j �ԍϐ���(*1) �� �ԍω\���ʎc��(*2)�@@�̏ꍇ�A�u�ԍω\�c�����ʒ��߃G���[�v�̗�O��throw����B
                    if (l_dblClosingContractQuantity > l_dblValue)
                    {
                        String l_strErrorMsg = "�ԍϐ��� �� �ԍω\���ʎc��, " + 
                            l_dblClosingContractQuantity + " > " + l_dblValue;
                        log.debug(l_strErrorMsg);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00299,
                            this.getClass().getName() + STR_METHOD_NAME,
                            l_strErrorMsg);
                    }                                
                    
                    //1.6.1.5: SettleContractEntry(arg0 : long, arg1 : double)
                    SettleContractEntry l_settleContractEntry = new SettleContractEntry(
                        l_lngContractId, l_dblClosingContractQuantity);
                    
                    //1.6.1.6: add(arg0 : Object)
                    l_lisSettleContractEntries.add(l_settleContractEntry);
                }
                catch (DataNetworkException l_ex)
                {
                    log.error(STR_METHOD_NAME, l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataQueryException l_ex)
                {
                    log.error(STR_METHOD_NAME, l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }
            
            SettleContractEntry[] l_settleContractEntries = new SettleContractEntry[l_lisSettleContractEntries.size()];
            l_lisSettleContractEntries.toArray(l_settleContractEntries);
            
            WEB3GentradeFinObjectManager l_finObjectMgr =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                
            //1.8: �i����t���[�F�����P��.�����ID��null�̏ꍇ�̂݁j
            Trader l_trader = null;
            if (!l_orderUnitRow.getTraderIdIsNull())
            {
                //1.8.1: getTrader(arg0 : long)
                try
                {
                    l_trader = l_finObjectMgr.getTrader(l_orderUnit.getTraderId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("���҃e�[�u���ɊY������f�[�^������܂���B", l_ex);
    
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }
            
            //1.9:create�ԍϒ������e(�،���ЃR�[�h : String, ���� : ����, 
            //�w�l : double, ���s���� : IfoOrderExecutionConditionType, 
            //���������� : Date, �ԍό��ʃG���g�� : SettleContractOrderEntry[], 
            //�������� : String, (W�w�l)�����w�l : double, ���������敪 : String, 
            //���񒍕��̒����P��ID : Long, �[��O�J�z�Ώۃt���O : boolean)                

            //���������敪 = �敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)
            String l_strOrderExpirationType = WEB3IfoDataAdapter.getExpirationDateType(l_orderUnit);
            //���񒍕��̒����P��ID = �����P��.���񒍕��̒����P��ID
            long l_lngFirstOrderUnitId = l_orderUnitRow.getFirstOrderUnitId();
            //�[��O�J�z�Ώۃt���O = �敨OP�f�[�^�A�_�v�^.get�[��O�J�z�Ώۃt���O�iPR�w�j(�����P��)
            boolean l_blnEveningSessionCarryoverFlag =
                WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(l_orderUnit);

            Long l_firstOrderUnitId = null;
            if (!l_orderUnitRow.getFirstOrderUnitIdIsNull())
            {
                l_firstOrderUnitId = new Long(l_lngFirstOrderUnitId);
            }
            Institution l_institution = l_subAccount.getInstitution();
            WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec = WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                l_institution.getInstitutionCode(),
                l_trader,
                l_orderUnit.getLimitPrice(),
                l_orderUnit.getExecutionConditionType(),
                l_orderUnitRow.getExpirationDate(),
                l_settleContractEntries,
                l_orderUnitRow.getOrderConditionType(),
                0,
                0,
                null,
                l_strOrderExpirationType,
                l_firstOrderUnitId,
                l_blnEveningSessionCarryoverFlag);
                
            //1.10: validate�ԍϒ���(�⏕���� : SubAccount, �ԍϒ������e : IfoSettleContractOrderSpec)                
            NewOrderValidationResult l_validationResult = l_orderMgr.validateSettleContractOrder(
                l_subAccount,
                l_settleContractOrderSpec);
                
            if (l_validationResult.getProcessingResult().isFailedResult())
            {
                log.debug("validate�I�v�V�����ԍϒ��������s�̏ꍇ�B");
                    
                throw new WEB3BaseException(
                    l_validationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            //1.11:create�萔��(�����P��ID : long)
            WEB3IfoBizLogicProvider l_ifoBizLogicProvider = 
                (WEB3IfoBizLogicProvider) l_tradingModule.getBizLogicProvider();
            WEB3GentradeCommission l_commission = l_ifoBizLogicProvider.createCommission(l_orderUnit.getOrderUnitId());
            
            //1.12:get�������(�،���� : Institution, �����R�[�h : String, �s��R�[�h : String)
            //�s��R�[�h�F�@@�����P��.�s��ID�ɊY������s��I�u�W�F�N�g.�s��R�[�h
            //�����F�@@�����P��.����ID�ɊY������敨OP�����I�u�W�F�N�g
            WEB3IfoProductManagerImpl l_productMgr = 
                (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();
                
            WEB3IfoProductImpl l_product = null;            
            try
            {
                l_product = (WEB3IfoProductImpl) l_productMgr.getProduct(l_orderUnitRow.getProductId());//NotFoundException
            }
            catch (NotFoundException l_ex)
            {
                log.error("�����e�[�u���ɊY������f�[�^������܂���B", l_ex);
    
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            String l_strMarketCode = null;        
            if (!l_orderUnitRow.getMarketIdIsNull())
            {
                try
                {
                    Market l_market = l_finObjectMgr.getMarket(l_orderUnitRow.getMarketId());//NotFoundException
                    l_strMarketCode = l_market.getMarketCode();
                }
                catch (NotFoundException l_ex)
                {
                    log.error("�s��e�[�u���ɊY������f�[�^������܂���B", l_ex);
    
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }                
            
            WEB3IfoTradedProductImpl l_tradedProduct = null;
            try
            {
                l_tradedProduct = l_productMgr.getIfoTradedProduct(
                    l_institution,
                    l_product.getProductCode(),
                    l_strMarketCode);//NotFoundException
            }
            catch (NotFoundException l_ex)
            {
                log.error("��������e�[�u���ɊY������f�[�^������܂���B", l_ex);
    
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            //1.13:�T�Z��n������v�Z����B        
    
            WEB3IfoEstimateDeliveryAmountCalcResult l_caclAmountCalcResult = 
                l_orderMgr.calcEstimateDeliveryAmount(
                    l_commission,
                    l_settleContractOrderSpec.getLimitPrice(),
                    l_subAccount,
                    l_tradedProduct,
                    l_settleContractOrderSpec.getTotalQuantity(),
                    l_orderUnit.getSide(),
                    true,
                    false);
                    
            //1.14:(���s���ʂɉ����Ē����n�f�[�^��UPDATE����)
            //1.14.1:(*)����I�������ꍇ 
            //1.14.1.1:update�����f�[�^(IfoOrderUnit, double, double)
            WEB3IfoFrontOrderService l_ifoOrderService = (WEB3IfoFrontOrderService) Services
                .getService(WEB3IfoFrontOrderService.class);
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch) l_accountMgr.getBranch(l_orderUnitRow.getBranchId());
            WEB3GentradeMarket l_market = (WEB3GentradeMarket) l_finObjectMgr.getMarket(l_orderUnitRow.getMarketId());

            this.updateOrderData(l_orderUnit,
                l_caclAmountCalcResult.getCalcUnitPrice(),
                l_caclAmountCalcResult.getEstimateDeliveryAmount(),
                l_ifoOrderService.getSubmitOrderRouteDiv(l_branch.getInstitution()
                .getInstitutionCode(), l_market.getMarketCode()));

            // 1.14.1.2:insert�ԍϒ����L���[(����ID : long)
            l_orderMgr.insertSettleContractHostOrder(l_orderUnit.getOrderId());
        
            //1.11.1.3:sendMQ�g���K(IfoOrderUnit)
            try
            {
                l_orderUnit = (IfoOrderUnit) l_orderMgr.getOrderUnit(l_orderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("�����P�ʃe�[�u���ɊY������f�[�^������܂���B", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        
            this.sendMQTrigger(l_orderUnit);
        }
        //1.14.2:(*)�������ɗ�O���X���[���ꂽ�ꍇ
        catch (Exception l_ex)
        {
            //1.14.2.1:get�����G���[���R�R�[�h(ErrorInfo)
            log.error(l_ex.getMessage(), l_ex);
            ErrorInfo l_errorInfo = null;
            if (l_ex instanceof WEB3BaseException)
            {
                l_errorInfo = ((WEB3BaseException)l_ex).getErrorInfo();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_errorInfo = ((WEB3BaseRuntimeException)l_ex).getErrorInfo();
            }
            String l_errorReasonCode = l_orderMgr.getErrorReasonCode(l_errorInfo.getErrorCode());

            //1.14.2.2:�t�w�l�����敨OP�����X�V�C���^�Z�v�^(�����G���[���R�R�[�h : String) 
            WEB3ToStopIfoOrderUpdateInterceptor l_updateInterceptor = 
                new WEB3ToStopIfoOrderUpdateInterceptor(l_errorReasonCode);

            //1.14.2.3:setThreadLocalPersistenceEventInterceptor(arg0 : IfoOrderManagerPersistenceEventInterceptor)
            l_orderMgr.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
            
            //1.14.2.4:DefaultOrderInvalidatedMarketResponseMessage(arg0 : long)
            DefaultOrderInvalidatedMarketResponseMessage l_responseMessage = 
                new DefaultOrderInvalidatedMarketResponseMessage(l_orderUnit.getOrderId());
                
            //1.14.2.5:process(arg0 : OrderInvalidatedMarketResponseMessage)
            MarketAdapter l_markertAdapter = l_tradingModule.getMarketAdapter();
                            
            IfoMarketResponseReceiverCallbackService l_callBackService =
                (IfoMarketResponseReceiverCallbackService) l_markertAdapter.getMarketResponseReceiverCallbackService();            
            
            try
            {
                ProcessingResult l_result = l_callBackService.process(l_responseMessage);
                if (l_result.isFailedResult())
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        l_result.getErrorInfo(),
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
            catch (Exception l_ex2)
            {
                String l_strErrMsg = "�����R���G���[�ƂȂ����t�w�l�����̎��������Ɏ��s���܂����B����ID�F[" + l_orderUnit.getOrderId() + "]";
                log.error(l_strErrMsg, l_ex2);
                if (l_errorInfo != null)
                {
                    if (l_errorInfo.error_tag.startsWith("SYSTEM_"))
                    {
                        throw new WEB3SystemLayerException(
                            l_errorInfo,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_strErrMsg);
                    }
                    else
                    {
                        throw new WEB3BusinessLayerException(
                            l_errorInfo,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_strErrMsg);
                    }
                }
                else
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_strErrMsg);
                }
            }

            // is�\�񒍕��m�F�v(�����P�� : IfoOrderUnit)
            //�����P�ʁF�@@�����P��
            boolean l_blnIsReserveOrderExist = l_orderMgr.isReserveOrderExist(l_orderUnit);

            //�\�񒍕��m�F�v�iis�\�񒍕��m�F�v() == true�j�̏ꍇ
            if (l_blnIsReserveOrderExist)
            {
                //invalidateAll�\�񒍕��P��(long)
                //�e�����̒���ID�F�@@�����P��.����ID
                WEB3ToSuccReservationIfoOrderUpdateService l_updateService =
                    (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                        WEB3ToSuccReservationIfoOrderUpdateService.class);

                l_updateService.invalidateAllOrderUnit(l_orderUnit.getOrderId());
            }
        }

        //1.16.2.6:�I�v�V�������������i�⏕����.�⏕�����^�C�v��"�����I�v�V������������i�敨�؋����j�j�̏ꍇ
        if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
        {
            //1.16.2.6.1:�]�͍Čv�Z(�⏕���� : �⏕����)(����]�̓T�[�r�X::�]�͍Čv�Z)
            WEB3TPTradingPowerService l_tpTradingPowerService =
                (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);
                    l_tpTradingPowerService.reCalcTradingPower(l_subAccount);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (update�����f�[�^)<BR>
     * �����n�f�[�^��update���s���B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.�����P�ʂ�clone���쐬����B<BR>
     * <BR>
     * �Q�j�@@�P�j�ɂč쐬����clone�ɑ΂��A�X�V�l���Z�b�g����B<BR>
     * �@@DB�X�V�d�l<BR>
     * �@@�u�t�w�l���������iOK�j_�敨OP�����P�ʃe�[�u��.xls�v�Q�ƁB<BR>
     * <BR>
     * �R�j�@@�����f�[�^��update����B<BR>
     * �@@OP�����}�l�[�W��.update�����f�[�^()���R�[������B<BR>
     * <BR>
     * �@@[update�����f�[�^()�Ɏw�肷�����]<BR>
     * �@@�@@�����P�ʁF�@@�Q�j�ɂč쐬���������P��<BR>
     * �@@�@@is�����쐬�F�@@true�i�쐬����j<BR>
     * @@param l_orderUnit - (�����P��)
     * @@param l_dblOrderPrice - (�����P��)
     * @@param l_dblEstimatedPrice - (�T�Z��n���)
     * @@param l_strOrderRootDiv - (�����o�H�敪)
     * @@throws WEB3BaseException
     */
    protected void updateOrderData(
        IfoOrderUnit l_orderUnit, 
        double l_dblOrderPrice, 
        double l_dblEstimatedPrice,
        String l_strOrderRootDiv) throws WEB3BaseException
    {    
        final String STR_METHOD_NAME =
            "updateOrderData(IfoOrderUnit, double, double, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j�@@�p�����[�^.�����P�ʂ�clone���쐬����B
        IfoOrderUnitRow l_row = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        IfoOrderUnitParams l_params = new IfoOrderUnitParams(l_row);
        
        // �Q�j�@@�P�j�ɂč쐬����clone�ɑ΂��A�X�V�l���Z�b�g����B
        //DB�X�V�d�l
        // �@@�u�t�w�l���������iOK�j_�敨OP�����P�ʃe�[�u��.xls�v�Q�ƁB
        //���������ŏI�ʔ� = �i�����l�j + 1
        l_params.setLastOrderActionSerialNo(l_params.getLastOrderActionSerialNo() + 1);
        
        //������� = 2�F�������i�V�K�����j
        l_params.setOrderStatus(OrderStatusEnum.ORDERING);
        
        //�����̒����P��
        //�i* �T�Z��n����Z�o�Ɏg�p�����P���j
        l_params.setPrice(l_dblOrderPrice);
        
        //�����̊T�Z��n���
        //�i* �T�Z��n����v�Z���ʁj
        l_params.setEstimatedPrice(l_dblEstimatedPrice);
                
		//�����E����敪
		l_params.setModifyCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);

        //1�F�����T�[�o
        l_params.setRequestType(WEB3RequestTypeDef.QUOTE_SERVER);
        
        //���ݎ���
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        //�����o�H�敪
        l_params.setSubmitOrderRouteDiv(l_strOrderRootDiv);
        
        //�R�j�@@�����f�[�^��update����B
        //OP�����}�l�[�W��.update�����f�[�^()���R�[������B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager = 
            (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager(); 
        IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderManager.toOrderUnit(l_params);
        l_orderManager.updateOrderData(l_ifoOrderUnit, true);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (sendMQ�g���K)<BR>
     * MQ�g���K�𔭍s����K�v������ꍇ�́A<BR>
     * MQ�g���K�𔭍s����B<BR>
     * <BR>
     * �P�j�@@MQ�g���K�𔭍s���邩�ǂ����𔻒肷��B<BR>
     * �@@������ԊǗ�.is�g���K���s()���R�[������B<BR>
     * <BR>
     * �@@[is�g���K���s()�Ɏw�肷�����]<BR>
     * �@@�@@���������F�@@null�i�Œ�j<BR>
     * �@@�@@�������P��.�����������w�肷��ƁA<BR>
     * �@@�@@�@@�@@�t�w�l����false���Ԃ����ׁB<BR>
     * <BR>
     * �Q�j�@@MQ�g���K���s�v�ۂ��擾����B<BR>
     * �@@������ؑ�.isMQ�g���K���s�o�H()���R�[������B<BR>
     * <BR>
     * �@@[isMQ�g���K���s�o�H()�Ɏw�肷�����]<BR>
     * �@@�@@�،���ЃR�[�h�F<BR>
     * �@@�@@�@@�����P��.�،����ID�ɊY������،���ЃR�[�h<BR>
     * �@@�@@�����^�C�v�F�@@�����P��.�����^�C�v<BR>
     * �@@�@@�s��R�[�h�F�@@�����P��.�s��ID�ɊY������s��R�[�h<BR>
     * �@@�@@�����o�H�敪�F�@@�����P�ʂ̓�����<BR>
     * �@@�@@�t�����g�����V�X�e���敪�F<BR>
     * �@@�@@�@@�敨OP�����T�[�r�X.get�t�����g�����V�X�e���敪(<BR>
     * �@@�@@�@@�@@�@@�����P��.�s��ID�ɊY������s��R�[�h)<BR>
     * <BR>
     * �R�j�@@�P�j�A�Q�j�̖߂�l�������Ƃ�true�̏ꍇ�A�ȍ~�̏��������{����B<BR>
     * <BR>
     *�@@�R�|�P�j�@@�������ɐݒ肷��f�[�^�R�[�h���擾����B<BR>
     *�@@�@@�敨OP�����T�[�r�X.get������MQ�f�[�^�R�[�h()���R�[������B<BR>
     * <BR>
     *�@@�@@[get������MQ�f�[�^�R�[�h()�Ɏw�肷�����]<BR>
     *�@@�@@�@@�����o�H�敪�F�@@�����P�ʂ̓�����<BR>
     * <BR>
     *�@@�@@null���ԋp���ꂽ�ꍇ�A�������I���ireturn�j����B<BR>
     * <BR>�@@ 
     * �@@�R�|�Q�j�@@WEB3MQMessageSpec�𐶐�����B<BR>
     * <BR>
     * �@@�@@[�R���X�g���N�^�Ɏw�肷�����]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@<BR>
     * �@@�@@�@@�@@�����P��.���XID�ɊY�����镔�X.�،���ЃR�[�h<BR>
     * �@@�@@�@@�f�[�^�R�[�h�F�@@<BR>
     * �@@�@@�@@�@@�敨OP�����T�[�r�X.get������MQ�f�[�^�R�[�h()�̖߂�l<BR>
     * <BR>
     * �@@�R�|�R�j�@@MQ�g���K�𔭍s����B<BR>
     * �@@�@@WEB3MQGatewayService.send()���\�b�h���R�[������B<BR>
     * 
     * �@@�@@[send()�Ɏw�肷�����]<BR>
     * �@@�@@�@@MQ���b�Z�[�W���e�F�@@�R�|�Q�j�ɂĐ��������C���X�^���X<BR>
     * <BR>
     * @@param l_orderUnit - (�敨OP�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     */
    protected void sendMQTrigger(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "sendMQTrigger(IfoOrderUnit l_orderUnit)  ";
        log.entering(STR_METHOD_NAME);
        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        try
        {
            //�P�j�@@MQ�g���K�𔭍s���邩�ǂ����𔻒肷��B
            // �@@������ԊǗ�.is�g���K���s()���R�[������B
            boolean l_blnIsTrigger = WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(null);
            
            //�Q�j�@@MQ�g���K���s�v�ۂ��擾����B
            //������ؑ�.isMQ�g���K���s�o�H()���R�[������B
            IfoOrderUnitRow l_row = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountMananger = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch)l_accountMananger.getBranch(l_row.getBranchId());
            WEB3GentradeMarket l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(l_row.getMarketId());

            WEB3IfoFrontOrderService l_frontOrderService = 
                (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
            String l_strSystemCode = 
                l_frontOrderService.getFrontOrderSystemCode(
                    l_market.getMarketCode());

            boolean l_blnIsSwitching = WEB3GentradeOrderSwitching.isSubmitMQTriggerEnable(
                l_branch.getInstitution().getInstitutionCode(),
                l_orderUnit.getProductType(),
                l_market.getMarketCode(),
                l_row.getSubmitOrderRouteDiv(),
                l_strSystemCode);
            
            //�R�j�@@�P�j�A�Q�j�̖߂�l�������Ƃ�true�̏ꍇ�A�ȍ~�̏��������{����B
            if (l_blnIsTrigger && l_blnIsSwitching)
            {
                //�R�|�P�j�@@�������ɐݒ肷��f�[�^�R�[�h���擾����B
                //�敨OP�����T�[�r�X.get������MQ�f�[�^�R�[�h()���R�[������B
                String l_strOrderMQDataCode = l_frontOrderService.getOrderMQDataCode(l_orderUnit);
                //null���ԋp���ꂽ�ꍇ�A�������I���ireturn�j����B
                if (WEB3StringTypeUtility.isEmpty(l_strOrderMQDataCode))
                {
                    return;
                }
                
                //�R�|�Q�j�@@WEB3MQMessageSpec�𐶐�����B
                //�f�[�^�R�[�h�F�@@
                //�敨OP�����T�[�r�X.get������MQ�f�[�^�R�[�h()�̖߂�l 
                WEB3MQMessageSpec l_spec = new WEB3MQMessageSpec(l_branch.getInstitution().getInstitutionCode(),
                    l_strOrderMQDataCode);
                
                //�R�|�Q�j�@@MQ�g���K�𔭍s����
                //WEB3MQGatewayService.send()���\�b�h���R�[������B
                WEB3MQGatewayService l_gatewayService = (WEB3MQGatewayService)Services.getService(WEB3MQGatewayService.class);
                l_gatewayService.send(l_spec);
            }
                
                log.exiting(STR_METHOD_NAME);           
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
    }

    /**
     * (is�����Ώ�)<BR>
     * �w��̒������t�w�l�����̏����Ώۂł��邩�𔻒肷��B<BR>
     * <BR>
     * �����Ώۂ̏ꍇ�Atrue���A�����ΏۊO�̏ꍇ�Afalse��ԋp����B <BR>
     * �ȉ��̏����̂����ꂩ�ɊY������ꍇ�A<BR>
     * �����ΏۊO�Ƃ��Afalse��ԋp����B�ȊO�Atrue��ԋp����B<BR>
     * <BR>
     * �E�p�����[�^.�����P��.�����L����� != "�I�[�v��"<BR>
     * �E�p�����[�^.�����P��.�������� != "�t�w�l"<BR>
     * �E�p�����[�^.�����P��.���N�G�X�g�^�C�v == "�����T�[�o"<BR>
     * @@param l_orderUnit - (�敨OP�����P��)
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isProcessObject (IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "isProcessObject (OrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�w��̒������t�w�l�����̏����Ώۂł��邩�𔻒肷��B
        IfoOrderUnitRow l_row = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderingCondition = l_row.getOrderConditionType();
        String l_strRequestType = l_row.getRequestType();
        
        //�ȉ��̏����̂����ꂩ�ɊY������ꍇ�A
        //�����ΏۊO�Ƃ��Afalse��ԋp����B�ȊO�Atrue��ԋp����
        if (!OrderOpenStatusEnum.OPEN.equals(l_orderUnit.getOrderOpenStatus())
            || !WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderingCondition)
            || WEB3RequestTypeDef.QUOTE_SERVER.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
    }

   /**
     * (create�V�K���������e)<BR>
     * �����Ŏw�肳�ꂽ�����P�ʃf�[�^���V�K���������e���쐬����B<BR>
     * <BR>
     * �P�j�@@�V�K���������e�̐���<BR>
     * �@@�V�K���������e�I�u�W�F�N�g�𐶐�����B<BR>
     * �@@[�R���X�g���N�^����]<BR>
     * �@@����ID�F�@@�����P��.����ID<BR>
     * �@@�����P�ʂh�c�F�@@�����P��.�����P��ID<BR>
     * �@@�����㐔�ʁF�@@�����P��.��������<BR>
     * �@@������w�l�F�@@�����P��.�w�l<BR>
     * �Q�j�@@�v���p�e�B�Z�b�g<BR>
     * �@@�P�j�Ő��������V�K���������e�Ƀv���p�e�B���Z�b�g����isetter���\�b�h���g�p�j<BR>
     * �@@�������s�����F�@@�����P��.���s����<BR>
     * �@@�����������F�@@�����P��.����������<BR>
     * �@@�������F�@@�����P��.������<BR>
     * �@@���������F�@@�����P��.��������<BR>
     * �@@�����������Z�q�F�@@�����P��.�����������Z�q<BR>
     * �@@�t�w�l��l�^�C�v�F�@@�����P��.�t�w�l��l�^�C�v<BR>
     * �@@�t�w�l��l�F�@@�����P��.�t�w�l��l<BR>
     * �@@�iW�w�l�j�����w�l�F�@@0�i�Œ�j<BR>
     * �@@�����㒍�������敪�F�@@�敨OP�f�[�^�A�_�v�^.get���������敪(�����P��) <BR>
     * �@@�[��O�J�z�Ώۃt���O�F�@@�敨OP�f�[�^�A�_�v�^.<BR>
     * �@@�@@�@@�@@get�[��O�J�z�Ώۃt���O�iPR�w�j(�����P��) <BR>
     * <BR>
     *�R�j�@@�v���p�e�B�Z�b�g�����V�K���������e��ԋp����B<BR>
     * @@param l_orderUnit - (�敨OP�����P��)<BR>
     * @@return WEB3IfoOpenContractChangeSpec<BR>
     * @@throws WEB3BaseException
     */
    protected WEB3IfoOpenContractChangeSpec createOpenContractChangeSpec (IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createOpenContractChangeSpec (IfoOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        //����ID
        long l_orderUnitId = l_orderUnitRow.getOrderUnitId();
        //�����P��ID
        long l_orderId = l_orderUnitRow.getOrderId();
        //�����㐔��
        double l_quantity = l_orderUnitRow.getQuantity();
        //������P��
        double l_limitPrice = l_orderUnitRow.getLimitPrice();
        //�iW�w�l�j�����w�l
        double l_wLimitPrice = 0D;

        //�V�K���������e�̐��� 
        WEB3IfoOpenContractChangeSpec l_web3IfoOpenContractChangeSpec = 
            new WEB3IfoOpenContractChangeSpec(
                l_orderId,
                l_orderUnitId,
                l_quantity,
                l_limitPrice);

        //�v���p�e�B�Z�b�g

        //���s����
        l_web3IfoOpenContractChangeSpec.setChangeExecCondType(l_orderUnitRow.getExecutionConditionType());
        //������
        l_web3IfoOpenContractChangeSpec.setChangeExpirationDate(WEB3DateUtility.toDay(l_orderUnitRow.getExpirationDate()));
        //������
        l_web3IfoOpenContractChangeSpec.setOrderBizDate(WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(),"yyyyMMdd"));
        //��������
        l_web3IfoOpenContractChangeSpec.setOrderCond(l_orderUnitRow.getOrderConditionType());
        //�����������Z�q
        l_web3IfoOpenContractChangeSpec.setOrderCondOperator(l_orderUnitRow.getOrderCondOperator());
        //�t�w�l��l�^�C�v
        l_web3IfoOpenContractChangeSpec.setStopOrderBasePriceType(l_orderUnitRow.getStopPriceType());
        //�t�w�l��l
        l_web3IfoOpenContractChangeSpec.setStopOrderBasePrice(l_orderUnitRow.getStopOrderPrice());
        //�iW�w�l�j�����w�l
        l_web3IfoOpenContractChangeSpec.setWLimitPriceChange(l_wLimitPrice);
        //���������敪
        l_web3IfoOpenContractChangeSpec.setExpirationDateType(WEB3IfoDataAdapter.getExpirationDateType(l_orderUnit));
        //�[��O�J�z�Ώۃt���O
        l_web3IfoOpenContractChangeSpec.setEveningSessionCarryoverFlag(
            WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(l_orderUnit));

        //�v���p�e�B�Z�b�g�����V�K���������e��ԋp����B
        return l_web3IfoOpenContractChangeSpec;
    }
}
@
