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
filename	WEB3MstkSellServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�������t�����T�[�r�X�����N���X(WEB3MstkSellServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 ���C�g (���u) �V�K�쐬
                 : 2006/11/14 ������ (���u)�@@���f��No.1026
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityEstimatedDeliveryPrice;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MiniClientRequestService;
import webbroker3.equity.WEB3MiniStockOrderUpdateInterceptor;
import webbroker3.equity.WEB3MstkSellUnitComparator;
import webbroker3.equity.WEB3NewMiniStockOrderSpec;
import webbroker3.equity.define.WEB3EquityRepaymentDivOtherDef;
import webbroker3.equity.message.WEB3MstkCommissionInfoUnit;
import webbroker3.equity.message.WEB3MstkProductCodeNameUnit;
import webbroker3.equity.message.WEB3MstkSellCompleteRequest;
import webbroker3.equity.message.WEB3MstkSellCompleteResponse;
import webbroker3.equity.message.WEB3MstkSellConfirmRequest;
import webbroker3.equity.message.WEB3MstkSellConfirmResponse;
import webbroker3.equity.message.WEB3MstkSellInputRequest;
import webbroker3.equity.message.WEB3MstkSellInputResponse;
import webbroker3.equity.message.WEB3MstkSellListRequest;
import webbroker3.equity.message.WEB3MstkSellListResponse;
import webbroker3.equity.message.WEB3MstkSellUnit;
import webbroker3.equity.service.delegate.WEB3MstkSellService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i�����~�j�������t�����T�[�r�XImpl�j�B<br>
 * <br>
 * �����~�j�������t�����T�[�r�X�����N���X
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3MstkSellServiceImpl extends WEB3MiniClientRequestService implements WEB3MstkSellService 
{
    
    /**
     * �i���O�o�̓��[�e�B���e�B�j�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkSellServiceImpl.class);
    
    /**
     * 
     */
    public WEB3MstkSellServiceImpl() 
    {
     
    }
    
    /**
     * �iexecute�j�B<br>
     * <br>
     * �����~�j�������t�������������{����B<BR>
     * <br>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <br>
     * <br>
     * �� �����̃��N�G�X�g�f�[�^���A�����~�j�������t�ꗗ���N�G�X�g�̏ꍇ <br>
     * �@@�|get���t�ꗗ()���R�[������B <br>
     * <br>
     * �� �����̃��N�G�X�g�f�[�^���A�����~�j�������t�������̓��N�G�X�g�̏ꍇ <br>
     * �@@�|get���͉��()���R�[������B <br>
     * <br>
     * �� �����̃��N�G�X�g�f�[�^���A�����~�j�������t�����m�F���N�G�X�g�̏ꍇ <br>
     * �@@�|validate����()���R�[������B <br>
     * <br>
     * �� �����̃��N�G�X�g�f�[�^���A�����~�j�������t�����������N�G�X�g�̏ꍇ <br>
     * �@@�|submit����()���R�[������B
     * @@param l_request (���N�G�X�g)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        //�����̃��N�G�X�g�f�[�^���A�����~�j�������t�ꗗ���N�G�X�g�̏ꍇ
        if(l_request instanceof WEB3MstkSellListRequest)
        {  
            l_response = this.getSellList((WEB3MstkSellListRequest)l_request);
        }
        //�����̃��N�G�X�g�f�[�^���A�����~�j�������t�������̓��N�G�X�g�̏ꍇ
        else if(l_request instanceof WEB3MstkSellInputRequest)
        {
            l_response = this.getInputScreen((WEB3MstkSellInputRequest)l_request);      
        }
        //�����̃��N�G�X�g�f�[�^���A�����~�j�������t�����m�F���N�G�X�g�̏ꍇ
        else if(l_request instanceof WEB3MstkSellConfirmRequest)
        {
            l_response = this.validateOrder((WEB3MstkSellConfirmRequest)l_request); 
        }
        //�����̃��N�G�X�g�f�[�^���A�����~�j�������t�����������N�G�X�g�̏ꍇ
        else if(l_request instanceof WEB3MstkSellCompleteRequest)
        {
            l_response = this.submitOrder((WEB3MstkSellCompleteRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;        
    }
    
    /**
     * �iget���t�ꗗ�j�B<br>
     * <br>
     * �����~�j�������t�ꗗ��ʕ\�����������{����B <br>
     * <br>
     * �V�[�P���X�} <br>
     * �u�i�~�j�����t�T�[�r�X�jget���t�ꗗ�v�Q�ƁB
     * @@param l_request (���N�G�X�g�f�[�^)<BR>
     * �����~�j�������t�ꗗ���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3MstkSellListResponse
     */
    protected WEB3MstkSellListResponse getSellList(WEB3MstkSellListRequest l_request) throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " getSellList(WEB3MstkSellListRequest)";
        log.entering(STR_METHOD_NAME);
        
        //2) validate()
        l_request.validate();
        
        //3) get�⏕����()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //4) validate�~�j������(�⏕����)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
        l_orderManager.validateMiniStockOrder(l_subAccount);
        
        //5) is�~�j������I���x��(���X)
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        boolean l_blnMessageSuspensionFlag = WEB3GentradeTradingTimeManagement.isMiniStockSuspension(l_branch);
        
        //6) createResponse()
        WEB3MstkSellListResponse l_response = (WEB3MstkSellListResponse)l_request.createResponse();
        
        //7) create�����R�[�h����(�����~�j�������t�ꗗ���X�|���X, �⏕����, �����~�j�������t�ꗗ���N�G�X�g)
        this.createProductCodeName(l_response, l_subAccount, l_request);
        
        //8) create���t����(�����~�j�������t�ꗗ���X�|���X, �⏕����, �����~�j�������t�ꗗ���N�G�X�g)
        this.createSellUnit(l_response, l_subAccount, l_request);
        
        //9) �v���p�e�B�Z�b�g
        l_response.messageSuspensionFlag = l_blnMessageSuspensionFlag;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * �iget���͉�ʁj�B<br>
     * <br>
     * �����~�j�������t���͉�ʕ\�����������{����B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�~�j�����t�T�[�r�X�jget���͉�ʁv�Q�ƁB<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�����~�j�������t / �i�~�j�����t�T�[�r�X�jget���͉��): <BR>
     * ���t�\���� == 0�̏ꍇ�iget�~�j���ۗL����() - get�~�j������������()�j�A<BR>
     * ��O���X���[����<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00167<BR>
     * ==========================================================
     * @@param l_request (���N�G�X�g�f�[�^)<BR>
     * �����~�j�������t�������̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3MstkSellInputResponse
     */
    protected WEB3MstkSellInputResponse getInputScreen(WEB3MstkSellInputRequest l_request) throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " getInputScreen(WEB3MstkSellInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1) validate()
        l_request.validate();
        
        //2) get�⏕����()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //3) validate�~�j������(�⏕����)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
        l_orderManager.validateMiniStockOrder(l_subAccount);
        
        //4) validate��������i�~�j���j(�⏕����, String, boolean)
        WEB3EquityTradedProduct l_tradedProduct = 
            l_orderManager.validateMiniStockTradedProduct(l_subAccount, l_request.productCode, true);
            
        //5) getProduct()
        Product l_product = l_tradedProduct.getProduct();
        
        //6) getMarket()
        Market l_market = l_tradedProduct.getMarket();
        
        //7) validate�~�j���d������(�⏕����, �������)
        l_orderManager.validateMiniStockDuplicateOrder(l_subAccount, l_tradedProduct);
        //8) get�~�j���ۗL����(long, long, long)
        WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager)l_tradingMod.getPositionManager();
        double l_dblQuantity = l_positionManager.getMiniStockQuantity(
            l_subAccount.getAccountId(), 
            l_subAccount.getSubAccountId(), 
        l_product.getProductId());
            
        //9) get�~�j������������(long, long, long, boolean)
        double l_dblOrderingQuantity = l_orderManager.getMiniStockOrderingQuantity(
            l_subAccount.getAccountId(), 
            l_subAccount.getSubAccountId(), 
            l_product.getProductId(),
            true);
            
        //10) ���t�\���� == 0�̏ꍇ�iget�~�j���ۗL����() - get�~�j������������()�j�A��O���X���[����
        double l_dblSellQuantity = l_dblQuantity - l_dblOrderingQuantity; 
        if(l_dblSellQuantity == 0)
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00167,STR_METHOD_NAME);
                
        }
        //11)  is�~�j������I���x��(���X)
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        boolean l_blnMessageSuspensionFlag = WEB3GentradeTradingTimeManagement.isMiniStockSuspension(l_branch);

        //12)is�C���T�C�_�[�x���\��(�⏕���� : �⏕����, ����ID : long)
        boolean l_insiderWarningFlag = l_orderManager.isInsiderMessageSuspension(l_subAccount, l_tradedProduct.getProduct().getProductId());
        
        //13) createResponse()
        WEB3MstkSellInputResponse l_response = (WEB3MstkSellInputResponse)l_request.createResponse();
        
        //14) (*2) �v���p�e�B�Z�b�g
        l_response.orderQuantity = WEB3StringTypeUtility.formatNumber(l_dblSellQuantity);
        EqtypeProductRow l_productRow = (EqtypeProductRow) l_product.getDataSourceObject();
        l_response.productName = l_productRow.getStandardName();
        l_response.marketCode = l_market.getMarketCode();
        l_response.messageSuspensionFlag = l_blnMessageSuspensionFlag;
        l_response.insiderWarningFlag = l_insiderWarningFlag;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * �ivalidate�����j�B<BR>
     * <br>
     * �����~�j�������t���������R�������{����B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�~�j�����t�T�[�r�X�jvalidate�����v�Q�ƁB<BR>
     * @@param l_request (���N�G�X�g�f�[�^)
     * �����~�j�������t�����m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3MstkSellConfirmResponse
     */
    protected WEB3MstkSellConfirmResponse validateOrder(WEB3MstkSellConfirmRequest l_request) throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validateOrder(WEB3MstkSellConfirmRequest)";
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
            l_orderManager.validateMiniStockTradedProduct(l_subAccount, l_request.productCode, true);
        
        //5) create�����~�j�����������e(Trader, boolean, String, String, double)
        boolean l_blnIsNumber = WEB3StringTypeUtility.isNumber(l_request.orderQuantity);
        double l_dblOrderQuantity;
        if(!l_blnIsNumber)
        {
            l_dblOrderQuantity = 0;
        }
        else
        {
            l_dblOrderQuantity = Double.parseDouble(l_request.orderQuantity);
        }
        WEB3NewMiniStockOrderSpec l_orderSpec = WEB3NewMiniStockOrderSpec.createNewMiniStockOrderSpec(
            l_trader, 
            false, 
            l_request.productCode, 
            l_tradedProduct.getMarket().getMarketCode(),
            l_dblOrderQuantity);
        //6) validate�~�j�����t����(�⏕����, �����~�j�����������e)
        NewOrderValidationResult l_validationResult = l_orderManager.validateMiniStockSellOrder(l_subAccount, l_orderSpec);
        if(l_validationResult.getProcessingResult().isFailedResult())
        {
            throw new WEB3BaseException(l_validationResult.getProcessingResult().getErrorInfo(), STR_METHOD_NAME);
        }
        
        //7) get���O�C���`���l��()
        String l_strLoginChannel = this.getLoginChannel();
        
        //8) get������()
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //9) �萔��()
        WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
        
        //10)  (*1) �v���p�e�B�Z�b�g
        l_commission.setOrderChannel(l_strLoginChannel);
        l_commission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());
        l_commission.setBranchId(l_subAccount.getWeb3GenBranch().getBranchId());
        l_commission.setOrderBizDate(new Timestamp(l_datBizDate.getTime()));
        l_commission.setSonarTradedCode(WEB3TransactionTypeSONARDef.MARKET_TRADING);
        l_commission.setCommissionProductCode(WEB3CommisionProductCodeDef.MINI_STOCK);
        l_commission.setPayType(WEB3EquityRepaymentDivOtherDef.OTHER);
        
        //11) calc�T�Z��n����i�~�j���j(�萔�� : �萔��, �⏕���� : SubAccount, ������� :
        //    �������, ���� : double, is������ : boolean, �v�Z�P�� : double, is�S���l�� : boolean)
        WEB3EquityEstimatedDeliveryPrice l_estimatedDeliveryPrice = l_orderManager.calcMiniStockEstimatedDeliveryAmount(
            l_commission,
            l_subAccount,
            l_tradedProduct,
            l_dblOrderQuantity,
            true,
            Double.NaN,
            true);

        //14) is�~�j������I���x��(���X)
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        boolean l_blnMessageSuspensionFlag = WEB3GentradeTradingTimeManagement.isMiniStockSuspension(l_branch);
            
        
        //------------
        //�������擾
        //------------
        WEB3EquityProductManager l_productManager = 
            (WEB3EquityProductManager) l_tradingMod.getProductManager();
        WEB3EquityProductQuote l_equityProductQuote = l_productManager.getDisplayEquityProductQuote(l_tradedProduct, l_subAccount);
        String l_strQuoteTypeDiv = l_equityProductQuote.getQuoteTypeDiv();
        double l_dblCurrentPrice = l_equityProductQuote.getQuote();
        double l_dblChange = l_equityProductQuote.getComparedPreviousDay();
        Timestamp l_tsQuoteTime = l_equityProductQuote.getQuoteTime();        
         
        //19) createNewOrderId()
        long l_lngOrderId = l_orderManager.createNewOrderId();
        //20)is�C���T�C�_�[�x���\��(�⏕���� : �⏕����, ����ID : long)
        boolean l_insiderWarningFlag = l_orderManager.isInsiderMessageSuspension(l_subAccount, l_tradedProduct.getProduct().getProductId());
        //21) createResponse()
        WEB3MstkSellConfirmResponse l_response = (WEB3MstkSellConfirmResponse)l_request.createResponse();
        
        //22)  (*3) �v���p�e�B�Z�b�g
        l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_estimatedDeliveryPrice.getEstimateDeliveryAmount());
        l_response.commissionInfo = new WEB3MstkCommissionInfoUnit();
        l_response.commissionInfo.commissionCourse = null;
        l_response.commissionInfo.commission = WEB3StringTypeUtility.formatNumber(l_estimatedDeliveryPrice.getCommissionFee());
        l_response.commissionInfo.commissionConsumptionTax = 
            WEB3StringTypeUtility.formatNumber(l_estimatedDeliveryPrice.getCommissionFeeTax());
        l_response.checkPrice = WEB3StringTypeUtility.formatNumber(l_estimatedDeliveryPrice.getCalcUnitPrice());
        l_response.checkDate = WEB3DateUtility.toDay(l_datBizDate);
        l_response.orderId = "" + l_lngOrderId;
        l_response.messageSuspensionFlag = l_blnMessageSuspensionFlag;
        l_response.currentPriceDiv = l_strQuoteTypeDiv;
        l_response.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);
        l_response.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);
        l_response.currentPriceTime = l_tsQuoteTime;
        l_response.insiderWarningFlag = l_insiderWarningFlag;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * �isubmit�����j�B<BR>
     * <br>
     * �����~�j�������t�����o�^���������{����B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�~�j�����t�T�[�r�X�jsubmit�����v�Q�ƁB
     * @@param l_request (���N�G�X�g�f�[�^)<BR>
     * �����~�j�������t�����������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3MstkSellCompleteResponse
     */
    protected WEB3MstkSellCompleteResponse submitOrder(WEB3MstkSellCompleteRequest l_request) throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " submitOrder(WEB3MstkSellCompleteRequest)";
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
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
        WEB3EquityTradedProduct l_tradedProduct = 
            l_orderManager.validateMiniStockTradedProduct(l_subAccount, l_request.productCode, true);
        
        //5) create�����~�j�����������e(Trader, boolean, String, String, double)
        boolean l_blnIsNumber = WEB3StringTypeUtility.isNumber(l_request.orderQuantity);
        double l_dblOrderQuantity;
        if(!l_blnIsNumber)
        {
            l_dblOrderQuantity = 0;
        }
        else
        {
            l_dblOrderQuantity = Double.parseDouble(l_request.orderQuantity);   
        }
        WEB3NewMiniStockOrderSpec l_orderSpec = WEB3NewMiniStockOrderSpec.createNewMiniStockOrderSpec(
            l_trader, 
            false, 
            l_request.productCode, 
            l_tradedProduct.getMarket().getMarketCode(),
            l_dblOrderQuantity);
        //6) validate�~�j�����t����(�⏕����, �����~�j�����������e)
        NewOrderValidationResult l_validationResult = l_orderManager.validateMiniStockSellOrder(l_subAccount, l_orderSpec);
        if(l_validationResult.getProcessingResult().isFailedResult())
        {
            throw new WEB3BaseException(l_validationResult.getProcessingResult().getErrorInfo(), STR_METHOD_NAME);
        }
        
        //7) get���O�C���`���l��()
        String l_strLoginChannel = this.getLoginChannel();
        
        //8) get������()
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        
        //9) �萔��()
        WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
        
        //10)  (*1) �v���p�e�B�Z�b�g
        l_commission.setOrderChannel(l_strLoginChannel);
        l_commission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());
        l_commission.setBranchId(l_subAccount.getWeb3GenBranch().getBranchId());
        l_commission.setOrderBizDate(new Timestamp(l_datBizDate.getTime()));
        l_commission.setSonarTradedCode(WEB3TransactionTypeSONARDef.MARKET_TRADING);
        l_commission.setCommissionProductCode(WEB3CommisionProductCodeDef.MINI_STOCK);
        l_commission.setPayType(WEB3EquityRepaymentDivOtherDef.OTHER);
        
        //11) calc�T�Z��n����i�~�j���j(�萔�� : �萔��, �⏕���� : SubAccount, ������� : �������, ���� : double, is������ : boolean, �v�Z�P�� : double, is�S���l�� : boolean)
        WEB3EquityEstimatedDeliveryPrice l_estimatedDeliveryPrice = l_orderManager.calcMiniStockEstimatedDeliveryAmount(
            l_commission,
            l_subAccount,
            l_tradedProduct,
            l_dblOrderQuantity,
            true,
            Double.parseDouble(l_request.checkPrice),
            true);
        
        //12) �����~�j���������X�V�C���^�Z�v�^(�����~�j�����������e, �萔��, �T�Z��n����v�Z����)
        WEB3MiniStockOrderUpdateInterceptor l_intercepter = new WEB3MiniStockOrderUpdateInterceptor(
            l_orderSpec, 
            l_commission, 
            l_estimatedDeliveryPrice);

        //14) setThreadLocalPersistenceEventInterceptor(EqTypeOrderManagerPersistenceEventInterceptor)
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_intercepter);
        
        //15) submitNewMiniStockOrder(SubAccount, EqTypeNewMiniStockOrderSpec, long, String, boolean)
        boolean l_blnOrderId = WEB3StringTypeUtility.isInteger(l_request.orderId);
        long l_lngOrderId;
        if(!l_blnOrderId)
        {
            l_lngOrderId = 0;
        }
        else
        {
            l_lngOrderId = Long.parseLong(l_request.orderId);    
        }
        EqTypeOrderSubmissionResult l_orderSubmissionResult = 
            l_orderManager.submitNewMiniStockOrder(l_subAccount, l_orderSpec, l_lngOrderId, l_request.password, true);
        
        if(l_orderSubmissionResult.getProcessingResult().isFailedResult())
        {
            throw new WEB3BaseException(l_orderSubmissionResult.getProcessingResult().getErrorInfo(),STR_METHOD_NAME);
        }

        //16) is�C���T�C�_�[�x���\��(�⏕���� : �⏕����, ����ID : long)
        boolean l_insiderWarningFlag = l_orderManager.isInsiderMessageSuspension(l_subAccount, l_tradedProduct.getProduct().getProductId());
       
        //17) createResponse()
        WEB3MstkSellCompleteResponse l_response = (WEB3MstkSellCompleteResponse)l_request.createResponse();
        
        //18) (*3) �v���p�e�B�Z�b�g
        l_response.lastUpdatedTimestamp = l_finApp.getTradingSystem().getSystemTimestamp();
        l_response.orderActionId = l_request.orderId;
        l_response.insiderWarningFlag = l_insiderWarningFlag;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * �icreate�����R�[�h���́j�B<br>
     * <br>
     * �@@�w������̕ێ�����~�j���ۗL���Y�̖����R�[�h�Ɩ�������<br>
     * �ꗗ���쐬���A���X�|���X�f�[�^�ɃZ�b�g����B <br>
     * �Y���f�[�^�����݂��Ȃ��ꍇ�ɂ�null���Z�b�g����B <br>
     * <br>
     * �V�[�P���X�} <br>
     * �u�i�~�j�����t�T�[�r�X�jcreate�����R�[�h���́v�Q�ƁB
     * @@param l_response (���X�|���X�f�[�^)<br>
     * �����~�j�������t�ꗗ���X�|���X�f�[�^�I�u�W�F�N�g
     * @@param l_subAccount (�⏕����)<br>
     * �⏕�����I�u�W�F�N�g
     * @@param l_request (���N�G�X�g�f�[�^)
     * �����~�j�������t�ꗗ���N�G�X�g�f�[�^�I�u�W�F�N�g
     */
    protected void createProductCodeName(
        WEB3MstkSellListResponse l_response, 
        WEB3GentradeSubAccount l_subAccount, 
        WEB3MstkSellListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createProductCodeName()";
        log.entering(STR_METHOD_NAME);
        
        //2) get�~�j���ۗL���Y�ꗗ(�⏕����, ProductTypeEnum)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager)l_tradingMod.getPositionManager();
        List l_lisAssets = l_positionManager.getMiniStockAssets(l_subAccount, ProductTypeEnum.EQUITY);
        
        //3) HashMap()
        Map l_hashMap = new HashMap();
        List l_listNewAssets = new ArrayList();
        int l_intLength = l_lisAssets.size();
        
        //4) get�ۗL���Y�ꗗ()�̖߂�l�̐���LOOP����
        for(int i = 0; i < l_intLength; i++)
        {
            Asset l_asset = (Asset)l_lisAssets.get(i);
            WEB3EquityProduct l_product = (WEB3EquityProduct)l_asset.getProduct();
            if(!l_hashMap.containsKey("" + l_product.getProductId()))
            {
                //8) �����~�j���������R�[�h����()
                WEB3MstkProductCodeNameUnit l_productCodeNameUnit = new WEB3MstkProductCodeNameUnit();
                //9) (*1.2) �v���p�e�B�Z�b�g
                l_productCodeNameUnit.productCode = l_product.getProductCode();
                EqtypeProductRow l_productRow = (EqtypeProductRow) l_product.getDataSourceObject();
                l_productCodeNameUnit.productName = l_productRow.getStandardName();
                //10) put(arg0�i=�ۗL���Y.�����h�c.toString()�j : Object�i=�����~�j���������R�[�h���̃I�u�W�F�N�g�j, arg1 : Object)
                l_hashMap.put("" + l_product.getProductId(), l_productCodeNameUnit);
                l_listNewAssets.add(l_productCodeNameUnit);
            }  
        }
        if(l_intLength != 0)
        {
        	WEB3MstkProductCodeNameUnit[] l_productCodeNameUnit = new WEB3MstkProductCodeNameUnit[l_listNewAssets.size()];
        	l_listNewAssets.toArray(l_productCodeNameUnit);
        	l_response.productCodeNames = l_productCodeNameUnit;
		}
		else
		{
			l_response.productCodeNames = null;
		}
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �icreate���t���ׁj�B<br>
     * <br>
     * �@@�w������̕ێ�����~�j���ۗL���Y���A���t���ׂ��쐬���A<br>
     * ���X�|���X�f�[�^�ɃZ�b�g����B <br>
     * �Y���f�[�^�����݂��Ȃ��ꍇ�ɂ�null���Z�b�g����B <br>
     * <br>
     * �V�[�P���X�} <br>
     * �u�i�~�j�����t�T�[�r�X�jcreate���t���ׁv�Q�ƁB
     * @@param l_response (���X�|���X�f�[�^)<br>
     * �����~�j�������t�ꗗ���X�|���X�f�[�^�I�u�W�F�N�g
     * @@param l_subAccount (�⏕����)<br>
     * �⏕�����I�u�W�F�N�g
     * @@param l_request (���N�G�X�g�f�[�^)<br>
     * �����~�j�������t�ꗗ���N�G�X�g�f�[�^�I�u�W�F�N�g
     */
    protected void createSellUnit(
        WEB3MstkSellListResponse l_response, 
        WEB3GentradeSubAccount l_subAccount, 
        WEB3MstkSellListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createSellUnit()";
        log.entering(STR_METHOD_NAME);
        
        //2) getInstitution()
        Institution l_institution = l_subAccount.getInstitution();
        
        //3) ArrayList()
        List l_list = new ArrayList();
        int l_intLength = 0;
        if(l_request.productCode != null)
        {
            l_intLength = 1;
        }
        else if(l_response.productCodeNames != null)
        {
            l_intLength = l_response.productCodeNames.length;
        }
        else
        {
			l_intLength = 0;
        }
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            
        WEB3EquityProductManager l_productManager = (WEB3EquityProductManager)l_tradingMod.getProductManager();
        WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager)l_tradingMod.getPositionManager();
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
        
        for(int i = 0; i < l_intLength; i++)
        {
            String l_strProductCode;
            if(l_request.productCode != null)
            {
                l_strProductCode = l_request.productCode;
            }
            else
            {
                
                l_strProductCode = l_response.productCodeNames[i].productCode;
            }
            //5) getProduct(�،���� : Institution, �����R�[�h : String)
            WEB3EquityProduct l_equityProduct;
            try
            {
                l_equityProduct = (WEB3EquityProduct)l_productManager.getProduct(l_institution, l_strProductCode);
            }
            catch(NotFoundException l_ex)
            {
				if(l_request.productCode != null)
				{
                	throw new WEB3BusinessLayerException(
                		WEB3ErrorCatalog.BUSINESS_ERROR_00717,
                		this.getClass().getName() + "." + STR_METHOD_NAME);
				}
				else
				{
					throw new WEB3SystemLayerException(
						WEB3ErrorCatalog.SYSTEM_ERROR_80005,
						this.getClass().getName() + "." + STR_METHOD_NAME);
					
				}
            }
            //6) getProductId()
            long l_lngProductId = l_equityProduct.getProductId();
            //7) getStandardName()
            EqtypeProductRow l_productRow = (EqtypeProductRow) l_equityProduct.getDataSourceObject();
            String l_strStandardName = l_productRow.getStandardName();
            //8) get�~�j���s��()
            Market l_market = l_equityProduct.getMiniStockMarket();
            //9) get�~�j���ۗL����(long, long, long)
            double l_dblMstkQuantity = 
                l_positionManager.getMiniStockQuantity(l_subAccount.getAccountId(), l_subAccount.getSubAccountId(), l_lngProductId);
            
            //10) getTradedProduct(arg0�i=getProduct()�j : Product, arg1�i=get�~�j���s��()�j : Market)
            WEB3EquityTradedProduct l_tradedProduct = null;
            if (l_market != null)
            {
                try
                {
                    l_tradedProduct = 
                        (WEB3EquityTradedProduct)l_productManager.getTradedProduct(l_equityProduct, l_market);
                }
                catch(NotFoundException l_nfe) {}
            }
            
            //14) �����~�j�������t����()
            WEB3MstkSellUnit l_unit = new WEB3MstkSellUnit();
            
            //12) get�~�j������������(long, long, long, boolean)
            double l_dblBuyOrderQuantity = l_orderManager.getMiniStockOrderingQuantity(
                l_subAccount.getAccountId(),
                l_subAccount.getSubAccountId(),
                l_lngProductId,
                false);
                
            //13) get�~�j������������(long, long, long, boolean)
            double l_dblSellOrderQuantity = l_orderManager.getMiniStockOrderingQuantity(
                l_subAccount.getAccountId(),
                l_subAccount.getSubAccountId(),
                l_lngProductId,
                true); 
            
            // �v���p�e�B������
			l_unit.sellPossFlag = true;
			
            //15) �v���p�e�B�Z�b�g
            l_unit.productCode = l_equityProduct.getProductCode();
            l_unit.productName = l_strStandardName;
            if (l_market == null)
            {
				l_unit.marketCode = null;
				l_unit.sellPossFlag = false;
            }
            else
            {
				l_unit.marketCode = l_market.getMarketCode();
            }
            l_unit.balanceQuantity = WEB3StringTypeUtility.formatNumber(l_dblMstkQuantity);
            l_unit.buyOrderedQuantity = WEB3StringTypeUtility.formatNumber(l_dblBuyOrderQuantity);
            l_unit.sellOrderedQuantity = WEB3StringTypeUtility.formatNumber(l_dblSellOrderQuantity);
            
            //11) validate�~�j���d������(�⏕����, �������)
            double l_dblDeledQuantity = 0;
            if (l_tradedProduct != null)
            {
                try
                {
                    l_orderManager.validateMiniStockDuplicateOrder(l_subAccount,l_tradedProduct);
                    l_dblDeledQuantity = l_dblMstkQuantity - l_dblSellOrderQuantity;                
                }
                catch(WEB3BaseException l_ex) {}
            }
            l_unit.sellPossQuantity = WEB3StringTypeUtility.formatNumber(l_dblDeledQuantity);
            if (l_dblDeledQuantity == 0)
            {
				l_unit.sellPossFlag = false;
            }
            
            //16) add(arg0�i=�����~�j�������t���׃I�u�W�F�N�g�j
            l_list.add(l_unit);
        }
        //17) toArray()
        //18) sort(obj�i���t�ꗗList.toArray()�j : Object[], com�i=�����~�j�������t����Comparator[]�j : Comparator[])
        int l_intKeysLength = l_request.sortKeys.length;
        WEB3MstkSellUnitComparator[] l_comparator = new WEB3MstkSellUnitComparator[l_intKeysLength];
        for(int i = 0; i < l_intKeysLength; i++)
        {
            l_comparator[i] = new WEB3MstkSellUnitComparator(l_request.sortKeys[i].ascDesc, l_request.sortKeys[i].keyItem);
        }
        WEB3MstkSellUnit[] l_units = new WEB3MstkSellUnit[l_list.size()];
        l_list.toArray(l_units);
        WEB3ArraysUtility.sort(l_units, l_comparator);
        int l_intPageSize1 = Integer.parseInt(l_request.pageSize);
        int l_intPageIndex1 = Integer.parseInt(l_request.pageIndex);
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(l_units, l_intPageIndex1, l_intPageSize1);
        
        //�v���p�e�B�Z�b�g
        if(l_intLength != 0)
        {
			l_response.sellList = (WEB3MstkSellUnit[])l_pageIndexInfo.getArrayReturned(WEB3MstkSellUnit.class);
        }
        else
        {
			l_response.sellList = null;
        }
        l_response.totalRecords = "" + l_pageIndexInfo.getTotalRecords();
        l_response.totalPages = "" + l_pageIndexInfo.getTotalPages();
        l_response.pageIndex = "" + l_pageIndexInfo.getPageIndex();
        
    }
}
@
