head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.54.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p�������T�[�r�XImpl(WEB3ToSuccMarginCancelServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/09 ���@@�_�O�i���u) �V�K�쐬
                   2006/08/30 �ęԍg(���u) �d�l�ύX���f��165
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.WEB3MarginClientRequestService;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.message.WEB3MarginRepaymentUnit;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.message.WEB3SuccMarginCancelCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCancelCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginCancelConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCancelConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginCancelService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�i�A���j�M�p�������T�[�r�XImpl)<BR>
 * �i�A���j�M�p�������T�[�r�X�����N���X<BR>
 * 
 * @@author ���@@�_�O�i���u) 
 * @@version 1.0
 */
public class WEB3ToSuccMarginCancelServiceImpl extends WEB3MarginClientRequestService 
    implements WEB3ToSuccMarginCancelService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginCancelServiceImpl.class);
    
    /**
     * @@roseuid 436ACF770186
     */
    public WEB3ToSuccMarginCancelServiceImpl() 
    {
     
    }
    
    /**
     * �i�A���j�M�p�������������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate����()�A<BR>
     * submit����()���\�b�h�̂����ꂩ���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 433A2403034B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^.���N�G�X�g�f�[�^��null�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3SuccMarginCancelConfirmRequest)
        {
            l_response =
                this.validateOrder((WEB3SuccMarginCancelConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3SuccMarginCancelCompleteRequest)
        {
            l_response =
                this.submitOrder((WEB3SuccMarginCancelCompleteRequest) l_request);
        }
        else
        {
            log.debug("�p�����[�^.���N�G�X�g�f�[�^�̌^���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate����)<BR>
     * �i�A���j�M�p���������������R�����s��<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�A���j�M�p�������T�[�r�X�jvalidate�����v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �i�A���j�M�p�����������m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3SuccMarginCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 433A2D9501A5
     */
    protected WEB3SuccMarginCancelConfirmResponse validateOrder(WEB3SuccMarginCancelConfirmRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateOrder(WEB3SuccMarginCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        //1.2 get�⏕����( )
        WEB3GentradeSubAccount l_gentradeSubAccount = getSubAccount();
        //1.3 get�����\�񒍕��P��(long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnitImpl = null;
        try
        {
            long l_lngOrderId = Long.parseLong(l_request.id);
            l_orderUnitImpl = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
        }
        catch (NotFoundException l_ne)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ne.getMessage(),
                l_ne);
        }
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow) l_orderUnitImpl.getDataSourceObject();
        
        //1.4 validate�M�p�������(�⏕����, �����\�񒍕��P��Impl)
        l_toOrderManager.validateMarginCancelOrder(l_gentradeSubAccount, l_orderUnitImpl);
        //1.5 create��������ByOrder(�����\�񒍕��P��Impl)
        WEB3MarginContractUnit[] l_marginContractUnits = l_toOrderManager.createContractUnitByOrder(l_orderUnitImpl);
        //1.6 get�������( )
        EqTypeTradedProduct l_tradeProduct = (EqTypeTradedProduct) l_orderUnitImpl.getTradedProduct();
        //1.7 get�\���p�������(������� : EqTypeTradedProduct, �⏕���� : �⏕����)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityProductManager l_productMgr = 
            (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
        WEB3EquityProductQuote l_equityProductQuote =
            l_productMgr.getDisplayEquityProductQuote(l_tradeProduct, l_gentradeSubAccount);

        //1.8 get�����敪( )
        String l_strQuoteTypeDiv = l_equityProductQuote.getQuoteTypeDiv();
        //1.9 get����( )
        double l_dblQuote = l_equityProductQuote.getQuote();
        //1.10 get�O����( )
        double l_dblComparePreviousDay = l_equityProductQuote.getComparedPreviousDay();
        //1.11 get�������\����( )
        Timestamp l_tsQuoteTime = l_equityProductQuote.getQuoteTime();
        //1.12 get����( )
        EqTypeProduct l_product = (EqTypeProduct) l_orderUnitImpl.getProduct();
        //1.13 get�s��ǌx���s��(���X : ���X, �����^�C�v : ProductTypeEnum, �M�p����敪 : String)
        String[] l_tradeCloseMarkets = WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
            l_gentradeSubAccount.getWeb3GenBranch(),
            ProductTypeEnum.EQUITY,
            l_rsvEqOrderUnitRow.getRepaymentType());
        
        //1.14 createResponse( )        
        WEB3SuccMarginCancelConfirmResponse l_response =
            (WEB3SuccMarginCancelConfirmResponse) l_request.createResponse();
        //(*)���X�|���X�f�[�^�Ɉȉ��̒ʂ�v���p�e�B���Z�b�g����B
        //�m�F��������      ���@@������ԊǗ�.get������()
        l_response.checkDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        //�T�Z��n���      ���@@�\�񒍕��P��.�T�Z��n���
        l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_rsvEqOrderUnitRow.getEstimatedPrice());
        //����I���x���s��R�[�h�ꗗ   ���@@get�s��ǌx���s��()�̖߂�l
        l_response.messageSuspension = l_tradeCloseMarkets;
        //�����R�[�h           ���@@get����()�̖߂�l.�����R�[�h
        l_response.productCode = l_product.getProductCode();
        //������         ���@@get����()�̖߂�l.getDataSourceObject().getStandardName()
        EqtypeProductRow l_productRow = (EqtypeProductRow) l_product.getDataSourceObject();
        l_response.productName = l_productRow.getStandardName();
        
        try
        {
            //�s��R�[�h           ���@@�\�񒍕��P��.get�s��().�s��R�[�h
            l_response.marketCode = l_orderUnitImpl.getMarket().getMarketCode();
        }
        catch (NotFoundException l_ne)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ne.getMessage(),
                l_ne);
        }
        
        //�����敪�F�@@�����f�[�^�A�_�v�^.get�����敪(�\�񒍕��P��.getTaxType())
        l_response.taxType = WEB3EquityDataAdapter.getTaxType(l_orderUnitImpl.getTaxType());
        //����敪            ���@@�\�񒍕��P��.get���b�Z�[�W�p����敪()
        l_response.tradingType = l_orderUnitImpl.getMsgTradingType();
        
        //�ٍ�          ���@@�M�p����ٍσI�u�W�F�N�g
        WEB3MarginRepaymentUnit l_repaymentUnit = new WEB3MarginRepaymentUnit();
        //�M�p����ٍσC���X�^���X�𐶐����A�ȉ��̃v���p�e�B���Z�b�g�B
        //�@@�ٍϋ敪   ���@@�\�񒍕��P��.�ٍϋ敪
        //�@@�ٍϊ����l  ���@@�\�񒍕��P��.�ٍϊ����l        
        l_repaymentUnit.repaymentDiv = l_rsvEqOrderUnitRow.getRepaymentType();
        l_repaymentUnit.repaymentTimeLimit = String.valueOf(l_rsvEqOrderUnitRow.getRepaymentNum());
        l_response.repayment = l_repaymentUnit;
        
        //��������            ���@@�\�񒍕��P��.��������
        l_response.orderQuantity = WEB3StringTypeUtility.formatNumber(l_rsvEqOrderUnitRow.getQuantity());
        //���o������       ���@@null
        l_response.partContQuantity = null;
        //�����P���敪      ���@@�\�񒍕��P��.get���b�Z�[�W�p�����P���敪()
        l_response.orderPriceDiv = l_orderUnitImpl.getMsgOrderPriceDiv();
        //�����P��            ���@@�\�񒍕��P��.get���b�Z�[�W�p�����P��()
        l_response.limitPrice  = l_orderUnitImpl.getMsgLimitPrice();              
        //���Ϗ����敪      ���@@�\�񒍕��P��.���Ϗ����敪
        l_response.closingOrder = l_rsvEqOrderUnitRow.getClosingOrderType();
        //�������׈ꗗ      ���@@create��������ByOrder()�̖߂�l
        l_response.contractUnits = l_marginContractUnits;
        //�����挻�n�������敪  ���@@�\�񒍕��P��.get���b�Z�[�W�p�����挻�n�������敪()
        l_response.swapTaxType = l_orderUnitImpl.getMsgSwapTaxType();
        //�l�i����            ���@@�\�񒍕��P��.get���b�Z�[�W�p�l�i����()
        l_response.priceCondType = l_orderUnitImpl.getMsgPriceCondType();
        //���s����            ���@@�\�񒍕��P��.get���b�Z�[�W�p���s����()
        l_response.execCondType = l_orderUnitImpl.getMsgExecCondType();
        //���������敪      ���@@�\�񒍕��P��.get���b�Z�[�W�p���������敪()
        l_response.expirationDateType = l_orderUnitImpl.getMsgExpirationDateType();
        //�����L������      ���@@�\�񒍕��P��.get���b�Z�[�W�p�����L������()
        l_response.expirationDate = WEB3DateUtility.toDay(l_orderUnitImpl.getMsgExpirationDate());
        //���������敪      ���@@�\�񒍕��P��.get���b�Z�[�W�p���������敪()
        l_response.orderCondType = l_orderUnitImpl.getMsgOrderCondType();
        //�t�w�l�p���������P��  ���@@null
        l_response.stopOrderCondPrice = null;
        //�t�w�l�p�����������Z�q ���@@null
        l_response.stopOrderCondOperator = null;
        //W�w�l�p���������P��  ���@@null
        l_response.wlimitOrderCondPrice = null;
        //W�w�l�p�����������Z�q ���@@null
        l_response.wlimitOrderCondOperator = null;
        //W�w�l�p�����P���敪  ���@@null
        l_response.wLimitOrderPriceDiv = null;
        //W�w�l�p�����P��        ���@@null
        l_response.wLimitPrice = null;
        //�����敪            ���@@get�����敪()�̖߂�l
        l_response.currentPriceDiv = l_strQuoteTypeDiv;
        //�����i���ݒl�j     ���@@get����()�̖߂�l
        l_response.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblQuote);
        //�O����         ���@@get�O����()�̖߂�l
        l_response.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblComparePreviousDay);
        //�������(�������\����)    ���@@get�������\����()�̖߂�l
        l_response.currentPriceTime = l_tsQuoteTime;
        //�P�������l���     ���@@�\�񒍕��P��.create�P�������l���()
        l_response.priceAdjustmentValueInfo = l_orderUnitImpl.createSuccPriceAdjustmentValueInfo();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit����)<BR>
     * �i�A���j�M�p�����������������s��<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�A���j�M�p�������T�[�r�X�jsubmit�����v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �i�A���j�M�p�����������������N�G�X�g�I�u�W�F�N�g
     * @@return WEB3SuccMarginCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 433A2D9501C5
     */
    protected WEB3SuccMarginCancelCompleteResponse submitOrder(WEB3SuccMarginCancelCompleteRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitOrder(WEB3SuccMarginCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();

        //1.2 get������(�m�F�������� : Date)
        WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        
        //1.3 get�⏕����( )
        WEB3GentradeSubAccount l_gentradeSubAccount = getSubAccount();
        
        //1.4 get�����\�񒍕��P��(long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnitImpl = null;
        try
        {
            long l_lngOrderId = Long.parseLong(l_request.id);
            l_orderUnitImpl = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
        }
        catch (NotFoundException l_ne)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ne.getMessage(),
                l_ne);
        }

        //1.5 validate�M�p�������(�⏕����, �����\�񒍕��P��Impl)
        l_toOrderManager.validateMarginCancelOrder(l_gentradeSubAccount, l_orderUnitImpl);
        //1.6 submit�\�񒍕����(SubAccount, �����\�񒍕��P��Impl, String)
        l_toOrderManager.submitEqtypeCancelOrder(l_gentradeSubAccount, l_orderUnitImpl, l_request.password);
        //1.7 createResponse()        
        WEB3SuccMarginCancelCompleteResponse l_response =
            (WEB3SuccMarginCancelCompleteResponse) l_request.createResponse();
        //(*)���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        //�X�V����    ���@@���ݓ����i��GtlUtils.getSystemTimestamp()�j
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
        //���ʔԍ�    ���@@���N�G�X�g.ID�i���\�񒍕��P��.����ID�j
        l_response.orderActionId = String.valueOf(l_orderUnitImpl.getOrderId());
        //�A�������ݒ�t���O�@@���@@false�i�Œ�j
        l_response.succSettingFlag = false;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
