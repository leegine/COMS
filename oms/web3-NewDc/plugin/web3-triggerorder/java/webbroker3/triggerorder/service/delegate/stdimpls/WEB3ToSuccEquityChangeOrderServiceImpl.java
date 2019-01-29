head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.52.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityChangeOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�������������T�[�r�XImpl(WEB3ToSuccEquityChangeOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/07 �A�C��(���u) �V�K�쐬
Revesion History : 2006/08/30 �ęԍg(���u) �d�l�ύX���f��165
Revesion History : 2007/01/11 ���G��(���u) ���f��216
Revesion History : 2007/01/19 �юu��(���u) ���f��225
Revesion History : 2007/08/20 ���g(���u) ���f��242
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3TradingTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityNewCashBasedOrderSpec;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.message.WEB3EquityCommonRequest;
import webbroker3.equity.message.WEB3EquityConfirmCommonResponse;
import webbroker3.equity.service.delegate.WEB3EquityOrderRequestAdapter;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccEqtypeChangeOrderSpec;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.message.WEB3SuccEquityBuyCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityBuyCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccEquityBuyConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityBuyConfirmResponse;
import webbroker3.triggerorder.message.WEB3SuccEquityChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityChangeCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccEquityChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityChangeConfirmResponse;
import webbroker3.triggerorder.message.WEB3SuccEquitySellCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccEquitySellCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccEquitySellConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccEquitySellConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityChangeOrderService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;


/**
 * (�i�A���j�������������T�[�r�XImpl)<BR>
 * �i�A���j�������������T�[�r�X�̎����N���X�B<BR>
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3ToSuccEquityChangeOrderServiceImpl extends WEB3ToSuccEquityOrderServiceImpl 
    implements WEB3ToSuccEquityChangeOrderService 
{
    
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccEquityChangeOrderServiceImpl.class);
    
    /**
     * @@roseuid 436ACF720242
     */
    public WEB3ToSuccEquityChangeOrderServiceImpl() 
    {
     
    }
    
    /**
     * (validate��������)<BR>
     * �i�A���j�����������������m�F���������{����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�i�A���j�������������T�[�r�X�jvalidate���������v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * 
     * @@return WEB3EquityChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4338D6E10317
     */
    protected WEB3SuccEquityChangeConfirmResponse validateChangeOrder(WEB3SuccEquityChangeConfirmRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateChangeOrder(WEB3SuccEquityChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }

        //1.1 validate( )
        l_request.validate();  //WEB3BusinessLayerException
        
        //1.2 get�����\�񒍕��P��(long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngOrderId = Long.parseLong(l_request.id);
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit = null;
        try
        {
            l_orderUnit = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);// NotFoundException, WEB3BaseException 
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage = "�e�[�u���ɊY������f�[�^������܂���B�u����ID:" + l_lngOrderId + "�v";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage,
                l_ex);
        }
        
        //1.3 validate�����\���( )
        l_orderUnit.validateOrderForChangeability();
        
        //1.4 is������( )
        boolean l_blnIsBuyOrder = l_orderUnit.isBuyOrder();
        
        WEB3EquityCommonRequest l_commonRequest = null;
        if (l_blnIsBuyOrder)
        {
            //1.5 create���t�����m�F���N�G�X�g(�i�A���j�����������������m�F���N�G�X�g, �����\�񒍕��P��Impl)
            l_commonRequest = this.createBuyConfirmRequest(l_request, l_orderUnit);
        }
        else
        {
            //1.6 create���t�����m�F���N�G�X�g(�i�A���j�����������������m�F���N�G�X�g, �����\�񒍕��P��Impl)
            l_commonRequest = this.createSellConfirmRequest(l_request, l_orderUnit);
        }
        
        //1.7 validate����(WEB3GenRequest)
        WEB3EquityConfirmCommonResponse l_confirmCommonResponse = 
            (WEB3EquityConfirmCommonResponse)this.validateOrder(l_commonRequest); //WEB3BaseException
        
        //1.8 get�������( )
        WEB3EquityTradedProduct l_tradedProduct = 
            (WEB3EquityTradedProduct)l_orderUnit.getTradedProduct();
        
        //1.9 validate����������]��(�⏕����, double, �������, �����\�񒍕��P��Impl)
        this.validateChangeTradingPower(
            this.getSubAccount(),
            Double.parseDouble(l_confirmCommonResponse.estimatedPrice),
            l_tradedProduct,
            l_orderUnit);
            
        //1.10 createResponse( )
        WEB3SuccEquityChangeConfirmResponse l_response = (WEB3SuccEquityChangeConfirmResponse)l_request.createResponse();
        
        //1.11 �i(*) �v���p�e�B�Z�b�g�j
        //�m�F���������F�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
        l_response.checkDate = l_confirmCommonResponse.checkDate;

        //�T�Z��n����F�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
        l_response.estimatedPrice = l_confirmCommonResponse.estimatedPrice;
        
        //����I���x���s��R�[�h�ꗗ�F�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
        l_response.messageSuspension = l_confirmCommonResponse.messageSuspension;
        
        //���o�������F�@@null���Z�b�g
        l_response.partContQuantity = null;
        
        if (l_confirmCommonResponse instanceof WEB3SuccEquityBuyConfirmResponse)
        {
            WEB3SuccEquityBuyConfirmResponse l_buyConfirmResponse = 
                (WEB3SuccEquityBuyConfirmResponse)l_confirmCommonResponse; 

            //�萔�����F�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
            l_response.commissionInfo = l_buyConfirmResponse.commissionInfo;

            //�m�F���P���F�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
            l_response.checkPrice = l_buyConfirmResponse.checkPrice;

            //�����L�������F�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
            l_response.expirationDate = l_buyConfirmResponse.expirationDate;

            //    ������P���F�@@
            //   �@@���N�G�X�g.�P�������l���null�i�}�w�l�w��j�̏ꍇ
            //   �@@�@@�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
            //   �@@��L�ȊO�̏ꍇ
            //   �@@�@@�@@null���Z�b�g
            WEB3SuccEquityBuyConfirmRequest l_equityBuyConfirmRequest =
                (WEB3SuccEquityBuyConfirmRequest)l_commonRequest;
            if (l_equityBuyConfirmRequest.priceAdjustmentValueInfo != null)
            {
                l_response.afterAdjustmentPrice = l_buyConfirmResponse.afterAdjustmentPrice;
            }

            //�C���T�C�_�[�x���\���t���O�F�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
            l_response.insiderWarningFlag = l_buyConfirmResponse.insiderWarningFlag;
        }
        else if (l_confirmCommonResponse instanceof WEB3SuccEquitySellConfirmResponse)
        {
            WEB3SuccEquitySellConfirmResponse l_sellConfirmResponse = 
                (WEB3SuccEquitySellConfirmResponse)l_confirmCommonResponse; 

            //�T�Z�뉿�P��(*1)�F�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
            if (!l_blnIsBuyOrder && !l_orderUnit.isReversingTrade()) //WEB3BaseException
            {
                l_response.estimatedBookPrice = l_sellConfirmResponse.estimatedBookPrice;
            }

            //�萔�����F�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
            l_response.commissionInfo = l_sellConfirmResponse.commissionInfo;

            //�m�F���P���F�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
            l_response.checkPrice = l_sellConfirmResponse.checkPrice;

            //�����L�������F�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
            l_response.expirationDate = l_sellConfirmResponse.expirationDate;

            //    ������P���F�@@
            //   �@@���N�G�X�g.�P�������l���null�i�}�w�l�w��j�̏ꍇ
            //   �@@�@@�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
            //   �@@��L�ȊO�̏ꍇ
            //   �@@�@@�@@null���Z�b�g
            WEB3SuccEquitySellConfirmRequest l_equitySellConfirmRequest =
                (WEB3SuccEquitySellConfirmRequest)l_commonRequest;
            if (l_equitySellConfirmRequest.priceAdjustmentValueInfo != null)
            {
                l_response.afterAdjustmentPrice = l_sellConfirmResponse.afterAdjustmentPrice;
            }

            //�C���T�C�_�[�x���\���t���O�F�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
            l_response.insiderWarningFlag = l_sellConfirmResponse.insiderWarningFlag;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit��������)<BR>
     * �i�A���j�����������������������������{����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�i�A���j�������������T�[�r�X�jsubmit���������v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * 
     * @@return WEB3EquityChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4338D7A50078
     */
    protected WEB3SuccEquityChangeCompleteResponse submitChangeOrder(WEB3SuccEquityChangeCompleteRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitChangeOrder(WEB3SuccEquityChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }
        
        //1.1 validate( )
        l_request.validate();  //WEB3BusinessLayerException

        //1.2 get�����\�񒍕��P��(long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngOrderId = Long.parseLong(l_request.id);
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit = null;
        try
        {
            l_orderUnit = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);// NotFoundException, WEB3BaseException 
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage = "�e�[�u���ɊY������f�[�^������܂���B�u����ID:" + l_lngOrderId + "�v";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage,
                l_ex);
        }

        //1.3 validate�����\���( )
        l_orderUnit.validateOrderForChangeability();

        //1.4 is������( )
        boolean l_blnIsBuyOrder = l_orderUnit.isBuyOrder();

        WEB3EquityCommonRequest l_commonRequest = null;
        if (l_blnIsBuyOrder)
        {
            //1.5 create���t�����������N�G�X�g(�i�A���j�����������������������N�G�X�g, �����\�񒍕��P��Impl)
            l_commonRequest = this.createBuyCompleteRequest(l_request, l_orderUnit);
        }
        else
        {
            //1.6 create���t�����������N�G�X�g(�i�A���j�����������������������N�G�X�g, �����\�񒍕��P��Impl)
            l_commonRequest = this.createSellCompleteRequest(l_request, l_orderUnit);
        }
        
        //1.7 submit����(WEB3GenRequest)
        WEB3GenResponse l_submitOrderResponse = 
            this.submitOrder(l_commonRequest); //WEB3BaseException

        //1.8 get�������( )
        WEB3EquityTradedProduct l_tradedProduct = 
            (WEB3EquityTradedProduct)l_orderUnit.getTradedProduct();

        //1.9 validate����������]��(�⏕����, double, �������, �����\�񒍕��P��Impl)
        this.validateChangeTradingPower(
            this.getSubAccount(),
            Double.parseDouble(l_request.estimatedPrice),
            l_tradedProduct,
            l_orderUnit);
            
        //1.10 get�㗝���͎�( )
        WEB3GentradeTrader l_trader = (WEB3GentradeTrader)this.getTrader(); //WEB3SystemLayerException
        
        //1.12 create�����\�񒍕��������e(long, double, double, double, double, Date, boolean, ����, Double)
        double l_dblOrderPrice = 0;
        if(!WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
        {
            l_dblOrderPrice = Double.parseDouble(l_request.limitPrice);
        }

        // is�o����܂Œ����P��( )
        boolean l_blnCarried = false;
        Date l_date = l_request.checkDate;
        if(!WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_request.expirationDateType))
        {
            l_date = l_request.expirationDate;
            l_blnCarried = true;
        }
        
        Double l_afterAdjust = null;
        if (l_request.priceAdjustmentValueInfo != null)
        {
            l_afterAdjust = new Double(
                l_request.priceAdjustmentValueInfo.getPriceAdjustmentValue());
        }
         
        WEB3ToSuccEqtypeChangeOrderSpec l_spec = WEB3ToSuccEqtypeChangeOrderSpec.createEqtypeChangeOrderSpec(
            l_orderUnit.getOrderId(),
            Double.parseDouble(l_request.orderQuantity),
            l_dblOrderPrice, 
            Double.parseDouble(l_request.estimatedPrice),
            Double.parseDouble(l_request.checkPrice),
            l_date,
            l_blnCarried,
            l_trader,
            l_afterAdjust);
            
        //1.13 submit���������\�񒍕�(SubAccount, �����\�񒍕��������e, String, �����\�񒍕��P��Impl)
        l_toOrderManager.submitEqtypeChangeOrder(
            this.getSubAccount(),
            l_spec,
            l_request.password,
            l_orderUnit);
            
        //1.14 createResponse( )
        WEB3SuccEquityChangeCompleteResponse l_response = 
            (WEB3SuccEquityChangeCompleteResponse)l_request.createResponse();

        //1.15 �i(*) �v���p�e�B�Z�b�g�j
        if (l_submitOrderResponse instanceof WEB3SuccEquityBuyCompleteResponse)
        {
            WEB3SuccEquityBuyCompleteResponse l_buyCompleteResponse = 
                (WEB3SuccEquityBuyCompleteResponse)l_submitOrderResponse;
                
            //�X�V���ԁF�@@super�N���X��submit����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
            l_response.lastUpdatedTimestamp = l_buyCompleteResponse.lastUpdatedTimestamp;
            
            //���ʔԍ��F�@@super�N���X��submit����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
            l_response.orderActionId = l_buyCompleteResponse.orderActionId;
            
            //�C���T�C�_�[�x���\���t���O�F�@@super�N���X��submit����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
            l_response.insiderWarningFlag = l_buyCompleteResponse.insiderWarningFlag;

            //�����L�������F�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
            l_response.expirationDate = l_buyCompleteResponse.expirationDate;
        }
        if (l_submitOrderResponse instanceof WEB3SuccEquitySellCompleteResponse)
        {
            WEB3SuccEquitySellCompleteResponse l_sellCompleteResponse = 
                (WEB3SuccEquitySellCompleteResponse)l_submitOrderResponse;
                
            //�X�V���ԁF�@@super�N���X��submit����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
            l_response.lastUpdatedTimestamp = l_sellCompleteResponse.lastUpdatedTimestamp;
            
            //���ʔԍ��F�@@super�N���X��submit����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
            l_response.orderActionId = l_sellCompleteResponse.orderActionId;
            
            //�C���T�C�_�[�x���\���t���O�F�@@super�N���X��submit����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
            l_response.insiderWarningFlag = l_sellCompleteResponse.insiderWarningFlag;

            //�����L�������F�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
            l_response.expirationDate = l_sellCompleteResponse.expirationDate;
        }

        //�A�������ݒ�t���O�F�@@false�i�Œ�j
        l_response.succSettingFlag = false;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * �i�A���j���������������������s����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate��������()�܂���submit��������()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4338D6E10346
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
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

        if (l_request instanceof WEB3SuccEquityChangeConfirmRequest)
        {
            //validate��������)()
            l_response = 
                this.validateChangeOrder((WEB3SuccEquityChangeConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3SuccEquityChangeCompleteRequest)
        {
            //submit��������()()
            l_response = 
                this.submitChangeOrder((WEB3SuccEquityChangeCompleteRequest)l_request);
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
     * (create���t�����m�F���N�G�X�g)<BR>
     * �A�������́A���t�����m�F���N�G�X�g���쐬����B<BR>
     * <BR>
     * �P�j�@@�߂�l�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@���ʃ��N�G�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@this.set�����������ʃ��N�G�X�g(���������߂�l�C���X�^���X, �����̃��N�G�X�g�f�[�^)��<BR>
     * �@@�@@�R�[������B<BR>
     * <BR>
     * �R�j�@@�����N�G�X�g�ŗL�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�������������t�����m�F���N�G�X�g��<BR>
     * �@@�@@�����R�[�h�F�@@�����̒����Ώے����P��.get����().�����R�[�h<BR>
     * �@@�@@�s��R�[�h�F�@@�����̒����Ώے����P��.get�s��().�s��R�[�h<BR>
     * �@@�@@�����敪�F�@@�����f�[�^�A�_�v�^.get�����敪(�����̒����Ώے����P��.getTaxType)<BR>
     * �@@�@@����敪�F�@@"�������t����"<BR>
     * <BR>
     * �@@�@@���i�A���j�����������t�����m�F���N�G�X�g��<BR>
     * �@@�@@�A���������ʏ��F�@@�����̒����Ώے����P��.create�A���������ʏ��()<BR>
     * �@@�@@�P�������l���F�@@�����̃��N�G�X�g�f�[�^�̓����v���p�e�B<BR>
     * <BR>
     * �S�j�@@�߂�l�C���X�^���X��ԋp����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * 
     * @@param l_orderUnit - (�����Ώے����P��)<BR>
     * �����Ώے����P�ʁB<BR>
     * @@return WEB3SuccEquityBuyConfirmRequest
     * @@throws WEB3BaseException
     * @@roseuid 433B543603D9
     */
    protected WEB3SuccEquityBuyConfirmRequest createBuyConfirmRequest(
        WEB3SuccEquityChangeConfirmRequest l_request, 
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createBuyConfirmRequest(" +
            "WEB3SuccEquityChangeConfirmRequest" +
            ", WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }

        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�����Ώے����P�ʂ����w��(null)�ł��B");
        }

        //�P�j�@@�߂�l�C���X�^���X�𐶐�����B
        WEB3SuccEquityBuyConfirmRequest l_succEquityBuyConfirmRequest = 
            new WEB3SuccEquityBuyConfirmRequest();

        //�Q�j�@@���ʃ��N�G�X�g�̃v���p�e�B���Z�b�g����B
        //�@@�@@this.set�����������ʃ��N�G�X�g(���������߂�l�C���X�^���X, �����̃��N�G�X�g�f�[�^)��
        //�@@�@@�R�[������B
        this.setEquityCommonRequest(l_succEquityBuyConfirmRequest, l_request);

        //�R�j�@@�����N�G�X�g�ŗL�̃v���p�e�B���Z�b�g����B
        //�@@�@@�������������t�����m�F���N�G�X�g��
        //�@@�@@�����R�[�h�F�@@�����̒����Ώے����P��.get����().�����R�[�h
        EqTypeProduct l_eqtypeProduct = (EqTypeProduct)l_orderUnit.getProduct();
        if (l_eqtypeProduct == null)
        {
            String l_strMessage = "�e�[�u���ɊY������f�[�^������܂���B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage);
        }
        l_succEquityBuyConfirmRequest.productCode = l_eqtypeProduct.getProductCode();
        
        //�@@�@@�s��R�[�h�F�@@�����̒����Ώے����P��.get�s��().�s��R�[�h
        Market l_market = null;
        try
        {
            l_market = l_orderUnit.getMarket();  //NotFoundException
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage = "�e�[�u���ɊY������f�[�^������܂���B";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage,
                l_ex);
        }
        if (l_market != null)
        {
            l_succEquityBuyConfirmRequest.marketCode = l_market.getMarketCode();
        }
        
        //�����敪�F�@@�����f�[�^�A�_�v�^.get�����敪(�����̒����Ώے����P��.getTaxType)
        l_succEquityBuyConfirmRequest.taxType = 
            WEB3EquityDataAdapter.getTaxType(l_orderUnit.getTaxType());
        
        //�@@�@@����敪�F�@@"�������t����"
        l_succEquityBuyConfirmRequest.tradingType = WEB3TradingTypeDef.BUY_ORDER;

        //�@@�@@���i�A���j�����������t�����m�F���N�G�X�g��
        //�@@�@@�A���������ʏ��F�@@�����̒����Ώے����P��.create�A���������ʏ��()
        l_succEquityBuyConfirmRequest.succCommonInfo = l_orderUnit.createSuccCommonInfo();
        
        //�@@�@@�P�������l���F�@@�����̃��N�G�X�g�f�[�^�̓����v���p�e�B 
        l_succEquityBuyConfirmRequest.priceAdjustmentValueInfo = l_request.priceAdjustmentValueInfo;

        //�S�j�@@�߂�l�C���X�^���X��ԋp����B

        log.exiting(STR_METHOD_NAME);
        return l_succEquityBuyConfirmRequest;
    }
    
    /**
     * (create���t�����m�F���N�G�X�g)<BR>
     * �A�������́A���t�����m�F���N�G�X�g���쐬����B<BR>
     * <BR>
     * �P�j�@@�߂�l�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@���ʃ��N�G�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@this.set�����������ʃ��N�G�X�g(���������߂�l�C���X�^���X, �����̃��N�G�X�g�f�[�^)��<BR>
     * �@@�@@�R�[������B<BR>
     * <BR>
     * �R�j�@@�����N�G�X�g�ŗL�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�������������t�����m�F���N�G�X�g��<BR>
     * �@@�@@ID�i�����YID�j�F�@@���Ύ��(*1)�̏ꍇ�́Anull���Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���Ύ���łȂ��ꍇ�́A�����|�W�V�����}�l�[�W��.get�ۗL���Y(<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@����ID, �⏕����ID, ����ID, �ŋ敪)�Ŏ擾����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ۗL���Y.���YID���Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��get�ۗL���Y()�̈����́A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����̒����Ώے����P�ʂ̓����v���p�e�B���Z�b�g�B<BR>
     * �@@�@@�s��R�[�h�F�@@�����̒����Ώے����P��.get�s��().�s��R�[�h<BR>
     * <BR>
     * �@@�@@(*1)���Ύ�����ǂ����̔���<BR>
     * �@@�@@�@@�����̒����Ώے����P��.is���Δ������()==true�̏ꍇ�́A���Ύ���ł���B<BR>
     * �@@�@@�@@�ȊO�A���Ύ���łȂ��B<BR>
     * <BR>
     * �@@�@@���i�A���j�����������t�����m�F���N�G�X�g��<BR>
     * �@@�@@�A���������ʏ��F�@@�����̒����Ώے����P��.create�A���������ʏ��()<BR>
     * �@@�@@�P�������l���F�@@�����̃��N�G�X�g�f�[�^�̓����v���p�e�B<BR>
     * <BR>
     * �S�j�@@�߂�l�C���X�^���X��ԋp����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * 
     * @@param l_orderUnit - (�����Ώے����P��)<BR>
     * �����Ώے����P�ʁB<BR>
     * @@return WEB3SuccEquitySellConfirmRequest
     * @@throws WEB3BaseException
     * @@roseuid 433BB093020C
     */
    protected WEB3SuccEquitySellConfirmRequest createSellConfirmRequest(
        WEB3SuccEquityChangeConfirmRequest l_request, 
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createSellConfirmRequest(" +
            "WEB3SuccEquityChangeConfirmRequest" +
            ", WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }

        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�����Ώے����P�ʂ����w��(null)�ł��B");
        }

        // �P�j�@@�߂�l�C���X�^���X�𐶐�����B
        WEB3SuccEquitySellConfirmRequest l_succEquitySellConfirmRequest =
            new WEB3SuccEquitySellConfirmRequest();

        // �Q�j�@@���ʃ��N�G�X�g�̃v���p�e�B���Z�b�g����B
        // �@@�@@this.set�����������ʃ��N�G�X�g(���������߂�l�C���X�^���X, �����̃��N�G�X�g�f�[�^)��
        // �@@�@@�R�[������B
        this.setEquityCommonRequest(l_succEquitySellConfirmRequest, l_request);

        // �R�j�@@�����N�G�X�g�ŗL�̃v���p�e�B���Z�b�g����B
        // �@@�@@�������������t�����m�F���N�G�X�g��
        // �@@�@@ID�i�����YID�j�F�@@���Ύ��(*1)�̏ꍇ�́Anull���Z�b�g�B
        //   ���Ύ���łȂ��ꍇ�́A�����|�W�V�����}�l�[�W��.get�ۗL���Y(
        //   ����ID, �⏕����ID, ����ID, �ŋ敪)�Ŏ擾����
        //   �ۗL���Y.���YID���Z�b�g�B
        //   ��get�ۗL���Y()�̈����́A
        //   �����̒����Ώے����P�ʂ̓����v���p�e�B���Z�b�g�B
        if (l_orderUnit.isReversingTrade()) //WEB3BaseException
        {
            l_succEquitySellConfirmRequest.id = null;
        }
        else
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            RsvEqOrderUnitRow l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow)l_orderUnit.getDataSourceObject();
            Asset l_asset = l_positionManager.getAsset(
                l_orderUnit.getAccountId(),
                l_orderUnit.getSubAccountId(),
                l_rsvEqOrderUnitRow.getProductId(),
                l_orderUnit.getTaxType()); //WEB3BaseException
            if (l_asset == null)
            {
                String l_strMessage = "�e�[�u���ɊY������f�[�^������܂���B";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_strMessage);
            }
                            
            l_succEquitySellConfirmRequest.id = l_asset.getAssetId() + "";
        }
        
        // �@@�@@�s��R�[�h�F�@@�����̒����Ώے����P��.get�s��().�s��R�[�h
        Market l_market = null;
        try
        {
            l_market = l_orderUnit.getMarket();  //NotFoundException
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage = "�e�[�u���ɊY������f�[�^������܂���B";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage,
                l_ex);
        }        
        if (l_market != null)
        {
            l_succEquitySellConfirmRequest.marketCode = l_market.getMarketCode(); 
        }
        
        // �@@�@@���i�A���j�����������t�����m�F���N�G�X�g��
        // �@@�@@�A���������ʏ��F�@@�����̒����Ώے����P��.create�A���������ʏ��()
        l_succEquitySellConfirmRequest.succCommonInfo = l_orderUnit.createSuccCommonInfo();
        
        // �@@�@@�P�������l���F�@@�����̃��N�G�X�g�f�[�^�̓����v���p�e�B
        l_succEquitySellConfirmRequest.priceAdjustmentValueInfo = l_request.priceAdjustmentValueInfo;

        // �S�j�@@�߂�l�C���X�^���X��ԋp����B

        log.exiting(STR_METHOD_NAME);
        return l_succEquitySellConfirmRequest;
    }
    
    /**
     * (create���t�����������N�G�X�g)<BR>
     * �A�������́A���t�����������N�G�X�g���쐬����B<BR>
     * <BR>
     * �P�j�@@�߂�l�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@���ʃ��N�G�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@this.set�����������ʃ��N�G�X�g(���������߂�l�C���X�^���X, �����̃��N�G�X�g�f�[�^)��<BR>
     * �@@�@@�R�[������B<BR>
     * <BR>
     * �R�j�@@�����N�G�X�g�ŗL�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�������������t�����������N�G�X�g��<BR>
     * �@@�@@����ID�F�@@�����̒����Ώے����P��.����ID<BR>
     * �@@�@@�����R�[�h�F�@@�����̒����Ώے����P��.get����().�����R�[�h<BR>
     * �@@�@@�s��R�[�h�F�@@�����̒����Ώے����P��.get�s��().�s��R�[�h<BR>
     * �@@�@@�����敪�F�@@�����f�[�^�A�_�v�^.get�����敪(�����̒����Ώے����P��.getTaxType) <BR>
     * �@@�@@����敪�F�@@"�������t����"<BR>
     * �@@�@@�m�F���P���F�@@�����̃��N�G�X�g�f�[�^�̓����v���p�e�B<BR>
     * �@@�@@�m�F���������F�@@�����̃��N�G�X�g�f�[�^�̓����v���p�e�B<BR>
     * �@@�@@�Ïؔԍ��F�@@�����̃��N�G�X�g�f�[�^�̓����v���p�e�B<BR>
     * <BR>
     * �@@�@@���i�A���j�����������t�����������N�G�X�g��<BR>
     * �@@�@@�A���������ʏ��F�@@�����̒����Ώے����P��.create�A���������ʏ��()<BR>
     * �@@�@@�P�������l���F�@@�����̃��N�G�X�g�f�[�^�̓����v���p�e�B<BR>
     * �@@�@@������P���F�@@�����̃��N�G�X�g�f�[�^�̓����v���p�e�B<BR>
     * <BR>
     * �S�j�@@�߂�l�C���X�^���X��ԋp����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * 
     * @@param l_orderUnit - (�����Ώے����P��)<BR>
     * �����Ώے����P�ʁB<BR>
     * @@return WEB3SuccEquityBuyCompleteRequest
     * @@throws WEB3BaseException
     * @@roseuid 433BBAF00085
     */
    protected WEB3SuccEquityBuyCompleteRequest createBuyCompleteRequest(
        WEB3SuccEquityChangeCompleteRequest l_request, 
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createBuyCompleteRequest(" +
            "WEB3SuccEquityChangeCompleteRequest" +
            ", WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }

        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�����Ώے����P�ʂ����w��(null)�ł��B");
        }

        //�P�j�@@�߂�l�C���X�^���X�𐶐�����B
        WEB3SuccEquityBuyCompleteRequest l_succEquityBuyCompleteRequest 
            = new WEB3SuccEquityBuyCompleteRequest();

        //�Q�j�@@���ʃ��N�G�X�g�̃v���p�e�B���Z�b�g����B
        //�@@�@@this.set�����������ʃ��N�G�X�g(���������߂�l�C���X�^���X, �����̃��N�G�X�g�f�[�^)��
        //�@@�@@�R�[������B
        this.setEquityCommonRequest(l_succEquityBuyCompleteRequest, l_request);

        //�R�j�@@�����N�G�X�g�ŗL�̃v���p�e�B���Z�b�g����B
        //�@@�@@�������������t�����������N�G�X�g��
        //�@@�@@����ID�F�@@�����̒����Ώے����P��.����ID
        l_succEquityBuyCompleteRequest.orderId = l_orderUnit.getOrderId() + ""; 
        
        //�@@�@@�����R�[�h�F�@@�����̒����Ώے����P��.get����().�����R�[�h
        EqTypeProduct l_eqtypeProduct = (EqTypeProduct)l_orderUnit.getProduct();
        if (l_eqtypeProduct == null)
        {
            String l_strMessage = "�e�[�u���ɊY������f�[�^������܂���B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage);
        }
        l_succEquityBuyCompleteRequest.productCode = l_eqtypeProduct.getProductCode();
        
        //�@@�@@�s��R�[�h�F�@@�����̒����Ώے����P��.get�s��().�s��R�[�h
        Market l_market = null;
        try
        {
            l_market = l_orderUnit.getMarket();  //NotFoundException
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage = "�e�[�u���ɊY������f�[�^������܂���B";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage,
                l_ex);
        }
        if (l_market != null)
        {
            l_succEquityBuyCompleteRequest.marketCode = l_market.getMarketCode();
        }        
        
        //�����敪�F�@@�����f�[�^�A�_�v�^.get�����敪(�����̒����Ώے����P��.getTaxType) 
        l_succEquityBuyCompleteRequest.taxType = 
            WEB3EquityDataAdapter.getTaxType(l_orderUnit.getTaxType());
        
        //�@@�@@����敪�F�@@"�������t����"
        l_succEquityBuyCompleteRequest.tradingType = WEB3TradingTypeDef.BUY_ORDER;
        
        //�@@�@@�m�F���P���F�@@�����̃��N�G�X�g�f�[�^�̓����v���p�e�B
        l_succEquityBuyCompleteRequest.checkPrice = l_request.checkPrice;
        
        //�@@�@@�m�F���������F�@@�����̃��N�G�X�g�f�[�^�̓����v���p�e�B
        l_succEquityBuyCompleteRequest.checkDate = l_request.checkDate;
        
        //�@@�@@�Ïؔԍ��F�@@�����̃��N�G�X�g�f�[�^�̓����v���p�e�B
        l_succEquityBuyCompleteRequest.password = l_request.password;

        //�@@�@@���i�A���j�����������t�����������N�G�X�g��
        //�@@�@@�A���������ʏ��F�@@�����̒����Ώے����P��.create�A���������ʏ��()
        l_succEquityBuyCompleteRequest.succCommonInfo = l_orderUnit.createSuccCommonInfo();
        
        //�@@�@@�P�������l���F�@@�����̃��N�G�X�g�f�[�^�̓����v���p�e�B 
        l_succEquityBuyCompleteRequest.priceAdjustmentValueInfo = l_request.priceAdjustmentValueInfo;

        //    ������P���F�@@�����̃��N�G�X�g�f�[�^�̓����v���p�e�B
        l_succEquityBuyCompleteRequest.afterAdjustmentPrice = l_request.afterAdjustmentPrice;

        //�S�j�@@�߂�l�C���X�^���X��ԋp����B

        log.exiting(STR_METHOD_NAME);
        return l_succEquityBuyCompleteRequest;
    }
    
    /**
     * (create���t�����������N�G�X�g)<BR>
     * �A�������́A���t�����������N�G�X�g���쐬����B<BR>
     * <BR>
     * �P�j�@@�߂�l�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@���ʃ��N�G�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@this.set�����������ʃ��N�G�X�g(���������߂�l�C���X�^���X, �����̃��N�G�X�g�f�[�^)��<BR>
     * �@@�@@�R�[������B<BR>
     * <BR>
     * �R�j�@@�����N�G�X�g�ŗL�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�������������t�����������N�G�X�g��<BR>
     * �@@�@@����ID�F�@@�����̒����Ώے����P��.����ID<BR>
     * �@@�@@ID�i�����YID�j�F�@@���Ύ��(*1)�̏ꍇ�́Anull���Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���Ύ���łȂ��ꍇ�́A�����|�W�V�����}�l�[�W��.get�ۗL���Y(<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@����ID, �⏕����ID, ����ID, �ŋ敪)�Ŏ擾����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ۗL���Y.���YID���Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��get�ۗL���Y()�̈����́A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����̒����Ώے����P�ʂ̓����v���p�e�B���Z�b�g�B<BR>
     * �@@�@@�s��R�[�h�F�@@�����̒����Ώے����P��.get�s��().�s��R�[�h<BR>
     * �@@�@@�m�F���P���F�@@�����̃��N�G�X�g�f�[�^�̓����v���p�e�B<BR>
     * �@@�@@�m�F���������F�@@�����̃��N�G�X�g�f�[�^�̓����v���p�e�B<BR>
     * �@@�@@�Ïؔԍ��F�@@�����̃��N�G�X�g�f�[�^�̓����v���p�e�B<BR>
     * <BR>
     * �@@�@@(*1)���Ύ�����ǂ����̔���<BR>
     * �@@�@@�@@�����̒����Ώے����P��.is���Δ������()==true�̏ꍇ�́A���Ύ���ł���B<BR>
     * �@@�@@�@@�ȊO�A���Ύ���łȂ��B<BR>
     * <BR>
     * �@@�@@���i�A���j�����������t�����������N�G�X�g��<BR>
     * �@@�@@�A���������ʏ��F�@@�����̒����Ώے����P��.create�A���������ʏ��()<BR>
     * �@@�@@�P�������l���F�@@�����̃��N�G�X�g�f�[�^�̓����v���p�e�B<BR>
     * �@@�@@������P���F�@@�����̃��N�G�X�g�f�[�^�̓����v���p�e�B<BR>
     * <BR>
     * �S�j�@@�߂�l�C���X�^���X��ԋp����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * 
     * @@param l_orderUnit - (�����Ώے����P��)<BR>
     * �����Ώے����P�ʁB<BR>
     * @@return WEB3SuccEquitySellCompleteRequest
     * @@throws WEB3BaseException
     * @@roseuid 433BBB150392
     */
    protected WEB3SuccEquitySellCompleteRequest createSellCompleteRequest(
        WEB3SuccEquityChangeCompleteRequest l_request, 
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createSellCompleteRequest(" +
            "WEB3SuccEquityChangeCompleteRequest" +
            ", WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }

        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�����Ώے����P�ʂ����w��(null)�ł��B");
        }

        // �P�j�@@�߂�l�C���X�^���X�𐶐�����B
        WEB3SuccEquitySellCompleteRequest l_succEquitySellCompleteRequest = 
            new WEB3SuccEquitySellCompleteRequest();

        // �Q�j�@@���ʃ��N�G�X�g�̃v���p�e�B���Z�b�g����B
        // �@@�@@this.set�����������ʃ��N�G�X�g(���������߂�l�C���X�^���X, �����̃��N�G�X�g�f�[�^)��
        // �@@�@@�R�[������B
        this.setEquityCommonRequest(l_succEquitySellCompleteRequest, l_request);

        // �R�j�@@�����N�G�X�g�ŗL�̃v���p�e�B���Z�b�g����B
        // �@@�@@�������������t�����������N�G�X�g��
        // �@@�@@����ID�F�@@�����̒����Ώے����P��.����ID
        l_succEquitySellCompleteRequest.orderId = l_orderUnit.getOrderId() + "";
        
        // �@@�@@ID�i�����YID�j�F�@@���Ύ��(*1)�̏ꍇ�́Anull���Z�b�g�B
        //   ���Ύ���łȂ��ꍇ�́A�����|�W�V�����}�l�[�W��.get�ۗL���Y(
        //   ����ID, �⏕����ID, ����ID, �ŋ敪)�Ŏ擾����
        //   �ۗL���Y.���YID���Z�b�g�B
        if (l_orderUnit.isReversingTrade()) //WEB3BaseException
        {
            l_succEquitySellCompleteRequest.id = null;
        }
        else
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            RsvEqOrderUnitRow l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow)l_orderUnit.getDataSourceObject();
            Asset l_asset = l_positionManager.getAsset(
                l_orderUnit.getAccountId(),
                l_orderUnit.getSubAccountId(),
                l_rsvEqOrderUnitRow.getProductId(),
                l_orderUnit.getTaxType()); //WEB3BaseException
            if (l_asset == null)
            {
                String l_strMessage = "�e�[�u���ɊY������f�[�^������܂���B";
                log.error(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_strMessage);
            }
                            
            l_succEquitySellCompleteRequest.id = l_asset.getAssetId() + "";
        }
        
        // �@@�@@�s��R�[�h�F�@@�����̒����Ώے����P��.get�s��().�s��R�[�h
        Market l_market = null;
        try
        {
            l_market = l_orderUnit.getMarket();  //NotFoundException
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage = "�e�[�u���ɊY������f�[�^������܂���B";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage,
                l_ex);
        }
        if (l_market != null)
        {
            l_succEquitySellCompleteRequest.marketCode = l_market.getMarketCode();
        }        
        
        // �@@�@@�m�F���P���F�@@�����̃��N�G�X�g�f�[�^�̓����v���p�e�B
        l_succEquitySellCompleteRequest.checkPrice = l_request.checkPrice;
        
        // �@@�@@�m�F���������F�@@�����̃��N�G�X�g�f�[�^�̓����v���p�e�B
        l_succEquitySellCompleteRequest.checkDate = l_request.checkDate;
        
        // �@@�@@�Ïؔԍ��F�@@�����̃��N�G�X�g�f�[�^�̓����v���p�e�B
        l_succEquitySellCompleteRequest.password = l_request.password;

        // �@@�@@(*1)���Ύ�����ǂ����̔���
        // �@@�@@�@@�����̒����Ώے����P��.is���Δ������()==true�̏ꍇ�́A���Ύ���ł���B
        // �@@�@@�@@�ȊO�A���Ύ���łȂ��B

        // �@@�@@���i�A���j�����������t�����������N�G�X�g��
        // �@@�@@�A���������ʏ��F�@@�����̒����Ώے����P��.create�A���������ʏ��()
        l_succEquitySellCompleteRequest.succCommonInfo = l_orderUnit.createSuccCommonInfo();
        
        // �@@�@@�P�������l���F�@@�����̃��N�G�X�g�f�[�^�̓����v���p�e�B 
        l_succEquitySellCompleteRequest.priceAdjustmentValueInfo = l_request.priceAdjustmentValueInfo;;

        //    ������P���F�@@�����̃��N�G�X�g�f�[�^�̓����v���p�e�B
        l_succEquitySellCompleteRequest.afterAdjustmentPrice = l_request.afterAdjustmentPrice;
        
        // �S�j�@@�߂�l�C���X�^���X��ԋp����B

        log.exiting(STR_METHOD_NAME);
        return l_succEquitySellCompleteRequest;
    }
    
    /**
     * (set�����������ʃ��N�G�X�g)<BR>
     * �w�肳�ꂽ�u�ioutput�j�����������ʃ��N�G�X�g�v�̃C���X�^���X�ɁA�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �ȉ��̃v���p�e�B�ɁA�u�iinput�j�����������ʃ��N�G�X�g�v�̓����v���p�e�B�̒l���Z�b�g����B<BR>
     * <BR>
     * [�Ώۃv���p�e�B]<BR>
     * �@@��������<BR>
     * �@@�����P���敪<BR>
     * �@@�����P��<BR>
     * �@@�l�i����<BR>
     * �@@���s����<BR>
     * �@@���������敪<BR>
     * �@@�����L������<BR>
     * �@@���������敪<BR>
     * �@@�t�w�l�p���������P��<BR>
     * �@@�t�w�l�p�����������Z�q<BR>
     * �@@W�w�l�p���������P��<BR>
     * �@@W�w�l�p�����������Z�q<BR>
     * �@@W�w�l�p�����P���敪<BR>
     * �@@W�w�l�p�����P��<BR>
     * @@param l_outputRequest - (�ioutput�j�����������ʃ��N�G�X�g)<BR>
     * �ioutput�j�����������ʃ��N�G�X�g�B<BR>
     * @@param l_intputRequest - (�iinput�j�����������ʃ��N�G�X�g�B)<BR>
     * �iinput�j�����������ʃ��N�G�X�g�B<BR>
     * �i�i�A���j�����������������m�F���N�G�X�g or �i�A���j�����������������������N�G�X�g�j<BR>
     * @@throws WEB3BaseException
     * @@roseuid 433B68E103B1
     */
    protected void setEquityCommonRequest(
        WEB3EquityCommonRequest l_outputRequest, 
        WEB3EquityCommonRequest l_intputRequest)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " setEquityCommonRequest(" +
            "WEB3EquityCommonRequest" +
            ", WEB3EquityCommonRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_intputRequest == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�iinput�j�����������ʃ��N�G�X�g�����w��(null)�ł��B");
        }

        if (l_outputRequest == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�ioutput�j�����������ʃ��N�G�X�g�����w��(null)�ł��B");
        }

        //�@@��������
        l_outputRequest.orderQuantity = l_intputRequest.orderQuantity;
        
        //�@@�����P���敪
        l_outputRequest.orderPriceDiv = l_intputRequest.orderPriceDiv;
        
        //�@@�����P��
        l_outputRequest.limitPrice = l_intputRequest.limitPrice;
        
        //�@@�l�i����
        l_outputRequest.priceCondType = l_intputRequest.priceCondType;
        
        //�@@���s����
        l_outputRequest.execCondType = l_intputRequest.execCondType;
        
        //�@@���������敪
        l_outputRequest.expirationDateType = l_intputRequest.expirationDateType;
        
        //�@@�����L������
        l_outputRequest.expirationDate = l_intputRequest.expirationDate;
        
        //�@@���������敪
        l_outputRequest.orderCondType = l_intputRequest.orderCondType;
        
        //�@@�t�w�l�p���������P��
        l_outputRequest.stopOrderCondPrice = l_intputRequest.stopOrderCondPrice;
        
        //�@@�t�w�l�p�����������Z�q
        l_outputRequest.stopOrderCondOperator = l_intputRequest.stopOrderCondOperator;
        
        //�@@W�w�l�p���������P��
        l_outputRequest.wlimitOrderCondPrice = l_intputRequest.wlimitOrderCondPrice;
        
        //�@@W�w�l�p�����������Z�q
        l_outputRequest.wlimitOrderCondOperator = l_intputRequest.wlimitOrderCondOperator;
        
        //�@@W�w�l�p�����P���敪
        l_outputRequest.wLimitOrderPriceDiv = l_intputRequest.wLimitOrderPriceDiv;
        
        //�@@W�w�l�p�����P��
        l_outputRequest.wLimitPrice = l_intputRequest.wLimitPrice;

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate����]��)<BR>
     * ����]�͂��`�F�b�N���A����]�͌��ʃI�u�W�F�N�g��ԋp����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * ����������return����B�i�J�������j<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param l_orderSpec - (�����������e)<BR>
     * �����������e�I�u�W�F�N�g�B<BR>
     * @@param l_blnUpdateFlg - (�]�͍X�V�t���O)<BR>
     * �]�͍X�V�t���O�B<BR>
     * �ifalse�F�@@�m�F���Atrue�F�@@�������j<BR>
     * @@param l_tradedProduct - (�������)<BR>
     * ��������I�u�W�F�N�g�B<BR>
     * @@return WEB3TPTradingPowerResult
     * @@roseuid 433B7CA501CD
     */
    protected WEB3TPTradingPowerResult validateTradingPower(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3EquityNewCashBasedOrderSpec l_orderSpec, 
        boolean l_blnUpdateFlg, 
        WEB3EquityTradedProduct l_tradedProduct) 
    {
        return null;
    }
    
    /**
     * (validate����������]��)<BR>
     * ����]�͂��`�F�b�N���A����]�͌��ʃI�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * �����̒����O�����P��.is������()==false�i�������j�̏ꍇ�́A<BR>
     * ����������return����B�inull��ԋp�j<BR>
     * <BR>
     * �����̒����O�����P��.is������()==true�i�������j�̏ꍇ�́A<BR>
     * �]�̓`�F�b�N�����{���镔�X(*2)�̏ꍇ�̂݁A<BR>
     * ���t�\�z(*1)�ƈ����̊T�Z��n������r���A<BR>
     * �i�T�Z��n��� > ���t�\�z(*1)�j�̏ꍇ�́A<BR>
     * �u����]�͕s���v�̗�O��throw����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_01306<BR>
     * <BR>
     * �ȊO�Anull��ԋp����B<BR>
     * <BR>
     * (*1)���t�\�z<BR>
     * ����]�̓T�[�r�X.get�������t�\�z�`�A�������`(<BR>
     * �����̕⏕����, �����̎������.��n��, <BR>
     * �����̒����O�����P��.�T�Z��n���)�̖߂�l���A<BR>
     * ���t�\�z�Ƃ���B<BR>
     * <BR>
     * (*2)�]�̓`�F�b�N�����{���镔�X<BR>
     * �A�������}�l�[�W��Impl.is�]�̓`�F�b�N���{���X()==true�̏ꍇ�́A<BR>
     * �]�̓`�F�b�N�����{���镔�X�ł���Ɣ��肷��B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param l_dblEstimatedPrice - (�T�Z��n���)<BR>
     * �T�Z��n����B<BR>
     * @@param l_tradedProduct - (�������)<BR>
     * ��������I�u�W�F�N�g�B<BR>
     * @@param l_orderUnit - (�����O�����P��)<BR>
     * �����Ώۂ̊����\�񒍕��P�ʃI�u�W�F�N�g�B<BR>
     * @@return WEB3TPTradingPowerResult
     * @@throws WEB3BaseException
     * @@roseuid 433B7CD70392
     */
    protected WEB3TPTradingPowerResult validateChangeTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        double l_dblEstimatedPrice, 
        WEB3EquityTradedProduct l_tradedProduct, 
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateChangeTradingPower(" +
            "WEB3GentradeSubAccount" +
            ", double" +
            ", WEB3EquityTradedProduct" +
            ", WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        if (l_tradedProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "��������I�u�W�F�N�g�����w��(null)�ł��B");
        }

        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�����Ώۂ̊����\�񒍕��P�ʃI�u�W�F�N�g�����w��(null)�ł��B");
        }

        if (!l_orderUnit.isBuyOrder())
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
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow)l_orderUnit.getDataSourceObject();
        Double l_estimatedPrice = null;
        if (l_rsvEqOrderUnitRow != null && !l_rsvEqOrderUnitRow.getEstimatedPriceIsNull())
        {
            l_estimatedPrice = new Double(l_rsvEqOrderUnitRow.getEstimatedPrice());
        }
        double l_dblBuyPower = l_trdingPowerService.getSuccEquityTradingPower(
            l_subAccount,
            l_tradedProduct.getDailyDeliveryDate(),
            l_estimatedPrice);
        if (l_dblEstimatedPrice > l_dblBuyPower)
        {
            String l_strMessage = "����]�̓`�F�b�N�G���[�B"
                + "�u�T�Z��n���(" + l_dblEstimatedPrice 
                + ") > ���t�\�z(" + l_dblBuyPower + ")�v";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage);
        }

        //�ȊO�Anull��ԋp����B

        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (validate�A�������ő�ݒ萔)<BR>
     * �A�������̍ő�ݒ萔�𒴉߂��Ă��܂�Ȃ����ǂ������`�F�b�N����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * ����������return����B�i�J�������j<BR>
     * @@param l_parentOrderUnit - (�e�����̒����P��)<BR>
     * �e�����̒����P�ʁB<BR>
     * @@roseuid 433B846303C1
     */
    protected void validateSuccOrderMaxQuantity(EqTypeOrderUnit l_parentOrderUnit) 
    {
    }
    
    /**
     * (submit������������)<BR>
     * �\�񒍕���o�^����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * ����������return����B�i�J�������j<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param l_orderSpec - (�����������e)<BR>
     * �����������e�I�u�W�F�N�g�B<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * �\�񒍕��̒���ID�B<BR>
     * @@param l_strTradingPassword - (����p�X���[�h)<BR>
     * ����p�X���[�h�B<BR>
     * @@param l_requestAdaptor - (�i�A���j�����������N�G�X�g�A�_�v�^)<BR>
     * �i�A���j�����������N�G�X�g�A�_�v�^�B<BR>
     * @@roseuid 433B8A6B0315
     */
    protected void submitNewCashBasedOrder(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3EquityNewCashBasedOrderSpec l_orderSpec, 
        long l_lngOrderId, 
        String l_strTradingPassword, 
        WEB3EquityOrderRequestAdapter l_requestAdaptor) 
    {
    }
    
    /**
     * (notify�\�񒍕��o�^)<BR>
     * �\�񒍕��̓o�^�����[���G���W���T�[�o�ɒʒm����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * ����������return����B�i�J�������j<BR>
     * @@param l_lngRsvOrderId - (�q�����̒���ID)<BR>
     * �q�����̒���ID�B<BR>
     */
    protected void notifyRsvOrderRegister(long l_lngRsvOrderId) throws WEB3BaseException
    {
    }
}
@
