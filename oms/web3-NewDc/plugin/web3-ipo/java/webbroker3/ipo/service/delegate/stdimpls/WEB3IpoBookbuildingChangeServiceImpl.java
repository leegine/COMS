head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.46.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoBookbuildingChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  IPO�u�b�N�r���f�B���O�����T�[�r�X�����N���X(WEB3IPOBookbuildingChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/17 ���]��(���u) �V�K�쐬
Revesion History : 2004/12/29 ���(SRA) �c�Ή�>>>047,044
*/

package webbroker3.ipo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ipo.WEB3IpoBookbuildingChangeOrderSpec;
import webbroker3.ipo.WEB3IpoBookbuildingPriceCalcResult;
import webbroker3.ipo.WEB3IpoClientRequestService;
import webbroker3.ipo.WEB3IpoOrderImpl;
import webbroker3.ipo.WEB3IpoOrderManagerImpl;
import webbroker3.ipo.WEB3IpoOrderValidator;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeCompleteRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeCompleteResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeConfirmRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeConfirmResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeInputRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeInputResponse;
import webbroker3.ipo.service.delegate.WEB3IpoBookbuildingChangeService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

/**
 * IPO�u�b�N�r���f�B���O�����T�[�r�X�����N���X
 * @@author ���]��(���u)
 * @@version 1.0
 */
public class WEB3IpoBookbuildingChangeServiceImpl 
    extends WEB3IpoClientRequestService implements WEB3IpoBookbuildingChangeService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IpoBookbuildingChangeServiceImpl.class);
    
    /**
     * @@roseuid 4112F18F038E
     */
    public WEB3IpoBookbuildingChangeServiceImpl() 
    {
     
    }
    
    /**
     * IPO�u�b�N�r���f�B���O�������������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���AIPO�u�b�N�r���f�B���O�������̓��N�G�X�g�̏ꍇ<BR>
     * �@@�|get���͉��()���R�[������B<BR>
     * �� �����̃��N�G�X�g�f�[�^���AIPO�u�b�N�r���f�B���O�����m�F���N�G�X�g�̏ꍇ<BR>
     * �@@�|validate�u�b�N�r���f�B���O����()���R�[������B<BR>
     * �� �����̃��N�G�X�g�f�[�^���AIPO�u�b�N�r���f�B���O�����������N�G�X�g�̏ꍇ<BR>
     * �@@�|submit�u�b�N�r���f�B���O����()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40D96AD7029F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );
        
        WEB3GenResponse l_response = null;
        if(l_request instanceof WEB3IPOBookBuildingChangeInputRequest)
        {
            l_response = getInputScreen((WEB3IPOBookBuildingChangeInputRequest)l_request);
        }
        else if(l_request instanceof WEB3IPOBookBuildingChangeConfirmRequest)
        {
            l_response = validateBookbuildingChange((WEB3IPOBookBuildingChangeConfirmRequest)l_request);
        }
        else if(l_request instanceof WEB3IPOBookBuildingChangeCompleteRequest)
        {
            l_response = submitBookbuildingChange((WEB3IPOBookBuildingChangeCompleteRequest)l_request);
        }
        else
        {
            String l_strErrorMessage = 
                "�p�����[�^�̗ތ^���s���A�Y������WEB3IPOBookBuildingEnterRequest," + 
                "WEB3IPOProductInfoRequest�ތ^�B";
            log.error(l_strErrorMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strErrorMessage);            
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;        
     
    }
    
    /**
     * (get���͉��)<BR>
     * IPO�u�b�N�r���f�B���O�������͉�ʕ\���f�[�^�쐬�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�ޯ�����ިݸޒ����jget���͉�ʁv�Q�ƁB
     * @@param l_request - IPO�u�b�N�r���f�B���O�������̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingChangeInputResponse
     * @@roseuid 40D969A6006D
     */
    protected WEB3IPOBookBuildingChangeInputResponse getInputScreen(WEB3IPOBookBuildingChangeInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3IpoBookbuildingChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.validate������t�\
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
                     
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3IpoOrderManagerImpl l_orderManagerImpl = 
                             (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
       
        //1.2.getOrdervalidater
        WEB3IpoOrderValidator l_orderValidator = (WEB3IpoOrderValidator)l_orderManagerImpl.getOrderValidator();
        
        //1.3.�⏕�������擾���� 
        SubAccount l_subAccount = this.getSubAccount();
        
        //1.4.validate����\�ڋq
        OrderValidationResult l_result = l_orderValidator.validateSubAccountForTrading(l_subAccount);
        if(!l_result.getProcessingResult().equals(ProcessingResult.SUCCESS_RESULT))
        {
            String l_strErrorMessage = "invalid����\�ڋq.";
            log.error(l_strErrorMessage);
			//2004/11/10 ��Q�Ǘ��[No.U00394 �o�̓G���[�̏C�� ���@@SRA START    
            throw new WEB3BaseException(
			    l_result.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);
//			throw new WEB3BaseException(
//						   WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
//						   this.getClass().getName() + STR_METHOD_NAME,
//						   l_strErrorMessage); 
			//2004/11/10 ��Q�Ǘ��[No.U00394 �o�̓G���[�̏C�� ���@@SRA END 
        }
        
        //1.5.IPO�\��
        long l_lngOrderId = Long.parseLong(l_request.id);
        WEB3IpoOrderImpl l_ipoOrder;
        try 
        {
            l_ipoOrder =
                (WEB3IpoOrderImpl) l_finApp
                    .getTradingModule(ProductTypeEnum.IPO)
                    .getOrderManager()
                    .getOrderUnit(l_lngOrderId);
 
            //1.6.IPO�������擾����
            WEB3IpoProductImpl l_product = (WEB3IpoProductImpl) l_ipoOrder.getProduct();
        
            //1.7.IPO�����`�F�b�N::validate�u�b�N�r���f�B���O����
            l_orderValidator.validateBookbuildingProduct(l_product); 
            
            //1.8.get���̑����i���t�\�z()
            TradingSystem l_trdSys = l_finApp.getTradingSystem();         
            WEB3TPTradingPowerService l_tpTPS  
                = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            double l_trdPow = l_tpTPS.getOtherTradingPower((WEB3GentradeSubAccount)this.getSubAccount(),l_trdSys.getBizDate() );
        
            //1.9.IPO�u�b�N�r���f�B���O�������̓��X�|���X::IPO�u�b�N�r���f�B���O�������̓��X�|���X
            WEB3IPOBookBuildingChangeInputResponse l_bookBulidingChangeInputResponse 
                = (WEB3IPOBookBuildingChangeInputResponse) l_request.createResponse();
        
            IpoProductRow l_ipoProductRow = (IpoProductRow)l_product.getDataSourceObject();
            IpoOrderRow l_IpoOrderRow = (IpoOrderRow)l_ipoOrder.getDataSourceObject();
            
            //�����R�[�h
            l_bookBulidingChangeInputResponse.productCode = l_ipoProductRow.getProductCode() ;
            
            //������
            l_bookBulidingChangeInputResponse.productName = l_product.getStandardName();
            
            //���J�s��R�[�h
            l_bookBulidingChangeInputResponse.publicOfferingMarketCode = l_ipoProductRow.getPublicMarket();
            
            //�������敪
            l_bookBulidingChangeInputResponse.temporaryConditionDiv = l_ipoProductRow.getProvisionalValueDiv();
            
            //�����������l
            if(l_ipoProductRow.getProvisionalMinValueIsNull())
            {
                l_bookBulidingChangeInputResponse.temporaryConditionLower = null;
            }
            else
            {
                l_bookBulidingChangeInputResponse.temporaryConditionLower = WEB3StringTypeUtility.formatNumber(
                    l_ipoProductRow.getProvisionalMinValue());
            }
            
            //����������l
            if(l_ipoProductRow.getProvisionalMaxValueIsNull())
            {
                l_bookBulidingChangeInputResponse.temporaryConditionUpper = null;
            }
            else
            {
                l_bookBulidingChangeInputResponse.temporaryConditionUpper = WEB3StringTypeUtility.formatNumber(
                    l_ipoProductRow.getProvisionalMaxValue());
            }
            
            //����
            if(l_ipoProductRow.getTickValueIsNull())
            {
                l_bookBulidingChangeInputResponse.tickValue = null;
            }
            else
            {
                l_bookBulidingChangeInputResponse.tickValue = WEB3StringTypeUtility.formatNumber(
                    l_ipoProductRow.getTickValue());
            }
            
            //�\���p�P�ʋ敪
            l_bookBulidingChangeInputResponse.displayUnitDiv = l_ipoProductRow.getIpoUnitDiv();
            
            //�w���\���P��
            if(!l_ipoProductRow.getLotSizeIsNull())
            {
                l_bookBulidingChangeInputResponse.offerUnit = WEB3StringTypeUtility.formatNumber(l_ipoProductRow.getLotSize());
            }
            
            //���s�\
            l_bookBulidingChangeInputResponse.marketOrderFlag = l_ipoProductRow.getEnableMarketOrder();
            
            //�o���]��
            l_bookBulidingChangeInputResponse.paymentPower = WEB3StringTypeUtility.formatNumber(l_trdPow);
            
            //�\������
            l_bookBulidingChangeInputResponse.demandQuantity = 
                WEB3StringTypeUtility.formatNumber(l_ipoOrder.getQuantity());
            
            //�\�����i�敪
            if(l_ipoOrder.getLimitPrice() == 0)
            {
                l_bookBulidingChangeInputResponse.demandPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            else
            {
                l_bookBulidingChangeInputResponse.demandPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            }
            
            //�\�����i
            l_bookBulidingChangeInputResponse.demandPrice = 
                WEB3StringTypeUtility.formatNumber(l_ipoOrder.getLimitPrice());
            
            //�\�������z
            if(l_IpoOrderRow.getBookbuildingPriceIsNull())
            {
                l_bookBulidingChangeInputResponse.demandEquivalentPrice = null;
            }
            else
            {
                l_bookBulidingChangeInputResponse.demandEquivalentPrice = 
                    WEB3StringTypeUtility.formatNumber(l_IpoOrderRow.getBookbuildingPrice());
            }
        
                   
            log.exiting(STR_METHOD_NAME);
            return l_bookBulidingChangeInputResponse; 

        } 
        catch (NotFoundException l_ex) 
        {
            log.error("�f�[�^�s�����G���[�B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (validate�u�b�N�r���f�B���O����)<BR>
     * IPO�u�b�N�r���f�B���O�����m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�ޯ�����ިݸޒ����jvalidate�u�b�N�r���f�B���O�����v�Q�ƁB
     * @@param l_request - IPO�u�b�N�r���f�B���O�����m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingChangeConfirmResponse
     * @@roseuid 40D969A6008C
     */
    protected WEB3IPOBookBuildingChangeConfirmResponse validateBookbuildingChange(WEB3IPOBookBuildingChangeConfirmRequest l_request) 
        throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = "validateBookbuildingChange(WEB3IpoBookbuildingChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
              
        l_request.validate();
        
        //validate������t�\
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        Trader l_trader = this.getTrader();
        
        //�u�b�N�r���f�B���O�������e::�u�b�N�r���f�B���O�������e
        
        if (!WEB3StringTypeUtility.isNumber(l_request.id))
        {
            String l_strErrorMessage = 
                "Ipo order id�ތ^���s���A�Y������long�ތ^�B";
            log.error(l_strErrorMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strErrorMessage);      
        }
        long l_lngIpoOrderId = Long.parseLong(l_request.id);

        double l_dblOrderQuantity =0;
        if (WEB3StringTypeUtility.isNumber(l_request.demandQuantity))
        {
            l_dblOrderQuantity =Double.parseDouble(l_request.demandQuantity);
        }
        
        double l_orderPrice =0;
        if (WEB3StringTypeUtility.isNumber(l_request.demandPrice))
        {
            l_orderPrice =Double.parseDouble(l_request.demandPrice);
        }
        
        WEB3IpoBookbuildingChangeOrderSpec l_bookBuildingChangeSpe = 
            new WEB3IpoBookbuildingChangeOrderSpec(l_trader, l_lngIpoOrderId, l_dblOrderQuantity, l_orderPrice, 0);

        //�⏕�������擾���� 
        SubAccount l_subAccount = this.getSubAccount();
        
        //IPO�\���}�l�[�W��::validate�u�b�N�r���f�B���O����
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3IpoOrderManagerImpl l_orderManagerImpl = 
                             (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
        
        OrderValidationResult l_result = l_orderManagerImpl.validateChangeOrder(l_subAccount, l_bookBuildingChangeSpe);
        if(!l_result.getProcessingResult().equals(ProcessingResult.SUCCESS_RESULT))
        {
            String l_strErrorMessage = "invalid�u�b�N�r���f�B���O����.";
            log.error(l_strErrorMessage);
			//2004/11/10 ��Q�Ǘ��[No.U00394 �o�̓G���[�̏C�� ���@@SRA START    
			throw new WEB3BaseException(
				l_result.getProcessingResult().getErrorInfo(),
				this.getClass().getName() + STR_METHOD_NAME,
				l_strErrorMessage);
//			throw new WEB3BaseException(
//						   WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
//						   this.getClass().getName() + STR_METHOD_NAME,
//						   l_strErrorMessage); 
			//2004/11/10 ��Q�Ǘ��[No.U00394 �o�̓G���[�̏C�� ���@@SRA END      
        }

        
        //IPO�\��
        WEB3IpoOrderImpl l_ipoOrder;
        try 
        {
            l_ipoOrder =
                (WEB3IpoOrderImpl) l_finApp
                    .getTradingModule(ProductTypeEnum.IPO)
                    .getOrderManager()
                    .getOrderUnit(l_lngIpoOrderId);

            //IPO�������擾����
            WEB3IpoProductImpl l_product = (WEB3IpoProductImpl) l_ipoOrder.getProduct();        
            //MainAccount���擾����
            WEB3GentradeMainAccount l_mainAccount  = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();        
            //l_subAccount.getMainAccount();
        
            double l_dblLimitPrice = l_bookBuildingChangeSpe.getLimitPrice();
            double l_dblQuantity = l_bookBuildingChangeSpe.getQuantity();
        
            WEB3IpoBookbuildingPriceCalcResult l_priceCalcResult = 
                l_orderManagerImpl.calcBookbuildingPrice(l_mainAccount, l_product, l_dblLimitPrice, l_dblQuantity, 0);
        

            //�\�������z�v�Z����::get�\�������z            
            double l_dblBoolbuildingPrice = l_priceCalcResult.getBookbuildingPrice();
            
            //�\�������z�v�Z����::get��l�i�����j
            double l_dblBasePrice = l_priceCalcResult.getCurrentPrice();
        
            //IPO�u�b�N�r���f�B���O�����m�F���N�G�X�g::createResponse
            WEB3IPOBookBuildingChangeConfirmResponse l_bookBulidingChangeConfirmResponse 
                = (WEB3IPOBookBuildingChangeConfirmResponse) l_request.createResponse();
                
            //�\�������z
            l_bookBulidingChangeConfirmResponse.demandEquivalentPrice = WEB3StringTypeUtility.formatNumber(l_dblBoolbuildingPrice);
            
            //�m�F��������
            l_bookBulidingChangeConfirmResponse.checkDate = l_datOrderBizDate;
            
            //�m�F����l
            if(!Double.isNaN(l_dblBasePrice))
            {
                l_bookBulidingChangeConfirmResponse.checkValue = WEB3StringTypeUtility.formatNumber(l_dblBasePrice);
            }
            else
            {
                l_bookBulidingChangeConfirmResponse.checkValue = "0";
            }
        
            log.exiting(STR_METHOD_NAME);
            return l_bookBulidingChangeConfirmResponse;
        } 
        catch (NotFoundException l_ex) 
        {
            log.error("�f�[�^�s�����G���[�B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }


    }
    
    /**
     * (submit�u�b�N�r���f�B���O����)<BR>
     * IPO�u�b�N�r���f�B���O���������������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�ޯ�����ިݸޒ����jsubmit�u�b�N�r���f�B���O�����v�Q�ƁB
     * @@param l_request - IPO�u�b�N�r���f�B���O�����������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingChangeCompleteResponse
     * @@roseuid 40D969A6009B
     */
    protected WEB3IPOBookBuildingChangeCompleteResponse submitBookbuildingChange(WEB3IPOBookBuildingChangeCompleteRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "submitBookbuildingChange(WEB3IpoBookbuildingChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        

        l_request.validate();
        //validate������t�\
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        
        Trader l_trader = this.getTrader();

        //�u�b�N�r���f�B���O�������e::�u�b�N�r���f�B���O�������e
        if (!WEB3StringTypeUtility.isNumber(l_request.id))
        {
            String l_strErrorMessage = 
                "Ipo order id�ތ^���s���A�Y������long�ތ^�B";
            log.error(l_strErrorMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strErrorMessage);      
        }
        long l_lngIpoOrderId = Long.parseLong(l_request.id);
        
        double l_dblOrderQuantity =0;
        if (WEB3StringTypeUtility.isNumber(l_request.demandQuantity))
        {
            l_dblOrderQuantity =Double.parseDouble(l_request.demandQuantity);
        }
        
        double l_dblOrderPrice =0;
        if (WEB3StringTypeUtility.isNumber(l_request.demandPrice))
        {
            l_dblOrderPrice =Double.parseDouble(l_request.demandPrice);
        }
        
        double l_dblCheckValue =0;
        if (WEB3StringTypeUtility.isNumber(l_request.checkValue))
        {
            l_dblCheckValue =Double.parseDouble(l_request.checkValue);
        }
        
        WEB3IpoBookbuildingChangeOrderSpec l_bookBuildingChangeSpe = 
            new WEB3IpoBookbuildingChangeOrderSpec(l_trader, l_lngIpoOrderId, l_dblOrderQuantity, l_dblOrderPrice, l_dblCheckValue);     
               
        //�⏕�������擾���� 
        SubAccount l_subAccount = this.getSubAccount();
        
        //IPO�\���}�l�[�W��::submit�u�b�N�r���f�B���O����
        //l_request.password
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3IpoOrderManagerImpl l_orderManagerImpl = 
                             (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
        
        OrderSubmissionResult l_result = l_orderManagerImpl.submitChangeOrder(l_subAccount, l_bookBuildingChangeSpe, l_request.password, false);
        if(!l_result.getProcessingResult().equals(ProcessingResult.SUCCESS_RESULT))
        {
            String l_strErrorMessage = "submit�u�b�N�r���f�B���O����error!";
            log.error(l_strErrorMessage);
			//2004/11/10 ��Q�Ǘ��[No.U00394 �o�̓G���[�̏C�� ���@@SRA START    
			throw new WEB3BaseException(
				l_result.getProcessingResult().getErrorInfo(),
				this.getClass().getName() + STR_METHOD_NAME,
				l_strErrorMessage);
//			throw new WEB3BaseException(
//						   WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
//						   this.getClass().getName() + STR_METHOD_NAME,
//						   l_strErrorMessage); 
			//2004/11/10 ��Q�Ǘ��[No.U00394 �o�̓G���[�̏C�� ���@@SRA END  
        }
    
        //IPO�\��
        WEB3IpoOrderImpl l_ipoOrder;
        try 
        {
            l_ipoOrder =
                (WEB3IpoOrderImpl) l_finApp
                    .getTradingModule(ProductTypeEnum.IPO)
                    .getOrderManager()
                    .getOrderUnit(l_lngIpoOrderId);

            long l_lastOrderAction = l_ipoOrder.getLastOrderActionId();
        
            WEB3IPOBookBuildingChangeCompleteResponse l_bookBuildingChangeCompleteResponse 
                = (WEB3IPOBookBuildingChangeCompleteResponse) l_request.createResponse();
        
            Timestamp l_tsCurrentTime = l_finApp.getTradingSystem().getSystemTimestamp();
            
            //�X�V����
            l_bookBuildingChangeCompleteResponse.lastUpdatedTimestamp = l_tsCurrentTime;
        
            //���ʔԍ�
            l_bookBuildingChangeCompleteResponse.orderActionId = l_request.id;

            log.exiting(STR_METHOD_NAME);
            return l_bookBuildingChangeCompleteResponse;

        } 
        catch (NotFoundException l_ex) 
        {
            log.error("�f�[�^�s�����G���[�B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        

    }
}
@
