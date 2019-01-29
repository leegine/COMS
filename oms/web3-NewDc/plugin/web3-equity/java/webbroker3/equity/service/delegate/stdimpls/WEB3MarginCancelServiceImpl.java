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
filename	WEB3MarginCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p�������T�[�r�XImpl(WEB3MarginCancelServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/08 �������F(SRA) �V�K�쐬
Revesion History : 2006/11/27 �đo�g(���u) ���f��1019
Revesion History : 2006/12/14 ������@@(���u)�@@���f��No.1083
Revesion History : 2007/01/31 �đo�g(���u) ���f��1120
Revesion History : 2007/06/05 �����q (���u) �d�l�ύX�E���f��1166
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityCancelOrderSpec;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityFinTransactionManager;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginCancelUpdateInterceptor;
import webbroker3.equity.WEB3MarginClientRequestService;
import webbroker3.equity.define.WEB3EquityWlimitOrderPriceDivDef;
import webbroker3.equity.define.WEB3MarginTradeTypeDef;
import webbroker3.equity.message.WEB3MarginCancelCompleteRequest;
import webbroker3.equity.message.WEB3MarginCancelCompleteResponse;
import webbroker3.equity.message.WEB3MarginCancelConfirmRequest;
import webbroker3.equity.message.WEB3MarginCancelConfirmResponse;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.message.WEB3MarginRepaymentUnit;
import webbroker3.equity.service.delegate.WEB3MarginCancelService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.tradingpower.define.WEB3TPResultAttentionObjectionTypeDef;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i�M�p�������T�[�r�XImpl�j�B<BR>
 * <BR>
 * �M�p�������T�[�r�X�����N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginCancelServiceImpl
    extends WEB3MarginClientRequestService
    implements WEB3MarginCancelService 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginCancelServiceImpl.class); 
    
    /**
     * @@roseuid 4140066E00E2
     */
    public WEB3MarginCancelServiceImpl() 
    {
     
    }
    
    /**
     * �M�p�������T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate����()�A<BR>
     * submit����()���\�b�h�̂����ꂩ���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 405807E70179
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute()";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3MarginCancelConfirmRequest)
        {
            l_response = this.validateOrder((WEB3MarginCancelConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3MarginCancelCompleteRequest)
        {
            l_response = this.submitOrder((WEB3MarginCancelCompleteRequest)l_request);
        }
        else
        {
            log.error("�p�����[�^�^�C�v�s���B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate����)<BR>
     * �M�p����̎���R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p�������T�[�r�X�jvalidate�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �M�p���������N�G�X�g�I�u�W�F�N�g
     * @@return WEB3MarginCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4058082D035D
     */
    protected WEB3MarginCancelConfirmResponse validateOrder(WEB3MarginCancelConfirmRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3MarginCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1. validate()
        l_request.validate();
        
        //1.2. get�⏕����()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
                
        //1.3. get�㗝���͎�()
        Trader l_trader = this.getTrader();
        
        //1.4. ��������������e()
        long l_lngOrderId = Long.parseLong(l_request.id);
        WEB3EquityCancelOrderSpec l_cancelOrderSpec =
            new WEB3EquityCancelOrderSpec(l_lngOrderId, l_trader);
        
        //1.5. getOrderUnits()
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        //1.6. getMarket()
        Market l_market = null;
        try
        {
            WEB3GentradeFinObjectManager l_objectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            l_market = l_objectManager.getMarket(l_orderUnitRow.getMarketId());
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(),
                l_nfe);
        }
        
        //1.10. validate�����������()
        OrderValidationResult l_result =
            l_orderManager.validateCancelOrder(l_subAccount, l_cancelOrderSpec);
        if (l_result.getProcessingResult().isFailedResult())
        {
            throw new WEB3BaseException(
                l_result.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.11. getOrderType()
        OrderTypeEnum l_orderType = l_orderUnit.getOrderType();
        
        //1.12. ��������������n�����̏ꍇ�̂ݎ��{����
        WEB3TPTradingPowerResult l_tpResult = null;
        if (OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_orderType))
        {
            //�Z�L�����e�B�T�[�r�X���擾
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(
                    OpLoginSecurityService.class); 
            // �����o�H�敪�擾
            String l_strOrderRootDiv =
                l_opLoginSec.getSessionProperty(
                    WEB3SessionAttributeDef.ORDER_ROOT_DIV);
            
            //1.12.1. �M�p����X�V�C���^�Z�v�^()
            WEB3MarginCancelUpdateInterceptor l_updateInterceptor =
                new WEB3MarginCancelUpdateInterceptor(
                    l_orderUnitRow.getEstimatedPrice(),
                    l_strOrderRootDiv,
                    (WEB3GentradeTrader)this.getTrader());
            //1.12.2. validate����]��()
            WEB3TPTradingPowerService l_tradingpowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            Object[] l_orderSpecIntercepters = { l_updateInterceptor };
            Object[] l_orderSpecs = { l_cancelOrderSpec };

            l_tpResult = l_tradingpowerService.validateTradingPower(
                l_subAccount,
                l_orderSpecIntercepters,
                l_orderSpecs,
                l_orderType,
                false);
            
            if (l_tpResult.isResultFlg() == false)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        
        //1.13. createResponse()
        WEB3MarginCancelConfirmResponse l_response =
            (WEB3MarginCancelConfirmResponse)l_request.createResponse();

        //1.14. create��������ByOrder()
        WEB3MarginContractUnit[] l_byOrder =
            l_orderManager.createContractUnitByOrder(l_orderUnit.getOrderUnitId());
        
        //1.15. get�s��ǌx���s��()
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
        String[] l_strCloseMarket =
            WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
                (WEB3GentradeBranch)l_mainAccount.getBranch(),
                ProductTypeEnum.EQUITY,
                l_orderUnitRow.getRepaymentType());
            
        //1.16. get���s�����iSONAR�j()
        String l_strExecutionCondition =
            l_orderManager.getExecutionConditionTypeSonar(
                l_orderUnitRow.getExecutionConditionType());
        
        //1.17. getTradedProduct()
        WEB3EquityTradedProduct l_tradedProduct =
            (WEB3EquityTradedProduct)l_orderUnit.getTradedProduct();
        
        // �g���v���_�N�g�}�l�[�W���擾
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
            
        //�������擾
        WEB3EquityProductQuote l_productQuote =
            l_productManager.getDisplayEquityProductQuote(l_tradedProduct, l_subAccount);
        
        //�����敪
        String l_strQuoteTypeDiv = l_productQuote.getQuoteTypeDiv();
        // ����
        double l_dblCurrentPrice = l_productQuote.getQuote();
        // �O����
        double l_dblChange = l_productQuote.getComparedPreviousDay();
        // �������\����
        Timestamp l_currentPriceTime = l_productQuote.getQuoteTime();
        
        //1.22. �M�p����ٍ�()
        WEB3MarginRepaymentUnit l_repaymentUnit = new WEB3MarginRepaymentUnit();
        
        //1.23. �v���p�e�B�Z�b�g
        l_repaymentUnit.repaymentDiv = l_orderUnitRow.getRepaymentType();
        l_repaymentUnit.repaymentTimeLimit = String.valueOf(l_orderUnitRow.getRepaymentNum());
        
        //1.24. get������()
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //1.25. getProduct( )
        WEB3EquityProduct l_product = (WEB3EquityProduct)l_orderUnit.getProduct();
        
        //1.26. �v���p�e�B�Z�b�g
        //���X�|���X.�m�F��������
        l_response.checkDate = l_datOrderBizDate;
        //���X�|���X.�T�Z��n���
        if (OrderOpenStatusEnum.OPEN.equals(l_orderUnit.getOrderOpenStatus()))
        {
            l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getEstimatedPrice());
        }
        else if (OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus()))
        {
            WEB3EquityFinTransactionManager l_transactionManager = 
                (WEB3EquityFinTransactionManager)l_tradingModule.getFinTransactionManager();
            l_response.estimatedPrice =
                WEB3StringTypeUtility.formatNumber(
                    l_transactionManager.getNetAmountTotal(l_orderUnit));
        }
        //���X�|���X.����I���x���s��R�[�h�ꗗ
        l_response.messageSuspension = l_strCloseMarket;
        //���X�|���X.�����R�[�h
        l_response.productCode = l_product.getProductCode();
        //���X�|���X.������
        EqtypeProductRow l_productRow = (EqtypeProductRow) l_product.getDataSourceObject();
        l_response.productName = l_productRow.getStandardName();
        //���X�|���X.�s��R�[�h
        l_response.marketCode = l_market.getMarketCode();
        //���X�|���X.�����敪
        if (TaxTypeEnum.NORMAL.equals(l_orderUnitRow.getTaxType()))
        {
            l_response.taxType = WEB3TaxTypeSpecialDef.NORMAL;
        }
        else if (TaxTypeEnum.SPECIAL.equals(l_orderUnitRow.getTaxType()) ||
                  TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_orderUnitRow.getTaxType()))
        {
            l_response.taxType = WEB3TaxTypeSpecialDef.SPECIAL;
        }
        //���X�|���X.����敪
        if (OrderTypeEnum.MARGIN_LONG.equals(l_orderType))
        {
            l_response.tradingType = WEB3MarginTradeTypeDef.OPEN_LONG_MARGIN;
        }
        else if (OrderTypeEnum.MARGIN_SHORT.equals(l_orderType))
        {
            l_response.tradingType = WEB3MarginTradeTypeDef.OPEN_SHORT_MARGIN;
        }
        else if (OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderType))
        {
            l_response.tradingType = WEB3MarginTradeTypeDef.CLOSE_SELL_MARGIN;
        }
        else if (OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderType))
        {
            l_response.tradingType = WEB3MarginTradeTypeDef.CLOSE_BUY_MARGIN;
        }
        else if (OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderType))
        {
            l_response.tradingType = WEB3MarginTradeTypeDef.GENBIKI_ORDER;
        }
        else if (OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_orderType))
        {
            l_response.tradingType = WEB3MarginTradeTypeDef.GENWADASI_ORDER;
        }
        //���X�|���X.�ٍ�
        l_response.repayment = l_repaymentUnit;
        //���X�|���X.��������
        l_response.orderQuantity = WEB3StringTypeUtility.formatNumber(l_orderUnit.getQuantity());
        //���X�|���X.���o������
        double l_dblExecutedQuantity = l_orderUnit.getExecutedQuantity();
        if (l_dblExecutedQuantity == 0.0D)
        {
            l_response.partContQuantity = null;
        }
        else
        {
            l_response.partContQuantity = WEB3StringTypeUtility.formatNumber(l_dblExecutedQuantity);
        }
        //���X�|���X.�����P���敪
        if (l_orderUnit.isMarketOrder() == true)
        {
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
        }
        else
        {
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
        }
        //���X�|���X.�����P��
        if (l_orderUnit.isMarketOrder() == true)
        {
            l_response.limitPrice = null;
        }
        else
        {
            l_response.limitPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getLimitPrice());
        }
        //���X�|���X.���Ϗ����敪
        l_response.closingOrder = l_orderUnitRow.getClosingOrderType();
        //���X�|���X.�������׈ꗗ
        l_response.contractUnits = l_byOrder;
        //���X�|���X.�����挻�n�������敪�F�@@
        TaxTypeEnum l_swapTaxType = l_orderUnitRow.getSwapTaxType();
        if (TaxTypeEnum.UNDEFINED.equals(l_swapTaxType))
        {
            l_response.swapTaxType = null;
        }
        else if (TaxTypeEnum.NORMAL.equals(l_swapTaxType))
        {
            l_response.swapTaxType = WEB3TaxTypeSpecialDef.NORMAL;
        }
        else if (TaxTypeEnum.SPECIAL.equals(l_swapTaxType) ||
                  TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_swapTaxType))
        {
            l_response.swapTaxType = WEB3TaxTypeSpecialDef.SPECIAL;
        }
        //���X�|���X.���s����
        l_response.execCondType = l_strExecutionCondition;
        //���X�|���X.�l�i����
        l_response.priceCondType = l_orderUnitRow.getPriceConditionType();
        //���X�|���X.���������敪
        boolean l_isCarriedOrderUnit = l_orderManager.isCarriedOrderUnit(l_orderUnit);
        if (l_isCarriedOrderUnit == true)
        {
            l_response.expirationDateType = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
        }
        else
        {
            l_response.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
        }
        //���X�|���X.�����L������
        if (l_isCarriedOrderUnit == true)
        {
            l_response.expirationDate = WEB3DateUtility.toDay(l_orderUnitRow.getExpirationDate());
        }
        else
        {
            l_response.expirationDate = null;
        }
        
        //���X�|���X.���������敪
        l_response.orderCondType = l_orderUnitRow.getOrderConditionType();
        //���X�|���X.�t�w�l�p���������P��
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_response.stopOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
        }
        else
        {
            l_response.stopOrderCondPrice = null;
        }
        //���X�|���X.�t�w�l�p�����������Z�q
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_response.stopOrderCondOperator = l_orderUnitRow.getOrderCondOperator();
        }
        else
        {
            l_response.stopOrderCondOperator = null;
        }
        //���X�|���X.W�w�l�p���������P��
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_response.wlimitOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
        }
        else
        {
            l_response.wlimitOrderCondPrice = null;
        }
        //���X�|���X.W�w�l�p�����������Z�q
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_response.wlimitOrderCondOperator = l_orderUnitRow.getOrderCondOperator();
        }
        else
        {
            l_response.wlimitOrderCondOperator = null;
        }
		//���X�|���X.W�w�l�p�����P���敪
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
	        if (l_orderUnitRow.getWLimitPrice() == 0)
	        {
		        l_response.wLimitOrderPriceDiv = WEB3EquityWlimitOrderPriceDivDef.WLIMIT_ORDER_PRICE_DIV_MARKET_PRICE;
		        l_response.wLimitPrice = null;
	        }
	        else
	        {
		        l_response.wLimitOrderPriceDiv = WEB3EquityWlimitOrderPriceDivDef.WLIMIT_ORDER_PRICE_DIV_LIMIT_PRICE;
		        //���X�|���X.W�w�l�p�����P��
		        l_response.wLimitPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getWLimitPrice());
	        }
            //���X�|���X.W�w�l�p���s����
            l_response.wlimitExecCondType =
                l_orderManager.getExecutionConditionTypeSonar(l_orderUnitRow.getWLimitExecCondType());
        }
        else
        {
	        l_response.wLimitOrderPriceDiv = null;
            l_response.wlimitExecCondType = null;
        }

        //���X�|���X.W�w�l�p�L����ԋ�
        l_response.wlimitEnableStatusDiv =
            WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);
        //���X�|���X.W�w�l�p�֑ؑO�����P��
        l_response.wlimitBefChgLimitPrice =
            WEB3EquityDataAdapter.getWLimitBefSwitchPrice(l_orderUnit);
        //���X�|���X.W�w�l�p�֑ؑO���s����
        l_response.wlimitBefChgExecCondType =
            WEB3EquityDataAdapter.getWLimitBefSwitchExecCondType(l_orderUnit);
        //���X�|���X.�����������敪
        l_response.orgOrderCondType = l_orderUnitRow.getOrgOrderConditionType();
        //���X�|���X.�����������P��
        if (l_orderUnitRow.getOrgStopOrderPriceIsNull())
        {
            l_response.orgOrderCondPrice = null;
        }
        else
        {
            l_response.orgOrderCondPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getOrgStopOrderPrice());
        }
        //���X�|���X.�������������Z�q
        l_response.orgOrderCondOperator = l_orderUnitRow.getOrgOrderCondOperator();
        //���X�|���X.���v�w�l�p�����P���敪
        String l_strOrgWlimitOrderPriceDiv =
            WEB3EquityDataAdapter.getOrgWLimitOrderPriceDiv(l_orderUnit);
        l_response.orgWlimitOrderPriceDiv = l_strOrgWlimitOrderPriceDiv;
        //���X�|���X.���v�w�l�p�����P��:��W�w�l�p�����P���敪��"�w�l"�̏ꍇ�̂݁A
        // �����f�[�^�A�_�v�^.get���v�w�l�p�����P��(�����P��)�̖߂�l���Z�b�g�B
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWlimitOrderPriceDiv))
        {
            l_response.orgWlimitPrice =
                WEB3EquityDataAdapter.getOrgWLimitOrderPrice(l_orderUnit);
        }
        //���X�|���X.���v�w�l�p���s����
        l_response.orgWlimitExecCondType =
            WEB3EquityDataAdapter.getOrgWLimitExecCondType(l_orderUnit);
        //���X�|���X.�����敪
        l_response.currentPriceDiv = l_strQuoteTypeDiv;
        //���X�|���X.�����i���ݒl�j
        l_response.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);
        //���X�|���X.�O����
        l_response.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);
        //���X�|���X.������ԁi�������\���ԁj
        l_response.currentPriceTime = l_currentPriceTime;
        
        //��񍀖ڂ̐ݒ�d�l�́A�ȉ��̒ʂ�B
        //�@@���ݒl�F�@@�擾�������������������.get���ݒl()�̖߂�l���Z�b�g
        l_response.boardCurrentPrice = l_productQuote.getBoardCurrentPrice();

        //�@@���ݒl�����F�@@�擾�������������������.get���ݒl����()�̖߂�l���Z�b�g
        l_response.boardCurrentPriceTime = l_productQuote.getBoardCurrentPriceTime();

        //�@@���ݒl�敪�F�@@�擾�������������������.get���ݒl�敪()�̖߂�l���Z�b�g
        l_response.boardCurrentPriceDiv = l_productQuote.getBoardCurrentPriceDiv();

        //�@@���ݒl�O����F�@@�擾�������������������.get���ݒl�O����()�̖߂�l���Z�b�g
        l_response.boardChange = l_productQuote.getBoardChange();

        //�@@�o�����F�@@�擾�������������������.get�o����()�̖߂�l���Z�b�g
        l_response.volume = l_productQuote.getVolume();

        //�@@�o���������F�@@�擾�������������������.get�o��������()�̖߂�l���Z�b�g
        l_response.volumeTime = l_productQuote.getVolumeTime();

        //�@@���C�z�l�^�C�g���敪�F�@@�擾�������������������.get���C�z�l�^�C�g���敪()�̖߂�l���Z�b�g
        l_response.askPriceTitle = l_productQuote.getAskPriceTitle();

        //�@@���C�z�l�F�@@�擾�������������������.get���C�z�l()�̖߂�l���Z�b�g
        l_response.askPrice = l_productQuote.getAskPrice();

        //�@@���C�z�l�����F�@@�擾�������������������.get���C�z�l����()�̖߂�l���Z�b�g
        l_response.askPriceTime = l_productQuote.getAskPriceTime();

        //�@@���C�z�l�^�C�g���敪�F�@@�擾�������������������.get���C�z�l�^�C�g���敪()�̖߂�l���Z�b�g
        l_response.bidPriceTitle = l_productQuote.getBidPriceTitle();

        //�@@���C�z�l�F�@@�擾�������������������.get���C�z�l()�̖߂�l���Z�b�g
        l_response.bidPrice = l_productQuote.getBidPrice();

        //�@@���C�z�l�����F�@@�擾�������������������.get���C�z�l����()�̖߂�l���Z�b�g
        l_response.bidPriceTime = l_productQuote.getBidPriceTime();

        //�@@��l�i�F�@@�擾�������������������.get��l�i()�̖߂�l���Z�b�g
        l_response.basePrice = l_productQuote.getBasePrice();
        
        //���X�|���X.���ӕ����\���敪
        //���X�|���X.�a����s���z
        if (OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_orderType))
        {
            l_response.attentionObjectionType = l_tpResult.getAttentionObjectionType();
            if (WEB3TPResultAttentionObjectionTypeDef.LACK_ACC_BAL_ATTENTION.equals(
                l_tpResult.getAttentionObjectionType()))
            {
                l_response.accountBalanceInsufficiency =
                    WEB3StringTypeUtility.formatNumber(l_tpResult.getLackAccountBalance());
            }
        }

        // �������ϗ��R
        l_response.forcedSettleReason = l_orderUnitRow.getForcedSettleReasonType();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit����)<BR>
     * �M�p����̒���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p�������T�[�r�X�jsubmit�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �M�p���������N�G�X�g�I�u�W�F�N�g
     * @@return WEB3MarginCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4058083503AC
     */
    protected WEB3MarginCancelCompleteResponse submitOrder(WEB3MarginCancelCompleteRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3MarginCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1. validate()
        l_request.validate();
        
        //1.2. get�⏕����()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
                
        //1.3. get�㗝���͎�()
        Trader l_trader = this.getTrader();
        
        //1.4. ��������������e()
        long l_lngOrderId = Long.parseLong(l_request.id);
        WEB3EquityCancelOrderSpec l_cancelOrderSpec =
            new WEB3EquityCancelOrderSpec(l_lngOrderId, l_trader);
        
        //1.5. getOrderUnits()
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        //1.6. getMarket()
        Market l_market = null;
        try
        {
            WEB3GentradeFinObjectManager l_objectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            l_market = l_objectManager.getMarket(l_orderUnitRow.getMarketId());
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(),
                l_nfe);
        }
        
        //1.10. get������()
        if (l_request.checkDate == null)
        {
            l_request.checkDate =
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }
        WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        
        //1.11. validate�����������()
        OrderValidationResult l_result =
            l_orderManager.validateCancelOrder(l_subAccount, l_cancelOrderSpec);
        if (l_result.getProcessingResult().isFailedResult())
        {
            throw new WEB3BaseException(
                l_result.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.12. getOrderType()
        OrderTypeEnum l_orderType = l_orderUnit.getOrderType();
        
        // ��n���
        double l_dblNetAmount = 0D;
        // ��肪����ꍇ
        if (l_orderUnit.isUnexecuted() == false)
        {
            // �g�����������}�l�[�W��.get��������n���
            l_dblNetAmount = l_orderManager.getEstimateDeliveryAmountForClose(l_orderUnit);
        }
        // ��肪�Ȃ��ꍇ
        else
        {
            // �����P��.�T�Z��n���
            l_dblNetAmount = l_orderUnitRow.getEstimatedPrice();
        }
        
        //�Z�L�����e�B�T�[�r�X���擾
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class); 
        // �����o�H�敪�擾
        String l_strOrderRootDiv =
            l_opLoginSec.getSessionProperty(
                WEB3SessionAttributeDef.ORDER_ROOT_DIV);
                
        //1.13. �M�p����X�V�C���^�Z�v�^()
        WEB3MarginCancelUpdateInterceptor l_updateInterceptor =
            new WEB3MarginCancelUpdateInterceptor(
                l_dblNetAmount,
                l_strOrderRootDiv,
                (WEB3GentradeTrader)this.getTrader());
        
        //1.14. setThreadLocalPersistenceEventInterceptor()
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
        
        //1.15. submit���������������()
        OrderSubmissionResult l_submissionResult =
            l_orderManager.submitCancelOrder(
                l_subAccount,
                l_cancelOrderSpec,
                l_request.password,
                true);
        if (l_submissionResult.getProcessingResult().isFailedResult())
        {            
            throw new WEB3BusinessLayerException(
                l_submissionResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME);
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
        
        if (OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_orderType))
        {
			//1.16. validate����]��()
            WEB3TPTradingPowerService l_tradingpowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            Object[] l_orderSpecIntercepters = { l_updateInterceptor };
            Object[] l_orderSpecs = { l_cancelOrderSpec };
        
            WEB3TPTradingPowerResult l_tpResult = null;
            l_tpResult = l_tradingpowerService.validateTradingPower(
                l_subAccount,
                l_orderSpecIntercepters,
                l_orderSpecs,
                l_orderType,
                true);
            
            if (l_tpResult.isResultFlg() == false)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        else
        {
            // �]�͍Čv�Z()
			WEB3TPTradingPowerService l_tradingpowerService =
			    (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
			l_tradingpowerService.reCalcTradingPower(l_subAccount);
        }
        
        //1.17. createResponse()
        WEB3MarginCancelCompleteResponse l_response =
            (WEB3MarginCancelCompleteResponse)l_request.createResponse();
        
        //1.18. �v���p�e�B�Z�b�g
        // ���X�|���X.�X�V����
        l_response.lastUpdatedTimestamp =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        // ���X�|���X.���ʔԍ�
        l_response.orderActionId = String.valueOf(l_orderUnitRow.getOrderId()); 
        l_response.succSettingFlag = l_blnIsCancelAllOrderUnit;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
