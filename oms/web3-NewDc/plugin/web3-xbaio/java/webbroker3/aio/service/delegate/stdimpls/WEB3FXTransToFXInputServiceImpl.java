head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.27.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransToFXInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�ւ̐U�֓��̓T�[�r�XImpl(WEB3FXTransToFXInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/1/21 ���z (���u) �V�K�쐬
                   2006/4/27 ����(���u) �d�l�ύX NO.541
                   2006/07/12 ������ (���u) �d�l�ύX�E���f��No.595,600
                   2006/08/24 ��� (SCS) �d�l�ύX�E���f��No.630
Revesion History : 2008/09/23 �g�C�� (���u) �d�l�ύX�E���f��No.996
Revesion History : 2009/03/12 ���u�� (���u) �d�l�ύX�E���f��No.1112
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3FXDataControlService;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.FxTransferMasterParams;
import webbroker3.aio.message.WEB3FXAccInformationUnit;
import webbroker3.aio.message.WEB3FXTransToFXInputRequest;
import webbroker3.aio.message.WEB3FXTransToFXInputResponse;
import webbroker3.aio.service.delegate.WEB3FXTransToFXInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AioTransferDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

/**
 * (FX�ւ̐U�֓��̓T�[�r�XImpl) <BR>
 * FX�ւ̐U�֓��̓T�[�r�X�����N���X <BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3FXTransToFXInputServiceImpl extends WEB3ClientRequestService
    implements WEB3FXTransToFXInputService
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXTransToFXInputServiceImpl.class);  
    
    /**
     * @@roseuid 41E780B101C5
     */
    public WEB3FXTransToFXInputServiceImpl()
    {
    }

    /**
     * FX�ւ̐U�֓��̓T�[�r�X�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�iFX�ւ̐U�֓��́j���͉�ʕ\���f�[�^�擾�v �Q�ƁB <BR>
     * <BR>
     * ======================================================== <BR>
     * �V�[�P���X�}(�u(�ב֕ۏ؋��T�[�r�X���f��) / FX����U�֓��� �v<BR>
     * �iFX����U�֓��́j���͉�ʕ\���f�[�^�擾)<BR> 
     * <BR>: 1.10 createFX�������ꗗ(String, String, String) <BR>
     * �߂�l��null�̏ꍇ�A��O��throw����B <BR>
     * <BR>
     * class: WEB3SystemLayerException <BR>
     * tag: SYSTEM_ERROR_80005 <BR>
     * <BR>
     * ==========================================================
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@roseuid 41BCF31B0066
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        String l_strMethodName = "execute(WEB3GenRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1. get�⏕����(SubAccountTypeEnum)
        //[����] 
        //�⏕�����^�C�v�F 1�i������������i�a����j�j
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.2. validate����(SubAccount)
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //(1)FinApp, TradingModule, AioOrderManager
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        //(2)validate
        l_orderManager.validateOrder(l_subAccount);
        
        //1.3. get��Е�FX�V�X�e������(String, String, String)
        //[�����̐ݒ�] 
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
        //���X�R�[�h�F�@@�⏕����.get����X.getBranchCode()
        //FX�V�X�e���R�[�h�F�@@���N�G�X�g�f�[�^.FX�V�X�e���R�[�h
        //(1)produce FX�f�[�^����T�[�r�X 
        WEB3FXDataControlService l_dataControlService = 
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);
        //(2)
        CompFxConditionParams l_params;        
        WEB3FXTransToFXInputRequest l_fxTransToFXInputRequest = null;
        
        if (l_request instanceof WEB3FXTransToFXInputRequest)
        {
        	l_fxTransToFXInputRequest = (WEB3FXTransToFXInputRequest)l_request;              
        }        
        else
        {
            log.debug(
                "���N�G�X�g�f�[�^��"
                + " WEB3FXTransToFXInputRequest�ȊO�ł���, but is " + 
                l_request.getClass().getName());
            
            log.exiting(l_strMethodName);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + l_strMethodName);
        }
        try
        {
            l_params =
                l_dataControlService.getCompFxCondition(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_fxTransToFXInputRequest.fxSystemCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("get��Е�FX�V�X�e������", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }

        //getFX�U�֏����}�X�^(long, String)
        //�y�����z
        //FX�V�X�e������ID�@@= ��Е�FX�V�X�e������Params.FX�V�X�e������ID
        //�U�֋敪 = 1�F�o��
        FxTransferMasterParams l_fxTransferMasterParams =
            l_dataControlService.getFxTransferMasterParams(
                l_params.getFxSystemId(),
                WEB3AioTransferDivDef.CASHOUT);

        //1.4. validate�O���V�X�e����t�\(String)
        //[�����̐ݒ�] 
        //�V�X�e���R�[�h�F�@@��Е�FX�V�X�e������Params.FX�V�X�e���R�[�h
        l_orderManager.validateOtherSystemAcceptPossible(l_params.getFxSystemCode());

        //1.5) FX�V�X�e���敪�ʂɁA����\���`�F�b�N���s���B
        //[�����̐ݒ�]
        //�⏕�����F�@@get�⏕����()�̖߂�l
        //��Е�FX�V�X�e������Params�F�@@�@@get��Еʂe�w�V�X�e������()�̖߂�l
        l_dataControlService.validateChangePoss(
            l_subAccount,
            l_params);

        //1.6. get������()
        Date l_datOrderBizDate = 
            WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //1.7. get��n��(Date, SubAccount, String)
        //��n�����擾����B 
        //[�����̐ݒ�] 
        //�������F get������()�̖߂�l 
        //�⏕�����F�@@get�⏕����()�̖߂�l
        //��n���ݒ�敪�F�@@getFX�U�֏����}�X�^().��n���ݒ�敪
        Date l_datDeliveryDate = l_dataControlService.getDeliveryDate(
            l_datOrderBizDate,
            l_subAccount,
            l_fxTransferMasterParams.getDeliveryDateDiv());

        //1.8. get�U�։�(SubAccount, Date, OrderCategEnum)
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�������F get������()�̖߂�l 
        //�����J�e�S���F�@@15�i�ב֕ۏ؋��U�ցj
        int l_intTransferCount = 
            l_orderManager.getTransferCount(
                l_subAccount, l_datOrderBizDate, OrderCategEnum.FX);
        
        //1.9. validate�U�։\��(SubAccount, Date, OrderCategEnum)
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�������F get������()�̖߂�l 
        //�����J�e�S���F�@@15�i�ב֕ۏ؋��U�ցj
        int l_intPossibleCount = 
            l_orderManager.validateTransferPossibleCount(
                l_subAccount, l_datOrderBizDate, OrderCategEnum.FX);
        
        //1.10. get�o���\�z(�⏕���� : �⏕����, ��n�� : Date) 
        //�o���\�z���擾����B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //��n���F get��n��()�̖߂�l 
        WEB3TPTradingPowerService l_tPTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);

        // �p�����[�^�E��n���̌���
        WEB3GentradeSubAccount l_genSubAccount = (WEB3GentradeSubAccount) l_subAccount;

        double l_dblCashoutPossiblePrice =  
            l_tPTradingPowerService.getPaymentTradingPower(
                    l_genSubAccount, l_datDeliveryDate);
                
        //1.11. createFX�������ꗗ(String, String, String)
        //[�����̐ݒ�] 
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
        //���X�R�[�h�F�@@�⏕����.get����X.getBranchCode() 
        //�ڋq�R�[�h�F�@@�⏕����.getMainAccount().getAccountCode()
        //FX�V�X�e���R�[�h�F�@@���N�G�X�g�f�[�^.FX�V�X�e���R�[�h
        WEB3FXAccInformationUnit[] l_accInformationUnit =
            l_dataControlService.createFXAccInformationUnits(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_subAccount.getMainAccount().getAccountCode(),
                l_fxTransToFXInputRequest.fxSystemCode);
                
        if (l_accInformationUnit == null)
        {
            log.debug("FX�������擾�G���[");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                "FX�������擾�G���[");
        }
        
        //1.12. createResponse()
        WEB3FXTransToFXInputResponse l_response = 
            (WEB3FXTransToFXInputResponse)l_request.createResponse();
            
        //1.13. ���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        //FX�������ꗗ�FcreateFX�������ꗗ()�̖߂�l
        l_response.fxAccInformationList = l_accInformationUnit;
        
        //�U�֏���񐔁Fvalidate�U�։\��()�̖߂�l
        l_response.transferCountUpper = String.valueOf(l_intPossibleCount);
        
        //�U�։񐔁Fget�U�։�()�̖߂�l
        l_response.transferCount = String.valueOf(l_intTransferCount);
        
        //�U�։\�z�Fget�o���\�z()�̖߂�l
        l_response.transferableAmt = 
            WEB3StringTypeUtility.formatNumber(l_dblCashoutPossiblePrice);
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
}@
