head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.32.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeToIfoDepositInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �؋����ւ̐U�֓��̓T�[�r�XImpl(WEB3AccTransChangeToIfoDepositInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ������ (���u) �V�K�쐬
                   2004/10/26 ���E(���u) ���r���[
                   2004/12/09 ���E (���u) �c�Ή�
Revesion History : 2007/08/23 ���g (���u) �d�l�ύX�E���f��752
Revesion History : 2009/03/16 �Ԑi (���u) �d�l�ύX�E���f��1143
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
import webbroker3.aio.message.WEB3AccTransChangeToIfoDepositInputResponse;
import webbroker3.aio.service.delegate.WEB3AccTransChangeToIfoDepositInputService;
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
 * (�؋����ւ̐U�֓��̓T�[�r�XImpl)<BR>
 * �؋����ւ̐U�֓��̓T�[�r�X�����N���X<BR>
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3AccTransChangeToIfoDepositInputServiceImpl
    extends WEB3ClientRequestService
    implements WEB3AccTransChangeToIfoDepositInputService
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AccTransChangeToIfoDepositInputServiceImpl.class);

    /**
     * �؋����ւ̐U�֓��̓T�[�r�X�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�؋����ւ̐U�ցj���͉�ʕ\���f�[�^�擾�v �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3GenResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4135A9090064
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
        
        //===========remain zhou-yong NO.1 begin ============
        
        //1.8) get�؋����v�Z(�⏕���� : �⏕����)
        //�A�C�e���̒�`
        //�؋����v�Z�C���X�^���X�𐶐�����B
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l
        WEB3IfoDepositCalcService l_ifoDepositCalcService =
            (WEB3IfoDepositCalcService) Services.getService(
                WEB3IfoDepositCalcService.class);

        WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
        
        WEB3IfoDepositCalc l_ifoDepositCalc = l_ifoDepositCalcService.getIfoDepositCalc(l_gentradeSubAccount);

        //�؋����U�ւ̎w������擾����B
        //[����]�������F get������()�̖߂�l
        int l_intBizDate =
            l_orderManager.getMarginTransferDesignatedDate(l_datBizDate);

        //1.9)  calc�؋����c��(�w��� : int)
        //�؋����c�����Z�o����
        //[����] 
        //�w����F get�؋����U�֎w���()�̖߂�l
        double l_dblCalcIfoDepositBalance = 
            l_ifoDepositCalc.calcIfoDepositBalance(l_intBizDate);
        
        //1.10) calc�������z( )
        //�A�C�e���̒�`
        //�؋����������z���Z�o����B
        double l_dblCalcNonPayAmount = l_ifoDepositCalc.calcNonPayAmount();
        
        // 1.11) �⏕�����I�u�W�F�N�g���擾����B �i�a��������j
        SubAccount l_subAccount2 = this.getSubAccount(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        // 1.12) get�o���\�z(�⏕���� : �⏕����, ��n�� : Date) 
        //�o���\�z���擾����B 
        //[����] 
        //�⏕�����F  get�⏕����()�̖߂�l�i�a��������j 
        //��n���F get������()�̖߂�l 
        WEB3TPTradingPowerService l_tPTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        WEB3GentradeSubAccount l_gentradeSubAccount2 = (WEB3GentradeSubAccount)l_subAccount2;
        //�o���\�z
        double l_dblCashoutPossiblePrice =  l_tPTradingPowerService.getPaymentTradingPower(
            l_gentradeSubAccount2, l_datBizDate);

        //===========remain zhou-yong NO.1 end ============        
        
        // 1.13) ���X�|���X�f�[�^�𐶐�����
        WEB3AccTransChangeToIfoDepositInputResponse l_response = 
            (WEB3AccTransChangeToIfoDepositInputResponse) l_request.createResponse();
            
        // 18) ���X�|���X�̃v���p�e�B�Z�b�g
        MainAccount l_mainAccount = l_subAccount.getMainAccount();
        l_response.changeCountUpper = 
            ((MainAccountRow)l_mainAccount.getDataSourceObject()).getTransferCount() + "";
        l_response.changeCount = l_intTransferCount + "";
        l_response.preIfoDeposit = 
                WEB3StringTypeUtility.formatNumber(l_dblCalcIfoDepositBalance);
        l_response.nonPayAmt = 
            WEB3StringTypeUtility.formatNumber(l_dblCalcNonPayAmount);
        
        //���X�|���X.�U�։\�z = ����]�̓T�[�r�X.get�o���\�z()�̖߂�l
        l_response.changePossAmt = WEB3StringTypeUtility.formatNumber(
            l_dblCashoutPossiblePrice);

        // ���^�[��           
        log.exiting(STR_METHOD_NAME);     
        return l_response;
    }
}
@
