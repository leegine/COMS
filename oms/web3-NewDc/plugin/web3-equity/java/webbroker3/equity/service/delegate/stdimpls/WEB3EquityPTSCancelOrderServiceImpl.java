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
filename	WEB3EquityPTSCancelOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (PTS)����������������T�[�r�XImpl�iWEB3EquityPTSCancelOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/18 �И��� (���u) �V�K�쐬���f��No.1213
*/

package webbroker3.equity.service.delegate.stdimpls;


import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3TradingTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityCancelOrderInterceptor;
import webbroker3.equity.WEB3EquityCancelOrderSpec;
import webbroker3.equity.WEB3EquityClientRequestService;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityPTSOrderManager;
import webbroker3.equity.WEB3EquityPTSTradingTimeManagement;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.define.WEB3EquityWlimitOrderPriceDivDef;
import webbroker3.equity.message.WEB3EquityCancelCompleteRequest;
import webbroker3.equity.message.WEB3EquityCancelCompleteResponse;
import webbroker3.equity.message.WEB3EquityCancelConfirmRequest;
import webbroker3.equity.message.WEB3EquityCancelConfirmResponse;
import webbroker3.equity.service.delegate.WEB3EquityPTSCancelOrderService;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * ((PTS)����������������T�[�r�XImpl)<BR>
 * (PTS)����������������T�[�r�X�����N���X<BR>
 *
 * @@author �И���
 * @@version 1.0
 */
public class WEB3EquityPTSCancelOrderServiceImpl
    extends WEB3EquityClientRequestService
    implements WEB3EquityPTSCancelOrderService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityPTSCancelOrderServiceImpl.class);

    /**
     * @@roseuid 4766071F03CF
     */
    public WEB3EquityPTSCancelOrderServiceImpl()
    {

    }

    /**
     * (PTS)��������������������s����B<BR>
     * <BR>
     * �i�V�X�e���������j�K�C�h 4.4.�Ɩ����W�b�N�@@�Q�Ɓj<BR>
     * <BR>
     * �P�j�@@���{���\�b�h����<BR>
     * �@@�P�|�P�j�@@���N�G�X�g�f�[�^���u����������������m�F���N�G�X�g�v�̏ꍇ<BR>
     * �@@�@@this.validate�������()���R�[������B<BR>
     * <BR>
     * �@@�P�|�Q�j�@@���N�G�X�g�f�[�^���u����������������������N�G�X�g�v�̏ꍇ<BR>
     * �@@�@@this.submit�������()���R�[������B<BR>
     * <BR>
     * �Q�j�@@���\�b�h�̖߂�l��ԋp����B<BR>
     * @@param l_request - (���̓f�[�^)<BR>
     * �N���C�A���g����̓��̓f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47424C0902A0
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        WEB3GenResponse l_response = null;
        //���{���\�b�h����
        //���N�G�X�g�f�[�^���u����������������m�F���N�G�X�g�v�̏ꍇ
        if (l_request instanceof WEB3EquityCancelConfirmRequest)
        {
            WEB3EquityCancelConfirmRequest l_cancelConfirmRequest =
                (WEB3EquityCancelConfirmRequest)l_request;
            //this.validate�������()���R�[������B
            l_response = this.validateCancelOrder(l_cancelConfirmRequest);
        }
        //���N�G�X�g�f�[�^���u����������������������N�G�X�g�v�̏ꍇ
        else if (l_request instanceof WEB3EquityCancelCompleteRequest)
        {
            WEB3EquityCancelCompleteRequest l_cancelCompleteRequest =
                (WEB3EquityCancelCompleteRequest)l_request;
            l_response = this.submitCancelOrder(l_cancelCompleteRequest);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }
        //�T�[�r�X���\�b�h�̖߂�l��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�������)<BR>
     * �iPTS�j������������������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u(PTS)������������v�Q��<BR>
     * @@param l_request - (���̓f�[�^)<BR>
     * �N���C�A���g����̓��̓f�[�^<BR>
     * @@return WEB3EquityCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 47396A010133
     */
    protected WEB3EquityCancelCompleteResponse submitCancelOrder(WEB3EquityCancelCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitCancelOrder(WEB3EquityCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate
        l_request.validate();
        //validate������t�\
        WEB3EquityPTSTradingTimeManagement.validateOrderAccept();
        //�����h�c : ���N�G�X�g�f�[�^.ID
        long l_lngOrderId = Long.parseLong(l_request.id);
        //��������������e
        WEB3EquityCancelOrderSpec l_cancelOrderSpec =
            new WEB3EquityCancelOrderSpec(l_lngOrderId, this.getTrader());

        //get�⏕����
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        //get������(�m�F�������� : Date)
        //�m�F�������� : ���N�G�X�g�f�[�^.�m�F��������
        if (l_request.checkDate == null)
        {
            l_request.checkDate =
                WEB3EquityPTSTradingTimeManagement.getOrderBizDate();
        }
        WEB3EquityPTSTradingTimeManagement.getOrderBizDate(l_request.checkDate);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        //PTS�����}�l�[�W��
        WEB3EquityPTSOrderManager l_orderManager =
            (WEB3EquityPTSOrderManager)l_tradingModule.getOrderManager();
        //validatePTS�������
        EqTypeOrderValidationResult l_validationResult =
            l_orderManager.validatePTSCancelOrder(l_subAccount, l_cancelOrderSpec);
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            ProcessingResult l_processingResult = l_validationResult.getProcessingResult();
            log.debug("ProcessingResult() = " + l_processingResult);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                l_processingResult.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_processingResult.getErrorInfo().getErrorMessage());
        }

        //getOrderUnits(����ID : long)
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        //����t���[�F�@@��肪����ꍇ�i�����P��.isUnexecuted( )==false�j
        double l_dblEstimatedPrice;
        if (!l_orderUnit.isUnexecuted())
        {
            //get��������n���(�����P��)
            l_dblEstimatedPrice = l_orderManager.getEstimateDeliveryAmountForClose(l_orderUnit);
        }
        else
        {
            //����Ώۂ̒����P��.�T�Z��n���
            l_dblEstimatedPrice = l_orderUnitRow.getEstimatedPrice();
        }

        //������������C���^�Z�v�^()
        OpLoginSecurityService l_securityService =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);
        String l_strOrderRootDiv =
            l_securityService.getSessionProperty(
                WEB3SessionAttributeDef.ORDER_ROOT_DIV);
        WEB3EquityCancelOrderInterceptor l_cancelInterceptor =
            new WEB3EquityCancelOrderInterceptor(
                l_dblEstimatedPrice,
                l_strOrderRootDiv,
                (WEB3GentradeTrader)this.getTrader());

        //setThreadLocalPersistenceEventInterceptor()
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_cancelInterceptor);

        //submit���������������()
        EqTypeOrderSubmissionResult l_orderSubmissionResult =
            l_orderManager.submitCancelOrder(
                l_subAccount,
                l_cancelOrderSpec,
                l_request.password,
                true);
        if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
        {
            ProcessingResult l_processingResult = l_orderSubmissionResult.getProcessingResult();
            log.debug("ProcessingResult() = " + l_processingResult);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                l_processingResult.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_processingResult.getErrorInfo().getErrorMessage());
        }

        //�]�͍Čv�Z()
        WEB3TPTradingPowerService l_tradingpowerService =
        (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        l_tradingpowerService.reCalcTradingPower(l_subAccount);

        //createResponse()
        WEB3EquityCancelCompleteResponse l_completeResponse =
            (WEB3EquityCancelCompleteResponse)l_request.createResponse();

        //�v���p�e�B�Z�b�g
        // ���X�|���X.�X�V����
        l_completeResponse.lastUpdatedTimestamp = l_orderUnitRow.getLastUpdatedTimestamp();
        // ���X�|���X.���ʔԍ�
        l_completeResponse.orderActionId = String.valueOf(l_orderUnitRow.getOrderId());
        // ���X�|���X.�A�������ݒ�t���O
        l_completeResponse.succSettingFlag = false;

        log.exiting(STR_METHOD_NAME);
        return l_completeResponse;
    }

    /**
     * (validate�������)<BR>
     * �iPTS�j��������R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}�u(PTS)��������m�F�v�Q��<BR>
     * @@param l_request - (���̓f�[�^)<BR>
     * �N���C�A���g����̓��̓f�[�^<BR>
     * @@return WEB3EquityCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 473963B40296
     */
    protected WEB3EquityCancelConfirmResponse validateCancelOrder(WEB3EquityCancelConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateCancelOrder(WEB3EquityCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate()
        l_request.validate();

        //get�㗝���͎�()
        Trader l_trader = this.getTrader();

        //get�⏕����
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPTSOrderManager l_orderManager =
            (WEB3EquityPTSOrderManager)l_tradingModule.getOrderManager();
        long l_lngOrderId = Long.parseLong(l_request.id);

        //validate������t�\()
        WEB3EquityPTSTradingTimeManagement.validateOrderAccept();

        //��������������e()
        WEB3EquityCancelOrderSpec l_cancelOrderSpec =
            new WEB3EquityCancelOrderSpec(l_lngOrderId, l_trader);

        //validatePTS�������(�⏕����, ��������������e)
        EqTypeOrderValidationResult l_validationResult =
            (EqTypeOrderValidationResult)l_orderManager.validatePTSCancelOrder(l_subAccount, l_cancelOrderSpec);
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            ProcessingResult l_processingResult = l_validationResult.getProcessingResult();
            log.debug("ProcessingResult() = " + l_processingResult);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                l_processingResult.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_processingResult.getErrorInfo().getErrorMessage());
        }

        //get�s��ǌx���s��
        String[] l_strTradeCloseMarkets =
            WEB3EquityPTSTradingTimeManagement.getTradeCloseMarket(
                l_subAccount.getWeb3GenBranch(),
                ProductTypeEnum.EQUITY,
                WEB3MarginTradingDivDef.DEFAULT);

        //createResponse()
        WEB3EquityCancelConfirmResponse l_confirmResponse =
            (WEB3EquityCancelConfirmResponse)l_request.createResponse();

        //getOrderUnits()
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        //���蒍���̏ꍇ�̂ݎ��s
        double l_dblEstimatedBookPrice = 0.0D;
        OrderTypeEnum l_orderType = l_orderUnit.getOrderType();
        if (OrderTypeEnum.EQUITY_SELL.equals(l_orderType))
        {
            WEB3EquityBizLogicProvider l_bizLogicProvider =
                (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
            l_dblEstimatedBookPrice =
                l_bizLogicProvider.calcEstimatedBookPrice(
                    l_orderUnitRow.getProductId(),
                    l_subAccount,
                    l_orderUnit.getTaxType());
        }

        Market l_market = null;
        try
        {
            WEB3GentradeFinObjectManager l_objectManager =
              (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            l_market = l_objectManager.getMarket(l_orderUnitRow.getMarketId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //getTradedProduct()
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
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //get�\���p�������()
        WEB3EquityProductQuote l_productQuote = null;
        l_productQuote =
            l_productManager.getDisplayEquityProductQuote(
                l_tradedProduct,
                l_subAccount);

        //�v���p�e�B�Z�b�g
        //���X�|���X.�m�F��������
        l_confirmResponse.checkDate = WEB3EquityPTSTradingTimeManagement.getOrderBizDate();
        //���X�|���X.�T�Z��n���
        l_confirmResponse.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getEstimatedPrice());
        //���X�|���X.����I���x���s��R�[�h�ꗗ
        l_confirmResponse.messageSuspension = l_strTradeCloseMarkets;
        //���X�|���X.�����R�[�h
        l_confirmResponse.productCode = l_product.getProductCode();
        //���X�|���X.������
        EqtypeProductRow l_productRow = (EqtypeProductRow)l_product.getDataSourceObject();
        l_confirmResponse.productName = l_productRow.getStandardName();
        //���X�|���X.�s��R�[�h
        l_confirmResponse.marketCode = l_market.getMarketCode();
        //���X�|���X.�����敪
        l_confirmResponse.taxType = WEB3EquityDataAdapter.getTaxType(l_orderUnit.getTaxType());
        //���X�|���X.����敪
        if (OrderTypeEnum.EQUITY_BUY.equals(l_orderType))
        {
            l_confirmResponse.tradingType = WEB3TradingTypeDef.BUY_ORDER;
        }
        else
        {
            l_confirmResponse.tradingType = WEB3TradingTypeDef.SELL_ORDER;
        }
        //���X�|���X.��������
        l_confirmResponse.orderQuantity =
            WEB3StringTypeUtility.formatNumber(l_orderUnit.getQuantity());
        //���X�|���X.���o������
        if (l_orderUnitRow.getExecutedQuantityIsNull())
        {
            l_confirmResponse.partContQuantity = null;
        }
        else
        {
            l_confirmResponse.partContQuantity =
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getExecutedQuantity());
        }
        //���X�|���X.�����P���敪
        if (l_orderUnit.isMarketOrder())
        {
            l_confirmResponse.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            //���X�|���X.�����P��
            l_confirmResponse.limitPrice = null;
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
        boolean l_blnIsCarriedOrderUnit = l_orderManager.isCarriedOrderUnit(l_orderUnit);
        if (!l_blnIsCarriedOrderUnit)
        {
            l_confirmResponse.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
        }
        else
        {
            l_confirmResponse.expirationDateType = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
        }
        //���X�|���X.�����L������
        if (l_blnIsCarriedOrderUnit)
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
                l_confirmResponse.wLimitOrderPriceDiv =
                    WEB3EquityWlimitOrderPriceDivDef.WLIMIT_ORDER_PRICE_DIV_MARKET_PRICE;
            }
            else
            {
                l_confirmResponse.wLimitOrderPriceDiv =
                    WEB3EquityWlimitOrderPriceDivDef.WLIMIT_ORDER_PRICE_DIV_LIMIT_PRICE;
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
}
@
