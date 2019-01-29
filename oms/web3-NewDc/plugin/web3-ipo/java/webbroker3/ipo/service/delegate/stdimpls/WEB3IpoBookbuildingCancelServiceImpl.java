head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.46.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoBookbuildingCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�u�b�N�r���f�B���O����T�[�r�X�����N���X(WEB3IpoBookbuildingCancelServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 �A�C��(���u) �V�K�쐬
Revesion History : 2004/12/29 ���(SRA) �c�Ή�>>>047
*/

package webbroker3.ipo.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ipo.WEB3IpoBookbuildingCancelOrderSpec;
import webbroker3.ipo.WEB3IpoClientRequestService;
import webbroker3.ipo.WEB3IpoOrderImpl;
import webbroker3.ipo.WEB3IpoOrderManagerImpl;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.message.WEB3IPOBookBuildingCancelCompleteRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingCancelCompleteResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingCancelConfirmRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingCancelConfirmResponse;
import webbroker3.ipo.service.delegate.WEB3IpoBookbuildingCancelService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * IPO�u�b�N�r���f�B���O����T�[�r�X�����N���X
 *                                                                
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3IpoBookbuildingCancelServiceImpl 
    extends WEB3IpoClientRequestService implements WEB3IpoBookbuildingCancelService 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IpoBookbuildingCancelServiceImpl.class);           
     
    /**
     * @@roseuid 4112F18F0276
     */
    public WEB3IpoBookbuildingCancelServiceImpl() 
    {
     
    }
    
    /**
     * IPO�u�b�N�r���f�B���O������������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���AIPO�u�b�N�r���f�B���O����m�F���N�G�X�g�̏ꍇ<BR>
     * �@@�|validate�u�b�N�r���f�B���O���()���R�[������B<BR>
     * �� �����̃��N�G�X�g�f�[�^���AIPO�u�b�N�r���f�B���O����������N�G�X�g�̏ꍇ<BR>
     * �@@�|submit�u�b�N�r���f�B���O���()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40D93482014F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3GenResponse l_response = null;
        if(l_request instanceof WEB3IPOBookBuildingCancelConfirmRequest)
        {
            //IPO�u�b�N�r���f�B���O����m�F���N�G�X�g�̏ꍇ
            l_response = validateBookbuildingCancel(
                (WEB3IPOBookBuildingCancelConfirmRequest)l_request);
        }
        else if(l_request instanceof WEB3IPOBookBuildingCancelCompleteRequest)
        {
            //IPO�u�b�N�r���f�B���O����������N�G�X�g�̏ꍇ
            l_response = submitBookbuildingCancel(
                (WEB3IPOBookBuildingCancelCompleteRequest)l_request);
        }
        else
        {
            String l_strErrorMessage = 
                "�p�����[�^�̗ތ^���s���A�Y������WEB3IPOBookBuildingCancelConfirmRequest," + 
                "WEB3IPOBookBuildingCancelCompleteRequest�ތ^�B";
            log.error(l_strErrorMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);            
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�u�b�N�r���f�B���O���)<BR>
     * IPO�u�b�N�r���f�B���O����m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�ޯ�����ިݸގ���jvalidate�u�b�N�r���f�B���O����v�Q�ƁB<BR>
     * @@param l_request - IPO�u�b�N�r���f�B���O����m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingCancelConfirmResponse
     * @@roseuid 40D93482015F
     */
    protected WEB3IPOBookBuildingCancelConfirmResponse validateBookbuildingCancel(
        WEB3IPOBookBuildingCancelConfirmRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateBookbuildingCancel(WEB3IpoBookbuildingCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME );
        
        WEB3IPOBookBuildingCancelConfirmResponse l_response= null;
        try
        {
            //1.1validate������t�\
            WEB3GentradeTradingTimeManagement.validateOrderAccept();//WEB3BaseException
            
            //1.2get������
            Date l_bizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();//WEB3SystemLayerException
        
            //1.3get�㗝���͎�
            Trader l_trader = this.getTrader();//WEB3SystemLayerException
        
            //1.4�u�b�N�r���f�B���O������e
            long l_lngIpoOrderId = Long.parseLong(l_request.id);
            WEB3IpoBookbuildingCancelOrderSpec l_cancelOrderSpec = 
                new WEB3IpoBookbuildingCancelOrderSpec(l_trader, l_lngIpoOrderId);
                        
            //1.5get�⏕����
            SubAccount l_subAccount = this.getSubAccount();//WEB3BaseException

            //1.6validate�u�b�N�r���f�B���O���
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IPO);
            WEB3IpoOrderManagerImpl l_ipoOrderManager = 
                (WEB3IpoOrderManagerImpl)l_tradingModule.getOrderManager();
                 
            OrderValidationResult l_result = l_ipoOrderManager.validateCancelOrder(l_subAccount,l_cancelOrderSpec);
            if(!l_result.getProcessingResult().equals(ProcessingResult.SUCCESS_RESULT))
            {
                String l_strErrorMessage = "invalid�u�b�N�r���f�B���O���.";
                log.error(l_strErrorMessage);
                throw new WEB3BaseException(
                    // 2004/11/15 U00413 �r�W�l�X�G���[�����X�|���X�ɃZ�b�g����悤�ɏC�� ����@@SRA START 
                    //WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                    l_result.getProcessingResult().getErrorInfo(),
                    // 2004/11/15 U00413 �r�W�l�X�G���[�����X�|���X�ɃZ�b�g����悤�ɏC�� ����@@SRA START
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strErrorMessage);  
            }

            //1.7getIPO�\��
            WEB3IpoOrderImpl l_ipoOrder = (WEB3IpoOrderImpl)l_ipoOrderManager.getOrderUnit(l_lngIpoOrderId);//NotFoundException
            
            //1.8getIPO����
            WEB3IpoProductImpl l_product = (WEB3IpoProductImpl)l_ipoOrder.getProduct();
            
            //1.9createResponse
            l_response = (WEB3IPOBookBuildingCancelConfirmResponse)l_request.createResponse();
            
            //1.10�v���p�e�B�Z�b�g
            
            IpoProductRow l_productRow = (IpoProductRow)l_product.getDataSourceObject();
            //�����R�[�h
            l_response.productCode = l_productRow.getProductCode();
            
            //������
            l_response.productName = l_product.getStandardName();
            
            //���J�s��R�[�h
            l_response.publicOfferingMarketCode = l_product.getPublicMarket();
            
            //�w���\���P��
            l_response.offerUnit = WEB3StringTypeUtility.formatNumber(
                l_product.getLotSize());
                
            //�������敪
            l_response.temporaryConditionDiv = l_productRow.getProvisionalValueDiv();
            
            //����������l
            l_response.temporaryConditionUpper = WEB3StringTypeUtility.formatNumber(
                l_productRow.getProvisionalMaxValue());
            //�����������l
            l_response.temporaryConditionLower = WEB3StringTypeUtility.formatNumber(
                l_productRow.getProvisionalMinValue());
            
            //����
            l_response.tickValue = WEB3StringTypeUtility.formatNumber(
                l_productRow.getTickValue());
                
            //�\���p�P�ʋ敪
            l_response.displayUnitDiv = l_productRow.getIpoUnitDiv();
            
            //���s�\
            l_response.marketOrderFlag = l_productRow.getEnableMarketOrder();
            
            //�\������
            double l_dblQuantity = l_ipoOrder.getQuantity();
            if(Double.isNaN(l_dblQuantity))
            {
                l_dblQuantity = 0D; 
            }
            l_response.demandQuantity = WEB3StringTypeUtility.formatNumber(
                l_dblQuantity);
            
            //�\�����i�敪
            if(l_ipoOrder.getLimitPrice() == 0)
            {
                l_response.demandPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            else
            {
                l_response.demandPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            }
            
            //�\�����i
            l_response.demandPrice = WEB3StringTypeUtility.formatNumber(
                l_ipoOrder.getLimitPrice());
                
            //�\�������z
            IpoOrderRow l_orderRow = (IpoOrderRow)l_ipoOrder.getDataSourceObject();
            if(l_orderRow.getBookbuildingPriceIsNull())
            {
                l_response.demandEquivalentPrice = null;
            }
            else
            {
                l_response.demandEquivalentPrice = WEB3StringTypeUtility.formatNumber(l_orderRow.getBookbuildingPrice());
            }
            
            //�m�F��������
            l_response.checkDate = l_bizDate;
        }
        catch (NotFoundException l_e)
        {
            log.error(STR_METHOD_NAME, l_e);
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e);
        }
        
        log.exiting(STR_METHOD_NAME);        
        return l_response;
    }
    
    /**
     * (submit�u�b�N�r���f�B���O���)<BR>
     * IPO�u�b�N�r���f�B���O��������������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�ޯ�����ިݸގ���jsubmit�u�b�N�r���f�B���O����v�Q�ƁB
     * @@param l_request - IPO�u�b�N�r���f�B���O����������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingCancelCompleteResponse
     * @@roseuid 40D93482017E
     */
    protected WEB3IPOBookBuildingCancelCompleteResponse submitBookbuildingCancel(
        WEB3IPOBookBuildingCancelCompleteRequest l_request)
        throws WEB3BaseException     
    {
        final String STR_METHOD_NAME = " submitBookbuildingCancel(WEB3IpoBookbuildingCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME );
           
        WEB3IPOBookBuildingCancelCompleteResponse l_response= null;
        try
        {
            //1.1validate������t�\
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            
            //1.2get������
            Date l_bizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
           
            //1.3get�㗝���͎�
            Trader l_trader = this.getTrader();
           
            //1.4�u�b�N�r���f�B���O������e
            long l_lngIpoOrderId = Long.parseLong(l_request.id);
            WEB3IpoBookbuildingCancelOrderSpec l_cancelOrderSpec = 
                new WEB3IpoBookbuildingCancelOrderSpec(l_trader, l_lngIpoOrderId);
                           
            //1.5get�⏕����
            SubAccount l_subAccount = this.getSubAccount();
      
            //1.6submit�u�b�N�r���f�B���O���
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IPO);
            WEB3IpoOrderManagerImpl l_ipoOrderManager = 
                (WEB3IpoOrderManagerImpl)l_tradingModule.getOrderManager();
                
            OrderSubmissionResult l_result = l_ipoOrderManager.submitCancelOrder(
                l_subAccount, 
                l_cancelOrderSpec, 
                l_request.password,
                false);
                
            if(!l_result.getProcessingResult().equals(ProcessingResult.SUCCESS_RESULT))
            {
                String l_strErrorMessage = "submit�u�b�N�r���f�B���O���error!";
                log.error(l_strErrorMessage);
                throw new WEB3BaseException(
                    // 2004/11/15 U00413 �r�W�l�X�G���[�����X�|���X�ɃZ�b�g����悤�ɏC�� ����@@SRA START
                    //WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    l_result.getProcessingResult().getErrorInfo(),
                    // 2004/11/15 U00413 �r�W�l�X�G���[�����X�|���X�ɃZ�b�g����悤�ɏC�� ����@@SRA START 
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strErrorMessage);  
            }
      
            //1.7getIPO�\��
            WEB3IpoOrderImpl l_ipoOrder = (WEB3IpoOrderImpl)l_ipoOrderManager.getOrderUnit(l_lngIpoOrderId);
               
            //1.8get�ŐV�����h�c
            long l_lngNewActionID = l_ipoOrder.getLastOrderActionId();            
               
            //1.9createResponse
            l_response = new WEB3IPOBookBuildingCancelCompleteResponse();
               
            //1.10�v���p�e�B�Z�b�g
            
            //�X�V����
            TradingSystem l_tradingSys = l_finApp.getTradingSystem();
            l_response.lastUpdatedTimestamp = l_tradingSys.getSystemTimestamp();;
            
            //���ʔԍ�
            l_response.orderActionId = l_request.id;
        }
        catch (NotFoundException l_e)
        {
            log.error(STR_METHOD_NAME, l_e);
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e);
        }
            
        log.exiting(STR_METHOD_NAME);        
        return l_response;
    }
}
@
