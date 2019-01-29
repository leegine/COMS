head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.32.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConTransferServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������ւ̐U�փT�[�r�XImpl�N���X(WEB3FEqConTransferServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 ��O�� (���u) �V�K�쐬       
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioTransferOrderUpdateInterceptor;
import webbroker3.aio.WEB3FEqConTransferDataControlService;
import webbroker3.aio.WEB3FEqConTransferOrderUpdateInterceptor;
import webbroker3.aio.data.FeqAccountParams;
import webbroker3.aio.define.WEB3AioDescriptionDef;
import webbroker3.aio.message.WEB3FEqConTransferCompleteRequest;
import webbroker3.aio.message.WEB3FEqConTransferCompleteResponse;
import webbroker3.aio.message.WEB3FEqConTransferConfirmRequest;
import webbroker3.aio.message.WEB3FEqConTransferConfirmResponse;
import webbroker3.aio.message.WEB3FEqConTransferInputRequest;
import webbroker3.aio.message.WEB3FEqConTransferInputResponse;
import webbroker3.aio.service.delegate.WEB3FEqConTransferService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�O�������ւ̐U�փT�[�r�XImpl)<BR>
 * �O�������ւ̐U�փT�[�r�X�����N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3FEqConTransferServiceImpl extends WEB3ClientRequestService 
    implements WEB3FEqConTransferService 
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FEqConTransferServiceImpl.class);  
    
    /**
     * @@roseuid 423559DA0251
     */
    public WEB3FEqConTransferServiceImpl() 
    {
     
    }
    
    /**
     * �O�������ւ̐U�֏������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���R�[������B<BR>
     * <BR>
     *   get���͉��()<BR>
     *   validate�U��()<BR>
     *   submit�U��()
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E3863901FE
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        String l_strMethodName = "execute(WEB3GenRequest l_request)";
        log.entering(l_strMethodName);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        WEB3GenResponse l_response;
        
        //���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̂����ꂩ�̃��\�b�h���R�[������B 
        // get���͉��() 
        // validate�U��() 
        // submit�U��() 
        if (l_request instanceof WEB3FEqConTransferInputRequest)
        {
            l_response = 
                getInputScreen((WEB3FEqConTransferInputRequest)l_request);   
        }
        else if (l_request instanceof WEB3FEqConTransferConfirmRequest)
        {
            l_response =
                validateTransfer((WEB3FEqConTransferConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3FEqConTransferCompleteRequest)
        {
            l_response =
                submitTransfer((WEB3FEqConTransferCompleteRequest)l_request);
        }
        else
        {
            log.debug("error in get necessory request");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (get���͉��)<BR>
     * ���͉�ʕ\�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O�������ւ̐U�ցjget���͉�ʁv �Q��
     * ----------------------------------------------
     * 1.6 get�O�������ڋq(String, String, String)
     * �߂�l��null�̏ꍇ�A��O��thorw����B
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01944<BR>
     * <BR>
     * ----------------------------------------------
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@throws WEB3BaseException
     * @@return WEB3FEqConTransferInputResponse
     * @@roseuid 41E3925A02F8
     */
    protected WEB3FEqConTransferInputResponse getInputScreen(
        WEB3FEqConTransferInputRequest l_request) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "getInputScreen(WEB3FEqConTransferInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 �⏕�����I�u�W�F�N�g���擾����B 
        //[����] 
        //�⏕�����^�C�v�F 1�i������������i�a����j�j
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //1.2 validate(����) �ȉ��̃`�F�b�N���s���B 
        //�|��t���ԃ`�F�b�N 
        //�|�V�X�e����~���`�F�b�N 
        //�|�ڋq�̃`�F�b�N�i�x�q�A�Ǘ����b�N���j 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        
        l_aioOrderManager.validateOrder(l_subAccount);
        
        //1.3 validate�O���U�։\()
        //�O���̐U�֎�����\�ł��邩���`�F�b�N����B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        l_aioOrderManager.validateFeqConTransferPossible(l_subAccount);
        
        //1.4 get������( )
        //���������擾����B 
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //1.5 validate�U�։\��(SubAccount, Date, OrderCategEnum)
        //�U�։\�񐔂̃`�F�b�N���s���B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�������F get������()�̖߂�l 
        //�����J�e�S���F 16�i�O�������U�ցj 
        l_aioOrderManager.validateTransferPossibleCount(
                l_subAccount, 
                l_datBizDate, 
                OrderCategEnum.FEQ_TRANSFER);
        
        //1.6 get�O�������ڋq(String, String, String)
        //�O�������ڋq�I�u�W�F�N�g���擾����B 
        //[����] 
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
        //���X�R�[�h�F �⏕����.get����X.getBranchCode() 
        //�ڋq�R�[�h�F �⏕����.getMainAccount().getAccountCode() 
        WEB3FEqConTransferDataControlService l_feqConTransferDataControlService = 
            (WEB3FEqConTransferDataControlService) Services.getService(
                WEB3FEqConTransferDataControlService.class);     
        
        FeqAccountParams l_feqAccountParams = null;
        try
        {
            l_feqAccountParams = 
                l_feqConTransferDataControlService.getFeqAccountByAccountCode(
                    l_subAccount.getInstitution().getInstitutionCode(), 
                    l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                    l_subAccount.getMainAccount().getAccountCode());
        }
        catch (NotFoundException l_ex)
        {
            log.debug("NotFoundException: ", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.7 get�o���\�z(�⏕���� : �⏕����, ��n�� : Date) 
        //�o���\�z���擾����B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //��n���F �擾�����������̗��c�Ɠ� 
        WEB3TPTradingPowerService l_tPTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
        
        //�擾�����������̗��c�Ɠ�
        Date l_datBizTomDate = new WEB3GentradeBizDate(
            new Timestamp(l_datBizDate.getTime())).roll(1);
        
        //�o���\�z
        double l_dblCashoutPossiblePrice =  l_tPTradingPowerService.getPaymentTradingPower(
                l_gentradeSubAccount, l_datBizTomDate);
        
        //1.8) createResponse( )
        WEB3FEqConTransferInputResponse l_response = 
            (WEB3FEqConTransferInputResponse)l_request.createResponse();
        
        //1.9) (*)�v���p�e�B�Z�b�g
        //���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B

        //��������ԍ� : �O�������ڋq.�O�����������ԍ�
        l_response.tradeAccountCode = l_feqAccountParams.getFeqAccountCode();
   
        //�U�։\�z : get�o���\�z�̖߂�l
        l_response.changePossAmt = WEB3StringTypeUtility.formatNumber(l_dblCashoutPossiblePrice);        
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (validate�U��)<BR>
     * �U�ւ̊m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O�������ւ̐U�ցjvalidate�U�ցv �Q��
     * ======================================================== <BR>
     * 1.16 �߂�l�̎���]�͌���.����t���O == false �̏ꍇ�A��O���X���[�� <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01306 <BR>
     * <BR>
     * ==========================================================
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@throws WEB3BaseException
     * @@return WEB3FEqConTransferConfirmResponse
     * @@roseuid 41E3925A0317
     */
    protected WEB3FEqConTransferConfirmResponse validateTransfer(
        WEB3FEqConTransferConfirmRequest l_request) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "validateTransfer(WEB3FEqConTransferConfirmRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 get�⏕����(SubAccountTypeEnum)
        //[����] 
        //�⏕�����^�C�v�F 1�i������������i�a����j�j
        SubAccount l_subAccount =
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.3 validate����(SubAccount)
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();

        l_aioOrderManager.validateOrder(l_subAccount);
        
        //1.4 validate�O���U�։\()
        //�O���̐U�֎�����\�ł��邩���`�F�b�N����B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        l_aioOrderManager.validateFeqConTransferPossible(l_subAccount);
        
        //1.5 get������( )
        //���������擾����B 
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //1.6 validate�U�։\��(SubAccount, Date, OrderCategEnum)
        //�U�։\�񐔂̃`�F�b�N���s���B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�������F get������()�̖߂�l 
        //�����J�e�S���F 16�i�O�������U�ցj 
        l_aioOrderManager.validateTransferPossibleCount(
                l_subAccount, 
                l_datBizDate, 
                OrderCategEnum.FEQ_TRANSFER);
        
        //1.7 validate�O������U��(SubAccount, double)
        //����U�ւ̃`�F�b�N���s���B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�U�֋��z�F ���N�G�X�g�f�[�^.�U�֋��z 
        l_aioOrderManager.validateFeqConFirstTransfer(
                l_subAccount, 
                Double.parseDouble(l_request.changeAmt));
      
        //���iID�i����ID�j���擾����B
        long l_lngProductId = 
            l_aioOrderManager.getProductId(l_subAccount.getInstitution());        
        
        //1.8 ���o���������e�𐶐�����B 
        //[����] 
        //�㗝���͎ҁF null 
        //������ʁF 1013�i�O�������U�֒����i�a�������O�����������j�j 
        //�U�փ^�C�v�F 2�i�o���j 
        //���iID�F get���iID()�̖߂�l 
        //���z�F ���N�G�X�g�f�[�^.�U�֋��z 
        //�L�q�F null 
        //�U�֗\����F �擾���������� 
        //���ϋ@@��ID�F null 
        //����ID�F null 
        WEB3AioNewOrderSpec l_aioNewOrderSpec = 
            new WEB3AioNewOrderSpec(
                null, 
                OrderTypeEnum.TRANSFER_TO_FEQ, 
                AssetTransferTypeEnum.CASH_OUT, 
                l_lngProductId, 
                Double.parseDouble(l_request.changeAmt), 
                null, 
                l_datBizDate, 
                null, 
                null);
        
        //1.9) �O���U�֒����X�V�C���^�Z�v�^�𐶐�����B 
        //[����] 
        //���o���������e�F�@@���o���������e  
        WEB3FEqConTransferOrderUpdateInterceptor l_orderUpdateInterceptor =
            new WEB3FEqConTransferOrderUpdateInterceptor(l_aioNewOrderSpec);
        
        //1.10)(*1)�v���p�e�B�Z�b�g 
        l_orderUpdateInterceptor.setOrderBizDate(l_datBizDate);
        //�擾�����������̗��c�Ɠ�
        Date l_datBizTomDate = new WEB3GentradeBizDate(
            new Timestamp(l_datBizDate.getTime())).roll(1);
        l_orderUpdateInterceptor.setDeliveryDate(l_datBizTomDate);
        
        //�������e�C���^�Z�v�^�̔z��
        WEB3FEqConTransferOrderUpdateInterceptor[] l_orderUpdateInterceptors = 
            {l_orderUpdateInterceptor};
            
        //�������e�̔z��
        WEB3AioNewOrderSpec[] l_aioNewOrderSpecs = {l_aioNewOrderSpec}; 
        
        //1.11) �]�͂̃`�F�b�N���s���B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�������e�C���^�Z�v�^�F �O���U�֒����X�V�C���^�Z�v�^��v�f�Ƃ����z��
        //�������e�F ���o���������e��v�f�Ƃ����z�� 
        //������ʁF1013�i�O�������U�֒����i�a�������O�����������j�j 
        //�]�͍X�V�t���O�F false 
        WEB3TPTradingPowerService l_tpTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        WEB3TPTradingPowerResult l_powerResult =      
            l_tpTradingPowerService.validateTradingPower(
                (WEB3GentradeSubAccount)l_subAccount, 
                l_orderUpdateInterceptors, 
                l_aioNewOrderSpecs, 
                OrderTypeEnum.TRANSFER_TO_FEQ, 
                false);
        
        //�߂�l�̎���]�͌���.����t���O == false �̏ꍇ�A��O���X���[��
        if (l_powerResult.isResultFlg() == false)
        {
            log.debug("����]�̓`�F�b�N�G���[�B"); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����]�̓`�F�b�N�G���[�B");     
        }
        
        //����]�͌���.get����\�z()�̖߂�l
        double l_dblTradingPower = l_powerResult.getTradingPower();
        
        //1.12 createResponse( )
        WEB3FEqConTransferConfirmResponse l_response = 
            (WEB3FEqConTransferConfirmResponse)l_request.createResponse();
        
        //1.13 (*)�v���p�e�B�Z�b�g
        //���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B

        //�U�։\�z = ����]�͌���.get����\�z()�̖߂�l
        l_response.changePossAmt = WEB3StringTypeUtility.formatNumber(l_dblTradingPower);
        log.debug("�U�։\�z :" + l_response.changePossAmt);
        
        //�m�F�������� : get������()�̖߂�l
        l_response.checkDate = l_datBizDate;
        log.debug("�m�F�������� :" + l_response.checkDate);
        
        //��n�\����F �������̗�
        Date l_datDeliveryScheduledDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
        
        l_response.deliveryScheduledDate = l_datDeliveryScheduledDate;
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (submit�U��)<BR>
     * �U�ւ̊����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O�������ւ̐U�ցjsubmit�U�ցv �Q��
     * --------------------------------------------------------
     * 1.5 get�O���U�֔�����( )
     * ���N�G�X�g�f�[�^.�m�F�������� != �擾���������� �̏ꍇ�A
     * ��O���X���[����B
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00205<BR>
     * <BR>
     * --------------------------------------------------------
     * 1.14 �߂�l�̎���]�͌���.����t���O == false �̏ꍇ�A��O���X���[�� <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01306 <BR>
     * <BR>
     * --------------------------------------------------------
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@throws WEB3BaseException
     * @@return WEB3FEqConTransferCompleteResponse
     * @@roseuid 41E3925A0337
     */
    protected WEB3FEqConTransferCompleteResponse submitTransfer(
        WEB3FEqConTransferCompleteRequest l_request) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "submitTransfer(WEB3FEqConTransferCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 get�⏕����(SubAccountTypeEnum)
        //[����] 
        //�⏕�����^�C�v�F 1�i������������i�a����j�j
        SubAccount l_subAccount =
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.3 validate����(SubAccount)
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();

        l_aioOrderManager.validateOrder(l_subAccount);
        
        //1.4 validate�O���U�։\()
        //�O���̐U�֎�����\�ł��邩���`�F�b�N����B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        l_aioOrderManager.validateFeqConTransferPossible(l_subAccount);
        
        //1.5 get������(Date)        
        //���������擾����B 
        //[����]  
        //�m�F���������F ���N�G�X�g�f�[�^.�m�F��������
        Date l_datBizDate = 
            WEB3GentradeTradingTimeManagement.getOrderBizDate(
                    l_request.checkDate);
        
        //1.6 validate�U�։\��(SubAccount, Date, OrderCategEnum)
        //�U�։\�񐔂̃`�F�b�N���s���B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�������F get������()�̖߂�l 
        //�����J�e�S���F 16�i�O�������U�ցj 
        l_aioOrderManager.validateTransferPossibleCount(
                l_subAccount, 
                l_datBizDate, 
                OrderCategEnum.FEQ_TRANSFER);
        
        //1.7 validate�O������U��(SubAccount, double)
        //����U�ւ̃`�F�b�N���s���B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�U�֋��z�F ���N�G�X�g�f�[�^.�U�֋��z 
        l_aioOrderManager.validateFeqConFirstTransfer(
                l_subAccount, 
                Double.parseDouble(l_request.changeAmt));
                        
        
        //1.8 get�㗝���͎�( )
        //�A�C�e���̒�`
        //�㗝���͎҃I�u�W�F�N�g���擾����B
        Trader l_trader = this.getTrader();
        
        //1.9 get���iID(Institution)
        //�����ɊY���������ID�i���iID�j���擾����B 
        //[����] 
        //�،���ЁF �⏕����.get����X().getInstitution()�̖߂�l 
        long l_lngProductId = l_aioOrderManager.getProductId(l_subAccount.getInstitution());
        
        //���ʃR�[�h(1)
        //1.10  get�V�K���ʃR�[�h(String, String, ProductTypeEnum)
        //�V�K�̎��ʃR�[�h���擾����B
        //[����] 
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
        //���X�R�[�h�F�@@�⏕����.get����X().getBranchCode() 
        //�����^�C�v�F 5�i�����j
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService=
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class); 
        
        String l_strNewRequestNumber1 = l_hostReqOrderNumberManageService.getNewNumber(
            l_subAccount.getInstitution().getInstitutionCode(), 
            l_subAccount.getMainAccount().getBranch().getBranchCode(), 
            ProductTypeEnum.CASH);
        log.debug("���ʃR�[�h(1) = " + l_strNewRequestNumber1);
        
        String l_strNewRequestNumber2 = null;  //���ʃR�[�h(2)
        
        //�U�֒���(1)
        //1.11 ���o���������e�𐶐�����B 
        //[����] 
        //�㗝���͎ҁF get�㗝���͎�()�̖߂�l 
        //������ʁF 1013�i�O�������U�֒����i�a�������O�����������j�j 
        //�U�փ^�C�v�F 2�i�o���j 
        //���iID�F get���iID()�̖߂�l 
        //���z�F ���N�G�X�g�f�[�^.�U�֋��z 
        //�L�q�F �hfeq_transfer�h 
        //�U�֗\����F �擾���������� 
        //���ϋ@@��ID�F null 
        //����ID�F null 

        WEB3AioNewOrderSpec l_aioNewOrderSpec1 = new WEB3AioNewOrderSpec(
                l_trader,
                OrderTypeEnum.TRANSFER_TO_FEQ,
                AssetTransferTypeEnum.CASH_OUT,
                l_lngProductId,
                Double.parseDouble(l_request.changeAmt),
                WEB3AioDescriptionDef.FEQ_TRANSFER,
                l_datBizDate,
                null,
                null);
        
        //1.12 �O���U�֒����X�V�C���^�Z�v�^�𐶐�����B 
        //[�����̐ݒ�] 
        //���o���������e�F�@@���o���������e�i�U�֒���(1)�j
        WEB3FEqConTransferOrderUpdateInterceptor l_transferOrderUpdateInterceptor1 =
            new WEB3FEqConTransferOrderUpdateInterceptor(l_aioNewOrderSpec1);
        
        //1.13 (*)�v���p�e�B�Z�b�g
        //������ : �擾���������� 
        l_transferOrderUpdateInterceptor1.setOrderBizDate(l_datBizDate);
        
        //�擾�����������̗��c�Ɠ�
        Date l_datBizTomDate = new WEB3GentradeBizDate(
            new Timestamp(l_datBizDate.getTime())).roll(1);
        
        //��n���F�擾�����������̗��c�Ɠ� 
        l_transferOrderUpdateInterceptor1.setDeliveryDate(l_datBizTomDate);
        
        //���ʃR�[�h�Fget�V�K���ʃR�[�h�̖߂�l(���ʃR�[�h(1))
        l_transferOrderUpdateInterceptor1.setOrderRequestNumber(l_strNewRequestNumber1);
        
        WEB3FEqConTransferOrderUpdateInterceptor[] l_orderUpdateInterceptors = 
            {l_transferOrderUpdateInterceptor1};
    
        //�������e�̔z��
        WEB3AioNewOrderSpec[] l_aioNewOrderSpecs = {l_aioNewOrderSpec1}; 
    
        //1.14 �]�͂̃`�F�b�N���s���B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�������e�C���^�Z�v�^�F �O���U�֒����X�V�C���^�Z�v�^��v�f�Ƃ����z�� 
        //�������e�F ���o���������e��v�f�Ƃ����z�� 
        //������ʁF 1013�i�O�������U�֒����i�a�������O�����������j�j 
        //�]�͍X�V�t���O�F false 
        WEB3TPTradingPowerService l_tpTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        WEB3TPTradingPowerResult l_powerResult =      
            l_tpTradingPowerService.validateTradingPower(
                (WEB3GentradeSubAccount)l_subAccount, 
                l_orderUpdateInterceptors, 
                l_aioNewOrderSpecs, 
                OrderTypeEnum.TRANSFER_TO_FEQ, 
                false);
        
        //�߂�l�̎���]�͌���.����t���O == false �̏ꍇ�A��O���X���[��
        if (l_powerResult.isResultFlg() == false)
        {
            log.debug("����]�̓`�F�b�N�G���[�B"); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����]�̓`�F�b�N�G���[�B");     
        }
        
        //1.15 (*1)�M�p�����J�ݍς̏ꍇ�A���{�B
        //�i�⏕����.getMainAccount().is�M�p�����J�݁i�ٍϋ敪�i���h�w��Ȃ��h�j�̖߂�l��true�j
        WEB3GentradeMainAccount l_mainAccount = 
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        boolean l_booisMarginAccountEstablished = 
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
        
        double l_dblTransferAmount = 0;
        SubAccount l_subAccountMargin = null;
        WEB3GentradeSubAccount l_gentradeSubAccountMargin = null;
        
        if (l_booisMarginAccountEstablished)
        {
            //1.15.1 get�⏕����(SubAccountTypeEnum)
            //�⏕�����I�u�W�F�N�g���擾����B 
            //[����] 
            //�⏕�����^�C�v�F 2�i�����M�p��������i�ۏ؋��j�j
            l_subAccountMargin =
                this.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_gentradeSubAccountMargin = 
                (WEB3GentradeSubAccount)l_subAccountMargin;
            
            //1.15.2 get�a����ւ̐U�֊z(�⏕���� : �⏕����, �K�v���� : double, ��n�� : Date)
            //�����ڋq����c�����擾����B 
            //[����] 
            //�⏕�����F get�⏕����()�̖߂�l�i�ۏ؋������j 
            //�K�v�����F ���N�G�X�g�f�[�^.�U�֋��z 
            //��n���F�@@�擾�����������̗��c�Ɠ�             
            l_dblTransferAmount = 
                l_tpTradingPowerService.getTransferAmountToEquitySubAcount(
                    l_gentradeSubAccountMargin, 
                    Double.parseDouble(l_request.changeAmt), 
                    l_datBizTomDate);
        }
        
        //����ID(1)
        //1.16 createNewOrderId( ) 
        //�V�K����ID���̔Ԃ���B
        long l_lngNewOrderId1 = l_aioOrderManager.createNewOrderId();
        log.debug("����ID(1) = " + l_lngNewOrderId1);
        
        long l_lngNewOrderId2 = 0L; //����ID(2)
        long l_lngNewOrderId3 = 0L; //����ID(3)
        
        //1.17 submit�U�֒���() �U�֒�����o�^����B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�����^�C�v�F 5�i�����j 
        //������ʁF 1013�i�O�������U�֒����i�a�������O�����������j�j 
        //�������e�F ���o���������e�i�U�֒���(1)�j 
        //�C���^�Z�v�^�F �O���U�֒����X�V�C���^�Z�v�^�I�u�W�F�N�g�i�U�֒���(1)�j 
        //����ID�F createNewOrderId()�̖߂�l�i����ID(1)�j 
        //�p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ� 
        l_aioOrderManager.submitTransferOrder(
                l_subAccount, 
                ProductTypeEnum.CASH, 
                OrderTypeEnum.TRANSFER_TO_FEQ, 
                l_aioNewOrderSpec1, 
                l_transferOrderUpdateInterceptor1, 
                l_lngNewOrderId1,
                l_request.password);
        
		//�@@�Ɩ����t�̎擾�iyyyyMMdd�j
		String l_strBizDate1 = WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMMdd");
		Date l_datBizDate1 = WEB3DateUtility.getDate(l_strBizDate1, "yyyyMMdd");
		
		//�@@��������yyyyMMdd�^�ɕύX
		String l_strOrderBizdate1 = WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd");
		Date l_datOrderBizDate1 = WEB3DateUtility.getDate(l_strOrderBizdate1, "yyyyMMdd");
		
		//1.18 is�ۏ؋��U��(�⏕���� l_subAccount, Date �Ɩ����t, Date �����P��.������)
		boolean l_blnIsDepositTransfer = l_aioOrderManager.isDepositTransfer(l_subAccount, l_datBizDate1, l_datOrderBizDate1);
        
		//1.19  (*2)�M�p�ۏ؋�����a����ւ̐U�֗v�̏ꍇ
		//�i�⏕����.getMainAccount().is�M�p�����J�݁i�ٍϋ敪�i���h�w��Ȃ��h�j�̖߂�l��true 
		// and get�a����ւ̐U�֊z()�̖߂�l > 0
		// and is�ۏ؋��U��()�̖߂�l = true�j  
		log.debug("get�a����ւ̐U�֊z()�̖߂�l = " + l_dblTransferAmount);
		if (l_booisMarginAccountEstablished && l_dblTransferAmount > 0 && l_blnIsDepositTransfer)
        {
            log.debug("�M�p�ۏ؋�����a����ւ̐U�֗v�̏ꍇ");
            //1.19.1 �V�[�P���X�}
            //�i�O�������ւ̐U�ցj�M�p�U�֒����o�^ �Q��
            
            //1.1) get�V�K���ʃR�[�h(String, String, ProductTypeEnum)
            //�V�K�̎��ʃR�[�h���擾����B�i���ʃR�[�h(2)�j 
            //[����] 
            //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
            //���X�R�[�h�F�@@�⏕����.get����X().getBranchCode() 
            //�����^�C�v�F 5�i�����j
            l_strNewRequestNumber2 = l_hostReqOrderNumberManageService.getNewNumber(
                    l_subAccount.getInstitution().getInstitutionCode(), 
                    l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                    ProductTypeEnum.CASH);
            log.debug("���ʃR�[�h(2) = " + l_strNewRequestNumber2);
            
            //�U�֒���(2)
            //1.2) ���o���������e�C���X�^���X�𐶐�����B 
            //[����] 
            //�㗝���͎ҁF get�㗝���͎�()�̖߂�l 
            //������ʁF 1006�i�U�֒����i�M�p�ۏ؋�����a����j�j 
            //�U�փ^�C�v�F 2�i�o���j 
            //���iID�F get���iID()�̖߂�l 
            //���z�F get�a����ւ̐U�֊z()�̖߂�l 
            //�L�q�F �hfeq_transfer�h 
            //�U�֗\����F get������()�̖߂�l 
            //���ϊ���ID�F null 
            //����ID�F null 
            WEB3AioNewOrderSpec l_aioNewOrderSpec2 = new WEB3AioNewOrderSpec(
                    l_trader, 
                    OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT, //1006
                    AssetTransferTypeEnum.CASH_OUT,
                    l_lngProductId, 
                    l_dblTransferAmount, //get�a����ւ̐U�֊z()�̖߂�l
                    WEB3AioDescriptionDef.FEQ_TRANSFER, 
                    l_datBizDate, 
                    null, 
                    null);
            
            //1.3) �U�֒����X�V�C���^�Z�v�^�𐶐�����B 
            //[�����̐ݒ�] 
            //���o���������e�F�@@���o���������e�i�U�֒���(2)�j
            WEB3AioTransferOrderUpdateInterceptor l_transferOrderUpdateInterceptor2 =
                new WEB3AioTransferOrderUpdateInterceptor(l_aioNewOrderSpec2);

            //1.4)  (*1)�v���p�e�B�Z�b�g
            //(*1)�ȉ��̂Ƃ���Ƀv���p�e�B���Z�b�g����B
            //�������F�@@get������()�̖߂�l
            l_transferOrderUpdateInterceptor2.setBizDate(l_datBizDate);
            //��n���F�@@�������̗��c�Ɠ�
            l_transferOrderUpdateInterceptor2.setDeliveryDate(l_datBizTomDate);
            //���ʃR�[�h�F�@@get�V�K���ʃR�[�h()�̖߂�l�i���ʃR�[�h(2)�j
            l_transferOrderUpdateInterceptor2.setOrderRequestNumber(l_strNewRequestNumber2);
            //MQ�X�e�[�^�X�F�@@0(�����M)
            l_transferOrderUpdateInterceptor2.setMqStatus(WEB3MqStatusDef.NOT_SEND_MAIL);
            
            //�U�֒���(3)
            //1.5) ���o���������e�C���X�^���X�𐶐�����B
            //[����]
            //�㗝���͎ҁF get�㗝���͎�()�̖߂�l
            //������ʁF 1006�i�U�֒����i�M�p�ۏ؋�����a����j�j
            //�U�փ^�C�v�F 1�i�����j
            //���iID�F get���iID()�̖߂�l
            //���z�F get�a����ւ̐U�֊z()�̖߂�l
            //�L�q�F �hfeq_transfer�h 
            //�U�֗\����F get������()�̖߂�l
            //���ϊ���ID�F null
            //����ID�F null
            WEB3AioNewOrderSpec l_aioNewOrderSpec3 = new WEB3AioNewOrderSpec(
                    l_trader, 
                    OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT, //1006
                    AssetTransferTypeEnum.CASH_IN,  //1�i�����j
                    l_lngProductId, 
                    l_dblTransferAmount, //get�a����ւ̐U�֊z()�̖߂�l
                    WEB3AioDescriptionDef.FEQ_TRANSFER, 
                    l_datBizDate, 
                    null, 
                    null);
            
            //1.6) �U�֒����X�V�C���^�Z�v�^�𐶐�����B
            //[�����̐ݒ�]
            //���o���������e�F�@@���o���������e�i�U�֒���(3)�j
            WEB3AioTransferOrderUpdateInterceptor l_transferOrderUpdateInterceptor3 =
                new WEB3AioTransferOrderUpdateInterceptor(l_aioNewOrderSpec3);
            
            //1.7)  (*2)�v���p�e�B�Z�b�g
            //(*2)�ȉ��̂Ƃ���Ƀv���p�e�B���Z�b�g����B
            //�������F�@@get������()�̖߂�l
            l_transferOrderUpdateInterceptor3.setBizDate(l_datBizDate);
            //��n���F�@@�������̗��c�Ɠ�
            l_transferOrderUpdateInterceptor3.setDeliveryDate(l_datBizTomDate);
            //���ʃR�[�h�F�@@get�V�K���ʃR�[�h()�̖߂�l�i���ʃR�[�h(2)�j
            l_transferOrderUpdateInterceptor3.setOrderRequestNumber(l_strNewRequestNumber2);
            //MQ�X�e�[�^�X�F�@@0(�����M)
            l_transferOrderUpdateInterceptor3.setMqStatus(
                    WEB3MqStatusDef.NOT_SEND_MAIL);
            
            //����ID(2)
            //1.8) �V�K����ID���̔Ԃ���B
            l_lngNewOrderId2 = l_aioOrderManager.createNewOrderId();
            log.debug("����ID(2) = " + l_lngNewOrderId2);
            
            //1.9) �M�p�U�֒����i�U�֌��j��o�^����B
            //[����]
            //�⏕�����F get�⏕����()�̖߂�l�i�ۏ؋������j
            //�����^�C�v�F 5�i�����j
            //������ʁF 1006�i�U�֒����i�M�p�ۏ؋�����a����j�j
            //�������e�F ���o���������e�i�U�֒���(2)�j
            //�C���^�Z�v�^�F �U�֒����X�V�C���^�Z�v�^�i�U�֒���(2)�j
            //����ID�F createNewOrderId()�̖߂�l�i����ID(2)�j
            //�p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ�
            l_aioOrderManager.submitTransferOrder(
                    l_subAccountMargin, //get�⏕����()�̖߂�l�i�ۏ؋������j
                    ProductTypeEnum.CASH, 
                    OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT, 
                    l_aioNewOrderSpec2, 
                    l_transferOrderUpdateInterceptor2, 
                    l_lngNewOrderId2,
                    l_request.password);
            
            //����ID(3)
            //1.10) �V�K����ID���̔Ԃ���B
            l_lngNewOrderId3 = l_aioOrderManager.createNewOrderId();
            log.debug("����ID(3) = " + l_lngNewOrderId3);
            
            //1.11) �M�p�U�ւ̔��Β����i�U�֐�j��o�^����B
            //[����]
            //�⏕�����F get�⏕����()�̖߂�l�i�a��������j
            //�����^�C�v�F 5�i�����j
            //������ʁF 1006�i�U�֒����i�M�p�ۏ؋�����a����j�j
            //�������e�F ���o���������e�i�U�֒���(3)�j
            //�C���^�Z�v�^�F �U�֒����X�V�C���^�Z�v�^�i�U�֒���(3)�j
            //����ID�F createNewOrderId()�̖߂�l�i����ID(3)�j
            //�p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ�
            l_aioOrderManager.submitTransferOrder(
                    l_subAccount, //get�⏕����()�̖߂�l�i�a��������j
                    ProductTypeEnum.CASH, 
                    OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT, 
                    l_aioNewOrderSpec3, 
                    l_transferOrderUpdateInterceptor3, 
                    l_lngNewOrderId3,
                    l_request.password);            
        }
        
        //1.20 �]�͍Čv�Z(�⏕���� : �⏕����)
        //�]�͍X�V���s���B 
        //[�����̐ݒ�] 
        //�⏕�����F�@@get�⏕����()(*)�̖߂�l 
        //(*)�a������̕⏕�����^�C�v���w�肵�Ď擾�����⏕����
        l_tpTradingPowerService.reCalcTradingPower((WEB3GentradeSubAccount)l_subAccount);
        
        //1.21  insertUWG�U�֏�(String, String, String, String, String, String)
        //UWG�U�֏󋵃e�[�u���ɍs��insert����B 
        //[����] 
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
        //���X�R�[�h�F�@@�⏕����.get����X().getBranchCode() 
        //�ڋq�R�[�h�F�@@�⏕����.getMainAccount().getAccountCode() 
        //��n�\����F �擾����������.toString() 
        //���ʃR�[�h�F get�V�K���ʃR�[�h()�̖߂�l�i���ʃR�[�h(1)�j 
        //�M�p�U�֗p���ʃR�[�h�F (*) 
        //�U�֋��z�F ���N�G�X�g�f�[�^.�U�֋��z 
        //(*)�M�p�����J�ݍ� && �ۏ؋�����a������ւ̐U�ւ��K�v�ȏꍇ�A�̔Ԃ������ʃR�[�h(2)���Z�b�g�B
        //   �ȊO�Anull���Z�b�g�B 
        WEB3FEqConTransferDataControlService l_feqConTransferDataControlService = 
            (WEB3FEqConTransferDataControlService) Services.getService(
                WEB3FEqConTransferDataControlService.class);        
        
        String l_strMrgTrnRequestNumber = null;
        if (l_booisMarginAccountEstablished && l_dblTransferAmount > 0)
        {
            l_strMrgTrnRequestNumber = l_strNewRequestNumber2;
        }
            
        l_feqConTransferDataControlService.insertUwgTransferStatus(
                l_subAccount.getInstitution().getInstitutionCode(), 
                l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                l_subAccount.getMainAccount().getAccountCode(), 
                WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd"), 
                l_strNewRequestNumber1, 
                l_strMrgTrnRequestNumber, 
                l_request.changeAmt);
        
        //1.22 getOrderUnits()
        //�����P�ʂ��擾����B 
        //���z��̐擪�̗v�f���擾 
        //[����] 
        //����ID�F ����ID(1)
        OrderUnit[] l_orderUnits = l_aioOrderManager.getOrderUnits(l_lngNewOrderId1);
                
        AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_orderUnits[0];
        AioOrderUnitRow l_orderUnitRow = 
            (AioOrderUnitRow)l_aioOrderUnit.getDataSourceObject();        
        
        //1.23 createResponse( )
        WEB3FEqConTransferCompleteResponse l_response = 
            (WEB3FEqConTransferCompleteResponse)l_request.createResponse();
        
        //1.24 (*)�v���p�e�B�Z�b�g
        //���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        //����ID�F ����ID(1)
        l_response.orderId = l_lngNewOrderId1 + "";
        log.debug("����ID : " + l_response.orderId);
        
        //��t���� : �����P��.�쐬���t
        l_response.receptionDate = l_orderUnitRow.getCreatedTimestamp();        
        log.debug("��t���� : " + l_response.receptionDate);
        
        //��n�\����F �������̗�
        Date l_datDeliveryScheduledDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
        
        l_response.deliveryScheduledDate = l_datDeliveryScheduledDate;        
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
}
@
