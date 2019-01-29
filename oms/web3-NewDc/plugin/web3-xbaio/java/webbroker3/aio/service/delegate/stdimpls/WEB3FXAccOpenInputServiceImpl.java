head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.29.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXAccOpenInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�����J�ݓ��̓T�[�r�XImpl(WEB3FXAccOpenInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/26 ���E (���u) �V�K�쐬
Revesion History : 2008/05/20 �đo�g (���u) �d�l�ύX ���f��No.850�ANo.853�ANo.876�ANo.882
Revesion History : 2008/09/22 ���g (���u) �d�l�ύX�E���f��1003
Revesion History : 2009/03/18 �Ԑi (���u) �d�l�ύX�E���f��1122�A1125�A1126
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3FXDataControlService;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.define.WEB3AioFxSystemCodeDivDef;
import webbroker3.aio.message.WEB3FXAccOpenInputRequest;
import webbroker3.aio.message.WEB3FXAccOpenInputResponse;
import webbroker3.aio.message.WEB3FXAccOpenTradeAgreementRequest;
import webbroker3.aio.message.WEB3FXAccOpenTradeAgreementResponse;
import webbroker3.aio.message.WEB3FXTradeAgreementUnit;
import webbroker3.aio.service.delegate.WEB3FXAccOpenInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3ExtConnectSystemCodeDef;
import webbroker3.common.define.WEB3QuestionCheckDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.QuestionParams;
import webbroker3.util.WEB3LogUtility;

/**
 * (FX�����J�ݓ��̓T�[�r�XImpl) <BR>
 * FX�����J�ݓ��̓T�[�r�X�����N���X <BR>
 * @@author ���E(���u)
 * @@version 1.0 
 */
public class WEB3FXAccOpenInputServiceImpl extends WEB3ClientRequestService
    implements WEB3FXAccOpenInputService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXAccOpenInputServiceImpl.class);  
    
    /**
     * @@roseuid 41E783E203B9
     */
    public WEB3FXAccOpenInputServiceImpl()
    {
    }

    /**
     * FX�����J�݃T�[�r�X�������s���B <BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̂����ꂩ�̃��\�b�h���R�[������B <BR>
     * �Eget������Ӊ��() <BR>
     * �Eget���͉��() <BR>
     * 
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41C6540E024B
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
        if (l_request instanceof WEB3FXAccOpenTradeAgreementRequest)
        {
            l_response =
                this.getTradeAgreementScreen((WEB3FXAccOpenTradeAgreementRequest) l_request);
        }
        else if (l_request instanceof WEB3FXAccOpenInputRequest)
        {
            l_response =
                this.getInputScreen((WEB3FXAccOpenInputRequest) l_request);
        }
        else
        {
            log.debug(
                    "���N�G�X�g�f�[�^��"
                    + " WEB3FXAccOpenTradeAgreementRequest "
                    + " �� WEB3FXAccOpenInputRequestt�ȊO�ł���, but is "
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
     * (get������Ӊ��) <BR>
     * FX�����J�ݎ�����Ӊ�ʕ\���f�[�^�̎擾�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�iFX�����J�ݓ��́jget������Ӊ�ʁv�Q�ƁB <BR>
     * <BR>
     * ======================================================== <BR>
     * �V�[�P���X�}(�u(�ב֕ۏ؋��T�[�r�X���f��) / FX�����J�ݓ��� �v<BR>
     * �iFX�����J�ݓ��́jget������Ӊ�� )<BR>
     * : 1.6 get����(String, String) <BR>
     * �߂�l��null�̏ꍇ�A��O��thorw����B <BR>
     * <BR>
     * class: WEB3SystemLayerException <BR>
     * tag: SYSTEM_ERROR_80005 <BR>
     * <BR>
     * ========================================================== 
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3FXAccOpenTradeAgreementResponse
     * @@throws WEB3BaseException
     * @@roseuid 41C654E001FD
     */
    protected WEB3FXAccOpenTradeAgreementResponse getTradeAgreementScreen(
        WEB3FXAccOpenTradeAgreementRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getTradeAgreementScreen(WEB3FXAccOpenTradeAgreementRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1) get�⏕����(SubAccountTypeEnum)
        //�A�C�e���̒�`
        //�⏕�����I�u�W�F�N�g���擾����B 
        //[����] 
        //�⏕�����^�C�v�F 1�i������������i�a����j�j
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.2) validate����(SubAccount)
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
        
        //1.3) get��Е�FX�V�X�e������(String, String, String)
        //�A�C�e���̒�`
        //��Е�FX�V�X�e������Params���擾����B 
        //[�����̐ݒ�] 
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
        //���X�R�[�h�F�@@�⏕����.get����X.getBranchCode()
        //FX�V�X�e���R�[�h�F�@@���N�G�X�g�f�[�^.FX�V�X�e���R�[�h
        WEB3FXDataControlService l_fxDataControlService = 
            (WEB3FXDataControlService) Services.getService(WEB3FXDataControlService.class);
        
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
            log.debug("__NotFoundExcepiton__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);        
        }

        //1.4) validate�O���V�X�e����t�\(String)
        //�A�C�e���̒�`
        //FX�V�X�e���̎�t���ԃ`�F�b�N���s���B
        //[�����̐ݒ�] 
        //�V�X�e���R�[�h�F�@@��Е�FX�V�X�e������Params.FX�V�X�e���R�[�h
        String l_strFxSystemCode = l_compFxConditionParams.getFxSystemCode();
        if (!WEB3AioFxSystemCodeDivDef.TFX.equals(l_strFxSystemCode))
        {
            l_aioOrderManager.validateOtherSystemAcceptPossible(l_strFxSystemCode);
        }
        
        //1.5) validateFX�����J�݉\(SubAccount, String)
        //�A�C�e���̒�`
        //FX�����J�݂��\�ł��邩���`�F�b�N����B 
        //[�����̐ݒ�] 
        //�⏕�����F�@@get�⏕����()�̖߂�l 
        //��Е�FX�V�X�e������Params�F�@@get��Е�FX�V�X�e������()�̖߂�l
        l_aioOrderManager.validateFXAccOpenPossible(l_subAccount, l_compFxConditionParams);
        
        //1.6) validate�N����l(SubAccount, String, long)
        //�ڋq�̔N��A��ЁA���X���w�肷��N����l�� 
        //�����邩�̃`�F�b�N���s���B 
        //[����] 
        //�⏕�����F�@@get�⏕����()�̖߂�l 
        //�v���t�@@�����X���F�@@fxaccountopen.lowlimit.age 
        //�v���t�@@�����X���̘A�ԁF1
        WEB3GentradeBranch l_branch = (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch();
        l_branch.validateAgeLowLimit(
            (WEB3GentradeSubAccount)l_subAccount, 
            WEB3BranchPreferencesNameDef.LOWLIMIT_AGE, 
            1);
        
        //1.7) validate�N�����l(SubAccount, String, long)
        //�ڋq�̔N��A��ЁA���X���w�肷��N�����l�� 
        //�����邩�̃`�F�b�N���s���B 
        //[����] 
        //�⏕�����F�@@get�⏕����()�̖߂�l 
        //�v���t�@@�����X���F�@@fxaccountopen.highlimit.age 
        //�v���t�@@�����X���̘A�ԁF�@@1 
        l_branch.validateAgeHighLimit(
            (WEB3GentradeSubAccount)l_subAccount, 
            WEB3BranchPreferencesNameDef.HIGHLIMIT_AGE, 
            1);
        
        //1.8) get����(String, String, String)
        //�A�C�e���̒�`
        //����Params���擾����B
        //[�����̐ݒ�] 
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
        //���X�R�[�h�F�@@�⏕����.get����X.getBranchCode()
        //FX�V�X�e���R�[�h�F�@@���N�G�X�g�f�[�^.FX�V�X�e���R�[�h
        QuestionParams[] l_questionParams = 
            l_fxDataControlService.getQuestions(
                l_strInstitutionCode,
                l_strBranchCode,
                l_request.fxSystemCode);
        
        //�߂�l��null�̏ꍇ�A��O��thorw����B
        if(l_questionParams == null)
        {
            log.debug("������e�擾�G���[");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "������e�擾�G���[");   
        }
        
        List l_lisQuestionParams = new Vector();
        
        //1.9) get����()�̖߂�l�̎���Params�v�f�����Ƃ�Loop����
        for(int i = 0; i < l_questionParams.length; i++)
        {
            //1.9.1) FX������ӎ�����( )
            //�A�C�e���̒�`
            //FX������ӎ�����I�u�W�F�N�g�𐶐�����B
            WEB3FXTradeAgreementUnit l_fXTradeAgreementUnit = new WEB3FXTradeAgreementUnit();
            
            //FX������ӎ�����𐶐����A�v���p�e�B�Z�b�g���s���B
            //�@@����ԍ��F�@@����Params.����ԍ�
            //�@@������e�F�@@����Params.������e
            //�@@������񓚂̐ݒ�͍s
            QuestionParams l_questionParam = l_questionParams[i];
            l_fXTradeAgreementUnit.questionNumber = l_questionParam.getQuestionNo();
            l_fXTradeAgreementUnit.questionContents = l_questionParam.getQuestion();
            
            l_lisQuestionParams.add(l_fXTradeAgreementUnit);
        }
        
        //1.10) createResponse( )
        WEB3FXAccOpenTradeAgreementResponse l_response = 
            (WEB3FXAccOpenTradeAgreementResponse)l_request.createResponse();
        
        //1.11) (*)�v���p�e�B�Z�b�g
        //���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        //�ڋq���F�@@�ڋq(*1).���O�i�c���j
        //������ӎ�����ꗗ�F�@@�i*��L�ŕҏW����FX������ӎ�����̔z��j
        //(*1)�⏕����.getMainAccount()�ɂĎ擾

        MainAccountParams l_mainAccountParams = 
            (MainAccountParams)l_subAccount.getMainAccount().getDataSourceObject();
        
        WEB3FXTradeAgreementUnit[] l_fXTradeAgreementUnitToResponse = 
            new WEB3FXTradeAgreementUnit[l_lisQuestionParams.size()];
        l_lisQuestionParams.toArray(l_fXTradeAgreementUnitToResponse);

        l_response.accountName = l_mainAccountParams.getFamilyName();
        
        l_response.fxTradeAgreementList = l_fXTradeAgreementUnitToResponse;
        
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get���͉��) <BR>
     * FX�����J�ݓ��͉�ʕ\���f�[�^�̎擾�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�iFX�����J�ݓ��́jget���͉�ʁv�Q�ƁB <BR>
     * 
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3FXAccOpenInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 41C655740316
     */
    protected WEB3FXAccOpenInputResponse getInputScreen(
        WEB3FXAccOpenInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getInputScreen(WEB3FXAccOpenInputRequest l_request)";
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
        
        //1.8)  �i���j�d�q���`�F�b�N���s���B
        //�i���N�G�X�g.�d�q���`�F�b�N�t���O==true�̏ꍇ�j        
        String[] l_strRequestCodes = null;
            
        if (l_request.batoCheckFlag)
        {
            //1.8.1) validateFX�h�L�������g�{������(String, String[])
            //FX�h�L�������g���{���ς݂��̊m�F���s���B 
            //[�����̐ݒ�] 
            //��ʃR�[�h�F���N�G�X�g.��ʃR�[�h 
            //���ʃR�[�h�F���N�G�X�g.���ʃR�[�h[]
            l_strRequestCodes = 
                l_fxDataControlService.validateFxDocReadHistory(
                    l_request.typeCode,
                    l_request.requestCode);

            //1.8.2)�i���jvalidateFX�h�L�������g�{������( )�̖߂�l�z��null�̏ꍇ
            if (l_strRequestCodes == null)
            {
                //1.8.2.1 insert�����s�v��������(String, String, String)
                //FX�����s�v���������Ǘ��e�[�u���ɍs��insert���s���B 
                //[�����̐ݒ�] 
                //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
                //���X�R�[�h�F �⏕����.get����X.getBranchCode() 
                //�ڋq�R�[�h�F �⏕����.getMainAccount().getAccountCode()
                l_fxDataControlService.insertUnnecessaryExplanation(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_subAccount.getMainAccount().getAccountCode());
            }
        }
        else
        {
            //1.9 �i���j���N�G�X�g.�d�q���`�F�b�N�t���O==false�̏ꍇ
            //insert�����s�v��������(String, String, String)
            //FX�����s�v���������Ǘ��e�[�u���ɍs��insert���s���B 
            //[�����̐ݒ�] 
            //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
            //���X�R�[�h�F �⏕����.get����X.getBranchCode() 
            //�ڋq�R�[�h�F �⏕����.getMainAccount().getAccountCode()
            l_fxDataControlService.insertUnnecessaryExplanation(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_subAccount.getMainAccount().getAccountCode());   
        }

        String l_strFxMailAddress = null;
        boolean l_blnIsGFTAccOpen = false;
        //get��Е�FX�V�X�e������()�̖߂�l.�O���ڑ��V�X�e���R�[�h==01�FGFT�̏ꍇ
        if (WEB3ExtConnectSystemCodeDef.GFT.equals(
            l_compFxConditionParams.getExtConnectSystemCode()))
        {
            //getGFTFX�V�X�e���R�[�h�ꗗ(String, String)
            //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h
            //���X�R�[�h�F �⏕����.get����X.getBranchCode()
            ArrayList l_arrayListGFXFxSystemCodeLists =
                l_fxDataControlService.getGFTFxSystemCodeLists(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode());

            // getGFTFX���[���A�h���X(SubAccount,ArreyList)
            //�⏕�����F�@@get�⏕����()�̖߂�l
            //FX�V�X�e���R�[�h�ꗗ�F�@@getGFTFX�V�X�e���R�[�h�ꗗ()�̖߂�l
            l_strFxMailAddress = l_fxDataControlService.getGFTFxMailAddress(
                l_subAccount,
                l_arrayListGFXFxSystemCodeLists);

            //isGFT�����J��(Stinrg, String, String, ArreyList)
            //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h
            //���X�R�[�h�F �⏕����.get����X.getBranchCode()
            //�ڋq�R�[�h�F �⏕����.getMainAccount().getAccountCode()
            //FX�V�X�e���R�[�h�ꗗ�F�@@getFX�V�X�e���R�[�h�ꗗ()�̖߂�l
            l_blnIsGFTAccOpen = l_fxDataControlService.isGFTAccOpen(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_subAccount.getMainAccount().getAccountCode(),
                l_arrayListGFXFxSystemCodeLists);
        }
        //get��Е�FX�V�X�e������()�̖߂�l.�O���ڑ��V�X�e���R�[�h==01�FGFT�ȊO�̏ꍇ
        else
        {
            // getFX���[���A�h���X(SubAccount,ArreyList)
            //�⏕�����F�@@get�⏕����()�̖߂�l
            l_strFxMailAddress = l_fxDataControlService.getFxMailAddress(
                l_subAccount);
        }

        //1.10) createResponse( )
        WEB3FXAccOpenInputResponse l_response = 
            (WEB3FXAccOpenInputResponse)l_request.createResponse();
        
        //1.11) (*)�v���p�e�B�Z�b�g
        //get��Е�FX�V�X�e������()�̖߂�l.�O���ڑ��V�X�e���R�[�h==01�FGFT�̏ꍇ
        //�@@���[���A�h���X�F�@@getGFTFX���[���A�h���X()�̖߂�l
        //get��Е�FX�V�X�e������()�̖߂�l.�O���ڑ��V�X�e���R�[�h==01�FGFT�ȊO�̏ꍇ
        //�@@���[���A�h���X�F�@@getFX���[���A�h���X()�̖߂�l
        l_response.mailAddress = l_strFxMailAddress;

        //���ʃR�[�h�FvalidateFX�h�L�������g�{������( )�̖߂�l�z��inull�̏ꍇ�A�Z�b�g���Ȃ��j�i*1�j
        //(*1)���N�G�X�g.�d�q���`�F�b�N�t���O==false�̏ꍇ�́A�Z�b�g���Ȃ��B
        if (l_request.batoCheckFlag && l_strRequestCodes != null)
        {
            l_response.requestCode = l_strRequestCodes;
        }

        //get��Е�FX�V�X�e������()�̖߂�l.�O���ڑ��V�X�e���R�[�h==01�FGFT�̏ꍇ
        //GFT�����J�݃t���O�F�@@isGFT�����J�݁i�j�̖߂�l
        //get��Е�FX�V�X�e������()�̖߂�l.�O���ڑ��V�X�e���R�[�h==01�FGFT�ȊO�̏ꍇ
        //false
        l_response.gftAccOpenFlag = l_blnIsGFTAccOpen;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}@
