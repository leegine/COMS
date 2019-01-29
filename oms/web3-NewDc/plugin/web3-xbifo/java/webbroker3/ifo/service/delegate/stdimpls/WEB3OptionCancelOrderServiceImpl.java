head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionCancelOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : OP��������T�[�r�XImpl(WEB3OptionCancelOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/18 ���� �V�K�쐬
              001: 2004/06/24 ���� submit����   �C��
              002: 2004/07/22 ���Ō� (���u) WEB3OrderPriceDivDef��WEB3IfoOrderPriceDivDef�������ւ���
              003: 2004/08/09 ���Ō� (Sinocom) �Ή�����:�yWEB3-XBIFO-A-CD-0082�z     
              004: 2004/08/13 ������@@(���u) STBUG(IFO_ST-000079)��Ή�
              005: 2004/08/13 li-qiang�@@(���u) STBUG(IFO_ST-000103)��Ή�
              006: 2004/08/15 ������@@(���u) BUG83��Ή�
              012: 2006/07/13 ���G�� (���u) �d�l�ύX�@@���f��477
Revesion History : 2007/06/08 ���^�] (���u) �d�l�ύX���f��No.656
Revesion History : 2007/06/21 �Ј��� (���u) �d�l�ύX���f��712
Revesion History : 2008/04/14 �����F (���u) ���f�� 844
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
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
import webbroker3.ifo.WEB3IfoCancelUpdateInterceptor;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3OptionClientRequestService;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.ifo.define.WEB3IfoTradeTypeDef;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.message.WEB3OptionsCancelCompleteRequest;
import webbroker3.ifo.message.WEB3OptionsCancelCompleteResponse;
import webbroker3.ifo.message.WEB3OptionsCancelConfirmRequest;
import webbroker3.ifo.message.WEB3OptionsCancelConfirmResponse;
import webbroker3.ifo.service.delegate.WEB3OptionCancelOrderService;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3IfoQuoteData;
import webbroker3.quoteadaptor.WEB3QuoteDataSupplierService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (OP��������T�[�r�XImpl)<BR>
 * �����w���I�v�V������������T�[�r�X�����N���X<BR>
 * 
 * @@author ����
 * @@version 1.0
 */
public class WEB3OptionCancelOrderServiceImpl extends WEB3OptionClientRequestService implements WEB3OptionCancelOrderService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3OptionCancelOrderServiceImpl.class);    
    
    /**
     * @@roseuid 40C0BD6E02FD
     */
    public WEB3OptionCancelOrderServiceImpl() 
    {
     
    }
    
    /**
     * (validate����)<BR>
     * �����w���I�v�V�����̎�������R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iOP����T�[�r�X�jvalidate�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * �����w���I�v�V������������m�F���N�G�X�g
     * @@return webbroker3.ifo.message.WEB3OptionsCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 405169280118
     */
    protected WEB3OptionsCancelConfirmResponse validateOrder(
        WEB3OptionsCancelConfirmRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3OptionsCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {   
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);                 
        }
          
        log.debug("�V�[�P���X�}�u�iOP����T�[�r�X�jvalidate�����v�Q��");
        //1.1 ���N�G�X�g�f�[�^�̃`�F�b�N�����{����
        l_request.validate();
        
        //1.2 ����Ώۂ̒����h�c���w�肵�A����������e�iCancelOrderSpec�j�𐶐�����
        long l_lngOrderId = Long.parseLong(l_request.id);    
        CancelOrderSpec l_cancelOrderSpec = new CancelOrderSpec(l_lngOrderId);
        
        //1.3 �⏕�������擾����
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount();
            
        //1.4 validate�������(�⏕���� : SubAccount, ����������e : CancelOrderSpec)
        //OP�����}�l�[�W�����擾����
        TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager = (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        OrderValidationResult l_result = l_orderManager.validateCancelOrder(l_subAccount,l_cancelOrderSpec);
        if (l_result.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_result.getProcessingResult());
            throw new WEB3BusinessLayerException(
                l_result.getProcessingResult().getErrorInfo(),
                STR_METHOD_NAME);  
        }

        try 
        {
            //1.5 ���X�|���X�f�[�^����
            WEB3OptionsCancelConfirmResponse l_response = 
                (WEB3OptionsCancelConfirmResponse)l_request.createResponse();        
            
            //1.6 �����h�c���w�肵�Ē����I�u�W�F�N�g���擾����
            IfoOrderImpl l_ifoOrder = (IfoOrderImpl)l_orderManager.getOrder(l_lngOrderId);          
            
            //1.7 �����P�ʎ擾            
            OrderUnit[] l_orderUnits = l_ifoOrder.getOrderUnits();
            
            //1.8 �敨OP�����I�u�W�F�N�g���擾
            OrderUnit l_orderUnit = l_orderUnits[0];
            IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();          
            
            // �v���_�N�gID���擾����
            long l_lngProductId = l_ifoOrderUnitRow.getProductId();
            // �g���v���_�N�g�}�l�[�W��
            WEB3IfoProductManagerImpl l_productManager = (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
            WEB3IfoProductImpl l_ifoProduct = (WEB3IfoProductImpl)l_productManager.getProduct(l_lngProductId);     
            
            //1.9 ���ʖ��ׂ��쐬����
            WEB3FuturesOptionsContractUnit[] l_web3ContractUnits = 
                l_orderManager.createContractUnitByOrder(l_lngOrderId);
            
            //1.10 �s��ǌx���w�����擾����
            // [get�s��ǌx���w��()�Ɏw�肷�����]
            // ���X�F�@@�⏕����.get����X()
            // �敨�^�I�v�V�����敪�F�@@�h�I�v�V�����h
            String[] l_strTradeCloseSuspension = WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(
                l_subAccount.getWeb3GenBranch(),
                WEB3FuturesOptionDivDef.OPTION);     

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

            //1.16 ���������擾����            
            Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            log.debug("l_datBizDate =" + l_datBizDate);    

            //1.17.get�v�w�l�p�L����ԋ敪(IfoOrderUnit)
            String l_strWlimitEnableStatusDiv = WEB3IfoDataAdapter.getWLimitEnableStatusDiv((IfoOrderUnit) l_orderUnit);
            
            //1.18.get�v�w�l�p�֑ؑO�����P��(IfoOrderUnit)
            String l_strWlimitBefChgLimitPrice = WEB3IfoDataAdapter.getWLimitBefSwitchPrice((IfoOrderUnit) l_orderUnit);
            
            //1.19.get�v�w�l�p�֑ؑO���s����(IfoOrderUnit)
            String l_strWLimitBefChgExecCondType = WEB3IfoDataAdapter.getWLimitBefSwitchExecCondType((IfoOrderUnit) l_orderUnit);
            
            //1.20 �v���p�e�B�Z�b�g
            // ���X�|���X.����敪 = �����P��.�������         
            if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_ifoOrderUnitRow.getOrderType()))
            {
                // �����P��.������� = "605"�iOP�V�K���������j=> "3"
                l_response.tradingType = WEB3IfoTradeTypeDef.OPEN_LONG_CONTRACT;
            }
            else if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.equals(l_ifoOrderUnitRow.getOrderType()))
            {
                // �����P��.������� = "606"�iOP�V�K���������j=> "4"
                l_response.tradingType = WEB3IfoTradeTypeDef.OPEN_SHORT_CONTRACT;
            }
            else if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.equals(l_ifoOrderUnitRow.getOrderType()))
            {
                // �����P��.������� = "608"�iOP�����ԍϒ����j=> "5"
                l_response.tradingType = WEB3IfoTradeTypeDef.CLOSE_SELL_CONTRACT;
            }
            else if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.equals(l_ifoOrderUnitRow.getOrderType()))
            {
                // �����P��.������� = "607"�iOP�����ԍϒ����j=> "6"
                l_response.tradingType = WEB3IfoTradeTypeDef.CLOSE_BUY_CONTRACT;
            }

            // ���X�|���X.����s�� = �敨OP����
            l_response.marketCode = l_ifoProduct.getPrimaryMarket().getMarketCode();
  
            // ���X�|���X.�w����� = �敨OP����.�����Y�����R�[�h
            l_response.targetProductCode = l_ifoProduct.getUnderlyingProductCode();
            
            // ���X�|���X.���� = �敨OP����.����           
            l_response.delivaryMonth = ((IfoProductRow)l_ifoProduct.getDataSourceObject()).getMonthOfDelivery();

            // ���X�|���X.�I�v�V�������i�敪 = �敨OP����
            // P�F�v�b�g�I�v�V���� C�F�R�[���I�v�V����
            if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(
                ((IfoProductRow) l_ifoProduct.getDataSourceObject()).getDerivativeType()))
            {
                l_response.opProductType = WEB3IfoProductTypeDef.CALL_OPTIONS;                           
            }
            else
            {
                l_response.opProductType = WEB3IfoProductTypeDef.PUT_OPTIONS;            
            }            
            
            // ���X�|���X.�s�g���i = �敨OP����.�s�g���i
            double l_dblStrikePrice = ((IfoProductRow)l_ifoProduct.getDataSourceObject()).getStrikePrice();
            if (Double.isNaN(l_dblStrikePrice))
            {
                l_dblStrikePrice = 0D;
            }
            l_response.strikePrice = WEB3StringTypeUtility.formatNumber(l_dblStrikePrice);

            // ���X�|���X.�������� = �����P��.��������
            double l_dblIfoQuantity = l_ifoOrderUnitRow.getQuantity();
            if (Double.isNaN(l_dblIfoQuantity))
            {
                l_dblIfoQuantity = 0D;
            }
            l_response.opOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblIfoQuantity);

            // ���X�|���X.����萔�� = �����P��.��萔��
            l_response.partExecQuantity = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getExecutedQuantity());
            
            // ���X�|���X.�����P���敪 = �i�����P��.�w�l==0�̏ꍇ�́h���s�h�A�ȊO�h�w�l�h�j
            // ���X�|���X.�����P�� = �����P��.�w�l
            if (l_ifoOrderUnitRow.getLimitPrice() == 0)
            {
                l_response.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                l_response.limitPrice = null;             
            }
            else 
            {
                l_response.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;         
                l_response.limitPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getLimitPrice());             
            }
              
        	//���X�|���X.���s���� = �敨OP�f�[�^�A�_�v�^.get���s�����iPR�w�j(�����P��.���s����)�̖߂�l
            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnits[0].getDataSourceObject();
            l_response.execCondType = WEB3IfoDataAdapter.getExecutionCondByPr(
        		l_orderUnitRow.getExecutionConditionType());
           
            //���X�|���X.���������敪 = �敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)
            l_response.expirationDateType = WEB3IfoDataAdapter.getExpirationDateType((IfoOrderUnit)l_orderUnit);

            //���X�|���X.�����L������ = �敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)�̖߂�l��"�o����܂Œ���"��
            // �ꍇ�̂݁A�����P��.�������������Z�b�g�B�ȊO�̏ꍇ�Anull���Z�b�g�B
            if ((WEB3OrderExpirationDateTypeDef.CARRIED_ORDER).equals(
                WEB3IfoDataAdapter.getExpirationDateType((IfoOrderUnit)l_orderUnit)))
            {
                l_response.expirationDate = WEB3DateUtility.toDay(l_ifoOrderUnitRow.getExpirationDate());
            }
            else
            {
                l_response.expirationDate = null;
            }
            
            //���X�|���X.���������敪 = �����P��.��������           
            String l_strOrderConditionType = l_ifoOrderUnitRow.getOrderConditionType();
            l_response.orderCondType = l_strOrderConditionType;                    
             
            //(**1) �����P��.�������� == �h�t�w�l�h�̏ꍇ�̂݃Z�b�g�B�ȊOnull�B
            //(**2) �����P��.�������� == �hW�w�l�h�̏ꍇ�̂݃Z�b�g�B�ȊOnull�B                                 
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
            {   
                //���X�|���X.�t�w�l�p�v���~�A���^�����Y���i = (**1)�����P��.�t�w�l�����^�C�v  
                l_response.stopPremium_underlyingAssets = l_ifoOrderUnitRow.getStopPriceType();   

                //���X�|���X.�t�w�l�p���������P�� = (**1)�����P��.�t�w�l��l
                l_response.stopOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getStopOrderPrice());

                //���X�|���X.�t�w�l�p�����������Z�q = (**1)�����P��.�����������Z�q
                l_response.stopOrderCondOperator = l_ifoOrderUnitRow.getOrderCondOperator();
                
                l_response.wlimitOrderCondPrice = null;
                l_response.wlimitOrderCondOperator = null;
                l_response.wLimitOrderPriceDiv = null;
                l_response.wLimitPrice = null;
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
            {          
                l_response.stopPremium_underlyingAssets = null;
                l_response.stopOrderCondPrice = null;                
                l_response.stopOrderCondOperator = null;               
                
                //���X�|���X.W�w�l�p�v���~�A���^�����Y���i = (**2)�����P��.�t�w�l�����^�C�v
                l_response.wlimitPremium_underlyingAssets = l_ifoOrderUnitRow.getStopPriceType(); 

                //���X�|���X.W�w�l�p���������P�� = (**2)�����P��.�t�w�l��l
                l_response.wlimitOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getStopOrderPrice());                       
                         
                //���X�|���X.W�w�l�p�����������Z�q = (**2)�����P��.�����������Z�q
                l_response.wlimitOrderCondOperator = l_ifoOrderUnitRow.getOrderCondOperator();

                //���X�|���X.W�w�l�p�����P���敪 = (**2)�����P��.W�w�l�p�����w�l==0�̏ꍇ�h���s�h�A�ȊO�h�w�l�h
                //���X�|���X.W�w�l�p�����P�� = (**2)�����P��.W�w�l�p�����w�l
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
                
                //���X�|���X.W�w�l�p���s���� = �敨OP�f�[�^�A�_�v�^.get���s����
                //�iPR�w�j(�����P��.�iW�w�l�j���s����)�̖߂�l
                l_response.wlimitExecCondType = WEB3IfoDataAdapter.getExecutionCondByPr(
            		l_orderUnitRow.getWLimitExecCondType());              
            }            
            
			//���X�|���X.W�w�l�p�L����ԋ敪���敨OP�f�[�^�A�_�v�^.get�v�w�l�p�L����ԋ敪(�j�̖߂�l
            l_response.wlimitEnableStatusDiv = l_strWlimitEnableStatusDiv;
            
			//���X�|���X.W�w�l�p�֑ؑO�����P�����敨OP�f�[�^�A�_�v�^.get�v�w�l�p�֑ؑO�����P��()�̖߂�l
            l_response.wlimitBefChgLimitPrice = l_strWlimitBefChgLimitPrice;
            
			//���X�|���X.W�w�l�p�֑ؑO���s�������敨OP�f�[�^�A�_�v�^.get�v�w�l�p�֑ؑO���s����()�̖߂�l
            l_response.wlimitBefChgExecCondType = l_strWLimitBefChgExecCondType;
            
			//���X�|���X.�����������敪�������P��.����������
            l_response.orgOrderCondType = l_orderUnitRow.getOrgOrderConditionType();
            
			//���X�|���X.���v���~�A���^�����Y���i�������P��.���t�w�l��l�^�C�v
            l_response.orgPremium_underlyingAssets = l_orderUnitRow.getOrgStopPriceType();
            
			//���X�|���X.�����������P���������P��.���t�w�l��l
            if (!l_orderUnitRow.getOrgStopOrderPriceIsNull())
            {
                l_response.orgOrderCondPrice = WEB3StringTypeUtility.formatNumber(
                    l_orderUnitRow.getOrgStopOrderPrice());
            }
            
			//���X�|���X.�������������Z�q�������P��.�������������Z�q
            l_response.orgCondOperator = l_orderUnitRow.getOrgOrderCondOperator();
            
			//���X�|���X.���v�w�l�p�����P���敪���敨OP�f�[�^�A�_�v�^.get��W�w�l�p�����P���敪(�����P��)
            l_response.orgWLimitOrderPriceDiv = 
            	WEB3IfoDataAdapter.getOrgWLimitOrderPriceDiv((IfoOrderUnit) l_orderUnit);
            
			//���X�|���X.���v�w�l�p�����P�������v�w�l�p�����P���敪��"�w�l"��
            //�ꍇ�̂݁A�敨OP�f�[�^�A�_�v�^.get���v�w�l�p�����P��(�����P��)
            if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_response.orgWLimitOrderPriceDiv))
            {
            	l_response.orgWLimitPrice = WEB3IfoDataAdapter.getOrgWLimitOrderPrice((IfoOrderUnit) l_orderUnit);
            }
		
            //���X�|���X.���v�w�l�p���s�������敨OP�f�[�^�A�_�v�^.get��W�w�l�p���s����(�����P��)
            l_response.orgWlimitExecCondType = 
            	WEB3IfoDataAdapter.getOrgWLimitExecCondType((IfoOrderUnit) l_orderUnit);
            
            //���X�|���X.�T�Z��n��� = �����P��.�T�Z��n���
            l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(
        		l_ifoOrderUnitRow.getEstimatedPrice());

            //���X�|���X.����I���x������ = ������ԊǗ�.get�s��ǌx���w��()�̖߂�l
            l_response.messageSuspension = l_strTradeCloseSuspension;

            //���X�|���X.�m�F�������� = ������ԊǗ�.get������()�̖߂�l
            l_response.checkDate = WEB3DateUtility.toDay(l_datBizDate);

            //���X�|���X.���Ϗ��� = �����P��.���Ϗ���
            l_response.closingOrder = l_ifoOrderUnitRow.getClosingOrder();

            //���X�|���X.���ʖ��� = �icreate���ʖ���ByOrder()�̖߂�l      
            l_response.contractUnits = l_web3ContractUnits;

            //getCurrentPrice�̕Ԃ�l                                   
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
            
            //���X�|���X.�O���� = �igetChange()�̖߂�l�j
            l_response.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);

            //���X�|���X.������� = �igetCurrentPriceTime()�̖߂�l�j
            l_response.currentPriceTime = l_currentPriceTime;

            //���X�|���X.����敪 = �����P��.get����敪()
            l_response.sessionType = l_ifoOrderUnitRow.getSessionType();

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (NotFoundException l_nfe)
        {
            log.error(l_nfe.getMessage(),l_nfe);
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
    }
    
    /**
     * (submit����)<BR>
     * �����w���I�v�V�����̎��������o�^����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iOP����T�[�r�X�jsubmit�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �����w���I�v�V������������������N�G�X�g<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 405169280137
     */
    protected WEB3OptionsCancelCompleteResponse submitOrder(
        WEB3OptionsCancelCompleteRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3OptionsCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
            
        if (l_request == null)
        {   
            throw new WEB3BaseException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);                 
        }
 
        //1.1 ���N�G�X�g�f�[�^�̃`�F�b�N�����{����
        l_request.validate();

        //1.2 ���������擾����B
        //  [get������()�Ɏw�肷�����]
        //  �m�F���������F���N�G�X�g�f�[�^.�m�F��������
        Date l_datBizDate = null;
        if (l_request.checkDate != null)
        {
            l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        }

        //1.3 ����Ώۂ̒����h�c���w�肵�A����������e�iCancelOrderSpec�j�𐶐�����
        long l_lngOrderId = Long.parseLong(l_request.id);
        CancelOrderSpec l_cancelOrderSpec = new CancelOrderSpec(l_lngOrderId);
 
        //1.4 �⏕�������擾����
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();
        log.debug("l_subAccount = " + l_subAccount);    

        //1.5 ������������R�����s��
        //OP�����}�l�[�W�����擾����
        TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager = (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        OrderValidationResult l_result = l_orderManager.validateCancelOrder(
            l_subAccount,
            l_cancelOrderSpec);
        if (l_result.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_result.getProcessingResult());
            throw new WEB3BaseException(
                l_result.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);        
        } 

        //1.6 get�㗝���͎�()
        Trader l_trader = this.getTrader();

        //1.7 �敨OP����X�V�C���^�Z�v�^�𐶐�����
        WEB3IfoCancelUpdateInterceptor l_interceptor = new WEB3IfoCancelUpdateInterceptor();

        //1.8 �C���^�Z�v�^.�����ID = �㗝���͎�.getTraderId()�̖߂�l
        long l_lngTraderID = 0;
        if (l_trader != null)
        {
            l_lngTraderID = l_trader.getTraderId();
        }
        l_interceptor.setTraderId(l_lngTraderID);

        //1.9 �C���^�Z�v�^���Z�b�g����
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);

        //1.10 submitCancelOrder()
        OrderSubmissionResult l_orderResult = null;
        l_orderResult = l_orderManager.submitCancelOrder(
            l_subAccount,
            l_cancelOrderSpec,       
            l_request.password,
            true);
        if (l_orderResult.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_orderResult.getProcessingResult());
            throw new WEB3BusinessLayerException(
                l_orderResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //�⏕���� != �؋��������̏ꍇ�A����]�̓T�[�r�X.�]�͍Čv�Z()��call����B
        if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
        {
            log.debug("�����^�C�v�F�@@" + l_subAccount.getSubAccountType());
            //1.11 [�]�͍Čv�Z()�Ɏw�肷�����]
            //  �⏕�����F�@@�⏕����
            WEB3TPTradingPowerService l_tradingPowerService =
                (WEB3TPTradingPowerService)Services.getService(
                    WEB3TPTradingPowerService.class);
            l_tradingPowerService.reCalcTradingPower(l_subAccount);
        }

        //getOrderUnits(����ID : long)
        //����ID�F�@@���N�G�X�g�f�[�^.����ID
        IfoOrderUnit l_ifoOrderUnit =
            (IfoOrderUnit)(l_orderManager.getOrderUnits(Long.parseLong(l_request.id)))[0];

        //is�\�񒍕��m�F�v(IfoOrderUnit)
        boolean l_blnIsReserveOrderExist = l_orderManager.isReserveOrderExist(l_ifoOrderUnit);

        //�\�񒍕��m�F�v�iis�\�񒍕��m�F�v() == true�j�̏ꍇ
        boolean l_blnCancelAllOrderUnit = false;
        if (l_blnIsReserveOrderExist)
        {
            //cancelAll�\�񒍕��P��(�e�����̒���ID : long)
            WEB3ToSuccReservationIfoOrderUpdateService l_ifoOrderUpdateService =
                (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                    WEB3ToSuccReservationIfoOrderUpdateService.class);
            l_blnCancelAllOrderUnit = l_ifoOrderUpdateService.cancelAllOrderUnit(l_ifoOrderUnit.getOrderId());
        }

        //1.14 ���X�|���X�f�[�^����
        WEB3OptionsCancelCompleteResponse l_response =
            (WEB3OptionsCancelCompleteResponse)l_request.createResponse();

        //1.15 (*2)�v���p�e�B�Z�b�g
        //  ���X�|���X.�X�V���� = ���ݓ���(GtlUtils.getSystemTimestamp())
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
        //  ���X�|���X.���ʔԍ� = ���N�G�X�g�f�[�^.����ID
        l_response.orderActionId = l_request.id;
        //  ���X�|���X.�A�������ݒ�t���O = cancelAll�\�񒍕��P�ʂ̖߂�l
        l_response.succSettingFlag = l_blnCancelAllOrderUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * �����w���I�v�V�����������<BR>�T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate����()�܂��́A<BR>submit����()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 405172F801E3
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3OptionsCancelConfirmRequest)
        {
            l_response = this.validateOrder((WEB3OptionsCancelConfirmRequest)l_request);
        }
        else if(l_request instanceof WEB3OptionsCancelCompleteRequest)
        {
            l_response = this.submitOrder((WEB3OptionsCancelCompleteRequest)l_request);
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
