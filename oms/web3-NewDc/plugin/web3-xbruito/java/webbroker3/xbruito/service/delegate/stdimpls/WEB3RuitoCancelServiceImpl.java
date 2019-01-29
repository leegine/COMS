head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ�����T�[�r�XImpl (WEB3RuitoCancelServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/09 ���u�� (���u) �V�K�쐬
                   2004/12/06 ��O�� (���u) �c�Ή�
*/
package webbroker3.xbruito.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BuySellTypeDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.xbruito.WEB3RuitoCancelDescitionInterceptor;
import webbroker3.xbruito.WEB3RuitoClientRequestService;
import webbroker3.xbruito.WEB3RuitoOrderManager;
import webbroker3.xbruito.WEB3RuitoProduct;
import webbroker3.xbruito.WEB3RuitoProductManager;
import webbroker3.xbruito.marketadaptor.WEB3RuitoMarketRequestSubmitServiceImpl;
import webbroker3.xbruito.message.WEB3RuitoCancelCompleteRequest;
import webbroker3.xbruito.message.WEB3RuitoCancelCompleteResponse;
import webbroker3.xbruito.message.WEB3RuitoCancelConfirmRequest;
import webbroker3.xbruito.message.WEB3RuitoCancelConfirmResponse;
import webbroker3.xbruito.service.delegate.WEB3RuitoCancelService;

/**
 * �ݐϓ�������T�[�r�X�����N���X<BR>
 */

public class WEB3RuitoCancelServiceImpl
    extends WEB3RuitoClientRequestService
    implements WEB3RuitoCancelService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoCancelServiceImpl.class);

    /**
     * �ݐϓ�������T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate���()�Asubmit���()<BR>
     * ���\�b�h�̂����ꂩ���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^ <BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40581C5900CD
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }
        if (l_request instanceof WEB3RuitoCancelConfirmRequest)
        {
            log.exiting(STR_METHOD_NAME);
            return this.validateCancel(
                (WEB3RuitoCancelConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3RuitoCancelCompleteRequest)
        {
            log.exiting(STR_METHOD_NAME);
            return this.submitCancel(
                (WEB3RuitoCancelCompleteRequest) l_request);
        }
        else
        {
            log.debug(STR_METHOD_NAME + " __Error[���͒l���s���ł�]__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l�s���B");
        }
    }

    /**
     * (validate���)
     * �ݐϓ�������R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�ݓ�����^(�ݓ�)����R���v�Q��<BR>
     * <BR>
     * @@param l_request - �ݐϓ�������m�F���N�G�X�g�I�u�W�F�N�g <BR>
     * @@return webbroker3.xbruito.message.WEB3RuitoCancelConfirmResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40581CA40050
     */
    protected WEB3RuitoCancelConfirmResponse validateCancel
        (WEB3RuitoCancelConfirmRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateCancel" + "(WEB3RuitoCancelConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }
        //1.1�@@���N�G�X�g�f�[�^�`�F�b�N
        l_request.validate();

        //1.2�@@�⏕�����擾
        SubAccount l_subAccount = this.getSubAccount();
        
        //1.3�@@�ڋq�ʎ����~�����`�F�b�N
        //�|FinApp.getCommonOrderValidator()���R�[�����A�����`�F�b�N�I�u�W�F�N�g���擾����B
        FinApp l_finApp = GtlUtils.getFinApp();
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator(); 
        
        //1.4 �|�����`�F�b�N.validate����\�ڋq()���R�[������B
        OrderValidationResult l_orderValidationResult = 
            l_orderValidator.validateSubAccountForTrading(l_subAccount);
        
        //�|�`�F�b�N�G���[�̏ꍇ�͂��O���X���[����B
        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("�����~�ڋq�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00275,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.5�@@1.6 �����P�ʃI�u�W�F�N�g�̎擾        
        WEB3RuitoOrderManager l_ruitoOrderManager =
            (WEB3RuitoOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.RUITO).getOrderManager();
        
        RuitoOrderUnitParams l_ruitoOrderUnitParams = null;
        long l_lngOrderId = Long.parseLong(l_request.id);
        log.debug("l_lngOrderId = " + l_lngOrderId);
        
       
        //�ݓ������P�ʃI�u�W�F�N�g�̔z����擾����B
        OrderUnit l_orderUnit[] = 
            l_ruitoOrderManager.getOrderUnits(l_lngOrderId);
        //�ݓ������P��Params���擾����B
        l_ruitoOrderUnitParams =
            (RuitoOrderUnitParams) l_orderUnit[0].getDataSourceObject();
        
        //1.8�@@�g���ݓ��������擾����B 
        WEB3RuitoProduct l_ruitoProduct = null; //�g���ݓ�����
        WEB3RuitoProductManager l_ruitoProductManager =
            (WEB3RuitoProductManager)l_finApp.getTradingModule(
                    ProductTypeEnum.RUITO).getProductManager();
        
        long l_lngProductId = l_ruitoOrderUnitParams.getProductId();
        log.debug("ProductId = " + l_lngProductId);
        try
        {
            l_ruitoProduct = (WEB3RuitoProduct)l_ruitoProductManager.getRuitoProduct(
                l_lngProductId); 
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }        
        //1.9�@@��t���ԃ`�F�b�N�A�V�X�e�������~�`�F�b�N
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.10�@@�ݓ�����������e�I�u�W�F�N�g�𐶐�����B
        CancelOrderSpec l_cancelOrderSpec = 
            new CancelOrderSpec(l_lngOrderId);
        
        log.debug("l_cancelOrderSpec.getOrderId = " + l_cancelOrderSpec.getOrderId());
        
        //1.11�@@�����R�����s���B  
        l_orderValidationResult = 
            l_ruitoOrderManager.validateCancelOrder(
                l_subAccount,
                l_cancelOrderSpec);
                                            
        boolean isSuccessfulResult = 
            l_orderValidationResult.getProcessingResult().isSuccessfulResult();
        log.debug("isSuccessfulResult = " + isSuccessfulResult);
        
        if (!isSuccessfulResult)
        {
            log.debug("__ValidateCancelOrderError__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //���������擾����B 
        String l_productName = l_ruitoProduct.getProductName();
        log.debug("�g���ݓ�����.get������() = " + l_productName);
        
        //�������ʂ̎擾 
        double l_ruitoOrderQuantity = l_ruitoOrderUnitParams.getQuantity();
        log.debug("�������ʂ̎擾 = " + l_ruitoOrderQuantity);
        
        //�������ʋ敪�̎擾 
        String l_ruitoOrderQuantityType = null; //�������ʋ敪
        //�������ʃ^�C�v���擾����B
        QuantityTypeEnum l_orderQuantityType =
            l_ruitoOrderUnitParams.getQuantityType();
        log.debug("�������ʋ敪�̎擾 = " + l_orderQuantityType);
        
        //�������ʃ^�C�v���擾����B
        if (QuantityTypeEnum.QUANTITY.equals(l_orderQuantityType))
        {
            log.debug("�ݓ������P��.�������ʃ^�C�v='1:����'�̏ꍇ");
            l_ruitoOrderQuantityType = WEB3SellDivDef.COUNT_DESIGNATE;
        } 
        else if (QuantityTypeEnum.AMOUNT.equals(l_orderQuantityType))
        {
            log.debug("�ݓ������P��.�������ʃ^�C�v='2:���z'�̏ꍇ");
            l_ruitoOrderQuantityType = WEB3SellDivDef.MONEY_DESIGNATE;
        }
        
        log.debug("�������ʃ^�C�v = " + l_ruitoOrderQuantityType);

        //�����敪�̎擾
        String l_ruitoSellDiv = null;
        OrderTypeEnum l_orderType = l_ruitoOrderUnitParams.getOrderType();
        log.debug("�����敪�̎擾 = " + l_orderType);
        
        //������ʂ��擾����
        if (OrderTypeEnum.RUITO_SELL.equals(l_orderType))
        {
            log.debug("������� == OrderTypeEnum.RUITO_SELL�̏ꍇ");
            //�ݓ����敪
            l_ruitoSellDiv = l_ruitoOrderUnitParams.getGpSellDiv();
        }
        
        log.debug("������ʂ��擾 = " + l_ruitoSellDiv);

        //1.12 �������̎擾   
        Date l_orderBizDate =
            WEB3GentradeTradingTimeManagement.getOrderBizDate();
        log.debug("�������̎擾 = " + l_orderBizDate);

        //�ݓ�����m�F���X�|���X�I�u�W�F�N�g�𐶐����A���^�[������B  

        WEB3RuitoCancelConfirmResponse l_response =
            (WEB3RuitoCancelConfirmResponse)l_request.createResponse();

        //�������F
        l_response.ruitoProductName = l_productName;
        // �������ʋ敪�F �擾�����������ʋ敪
        l_response.ruitoOrderQuantityType = l_ruitoOrderQuantityType;

        //�������ʁF �擾������������
        l_response.ruitoOrderQuantity = 
            WEB3StringTypeUtility.formatNumber(l_ruitoOrderQuantity);

        //�����敪�F 
        String l_ruitoDealingType = null;

        log.debug("l_orderType = " + l_orderType);
        if (OrderTypeEnum.RUITO_BUY.equals(l_orderType))
        {
            log.debug("������� == OrderTypeEnum.RUITO_BUY�̏ꍇ");
            l_ruitoDealingType = WEB3BuySellTypeDef.BUY;
        }
        else
        {
            log.debug("������� != OrderTypeEnum.RUITO_BUY�̏ꍇ");
            l_ruitoDealingType = l_ruitoSellDiv;
        }
        log.debug("l_ruitoDealingType = " + l_ruitoDealingType);
        l_response.ruitoDealingType = l_ruitoDealingType;
        
        //�m�F���������F �擾������������ݒ肷��B
        l_response.checkDate = l_orderBizDate;
        
        // ��񒍕�����i�����P��.������ʁ��ݓ��������j�̏ꍇ�A
        // ����]�̓`�F�b�N�������s���B
        if (OrderTypeEnum.RUITO_SELL.equals(l_orderType))
        {
            // �|�ݓ�����m��C���^�Z�v�^�I�u�W�F�N�g�𐶐�����B
            WEB3RuitoCancelDescitionInterceptor l_ruitoCancelInterceptor =
                new WEB3RuitoCancelDescitionInterceptor();
        
            //�|����]�̓T�[�r�X���擾����validate����]��( )���R�[����
            //  ����]�͌��ʃI�u�W�F�N�g���擾����B
            WEB3TPTradingPowerService l_tpTradingPowerService = 
                (WEB3TPTradingPowerService) Services.getService(
                    WEB3TPTradingPowerService.class);
        
            WEB3GentradeSubAccount l_gentradeSubAccount = 
                (WEB3GentradeSubAccount)l_subAccount;
        
            Object l_orderSpecIntercepters[] = new Object[1];
            l_orderSpecIntercepters[0] = l_ruitoCancelInterceptor;
        
            Object[] l_orderSpecs = new Object[1];
            l_orderSpecs[0] = l_cancelOrderSpec;
        
            WEB3TPTradingPowerResult l_tpTradingPowerResult = 
                l_tpTradingPowerService.validateTradingPower(
                    l_gentradeSubAccount,
                    l_orderSpecIntercepters,
                    l_orderSpecs,
                    OrderTypeEnum.RUITO_SELL,
                    false);
        
            //�|�擾��������]�͌��ʃI�u�W�F�N�g.is����t���O( )��false�̏ꍇ�A 
            //[����]�̓`�F�b�N�G���[]�Ƃ��ė�O���X���[����B
            if (!l_tpTradingPowerResult.isResultFlg())
            {
                log.debug("����]�̓`�F�b�N�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                    getClass().getName() + "." + STR_METHOD_NAME, 
                    "����]�̓`�F�b�N�G���[");
            }
        }
               
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit���)
     * �ݐϓ�������o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�ݓ�����^(�ݓ�)����o�^�v�Q�� <BR>
     * <BR>
     * @@param l_request - �ݐϓ�������������N�G�X�g�I�u�W�F�N�g <BR>
     * @@return webbroker3.xbruito.message.WEB3RuitoCancelCompleteResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40581CE000FC
     */
    protected WEB3RuitoCancelCompleteResponse submitCancel(
        WEB3RuitoCancelCompleteRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitCancel(WEB3RuitoCancelCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }
        
        //1.1�@@���N�G�X�g�f�[�^�`�F�b�N 
        l_request.validate();
        
        //1.2�@@�������擾 
        WEB3GentradeTradingTimeManagement.getOrderBizDate(
            l_request.checkDate);

        //1.3�@@�⏕�����擾
        SubAccount l_subAccount = this.getSubAccount();
        
        //1.4�@@�ڋq�ʎ����~�����`�F�b�N
        //�|FinApp.getCommonOrderValidator()���R�[�����A�����`�F�b�N�I�u�W�F�N�g���擾����B
        FinApp l_finApp = GtlUtils.getFinApp();
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator(); 
        
        //1.5 �|�����`�F�b�N.validate����\�ڋq()���R�[������B
        OrderValidationResult l_orderValidationResult = 
            l_orderValidator.validateSubAccountForTrading(l_subAccount);
        
        //�|�`�F�b�N�G���[�̏ꍇ�͂��O���X���[����B
        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("�����~�ڋq�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00275,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.6 �|this.get�㗝���͎�( )���R�[�����A�㗝���͎҃I�u�W�F�N�g���擾����B
        Trader l_trader = this.getTrader();        
        
        //1.7 �|validate����p�X���[�h( )���R�[������B
        log.debug("���N�G�X�g�f�[�^.�Ïؔԍ� = " + l_request.password);
        l_orderValidationResult = l_orderValidator.validateTradingPassword(
                l_trader,
                l_subAccount,
                l_request.password);
        
        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("����p�X���[�h�s��");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00009,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3RuitoOrderManager l_ruitoOrderManager =
            (WEB3RuitoOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.RUITO).getOrderManager();

        RuitoOrderUnitParams l_ruitoOrderUnitParams = null;

        long l_lngOrderId = Long.parseLong(l_request.id);
        log.debug("l_lngOrderId = " + l_lngOrderId);

        OrderUnit[] l_orderUnits = 
            l_ruitoOrderManager.getOrderUnits(l_lngOrderId);
        log.debug("l_orderUit.length = " + l_orderUnits.length);
        
        //�ݓ������P��Params���擾����B
        l_ruitoOrderUnitParams =
              (RuitoOrderUnitParams) l_orderUnits[0].getDataSourceObject();
        log.debug("l_ruitoOrderUnitParams = " + l_ruitoOrderUnitParams);
        
        //1.11�@@�g���ݓ��������擾���� 
        WEB3RuitoProductManager l_ruitoProductManager =
              (WEB3RuitoProductManager) 
              l_finApp.getTradingModule(ProductTypeEnum.RUITO).getProductManager();
        log.debug("ProductId = " + l_ruitoOrderUnitParams.getProductId());
        
        try
        {                        
            l_ruitoProductManager.getRuitoProduct(l_ruitoOrderUnitParams.getProductId());

        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }       
        
        //�@@��t���ԃ`�F�b�N�A�V�X�e�������~�`�F�b�N 
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.13�@@CancelOrderSpec�I�u�W�F�N�g�𐶐�����B 
        CancelOrderSpec l_cancelOrderSpec = new CancelOrderSpec(l_lngOrderId);
            
        //1.14�@@�����R��
        l_orderValidationResult =
            l_ruitoOrderManager.validateCancelOrder(
                l_subAccount,
                l_cancelOrderSpec);
                                            
        boolean isSuccessfulResult = 
            l_orderValidationResult.getProcessingResult().isSuccessfulResult();
        log.debug("isSuccessfulResult = " + isSuccessfulResult);
        
        if (!isSuccessfulResult)
        {
            log.debug("__ValidateCancelOrderError__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                STR_METHOD_NAME);
        }
         
        //1.15�@@�ݓ��s�ꃊ�N�G�X�g���M�T�[�r�X�ɁA�s�ꑗ�M���������{����Ƃ����ݒ���s���B
        MarketAdapter l_marketAdapter = 
            l_finApp.getTradingModule(ProductTypeEnum.RUITO).getMarketAdapter();
        WEB3RuitoMarketRequestSubmitServiceImpl l_web3RuitoMarketRequestSubmitService =
            (WEB3RuitoMarketRequestSubmitServiceImpl)l_marketAdapter.getMarketRequestSenderServce();

        l_web3RuitoMarketRequestSubmitService.setMarketSubmit(true);

        //1.16�@@�ݓ�����m��C���^�Z�v�^�I�u�W�F�N�g�𐶐�����B
        WEB3RuitoCancelDescitionInterceptor l_ruitoCancelDescitionInterceptor =
            new WEB3RuitoCancelDescitionInterceptor();

        //1.17�@@�������
        long l_orderHistoryId = 0; //��������ID
        String l_mrfOrderRequestNumber = l_ruitoOrderUnitParams.getMrfOrderRequestNumber();            
        log.debug("l_mrfOrderRequestNumber = " + l_mrfOrderRequestNumber);
        
        boolean l_blnConfirmedQuantityIsNull = 
            l_ruitoOrderUnitParams.getConfirmedQuantityIsNull();
        log.debug("l_confirmedQuantityIsNull = " + l_blnConfirmedQuantityIsNull);
        
        //�ݓ������P��Params.getMRF�������ʃR�[�h()�̒l��null�ł͂Ȃ�
        //�ݓ������P��Params.getConfirmedQuantityIsNull()�̒l��true�ꍇ�A
        //this.������������iMRF�������L��F���������j()���R�[������B 
        if (l_mrfOrderRequestNumber != null && l_blnConfirmedQuantityIsNull)    
        {
            log.debug("l_mrfOrderRequestNumber != null && l_confirmedQuantityIsNull");
            this.cancelProcessMRFAutoSellTomorrowOrder(
                l_subAccount,
                l_cancelOrderSpec,
                l_request.password,
                l_ruitoCancelDescitionInterceptor);
        }
        //��L�ȊO�̏ꍇ�Athis.������������iMRF�������Ȃ��j()���R�[������B 
        else 
        {
            log.debug("l_mrfOrderRequestNumber != null && l_confirmedQuantityIsNull �ȊO�̏ꍇ");
            this.cancelProcessNotMRFAutoSell(
                l_subAccount,
                l_cancelOrderSpec,
                l_request.password,
                l_ruitoCancelDescitionInterceptor);
        }
               
        log.debug("l_orderHistoryId = " + l_orderHistoryId);
        
        //�|����]�̓T�[�r�X���擾����validate����]��( )���R�[����
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
                
        WEB3GentradeSubAccount l_gentradeSubAccount = 
            (WEB3GentradeSubAccount)l_subAccount;
        
        // ��񒍕�����i�����P��.������ʁ��ݓ��������j�̏ꍇ�A
        // ����]�̓`�F�b�N�������s���B
        if (OrderTypeEnum.RUITO_SELL.equals(l_ruitoOrderUnitParams.getOrderType()))
        {

            //  ����]�͌��ʃI�u�W�F�N�g���擾����B
            Object l_orderSpecIntercepters[] = new Object[1];
            l_orderSpecIntercepters[0] = l_ruitoCancelDescitionInterceptor;
        
            Object[] l_orderSpecs = new Object[1];
            l_orderSpecs[0] = l_cancelOrderSpec;
        
            WEB3TPTradingPowerResult l_tpTradingPowerResult = 
                l_tpTradingPowerService.validateTradingPower(
                    l_gentradeSubAccount,
                    l_orderSpecIntercepters,
                    l_orderSpecs,
                    OrderTypeEnum.RUITO_SELL,
                    true);
        
            //�|�擾��������]�͌��ʃI�u�W�F�N�g.is����t���O( )��false�̏ꍇ�A 
            //[����]�̓`�F�b�N�G���[]�Ƃ��ė�O���X���[����B
            if (!l_tpTradingPowerResult.isResultFlg())
            {
                log.debug("����]�̓`�F�b�N�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                    getClass().getName() + "." + STR_METHOD_NAME, 
                    "����]�̓`�F�b�N�G���[");
            }
        }
        
        // ���t��������i�����P��.������ʁ��ݓ��������j�̏ꍇ�A
        // ����]�͍Čv�Z�������s���B
        else if (OrderTypeEnum.RUITO_BUY.equals(
                        l_ruitoOrderUnitParams.getOrderType()))
        {
            l_tpTradingPowerService.reCalcTradingPower(l_gentradeSubAccount);
        }

        //���������̎擾 
        Date l_threadLocalSystemAttributesRegistry = 
            (Date)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
                
        log.debug("l_threadLocalSystemAttributesRegistry = "
            + l_threadLocalSystemAttributesRegistry);

        //1.20 �ݓ�����������X�|���X�I�u�W�F�N�g�𐶐����A���^�[������B
        WEB3RuitoCancelCompleteResponse l_response =
            (WEB3RuitoCancelCompleteResponse)l_request.createResponse();

        //�X�V���ԁF �擾������������ 
        l_response.lastUpdatedTimestamp = l_threadLocalSystemAttributesRegistry;
            
        //���ʔԍ��F �����P�ʁD����ID 
        l_response.orderActionId = l_ruitoOrderUnitParams.getOrderId() + "";


        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * ��������iMRF�������L��F���������j<BR>
     * <BR>
     * �V�[�P���X�}�u�ݓ�����^(�ݓ�)���(MRF�������L��F���������v�Q�� <BR>
     * <BR>
     * @@param l_subAccount - �⏕���� <BR>
     * @@param l_cancelOrderSpec - ����������e <BR>
     * @@param l_tradePassword - ����p�X���[�h <BR>
     * @@param l_ruitoCancelDecisionInterceptor - �ݓ�����m��C���^�Z�v�^ <BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40839A480318
     */
    public void cancelProcessMRFAutoSellTomorrowOrder(
        SubAccount l_subAccount,
        CancelOrderSpec l_cancelOrderSpec,
        String l_tradePassword,
        WEB3RuitoCancelDescitionInterceptor 
        l_ruitoCancelDecisionInterceptor)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME ="cancelProcessMRFAutoSellTomorrowOrder"
            + "(SubAccount l_subAccount, "
            + "CancelOrderSpec l_cancelOrderSpec, "
            + "String l_tradePassword, "
            + "WEB3RuitoCancelDescitionInterceptor "
            + "l_ruitoCancelDecisionInterceptor)";
        log.entering(STR_METHOD_NAME);
                                 
        if (l_subAccount == null || l_cancelOrderSpec == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }
   
        //1.1�@@�g���ݓ������}�l�[�W��.setThreadLocalPersistenceEventInterceptor()
        //���R�[�����A �C���^�Z�v�^�̐ݒ���s���B
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3RuitoOrderManager l_ruitoOrderManager =
            (WEB3RuitoOrderManager)
                l_finApp.getTradingModule(ProductTypeEnum.RUITO).getOrderManager();
        l_ruitoOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_ruitoCancelDecisionInterceptor);
 
        //1.2�@@�g���ݓ������}�l�[�W��.submitCancelOrder()���R�[������B
        log.debug("l_subAccount.getAccountId = " + l_subAccount.getAccountId());
        log.debug("l_cancelOrderSpec = " + l_cancelOrderSpec.getOrderId());
        log.debug("l_tradePassword = " + l_tradePassword);

        OrderSubmissionResult l_submitCancelOrder = 
            l_ruitoOrderManager.submitCancelOrder(
                l_subAccount,
                l_cancelOrderSpec,
                l_tradePassword,
                true); 
 
        //�g���ݓ������}�l�[�W��.submitCancelOrder()�̖߂�l����
        boolean blnSuccessfulResult = 
            l_submitCancelOrder.getProcessingResult().isSuccessfulResult();
        log.debug("isSuccessfulResult = " + blnSuccessfulResult);

        if (!blnSuccessfulResult)
        {
            log.debug("__SubmitCancelOrderError__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00240,
                this.getClass().getName() + STR_METHOD_NAME, 
                "����������s�B");
         }
        //�@@MRF������񒍕��̎�����s���B 
        //1.3 �ݓ������P�ʃI�u�W�F�N�g���擾����B 
        String l_strMrfOrderRequestNumber = null;  //�������ʔԍ�
        OrderUnit[] l_orderUnits = 
            l_ruitoOrderManager.getOrderUnits(l_cancelOrderSpec.getOrderId());
        RuitoOrderUnitParams l_ruitoOrderUnitParams = 
            (RuitoOrderUnitParams)l_orderUnits[0].getDataSourceObject();
        RuitoOrderUnit l_ruitoOrderUnit;  
        
        l_strMrfOrderRequestNumber = 
            l_ruitoOrderUnitParams.getMrfOrderRequestNumber(); 
        try
        {
            log.debug("AccountId = " + l_subAccount.getAccountId());
            log.debug("SubAccountId = " + l_subAccount.getSubAccountId());
            log.debug("l_strMrfOrderRequestNumber = " + l_strMrfOrderRequestNumber);
            
            l_ruitoOrderUnit = 
                l_ruitoOrderManager.getRuitoOrderUnit(l_subAccount.getAccountId(),
                    l_subAccount.getSubAccountId(),
                    l_strMrfOrderRequestNumber);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80006,
               this.getClass().getName() + STR_METHOD_NAME,
               l_ex.getMessage(),
               l_ex);
        }

        //1.4�@@CancelOrderSpec�I�u�W�F�N�g�𐶐�����B
        CancelOrderSpec l_cancelOrderSpecNew =
            new CancelOrderSpec((l_ruitoOrderUnit.getOrderId())); 
        
        //1.5  �g���ݓ������}�l�[�W��.setThreadLocalPersistenceEventInterceptor()
        //���R�[�����A�C���^�Z�v�^�̐ݒ���s��
        l_ruitoOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_ruitoCancelDecisionInterceptor);

        //1.6�@@�g���ݓ������}�l�[�W��.submitCancelOrder()���R�[������B
        log.debug("l_subAccount.getAccountId = " + l_subAccount.getAccountId());
        log.debug("l_cancelOrderSpec.getOrderId = " + l_cancelOrderSpecNew.getOrderId());
        log.debug("l_tradePassword = " + l_tradePassword);

        OrderSubmissionResult l_orderSubmissionResult = 
            l_ruitoOrderManager.submitCancelOrder(
                l_subAccount,
                l_cancelOrderSpecNew,
                l_tradePassword,
                true);

        //�g���ݓ������}�l�[�W��.submitCancelOrde()�̖߂�l����
        boolean l_blnOrderSubmissionResult =
            l_orderSubmissionResult.getProcessingResult().isSuccessfulResult();
        log.debug("l_blnOrderSubmissionResult = " + l_blnOrderSubmissionResult);  
        if (!l_blnOrderSubmissionResult)
        {
            log.debug("__SubmitCancelOrderError__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00240,
                this.getClass().getName() + STR_METHOD_NAME,
                "����������s�B");
        }
        log.exiting(STR_METHOD_NAME);        
    }

    /**
     * ��������iMRF�������Ȃ��j<BR>
     * <BR>
     * �V�[�P���X�}�u�ݓ�����^�i�ݓ��j����iMRF�������Ȃ��j�v�Q�� <BR>
     * <BR>
     * @@param l_subAccount - �⏕���� <BR>
     * @@param l_cancelOrderSpec - ����������e <BR>
     * @@param l_tradePassword - ����p�X���[�h <BR>
     * @@param l_ruitoCancelDecisionInterceptor - �ݓ�����m��C���^�Z�v�^ <BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40839A95000B
     */
    public void cancelProcessNotMRFAutoSell(
        SubAccount l_subAccount,
        CancelOrderSpec l_cancelOrderSpec,
        String l_tradePassword,
        WEB3RuitoCancelDescitionInterceptor 
        l_ruitoCancelDecisionInterceptor)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME ="cancelProcessNotMRFAutoSell"
            + "(SubAccount l_subAccount, "
            + "CancelOrderSpec l_cancelOrderSpec, "
            + "String l_tradePassword, "
            + "WEB3RuitoCancelDescitionInterceptor "
            + "l_ruitoCancelDecisionInterceptor)";
        log.entering(STR_METHOD_NAME);
       
        if (l_subAccount == null || l_cancelOrderSpec == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }

        //1.1�@@�g���ݓ������}�l�[�W��.setThreadLocalPersistenceEventInterceptor()
        //���R�[�����A
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3RuitoOrderManager l_ruitoOrderManager =
            (WEB3RuitoOrderManager)
            l_finApp.getTradingModule(ProductTypeEnum.RUITO).getOrderManager();
        l_ruitoOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_ruitoCancelDecisionInterceptor);

        //1.2�@@�g���ݓ������}�l�[�W��.submitCancelOrde()���R�[������B
        log.debug("l_subAccount.getAccountId = " + l_subAccount.getAccountId());
        log.debug("����p�X���[�h = " + l_tradePassword);

        OrderSubmissionResult l_orderSubmissionResult = 
            l_ruitoOrderManager.submitCancelOrder(l_subAccount,
                                                  l_cancelOrderSpec,
                                                  l_tradePassword,
                                                  true);
        
        //�g���ݓ������}�l�[�W��.submitCancelOrder()�̖߂�l���� 
        if (!l_orderSubmissionResult.getProcessingResult().isSuccessfulResult())
        {
            log.debug("__SubmitCancelOrderError__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00240,
               this.getClass().getName() + "." + STR_METHOD_NAME, 
               "����������s�B");
        }
    }
}
@
