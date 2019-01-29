head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesCancelOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨��������T�[�r�X�����N���X(WEB3FuturesCancelOrderServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/19 Ḗ@@�� (���u) �V�K�쐬
              001: 2004/08/05 ���Ō� (���u) Review �C��
              002: 2006/07/28 �ęԍg (���u) �d�l�ύX ���f��493
Revesion History : 2007/06/21 �����F (���u)�d�l�ύX ���f��710
Revesion History : 2008/03/14 �����F (���u)�d�l�ύX ���f��831 859
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoOrderImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3FuturesClientRequestService;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoCancelUpdateInterceptor;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.define.WEB3IfoTradeTypeDef;
import webbroker3.ifo.message.WEB3FuturesCancelCompleteRequest;
import webbroker3.ifo.message.WEB3FuturesCancelCompleteResponse;
import webbroker3.ifo.message.WEB3FuturesCancelConfirmRequest;
import webbroker3.ifo.message.WEB3FuturesCancelConfirmResponse;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.service.delegate.WEB3FuturesCancelOrderService;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3IfoQuoteData;
import webbroker3.quoteadaptor.WEB3QuoteDataSupplierService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�敨��������T�[�r�XImpl)<BR>
 * �����w���敨��������T�[�r�X�����N���X<BR>
 * @@author Ḗ@@��
 * @@version 1.0
 */
public class WEB3FuturesCancelOrderServiceImpl extends WEB3FuturesClientRequestService implements WEB3FuturesCancelOrderService
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(
            WEB3FuturesCancelOrderServiceImpl.class);

    /**
     * @@roseuid 40F7A2C40157
     */
    public WEB3FuturesCancelOrderServiceImpl()
    {

    }

    /**
     * (validate����)<BR>
     * �����w���敨�̎�������R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨����T�[�r�X�jvalidate�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �����w���敨��������m�F���N�G�X�g<BR>
     * @@return WEB3FuturesCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A8291800FC
     */
    protected WEB3FuturesCancelConfirmResponse validateOrder(WEB3FuturesCancelConfirmRequest l_request)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateOrder(WEB3FuturesCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        log.debug("�V�[�P���X�}�u�i�敨����T�[�r�X�jvalidate�����v�Q��");
        //1.1 validate()
        l_request.validate();
        
        //1.2 CancelOrderSpec(long)
        long l_lngOrderId = Long.parseLong(l_request.id);
        CancelOrderSpec l_orderSpec = new CancelOrderSpec(l_lngOrderId);

        //1.3 get�⏕����()
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount) this.getSubAccount();

        //1.4  validate�敨�������(SubAccount, CancelOrderSpec)
        //FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = GtlUtils.getTradingModule(ProductTypeEnum.IFO);
        WEB3FuturesOrderManagerImpl l_orderManager = 
            (WEB3FuturesOrderManagerImpl)l_tradingMod.getOrderManager();
        OrderValidationResult l_result = 
            l_orderManager.validateFuturesCancelOrder(l_subAccount,l_orderSpec);
        if(l_result.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_result.getProcessingResult());
            throw new WEB3BusinessLayerException(
                l_result.getProcessingResult().getErrorInfo(),
                STR_METHOD_NAME);
        }
        
        //1.5 createResponse()
        WEB3FuturesCancelConfirmResponse l_response = 
            (WEB3FuturesCancelConfirmResponse)l_request.createResponse();

        //1.6 IfoOrderImpl(long)
        IfoOrderImpl l_orderImpl = null;
        try 
        {
            l_orderImpl = new IfoOrderImpl(l_lngOrderId);
        }
        catch (DataQueryException l_ex) 
        {
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 this.getClass().getName() + "." + l_ex.getMessage()); 
        } 
        catch (DataNetworkException l_ex) 
        {
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 this.getClass().getName() + "." + l_ex.getMessage());
        }

        //1.7 getOrderUnits()
        OrderUnit l_orderUnit = l_orderImpl.getOrderUnits()[0];

        //1.8 getProduct()
        IfoProduct l_product = (IfoProduct)l_orderUnit.getProduct();

        //1.9 create���ʖ���ByOrder(long)
        WEB3FuturesOptionsContractUnit[] l_createContractUnitByOrder = l_orderManager.createContractUnitByOrder(l_lngOrderId);

        //1.10 get�s��ǌx���w��(���X, String)
        String[] l_strWarningIndex = WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(
            l_subAccount.getWeb3GenBranch(),
            WEB3FuturesOptionDivDef.FUTURES);
        
        //1.11 getTradedProduct()
        TradedProduct l_tradedProduct = l_orderUnit.getTradedProduct();

        //1.12 getQuote(tradedProduct : TradedProduct, realType : RealType)
        //[getQuote()�Ɏw�肷�����]
        // tradedProduct(��������j�F
        // getTradedProduct( )�̖߂�l�̎�������I�u�W�F�N�g 
        // realType�F  
        // �ڋq = �⏕����.getMainAccount()  
        // �ڋq.is���A���ڋq( )==true�̏ꍇ�́h���A���h�Afalse�̏ꍇ�́h20���f�B���C�h���Z�b�g�B
        WEB3IfoQuoteData l_quoteData = null;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class); 
        WEB3QuoteDataSupplierService l_supplierProvide = 
            (WEB3QuoteDataSupplierService)l_finApp.getTradingModule(
                ProductTypeEnum.IFO).getQuoteDataSupplierService();
        RealType l_realType = null;
        WEB3GentradeMainAccount  l_mainAccount = (WEB3GentradeMainAccount )l_subAccount.getMainAccount();
        if (l_mainAccount.isRealCustomer())
        {
            l_realType = RealType.REAL;
        }
        else
        {
            l_realType = RealType.DELAY;
        }
        try
        {
            l_quoteData = (WEB3IfoQuoteData)l_supplierProvide.getQuote(
                l_tradedProduct,
                l_realType);
        }
        catch (NotFoundException l_nfe)
        {
            log.error(l_nfe.getMessage(),l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(), l_nfe);
        }

        //1.13 getCurrentPrice()
        double l_dblCurrentPrice = l_quoteData.getCurrentPrice();
            
        //1.14 getChange()
        double l_dblChange = l_quoteData.getChange();
            
        //1.15 getCurrentPriceTime()
        Timestamp l_currentPriceTime = l_quoteData.getCurrentPriceTime();
        
        //1.16 get������
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        log.debug("get������:" + l_datOrderBizDate);

        //1.17  �v���p�e�B�Z�b�g
        // ���X�|���X.����敪 = �i���̔�����s���j
        OrderTypeEnum l_strOrderType = l_orderUnit.getOrderType();
        if(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.equals(l_strOrderType))
        {
            //�����P��.�������==601�i�敨�V�K���������j�̏ꍇ�A"3"
            l_response.tradingType = WEB3IfoTradeTypeDef.OPEN_LONG_CONTRACT;            
        }
        else if(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.equals(l_strOrderType))
        {
            //�����P��.�������==602�i�敨�V�K���������j�̏ꍇ�A"4"
            l_response.tradingType = WEB3IfoTradeTypeDef.OPEN_SHORT_CONTRACT;
        }
        else if(OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.equals(l_strOrderType))
        {
            //�����P��.�������==604�i�敨�����ԍϒ����j�̏ꍇ�A"5"
            l_response.tradingType = WEB3IfoTradeTypeDef.CLOSE_SELL_CONTRACT;
        }
        else if(OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE.equals(l_strOrderType))
        {
            //�����P��.�������==603�i�敨�����ԍϒ����j�̏ꍇ�A"6"
            l_response.tradingType = WEB3IfoTradeTypeDef.CLOSE_BUY_CONTRACT;
        }

        // ���X�|���X.����s�� = �����P��.�s��R�[�h(SONAR)
        IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        l_response.marketCode = l_ifoOrderUnitRow.getSonarMarketCode();

        // ���X�|���X.�w����� = �敨OP����.�����Y�R�[�h
        l_response.targetProductCode = l_product.getUnderlyingProductCode();

        // ���X�|���X.���� = �敨OP����.����
        l_response.delivaryMonth = l_product.getMonthOfDelivery();

        // ���X�|���X.�������� = �����P��.��������
        double l_orderQuantity = l_orderUnit.getQuantity();
        if(Double.isNaN(l_orderQuantity))
        {
            l_orderQuantity = 0;
        }
        l_response.futOrderQuantity = WEB3StringTypeUtility.formatNumber(l_orderQuantity);

        // ���X�|���X.����萔�� = �����P��.����萔��
        l_response.partExecQuantity = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getExecutedQuantity());
 
        // ���X�|���X.�����P���敪 = �i�����P��.�w�l==0�̏ꍇ�́h���s�h�A�ȊO�h�w�l�h�j
        // ���X�|���X.�����P�� = �����P��.�w�l
        if(l_ifoOrderUnitRow.getLimitPrice() == 0) 
        {
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE; 
            l_response.limitPrice = null;
        }
        else
        {
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            l_response.limitPrice =  WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getLimitPrice());
        }

        //���X�|���X.���s����   
        l_response.execCondType = 
            WEB3IfoDataAdapter.getExecutionCondByPr(l_ifoOrderUnitRow.getExecutionConditionType());

        // ���X�|���X.���������敪 = �敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)
        // ���X�|���X.�����L������ = �敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)�̖߂�l��
        //�@@�@@�@@�@@�@@�@@�@@�@@"�o����܂Œ���"�̏ꍇ�̂݁A�����P��.�������������Z�b�g�B
        String l_strExpirationDateType = WEB3IfoDataAdapter.getExpirationDateType((IfoOrderUnit)l_orderUnit);
        l_response.expirationDateType = l_strExpirationDateType;
        if(WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_strExpirationDateType))
        {
            l_response.expirationDate = WEB3DateUtility.toDay(l_ifoOrderUnitRow.getExpirationDate());
        }        
        else
        {
            l_response.expirationDate = null;
        }

        // ���X�|���X.���������敪 = �����P��.��������
        l_response.orderCondType = l_ifoOrderUnitRow.getOrderConditionType();
        
        String l_strOrderConditionType = l_ifoOrderUnitRow.getOrderConditionType();
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
        {                             
            // ���X�|���X.�t�w�l�p���������P�� = (**1)�����P��.�t�w�l��l
            l_response.stopOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getStopOrderPrice());

            // ���X�|���X.�t�w�l�p�����������Z�q = (**1)�����P��.�����������Z�q
            l_response.stopOrderCondOperator = l_ifoOrderUnitRow.getOrderCondOperator();
                
            l_response.wlimitOrderCondPrice = null;
            l_response.wlimitOrderCondOperator = null;
            l_response.wLimitOrderPriceDiv = null;
            l_response.wLimitPrice = null;
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {          
            l_response.stopOrderCondPrice = null;                
            l_response.stopOrderCondOperator = null;               
           
            // ���X�|���X.W�w�l�p���������P�� = (**2)�����P��.�t�w�l��l
            l_response.wlimitOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getStopOrderPrice());                       
                         
            // ���X�|���X.W�w�l�p�����������Z�q = (**2)�����P��.�����������Z�q
            l_response.wlimitOrderCondOperator = l_ifoOrderUnitRow.getOrderCondOperator();
            
            // ���X�|���X.W�w�l�p�����P���敪 = (**2)�����P��.W�w�l�p�����w�l==0�̏ꍇ�h���s�h�A�ȊO�h�w�l�h
            // ���X�|���X.W�w�l�p�����P�� = (**2)�����P��.W�w�l�p�����w�l
            if (l_ifoOrderUnitRow.getWLimitPrice() == 0)
            {
                l_response.wLimitOrderPriceDiv =  WEB3OrderPriceDivDef.MARKET_PRICE;
                l_response.wLimitPrice = null; 
            }
            else
            {
                l_response.wLimitOrderPriceDiv =  WEB3OrderPriceDivDef.LIMIT_PRICE;
                l_response.wLimitPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getWLimitPrice());                      
            }  
            
            //�v�w�l�p���s����
            l_response.wlimitExecCondType = 
                WEB3IfoDataAdapter.getExecutionCondByPr(l_ifoOrderUnitRow.getWLimitExecCondType()); 
        } 
        
        IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderUnit;
        
        //���X�|���X.�v�w�l�p�L����ԋ敪
        l_response.wlimitEnableStatusDiv = 
            WEB3IfoDataAdapter.getWLimitEnableStatusDiv(l_ifoOrderUnit);
        
        //���X�|���X.�v�w�l�p�֑ؑO�����P��
        l_response.wlimitBefChgLimitPrice = WEB3IfoDataAdapter.getWLimitBefSwitchPrice(l_ifoOrderUnit);  
        
        //���X�|���X.�v�w�l�p�֑ؑO���s����
        l_response.wlimitBefChgExecCondType = WEB3IfoDataAdapter.getWLimitBefSwitchExecCondType(l_ifoOrderUnit);
        
        //���X�|���X.�����������敪 = �����P��.��������
        l_response.orgOrderCondType = l_ifoOrderUnitRow.getOrgOrderConditionType();
  
        //���X�|���X.�����������P�� = �����P��.���t�w�l��l
        if(!l_ifoOrderUnitRow.getOrgStopOrderPriceIsNull())
        {
            l_response.orgOrderCondPrice = 
                WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getOrgStopOrderPrice());
        }

        //���X�|���X.�������������Z�q = �����P��.�������������Z�q
        l_response.orgCondOperator = l_ifoOrderUnitRow.getOrgOrderCondOperator();  
        
        //���X�|���X.��W�w�l�p�����P���敪
        String l_strOrgWLimitOrderPriceDiv = WEB3IfoDataAdapter.getOrgWLimitOrderPriceDiv(l_ifoOrderUnit);
        l_response.orgWLimitOrderPriceDiv = l_strOrgWLimitOrderPriceDiv;
               
        //���X�|���X.��W�w�l�p�����P��
        if(WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWLimitOrderPriceDiv))    
        {
            l_response.orgWLimitPrice = WEB3IfoDataAdapter.getOrgWLimitOrderPrice(l_ifoOrderUnit);
        }
               
        //���X�|���X.��W�w�l�p���s����
        l_response.orgWlimitExecCondType = WEB3IfoDataAdapter.getOrgWLimitExecCondType(l_ifoOrderUnit);    
            
        // ���X�|���X.�T�Z������i���ϑ��v�j = �����P��.�T�Z��n���
        l_response.estimatedContractPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getEstimatedPrice());

        // ���X�|���X.����I���x������ = ������ԊǗ�.get�s��ǌx���w��()�̖߂�l
        l_response.messageSuspension = l_strWarningIndex;

        // ���X�|���X.�m�F�������� = ������ԊǗ�.get������()�̖߂�l
        l_response.checkDate =  WEB3DateUtility.toDay(l_datOrderBizDate);

        // ���X�|���X.���Ϗ��� = �����P��.���Ϗ���
        l_response.closingOrder = l_ifoOrderUnitRow.getClosingOrder();

        // ���X�|���X.���ʖ��� = �icreate���ʖ���ByOrder()�̖߂�l�j
        l_response.contractUnits = l_createContractUnitByOrder;

        // ���X�|���X.���ݒl = �igetCurrentPrice()�̖߂�l�j
        //������0�̏ꍇ�Anull��ݒ肷��                                         
        if (l_dblCurrentPrice == 0D)                                            
        {                                           
            l_response.currentPrice = null;                                               
        }                                           
        else                                            
        {                                           
            //������0�łȂ��ꍇ�A�擾�������ݒl��ݒ肷��                                             
            l_response.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);                                              
        }                                           
            
        // ���X�|���X.�O���� = �igetChange()�̖߂�l�j
        l_response.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);

        // ���X�|���X.������� = �igetCurrentPriceTime()�̖߂�l�j
        l_response.currentPriceTime = l_currentPriceTime;

        //���X�|���X.����敪 = �����P��.����敪
        l_response.sessionType = l_ifoOrderUnitRow.getSessionType();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit����)<BR>
     * �����w���敨�̎��������o�^����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨����T�[�r�X�jsubmit�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �����w���敨��������������N�G�X�g<BR>
     * @@return WEB3FuturesCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A82918011B
     */
    protected WEB3FuturesCancelCompleteResponse submitOrder(WEB3FuturesCancelCompleteRequest l_request)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "submitOrder(WEB3FuturesCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate
        l_request.validate();

        //1.2 get������()
        // ���N�G�X�g�f�[�^.�m�F��������!=null�̏ꍇ�A�R�[���B
        Date l_datOrderBizDate = null;
        if (l_request.checkDate != null)
        {
            l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        }

        //1.3 CancelOrderSpec(long)
        String l_strOrderId = l_request.id;
        long l_lngOrderId = Long.parseLong(l_strOrderId);
        CancelOrderSpec l_orderSpec = new CancelOrderSpec(l_lngOrderId);

        //1.4 get�⏕����()
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();

        //1.5 validate�������(SubAccount, CancelOrderSpec)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3FuturesOrderManagerImpl l_orderManager = (WEB3FuturesOrderManagerImpl)l_tradingMod.getOrderManager();
        OrderValidationResult l_orderResult = l_orderManager.validateFuturesCancelOrder(l_subAccount,l_orderSpec);
        if(l_orderResult.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_orderResult.getProcessingResult());
            throw new WEB3BusinessLayerException(
                l_orderResult.getProcessingResult().getErrorInfo(),
                STR_METHOD_NAME);
        }
        
        //1.6 get�㗝���͎�()
        Trader l_trader = this.getTrader();
        
        //1.7 �敨OP����X�V�C���^�Z�v�^
        WEB3IfoCancelUpdateInterceptor l_interceptor = new WEB3IfoCancelUpdateInterceptor();

        //1.8 �C���^�Z�v�^.�����ID = �㗝���͎�.getTraderId()�̖߂�l
        long l_lngTraderID = 0;
        if (l_trader != null)
        {
            l_lngTraderID = l_trader.getTraderId();
        }
        l_interceptor.setTraderId(l_lngTraderID);

        //1.9 setThreadLocalPersistenceEventInterceptor
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);

        //1.10 submitCancelOrder()
        OrderSubmissionResult l_result = l_orderManager.submitCancelOrder(
            l_subAccount,
            l_orderSpec,
            l_request.password,
            true);
        if (l_result.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_result.getProcessingResult());
            throw new WEB3BusinessLayerException(
                l_result.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //getOrderUnits(����ID : long)
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(Long.parseLong(l_request.id));
        IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderUnits[0];

        //is�\�񒍕��m�F�v(IfoOrderUnit)
        boolean l_blnIsReserveOrderExist = l_orderManager.isReserveOrderExist(l_ifoOrderUnit);

        boolean l_blnCancelAllOrderUnit = false;
        //�񒍕��m�F�v�iis�\�񒍕��m�F�v()==true�j�̏ꍇ
        if (l_blnIsReserveOrderExist)
        {
            WEB3ToSuccReservationIfoOrderUpdateService l_ifoOrderUpdateService =
                (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                    WEB3ToSuccReservationIfoOrderUpdateService.class);
            //cancelAll�\�񒍕��P��(�e�����̒���ID : long)
            l_blnCancelAllOrderUnit =
                l_ifoOrderUpdateService.cancelAllOrderUnit(l_ifoOrderUnit.getOrderId());
        }

        //1.11 createResponse()
        WEB3FuturesCancelCompleteResponse l_response = (WEB3FuturesCancelCompleteResponse)l_request.createResponse();

        if (l_result.getProcessingResult().isSuccessfulResult())
        {
            // ���X�|���X.�X�V���� = ���ݓ���(GtlUtils.getSystemTimestamp())
            l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
            
            // ���X�|���X.���ʔԍ� = ���N�G�X�g�f�[�^.����ID
            l_response.orderActionId = l_request.id;

            // ���X�|���X.�A�������ݒ�t���O = cancelAll�\�񒍕��P�ʂ̖߂�l
            l_response.succSettingFlag = l_blnCancelAllOrderUnit;
        }
        else
        {
            log.debug("ProcessingResult() = " + l_result.getProcessingResult());
            throw new WEB3BaseException(
                l_result.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * �����w���敨��������T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate����()�܂��́Asubmit����()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A82918012B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        if(l_request instanceof WEB3FuturesCancelConfirmRequest)
        {
            l_response = this.validateOrder((WEB3FuturesCancelConfirmRequest)l_request);
        }
        else if(l_request instanceof WEB3FuturesCancelCompleteRequest)
        {
            l_response = this.submitOrder((WEB3FuturesCancelCompleteRequest)l_request);
        }
        else
        {
            log.error(STR_METHOD_NAME + "�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
