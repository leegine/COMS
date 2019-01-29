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
filename	WEB3EquityPTSOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (PTS)�������������T�[�r�XImpl�iWEB3EquityPTSOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/18 �И��� (���u) �V�K�쐬���f��No.1215,No.1256,No.1267,No.1270,No.1272
Revision History : 2008/01/22 ��іQ (���u) ���f�� No.1289
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityClientRequestService;
import webbroker3.equity.WEB3EquityEstimatedDeliveryPrice;
import webbroker3.equity.WEB3EquityNewCashBasedOrderSpec;
import webbroker3.equity.WEB3EquityOrderManagerPersistenceEventInterceptor;
import webbroker3.equity.WEB3EquityPTSOrderManager;
import webbroker3.equity.WEB3EquityPTSTradingTimeManagement;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.message.WEB3EquityBuyCompleteRequest;
import webbroker3.equity.message.WEB3EquityBuyCompleteResponse;
import webbroker3.equity.message.WEB3EquityBuyConfirmRequest;
import webbroker3.equity.message.WEB3EquityBuyConfirmResponse;
import webbroker3.equity.message.WEB3EquityCommissionInfoUnit;
import webbroker3.equity.message.WEB3EquityCommonRequest;
import webbroker3.equity.message.WEB3EquitySellCompleteRequest;
import webbroker3.equity.message.WEB3EquitySellCompleteResponse;
import webbroker3.equity.message.WEB3EquitySellConfirmRequest;
import webbroker3.equity.message.WEB3EquitySellConfirmResponse;
import webbroker3.equity.service.delegate.WEB3EquityOrderRequestAdapter;
import webbroker3.equity.service.delegate.WEB3EquityPTSOrderService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.tradingpower.define.WEB3TPResultAttentionObjectionTypeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * ((PTS)�������������T�[�r�XImpl)<BR>
 * (PTS)�������������T�[�r�X�����N���X<BR>
 *
 * @@author �И���
 * @@version 1.0
 */
public class WEB3EquityPTSOrderServiceImpl
    extends WEB3EquityClientRequestService
    implements WEB3EquityPTSOrderService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityPTSOrderServiceImpl.class);

    /**
     * @@roseuid 4766071F0352
     */
    public WEB3EquityPTSOrderServiceImpl()
    {

    }

    /**
     * (PTS)�����������������{����B <BR>
     * <BR>
     * �P�j�@@�����̊����������N�G�X�g�̃I�u�W�F�N�g�^���A<BR>
     * �@@�@@�@@�T�[�r�X���\�b�h�𔻒肵�R�[������B<BR>
     * <BR>
     * �Q�j�@@�T�[�r�X���\�b�h�̖߂�l��ԋp����B <BR>
     * @@param l_request - (���̓f�[�^)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4740EC250121
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
        //�����̊����������N�G�X�g�̃I�u�W�F�N�g�^���A
        //�T�[�r�X���\�b�h�𔻒肵�R�[������B
        if (l_request instanceof WEB3EquityBuyConfirmRequest
            || l_request instanceof WEB3EquitySellConfirmRequest)
        {
            l_response = this.validateOrder(l_request);
        }
        else if (l_request instanceof WEB3EquityBuyCompleteRequest
            || l_request instanceof WEB3EquitySellCompleteRequest)
        {
            l_response = this.submitOrder(l_request);
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
     * (validate����)<BR>
     * (PTS)���������������͊m�F���������{����B <BR>
     * <BR>
     * �V�[�P���X�}�u�iPTS�j���������������͊m�F�v�Q�ƁB<BR>
     * @@param l_request - (���̓f�[�^)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4740EB5D02EB
     */
    protected WEB3GenResponse validateOrder(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityCommonRequest l_commonRequest = (WEB3EquityCommonRequest)l_request;
        //validate()
        l_commonRequest.validate();

        //�����������N�G�X�g�A�_�v�^
        WEB3EquityOrderRequestAdapter l_requestAdaptor =
            WEB3EquityOrderRequestAdapter.create(l_request);

        //�s��R�[�h
        String l_strMarketCode = null;
        if (l_request instanceof WEB3EquityBuyConfirmRequest)
        {
            WEB3EquityBuyConfirmRequest l_buyConfirmRequest =
                (WEB3EquityBuyConfirmRequest)l_request;
            l_strMarketCode = l_buyConfirmRequest.marketCode;
        }
        else if (l_request instanceof WEB3EquitySellConfirmRequest)
        {
            WEB3EquitySellConfirmRequest l_sellConfirmRequest =
                (WEB3EquitySellConfirmRequest)l_request;
            l_strMarketCode = l_sellConfirmRequest.marketCode;
        }

        //���������F�@@���N�G�X�g.���������敪
        String l_strOrderCondType = l_commonRequest.orderCondType;
        String l_strOrderCondOperator = null;
        double l_dblStopOrderBasePrice = 0.0D;
        double l_dblWLimitOrderChange = 0.0D;
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderCondType))
        {
            //�����������Z�q
            l_strOrderCondOperator = null;
            //�t�w�l��l
            l_dblStopOrderBasePrice = 0.0D;
            //�iW�w�l�j�����w�l
            l_dblWLimitOrderChange = 0.0D;
        }
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderCondType))
        {
            //�����������Z�q
            l_strOrderCondOperator = l_commonRequest.stopOrderCondOperator;
            //�t�w�l��l
            l_dblStopOrderBasePrice = Double.parseDouble(l_commonRequest.stopOrderCondPrice);
            //�iW�w�l�j�����w�l
            l_dblWLimitOrderChange = 0.0D;
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderCondType))
        {
            //�����������Z�q
            l_strOrderCondOperator = l_commonRequest.wlimitOrderCondOperator;
            //�t�w�l��l
            l_dblStopOrderBasePrice = Double.parseDouble(l_commonRequest.wlimitOrderCondPrice);
            //�iW�w�l�j�����w�l
            if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_commonRequest.wLimitOrderPriceDiv))
            {
                l_dblWLimitOrderChange = Double.parseDouble(l_commonRequest.wLimitPrice);
            }
            else
            {
                l_dblWLimitOrderChange = 0.0D;
            }
        }

        //get�⏕����()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        //����������
        Timestamp l_tsExpirationDate = new Timestamp(WEB3EquityPTSTradingTimeManagement.getOrderBizDate().getTime());
        //�����������e
        WEB3EquityNewCashBasedOrderSpec l_orderSpec =
            WEB3EquityNewCashBasedOrderSpec.createPTSEquityOrderSpec(
                l_subAccount.getInstitution().getInstitutionCode(),
                (WEB3GentradeTrader)this.getTrader(),
                l_strMarketCode,
                l_requestAdaptor.getProductCode(),
                Double.parseDouble(l_commonRequest.orderQuantity),
                l_requestAdaptor.getPrice(),
                l_requestAdaptor.getExecCondType(),
                l_requestAdaptor.getTaxDivision(),
                l_tsExpirationDate,
                l_requestAdaptor.isSellOrder(),
                this.getLoginChannel(),
                l_commonRequest.priceCondType,
                l_strOrderCondType,
                l_strOrderCondOperator,
                l_dblStopOrderBasePrice,
                l_dblWLimitOrderChange,
                null);

        //set�萔�����i�R�[�h()
        l_orderSpec.setCommissionProductCode(WEB3CommisionProductCodeDef.LISTING_STOCK);
        //create�萔��()
        WEB3GentradeCommission l_commission =
            l_orderSpec.createCommission(
                l_subAccount.getWeb3GenBranch(),
                WEB3TransactionTypeSONARDef.MARKET_TRADING);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //PTS�����}�l�[�W��
        WEB3EquityPTSOrderManager l_orderManager =
            (WEB3EquityPTSOrderManager)l_tradingModule.getOrderManager();
        //validatePTS����
        EqTypeNewOrderValidationResult l_validationResult =
            l_orderManager.validatePTSOrder(l_subAccount, l_orderSpec);
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

        //�g���v���_�N�g�}�l�[�W��
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        //�������
        WEB3EquityTradedProduct l_tradedProduct = null;
        try
        {
            l_tradedProduct =
                (WEB3EquityTradedProduct)l_productManager.getTradedProduct(
                l_subAccount.getInstitution(),
                l_orderSpec.getProductCode(),
                l_orderSpec.getMarketCode());
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

        //calc�T�Z��n���()
        //�����͈ȉ��̒ʂ�ɐݒ肷��B
        //�萔�� : �����������e.get�萔��()
        //�w�l : �����������e.getLimitPrice()
        //�iW�w�l�j�����w�l : �����������e.get�iW�w�l�j�����w�l()
        //�t�w�l��l : �����������e.get�t�w�l��l()
        //���s���� : �����������e.getExecConditionType()
        //�iW�w�l�j���s���� : �����������e.get�i�v�w�l�j���s����( )
        //�l�i���� : �����������e.get�l�i����()
        //�������� : �����������e.get��������()
        //�m�F���擾���� : null
        //is�X�g�b�v�����L�� : false�i�Œ�j
        //�⏕���� : this.get�⏕����()
        //������� : �g���v���_�N�g�}�l�[�W��.getTradedProduct()
        //���� : �����������e.getQuantity( )
        //is������ : �����������e.isSellOrder( )
        //��萔�� : 0
        //���v�����z : 0
        //isSkip���z�`�F�b�N : false�i�Œ�j
        WEB3EquityEstimatedDeliveryPrice l_estimatedDeliveryPrice =
            l_orderManager.calcEstimateDeliveryAmount(
                l_commission,
                l_orderSpec.getLimitPrice(),
                l_orderSpec.getWLimitPriceChange(),
                l_orderSpec.getStopLimitPriceBasePrice(),
                l_orderSpec.getExecConditionType(),
                l_orderSpec.getWlimitExecCondType(),
                l_orderSpec.getPriceConditionType(),
                l_orderSpec.getOrderCond(),
                null,
                false,
                l_subAccount,
                l_tradedProduct,
                l_orderSpec.getQuantity(),
                l_orderSpec.isSellOrder(),
                0.0D,
                0.0D,
                false);

        //set�����P��()
        l_orderSpec.setOrderUnitPrice(l_estimatedDeliveryPrice.getCalcUnitPrice());
        //set�T�Z��n���()
        l_orderSpec.setEstimateDeliveryAmount(l_estimatedDeliveryPrice.getEstimateDeliveryAmount());

        //getMarket
        Market l_market = null;
        try
        {
            WEB3GentradeFinObjectManager l_objectManager =
              (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            l_market = l_objectManager.getMarket(l_orderSpec.getInstitutionCode(), l_strMarketCode);
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

        //validatePTS�s��ʎ���\������z
        //���X�F�@@this.get�⏕����.get����X()
        //�s��F�@@getMarket()�̖߂�l
        //�S����������F�@@�萔��.get���o��v�Z�p���()
        l_orderManager.validatePTSMarketMaxHandlingPrice(
            l_subAccount.getWeb3GenBranch(),
            l_market,
            l_commission.getExpensesCalcAmount());

        //���������C���^�Z�v�^
        WEB3EquityOrderManagerPersistenceEventInterceptor l_eventInterceptor =
            new WEB3EquityOrderManagerPersistenceEventInterceptor();

        //set�����������e(�����������e)
        l_eventInterceptor.setEquityOrderSpec(l_orderSpec);

        WEB3TPTradingPowerService l_tradingpowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

        //validate����]��()
        Object[] l_orderSpecIntercepters = { l_eventInterceptor };
        Object[] l_orderSpecs = { l_orderSpec };
        OrderTypeEnum l_orderType;
        if (l_orderSpec.isSellOrder())
        {
            l_orderType = OrderTypeEnum.EQUITY_SELL;
        }
        else
        {
            l_orderType = OrderTypeEnum.EQUITY_BUY;
        }
        WEB3TPTradingPowerResult l_tpResult =
            l_tradingpowerService.validateTradingPower(
                l_subAccount,
                l_orderSpecIntercepters,
                l_orderSpecs,
                l_orderType,
                false);

        //throw���������]�̓G���[�ڍ׏��
        l_orderManager.throwEquityTpErrorDetailInfo(
            l_tpResult,
            l_orderType,
            l_orderSpec.getQuantity());

        //createNewOrderId()
        long l_lngOrderId = l_orderManager.createNewOrderId();

        //createResponse()
        WEB3GenResponse l_response = l_request.createResponse();

        //get�s��ǌx���s��()
        String[] l_strMessageSuspensions =
            WEB3EquityPTSTradingTimeManagement.getTradeCloseMarket(
                (WEB3GentradeBranch)l_subAccount.getWeb3GenBranch(),
                ProductTypeEnum.EQUITY,
                WEB3MarginTradingDivDef.DEFAULT);

        //is�C���T�C�_�[�x���\��()
        boolean l_blnIsInsiderMessageSuspension =
            l_orderManager.isInsiderMessageSuspension(
                l_subAccount,
                l_tradedProduct.getProduct().getProductId());

        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();

        //����t���[�F�@@���蒍���i�����������N�G�X�g�A�_�v�^.is������() == true�j�̏ꍇ�̂ݎ��s
        String l_strEstimatedBookPrice = null;
        if (l_requestAdaptor.isSellOrder())
        {
            //getAsset
            WEB3EquitySellConfirmRequest l_sellConfirmRequest = (WEB3EquitySellConfirmRequest)l_request;
            Asset l_asset = null;
            try
            {
                l_asset = l_positionManager.getAsset(Long.parseLong(l_sellConfirmRequest.id));
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
            //calc�T�Z�뉿�P��
            WEB3EquityBizLogicProvider l_bizLogicProvider =
                (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
            double l_dblEstimatedBookPrice =
                l_bizLogicProvider.calcEstimatedBookPrice(
                    l_asset.getProduct().getProductId(),
                    l_subAccount,
                    l_asset.getTaxType());
            l_strEstimatedBookPrice =
                WEB3StringTypeUtility.formatNumber(l_dblEstimatedBookPrice);
        }

        //�v���p�e�B�Z�b�g
        if (l_response instanceof WEB3EquityBuyConfirmResponse)
        {
            WEB3EquityBuyConfirmResponse l_buyConfirmResponse =
                (WEB3EquityBuyConfirmResponse)l_response;
            //���X�|���X.�m�F��������
            l_buyConfirmResponse.checkDate = WEB3EquityPTSTradingTimeManagement.getOrderBizDate();
            //���X�|���X.�T�Z��n���
            l_buyConfirmResponse.estimatedPrice =
                WEB3StringTypeUtility.formatNumber(l_orderSpec.getEstimateDeliveryAmount());
            //���X�|���X.����I���x���s��R�[�h�ꗗ
            l_buyConfirmResponse.messageSuspension = l_strMessageSuspensions;
            // �萔���R�[�X
            WEB3EquityCommissionInfoUnit l_commissionInfo = new WEB3EquityCommissionInfoUnit();
            l_commissionInfo.commissionCourse = l_commission.getCommissionCourseDiv();
            // �萔��
            l_commissionInfo.commission =
                WEB3StringTypeUtility.formatNumber(l_estimatedDeliveryPrice.getCommissionFee());
            // �萔�������
            l_commissionInfo.commissionConsumptionTax =
                WEB3StringTypeUtility.formatNumber(l_estimatedDeliveryPrice.getCommissionFeeTax());
            //���X�|���X.�萔�����
            l_buyConfirmResponse.commissionInfo = l_commissionInfo;
            //���X�|���X.�m�F���P��
            //calc�T�Z��n���( )�̖߂�l.get�m�F���擾����( )�̖߂�l
            l_buyConfirmResponse.checkPrice = l_estimatedDeliveryPrice.getCheckGetCurrentPrice();
            //���X�|���X.����ID
            l_buyConfirmResponse.orderId = String.valueOf(l_lngOrderId);
            //���X�|���X.�C���T�C�_�[�x���\���t���O
            l_buyConfirmResponse.insiderWarningFlag = l_blnIsInsiderMessageSuspension;
            //���X�|���X.���ӕ����\���敪
            //���X�|���X.�a����s���z
            //����]�͌���.get���ӕ����\���敪 == "3�F�a����s�����ӕ����\��" �܂���
            //����]�͌���.get���ӕ����\���敪 == "1�F�����s�����ӕ����\��"�̏ꍇ
            //����]�͌��ʃN���X�̓����ڂ��Z�b�g����
            if (l_tpResult != null)
            {
                l_buyConfirmResponse.attentionObjectionType = l_tpResult.getAttentionObjectionType();
                if (WEB3TPResultAttentionObjectionTypeDef.LACK_ACC_BAL_ATTENTION.equals(
                        l_tpResult.getAttentionObjectionType())
                    || WEB3TPResultAttentionObjectionTypeDef.LACK_CASH_AMT_ATTENTION.equals(
                        l_tpResult.getAttentionObjectionType()))
                {
                    l_buyConfirmResponse.accountBalanceInsufficiency =
                        WEB3StringTypeUtility.formatNumber(l_tpResult.getLackAccountBalance());
                }
            }
            //�s��R�[�h
            l_buyConfirmResponse.marketCode = l_strMarketCode;
            //���X�|���X.������
            EqtypeProductRow l_productRow = (EqtypeProductRow)l_tradedProduct.getProduct().getDataSourceObject();
            l_buyConfirmResponse.productName = l_productRow.getStandardName();
            // ���X�|���X.�����L������
            l_buyConfirmResponse.expirationDate = l_requestAdaptor.getExpirationDate();
        }
        else
        {
            WEB3EquitySellConfirmResponse l_sellConfirmResponse =
                (WEB3EquitySellConfirmResponse)l_response;
            //���X�|���X.�m�F��������
            Date l_bizDate = WEB3EquityPTSTradingTimeManagement.getOrderBizDate();
            l_sellConfirmResponse.checkDate = WEB3DateUtility.toDay(l_bizDate);
            //���X�|���X.�T�Z��n���
            l_sellConfirmResponse.estimatedPrice =
                WEB3StringTypeUtility.formatNumber(l_orderSpec.getEstimateDeliveryAmount());
            //���X�|���X.����I���x���s��R�[�h�ꗗ
            l_sellConfirmResponse.messageSuspension = l_strMessageSuspensions;
            // �萔���R�[�X
            WEB3EquityCommissionInfoUnit l_commissionInfo = new WEB3EquityCommissionInfoUnit();
            l_commissionInfo.commissionCourse = l_commission.getCommissionCourseDiv();
            // �萔��
            l_commissionInfo.commission =
                WEB3StringTypeUtility.formatNumber(l_estimatedDeliveryPrice.getCommissionFee());
            // �萔�������
            l_commissionInfo.commissionConsumptionTax =
                WEB3StringTypeUtility.formatNumber(l_estimatedDeliveryPrice.getCommissionFeeTax());
            //���X�|���X.�萔�����
            l_sellConfirmResponse.commissionInfo = l_commissionInfo;
            //���X�|���X.�m�F���P��
            //calc�T�Z��n���( )�̖߂�l.get�m�F���擾����( )�̖߂�l
            l_sellConfirmResponse.checkPrice = l_estimatedDeliveryPrice.getCheckGetCurrentPrice();
            //���X�|���X.����ID
            l_sellConfirmResponse.orderId = String.valueOf(l_lngOrderId);
            //���X�|���X.�C���T�C�_�[�x���\���t���O
            l_sellConfirmResponse.insiderWarningFlag = l_blnIsInsiderMessageSuspension;
            //���X�|���X.���ӕ����\���敪
            //���X�|���X.�a����s���z
            //����]�͌���.get���ӕ����\���敪 == "3�F�a����s�����ӕ����\��" �܂���
            //����]�͌���.get���ӕ����\���敪 == "1�F�����s�����ӕ����\��"�̏ꍇ
            //����]�͌��ʃN���X�̓����ڂ��Z�b�g����
            if (l_tpResult != null)
            {
                l_sellConfirmResponse.attentionObjectionType = l_tpResult.getAttentionObjectionType();
                if (WEB3TPResultAttentionObjectionTypeDef.LACK_ACC_BAL_ATTENTION.equals(
                        l_tpResult.getAttentionObjectionType())
                    || WEB3TPResultAttentionObjectionTypeDef.LACK_CASH_AMT_ATTENTION.equals(
                        l_tpResult.getAttentionObjectionType()))
                {
                    l_sellConfirmResponse.accountBalanceInsufficiency =
                        WEB3StringTypeUtility.formatNumber(l_tpResult.getLackAccountBalance());
                }
            }
            //�s��R�[�h
            l_sellConfirmResponse.marketCode = l_strMarketCode;
            //���X�|���X.�T�Z�뉿�P��
            l_sellConfirmResponse.estimatedBookPrice = l_strEstimatedBookPrice;
            // ���X�|���X.�����L������
            l_sellConfirmResponse.expirationDate = l_requestAdaptor.getExpirationDate();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit����)<BR>
     * (PTS)�������������o�^�X�V���������{����B <BR>
     * <BR>
     * �V�[�P���X�}�u�iPTS�j�������������o�^�X�V�v�Q�ƁB <BR>
     * @@param l_request - (���̓f�[�^)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4743D9E300E9
     */
    protected WEB3GenResponse submitOrder(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityCommonRequest l_commonRequest = (WEB3EquityCommonRequest)l_request;

        //validate()
        l_commonRequest.validate();

        //�����������N�G�X�g�A�_�v�^
        WEB3EquityOrderRequestAdapter l_requestAdaptor =
            WEB3EquityOrderRequestAdapter.create(l_request);

        //get������
        Date l_datCheckDate = null;
        //get�s��R�[�h( )
        String l_strMarketCode = null;
        if (l_request instanceof WEB3EquityBuyCompleteRequest)
        {
            WEB3EquityBuyCompleteRequest l_buyCompleteRequest =
                (WEB3EquityBuyCompleteRequest)l_request;
            if (l_buyCompleteRequest.checkDate == null)
            {
                l_buyCompleteRequest.checkDate =
                    WEB3EquityPTSTradingTimeManagement.getOrderBizDate();
            }
            l_datCheckDate = l_buyCompleteRequest.checkDate;
            l_strMarketCode = l_buyCompleteRequest.marketCode;
        }
        else if (l_request instanceof WEB3EquitySellCompleteRequest)
        {
            WEB3EquitySellCompleteRequest l_sellCompleteRequest =
                (WEB3EquitySellCompleteRequest)l_request;
            if (l_sellCompleteRequest.checkDate == null)
            {
                l_sellCompleteRequest.checkDate =
                    WEB3EquityPTSTradingTimeManagement.getOrderBizDate();
            }
            l_datCheckDate = l_sellCompleteRequest.checkDate;
            l_strMarketCode = l_sellCompleteRequest.marketCode;
        }
        WEB3EquityPTSTradingTimeManagement.getOrderBizDate(l_datCheckDate);

        //���������F�@@���N�G�X�g.���������敪
        String l_strOrderCondType = l_commonRequest.orderCondType;
        String l_strOrderCondOperator = null;
        double l_dblStopOrderBasePrice = 0.0D;
        double l_dblWLimitOrderChange = 0.0D;
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderCondType))
        {
            //�����������Z�q
            l_strOrderCondOperator = null;
            //�t�w�l��l
            l_dblStopOrderBasePrice = 0.0D;
            //�iW�w�l�j�����w�l
            l_dblWLimitOrderChange = 0.0D;
        }
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderCondType))
        {
            //�����������Z�q
            l_strOrderCondOperator = l_commonRequest.stopOrderCondOperator;
            //�t�w�l��l
            l_dblStopOrderBasePrice = Double.parseDouble(l_commonRequest.stopOrderCondPrice);
            //�iW�w�l�j�����w�l
            l_dblWLimitOrderChange = 0.0D;
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderCondType))
        {
            //�����������Z�q
            l_strOrderCondOperator = l_commonRequest.wlimitOrderCondOperator;
            //�t�w�l��l
            l_dblStopOrderBasePrice = Double.parseDouble(l_commonRequest.wlimitOrderCondPrice);
            //�iW�w�l�j�����w�l
            if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_commonRequest.wLimitOrderPriceDiv))
            {
                l_dblWLimitOrderChange = Double.parseDouble(l_commonRequest.wLimitPrice);
            }
            else
            {
                l_dblWLimitOrderChange = 0.0D;
            }
        }

        //get�⏕����()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        //����������
        Timestamp l_tsExpirationDate = new Timestamp(WEB3EquityPTSTradingTimeManagement.getOrderBizDate().getTime());
        //�����������e
        WEB3EquityNewCashBasedOrderSpec l_orderSpec =
            WEB3EquityNewCashBasedOrderSpec.createPTSEquityOrderSpec(
                l_subAccount.getInstitution().getInstitutionCode(),
                (WEB3GentradeTrader)this.getTrader(),
                l_strMarketCode,
                l_requestAdaptor.getProductCode(),
                Double.parseDouble(l_commonRequest.orderQuantity),
                l_requestAdaptor.getPrice(),
                l_requestAdaptor.getExecCondType(),
                l_requestAdaptor.getTaxDivision(),
                l_tsExpirationDate,
                l_requestAdaptor.isSellOrder(),
                this.getLoginChannel(),
                l_commonRequest.priceCondType,
                l_strOrderCondType,
                l_strOrderCondOperator,
                l_dblStopOrderBasePrice,
                l_dblWLimitOrderChange,
                null);

        //set�萔�����i�R�[�h()
        l_orderSpec.setCommissionProductCode(WEB3CommisionProductCodeDef.LISTING_STOCK);
        //create�萔��()
        WEB3GentradeCommission l_commission =
            l_orderSpec.createCommission(
                l_subAccount.getWeb3GenBranch(),
                WEB3TransactionTypeSONARDef.MARKET_TRADING);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //PTS�����}�l�[�W��
        WEB3EquityPTSOrderManager l_orderManager =
            (WEB3EquityPTSOrderManager)l_tradingModule.getOrderManager();
        //validatePTS����
        EqTypeNewOrderValidationResult l_validationResult =
            l_orderManager.validatePTSOrder(l_subAccount, l_orderSpec);
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

        //�g���v���_�N�g�}�l�[�W��
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        //�������
        WEB3EquityTradedProduct l_tradedProduct = null;
        try
        {
            l_tradedProduct =
                (WEB3EquityTradedProduct)l_productManager.getTradedProduct(
                l_subAccount.getInstitution(),
                l_orderSpec.getProductCode(),
                l_orderSpec.getMarketCode());
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

        //calc�T�Z��n���()
        //�����͈ȉ��̒ʂ�ɐݒ肷��B
        //�萔�� : �����������e.get�萔��()
        //�w�l : �����������e.getLimitPrice()
        //�iW�w�l�j�����w�l : �����������e.get�iW�w�l�j�����w�l()
        //�t�w�l��l : �����������e.get�t�w�l��l()
        //���s���� : �����������e.getExecConditionType()
        //�iW�w�l�j���s���� : �����������e.get�i�v�w�l�j���s����( )
        //�l�i���� : �����������e.get�l�i����()
        //�������� : �����������e.get��������()
        //�m�F���擾���� : null
        //is�X�g�b�v�����L�� : false�i�Œ�j
        //�⏕���� : this.get�⏕����()
        //������� : �g���v���_�N�g�}�l�[�W��.getTradedProduct()
        //���� : �����������e.getQuantity( )
        //is������ : �����������e.isSellOrder( )
        //��萔�� : 0
        //���v�����z : 0
        //isSkip���z�`�F�b�N : false�i�Œ�j
        WEB3EquityEstimatedDeliveryPrice l_estimatedDeliveryPrice =
            l_orderManager.calcEstimateDeliveryAmount(
                l_commission,
                l_orderSpec.getLimitPrice(),
                l_orderSpec.getWLimitPriceChange(),
                l_orderSpec.getStopLimitPriceBasePrice(),
                l_orderSpec.getExecConditionType(),
                l_orderSpec.getWlimitExecCondType(),
                l_orderSpec.getPriceConditionType(),
                l_orderSpec.getOrderCond(),
                null,
                false,
                l_subAccount,
                l_tradedProduct,
                l_orderSpec.getQuantity(),
                l_orderSpec.isSellOrder(),
                0.0D,
                0.0D,
                false);

        //set�����P��()
        l_orderSpec.setOrderUnitPrice(l_estimatedDeliveryPrice.getCalcUnitPrice());
        //set�T�Z��n���()
        l_orderSpec.setEstimateDeliveryAmount(l_estimatedDeliveryPrice.getEstimateDeliveryAmount());

        //getMarket
        Market l_market = null;
        try
        {
            WEB3GentradeFinObjectManager l_objectManager =
              (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            l_market = l_objectManager.getMarket(l_orderSpec.getInstitutionCode(), l_strMarketCode);
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

        //validatePTS�s��ʎ���\������z
        //���X�F�@@this.get�⏕����.get����X()
        //�s��F�@@getMarket()�̖߂�l
        //�S����������F�@@�萔��.get���o��v�Z�p���()
        l_orderManager.validatePTSMarketMaxHandlingPrice(
            l_subAccount.getWeb3GenBranch(),
            l_market,
            l_commission.getExpensesCalcAmount());

        //���������C���^�Z�v�^
        WEB3EquityOrderManagerPersistenceEventInterceptor l_eventInterceptor =
            new WEB3EquityOrderManagerPersistenceEventInterceptor();

        //set�����������e(�����������e)
        l_eventInterceptor.setEquityOrderSpec(l_orderSpec);

        WEB3TPTradingPowerService l_tradingpowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

        //validate����]��()
        Object[] l_orderSpecIntercepters = { l_eventInterceptor };
        Object[] l_orderSpecs = { l_orderSpec };
        OrderTypeEnum l_orderType;
        if (l_orderSpec.isSellOrder())
        {
            l_orderType = OrderTypeEnum.EQUITY_SELL;
        }
        else
        {
            l_orderType = OrderTypeEnum.EQUITY_BUY;
        }
        WEB3TPTradingPowerResult l_tpResult =
            l_tradingpowerService.validateTradingPower(
                l_subAccount,
                l_orderSpecIntercepters,
                l_orderSpecs,
                l_orderType,
                true);

        //throw���������]�̓G���[�ڍ׏��
        l_orderManager.throwEquityTpErrorDetailInfo(
            l_tpResult,
            l_orderType,
            l_orderSpec.getQuantity());

        //setThreadLocalPersistenceEventInterceptor
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_eventInterceptor);

        //submitNewCashBasedOrder
        long l_lngOrderId;
        String l_strPassward;
        if (l_commonRequest instanceof WEB3EquityBuyCompleteRequest)
        {
            if (((WEB3EquityBuyCompleteRequest)l_commonRequest).orderId == null)
            {
                ((WEB3EquityBuyCompleteRequest)l_commonRequest).orderId =
                    String.valueOf(l_orderManager.createNewOrderId());
            }
            l_lngOrderId = Long.parseLong(((WEB3EquityBuyCompleteRequest)l_commonRequest).orderId);
            l_strPassward = ((WEB3EquityBuyCompleteRequest)l_commonRequest).password;
        }
        else
        {
            if (((WEB3EquitySellCompleteRequest)l_commonRequest).orderId == null)
            {
                ((WEB3EquitySellCompleteRequest)l_commonRequest).orderId =
                    String.valueOf(l_orderManager.createNewOrderId());
            }
            l_lngOrderId = Long.parseLong(((WEB3EquitySellCompleteRequest)l_commonRequest).orderId);
            l_strPassward = ((WEB3EquitySellCompleteRequest)l_commonRequest).password;
        }
        EqTypeOrderSubmissionResult l_result =
            l_orderManager.submitNewCashBasedOrder(
                l_subAccount,
                l_orderSpec,
                l_lngOrderId,
                l_strPassward,
                true);
        if (l_result.getProcessingResult().isFailedResult())
        {
            ProcessingResult l_processingResult = l_result.getProcessingResult();
            log.debug("ProcessingResult() = " + l_processingResult);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                l_processingResult.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_processingResult.getErrorInfo().getErrorMessage());
        }

        //createResponse()
        WEB3GenResponse l_response = l_commonRequest.createResponse();

        //is�C���T�C�_�[�x���\��()
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        boolean l_blnIsInsiderMessageSuspension =
            l_orderManager.isInsiderMessageSuspension(
                l_subAccount,
                l_orderUnitRow.getProductId());

        //�v���p�e�B�Z�b�g
        if (l_response instanceof WEB3EquityBuyCompleteResponse)
        {
            WEB3EquityBuyCompleteResponse l_buyCompleteResponse =
                (WEB3EquityBuyCompleteResponse)l_response;
            //���X�|���X.�X�V����
            l_buyCompleteResponse.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
            //���X�|���X.���ʔԍ�
            l_buyCompleteResponse.orderActionId = ((WEB3EquityBuyCompleteRequest)l_request).orderId;
            //���X�|���X.�C���T�C�_�[�x���\���t���O
            l_buyCompleteResponse.insiderWarningFlag = l_blnIsInsiderMessageSuspension;
            //�s��R�[�h
            l_buyCompleteResponse.marketCode = l_strMarketCode;
            // ���X�|���X.�����L������
            l_buyCompleteResponse.expirationDate = l_requestAdaptor.getExpirationDate();
        }
        else
        {
            WEB3EquitySellCompleteResponse l_sellCompleteResponse =
                (WEB3EquitySellCompleteResponse)l_response;
            //���X�|���X.�X�V����
            l_sellCompleteResponse.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
            //���X�|���X.���ʔԍ�
            l_sellCompleteResponse.orderActionId = ((WEB3EquitySellCompleteRequest)l_request).orderId;
            //���X�|���X.�C���T�C�_�[�x���\���t���O
            l_sellCompleteResponse.insiderWarningFlag = l_blnIsInsiderMessageSuspension;
            //�s��R�[�h
            l_sellCompleteResponse.marketCode = l_strMarketCode;
            // ���X�|���X.�����L������
            l_sellCompleteResponse.expirationDate = l_requestAdaptor.getExpirationDate();
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
