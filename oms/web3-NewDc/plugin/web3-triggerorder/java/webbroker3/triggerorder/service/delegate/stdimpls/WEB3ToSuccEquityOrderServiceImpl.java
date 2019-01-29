head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.52.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j���������T�[�r�XImpl(WEB3ToSuccEquityOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/14 �A�C��(���u) �V�K�쐬
Revesion History : 2007/01/12 ���G��(���u) ���f��216
Revesion History : 2007/01/17 ���G��(���u) ���f��222
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeAsset;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewCashBasedOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityNewCashBasedOrderSpec;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3EquityTradingModule;
import webbroker3.equity.service.delegate.WEB3EquityOrderRequestAdapter;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderServiceImpl;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccCommonInfo;
import webbroker3.triggerorder.message.WEB3SuccEquityBuyCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityBuyConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityBuyConfirmResponse;
import webbroker3.triggerorder.message.WEB3SuccEquitySellCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccEquitySellConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccEquitySellConfirmResponse;
import webbroker3.triggerorder.message.WEB3SuccPriceAdjustmentValueInfo;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityOrderRequestAdapter;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityOrderService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�i�A���j���������T�[�r�XImpl)<BR>
 * �i�A���j���������T�[�r�X�����N���X�B
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3ToSuccEquityOrderServiceImpl extends WEB3EquityOrderServiceImpl 
    implements WEB3ToSuccEquityOrderService 
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccEquityOrderServiceImpl.class);
    
    /**
     * @@roseuid 4349D9C700FA
     */
    public WEB3ToSuccEquityOrderServiceImpl() 
    {
     
    }
    
    /**
     * (validate����)<BR>
     * �i�A���j�������������m�F���������{����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�i�A���j���������T�[�r�X�jvalidate�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * �u�i�A���j�����������t�����m�F���N�G�X�g�v�܂���<BR>
     * �u�i�A���j�����������t�����m�F���N�G�X�g�v��<BR>
     *  �ݒ肳���B<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException 
     * @@roseuid 431D3A8A00AB
     */
    protected WEB3GenResponse validateOrder(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" validateOrder(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        WEB3SuccCommonInfo l_succCommonInfo = null;
        if (l_request instanceof WEB3SuccEquityBuyConfirmRequest)
        {
            WEB3SuccEquityBuyConfirmRequest l_buyConfirmRequest = (WEB3SuccEquityBuyConfirmRequest)l_request;
            
            //1.1 validate()
            l_buyConfirmRequest.validate(); //WEB3BusinessLayerException 
            l_succCommonInfo = l_buyConfirmRequest.succCommonInfo;
        }
        else if (l_request instanceof WEB3SuccEquitySellConfirmRequest)
        {
            WEB3SuccEquitySellConfirmRequest l_sellConfirmRequest = (WEB3SuccEquitySellConfirmRequest)l_request;

            //1.1 validate()
            l_sellConfirmRequest.validate(); //WEB3BusinessLayerException 
            l_succCommonInfo = l_sellConfirmRequest.succCommonInfo;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }

        //1.2 get�����e�����̒����P��(long)
        WEB3ToSuccOrderManagerImpl l_orderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngParentOrderId = Long.parseLong(l_succCommonInfo.parentOrderId);
        EqTypeOrderUnit l_orderUnit = 
            l_orderManager.getEqtypeParentOrderUnit(l_lngParentOrderId);
            
        //1.3 validate���N�G�X�g�f�[�^at���Ύ��(WEB3GenRequest, �����P��)
        this.validateRequestDataAtReversingTrade(l_request, l_orderUnit);   
            
        //1.4 validate�A������(�⏕����, ProductTypeEnum, String, String, OrderUnit)
        l_orderManager.validateSuccOrder(
            this.getSubAccount(),
            ProductTypeEnum.EQUITY,
            WEB3FuturesOptionDivDef.DEFAULT,
            l_succCommonInfo.succTradingType,
            l_orderUnit);
                
        //1.5 validate�A�������ő�ݒ萔(OrderUnit)
        this.validateSuccOrderMaxQuantity(l_orderUnit);
            
        //1.6 validate����(���̓f�[�^ : WEB3GenRequest)
        l_response = super.validateOrder(l_request); //WEB3BaseException

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit����)<BR>
     * �i�A���j��������������o�^����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�i�A���j���������T�[�r�X�jsubmit�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * �u�i�A���j�����������t�����������N�G�X�g�v�܂���<BR>
     * �u�i�A���j�����������t�����������N�G�X�g�v��<BR>
     * �ݒ肳���B<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException 
     * @@roseuid 431D3A8A00BA
     */
    protected WEB3GenResponse submitOrder(WEB3GenRequest l_request) throws WEB3BaseException  
    {
        final String STR_METHOD_NAME =" submitOrder(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        WEB3SuccCommonInfo l_succCommonInfo = null;
        long l_lngOrderId = 0;
        if (l_request instanceof WEB3SuccEquityBuyCompleteRequest)
        {
            WEB3SuccEquityBuyCompleteRequest l_buyCompleteRequest = (WEB3SuccEquityBuyCompleteRequest)l_request;
            
            //1.1 validate()
            l_buyCompleteRequest.validate(); //WEB3BusinessLayerException
            
            l_succCommonInfo = l_buyCompleteRequest.succCommonInfo;
            l_lngOrderId = Long.parseLong(l_buyCompleteRequest.orderId);
        }
        else if (l_request instanceof WEB3SuccEquitySellCompleteRequest)
        { 
            WEB3SuccEquitySellCompleteRequest l_sellCompleteRequest = (WEB3SuccEquitySellCompleteRequest)l_request;
            //1.1 validate()
            l_sellCompleteRequest.validate(); //WEB3BusinessLayerException
            
            l_succCommonInfo = l_sellCompleteRequest.succCommonInfo;
            l_lngOrderId = Long.parseLong(l_sellCompleteRequest.orderId);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }
        
        //1.2 get�����e�����̒����P��(long)
        WEB3ToSuccOrderManagerImpl l_orderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngParentOrderId = Long.parseLong(l_succCommonInfo.parentOrderId);
        EqTypeOrderUnit l_orderUnit = 
            l_orderManager.getEqtypeParentOrderUnit(l_lngParentOrderId);
        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�e�[�u���ɊY������f�[�^������܂���B");
        }
             
        //1.3 validate���N�G�X�g�f�[�^at���Ύ��(WEB3GenRequest, �����P��)
        this.validateRequestDataAtReversingTrade(l_request, l_orderUnit);   
            
        //1.4 validate�A������(�⏕����, ProductTypeEnum, String, String, OrderUnit)
        l_orderManager.validateSuccOrder(
            this.getSubAccount(),
            ProductTypeEnum.EQUITY,
            WEB3FuturesOptionDivDef.DEFAULT,
            l_succCommonInfo.succTradingType,
            l_orderUnit);
            
        //1.5 validate�A�������ő�ݒ萔(OrderUnit)
        this.validateSuccOrderMaxQuantity(l_orderUnit);
            
        //1.6  submit����(���̓f�[�^ : WEB3GenRequest)
        l_response = super.submitOrder(l_request); //WEB3BaseException
            
        //1.7 notify�\�񒍕��o�^(long)
        this.notifyRsvOrderRegister(l_lngOrderId);
            
        //1.8 �i*�jsuper.submit����()�̖߂�l��ԋp����
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * �i�A���j�����������������s����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate����()�܂���submit����()���\�b�h<BR>
     * ���R�[������B
     * @@param l_request - ���N�G�X�g�f�[�^�B
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 431D3A8A00DA
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }
        
        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3SuccEquityBuyConfirmRequest)
        {
            //validate����()()
            l_response = 
                this.validateOrder((WEB3SuccEquityBuyConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3SuccEquitySellConfirmRequest)
        {
            //validate����()()
            l_response = 
                this.validateOrder((WEB3SuccEquitySellConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3SuccEquityBuyCompleteRequest)
        {
            //submit����()
            l_response = 
                this.submitOrder((WEB3SuccEquityBuyCompleteRequest)l_request);
        }
        else if (l_request instanceof WEB3SuccEquitySellCompleteRequest)
        {
            //submit����()
            l_response = 
                this.submitOrder((WEB3SuccEquitySellCompleteRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^�̃C���X�^���X�𐶐�����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �i�A���j�����������N�G�X�g�A�_�v�^.create(�����̃��N�G�X�g)���R�[������B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�f�[�^�B
     * @@return WEB3EquityOrderRequestAdapter
     * @@roseuid 43278E38026D
     */
    protected WEB3EquityOrderRequestAdapter createRequestAdapter(WEB3GenRequest l_request) 
    {
        final String STR_METHOD_NAME =" createRequestAdapter(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityOrderRequestAdapter l_adapter = WEB3ToSuccEquityOrderRequestAdapter.create(l_request);
        
        log.exiting(STR_METHOD_NAME);
        return l_adapter;
    }
    
    /**
     * (validate������������)<BR>
     * �������������R�����\�b�h�̌Ăяo�����s���B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * EQTYPE�̊g�����������}�l�[�W��.validate������������(<BR>
     * �⏕����, �����������e, is�A�����Δ���)��delegate����B<BR>
     * <BR>
     * ------------------------------------------------------<BR>
     * ��validate������������()�F�����ݒ�d�l��<BR>
     * <BR>
     * �⏕�����F�@@this.get�⏕����()�̖߂�l<BR>
     * �����������e�F�@@�����̊����������e�I�u�W�F�N�g<BR>
     * is�A�����Δ����F�@@�A�������}�l�[�W��Impl.is���Δ������(<BR>
     * �A����������敪(*1), �e�����̒����P��(*2))�̖߂�l<BR>
     * <BR>
     * (*1)�A����������敪�F�@@�����̃��N�G�X�g�A�_�v�^.���N�G�X�g.�A���������ʏ�� ��<BR>
     * ������<BR>
     * (*2)�e�����̒����P�ʁF�@@�����̃��N�G�X�g�A�_�v�^.�e�����̒����P��<BR>
     * ------------------------------------------------------<BR>
     * @@param l_orderSpec - (�����������e)<BR>
     * 
     * @@param l_requestAdaptor - (�i�A���j�����������N�G�X�g�A�_�v�^)<BR>
     * �i�A���j�����������N�G�X�g�A�_�v�^�B
     * @@return EqTypeNewOrderValidationResult
     * @@throws WEB3BaseException
     * @@roseuid 43276F1C0309
     */
    protected EqTypeNewOrderValidationResult validateNewCashBasedOrder(
        EqTypeNewCashBasedOrderSpec l_orderSpec, 
        WEB3EquityOrderRequestAdapter l_requestAdaptor) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" validateNewCashBasedOrder(EqTypeNewCashBasedOrderSpec, " +
            "WEB3EquityOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);

        if (l_requestAdaptor == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�i�A���j�����������N�G�X�g�A�_�v�^�����w��(null)�ł��B");
        }
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3EquityTradingModule l_tradingModule
            = (WEB3EquityTradingModule) l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_equityOrderManager
            = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();

        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        
        WEB3SuccCommonInfo l_succCommonInfo = null;
        if (l_requestAdaptor.request instanceof WEB3SuccEquityBuyConfirmRequest)
        {
            WEB3SuccEquityBuyConfirmRequest l_buyConfirmRequest = 
                (WEB3SuccEquityBuyConfirmRequest)l_requestAdaptor.request;
            l_succCommonInfo = l_buyConfirmRequest.succCommonInfo;
        }
        else if (l_requestAdaptor.request instanceof WEB3SuccEquityBuyCompleteRequest)
        {
            WEB3SuccEquityBuyCompleteRequest l_buyCompleteRequest = 
                (WEB3SuccEquityBuyCompleteRequest)l_requestAdaptor.request;
            l_succCommonInfo = l_buyCompleteRequest.succCommonInfo;
        }
        else if (l_requestAdaptor.request instanceof WEB3SuccEquitySellConfirmRequest)
        {
            WEB3SuccEquitySellConfirmRequest l_sellConfirmRequest = 
                (WEB3SuccEquitySellConfirmRequest)l_requestAdaptor.request;
            l_succCommonInfo = l_sellConfirmRequest.succCommonInfo;
        }
        else if (l_requestAdaptor.request instanceof WEB3SuccEquitySellCompleteRequest)
        {
            WEB3SuccEquitySellCompleteRequest l_sellCompleteRequest = 
                (WEB3SuccEquitySellCompleteRequest)l_requestAdaptor.request;
            l_succCommonInfo = l_sellCompleteRequest.succCommonInfo;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }
        
        WEB3ToSuccEquityOrderRequestAdapter l_toSuccEquityOrderRequestAdapter = null;
        if (l_requestAdaptor instanceof WEB3ToSuccEquityOrderRequestAdapter)
        {
            l_toSuccEquityOrderRequestAdapter = 
                (WEB3ToSuccEquityOrderRequestAdapter)l_requestAdaptor;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }
        
        boolean l_blnReversingTrade = l_toOrderManager.isReversingTrade(
            l_succCommonInfo.succTradingType, 
            l_toSuccEquityOrderRequestAdapter.parentOrderUnit);
        
        //validate������������()            
        EqTypeNewOrderValidationResult l_result = l_equityOrderManager.validateNewCashBasedOrder(
            this.getSubAccount(), //WEB3BaseException
            l_orderSpec,
            l_blnReversingTrade);
        
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }
    
    /**
     * (validate����]��)<BR>
     * ����]�͂��`�F�b�N���A����]�͌��ʃI�u�W�F�N�g��ԋp����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �����̊����������e.isSellOrder()==true�i�������j�̏ꍇ�́A<BR>
     * ����������return����B�inull��ԋp�j<BR>
     * <BR>
     * �����̊����������e.isSellOrder()==false�i�������j�̏ꍇ�́A<BR>
     * �]�̓`�F�b�N�����{���镔�X(*3)�̏ꍇ�̂݁A<BR>
     * ���t�\�z(*1)�ƊT�Z��n���(*2)���r���A<BR>
     * �i�T�Z��n���(*2) > ���t�\�z(*1)�j�̏ꍇ�́A<BR>
     * �u����]�͕s���v�̗�O��throw����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_01306<BR>
     * <BR>
     * �ȊO�Anull��ԋp����B<BR>
     * <BR>
     * (*1)���t�\�z<BR>
     * ����]�̓T�[�r�X.get�������t�\�z�`�A�������`(�����̕⏕����, ������<BR>
     * �������.��n��, null)<BR>
     * �̖߂�l���A���t�\�z�Ƃ���B<BR>
     * <BR>
     * (*2)�T�Z��n���<BR>
     * �����̊����������e.get�T�Z��n���()�Ŏ擾�B<BR>
     * <BR>
     * (*3)�]�̓`�F�b�N�����{���镔�X<BR>
     * �A�������}�l�[�W��Impl.is�]�̓`�F�b�N���{���X()==true�̏ꍇ�́A<BR>
     * �]�̓`�F�b�N�����{���镔�X�ł���Ɣ��肷��B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B
     * @@param l_orderSpec - (�����������e)<BR>
     * �����������e�I�u�W�F�N�g�B
     * @@param l_blnUpdateFlg - (�]�͍X�V�t���O)<BR>
     * �]�͍X�V�t���O�B<BR>
     * �ifalse�F�@@�m�F���Atrue�F�@@�������j<BR>
     * @@param l_tradedProduct - (�������)<BR>
     * ��������I�u�W�F�N�g�B
     * @@return WEB3TPTradingPowerResult
     * @@throws WEB3BaseException
     * @@roseuid 43278E38026F
     */
    protected WEB3TPTradingPowerResult validateTradingPower(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3EquityNewCashBasedOrderSpec l_orderSpec,
        boolean l_blnUpdateFlg,
        WEB3EquityTradedProduct l_tradedProduct) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" validateTradingPower(" +
            "WEB3GentradeSubAccount, WEB3EquityNewCashBasedOrderSpec, boolean, WEB3EquityTradedProduct)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderSpec == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�����������e�I�u�W�F�N�g�����w��(null)�ł��B");
        }
        if (l_tradedProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "��������I�u�W�F�N�g�����w��(null)�ł��B");
        }

        if (l_orderSpec.isSellOrder())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        if (l_toOrderManager.isCheckTradingPowerBranch(l_subAccount) == false)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        WEB3TPTradingPowerService l_trdingPowerService = 
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        double l_dblBuyPower = l_trdingPowerService.getSuccEquityTradingPower(
            l_subAccount,
            l_tradedProduct.getDailyDeliveryDate(),
            null);
        
        double l_dblDeliveryAmount = l_orderSpec.getEstimateDeliveryAmount();
        
        if (l_dblDeliveryAmount > l_dblBuyPower)
        {
            String l_strMessage = "�T�Z��n����u" + l_dblDeliveryAmount 
                + "�v > ���t�\�z�u" + l_dblBuyPower + "�v�B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (get�T�Z�뉿�P��)<BR>
     * �T�Z�뉿�P�����擾���Ԃ��B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�����̃��N�G�X�g�A�_�v�^.is������()==false�̏ꍇ�́Anull��ԋp����B<BR>
     * �@@�@@�@@�ȊO�A�ȉ��̏������s���B<BR>
     * <BR>
     * �Q�j�@@�e�����̔��Δ���������ǂ����𔻒肷��B<BR>
     * <BR>
     * �@@�@@�A�������}�l�[�W��Impl.is���Δ������()==true�i���Δ����j�̏ꍇ�́A<BR>
     * �@@�@@null��ԋp����B<BR>
     * <BR>
     * �@@�@@-------------------------------------------------------<BR>
     * �@@�@@��is���Δ������()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�A����������敪�F�@@�����̃��N�G�X�g�A�_�v�^�̃��N�G�X�g.<BR>
     * �A���������ʏ��.�A����������敪<BR>
     * �@@�@@�e�����̒����P�ʁF�@@�����̃��N�G�X�g�A�_�v�^�̐e�����̒����P��<BR>
     * �@@�@@-------------------------------------------------------<BR>
     * <BR>
     * �R�j�@@���Δ����łȂ��ꍇ�A�ۗL���Y�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�����|�W�V�����}�l�[�W��.getAsset(�����̃��N�G�X�g�A�_�v�^.���N�G�X�g.ID)<BR>
     * ���R�[������B<BR>
     * <BR>
     * �S�j�@@�����v�Z�T�[�r�X.calc�T�Z�뉿�P��(�ۗL���Y.����ID, this.get�⏕����(), <BR>
     * �ۗL���Y.�ŋ敪)���R�[�����A<BR>
     * �@@�@@�@@�߂�l��ԋp����B<BR>
     * @@param l_requestAdaptor - (�i�A���j�����������N�G�X�g�A�_�v�^)<BR>
     * �i�A���j�����������N�G�X�g�A�_�v�^�B
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 43278E3802BB
     */
    protected String getEstimatedBookPrice(WEB3EquityOrderRequestAdapter l_requestAdaptor) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =" getEstimatedBookPrice(WEB3EquityOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        if (l_requestAdaptor == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�i�A���j�����������N�G�X�g�A�_�v�^�����w��(null)�ł��B");
        }

        //�P�j�@@�����̃��N�G�X�g�A�_�v�^.is������()==false�̏ꍇ�́Anull��ԋp����B
        //�@@�@@�@@�ȊO�A�ȉ��̏������s���B
        if (!l_requestAdaptor.isSellOrder()) //WEB3BaseException
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        

        long l_lngAssetId = 0;
        //�Q�j�@@�e�����̔��Δ���������ǂ����𔻒肷��B
        //�@@�@@�A�������}�l�[�W��Impl.is���Δ������()==true�i���Δ����j�̏ꍇ�́A
        //�@@�@@null��ԋp����B
        WEB3SuccCommonInfo l_succCommonInfo = null;
        if (l_requestAdaptor.request instanceof WEB3SuccEquitySellConfirmRequest)
        {
            WEB3SuccEquitySellConfirmRequest l_sellConfirmRequest = 
                (WEB3SuccEquitySellConfirmRequest)l_requestAdaptor.request;
            l_succCommonInfo = l_sellConfirmRequest.succCommonInfo;
            if (!WEB3StringTypeUtility.isEmpty(l_sellConfirmRequest.id))
            {
                l_lngAssetId = Long.parseLong(l_sellConfirmRequest.id);
            }
        }
        else if (l_requestAdaptor.request instanceof WEB3SuccEquitySellCompleteRequest)
        {
            WEB3SuccEquitySellCompleteRequest l_sellCompleteRequest = 
                (WEB3SuccEquitySellCompleteRequest)l_requestAdaptor.request;
            l_succCommonInfo = l_sellCompleteRequest.succCommonInfo;
            if (!WEB3StringTypeUtility.isEmpty(l_sellCompleteRequest.id))
            {
                l_lngAssetId = Long.parseLong(l_sellCompleteRequest.id);
            }
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }
        WEB3ToSuccEquityOrderRequestAdapter l_toSuccEquityOrderRequestAdapter = null;
        if (l_requestAdaptor instanceof WEB3ToSuccEquityOrderRequestAdapter)
        {
            l_toSuccEquityOrderRequestAdapter = 
                (WEB3ToSuccEquityOrderRequestAdapter)l_requestAdaptor;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }
        
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        boolean l_blnReversingTrade = l_toOrderManager.isReversingTrade(
            l_succCommonInfo.succTradingType, 
            l_toSuccEquityOrderRequestAdapter.parentOrderUnit);

        if (l_blnReversingTrade)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�R�j�@@���Δ����łȂ��ꍇ�A�ۗL���Y�I�u�W�F�N�g���擾����B
        //�@@�����|�W�V�����}�l�[�W��.getAsset(�����̃��N�G�X�g�A�_�v�^.���N�G�X�g.ID)
        //���R�[������B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        Asset l_asset = null;
        try
        {
            l_asset = l_positionManager.getAsset(l_lngAssetId);
        }
        catch (NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�S�j�@@�����v�Z�T�[�r�X.calc�T�Z�뉿�P��(�ۗL���Y.����ID, this.get�⏕����(), 
        //�ۗL���Y.�ŋ敪)���R�[�����A
        //�@@�@@�@@�߂�l��ԋp����B
        WEB3EquityBizLogicProvider l_bizLogicProvider =
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
        double l_dblEstimatedBookPrice =  l_bizLogicProvider.calcEstimatedBookPrice(
            l_asset.getProduct().getProductId(),
            this.getSubAccount(),
            l_asset.getTaxType());
        
        String l_strEstimatedBookPrice = WEB3StringTypeUtility.formatNumber(l_dblEstimatedBookPrice);
            
        log.exiting(STR_METHOD_NAME);
        return l_strEstimatedBookPrice;
    }
    
    /**
     * (validate���t�\����)<BR>
     * �������̏ꍇ�́A���t�\���ʂ̃`�F�b�N���s���B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�����̃��N�G�X�g�A�_�v�^.is������()==false�̏ꍇ�́A����������return����B<BR>
     * �@@�@@�@@�ȊO�A�ȉ��̏������s���B <BR>
     * <BR>
     * �Q�j�@@�e�����̔��Δ���������ǂ����𔻒肷��B<BR>
     * <BR>
     * �@@�@@�A�������}�l�[�W��Impl.is���Δ������()==false�i���Δ����ł͂Ȃ��j�̏ꍇ�́A<BR>
     * �@@�@@���������ɂ��̂܂�return����B<BR>
     * <BR>
     * �@@�@@-------------------------------------------------------<BR>
     * �@@�@@��is���Δ������()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�A����������敪�F�@@�����̃��N�G�X�g�A�_�v�^�̃��N�G�X�g.�A���������ʏ��<BR>
     * .�A����������敪<BR>
     * �@@�@@�e�����̒����P�ʁF�@@�����̃��N�G�X�g�A�_�v�^�̐e�����̒����P��<BR>
     * �@@�@@-------------------------------------------------------<BR>
     * <BR>
     * �@@�@@�߂�l��true�̏ꍇ�́A�e�����̔��Δ�������Ɣ��肵�A�ȉ��̏������s���B<BR>
     * <BR>
     * �R�j�@@�m��c�̐���(*1)���擾����B<BR>
     * <BR>
     * �@@�@@�����|�W�V�����}�l�[�W��.get�ۗL���Y()�ɂ��A�ۗL���Y�I�u�W�F�N�g��<BR>
     * �擾����B<BR>
     * <BR>
     * �@@�@@-------------------------------------------------------<BR>
     * �@@�@@��get�ۗL���Y()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@����ID�F�@@get�⏕����().����ID<BR>
     * �@@�@@�⏕����ID�F�@@get�⏕����().�⏕����ID<BR>
     * �@@�@@����ID�F�@@�����̃��N�G�X�g�A�_�v�^.get����().����ID<BR>
     * �@@�@@�ŋ敪�F�@@�����̃��N�G�X�g�A�_�v�^.get�ŋ敪()<BR>
     * �@@�@@-------------------------------------------------------<BR>
     * <BR>
     * �@@�@@�m��c�̐���(*1) = �ۗL���Y.����<BR>
     * �@@�@@���Y������ۗL���Y�I�u�W�F�N�g�����݂��Ȃ��ꍇ�́A0���Z�b�g����B<BR>
     * <BR>
     * �S�j�@@�������̐���(*2)���擾����B<BR>
     * <BR>
     * �@@�@@�������̐���(*2) = �Q�j�Ŏ擾�����ۗL���Y.getLockedQuantity()<BR>
     * �@@�@@���Y������ۗL���Y�I�u�W�F�N�g�����݂��Ȃ��ꍇ�́A0���Z�b�g����B<BR>
     * <BR>
     * �T�j�@@�e�����̖���萔��(*3)���擾����B<BR>
     * <BR>
     * �@@�@@�e�����̖���萔��(*3) = �����̃��N�G�X�g�A�_�v�^.�e�����̒����P��.��������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@ �| �����̃��N�G�X�g�A�_�v�^.�e������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����P��.��萔��<BR>
     * <BR>
     * �U�j�@@���t���������t�\���ʂ͈͓̔����ǂ������`�F�b�N����B<BR>
     * <BR>
     * �@@�@@�i�m��c�̐���(*1) �| �������̐���(*2) �{ �e�����̖���萔��(*3)�j<<BR>
     * �����̃��N�G�X�g�A�_�v�^.���N�G�X�g.��������<BR>
     * �@@�@@ �̏ꍇ�́A�u���t�\���ʃG���[�v�̗�O��throw����B<BR>
     * �@@�@@ class: WEB3BusinessLayerException<BR>
     * �@@�@@ tag:   BUSINESS_ERROR_00167<BR>
     * @@param l_requestAdaptor - (�i�A���j�����������N�G�X�g�A�_�v�^)<BR>
     * �i�A���j�����������N�G�X�g�A�_�v�^�B
     * @@throws WEB3BaseException
     * @@roseuid 431E7B4E0200
     */
    protected void validateSellableAssetQuantity(WEB3EquityOrderRequestAdapter l_requestAdaptor)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" validateSellableAssetQuantity(WEB3EquityOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        if (l_requestAdaptor == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�i�A���j�����������N�G�X�g�A�_�v�^�����w��(null)�ł��B");
        }

        //�P�j�@@�����̃��N�G�X�g�A�_�v�^.is������()==false�̏ꍇ�́A����������return����B
        //�@@�@@�@@�ȊO�A�ȉ��̏������s���B 
        if (!l_requestAdaptor.isSellOrder())
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //�Q�j�@@�e�����̔��Δ���������ǂ����𔻒肷��B
        //�@@�@@�A�������}�l�[�W��Impl.is���Δ������()==false�i���Δ����ł͂Ȃ��j�̏ꍇ�́A
        //�@@�@@���������ɂ��̂܂�return����B
        //�@@�@@�߂�l��true�̏ꍇ�́A�e�����̔��Δ�������Ɣ��肵�A�ȉ��̏������s���B
        double l_dblRequestOrderQuantity = 0;
        WEB3SuccCommonInfo l_succCommonInfo = null;
        if (l_requestAdaptor.request instanceof WEB3SuccEquitySellConfirmRequest)
        {
            WEB3SuccEquitySellConfirmRequest l_sellConfirmRequest = 
                (WEB3SuccEquitySellConfirmRequest)l_requestAdaptor.request;
            l_succCommonInfo = l_sellConfirmRequest.succCommonInfo;
            l_dblRequestOrderQuantity = Double.parseDouble(l_sellConfirmRequest.orderQuantity);
        }
        else if (l_requestAdaptor.request instanceof WEB3SuccEquitySellCompleteRequest)
        {
            WEB3SuccEquitySellCompleteRequest l_sellCompleteRequest = 
                (WEB3SuccEquitySellCompleteRequest)l_requestAdaptor.request;
            l_succCommonInfo = l_sellCompleteRequest.succCommonInfo;
            l_dblRequestOrderQuantity = Double.parseDouble(l_sellCompleteRequest.orderQuantity);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }
        WEB3ToSuccEquityOrderRequestAdapter l_toSuccEquityOrderRequestAdapter = null;
        if (l_requestAdaptor instanceof WEB3ToSuccEquityOrderRequestAdapter)
        {
            l_toSuccEquityOrderRequestAdapter = 
                (WEB3ToSuccEquityOrderRequestAdapter)l_requestAdaptor;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }
        
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        boolean l_blnReversingTrade = l_toOrderManager.isReversingTrade(
            l_succCommonInfo.succTradingType, 
            l_toSuccEquityOrderRequestAdapter.parentOrderUnit);

        if (!l_blnReversingTrade)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //�R�j�@@�m��c�̐���(*1)���擾����B
        //�@@�@@�����|�W�V�����}�l�[�W��.get�ۗL���Y()�ɂ��A�ۗL���Y�I�u�W�F�N�g��
        //�擾����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        EqTypeAsset l_asset = l_positionManager.getAsset(
            this.getSubAccount().getAccountId(),
            this.getSubAccount().getSubAccountId(),
            l_toSuccEquityOrderRequestAdapter.getProduct().getProductId(),
            l_toSuccEquityOrderRequestAdapter.getTaxDivision()); //WEB3BaseException
        double l_dblRestQuantity = 0;
        if (l_asset != null)
        {
            l_dblRestQuantity = l_asset.getQuantity();
        }

        //�S�j�@@�������̐���(*2)���擾����B
        //�@@�@@�������̐���(*2) = �Q�j�Ŏ擾�����ۗL���Y.getLockedQuantity()
        //�@@�@@���Y������ۗL���Y�I�u�W�F�N�g�����݂��Ȃ��ꍇ�́A0���Z�b�g����B
        double l_dblOrderQuantity = 0;
        if (l_asset != null)
        {
            l_dblOrderQuantity = l_asset.getLockedQuantity();
        }
       
        
        //�T�j�@@�e�����̖���萔��(*3)���擾����B
        //
        //�@@�@@�e�����̖���萔��(*3) = �����̃��N�G�X�g�A�_�v�^.�e�����̒����P��.��������
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@ �| �����̃��N�G�X�g�A�_�v�^.�e������
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����P��.��萔��
        EqTypeOrderUnit l_orderUnit = l_toSuccEquityOrderRequestAdapter.parentOrderUnit;
        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�e�����̒����P�ʂ����w��(null)�ł��B");
        }
        
        double l_dblQuantity = l_orderUnit.getQuantity();
        if (Double.isNaN(l_dblQuantity))
        {
            l_dblQuantity = 0;
        }
        double l_dblExecutedQuantity = l_orderUnit.getExecutedQuantity();
        if (Double.isNaN(l_dblExecutedQuantity))
        {
            l_dblExecutedQuantity = 0;
        }
        double l_dblUnexecutedQuantity = l_dblQuantity - l_dblExecutedQuantity;
        
        //�U�j�@@���t���������t�\���ʂ͈͓̔����ǂ������`�F�b�N����B
        //�@@�@@�i�m��c�̐���(*1) �| �������̐���(*2) �{ �e�����̖���萔��(*3)�j
        //�����̃��N�G�X�g�A�_�v�^.���N�G�X�g.��������
        //�@@�@@ �̏ꍇ�́A�u���t�\���ʃG���[�v�̗�O��throw����B
        if (l_dblRequestOrderQuantity > (l_dblRestQuantity - l_dblOrderQuantity + l_dblUnexecutedQuantity))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00167,
                getClass().getName() + STR_METHOD_NAME,
                "�w�芔���͔��t�\�����𒴂��Ă��܂��B");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit������������)<BR>
     * �\�񒍕���o�^����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �A�������}�l�[�W��Impl.submit���������V�K�\�񒍕�()���R�[������B<BR>
     * <BR>
     * ---------------------------------------------------------<BR>
     * ��submit���������V�K�\�񒍕�()�F�����ݒ�d�l��<BR>
     * <BR>
     * �⏕�����F�@@�����̕⏕����<BR>
     * �������e�F�@@�����̊����������e<BR>
     * ����ID�F�@@�����̒���ID<BR>
     * ����p�X���[�h�F�@@�����̃p�X���[�h<BR>
     * �A����������敪�F�@@<BR>
     * �����̃��N�G�X�g�A�_�v�^.���N�G�X�g.�A���������ʏ��.�A����������敪<BR>
     * �P�������l�F�@@�����̃��N�G�X�g�A�_�v�^.���N�G�X�g.�P�������l���.get�P�������l()<BR>
     * �@@�@@�������̃��N�G�X�g�A�_�v�^.���N�G�X�g.�P�������l���==null�̏ꍇ�́A<BR>
     * �@@�@@�@@null���Z�b�g<BR>
     * �e�����̒����P�ʁF�@@�����̃��N�G�X�g�A�_�v�^.�e�����̒����P��<BR>
     * ---------------------------------------------------------<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B
     * @@param l_orderSpec - (�����������e)<BR>
     * �����������e�I�u�W�F�N�g�B
     * @@param l_lngOrderId - (����ID)<BR>
     * �\�񒍕��̒���ID�B
     * @@param l_strTradingPassword - (����p�X���[�h)<BR>
     * ����p�X���[�h�B
     * @@param l_requestAdaptor - (�i�A���j�����������N�G�X�g�A�_�v�^)<BR>
     * �i�A���j�����������N�G�X�g�A�_�v�^�B
     * @@throws WEB3BaseException
     * @@roseuid 431D550701F3
     */
    protected void submitNewCashBasedOrder(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3EquityNewCashBasedOrderSpec l_orderSpec, 
        long l_lngOrderId, 
        String l_strTradingPassword, 
        WEB3EquityOrderRequestAdapter l_requestAdaptor)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" submitNewCashBasedOrder(" +
            "WEB3GentradeSubAccount, WEB3EquityNewCashBasedOrderSpec, long, String, WEB3EquityOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccEquityOrderRequestAdapter l_toSuccEquityOrderRequestAdapter = null;
        if (l_requestAdaptor instanceof WEB3ToSuccEquityOrderRequestAdapter)
        {
            l_toSuccEquityOrderRequestAdapter = 
                (WEB3ToSuccEquityOrderRequestAdapter)l_requestAdaptor;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }
        
        //�A�������}�l�[�W��Impl.submit���������V�K�\�񒍕�()���R�[������B
        WEB3SuccCommonInfo l_succCommonInfo = null;
        WEB3SuccPriceAdjustmentValueInfo l_priceAdjustmentValueInfo = null;
        if (l_requestAdaptor.request instanceof WEB3SuccEquityBuyConfirmRequest)
        {
            WEB3SuccEquityBuyConfirmRequest l_buyConfirmRequest = 
                (WEB3SuccEquityBuyConfirmRequest)l_requestAdaptor.request;
            l_succCommonInfo = l_buyConfirmRequest.succCommonInfo;
            l_priceAdjustmentValueInfo = l_buyConfirmRequest.priceAdjustmentValueInfo;
        }
        else if (l_requestAdaptor.request instanceof WEB3SuccEquityBuyCompleteRequest)
        {
            WEB3SuccEquityBuyCompleteRequest l_buyCompleteRequest = 
                (WEB3SuccEquityBuyCompleteRequest)l_requestAdaptor.request;
            l_succCommonInfo = l_buyCompleteRequest.succCommonInfo;
            l_priceAdjustmentValueInfo = l_buyCompleteRequest.priceAdjustmentValueInfo;
        }
        else if (l_requestAdaptor.request instanceof WEB3SuccEquitySellConfirmRequest)
        {
            WEB3SuccEquitySellConfirmRequest l_sellConfirmRequest = 
                (WEB3SuccEquitySellConfirmRequest)l_requestAdaptor.request;
            l_succCommonInfo = l_sellConfirmRequest.succCommonInfo;
            l_priceAdjustmentValueInfo = l_sellConfirmRequest.priceAdjustmentValueInfo;
        }
        else if (l_requestAdaptor.request instanceof WEB3SuccEquitySellCompleteRequest)
        {
            WEB3SuccEquitySellCompleteRequest l_sellCompleteRequest = 
                (WEB3SuccEquitySellCompleteRequest)l_requestAdaptor.request;
            l_succCommonInfo = l_sellCompleteRequest.succCommonInfo;
            l_priceAdjustmentValueInfo = l_sellCompleteRequest.priceAdjustmentValueInfo;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }
        Double l_priceAdjustmentValue = null;
        if (l_priceAdjustmentValueInfo != null)
        {
            l_priceAdjustmentValue = new Double(l_priceAdjustmentValueInfo.getPriceAdjustmentValue());
        }
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        l_toOrderManager.submitEqtypeNewOrder(
            l_subAccount,
            l_orderSpec,
            l_lngOrderId,
            l_strTradingPassword,
            l_succCommonInfo.succTradingType,
            l_priceAdjustmentValue,
            l_toSuccEquityOrderRequestAdapter.parentOrderUnit);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate���N�G�X�g�f�[�^at���Ύ��)<BR>
     * ���Ύ���w�莞�ɌŗL�́A���N�G�X�g�f�[�^�̃v���p�e�B�`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�A�������}�l�[�W��Impl.is���Δ������(<BR>
     * �@@�@@�@@�����̃��N�G�X�g.�A���������ʏ��.�A����������敪, �����̐e������<BR>
     * �@@�@@�@@�����P��)==false<BR>
     * �@@�@@�@@�i�����Ύ���łȂ��j�ꍇ�́A<BR>
     * �@@�@@�@@����������return����B<BR>
     * <BR>
     * �@@�@@�@@�ȊO�A�ȉ��̏������s���B<BR>
     * <BR>
     * �Q�j�@@�����̃��N�G�X�g�f�[�^�̌^��"�i�A���j�����������t�����m�F���N�G�X�g"�܂���<BR>
     * �@@�@@�@@"�i�A���j�����������t�����������N�G�X�g"�̏ꍇ�A<BR>
     * �@@�@@�@@�����̂�N�X�ƃf�[�^.�����R�[�h�������̐e�����̒����P��.����ID�ɊY������<BR>
     * �@@�@@�@@��������.�����R�[�h �̏ꍇ��<BR>
     * �@@�@@�@@�u���Ύ�����̖����w�肪�A�e�����ƕs�����v�̗�O��throw����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_02250<BR>
     * <BR>
     * �R�j�@@�����̐e�����̒����P��==�������n�����̏ꍇ�A<BR>
     *      �u�e�������������n�����̏ꍇ�A�}�w�l�͎w��s�v�̗�O��throw����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_02291<BR>
     *
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B
     * @@param l_parentOrderUnit - (�e�����̒����P��)<BR>
     * �e�����̒����P�ʁB
     * @@throws WEB3BaseException
     * @@roseuid 4332436000BD
     */
    protected void validateRequestDataAtReversingTrade(WEB3GenRequest l_request, EqTypeOrderUnit l_parentOrderUnit)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" validateRequestDataAtReversingTrade(WEB3GenRequest, EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }
        if (l_parentOrderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�e�����̒����P�ʂ����w��(null)�ł��B");
        }
        //�P�j
        WEB3SuccCommonInfo l_succCommonInfo = null;
        WEB3SuccPriceAdjustmentValueInfo l_succPriceInfo = null;
        if (l_request instanceof WEB3SuccEquityBuyConfirmRequest)
        {
            WEB3SuccEquityBuyConfirmRequest l_buyConfirmRequest = 
                (WEB3SuccEquityBuyConfirmRequest)l_request;
            l_succCommonInfo = l_buyConfirmRequest.succCommonInfo;
            l_succPriceInfo = l_buyConfirmRequest.priceAdjustmentValueInfo;
        }
        else if (l_request instanceof WEB3SuccEquityBuyCompleteRequest)
        {
            WEB3SuccEquityBuyCompleteRequest l_buyCompleteRequest = 
                (WEB3SuccEquityBuyCompleteRequest)l_request;
            l_succCommonInfo = l_buyCompleteRequest.succCommonInfo;
            l_succPriceInfo = l_buyCompleteRequest.priceAdjustmentValueInfo;
        }
        else if (l_request instanceof WEB3SuccEquitySellConfirmRequest)
        {
            WEB3SuccEquitySellConfirmRequest l_sellConfirmRequest = 
                (WEB3SuccEquitySellConfirmRequest)l_request;
            l_succCommonInfo = l_sellConfirmRequest.succCommonInfo;
            l_succPriceInfo = l_sellConfirmRequest.priceAdjustmentValueInfo;
        }
        else if (l_request instanceof WEB3SuccEquitySellCompleteRequest)
        {
            WEB3SuccEquitySellCompleteRequest l_sellCompleteRequest = 
                (WEB3SuccEquitySellCompleteRequest)l_request;
            l_succCommonInfo = l_sellCompleteRequest.succCommonInfo;
            l_succPriceInfo = l_sellCompleteRequest.priceAdjustmentValueInfo;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        boolean l_blnReversingTrade = l_toOrderManager.isReversingTrade(
            l_succCommonInfo.succTradingType, 
            l_parentOrderUnit);

        if (!l_blnReversingTrade)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //�Q�j
        String l_strProductCode = null;
        if (l_request instanceof WEB3SuccEquityBuyConfirmRequest)
        {
            WEB3SuccEquityBuyConfirmRequest l_buyConfirmRequest = 
                (WEB3SuccEquityBuyConfirmRequest)l_request;
            l_strProductCode = l_buyConfirmRequest.productCode;
        }
        else if (l_request instanceof WEB3SuccEquityBuyCompleteRequest)
        {
            WEB3SuccEquityBuyCompleteRequest l_buyCompleteRequest = 
                (WEB3SuccEquityBuyCompleteRequest)l_request;
            l_strProductCode = l_buyCompleteRequest.productCode;
        }
        if (l_strProductCode != null)
        {
            EqTypeProduct l_product = (EqTypeProduct)l_parentOrderUnit.getProduct();
            if (l_product == null || !l_strProductCode.equals(l_product.getProductCode()))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02250,
                    getClass().getName() + STR_METHOD_NAME, null);
            }
        }
        
        //�R�j
        if (OrderCategEnum.SWAP_MARGIN.equals(l_parentOrderUnit.getOrderCateg()))
        {
            if (l_succPriceInfo != null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02291,
                    getClass().getName() + STR_METHOD_NAME, null);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�A�������ő�ݒ萔)<BR>
     * �A�������̍ő�ݒ萔�𒴉߂��Ă��܂�Ȃ����ǂ������`�F�b�N����B<BR>
     * <BR>
     * �A�������}�l�[�W��Impl.validate�A�������ő�ݒ萔(�����̐e�����̒����P��)��<BR>
     * delegate����B<BR>
     * @@param l_parentOrderUnit - (�e�����̒����P��)<BR>
     * �e�����̒����P�ʁB
     * @@throws WEB3BaseException
     * @@roseuid 433B833C017F
     */
    protected void validateSuccOrderMaxQuantity(EqTypeOrderUnit l_parentOrderUnit)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" validateSuccOrderMaxQuantity(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        l_toOrderManager.validateSuccOrderMaxQuantity(l_parentOrderUnit);
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (notify�\�񒍕��o�^)<BR>
     * �\�񒍕��̓o�^�����[���G���W���T�[�o�ɒʒm����B<BR>
     * <BR>
     * �P�j�@@�\�񒍕��P�ʂ̎擾<BR>
     * �@@�A�������}�l�[�W��.get�����\�񒍕��P��()�ɂāA<BR>
     * �@@�\�񒍕��P�ʂ��擾����B<BR>
     * <BR>
     * �@@[get�����\�񒍕��P��()�Ɏw�肷�����]<BR>
     * �@@�@@����ID�F�@@�q�����̒���ID<BR>
     * <BR>
     * �Q�j�@@���[���G���W���ɒ����̓o�^��ʒm����B<BR>
     * �@@�g�����������}�l�[�W��.notify���[���G���W���T�[�o()<BR>
     * �@@���R�[������B<BR>
     * <BR>
     * �@@[notify���[���G���W���T�[�o()�Ɏw�肷�����]<BR>
     * �@@�@@�����P�ʁF�@@�P�j�̖߂�l<BR>
     * �@@�@@�����F�@@NEW_OPEN_CONTRACT_ORDER<BR>
     * @@param l_lngSubOrderId - (�q�����̒���ID)<BR>
     * �q�����̒���ID�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 435EDBFC0375
     */
    protected void notifyRsvOrderRegister(long l_lngSubOrderId) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " notifyRsvOrderRegister(long)";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit = null;
        try
        {
            //�P�j�\�񒍕��P�ʂ̎擾
            l_orderUnit = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngSubOrderId);
        }
        catch (NotFoundException l_nft)
        {
            log.error(STR_METHOD_NAME, l_nft);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nft.getMessage(),
                l_nft);
        }

        //�Q�j���[���G���W���ɒ����̓o�^��ʒm����
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3EquityOrderManager l_orderMgr =
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
        
        l_orderMgr.notifyRLS(l_orderUnit, OrderManagerPersistenceContext.NEW_OPEN_CONTRACT_ORDER);
            
        log.exiting(STR_METHOD_NAME);        
    }

    /**
     * �iset�P���j<BR>
     * �����̃��X�|���X�D������P���ɒP����ݒ肷��B<BR>
     * <BR>
     * �P�j���N�G�X�g.�P�������l���null�i�}�w�l�w��j�̏ꍇ<BR>
     * �@@�@@���X�|���X�D������P���ɁA�����̃��N�G�X�g�A�_�v�^�Dget�P��()�̖߂�l���Z�b�g����B<BR>
     * <BR>
     * �Q�j��L�ȊO�̏ꍇ�A<BR>
     * �@@�@@�����������^�[������B<BR>
     * <BR>
     * @@param l_requestAdaptor - (�����������N�G�X�g�A�_�v�^)
     * @@param l_response - (���X�|���X)
     * @@throws WEB3BaseException
     */
    protected void setPrice(
        WEB3EquityOrderRequestAdapter l_requestAdaptor,
        WEB3GenResponse l_response)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setPrice(WEB3EquityOrderRequestAdapter, WEB3GenResponse)";
        log.entering(STR_METHOD_NAME);

        if (l_requestAdaptor == null || l_response == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //    �P�j���N�G�X�g.�P�������l���null�i�}�w�l�w��j�̏ꍇ 
        //  �@@�@@���X�|���X�D������P���ɁA�����̃��N�G�X�g�A�_�v�^�Dget�P��()�̖߂�l���Z�b�g����
        WEB3SuccPriceAdjustmentValueInfo l_priceAdjustmentValueInfo = null;
        if (l_requestAdaptor.request instanceof WEB3SuccEquityBuyConfirmRequest)
        {
            WEB3SuccEquityBuyConfirmRequest l_buyConfirmRequest =
                (WEB3SuccEquityBuyConfirmRequest)l_requestAdaptor.request;

            WEB3SuccEquityBuyConfirmResponse l_equityBuyConfirmResponse =
                (WEB3SuccEquityBuyConfirmResponse)l_response;

            l_priceAdjustmentValueInfo = l_buyConfirmRequest.priceAdjustmentValueInfo;
            if (l_priceAdjustmentValueInfo != null)
            {
                l_equityBuyConfirmResponse.afterAdjustmentPrice =
                    WEB3StringTypeUtility.formatNumber(l_requestAdaptor.getPrice());
            }
        }
        else if (l_requestAdaptor.request instanceof WEB3SuccEquitySellConfirmRequest)
        {
            WEB3SuccEquitySellConfirmRequest l_sellConfirmRequest =
                (WEB3SuccEquitySellConfirmRequest)l_requestAdaptor.request;

            WEB3SuccEquitySellConfirmResponse l_equitySellConfirmResponse =
                (WEB3SuccEquitySellConfirmResponse)l_response;

            l_priceAdjustmentValueInfo = l_sellConfirmRequest.priceAdjustmentValueInfo;
            if (l_priceAdjustmentValueInfo != null)
            {
                l_equitySellConfirmResponse.afterAdjustmentPrice =
                    WEB3StringTypeUtility.formatNumber(l_requestAdaptor.getPrice());
            }
        }

        //�Q�j��L�ȊO�̏ꍇ�A
        //�����������^�[������B
        log.exiting(STR_METHOD_NAME); 
    }
}
@
