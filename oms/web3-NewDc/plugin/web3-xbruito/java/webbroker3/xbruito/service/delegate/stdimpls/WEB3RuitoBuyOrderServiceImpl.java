head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoBuyOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ������t�����T�[�r�X�����N���X(WEB3RuitoBuyOrderServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/09 �� �E (���u) �V�K�쐬
                   2004/12/08 ��O�� (���u) �c�Ή�
Revesion History : 2007/10/25 ��іQ (���u) ���f��NO.094
*/
package webbroker3.xbruito.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DesignateMethodDef;
import webbroker3.common.define.WEB3MRFFundCodeDef;
import webbroker3.common.define.WEB3ReturnMethodDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.WEB3RuitoClientRequestService;
import webbroker3.xbruito.WEB3RuitoNewOrderDecisionInterceptor;
import webbroker3.xbruito.WEB3RuitoNewOrderSpec;
import webbroker3.xbruito.WEB3RuitoOrderManager;
import webbroker3.xbruito.WEB3RuitoProduct;
import webbroker3.xbruito.WEB3RuitoProductManager;
import webbroker3.xbruito.marketadaptor.WEB3RuitoMarketRequestSubmitServiceImpl;
import webbroker3.xbruito.message.WEB3RuitoBuyCompleteRequest;
import webbroker3.xbruito.message.WEB3RuitoBuyCompleteResponse;
import webbroker3.xbruito.message.WEB3RuitoBuyConfirmRequest;
import webbroker3.xbruito.message.WEB3RuitoBuyConfirmResponse;
import webbroker3.xbruito.service.delegate.WEB3RuitoBuyOrderService;
/**
 * �ݐϓ������t�����T�[�r�X�����N���X<BR>
 */
public class WEB3RuitoBuyOrderServiceImpl
    extends WEB3RuitoClientRequestService
    implements WEB3RuitoBuyOrderService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoBuyOrderServiceImpl.class);
    /**
     * �ݐϓ������t�����T�[�r�X���������{����B<BR> 
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate���t����()�Asubmit���t����()<BR>
     * ���\�b�h�̂����ꂩ���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4058282F02F0
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        else if (l_request instanceof WEB3RuitoBuyConfirmRequest)
        {
            return this.validateBuyOrder(
                (WEB3RuitoBuyConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3RuitoBuyCompleteRequest)
        {
            return this.submitBuyOrder((WEB3RuitoBuyCompleteRequest) l_request);
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
     * �ݐϓ������t�����R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�ݓ����t�����^(�ݓ�)���t�����R���v�Q��
     * <BR>
     * is����t���O��false�Ȃ��O���X���[����
     * �u����]�̓G���[�v
     *      classpath:WEB3BusinessLayerException <BR>
     *      tag:      BUSINESS_ERROR_01306 <BR>
     * <BR>
     * @@param l_request - �ݐϓ������t�����m�F���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.xbruito.message.WEB3RuitoBuyConfirmResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4058287301D7
     */
    protected WEB3RuitoBuyConfirmResponse validateBuyOrder(WEB3RuitoBuyConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateBuyOrder(WEB3RuitoBuyConfirmRequest l_request)";
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

        //1.1�@@���t���ʃ`�F�b�N
        l_request.validate();
        //1.2�@@�⏕�����擾
        SubAccount l_subAccount = this.getSubAccount();
        log.debug("�⏕���� = " + l_subAccount);
        
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
        //1.5�@@�ݓ������擾    
        WEB3RuitoProduct l_web3RuitoProduct = null; //�g���ݓ�����        
        WEB3RuitoProductManager l_web3RuitoProductManager =
            (WEB3RuitoProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.RUITO).getProductManager();
        
        //�g���ݓ������}�l�[�W��
        try
        {
            l_web3RuitoProduct =
                (WEB3RuitoProduct) l_web3RuitoProductManager.getRuitoProduct(
                    l_subAccount.getInstitution(),
                    l_request.ruitoProductCode);
            log.debug("l_expansionRuitoProduct = " + l_web3RuitoProduct);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.6�@@��t���ԃ`�F�b�N�A�V�X�e�������~�`�F�b�N
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        WEB3RuitoOrderManager l_web3RuitoOrderManager =
            (WEB3RuitoOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.RUITO).getOrderManager();
        
        log.debug(
            "Double.parseDouble(l_request.ruitoOrderQuantity) = "
                + Double.parseDouble(l_request.ruitoOrderQuantity));
        //1.7 �����R�����s���B 
        NewOrderValidationResult l_newOrderValidationResult =
            l_web3RuitoOrderManager.validateNewOrder(
                l_subAccount,
                l_web3RuitoProduct,
                Double.parseDouble(l_request.ruitoOrderQuantity),
                true,
                null,
                l_request.specifyDiv);
        log.debug("l_newOrderValidationResult = " + l_newOrderValidationResult);

        //�d�q���V�X�e���ڑ��T�[�r�X
        WEB3GentradeBatoClientService l_service =
            (WEB3GentradeBatoClientService)Services.getService(WEB3GentradeBatoClientService.class);

        //���N�G�X�g.�d�q���`�F�b�N�t���O == True �̏ꍇ�A�ژ_�����{���`�F�b�N�����{����
        //validate�ژ_�����{��(��ʃR�[�h : String, �����R�[�h : String)
        //��ʃR�[�h�F���N�G�X�g.��ʃR�[�h
        //�����R�[�h�F�ݓ����t�����m�F���N�G�X�g.�����R�[�h
        WEB3GentradeProspectusResult l_gentradeProspectusResult = null;
        if (l_request.batoCheckFlag)
        {
            l_gentradeProspectusResult =
                l_service.validateProspectus(l_request.typeCode, l_request.ruitoProductCode);
        }

        //�@@����]�̓`�F�b�N����
        //1.8 ���ʃR�[�h���̔Ԃ��� 
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService=
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);
        
        String l_strNewNumber = l_hostReqOrderNumberManageService.getNewNumber(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                ProductTypeEnum.RUITO);
        
        log.debug("�V�K���ʃR�[�h = " + l_strNewNumber);
        
        //1.9 �|�ݓ��V�K�����m��C���^�Z�v�^�I�u�W�F�N�g�𐶐����A
        WEB3RuitoNewOrderDecisionInterceptor l_ruitoNewOrderInterceptor =
            new WEB3RuitoNewOrderDecisionInterceptor();
        
        //1.10 �ݓ��V�K�����m��C���^�Z�v�^�ɑ΂���v���p�e�B�Z�b�g
        //���ʃR�[�h�F �������ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h()�̖߂�l 
        l_ruitoNewOrderInterceptor.setRequestNumber(l_strNewNumber);
        //MRF���ʃR�[�h�F null 
        l_ruitoNewOrderInterceptor.setMRFOrderRequestNumber(null);
        //�Ԋҕ��@@�F null 
        l_ruitoNewOrderInterceptor.setReturnMethod(null);
        //��n���@@�F null 
        l_ruitoNewOrderInterceptor.setPaymentMethod(null);
        //�ݓ��^�C�v�F �g���ݓ�����.getRitoType()�̖߂�l
        l_ruitoNewOrderInterceptor.setRuitoTypeEnum(l_web3RuitoProduct.getRuitoType());
        log.debug("�g���ݓ�����.getRitoType() = " + l_web3RuitoProduct.getRuitoType());        

        //�ݓ����敪�F null 
        l_ruitoNewOrderInterceptor.setRuitoSellDiv(null);
        
        //�����o�H�敪�F�Z�b�V����.�����o�H�敪
		OpLoginSecurityService l_opLoginSecService =
				(OpLoginSecurityService)Services.getService(OpLoginSecurityService.class); 
		l_ruitoNewOrderInterceptor.setOrderRootDiv(
				l_opLoginSecService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));
		log.debug("�����o�H�敪 = " + l_opLoginSecService.getSessionProperty
						(WEB3SessionAttributeDef.ORDER_ROOT_DIV));

        //�����`���l���F this.get���O�C���`���l��()�̖߂�l 
        l_ruitoNewOrderInterceptor.setOrderChannel(
            this.getLoginChannel());
                
        //�@@(*) ����.�w����@@��3�i���z�j�̏ꍇ��QuantityTypeEnum.���z���w�� 
        //  (*) ����.�w����@@��4�i�����j�̏ꍇ��QuantityTypeEnum.���ʂ��w�� 
        QuantityTypeEnum l_quantityType = null;
        if (WEB3DesignateMethodDef.AMOUNT.equals(l_request.specifyDiv))
        {
            l_quantityType = QuantityTypeEnum.AMOUNT;
        }
        else if (WEB3DesignateMethodDef.NUMBER.equals(l_request.specifyDiv))
        {
            l_quantityType = QuantityTypeEnum.QUANTITY;
        }
        
        //�|�V�K�������e�̐��� 
        WEB3RuitoNewOrderSpec l_ruitoNewOrderSpec =
            new WEB3RuitoNewOrderSpec(
                this.getTrader(),
                true,
                l_request.ruitoProductCode,
                Double.parseDouble(l_request.ruitoOrderQuantity),                
                l_quantityType,
                TaxTypeEnum.UNDEFINED);
        
        //�|����]�̓T�[�r�X���擾����validate����]��( )���R�[����
        //  ����]�͌��ʃI�u�W�F�N�g���擾����B
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        WEB3GentradeSubAccount l_gentradeSubAccount = 
            (WEB3GentradeSubAccount)l_subAccount;
        
        Object l_orderSpecIntercepters[] = new Object[1];
        l_orderSpecIntercepters[0] = l_ruitoNewOrderInterceptor;
        
        Object[] l_orderSpecs = new Object[1];
        l_orderSpecs[0] = l_ruitoNewOrderSpec;
        
        WEB3TPTradingPowerResult l_tpTradingPowerResult = 
            l_tpTradingPowerService.validateTradingPower(
                l_gentradeSubAccount,
                l_orderSpecIntercepters,
                l_orderSpecs,
                OrderTypeEnum.RUITO_BUY,
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
        
        //1.15�@@�������̎擾 
        Date l_date = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //1.16�@@����ID���̔Ԃ���B 
        //�g���ݓ������}�l�[�W��.createNewOrderId()���R�[�����āA����ID���̔Ԃ���B�@@
        long l_lngNewOrderId = l_web3RuitoOrderManager.createNewOrderId();
        
        //1.17�@@�ݓ����t�����m�F���X�|���X�I�u�W�F�N�g�𐶐����A���^�[������        
        WEB3RuitoBuyConfirmResponse l_ruitoBuyConfirmResponse =
            (WEB3RuitoBuyConfirmResponse)l_request.createResponse();
        
        //�m�F���������F �擾������������ݒ肷��B 
        l_ruitoBuyConfirmResponse.checkDate = l_date;
        
        //����ID�F�̔Ԃ�������ID
        l_ruitoBuyConfirmResponse.orderId = l_lngNewOrderId + "";

        //�ژ_�����{���`�F�b�N����
        l_ruitoBuyConfirmResponse.prospectusResult = l_gentradeProspectusResult;

        log.exiting(STR_METHOD_NAME);
        return l_ruitoBuyConfirmResponse;
    }
    /**
     * �ݐϓ������t�����o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�ݓ����t�����^(�ݓ�)���t�����o�^�v�Q��
     * <BR>
     * @@param l_request - �ݐϓ������t�����������N�G�X�g�f�[�^
     * @@return webbroker3.xbruito.message.WEB3RuitoBuyCompleteResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 405828AF030F
     */
    protected WEB3RuitoBuyCompleteResponse submitBuyOrder(WEB3RuitoBuyCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitBuyOrder(WEB3RuitoBuyCompleteRequest l_request)";
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

        //1.1�@@���N�G�X�g���e�`�F�b�N
        l_request.validate();
        //1.2�@@�������擾
        Date l_orderBizDate = 
            WEB3GentradeTradingTimeManagement.getOrderBizDate(
                l_request.checkDate);
        log.debug("������ = " + l_orderBizDate);
        
        //1.3�@@�⏕�����擾
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        log.debug("�⏕���� = " + l_subAccount);
        
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
                getClass().getName() + "." + STR_METHOD_NAME, 
                "�����~�ڋq�`�F�b�N�G���[�̏ꍇ");
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
        //�@@�ݓ������擾
        WEB3RuitoProduct l_web3RuitoProduct = null; //�g���ݓ�����  
        WEB3RuitoProductManager l_web3RuitoProductManager =
            (WEB3RuitoProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.RUITO).getProductManager();
        //�g���ݓ������}�l�[�W��
        try
        {
            l_web3RuitoProduct =
                (WEB3RuitoProduct) l_web3RuitoProductManager.getRuitoProduct(
                    l_subAccount.getInstitution(),
                    l_request.ruitoProductCode);
            
            log.debug("�g���ݓ�����.getProductId() = " + 
                    l_web3RuitoProduct.getProductId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.8�@@��t���ԃ`�F�b�N�A�V�X�e�������~�`�F�b�N    
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.9�@@�����R�����s���B
        WEB3RuitoOrderManager l_ruitoOrderManager =
            (WEB3RuitoOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.RUITO).getOrderManager();

        NewOrderValidationResult l_newOrderValidationResult =
            l_ruitoOrderManager.validateNewOrder(
                l_subAccount,
                l_web3RuitoProduct,
                Double.parseDouble(l_request.ruitoOrderQuantity),
                true,
                null,
                l_request.specifyDiv);
        
        log.debug("l_newOrderValidationResult = " + l_newOrderValidationResult);
        //�@@��n���擾
        Date l_datDeliveryDate =
            l_web3RuitoProductManager.getDeliveryDate(
                l_subAccount.getInstitution(),
                l_web3RuitoProduct.getProductCode(),
                true);
        log.debug("��n���擾 = " + l_datDeliveryDate);      
        
        RuitoTypeEnum l_ruitoTypeEnum = l_web3RuitoProduct.getRuitoType();
        log.debug("l_ruitoTypeEnum = " + l_ruitoTypeEnum);
                
        //1.10�@@MRF�������`�F�b�N 
        double l_dblTransferAmount = 0.0;   //get�����a����ւ̐U�֊z()�̖߂�l
        
        //����]�̓T�[�r�X���擾
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        // �擾�����g���ݓ������̗ݓ��^�C�v��RuitoTypeEnum.�������t�@@���h�̏ꍇ
        //  �́AMRF�̎��������s��Ȃ��B 
        // �擾������n���Ǝ擾�����������̓��t�i�N�����j���������Ȃ��ꍇ�A
        // MRF�������������Ȃ�Ȃ��B
        if (!RuitoTypeEnum.CHUUKOKU_FUND.equals(l_ruitoTypeEnum) && 
                WEB3DateUtility.compareToDay(l_datDeliveryDate, l_orderBizDate) == 0)
        {
            //�|��n���Ɣ������̓��t���������ꍇ�A����]�̓T�[�r�X���擾���āA 
            //get�����a����ւ̐U�֊z()���R�[�����AMRF�̎������v�ۂ̃`�F�b�N���s���B 
            l_dblTransferAmount = 
                l_tpTradingPowerService.getTransferAmountToEquitySubAcount(
                    (WEB3GentradeSubAccount)l_subAccount,
                    Double.parseDouble(l_request.ruitoOrderQuantity),
                    l_datDeliveryDate);
        }
        //1.11�@@���t��������
        //MRF�̎��������s���K�v������ꍇ�Athis.���t���������iMRF�������L��j()���R�[�����A
        //��������ID���擾����
        log.debug("get�����a����ւ̐U�֊z() = " + l_dblTransferAmount);
        if (l_dblTransferAmount > 0)
        {
            log.debug("MRF�̎��������s���K�v������ꍇ");
            this.buyOrderProcessMRFAutoSell(
                l_subAccount,
                l_web3RuitoProduct,
                Double.parseDouble(l_request.ruitoOrderQuantity),
                l_request.specifyDiv,
                l_dblTransferAmount,
                l_trader,
                l_request.password,
                l_request.orderId);
        }  
        //MRF�̎��������s���K�v���Ȃ��ꍇ�Athis.���t���������iMRF�������Ȃ��j()���R�[�����A
        //��������ID���擾����
        else
        {
            log.debug("MRF�̎��������s���K�v���Ȃ��ꍇ");
            this.buyOrderProcessNotMRFAutoSell(
                l_subAccount,
                l_web3RuitoProduct,
                Double.parseDouble(l_request.ruitoOrderQuantity),
                l_request.specifyDiv,
                l_trader,
                l_request.password, 
                l_request.orderId);
        }
        
        //1.12�@@���������̎擾
        Date l_datProcessDate = (Date)ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        
        //1.13�@@�ݓ����t�����������X�|���X�I�u�W�F�N�g�𐶐����A���^�[������B 
        WEB3RuitoBuyCompleteResponse l_response =
            (WEB3RuitoBuyCompleteResponse)l_request.createResponse();

        //�X�V���ԁF �擾������������ 

        l_response.lastUpdatedTimestamp = l_datProcessDate;
        log.debug("�X�V���ԁF" + WEB3DateUtility.formatDate(l_datProcessDate, "yyyyMMdd"));
        
        //���ʔԍ��F �ݓ����t�����������N�G�X�g.����ID
        l_response.orderActionId = l_request.orderId;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (���t���������iMRF�������L��j) <BR>
     * MRF�������𔺂��ݐϓ������t�����o�^���s���B<BR>
     * �V�[�P���X�}�u�ݓ����t�����^(�ݓ�)���t����(MRF�������L��)�v�Q�� <BR>
     * <BR>
     * �@@�|�g���ݓ������}�l�[�W��.submitNewOrder()�̖߂�l����<BR>
     * �@@�@@�g���ݓ������}�l�[�W��.submitNewOrder()��<BR>
     *      �߂�l.getProcessingResult().isSuccessfulResult()==false<BR>
     * �@@�@@�̏ꍇ�A��O���X���[����B<BR>
     *      class:WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_00191<BR>
     * <BR>
     * @@param l_subAccount - �⏕����<BR> 
     * @@param l_expansionRuitoProduct - �g���ݓ�����<BR> 
     * @@param l_dblBuyPriceQuantity - ���t���z����<BR>
     * @@param l_strDesignateMethod - �w����@@<BR>
     * @@param l_dblMRFSellPrice - MRF�����z<BR>
     * @@param l_proxyInputPerson - �㗝���͎�<BR>
     * @@param l_strDealingPassword - ����p�X���[�h<BR>
     * @@param l_orderId - ����ID<BR>
     * @@return long
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40762733030C
     */
    public void buyOrderProcessMRFAutoSell(
        SubAccount l_subAccount,
        WEB3RuitoProduct l_web3RuitoProduct,
        double l_dblBuyPriceQuantity,
        String l_strDesignateMethod,
        double l_dblMRFSellPrice,
        Trader l_proxyInputPerson,
        String l_strDealingPassword, 
        String l_orderId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "buyOrderProcessMRFAutoSell(SubAccount l_subAccount, "
                + "WEB3RuitoProduct l_expansionRuitoProduct, "
                + "double l_dblBuyPriceQuantity, "
                + "String l_strDesignateMethod, double l_dblMRFSellPrice, "
                + "Trader l_proxyInputPerson, String l_strDealingPassword,)"
                + "String l_orderId)";
        log.entering(STR_METHOD_NAME);

        //�ݓ��������ʃR�[�h�̔ԃT�[�r�X
        WEB3HostReqOrderNumberManageService l_WEB3HostReqOrderNumberManageService=
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);
        
        //1.1 MRF��񒍕��̎��ʃR�[�h���擾����B 
        String l_strMrfSellOrderRequestCode = 
            l_WEB3HostReqOrderNumberManageService.getNewNumber(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                ProductTypeEnum.RUITO);        

        log.debug("MRF��񒍕��̎��ʃR�[�h = " + l_strMrfSellOrderRequestCode);
        
        //1.2 �������t�@@���h�EMMF���t�����̎��ʃR�[�h���擾����B 
        String l_strFundMmfRequestNumber = 
            l_WEB3HostReqOrderNumberManageService.getNewNumber(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                ProductTypeEnum.RUITO);        

        log.debug("�������t�@@���h�EMMF���t�����̎��ʃR�[�h = " + l_strFundMmfRequestNumber);
        
        //�@@�������t�@@���h�EMMF���t����                
        //�|�ݓ��V�K�����m��C���^�Z�v�^��ݓ������}�l�[�W���ɐݒ肷��B 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3RuitoOrderManager l_ruitoOrderManager = null;
        //�ݓ������}�l�[�W��             
        l_ruitoOrderManager =
            (WEB3RuitoOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.RUITO).getOrderManager();        

        //1.3 �ݓ��V�K�����m��C���^�Z�v�^       
        WEB3RuitoNewOrderDecisionInterceptor l_ruitoNewOrderDecisionInterceptor =
            new WEB3RuitoNewOrderDecisionInterceptor();
        
        //1.4 �C���^�Z�v�^��ݒ肷��B 
        l_ruitoOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_ruitoNewOrderDecisionInterceptor);
        
        //1.5 �|�ݓ��V�K�����m��C���^�Z�v�^�Ɏ��ʃR�[�h��ݒ肷��B 
        //���ʃR�[�h�F �������t�@@���h�EMMF���t�����̎��ʃR�[�h
        l_ruitoNewOrderDecisionInterceptor.setRequestNumber(
                l_strFundMmfRequestNumber);
        log.debug(
            "�ݓ��V�K�����m��C���^�Z�v�^.���ʃR�[�h = "
                + l_ruitoNewOrderDecisionInterceptor.getRequestNumber());
        
        //1.6 �|�ݓ��V�K�����m��C���^�Z�v�^��MRF���ʃR�[�h��ݒ肷��B
        //MRF���ʃR�[�h�F MRF��񒍕��̎��ʃR�[�h
        l_ruitoNewOrderDecisionInterceptor.setMRFOrderRequestNumber(
                l_strMrfSellOrderRequestCode);
        log.debug(
            "�ݓ��V�K�����m��C���^�Z�v�^.MRF���ʃR�[�h = "
                + l_ruitoNewOrderDecisionInterceptor.getMRFOrderRequestNumber());
        
        //1.7 �|�ݓ��V�K�����m��C���^�Z�v�^�ɕԊҕ��@@��ݒ肷��B
        l_ruitoNewOrderDecisionInterceptor.setReturnMethod(null);
        //1.8 �|�ݓ��V�K�����m��C���^�Z�v�^�Ɏ�n���@@��ݒ肷��B
        l_ruitoNewOrderDecisionInterceptor.setPaymentMethod(null);
        
        //1.9 �|�ݓ��V�K�����m��C���^�Z�v�^�ɗݓ��^�C�v��ݒ肷��B
        l_ruitoNewOrderDecisionInterceptor.setRuitoTypeEnum(
                l_web3RuitoProduct.getRuitoType());
        log.debug(
            "�ݓ��V�K�����m��C���^�Z�v�^.�ݓ��^�C�v = "
                + l_ruitoNewOrderDecisionInterceptor.getRuitoTypeEnum());
        
        // �|�ݓ��V�K�����m��C���^�Z�v�^�ɗݓ����敪��ݒ肷��B
        l_ruitoNewOrderDecisionInterceptor.setRuitoSellDiv(null);
        
        // �|�ݓ��V�K�����m��C���^�Z�v�^�ɒ����o�H�敪��ݒ肷��B
		OpLoginSecurityService l_opLoginSecService =
				(OpLoginSecurityService)Services.getService(OpLoginSecurityService.class); 
		l_ruitoNewOrderDecisionInterceptor.setOrderRootDiv(
				l_opLoginSecService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));
		log.debug("�����o�H�敪 = " + l_opLoginSecService.getSessionProperty
						(WEB3SessionAttributeDef.ORDER_ROOT_DIV));
						
        //1.12 �|�ݓ��V�K�����m��C���^�Z�v�^�ɒ����`���l����ݒ肷��B 
        log.debug("this.getLoginChannel() = " + this.getLoginChannel());
        l_ruitoNewOrderDecisionInterceptor.setOrderChannel(
            this.getLoginChannel());
        
        //1.13 �|�ݓ��s�ꃊ�N�G�X�g���M�T�[�r�X�ɁA�s�ꑗ�M���������{���Ȃ��Ƃ����ݒ���s���B 
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.RUITO);
        MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
        WEB3RuitoMarketRequestSubmitServiceImpl l_ruitoMarketRequestSubmitService =
            (WEB3RuitoMarketRequestSubmitServiceImpl) l_marketAdapter
                .getMarketRequestSenderServce();
        
        l_ruitoMarketRequestSubmitService.setMarketSubmit(false);
        log.debug(
            "�ݓ��s�ꃊ�N�G�X�g���M�T�[�r�X.�s�ꑗ�M�� = "
                + l_ruitoMarketRequestSubmitService.isMarketSubmit());
        
        //1.14 �|�V�K�������e�̐���         
        MainAccountRow l_mainaccountRow = null;
        l_mainaccountRow =
            (MainAccountRow) l_subAccount.getMainAccount().getDataSourceObject();
        
        QuantityTypeEnum l_quantityTypeEnum = null;
        if (WEB3DesignateMethodDef.AMOUNT.equals(l_strDesignateMethod))
        {
            log.debug("����.�w����@@��3�i���z�j�̏ꍇ");
            l_quantityTypeEnum = QuantityTypeEnum.AMOUNT;
        }
        else if (WEB3DesignateMethodDef.NUMBER.equals(l_strDesignateMethod))
        {            
            log.debug("����.�w����@@��4�i�����j�̏ꍇ" );
            l_quantityTypeEnum = QuantityTypeEnum.QUANTITY;
        }
        WEB3RuitoNewOrderSpec l_web3RuitoNewOrderSpec =
            new WEB3RuitoNewOrderSpec(
                l_proxyInputPerson,
                true,
                l_web3RuitoProduct.getProductCode(),
                l_dblBuyPriceQuantity,
                l_quantityTypeEnum,
                TaxTypeEnum.UNDEFINED);        

        //�@@����]�̓`�F�b�N����
        //�|����]�̓T�[�r�X���擾����validate����]��( )���R�[����
        //  ����]�͌��ʃI�u�W�F�N�g���擾����B
        WEB3RuitoNewOrderDecisionInterceptor l_web3RuitoNewOrderDecisionInterceptor =
            new WEB3RuitoNewOrderDecisionInterceptor();      
        
        Object[] l_orderSpecIntercepters = new Object[1]; 
        l_orderSpecIntercepters[0] = l_web3RuitoNewOrderDecisionInterceptor;
        Object[] l_orderSpecs = new Object[1];
        l_orderSpecs[0] = l_web3RuitoNewOrderSpec;
        
        // ����]�̓T�[�r�X���擾
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        //1.15 ����]�͂̃`�F�b�N�����{����B 
        WEB3TPTradingPowerResult l_tpTradingPowerResult = 
            l_tpTradingPowerService.validateTradingPower(
                (WEB3GentradeSubAccount)l_subAccount,
                l_orderSpecIntercepters,
                l_orderSpecs,
                OrderTypeEnum.RUITO_BUY,
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
                "[����]�̓`�F�b�N�G���[]");
        }
        
        //1.18 �V�K���t����
        OrderSubmissionResult l_orderSubmissionResult = 
            l_ruitoOrderManager.submitNewOrder(
                l_subAccount,
                ProductTypeEnum.RUITO,
                l_web3RuitoNewOrderSpec,
                Long.parseLong(l_orderId),
                l_strDealingPassword,
                true);
        //�|�g���ݓ������}�l�[�W��.submitNewOrder()�̖߂�l���� 
        boolean l_blnResult = 
            l_orderSubmissionResult.getProcessingResult().isSuccessfulResult();
        log.debug("�g���ݓ������}�l�[�W��.submitNewOrder()�̖߂�l = " + l_blnResult);
        
        if (!l_blnResult)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00191,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�@@MRF��񒍕� 
        
        //1.20 �ݓ������}�l�[�W��.setThreadLocalPersistenceEventIntercepto()���R�[����,
        //�ݓ��V�K�ݓ��V�K�����m��C���^�Z�v�^��ݒ肷��
        l_ruitoOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_ruitoNewOrderDecisionInterceptor);
        
        //1.21 �|�ݓ��V�K�����m��C���^�Z�v�^�Ɏ��ʃR�[�h��ݒ肷��B
        //���ʃR�[�h�F MRF��񒍕��̎��ʃR�[�h
        l_ruitoNewOrderDecisionInterceptor.setRequestNumber(
            l_strMrfSellOrderRequestCode);
        
        log.debug("���ʃR�[�h�F = "
                + l_ruitoNewOrderDecisionInterceptor.getRequestNumber());
        
        //1.22 �|�ݓ��V�K�����m��C���^�Z�v�^��MRF���ʃR�[�h��ݒ肷��B
        //MRF���ʃR�[�h�F �������t�@@���h�EMMF���t�����̎��ʃR�[�h
        l_ruitoNewOrderDecisionInterceptor.setMRFOrderRequestNumber(
                l_strFundMmfRequestNumber);
        
        log.debug("MRF���ʃR�[�h�F "
                + l_ruitoNewOrderDecisionInterceptor.getMRFOrderRequestNumber());
        
        //�|MRF�����̎擾
        String l_strMrfFundCode = null; //MRF�R�[�h
        WEB3RuitoProduct l_mrfProduct = null; //MRF����
        WEB3RuitoProductManager l_web3RuitoProductManager =
            (WEB3RuitoProductManager) l_finApp.getTradingModule(
                    ProductTypeEnum.RUITO).getProductManager();
        
        //�g���ݓ������}�l�[�W��       
        SubAccountRow l_subAccountRow = null;
        MainAccountRow l_mainAccountRow = 
            (MainAccountRow)l_subAccount.getMainAccount().getDataSourceObject();

        log.debug("l_mainAccountRow = " + l_mainAccountRow);
        
        l_strMrfFundCode = l_mainAccountRow.getMrfFundCode();
        log.debug("MRF�R�[�h�F = " + l_strMrfFundCode);
        
        //1.24 MRF�������擾����B 
        l_mrfProduct =
            l_web3RuitoProductManager.getMRFProduct(
                l_subAccount.getInstitution(),
                l_strMrfFundCode);
        log.debug("l_mrfProduct = " + l_mrfProduct);
        
        //1.25 �ݓ��V�K�����m��C���^�Z�v�^�ɕԊҕ��@@��ݒ肷��
        log.debug("l_mrfProduct.getMRFCode() = " + l_mrfProduct.getMRFCode());
        if (WEB3MRFFundCodeDef.NOMURA.equals(l_mrfProduct.getMRFCode()))
        {
            log.debug(
                "if (l_mrfProduct.getMRFCode().equals(WEB3MRFFundCodeDef.NOMURA))");
            l_ruitoNewOrderDecisionInterceptor.setReturnMethod(
                WEB3ReturnMethodDef.CASHING);
        }
        else
        {
            log.debug(
                "l_ruitoNewOrderDecisionInterceptor.setReturnMethod(WEB3ReturnMethodDef.DAY_SELL)");
            l_ruitoNewOrderDecisionInterceptor.setReturnMethod(
                WEB3ReturnMethodDef.DAY_SELL);
        }
        //1.26 �ݓ��V�K�����m��C���^�Z�v�^�Ɏ�n���@@��ݒ肷��       
        l_ruitoNewOrderDecisionInterceptor.setPaymentMethod(null);
        //1.27 �ݓ��V�K�����m��C���^�Z�v�^�ɗݓ��^�C�v��ݒ肷��
        l_ruitoNewOrderDecisionInterceptor.setRuitoTypeEnum(RuitoTypeEnum.MRF);
        //�ݓ��V�K�����m��C���^�Z�v�^�ɗݓ����敪��ݒ肷��
        l_ruitoNewOrderDecisionInterceptor.setRuitoSellDiv(null);
        
        //1.28 �ݓ��V�K�����m��C���^�Z�v�^�ɒ����o�H�敪��ݒ肷��
		l_ruitoNewOrderDecisionInterceptor.setOrderRootDiv(
				l_opLoginSecService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));
		log.debug("�����o�H�敪 = " + l_opLoginSecService.getSessionProperty
						(WEB3SessionAttributeDef.ORDER_ROOT_DIV));

        //1.29 �ݓ��V�K�����m��C���^�Z�v�^�ɒ����`���l����ݒ肷�� get���O�C���`���l��
        l_ruitoNewOrderDecisionInterceptor.setOrderChannel(
            this.getLoginChannel());
        
        //1.30 �ݓ��s�ꃊ�N�G�X�g���M�T�[�r�X�ɁA�s�ꑗ�M���������{����Ƃ����ݒ���s��
        log.debug("MRF�������𔺂��ݐϓ������t�����o�^���s��");
        l_ruitoMarketRequestSubmitService.setMarketSubmit(true);
        log.debug(
            "l_ruitoMarketRequestSubmitService.isMarketSubmit() = "
                + l_ruitoMarketRequestSubmitService.isMarketSubmit());
        
        //1.31 �V�K�������e�̐���
        l_web3RuitoNewOrderSpec =
                new WEB3RuitoNewOrderSpec(
                    l_proxyInputPerson,
                    false,
                    l_mrfProduct.getProductCode(),
                    l_dblMRFSellPrice,
                    QuantityTypeEnum.AMOUNT,
                    TaxTypeEnum.UNDEFINED //�擾�ŋ敪
                    );
        log.debug(" l_web3RuitoNewOrderSpec =  " + l_web3RuitoNewOrderSpec);
        //1.32 MRF��񒍕�
        OrderSubmissionResult l_submissionResult = 
            l_ruitoOrderManager.submitNewOrder(
                l_subAccount,
                ProductTypeEnum.RUITO,
                l_web3RuitoNewOrderSpec,
                l_ruitoOrderManager.createNewOrderId(),
                l_strDealingPassword,
                true);
        
        //�g���ݓ������}�l�[�W��.submitNewOrder()�̖߂�l����     
        boolean l_blnSuccessfulResult;
        l_blnSuccessfulResult =
            l_submissionResult.getProcessingResult().isSuccessfulResult();
        log.debug("l_blnSuccessfulResult =  " + l_blnSuccessfulResult);
        
        if (!l_blnSuccessfulResult)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00191,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);        
    }
    /**
     * MRF�������𔺂�Ȃ��ݐϓ������t�����o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�ݓ����t�����^(�ݓ�)���t����(MRF�������Ȃ�)�v�Q�� <BR>
     * <BR>
     * �@@�|�g���ݓ������}�l�[�W��.submitNewOrder()�̖߂�l����<BR>
     * �@@�@@�g���ݓ������}�l�[�W��.submitNewOrder()��<BR>
     *      �߂�l.getProcessingResult().isSuccessfulResult()==false<BR>
     * �@@�@@�̏ꍇ�A��O���X���[����B<BR>
     *      class:WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_00191<BR>
     * <BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@param l_expansionRuitoProduct - �g���ݓ�����<BR>
     * @@param l_dblBuyPriceQuantity - ���t���z����<BR>
     * @@param l_strDesignateMethod - �w����@@<BR>
     * @@param l_proxyInputPerson - �㗝���͎�<BR>
     * @@param l_strDealingPassword - ����p�X���[�h<BR>
     * @@param l_orderId - ����ID<BR>
     * @@return long
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40764654003E
     */
    public void buyOrderProcessNotMRFAutoSell(
        SubAccount l_subAccount,
        WEB3RuitoProduct l_web3RuitoProduct,
        double l_dblBuyPriceQuantity,
        String l_strDesignateMethod,
        Trader l_proxyInputPerson,
        String l_strDealingPassword, 
        String l_orderId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "buyOrderProcessNotMRFAutoSell(SubAccount l_subAccount, " + 
            "WEB3RuitoProduct l_web3RuitoProduct, double l_dblBuyPriceQuantity, " + 
            "String l_strDesignateMethod, Trader l_proxyInputPerson, " +
            "String l_strDealingPassword)";
        
        log.entering(STR_METHOD_NAME);
        
        log.debug("l_subAccount = " + l_subAccount.getSubAccountId());
        log.debug("l_web3RuitoProduct = " + l_web3RuitoProduct.getProductCode());
        log.debug("l_proxyInputPerson = " + l_proxyInputPerson);
        log.debug("l_strDealingPassword = " + l_strDealingPassword);
        
        //1.1�@@���ʃR�[�h�̎擾
        //�ݓ��������ʃR�[�h�̔ԃT�[�r�X
        WEB3HostReqOrderNumberManageService l_reqOrderNumberManageService=
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);
        
        String l_strNewRequestNumber = l_reqOrderNumberManageService.getNewNumber(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                ProductTypeEnum.RUITO);      
      
        log.debug(" �������ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h = " + l_strNewRequestNumber);
        
        //�@@���t�������� 
        //�ݓ��V�K�����m��C���^�Z�v�^��ݓ������}�l�[�W���ɐݒ肷�� 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3RuitoOrderManager l_ruitoOrderManager = null;
        //�ݓ������}�l�[�W��
        l_ruitoOrderManager =
            (WEB3RuitoOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.RUITO).getOrderManager();
        
        //1.2�ݓ��V�K�����m��C���^�Z�v�^
        WEB3RuitoNewOrderDecisionInterceptor l_ruitoDefaultRuitoOrderDecision =
            new WEB3RuitoNewOrderDecisionInterceptor();
        
        //1.3 �C���^�Z�v�^��ݒ肷��B 
        l_ruitoOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_ruitoDefaultRuitoOrderDecision);
        log.debug(
            " l_ruitoOrderManager.getThreadLocalPersistenceEventInterceptor() = "
                + l_ruitoOrderManager
                    .getThreadLocalPersistenceEventInterceptor());
        //1.4 �ݓ��V�K�����m��C���^�Z�v�^�Ɏ��ʃR�[�h��ݒ肷��
        l_ruitoDefaultRuitoOrderDecision.setRequestNumber(
            l_strNewRequestNumber);
        //1.5 �ݓ��V�K�����m��C���^�Z�v�^��MRF���ʃR�[�h��ݒ肷��
        l_ruitoDefaultRuitoOrderDecision.setMRFOrderRequestNumber(null);
        //1.6 �ݓ��V�K�����m��C���^�Z�v�^�ɕԊҕ��@@��ݒ肷��
        l_ruitoDefaultRuitoOrderDecision.setReturnMethod(null);
        //1.7 �ݓ��V�K�����m��C���^�Z�v�^�Ɏ�n���@@��ݒ肷��
        l_ruitoDefaultRuitoOrderDecision.setPaymentMethod(null);
        //1.8 �ݓ��V�K�����m��C���^�Z�v�^�ɗݓ��^�C�v��ݒ肷��
        log.debug(
            "l_expansionRuitoProduct.getRuitoType() = "
                + l_web3RuitoProduct.getRuitoType());
        l_ruitoDefaultRuitoOrderDecision.setRuitoTypeEnum(
                l_web3RuitoProduct.getRuitoType());
        log.debug(
            "l_ruitoDefaultRuitoOrderDecision.getRuitoTypeEnum() = "
                + l_ruitoDefaultRuitoOrderDecision.getRuitoTypeEnum());
        //1.9 �ݓ��V�K�����m��C���^�Z�v�^�ɗݓ����敪��ݒ肷��
        l_ruitoDefaultRuitoOrderDecision.setRuitoSellDiv(null);
        log.debug("this.getLoginChannel() = " + this.getLoginChannel());
        
        //1.10 �ݓ��V�K�����m��C���^�Z�v�^�ɒ����o�H�敪��ݒ肷��
		OpLoginSecurityService l_opLoginSecService =
				(OpLoginSecurityService)Services.getService(OpLoginSecurityService.class); 
		l_ruitoDefaultRuitoOrderDecision.setOrderRootDiv(
				l_opLoginSecService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));
		log.debug("�����o�H�敪 = " + l_opLoginSecService.getSessionProperty
						(WEB3SessionAttributeDef.ORDER_ROOT_DIV));
						
        //1.11 �ݓ��V�K�����m��C���^�Z�v�^�ɒ����`���l����ݒ肷��  get���O�C���`���l��()
        l_ruitoDefaultRuitoOrderDecision.setOrderChannel(
            this.getLoginChannel());
        
        //1.12 �ݓ��s�ꃊ�N�G�X�g���M�T�[�r�X�ɁA�s�ꑗ�M���������{����Ƃ����ݒ���s��
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.RUITO);
        MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
        WEB3RuitoMarketRequestSubmitServiceImpl l_ruitoMarketRequestSubmitService =
            (WEB3RuitoMarketRequestSubmitServiceImpl) 
                l_marketAdapter.getMarketRequestSenderServce();
        
        log.debug("MRF�������𔺂�Ȃ��ݐϓ������t�����o�^���s��");
        l_ruitoMarketRequestSubmitService.setMarketSubmit(true);
        
        //1.14 �V�K�������e�̐���         
        WEB3RuitoNewOrderSpec l_web3RuitoNewOrderSpec = null; //�g���ݓ��V�K�������e
        MainAccountRow l_mainaccountRow =(MainAccountRow) 
            l_subAccount.getMainAccount().getDataSourceObject();
        
        log.debug("l_mainaccountRow = " + l_mainaccountRow);
        log.debug("l_strDesignateMethod = " + l_strDesignateMethod);
        log.debug(
            "l_mainaccountRow.getTaxType() = " + l_mainaccountRow.getTaxType());
        if (WEB3DesignateMethodDef.AMOUNT.equals(l_strDesignateMethod))
        {
            l_web3RuitoNewOrderSpec =
                new WEB3RuitoNewOrderSpec(
                    l_proxyInputPerson,
                    true,
                    l_web3RuitoProduct.getProductCode(),
                    l_dblBuyPriceQuantity,
                    QuantityTypeEnum.AMOUNT,
                    TaxTypeEnum.UNDEFINED);
                
            log.debug("l_ruitoNewOrder = " + l_web3RuitoNewOrderSpec);
        }
        else if (WEB3DesignateMethodDef.NUMBER.equals(l_strDesignateMethod))
        {
            l_web3RuitoNewOrderSpec =
                new WEB3RuitoNewOrderSpec(
                    l_proxyInputPerson,
                    true,
                    l_web3RuitoProduct.getProductCode(),
                    l_dblBuyPriceQuantity,
                    QuantityTypeEnum.QUANTITY,
                    TaxTypeEnum.UNDEFINED);
            log.debug("l_ruitoNewOrder =" + l_web3RuitoNewOrderSpec);
        }
        
        //===============================================================
        //1.15 �|����]�̓`�F�b�N���� 
        //�|����]�̓T�[�r�X���擾����validate����]��( )���R�[����
        //  ����]�͌��ʃI�u�W�F�N�g���擾����B
        WEB3RuitoNewOrderDecisionInterceptor l_web3RuitoNewOrderDecisionInterceptor =
            new WEB3RuitoNewOrderDecisionInterceptor();      
        
        Object[] l_orderSpecIntercepters = new Object[1]; 
        l_orderSpecIntercepters[0] = l_web3RuitoNewOrderDecisionInterceptor;
        Object[] l_orderSpecs = new Object[1];
        l_orderSpecs[0] = l_web3RuitoNewOrderSpec;
        
        //����]�̓T�[�r�X���擾
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        WEB3TPTradingPowerResult l_tpTradingPowerResult = 
            l_tpTradingPowerService.validateTradingPower(
                (WEB3GentradeSubAccount)l_subAccount,
                l_orderSpecIntercepters,
                l_orderSpecs,
                OrderTypeEnum.RUITO_BUY,
                true);
        
        //1.16 �|�擾��������]�͌��ʃI�u�W�F�N�g.is����t���O( )��false�̏ꍇ�A 
        //[����]�̓`�F�b�N�G���[]�Ƃ��ė�O���X���[����B
        if (!l_tpTradingPowerResult.isResultFlg())
        {
            log.debug("����]�̓`�F�b�N�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                getClass().getName() + "." + STR_METHOD_NAME, 
                "[����]�̓`�F�b�N�G���[]");
        }     
        //=========================================================
        
        //1.17 ���t����     
        OrderSubmissionResult l_orderSubmissionResult = null;
        l_orderSubmissionResult =
            l_ruitoOrderManager.submitNewOrder(
                l_subAccount,
                ProductTypeEnum.RUITO,
                l_web3RuitoNewOrderSpec,
                Long.parseLong(l_orderId),
                l_strDealingPassword,
                true);
        
        //1.18 �g���ݓ������}�l�[�W��.submitNewOrder()�̖߂�l����
        boolean l_blnResult =
            l_orderSubmissionResult.getProcessingResult().isSuccessfulResult();
        log.debug("�g���ݓ������}�l�[�W��.submitNewOrder()�̖߂�l = " + l_blnResult);
        
        if (!l_blnResult)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00191,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        

        log.exiting(STR_METHOD_NAME);        
    }
}@
