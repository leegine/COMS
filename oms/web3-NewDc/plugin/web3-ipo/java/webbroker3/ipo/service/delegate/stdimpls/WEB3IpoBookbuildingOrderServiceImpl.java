head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.46.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoBookbuildingOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�u�b�N�r���f�B���O�\���T�[�r�X�����N���X(WEB3IpoBookbuildingOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 �A�C��(���u) �V�K�쐬
Revesion History : 2004/12/27 ���(SRA) �c�Ή�>>>036
Revesion History : 2004/12/29 ���(SRA) �c�Ή�>>>047,044
Revesion History : 2005/01/11 ���(SRA) �c�Ή�>>>036(�d�q����Q�̃v���p�e�B�폜)
Revesion History : 2006/01/26 �s�p�i���u�j�d�l�ύX�E���f��118
Revesion History : 2006/06/27 �(���u) �d�l�ύXNo.148�Ή�
Revesion History : 2006/11/22 �����q (���u) �d�l�ύXNo.168�Ή�
*/

package webbroker3.ipo.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3DocReadingDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.ipo.WEB3IpoBookbuildingNewOrderSpec;
import webbroker3.ipo.WEB3IpoBookbuildingPriceCalcResult;
import webbroker3.ipo.WEB3IpoClientRequestService;
import webbroker3.ipo.WEB3IpoOrderImpl;
import webbroker3.ipo.WEB3IpoOrderManagerImpl;
import webbroker3.ipo.WEB3IpoOrderValidator;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.WEB3IpoProductManagerImpl;
import webbroker3.ipo.data.IpoProductParams;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandCompleteRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandCompleteResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandConfirmRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandConfirmResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandInputRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandInputResponse;
import webbroker3.ipo.service.delegate.WEB3IpoBookbuildingOrderService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * IPO�u�b�N�r���f�B���O�\���T�[�r�X�����N���X
 * <br> 
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3IpoBookbuildingOrderServiceImpl 
    extends WEB3IpoClientRequestService 
    implements WEB3IpoBookbuildingOrderService 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IpoBookbuildingOrderServiceImpl.class);
            
    /**
     * @@roseuid 4112F18E03E7
     */
    public WEB3IpoBookbuildingOrderServiceImpl() 
    {
     
    }
    
    /**
     * IPO�u�b�N�r���f�B���O�\�����������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���AIPO�u�b�N�r���f�B���O�\�����̓��N�G�X�g�̏ꍇ<BR>
     * �@@�|get���͉��()���R�[������B<BR>
     * �� �����̃��N�G�X�g�f�[�^���AIPO�u�b�N�r���f�B���O�\���m�F���N�G�X�g�̏ꍇ<BR>
     * �@@�|validate�u�b�N�r���f�B���O�\��()���R�[������B<BR>
     * �� �����̃��N�G�X�g�f�[�^���AIPO�u�b�N�r���f�B���O�\���������N�G�X�g�̏ꍇ<BR>
     * �@@�|submit�u�b�N�r���f�B���O�\��()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40D284740333
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3GenResponse l_response = null;
        if(l_request instanceof WEB3IPOBookBuildingDemandInputRequest)
        {
            //IPO�u�b�N�r���f�B���O�\�����̓��N�G�X�g�̏ꍇ
            l_response = getInputScreen(
                (WEB3IPOBookBuildingDemandInputRequest)l_request);
        }
        else if(l_request instanceof WEB3IPOBookBuildingDemandConfirmRequest)
        {
            //IPO�u�b�N�r���f�B���O�\���m�F���N�G�X�g�̏ꍇ
            l_response = validateBookbuildingOrder(
                (WEB3IPOBookBuildingDemandConfirmRequest)l_request);
        }
        else if(l_request instanceof WEB3IPOBookBuildingDemandCompleteRequest)
        {
            //IPO�u�b�N�r���f�B���O�\���������N�G�X�g�̏ꍇ
            l_response = submitBookbuildingOrder(
                (WEB3IPOBookBuildingDemandCompleteRequest)l_request);
        }
        else
        {
            String l_strErrorMessage = 
                "�p�����[�^�̗ތ^���s���A�Y������WEB3IPOBookBuildingOrderInputRequestt," + 
                "WEB3IPOBookBuildingOrderConfirmRequest,WEB3IPOBookBuildingOrderCompleteRequest�ތ^�B";
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
     * (get���͉��)<BR>
     * IPO�u�b�N�r���f�B���O�\�����͉�ʕ\���f�[�^�쐬�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�ޯ�����ިݸސ\���jget���͉�ʁv�Q�ƁB<BR>
     * @@param l_request - IPO�u�b�N�r���f�B���O�\�����̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingOrderInputResponse
     * @@roseuid 40D2843A020A
     */
    protected WEB3IPOBookBuildingDemandInputResponse getInputScreen(
        WEB3IPOBookBuildingDemandInputRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3IpoBookbuildingOrderInputRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3IPOBookBuildingDemandInputResponse l_response = null;
        try
        {
            //1.1 validate������t�\
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            
            //1.2 getOrderValidator( )
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IPO);
            WEB3IpoOrderManagerImpl l_ipoOrderManager = 
                (WEB3IpoOrderManagerImpl)l_tradingModule.getOrderManager();
            WEB3IpoOrderValidator l_orderValidator = 
                (WEB3IpoOrderValidator)l_ipoOrderManager.getOrderValidator();
    
            //1.3 validate����\�ڋq
            OrderValidationResult l_orderValidationResult = 
                l_orderValidator.validateSubAccountForTrading(this.getSubAccount());
            if(!OrderValidationResult.VALIDATION_OK_RESULT.equals(l_orderValidationResult))
            {
                String l_strErrorMessage = "get���͉��error!";
                log.error(l_strErrorMessage);
                // 2004/11/08 ��Q�Ǘ��[No.U00377 �o�̓G���[�̏C�� ����@@SRA START
                throw new WEB3BaseException(
                    //WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strErrorMessage);
                // 2004/11/08 ��Q�Ǘ��[No.U00377 �o�̓G���[�̏C�� ����@@SRA END  
            }
            
            Integer l_branchPreferences = 
                this.getBranchPreferences((WEB3GentradeSubAccount) this.getSubAccount());
            if(l_branchPreferences != null && l_branchPreferences.intValue() == 1)
            {
                //1.4  validate���Z��(�⏕����)
                l_orderValidator.validateResident((WEB3GentradeSubAccount)this.getSubAccount());
            }
            
            //1.5 IPO����
            WEB3IpoProductManagerImpl l_productManager = 
                (WEB3IpoProductManagerImpl)l_tradingModule.getProductManager();
            long l_lngProductID = Long.parseLong(l_request.id);
            WEB3IpoProductImpl l_product = 
                (WEB3IpoProductImpl)l_productManager.getProduct(l_lngProductID);//NotFoundException
        
//            //1.5 validate�ژ_��������
//            l_orderValidator.validateProspectusExistingRead(this.getSubAccount(), l_product);
    
            //1.6 validate�u�b�N�r���f�B���O����
            l_orderValidator.validateBookbuildingProduct(l_product);
            
            //1.7 validate��d�\��
            l_orderValidator.validateDuplicateOrder(this.getSubAccount(), l_product);
            
//            //1.8 is�ژ_��������
//            WEB3GentradeDocumentSystemConnectService l_documentSystemConnect = 
//                (WEB3GentradeDocumentSystemConnectService)Services.getService(WEB3GentradeDocumentSystemConnectService.class);
//            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)this.getSubAccount().getMainAccount();
//            boolean l_blnProspectusAccept = l_documentSystemConnect.isProspectusAccept(
//                l_mainAccount,
//                ((IpoProductParams)l_product.getDataSourceObject()).getProductType(),
//                ((IpoProductParams)l_product.getDataSourceObject()).getProductCode());
        
//            //1.9 is��~��
//            boolean l_blnStop = l_documentSystemConnect.isSystemStop();


            //1.8 get���̑����i���t�\�z(�⏕����,��n��)
            TradingSystem l_trdSys = l_finApp.getTradingSystem();         
            WEB3TPTradingPowerService l_tpTPS  
                = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            double l_trdPow = l_tpTPS.getOtherTradingPower((WEB3GentradeSubAccount)this.getSubAccount(),l_trdSys.getBizDate() );
            
            //1.9 (*) �d�q���`�F�b�N���s���i���N�G�X�g�f�[�^.�d�q���`�F�b�N�t���O == true
            //���� IPO����.get�ژ_�����{���敪() == 0:�{���v�j�̏ꍇ
            boolean l_blnBatoCheckFlag = l_request.batoCheckFlag;
            
            WEB3GentradeProspectusResult l_prospectusResult = null;
            
            if (l_blnBatoCheckFlag
                && WEB3DocReadingDivDef.DEFAULT.equals(l_product.getDocReadingDiv()))
            {
                //1.9.1  validate�ژ_�����{��(String, String)
                WEB3GentradeBatoClientService l_batoClientService = (WEB3GentradeBatoClientService) Services.getService(WEB3GentradeBatoClientService.class);
                
                String l_strProductCode = ((IpoProductParams)l_product.getDataSourceObject()).getProductCode();
                
                log.debug("�����R�[�h: " + l_strProductCode);
                
                if (l_strProductCode != null)
                {
                    l_strProductCode = l_strProductCode.trim();
                    if (l_strProductCode.endsWith("1"))
                    {
                        if (l_strProductCode.length() >= 4)
                        {
                            l_strProductCode = l_strProductCode.trim().substring(0, 4);
                        }
                        else
                        {
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                                getClass().getName() + STR_METHOD_NAME,
                                "�����R�[�h�̌�����4����菬�����B");
                        }
                    }
                    else
                    {
                        if (l_strProductCode.length() >= 5)
                        {
                            l_strProductCode = l_strProductCode.trim().substring(0, 5);
                        }
                        else
                        {
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                                getClass().getName() + STR_METHOD_NAME,
                                "�����R�[�h�̌�����5����菬�����B");
                        }
                    }
                }
                
                l_prospectusResult = l_batoClientService.validateProspectus(l_request.typeCode, l_strProductCode);
            }
            
            
            //1.10 IPO�u�b�N�r���f�B���O�\�����̓��X�|���X
            l_response = 
                (WEB3IPOBookBuildingDemandInputResponse)l_request.createResponse();
            
            //1.11 �v���p�e�B�Z�b�g
            IpoProductRow l_productRow = (IpoProductRow)l_product.getDataSourceObject();
            
            //�����R�[�h
            
            l_response.productCode = l_productRow.getProductCode();
            
            //������
            l_response.productName = l_product.getStandardName();
            
            //���J�s��R�[�h
            l_response.publicOfferingMarketCode = l_product.getPublicMarket();
            
            //�������敪
            l_response.temporaryConditionDiv = l_productRow.getProvisionalValueDiv();
            
            //�����������l
            if(!l_productRow.getProvisionalMinValueIsNull())
            {
                l_response.temporaryConditionLower = WEB3StringTypeUtility.formatNumber(
                    l_productRow.getProvisionalMinValue());
            }
            
            //����������l
            if(!l_productRow.getProvisionalMaxValueIsNull())
            {
                l_response.temporaryConditionUpper = WEB3StringTypeUtility.formatNumber(
                    l_productRow.getProvisionalMaxValue());
            }
                
            //����
            if(!l_productRow.getTickValueIsNull())
            {
                l_response.tickValue = WEB3StringTypeUtility.formatNumber(
                    l_productRow.getTickValue());
            }
            
            //�\���p�P�ʋ敪
            l_response.displayUnitDiv = l_productRow.getIpoUnitDiv();
            
            //�w���\���P��
            if(!l_productRow.getLotSizeIsNull())
            {
                l_response.offerUnit = WEB3StringTypeUtility.formatNumber(l_productRow.getLotSize());
            }
            
            //���s�\
            l_response.marketOrderFlag = l_productRow.getEnableMarketOrder();
            
            //�o���]��
            l_response.paymentPower = WEB3StringTypeUtility.formatNumber(l_trdPow);
                
//            //�ژ_��������
//            l_response.prospectusAgreement = l_blnProspectusAccept; 
            
            //�d�q����Q�t���O
//            WEB3GentradeDocumentSystemConnectService l_documentSystemConnect = 
//                (WEB3GentradeDocumentSystemConnectService)Services.getService(WEB3GentradeDocumentSystemConnectService.class);
//            l_response.batoTroubleFlag = l_documentSystemConnect.isSystemStop();
            
            //���X�|���X�f�[�^.�ژ_�����{���`�F�b�N����
            if (l_blnBatoCheckFlag)
            {
                log.debug("�d�q���`�F�b�N���s���ꍇ�Avalidate�ژ_�����{��()�̖߂�l�B");
                l_response.prospectusResult = l_prospectusResult;
            }
            else
            {
                log.debug("�d�q���`�F�b�N���s���ȊO�ꍇ�Anull�̖߂�l�B");
                l_response.prospectusResult = null;
            }
        }
        catch (NotFoundException l_e)
        {
            String l_strErrorMessage = "get���͉��error!";
            log.error(l_strErrorMessage, l_e);
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage, 
                l_e);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;        
    }
    
    /**
     * (validate�u�b�N�r���f�B���O�\��)<BR>
     * IPO�u�b�N�r���f�B���O�\���m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�ޯ�����ިݸސ\���jvalidate�u�b�N�r���f�B���O�\���v�Q�ƁB<BR>
     * @@param l_request - IPO�u�b�N�r���f�B���O�\���m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingOrderConfirmResponse
     * @@roseuid 40D2843A0268
     */
    protected WEB3IPOBookBuildingDemandConfirmResponse validateBookbuildingOrder(
        WEB3IPOBookBuildingDemandConfirmRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateBookbuildingOrder(WEB3IpoBookbuildingOrderConfirmRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3IPOBookBuildingDemandConfirmResponse l_response = null;
        try
        {
            //1.1 validate
            l_request.validate();
            
            //1.2 validate������t�\
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            
            //1.3 get������
            Date l_bizOrderDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            
            //1.4 get�㗝���͎�
            Trader l_trader = this.getTrader();
            
			//1.5 �u�b�N�r���f�B���O�\�����e
			// 2004/11/4 U00361 BB�\�����e�C���X�^���X�����s��̂���"0"���Z�b�g�@@���@@SRA START
			//�@@���s�ł��\�����i��Null�̎��A�\�����i��"0"���Z�b�g����
			//if(WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.demandPriceDiv) && l_request.demandPrice == null){
			//	l_request.demandPrice = "0";
			//}
			// 2004/11/4 U00361 BB�\�����e�C���X�^���X�����s��̂���"0"���Z�b�g�@@���@@SRA END
			
			// 2004/11/10 U00393 BB�\�����e�C���X�^���X�����s��̂��߈ꎞ�ϐ���"0"���Z�b�g�@@���@@SRA START
			//�@@�\�����i�̈ꎞ�ϐ�l_dbldemandPrice���Z�b�g���A����������Ƃ��ēn��
			  double l_dbldemandPrice = 0;
			  if(WEB3StringTypeUtility.isNumber(l_request.demandPrice)){
				  l_dbldemandPrice = Double.parseDouble(l_request.demandPrice);
			  }
					  WEB3IpoBookbuildingNewOrderSpec l_ipoBookBuildingOrderSpec = 
									  new WEB3IpoBookbuildingNewOrderSpec(
											  l_trader,
											  Long.parseLong(l_request.id),
											  Double.parseDouble(l_request.demandQuantity),
											  l_dbldemandPrice,
											  0);			
//					  WEB3IpoBookbuildingNewOrderSpec l_ipoBookBuildingOrderSpec = 
//									  new WEB3IpoBookbuildingNewOrderSpec(
//											  l_trader,
//											  Long.parseLong(l_request.id),
//											  Double.parseDouble(l_request.demandQuantity),
//											  Double.parseDouble(l_request.demandPrice),
//											  0);
			// 2004/11/10 U00393 BB�\�����e�C���X�^���X�����s��̂��߈ꎞ�ϐ���"0"���Z�b�g�@@���@@SRA END            
            
            //1.6 get�⏕����
            SubAccount l_subAccount = this.getSubAccount();
            
            //1.7 validate�u�b�N�r���f�B���O�\��
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IPO);
            WEB3IpoOrderManagerImpl l_ipoOrderManager = 
                (WEB3IpoOrderManagerImpl)l_tradingModule.getOrderManager();
            NewOrderValidationResult l_orderValidationResult = l_ipoOrderManager.validateNewOrder(
                l_subAccount, 
                ProductTypeEnum.IPO, 
                l_ipoBookBuildingOrderSpec);
                
            if(!l_orderValidationResult.getProcessingResult().equals(ProcessingResult.SUCCESS_RESULT))
            {
                String l_strErrorMessage = "invalidate�u�b�N�r���f�B���O�\��.";
                log.error(l_strErrorMessage);
                // 2004/11/08 ��Q�Ǘ��[No.U00377 �o�̓G���[�C�� ����@@SRA START
                throw new WEB3BaseException(
                    //WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    l_orderValidationResult.getProcessingResult().getErrorInfo(), 
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strErrorMessage);
                // 2004/11/08 ��Q�Ǘ��[No.U00377 �o�̓G���[�C�� ����@@SRA END  
            }
            
            //1.8 calc�\�������z
            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount )
                this.getSubAccount().getMainAccount();
                
            //IPO����
            WEB3IpoProductManagerImpl l_productManager = 
                (WEB3IpoProductManagerImpl)l_tradingModule.getProductManager();
            long l_lngProductID = Long.parseLong(l_request.id);
            WEB3IpoProductImpl l_product = 
                (WEB3IpoProductImpl)l_productManager.getProduct(l_lngProductID);

            WEB3IpoBookbuildingPriceCalcResult l_ipoBookBuildingPriceCalcResult = 
                l_ipoOrderManager.calcBookbuildingPrice(
                    l_mainAccount,
                    l_product,
                    l_ipoBookBuildingOrderSpec.getLimitPrice(),
                    l_ipoBookBuildingOrderSpec.getQuantity(),
                    0);
                    
            //1.9 get�\�������z
            double l_dblOrderPrice = l_ipoBookBuildingPriceCalcResult.getBookbuildingPrice();
            
            //1.10 get��l�i�����j
            double l_dblBasePrice = l_ipoBookBuildingPriceCalcResult.getCurrentPrice();
            
            //1.11 createResponse(
            l_response = (WEB3IPOBookBuildingDemandConfirmResponse)l_request.createResponse();
             
            //1.12 �v���p�e�B�Z�b�g
            l_response.demandEquivalentPrice = WEB3StringTypeUtility.formatNumber(l_dblOrderPrice);
            l_response.checkDate = l_bizOrderDate;
            
            if(!Double.isNaN(l_dblBasePrice))
            {
                l_response.checkValue = WEB3StringTypeUtility.formatNumber(l_dblBasePrice);
            }
            else
            {
                l_response.checkValue = "0";
            }
            
        }
        catch(NotFoundException l_e)
        {
            String l_strErrorMessage = "validate�u�b�N�r���f�B���O�\��-error.";
             log.error(l_strErrorMessage, l_e);
             throw new WEB3BaseException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                 this.getClass().getName() + STR_METHOD_NAME,
                 l_strErrorMessage, 
                 l_e);  
        }

        
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (submit�u�b�N�r���f�B���O�\��)<BR>
     * IPO�u�b�N�r���f�B���O�\�������������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�ޯ�����ިݸސ\���jsubmit�u�b�N�r���f�B���O�\���v�Q�ƁB<BR>
     * @@param l_request - IPO�u�b�N�r���f�B���O�\���������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingOrderCompleteRequest
     * @@roseuid 40D2843A0278
     */
    protected WEB3IPOBookBuildingDemandCompleteResponse submitBookbuildingOrder(
        WEB3IPOBookBuildingDemandCompleteRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitBookbuildingOrder(WEB3IpoBookbuildingOrderCompleteRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3IPOBookBuildingDemandCompleteResponse l_response = null;
        try
        {
            //1.1 validate
            l_request.validate();
            
            //1.2 validate������t�\
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            
            //1.3 get������
            Date l_bizOrderDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            
            //1.4 get�㗝���͎�
            Trader l_trader = this.getTrader();
            
			//1.5 �u�b�N�r���f�B���O�\�����e
			//2004/11/4 BB�\�����e�C���X�^���X�����s��̂���"0"���Z�b�g�@@���@@SRA START
			//�@@���s�ł��\�����i��Null�̎��A�\�����i��"0"���Z�b�g����
			//if(WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.demandPriceDiv) && l_request.demandPrice == null){
			//	 l_request.demandPrice = "0";
			// }
			// 2004/11/4 BB�\�����e�C���X�^���X�����s��̂���"0"���Z�b�g�@@���@@SRA END
            
			//2004/11/10 U00393 BB�\�����e�C���X�^���X�����s��̂��߈ꎞ�ϐ���"0"���Z�b�g�@@���@@SRA START
			double l_dbldemandPrice = 0;
						  if(WEB3StringTypeUtility.isNumber(l_request.demandPrice)){
							  l_dbldemandPrice = Double.parseDouble(l_request.demandPrice);
						  }
			//2004/11/10 U00393 BB�\�����e�C���X�^���X�����s��̂��߈ꎞ�ϐ���"0"���Z�b�g�@@���@@SRA END
            
			double l_dblCheckValue = 0;
			if(l_request.checkValue != null)
			{
				l_dblCheckValue = Double.parseDouble(l_request.checkValue);
			}
            
			//2004/11/10 U00393 BB�\�����e�C���X�^���X�����s��̂��߈ꎞ�ϐ���"0"���Z�b�g�@@���@@SRA START
			WEB3IpoBookbuildingNewOrderSpec l_ipoBookBuildingOrderSpec = 
							new WEB3IpoBookbuildingNewOrderSpec(
									l_trader,
									Long.parseLong(l_request.id),
									Double.parseDouble(l_request.demandQuantity),
									l_dbldemandPrice,
									l_dblCheckValue);
//			  WEB3IpoBookbuildingNewOrderSpec l_ipoBookBuildingOrderSpec = 
//				  new WEB3IpoBookbuildingNewOrderSpec(
//						  l_trader,
//						  Long.parseLong(l_request.id),
//						  Double.parseDouble(l_request.demandQuantity),
//						  Double.parseDouble(l_request.demandPrice),
//						  l_dblCheckValue);
			//2004/11/10 U00393 BB�\�����e�C���X�^���X�����s��̂��߈ꎞ�ϐ���"0"���Z�b�g�@@���@@SRA END
	          
            
            //1.6 get�⏕����
            SubAccount l_subAccount = this.getSubAccount();
            
            //1.7 validate�u�b�N�r���f�B���O�\��
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IPO);
            WEB3IpoOrderManagerImpl l_ipoOrderManager = 
                (WEB3IpoOrderManagerImpl)l_tradingModule.getOrderManager();
            NewOrderValidationResult l_orderValidationResult = l_ipoOrderManager.validateNewOrder(
                l_subAccount, 
                ProductTypeEnum.IPO, 
                l_ipoBookBuildingOrderSpec);
            if(!l_orderValidationResult.getProcessingResult().equals(ProcessingResult.SUCCESS_RESULT))
            {
                String l_strErrorMessage = "invalidate�u�b�N�r���f�B���O�\��.";
                log.error(l_strErrorMessage);
                // 2004/11/08 ��Q�Ǘ��[No.U00377 �o�̓G���[�̏C�� ����@@SRA START
                throw new WEB3BaseException(
                    //WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strErrorMessage);
                // 2004/11/08 ��Q�Ǘ��[No.U00377 �o�̓G���[�̏C�� ����@@SRA END  
            }
            
            //1.8 submit�u�b�N�r���f�B���O�\��
            OrderSubmissionResult submissionResult = l_ipoOrderManager.submitNewOrder(
                l_subAccount,
                ProductTypeEnum.IPO,
                l_ipoBookBuildingOrderSpec,
                l_orderValidationResult.getNewOrderId(),
                l_request.password,
                true);
            if(!submissionResult.getProcessingResult().equals(ProcessingResult.SUCCESS_RESULT))
            {
                String l_strErrorMessage = "submit�u�b�N�r���f�B���O�\��error!";
                log.error(l_strErrorMessage);
                // 2004/11/09 ��Q�Ǘ��[No.U00392 �o�̓G���[�C�� ����@@SRA START 
                throw new WEB3BaseException(
                    //WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    submissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strErrorMessage);  
                // 2004/11/09 ��Q�Ǘ��[No.U00392 �o�̓G���[�C�� ����@@SRA END
            }
            
            //1.9 IPO�\��
            WEB3IpoOrderImpl l_ipoOrder = 
                (WEB3IpoOrderImpl)l_ipoOrderManager.getOrderUnit(l_orderValidationResult.getNewOrderId());
                        
            //1.10 createResponse(
            l_response = (WEB3IPOBookBuildingDemandCompleteResponse)l_request.createResponse();
            
            //1.11 �v���p�e�B�Z�b�g
            l_response.orderActionId = 
                WEB3StringTypeUtility.formatNumber(l_orderValidationResult.getNewOrderId());

            TradingSystem l_tradingSystem = l_finApp.getTradingSystem();
            l_response.lastUpdatedTimestamp = 
                l_tradingSystem.getSystemTimestamp();
            
        }
        catch (NotFoundException l_e)
        {
            String l_strErrorMessage = "IPO�\��error!";
            log.error(l_strErrorMessage, l_e);
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage, 
                l_e);  
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (get���X�v���t�@@�����X)<BR>
     * �ڋq�I�u�W�F�N�g���A���X�p�v���t�@@�����X�e�[�u������<BR>
     * IPO���Z�҃`�F�b�N���擾����B<BR>
     * <BR>
     * �P�jDB����<BR>
     * �@@�@@���X�v���t�@@�����X�e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@���XID = �⏕����.getBranch().getBranchId() And<BR>
     * �@@�@@�v���t�@@�����X�� = �v���t�@@�����X��.IPO���Z�҃`�F�b�N And <BR>
     * �@@�@@�v���t�@@�����X���̘A�� = 1<BR>
     * <BR>
     * �@@�@@�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�@@��������.�v���t�@@�����X�̒l��ԋp����B<BR>
     * @@param l_subAccount - (�⏕�����I�u�W�F�N�g)<BR>
     * �⏕�����I�u�W�F�N�g�B
     * @@return Integer
     * @@throws WEB3BaseException
     */
    protected Integer getBranchPreferences(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBranchPreferences(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug("�⏕���� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�⏕���� = null�B");
        }
        
        try 
        {
            //�P�jDB���� 
            //  ���X�v���t�@@�����X�e�[�u�����ȉ��̏����Ō�������B
            BranchPreferencesRow l_branchReferencesRow = 
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_subAccount.getWeb3GenBranch().getBranchId(), 
                    WEB3BranchPreferencesNameDef.IPO_RESIDENT_CHECK,
                    1);
            
            log.exiting(STR_METHOD_NAME);
            
            //�Q�j�@@��������.�v���t�@@�����X�̒l��ԋp����B
            if (l_branchReferencesRow == null)
            {
                return null;
            }
            else
            {
                return Integer.valueOf(l_branchReferencesRow.getValue());
            }
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

}
@
