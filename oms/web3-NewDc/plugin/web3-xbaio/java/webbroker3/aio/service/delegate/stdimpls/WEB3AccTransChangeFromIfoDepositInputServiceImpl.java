head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.29.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeFromIfoDepositInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �؋�������U�֓��̓T�[�r�XImpl(WEB3AccTransChangeFromIfoDepositInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ������ (���u) �V�K�쐬
                   2004/10/26 ���E(���u) ���r���[
                   2004/12/09 ���E (���u) �c�Ή�
Revesion History : 2007/08/23 ���g (���u) �d�l�ύX�E���f��752
Revesion History : 2009/03/16 �Ԑi (���u) �d�l�ύX�E���f��1141
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.message.WEB3AccTransChangeFromIfoDepositInputResponse;
import webbroker3.aio.service.delegate.WEB3AccTransChangeFromIfoDepositInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifodeposit.WEB3IfoDepositCalc;
import webbroker3.ifodeposit.WEB3IfoDepositCalcService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�؋�������U�֓��̓T�[�r�XImpl)<BR>
 * �؋�������U�֓��̓T�[�r�X�����N���X<BR>
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3AccTransChangeFromIfoDepositInputServiceImpl extends WEB3ClientRequestService implements WEB3AccTransChangeFromIfoDepositInputService 
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
    WEB3AccTransChangeFromIfoDepositInputServiceImpl.class);
    
    /**
     * �؋�������U�֓��̓T�[�r�X�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�؋�������U�֓��́j���͉�ʕ\���f�[�^�擾�v �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 413435FC014D
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
        
        // 1.1) �⏕�����I�u�W�F�N�g���擾����B 
        // [����] 
        // �⏕�����^�C�v�F 7�i�؋��������j 
        SubAccount l_subAccount = this.getSubAccount(
                SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
                
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = (WEB3AioOrderManager) l_tradingModule.getOrderManager();
        
        // 1.2)�@@validate����(SubAccount)
        // �|��t���ԃ`�F�b�N 
        // �|�V�X�e����~���`�F�b�N 
        // �|�ڋq�̃`�F�b�N�i�x�q�A�Ǘ����b�N���j
        l_orderManager.validateOrder(l_subAccount);
        
        // 1.3) �敨����������J�݂��Ă��邩���`�F�b�N����
        l_orderManager.validateOpenFuturesTradedAccount(l_subAccount);
        
        // 1.4) get������()
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        // 1.7) �����̐U�։񐔂��擾����B 
        // [����] 
        // �⏕�����F get�⏕����()�̖߂�l 
        // �������F get������()�̖߂�l 
        // �����J�e�S���F13�i�U�ցj
        int l_intTransferCount = l_orderManager.getTransferCount(
                l_subAccount,l_datBizDate, OrderCategEnum.CASH_TRANSFER);       
        
        //=========== remain zhou-yong NO.1 bengin =================
        
        //1.8) get�؋����v�Z(�⏕���� : �⏕����)
        //�A�C�e���̒�`
        //�؋����v�Z�C���X�^���X�𐶐�����B
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        WEB3IfoDepositCalcService l_service =
            (WEB3IfoDepositCalcService) Services.getService(
                WEB3IfoDepositCalcService.class);

        WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
        
        WEB3IfoDepositCalc l_ifoDepositCalc = l_service.getIfoDepositCalc(l_gentradeSubAccount);

        //�؋����U�ւ̎w������擾����B
        int l_intBizDate = l_orderManager.getMarginTransferDesignatedDate(l_datBizDate);

        //1.9)  calc�؋����c��(�w��� : int)
        //�؋����c�����Z�o����
        //[����] 
        //�w����F get�؋����U�֎w���()�̖߂�l
        double l_dblCalcIfoDepositBalance = 
            l_ifoDepositCalc.calcIfoDepositBalance(l_intBizDate);
        
        //1.10) calc�؋����U�֗]�͊z( )
        //�A�C�e���̒�`
        //�U�։\�z���Z�o����B
        double l_dblCalcIfoDepositTransferableAmount = 
            l_ifoDepositCalc.calcIfoDepositTransferableAmount();
        
        //1.11) get�⏕����(SubAccountTypeEnum) �i�⏕�����A�j 
        //�A�C�e���̒�`
        //�⏕�����I�u�W�F�N�g���擾����B
        //[����] 
        //�⏕�����^�C�v�F 1�i�a��������j 
        SubAccount l_subAccount2 = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        // 1.12) get�o���\�z(�⏕���� : �⏕����, ��n�� : Date) 
        //�o���\�z���擾����B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l�i�⏕�����A�j 
        //��n���F get������()�̖߂�l 
        WEB3TPTradingPowerService l_tPTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        WEB3GentradeSubAccount l_gentradeSubAccount2 = (WEB3GentradeSubAccount)l_subAccount2;
        //�o���\�z
        double l_dblCashoutPossiblePrice =  l_tPTradingPowerService.getPaymentTradingPower(
            l_gentradeSubAccount2, l_datBizDate);

        //=========== remain zhou-yong NO.1 bengin =================
        
        // 1.13) ���X�|���X�f�[�^�𐶐�����
        WEB3AccTransChangeFromIfoDepositInputResponse l_response = 
            (WEB3AccTransChangeFromIfoDepositInputResponse) l_request.createResponse();
            
        // 1.14) ���X�|���X�̃v���p�e�B�Z�b�g
        MainAccount l_mainAccount = l_subAccount.getMainAccount();
        l_response.changeCountUpper = 
            ((MainAccountRow)l_mainAccount.getDataSourceObject()).getTransferCount() + "";
        l_response.changeCount = l_intTransferCount + "";
        l_response.changePossAmt = 
            WEB3StringTypeUtility.formatNumber(l_dblCalcIfoDepositTransferableAmount);
        l_response.ifoDepositBal = 
            WEB3StringTypeUtility.formatNumber(l_dblCalcIfoDepositBalance);
        
        //���X�|���X.���a������c�� = ����]�̓T�[�r�X.get�o���\�z()�̖߂�l
        l_response.depositBal = WEB3StringTypeUtility.formatNumber(
            l_dblCashoutPossiblePrice);

        log.exiting(STR_METHOD_NAME);     
        return l_response;
    }
}
@
