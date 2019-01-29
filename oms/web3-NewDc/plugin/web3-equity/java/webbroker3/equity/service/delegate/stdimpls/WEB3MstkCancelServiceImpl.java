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
filename	WEB3MstkCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j������������T�[�r�XImpl(WEB3MstkCancelServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 �J�N���V (���u) �V�K�쐬
*/

package webbroker3.equity.service.delegate.stdimpls;


import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityCancelOrderSpec;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3MiniClientRequestService;
import webbroker3.equity.WEB3MiniStockCancelUpdateInterceptor;
import webbroker3.equity.message.WEB3MstkCancelCompleteRequest;
import webbroker3.equity.message.WEB3MstkCancelCompleteResponse;
import webbroker3.equity.message.WEB3MstkCancelConfirmRequest;
import webbroker3.equity.message.WEB3MstkCancelConfirmResponse;
import webbroker3.equity.service.delegate.WEB3MstkCancelService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i�����~�j������������T�[�r�XImpl�j�B<BR>
 * <BR>
 * �����~�j������������T�[�r�X�����N���X
 * @@author �J�N���V
 * @@version 1.0
 */
public class WEB3MstkCancelServiceImpl extends WEB3MiniClientRequestService implements WEB3MstkCancelService 
{
    
    /**
     * �i���O�o�̓��[�e�B���e�B�j�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkCancelServiceImpl.class);    

    /**
     * 
     */
    public WEB3MstkCancelServiceImpl() 
    {
     
    }

    /**
     * �iexecute�j�B<BR>
     * <BR>
     * �����~�j��������������������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�����~�j������������m�F���N�G�X�g��<BR>
     * �ꍇ <BR>
     * �@@�|validate����()���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�����~�j������������������N�G�X�g��<BR>
     * �ꍇ <BR>
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
        
        //�����̃��N�G�X�g�f�[�^���A�����~�j������������m�F���N�G�X�g�̏ꍇ 
        if(l_request instanceof WEB3MstkCancelConfirmRequest)
        {
            
            l_response = this.validateOrder((WEB3MstkCancelConfirmRequest)l_request);
            
        } 
        
        //�����̃��N�G�X�g�f�[�^���A�����~�j������������������N�G�X�g�̏ꍇ 
        else if(l_request instanceof WEB3MstkCancelCompleteRequest)
        {
            
            l_response = this.submitOrder((WEB3MstkCancelCompleteRequest)l_request);   
            
        }    
        else
        {
            
            log.error(STR_METHOD_NAME + "�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
        
    }
    
    /**
     * �ivalidate�����j�B<BR>
     * <BR>
     * �~�j��������������R�����s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�~�j������T�[�r�X�jvalidate�����v�Q�ƁB
     * @@param l_request (���N�G�X�g�f�[�^)<BR>
     * �����~�j������������m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3MstkCancelConfirmResponse
     */
    protected WEB3MstkCancelConfirmResponse validateOrder(WEB3MstkCancelConfirmRequest l_request) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = " validateOrder(WEB3MstkCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //2) validate()
        l_request.validate();
        
        //3) get�⏕����()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //4)CancelOrderSpec(long)
        //arg0(����ID)�F ���N�G�X�g�f�[�^.����ID
        String l_strOrderId = l_request.id;
        long l_lngOrderId = Long.parseLong(l_strOrderId);

        WEB3EquityCancelOrderSpec l_orderSpec = new WEB3EquityCancelOrderSpec(l_lngOrderId, this.getTrader());
        
        //5)validate�~�j���������(�⏕����, CancelOrderSpec)
        //�⏕�����F�@@�⏕���� 
        //����������e�F�@@����������e 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);                       
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
        
        OrderValidationResult l_orderValidationResult = l_orderManager.validateMiniStockCancelOrder(
            l_subAccount,
            l_orderSpec);
        if(l_orderValidationResult.getProcessingResult().isFailedResult())
        {
           
            throw new WEB3BaseException(l_orderValidationResult.getProcessingResult().getErrorInfo(),STR_METHOD_NAME);
           
        }        
        
        //6)is�~�j������I���x��(���X)
        //�⏕����.get����X()
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        boolean l_blnMessageSuspensionFlag = WEB3GentradeTradingTimeManagement.isMiniStockSuspension(l_branch);
        
        //7)getOrderUnits(long)
        //����ID�F�@@���N�G�X�g�f�[�^.ID
        OrderUnit l_orderUnit = l_orderManager.getOrderUnits(l_lngOrderId)[0];
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        EqtypeOrderUnitParams l_orderUnitParams = new EqtypeOrderUnitParams(l_orderUnitRow);
        
        //8)getTradedProduct()
        TradedProduct l_tradedProduct = null;
        try
        {
            l_tradedProduct = l_orderUnit.getTradedProduct();
        }
        catch (RuntimeSystemException l_rse)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01966,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����P��ID=[" + Long.toString(l_orderUnit.getOrderUnitId()) + "]�̒����P�ʂɕR�t�������������",
                l_rse);
        }
        
        //9)getProduct()
        Product l_product = l_tradedProduct.getProduct();
                       
        //10)getMarket()
        Market l_market = l_tradedProduct.getMarket();
        
        //11)getSide() 
        SideEnum l_sideEnum = l_orderUnit.getSide();
                
        //12)getQuantity()
        double l_orderQuantity = l_orderUnit.getQuantity();
        
                
        //13)createResponse()
        WEB3MstkCancelConfirmResponse l_response = (WEB3MstkCancelConfirmResponse)l_request.createResponse();
        
        //14) �v���p�e�B�Z�b�g
        //14.1) �������F�@@�������.getProduct().getStandardName()
        EqtypeProductRow l_productRow = (EqtypeProductRow) l_product.getDataSourceObject();
        l_response.productName = l_productRow.getStandardName();
        
        //14.2) �s��R�[�h�F�@@�������.getMarket().getMarketCode()
        l_response.marketCode = l_market.getMarketCode();
        
        //14.3)�����敪:
        //�i�����P��.getSide() == SideEnum.BUY�j�̏ꍇ�A����
        if ((SideEnum.BUY).equals(l_sideEnum))
        {
            
            l_response.dealingType = "" + SideEnum.BUY.intValue();
            
        }
        //�i�����P��.getSide() == SideEnum.SELL�j�̏ꍇ�A����
        if ((SideEnum.SELL).equals(l_sideEnum))
        {
            
            l_response.dealingType = "" + SideEnum.SELL.intValue();
            
        }        
        //14.4) ���������F�@@�����P��.getQuantity()
        l_response.orderQuantity = WEB3StringTypeUtility.formatNumber(l_orderQuantity);
        
        //14.5)�m�F���������F�@@�����P��.������
        Date l_orderBizDateDate = WEB3DateUtility.getDate(l_orderUnitParams.getBizDate(), "yyyyMMdd");
        l_response.checkDate = WEB3DateUtility.toDay(l_orderBizDateDate);
        
        //14.6)����I���x�� 
        l_response.messageSuspensionFlag = l_blnMessageSuspensionFlag;
        log.debug("����I���x��" + l_response.messageSuspensionFlag);
        //14.7)�����R�[�h�F�@@�������.getProduct().getProductCode()
        l_response.productCode = ((WEB3EquityProduct)l_product).getProductCode();
        
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
        
    }
    
    /**
     * �isubmit�����j�B<BR>
     * <BR>
     * �~�j����������o�^���s���B<BR> 
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�~�j������T�[�r�X�jsubmit�����v�Q�ƁB
     * @@param l_request (���N�G�X�g�f�[�^)<BR>
     * �����~�j������������������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3MstkCancelCompleteResponse
     */
    protected WEB3MstkCancelCompleteResponse submitOrder(WEB3MstkCancelCompleteRequest l_request) throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " submitOrder(WEB3MstkCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //2) validate()
        l_request.validate();
        
        //3) get�⏕����()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //4)CancelOrderSpec(long)
        //arg0�i����ID�j�F�@@���N�G�X�g�f�[�^.����ID 
        String l_strOrderId = l_request.id;
        log.debug(l_strOrderId);
        long l_lngOrderId = Long.parseLong(l_strOrderId);

        WEB3EquityCancelOrderSpec l_orderSpec = new WEB3EquityCancelOrderSpec(l_lngOrderId, this.getTrader());
        
        //5)validate�~�j���������(�⏕����, CancelOrderSpec)
        //�⏕�����F�@@�⏕���� 
        //����������e�F�@@����������e
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);        
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
        
        OrderValidationResult l_orderValidationResult = l_orderManager.validateMiniStockCancelOrder(
            l_subAccount,
            l_orderSpec);
            
        if(l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            
            throw new WEB3BaseException(l_orderValidationResult.getProcessingResult().getErrorInfo(),STR_METHOD_NAME);
           
        } 
		//) get������()
		Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
         
        //6)�����~�j��������X�V�C���^�Z�v�^()
            
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
        String l_strOrderRootDiv = l_opLoginSec
            .getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV);
        WEB3MiniStockCancelUpdateInterceptor l_interceptor
            = new WEB3MiniStockCancelUpdateInterceptor(l_strOrderRootDiv, (WEB3GentradeTrader) this.getTrader());
        
        //7)setThreadLocalPersistenceEventInterceptor(EqTypeOrderManagerPersistenceEventInterceptor)
        //[setThreadLocalPersistenceInterceptor()�Ɏw�肷�����] 
        //arg0�F�@@�i�������������~�j��������X�V�C���^�Z�v�^�I�u�W�F�N�g�j
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);
        
        //8)submitCancelOrder(SubAccount, EqTypeCancelOrderSpec, �_���r���[::java::lang::String, boolean) 
        //arg0�i�⏕�����j�F�@@�⏕���� 
        //arg1�i����������e�j�F�@@����������e 
        //arg2�i����p�X���[�h�j�F�@@���N�G�X�g�f�[�^.�Ïؔԍ� 
        //arg3�iisSkip�����R���j�F�@@true 
        OrderSubmissionResult l_result = l_orderManager.submitCancelOrder(
            l_subAccount,
            (CancelOrderSpec)l_orderSpec,
            l_request.password,
            true);
        
        if (l_result.getProcessingResult().isFailedResult())
        {
            
            throw new WEB3BaseException(l_result.getProcessingResult().getErrorInfo(), STR_METHOD_NAME);
        }

        //9)�]�͍Čv�Z
        log.debug("�]�͍Čv�Z���s��");
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);
        l_tpTradingPowerService.reCalcTradingPower(this.getSubAccount());
        
        //10)createResponse()
        WEB3MstkCancelCompleteResponse l_response = (WEB3MstkCancelCompleteResponse)l_request.createResponse();
        
        //11) �v���p�e�B�Z�b�g
        //11.1)�X�V���ԁF�@@TradingSystem.getSystemTimestamp()
        l_response.lastUpdatedTimestamp = l_finApp.getTradingSystem().getSystemTimestamp();
        
        //���ʔԍ�11.2)�F�@@���N�G�X�g�f�[�^.����ID
        l_response.orderActionId = l_request.id;
        
        log.exiting(STR_METHOD_NAME);  
        return l_response;
        
    }
}
@
