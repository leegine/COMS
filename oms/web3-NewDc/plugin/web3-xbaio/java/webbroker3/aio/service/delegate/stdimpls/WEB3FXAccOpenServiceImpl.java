head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.27.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXAccOpenServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�����J�݃T�[�r�XImpl(WEB3FXAccOpenServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/25 ���E (���u) �V�K�쐬
                 : 2006/02/08 �A���� (���u) �d�l�ύX�E���f��470
                 : 2006/04/24 �юu�� (���u) �d�l�ύX�E���f��532
                 : 2006/05/08 ���� (���u) �d�l�ύX�E���f��550
Revesion History : 2008/04/09 �g�C�� (���u) �d�l�ύX�E���f��833 ���f��842 ���f��844
Revesion History : 2008/04/23 ���u�� (���u) �d�l�ύX���f��.845
                 : 2008/05/08 ���� (SCS) �d�l�ύX�E���f��833
                 : 2008/05/23 �O�� (SCS)  
Revesion History : 2008/05/20 �đo�g (���u) �d�l�ύX�E���f��No.854 No.855 No.857 No.874 No.877 No.883
Revesion History : 2008/09/22 ���g (���u) �d�l�ύX�E���f��1005~1009,1011,1012,1015,1052,1054
Revesion History : 2009/03/18  �Ԑi (���u) �d�l�ύX�E���f��1123,1127,1128,1130,1131,1137,1160 �c�a�X�V�d�l 215
Revesion History : 2009/05/31 �đo�g (���u) �d�l�ύX�E���f��No.1165
Revesion History : 2009/09/16 �И��� (���u) �d�l�ύX�E���f��1196
Revesion History : 2009/10/09 �����F (���u) �d�l�ύX�E���f��1235
Revesion History : 2009/10/27 �����F(���u) �d�l�ύX ���f��No.1246
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.aio.WEB3FXAccOpenConnection;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3FXDataControlService;
import webbroker3.aio.WEB3FXTelegramProcessService;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.GftAccountOpenStatusParams;
import webbroker3.aio.define.WEB3AdminAioGftOperationDivDef;
import webbroker3.aio.define.WEB3AioAcceptResultCodeDef;
import webbroker3.aio.define.WEB3AioFxSystemCodeDivDef;
import webbroker3.aio.define.WEB3AioTransferDetailMessageDef;
import webbroker3.aio.message.WEB3FXAccOpenAskingRequest;
import webbroker3.aio.message.WEB3FXAccOpenAskingResponse;
import webbroker3.aio.message.WEB3FXAccOpenCompleteRequest;
import webbroker3.aio.message.WEB3FXAccOpenCompleteResponse;
import webbroker3.aio.message.WEB3FXAccOpenCompleteSoapRequest;
import webbroker3.aio.message.WEB3FXAccOpenCompleteSoapResponse;
import webbroker3.aio.message.WEB3FXAccOpenConfirmRequest;
import webbroker3.aio.message.WEB3FXAccOpenConfirmResponse;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.aio.service.delegate.WEB3FXAccOpenService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccOpenRealUpdateDef;
import webbroker3.common.define.WEB3AccTypeDef;
import webbroker3.common.define.WEB3AccountOpenDef;
import webbroker3.common.define.WEB3AioSoapConnectDivDef;
import webbroker3.common.define.WEB3ExtConnectSystemCodeDef;
import webbroker3.common.define.WEB3GftErrorReasonCodeDef;
import webbroker3.common.define.WEB3QuestionCheckDivDef;
import webbroker3.common.define.WEB3SendRcvDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccOpenDiv;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (FX�����J�݃T�[�r�XImpl) <BR>
 * FX�����J�݃T�[�r�X�����N���X <BR>
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3FXAccOpenServiceImpl extends WEB3ClientRequestService
    implements WEB3FXAccOpenService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXAccOpenServiceImpl.class);  
    
    /**
     * @@roseuid 41E7829703D8
     */
    public WEB3FXAccOpenServiceImpl()
    {
    }

    /**
     * FX�����J�݃T�[�r�X�������s���B <BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̂����ꂩ�̃��\�b�h���R�[������B <BR>
     * �Evalidate�����J��() <BR>
     * �Estart�����J��() <BR>
     * �Esubmit�����J��() <BR>
     * �Esubmit�����J��()�iSOAP�ڑ��j<BR>
     * 
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41C78E8E02A3
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3FXAccOpenCompleteSoapRequest)
        {
            l_response =
                this.submitAccountOpenSoap(
                    (WEB3FXAccOpenCompleteSoapRequest) l_request);
        }
        else if (l_request instanceof WEB3FXAccOpenAskingRequest)
        {
            l_response =
                this.startAccountOpen(
                    (WEB3FXAccOpenAskingRequest) l_request);
        }
        else if (l_request instanceof WEB3FXAccOpenConfirmRequest)
        {
            l_response =
                this.validateAccountOpen(
                    (WEB3FXAccOpenConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3FXAccOpenCompleteRequest)
        {
            l_response =
                this.submitAccountOpen(
                    (WEB3FXAccOpenCompleteRequest) l_request);
        }
        else
        {
            log.debug(
                    "���N�G�X�g�f�[�^��"
                    + " WEB3FXAccOpenAskingRequest "
                    + " �� WEB3FXAccOpenConfirmRequest"
                    + " �� WEB3FXAccOpenCompleteRequest" 
                    + " �� WEB3FXAccOpenCompleteSoapRequest�ȊO�ł���, but is "
                    + l_request.getClass().getName());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�����J��) <BR>
     * �����J�݂̔����R�����s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�iFX�����J�݁jvalidate�����J�݁v�Q�ƁB <BR>
     * 
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3FXAccOpenConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 41C78E8E02C2
     */
    protected WEB3FXAccOpenConfirmResponse validateAccountOpen(
        WEB3FXAccOpenConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAccountOpen(WEB3FXAccOpenConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1) validate( )
        l_request.validate();
        
        //1.2) get�⏕����(SubAccountTypeEnum)
        //�A�C�e���̒�`
        //�⏕�����I�u�W�F�N�g���擾����B 
        //[����] 
        //�⏕�����^�C�v�F 1�i������������i�a����j�j
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.3) validate����(SubAccount)
        //�A�C�e���̒�`
        //�ȉ��̃`�F�b�N���s���B 
        //�@@�|��t���ԃ`�F�b�N 
        //�@@�|�V�X�e����~���`�F�b�N 
        //�@@�|�ڋq�̃`�F�b�N�i�x�q�A�Ǘ����b�N���j 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        
        l_aioOrderManager.validateOrder(l_subAccount);
        
        //get��Е�FX�V�X�e������(String, String, String)
        //�A�C�e���̒�`
        //��Е�FX�V�X�e������Params���擾����B
        //[�����̐ݒ�] 
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
        //���X�R�[�h�F�@@�⏕����.get����X.getBranchCode()
        //FX�V�X�e���R�[�h�F�@@���N�G�X�g�f�[�^.FX�V�X�e���R�[�h
        WEB3FXDataControlService l_fxDataControlService = 
            (WEB3FXDataControlService) Services.getService(WEB3FXDataControlService.class);
        
        CompFxConditionParams l_compFxConditionParams = null;
        try
        {
            l_compFxConditionParams = 
                l_fxDataControlService.getCompFxCondition(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_request.fxSystemCode);
        }
        catch(NotFoundException l_ex)
        {
            log.debug("__NotFoundExcepiton__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);        
        }

        //1.5) validate�O���V�X�e����t�\(String)
        //�A�C�e���̒�`
        //FX�V�X�e���̎�t���ԃ`�F�b�N���s���B
        //[�����̐ݒ�] 
        //�V�X�e���R�[�h�F�@@��Е�FX�V�X�e������Params.FX�V�X�e���R�[�h
        String l_strFxSystemCode = l_compFxConditionParams.getFxSystemCode();
        if (!WEB3AioFxSystemCodeDivDef.TFX.equals(l_strFxSystemCode))
        {
            l_aioOrderManager.validateOtherSystemAcceptPossible(l_strFxSystemCode);
        }
        
        //1.6) validateFX�����J�݉\(SubAccount, String)
        //�A�C�e���̒�`
        //FX�����J�݂��\�ł��邩���`�F�b�N����B 
        //[�����̐ݒ�] 
        //�⏕�����F�@@get�⏕����()�̖߂�l 
        //��Е�FX�V�X�e������Params�F�@@get��Е�FX�V�X�e������()�̖߂�l
        l_aioOrderManager.validateFXAccOpenPossible(l_subAccount, l_compFxConditionParams);
        
        //1.7) createResponse( )
        //�A�C�e���̒�`
        //���X�|���X�f�[�^�𐶐�����B
        WEB3FXAccOpenConfirmResponse l_response 
            = (WEB3FXAccOpenConfirmResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (start�����J��) <BR>
     * �����J�݂̈˗��������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�iFX�����J�݁jstart�����J�݁v�Q�ƁB <BR>
     * 
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3FXAccOpenAskingResponse
     * @@throws WEB3BaseException
     * @@roseuid 41C78E8E02E2
     */
    protected WEB3FXAccOpenAskingResponse startAccountOpen(
        WEB3FXAccOpenAskingRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "startAccountOpen(WEB3FXAccOpenAskingRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1) validate( )
        l_request.validate();

        //get�⏕����(�⏕�����^�C�v : SubAccountTypeEnum)
        //[����]
        //�⏕�����^�C�v�F 1�i������������i�a����j�j
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        WEB3FXDataControlService l_fxDataControlService =
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);
        //get��Е�FX�V�X�e������(String, String, String)
        //�A�C�e���̒�`
        //��Е�FX�V�X�e������Params���擾����B
        //[�����̐ݒ�]
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h
        //���X�R�[�h�F�@@�⏕����.get����X().getBranchCode()
        //FX�V�X�e���R�[�h�F�@@���N�G�X�g�f�[�^.FX�V�X�e���R�[�h
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();

        CompFxConditionParams l_compFxConditionParams = null;
        try
        {
            l_compFxConditionParams =
                l_fxDataControlService.getCompFxCondition(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_request.fxSystemCode);
        }
        catch(NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //get��Е�FX�V�X�e������()�̖߂�l.���⓯�Ӄ`�F�b�N���{�敪==1�F�`�F�b�N����̏ꍇ
        if (WEB3QuestionCheckDivDef.CHECK.equals(
            l_compFxConditionParams.getQuestionCheckDiv()))
        {
            //validateFX������ӎ���(FX������ӎ�����[])
            //[�����̐ݒ�]
            //FX������ӎ�����ꗗ�F�@@���N�G�X�g�f�[�^.FX������ӎ�����ꗗ
            l_fxDataControlService.validateFXTradingAgreeQuestion(l_request.fxTradeAgreementList);
        }

        //1.4) validate����(SubAccount)
        //�A�C�e���̒�`
        //�ȉ��̃`�F�b�N���s���B 
        //�@@�|��t���ԃ`�F�b�N 
        //�@@�|�V�X�e����~���`�F�b�N 
        //�@@�|�ڋq�̃`�F�b�N�i�x�q�A�Ǘ����b�N���j 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        
        l_aioOrderManager.validateOrder(l_subAccount);

        //1.6) validate�O���V�X�e����t�\(String)
        //�A�C�e���̒�`
        //FX�V�X�e���̎�t���ԃ`�F�b�N���s���B
        //[�����̐ݒ�] 
        //�V�X�e���R�[�h�F�@@��Е�FX�V�X�e������Params.FX�V�X�e���R�[�h
        String l_strFxSystemCode = l_compFxConditionParams.getFxSystemCode();
        if (!WEB3AioFxSystemCodeDivDef.TFX.equals(l_strFxSystemCode))
        {
            l_aioOrderManager.validateOtherSystemAcceptPossible(l_strFxSystemCode);
        }
        
        //1.7) validateFX�����J�݉\(SubAccount, String)
        //�A�C�e���̒�`
        //FX�����J�݂��\�ł��邩���`�F�b�N����B 
        //[�����̐ݒ�] 
        //�⏕�����F�@@get�⏕����()�̖߂�l 
        //��Е�FX�V�X�e������Params�F�@@get��Е�FX�V�X�e������()�̖߂�l
        l_aioOrderManager.validateFXAccOpenPossible(l_subAccount, l_compFxConditionParams);
        
        //1.8) get�㗝���͎�( )
        //�A�C�e���̒�`
        //�㗝���͎҂��擾����B
        Trader l_trader = this.getTrader();
        
        //1.9) validate����p�X���[�h(Trader, SubAccount, String)
        //�A�C�e���̒�`
        //�p�X���[�h�̃`�F�b�N���s���B 
        //[����] 
        //�㗝���͎ҁF get�㗝���͎�()�̖߂�l 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ� 
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator(); 
        
        OrderValidationResult l_orderValidationResult =
            l_orderValidator.validateTradingPassword(
                l_trader,
                l_subAccount,
                l_request.password);
        
        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("�p�X���[�h�̃`�F�b�N���s��");
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);                          
        }
        
        //1.10) get�V�KFX���O�C��ID(String, String)
        //�A�C�e���̒�`
        //�V�K��FX���O�C��ID��t�Ԃ���B 
        //[�����̐ݒ�] 
        //FX���O�C��ID�������F�@@��Е�FX�V�X�e������Params.FX���O�C��ID������  
        //�ڋq�R�[�h�F�@@�⏕����.getMainAccount().getAccountCode()
        String l_strAccountCode = l_subAccount.getMainAccount().getAccountCode();
        
        String l_strNewFXLoginID = l_fxDataControlService.getNewFXLoginID(
            l_compFxConditionParams.getFxHeadOfLoginId(),
            l_strAccountCode);
        
        //1.11) get�V�K���ʃR�[�h(String, String, ProductTypeEnum)
        //�A�C�e���̒�`
        //�V�K�̎��ʃR�[�h���擾����B 
        //[�����̐ݒ�] 
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
        //���X�R�[�h�F�@@�⏕����.get����X().getBranchCode() 
        //�����^�C�v�F 5�i�����j
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService = 
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);
        
        String l_strNewNumber = 
            l_hostReqOrderNumberManageService.getNewNumber(
                l_strInstitutionCode,
                l_strBranchCode, 
                ProductTypeEnum.CASH);

        String l_strOperationDiv = WEB3AdminAioGftOperationDivDef.ACCOUNT_OPEN;
        //get��Е�FX�V�X�e������()�̖߂�l.�O���ڑ��V�X�e���R�[�h==01�FGFT�̏ꍇ
        //getGFT�����敪(�⏕����)
        //�����敪��ݒ肷��B
        //[get�����݋敪()�Ɏw�肷�����]
        //�⏕�����F�@@get�⏕����()�̖߂�l
        if (WEB3ExtConnectSystemCodeDef.GFT.equals(
            l_compFxConditionParams.getExtConnectSystemCode()))
        {
        	l_strOperationDiv = this.getGFTOperationDiv(l_subAccount);
        }
        
        //1.12) GFT�˗��d������( )
        //�A�C�e���̒�`
        //GFT�˗��d�����׃I�u�W�F�N�g�𐶐�����B
        WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit = new WEB3FXGftAskingTelegramUnit();
        
        //1.13) (*)�v���p�e�B�Z�b�g
        //GFT�˗��d�����ׂɕK�v�ȃv���p�e�B���Z�b�g����i���L�ȊO�̃v���p�e�B�͐ݒ肵�Ȃ��j
        
        //DIR��GFT���M����   �F���ݎ����i�V�X�e���^�C���X�^���v�j
        Timestamp l_tsSystemTimestamp = new Timestamp(new Date().getTime());
        l_fXGftAskingTelegramUnit.dirSendTime =
            WEB3DateUtility.formatDate(l_tsSystemTimestamp, "yyyyMMddHHmmss");

        //�����敪:
        //get��Е�FX�V�X�e������()�̖߂�l.�O���ڑ��V�X�e���R�[�h==01�FGFT�̏ꍇ
        //get�����敪()�̖߂�l
        //get��Е�FX�V�X�e������()�̖߂�l.�O���ڑ��V�X�e���R�[�h==01�FGFT�ȊO�̏ꍇ
        //�V�K�J�݁F01
        l_fXGftAskingTelegramUnit.gftOperationDiv = l_strOperationDiv;

        //���[���A�h���X   �F���N�G�X�g�f�[�^.FX���[���A�h���X
        l_fXGftAskingTelegramUnit.fxMailAddress = l_request.fxMailAddress;
        
        //�������O�C��ID  �Fget�V�KFX���O�C��ID()�̖߂�l
        l_fXGftAskingTelegramUnit.fxFirstLoginId = l_strNewFXLoginID;
        
        //�����p�X���[�h   �F���N�G�X�g�f�[�^.FX�Ïؔԍ�
        l_fXGftAskingTelegramUnit.fxFirstPassword = l_request.fxPassword;
        
        //�S���敪      �F��Е�FX�V�X�e������Params.�S���敪
        l_fXGftAskingTelegramUnit.groupName = l_compFxConditionParams.getGroupName();
        
        //��ЃR�[�h     �F�⏕����.�،���ЃR�[�h
        l_fXGftAskingTelegramUnit.institutionCode = l_strInstitutionCode;
        
        //WOLF�Z�b�V�����L�[   �F���N�G�X�g�f�[�^.WOLF�Z�b�V�����L�[
        l_fXGftAskingTelegramUnit.wolfSession = l_request.wolfSession;
        
        //�A�v���P�[�V����ID    �F���N�G�X�g�f�[�^.�A�v���P�[�V����ID
        l_fXGftAskingTelegramUnit.wolfAid = l_request.wolfAid;
        
        //�Đ����T�[�r�XID �F���N�G�X�g�f�[�^.�Đ����T�[�r�XID
        l_fXGftAskingTelegramUnit.regetServiceId = l_request.regetServiceId;
        
        //SSID      �F���N�G�X�g�f�[�^.SSID
        l_fXGftAskingTelegramUnit.wolfSsid = l_request.wolfSsid;
        
        //���X�R�[�h     �F�⏕����.get����X().getBranchCode()
        l_fXGftAskingTelegramUnit.branchCode = l_strBranchCode;
        
        //�ڋq�R�[�h     �F�⏕����.getMainAccount().getAccountCode()
        l_fXGftAskingTelegramUnit.accountCode = l_strAccountCode;
        
        //���ʃR�[�h     �Fget�V�K���ʃR�[�h()�̖߂�l
        l_fXGftAskingTelegramUnit.requestNumber = l_strNewNumber;
        
        //���O�i���j     �F�ڋq(*).���O�i�c���j
        //(*)�⏕����.getMainAccount()�ɂĎ擾
        MainAccountParams l_mainAccountParams = 
            (MainAccountParams)l_subAccount.getMainAccount().getDataSourceObject();
        
        l_fXGftAskingTelegramUnit.fxLastName = l_mainAccountParams.getFamilyName();

        //�i�Z���P�A�Z���Q�A�Z���R�̑O��X�y�[�X���폜�j
        //�Z���P�F�ڋq�i*�j.�Z���P
        String l_strAddress1 = l_mainAccountParams.getAddressLine1();
        l_strAddress1 = l_strAddress1.replaceAll("(^(\\s|�@@)+)|((\\s|�@@)+$)", "");
        if (WEB3StringTypeUtility.isNotEmpty(l_strAddress1))
        {
            l_fXGftAskingTelegramUnit.address1 = l_strAddress1;
        }

        //�Z���Q�F�ڋq�i*�j.�Z���Q
        String l_strAddress2 = l_mainAccountParams.getAddressLine2();
        if (l_strAddress2 != null)
        {
            l_strAddress2 =
                l_strAddress2.replaceAll("(^(\\s|�@@)+)|((\\s|�@@)+$)", "");
        }

        if (WEB3StringTypeUtility.isNotEmpty(l_strAddress2))
        {
            l_fXGftAskingTelegramUnit.address2 = l_strAddress2;
        }

        //�Z���R�F�ڋq�i*�j.�Z���R
        String l_strAddress3 = l_mainAccountParams.getAddressLine3();
        if (l_strAddress3 != null)
        {
            l_strAddress3 =
                l_strAddress3.replaceAll("(^(\\s|�@@)+)|((\\s|�@@)+$)", "");
        }
        if (WEB3StringTypeUtility.isNotEmpty(l_strAddress3))
        {
            l_fXGftAskingTelegramUnit.address3 = l_strAddress3;
        }

        //1.14) createGFT�d���n�b�V���l(GFT�˗��d������)
        //�A�C�e���̒�`
        //GFT�d���̃n�b�V���l���擾����B 
        //[�����̐ݒ�] 
        //GFT�˗��d�����ׁF�@@�v���p�e�B�Z�b�g���s����GFT�˗��d������
        WEB3FXTelegramProcessService l_fXTelegramProcessService = 
            (WEB3FXTelegramProcessService)Services.getService(WEB3FXTelegramProcessService.class);
        
        String l_strGFTTelegramHashValue = 
            l_fXTelegramProcessService.createGFTTelegramHashValue(l_fXGftAskingTelegramUnit);
        
        //createGFT�d���n�b�V���l()�̖߂�l�̃n�b�V���l��
        //GFT�˗��d������.�n�b�V���l�ɃZ�b�g����B
        l_fXGftAskingTelegramUnit.hashValue = l_strGFTTelegramHashValue;
        
        //GFT�����J�ݏ󋵃e�[�u���A����сA
        //GFT�d���ۑ��e�[�u���A����сA
        //����񓚃e�[�u����
        //�f�[�^��insert����B
        
        //1.15) insertGFT�d���ۑ�(GFT�˗��d������)
        //�A�C�e���̒�`
        //GFT�d���ۑ��e�[�u���ɍs��insert����B 
        //[�����̐ݒ�] 
        //GFT�˗��d�����ׁF�@@�v���p�e�B�Z�b�g���s����GFT�˗��d������
        l_fxDataControlService.insertGFTMessage(l_fXGftAskingTelegramUnit);
        
        //insertGFT�����J�ݏ�(GFT�˗��d������, String, String)
        //�A�C�e���̒�`
        //GFT�����J�ݏ󋵃e�[�u���ɍs��insert����B
        //[�����̐ݒ�] 
        //GFT�˗��d�����ׁF�@@�v���p�e�B�Z�b�g���s����GFT�˗��d������
        //������敪�F ���N�G�X�g�f�[�^.������敪
        //FX�V�X�e���R�[�h�F�@@��Е�FX�V�X�e������Params.FX�V�X�e���R�[�h
        l_fxDataControlService.insertGFTAccountOpenStatus(
            l_fXGftAskingTelegramUnit,
            l_request.agreementDiv,
            l_strFxSystemCode);
        
        //insert�����(String, String, String, FX������ӎ�����[], String)
        //�A�C�e���̒�`
        //����񓚃e�[�u���ɍs��insert����B 
        //[�����̐ݒ�] 
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
        //���X�R�[�h�F�@@�⏕����.get����X().getBranchCode() 
        //���ʃR�[�h�F�@@get�V�K���ʃR�[�h()�̖߂�l 
        //FX������ӎ�����ꗗ�F�@@���N�G�X�g�f�[�^.FX������ӎ�����ꗗ
        //FX�V�X�e���R�[�h�F�@@���N�G�X�g�f�[�^.FX�V�X�e���R�[�h
        l_fxDataControlService.insertQuestionAnswer(
            l_strInstitutionCode,
            l_strBranchCode,
            l_strNewNumber,
            l_request.fxTradeAgreementList,
            l_request.fxSystemCode);
        
        //1.18) createResponse( )
        //�A�C�e���̒�`
        //���X�|���X�f�[�^�𐶐�����
        WEB3FXAccOpenAskingResponse l_response = 
            (WEB3FXAccOpenAskingResponse)l_request.createResponse();
        
        //1.19) (*)�v���p�e�B�Z�b�g
        //���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        //URL�F ��Е�FX�V�X�e������Params.URL
        l_response.fxUrl = l_compFxConditionParams.getUrl();

        //GFT�˗��d�����ׁF�@@(*�@@��L�ŕҏW���s����GF
        l_response.fxGftAskingTelegramUnit = l_fXGftAskingTelegramUnit;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�����J��) <BR>
     * �����J�݂̓o�^�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�iFX�����J�݁jsubmit�����J�݁v�Q�ƁB <BR>
     * <BR>
     * ======================================================== <BR>
     * �V�[�P���X�}(�u(�ב֕ۏ؋��T�[�r�X���f��) / FX�����J�� �v<BR>
     * �iFX�����J�݁jsubmit�����J��) <BR>
     * : 1.3 getGFT�����J�ݏ�(String, String, String) <BR>
     * �߂�l��null�̏ꍇ�A��O��thorw����B <BR>
     * <BR>
     * class: WEB3SystemLayerException <BR>
     * tag: SYSTEM_ERROR_80005 <BR>
     * <BR>
     * ========================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * �V�[�P���X�}(�u(�ב֕ۏ؋��T�[�r�X���f��) / FX�����J�� �v<BR>
     * �iFX�����J�݁jsubmit�����J��) <BR>
     * : 1.7.2 (*)��O��throw <BR>
     * �@@�̏ꍇ�F�u2�d��M�G���[�v <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01972 <BR>
     * <BR>
     * �A�B�C�̏ꍇ�F�u���̑���FX�V�X�e���G���[�v <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01800 <BR>
     * <BR>
     * �D�̏ꍇ�FGFT���ʒʒm�d������.��t���ʂɉ����Ĉȉ��̗�O�ƂȂ� <BR>
     * 00000105(�z�X�g�������ԊO)�̏ꍇ �F�u��t���ԊO�G���[�v <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01801 <BR>
     * <BR>
     * 00000199(�z�X�g�V�X�e���G���[)�̏ꍇ �F�u�ʐM�G���[�v <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01802 <BR>
     * <BR>
     * 00000204(�c���s���G���[)�̏ꍇ �F�u�c���s���G���[�v <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01803 <BR>
     * <BR>
     * 00000801(2�d���M�G���[)�̏ꍇ �F�u2�d���M�G���[�v <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01799 <BR>
     * <BR>
     * �ȊO�̎�t���ʂ̏ꍇ �F�u���̑���FX�V�X�e���G���[�v <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01800 <BR>
     * <BR>
     * ========================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * �V�[�P���X�}(�u(�ב֕ۏ؋��T�[�r�X���f��) / FX�����J�� �v<BR>
     * �iFX�����J�݁jsubmit�����J��) <BR>
     * : 1.1.3 createFX�������ꗗ(String, String, String) <BR>
     * �߂�l��null�̏ꍇ�A��O��thorw����B <BR>
     * <BR>
     * class: WEB3SystemLayerException <BR>
     * tag: SYSTEM_ERROR_80005 <BR>
     * <BR>
     * ========================================================== 
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3FXAccOpenCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 41C78E8E02F1
     */
    protected WEB3FXAccOpenCompleteResponse submitAccountOpen(
        WEB3FXAccOpenCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitAccountOpen(WEB3FXAccOpenCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1) validate( )
        l_request.validate();
        
        //GFT�d���ۑ��e�[�u����
        //�f�[�^��insert����B
        
        //insertGFT�d���ۑ�(GFT���ʒʒm�d������)
        //�A�C�e���̒�`
        //GFT�d���ۑ��e�[�u���ɍs��insert����B
        //[�����̐ݒ�] 
        //GFT���ʒʒm�d�����ׁF�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������
        WEB3FXDataControlService l_fxDataControlService = 
            (WEB3FXDataControlService) Services.getService(WEB3FXDataControlService.class);
        
        l_fxDataControlService.insertGFTMessage(l_request.fxGftResultNoticeTelegramUnit);
        
        //1.3) getGFT�����J�ݏ�(String, String, String)
        //�A�C�e���̒�`
        //GFT�����J�ݏ�Params���擾����B 
        //[�����̐ݒ�] 
        //�،���ЃR�[�h�F�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������.��ЃR�[�h 
        //���X�R�[�h�F�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������.���X�R�[�h 
        //���ʃR�[�h�F�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������.���ʃR�[�h
        GftAccountOpenStatusParams l_params = 
            l_fxDataControlService.getGFTAccountOpenStatus(
                l_request.fxGftResultNoticeTelegramUnit.institutionCode,
                l_request.fxGftResultNoticeTelegramUnit.branchCode,
                l_request.fxGftResultNoticeTelegramUnit.requestNumber);
        
        //�߂�l��null�̏ꍇ�A��O��thorw����B
        if(l_params == null)
        {
            log.debug("GFT�����J�ݏ󋵎擾�G���[");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "GFT�����J�ݏ󋵎擾�G���[");   
        }

        //get��Е�FX�V�X�e������(String, String, String)
        //�،���ЃR�[�h�F ���N�G�X�g�f�[�^.GFT���ʒʒm�d������.�،���ЃR�[�h
        //���X�R�[�h�F�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������.���X�R�[�h
        //FX�V�X�e���R�[�h�F�@@getGFT�����J�ݏ�()�̖߂�l.FX�V�X�e���R�[�h
        CompFxConditionParams l_compFxConditionParams = null;
        try
        {
            l_compFxConditionParams =
                l_fxDataControlService.getCompFxCondition(
                    l_request.fxGftResultNoticeTelegramUnit.institutionCode,
                    l_request.fxGftResultNoticeTelegramUnit.branchCode,
                    l_params.getFxSystemCode());
        }
        catch (NotFoundException l_ex)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        boolean l_blnGFTTelegramSet = false;
        boolean l_blnGFTTelegramLengthPropSame = false;
        boolean l_blnMailSame = false;

        //get��Е�FX�V�X�e������()�̖߂�l.SOAP�ڑ����{�敪==�O�FSOAP�ڑ������{�̏ꍇ
        if (WEB3AioSoapConnectDivDef.SOAP_CONNECT_NOT_ENFORCEMENT.equals(
            l_compFxConditionParams.getSoapConnectDiv()))
        {
            // isGFT�d�����ڐݒ�(GFT���ʒʒm�d������)
            //�A�C�e���̒�`
            //���ʒʒm�d���̕K�{���ڂɒl���ݒ肳��Ă��邩���肷��B
            //[�����̐ݒ�]
            //GFT���ʒʒm�d�����ׁF�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������
            WEB3FXTelegramProcessService l_fXTelegramProcessService =
                (WEB3FXTelegramProcessService)Services.getService(WEB3FXTelegramProcessService.class);

            l_blnGFTTelegramSet =
                l_fXTelegramProcessService.isGFTTelegramSet(l_request.fxGftResultNoticeTelegramUnit);

            //isGFT�d������������v(GFT���ʒʒm�d������)
            //�A�C�e���̒�`
            //���ʒʒm�d���̐ݒ�l�̃t�H�[�}�b�g�������������肷��B
            //[�����̐ݒ�]
            //GFT���ʒʒm�d�����ׁF�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������
            l_blnGFTTelegramLengthPropSame =
                l_fXTelegramProcessService.isGFTTelegramLengthPropSame(
                    l_request.fxGftResultNoticeTelegramUnit);

            //isGFT�d�����ڐݒ�()=true and isGFT�d������������v()=true �̏ꍇ�A���{
            if (l_blnGFTTelegramSet && l_blnGFTTelegramLengthPropSame)
            {
                // isGFT�d������M���ڈ�v(GFT���ʒʒm�d������)
                //�A�C�e���̒�`
                //����M�d���̍��ڂ���v���邩���肷��B
                //[�����̐ݒ�]
                //GFT���ʒʒm�d�����ׁF�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������
                l_blnMailSame =
                    l_fXTelegramProcessService.isGFTTelegramSendAndReceiveValueSame(
                        l_request.fxGftResultNoticeTelegramUnit);
            }
        }

        //get�����J��FX�V�X�e���R�[�h(String)
        //[����]
        //�،���ЃR�[�h�F ���N�G�X�g�f�[�^.GFT���ʒʒm�d������.�،���ЃR�[�h
        String l_strSameTimeFxSystemCode =
            l_fxDataControlService.getSameTimeFxSystemCode(
                l_request.fxGftResultNoticeTelegramUnit.institutionCode);

        //1.7 (*1)
        //(*1)�ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�A���{����B
        //��get��Е�FX�V�X�e������()�̖߂�l.SOAP�ڑ����{�敪 != 0�FSOAP�ڑ������{�̏ꍇ�A�B�A�C�͏����Ɋ܂߂Ȃ�
        //�@@����M�敪���s���̏ꍇ�i2�d��M�G���[�j
        //�igetGFT�����J�ݏ�()�̖߂�l��GFT�����J�ݏ�Params.����M�敪 �� 2(��M��)�j
        //�A����M�敪���s���̏ꍇ�i���̑��G���[�j
        //�igetGFT�����J�ݏ�()�̖߂�l��GFT�����J�ݏ�Params.����M�敪 != (1(���M��)�A2(��M��))
        //�B���ʒʒm�d�����e�̑Ó������s���̏ꍇ
        //�iisGFT�d�����ڐݒ�()��fasle�A�܂��́AisGFT�d������������v()��false�j
        //�C����M�d���̍��ڂ��s��v�̏ꍇ
        //�iisGFT�d������M���ڈ�v()��false�j
        //�D�U�ւ�����Ɏ�t���Ȃ������ꍇ
        //(���N�G�X�g�f�[�^.GFT���ʒʒm�d������.��t���� != 00000000�i����j�j
        if (!WEB3AioSoapConnectDivDef.SOAP_CONNECT_NOT_ENFORCEMENT.equals(
            l_compFxConditionParams.getSoapConnectDiv()))
        {
            l_blnGFTTelegramSet = true;
            l_blnGFTTelegramLengthPropSame = true;
            l_blnMailSame = true;
        }
        if(WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(l_params.getSendRcvDiv())
            || (!(WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(l_params.getSendRcvDiv()) 
                    || WEB3SendRcvDivDef.SEND_COMPLETE.equals(l_params.getSendRcvDiv()))
                )
            || (!l_blnGFTTelegramSet || !l_blnGFTTelegramLengthPropSame)
            || (!l_blnMailSame)
            || !WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000.equals(
                l_request.fxGftResultNoticeTelegramUnit.resultCode)
            )
        {
            //1.7.1 (*2)
            //(*2)
            //�@@�ȊO�̏ꍇ�A���{����B
            if(!WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(l_params.getSendRcvDiv()))
            {
                //1.7.1.1) updateGFT�����J�ݏ�(GFT�����J�ݏ�Params, GFT���ʒʒm�d������, String)
                //�A�C�e���̒�`
                //GFT�����J�ݏ󋵂̍X�V���s���B 
                //[�����̐ݒ�] 
                //�@@GFT�����J�ݏ�Params�F�@@getGFT�����J�ݏ�()�̖߂�l 
                //�@@GFT���ʒʒm�d�����ׁF�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������ 
                //�@@�G���[���R�R�[�h�F�@@ 
                //�@@�@@�A�̏ꍇ�F0001�i����M�敪�`�F�b�N�G���[�j 
                //�@@�@@�B�̏ꍇ�F0002�i�p�����[�^�Ó����`�F�b�N�G���[�j 
                //�@@�@@�C�̏ꍇ�F0003�i�p�����[�^��v�`�F�b�N�G���[�j 
                //�@@�@@�D�̏ꍇ�F0004�i��t���ʃR�[�h�`�F�b�N�G���[�j
                String l_strReasonCodeDef = null;
                
                if(!(WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(l_params.getSendRcvDiv()) 
                   || WEB3SendRcvDivDef.SEND_COMPLETE.equals(l_params.getSendRcvDiv()))
                   )
                {
                    l_strReasonCodeDef = WEB3GftErrorReasonCodeDef.SENDRCV_ERROR;
                }
                else if(l_blnGFTTelegramSet == false || l_blnGFTTelegramLengthPropSame == false)
                {
                    l_strReasonCodeDef = WEB3GftErrorReasonCodeDef.PARAM_VALIDITY_ERROR;                    
                }
                else if(l_blnMailSame ==false)
                {
                    l_strReasonCodeDef = WEB3GftErrorReasonCodeDef.PARAM_MISMATCH_ERROR;                    
                    
                }
                else if(!WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000.equals(
                    l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    l_strReasonCodeDef = WEB3GftErrorReasonCodeDef.RESULT_CODE_ERROR;
                }
                
                l_fxDataControlService.updateGFTAccountOpenStatus(
                    l_params,
                    l_request.fxGftResultNoticeTelegramUnit,
                    l_strReasonCodeDef);
            }
            
            //1.7.2) (*)��O��throw
            //(*)��O��throw����B�@@�@@
            //�@@�@@�̏ꍇ�F�u2�d��M�G���[�v
            if(WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(l_params.getSendRcvDiv()))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01972,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "2�d��M�G���[");   
            }
            
            //�@@�A�B�C�̏ꍇ�F�u���̑���FX�V�X�e���G���[�v
            if( (!(WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(l_params.getSendRcvDiv()) 
                    || WEB3SendRcvDivDef.SEND_COMPLETE.equals(l_params.getSendRcvDiv()))
                 )
                || (l_blnGFTTelegramSet == false || l_blnGFTTelegramLengthPropSame == false)
                || (l_blnMailSame == false))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01800,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���̑���FX�V�X�e���G���[");   
            }
            
            //�@@�D�̏ꍇ�FGFT���ʒʒm�d������.��t���ʂɉ����Ĉȉ��̗�O�ƂȂ�
            //�@@�@@�@@�@@�@@�@@�@@�@@00000105(�z�X�g�������ԊO)�̏ꍇ �F�u��t���ԊO�G���[�v
            //�@@�@@�@@�@@�@@�@@�@@�@@00000199(�z�X�g�V�X�e���G���[)�̏ꍇ   �F�u�ʐM�G���[�v
            //�@@�@@�@@�@@�@@�@@�@@�@@00000204(�c���s���G���[)�̏ꍇ  �F�u�c���s���G���[�v
            //�@@�@@�@@�@@�@@�@@�@@�@@00000801(2�d���M�G���[)�̏ꍇ  �F�u2�d���M�G���[�v
            //�@@�@@�@@�@@�@@�@@�@@�@@�ȊO�̎�t���ʂ̏ꍇ        �F�u���̑���FX�V�X�e���G���[�v
            if(!WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000.equals(
                l_request.fxGftResultNoticeTelegramUnit.resultCode))
            {
                if(WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_00000105.equals(
                    l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01801,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "��t���ԊO�G���[");                    
                }
                else if(WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_00000199.equals(
                    l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01802,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�ʐM�G���[");                    
                }
                else if(WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_00000204.equals(
                    l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01803,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�c���s���G���[");                    
                }
                else if(WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_00000801.equals(
                    l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01799,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "2�d���M�G���[");                    
                }
                else
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01800,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���̑���FX�V�X�e���G���[");  
                }
            }
        }
        
        //FX�ڋq�e�[�u���A����сA
        //FX�����ԍ��e�[�u����
        //�f�[�^��insert����B
        
        //1.8) insertFX�ڋq(GFT���ʒʒm�d������)
        l_fxDataControlService.insertFXAccount(
                l_request.fxGftResultNoticeTelegramUnit, l_params);

        //get�����J��FX�V�X�e���R�[�h()�̖߂�l��null�łȂ��ꍇ���{����B
        if (l_strSameTimeFxSystemCode != null)
        {
            //insert���������J��(GFT���ʒʒm�d������, GFT�����J�ݏ�Params, String)
            //GFT���ʒʒm�d�����ׁF GFT���ʒʒm�d�����׃I�u�W�F�N�g
            //GFT�����J�ݏ�Params�F GFT�����J�ݏ󋵍s�I�u�W�F�N�g
            //���������J��FX�V�X�e���R�[�h�F�@@get���������J��FX�V�X�e���R�[�h()�̖߂�l
            l_fxDataControlService.insertSimultaneousAccountOpen(
                l_request.fxGftResultNoticeTelegramUnit,
                l_params,
                l_strSameTimeFxSystemCode);
        }

        //insertFX�����ԍ�(GFT���ʒʒm�d������, String)
        //[����]
        // GFT���ʒʒm�d�����ׁF GFT���ʒʒm�d�����׃I�u�W�F�N�g
        // FX�V�X�e���R�[�h�F�@@GFT�����J�ݏ�Params.FX�V�X�e���R�[�h
        l_fxDataControlService.insertFXAccountCode(
            l_request.fxGftResultNoticeTelegramUnit,
            l_params.getFxSystemCode(),
            l_strSameTimeFxSystemCode);

        //1.10) get�⏕����(SubAccountTypeEnum)
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //1.11.1) get�㗝���͎�( )
        Trader l_trader = this.getTrader();

        // get��Е�FX�V�X�e������()�̖߂�l.�������==01�FFX�̏ꍇ���{����B
        if (WEB3AccTypeDef.FX.equals(l_compFxConditionParams.getAccType()))
        {
            //get��Е�FX�V�X�e������()�̖߂�l.�����敪���A���X�V==�P�F���A���X�V�̏ꍇ
            if (WEB3AccOpenRealUpdateDef.REAL_UPDATE.equals
                (l_compFxConditionParams.getAccOpenRealUpdate()))
            {
                //1.11.3) updateFX�����J�݋敪(String, String, String, String, String)
                //�A�C�e���̒�`
                //�ڋq�}�X�^�[��FX�����J�݋敪���X�V����B
                //[�����̐ݒ�]
                //�،���ЃR�[�h�F�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������.��ЃR�[�h
                //���X�R�[�h�F�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������.���X�R�[�h
                //�ڋq�R�[�h�F�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������.�ڋq�R�[�h
                //�X�V��FX�����J�݋敪�F�@@1(�����J��)
                //�X�V�҃R�[�h�F
                //get�㗝���͎�()��null�̏ꍇ�A�⏕����.getMainAccount().getAccountCode()
                //�@@get�㗝���͎�()!=null�̏ꍇ�Aget�㗝���͎�()�̖߂�l�̈���.getTraderCode()
                if(l_trader == null)
                {
                    l_fxDataControlService.updateFXAccountOpenDiv(
                        l_request.fxGftResultNoticeTelegramUnit.institutionCode,
                        l_request.fxGftResultNoticeTelegramUnit.branchCode,
                        l_request.fxGftResultNoticeTelegramUnit.accountCode,
                        WEB3AccountOpenDef.OPEN,
                        l_subAccount.getMainAccount().getAccountCode());
                }
                else
                {
                    l_fxDataControlService.updateFXAccountOpenDiv(
                        l_request.fxGftResultNoticeTelegramUnit.institutionCode,
                        l_request.fxGftResultNoticeTelegramUnit.branchCode,
                        l_request.fxGftResultNoticeTelegramUnit.accountCode,
                        WEB3AccountOpenDef.OPEN,
                        l_trader.getTraderCode());
                }
            }
            //get��Е�FX�V�X�e������()�̖߂�l.�����敪���A���X�V==�O�F���A���X�V���Ȃ��ꍇ
            else if (WEB3AccOpenRealUpdateDef.NOT_REAL_UPDATE.equals(
                l_compFxConditionParams.getAccOpenRealUpdate()))
            {
                //updateFX�����J�݋敪�X�V�҃R�[�h(String, String, String, String)
                //�A�C�e���̒�`
                //�ڋq�}�X�^�[��FX�����J�݋敪���X�V����B
                //[�����̐ݒ�]
                //�،���ЃR�[�h�F�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������.��ЃR�[�h
                //���X�R�[�h�F�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������.���X�R�[�h
                //�ڋq�R�[�h�F�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������.�ڋq�R�[�h
                //�X�V�҃R�[�h�F
                //�@@get�㗝���͎�()��null�̏ꍇ�A�⏕����.getMainAccount().getAccountCode()
                //�@@get�㗝���͎�()!=null�̏ꍇ�Aget�㗝���͎�()�̖߂�l�̈���.getTraderCode()
                if(l_trader == null)
                {
                    l_fxDataControlService.updateFXAccountOpenDivUpdaterCode(
                        l_request.fxGftResultNoticeTelegramUnit.institutionCode,
                        l_request.fxGftResultNoticeTelegramUnit.branchCode,
                        l_request.fxGftResultNoticeTelegramUnit.accountCode,
                        l_subAccount.getMainAccount().getAccountCode());
                }
                else
                {
                    l_fxDataControlService.updateFXAccountOpenDivUpdaterCode(
                        l_request.fxGftResultNoticeTelegramUnit.institutionCode,
                        l_request.fxGftResultNoticeTelegramUnit.branchCode,
                        l_request.fxGftResultNoticeTelegramUnit.accountCode,
                        l_trader.getTraderCode());
                }
            }
        }

        //�����̓��e�Ō����J�݋敪�e�[�u��(acc_open_div)�ɍs��insert���s���B
        //����ID�F�@@�⏕����.getMainAccount().getAccountId()
        //������ʁF�@@get��Е�FX�V�X�e������()�̖߂�l.�������
        //�����J�݋敪�F
        //�P�jget��Е�FX�V�X�e������()�̖߂�l.�����敪���A���X�V==�P�F���A���X�V�̏ꍇ
        //�@@�@@1:�J�ݍ�
        //�Q�jget��Е�FX�V�X�e������()�̖߂�l.�����敪���A���X�V==�O�F���A���X�V���Ȃ��ꍇ
        //�@@�@@0:���J��
        //�X�V�҃R�[�h�F
        //�@@get�㗝���͎�()��null�̏ꍇ�A�⏕����.getMainAccount().getAccountCode()
        //get�㗝���͎�()!=null�̏ꍇ�Aget�㗝���͎�()�̖߂�l�̈���.getTraderCode()
        WEB3GentradeAccOpenDiv l_genAccOpenDiv = new WEB3GentradeAccOpenDiv();
        long l_lonAccountId = l_subAccount.getMainAccount().getAccountId();
        String l_strAccType = l_compFxConditionParams.getAccType();
        String l_strAccOpenDiv = "";
        String l_strLastUpdater = "";
        if (WEB3AccOpenRealUpdateDef.REAL_UPDATE.equals(
            l_compFxConditionParams.getAccOpenRealUpdate()))
        {
        	l_strAccOpenDiv = WEB3AccountOpenDef.OPEN;
        }
        else if (WEB3AccOpenRealUpdateDef.NOT_REAL_UPDATE.equals(
            l_compFxConditionParams.getAccOpenRealUpdate()))
        {
        	l_strAccOpenDiv = WEB3AccountOpenDef.NOT_OPEN;
        }
        if (l_trader == null)
        {
        	l_strLastUpdater = l_subAccount.getMainAccount().getAccountCode();
        }
        else
        {
        	l_strLastUpdater = l_trader.getTraderCode();
        }
        l_genAccOpenDiv.insertAccOpenDiv(l_lonAccountId,
            l_strAccType,
            l_strAccOpenDiv,
            l_strLastUpdater);

        //get�����J��FX�V�X�e���R�[�h()�̖߂�l��null�łȂ��ꍇ
        //�����̓��e�Ō����J�݋敪�e�[�u��(acc_open_div)�ɍs��insert���s���B
        if (l_strSameTimeFxSystemCode != null)
        {
        	//[�����̐ݒ�]
        	//����ID�F�@@�⏕����.getMainAccount().getAccountId()
        	//������ʁF�@@ 02:CFD
        	//�����J�݋敪�F
        	//�P�jget��Е�FX�V�X�e������()�̖߂�l.�����敪���A���X�V==�P�F���A���X�V�̏ꍇ
        	//1:�J�ݍ� 
        	//�Q�jget��Е�FX�V�X�e������()�̖߂�l.�����敪���A���X�V==�O�F���A���X�V���Ȃ��ꍇ
        	//0:���J��
        	//�X�V�҃R�[�h�F
        	//�@@get�㗝���͎�()��null�̏ꍇ�A�⏕����.getMainAccount().getAccountCode()
        	//get�㗝���͎�()!=null�̏ꍇ�Aget�㗝���͎�()�̖߂�l�̈���.getTraderCode()
            l_genAccOpenDiv.insertAccOpenDiv(l_lonAccountId,
                WEB3AccTypeDef.CFD,
                l_strAccOpenDiv,
                l_strLastUpdater);
        }

        //1.12) updateGFT�����J�ݏ�(GFT�����J�ݏ�Params, GFT���ʒʒm�d������, String)
        //�A�C�e���̒�`
        //GFT�����J�ݏ󋵂̍X�V���s���B 
        //[�����̐ݒ�] 
        //�@@GFT�����J�ݏ�Params�F�@@getGFT�����J�ݏ�()�̖߂�l 
        //�@@GFT���ʒʒm�d�����ׁF�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������ 
        //�@@�G���[���R�R�[�h�F�@@0000�i����j
        l_fxDataControlService.updateGFTAccountOpenStatus(
            l_params,
            l_request.fxGftResultNoticeTelegramUnit,
            WEB3GftErrorReasonCodeDef.NORMAL);
        
        //1.14) createResponse( )
        WEB3FXAccOpenCompleteResponse l_response = 
            (WEB3FXAccOpenCompleteResponse)l_request.createResponse();
        
        //1.15) (*)�v���p�e�B�Z�b�g
        //���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        //�ב֕ۏ؋����O�C��ID�F�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������.�������O�C��ID
        l_response.fxLoginId = l_request.fxGftResultNoticeTelegramUnit.fxFirstLoginId;
        
        //�ב֕ۏ؋��������ꗗ�F�@@GFT���ʒʒm�d������.�ב֕ۏ؋��������ꗗ
        l_response.fxAccInformationList =
            l_request.fxGftResultNoticeTelegramUnit.fxAccInformationList;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�����J�݁iSOAP�ڑ��j)<BR>
     * �����J�݂̊����������s���B<BR>
     * ��SOAP�ڑ��ɂčs���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�iFX�����J�݁jsubmit�����J�݁iSOAP�ڑ��j�v�Q�ƁB <BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3FXAccOpenCompleteSoapResponse
     * @@throws WEB3BaseException
     * @@roseuid 41C78E8E02E2
     */
    protected WEB3FXAccOpenCompleteSoapResponse submitAccountOpenSoap(
        WEB3FXAccOpenCompleteSoapRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitAccountOpenSoap(WEB3FXAccOpenCompleteSoapRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3FXDataControlService l_fxDataControlService =
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);

        //validate( )
        l_request.validate();

        //get��Е�FX�V�X�e������(String, String, String)
        //�،���ЃR�[�h�F get����.getInstitution().getInstitutionCode()
        //���X�R�[�h�F�@@get����.getBranch().getBranchCode()
        //FX�V�X�e���R�[�h�F�@@��Е�FX�V�X�e������Params.FX�V�X�e���R�[�h
        String l_strInstitutionCodeByMainAccount =
            this.getMainAccount().getInstitution().getInstitutionCode();
        String l_strBranchCodeByMainAccount =
            this.getMainAccount().getBranch().getBranchCode();
        CompFxConditionParams l_compFxConditionParams = null;
        try
        {
            l_compFxConditionParams =
                l_fxDataControlService.getCompFxCondition(
                    l_strInstitutionCodeByMainAccount,
                    l_strBranchCodeByMainAccount,
                    l_request.fxSystemCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�����J�݈˗��������s���B
        //[����]
        //���N�G�X�g�f�[�^�F ����.���N�G�X�g�f�[�^��FX�����J�݈˗����N�G�X�g�ɃL���X�g��������
        WEB3FXAccOpenAskingRequest l_fxAccOpenAskingRequest = new WEB3FXAccOpenAskingRequest();
        l_fxAccOpenAskingRequest.agreementDiv = l_request.agreementDiv;
        l_fxAccOpenAskingRequest.complianceInfo = l_request.complianceInfo;
        l_fxAccOpenAskingRequest.fxMailAddress = l_request.fxMailAddress;
        l_fxAccOpenAskingRequest.fxPassword = l_request.fxPassword;
        l_fxAccOpenAskingRequest.fxTradeAgreementList = l_request.fxTradeAgreementList;
        l_fxAccOpenAskingRequest.password = l_request.password;
        l_fxAccOpenAskingRequest.regetServiceId = l_request.regetServiceId;
        l_fxAccOpenAskingRequest.wolfAid = l_request.wolfAid;
        l_fxAccOpenAskingRequest.wolfSession = l_request.wolfSession;
        l_fxAccOpenAskingRequest.wolfSsid = l_request.wolfSsid;
        l_fxAccOpenAskingRequest.fxSystemCode = l_request.fxSystemCode;
        WEB3FXAccOpenAskingResponse l_fxAccOpenAskingResponse = this.startAccountOpen(l_fxAccOpenAskingRequest);
        //start�����J�݂̖߂�FX�����J�݈˗����X�|���X.GFT�˗��d�����ׂɃv���p�e�B���Z�b�g����B
        //
        //FX�����J�݈˗����X�|���X.GFT�˗��d������.FX�Ïؔԍ��Q = ���N�G�X�g�f�[�^.FX�Ïؔԍ��Q
        //�i���N�G�X�g�f�[�^.FX�Ïؔԍ��Q = null�̏ꍇ�Anull���Z�b�g����j
        if (l_request.fxPassword2 != null)
        {
            l_fxAccOpenAskingResponse.fxGftAskingTelegramUnit.fxPassword2 = l_request.fxPassword2;
        }
        else
        {
            l_fxAccOpenAskingResponse.fxGftAskingTelegramUnit.fxPassword2 = null;
        }

        //do�����J�ݎ��s
        WEB3FXAccOpenConnection l_controlService =
            (WEB3FXAccOpenConnection)Services.getService(WEB3FXAccOpenConnection.class);
        WEB3FXGftResultNoticeTelegramUnit  l_fxGftResultNoticeTelegramUnit =
            l_controlService.doAccountOpen(l_compFxConditionParams, l_fxAccOpenAskingResponse.fxGftAskingTelegramUnit);

        //�R���X�g���N�^�ɂăC���X�^���X��������B
        //FX�����J�݊������N�G�X�g
        WEB3FXAccOpenCompleteRequest l_fxAccOpenCompleteRequest = new WEB3FXAccOpenCompleteRequest();

        //FX�����J�݊������N�G�X�g�̃v���p�e�B���Z�b�g����B
        //GFT���ʒʒm�d�����ׁF �����������ʒʒm�d������
        l_fxAccOpenCompleteRequest.fxGftResultNoticeTelegramUnit = l_fxGftResultNoticeTelegramUnit;

        //submit�����J��(FX�����J�݊������N�G�X�g)
        //�����J�݊����������s���B
        //[����]
        //���N�G�X�g�f�[�^�F ��������FX�����J�݊������N�G�X�g
        WEB3FXAccOpenCompleteResponse l_fxAccOpenCompleteResponse = this.submitAccountOpen(l_fxAccOpenCompleteRequest);

        //���X�|���X�f�[�^�𐶐�����B
        WEB3FXAccOpenCompleteSoapResponse l_response = (WEB3FXAccOpenCompleteSoapResponse)l_request.createResponse();

        //�ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        //�ב֕ۏ؋����O�C��ID�F FX�����J�݊������X�|���X.�ב֕ۏ؋����O�C��ID
        //�ב֕ۏ؋��������ꗗ�F FX�����J�݊������X�|���X.�ב֕ۏ؋��������ꗗ
        l_response.fxLoginId = l_fxAccOpenCompleteResponse.fxLoginId;
        l_response.fxAccInformationList = l_fxAccOpenCompleteResponse.fxAccInformationList;

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (getGFT�����敪)<BR>
     * �����敪�ɃZ�b�g����l��ݒ肷��B<BR>
     * <BR>
     * �P�jFX�V�X�e���R�[�h�ꗗ�擾<BR>
     * �@@FX�f�[�^����T�[�r�X.getGFTFX�V�X�e���R�[�h�ꗗ()���R�[������B<BR>
     * <BR>
     * �@@[�����̐ݒ�]<BR>
     * �@@�،���ЃR�[�h�F�@@�⏕����.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h�F�@@�⏕����.get����X.getBranchCode()<BR>
     * <BR>
     * �Q�j<BR>
     * �@@FX�f�[�^����T�[�r�X.isGFT�����J��()���R�[������B<BR>
     * �@@�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h�F �⏕����.get����X.getBranchCode()<BR>
     * �@@�ڋq�R�[�h�F �⏕����.getMainAccount().getAccountCode()<BR>
     * �@@FX�V�X�e���R�[�h�ꗗ�F�@@getGFTFX�V�X�e���R�[�h�ꗗ()�̖߂�l<BR>
     * <BR>
     * �@@�Q�|�P�jisGFT�����J��()�̖߂�l ==false�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�V�K�J�݁F01��ԋp����B<BR>
     * <BR>
     * �@@�Q�|�Q�jisGFT�����J��()�̖߂�l ==true�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�ǉ��J�݁F03��ԋp����B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getGFTOperationDiv(SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getGFTOperationDiv(SubAccount)";
        log.entering(STR_METHOD_NAME);

        //�P�jFX�V�X�e���R�[�h�ꗗ�擾
        //FX�f�[�^����T�[�r�X.getGFTFX�V�X�e���R�[�h�ꗗ()���R�[������B
        //[�����̐ݒ�]
        //�،���ЃR�[�h�F�@@�⏕����.�،���ЃR�[�h
        //���X�R�[�h�F�@@�⏕����.get����X.getBranchCode()
        WEB3FXDataControlService l_controlService =
            (WEB3FXDataControlService) Services.getService(
                WEB3FXDataControlService.class);
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        ArrayList l_lisGFTFxSystemCodeList = null;
        l_lisGFTFxSystemCodeList =
            l_controlService.getGFTFxSystemCodeLists(l_strInstitutionCode, l_strBranchCode);

        //FX�f�[�^����T�[�r�X.isGFT�����J��()���R�[������B
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h
        //���X�R�[�h�F �⏕����.get����X.getBranchCode()
        //�ڋq�R�[�h�F �⏕����.getMainAccount().getAccountCode()
        //FX�V�X�e���R�[�h�ꗗ�F�@@getGFTFX�V�X�e���R�[�h�ꗗ()�̖߂�l
        boolean l_blnisGFTAccOpen = false;
        String l_strAccountCode = l_subAccount.getMainAccount().getAccountCode();
        l_blnisGFTAccOpen = l_controlService.isGFTAccOpen(
            l_strInstitutionCode,
            l_strBranchCode,
            l_strAccountCode,
            l_lisGFTFxSystemCodeList);

        //isGFT�����J��()�̖߂�l ==false�̏ꍇ
        //�V�K�J�݁F01��ԋp����B
        //isGFT�����J��()�̖߂�l ==true�̏ꍇ
        //�ǉ��J�݁F03��ԋp����B
        if (!l_blnisGFTAccOpen)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3AdminAioGftOperationDivDef.ACCOUNT_OPEN;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3AdminAioGftOperationDivDef.ADD_ACCOUNT;
        }
    }
}@
