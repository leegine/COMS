head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.30.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeFromIfoDepositServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �؋�������U�փT�[�r�XImpl(WEB3AccTransChangeFromIfoDepositServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ������ (���u) �V�K�쐬
                   2004/10/22 ���z (���u) ���r���[
                   2004/12/09 ���E (���u) �c�Ή�
Revesion History : 2007/07/12 ��іQ(���u) ���f��No.732
Revision History : 2007/07/28 �Ј���(���u) �d�l�ύX���f��743
Revesion History : 2007/08/23 ���g (���u) �d�l�ύX�E���f��752
Revesion History : 2009/03/16 �Ԑi (���u) �d�l�ύX�E���f��1140
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
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderRow;
import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioTransferOrderUpdateInterceptor;
import webbroker3.aio.message.WEB3AccTransChangeFromIfoDepositCompleteRequest;
import webbroker3.aio.message.WEB3AccTransChangeFromIfoDepositCompleteResponse;
import webbroker3.aio.message.WEB3AccTransChangeFromIfoDepositConfirmRequest;
import webbroker3.aio.message.WEB3AccTransChangeFromIfoDepositConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AccTransChangeFromIfoDepositService;
import webbroker3.aio.service.delegate.WEB3MarginTransferService;
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
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�؋�������U�փT�[�r�XImpl)<BR>
 * �؋�������U�փT�[�r�X�����N���X<BR>
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3AccTransChangeFromIfoDepositServiceImpl
    extends WEB3ClientRequestService
    implements WEB3AccTransChangeFromIfoDepositService
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AccTransChangeFromIfoDepositServiceImpl.class);

    /**
     * �؋�������U�փT�[�r�X�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��validate����()�A<BR>
     *  �܂���submit����()���\�b�h���R�[������B <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41352FED007E
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
        if (l_request instanceof WEB3AccTransChangeFromIfoDepositConfirmRequest)
        {
            // validate����()
            l_response =
                this.validateOrder(
                    (WEB3AccTransChangeFromIfoDepositConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3AccTransChangeFromIfoDepositCompleteRequest)
        {
            // submit����()
            l_response =
                this.submitOrder(
                    (WEB3AccTransChangeFromIfoDepositCompleteRequest) l_request);
        }
        else
        {
            log.debug(
                    "���N�G�X�g�f�[�^��"
                    + " WEB3AccTransChangeFromIfoDepositConfirmRequest "
                    + " �� WEB3AccTransChangeFromIfoDepositCompleteRequest�ȊO�ł���, but is " + l_request.getClass().getName());
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
     * �u�i�؋�������U�ցjvalidate�����v�Q�ƁB<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}( �u(�؋����U�փT�[�r�X���f��) / �؋�������U�ցv<BR>
     * �i�؋�������U�ցjvalidate����  )<BR>
     * �@@�@@�@@:  1.11.calc�؋����U�։\�z( )<BR>   
     *     ���N�G�X�g�f�[�^.�U�֋��z > calc�؋����U�։\�z()�̖߂�l�̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00761<BR>
     * <BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3AccTransChangeFromIfoDepositConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4135302102B1
     */
    protected WEB3AccTransChangeFromIfoDepositConfirmResponse validateOrder(
        WEB3AccTransChangeFromIfoDepositConfirmRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(" +
                "WEB3AccTransChangeFromIfoDepositConfirmRequest l_request)";
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
        SubAccount l_subAccount = this.getSubAccount(
                SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
                
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_TradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = (WEB3AioOrderManager) l_TradingModule.getOrderManager();
        
        // 1.3)�@@validate����(SubAccount)
        // �|��t���ԃ`�F�b�N 
        // �|�V�X�e����~���`�F�b�N 
        // �|�ڋq�̃`�F�b�N�i�x�q�A�Ǘ����b�N���j
        l_orderManager.validateOrder(l_subAccount);
        
        // 1.4) �敨����������J�݂��Ă��邩���`�F�b�N����
        l_orderManager.validateOpenFuturesTradedAccount(l_subAccount);
        
        // 1.5) get������()
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        // 1.6) �U�։񐔂��A�U�։\�񐔂𒴂��ĂȂ����`�F�b�N����
        l_orderManager.validateTransferPossibleCount(l_subAccount,l_datBizDate, OrderCategEnum.CASH_TRANSFER);

        //=============remain zhou-yong NO.2 begin ===========
        
        //1.9) get�؋����v�Z(�⏕���� : �⏕����)
        //�A�C�e���̒�`
        //�؋����v�Z�I�u�W�F�N�g���擾����B
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l
        WEB3IfoDepositCalcService l_service =
            (WEB3IfoDepositCalcService) Services.getService(
                WEB3IfoDepositCalcService.class);

        WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
        
        WEB3IfoDepositCalc l_ifoDepositCalc = l_service.getIfoDepositCalc(l_gentradeSubAccount);

        //�؋����U�ւ̎w������擾����B
        int l_intBizDate =
            l_orderManager.getMarginTransferDesignatedDate(l_datBizDate);
        
        //1.10)  calc�؋����c��(�w��� : int)
        //�؋����c�����擾����B
        //[����] 
        //�w����F get�؋����U�֎w���()�̖߂�l
        double l_dblCalcIfoDepositBalance = 
            l_ifoDepositCalc.calcIfoDepositBalance(l_intBizDate);
        
        //1.11) calc�؋����U�֗]�͊z( )
        //�A�C�e���̒�`
        //�؋����̐U�։\�z���擾����B 
        double l_dblCalcIfoDepositTransferableAmount = l_ifoDepositCalc.calcIfoDepositTransferableAmount();
        
        //���N�G�X�g�f�[�^.�U�֋��z > calc�؋����U�֗]�͊z()�̖߂�l
        //�̏ꍇ�A��O���X���[����B
        if(Double.parseDouble(l_request.changeAmt) > l_dblCalcIfoDepositTransferableAmount)
        {
            log.debug("�U�֋��z���\�z�𒴂��Ă��܂��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00761,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�U�֋��z[" + l_request.changeAmt + "] > calc�؋����U�֗]�͊z()�̖߂�l[" 
                + l_dblCalcIfoDepositTransferableAmount + "]");
        }
        // =============remain zhou-yong NO.2 end ===========
        // 1.12) �⏕�����I�u�W�F�N�g���擾����B �i�⏕�����A�j
        SubAccount l_subAccount2 = this.getSubAccount(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //=========remain zhou-yong NO.2 begin ==========
        
        // 1.13) get�o���\�z(�⏕���� : �⏕����, ��n�� : Date) 
        //�o���\�z���擾����B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l�i�⏕�����A�j 
        //��n���F get������()�̖߂�l 
        WEB3TPTradingPowerService l_tPTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        WEB3GentradeSubAccount l_gentradeSubAccount2 = (WEB3GentradeSubAccount)l_subAccount2;
        
        double l_dblCashoutPossiblePrice = l_tPTradingPowerService.getPaymentTradingPower(
            l_gentradeSubAccount2, l_datBizDate);
        
        //=========remain zhou-yong NO.2 begin ==========
        
        // 1.14) �V�K�̒���ID���擾����B 
        long l_lngNewOrderID = l_orderManager.createNewOrderId();
        
        // 1.15) ���X�|���X�f�[�^�𐶐�����
        WEB3AccTransChangeFromIfoDepositConfirmResponse l_response = 
            (WEB3AccTransChangeFromIfoDepositConfirmResponse) l_request.createResponse();
        
        // 1.16) �v���p�e�B�Z�b�g
        //���X�|���X.�U�֌�U�։\�z = �؋����v�Z.ca�����؋����U�֗]�͊z()�̖߂�l - ���N�G�X�g�f�[�^.�U�֋��z
        l_response.aftChangePossAmt = WEB3StringTypeUtility.formatNumber(
            l_dblCalcIfoDepositTransferableAmount 
                - Double.parseDouble(l_request.changeAmt));
        
        //���X�|���X.�U�֌�؋��� = �؋����v�Z.calc�؋����c��()�̖߂�l - ���N�G�X�g�f�[�^.�U�֋��z
        l_response.aftIfoDeposit =  
            WEB3StringTypeUtility.formatNumber(l_dblCalcIfoDepositBalance
                - Double.parseDouble(l_request.changeAmt));
        
        //���X�|���X.�U�֌エ�a������c�� = ����]�̓T�[�r�X.get�o���\�z()�̖߂�l + ���N�G�X�g�f�[�^.�U�֋��z
        l_response.aftDepositBal =  WEB3StringTypeUtility.formatNumber(
            l_dblCashoutPossiblePrice
                + Double.parseDouble(l_request.changeAmt));

        //���X�|���X.�m�F�������� = ������ԊǗ�.get������()�̖߂�l
        l_response.checkDate = WEB3DateUtility.toDay(l_datBizDate);
        
        //���X�|���X.����ID = AIO�����}�l�[�W��.createNewOrderId()�̖߂�l
        l_response.orderId = l_lngNewOrderID + "";
        
        // ���^�[��           
        log.exiting(STR_METHOD_NAME);     
        return l_response;
    }

    /**
     * (submit����)<BR>
     * �U�֒����̓o�^���s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�؋�������U�ցjsubmit�����v�Q�ƁB<BR>
     * <BR>
     *  ========================================================<BR>
     * 
     * �V�[�P���X�}(�u(�؋����U�փT�[�r�X���f��) / <BR>
     *    �؋�������U�ցv�i�؋�������U�ցjsubmit���� )<BR>
     * �@@�@@�@@�@@�@@�@@ : 1.8.calc�؋����U�։\�z( )<BR>   
     *     ���N�G�X�g�f�[�^.�U�֋��z > calc�؋����U�։\�z()�̖߂�l�̏ꍇ�A<BR>
     *     ��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00761<BR>
     * <BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * 
     * @@return WEB3AccTransChangeFromIfoDepositCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4135308D012A
     */
    protected WEB3AccTransChangeFromIfoDepositCompleteResponse submitOrder(
        WEB3AccTransChangeFromIfoDepositCompleteRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder(" +
            "WEB3AccTransChangeFromIfoDepositCompleteRequest l_request)";
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
        //[����] 
        //�⏕�����^�C�v�F 7�i�؋��������j 
        SubAccount l_subAccount = this.getSubAccount(
                SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
                
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_TradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = (WEB3AioOrderManager) l_TradingModule.getOrderManager();
        
        // 1.3)�@@validate����(SubAccount)
        // �|��t���ԃ`�F�b�N 
        // �|�V�X�e����~���`�F�b�N 
        // �|�ڋq�̃`�F�b�N�i�x�q�A�Ǘ����b�N���j
        l_orderManager.validateOrder(l_subAccount);
        
        // 1.4) �敨����������J�݂��Ă��邩���`�F�b�N����
        l_orderManager.validateOpenFuturesTradedAccount(l_subAccount);
        
        // 1.5) get������()
        //      [����] 
        //      �m�F���������F ���N�G�X�g�f�[�^.�m�F��������
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(
            l_request.checkDate);
        
        // 1.6) �U�։񐔂��A�U�։\�񐔂𒴂��ĂȂ����`�F�b�N����
        l_orderManager.validateTransferPossibleCount(l_subAccount,l_datBizDate, OrderCategEnum.CASH_TRANSFER);

        //============remain zhou-yong NO.1 begin ===============
        
        //1.7) get�؋����v�Z(�⏕���� : �⏕����)
        //�A�C�e���̒�`
        //�؋����v�Z�C���X�^���X�𐶐�����B
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l
        WEB3IfoDepositCalcService l_service =
            (WEB3IfoDepositCalcService) Services.getService(
                WEB3IfoDepositCalcService.class);
        
        WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
        
        WEB3IfoDepositCalc l_ifoDepositCalc = l_service.getIfoDepositCalc(l_gentradeSubAccount);
        
        // 1.8) calc�؋����U�֗]�͊z( )
        //�A�C�e���̒�`
        //�U�։\�z���Z�o����B
        double l_dblCalcIfoDepositTransferableAmount = 
            l_ifoDepositCalc.calcIfoDepositTransferableAmount();
        
        //���N�G�X�g�f�[�^.�U�֋��z > calc�؋����U�։\�z()�̖߂�l�@@�̏ꍇ�ł�
        if(Double.parseDouble(l_request.changeAmt) > l_dblCalcIfoDepositTransferableAmount)
        {
            log.debug("�U�֋��z���\�z�𒴂��Ă��܂��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00761,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�U�֋��z[" + l_request.changeAmt + "] > calc�؋����U�։\�z()�̖߂�l[" 
                + l_dblCalcIfoDepositTransferableAmount + "]");            
        }

        // 1.9) �㗝���͎҃I�u�W�F�N�g���擾����
        Trader l_trader = this.getTrader();
        
        // 1.10) �V�K�̎��ʃR�[�h���擾����B
        // [����] 
        // �،���ЃR�[�h�F �⏕����.get����X().getInstitution().getInstitutionCode()�̖߂�l 
        // ���X�R�[�h�F �⏕����.get����X().getBranchCode()�̖߂�l 
        // �����^�C�v�F 5�i�����j
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService=
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);
        
        String l_strNewNumber = l_hostReqOrderNumberManageService.getNewNumber(
            l_subAccount.getInstitution().getInstitutionCode(), 
            l_subAccount.getMainAccount().getBranch().getBranchCode(), 
            ProductTypeEnum.CASH);

        //1.11) get���iID(Institution)(AIO�����}�l�[�W��::get���iID)
        //�A�C�e���̒�`
        //���iID�i����ID�j���擾����B
        //[����] 
        //�،���ЁF �⏕����.get����X().getInstitution()�̖߂�l 
        long l_lngProductId = l_orderManager.getProductId(l_subAccount.getInstitution());
        
        // 1.12) �⏕�����I�u�W�F�N�g���擾����B
        //[����] 
        //�⏕�����^�C�v�F 1�i�a��������j
        SubAccount l_subAccount2 = this.getSubAccount(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //get����( )
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)this.getMainAccount();

        // is�M�p�����J��(�ٍϋ敪 : String)
        //�ٍϋ敪�F�@@"0"�i�w�薳���j
        boolean l_blnIsMarginAccountEstablished =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //�ڋq���M�p�������J�݂��Ă���iis�M�p�����J��()==TRUE�j�ꍇ�A�������s��
        if (l_blnIsMarginAccountEstablished)
        {
            // submit�ۏ؋��U��(�ڋq, Date, double, String)
            //�ڋq�F�@@get����()�̖߂�l
            //��n���F�@@get������()�̖߂�l
            //�����z�F�@@���N�G�X�g�f�[�^.�U�֋��z
            //�Ïؔԍ��F�@@���N�G�X�X�g�f�[�^.�Ïؔԍ�
            //�㗝���͎ҁF�@@get�㗝���͎�()�̖߂�l
            WEB3MarginTransferService l_marginTransferService =
                (WEB3MarginTransferService)Services.getService(WEB3MarginTransferService.class);

            l_marginTransferService.submitMarginTransfer(
                l_mainAccount,
                l_datBizDate,
                Double.parseDouble(l_request.changeAmt),
                l_request.password,
                l_trader);
        }

        // 1.13) ���o���������e(Trader, OrderTypeEnum, AssetTransferTypeEnum, 
        //long, double, String, Date, String, Long) (�U�֌�����)
        //�A�C�e���̒�`
        //���o���������e�C���X�^���X�𐶐�����B
        //[����] 
        //�㗝���͎ҁF get�㗝���͎�()�̖߂�l 
        //������ʁF 1008�i�U�֒����i����؋�������a����j 
        //�U�փ^�C�v�F 2�i�o���j 
        //���iID�F get���iID()�̖߂�l 
        //���z�F ���N�G�X�g�f�[�^.�U�֋��z 
        //�L�q�F null 
        //�U�֗\����F ����.������ 
        //���ϋ@@��ID�F null 
        //����ID�F null 
        WEB3AioNewOrderSpec l_aioNewOrderSpec1 = new WEB3AioNewOrderSpec(
            l_trader,
            OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT,
            AssetTransferTypeEnum.CASH_OUT,
            l_lngProductId,
            Double.parseDouble(l_request.changeAmt),
            null,
            l_request.checkDate,
            null,
            null); 
        
        //1.14)  �U�֒����X�V�C���^�Z�v�^
        //�A�C�e���̒�`
        //�U�֒����X�V�C���^�Z�v�^�̃C���X�^���X�𐶐�����B
        //[����] 
        //���o���������e�F ���o���������e�I�u�W�F�N�g�i�U�֌������j 
        WEB3AioTransferOrderUpdateInterceptor l_transferOrderUpdateInterceptor1 =
            new WEB3AioTransferOrderUpdateInterceptor(l_aioNewOrderSpec1);
        
        //1.15) (*1)�v���p�e�B�Z�b�g
        //(*1)(*2) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        //�C���^�Z�v�^.������ = get������()�̖߂�l
        //�C���^�Z�v�^.��n�� = get������()�̖߂�l
        //�C���^�Z�v�^.���ʃR�[�h = get�V�K���ʃR�[�h()�̖߂�l
        l_transferOrderUpdateInterceptor1.setBizDate(l_datBizDate);
        l_transferOrderUpdateInterceptor1.setDeliveryDate(l_datBizDate);
        l_transferOrderUpdateInterceptor1.setOrderRequestNumber(l_strNewNumber);
        l_transferOrderUpdateInterceptor1.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);
        
        //1.16) ���o���������e(Trader, OrderTypeEnum, AssetTransferTypeEnum, long, double,
        //String, Date, String, Long) �U�֐撍���j 
        //�A�C�e���̒�`
        //���o���������e�C���X�^���X�𐶐�����B
        //[����] 
        //�㗝���͎ҁF get�㗝���͎�()�̖߂�l 
        //������ʁF 1008�i�U�֒����i����؋�������a����j 
        //�U�փ^�C�v�F 1�i�����j 
        //���iID�F get���iID()�̖߂�l 
        //���z�F ���N�G�X�g�f�[�^.�U�֋��z 
        //�L�q�F null 
        //�U�֗\����F ����.������ 
        //���ϋ@@��ID�F null 
        //����ID�F null 
        WEB3AioNewOrderSpec l_aioNewOrderSpec2 = new WEB3AioNewOrderSpec(
            l_trader,
            OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT,
            AssetTransferTypeEnum.CASH_IN,
            l_lngProductId,
            Double.parseDouble(l_request.changeAmt),
            null,
            l_request.checkDate,
            null,
            null); 
        
        //1.17)  �U�֒����X�V�C���^�Z�v�^(���o���������e)
        //�A�C�e���̒�`
        //�U�֒����X�V�C���^�Z�v�^�̃C���X�^���X�𐶐�����B
        //[����] 
        //���o���������e�F ���o���������e�I�u�W�F�N�g�i�U�֐撍���j 
        WEB3AioTransferOrderUpdateInterceptor l_transferOrderUpdateInterceptor2 =
            new WEB3AioTransferOrderUpdateInterceptor(l_aioNewOrderSpec2);
        
        //1.18) (*8)�v���p�e�B�Z�b�g
        //(*1)(*2) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        //�C���^�Z�v�^.������ = get������()�̖߂�l
        //�C���^�Z�v�^.��n�� = get������()�̖߂�l
        //�C���^�Z�v�^.���ʃR�[�h = get�V�K���ʃR�[�h()�̖߂�l
        l_transferOrderUpdateInterceptor2.setBizDate(l_datBizDate);
        l_transferOrderUpdateInterceptor2.setDeliveryDate(l_datBizDate);
        l_transferOrderUpdateInterceptor2.setOrderRequestNumber(l_strNewNumber);
        l_transferOrderUpdateInterceptor2.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);
                
        //1.19) submit�U�֒���(SubAccount, ProductTypeEnum, OrderTypeEnum, NewOrderSpec,
        //AioOrderManagerPersistenceInterceptor, long, String)
        //�A�C�e���̒�`
        //�U�֌��̐U�֒�����o�^����B
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l�i�؋��������j 
        //�����^�C�v�F 5�i�����j 
        //������ʁF 1008�i�U�֒����i����؋�������a����j 
        //�������e�F �U�֌��̓��o���������e�I�u�W�F�N�g 
        //�C���^�Z�v�^�F �U�֌��̐U�֒����X�V�C���^�Z�v�^�I�u�W�F�N�g 
        //����ID�F ���N�G�X�g�f�[�^.����ID 
        //�p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ� 
        l_orderManager.submitTransferOrder(
            l_subAccount,
            ProductTypeEnum.CASH,
            OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT,
            l_aioNewOrderSpec1,
            l_transferOrderUpdateInterceptor1,
            Long.parseLong(l_request.orderId),
            l_request.password);
        
        //1.20) createNewOrderId( )
        //�A�C�e���̒�`
        //���Β����p�̒���ID���擾����B
        long l_lngCreateNewOrderId = l_orderManager.createNewOrderId();
        
        //1.21) submit�U�֒���(SubAccount, ProductTypeEnum, OrderTypeEnum,
        //NewOrderSpec, AioOrderManagerPersistenceInterceptor, long, String)
        //�A�C�e���̒�`
        //�U�֐�̐U�֒�����o�^����B
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l�i�a��������j 
        //�����^�C�v�F 5�i�����j 
        //������ʁF 1008�i�U�֒����i����؋�������a����j 
        //�������e�F �U�֐�̓��o���������e�I�u�W�F�N�g 
        //�C���^�Z�v�^�F �U�֐�̐U�֒����X�V�C���^�Z�v�^�I�u�W�F�N�g 
        //����ID�F createNewOrderId()�̖߂�l 
        //�p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ� 
        l_orderManager.submitTransferOrder(
            l_subAccount2,
            ProductTypeEnum.CASH,
            OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT,
            l_aioNewOrderSpec2,
            l_transferOrderUpdateInterceptor2,
            l_lngCreateNewOrderId,
            l_request.password);
        
        //1.22) �]�͍Čv�Z(�⏕���� : �⏕����)
        //�]�͂̍X�V���s���B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l�i�a��������j 
        WEB3TPTradingPowerService l_tPTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);

        l_tPTradingPowerService.reCalcTradingPower(
                (WEB3GentradeSubAccount)l_subAccount2);

        //1.23) getOrder(long)
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
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        // 1.24) ���X�|���X�f�[�^�𐶐�����
        WEB3AccTransChangeFromIfoDepositCompleteResponse l_response = 
            (WEB3AccTransChangeFromIfoDepositCompleteResponse) l_request.createResponse();
            
        // 18) ���X�|���X�̃v���p�e�B�Z�b�g
        l_response.lastUpdatedTimestamp = 
            ((AioOrderRow)l_order.getDataSourceObject()).getLastUpdatedTimestamp();
        l_response.orderId = l_request.orderId;

        //===========remain zhou-yong NO.1 end ==============
        
        // ���^�[��           
        log.exiting(STR_METHOD_NAME);     
        return l_response;
    }
}
@
