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
filename	WEB3EquityOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������T�[�r�XImpl(WEB3EquityOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 ���j (���u) �V�K�쐬
Revesion History : 2004/07/12 羐� (���u) �C��
Revesion History : 2004/12/20 �������F(SRA) �c�Č��Ή��ɂ��C��
Revesion History : 2005/01/06 �����a��(SRA) JavaDoc�C��
Revesion History : 2006/11/02 ������@@(���u)���f��No.988,991,992
Revesion History : 2006/11/14 ������@@(���u)���f��No.1026
Revesion History : 2006/12/25 �đo�g(���u) ���f��No.1091,No.1097
Revesion History : 2007/01/17 ������@@(���u)���f��No.1107
Revesion History : 2007/06/14 �����q�@@(���u)���f��No.1173
Revesion History : 2007/08/07 ���n�m�@@(���u)���f��No.1192
Revesion History : 2007/12/04 ��іQ�@@(���u)���f��No.1227
Revesion History : 2007/12/10 ��іQ  (���u)���f��No.1240
Revesion History : 2008/10/06 ���� (���u) ���f��No.1323
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewCashBasedOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityClientRequestService;
import webbroker3.equity.WEB3EquityEstimatedDeliveryPrice;
import webbroker3.equity.WEB3EquityNewCashBasedOrderSpec;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityOrderManagerPersistenceEventInterceptor;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
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
import webbroker3.equity.service.delegate.WEB3EquityOrderService;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.tradingpower.define.WEB3TPTradingPowerErrorDivDef;
import webbroker3.tradingpower.define.WEB3TPResultAttentionObjectionTypeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3TradingTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;


/**
 * �i���������T�[�r�XImpl�j�B<BR>
 * <BR>
 * ���������������[�X�P�[�X�̃G���g���|�C���g
 * @@version 1.0
 */
public class WEB3EquityOrderServiceImpl
    extends WEB3EquityClientRequestService
    implements WEB3EquityOrderService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderServiceImpl.class);
    
    /**
     * @@roseuid 40A02D070251
     */
    public WEB3EquityOrderServiceImpl()
    {
    }

    /**
     * (validate����) <BR>
     * <BR>
     * �������������m�F���������{����B<BR>
     * �V�[�P���X�}�u�i���������T�[�r�X�j�������͊m�F�v�Q�ƁB<BR>
     * <BR>
     * @@param l_request - (���̓f�[�^) <BR>
     * �N���C�A���g����̃��N�G�X�g���b�Z�[�W���w�肷��B <BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 3FFD338F00FA
     */
    protected WEB3GenResponse validateOrder(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityCommonRequest l_commonRequest = (WEB3EquityCommonRequest)l_request;
        
        //1.1. validate()
        l_commonRequest.validate();
        
        //1.2. get�⏕����()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //1.3. get�㗝���͎�()
        WEB3GentradeTrader l_trader = (WEB3GentradeTrader)this.getTrader();
        
        //1.4. get���O�C���`���l��()
        String l_loginChannel = this.getLoginChannel();
        
        //1.5. create���N�G�X�g�A�_�v�^()
        WEB3EquityOrderRequestAdapter l_adapter =
            this.createRequestAdapter(l_request);
        
        //1.6. get�����R�[�h()
        String l_strProductCode = l_adapter.getProductCode();

        //1.8. get���s����()
        EqTypeExecutionConditionType l_execCondType = l_adapter.getExecCondType();
        
        //1.9. get�ŋ敪()
        TaxTypeEnum l_taxType = l_adapter.getTaxDivision();
        
        //1.10. is������()
        boolean l_blnIsSellOrder = l_adapter.isSellOrder();
        
        // get�i�v�w�l�j���s����( )
        //�i�v�w�l�j���s�������擾����B
        EqTypeExecutionConditionType l_wLimitExecCondType = l_adapter.getWLimitExecCondType();

        //get�s��R�[�h( )
        String l_strRequestMarketCode = l_adapter.getMarketCode();
        if (l_request instanceof WEB3EquityBuyConfirmRequest)
        {
            ((WEB3EquityBuyConfirmRequest)l_request).marketCode = l_strRequestMarketCode;
        }
        else
        {
            ((WEB3EquitySellConfirmRequest)l_request).marketCode = l_strRequestMarketCode;
        }
        //reset�s��R�[�h(�s��R�[�h : String)
        //�����͈ȉ��̒ʂ�ɐݒ肷��B
        //�s��R�[�h�F�@@���N�G�X�g.�s��R�[�h
        WEB3GentradeTradingTimeManagement.resetMarketCode(l_strRequestMarketCode);

        //get�P��()
        double l_dblPrice = l_adapter.getPrice();

        //1.11. create�������e()
        String l_strOrderCondOperator = null;
        double l_dblStopOrderBasePrice = 0.0D;
        double l_dblWLimitOrderChange = 0.0D;
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_commonRequest.orderCondType))
        {
            //�����������Z�q
            l_strOrderCondOperator = null;
            //�t�w�l��l
            l_dblStopOrderBasePrice = 0.0D;
            //�iW�w�l�j�����w�l
            l_dblWLimitOrderChange = 0.0D;
            //�i�v�w�l�j���s�����F
            //���N�G�X�g.���������敪���i�h�w��Ȃ��h�A�h�t�w�l�h�j�̏ꍇ�́Anull���Z�b�g�B
            l_wLimitExecCondType = null;
        }
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_commonRequest.orderCondType))
        {
            //�����������Z�q
            l_strOrderCondOperator = l_commonRequest.stopOrderCondOperator;
            //�t�w�l��l
            l_dblStopOrderBasePrice = Double.parseDouble(l_commonRequest.stopOrderCondPrice);
            //�iW�w�l�j�����w�l
            l_dblWLimitOrderChange = 0.0D;;
            //�i�v�w�l�j���s�����F
            //���N�G�X�g.���������敪���i�h�w��Ȃ��h�A�h�t�w�l�h�j�̏ꍇ�́Anull���Z�b�g�B
            l_wLimitExecCondType = null;
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_commonRequest.orderCondType))
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

        Timestamp l_tsExpirationDate = null;
        Long l_firstOrderUnitId = null;
        if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_commonRequest.expirationDateType))
        {
            l_tsExpirationDate = new Timestamp(WEB3GentradeTradingTimeManagement.getOrderBizDate().getTime());
            l_firstOrderUnitId = null;
        }
        else if (l_commonRequest.expirationDateType.equals(WEB3OrderExpirationDateTypeDef.CARRIED_ORDER))
        {
            l_tsExpirationDate = new Timestamp(l_adapter.getExpirationDate().getTime());
            l_firstOrderUnitId = new Long(0);
        }

        String l_strMarketCode;
        String l_strPriceConditionType;
        if (l_request instanceof WEB3EquityBuyConfirmRequest)
        {
            l_strMarketCode = ((WEB3EquityBuyConfirmRequest)l_request).marketCode;
            l_strPriceConditionType = ((WEB3EquityBuyConfirmRequest)l_request).priceCondType;
        }
        else
        {
            l_strMarketCode = ((WEB3EquitySellConfirmRequest)l_request).marketCode;
            l_strPriceConditionType = ((WEB3EquitySellConfirmRequest)l_request).priceCondType;
        }
        WEB3EquityNewCashBasedOrderSpec l_orderSpec =
            WEB3EquityNewCashBasedOrderSpec.createOrderSpec(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_trader,
                l_strMarketCode,
                l_strProductCode,
                Double.parseDouble(l_commonRequest.orderQuantity),
                l_dblPrice,
                l_execCondType,
                l_taxType,
                l_tsExpirationDate,
                l_blnIsSellOrder,
                l_loginChannel,
                l_strPriceConditionType,
                l_commonRequest.orderCondType,
                l_strOrderCondOperator,
                l_dblStopOrderBasePrice,
                l_dblWLimitOrderChange,
                l_firstOrderUnitId,
                l_wLimitExecCondType);

        //1.12. set�萔�����i�R�[�h()
        l_orderSpec.setCommissionProductCode(WEB3CommisionProductCodeDef.LISTING_STOCK);

        //1.13. create�萔��()
        String l_strSonarTradedCode;
        String l_strTradingType;
        if (l_request instanceof WEB3EquityBuyConfirmRequest)
        {
            l_strTradingType = ((WEB3EquityBuyConfirmRequest)l_request).tradingType;
            if (WEB3TradingTypeDef.BUY_ORDER.equals(l_strTradingType))
            {
                l_strSonarTradedCode = WEB3TransactionTypeSONARDef.MARKET_TRADING;
            }
            else
            {
                l_strSonarTradedCode = WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET;
            }
        }
        else
        {
            l_strSonarTradedCode = WEB3TransactionTypeSONARDef.MARKET_TRADING;
        }
        WEB3GentradeCommission l_commission =
            l_orderSpec.createCommission(
                this.getMainAccount().getBranch(),
                l_strSonarTradedCode);
        l_orderSpec.setCommissionProductCode(l_commission.getCommissionProductCode());
        
        //1.14. validate������������()
        EqTypeNewOrderValidationResult l_eqNewOrderValidationResult =
            this.validateNewCashBasedOrder(l_orderSpec, l_adapter);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        WEB3EquityTradedProduct l_tradedProduct = null;

        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        //throw�����R�����ʃG���[���(OrderValidationResult, �،����, String)
        //�����R�����ʁF�@@validate������������()�̖߂�l
        //�،���ЁF�@@�⏕����.getInstitution()
        //�����R�[�h�F�@@���N�G�X�g�A�_�v�^.get�����R�[�h()
        l_orderManager.throwOrderValidationResultErrorInfo(
            l_eqNewOrderValidationResult,
            l_subAccount.getInstitution(),
            l_strProductCode);

        //1.15. validate���t�\����()
        this.validateSellableAssetQuantity(l_adapter);

        //validate�@@�\�a������(�⏕����)
        //�⏕�����F�@@get�⏕����()�̖߂�l
        l_orderManager.validateMechanismDepositAgree(l_subAccount);

        //getTradedProduct()
        try
        {
            l_tradedProduct =
                (WEB3EquityTradedProduct)l_productManager.getTradedProduct(
                    l_subAccount.getInstitution(),
                    l_orderSpec.getProductCode(),
                    l_orderSpec.getMarketCode());
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);                
        }

        //1.18. getQuantity()
        double l_dblQuantity = l_orderSpec.getQuantity();
        
        //1.22. calc�T�Z��n���()
        //�T�Z��n������v�Z����B
        //�����͈ȉ��̒ʂ�ɐݒ肷��B

        //�萔�� : �����������e.get�萔��( )
        //�w�l : �����������e.getLimitPrice()
        //�iW�w�l�j�����w�l : �����������e.get�iW�w�l�j�����w�l()
        //�t�w�l��l : �����������e.get�t�w�l��l()
        //���s���� : �����������e.getExecConditionType()
        //�iW�w�l�j���s���� : �����������e.get�iW�w�l�j���s����()
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
                l_dblQuantity,
                l_blnIsSellOrder,
                0.0D,
                0.0D,
                false);

        //set�����P��(double)
        //�����P�����Z�b�g����B
        //�T�Z��n����v�Z����.get�v�Z�P��()���Z�b�g�B
        l_orderSpec.setOrderUnitPrice(l_estimatedDeliveryPrice.getCalcUnitPrice());

        //1.23. set�T�Z��n���()
        l_orderSpec.setEstimateDeliveryAmount(l_estimatedDeliveryPrice.getEstimateDeliveryAmount());
        
        //1.24. validate����]��()
        WEB3TPTradingPowerResult l_tpResult =
            this.validateTradingPower(
                l_subAccount,
                l_orderSpec,
                false,
                l_tradedProduct);
        
        //1.25. createNewOrderId()
        long l_lngOrderId = l_orderManager.createNewOrderId();
        
        //1.26. createResponse()
        WEB3GenResponse l_response = l_request.createResponse();

        //1.27. get�s��ǌx���s��()
        String[] l_strMessageSuspensions =
            WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
                (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch(),
                ProductTypeEnum.EQUITY,
                WEB3MarginTradingDivDef.DEFAULT);
        
        //1.28. is�C���T�C�_�[�x���\��()
        boolean l_blnIsInsiderMessageSuspension =
            l_orderManager.isInsiderMessageSuspension(
                l_subAccount,
                l_tradedProduct.getProduct().getProductId());
        
        //1.29. �������̏ꍇ�̂ݎ��s
        String l_strEstimatedBookPrice = null;
        if (l_blnIsSellOrder)
        {   
            l_strEstimatedBookPrice = this.getEstimatedBookPrice(l_adapter);
        }

        //set�P��(�����������N�G�X�g�A�_�v�^, WEB3GenResponse)
        //�����͈ȉ��̒ʂ�ɐݒ肷��B
        //�����������N�G�X�g�A�_�v�^�F�@@�������������I�u�W�F�N�g
        //���X�|���X�F�����������X�|���X
        setPrice(l_adapter, l_response);

        //1.30. �v���p�e�B�Z�b�g
        if (l_response instanceof WEB3EquityBuyConfirmResponse)
        {
            WEB3EquityBuyConfirmResponse l_buyConfirmResponse =
                (WEB3EquityBuyConfirmResponse)l_response;
            //���X�|���X.�m�F��������
            Date l_bizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            l_buyConfirmResponse.checkDate = WEB3DateUtility.toDay(l_bizDate);
            //���X�|���X.�T�Z��n���
            l_buyConfirmResponse.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_orderSpec.getEstimateDeliveryAmount());
            //���X�|���X.����I���x���s��R�[�h�ꗗ
            l_buyConfirmResponse.messageSuspension = l_strMessageSuspensions;
            //���X�|���X.�m�F���P��
            //calc�T�Z��n���( )�̖߂�l.get�m�F���擾����( )�̖߂�l
            l_buyConfirmResponse.checkPrice = l_estimatedDeliveryPrice.getCheckGetCurrentPrice();
            WEB3EquityCommissionInfoUnit l_commissionInfo =
                new WEB3EquityCommissionInfoUnit();
            // �萔���R�[�X
            l_commissionInfo.commissionCourse = l_commission.getCommissionCourseDiv();
            // �萔��
            l_commissionInfo.commission =
                WEB3StringTypeUtility.formatNumber(l_estimatedDeliveryPrice.getCommissionFee());
            // �萔�������
            l_commissionInfo.commissionConsumptionTax =
                WEB3StringTypeUtility.formatNumber(l_estimatedDeliveryPrice.getCommissionFeeTax());
            //���X�|���X.�萔�����
            l_buyConfirmResponse.commissionInfo = l_commissionInfo;
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
            l_buyConfirmResponse.marketCode = l_strRequestMarketCode;
            //���X�|���X.������
			EqtypeProductRow l_productRow = (EqtypeProductRow)l_tradedProduct.getProduct().getDataSourceObject();
            l_buyConfirmResponse.productName = l_productRow.getStandardName();
            // ���X�|���X.�����L������
            l_buyConfirmResponse.expirationDate = l_adapter.getExpirationDate();
        }
        else
        {
            WEB3EquitySellConfirmResponse l_sellConfirmResponse =
                (WEB3EquitySellConfirmResponse)l_response;
            //���X�|���X.�m�F��������
            Date l_bizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            l_sellConfirmResponse.checkDate = WEB3DateUtility.toDay(l_bizDate);
            //���X�|���X.�T�Z��n���
            l_sellConfirmResponse.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_orderSpec.getEstimateDeliveryAmount());
            //���X�|���X.����I���x���s��R�[�h�ꗗ
            l_sellConfirmResponse.messageSuspension = l_strMessageSuspensions;
            //���X�|���X.�m�F���P��
            //calc�T�Z��n���( )�̖߂�l.get�m�F���擾����( )�̖߂�l
            l_sellConfirmResponse.checkPrice = l_estimatedDeliveryPrice.getCheckGetCurrentPrice();
            WEB3EquityCommissionInfoUnit l_commissionInfo =
                new WEB3EquityCommissionInfoUnit();
            // �萔���R�[�X
            l_commissionInfo.commissionCourse = l_commission.getCommissionCourseDiv();
            // �萔��
            l_commissionInfo.commission =
                WEB3StringTypeUtility.formatNumber(l_estimatedDeliveryPrice.getCommissionFee());
            // �萔�������
            l_commissionInfo.commissionConsumptionTax =
                WEB3StringTypeUtility.formatNumber(l_estimatedDeliveryPrice.getCommissionFeeTax());
            //���X�|���X.�萔�����
            l_sellConfirmResponse.commissionInfo = l_commissionInfo;
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
            l_sellConfirmResponse.marketCode = l_strRequestMarketCode;
            //���X�|���X.�T�Z�뉿�P��
            l_sellConfirmResponse.estimatedBookPrice = l_strEstimatedBookPrice;
            // ���X�|���X.�����L������
            l_sellConfirmResponse.expirationDate = l_adapter.getExpirationDate();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit����)<BR>
     * <BR>
     * ��������������o�^����B<BR>
     * �V�[�P���X�}�u�i���������T�[�r�X�j�����o�^�X�V�v�Q�ƁB<BR>
     * <BR>
     * @@param l_request - (���̓f�[�^) <BR>
     * �N���C�A���g����̃��N�G�X�g���b�Z�[�W���w�肷��B <BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 3FFD343B0271
     */
    protected WEB3GenResponse submitOrder(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityCommonRequest l_commonRequest = (WEB3EquityCommonRequest)l_request;
        
        //1.1. validate()
        l_commonRequest.validate();

        //1.3. get�⏕����()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //1.4. get�㗝���͎�()
        WEB3GentradeTrader l_trader = (WEB3GentradeTrader)this.getTrader();
        
        //1.5. get���O�C���`���l��()
        String l_loginChannel = this.getLoginChannel();
        
        //1.6. create���N�G�X�g�A�_�v�^()
        WEB3EquityOrderRequestAdapter l_adapter =
            this.createRequestAdapter(l_request);
        
        //1.7. get�����R�[�h()
        String l_strProductCode = l_adapter.getProductCode();

        //1.9. get���s����()
        EqTypeExecutionConditionType l_execCondType = l_adapter.getExecCondType();
        
        //1.10. get�ŋ敪()
        TaxTypeEnum l_taxType = l_adapter.getTaxDivision();

        //1.11. is������()
        boolean l_blnIsSellOrder = l_adapter.isSellOrder();

        //get�i�v�w�l�j���s����( )
        //�i�v�w�l�j���s�������擾����B
        EqTypeExecutionConditionType l_wLimitExecCondType = l_adapter.getWLimitExecCondType();

        //get�s��R�[�h( )
        String l_strRequestMarketCode = l_adapter.getMarketCode();
        if (l_request instanceof WEB3EquityBuyCompleteRequest)
        {
            ((WEB3EquityBuyCompleteRequest)l_request).marketCode = l_strRequestMarketCode;
        }
        else if (l_request instanceof WEB3EquitySellCompleteRequest)
        {
            ((WEB3EquitySellCompleteRequest)l_request).marketCode = l_strRequestMarketCode;
        }
        //reset�s��R�[�h(�s��R�[�h : String)
        WEB3GentradeTradingTimeManagement.resetMarketCode(l_strRequestMarketCode);

        //get�P��()
        double l_dblPrice = l_adapter.getPrice();

        //get������()
        Date l_datCheckDate;
        if (l_request instanceof WEB3EquityBuyCompleteRequest)
        {
            if (((WEB3EquityBuyCompleteRequest)l_request).checkDate == null)
            {
                ((WEB3EquityBuyCompleteRequest)l_request).checkDate =
                    WEB3GentradeTradingTimeManagement.getOrderBizDate();
            }
            l_datCheckDate = ((WEB3EquityBuyCompleteRequest)l_request).checkDate;
        }
        else
        {
            if (((WEB3EquitySellCompleteRequest)l_request).checkDate == null)
            {
                ((WEB3EquitySellCompleteRequest)l_request).checkDate =
                    WEB3GentradeTradingTimeManagement.getOrderBizDate();
            }
            l_datCheckDate = ((WEB3EquitySellCompleteRequest)l_request).checkDate;
        }
        WEB3GentradeTradingTimeManagement.getOrderBizDate(l_datCheckDate);

        //1.12. create�������e()
        String l_strOrderCondOperator = null;
        double l_dblStopOrderBasePrice = 0.0D;
        double l_dblWLimitOrderChange = 0.0D;
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_commonRequest.orderCondType))
        {
            //�����������Z�q
            l_strOrderCondOperator = null;
            //�t�w�l��l
            l_dblStopOrderBasePrice = 0.0D;
            //�iW�w�l�j�����w�l
            l_dblWLimitOrderChange = 0.0D;
            //�i�v�w�l�j���s�����F
            //���N�G�X�g.���������敪���i�h�w��Ȃ��h�A�h�t�w�l�h�j�̏ꍇ�́Anull���Z�b�g�B
            l_wLimitExecCondType = null;
        }
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_commonRequest.orderCondType))
        {
            //�����������Z�q
            l_strOrderCondOperator = l_commonRequest.stopOrderCondOperator;
            //�t�w�l��l
            l_dblStopOrderBasePrice = Double.parseDouble(l_commonRequest.stopOrderCondPrice);
            //�iW�w�l�j�����w�l
            l_dblWLimitOrderChange = 0.0D;;
            //�i�v�w�l�j���s�����F
            //���N�G�X�g.���������敪���i�h�w��Ȃ��h�A�h�t�w�l�h�j�̏ꍇ�́Anull���Z�b�g�B
            l_wLimitExecCondType = null;
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_commonRequest.orderCondType))
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

        Timestamp l_tsExpirationDate = null;
        Long l_firstOrderUnitId = null;
        if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_commonRequest.expirationDateType))
        {
            l_tsExpirationDate = new Timestamp(WEB3GentradeTradingTimeManagement.getOrderBizDate().getTime());
            l_firstOrderUnitId = null;
        }
        else if (l_commonRequest.expirationDateType.equals(WEB3OrderExpirationDateTypeDef.CARRIED_ORDER))
        {
            l_tsExpirationDate = new Timestamp(l_adapter.getExpirationDate().getTime());
            l_firstOrderUnitId = new Long(0);
        }

        String l_strMarketCode;
        String l_strPriceConditionType;
        if (l_request instanceof WEB3EquityBuyCompleteRequest)
        {
            l_strMarketCode = ((WEB3EquityBuyCompleteRequest)l_request).marketCode;
            l_strPriceConditionType = ((WEB3EquityBuyCompleteRequest)l_request).priceCondType;
        }
        else
        {
            l_strMarketCode = ((WEB3EquitySellCompleteRequest)l_request).marketCode;
            l_strPriceConditionType = ((WEB3EquitySellCompleteRequest)l_request).priceCondType;
        }
        WEB3EquityNewCashBasedOrderSpec l_orderSpec =
            WEB3EquityNewCashBasedOrderSpec.createOrderSpec(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_trader,
                l_strMarketCode,
                l_strProductCode,
                Double.parseDouble(l_commonRequest.orderQuantity),
                l_dblPrice,
                l_execCondType,
                l_taxType,
                l_tsExpirationDate,
                l_blnIsSellOrder,
                l_loginChannel,
                l_strPriceConditionType,
                l_commonRequest.orderCondType,
                l_strOrderCondOperator,
                l_dblStopOrderBasePrice,
                l_dblWLimitOrderChange,
                l_firstOrderUnitId,
                l_wLimitExecCondType);

        //1.13. set�萔�����i�R�[�h()
        l_orderSpec.setCommissionProductCode(WEB3CommisionProductCodeDef.LISTING_STOCK);

        //1.14. create�萔��()
        String l_strSonarTradedCode;
        String l_strTradingType;
        if (l_request instanceof WEB3EquityBuyCompleteRequest)
        {
            l_strTradingType = ((WEB3EquityBuyCompleteRequest)l_request).tradingType;
            if (WEB3TradingTypeDef.BUY_ORDER.equals(l_strTradingType))
            {
                l_strSonarTradedCode = WEB3TransactionTypeSONARDef.MARKET_TRADING;
            }
            else
            {
                l_strSonarTradedCode = WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET;
            }
        }
        else
        {
            l_strSonarTradedCode = WEB3TransactionTypeSONARDef.MARKET_TRADING;
        }
        WEB3GentradeCommission l_commission =
            l_orderSpec.createCommission(
                this.getMainAccount().getBranch(),
                l_strSonarTradedCode);
        
        //1.15. validate������������()
        EqTypeNewOrderValidationResult l_eqNewOrderValidationResult =
            this.validateNewCashBasedOrder(l_orderSpec, l_adapter);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        //throw�����R�����ʃG���[���(OrderValidationResult, �،����, String)
        //�����R�����ʁF�@@validate������������()�̖߂�l
        //�،���ЁF�@@�⏕����.getInstitution()
        //�����R�[�h�F�@@���N�G�X�g�A�_�v�^.get�����R�[�h()
        l_orderManager.throwOrderValidationResultErrorInfo(
            l_eqNewOrderValidationResult,
            l_subAccount.getInstitution(),
            l_strProductCode);

        //1.16. validate���t�\����()
        this.validateSellableAssetQuantity(l_adapter);

        //validate�@@�\�a������(�⏕����)
        //�⏕�����F�@@get�⏕����()�̖߂�l
        l_orderManager.validateMechanismDepositAgree(l_subAccount);

        //getTradedProduct()
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        WEB3EquityTradedProduct l_tradedProduct = null;
        try
        {
            l_tradedProduct =
                (WEB3EquityTradedProduct)l_productManager.getTradedProduct(
                    l_subAccount.getInstitution(),
                    l_orderSpec.getProductCode(),
                    l_orderSpec.getMarketCode());
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);                
        }

        //1.18. getQuantity()
        double l_dblQuantity = l_orderSpec.getQuantity();
        
        String l_strCheckPrice;
        if (l_request instanceof WEB3EquityBuyCompleteRequest)
        {
            l_strCheckPrice = ((WEB3EquityBuyCompleteRequest)l_request).checkPrice;
        }
        else
        {
            l_strCheckPrice = ((WEB3EquitySellCompleteRequest)l_request).checkPrice;
        }

        //1.21. calc�T�Z��n���()
        //�����͈ȉ��̒ʂ�ɐݒ肷��B

        //�萔�� : �쐬�����萔���I�u�W�F�N�g
        //�w�l : �����������e.getLimitPrice()
        //�iW�w�l�j�����w�l : �����������e.get�iW�w�l�j�����w�l()
        //�t�w�l��l : �����������e.get�t�w�l��l()
        //���s���� : �����������e.getExecConditionType()
        //�iW�w�l�j���s���� : �����������e.get�i�v�w�l�j���s����( )
        //�l�i���� : �����������e.get�l�i����()
        //�������� : �����������e.get��������()
        //�m�F���擾���� : ���N�G�X�g.�m�F���P��
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
                l_strCheckPrice,
                false,
                l_subAccount,
                l_tradedProduct,
                l_dblQuantity,
                l_blnIsSellOrder,
                0.0D,
                0.0D,
                false);

        //set�����P��()
        //�����P�����Z�b�g����B
        //�����P���F�@@�T�Z��n����v�Z����.get�v�Z�P��()���Z�b�g����B
        l_orderSpec.setOrderUnitPrice(l_estimatedDeliveryPrice.getCalcUnitPrice());

        //1.22. set�T�Z��n���()
        l_orderSpec.setEstimateDeliveryAmount(l_estimatedDeliveryPrice.getEstimateDeliveryAmount());
        
        //1.23. validate����]��()
        this.validateTradingPower(
            l_subAccount,
            l_orderSpec,
            true,
            l_tradedProduct);
        
        //1.24. submit������������()
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
        this.submitNewCashBasedOrder(
            l_subAccount,
            l_orderSpec,
            l_lngOrderId,
            l_strPassward,
            l_adapter);
        
        //1.25. createResponse()
        WEB3GenResponse l_response = l_commonRequest.createResponse();
        
        //1.26. is�C���T�C�_�[�x���\��()
        WEB3EquityProduct l_product = null;
        try
        {
            l_product =
                (WEB3EquityProduct)l_productManager.getProduct(
                    l_subAccount.getInstitution(),
                    l_strProductCode);
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        boolean l_blnIsInsiderMessageSuspension =
            l_orderManager.isInsiderMessageSuspension(
                l_subAccount,
                l_product.getProductId());
        
        //1.27. �v���p�e�B�Z�b�g
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
            l_buyCompleteResponse.marketCode = l_strRequestMarketCode;
            // ���X�|���X.�����L������
            l_buyCompleteResponse.expirationDate = l_adapter.getExpirationDate();
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
            l_sellCompleteResponse.marketCode = l_strRequestMarketCode;
            // ���X�|���X.�����L������
            l_sellCompleteResponse.expirationDate = l_adapter.getExpirationDate();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * �����������������s����B <BR>
     * <BR>
     * �i�V�X�e���������j�K�C�h 4.4.�Ɩ����W�b�N�@@�Q�Ɓj <BR>
     * <BR>
     * �P�j�@@���{���\�b�h���� <BR>
     * �����̊����������N�G�X�g�̃I�u�W�F�N�g�^���A <BR>
     * �T�[�r�X���\�b�h�𔻒肵�R�[������B <BR>
     * <BR>
     * �Q�j�@@�T�[�r�X���\�b�h�̖߂�l��ԋp����B <BR>
     * @@param l_request
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 400E3ED800CA
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3EquityBuyConfirmRequest ||
            l_request instanceof WEB3EquitySellConfirmRequest)
        {
            l_response = this.validateOrder(l_request);
        }
        else if (l_request instanceof WEB3EquityBuyCompleteRequest ||
                  l_request instanceof WEB3EquitySellCompleteRequest)
        {
            l_response = this.submitOrder(l_request);
        }
        else
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�^�C�v �F " + l_request);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^�̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �����������N�G�X�g�A�_�v�^.create(�����̃��N�G�X�g)���R�[������B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�f�[�^�B
     * @@return WEB3EquityOrderRequestAdapter
     */
    protected WEB3EquityOrderRequestAdapter createRequestAdapter(WEB3GenRequest l_request)
    {
        final String STR_METHOD_NAME = "createRequestAdapter(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityOrderRequestAdapter l_requestAdaptor =
            WEB3EquityOrderRequestAdapter.create(l_request);
        
        log.exiting(STR_METHOD_NAME);
        return l_requestAdaptor;
    }
    
    /**
     * (validate������������)<BR>
     * �������������R�����\�b�h�̌Ăяo�����s���B<BR>
     * <BR>
     * EQTYPE�̊g�����������}�l�[�W��.validate������������(<BR>
     * �⏕����, �����������e)��delegate����B<BR>
     * <BR>
     * ------------------------------------------------------<BR>
     * ��validate������������()�F�����ݒ�d�l��<BR>
     * <BR>
     * �⏕�����F�@@this.get�⏕����()�̖߂�l<BR>
     * �����������e�F�@@�����̊����������e�I�u�W�F�N�g<BR>
     * ------------------------------------------------------<BR>
     * @@param l_orderSpec - (�����������e)<BR>
     * �����������e�I�u�W�F�N�g�B
     * @@param l_requestAdaptor - (�����������N�G�X�g�A�_�v�^)<BR>
     * �����������N�G�X�g�A�_�v�^�B
     * @@return EqTypeNewOrderValidationResult
     * @@throws WEB3BaseException
     */
    protected EqTypeNewOrderValidationResult validateNewCashBasedOrder(
        EqTypeNewCashBasedOrderSpec l_orderSpec,
        WEB3EquityOrderRequestAdapter l_requestAdaptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateNewCashBasedOrder(EqTypeNewCashBasedOrderSpec, WEB3EquityOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqTypeNewOrderValidationResult l_result =
            l_orderManager.validateNewCashBasedOrder(
                this.getSubAccount(),
                l_orderSpec);
        
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }
    
    /**
     * (validate����]��)<BR>
     * ����]�͂��`�F�b�N���A����]�͌��ʃI�u�W�F�N�g��ԋp����B<BR>
     * �V�[�P���X�}�u�i���������T�[�r�X�jvalidate����]�́v���Q�ƁB<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B
     * @@param l_orderSpec - (�����������e)<BR>
     * �����������e�I�u�W�F�N�g�B
     * @@param l_blnUpdateFlg - (�]�͍X�V�t���O)<BR>
     * �]�͍X�V�t���O�B<BR>
     * �ifalse�F�@@�m�F���Atrue�F�@@�������j
     * @@param l_tradedProduct - (�������)<BR>
     * ��������I�u�W�F�N�g�B
     * @@return WEB3TPTradingPowerResult
     * @@throws WEB3BaseException
     */
    protected WEB3TPTradingPowerResult validateTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        WEB3EquityNewCashBasedOrderSpec l_orderSpec,
        boolean l_blnUpdateFlg,
        WEB3EquityTradedProduct l_tradedProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTradingPower(WEB3GentradeSubAccount, WEB3EquityNewCashBasedOrderSpec, boolean, WEB3EquityTradedProduct)";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityOrderManagerPersistenceEventInterceptor l_interceptor =
            new WEB3EquityOrderManagerPersistenceEventInterceptor();
                   
        l_interceptor.setEquityOrderSpec(l_orderSpec);
        
        WEB3TPTradingPowerService l_tradingpowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        Object[] l_orderSpecIntercepters = { l_interceptor };
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
                l_blnUpdateFlg);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        if (l_tpResult.isResultFlg() == false)
        {
            if (WEB3TPTradingPowerErrorDivDef.MARGIN_SEC_ERROR.equals(l_tpResult.getTpErrorInfo().tradinPowerErrorDiv))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01928,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            else
            {
                if (l_orderSpec.isBuyOrder())
                {
                    String l_strLackAccountBalanceInfo =
                        l_orderManager.getLackAccountBalanceInfoBuy(l_tpResult);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01929,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_strLackAccountBalanceInfo);
                }
                else
                {
                    String l_strLackAccountBalanceInfo =
                        l_orderManager.getLackAccountBalanceInfoSell(
                            l_tpResult,
                            l_orderSpec.getQuantity());
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01930,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_strLackAccountBalanceInfo);
                }
            }
        }
        
        if (l_blnUpdateFlg)
        {
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_tpResult;
    }
    
    /**
     * (get�T�Z�뉿�P��)<BR>
     * �T�Z�뉿�P�����擾���Ԃ��B<BR>
     * <BR>
     * �P�j�@@�ۗL���Y�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�����|�W�V�����}�l�[�W��.getAsset(�����̃��N�G�X�g�A�_�v�^.���N�G�X�g.ID)���R�[������B<BR>
     * <BR>
     * �Q�j�@@�����v�Z�T�[�r�X.calc�T�Z�뉿�P��(�ۗL���Y.����ID, get�⏕����(), �ۗL���Y.�ŋ敪)���R�[�����A<BR>
     * �@@�@@�@@�߂�l��ԋp����B<BR>
     * @@param l_requestAdaptor - (�����������N�G�X�g�A�_�v�^)<BR>
     * �����������N�G�X�g�A�_�v�^�B
     * @@return String
     * @@throws WEB3BaseException
     */
    protected String getEstimatedBookPrice(
        WEB3EquityOrderRequestAdapter l_requestAdaptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getEstimatedBookPrice(WEB3EquityOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        WEB3GenRequest l_request = l_requestAdaptor.request;
        long l_lngAssetId = 0L;
        String l_strId = null;
        if (l_request instanceof WEB3EquitySellConfirmRequest)
        {
            l_strId = ((WEB3EquitySellConfirmRequest)l_request).id;
        }
        else if (l_request instanceof WEB3EquitySellCompleteRequest)
        {
            l_strId = ((WEB3EquitySellCompleteRequest)l_request).id;
        }
        if (l_strId != null)
        {
            l_lngAssetId = Long.parseLong(l_strId);
        }
        Asset l_asset = null;
        try
        {
            l_asset = l_positionManager.getAsset(l_lngAssetId);
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        WEB3EquityBizLogicProvider l_bizLogicProvider =
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
        double l_dblEstimatedBookPrice =
            l_bizLogicProvider.calcEstimatedBookPrice(
                l_asset.getProduct().getProductId(),
                this.getSubAccount(),
                l_asset.getTaxType());
        String l_strEstimatedBookPrice =
            WEB3StringTypeUtility.formatNumber(l_dblEstimatedBookPrice);
        
        log.exiting(STR_METHOD_NAME);
        return l_strEstimatedBookPrice;
    }
    
    /**
     * (validate���t�\����)<BR>
     * ���t�\���ʃ`�F�b�N���s���B<BR>
     * <BR>
     * ����������return����B<BR>
     * �i���t�\���ʃ`�F�b�N��validate������������()���ōs���Ă��邽�߁j<BR>
     * @@param l_requestAdaptor - (�����������N�G�X�g�A�_�v�^)<BR>
     * �����������N�G�X�g�A�_�v�^�B
     * @@throws WEB3BaseException
     */
    protected void validateSellableAssetQuantity(
        WEB3EquityOrderRequestAdapter l_requestAdaptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateSellableAssetQuantity(WEB3EquityOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return;
    }
    
    /**
     * (submit������������)<BR>
     * ��������������o�^����B<BR>
     * <BR>
     * �g�����������}�l�[�W��.submitNewCashBasedOrder(<BR>
     * �⏕����, �����������e, ����ID, ����p�X���[�h, true�i�������R�����X�L�b�v����j)���R�[������B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B
     * @@param l_orderSpec - (�����������e)<BR>
     * �����������e�I�u�W�F�N�g�B
     * @@param l_lngOrderId - (����ID)<BR>
     * �\�񒍕��̒���ID�B
     * @@param l_strTradingPassword - (����p�X���[�h)<BR>
     * ����p�X���[�h�B
     * @@param l_requestAdaptor - (�����������N�G�X�g�A�_�v�^)<BR>
     * �����������N�G�X�g�A�_�v�^�B
     * @@throws WEB3BaseException
     */
    protected void submitNewCashBasedOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3EquityNewCashBasedOrderSpec l_orderSpec,
        long l_lngOrderId,
        String l_strTradingPassword,
        WEB3EquityOrderRequestAdapter l_requestAdaptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitNewCashBasedOrder(WEB3GentradeSubAccount, WEB3EquityNewCashBasedOrderSpec, long, String, WEB3EquityOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqTypeOrderSubmissionResult l_result =
            l_orderManager.submitNewCashBasedOrder(
                l_subAccount,
                l_orderSpec,
                l_lngOrderId,
                l_strTradingPassword,
                true);
        if (l_result.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_result.getProcessingResult());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                l_result.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�P��)<BR>
     * ���������Ƀ��^�[������B<BR>
     * <BR>
     * @@param l_requestAdapter - (�����������N�G�X�g�A�_�v�^)<BR>
     * �����������N�G�X�g�A�_�v�^�B<BR>
     * @@param l_response - (���X�|���X)<BR>
     * ���X�|���X<BR>
     * @@throws WEB3BaseException
     */
    protected void setPrice(
        WEB3EquityOrderRequestAdapter l_requestAdapter,
        WEB3GenResponse l_response)
        throws WEB3BaseException
    {
        
    }
}
@
