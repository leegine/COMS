head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.40.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O����������T�[�r�XImpl(WEB3FeqCancelServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ��O�� (���u) �V�K�쐬       
                 : 2005/08/03 �A�C��(���u) ���r���[       
Revesion History : 2008/01/21 �đo�g(���u) ���f��No.381�A���f��No.372
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrder;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BuySellTypeDef;
import webbroker3.common.define.WEB3InputOutputActionSettlementDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqBizLogicProvider;
import webbroker3.feq.WEB3FeqCancelUpdateInterceptor;
import webbroker3.feq.WEB3FeqClientRequestService;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.message.WEB3FeqCancelCompleteRequest;
import webbroker3.feq.message.WEB3FeqCancelCompleteResponse;
import webbroker3.feq.message.WEB3FeqCancelConfirmRequest;
import webbroker3.feq.message.WEB3FeqCancelConfirmResponse;
import webbroker3.feq.service.delegate.WEB3FeqCancelService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�O����������T�[�r�XImpl)<BR>
 * �O����������T�[�r�X�����N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3FeqCancelServiceImpl extends WEB3FeqClientRequestService implements WEB3FeqCancelService 
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqCancelServiceImpl.class);  
    
    /**
     * @@roseuid 42CE39F6008C
     */
    public WEB3FeqCancelServiceImpl() 
    {
     
    }
    
    /**
     * �O����������T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���R�[������B<BR>
     * <BR>
     *    validate����()<BR>
     *    submit����()<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 429ADE42014F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3GenResponse l_response;
        
        //���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���R�[������B 
        // validate����() 
        // submit����() 
        if (l_request instanceof WEB3FeqCancelConfirmRequest)
        {
            l_response = 
                validateOrder((WEB3FeqCancelConfirmRequest)l_request);   
        }        
        else if (l_request instanceof WEB3FeqCancelCompleteRequest)
        {
            l_response =
                submitOrder((WEB3FeqCancelCompleteRequest)l_request);
        }
        else
        {
            log.debug("error in get necessory request");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (validate����)<BR>
     * ��������̊m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O������jvalidate�����v �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * 
     * @@return WEB3FeqCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 429ADE42017D
     */
    protected WEB3FeqCancelConfirmResponse validateOrder(
        WEB3FeqCancelConfirmRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateOrder(WEB3FeqCancelConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 �⏕�����I�u�W�F�N�g���擾����B
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount)this.getSubAccount();
        
        //1.3 get�����P��ByOrderId(long)
        //�����P�ʃI�u�W�F�N�g���擾����B 
        //[����] 
        //����ID�F ���N�G�X�g.����ID 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        FeqOrderUnit l_feqOrderUnit = l_feqOrderManager.getOrderUnitByOrderId(
            Long.parseLong(l_request.orderId));
        
        log.debug("get�����P��ByOrderId(long)");
        
        FeqOrderUnitParams l_feqOrderUnitParams = 
            new FeqOrderUnitParams(
                (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject());
        
        WEB3FeqProductManager l_feqProductManager = 
            (WEB3FeqProductManager)l_tradingModule.getProductManager();        

        WEB3FeqProduct l_feqProduct = null;
        try
        {
            //1.4 get�O����������(long)
            //�O�����������I�u�W�F�N�g���擾����B 
            //[����] 
            //����ID�F �����P��.����ID 
            l_feqProduct =
                (WEB3FeqProduct) l_feqProductManager.getProduct(
                    l_feqOrderUnitParams.getProductId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(
                "__NotFoundException__ when "
                + "l_feqProductManager.getProduct(ProductId) with "
                + "ProductId = "
                + l_feqOrderUnitParams.getProductId());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        if (l_feqProduct == null)
        {
            log.debug("Error in �O�����������I�u�W�F�N�g���擾����");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } 
        
        //1.5 get������( )
        //���������擾����B
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //1.6 CancelOrderSpec(����ID : long)
        //����������e�C���X�^���X�𐶐�����B 
        //[����] 
        //����ID�F �����P��.����ID 
        CancelOrderSpec l_cancelOrderSpec = 
            new CancelOrderSpec(l_feqOrderUnitParams.getOrderId());

        //1.7 validate�������(SubAccount, CancelOrderSpec)(�O�����������}�l�[�W��::validate�������)
        //���������̔����R�����s���B 
        //[����] 
        //�⏕�����F �⏕�����I�u�W�F�N�g 
        //�������e�F ����������e�I�u�W�F�N�g 
        OrderValidationResult l_cancelValidationResult =
            l_feqOrderManager.validateCancelOrder(l_subAccount, l_cancelOrderSpec);
        if (l_cancelValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("�����R���`�F�b�N�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_cancelValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����R���`�F�b�N�G���[");
        }
        
        WEB3FeqOrderUnit l_web3FeqOrderUnit = (WEB3FeqOrderUnit) l_feqOrderUnit;

        BigDecimal l_bdCalcForeignCCYAmount = new BigDecimal("0");
        
        log.debug("�����P��.is���t()�̖߂�l = " + l_web3FeqOrderUnit.isBuy());
        
        //1.8 (*1) �����P��.is���t()�̖߂�l == false �̏ꍇ
        if (!l_web3FeqOrderUnit.isBuy())
        {
            //1.8.1 calc�T�Z�뉿�P��(�⏕����, long, TaxTypeEnum)
            //�T�Z�뉿�P�����v�Z����B 
            //[����] 
            //�⏕�����F �⏕�����I�u�W�F�N�g 
            //����ID�F �����P��.����ID 
            //�ŋ敪�F �����P��.�ŋ敪 
            WEB3FeqBizLogicProvider l_feqBizLogicProvider = 
                (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();

            TaxTypeEnum l_taxType = l_feqOrderUnitParams.getTaxType();
            
            BigDecimal l_bdBookValuePrice = 
                l_feqBizLogicProvider.calcEstimatedBookValuePrice(
                    l_subAccount, 
                    l_feqOrderUnitParams.getProductId(), 
                    l_taxType);
            
            //1.8.2 �ʉ݃I�u�W�F�N�g���擾����B
            WEB3GentradeCurrency l_currency = l_feqProduct.getCurrency();
                        
            //1.8.3 calc�O�݊��Z(BigDecimal, double, int, String)
            //�T�Z�뉿�P�����O�݊��Z����B 
            //[����] 
            //���z�i�~�݁j�F calc�T�Z�뉿�P��()�̖߂�l 
            //���[�g�F �ʉ�.get���t��בփ��[�g()�̖߂�l 
            //�����������F �ʉ�.get����������()�̖߂�l 
            //�O�݊��Z�ۂߕ����F �ʉ�.get�O�݊��Z�ۂߕ���()�̖߂�l
            l_bdCalcForeignCCYAmount = 
                l_feqBizLogicProvider.calcForeignCCYAmount(
                    l_bdBookValuePrice, 
                    l_currency.getSellBaseRate(), 
                    l_currency.getScale(), 
                    l_currency.getChangeFCcyRoundDiv());
            
            log.debug("calc�O�݊��Z�̖߂�l = " + l_bdCalcForeignCCYAmount);
        }
        
        //1.9 is�o����܂Œ����P��(FeqOrderUnit)
        //�o����܂Œ������ǂ����𔻒肷��B 
        //[����] 
        //�����P�ʁF �����P�ʃI�u�W�F�N�g 
        boolean l_blnCarriedOrderUnit =  
            l_feqOrderManager.isCarriedOrderUnit(l_feqOrderUnit);        
        
        //1.10 get���s�����iSONAR�j(String)(�O�����������}�l�[�W��::get���s�����iSONAR�j)
        //SONAR�̎��s�����敪���擾����B 
        //[����] 
        //���s�����F �����P��.���s���� 
        String l_strExecCondType = 
            l_feqOrderUnitParams.getExecutionConditionType().intValue() + "";
        
        String l_strExecutionConditionTypeSonar = 
            l_feqOrderManager.getExecutionConditionTypeSonar(l_strExecCondType);
        
        //1.11 get�s��ǌx���O���s��(���X : ���X)        
        //�s��ǌx���s����擾����B 
        //[����] 
        //���X�F �⏕����.get����X()�̖߂�l
        String[] l_strTradeCloseFeqMarkets = 
            WEB3GentradeTradingTimeManagement.getTradeCloseFeqMarket(
                (WEB3GentradeBranch)l_subAccount.getWeb3GenBranch());
        
        //1.12 ���X�|���X�f�[�^�𐶐�����B 
        WEB3FeqCancelConfirmResponse l_response = 
            (WEB3FeqCancelConfirmResponse) l_request.createResponse();
        
        //1.13 (*2)�v���p�e�B�Z�b�g
        //(*2) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B

        //�s��R�[�h�F �O����������.get�s��R�[�h()�̖߂�l
        l_response.marketCode = l_feqProduct.getMarketCode();
        
        //�����R�[�h�F �O����������.getProductCode()�̖߂�l
        l_response.productCode = l_feqProduct.getProductCode();
                
        //���n�����R�[�h�F �O����������.get���n�����R�[�h()�̖߂�l
        l_response.localProductCode = l_feqProduct.getOffshoreProductCode();
            
        //�������F �O����������.get�\��������()�̖߂�l
        l_response.productName = l_feqProduct.getDisplayProductName();
        
        //��������敪�F�i�ȉ��̂Ƃ���j 
        //      �����P��.�ŋ敪 == "���"�̏ꍇ�A"���"���Z�b�g�B
        //      �����P��.�ŋ敪 == "����"�̏ꍇ�A"����"���Z�b�g�B        
        if (TaxTypeEnum.NORMAL.equals(l_feqOrderUnitParams.getTaxType()))
        {
            log.debug("�����P��.�ŋ敪 == '���'�̏ꍇ");
            l_response.taxType = WEB3TaxTypeSpecialDef.NORMAL;
        }
        else if (TaxTypeEnum.SPECIAL.equals(l_feqOrderUnitParams.getTaxType()))
        {
            log.debug("�����P��.�ŋ敪 == '����'�̏ꍇ");
            l_response.taxType = WEB3TaxTypeSpecialDef.SPECIAL;
        }
            
        //�����敪�F �i�ȉ��̂Ƃ���j
        //   �����P��.������� == �h�O�������h �̏ꍇ�A�h���t�h
        //   �����P��.������� == �h�O������h �̏ꍇ�A�h���t�h
        String l_strDealingType = null;
        OrderTypeEnum l_orderType = l_feqOrderUnitParams.getOrderType();
        if (OrderTypeEnum.FEQ_BUY.equals(l_orderType))
        {
            log.debug("�����P��.������� == �h�O�������h �̏ꍇ");
            l_strDealingType = WEB3BuySellTypeDef.BUY;
        }
        else if (OrderTypeEnum.FEQ_SELL.equals(l_orderType))
        {
            log.debug("�����P��.������� == �h�O������h �̏ꍇ");
            l_strDealingType = WEB3BuySellTypeDef.SELL;
        }
        l_response.dealingType = l_strDealingType;        
        
        //���ϋ敪�F �����P��.���ϋ敪
        l_response.settleDiv = l_feqOrderUnitParams.getSettleDiv();
        
        //�������ʁF �����P��.��������
        l_response.orderQuantity = 
            WEB3StringTypeUtility.formatNumber(l_feqOrderUnitParams.getQuantity());
        
        //���o�����ʁF �����P��.��萔��        
        l_response.partContQuantity = 
            WEB3StringTypeUtility.formatNumber(
                l_feqOrderUnitParams.getExecutedQuantity());        
        
        log.debug("���o�����ʁF = " + l_response.partContQuantity);
        
        //�����P���敪�F �i�ȉ��̂Ƃ���j
        //   �����P��.�w�l == 0 �̏ꍇ�A�h���s�h
        //   �����P��.�w�l != 0 �̏ꍇ�A�h�w�l�h
        String l_strOrderPriceDiv = null;
        if (l_feqOrderUnitParams.getLimitPrice() == 0)
        {
            l_strOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
        }
        else
        {
            l_strOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
        }
        l_response.orderPriceDiv = l_strOrderPriceDiv;
        
        //�����P���F �����P��.�w�l
        l_response.limitPrice = 
            WEB3StringTypeUtility.formatNumber(l_feqOrderUnitParams.getLimitPrice());
        
        //�ʉ݃R�[�h: �O����������.get�ʉ݃R�[�h()�̖߂�l
        l_response.currencyCode = l_feqProduct.getCurrencyCode();
        
        //���s�����F get���s�����iSONAR�j()�̖߂�l
        l_response.execCondType = l_strExecutionConditionTypeSonar;
        
        //���������敪�F �i�ȉ��̂Ƃ���j
        //   is�o����܂Œ����P��()�̖߂�l == true �̏ꍇ�A�h�o����܂Œ����h
        //   is�o����܂Œ����P��()�̖߂�l == false �̏ꍇ�A�h��������h
        String l_strCarriedOrderUnit = null;
        
        log.debug("is�o����܂Œ����P��()�̖߂�l = " + l_blnCarriedOrderUnit);
        
        if (l_blnCarriedOrderUnit)
        {
            l_strCarriedOrderUnit = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
        }
        else
        {
            l_strCarriedOrderUnit = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
        }
        l_response.expirationDateType = l_strCarriedOrderUnit;
        
        //�����L�������F �i�ȉ��̂Ƃ���j
        //   is�o����܂Œ����P��()�̖߂�l == true �̏ꍇ�A�����P��.����������
        //   is�o����܂Œ����P��()�̖߂�l == false �̏ꍇ�Anull
        if (l_blnCarriedOrderUnit)
        {
            l_response.expirationDate = WEB3DateUtility.toDay(
                l_feqOrderUnitParams.getExpirationDate());
        }
        else
        {
            l_response.expirationDate = null;
        }
        
        //���������F �����P��.��������
        l_response.orderCondType = l_feqOrderUnitParams.getOrderConditionType();
        
        log.debug("�����P��.�������� = " + l_feqOrderUnitParams.getOrderConditionType());
        
        String l_strConditionType = l_response.orderCondType;
        
        //(*A)�������� == �h�t�w�l�h �̏ꍇ
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
            l_strConditionType))
        {
            log.debug("�������� == �h�t�w�l�h �̏ꍇ");
            //�t�w�l�p���������P���F �����P��.�t�w�l��l
            l_response.stopOrderCondPrice = WEB3StringTypeUtility.formatNumber(
                l_feqOrderUnitParams.getStopOrderPrice());
            
            //�t�w�l�p�����������Z�q�F �����P��.�����������Z�q
            l_response.stopOrderCondOperator = 
                l_feqOrderUnitParams.getOrderCondOperator();
        }        
        //(*B)�������� == �hW�w�l�h �̏ꍇ
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(
                l_strConditionType))
        {
            log.debug("�������� == �hW�w�l�h �̏ꍇ");
            //W�w�l�p���������P���F �����P��.�t�w�l��l
            l_response.wlimitOrderCondPrice = WEB3StringTypeUtility.formatNumber(
                l_feqOrderUnitParams.getStopOrderPrice());
            
            //W�w�l�p�����������Z�q�F �����P��.�����������Z�q
            l_response.wlimitOrderCondOperator = 
                l_feqOrderUnitParams.getOrderCondOperator();
            
            //W�w�l�p�����P���敪�F �i�ȉ��̂Ƃ���j
            //   �����P��.�iW�w�l�j�����w�l == 0 �̏ꍇ�A�h���s�h
            //   �����P��.�iW�w�l�j�����w�l != 0 �̏ꍇ�A�h�w�l�h
            String l_strWLimitOrderPriceDiv = null;
            if (l_feqOrderUnitParams.getWLimitPrice() == 0)
            {
                log.debug("�����P��.�iW�w�l�j�����w�l == 0 �̏ꍇ");
                l_strWLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            else
            {
                log.debug("�����P��.�iW�w�l�j�����w�l != 0 �̏ꍇ");
                l_strWLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            }
            l_response.wLimitOrderPriceDiv = l_strWLimitOrderPriceDiv;
            
            //W�w�l�p�����P���F �����P��.�iW�w�l�j�����w�l
            l_response.wLimitPrice = WEB3StringTypeUtility.formatNumber(
                l_feqOrderUnitParams.getWLimitPrice());            
        }

        //�T�Z��n����F �����P��.�T�Z��n���(*C)
        //(*C)�����P��.���ϋ敪 == �h�~�݁h �̏ꍇ�A�~�݊��Z��������
        //    �����P��.���ϋ敪 == �h�O�݁h �̏ꍇ�A�O�݊��Z�������� ���Z�b�g
        if (WEB3InputOutputActionSettlementDivDef.EN_SETTLE.equals(
                l_feqOrderUnitParams.getSettleDiv()))
        {            
            //QA38
            //�����P��.���ϋ敪 == �h�~�݁h �̏ꍇ�A�����P��.�T�Z��n���
            log.debug("�����P��.���ϋ敪 == �h�~�݁h �̏ꍇ");
            l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(
                l_feqOrderUnitParams.getEstimatedPrice());
        }
        else
        {
            //QA38
            //�����P��.���ϋ敪 == �h�O�݁h �̏ꍇ�A�����P��.�T�Z��n����i�O�݁j
            log.debug("�����P��.���ϋ敪 == �h�O�݁h �̏ꍇ");
            l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(
                l_feqOrderUnitParams.getFEstimatedPrice());
        }
        
        //(*D)�����P��.is���t()�̖߂�l == false �̏ꍇ
        //�T�Z�뉿�P���F calc�O�݊��Z()�̖߂�l
        if (!l_web3FeqOrderUnit.isBuy())
        {
            log.debug("�����P��.is���t()�̖߂�l == false");
            l_response.estimatedBookPrice = 
                WEB3StringTypeUtility.formatNumber(l_bdCalcForeignCCYAmount.doubleValue());
        }
        //�m�F���������F get������()�̖߂�l
        l_response.checkDate = l_datBizDate;
        
        //�����擾�敪�F null
        l_response.currentPriceGetDiv = null;
        
        //�����F null
        l_response.currentPrice = null;
        
        //�O����F null
        l_response.comparedPreviousDay = null;
        
        //������ԁF null
        l_response.currentPriceTime = null;
            
        //����I���x���s��R�[�h�ꗗ�F get�s��ǌx���O���s��()�̖߂�l
        l_response.messageSuspension = l_strTradeCloseFeqMarkets;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit����)
     * ��������̍X�V�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O������jsubmit�����v �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * 
     * @@return WEB3FeqCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 429ADE42019D
     */
    protected WEB3FeqCancelCompleteResponse submitOrder(
        WEB3FeqCancelCompleteRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "submitOrder(WEB3FeqCancelCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 �⏕�����I�u�W�F�N�g���擾����B
        SubAccount l_subAccount = this.getSubAccount();
        
        //1.3 get�㗝���͎�( )
        //�㗝���͎҃I�u�W�F�N�g���擾����B 
        Trader l_trader = this.getTrader();

        //1.4 get�����P��ByOrderId(long)
        //�����P�ʃI�u�W�F�N�g���擾����B 
        //[����] 
        //����ID�F ���N�G�X�g.����ID 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        FeqOrderUnit l_feqOrderUnit = l_feqOrderManager.getOrderUnitByOrderId(
            Long.parseLong(l_request.orderId));
        
        FeqOrderUnitParams l_feqOrderUnitParams = 
            new FeqOrderUnitParams(
                (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject());
                               
        //1.5 get������( )
        //[����] 
        //�m�F���������F ���N�G�X�g.�m�F�������� 
        WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        
        //1.6 CancelOrderSpec(����ID : long)
        //����������e�C���X�^���X�𐶐�����B 
        //[����] 
        //����ID�F �����P��.����ID 
        CancelOrderSpec l_cancelOrderSpec = 
            new CancelOrderSpec(l_feqOrderUnitParams.getOrderId());

        //1.7 validate�������(SubAccount, CancelOrderSpec)(�O�����������}�l�[�W��::validate�������)
        //���������̔����R�����s���B 
        //[����] 
        //�⏕�����F �⏕�����I�u�W�F�N�g 
        //�������e�F ����������e�I�u�W�F�N�g 
        OrderValidationResult l_cancelValidationResult =
            l_feqOrderManager.validateCancelOrder(l_subAccount, l_cancelOrderSpec);
        if (l_cancelValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("�����R���`�F�b�N�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_cancelValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����R���`�F�b�N�G���[");
        }
        
        //1.8 �O����������X�V�C���^�Z�v�^�𐶐�����B 
        //[����] 
        //�㗝���͎ҁF get�㗝���͎�()�̖߂�l
        WEB3FeqCancelUpdateInterceptor l_updateInterceptor = 
            new WEB3FeqCancelUpdateInterceptor(l_trader);
        
        //1.9 �C���^�Z�v�^���Z�b�g����B 
        // [����] 
        // �O����������X�V�C���^�Z�v�^ �F�@@�i�O����������X�V�C���^�Z�v�^�j
        l_feqOrderManager.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
        
        //1.10 submitCancelOrder()
        //��������̓o�^���s���B 
        //[����] 
        //�⏕�����F �⏕�����I�u�W�F�N�g 
        //�������e�F ����������e�I�u�W�F�N�g 
        //�p�X���[�h�F ���N�G�X�g.�Ïؔԍ� 
        //isSkip�����R���F true 
        OrderSubmissionResult l_submissionResult =
            l_feqOrderManager.submitCancelOrder(
                l_subAccount,
                l_cancelOrderSpec,
                l_request.password,
                true);
        if (l_submissionResult.getProcessingResult().isFailedResult())
        {
            log.debug("Error in submitCancelOrder" +
                l_submissionResult.getProcessingResult().getErrorInfo());
            throw new WEB3SystemLayerException(
                l_submissionResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.11 �]�͍Čv�Z(�⏕���� : �⏕����)
        //����]�͂̍X�V���s���B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        l_tpTradingPowerService.reCalcTradingPower(
            (WEB3GentradeSubAccount)l_subAccount);

        //1.12 getOrder(����ID : long)
        //�����I�u�W�F�N�g���擾����B 
        //[����] 
        //����ID�F ���N�G�X�g.����ID 
        FeqOrderRow l_feqOrderRow = null;
        try
        {
            FeqOrder l_feqOrder = (FeqOrder) 
                l_feqOrderManager.getOrder(Long.parseLong(l_request.orderId));
            
            l_feqOrderRow = (FeqOrderRow) l_feqOrder.getDataSourceObject();
        }
        catch (NotFoundException l_ex)
        {
            log.error(
                "__NotFoundException__ when "
                + "l_feqOrderManager.getOrder(OrderId) with "
                + "OrderId = "
                + Long.parseLong(l_request.orderId));
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //1.13 ���X�|���X�f�[�^�𐶐�����B
        WEB3FeqCancelCompleteResponse l_response = 
            (WEB3FeqCancelCompleteResponse) l_request.createResponse();
        
        //1.14  (*)�v���p�e�B�Z�b�g
        //(*) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        
        //�X�V���ԁF ����.�X�V���t
        l_response.lastUpdatedTimestamp = 
            l_feqOrderRow.getLastUpdatedTimestamp();
        
        //����ID�F ���N�G�X�g.����ID
        l_response.orderId = l_request.orderId;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
