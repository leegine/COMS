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
filename	WEB3MstkBuyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�������t�����T�[�r�XImpl(WEB3MstkBuyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ���]�� (���u) �V�K�쐬
                   2004/12/10 �����a��(SRA) �c�Č��Ή� �m��.�Q�W�O���m��.�Q�W�Q���m��.�Q�W�S���m��.�Q�W�T
                   2004/12/13 �����a��(SRA) �c�Č��Ή� �m��.�S�O�U
                   2004/12/29 �����a��(SRA) JavaDoc�C��
                   2006/11/14 ������ (���u)�@@���f��No.1026
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3PayTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityEstimatedDeliveryPrice;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MiniClientRequestService;
import webbroker3.equity.WEB3MiniStockOrderUpdateInterceptor;
import webbroker3.equity.WEB3NewMiniStockOrderSpec;
import webbroker3.equity.message.WEB3MstkBuyCompleteRequest;
import webbroker3.equity.message.WEB3MstkBuyCompleteResponse;
import webbroker3.equity.message.WEB3MstkBuyConfirmRequest;
import webbroker3.equity.message.WEB3MstkBuyConfirmResponse;
import webbroker3.equity.message.WEB3MstkBuyInputRequest;
import webbroker3.equity.message.WEB3MstkBuyInputResponse;
import webbroker3.equity.message.WEB3MstkCommissionInfoUnit;
import webbroker3.equity.service.delegate.WEB3MstkBuyService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;

/**
 * �i�����~�j�������t�����T�[�r�XImpl�j�B<BR>
 * <BR>
 * �����~�j�������t�����T�[�r�X�����N���X
 * @@author ���]��
 * @@version 1.0
 */
public class WEB3MstkBuyServiceImpl extends WEB3MiniClientRequestService implements WEB3MstkBuyService 
{
    /**
     * �i���O�o�̓��[�e�B���e�B�j�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkBuyServiceImpl.class);

    /**
     * 
     */
    public WEB3MstkBuyServiceImpl() 
    {
    }

    /**
     * �iexecute�j�B<BR>
     * <BR>
     * �����~�j�������t�������������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�����~�j�������t�������̓��N�G�X�g�̏ꍇ<BR>
     * �@@�|get���͉��()���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�����~�j�������t�����m�F���N�G�X�g�̏ꍇ<BR>
     * �@@�|validate����()���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�����~�j�������t�����������N�G�X�g�̏ꍇ<BR>
     * �@@�|submit����()���R�[������B 
     * @@param l_request (���N�G�X�g)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3MstkBuyInputRequest)
        {
            l_response = this.getInputScreen((WEB3MstkBuyInputRequest)l_request);
        }
        else if (l_request instanceof WEB3MstkBuyConfirmRequest)
        {           
            l_response = this.validateOrder((WEB3MstkBuyConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3MstkBuyCompleteRequest)
        {           
            l_response = this.submitOrder((WEB3MstkBuyCompleteRequest)l_request);
        }
        else
        {
            String l_strErrorMessage = 
                "�p�����[�^�̗ތ^���s���A�Y������WEB3MstkBuyInputRequest," + "WEB3MstkBuyConfirmRequest,"+
                "WEB3MstkBuyCompleteRequest�ތ^�B";
            log.error(l_strErrorMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);            
        }

        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * �iget���͉�ʁj�B<BR>
     * <BR>
     * �����~�j�������t���͉�ʕ\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�~�j�����t�T�[�r�X�jget���͉�ʁv�Q�ƁB
     * @@param l_request (���N�G�X�g�f�[�^)<BR>
     * �����~�j�������t�������̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3MstkBuyInputResponse
     */
    protected WEB3MstkBuyInputResponse getInputScreen(WEB3MstkBuyInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3MstkBuyInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1)validate( )
        l_request.validate();       //throws WEB3BaseException
        
        //2)get�⏕����()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //3)validate�~�j������(�⏕����)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManagerImpl = 
                                     (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        l_orderManagerImpl.validateMiniStockOrder(l_subAccount);         
           
        //4)is�~�j������I���x��(���X)             
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        boolean l_blnMiniStockSuspension = WEB3GentradeTradingTimeManagement.isMiniStockSuspension(l_branch) ;
        
        
        //createResponse()
        WEB3MstkBuyInputResponse l_response = (WEB3MstkBuyInputResponse)l_request.createResponse();
        String l_strProductCode = l_request.productCode;
        
        Date l_datDeliveryDate = null;

        //------------
        //�����w�肠��
        //------------
        if (l_strProductCode != null)
        {
            //6.1)validate��������i�~�j���j(�⏕���� : �⏕����, �����R�[�h : String, is������ : boolean)
            WEB3EquityTradedProduct l_tradedProduct = l_orderManagerImpl.validateMiniStockTradedProduct(l_subAccount, l_strProductCode, false);
     
            //6.2)getProduct( )
            EqtypeProductRow l_productRow = (EqtypeProductRow) l_tradedProduct.getProduct().getDataSourceObject();
            String l_strProductName = l_productRow.getStandardName();
            //6.3)getMarket( )
            String l_strMarketCode = l_tradedProduct.getMarket().getMarketCode();
            l_response.productName = l_strProductName;
            l_response.marketCode = l_strMarketCode;
            
            // 6.4)is�C���T�C�_�[�x���\��(�⏕���� : �⏕����, ����ID : long)
            boolean l_insiderWarningFlag = l_orderManagerImpl.isInsiderMessageSuspension(l_subAccount, l_tradedProduct.getProduct().getProductId());
            l_response.insiderWarningFlag = l_insiderWarningFlag;

            //-----------------------------------------------------
            //��n���̎擾
            //validate��������i�~�j���j�̖߂�l�̊�����������D��n��
            //-----------------------------------------------------
            l_datDeliveryDate = l_tradedProduct.getDailyDeliveryDate();
        }
        //------------
        //�����w��Ȃ�
        //------------
        else
        {
            l_response.productName = null;
            l_response.marketCode = null;
            l_response.insiderWarningFlag = false;
            
            // 7.1)validate�ڋq�����ʎ����~(�⏕���� : �⏕����, ����ID : long, ������� : OrderTypeEnum)
            l_orderManagerImpl.validateAccountProductOrderStop(l_subAccount, 0, OrderTypeEnum.MINI_STOCK_BUY);

            //-----------------------------------------------------
            //��n���̎擾
            //��������3�c�Ɠ���
            //-----------------------------------------------------
            Date l_datCheckDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            WEB3GentradeBizDate l_bizDateCheckDate
                = new WEB3GentradeBizDate(new Timestamp(l_datCheckDate.getTime()));
            l_datDeliveryDate =  l_bizDateCheckDate.roll(3);
        }

        //------------------------
        //get���̑����i���t�\�z()
        //------------------------
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);
        double l_dblBuyPossiblePrice = l_tpTradingPowerService.getOtherTradingPower(l_subAccount, l_datDeliveryDate);
        
        l_response.tradingPower = WEB3StringTypeUtility.formatNumber(l_dblBuyPossiblePrice);
        l_response.messageSuspensionFlag = l_blnMiniStockSuspension;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * �ivalidate�����j�B<BR>
     * <BR>
     * �����~�j�������t���������R�������{����B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�~�j�����t�T�[�r�X�jvalidate�����v�Q�ƁB
     * @@param l_request (���N�G�X�g�f�[�^)<BR>
     * �����~�j�������t�����m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3MstkBuyConfirmResponse
     */
    protected WEB3MstkBuyConfirmResponse validateOrder(WEB3MstkBuyConfirmRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateOrder(WEB3MstkBuyConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1)validate( )        
        l_request.validate(); //throws WEB3BaseException
        
        //2)get�⏕����()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //3)�㗝���͎҂��擾����
        Trader l_trader = this.getTrader();
        
        //4)validate��������i�~�j���j(�⏕����, String, boolean)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManagerImpl = 
                                     (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        String l_strProductCode = l_request.productCode;
        WEB3EquityTradedProduct l_tradedProduct = 
                                     l_orderManagerImpl.validateMiniStockTradedProduct(l_subAccount, l_strProductCode, false);
        
        //5)create�����~�j�����������e(Trader, boolean, String, String, double)
        boolean l_blnIsNumber = WEB3StringTypeUtility.isNumber(l_request.orderQuantity);
        double l_dblOrderQuantity;
        if(!l_blnIsNumber)
        {
            l_dblOrderQuantity = 0;
        }
        l_dblOrderQuantity = Double.parseDouble(l_request.orderQuantity); 
        
        String l_strMarketCode = l_tradedProduct.getMarket().getMarketCode();
        WEB3NewMiniStockOrderSpec l_mstkOrderSpec = WEB3NewMiniStockOrderSpec.createNewMiniStockOrderSpec(
                l_trader, 
                true, 
                l_strProductCode, 
                l_strMarketCode, 
                l_dblOrderQuantity
                );
        
        //6)validate�~�j�����t����(�⏕����, �����~�j�����������e)
        NewOrderValidationResult l_orderValidationResult = l_orderManagerImpl.validateMiniStockBuyOrder(l_subAccount, l_mstkOrderSpec);
        if(l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            throw new WEB3BaseException(l_orderValidationResult.getProcessingResult().getErrorInfo(),STR_METHOD_NAME);
        }
        //7)get���O�C���`���l��
        String l_strLoginChannel = this.getLoginChannel();
        //8)get������
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        //9)�萔��
        WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
        //10)�萔���̃Z�b�g
        l_commission.setOrderChannel(l_strLoginChannel);
        l_commission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());
        l_commission.setBranchId(l_subAccount.getWeb3GenBranch().getBranchId());
        l_commission.setOrderBizDate(new Timestamp(l_datOrderBizDate.getTime()));
        l_commission.setSonarTradedCode(WEB3TransactionTypeSONARDef.MARKET_TRADING);
        l_commission.setCommissionProductCode(WEB3CommisionProductCodeDef.MINI_STOCK);
        l_commission.setPayType(WEB3PayTypeDef.OTHER);
        
        //11) calc�T�Z��n����i�~�j���j(
        //                  �萔�� : �萔��,
        //                  �⏕���� : SubAccount,
        //                  ������� : �������,
        //                  ���� : double,
        //                  is������ : boolean,
        //                  �v�Z�P�� : double,
        //                  is�S���l�� : boolean)
        WEB3EquityEstimatedDeliveryPrice l_estimatedDeliveryPrice = l_orderManagerImpl.calcMiniStockEstimatedDeliveryAmount(
            l_commission, 
            l_subAccount, 
            l_tradedProduct, 
            l_dblOrderQuantity, 
            false, 
            Double.NaN,
            true);
            
        //12) �����~�j���������X�V�C���^�Z�v�^(�~�j���������e : �����~�j�����������e
        //    , �萔�� : �萔��, �T�Z��n����v�Z���� : �T�Z��n����v�Z����)
        WEB3MiniStockOrderUpdateInterceptor l_orderUpdateInterceptor = new WEB3MiniStockOrderUpdateInterceptor(
            l_mstkOrderSpec,
            l_commission,
            l_estimatedDeliveryPrice);

        //13) validate����]��(�⏕���� : �⏕����, �������e�C���^�Z�v�^ : Object[], �������e : Object[], ������� : OrderTypeEnum, �]�͍X�V�t���O : boolean)
        log.debug("validate����]�͂��s��");
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);

        WEB3MiniStockOrderUpdateInterceptor[] l_orderUpdateInterceptors = new WEB3MiniStockOrderUpdateInterceptor[1];
        WEB3NewMiniStockOrderSpec[] l_orderSpecs = new WEB3NewMiniStockOrderSpec[1];
        l_orderUpdateInterceptors[0] = l_orderUpdateInterceptor;
        l_orderSpecs[0] = l_mstkOrderSpec;
        
        WEB3TPTradingPowerResult l_tpResult = null;
        l_tpResult = l_tpTradingPowerService.validateTradingPower(
            l_subAccount,
            l_orderUpdateInterceptors,
            l_orderSpecs,
            OrderTypeEnum.MINI_STOCK_BUY,
            false);
        if (l_tpResult.isResultFlg() == false)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
            
        //14) is�~�j������I���x��(���X)
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        boolean l_blnMessageSuspensionFlag = WEB3GentradeTradingTimeManagement.isMiniStockSuspension(l_branch);  
        
        
        WEB3MstkBuyConfirmResponse l_response = (WEB3MstkBuyConfirmResponse)l_request.createResponse();
        //------------
        //�������擾
        //------------
        WEB3EquityProductManager l_productManager = 
            (WEB3EquityProductManager) l_tradingModule.getProductManager();
        WEB3EquityProductQuote l_equityProductQuote = l_productManager.getDisplayEquityProductQuote(l_tradedProduct, l_subAccount);
        String l_strQuoteTypeDiv = l_equityProductQuote.getQuoteTypeDiv();
        double l_dblCurrentPrice = l_equityProductQuote.getQuote();
        double l_dblChange = l_equityProductQuote.getComparedPreviousDay();
        Timestamp l_tsQuoteTime = l_equityProductQuote.getQuoteTime();
        
        //19) createNewOrderId()
        long l_lngOrderId = l_orderManagerImpl.createNewOrderId();
        //20) is�C���T�C�_�[�x���\��(�⏕���� : �⏕����, ����ID : long)
        boolean l_insiderWarningFlag = l_orderManagerImpl.isInsiderMessageSuspension(l_subAccount, l_tradedProduct.getProduct().getProductId());

        WEB3MstkCommissionInfoUnit l_commissionInfoUnit = new WEB3MstkCommissionInfoUnit();
        l_commissionInfoUnit.commissionCourse = null;
        l_commissionInfoUnit.commission = WEB3StringTypeUtility.formatNumber(l_estimatedDeliveryPrice.getCommissionFee());
        l_commissionInfoUnit.commissionConsumptionTax = WEB3StringTypeUtility.formatNumber(l_estimatedDeliveryPrice.getCommissionFeeTax());
        //21)  (*3) �v���p�e�B�Z�b�g
        l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_estimatedDeliveryPrice.getEstimateDeliveryAmount());

        l_response.commissionInfo = l_commissionInfoUnit;
        l_response.currentPriceDiv = l_strQuoteTypeDiv;
        l_response.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);
        l_response.checkPrice = WEB3StringTypeUtility.formatNumber(l_estimatedDeliveryPrice.getCalcUnitPrice());
        l_response.checkDate = WEB3DateUtility.toDay(l_datOrderBizDate);
        l_response.orderId = String.valueOf(l_lngOrderId);
        l_response.messageSuspensionFlag = l_blnMessageSuspensionFlag;
        l_response.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);
        l_response.currentPriceTime = l_tsQuoteTime;
        EqtypeProductRow l_productRow = (EqtypeProductRow) l_tradedProduct.getProduct().getDataSourceObject();
        l_response.productName = l_productRow.getStandardName();
        l_response.marketCode = l_mstkOrderSpec.getMarketCode();
        l_response.insiderWarningFlag = l_insiderWarningFlag;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * �isubmit�����j�B<BR>
     * <BR>
     * �����~�j�������t�����o�^���������{����B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�~�j�����t�T�[�r�X�jsubmit�����v�Q�ƁB
     * @@param l_request (���N�G�X�g�f�[�^)<BR>
     * �����~�j�������t�����������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3MstkBuyCompleteResponse
     */
    protected WEB3MstkBuyCompleteResponse submitOrder(WEB3MstkBuyCompleteRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitOrder(WEB3MstkBuyCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1) validate()
        l_request.validate();
        
        //2) get�⏕����()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //3) get�㗝���͎�()
        Trader l_trader = this.getTrader();
        
        //4) validate��������i�~�j���j(�⏕����, String, boolean)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tradingMod.getOrderManager();
        WEB3EquityTradedProduct l_tradedProduct = 
            l_orderManager.validateMiniStockTradedProduct(l_subAccount, l_request.productCode, false);
        
        //5) create�����~�j�����������e(Trader, boolean, String, String, double)
        boolean l_blnIsNumber = WEB3StringTypeUtility.isNumber(l_request.orderQuantity);
        double l_dblOrderQuantity;
        if(!l_blnIsNumber)
        {
            l_dblOrderQuantity = 0;
        }
        l_dblOrderQuantity = Double.parseDouble(l_request.orderQuantity); 
        
        WEB3NewMiniStockOrderSpec l_orderSpec = WEB3NewMiniStockOrderSpec.createNewMiniStockOrderSpec(
            l_trader, 
            true, 
            l_request.productCode, 
            l_tradedProduct.getMarket().getMarketCode(),
            l_dblOrderQuantity);
        //6) validate�~�j�����t����(�⏕����, �����~�j�����������e)
        NewOrderValidationResult l_buyResult = l_orderManager.validateMiniStockBuyOrder(l_subAccount, l_orderSpec);
        if (l_buyResult.getProcessingResult().isFailedResult())
        {
            
            throw new WEB3BaseException(l_buyResult.getProcessingResult().getErrorInfo(), STR_METHOD_NAME);
        }
        
        //7) get���O�C���`���l��()
        String l_strLoginCancel = this.getLoginChannel();
        
        //8) get������()
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        
        //9) �萔��()
        WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
        
        //10)  (*1) �v���p�e�B�Z�b�g
        l_commission.setOrderChannel(l_strLoginCancel);
        l_commission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());
        l_commission.setBranchId(l_subAccount.getWeb3GenBranch().getBranchId());
        l_commission.setOrderBizDate(new Timestamp(l_datBizDate.getTime()));
        l_commission.setSonarTradedCode(WEB3TransactionTypeSONARDef.MARKET_TRADING);
        l_commission.setCommissionProductCode(WEB3CommisionProductCodeDef.MINI_STOCK);
        l_commission.setPayType(WEB3PayTypeDef.OTHER);
        
        //11) calc�T�Z��n����i�~�j���j(
        //                  �萔�� : �萔��,
        //                  �⏕���� : SubAccount,
        //                  ������� : �������,
        //                  ���� : double,
        //                  is������ : boolean,
        //                  �v�Z�P�� : double,
        //                  is�S���l�� : boolean)
        WEB3EquityEstimatedDeliveryPrice l_estimatedDeliveryPrice = l_orderManager.calcMiniStockEstimatedDeliveryAmount(
            l_commission, 
            l_subAccount, 
            l_tradedProduct, 
            l_dblOrderQuantity, 
            false, 
            Double.parseDouble(l_request.checkPrice),
            true);
                 
        //12) �����~�j���������X�V�C���^�Z�v�^(�����~�j�����������e, �萔��, �T�Z��n����v�Z����)
        WEB3MiniStockOrderUpdateInterceptor l_intercepter = new WEB3MiniStockOrderUpdateInterceptor(
            l_orderSpec, 
            l_commission, 
            l_estimatedDeliveryPrice);
        
        //13) validate����]��(�⏕���� : �⏕����, �������e�C���^�Z�v�^ : Object[], �������e : Object[], ������� : OrderTypeEnum, �]�͍X�V�t���O : boolean)
        log.debug("validate����]�͂��s��");
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);

        WEB3MiniStockOrderUpdateInterceptor[] l_orderUpdateInterceptors = new WEB3MiniStockOrderUpdateInterceptor[1];
        WEB3NewMiniStockOrderSpec[] l_orderSpecs = new WEB3NewMiniStockOrderSpec[1];
        l_orderUpdateInterceptors[0] = l_intercepter;
		l_orderSpecs[0] = l_orderSpec;
        
        WEB3TPTradingPowerResult l_tpResult = null;
        l_tpResult = l_tpTradingPowerService.validateTradingPower(
            l_subAccount,
            l_orderUpdateInterceptors,
            l_orderSpecs,
            OrderTypeEnum.MINI_STOCK_BUY,
            true);
            
        if (l_tpResult.isResultFlg() == false)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //14) setThreadLocalPersistenceEventInterceptor(EqTypeOrderManagerPersistenceEventInterceptor)
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_intercepter);
        
        //15) submitNewMiniStockOrder(SubAccount, EqTypeNewMiniStockOrderSpec, long, String, boolean)
        long l_lngOrderId;
        l_lngOrderId = Long.parseLong(l_request.orderId);
        EqTypeOrderSubmissionResult l_result = 
            l_orderManager.submitNewMiniStockOrder(l_subAccount, l_orderSpec, l_lngOrderId, l_request.password, true);
        if (l_result.getProcessingResult().isFailedResult())
        {
            
            throw new WEB3BaseException(l_result.getProcessingResult().getErrorInfo(), STR_METHOD_NAME);
        }

        //16) is�C���T�C�_�[�x���\��(�⏕���� : �⏕����, ����ID : long)
        boolean l_insiderWarningFlag = l_orderManager.isInsiderMessageSuspension(l_subAccount, l_tradedProduct.getProduct().getProductId());

        //17) createResponse()
        WEB3MstkBuyCompleteResponse l_response = (WEB3MstkBuyCompleteResponse)l_request.createResponse();
        
        //18) (*3) �v���p�e�B�Z�b�g
        l_response.lastUpdatedTimestamp = l_finApp.getTradingSystem().getSystemTimestamp();
        l_response.orderActionId = l_request.orderId;
        l_response.insiderWarningFlag = l_insiderWarningFlag;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
