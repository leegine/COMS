head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.32.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransFromFXInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : FX����U�֓��̓T�[�r�XImpl(WEB3FXTransFromFXInputServiceImpl)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/24 ����(���u) �V�K�쐬
                  : 2006/04/25 �юu�� (���u) �d�l�ύX�E���f��535
                  : 2006/05/12 �s�p (���u) �d�l�ύX�E���f��571
                  : 2006/07/12 ������ (���u) �d�l�ύX�E���f��No.595,No.599
 Revesion History : 2008/09/23 �g�C�� (���u) �d�l�ύX�E���f��No.994                 
 Revesion History : 2009/03/12 ���u�� (���u) �d�l�ύX�E���f��No.1110
 Revesion History : 2009/06/26 ���g (���u) �d�l�ύX�E���f��1175
 */

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3FXDataControlService;
import webbroker3.aio.WEB3FXTransferAbleAmtDisplayService;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.message.WEB3FXAccInformationUnit;
import webbroker3.aio.message.WEB3FXTransFromFXInputRequest;
import webbroker3.aio.message.WEB3FXTransFromFXInputResponse;
import webbroker3.aio.message.WEB3FXTransferAbleAmtUnit;
import webbroker3.aio.service.delegate.WEB3FXTransFromFXInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3GetTransferableAmtDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (FX����U�֓��̓T�[�r�XImpl) <BR>
 * FX����U�֓��̓T�[�r�X�����N���X <BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FXTransFromFXInputServiceImpl extends WEB3ClientRequestService
    implements WEB3FXTransFromFXInputService
{
    /**
     * @@roseuid 41E776E803D8
     */
    public WEB3FXTransFromFXInputServiceImpl()
    {
    }

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FXTransFromFXInputServiceImpl.class);
            
    /**
     * FX����U�֓��̓T�[�r�X�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�iFX����U�֓��́j���͉�ʕ\���f�[�^�擾�v �Q�ƁB <BR>
     * <BR>
     * ======================================================== <BR>
     * �V�[�P���X�}(�u(�ב֕ۏ؋��T�[�r�X���f��) / FX����U�֓��� �v<BR>
     * �iFX����U�֓��́j���͉�ʕ\���f�[�^�擾) <BR>
     * : 1.9 createFX�������ꗗ(String, String, String) <BR>
     * �߂�l��null�̏ꍇ�A��O��throw����B <BR>
     * <BR>
     * class: WEB3SystemLayerException <BR>
     * tag: SYSTEM_ERROR_80005 <BR>
     * <BR>
     * ==========================================================
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BCFA6C0095
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1�j get�⏕����(SubAccountTypeEnum)
        // �⏕�����^�C�v�F 1�i������������i�a����j�j
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.2�j  validate����(SubAccount)
        //  �ȉ��̃`�F�b�N���s���B 
        //�@@�|��t���ԃ`�F�b�N 
        //�@@�|�V�X�e����~���`�F�b�N 
        //�@@�|�ڋq�̃`�F�b�N�i�x�q�A�Ǘ����b�N���j
        //============================FinApp==============================
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        //���o�������}�l�[�W���N���X���擾����B
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager) l_finApp.getTradingModule(ProductTypeEnum.AIO).getOrderManager();
        //�`�F�b�N���s��
        l_aioOrderManager.validateOrder(l_subAccount);
        
        //1.3�jget��Е�FX�V�X�e������(String, String, String)
        //  ��Е�FX�V�X�e������Params���擾����B
        //  [�����̐ݒ�] 
        //  �،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
        //  ���X�R�[�h�F�@@�⏕����.get����X.getBranchCode()
        //  FX�V�X�e���R�[�h�F�@@���N�G�X�g�f�[�^.FX�V�X�e���R�[�h
        WEB3FXDataControlService l_fXDataControlService =
            (WEB3FXDataControlService) Services.getService(WEB3FXDataControlService.class);
        String l_strInstitutionCode = 
            l_subAccount.getInstitution().getInstitutionCode();
        String l_strBranchCode = 
            l_subAccount.getMainAccount().getBranch().getBranchCode();
        WEB3FXTransFromFXInputRequest l_WEB3FXTransFromFXInputRequest =
            (WEB3FXTransFromFXInputRequest)l_request;
        String l_strFxSystemCode = l_WEB3FXTransFromFXInputRequest.fxSystemCode;
        CompFxConditionParams l_compFxConditionParams = null;
        try
        {
            l_compFxConditionParams =
                l_fXDataControlService.getCompFxCondition(
                    l_strInstitutionCode, 
                    l_strBranchCode,
                    l_strFxSystemCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.4�jvalidate�O���V�X�e����t�\(String)
        //  FX�V�X�e���̎�t���ԃ`�F�b�N���s���B 
        //  [�����̐ݒ�] 
        //  �V�X�e���R�[�h�F�@@��Е�FX�V�X�e������Params.FX�V�X�e���R�[�h
        l_aioOrderManager.validateOtherSystemAcceptPossible(
            l_compFxConditionParams.getFxSystemCode());

        //1.5) FX�V�X�e���敪�ʂɁA����\���`�F�b�N���s���B
        //[�����̐ݒ�]
        //�⏕�����F�@@get�⏕����()�̖߂�l
        //��Е�FX�V�X�e������Params�F�@@get��Еʂe�w�V�X�e������()�̖߂�l
        l_fXDataControlService.validateChangePoss(
            l_subAccount,
            l_compFxConditionParams);

        //1.6�j   get������()
        Date l_datOrderBizDate = 
            WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //1.7�jget�U�։�(SubAccount, Date, OrderCategEnum)  
        //�����̐U�։񐔂��擾����B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�������F get������()�̖߂�l 
        //�����J�e�S���F�@@15�i�ב֕ۏ؋��U�ցj   
        int l_intTransferCount = 
            l_aioOrderManager.getTransferCount(
                l_subAccount, 
                l_datOrderBizDate, 
                OrderCategEnum.FX);
        
        //1.8�jvalidate�U�։\��(SubAccount, Date, OrderCategEnum)
        //  �U�։\�񐔂̃`�F�b�N���s���B 
        //  [����] 
        //  �⏕�����F get�⏕����()�̖߂�l 
        //  �������F get������()�̖߂�l 
        //  �����J�e�S���F�@@15�i�ב֕ۏ؋��U�ցj
        int l_intTransferPossibleCount =
            l_aioOrderManager.validateTransferPossibleCount(
                l_subAccount, 
                l_datOrderBizDate, 
                OrderCategEnum.FX);
        
        //1.9�j createFX�������ꗗ(String, String, String)
        //FX�������̈ꗗ���擾����B 
        //[�����̐ݒ�] 
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
        //���X�R�[�h�F�@@�⏕����.get����X.getBranchCode() 
        //�ڋq�R�[�h�F�@@�⏕����.getMainAccount().getAccountCode()
        //FX�V�X�e���R�[�h�F�@@���N�G�X�g�f�[�^.FX�V�X�e���R�[�h
        WEB3FXAccInformationUnit[] l_fXAccInformationUnit =
            l_fXDataControlService.createFXAccInformationUnits(
                l_strInstitutionCode,
                l_strBranchCode,
                l_subAccount.getMainAccount().getAccountCode(),
                l_WEB3FXTransFromFXInputRequest.fxSystemCode);
        if (l_fXAccInformationUnit == null)
        {
            log.debug("FX�������擾�G���[�B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "FX�������擾�G���[�B");
        }

        WEB3FXTransferAbleAmtUnit[] l_transferAbleAmtUnits = null;
        //��Е�FX�V�X�e������.FX����̐U�։\�z�擾�敪 = �P�F�擾����̏ꍇ
        if (WEB3GetTransferableAmtDivDef.GET.equals(l_compFxConditionParams.getGetTransferableAmtDiv()))
        {
            //getFX����U�։\�z�i�`�F�b�N�Ȃ��j(�⏕���� : SubAccount,
            //��Е�FX�V�X�e������ : CompFxConditionParams)
            WEB3FXTransferAbleAmtDisplayService l_transferAbleAmtDisplayService =
                (WEB3FXTransferAbleAmtDisplayService)Services.getService(
                    WEB3FXTransferAbleAmtDisplayService.class);
            //[����]
            //�⏕�����F�擾�����⏕����
            //��Е�FX�V�X�e�������F�擾������Е�FX�V�X�e������
            l_transferAbleAmtUnits =
                l_transferAbleAmtDisplayService.getFXTransferAbleAmtNoCheck(
                    l_subAccount,
                    l_compFxConditionParams);
        }
        //1.10�j createResponse( )
        WEB3FXTransFromFXInputResponse l_fxTransFromFXInputResponse =
            (WEB3FXTransFromFXInputResponse) l_request.createResponse();
        
        //1.11�j �v���p�e�B�Z�b�g
        //���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B

        //FX����U�։\�z���F
        //��Е�FX�V�X�e������.FX����̐U�։\�z�擾�敪 = �P�F�擾����̏ꍇ
        //getFX����U�։\�z�i�`�F�b�N����j()�̖߂�l
        if (WEB3GetTransferableAmtDivDef.GET.equals(l_compFxConditionParams.getGetTransferableAmtDiv()))
        {
            l_fxTransFromFXInputResponse.fxTransferAbleAmtList = l_transferAbleAmtUnits;
        }

        //��Е�FX�V�X�e������.FX����̐U�։\�z�擾�敪 = �O�F�擾���Ȃ��̏ꍇ
        //FX����U�։\�z���ꗗ.accCode�@@=�@@createFX�������ꗗ()�̖߂�l�z��.FX�������.accCode
        //FX����U�։\�z���ꗗ.FX�������.courseDiv�@@=�@@createFX�������ꗗ()�̖߂�l�z��.FX�������.courseDiv
        //�i*�j���X�|���X.FX����U�։\�z���z��.�U�։\�z = null
        else if (WEB3GetTransferableAmtDivDef.NOT_GET.equals(l_compFxConditionParams.getGetTransferableAmtDiv()))
        {
            int l_intCount = l_fXAccInformationUnit.length;
            WEB3FXTransferAbleAmtUnit[] l_transferAbleAmtUnitNulls =
                new WEB3FXTransferAbleAmtUnit[l_intCount];
            WEB3FXTransferAbleAmtUnit l_transferAbleAmtUnit = null;
            for (int i = 0; i < l_intCount; i++)
            {
                l_transferAbleAmtUnit = new WEB3FXTransferAbleAmtUnit();
                l_transferAbleAmtUnit.fxAccountCode =
                    l_fXAccInformationUnit[i].fxAccountCode;
                l_transferAbleAmtUnit.fxCourseDiv =
                    l_fXAccInformationUnit[i].fxCourseDiv;
                l_transferAbleAmtUnit.transferableAmt = null;
                l_transferAbleAmtUnitNulls[i] = l_transferAbleAmtUnit;
            }
            l_fxTransFromFXInputResponse.fxTransferAbleAmtList = l_transferAbleAmtUnitNulls;
        }

        //�U�֏���񐔁F�@@validate�U�։\��()�̖߂�l
        l_fxTransFromFXInputResponse.transferCountUpper = l_intTransferPossibleCount + "";
        //�U�։񐔁F�@@get�U�։�()�̖߂�l
        l_fxTransFromFXInputResponse.transferCount = l_intTransferCount + "";

        log.exiting(STR_METHOD_NAME);
        return l_fxTransFromFXInputResponse;
    }

}@
