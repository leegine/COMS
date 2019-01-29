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
filename	WEB3EquityCancelOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������������T�[�r�XImpl (WEB3EquityCancelOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/27 �������F(SRA) �V�K�쐬
Revesion History : 2006/08/29 �ęԍg(���u) �d�l�ύX���f��970
Revesion History : 2006/11/03 ������@@(���u)���f��No.1017
Revesion History : 2006/12/14 �đo�g(���u) ���f��1083
Revesion History : 2007/01/31 �đo�g(���u) ���f��1120
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3TradingTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityCancelOrderInterceptor;
import webbroker3.equity.WEB3EquityCancelOrderSpec;
import webbroker3.equity.WEB3EquityClientRequestService;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.define.WEB3EquityWlimitOrderPriceDivDef;
import webbroker3.equity.message.WEB3EquityCancelCompleteRequest;
import webbroker3.equity.message.WEB3EquityCancelCompleteResponse;
import webbroker3.equity.message.WEB3EquityCancelConfirmRequest;
import webbroker3.equity.message.WEB3EquityCancelConfirmResponse;
import webbroker3.equity.service.delegate.WEB3EquityCancelOrderService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

/**
 * �i������������T�[�r�XImpl�j�B<BR>
 * <BR>
 * ������������T�[�r�X�����N���X
 * @@version 1.0  
 */
public class WEB3EquityCancelOrderServiceImpl
    extends WEB3EquityClientRequestService
    implements WEB3EquityCancelOrderService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityCancelOrderServiceImpl.class);

   /**
    * @@roseuid 40AC7467029D
    */
   public WEB3EquityCancelOrderServiceImpl()
   {

   }

   /**
    * (validate�������)<BR>
    * �u�V�[�P���X�}�i��������T�[�r�X�j��������m�F�v�Q�ƁB<BR>
    * @@param l_request - (���̓f�[�^)<BR>
    * <BR>
    * �N���C�A���g����̓��̓f�[�^<BR>
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@throws webbroker3.common.WEB3BaseException
    * @@roseuid 403097C202AA
    */
    public WEB3GenResponse validateCancelOrder(WEB3EquityCancelConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateCancelOrder(WEB3EquityCancelOrderConfirmRequest)";
        log.entering(STR_METHOD_NAME);
    
        //1.1. validate()
        l_request.validate();
        
        //1.2. get�⏕����()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
                
        //1.3. get�㗝���͎�()
        Trader l_trader = this.getTrader();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        long l_lngOrderId = Long.parseLong(l_request.id);
    
        //1.4. validate������t�\()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
    
        //1.5. ��������������e()
        WEB3EquityCancelOrderSpec l_cancelOrderSpec =
            new WEB3EquityCancelOrderSpec(l_lngOrderId, l_trader);
        
        //1.6. validate�����������()                
        EqTypeOrderValidationResult l_res =
            (EqTypeOrderValidationResult)l_orderManager.validateCancelOrder(l_subAccount, l_cancelOrderSpec);  
        if (l_res.getProcessingResult().isFailedResult())
        {
            throw new WEB3BaseException(
                l_res.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.7. get�s��ǌx���s��()
        String[] l_strTradeCloseMarket =
            WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
                (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch(),
                ProductTypeEnum.EQUITY,
                WEB3MarginTradingDivDef.DEFAULT);
    
        //1.8. createResponse()
        WEB3EquityCancelConfirmResponse l_confirmResponse =
            (WEB3EquityCancelConfirmResponse)l_request.createResponse();
            
        //1.9. getOrderUnits()
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();    
        Market l_market = null;
        try
        {
            WEB3GentradeFinObjectManager l_objectManager =
              (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            l_market = l_objectManager.getMarket(l_orderUnitRow.getMarketId());
        }
        catch (NotFoundException l_nfe)
        {
            log.error(l_nfe.getMessage(), l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(),
                l_nfe);
        }   
            
        //1.10. ���蒍���̏ꍇ�̂ݎ��s
        double l_dblEstimatedBookPrice = 0.0D;
        OrderTypeEnum l_orderType = l_orderUnit.getOrderType();
        if (OrderTypeEnum.EQUITY_SELL.equals(l_orderType))
        {
            WEB3EquityBizLogicProvider l_bizLogicProvider =
                (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();
            l_dblEstimatedBookPrice = 
                l_bizLogicProvider.calcEstimatedBookPrice(
                    l_orderUnitRow.getProductId(),
                    l_subAccount,
                    l_orderUnit.getTaxType());
        }
        
        //1.11. getTradedProduct()
        WEB3EquityTradedProduct l_tradedProduct = null;
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        WEB3EquityProduct l_product = (WEB3EquityProduct)l_orderUnit.getProduct();
        try
        {
            l_tradedProduct =
                (WEB3EquityTradedProduct)l_productManager.getTradedProduct(
                    l_subAccount.getInstitution(),
                    l_product.getProductCode(),
                    l_market.getMarketCode());
        }
        catch (NotFoundException l_nfe)
        {
            log.error(l_nfe.getMessage(), l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(),
                l_nfe);
        }        
       
        //1.12. get�\���p�������()
        WEB3EquityProductQuote l_productQuote = null;
        l_productQuote =
            l_productManager.getDisplayEquityProductQuote(
                l_tradedProduct,
                l_subAccount);
        
        //�v���p�e�B�Z�b�g
        //���X�|���X.�m�F��������
        Date l_bizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        l_confirmResponse.checkDate = WEB3DateUtility.toDay(l_bizDate);
        //���X�|���X.�T�Z��n���
        l_confirmResponse.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getEstimatedPrice());
        //���X�|���X.����I���x���s��R�[�h�ꗗ
        l_confirmResponse.messageSuspension = l_strTradeCloseMarket;
        //���X�|���X.�����R�[�h
        l_confirmResponse.productCode = l_product.getProductCode();
        //���X�|���X.������
        EqtypeProductRow l_productRow = (EqtypeProductRow) l_product.getDataSourceObject();
        l_confirmResponse.productName = l_productRow.getStandardName();
        //���X�|���X.�s��R�[�h
        l_confirmResponse.marketCode = l_market.getMarketCode();
        //���X�|���X.�����敪
        l_confirmResponse.taxType = WEB3EquityDataAdapter.getTaxType(l_orderUnit.getTaxType());
        //���X�|���X.����敪
        if (OrderTypeEnum.EQUITY_BUY.equals(l_orderType))
        {
            if (WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_orderUnitRow.getSonarTradedCode()))
            {
                l_confirmResponse.tradingType = WEB3TradingTypeDef.PRESENCE_ORDER;
            }
            else
            {
                l_confirmResponse.tradingType = WEB3TradingTypeDef.BUY_ORDER;
            }
        }
        else
        {
            l_confirmResponse.tradingType = WEB3TradingTypeDef.SELL_ORDER;
        }
        //���X�|���X.��������
        l_confirmResponse.orderQuantity =
            WEB3StringTypeUtility.formatNumber(l_orderUnit.getQuantity());
        //���X�|���X.���o������
        double l_dblExecutedQuantity = l_orderUnitRow.getExecutedQuantity();
        if (l_dblExecutedQuantity == 0.0D)
        {
            l_confirmResponse.partContQuantity = null;
        }
        else
        {
            l_confirmResponse.partContQuantity =
                WEB3StringTypeUtility.formatNumber(l_dblExecutedQuantity);
        }
        //���X�|���X.�����P���敪
        if (l_orderUnit.isMarketOrder())
        {
            l_confirmResponse.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
        }
        else
        {
            l_confirmResponse.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            //���X�|���X.�����P��
            l_confirmResponse.limitPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnit.getLimitPrice());
        }

        //���X�|���X.�T�Z�뉿�P��
        if (OrderTypeEnum.EQUITY_SELL.equals(l_orderType))
        {
            l_confirmResponse.estimatedBookPrice = 
                WEB3StringTypeUtility.formatNumber(l_dblEstimatedBookPrice);
        }
        //���X�|���X.�l�i����
        l_confirmResponse.priceCondType = l_orderUnitRow.getPriceConditionType();
        //���X�|���X.���s����
        l_confirmResponse.execCondType =
            l_orderManager.getExecutionConditionTypeSonar(l_orderUnitRow.getExecutionConditionType());
        //���X�|���X.���������敪
        boolean l_isCarriedOrderUnit = l_orderManager.isCarriedOrderUnit(l_orderUnit);
        if (!l_isCarriedOrderUnit)
        {
            l_confirmResponse.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
        }
        else
        {
            l_confirmResponse.expirationDateType = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
        }   
        //���X�|���X.�����L������
        if (l_isCarriedOrderUnit)
        {
            l_confirmResponse.expirationDate =
                l_orderUnitRow.getExpirationDate();
        }
        //���X�|���X.���������敪
        l_confirmResponse.orderCondType =
            l_orderUnitRow.getOrderConditionType();
        //���X�|���X.�t�w�l�p���������P��
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_confirmResponse.stopOrderCondPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
        }
        //���X�|���X.�t�w�l�p�����������Z�q
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_confirmResponse.stopOrderCondOperator =
                l_orderUnitRow.getOrderCondOperator();
        }
        //���X�|���X.W�w�l�p���������P��
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_confirmResponse.wlimitOrderCondPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
        }
        //���X�|���X.W�w�l�p�����������Z�q
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_confirmResponse.wlimitOrderCondOperator =
                l_orderUnitRow.getOrderCondOperator();
        }
        //���X�|���X.W�w�l�p�����P���敪
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            if (l_orderUnitRow.getWLimitPrice() == 0.0D)
            {
                l_confirmResponse.wLimitOrderPriceDiv = WEB3EquityWlimitOrderPriceDivDef.WLIMIT_ORDER_PRICE_DIV_MARKET_PRICE;
            }
            else
            {
                l_confirmResponse.wLimitOrderPriceDiv = WEB3EquityWlimitOrderPriceDivDef.WLIMIT_ORDER_PRICE_DIV_LIMIT_PRICE;
                //���X�|���X.W�w�l�p�����P��
                l_confirmResponse.wLimitPrice =
                                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getWLimitPrice());
            }
        }

        //�@@W�w�l�p���s�����F�@@�����P��.����������2�iW�w�l�j�̏ꍇ�̂݃Z�b�g�B
        //�@@�@@�@@�g�����������}�l�[�W��.get���s�����iSONAR�j(�����P��.�v�w�l�p���s����)�̖߂�l���Z�b�g�B
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_confirmResponse.wlimitExecCondType =
                l_orderManager.getExecutionConditionTypeSonar(l_orderUnitRow.getWLimitExecCondType());
        }

        //�@@W�w�l�p�L����ԋ敪�F�@@�����f�[�^�A�_�v�^.get�v�w�l�p�L����ԋ敪(�����P��)�̖߂�l���Z�b�g�B
        l_confirmResponse.wlimitEnableStatusDiv =
            WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);

        //�@@W�w�l�p�֑ؑO�����P���F�@@�����f�[�^�A�_�v�^.get�v�w�l�p�֑ؑO�����P��(�����P��)�̖߂�l���Z�b�g�B
        l_confirmResponse.wlimitBefChgLimitPrice =
            WEB3EquityDataAdapter.getWLimitBefSwitchPrice(l_orderUnit);

        //�@@W�w�l�p�֑ؑO���s�����F�@@�����f�[�^�A�_�v�^.get�v�w�l�p�֑ؑO���s����(�����P��)�̖߂�l���Z�b�g�B
        l_confirmResponse.wlimitBefChgExecCondType =
            WEB3EquityDataAdapter.getWLimitBefSwitchExecCondType(l_orderUnit);

        //�@@�����������敪�F�@@�����P��.����������
        l_confirmResponse.orgOrderCondType = l_orderUnitRow.getOrgOrderConditionType();

        //�@@�����������P���F�@@�����P��.���t�w�l��l
        if (l_orderUnitRow.getOrgStopOrderPriceIsNull())
        {
            l_confirmResponse.orgOrderCondPrice = null;
        }
        else
        {
            l_confirmResponse.orgOrderCondPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getOrgStopOrderPrice());
        }

        //�@@�������������Z�q�F�@@�����P��.�������������Z�q
        l_confirmResponse.orgOrderCondOperator = l_orderUnitRow.getOrgOrderCondOperator();

        //�@@���v�w�l�p�����P���敪�F�@@�����f�[�^�A�_�v�^.get���v�w�l�p�����P���敪(�����P��)�̖߂�l���Z�b�g�B
        String l_strOrgWlimitOrderPriceDiv =
            WEB3EquityDataAdapter.getOrgWLimitOrderPriceDiv(l_orderUnit);
        l_confirmResponse.orgWlimitOrderPriceDiv = l_strOrgWlimitOrderPriceDiv;

        //�@@���v�w�l�p�����P���F��W�w�l�p�����P���敪��"�w�l"�̏ꍇ�̂݁A
        //   �����f�[�^�A�_�v�^.get���v�w�l�p�����P��(�����P��)�̖߂�l���Z�b�g�B
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWlimitOrderPriceDiv))
        {
            l_confirmResponse.orgWlimitPrice =
                WEB3EquityDataAdapter.getOrgWLimitOrderPrice(l_orderUnit);
        }

        //�@@���v�w�l�p���s�����F�@@�����f�[�^�A�_�v�^.get���v�w�l�p���s����(�����P��)�̖߂�l���Z�b�g�B
        l_confirmResponse.orgWlimitExecCondType =
            WEB3EquityDataAdapter.getOrgWLimitExecCondType(l_orderUnit);

        //���X�|���X.�����敪
        l_confirmResponse.currentPriceDiv = l_productQuote.getQuoteTypeDiv();
        //���X�|���X.�����i���ݒl�j
        l_confirmResponse.currentPrice =
            WEB3StringTypeUtility.formatNumber(l_productQuote.getQuote());
        //���X�|���X.�O����
        l_confirmResponse.comparedPreviousDay =
            WEB3StringTypeUtility.formatNumber(l_productQuote.getComparedPreviousDay());
        //���X�|���X.������ԁi�������\���ԁj
        l_confirmResponse.currentPriceTime = l_productQuote.getQuoteTime();

        //���X�|���X.���ݒl
        l_confirmResponse.boardCurrentPrice = l_productQuote.getBoardCurrentPrice();

        //���X�|���X.���ݒl����
        l_confirmResponse.boardCurrentPriceTime = l_productQuote.getBoardCurrentPriceTime();

        //���X�|���X.���ݒl�敪
        l_confirmResponse.boardCurrentPriceDiv = l_productQuote.getBoardCurrentPriceDiv();

        //���X�|���X.���ݒl�O����
        l_confirmResponse.boardChange = l_productQuote.getBoardChange();

        //���X�|���X.�o����
        l_confirmResponse.volume = l_productQuote.getVolume();

        //���X�|���X.�o��������
        l_confirmResponse.volumeTime = l_productQuote.getVolumeTime();

        //���X�|���X.���C�z�l�^�C�g���敪
        l_confirmResponse.askPriceTitle = l_productQuote.getAskPriceTitle();

        //���X�|���X.���C�z�l
        l_confirmResponse.askPrice = l_productQuote.getAskPrice();

        //���X�|���X.���C�z�l����
        l_confirmResponse.askPriceTime = l_productQuote.getAskPriceTime();

        //���X�|���X.���C�z�l�^�C�g���敪
        l_confirmResponse.bidPriceTitle = l_productQuote.getBidPriceTitle();

        //���X�|���X.���C�z�l
        l_confirmResponse.bidPrice = l_productQuote.getBidPrice();

        //���X�|���X.���C�z�l����
        l_confirmResponse.bidPriceTime = l_productQuote.getBidPriceTime();

        //���X�|���X.��l�i
        l_confirmResponse.basePrice = l_productQuote.getBasePrice();

        log.exiting(STR_METHOD_NAME);
        return l_confirmResponse;
    }

   /**
    * (submit�������)<BR>
    * �u�V�[�P���X�}�i��������T�[�r�X�j��������X�V�v�Q��<BR>
    * @@param l_request - (���̓f�[�^)<BR>
    * <BR>
    * �N���C�A���g����̓��̓f�[�^<BR>
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@throws webbroker3.common.WEB3BaseException
    * @@roseuid 403097C202BA
    */
    public WEB3GenResponse submitCancelOrder(WEB3EquityCancelCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitCancelOrder(WEB3EquityCancelOrderCompleteRequest)";
        log.entering(STR_METHOD_NAME);
    
        //1.1. validate()
        l_request.validate();
        
        //1.2. get�⏕����()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
                
        //1.3. get�㗝���͎�()
        WEB3GentradeTrader l_trader = (WEB3GentradeTrader)this.getTrader();
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        long l_lngOrderId = Long.parseLong(l_request.id);
        
        //1.4. validate������t�\()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
    
        //1.5. ��������������e()
        WEB3EquityCancelOrderSpec l_cancelOrderSpec =
            new WEB3EquityCancelOrderSpec(l_lngOrderId, l_trader);
        
        //1.6. get������()
        if (l_request.checkDate == null)
        {
            l_request.checkDate =
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }
        WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        
        //1.7. validate�����������()               
        EqTypeOrderValidationResult l_res =
            (EqTypeOrderValidationResult)l_orderManager.validateCancelOrder(l_subAccount, l_cancelOrderSpec);  
        if (l_res.getProcessingResult().isFailedResult())
        {
            throw new WEB3BaseException(
                l_res.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.8. getOrderUnits()
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        //1.9. ��肪����ꍇ�i�����P��.isUnexecuted( )==false�j
        double l_dblEstimatedPrice;
        if (!l_orderUnit.isUnexecuted())
        {
            //1.9.1. get��������n���z()
			l_dblEstimatedPrice = l_orderManager.getEstimateDeliveryAmountForClose(l_orderUnit);
        }
        else
        {
            l_dblEstimatedPrice = l_orderUnitRow.getEstimatedPrice();
        }
        
        //1.10. ������������C���^�Z�v�^()
        OpLoginSecurityService l_securityService =
            (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);
        String l_strOrderRootDiv =
            l_securityService.getSessionProperty(
                WEB3SessionAttributeDef.ORDER_ROOT_DIV);
        WEB3EquityCancelOrderInterceptor l_cancelInterceptor =
            new WEB3EquityCancelOrderInterceptor(
                l_dblEstimatedPrice,
                l_strOrderRootDiv,
                l_trader);
        
        //1.11. setThreadLocalPersistenceEventInterceptor()
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_cancelInterceptor);
    
        //1.12. submit���������������()
        EqTypeOrderSubmissionResult l_orderSubmissionResult =
            l_orderManager.submitCancelOrder(
                l_subAccount,
                l_cancelOrderSpec,
                l_request.password,
                true);
        if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
        {
            ProcessingResult l_processingResult = l_orderSubmissionResult.getProcessingResult();
            throw new WEB3BaseException(
                l_processingResult.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_processingResult.getErrorInfo().getErrorMessage());
        }
        
        boolean l_blnIsCancelAllOrderUnit = false;
        if (l_orderManager.isReserveOrderConfirmRequire(l_orderUnit))
        {
            WEB3ToSuccReservationEqTypeOrderUpdateService l_updateService =
                (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                    WEB3ToSuccReservationEqTypeOrderUpdateService.class);
            l_blnIsCancelAllOrderUnit =
                l_updateService.cancelAllOrderUnit(l_orderUnit.getOrderId());
        }
        
        //1.13. �]�͍Čv�Z()
        WEB3TPTradingPowerService l_tradingpowerService =
        (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        l_tradingpowerService.reCalcTradingPower(l_subAccount);
        
        //1.14. createResponse()
        WEB3EquityCancelCompleteResponse l_completeResponse =
            (WEB3EquityCancelCompleteResponse)l_request.createResponse();
        
        //�v���p�e�B�Z�b�g
        // ���X�|���X.�X�V����
        l_completeResponse.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
        // ���X�|���X.���ʔԍ�
        l_completeResponse.orderActionId = String.valueOf(l_orderUnitRow.getOrderId()); 
        l_completeResponse.succSettingFlag = l_blnIsCancelAllOrderUnit;
    
        log.exiting(STR_METHOD_NAME);
        return l_completeResponse;
    }

   /**
    * ��������������������s����B<BR>
    * <BR>
    * �i�V�X�e���������j�K�C�h 4.4.�Ɩ����W�b�N�@@�Q�Ɓj<BR>
    * <BR>
    * �P�j�@@���{���\�b�h����<BR>
    * �@@�|���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u����������������m�F���N�G�X�g�v�̏ꍇ�́A<BR>
    * this.��������m�F( )���R�[������B<BR>
    * �@@�|���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u����������������������N�G�X�g�v�̏�<BR>
    * ���́Athis.��������X�V( )���R�[������B<BR>
    * <BR>
    * �Q�j�@@���\�b�h�̖߂�l��ԋp����B<BR>
    * @@param l_request
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@throws webbroker3.common.WEB3BaseException
    * @@roseuid 403097C202BC
    */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3EquityCancelConfirmRequest)
        {
            l_response = this.validateCancelOrder(
                (WEB3EquityCancelConfirmRequest)l_request);
        }
        else if(l_request instanceof WEB3EquityCancelCompleteRequest)
        {
            l_response = this.submitCancelOrder(
                (WEB3EquityCancelCompleteRequest)l_request);
        }
        else
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "request !=����������������m�F���N�G�X�g and request !=����������������������N�G�X�g");
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
