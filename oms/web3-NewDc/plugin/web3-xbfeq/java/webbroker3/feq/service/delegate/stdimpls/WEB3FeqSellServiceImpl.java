head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.38.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqSellServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���������t�T�[�r�XImpl(WEB3FeqSellServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/19 ���� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[   
Revesion History : 2008/01/21 �đo�g(���u) ���f��No.381�A���f��No.372
Revesion History : 2010/01/12 �����F(���u) ���f��No.531
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3InputOutputActionSettlementDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqAmountCalcResult;
import webbroker3.feq.WEB3FeqBizLogicProvider;
import webbroker3.feq.WEB3FeqClientRequestService;
import webbroker3.feq.WEB3FeqNewOrderSpec;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUpdateInterceptor;
import webbroker3.feq.WEB3FeqPositionManager;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.WEB3FeqProductQuote;
import webbroker3.feq.WEB3FeqTradedProduct;
import webbroker3.feq.message.WEB3FeqSellCompleteRequest;
import webbroker3.feq.message.WEB3FeqSellCompleteResponse;
import webbroker3.feq.message.WEB3FeqSellConfirmRequest;
import webbroker3.feq.message.WEB3FeqSellConfirmResponse;
import webbroker3.feq.message.WEB3FeqSellInputRequest;
import webbroker3.feq.message.WEB3FeqSellInputResponse;
import webbroker3.feq.service.delegate.WEB3FeqSellService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�O���������t�T�[�r�XImpl)<BR>
 * �O���������t�T�[�r�X�����N���X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqSellServiceImpl extends WEB3FeqClientRequestService 
    implements WEB3FeqSellService 
{
    
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqSellServiceImpl.class);
        
    /**
     * @@roseuid 42CE39F80186
     */
    public WEB3FeqSellServiceImpl() 
    {
     
    }
        
    /**
     * �O���������t�T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���R�[������B<BR>
     * <BR>
     *    get���͉��()<BR>
     *    validate����()<BR>
     *    submit����()<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 428AFE1A0354
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
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3FeqSellInputRequest)
        {
            //get���͉��
            l_response =
                this.getInputScreen(
                    (WEB3FeqSellInputRequest) l_request);
        }
        else if (l_request instanceof WEB3FeqSellConfirmRequest)
        {
            //validate����
            l_response =
                this.validateOrder(
                    (WEB3FeqSellConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3FeqSellCompleteRequest)
        {
            //submit����
            l_response =
                this.submitOrder(
                    (WEB3FeqSellCompleteRequest) l_request);
        }
        else
        {
            log.debug(
                "���N�G�X�g�f�[�^��"
                    + " WEB3FeqSellInputRequest "
                    + "�� WEB3FeqSellConfirmRequest "
                    + "�� WEB3FeqSellCompleteRequest�ȊO�ł���, but is "
                    + l_request.getClass().getName());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���͉��)<BR>
     * ���͉�ʂ̕\���ɕK�v�ȃf�[�^���擾����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�O�����t�jget���͉�ʁv �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return webbroker3.feq.message.WEB3FeqSellInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 428AFE1A0364
     */
    protected WEB3FeqSellInputResponse getInputScreen(WEB3FeqSellInputRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getInputScreen(WEB3FeqSellInputRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1)validate( )
        l_request.validate();
        
        //1.2)get�⏕����()
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount) this.getSubAccount();
        
        //1.3)validate����(�⏕����)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        l_feqOrderManager.validateOrder(l_subAccount);       
        
        //1.4 getAsset(�ۗL���YID : long)
        WEB3FeqPositionManager l_feqPositionManager =
            (WEB3FeqPositionManager) l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getPositionManager();
        //�ۗL���Y        
        AssetRow l_asset = null;
        long l_lngAsstId = 0;
        if (!WEB3StringTypeUtility.isEmpty(l_request.assetId))
        {
            l_lngAsstId = Long.parseLong(l_request.assetId);
        }
        try
        {
            l_asset = (AssetRow) 
                l_feqPositionManager.getAsset(l_lngAsstId).getDataSourceObject();
        }
        catch (NotFoundException l_ex)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //����ID 
        long l_lngProductId = l_asset.getProductId();
        
        //1.5 get�O����������(long)
        //�O�����������I�u�W�F�N�g���擾����B 
        //[����] 
        //����ID�F �ۗL���Y.����ID 
        WEB3FeqProductManager l_feqProductManager =
            (WEB3FeqProductManager) GtlUtils.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getProductManager();
        WEB3FeqProduct l_feqProduct =
            (WEB3FeqProduct) l_feqProductManager.getFeqProduct(l_lngProductId);
            
        //�s��R�[�h       
        String l_strMarketCode = l_feqProduct.getMarketCode();

        //1.6 get������( )                
        WEB3GentradeTradingTimeManagement.getOrderBizDate();
            
        //1.7 get�s��( )
        WEB3GentradeMarket l_market = l_feqProduct.getMarket();
        
        //1.8 validate�O������(�،����, String)
        //[����] 
        //�،���ЁF �⏕����.getInstitutuin()�̖߂�l 
        //�����R�[�h�F �O����������.�����R�[�h 
        
        //�،����
        WEB3GentradeInstitution l_institution = 
            (WEB3GentradeInstitution) l_subAccount.getInstitution();  
        l_feqProduct = 
            (WEB3FeqProduct) l_feqOrderManager.validateFeqProduct(
                l_institution, 
                l_feqProduct.getProductCode());
       
        //1.9 validate�s��(�s��)
        l_feqOrderManager.validateMarket(l_market);   

        //1.10 validate�������(�O����������, �s��, boolean)
        //��������̃`�F�b�N���s���B 
        //[����] 
        //�O�����������F �O�����������I�u�W�F�N�g 
        //�s��F get�s��()�̖߂�l 
        //is�������F false 
        WEB3FeqTradedProduct l_feqTradedProduct =
            (WEB3FeqTradedProduct)l_feqOrderManager.validateTradedProduct(
                l_feqProduct,
                l_market,
                false);
                
        //1.11 validate�ڋq�����ʎ����~(SubAccount, long, OrderTypeEnum)
        //�ڋq�����ʎ�������`�F�b�N���s���B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //����ID�F �O����������.����ID 
        //������ʁF �h�O������h 
        l_feqOrderManager.validateAccountProductTradedStop(
            l_subAccount, 
            l_feqProduct.getProductId(), 
            OrderTypeEnum.FEQ_SELL);
            
        //1.12 �戵�\��������(String, ProductTypeEnum, String, String, String)
        //�戵�\���������C���X�^���X���擾����B 
        //[����] 
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
        //�����^�C�v�F �h�O�������h 
        //�敨�^�I�v�V�����敪�F �hDEFAULT�h(�Œ�) 
        //�M�p����敪�F �hDEFAULT�h(�Œ�) 
        //�s��R�[�h�F �s��.�s��R�[�h 
        String l_stringInstitutionCode = 
            l_subAccount.getInstitution().getInstitutionCode();
        WEB3GentradeHandlingOrderCond l_gentradeHandlingOrderCond =
            new WEB3GentradeHandlingOrderCond(
                l_stringInstitutionCode,
                ProductTypeEnum.FOREIGN_EQUITY,
                WEB3FuturesOptionDivDef.DEFAULT,
                WEB3MarginTradingDivDef.DEFAULT,
                l_strMarketCode);
                
        //1.13 �戵�\���s�����擾( )
        String[] l_strHandlingPossibleExecConds = 
            l_gentradeHandlingOrderCond.getHandlingPossibleExecCond();
        
        //1.14 �戵�\���������擾( )      
        String[] l_strHandlingPossibleOrderConds = 
            l_gentradeHandlingOrderCond.getHandlingPossibleOrderCond();
            
        //1.15 �戵�\���������敪�擾( )
        String[] l_strHandlingPossibleExpirationDateTypes =
            l_gentradeHandlingOrderCond.getHandlingPossibleExpirationDateType();
        
        //1.16 is�o����܂Œ����戵�\( )
        boolean l_blnIsOrderUntilHandling =
            l_gentradeHandlingOrderCond.isOrderUntilDeadLinePossibleHandling();
        log.debug("is�o����܂Œ����戵�\()�̖߂�l ==" 
            + l_blnIsOrderUntilHandling);
            
        //1.17 (*3) is�o����܂Œ����戵�\()�̖߂�l == true �̏ꍇ
        
         //�o����܂Œ����J�n��
         Date l_datorderUntilDeadLineStartDay = null;
         //�o����܂Œ����ŏI��
         Date l_datOrderUntilDeadLineEndDay = null;       
         //�����������j���ꗗ
         Date[] l_datExpirationDateHolidays = null;
        
         if (l_blnIsOrderUntilHandling)
         {
             //1.17.1 get�o����܂Œ����J�n��()
             l_datorderUntilDeadLineStartDay = 
                 l_gentradeHandlingOrderCond.getOrderUntilDeadLineStartDay();
                
             //1.17.2 get�o����܂Œ����ŏI��()
             l_datOrderUntilDeadLineEndDay = 
                 l_gentradeHandlingOrderCond.getOrderUntilDeadLineEndDay();
             
             //1.17.3 get�����������j���ꗗ()
             l_datExpirationDateHolidays =
                 l_gentradeHandlingOrderCond.getExpirationDateHoliday();
         }
         
        //1.18 get�s��ǌx���O���s��(���X : ���X) 
        String[] l_strTradeCloseFeqMarkets =
            WEB3GentradeTradingTimeManagement.getTradeCloseFeqMarket(
                l_subAccount.getWeb3GenBranch());
                
        //1.19 get�\���p�������(�O�������������, �⏕����)
        //[����] 
        //��������F �O��������������I�u�W�F�N�g 
        //�⏕�����F �⏕�����I�u�W�F�N�g 
        WEB3FeqProductQuote l_feqProductQuote =
            l_feqProductManager.getIndicationCurrentPriceUnit(
                l_feqTradedProduct, l_subAccount);
        
        //�����擾�敪
        String l_strCurrentPriceGetDiv = null;
        //����
        String l_strCurrentPrice = null;
        //�O����
        String l_strComparedPreviousDay = null;
        //�������\����
        Date l_DatCurrentPricePublicTime = null;
        boolean l_blnfeqProductQuoteIsNull = true;
        
        if (l_feqProductQuote != null)
        {
            l_blnfeqProductQuoteIsNull = false;
            
            //1.20 get�����擾�敪( )
            l_strCurrentPriceGetDiv =
                l_feqProductQuote.getCurrentPriceGetDiv();

            //1.21 get����( )
            l_strCurrentPrice = 
                WEB3StringTypeUtility.formatNumber(
                    l_feqProductQuote.getCurrentPrice());
            
            //1.22 get�O����( )
            l_strComparedPreviousDay = 
                WEB3StringTypeUtility.formatNumber(
                    l_feqProductQuote.getComparedPreviousDay());
        
            //1.23 get�������\����( )
            l_DatCurrentPricePublicTime = 
                l_feqProductQuote.getCurrentPricePublicTime();
        }       
            
        //1.24 calc�T�Z�뉿�P��(�⏕����, long, TaxTypeEnum)    
        //�T�Z�뉿�P�����v�Z����B 
        //[����] 
        //�⏕�����F �⏕�����I�u�W�F�N�g 
        //����ID�F �ۗL���Y.����ID 
        //�ŋ敪�F �ۗL���Y.�ŋ敪 
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqBizLogicProvider l_feqBizLogicProvider =
            (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
        BigDecimal l_bdCalcEstimatedBookValuePrice = 
            l_feqBizLogicProvider.calcEstimatedBookValuePrice(
                l_subAccount,
                l_lngProductId,
                l_asset.getTaxType());
                
        //1.25 get�ʉ�( )        
        WEB3GentradeCurrency l_currency = l_feqProduct.getCurrency();
            
        //1.26 calc�O�݊��Z(BigDecimal, double, int, String)
        //�T�Z�뉿�P�����O�݊��Z����B 
        //[����] 
        //���z�i�~�݁j�F calc�T�Z�뉿�P��()�̖߂�l 
        //���[�g�F �ʉ�.get���t��בփ��[�g()�̖߂�l 
        //�����������F �ʉ�.get����������()�̖߂�l 
        //�O�݊��Z�ۂߕ����F �ʉ�.get�O�݊��Z�ۂߕ���()�̖߂�l 
        BigDecimal l_bdCalcForeignCCYAmount = 
            l_feqBizLogicProvider.calcForeignCCYAmount(
                l_bdCalcEstimatedBookValuePrice,
                l_currency.getSellBaseRate(),
                l_currency.getScale(),
                l_currency.getChangeFCcyRoundDiv());
                
        //1.27 createResponse( )
        WEB3FeqSellInputResponse l_response = 
            (WEB3FeqSellInputResponse)l_request.createResponse();
            
        //1.28 (*3)�v���p�e�B�Z�b�g
        //(*3) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        
        //�����P���敪�ꗗ�F �h���s�h�Ɓh�w�l�h�̋敪�̔z��
        String[] l_strPriceDivs = 
            {WEB3OrderPriceDivDef.MARKET_PRICE, 
             WEB3OrderPriceDivDef.LIMIT_PRICE};
        l_response.orderPriceDivList = l_strPriceDivs;
            
        //���s�����ꗗ�F �戵�\���s�����擾()�̖߂�l
        l_response.execCondList = l_strHandlingPossibleExecConds;
        
        //���������敪�ꗗ�F �戵�\���������敪�擾()�̖߂�l
        l_response.expirationDateTypeList = 
            l_strHandlingPossibleExpirationDateTypes;
            
        //�L�������J�n���F get�o����܂Œ����J�n��()�̖߂�l
        l_response.expirationStartDate = l_datorderUntilDeadLineStartDay;
        
        //�L�������I�����F get�o����܂Œ����ŏI��()�̖߂�l
        l_response.expirationEndDate = l_datOrderUntilDeadLineEndDay;
        
        //�L���������j���ꗗ�F get�����������j���ꗗ()�̖߂�l
        l_response.holidayList = l_datExpirationDateHolidays;
        
        //���������ꗗ�F �戵�\���������擾()�̖߂�l
        l_response.orderCondTypeList = l_strHandlingPossibleOrderConds;
        
        if (!l_blnfeqProductQuoteIsNull)
        {
            //�����擾�敪�F get�����擾�敪()�̖߂�l
            l_response.currentPriceGetDiv = l_strCurrentPriceGetDiv;
        
            //�����F get����()�̖߂�l
            l_response.currentPrice = l_strCurrentPrice;
        
            //�O����F get�O����()�̖߂�l
            l_response.comparedPreviousDay = l_strComparedPreviousDay;
            
            //������ԁF get�������\����()�̖߂�l
            l_response.currentPriceTime = l_DatCurrentPricePublicTime;
        }
        
        //����I���x���s��R�[�h�ꗗ�F get�s��ǌx���s��()�̖߂�l
        l_response.messageSuspension = l_strTradeCloseFeqMarkets;
        
        //�����R�[�h�F �O����������.get�����R�[�h()�̖߂�l
        l_response.productCode = l_feqProduct.getProductCode();
        
        //���n�����R�[�h�F �O����������.get���n�����R�[�h()�̖߂�l
        l_response.localProductCode = l_feqProduct.getOffshoreProductCode();
        
        //�������F �O����������.get�\��������()�̖߂�l
        l_response.productName = l_feqProduct.getDisplayProductName();
        
        //�s��R�[�h�F �O����������.get�s��R�[�h()�̖߂�l
        l_response.marketCode = l_feqProduct.getMarketCode();
        
        //�������ʁF �ۗL���Y.����() - �ۗL���Y.getLockedQuantity()
        double l_lockedQuantity =
            l_feqPositionManager.getAsset(l_subAccount.getAccountId(), l_subAccount.getSubAccountId(),
                l_lngProductId, l_asset.getTaxType()).getLockedQuantity();
        
        l_response.orderQuantity = 
            WEB3StringTypeUtility.formatNumber(l_asset.getQuantity() - l_lockedQuantity);
                
        //�����敪�ꗗ�F �i�ȉ��̂Ƃ���j
        String l_strTaxType = null;
        // �ۗL���Y.�ŋ敪�����h��ʁh �̏ꍇ�A �h��ʁh
        if (TaxTypeEnum.NORMAL.equals(l_asset.getTaxType()))
        {
            l_strTaxType = WEB3TaxTypeSpecialDef.NORMAL;
        }
        //�ۗL���Y.�ŋ敪 ���h����h �̏ꍇ�A �h����h
        else if (TaxTypeEnum.SPECIAL.equals(l_asset.getTaxType()))
        {
            l_strTaxType = WEB3TaxTypeSpecialDef.SPECIAL;
        }
        l_response.taxType = l_strTaxType;
        
        //�ʉ݃R�[�h�F �O����������.get�ʉ݃R�[�h()�̖߂�l
        l_response.currencyCode = l_feqProduct.getCurrencyCode();
        
        //���ϋ敪�ꗗ�F �i�ȉ��̂Ƃ���j
        //�O��������������擾�����ʉ݂̎c����ۗL���Ă���(*)�ꍇ�A�h�~�݁h�Ɓh�O�݁h�̋敪�̔z��
        //�O��������������擾�����ʉ݂̎c����ۗL���ĂȂ��ꍇ�A�h�~�݁h�݂̂̔z��
        //(*)�O�݂̎c���ۗL�̔�����@@�̏ڍׂ́A����
		//�b��Ή��Ƃ��ď�ɉ~�݂��Z�b�g����悤�ɏC��
		String[] l_strSettleDivList = null;
		l_strSettleDivList = new String[1];
		l_strSettleDivList[0] = WEB3InputOutputActionSettlementDivDef.EN_SETTLE;
		l_response.settleDivList = l_strSettleDivList;
        
        //�T�Z�뉿�P���F calc�O�݊��Z()�̖߂�l
        l_response.estimatedBookPrice = 
            WEB3StringTypeUtility.formatNumber(l_bdCalcForeignCCYAmount.doubleValue());
            
        log.exiting(STR_METHOD_NAME);    
        return l_response;
    }
    
    /**
     * (validate����)<BR>
     * ���t�����̊m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�O�����t�jvalidate�����v �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return webbroker3.feq.message.WEB3FeqSellConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 428AFE1A0383
     */
    protected WEB3FeqSellConfirmResponse validateOrder(WEB3FeqSellConfirmRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateOrder(WEB3FeqSellConfirmRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1)validate( )
        l_request.validate();
        
        //1.2)get�⏕����()
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount) this.getSubAccount();
        
        //1.3 get�㗝���͎�( )
        WEB3GentradeTrader l_trade = (WEB3GentradeTrader) this.getTrader();
        
        //1.4 getAsset(�ۗL���YID : long)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3FeqPositionManager l_feqPositionManager =
            (WEB3FeqPositionManager) l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getPositionManager();
        //�ۗL���Y        
        AssetRow l_asset = null;
        long l_lngAsstId = 0;
        if (!WEB3StringTypeUtility.isEmpty(l_request.assetId))
        {
            l_lngAsstId = Long.parseLong(l_request.assetId);
        }
        try
        {
            l_asset = (AssetRow) 
                l_feqPositionManager.getAsset(l_lngAsstId).getDataSourceObject();
        }
        catch (NotFoundException l_ex)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //����ID 
        long l_lngProductId = l_asset.getProductId();
        
        //1.5 get�O����������(long)
        //�O�����������I�u�W�F�N�g���擾����B 
        //[����] 
        //����ID�F �ۗL���Y.����ID 
        WEB3FeqProductManager l_feqProductManager =
            (WEB3FeqProductManager) GtlUtils.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getProductManager();
        WEB3FeqProduct l_feqProduct =
            (WEB3FeqProduct) l_feqProductManager.getFeqProduct(l_lngProductId);
            
        //�s��R�[�h       
        String l_strMarketCode = l_feqProduct.getMarketCode();

        //1.6 get������( )                
        Date l_datOrderBizDate = 
            WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //1.7 get���s����(String)
        //���s�����F ���N�G�X�g.���s���� 
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        FeqExecutionConditionType l_feqExecutionCond = 
            l_feqOrderManager.getExecutionCondition(l_request.execCondType);
           
         //1.8 create�V�K�������e   
        //�V�K�������e�𐶐�����B 
        //[����] 
        //�،���ЃR�[�h�F �⏕����.getInstitution().getInstitutionCode()�̖߂�l 
        //���ҁF get�㗝���͎�()�̖߂�l 
        //is���t�����F false 
        //�����R�[�h�F �O����������.�����R�[�h 
        //�s��R�[�h�F�O����������.�s��R�[�h 
        //�������ʁF ���N�G�X�g.�������� 
        //�����P���F ���N�G�X�g.�����P�� 
        //���s�����F get���s����()�̖߂�l 
        //�����������F ���N�G�X�g.�����L������ 
        //�ŋ敪�F �ۗL���Y.�ŋ敪 
        //�ʉ݃R�[�h�F �O����������.�ʉ݃R�[�h 
        //���������F ���N�G�X�g.�������� 
        //�iW�w�l�j�����w�l�F ���N�G�X�g.W�w�l�p�����P�� 
        //���ϋ敪�F ���N�G�X�g.���ϋ敪 
        //���񒍕��̒����P��ID�F �i�ȉ��̂Ƃ���j 
        //   ���N�G�X�g.���������敪 == �h��������h �̏ꍇ�Anull 
        //   ���N�G�X�g.���������敪 == �h�o����܂Œ����h �̏ꍇ�A0 
        String l_stringInstitutionCode = 
            l_subAccount.getInstitution().getInstitutionCode();
        Long l_lngFirstOrderUnitId = null;
        if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_request.expirationDateType))
        {
            l_lngFirstOrderUnitId = null;
        }
        else
        {
            if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_request.expirationDateType))
            {
                l_lngFirstOrderUnitId = new Long(0);
            }
        }
        
        //�����P��
        double l_dblLimitPrice = 0.0D;
        //W�w�l�p�����P��
        double l_dblWLimitPrice = 0.0D;
        if (!WEB3StringTypeUtility.isEmpty(l_request.limitPrice))
        {
            l_dblLimitPrice = Double.parseDouble(l_request.limitPrice);
        }
        if (!WEB3StringTypeUtility.isEmpty(l_request.wLimitPrice))
        {
            l_dblWLimitPrice = Double.parseDouble(l_request.wLimitPrice);
        }
        WEB3FeqNewOrderSpec l_feqNewOrderSpec =
            WEB3FeqNewOrderSpec.createNewOrderSpec(
                l_stringInstitutionCode,
                l_trade,
                false,
                l_feqProduct.getProductCode(),
                l_strMarketCode,
                Double.parseDouble(l_request.orderQuantity),
                l_dblLimitPrice,
                l_feqExecutionCond,
                l_request.expirationDate,
                l_asset.getTaxType(),
                l_feqProduct.getCurrencyCode(),
                l_request.orderCondType,
                l_dblWLimitPrice,
                l_request.settleDiv,
                l_lngFirstOrderUnitId);

        //1.9  validate�V�K����(SubAccount, ProductTypeEnum, NewOrderSpec)        
        //�V�K�����̔����R�����s���B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�����^�C�v�F �h�O�������h 
        //�������e�F �O�������V�K�������e�I�u�W�F�N�g 
        NewOrderValidationResult  l_newOrderValidationResult =
            l_feqOrderManager.validateNewOrder(
                l_subAccount,
                ProductTypeEnum.FOREIGN_EQUITY, 
                l_feqNewOrderSpec);
       
        if (l_newOrderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("�������e�̃`�F�b�N���s�� Error " +
                l_newOrderValidationResult.getProcessingResult().getErrorInfo());
            throw new WEB3BusinessLayerException(
                l_newOrderValidationResult.getProcessingResult().getErrorInfo(),
                STR_METHOD_NAME);
        }
        
        //1.10 get�v�Z�p�����P��(�O�������������, ���X, String, double, double, boolean)
        //�v�Z�p�����P�����擾����B 
        //[����] 
        //��������F �O����������.get�������()�̖߂�l 
        //���X�F �⏕����.get����X()�̖߂�l 
        //�����P���敪�F ���N�G�X�g.�����P���敪 
        //�����P���F ���N�G�X�g.�����P�� 
        //�����P���F ���N�G�X�g.W�w�l�p�����P�� 
        //is���t�F false 
        
        //���X
        WEB3GentradeBranch l_branch = 
            (WEB3GentradeBranch) l_subAccount.getMainAccount().getBranch();
        double l_dblUnitPrice =
            l_feqOrderManager.getUnitPrice(
                l_feqProduct.getFeqTradedProduct(),
                l_branch,
                l_request.orderPriceDiv,
                l_dblLimitPrice,
                l_dblWLimitPrice,
                false);
                
        //1.11 calc�������(double, double)
        //��������i�O�݁j���v�Z����B 
        //[����] 
        //�����F ���N�G�X�g.�������� 
        //�P���F get�v�Z�p�����P��()�̖߂�l 
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqBizLogicProvider l_feqBizLogicProvider =
            (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
        double l_dblCalcExecutionAmount =
            l_feqBizLogicProvider.calcExecutionAmount(
                Double.parseDouble(l_request.orderQuantity), 
                l_dblUnitPrice);  
                
        //1.12 get�s��( )
        WEB3GentradeMarket l_market = l_feqProduct.getMarket();
        
        //1.13 get�ʉ�( )
        WEB3GentradeCurrency l_currency = l_feqProduct.getCurrency();

//		calc�������(double, double)�̌��ʂ��A���Y�ʉ݂̏����_�����Ŋۂ߂�
		BigDecimal l_bdTradePriceFc = new BigDecimal(l_dblCalcExecutionAmount);
		int l_intDecimalPlace = l_currency.getScale();
		l_bdTradePriceFc = l_bdTradePriceFc.setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
		l_dblCalcExecutionAmount = l_bdTradePriceFc.doubleValue();
		log.debug("��������̊ۂ߂����� �� "+ l_dblCalcExecutionAmount);
                
        //1.14 get���t��בփ��[�g
        double l_dblSellBaseFxRate = l_currency.getSellBaseRate();   

        //1.15 calc�O���������z(�⏕����, �O����������, �s��, Date, 
        //      double, double, boolean, boolean, boolean)
        //�e����z�̌v�Z���s���B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�O�����������F get�O����������()�̖߂�l 
        //�s��F get�s��()�̖߂�l 
        //����F get������()�̖߂�l 
        //��������i�O�݁j�F calc�������()�̖߂�l 
        //�בփ��[�g�F get���t��בփ��[�g()�̖߂�l 
        //is���t�F false 
        //is���v�Z�F false 
        //is�w�l�F �i�ȉ��̂Ƃ���j 
        //   ���N�G�X�g.�����P���敪 == �h�w�l�h �̏ꍇ�Atrue 
        //   ���N�G�X�g.�����P���敪 == �h���s�h �̏ꍇ�Afalse 
        //�����`���l���F�@@this.get���O�C���`���l��()
        boolean l_blnOrderPrice = true;
        if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
        {
            l_blnOrderPrice = false;
        }
        else
        {
            if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_request.orderPriceDiv))
            {
                l_blnOrderPrice = true;
            }
        }
        WEB3FeqAmountCalcResult l_feqAmountCalcResult =
            l_feqBizLogicProvider.calcFeqAmount(
                l_subAccount,
                l_feqProduct,
                l_market,
                l_datOrderBizDate,
                l_datOrderBizDate,
                l_dblCalcExecutionAmount,
                l_dblSellBaseFxRate,
                false,
                false,
                l_blnOrderPrice,
                this.getLoginChannel());
        
        //1.16 calc�T�Z�뉿�P��(�⏕����, long, TaxTypeEnum)
        //�T�Z�뉿�P�����v�Z����B 
        //[����] 
        //�⏕�����F �⏕�����I�u�W�F�N�g 
        //����ID�F �ۗL���Y.����ID 
        //�ŋ敪�F �ۗL���Y.�ŋ敪 
        BigDecimal l_bdCalcEstimatedBookValuePrice = 
            l_feqBizLogicProvider.calcEstimatedBookValuePrice(
                l_subAccount,
                l_lngProductId,
                l_asset.getTaxType());
                
        //1.17 calc�O�݊��Z(double, double, int, String)
        //�T�Z�뉿�P�����O�݊��Z����B 
        //[����] 
        //���z�i�~�݁j�F calc�T�Z�뉿�P��()�̖߂�l 
        //���[�g�F get���t��בփ��[�g()�̖߂�l 
        //�����������F �ʉ�.get����������()�̖߂�l 
        //�O�݊��Z�ۂߕ����F �ʉ�.get�O�݊��Z�ۂߕ���()�̖߂�l 
        BigDecimal l_bdCalcForeignCCYAmount = 
            l_feqBizLogicProvider.calcForeignCCYAmount(
                l_bdCalcEstimatedBookValuePrice,
                l_dblSellBaseFxRate,
                l_currency.getScale(),
                l_currency.getChangeFCcyRoundDiv());
        
        //�O�����������X�V�C�x���g�C���^�Z�v�^
        //�������e�F �O�������V�K�������e�I�u�W�F�N�g
        //�v�Z���ʁF calc�O���������z()�̖߂�l
        //�v�Z�P���F get�v�Z�p�����P��()�̖߂�l
        //���������F ���N�G�X�g.��������
        //�����������Z�q�F �i�ȉ��̂Ƃ���j
        //   ���N�G�X�g.�������� == �h�t�w�l�h �̏ꍇ�A���N�G�X�g.�t�w�l�p�����������Z�q
        //   ���N�G�X�g.�������� == �hW�w�l�h �̏ꍇ�A���N�G�X�g.W�w�l�p�����������Z�q
        //���������P���F �i�ȉ��̂Ƃ���j
        //   ���N�G�X�g.�������� == �h�t�w�l�h �̏ꍇ�A���N�G�X�g.�t�w�l�p���������P��
        //   ���N�G�X�g.�������� == �hW�w�l�h �̏ꍇ�A���N�G�X�g.W�w�l�p���������P��
        //�����������Z�q
        String l_strOrderCondOperator = null;
        //���������P��
        double l_dblOrderCondPrice = 0.0D;
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_strOrderCondOperator = l_request.stopOrderCondOperator;
            l_dblOrderCondPrice =
                Double.parseDouble(l_request.stopOrderCondPrice);
        }
        else
        {
            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_strOrderCondOperator = l_request.wlimitOrderCondOperator;
                l_dblOrderCondPrice =
                    Double.parseDouble(l_request.wlimitOrderCondPrice);
            }
        }
        WEB3FeqOrderUpdateInterceptor l_feqOrderUpdateInterceptor =
            new WEB3FeqOrderUpdateInterceptor(
                l_feqNewOrderSpec,
                l_feqAmountCalcResult,
                l_dblUnitPrice,
                l_request.orderCondType,
                l_strOrderCondOperator,
                l_dblOrderCondPrice);

        // is���v�����̗p( )
        WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_subAccount.getInstitution();
        boolean l_blnIsDayTradeAdoption = l_institution.isDayTradeAdoption();

        //is���v��s��( )
        boolean l_blnIsDayTradeMarket = l_market.isDayTradeMarket();

        //is���v�����̗p()��true ���@@is���v��s��()��true�̏ꍇ
        if (l_blnIsDayTradeAdoption && l_blnIsDayTradeMarket)
        {
            //validate����]��
            WEB3TPTradingPowerService l_tpTradingPowerService = 
                (WEB3TPTradingPowerService) Services.getService(
                    WEB3TPTradingPowerService.class);
            //�O�����������X�V�C���^�Z�v�^��v�f�Ƃ����z�� 
            WEB3FeqOrderUpdateInterceptor[] l_feqOrderUpdateInterceptors = {l_feqOrderUpdateInterceptor};

            //�O�������V�K�������e��v�f�Ƃ����z�� 
            WEB3FeqNewOrderSpec[] l_feqNewOrderSpecs = {l_feqNewOrderSpec}; 

            WEB3TPTradingPowerResult l_tPTradingPowerResult =
                l_tpTradingPowerService.validateTradingPower(
                    l_subAccount,
                    l_feqOrderUpdateInterceptors,
                    l_feqNewOrderSpecs,
                    OrderTypeEnum.FEQ_SELL,
                    false);
            if (l_tPTradingPowerResult == null)
            {
                log.debug("�\�����Ȃ��V�X�e���G���[���������܂����B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�\�����Ȃ��V�X�e���G���[���������܂����B");
            }
            //�߂�l�̎���]�͌���.����t���O == false �̏ꍇ�A��O���X���[����B
            if (!l_tPTradingPowerResult.isResultFlg())
            {
                log.debug("����]�̓`�F�b�N�G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����]�̓`�F�b�N�G���[�B");
            }
        }

        //1.18 get�s��ǌx���O���s��(���X : ���X)
        //���X�F �⏕����.get����X()�̖߂�l 
        String[] l_strTradeCloseFeqMarkets = 
            WEB3GentradeTradingTimeManagement.getTradeCloseFeqMarket(l_branch);
            
        //1.19 createNewOrderId( )
        //�V�K�̒���ID���擾����B 
        long l_lngNewOrderId = l_feqOrderManager.createNewOrderId();

        //1.20  createResponse( )
        WEB3FeqSellConfirmResponse l_response = 
            (WEB3FeqSellConfirmResponse)l_request.createResponse();
        
        //(*2) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        
        //���t��בցF get���t��בփ��[�g()�̖߂�l
        l_response.sellExchange = 
            WEB3StringTypeUtility.formatNumber(l_dblSellBaseFxRate);
        
        //�T�Z�뉿�P���F calc�O�݊��Z�̖߂�l
        l_response.estimatedBookPrice = 
            WEB3StringTypeUtility.formatNumber(l_bdCalcForeignCCYAmount.doubleValue());
            
        //����ID�F createNewOrderId()�̖߂�l
        l_response.orderId = l_lngNewOrderId + "";
        
        //�m�F���P���F get�v�Z�p�����P��()�̖߂�l
        l_response.checkPrice = 
            WEB3StringTypeUtility.formatNumber(l_dblUnitPrice);
        
        //�m�F���������F get������()�̖߂�l
        l_response.checkDate = l_datOrderBizDate;
        
        //����I���x���s��R�[�h�ꗗ�F get�s��ǌx���s��()�̖߂�l
        l_response.messageSuspension = l_strTradeCloseFeqMarkets;

        //(*A)���N�G�X�g.���ϋ敪 == �h�~�݁h �̏ꍇ�A�ݒ肷��l�͉~�݊��Z���ꂽ���́B
        if (WEB3InputOutputActionSettlementDivDef.EN_SETTLE.equals(l_request.settleDiv))
        {
            //�T�Z��n����F �O���������z�v�Z����.��n���(*A)
            l_response.estimatedPrice = 
                WEB3StringTypeUtility.formatNumber(
                    l_feqAmountCalcResult.getNetAmount());

            //�萔���F �O���������z�v�Z����.�ϑ��萔��(*A)
            l_response.commission = 
                WEB3StringTypeUtility.formatNumber(
                    l_feqAmountCalcResult.getCommissionFee());

            //�萔������ŁF �O���������z�v�Z����.�ϑ��萔�������(*A)
            l_response.commissionConsumptionTax = 
                WEB3StringTypeUtility.formatNumber(
                    l_feqAmountCalcResult.getCommisionFeeTax());
        }
        else
        {
            //���N�G�X�g.���ϋ敪 == �h�O�݁h �̏ꍇ�A�ݒ肷��l�͊O�݊��Z���ꂽ���́B
            if (WEB3InputOutputActionSettlementDivDef.FOREIGN_SETTLE.equals(l_request.settleDiv))
            {
                //�T�Z��n����F �O���������z�v�Z����.��n���(�O��)
                l_response.estimatedPrice = 
                    WEB3StringTypeUtility.formatNumber(
                        l_feqAmountCalcResult.getNetAmountFc());
        
                //�萔���F �O���������z�v�Z����.�ϑ��萔��(�O��)
                l_response.commission = 
                    WEB3StringTypeUtility.formatNumber(
                        l_feqAmountCalcResult.getCommissionFeeFc());
            
                //�萔������ŁF �O���������z�v�Z����.�ϑ��萔�������(�O��)
                l_response.commissionConsumptionTax = 
                    WEB3StringTypeUtility.formatNumber(
                        l_feqAmountCalcResult.getCommisionFeeTaxFc());
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit����)<BR>
     * ���t�����̓o�^�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�O�����t�jsubmit�����v �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return webbroker3.feq.message.WEB3FeqSellCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 428AFE1A0392
     */
    protected WEB3FeqSellCompleteResponse submitOrder(WEB3FeqSellCompleteRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "submitOrder(WEB3FeqSellCompleteRequest l_request)";
            
        //1.1) validate( )
        l_request.validate();
        
        //1.2)get�⏕����()
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount) this.getSubAccount();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        
        //1.3 get�㗝���͎�( )
        WEB3GentradeTrader l_trade = (WEB3GentradeTrader) this.getTrader(); 
        
        //1.4 getAsset(�ۗL���YID : long)
        WEB3FeqPositionManager l_feqPositionManager =
            (WEB3FeqPositionManager) l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getPositionManager();
        //�ۗL���Y        
        AssetRow l_asset = null;
        long l_lngAsstId = 0;
        if (!WEB3StringTypeUtility.isEmpty(l_request.assetId))
        {
            l_lngAsstId = Long.parseLong(l_request.assetId);
        }
        try
        {
            l_asset = (AssetRow) 
                l_feqPositionManager.getAsset(l_lngAsstId).getDataSourceObject();
        }
        catch (NotFoundException l_ex)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //����ID 
        long l_lngProductId = l_asset.getProductId();
        
        //1.5 get�O����������(long)
        //�O�����������I�u�W�F�N�g���擾����B 
        //[����] 
        //����ID�F �ۗL���Y.����ID 
        WEB3FeqProductManager l_feqProductManager =
            (WEB3FeqProductManager) GtlUtils.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getProductManager();
        WEB3FeqProduct l_feqProduct =
            (WEB3FeqProduct) l_feqProductManager.getFeqProduct(l_lngProductId);
        
        //�s��R�[�h       
        String l_strMarketCode = l_feqProduct.getMarketCode();

        //1.6 get������(Date)            
        Date l_datOrderBizDate = 
            WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);

        //1.7 get���s����(String)
        //���s�����F ���N�G�X�g.���s���� 
        FeqExecutionConditionType l_executionCond = 
            l_feqOrderManager.getExecutionCondition(l_request.execCondType);
            
        //1.8 create�V�K�������e   
        //�V�K�������e�𐶐�����B 
        //[����] 
        //�،���ЃR�[�h�F �⏕����.getInstitution().getInstitutionCode()�̖߂�l 
        //���ҁF get�㗝���͎�()�̖߂�l 
        //is���t�����F false 
        //�����R�[�h�F �O����������.�����R�[�h 
        //�s��R�[�h�F�O����������.�s��R�[�h 
        //�������ʁF ���N�G�X�g.�������� 
        //�����P���F ���N�G�X�g.�����P�� 
        //���s�����F get���s����()�̖߂�l 
        //�����������F ���N�G�X�g.�����L������ 
        //�ŋ敪�F �ۗL���Y.�ŋ敪 
        //�ʉ݃R�[�h�F �O����������.�ʉ݃R�[�h 
        //���������F ���N�G�X�g.�������� 
        //�iW�w�l�j�����w�l�F ���N�G�X�g.W�w�l�p�����P�� 
        //���ϋ敪�F ���N�G�X�g.���ϋ敪 
        //���񒍕��̒����P��ID�F �i�ȉ��̂Ƃ���j 
        //   ���N�G�X�g.���������敪 == �h��������h �̏ꍇ�Anull 
        //   ���N�G�X�g.���������敪 == �h�o����܂Œ����h �̏ꍇ�A0 
        String l_stringInstitutionCode = 
            l_subAccount.getInstitution().getInstitutionCode();
        Long l_lngFirstOrderUnitId = null;
        if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_request.expirationDateType))
        {
            l_lngFirstOrderUnitId = null;
        }
        else
        {
            if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_request.expirationDateType))
            {
                l_lngFirstOrderUnitId = new Long(0);
            }
        }
        //�����P��
        double l_dblLimitPrice = 0.0D;
        //W�w�l�p�����P��
        double l_dblWLimitPrice = 0.0D;
        if (!WEB3StringTypeUtility.isEmpty(l_request.limitPrice))
        {
            l_dblLimitPrice = Double.parseDouble(l_request.limitPrice);
        }
        if (!WEB3StringTypeUtility.isEmpty(l_request.wLimitPrice))
        {
            l_dblWLimitPrice = Double.parseDouble(l_request.wLimitPrice);
        }
        WEB3FeqNewOrderSpec l_feqNewOrderSpec =
            WEB3FeqNewOrderSpec.createNewOrderSpec(
                l_stringInstitutionCode,
                l_trade,
                false,
                l_feqProduct.getProductCode(),
                l_strMarketCode,
                Double.parseDouble(l_request.orderQuantity),
                l_dblLimitPrice,
                l_executionCond,
                l_request.expirationDate,
                l_asset.getTaxType(),
                l_feqProduct.getCurrencyCode(),
                l_request.orderCondType,
                l_dblWLimitPrice,
                l_request.settleDiv,
                l_lngFirstOrderUnitId);
        
        //1.9 validate�V�K����
        //�V�K�����̔����R�����s���B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�����^�C�v�F �h�O�������h 
        //�������e�F �O�������V�K�������e�I�u�W�F�N�g 
        NewOrderValidationResult  l_newOrderValidationResult =
            l_feqOrderManager.validateNewOrder(
                l_subAccount,
                ProductTypeEnum.FOREIGN_EQUITY, 
                l_feqNewOrderSpec);
       
        if (l_newOrderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("�������e�̃`�F�b�N���s�� Error " +
                l_newOrderValidationResult.getProcessingResult().getErrorInfo());
            throw new WEB3BusinessLayerException(
                l_newOrderValidationResult.getProcessingResult().getErrorInfo(),
                STR_METHOD_NAME);
        }
        
        //1.10 calc�������(double, double)
        
        //�m�F���P��
        double l_dblCheckPrice = 0.0D;
        if (!WEB3StringTypeUtility.isEmpty(l_request.checkPrice))
        {
            l_dblCheckPrice = Double.parseDouble(l_request.checkPrice);
        }
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqBizLogicProvider l_feqBizLogicProvider =
            (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
        double l_dblCalcExecutionAmount =
            l_feqBizLogicProvider.calcExecutionAmount(
                Double.parseDouble(l_request.orderQuantity), 
                l_dblCheckPrice);
        
        //1.11 get�s��( )
        WEB3GentradeMarket l_market = l_feqProduct.getMarket();
        
        //1.12 get�ʉ�( )
        WEB3GentradeCurrency l_currency = l_feqProduct.getCurrency();
        
        //1.13 get���t��בփ��[�g( )
        double l_dblSellBaseFxRate = l_currency.getSellBaseRate();

//		calc�������(double, double)�̌��ʂ��A���Y�ʉ݂̏����_�����Ŋۂ߂�
		BigDecimal l_bdTradePriceFc = new BigDecimal(l_dblCalcExecutionAmount);
		int l_intDecimalPlace = l_currency.getScale();
		l_bdTradePriceFc = l_bdTradePriceFc.setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
		l_dblCalcExecutionAmount = l_bdTradePriceFc.doubleValue();
		log.debug("��������̊ۂ߂����� �� "+ l_dblCalcExecutionAmount);
        
        //1.14 calc�O���������z(�⏕����, �O����������, �s��, Date, 
        //      double, double, boolean, boolean, boolean)
        //�e����z�̌v�Z���s���B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�O�����������F get�O����������()�̖߂�l 
        //�s��F get�s��()�̖߂�l 
        //����F get������()�̖߂�l 
        //��������i�O�݁j�F calc�������()�̖߂�l 
        //�בփ��[�g�F get���t��בփ��[�g()�̖߂�l 
        //is���t�F false 
        //is���v�Z�F false 
        //is�w�l�F �i�ȉ��̂Ƃ���j 
        //   ���N�G�X�g.�����P���敪 == �h�w�l�h �̏ꍇ�Atrue 
        //   ���N�G�X�g.�����P���敪 == �h���s�h �̏ꍇ�Afalse
        //�����`���l���F�@@this.get���O�C���`���l��()
        boolean l_blnOrderPrice = true;
        if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
        {
            l_blnOrderPrice = false;
        }
        else
        {
            if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_request.orderPriceDiv))
            {
                l_blnOrderPrice = true;
            }
        }
        WEB3FeqAmountCalcResult l_feqAmountCalcResult =
            l_feqBizLogicProvider.calcFeqAmount(
                l_subAccount,
                l_feqProduct,
                l_market,
                l_datOrderBizDate,
                l_datOrderBizDate,
                l_dblCalcExecutionAmount,
                l_dblSellBaseFxRate,
                false,
                false,
                l_blnOrderPrice,
                this.getLoginChannel());       
                
        //1.15 �O�����������X�V�C�x���g�C���^�Z�v�^
        //[����] 
        //�������e�F �O�������V�K�������e�I�u�W�F�N�g 
        //�v�Z���ʁF calc�O���������z()�̖߂�l 
        //�v�Z�P���F ���N�G�X�g.�m�F���P�� 
        //���������F ���N�G�X�g.�������� 
        //�����������Z�q�F �i�ȉ��̂Ƃ���j 
        //���N�G�X�g.�������� == �h�t�w�l�h �̏ꍇ�A���N�G�X�g.�t�w�l�p�����������Z�q 
        //���N�G�X�g.�������� == �hW�w�l�h �̏ꍇ�A���N�G�X�g.W�w�l�p�����������Z�q 
        //���������P���F �i�ȉ��̂Ƃ���j 
        //���N�G�X�g.�������� == �h�t�w�l�h �̏ꍇ�A���N�G�X�g.�t�w�l�p���������P�� 
        //���N�G�X�g.�������� == �hW�w�l�h �̏ꍇ�A���N�G�X�g.W�w�l�p���������P�� 
        
        //�����������Z�q
        String l_strOrderCondOperator = null;
        //���������P��
        double l_dblOrderCondPrice = 0.0D;
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_strOrderCondOperator = l_request.stopOrderCondOperator;
            l_dblOrderCondPrice = 
                Double.parseDouble(l_request.stopOrderCondPrice);
        }
        else
        {
            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_strOrderCondOperator = l_request.wlimitOrderCondOperator;
                l_dblOrderCondPrice = 
                    Double.parseDouble(l_request.wlimitOrderCondPrice);
            }
        }
        WEB3FeqOrderUpdateInterceptor l_feqOrderUpdateInterceptor = 
            new WEB3FeqOrderUpdateInterceptor(
                l_feqNewOrderSpec,
                l_feqAmountCalcResult,
                l_dblCheckPrice,
                l_request.orderCondType,
                l_strOrderCondOperator,
                l_dblOrderCondPrice);

        //is���v�����̗p( )
        WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_subAccount.getInstitution();
        boolean l_blnIsDayTradeAdoption = l_institution.isDayTradeAdoption();

        //is���v��s��( )
        boolean l_blnIsDayTradeMarket = l_market.isDayTradeMarket();

        //is���v�����̗p()��true ���@@is���v��s��()��true�̏ꍇ
        if (l_blnIsDayTradeAdoption && l_blnIsDayTradeMarket)
        {
            //validate����]��
            WEB3TPTradingPowerService l_tpTradingPowerService = 
                (WEB3TPTradingPowerService) Services.getService(
                    WEB3TPTradingPowerService.class);
            //�O�����������X�V�C���^�Z�v�^��v�f�Ƃ����z�� 
            WEB3FeqOrderUpdateInterceptor[] l_feqOrderUpdateInterceptors = {l_feqOrderUpdateInterceptor};

            //�O�������V�K�������e��v�f�Ƃ����z�� 
            WEB3FeqNewOrderSpec[] l_feqNewOrderSpecs = {l_feqNewOrderSpec}; 

            WEB3TPTradingPowerResult l_tPTradingPowerResult =
                l_tpTradingPowerService.validateTradingPower(
                    l_subAccount,
                    l_feqOrderUpdateInterceptors,
                    l_feqNewOrderSpecs,
                    OrderTypeEnum.FEQ_SELL,
                    true);
            if (l_tPTradingPowerResult == null)
            {
                log.debug("�\�����Ȃ��V�X�e���G���[���������܂����B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�\�����Ȃ��V�X�e���G���[���������܂����B");
            }
            //�߂�l�̎���]�͌���.����t���O == false �̏ꍇ�A��O���X���[����B
            if (!l_tPTradingPowerResult.isResultFlg())
            {
                log.debug("����]�̓`�F�b�N�G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����]�̓`�F�b�N�G���[�B");
            }
        }

        //1.16 setThreadLocalPersistenceEventInterceptor(
            //arg0 : FeqOrderManagerPersistenceEventInterceptor)
        l_feqOrderManager.setThreadLocalPersistenceEventInterceptor(l_feqOrderUpdateInterceptor);     
        
        //1.17 submitNewOrder
        //�������e��DB�ɓo�^����B 
        //[����] 
        //�⏕�����F �⏕�����I�u�W�F�N�g 
        //�����^�C�v�F �h�O�������h 
        //�������e�F �O�������������e�I�u�W�F�N�g 
        //����ID�F ���N�G�X�g.����ID 
        //�p�X���[�h�F ���N�G�X�g.�Ïؔԍ� 
        //isSkip�����R���F true 
        long l_lngOrderId = 0;
        if (!WEB3StringTypeUtility.isEmpty(l_request.orderActionId))
        {
            l_lngOrderId =  Long.parseLong(l_request.orderActionId);
        } 
        OrderSubmissionResult l_submitNewOrderResult =
            l_feqOrderManager.submitNewOrder(
                l_subAccount,
                ProductTypeEnum.FOREIGN_EQUITY, 
                l_feqNewOrderSpec,
                l_lngOrderId,
                l_request.password,
                true);
            
        if (l_submitNewOrderResult.getProcessingResult().isFailedResult())
        {
            log.debug("Error in submitNewOrder" +
                l_submitNewOrderResult.getProcessingResult().getErrorInfo());
            throw new WEB3SystemLayerException(
                l_submitNewOrderResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        //1.18 �]�͍Čv�Z(�⏕���� : �⏕����)
        //�]�͂̍X�V���s���B 
        //[����] 
        //�⏕�����F �⏕�����I�u�W�F�N�g
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        l_tpTradingPowerService.reCalcTradingPower(l_subAccount);

        //1.19 getOrder(����ID : long)
        FeqOrderRow l_orderRow = null;
        try
        {
            Order l_order = l_feqOrderManager.getOrder(l_lngOrderId);
            l_orderRow = (FeqOrderRow) l_order.getDataSourceObject();
        }
        catch (NotFoundException l_ex)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.20  createResponse( )
        WEB3FeqSellCompleteResponse l_response = 
            (WEB3FeqSellCompleteResponse)l_request.createResponse();
        
        //1.21 (*) �v���p�e�B�Z�b�g
        //(*) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        //�X�V���ԁF ����.�X�V����
        l_response.lastUpdatedTimestamp = l_orderRow.getLastUpdatedTimestamp();
        //����ID�F���N�G�X�g.����ID
        l_response.orderActionId = l_request.orderActionId;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
