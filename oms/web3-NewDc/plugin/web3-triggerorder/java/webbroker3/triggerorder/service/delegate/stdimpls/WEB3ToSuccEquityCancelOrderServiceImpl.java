head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.50.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityCancelOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j������������T�[�r�XImpl(WEB3ToSuccEquityCancelOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/7  鰊](���u) �V�K�쐬
Revesion History : 2006/08/30 �ęԍg(���u) �d�l�ύX���f��165
Revesion History : 2007/02/09 ��іQ(���u) ���f�� No.230
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityClientRequestService;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.message.WEB3SuccEquityCancelCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityCancelCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccEquityCancelConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityCancelConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityCancelOrderService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�i�A���j������������T�[�r�XImpl)<BR>
 * �i�A���j������������T�[�r�X�����N���X�B<BR>
 * 
 * @@ author 鰊] <BR>
 * @@ version 1.0 <BR>
 */
public class WEB3ToSuccEquityCancelOrderServiceImpl extends WEB3EquityClientRequestService implements WEB3ToSuccEquityCancelOrderService 
{

    /**
     * (���O�o�̓��[�e�B���e�B)�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccEquityCancelOrderServiceImpl.class); 
    
    /**
     * @@roseuid 436ACF7102EE
     */
    public WEB3ToSuccEquityCancelOrderServiceImpl() 
    {
     
    }
    
    /**
     * �i�A���j��������������������s����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate�������()�܂���submit�������()<BR>
     * ���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^�B<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 433A06D60358
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws  WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3SuccEquityCancelConfirmRequest)
        {
            l_response = this.validateCancelOrder((WEB3SuccEquityCancelConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3SuccEquityCancelCompleteRequest)
        {
            l_response = this.submitCancelOrder((WEB3SuccEquityCancelCompleteRequest) l_request);
        }
        else
        {
            log.debug(STR_METHOD_NAME + "�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);

        return l_response;

    }
    
    /**
     * (validate�������)<BR>
     * �i�A���j����������������m�F���������{����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�i�A���j������������T�[�r�X�jvalidate��������v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * 
     * @@return WEB3SuccEquityCancelConfirmResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4340BE1002B4
     */
    protected WEB3SuccEquityCancelConfirmResponse 
        validateCancelOrder(WEB3SuccEquityCancelConfirmRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateCancelOrder(WEB3SuccEquityCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {   
            log.debug("parameter is null");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + "." + STR_METHOD_NAME);                
        }

        // 1.1 validate()
        l_request.validate();
        
        // 1.2 get�⏕����( )
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        // 1.3 get�����\�񒍕��P��(long)
        WEB3ToSuccOrderManagerImpl l_managerImpl =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnitImpl = null;
        try
        {
            l_orderUnitImpl = l_managerImpl.getReserveEqtypeOrderUnit(Long.parseLong(l_request.id));
        }
        catch (NotFoundException l_nfe)
        {
            log.error(WEB3ErrorCatalog.SYSTEM_ERROR_80005.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80005,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               l_nfe.getMessage(),
               l_nfe);
        }                                    
        
        // 1.4 validate�����������(�⏕����, �����\�񒍕��P��Impl)
        l_managerImpl.validateEqtypeCancelOrder(l_subAccount, l_orderUnitImpl);
        
        // 1.5 get�������( )
        TradedProduct l_tradedProduct = l_orderUnitImpl.getTradedProduct();
        
        // 1.6 get�\���p�������(������� : EqTypeTradedProduct, �⏕���� : �⏕����)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();                        
        
        WEB3EquityProductQuote l_productQuote =
            l_productManager.getDisplayEquityProductQuote(
                (EqTypeTradedProduct) l_tradedProduct,
                l_subAccount);
                
        // 1.7  get�����敪( )        
        String l_strQuoteTypeDiv = l_productQuote.getQuoteTypeDiv();
        
        // 1.8 get����( )
        double l_dblQuote = l_productQuote.getQuote();
        if (Double.isNaN(l_dblQuote))
        {
            l_dblQuote = 0;
        }
                
        // 1.9 get�O����( )
        double l_dblComparedPreviousDay = l_productQuote.getComparedPreviousDay();

        // 1.10 get�������\����( )
        Timestamp l_quoteTime = l_productQuote.getQuoteTime();

        // 1.11 get����( )
        WEB3EquityProduct l_product = (WEB3EquityProduct)l_orderUnitImpl.getProduct();
        if (l_product == null)
        {
            log.debug("�����I�u�W�F�N�g�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�����I�u�W�F�N�g�����݂��Ȃ��B");
        }
        
        // 1.12  get�T�Z�뉿�P��( )
        String l_strEstimatedBookPrice = l_orderUnitImpl.getEstimatedBookPrice();
        
        // 1.13 get�s��ǌx���s��(���X : ���X, �����^�C�v : ProductTypeEnum, �M�p����敪 : String)
        String[] l_tradeOpenMarkets = null;
        l_tradeOpenMarkets =
            WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
                l_subAccount.getWeb3GenBranch(),
                ProductTypeEnum.EQUITY,
                WEB3MarginTradingDivDef.DEFAULT);
                
        // 1.14 createResponse( )
        WEB3SuccEquityCancelConfirmResponse l_response =(WEB3SuccEquityCancelConfirmResponse)l_request.createResponse( );
        
        EqtypeProductRow l_row = (EqtypeProductRow)l_product.getDataSourceObject();
        // �m�F��������       ���@@������ԊǗ�.get������()
        l_response.checkDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        // �T�Z��n���       ���@@�\�񒍕��P��.�T�Z��n���
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow)l_orderUnitImpl.getDataSourceObject();
        l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_rsvEqOrderUnitRow.getEstimatedPrice());
        // ����I���x���s��R�[�h�ꗗ    ���@@get�s��ǌx���s��()�̖߂�l
        l_response.messageSuspension = l_tradeOpenMarkets;       
        // �����R�[�h            ���@@get����()�̖߂�l.�����R�[�h
        l_response.productCode = l_product.getProductCode();
        // ������          ���@@get����()�̖߂�l.getDataSourceObject().getStandardName()
        l_response.productName = l_row.getStandardName();
        // �s��R�[�h            ���@@�\�񒍕��P��.get�s��().�s��R�[�h
        WEB3GentradeMarket l_gentradeMarket = null;
        try
        {
            l_gentradeMarket = l_orderUnitImpl.getMarket();
        }
        catch (NotFoundException l_nfe)
        {
            log.error(WEB3ErrorCatalog.SYSTEM_ERROR_80005.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80005,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               l_nfe.getMessage(),
               l_nfe);
        }                                            
        if (l_gentradeMarket == null)
        {
            l_response.marketCode = null;
        }
        else
        {
            l_response.marketCode = l_gentradeMarket.getMarketCode();
        }        
        //�����敪�F�@@�����f�[�^�A�_�v�^.get�����敪(�\�񒍕��P��.getTaxType())
        l_response.taxType = 
            WEB3EquityDataAdapter.getTaxType(l_orderUnitImpl.getTaxType());
        // ����敪         ���@@�\�񒍕��P��.get���b�Z�[�W�p����敪()
        l_response.tradingType = l_orderUnitImpl.getMsgTradingType();
        // ��������         ���@@�\�񒍕��P��.��������
        l_response.orderQuantity = WEB3StringTypeUtility.formatNumber(l_orderUnitImpl.getQuantity());
        // ���o������        ���@@null
        l_response.partContQuantity = null;
        // �����P���敪       ���@@�\�񒍕��P��.get���b�Z�[�W�p�����P���敪()
        l_response.orderPriceDiv = l_orderUnitImpl.getMsgOrderPriceDiv();
        // �����P��         ���@@�\�񒍕��P��.get���b�Z�[�W�p�����P��()
        l_response.limitPrice = l_orderUnitImpl.getMsgLimitPrice();
        // �T�Z�뉿�P��       ���@@get�T�Z�뉿�P��()
        l_response.estimatedBookPrice = l_strEstimatedBookPrice;
        // �l�i����         ���@@�\�񒍕��P��.get���b�Z�[�W�p�l�i����()
        l_response.priceCondType = l_orderUnitImpl.getMsgPriceCondType();
        // ���s����         ���@@�\�񒍕��P��.get���b�Z�[�W�p���s����()
        l_response.execCondType = l_orderUnitImpl.getMsgExecCondType();
        // ���������敪       ���@@�\�񒍕��P��.get���b�Z�[�W�p���������敪()
        l_response.expirationDateType = l_orderUnitImpl.getMsgExpirationDateType();
        // �����L������       ���@@�\�񒍕��P��.get���b�Z�[�W�p�����L������()
        l_response.expirationDate = l_orderUnitImpl.getMsgExpirationDate();
        // ���������敪       ���@@�\�񒍕��P��.get���b�Z�[�W�p���������敪()
        l_response.orderCondType = l_orderUnitImpl.getMsgOrderCondType();
        // �t�w�l�p���������P��   ���@@null
        l_response.stopOrderCondPrice = null;
        // �t�w�l�p�����������Z�q  ���@@null
        l_response.stopOrderCondOperator = null;
        // W�w�l�p���������P��   ���@@null
        l_response.wlimitOrderCondPrice = null;
        // W�w�l�p�����������Z�q  ���@@null
        l_response.wlimitOrderCondOperator = null;
        // W�w�l�p�����P���敪   ���@@null
        l_response.wLimitOrderPriceDiv = null;
        // W�w�l�p�����P��     ���@@null
        l_response.wLimitPrice = null;
        // �����敪         ���@@get�����敪()�̖߂�l
        l_response.currentPriceDiv = l_strQuoteTypeDiv;
        // �����i���ݒl�j      ���@@get����()�̖߂�l
        l_response.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblQuote);
        // �O����          ���@@get�O����()�̖߂�l
        l_response.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblComparedPreviousDay);
        // �������(�������\����) ���@@get�������\����()�̖߂�l
        l_response.currentPriceTime = l_quoteTime;
        // �P�������l���      ���@@�\�񒍕��P��.create�P�������l���()
        l_response.priceAdjustmentValueInfo = l_orderUnitImpl.createSuccPriceAdjustmentValueInfo();
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;        
    }
    
    /**
     * (submit�������)<BR>
     * �i�A���j����������������������������{����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�i�A���j������������T�[�r�X�jsubmit��������v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * 
     * @@return WEB3SuccEquityCancelCompleteResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4340BE100341
     */
    protected WEB3SuccEquityCancelCompleteResponse 
        submitCancelOrder(WEB3SuccEquityCancelCompleteRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitCancelOrder(WEB3SuccEquityCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {   
            log.debug("parameter is null");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + "." + STR_METHOD_NAME);              
        }
        
        // 1.1 validate()
        l_request.validate();
        
        // 1.2 get������(�m�F�������� : Date)
        WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        
        // 1.3 get�⏕����( )
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
 
        // 1.4 get�����\�񒍕��P��(long)
        WEB3ToSuccOrderManagerImpl l_managerImpl =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnitImpl = null;
        try
        {
            l_orderUnitImpl = l_managerImpl.getReserveEqtypeOrderUnit(Long.parseLong(l_request.id));
        }
        catch (NotFoundException l_nfe)
        {
            log.error(WEB3ErrorCatalog.SYSTEM_ERROR_80005.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80005,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               l_nfe.getMessage(),
               l_nfe);
        }
        
        // 1.5 validate�����������(�⏕����, �����\�񒍕��P��Impl)
        l_managerImpl.validateEqtypeCancelOrder(l_subAccount, l_orderUnitImpl);
        
        // 1.6 submit�\�񒍕����(SubAccount, �����\�񒍕��P��Impl, String)
        l_managerImpl.submitEqtypeCancelOrder(l_subAccount, l_orderUnitImpl, l_request.password);
        
        // 1.7 createResponse( )
        WEB3SuccEquityCancelCompleteResponse l_response =(WEB3SuccEquityCancelCompleteResponse)l_request.createResponse( );
        // �X�V���� ���@@���ݓ����i��GtlUtils.getSystemTimestamp()�j
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
        // ���ʔԍ� ���@@���N�G�X�g.ID�i���\�񒍕��P��.����ID�j
        l_response.orderActionId = l_request.id;
        // �A�������ݒ�t���O�@@���@@false�i�Œ�j
        l_response.succSettingFlag = false;
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;        
    }
}
@
