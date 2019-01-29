head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.32.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeToIfoDepositServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �؋����ւ̐U�փT�[�r�XImpl(WEB3AccTransChangeToIfoDepositServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ������ (���u) �V�K�쐬
                   2004/10/26 ���E(���u) ���r���[
                   2004/12/09 ���E (���u) �c�Ή�
Revesion History : 2007/08/23 ���g (���u) �d�l�ύX�E���f��752
Revesion History : 2009/03/16 �Ԑi (���u) �d�l�ύX�E���f��1142
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderRow;
import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioTransferOrderUpdateInterceptor;
import webbroker3.aio.message.WEB3AccTransChangeToIfoDepositCompleteRequest;
import webbroker3.aio.message.WEB3AccTransChangeToIfoDepositCompleteResponse;
import webbroker3.aio.message.WEB3AccTransChangeToIfoDepositConfirmRequest;
import webbroker3.aio.message.WEB3AccTransChangeToIfoDepositConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AccTransChangeToIfoDepositService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.ifodeposit.WEB3IfoDepositCalc;
import webbroker3.ifodeposit.WEB3IfoDepositCalcService;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3DateUtility;

/**
 * (�؋����ւ̐U�փT�[�r�XImpl)<BR>
 * �؋����ւ̐U�փT�[�r�X�����N���X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3AccTransChangeToIfoDepositServiceImpl
    extends WEB3ClientRequestService
    implements WEB3AccTransChangeToIfoDepositService
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AccTransChangeToIfoDepositServiceImpl.class);

    /**
     * �؋����ւ̐U�փT�[�r�X�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��validate����()�A<BR>
     * �܂���submit����()���\�b�h���R�[������B <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3GenResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4135AEFC01AC
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AccTransChangeToIfoDepositConfirmRequest)
        {
            // validate����()
            l_response =
                this.validateOrder(
                    (WEB3AccTransChangeToIfoDepositConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3AccTransChangeToIfoDepositCompleteRequest)
        {
            // submit����()
            l_response =
                this.submitOrder(
                    (WEB3AccTransChangeToIfoDepositCompleteRequest) l_request);
        }
        else
        {
            log.debug(
                STR_METHOD_NAME
                    + "���N�G�X�g�f�[�^��"
                    + " WEB3AccTransChangeToIfoDepositConfirmRequest "
                    + " �� WEB3AccTransChangeToIfoDepositCompleteRequest�ȊO�ł���, but is " + l_request.getClass().getName());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate����)<BR>
     * �U�֒����̔����R�����s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�؋����ւ̐U�ցjvalidate�����v�Q�ƁB<BR>
     * ========================================================<BR>
     * �V�[�P���X�}(�u(�؋����U�փT�[�r�X���f��) / �؋����ւ̐U�� �v<BR>
     * �i�؋����ւ̐U�ցjvalidate���� )<BR>
     * �@@�@@�@@:  1.11.calc�ڋq����c��(OrderTypeEnum)<BR>   
     *     ���N�G�X�g�f�[�^.�U�֋��z > calc�ڋq����c��()�̖߂�l�̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00761<BR>
     * <BR>
     * ==========================================================<BR>
     * 1.20 �߂�l�̎���]�͌���.����t���O == false �̏ꍇ�A��O���X���[�� <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01306 <BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * 
     * @@return webbroker3.aio.message.WEB3AccTransChangeToIfoDepositConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4135AEFC01AE
     */
    protected WEB3AccTransChangeToIfoDepositConfirmResponse validateOrder(
        WEB3AccTransChangeToIfoDepositConfirmRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(" +
            "WEB3AccTransChangeToIfoDepositConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1.1) ���N�G�X�g�f�[�^�̐��������`�F�b�N����
        l_request.validate();
        
        // 1.2)�⏕�����I�u�W�F�N�g���擾����B 
        //�⏕�����^�C�v�F 7�i�؋��������j 
        SubAccount l_subAccountOptions = this.getSubAccount(
                SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
                
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = (WEB3AioOrderManager) l_tradingModule.getOrderManager();
        
        // 1.3)�@@validate����(SubAccount)
        // �|��t���ԃ`�F�b�N 
        // �|�V�X�e����~���`�F�b�N 
        // �|�ڋq�̃`�F�b�N�i�x�q�A�Ǘ����b�N���j
        l_orderManager.validateOrder(l_subAccountOptions);
        
        // 1.4) �敨����������J�݂��Ă��邩���`�F�b�N����
        l_orderManager.validateOpenFuturesTradedAccount(l_subAccountOptions);
        
        // 1.5) get������()
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        // 1.6) �U�։񐔂��A�U�։\�񐔂𒴂��ĂȂ����`�F�b�N����
        l_orderManager.validateTransferPossibleCount(l_subAccountOptions,l_datBizDate, OrderCategEnum.CASH_TRANSFER);

        //========remain zhou-yong NO.1 begin ===========
        
        //1.9) get�؋����v�Z(�⏕���� : �⏕����)
        //�A�C�e���̒�`
        //�؋����v�Z�C���X�^���X�𐶐�����B
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        WEB3IfoDepositCalcService l_service =
            (WEB3IfoDepositCalcService) Services.getService(
                WEB3IfoDepositCalcService.class);
        
        WEB3IfoDepositCalc l_ifoDepositCalc = l_service.getIfoDepositCalc(
            (WEB3GentradeSubAccount)l_subAccountOptions);

        //�؋����U�ւ̎w������擾����B
        //[����] �������F get������()�̖߂�l
        int l_intBizDate =
            l_orderManager.getMarginTransferDesignatedDate(l_datBizDate);

        //1.10)  calc�؋����c��(�w��� : int)
        //�؋����c�����Z�o����
        //[����] 
        //�w����F get�؋����U�֎w���()�̖߂�l
        double l_dblCalcIfoDepositBalance = 
            l_ifoDepositCalc.calcIfoDepositBalance(l_intBizDate);
        
        //1.11) calc�������z( )
        //�A�C�e���̒�`
        //�؋����������z���Z�o����B 
        double l_dblCalcNonPayAmount = l_ifoDepositCalc.calcNonPayAmount();
        
        //1.12) get�⏕����(SubAccountTypeEnum)
        //�A�C�e���̒�`
        //�⏕�����I�u�W�F�N�g���擾����B
        //[����] 
        //�⏕�����^�C�v�F 1�i�a��������j 
        SubAccount l_subAccountEquity = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.13) ���iID�i����ID�j���擾����B 
        //[����] 
        //�،���ЁF �⏕����.get����X().getInstitution()�̖߂�l 
        long l_lngProductId = 
            l_orderManager.getProductId(l_subAccountOptions.getInstitution());        
        
        //1.14) ���o���������e�C���X�^���X�𐶐�����B 
        //[����] 
        //�㗝���͎ҁF null 
        //������ʁF 1007�i�U�֒����i�a������犔��؋����j�j 
        //�U�փ^�C�v�F 2�i�o���j 
        //���iID�F get���iID()�̖߂�l 
        //���z�F ���N�G�X�g�f�[�^.�U�֋��z 
        //�L�q�F null 
        //�U�֗\����F get������()�̖߂�l 
        //���ϊ���ID�F null 
        //����ID�F null 
        
        WEB3AioNewOrderSpec l_aioNewOrderSpecOut = 
            new WEB3AioNewOrderSpec(
                null, 
                OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN, 
                AssetTransferTypeEnum.CASH_OUT, 
                l_lngProductId, 
                Double.parseDouble(l_request.changeAmt), 
                null, 
                l_datBizDate, 
                null, 
                null);
        
        //1.15) �U�֒����X�V�C���^�Z�v�^�̃C���X�^���X�𐶐�����B 
        //[����] 
        //���o���������e�F ���o���������e�I�u�W�F�N�g�i�U�֌������j 
        WEB3AioTransferOrderUpdateInterceptor l_orderUpdateInterceptorOut =
            new WEB3AioTransferOrderUpdateInterceptor(l_aioNewOrderSpecOut);
        
        //1.16)(*1)�v���p�e�B�Z�b�g 
        l_orderUpdateInterceptorOut.setBizDate(l_datBizDate);
        l_orderUpdateInterceptorOut.setDeliveryDate(l_datBizDate);
        
        //1.17) ���o���������e�C���X�^���X�𐶐�����B 
        //[����] 
        //�㗝���͎ҁF null 
        //������ʁF 1007�i�U�֒����i�a������犔��؋����j�j 
        //�U�փ^�C�v�F 1�i�����j 
        //���iID�F get���iID()�̖߂�l 
        //���z�F ���N�G�X�g�f�[�^.�U�֋��z 
        //�L�q�F null 
        //�U�֗\����F get������()�̖߂�l 
        //���ϊ���ID�F null 
        //����ID�F null 
        WEB3AioNewOrderSpec l_aioNewOrderSpecIn = 
            new WEB3AioNewOrderSpec(
                null, 
                OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN, 
                AssetTransferTypeEnum.CASH_IN, 
                l_lngProductId, 
                Double.parseDouble(l_request.changeAmt), 
                null, 
                l_datBizDate, 
                null, 
                null);
        
        //1.18) �U�֒����X�V�C���^�Z�v�^�̃C���X�^���X�𐶐�����B 
        //[����] 
        //���o���������e�F ���o���������e�I�u�W�F�N�g�i�U�֐撍���j 
        WEB3AioTransferOrderUpdateInterceptor l_orderUpdateInterceptorIn =
            new WEB3AioTransferOrderUpdateInterceptor(l_aioNewOrderSpecIn);
        
        //1.19) (*2)�v���p�e�B�Z�b�g 
        l_orderUpdateInterceptorIn.setBizDate(l_datBizDate);
        l_orderUpdateInterceptorIn.setDeliveryDate(l_datBizDate);
        
        //�������e�C���^�Z�v�^�̔z��
        WEB3AioTransferOrderUpdateInterceptor[] l_orderUpdateInterceptor = 
            {l_orderUpdateInterceptorOut, l_orderUpdateInterceptorIn};
            
        //�������e�̔z��
        WEB3AioNewOrderSpec[] l_aioNewOrderSpec = 
            {l_aioNewOrderSpecOut, l_aioNewOrderSpecIn}; 
        
        //1.20) �]�͂̃`�F�b�N���s���B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l�i�a��������j 
        //�������e�C���^�Z�v�^�F �U���������ƐU���撍���̃C���^�Z�v�^�̔z�� 
        //�������e�F �U���������ƐU���撍���̒������e�̔z�� 
        //������ʁF 1007�i�U�֒����i�a������犔��؋����j�j 
        //�]�͍X�V�t���O�F false 
        WEB3TPTradingPowerService l_tpTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        WEB3TPTradingPowerResult l_powerResult =      
            l_tpTradingPowerService.validateTradingPower(
                (WEB3GentradeSubAccount)l_subAccountEquity, 
                l_orderUpdateInterceptor, 
                l_aioNewOrderSpec, 
                OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN, 
                false);
        
        //����]�͌���.get����\�z()�̖߂�l
        double l_dblTradingPower = l_powerResult.getTradingPower();
        
        //�߂�l�̎���]�͌���.����t���O == false �̏ꍇ�A��O���X���[��
        if (l_powerResult.isResultFlg() == false)
        {
            log.debug("����]�̓`�F�b�N�G���[�B"); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����]�̓`�F�b�N�G���[�B");     
        }
        //1.21) createNewOrderId( )
        //�A�C�e���̒�`
        //�V�K�̒���ID���擾����B 
        long l_lngNewOrderId = l_orderManager.createNewOrderId();
        
        //1.22) createResponse( )
        //�A�C�e���̒�`
        //���X�|���X�f�[�^�𐶐�����B 
        WEB3AccTransChangeToIfoDepositConfirmResponse l_response = 
            (WEB3AccTransChangeToIfoDepositConfirmResponse)l_request.createResponse();
        
        //1.23)  (*) �v���p�e�B�Z�b�g
        //(*) �ȉ��̂Ƃ���A�v���p�e�B���Z�b�g����B
        //���X�|���X.�U�֌�U�։\�z = ����]�͌���.get����\�z()�̖߂�l
        l_response.aftChangePossAmt = 
            WEB3StringTypeUtility.formatNumber(l_dblTradingPower);        
        
        //���X�|���X.�U�֑O�؋��� = �؋����v�Z.calc�؋����c��()�̖߂�l
        l_response.preIfoDeposit = WEB3StringTypeUtility.formatNumber(l_dblCalcIfoDepositBalance);
        
        //���X�|���X.�U�֌�؋��� = �؋����v�Z.calc�؋����c��()�̖߂�l + ���N�G�X�g�f�[�^.�U�֋��z
        l_response.aftIfoDeposit = 
            WEB3StringTypeUtility.formatNumber(l_dblCalcIfoDepositBalance + Double.parseDouble(l_request.changeAmt));
        
        //���X�|���X.�U�֌㖢�����z = �؋����v�Z.calc�������z()�̖߂�l - ���N�G�X�g�f�[�^.�U�֋��z
        //  �i���v�Z���� < 0 �̏ꍇ�́A0���Z�b�g����B�j
        double l_dblAftNonPayAmt = l_dblCalcNonPayAmount - Double.parseDouble(l_request.changeAmt);
        if (l_dblAftNonPayAmt < 0)
        {
            l_dblAftNonPayAmt = 0;
        }
        l_response.aftNonPayAmt = 
            WEB3StringTypeUtility.formatNumber(l_dblAftNonPayAmt);

        //���X�|���X.�m�F�������� = ������ԊǗ�.get������()�̖߂�l
        l_response.checkDate = l_datBizDate;
        
        //���X�|���X.����ID = AIO�����}�l�[�W��.createNewOrderId()�̖߂�l
        l_response.orderId = String.valueOf(l_lngNewOrderId);
        
        //========remain zhou-yong NO.1 end =========== 
        
        // ���^�[��           
        log.exiting(STR_METHOD_NAME);     
        return l_response;
    }

    /**
     * (submit����)<BR>
     * �U�֒����̓o�^���s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�؋����ւ̐U�ցjsubmit�����P�A�Q�v�Q�ƁB<BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u(�؋����U�փT�[�r�X���f��) / �؋����ւ̐U�� �v<BR>
     * �i�؋����ւ̐U�ցjsubmit���� )<BR>
     * �@@�@@�@@:  1.8.calc�ڋq����c��(OrderTypeEnum)<BR>   
     *     ���N�G�X�g�f�[�^.�U�֋��z > calc�ڋq����c��()�̖߂�l�̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00761<BR>
     * <BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * 
     * @@return webbroker3.aio.message.WEB3AccTransChangeToIfoDepositCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4135AEFC01B0
     */
    protected WEB3AccTransChangeToIfoDepositCompleteResponse submitOrder(
        WEB3AccTransChangeToIfoDepositCompleteRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder(" +
            "WEB3AccTransChangeToIfoDepositCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1) �V�[�P���X�}
        //�i�؋����ւ̐U�ցjsubmit�����P
        
        //submit����1
        //1.1) validate( )
        //�A�C�e���̒�`
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B 
        l_request.validate();
        
        //1.2) get�⏕����(SubAccountTypeEnum)  (�؋�������)
        //�A�C�e���̒�`
        //�⏕�����I�u�W�F�N�g���擾����B
        //[����] 
        //�⏕�����^�C�v�F 7�i�؋��������j 
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
        
        //1.3) validate����(SubAccount)
        //�A�C�e���̒�`
        //�ȉ��̃`�F�b�N���s���B 
        //�@@�|��t���ԃ`�F�b�N 
        //�@@�|�V�X�e����~���`�F�b�N 
        //�@@�|�ڋq�̃`�F�b�N�i�x�q�A�Ǘ����b�N���j
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = (WEB3AioOrderManager) l_tradingModule.getOrderManager();
        
        l_orderManager.validateOrder(l_subAccount);
        
        //1.4) validate�敨��������J��(SubAccount)
        //�A�C�e���̒�`
        //�敨����������J�݂��Ă��邩���`�F�b�N����B
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l
        l_orderManager.validateOpenFuturesTradedAccount(l_subAccount);
        
        //1.5) get������(�m�F�������� : Date)
        //�A�C�e���̒�`
        //���������擾����B 
        //�����̊m�F���������Ǝ擾��������������v���Ȃ��ꍇ�́A��O���X���[����B
        //[����] 
        //�m�F���������F ���N�G�X�g�f�[�^.�m�F�������� 
        Date l_datOrderBizDate = 
            WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);

        //1.6) validate�U�։\��(SubAccount, Date, OrderCategEnum)
        //�A�C�e���̒�`
        //�U�։񐔂��A�U�։\�񐔂𒴂��ĂȂ����`�F�b�N����B
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�������F get������()�̖߂�l 
        //�����J�e�S���F�@@13�i�U�ցj
        l_orderManager.validateTransferPossibleCount(l_subAccount, l_datOrderBizDate, OrderCategEnum.CASH_TRANSFER);
        
        //1.7) get�⏕����(SubAccountTypeEnum) �i�a�������)
        //�A�C�e���̒�`
        //�⏕�����I�u�W�F�N�g���擾����B
        //[����] 
        //�⏕�����^�C�v�F 1�i�a��������j 
        SubAccount l_subAccount2 = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        WEB3GentradeSubAccount l_gentradeSubAccount2 = (WEB3GentradeSubAccount)l_subAccount2;
        
        //1.8) get�㗝���͎�( )
        //�A�C�e���̒�`
        //�㗝���͎҃I�u�W�F�N�g���擾����B
        Trader l_trader = this.getTrader();
        
        //1.9)  get�V�K���ʃR�[�h(String, String, ProductTypeEnum) �i���ʃR�[�h�@@)
        //�A�C�e���̒�`
        //�V�K�̎��ʃR�[�h���擾����B
        //[����] 
        //�،���ЃR�[�h�F �⏕����.get����X().getInstitution().getInstitutionCode()�̖߂�l 
        //���X�R�[�h�F �⏕����.get����X().getBranchCode()�̖߂�l 
        //�����^�C�v�F 5�i�����j
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService=
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class); 
        
        String l_strNewNumber1 = l_hostReqOrderNumberManageService.getNewNumber(
            l_subAccount.getInstitution().getInstitutionCode(), 
            l_subAccount.getMainAccount().getBranch().getBranchCode(), 
            ProductTypeEnum.CASH);

        //1.10) get���iID(Institution)
        //�A�C�e���̒�`
        //���iID�i����ID�j���擾����B 
        //[����] 
        //�،���ЁF �⏕����.get����X().getInstitution()�̖߂�l 
        long l_lngProductId = l_orderManager.getProductId(l_subAccount.getInstitution());
        
        //1.11) ���o���������e(Trader, OrderTypeEnum, AssetTransferTypeEnum, long,
        //double, String, Date, String, Long) �i�U�֌������@@�j
        //�A�C�e���̒�`
        //���o���������e�C���X�^���X�𐶐�����B
        //[����] 
        //�㗝���͎ҁF get�㗝���͎�()�̖߂�l 
        //������ʁF 1007�i�U�֒����i�a������犔��؋����j�j 
        //�U�փ^�C�v�F 2�i�o���j 
        //���iID�F get���iID()�̖߂�l 
        //���z�F ���N�G�X�g�f�[�^.�U�֋��z 
        //�L�q�F null 
        //�U�֗\����F get������()�̖߂�l 
        //���ϊ���ID�F null 
        //����ID�F null 
        WEB3AioNewOrderSpec l_aioNewOrderSpec1 = new WEB3AioNewOrderSpec(
            l_trader,
            OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN,
            AssetTransferTypeEnum.CASH_OUT,
            l_lngProductId,
            Double.parseDouble(l_request.changeAmt),
            null,
            l_datOrderBizDate,
            null,
            null); 
        
        //1.12) �U�֒����X�V�C���^�Z�v�^(���o���������e)
        //�A�C�e���̒�`
        //�U�֒����X�V�C���^�Z�v�^�̃C���X�^���X�𐶐�����B
        //[����] 
        //���o���������e�F ���o���������e�I�u�W�F�N�g�i�U�֌������@@�j 
        WEB3AioTransferOrderUpdateInterceptor l_transferOrderUpdateInterceptor1 =
            new WEB3AioTransferOrderUpdateInterceptor(l_aioNewOrderSpec1);
        
        //1.13) (*1)�v���p�e�B�Z�b�g
        //(*1)(*2) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        //�C���^�Z�v�^.������ = get������()�̖߂�l
        l_transferOrderUpdateInterceptor1.setBizDate(l_datOrderBizDate);
        
        //�C���^�Z�v�^.��n�� = get������()�̖߂�l
        l_transferOrderUpdateInterceptor1.setDeliveryDate(l_datOrderBizDate);
        
        //�C���^�Z�v�^.���ʃR�[�h = get�V�K���ʃR�[�h()�̖߂�l�i���ʃR�[�h�@@�j
        l_transferOrderUpdateInterceptor1.setOrderRequestNumber(l_strNewNumber1);
        
        l_transferOrderUpdateInterceptor1.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);
        
        //1.14) ���o���������e(Trader, OrderTypeEnum, AssetTransferTypeEnum, 
        //long, double, String, Date, String, Long) (�U�֐撍���@@)
        //�A�C�e���̒�`
        //���o���������e�C���X�^���X�𐶐�����B
        // [����] 
        //�㗝���͎ҁF get�㗝���͎�()�̖߂�l 
        //������ʁF 1007�i�U�֒����i�a������犔��؋����j�j 
        //�U�փ^�C�v�F 1�i�����j 
        //���iID�F get���iID()�̖߂�l 
        //���z�F ���N�G�X�g�f�[�^.�U�֋��z 
        //�L�q�F null 
        //�U�֗\����F get������()�̖߂�l 
        //���ϊ���ID�F null 
        //����ID�F null 
        WEB3AioNewOrderSpec l_aioNewOrderSpec2 = new WEB3AioNewOrderSpec(
            l_trader,
            OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN,
            AssetTransferTypeEnum.CASH_IN,
            l_lngProductId,
            Double.parseDouble(l_request.changeAmt),
            null,
            l_datOrderBizDate,
            null,
            null); 
        
        //1.15) �U�֒����X�V�C���^�Z�v�^(���o���������e)
        //�A�C�e���̒�`
        //�U�֒����X�V�C���^�Z�v�^�̃C���X�^���X�𐶐�����B
        //[����] 
        //���o���������e�F ���o���������e�I�u�W�F�N�g�i�U�֐撍���@@�j 
        WEB3AioTransferOrderUpdateInterceptor l_transferOrderUpdateInterceptor2 =
            new WEB3AioTransferOrderUpdateInterceptor(l_aioNewOrderSpec2);
        
        //1.16)  (*2)�v���p�e�B�Z�b�g
        //(*1)(*2) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        //�C���^�Z�v�^.������ = get������()�̖߂�l
        l_transferOrderUpdateInterceptor2.setBizDate(l_datOrderBizDate);
        
        //�C���^�Z�v�^.��n�� = get������()�̖߂�l
        l_transferOrderUpdateInterceptor2.setDeliveryDate(l_datOrderBizDate);
        
        //�C���^�Z�v�^.���ʃR�[�h = get�V�K���ʃR�[�h()�̖߂�l�i���ʃR�[�h�@@�j
        l_transferOrderUpdateInterceptor2.setOrderRequestNumber(l_strNewNumber1);
        
        //MQ�X�e�[�^�X = 1:���M�ς�
        l_transferOrderUpdateInterceptor2.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);
        
        //1.17) validate����]��(�⏕���� : �⏕����, �������e�C���^�Z�v�^ : Object[], 
        //�������e : Object[], ������� : OrderTypeEnum, �]�͍X�V�t���O : boolean)
        //�A�C�e���̒�`
        //�]�͎c���̍X�V���s���B
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l�i�a��������j 
        //�������e�C���^�Z�v�^�F �U���������@@�ƐU���撍���@@�̃C���^�Z�v�^�̔z�� 
        //�������e�F �U���������@@�ƐU���撍���@@�̒������e�̔z�� 
        //������ʁF 1007�i�U�֒����i�a������犔��؋����j�j 
        //�]�͍X�V�t���O�F false
        Object[] l_objCashoutCancelUpdate= {l_transferOrderUpdateInterceptor1,
                l_transferOrderUpdateInterceptor2};
        
        Object[] l_objNewOrderSpec= {l_aioNewOrderSpec1,l_aioNewOrderSpec2};
        
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        WEB3TPTradingPowerResult l_tPTradingPowerResult = 
            l_service.validateTradingPower(l_gentradeSubAccount2, l_objCashoutCancelUpdate, 
                l_objNewOrderSpec, OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN, false);
        
        //�߂�l�̎���]�͌���.����t���O == false �̏ꍇ�A��O���X���[����B
        if(!l_tPTradingPowerResult.isResultFlg())
        {
            log.debug("����]�̓`�F�b�N�G���[�B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����]�͌���.����t���O == false");            
        }        

        WEB3GentradeMainAccount l_genMainAccount = 
            (WEB3GentradeMainAccount) l_subAccount.getMainAccount();        
        
        boolean l_blnMarginAccountEstablished =
            l_genMainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
        
        double l_dblTransferAmountToEquitySubAcount = 0.0;
        SubAccount l_subAccount3 = null;
        
        //�⏕����.getMainAccount().is�M�p�����J��(�ٍϋ敪�i=�h�w��Ȃ��h�j)�̖߂�l = true �̏ꍇ�A���{
        if(l_blnMarginAccountEstablished)
        {
            //1.18)�ۏ؋���������a��������֐U�ւ��K�v�Ȋz���擾����B 
            //[����] 
            //�⏕�����F get�⏕����()�̖߂�l�i�ۏ؋������j 
            //�K�v�����F ���N�G�X�g�f�[�^.�U�֋��z 
            //��n���F get������()�̖߂�l 
            l_subAccount3 = this.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);          
       
            WEB3GentradeSubAccount l_gentradeSubAccount3 = (WEB3GentradeSubAccount)l_subAccount3;
            
            //1.19) get�a����ւ̐U�֊z(�⏕���� : �⏕����, �K�v���� : double, ��n�� : Date)
            //�ۏ؋���������a��������֐U�ւ��K�v�Ȋz���擾����B 
            //[����] 
            //�⏕�����F get�⏕����()�̖߂�l�i�ۏ؋������j 
            //�K�v�����F ���N�G�X�g�f�[�^.�U�֋��z 
            //��n���F get������()�̖߂�l            
            l_dblTransferAmountToEquitySubAcount = 
                l_service.getTransferAmountToEquitySubAcount(
                    l_gentradeSubAccount3, 
                    Double.parseDouble(l_request.changeAmt), 
                    l_datOrderBizDate);
           
        }
        //1.20) submit�U�֒���(SubAccount, ProductTypeEnum, OrderTypeEnum, NewOrderSpec, 
        //AioOrderManagerPersistenceInterceptor, long, String)
        //�A�C�e���̒�`
        //�U�֌��̐U�֒�����o�^����B
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l�i�a��������j 
        //�����^�C�v�F 5�i�����j 
        //������ʁF 1007�i�U�֒����i�a������犔��؋����j�j 
        //�������e�F ���o���������e�i�U�֌������@@�j 
        //�C���^�Z�v�^�F �U�֒����X�V�C���^�Z�v�^�i�U�֌������@@�j 
        //����ID�F ���N�G�X�g�f�[�^.����ID 
        //�p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ� 
        l_orderManager.submitTransferOrder(
            l_subAccount2,
            ProductTypeEnum.CASH,
            OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN,
            l_aioNewOrderSpec1,
            l_transferOrderUpdateInterceptor1,
            Long.parseLong(l_request.orderId),
            l_request.password);
        
        //1.21) createNewOrderId( ) (����ID�@@)
        //�A�C�e���̒�`
        //���Β����p�̒���ID���擾����B
        long l_lngNewOrderId = l_orderManager.createNewOrderId();
        
        //1.22) submit�U�֒���(SubAccount, ProductTypeEnum, OrderTypeEnum, NewOrderSpec,
        //AioOrderManagerPersistenceInterceptor, long, String)
        //�A�C�e���̒�`
        //�U�֐�̐U�֒�����o�^����B
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l�i�؋��������j 
        //�����^�C�v�F 5�i�����j 
        //������ʁF 1007�i�U�֒����i�a������犔��؋����j�j 
        //�������e�F ���o���������e�i�U�֐撍���@@�j 
        //�C���^�Z�v�^�F �U�֒����X�V�C���^�Z�v�^�i�U�֐撍���@@�j 
        //����ID�F createNewOrderId()�̖߂�l�i����ID�@@�j 
        //�p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ� 
        l_orderManager.submitTransferOrder(
            l_subAccount,
            ProductTypeEnum.CASH,
            OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN,
            l_aioNewOrderSpec2,
            l_transferOrderUpdateInterceptor2,
            l_lngNewOrderId,
            l_request.password);
        
        //1.23) �V�[�P���X�}
        //�i�؋����ւ̐U�ցjsubmit�����Q
        
		//�@@�Ɩ����t�̎擾�iyyyyMMdd�j
		String l_strBizDate1 = WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMMdd");
		Date l_datBizDate1 = WEB3DateUtility.getDate(l_strBizDate1, "yyyyMMdd");
		
		//�@@��������yyyyMMdd�^�ɕύX
		String l_strOrderBizdat1 = WEB3DateUtility.formatDate(l_datOrderBizDate, "yyyyMMdd");
		Date l_datOrderBizDate1 = WEB3DateUtility.getDate(l_strOrderBizdat1, "yyyyMMdd");
		
		//1.2) is�ۏ؋��U��(�⏕���� l_subAccount, Date �Ɩ����t, Date �����P��.������)
		boolean l_blnIsDepositTransfer = l_orderManager.isDepositTransfer(l_subAccount, l_datBizDate1, l_datOrderBizDate1);
      
        //1.3) �⏕����.getMainAccount().is�M�p�����J��()�̖߂�l = true and
        //get�����a����ւ̐U�֊z()�̖߂�l > 0 and
        //is�ۏ؋��U��()�̖߂�l = true�̏ꍇ
        //�M�p�ۏ؋�����a����ւ̐U�֒������o�^����B
        if (l_blnMarginAccountEstablished && 
            l_dblTransferAmountToEquitySubAcount > 0 &&
		    l_blnIsDepositTransfer)
        {
            //1.3.1) get�V�K���ʃR�[�h(String, String, ProductTypeEnum)
            //�V�K�̎��ʃR�[�h���擾����B
            //[����] 
            //�،���ЃR�[�h�F �⏕����.get����X().getInstitution().getInstitutionCode()�̖߂�l 
            //���X�R�[�h�F �⏕����.get����X().getBranchCode()�̖߂�l 
            //�����^�C�v�F 5�i�����j
            String l_strNewNumber2 = l_hostReqOrderNumberManageService.getNewNumber(
                l_subAccount.getInstitution().getInstitutionCode(), 
                l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                ProductTypeEnum.CASH);
            
            //1.3.2) ���o���������e(Trader, OrderTypeEnum, AssetTransferTypeEnum, long,
            //double, String, Date, String, Long) (�U�֌����� �A)
            //���o���������e�C���X�^���X�𐶐�����B
            //[����] 
            //�㗝���͎ҁF get�㗝���͎�()�̖߂�l 
            //������ʁF 1006�i�U�֒����i�M�p�ۏ؋�����a����j�j 
            //�U�փ^�C�v�F 2�i�o���j 
            //���iID�F get���iID()�̖߂�l 
            //���z�F get�����a����ւ̐U�֊z()�̖߂�l 
            //�L�q�F null 
            //�U�֗\����F get������()�̖߂�l 
            //���ϊ���ID�F null 
            //����ID�F null 
            WEB3AioNewOrderSpec l_aioNewOrderSpec3 = new WEB3AioNewOrderSpec(
                l_trader,
                OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT,
                AssetTransferTypeEnum.CASH_OUT,
                l_lngProductId,
                l_dblTransferAmountToEquitySubAcount,
                null,
                l_datOrderBizDate,
                null,
                null); 
            
            //1.3.3) �U�֒����X�V�C���^�Z�v�^(���o���������e)
            //�A�C�e���̒�`
            //�U�֒����X�V�C���^�Z�v�^�̃C���X�^���X�𐶐�����B
            //[����] 
            //���o���������e�F ���o���������e�I�u�W�F�N�g�i�U�֌������A�j 
            WEB3AioTransferOrderUpdateInterceptor l_transferOrderUpdateInterceptor3 =
                new WEB3AioTransferOrderUpdateInterceptor(l_aioNewOrderSpec3);
            
            //1.3.4) (*3)�v���p�e�B�Z�b�g
            //(*3)(*4) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
            //�C���^�Z�v�^.������ = get������()�̖߂�l
            l_transferOrderUpdateInterceptor3.setBizDate(l_datOrderBizDate);
            
            //�C���^�Z�v�^.��n�� = get������()�̖߂�l
            l_transferOrderUpdateInterceptor3.setDeliveryDate(l_datOrderBizDate);
            
            //�C���^�Z�v�^.���ʃR�[�h = get�V�K���ʃR�[�h()�̖߂�l�i���ʃR�[�h�A�j
            l_transferOrderUpdateInterceptor3.setOrderRequestNumber(l_strNewNumber2);
            
            //MQ�X�e�[�^�X = 1:���M�ς�
            l_transferOrderUpdateInterceptor3.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);
            
            //1.3.5) ���o���������e(Trader, OrderTypeEnum, AssetTransferTypeEnum, 
            //long, double, String, Date, String, Long) (�U�֐撍���A)
            //���o���������e�C���X�^���X�𐶐�����B
            //[����] 
            //�㗝���͎ҁF get�㗝���͎�()�̖߂�l 
            //������ʁF 1006�i�U�֒����i�M�p�ۏ؋�����a����j�j 
            //�U�փ^�C�v�F 1�i�����j 
            //���iID�F get���iID()�̖߂�l 
            //���z�F get�����a����ւ̐U�֊z()�̖߂�l
            //�L�q�F null 
            //�U�֗\����F get������()�̖߂�l 
            //���ϊ���ID�F null 
            //����ID�F null 
            WEB3AioNewOrderSpec l_aioNewOrderSpec4 = new WEB3AioNewOrderSpec(
                l_trader,
                OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT,
                AssetTransferTypeEnum.CASH_IN,
                l_lngProductId,
                l_dblTransferAmountToEquitySubAcount,
                null,
                l_datOrderBizDate,
                null,
                null); 
            
            //1.3.6) �U�֒����X�V�C���^�Z�v�^(���o���������e)
            //�A�C�e���̒�`
            //�U�֒����X�V�C���^�Z�v�^�̃C���X�^���X�𐶐�����B
            //[����] 
            //���o���������e�F ���o���������e�I�u�W�F�N�g�i�U�֐撍���A�j 
            WEB3AioTransferOrderUpdateInterceptor l_transferOrderUpdateInterceptor4 =
                new WEB3AioTransferOrderUpdateInterceptor(l_aioNewOrderSpec4);
            
            //1.3.7) (*4)�v���p�e�B�Z�b�g
            //(*3)(*4) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
            //�C���^�Z�v�^.������ = get������()�̖߂�l
            l_transferOrderUpdateInterceptor4.setBizDate(l_datOrderBizDate);
            
            //�C���^�Z�v�^.��n�� = get������()�̖߂�l
            l_transferOrderUpdateInterceptor4.setDeliveryDate(l_datOrderBizDate);
            
            //�C���^�Z�v�^.���ʃR�[�h = get�V�K���ʃR�[�h()�̖߂�l�i���ʃR�[�h�A�j
            l_transferOrderUpdateInterceptor4.setOrderRequestNumber(l_strNewNumber2);
            
            //MQ�X�e�[�^�X = 1:���M�ς�
            l_transferOrderUpdateInterceptor4.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);
            
            //1.3.8) createNewOrderId( ) (����ID�A)
            //�A�C�e���̒�`
            //�ۏ؋��U�֒����p�̒���ID���擾����
            long l_lngNewOrderId2 = l_orderManager.createNewOrderId();
            
            //1.3.9) submit�U�֒���(SubAccount, ProductTypeEnum, OrderTypeEnum,
            //NewOrderSpec, AioOrderManagerPersistenceInterceptor, long, String)
            //�A�C�e���̒�`
            //�M�p�U�֒����i�U�֌��j��o�^����B
            //[����] 
            //�⏕�����F get�⏕����()�̖߂�l�i�ۏ؋������j 
            //�����^�C�v�F 5�i�����j 
            //������ʁF 1006�i�U�֒����i�M�p�ۏ؋�����a����j�j 
            //�������e�F ���o���������e�i�U�֌������A�j 
            //�C���^�Z�v�^�F �U�֒����X�V�C���^�Z�v�^�i�U�֌������A�j 
            //����ID�F createNewOrderId()�̖߂�l�i����ID�A�j 
            //�p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ�
            l_orderManager.submitTransferOrder(
                l_subAccount3,
                ProductTypeEnum.CASH,
                OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT,
                l_aioNewOrderSpec3,
                l_transferOrderUpdateInterceptor3,
                l_lngNewOrderId2,
                l_request.password);
            
            //1.3.10) createNewOrderId( ) (����ID�B)
            //�A�C�e���̒�`
            //�ۏ؋��U�֔��Β����p�̒���ID���擾����B
            long l_lngNewOrderId3 = l_orderManager.createNewOrderId();

            //1.3.11) submit�U�֒���()            
            //�M�p�U�ւ̔��Β����i�U�֐�j��o�^����B
            //[����] 
            //�⏕�����F get�⏕����()�̖߂�l�i�a��������j 
            //�����^�C�v�F 5�i�����j 
            //������ʁF 1006�i�U�֒����i�M�p�ۏ؋�����a����j�j 
            //�������e�F ���o���������e�i�U�֐撍���A�j 
            //�C���^�Z�v�^�F �U�֒����X�V�C���^�Z�v�^�i�U�֐撍���A�j 
            //����ID�F createNewOrderId()�̖߂�l�i����ID�B�j 
            //�p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ� 
            l_orderManager.submitTransferOrder(
                l_subAccount2,
                ProductTypeEnum.CASH,
                OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT,
                l_aioNewOrderSpec4,
                l_transferOrderUpdateInterceptor4,
                l_lngNewOrderId3,
                l_request.password);
        }
        
        //1.4)�]�͂̍Čv�Z���s���B 
        //[����] 
        //�⏕�����F �⏕�����I�u�W�F�N�g�i�a��������j 
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        l_tpTradingPowerService.reCalcTradingPower(l_gentradeSubAccount2);
        
        //1.5)  getOrder(long)(AIO�����}�l�[�W��::getOrder)
        //�A�C�e���̒�`
        //�������擾����B
        //[����] 
        //����ID�F ���N�G�X�g�f�[�^.����ID 
        Order l_order = null;
        try
        {
            l_order = l_orderManager.getOrder(Long.parseLong(l_request.orderId));
        }
        catch (NotFoundException l_ex)
        {
            log.error("�������擾����: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //1.6)  createResponse( )(�؋����ւ̐U�֊������N�G�X�g::createResponse)
        ///�A�C�e���̒�`
        //���X�|���X�f�[�^�𐶐�����        
        WEB3AccTransChangeToIfoDepositCompleteResponse l_response = 
            (WEB3AccTransChangeToIfoDepositCompleteResponse)l_request.createResponse();
        //1.7) (*) �v���p�e�B�Z�b�g
        //(*) �ȉ��̂Ƃ���A�v���p�e�B���Z�b�g����B
        //���X�|���X.�X�V���� = ����.�X�V���t
        l_response.lastUpdatedTimestamp = 
            ((AioOrderRow)l_order.getDataSourceObject()).getLastUpdatedTimestamp();
        //���X�|���X.����ID = ���N�G�X�g�f�[�^.����ID
        l_response.orderId = l_request.orderId;
        
        return l_response;
    }
}
@
