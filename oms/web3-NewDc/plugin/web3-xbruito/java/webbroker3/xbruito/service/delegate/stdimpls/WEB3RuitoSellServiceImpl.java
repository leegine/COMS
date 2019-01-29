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
filename	WEB3RuitoSellServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ������T�[�r�X�����N���X(WEB3RuitoSellServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/09 ���u�� (���u) �V�K�쐬
                   2004/12/06 ��O�� (���u) �c�Ή�
*/

package webbroker3.xbruito.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DesignateMethodDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.WEB3RuitoClientRequestService;
import webbroker3.xbruito.WEB3RuitoNewOrderDecisionInterceptor;
import webbroker3.xbruito.WEB3RuitoNewOrderSpec;
import webbroker3.xbruito.WEB3RuitoOrderManager;
import webbroker3.xbruito.WEB3RuitoOrderManagerReusableValidationsCheck;
import webbroker3.xbruito.WEB3RuitoPositionManager;
import webbroker3.xbruito.WEB3RuitoProduct;
import webbroker3.xbruito.WEB3RuitoProductManager;
import webbroker3.xbruito.marketadaptor.WEB3RuitoMarketRequestSubmitServiceImpl;
import webbroker3.xbruito.message.WEB3RuitoSellCompleteRequest;
import webbroker3.xbruito.message.WEB3RuitoSellCompleteResponse;
import webbroker3.xbruito.message.WEB3RuitoSellConfirmRequest;
import webbroker3.xbruito.message.WEB3RuitoSellConfirmResponse;
import webbroker3.xbruito.service.delegate.WEB3RuitoSellService;

/**
 * �ݐϓ������T�[�r�X�����N���X<BR>
 */
public class WEB3RuitoSellServiceImpl
    extends WEB3RuitoClientRequestService
    implements WEB3RuitoSellService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoSellServiceImpl.class);

    /**
     * �ݐϓ������T�[�r�X���������{����B<BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * validate���()�Asubmit���()<BR>
     * �����ꂩ�̃��\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 405817F7012B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
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

        if (l_request instanceof WEB3RuitoSellConfirmRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u�ݓ����m�F���N�G�X�g�v�̏ꍇ
            log.exiting(STR_METHOD_NAME);
            return this.validateSell((WEB3RuitoSellConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3RuitoSellCompleteRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u�ݓ���񐳊������N�G�X�g�v�̏ꍇ
            log.exiting(STR_METHOD_NAME);
            return this.submitSell((WEB3RuitoSellCompleteRequest) l_request);
        }
        else
        {
            log.debug(STR_METHOD_NAME + " __Error[���͒l���s���ł�]__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                STR_METHOD_NAME);
        }        
    }

    /**
     * �ݐϓ������R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�ݓ����^�i�ݓ��j���R���v�Q�ƁB<BR>
     * <BR>
     * 1.19 is����t���O��false�Ȃ��O���X���[���� <BR>
     * �u����]�̓G���[�v<BR>
     *      classpath:WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_01306<BR>
     * <BR>
     * @@param l_request - �ݐϓ������m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.xbruito.message.WEB3RuitoSellConfirmResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 405818CE031F
     */
    public WEB3RuitoSellConfirmResponse validateSell(WEB3RuitoSellConfirmRequest l_request)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateSell(WEB3RuitoSellConfirmRequest l_request)";
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

        //1.1�@@���N�G�X�g�f�[�^�Ó����`�F�b�N
        l_request.validate();

        //1.2�@@�⏕�����擾
        SubAccount l_subAccount = getSubAccount();
        
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
                getClass().getName() + "." + STR_METHOD_NAME, 
                "�����~�ڋq�`�F�b�N�G���[");
        }
        
        //1.5�@@�ݓ������擾  
        //�|�g���ݓ������}�l�[�W�����擾����B
        WEB3RuitoProductManager l_web3RuitoProductManager =
            (WEB3RuitoProductManager) l_finApp.getTradingModule(
                    ProductTypeEnum.RUITO).getProductManager();

        RuitoProduct l_ruitoProduct = null;
        WEB3RuitoProduct l_web3RuitoProduct = null;
        
        try
        {
            //�|�g���ݓ������}�l�[�W��.get�ݓ�����()���R�[�����A�g���ݓ��������擾����B
            l_ruitoProduct =
                l_web3RuitoProductManager.getRuitoProduct(
                    l_subAccount.getInstitution(),
                    l_request.ruitoProductCode,
                    "0");
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
        l_web3RuitoProduct = (WEB3RuitoProduct) l_ruitoProduct;
        log.debug("l_web3RuitoProduct = " + l_web3RuitoProduct);

        long l_lngProductId = l_web3RuitoProduct.getProductId();
        log.debug("l_lngProductId = " + l_lngProductId);
        
        //1.6 �ݓ������̗ݓ��^�C�v���擾����
        RuitoTypeEnum l_ruitoType = l_web3RuitoProduct.getRuitoType();
        log.debug("l_ruitoType = " + l_ruitoType);
        
        //�@@��t���ԃ`�F�b�N�A�V�X�e�������~�`�F�b�N
        //�ݓ������̗ݓ��^�C�v���A����F�̏ꍇ
        if (RuitoTypeEnum.CHUUKOKU_FUND.equals(l_ruitoType))
        {
            log.debug("RuitoTypeEnum.CHUUKOKU_FUND.equals(l_ruitoTypeEnum)");
            //1.7 ��t���ԋ敪�̃��Z�b�g������ 
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(
                WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND);
            
            //1.8 setTimestamp( )
            WEB3GentradeTradingTimeManagement.setTimestamp();
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }
        //�ݓ������̗ݓ��^�C�v���AMMF�̏ꍇ
        else if (RuitoTypeEnum.MMF.equals(l_ruitoType))
        {
            log.debug("RuitoTypeEnum.MMF.equals(l_ruitoType)");
            //1.7 ��t���ԋ敪�̃��Z�b�g������ 
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(
                WEB3TradingTimeTypeDef.MMF_SET_CANCEL);
            
            //1.8 setTimestamp( )
            WEB3GentradeTradingTimeManagement.setTimestamp();
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }

        WEB3RuitoOrderManagerReusableValidationsCheck l_ruitoOrderManagerReusableValidCheck =
            new WEB3RuitoOrderManagerReusableValidationsCheck();

        double l_dblSellPossibleBalance = 0;    //���\�c��

        //1.10�@@�S�����̏ꍇ�ɁA���\�c�����Z�o����
        if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv))
        {
            //�g���ݓ��|�W�V�����}�l�[�W��.get���\�c��()�� 
            //�R�[�����A���\�c�����擾����B
            WEB3RuitoPositionManager l_web3RuitoPositionManager =
                (WEB3RuitoPositionManager) l_finApp.getTradingModule(
                    ProductTypeEnum.RUITO).getPositionManager();
                    
            l_dblSellPossibleBalance =       
                l_web3RuitoPositionManager.getSellPossibleBalance(
                    l_subAccount, l_web3RuitoProduct);
        }
        else
        {
            l_dblSellPossibleBalance =
                Double.parseDouble(l_request.ruitoOrderQuantity);  
        }            

        WEB3RuitoOrderManager l_web3RuitoOrderManager =
            (WEB3RuitoOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.RUITO).getOrderManager();
        
        double l_dblOrderQuantity = 0; 
        if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv))
        {            
            l_dblOrderQuantity = l_dblSellPossibleBalance;
        }
        else
        {
            l_dblOrderQuantity =
                Double.parseDouble(l_request.ruitoOrderQuantity);
        }
        //1.11 �����R�����s���B
        NewOrderValidationResult l_newOrderValidationResult = 
            l_web3RuitoOrderManager.validateNewOrder(
                l_subAccount,
                l_web3RuitoProduct,
                l_dblOrderQuantity,
                false,
                l_request.deliveryDiv,
                l_request.specifyDiv);
        
        if (!l_newOrderValidationResult.getProcessingResult().isSuccessfulResult())
        {
            log.debug("�����R���`�F�b�N�G���[");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00174,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�����R���`�F�b�N�G���[");
        } 

        //1.20�@@�������擾
        Date l_orderDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //1.21�@@����ID�̔� 
        //�g���ݓ������}�l�[�W��.createNewOrderId()���R�[�����Ē���ID���̔Ԃ���B
        long l_lngOrderId = l_web3RuitoOrderManager.createNewOrderId();
        
        //�y�ݓ����m�F���X�|���X�ɐݒ肷��l�z      
        WEB3RuitoSellConfirmResponse l_response =
            (WEB3RuitoSellConfirmResponse) l_request.createResponse();
        //�m�F���������F �擾������������ݒ� 
        l_response.checkDate = l_orderDate;
        //����ID�F �̔Ԃ�������ID��ݒ� 
        l_response.orderId = l_lngOrderId + "";
        log.debug("����ID�F" + l_response.orderId);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit���)
     * �ݐϓ������o�^�������s���B<BR>
     * �V�[�P���X�}�u�i�ݓ��j���o�^�v�Q�ƁB<BR>
     * <BR>
     * 1.13 �����R�����s���A�`�F�b�N�G���[�̏ꍇ�́A��O���X���[����B
     *      classpath:WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_00174<BR>
     * <BR>
     * 1.24 �ݓ������}�l�[�W��.submitNewOrder()�̖߂�l����<BR>
     *  �@@�@@�ݓ������}�l�[�W��.submitNewOrder()�̖߂�l<BR>
     *      .getProcessingResult().isSuccessfulResult()==false<BR>
     * �@@ �@@�̏ꍇ�A��O���X���[����B<BR>
     *      classpath:WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_00286<BR>
     * <BR>
     * @@param l_request - �ݐϓ�����񊮗����N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.xbruito.message.WEB3RuitoSellCompleteResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 405818D502A2
     */
    public WEB3RuitoSellCompleteResponse submitSell(WEB3RuitoSellCompleteRequest l_request)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "submitSell(WEB3RuitoSellCompleteRequest l_request)";
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

        //1.1�@@���N�G�X�g�f�[�^�Ó����`�F�b�N
        l_request.validate();

        //1.2�@@�⏕�����擾
        SubAccount l_subAccount = getSubAccount();

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
                getClass().getName() + "." + STR_METHOD_NAME, 
                "�����~�ڋq�G���[");
        }
        
        //1.5 �|this.get�㗝���͎�( )���R�[�����A�㗝���͎҃I�u�W�F�N�g���擾����B
        Trader l_trader = this.getTrader();        
        
        //1.6 �|validate����p�X���[�h( )���R�[������B
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
        //1.7�@@�ݓ������擾
        //�|�g���ݓ������}�l�[�W�����擾����B
        WEB3RuitoProductManager l_web3RuitoProductManager =
            (WEB3RuitoProductManager) l_finApp.getTradingModule(
                    ProductTypeEnum.RUITO).getProductManager();

        RuitoProduct l_ruitoProduct = null;
        String l_strNewNumber = null;
        RuitoTypeEnum l_ruitoType = null;
        WEB3RuitoProduct l_web3RuitoProduct = null;
        double l_dblRuitoOrderQuantity = 0;

        try
        {
           //�|�g���ݓ������}�l�[�W��.get�ݓ�����()���R�[�����A�g���ݓ��������擾����B
           l_ruitoProduct =
                l_web3RuitoProductManager.getRuitoProduct(
                    l_subAccount.getInstitution(),
                    l_request.ruitoProductCode,
                    "0");

            l_web3RuitoProduct = (WEB3RuitoProduct) l_ruitoProduct;

            long l_lngProductId = l_ruitoProduct.getProductId();
            log.debug("l_lngProductId = " + l_lngProductId);
            
            //1.8  getRuitoType( )
            l_ruitoType = l_ruitoProduct.getRuitoType();
            log.debug("l_ruitoTypeEnum = " + l_ruitoType);

            //�@@��t���ԃ`�F�b�N�A�V�X�e�������~�`�F�b�N 
            //�ݓ������̗ݓ��^�C�v���A����F�̏ꍇ
            if (RuitoTypeEnum.CHUUKOKU_FUND.equals(l_ruitoType))
            {
                log.debug("RuitoTypeEnum.CHUUKOKU_FUND.equals(l_ruitoTypeEnum)");
                //1.9 ��t���ԋ敪�̃��Z�b�g������ 
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(
                    WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND);
                //1.10 setTimestamp( )
                WEB3GentradeTradingTimeManagement.setTimestamp();
                //1.11 validate������t�\( )
                WEB3GentradeTradingTimeManagement.validateOrderAccept();
            }
            //�ݓ������̗ݓ��^�C�v���AMMF�̏ꍇ
            else if (RuitoTypeEnum.MMF.equals(l_ruitoType))
            {
                log.debug("RuitoTypeEnum.MMF.equals(l_ruitoTypeEnum)");
                //1.9 ��t���ԋ敪�̃��Z�b�g������ 
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(
                    WEB3TradingTimeTypeDef.MMF_SET_CANCEL);
                //1.10 setTimestamp( )
                WEB3GentradeTradingTimeManagement.setTimestamp();
                //1.11 validate������t�\( )
                WEB3GentradeTradingTimeManagement.validateOrderAccept();
            }

            //1.12 �S�����̏ꍇ�ɁA���\�c�����Z�o����
            if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv))
            {
                //1.12.1 �g���ݓ��|�W�V�����}�l�[�W��.get���\�c��()�� 
                //�R�[�����A���\�c�����擾����B
                WEB3RuitoPositionManager l_web3RuitoPositionManager =
                    (WEB3RuitoPositionManager) l_finApp.getTradingModule(
                        ProductTypeEnum.RUITO).getPositionManager();
                        
                l_dblRuitoOrderQuantity =       
                    l_web3RuitoPositionManager.getSellPossibleBalance(
                        l_subAccount, l_web3RuitoProduct);
            }
            else
            {
                l_dblRuitoOrderQuantity =
                    Double.parseDouble(l_request.ruitoOrderQuantity);  
            }           

            //�@@�����R�� 
            WEB3RuitoOrderManager l_web3RuitoOrderManager =
                (WEB3RuitoOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.RUITO).getOrderManager();
                    
            //1.13 �����R�����s���A�`�F�b�N�G���[�̏ꍇ�́A��O���X���[����B 
            NewOrderValidationResult l_newOrderValidationResult = 
                l_web3RuitoOrderManager.validateNewOrder(
                    l_subAccount,
                    l_web3RuitoProduct,
                    l_dblRuitoOrderQuantity,
                    false,
                    l_request.deliveryDiv,
                    l_request.specifyDiv
                );
            if (!l_newOrderValidationResult.getProcessingResult().isSuccessfulResult())
            {
                log.debug("�����R���`�F�b�N�G���[");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00174,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    "�����R���`�F�b�N�G���[");
            }   
            
          //1.14�@@�m�F���Ɣ��������������`�F�b�N���� 
          WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);

          //1.15�@@���ʃR�[�h���̔Ԃ��� 
          WEB3HostReqOrderNumberManageService l_WEB3HostReqOrderNumberManageService=
              (WEB3HostReqOrderNumberManageService)Services.getService(
                  WEB3HostReqOrderNumberManageService.class);
          
          l_strNewNumber = l_WEB3HostReqOrderNumberManageService.getNewNumber(
                  l_subAccount.getInstitution().getInstitutionCode(),
                  l_subAccount.getMainAccount().getBranch().getBranchCode(),
                  ProductTypeEnum.RUITO);         
          
            log.debug("l_strNewNumber = " + l_strNewNumber);        
      }
      catch (NotFoundException l_ex)
      {
          log.error("__NotFoundExcetion__", l_ex);
          log.exiting(STR_METHOD_NAME);
          throw new WEB3SystemLayerException(
              WEB3ErrorCatalog.SYSTEM_ERROR_80006,
              this.getClass().getName() + STR_METHOD_NAME,
              l_ex.getMessage(),
              l_ex);
      }

      //��񒍕�����
      //1.16 �ݓ��V�K�����m��C���^�Z�v�^�𐶐����� 
      WEB3RuitoNewOrderDecisionInterceptor l_web3RuitoNewOrderDecisionInterceptor =
          new WEB3RuitoNewOrderDecisionInterceptor();

      //�ݓ��V�K�����m��C���^�Z�v�^��ݓ������}�l�[�W���ɐݒ肷��
      WEB3RuitoOrderManager l_web3RuitoOrderManager =
          (WEB3RuitoOrderManager) l_finApp.getTradingModule(
              ProductTypeEnum.RUITO).getOrderManager();
      
      //1.17 �ݓ��V�K�ݓ��V�K�����m��C���^�Z�v�^��ݒ肷��B 
      l_web3RuitoOrderManager.setThreadLocalPersistenceEventInterceptor(
          l_web3RuitoNewOrderDecisionInterceptor);

      //�ݓ��V�K�����m��C���^�Z�v�^�Ɏ��ʃR�[�h��ݒ肷��
      l_web3RuitoNewOrderDecisionInterceptor.setRequestNumber(l_strNewNumber);

      //�ݓ��V�K�����m��C���^�Z�v�^��null��ݒ肷��
      l_web3RuitoNewOrderDecisionInterceptor.setMRFOrderRequestNumber(null);

      //�ݓ��V�K�����m��C���^�Z�v�^�ɕԊҕ��@@��ݒ肷��
      l_web3RuitoNewOrderDecisionInterceptor.setReturnMethod(null);

      //�ݓ��V�K�����m��C���^�Z�v�^�Ɏ�n���@@��ݒ肷��
      l_web3RuitoNewOrderDecisionInterceptor.setPaymentMethod(l_request.deliveryDiv);

      //�ݓ��V�K�����m��C���^�Z�v�^�ɗݓ��^�C�v��ݒ肷��
      l_web3RuitoNewOrderDecisionInterceptor.setRuitoTypeEnum(l_ruitoType);

      //�ݓ��V�K�����m��C���^�Z�v�^�ɗݓ����敪��ݒ肷��
      l_web3RuitoNewOrderDecisionInterceptor.setRuitoSellDiv(l_request.specifyDiv);

      //�ݓ��V�K�����m��C���^�Z�v�^�ɒ����o�H�敪��ݒ肷��
	  OpLoginSecurityService l_opLoginSecService =
			  (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class); 
		l_web3RuitoNewOrderDecisionInterceptor.setOrderRootDiv(
			  l_opLoginSecService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));
	  log.debug("�����o�H�敪 = " + l_opLoginSecService.getSessionProperty
					  (WEB3SessionAttributeDef.ORDER_ROOT_DIV));

      //�ݓ��V�K�����m��C���^�Z�v�^�ɒ����`���l����ݒ肷��
      l_web3RuitoNewOrderDecisionInterceptor.setOrderChannel(getLoginChannel());
           
      //�ݓ��s�ꃊ�N�G�X�g���M�T�[�r�X�ɁA�s�ꑗ�M���������{����Ƃ����ݒ���s��
      TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.RUITO);
      MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
      WEB3RuitoMarketRequestSubmitServiceImpl l_web3RuitoMarketRequestSubmitService =
          (WEB3RuitoMarketRequestSubmitServiceImpl)l_marketAdapter.getMarketRequestSenderServce();
        
      l_web3RuitoMarketRequestSubmitService.setMarketSubmit(true);

      //�V�K�������e�̐���
      WEB3RuitoNewOrderSpec l_web3RuitoNewOrderSpec =
          new WEB3RuitoNewOrderSpec(
              this.getTrader(),
              false,
              "0",
              l_request.ruitoProductCode,
              "0",
              l_dblRuitoOrderQuantity,
              this.getOrderQuantityType(l_request.specifyDiv, l_web3RuitoProduct),
              TaxTypeEnum.UNDEFINED);
              
      //�|�g���ݓ������}�l�[�W��.submitNewOrder()���R�[������B 
      OrderSubmissionResult l_orderSubmissionResult =
         l_web3RuitoOrderManager.submitNewOrder(
             l_subAccount,
             ProductTypeEnum.RUITO,
             l_web3RuitoNewOrderSpec,
             Long.parseLong(l_request.orderId),
             l_request.password,
             true);
      if (!l_orderSubmissionResult.getProcessingResult().isSuccessfulResult())
      {
         log.debug("__Result_False__" + l_orderSubmissionResult.getProcessingResult().getErrorInfo());
         log.exiting(STR_METHOD_NAME);
         throw new WEB3BusinessLayerException(
             WEB3ErrorCatalog.BUSINESS_ERROR_00286,
             getClass().getName() + "." + STR_METHOD_NAME,
            "��񒍕���o���s");
      }

      //�|����]�̓T�[�r�X���擾���āA�]�͍Čv�Z( )���R�[������B
      WEB3TPTradingPowerService l_tpTradingPowerService = 
          (WEB3TPTradingPowerService) Services.getService(
              WEB3TPTradingPowerService.class);

      l_tpTradingPowerService.reCalcTradingPower(
          (WEB3GentradeSubAccount)l_subAccount);      
     
      //�ݓ���񊮗����X�|���X�I�u�W�F�N�g�𐶐����A���^�[������
      WEB3RuitoSellCompleteResponse l_response =
          (WEB3RuitoSellCompleteResponse) l_request.createResponse();

      OrderUnit[] l_orderUnits = null; //�ݓ������P�ʃI�u�W�F�N�g

      l_orderUnits = l_web3RuitoOrderManager.getOrderUnits(
              Integer.parseInt(l_request.orderId));
        
      RuitoOrderUnitRow l_ruitoOrderUnitRow =
          (RuitoOrderUnitRow) l_orderUnits[0].getDataSourceObject();
      
      //�@@�ݓ���񊮗����X�|���X�I�u�W�F�N�g�𐶐����A���^�[������B
      //[�ݓ���񊮗����X�|���X�ɐݒ肷��l] 
      //�X�V���ԁF �����P��.�X�V����       
      l_response.lastUpdatedTimestamp =
          (Date) l_ruitoOrderUnitRow.getLastUpdatedTimestamp();
      
      //���ʔԍ��F ���N�G�X�g�f�[�^.����ID
      l_response.orderActionId = l_request.orderId;

      log.exiting(STR_METHOD_NAME);

      return l_response;
    }

    /**
     * �������ʃ^�C�v��Ԃ��B<BR>
     * <BR>
     * �P�j�@@����.�w����@@�̒l���h2�F�S���h�̏ꍇ�́A<BR>
     *     �ȉ��̏������s���B<BR>
     *  �@@�|����.�g���ݓ�����.get�w����@@�i���j()�̖߂�l���h<BR>
     *     0�F�I���w��h�̏ꍇ��<BR>
     * �@@�@@QuantityTypeEnum.���z��Ԃ��B<BR>
     *  �@@�|����.�g���ݓ�����.get�w����@@�i���j()�̖߂�l���h<BR>
     *     3�F���z�w��h�̏ꍇ��<BR>
     * �@@�@@QuantityTypeEnum.���z��Ԃ��B<BR>
     *  �@@�|����.�g���ݓ�����.get�w����@@�i���j()�̖߂�l���h<BR>
     *    4�F�����w��h�̏ꍇ��<BR>
     * �@@�@@QuantityTypeEnum.���ʂ�Ԃ��B<BR>
     * <BR>
     * �Q�j�@@����.�w����@@�̒l���h2�F�S���h��<BR>
     *     �Ȃ��ꍇ�́A�ȉ��̏������s���B<BR>
     * �@@ �|����.�w����@@�̒l���h3�F���z�w��h<BR>
     *    �̏ꍇ��QuantityTypeEnum.<BR>
     *    ���z��Ԃ��B<BR>
     *  �@@�|����.�w����@@�̒l���h4�F�����w��h�̏ꍇ��<BR>
     *    QuantityTypeEnum.���ʂ�Ԃ��B<BR>
     * <BR>
     * @@param l_designMethod - 2�F�S��<BR>
     * 3�F���z�w��<BR>
     * 4�F�����w��<BR>
     * @@param l_expandProduct - �g���ݓ�����<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum
     * @@roseuid 40A33B360264
     */
    public QuantityTypeEnum getOrderQuantityType(
        String l_designMethod,
        WEB3RuitoProduct l_expandProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getOrderQuantityType(String l_designMethod, "
            + "WEB3RuitoProduct l_expandProduct)";
        log.entering(STR_METHOD_NAME);
        
        if (l_expandProduct == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }
        
        log.debug("l_designMethod = " + l_designMethod);
        QuantityTypeEnum l_QuantityTypeEnum = null;

        if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_designMethod))
        {
            String l_strMethod = l_expandProduct.getPaymentMethodSell();
            
            log.debug("l_strMethod = " + l_strMethod);

            if (WEB3DesignateMethodDef.SELECT.equals(l_strMethod))
            {
                l_QuantityTypeEnum = QuantityTypeEnum.AMOUNT;
            }
            else if (WEB3DesignateMethodDef.AMOUNT.equals(l_strMethod))
            {
                l_QuantityTypeEnum = QuantityTypeEnum.AMOUNT;
            }
            else if (WEB3DesignateMethodDef.NUMBER.equals(l_strMethod))
            {
                l_QuantityTypeEnum = QuantityTypeEnum.QUANTITY;
            }
        }
        else if (WEB3SellDivDef.MONEY_DESIGNATE.equals(l_designMethod))
        {
            l_QuantityTypeEnum = QuantityTypeEnum.AMOUNT;
        }
        else if (WEB3SellDivDef.COUNT_DESIGNATE.equals(l_designMethod))
        {
            l_QuantityTypeEnum = QuantityTypeEnum.QUANTITY;
        }

        log.exiting(STR_METHOD_NAME);
        return l_QuantityTypeEnum;
    }
}
@
