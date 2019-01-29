head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.32.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransFromFXServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : FX����U�փT�[�r�XImpl(WEB3FXTransFromFXServiceImpl)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/21 ����(���u) �V�K�쐬
                  : 2006/04/25 �юu�� (���u) �d�l�ύX�E���f��535,536,544
                  : 2006/06/05 ��؁iSCS�j �d�l�ύX No.589�EDB�X�V�d�l 091
                  : 2006/07/12 ������ (���u) �d�l�ύX�E���f��No.599
                  : 2006/08/23 ���(SCS)�@@���f��No.630�ANO.631�Ή�
                  : 2006/10/12 �����q (���u) �d�l�ύX�E���f��No.666
 Revesion History : 2007/07/12 ���^�](���u) �d�l�ύX���f��No.731
 Revision History : 2007/07/28 �Ј��� (���u) �d�l�ύX���f��742
 Revesion History : 2008/04/09 ���u�� (���u) �d�l�ύX���f��.832,842,844
 Revesion History : 2008/04/23 ���u�� (���u) �d�l�ύX���f��.845
 Revesion History : 2008/04/28 ���u�� (���u) �d�l�ύX���f��.847
                  : 2008/05/08 ���� (SCS) �d�l�ύX�E���f��832
                  : 2008/05/23 �O�� (SCS)
 Revesion History : 2008/05/20 �đo�g (���u) �d�l�ύX���f��858,868
 Revesion History : 2008/06/18 �đo�g (���u) �d�l�ύX���f��900
 Revesion History : 2008/09/24 �g�C�� (���u) �d�l�ύX���f��995�A1041
 Revesion History : 2008/11/19 SCS�哈 �d�l�ύX���f��.1086
                  : 2009/02/10 ���� (SCS)�d�l�ύX�E���f��1102,1103
 Revesion History : 2009/03/11 �đo�g (���u) �d�l�ύX���f��1111�A1144�A1147�A1148
 Revesion History : 2009/04/20 �Ԑi (���u) �d�l�ύX�E���f��1162
 Revesion History : 2009/06/26 ���g (���u) �d�l�ύX�E���f��1176�A1187
 Revesion History : 2009/09/16 �И��� (���u) �d�l�ύX�E���f��1197,1215
 */

package webbroker3.aio.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrder;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderRow;

import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3FXTransConnection;
import webbroker3.aio.WEB3FXDataControlService;
import webbroker3.aio.WEB3FXTelegramProcessService;
import webbroker3.aio.WEB3FXTransferAbleAmtDisplayService;
import webbroker3.aio.WEB3FXTransferOrderUpdateInterceptor;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.FxAccountParams;
import webbroker3.aio.data.FxTransferMasterParams;
import webbroker3.aio.data.GftTransferStatusParams;
import webbroker3.aio.define.WEB3AioAcceptResultCodeDef;
import webbroker3.aio.define.WEB3AioCashInOutAmountDivDef;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.aio.message.WEB3FXTransFromFXAskingRequest;
import webbroker3.aio.message.WEB3FXTransFromFXAskingResponse;
import webbroker3.aio.message.WEB3FXTransFromFXCompleteRequest;
import webbroker3.aio.message.WEB3FXTransFromFXCompleteResponse;
import webbroker3.aio.message.WEB3FXTransFromFXCompleteSoapRequest;
import webbroker3.aio.message.WEB3FXTransFromFXCompleteSoapResponse;
import webbroker3.aio.message.WEB3FXTransFromFXConfirmRequest;
import webbroker3.aio.message.WEB3FXTransFromFXConfirmResponse;
import webbroker3.aio.message.WEB3FXTransferAbleAmtUnit;
import webbroker3.aio.service.delegate.WEB3FXTransFromFXService;
import webbroker3.aio.service.delegate.WEB3MarginTransferService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AioSoapConnectDivDef;
import webbroker3.common.define.WEB3AioTransferDivDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3FxDeliveryDateInsertCheckDef;
import webbroker3.common.define.WEB3FxSystemCodeDef;
import webbroker3.common.define.WEB3FxSystemDivDef;
import webbroker3.common.define.WEB3FxTransferMasterRemarkCodeDef;
import webbroker3.common.define.WEB3GetTransferableAmtDivDef;
import webbroker3.common.define.WEB3GftErrorReasonCodeDef;
import webbroker3.common.define.WEB3GftMessageOperationDef;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.define.WEB3SendRcvDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (FX����U�փT�[�r�XImpl) <BR>
 * FX����U�փT�[�r�XImpl
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FXTransFromFXServiceImpl extends WEB3ClientRequestService
    implements WEB3FXTransFromFXService
{
    /**
     * @@roseuid 41E7721102CE
     */
    public WEB3FXTransFromFXServiceImpl()
    {
    }
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FXTransFromFXServiceImpl.class);

    /**
     * FX����U�փT�[�r�X�������s���B <BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̂����ꂩ�̃��\�b�h���R�[������B <BR>
     * �Evalidate����() <BR>
     * �Estart����() <BR>
     * �Esubmit����() <BR>
     * �Esubmit����()�iSOAP�ڑ�)<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BE51600267
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
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3FXTransFromFXConfirmRequest)
        {
            //validate����()���\�b�h
            l_response =
                this.validateOrder(
                    (WEB3FXTransFromFXConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3FXTransFromFXCompleteSoapRequest)
        {
            //�Esubmit����()�iSOAP�ڑ�)
            l_response =
                this.submitOrderSoap(
                    (WEB3FXTransFromFXCompleteSoapRequest)l_request);
        }
        else if (l_request instanceof WEB3FXTransFromFXAskingRequest)
        {
            //start����
            l_response =
                this.startOrder(
                    (WEB3FXTransFromFXAskingRequest) l_request);
        }
        else if (l_request instanceof WEB3FXTransFromFXCompleteRequest)
        {
            //submit����()���\�b�h
            l_response =
                this.submitOrder(
                    (WEB3FXTransFromFXCompleteRequest) l_request);
        }
        else
        {
            log.debug(
                    "���N�G�X�g�f�[�^��"
                    + " WEB3FXTransFromFXConfirmRequest "
                    + "�� WEB3FXTransFromFXAskingRequest "
                    + "�� WEB3FXTransFromFXCompleteRequest"
                    + "�� WEB3FXTransFromFXCompleteSoapRequest�ȊO�ł���, but is "
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
     * (validate����) <BR>
     * �U�֒����̔����R�����s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�iFX����U�ցjvalidate�����v�Q�ƁB <BR>
     * 
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3FXTransFromFXConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BE52430332
     */
    protected WEB3FXTransFromFXConfirmResponse validateOrder(
        WEB3FXTransFromFXConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateOrder(WEB3FXTransFromFXConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1�j FX����U�֊m�F���N�G�X�g. validate( ) 
        l_request.validate();
        
        //1.2�j get�⏕����(SubAccountTypeEnum)
        // �⏕�����^�C�v�F 1�i������������i�a����j�j
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.3�j validate����(SubAccount)
        //  �ȉ��̃`�F�b�N���s���B 
        //�@@�|��t���ԃ`�F�b�N 
        //�@@�|�V�X�e����~���`�F�b�N 
        //�@@�|�ڋq�̃`�F�b�N�i�x�q�A�Ǘ����b�N���j
        //============================FinApp==============================
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
        //���o�������}�l�[�W���N���X���擾����B
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        
        //�`�F�b�N���s��
        l_aioOrderManager.validateOrder(l_subAccount);
        
        //1.4�jget��Е�FX�V�X�e������(String, String, String)
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
        String l_strFxSystemCode = l_request.fxSystemCode;
        CompFxConditionParams l_compFxConditionParams = null;
        try
        {
            l_compFxConditionParams =
                l_fXDataControlService.getCompFxCondition(
                    l_strInstitutionCode, l_strBranchCode, l_strFxSystemCode);
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

        //getFX�U�֏����}�X�^(long, String)
        //�y�����z
        //  FX�V�X�e������ID�@@= ��Е�FX�V�X�e������Params.FX�V�X�e������ID
        //  �U�֋敪 = 0�F����
        FxTransferMasterParams l_fxTransferMasterParams =
            l_fXDataControlService.getFxTransferMasterParams(
                l_compFxConditionParams.getFxSystemId(),
                WEB3AioTransferDivDef.CASHIN);

        //1.5�jvalidate�O���V�X�e����t�\(String)
        //  FX�V�X�e���̎�t���ԃ`�F�b�N���s���B 
        //  [�����̐ݒ�] 
        //  �V�X�e���R�[�h�F�@@��Е�FX�V�X�e������Params.FX�V�X�e���R�[�h
        l_aioOrderManager.validateOtherSystemAcceptPossible(
            l_compFxConditionParams.getFxSystemCode());

        //validate�U�։\(SubAccount, ��Е�FX�V�X�e������Params)
        //FX�V�X�e���敪�ʂɁA����\���`�F�b�N���s���B
        //[�����̐ݒ�]
        //�⏕�����F�@@get�⏕����()�̖߂�l
        //��Е�FX�V�X�e������Params�F�@@get��Еʂe�w�V�X�e������()�̖߂�l
        l_fXDataControlService.validateChangePoss(
            l_subAccount,
            l_compFxConditionParams);

        //1.7�jget������()
        Date l_datOrderBizDate =
            WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //get��n��(Date, �⏕����, String)
        //[�����̐ݒ�]
        //�������F�@@get������()�̖߂�l
        //�⏕�����F�@@get�⏕����()�̖߂�l
        //��n���ݒ�敪�F�@@getFX�U�֏����}�X�^()�̖߂�l.��n���ݒ�敪
        Date l_datDeliveryDate =
            l_fXDataControlService.getDeliveryDate(
                l_datOrderBizDate,
                l_subAccount,
                l_fxTransferMasterParams.getDeliveryDateDiv());

        //1.9�j validate�U�։\��(SubAccount, Date, OrderCategEnum)
        //  �U�։\�񐔂̃`�F�b�N���s���B 
        //  [����] 
        //  �⏕�����F get�⏕����()�̖߂�l 
        //  �������F get������()�̖߂�l 
        //  �����J�e�S���F�@@15�i�ב֕ۏ؋��U�ցj
    
        l_aioOrderManager.validateTransferPossibleCount(
            l_subAccount, 
            l_datOrderBizDate, 
            OrderCategEnum.FX);

        WEB3FXTransferAbleAmtUnit l_transferAbleAmtUnit = null;
        //��Е�FX�V�X�e������.FX����̐U�։\�z�擾�敪 = �P�F�擾����̏ꍇ
        if (WEB3GetTransferableAmtDivDef.GET.equals(l_compFxConditionParams.getGetTransferableAmtDiv()))
        {
            //getFX����U�։\�z�i�`�F�b�N����j(�⏕���� : SubAccount, 
            //��Е�FX�V�X�e������ : CompFxConditionParams, �U�֋��z : String, �R�[�X�敪 : String)
            WEB3FXTransferAbleAmtDisplayService l_transferAbleAmtDisplayService =
                (WEB3FXTransferAbleAmtDisplayService)Services.getService(
                    WEB3FXTransferAbleAmtDisplayService.class);
            //[����]
            //�⏕�����F�擾�����⏕����
            //��Е�FX�V�X�e�������F�擾������Е�FX�V�X�e������
            //�U�֋��z�F���N�G�X�g.�U�֋��z
            //�R�[�X�敪�F���N�G�X�g.FX�������.�R�[�X�敪
            String l_strFxCourseDiv = null;
            if (l_request.fxAccInformationUnit != null)
            {
                l_strFxCourseDiv = l_request.fxAccInformationUnit.fxCourseDiv;
            }
            l_transferAbleAmtUnit =
                l_transferAbleAmtDisplayService.getFXTransferAbleAmtCheck(
                    l_subAccount,
                    l_compFxConditionParams,
                    l_request.transferAmount,
                    l_strFxCourseDiv);
        }

        //1.10�j createResponse( )
        WEB3FXTransFromFXConfirmResponse 
              l_FXTransFromFXConfirmResponse =
                  (WEB3FXTransFromFXConfirmResponse) l_request.createResponse();  
        
        //1.11) (*)�v���p�e�B�Z�b�g
        //���X�|���X�f�[�^�Ƀv���p�e�B�Z�b�g����
        //��n��:get��n��()�̖߂�l
        l_FXTransFromFXConfirmResponse.deliveryDate = l_datDeliveryDate;

        //FX����U�։\�z���F
        //��Е�FX�V�X�e������.FX����̐U�։\�z�擾�敪 = �P�F�擾����̏ꍇ
        //getFX����U�։\�z�i�`�F�b�N����j()�̖߂�l
        if (WEB3GetTransferableAmtDivDef.GET.equals(l_compFxConditionParams.getGetTransferableAmtDiv()))
        {
            l_FXTransFromFXConfirmResponse.fxTransferAbleAmtUnit = l_transferAbleAmtUnit;
        }
        //��Е�FX�V�X�e������.FX����̐U�։\�z�擾�敪 = �O�F�擾���Ȃ��̏ꍇ
        //�@@�@@���X�|���X.FX����U�։\�z���.�����ԍ� = ���N�G�X�g�f�[�^.FX�������.�����ԍ�
        //       (FX�������null�̏ꍇ�Anull���Z�b�g)
        //�@@�@@���X�|���X.FX����U�։\�z���.�R�[�X�敪 = ���N�G�X�g�f�[�^.FX�������.�R�[�X�敪
        //       (FX�������null�̏ꍇ�Anull���Z�b�g)
        //�@@�@@�i*�j���X�|���X.FX����U�։\�z���.�U�։\�z = null
        else if (WEB3GetTransferableAmtDivDef.NOT_GET.equals(l_compFxConditionParams.getGetTransferableAmtDiv()))
        {
            WEB3FXTransferAbleAmtUnit l_transferAbleAmtUnitNull =
                new WEB3FXTransferAbleAmtUnit();

            String l_strFxAccountCode = null;
            String l_strFxCourseDiv = null;
            if (l_request.fxAccInformationUnit != null)
            {
                l_strFxAccountCode = l_request.fxAccInformationUnit.fxAccountCode;
                l_strFxCourseDiv = l_request.fxAccInformationUnit.fxCourseDiv;
            }

            l_transferAbleAmtUnitNull.fxAccountCode = l_strFxAccountCode;
            l_transferAbleAmtUnitNull.fxCourseDiv = l_strFxCourseDiv;
            l_transferAbleAmtUnitNull.transferableAmt = null;
            l_FXTransFromFXConfirmResponse.fxTransferAbleAmtUnit = l_transferAbleAmtUnitNull;
        }

        log.exiting(STR_METHOD_NAME);
        return l_FXTransFromFXConfirmResponse;
    }

    /**
     * (start����) <BR>
     * �U�֒����̈˗��������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�iFX����U�ցjstart�����v�Q�ƁB <BR>
     * 
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3FXTransFromFXAskingResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BE52A90054
     */
    protected WEB3FXTransFromFXAskingResponse startOrder(
        WEB3FXTransFromFXAskingRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "startOrder(WEB3FXTransFromFXAskingRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1�j FX����U�ֈ˗����N�G�X�g. validate( ) 
        l_request.validate();
        
        //1.2�j get�⏕����(SubAccountTypeEnum)
        // �⏕�����^�C�v�F 1�i������������i�a����j�j
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.3�j validate����(SubAccount)
        //  �ȉ��̃`�F�b�N���s���B 
        //�@@�|��t���ԃ`�F�b�N 
        //�@@�|�V�X�e����~���`�F�b�N 
        //�@@�|�ڋq�̃`�F�b�N�i�x�q�A�Ǘ����b�N���j
        //============================FinApp==============================
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
        //���o�������}�l�[�W���N���X���擾����B
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        
        //�`�F�b�N���s��
        l_aioOrderManager.validateOrder(l_subAccount);
        
        //1.4�jget��Е�FX�V�X�e������(String, String, String)
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
        String l_strFxSysCode = l_request.fxSystemCode;
        CompFxConditionParams l_compFxConditionParams = null;        
        try
        {
            l_compFxConditionParams =
                l_fXDataControlService.getCompFxCondition(
                    l_strInstitutionCode, 
                    l_strBranchCode,
                    l_strFxSysCode);
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

        //getFX�U�֏����}�X�^(long, String)
        //�y�����z
        //  FX�V�X�e������ID�@@= ��Е�FX�V�X�e������Params.FX�V�X�e������ID
        //  �U�֋敪 = 0�F����
        FxTransferMasterParams l_fxTransferMasterParams =
            l_fXDataControlService.getFxTransferMasterParams(
                l_compFxConditionParams.getFxSystemId(),
                WEB3AioTransferDivDef.CASHIN);

        //1.5�j validate�O���V�X�e����t�\(String)
        //  FX�V�X�e���̎�t���ԃ`�F�b�N���s���B 
        //  [�����̐ݒ�] 
        //  �V�X�e���R�[�h�F�@@��Е�FX�V�X�e������Params.FX�V�X�e���R�[�h
        l_aioOrderManager.validateOtherSystemAcceptPossible(
            l_compFxConditionParams.getFxSystemCode());

        //validate�U�։\(SubAccount, ��Е�FX�V�X�e������Params)
        //FX�V�X�e���敪�ʂɁA����\���`�F�b�N���s���B
        //[�����̐ݒ�]
        //�⏕�����F�@@get�⏕����()�̖߂�l
        //��Е�FX�V�X�e������Params�F�@@get��Еʂe�w�V�X�e������()�̖߂�l
        l_fXDataControlService.validateChangePoss(
            l_subAccount,
            l_compFxConditionParams);

        //1.7�j get������()
        Date l_datOrderBizDate =  
            WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //get��n��(Date, �⏕����, String)
        //[����]
        //�������F�@@get������()�̖߂�l
        //�⏕�����F�@@get�⏕����()�̖߂�l
        //��n���ݒ�敪�F�@@getFX�U�֏����}�X�^().��n���ݒ�敪
        Date l_datDeliveryDate =
            l_fXDataControlService.getDeliveryDate(l_datOrderBizDate, l_subAccount,
                l_fxTransferMasterParams.getDeliveryDateDiv());

        //1.9�jvalidate�U�։\��(SubAccount, Date, OrderCategEnum)
        //  �U�։\�񐔂̃`�F�b�N���s���B 
        //  [����] 
        //  �⏕�����F get�⏕����()�̖߂�l 
        //  �������F get������()�̖߂�l 
        //  �����J�e�S���F�@@15�i�ב֕ۏ؋��U�ցj
        l_aioOrderManager.validateTransferPossibleCount(
            l_subAccount, 
            l_datOrderBizDate, 
            OrderCategEnum.FX);
        
        //1.10�jget�㗝���͎�( )
        Trader l_trader = this.getTrader();
        
        //1.11�j validate����p�X���[�h(Trader, SubAccount, String)
        //[����] 
        //�㗝���͎ҁF get�㗝���͎�()�̖߂�l 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ� 
        WEB3GentradeOrderValidator l_gentradeOrderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        OrderValidationResult l_validationResult = 
            l_gentradeOrderValidator.validateTradingPassword(
                l_trader, 
                l_subAccount, 
                l_request.password);
        
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("�`�F�b�N�G���[�̏ꍇ�͂��O���X���[����");
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�`�F�b�N�G���[�̏ꍇ�͂��O���X���[����");
        }
        
        //1.12�j getFX�ڋq(String, String, String, String)
        //[�����̐ݒ�] 
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
        //���X�R�[�h�F�@@�⏕����.get����X().getBranchCode() 
        //FX�V�X�e���R�[�h�F�@@��Е�FX�V�X�e������.FX�V�X�e���R�[�h 
        //�ڋq�R�[�h�F�@@�⏕����.getMainAccount().getAccountCode()
        String l_strFxSystemCode = l_compFxConditionParams.getFxSystemCode();
        String l_strAccountCode = l_subAccount.getMainAccount().getAccountCode();
        FxAccountParams l_fxAccountParams = null;
        try
        {
            l_fxAccountParams =
                l_fXDataControlService.getFXAccount(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strFxSystemCode,
                    l_strAccountCode);
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
        
        //1.13�j get�V�K���ʃR�[�h(String, String, ProductTypeEnum)
        // [����] 
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
        //���X�R�[�h�F�@@�⏕����.get����X().getBranchCode() 
        //�����^�C�v�F 5�i�����j
        WEB3HostReqOrderNumberManageService l_reqOrderNumberManageService=
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);    
        String l_strNewNumber = 
            l_reqOrderNumberManageService.getNewNumber(
                l_strInstitutionCode,
                l_strBranchCode,
                ProductTypeEnum.CASH);
        
        //1.14�j GFT�˗��d������( )
        WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit = 
            new WEB3FXGftAskingTelegramUnit();
            
        //1.15)  get�����敪(FX�V�X�e���R�[�h : String)
        //[get�����݋敪()�Ɏw�肷�����]  
        //FX�V�X�e���R�[�h�F�@@���N�G�X�g�f�[�^�DFX�V�X�e���R�[�h
        String l_strOperationDiv = getOperationDiv(l_request.fxSystemCode);

        //get�ϊ�FX���O�C��ID(long, String, String, long)
        //[����]
        // �،����ID�F�@@�⏕����.getInstitution.getInstitutionId()
        // FX�V�X�e���R�[�h�F�@@��Е�FX�V�X�e������Params.FX�V�X�e���R�[�h
        // FX���O�C��ID�������F�@@��Е�FX�V�X�e������Params.FX���O�C��ID������
        // FX���O�C��ID�F�@@FX�ڋqParams.FX���O�C��ID
        String l_strChangedFXLoginID = l_fXDataControlService.getChangedFXLoginID(
            l_subAccount.getInstitution().getInstitutionId(),
            l_compFxConditionParams.getFxSystemCode(),
            l_compFxConditionParams.getFxHeadOfLoginId(),
            l_fxAccountParams.getFxLoginId());

        //1.17�j (*)�v���p�e�B�Z�b�g
        //(*)GFT�˗��d�����ׂɕK�v�ȃv���p�e�B���Z�b�g����i���L�ȊO�̃v���p�e�B�͐ݒ肵�Ȃ��j
        Timestamp l_tsSystemTimestamp = new Timestamp(new Date().getTime());
        //DIR��GFT���M����     �F���ݎ����i�V�X�e���^�C���X�^���v�j
        l_fXGftAskingTelegramUnit.dirSendTime = 
            WEB3DateUtility.formatDate(l_tsSystemTimestamp, "yyyyMMddHHmmss");

        //�����敪            �Fget�����敪�̖߂�l
        l_fXGftAskingTelegramUnit.gftOperationDiv = l_strOperationDiv;
        //�ב֕ۏ؋������ԍ�   �F���N�G�X�g�f�[�^.FX�������.�����ԍ�
        l_fXGftAskingTelegramUnit.fxAccountCode = l_request.fxAccInformationUnit.fxAccountCode;
        //�������O�C��ID        �Fget�ϊ�FX���O�C��ID�̖߂�l
        l_fXGftAskingTelegramUnit.fxFirstLoginId = l_strChangedFXLoginID;
        //�S���敪            �F��Е�FX�V�X�e������Params.�S���敪
        l_fXGftAskingTelegramUnit.groupName = l_compFxConditionParams.getGroupName();
        //���o���z            �F���N�G�X�g�f�[�^.�U�֋��z
        l_fXGftAskingTelegramUnit.cashinoutAmt = l_request.transferAmount;
        //WOLF�Z�b�V�����L�[     �F���N�G�X�g�f�[�^.WOLF�Z�b�V�����L�[
        l_fXGftAskingTelegramUnit.wolfSession = l_request.wolfSession;
        //�A�v���P�[�V����ID      �F���N�G�X�g�f�[�^.�A�v���P�[�V����ID
        l_fXGftAskingTelegramUnit.wolfAid = l_request.wolfAid;
        //�Đ����T�[�r�XID       �F���N�G�X�g�f�[�^.�Đ����T�[�r�XID
        l_fXGftAskingTelegramUnit.regetServiceId = l_request.regetServiceId;
        //SSID            �F���N�G�X�g�f�[�^.SSID
        l_fXGftAskingTelegramUnit.wolfSsid = l_request.wolfSsid;
        //��ЃR�[�h           �F�⏕����.�،���ЃR�[�h
        l_fXGftAskingTelegramUnit.institutionCode = l_strInstitutionCode;
        //���X�R�[�h           �F�⏕����.get����X().getBranchCode()
        l_fXGftAskingTelegramUnit.branchCode = l_strBranchCode;
        //�ڋq�R�[�h           �F�⏕����.getMainAccount().getAccountCode()
        l_fXGftAskingTelegramUnit.accountCode = l_strAccountCode;
        //���ʃR�[�h           �Fget�V�K���ʃR�[�h()�̖߂�l
        l_fXGftAskingTelegramUnit.requestNumber = l_strNewNumber;

        String l_strDeliveryDate =
            WEB3DateUtility.formatDate(
                l_datDeliveryDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        //��n��:get��n��()�̖߂�l
        l_fXGftAskingTelegramUnit.deliveryDate = l_strDeliveryDate;

        //1.18�j createGFT�d���n�b�V���l(GFT�˗��d������)
        //[�����̐ݒ�] 
        //GFT�˗��d�����ׁF�@@�v���p�e�B�Z�b�g���s����GFT�˗��d������
        WEB3FXTelegramProcessService l_fXTelegramProcessService=
            (WEB3FXTelegramProcessService)Services.getService(
                WEB3FXTelegramProcessService.class);  
        l_fXGftAskingTelegramUnit.hashValue =  
            l_fXTelegramProcessService.createGFTTelegramHashValue(l_fXGftAskingTelegramUnit);
        
        // 1.19�jinsertGFT�d���ۑ�(GFT�˗��d������)
        //[�����̐ݒ�] 
        //GFT�˗��d�����ׁF�@@�v���p�e�B�Z�b�g���s����GFT�˗��d������
        l_fXDataControlService.insertGFTMessage(l_fXGftAskingTelegramUnit);
        
        //insertGFT�U�֏�(GFT�˗��d������, String, String, String, ��Е�FX�V�X�e������Params, String)
        //GFT�U�֏󋵃e�[�u���ɍs��insert����B 
        //[�����̐ݒ�] 
        //GFT�˗��d�����ׁF�@@�v���p�e�B�Z�b�g���s����GFT�˗��d������ 
        //�R�[�X�敪�F�@@���N�G�X�g�f�[�^.FX�������.�R�[�X�敪 
        //��n�\����F get��n��()�̖߂�l 
        //�M�p�U�֗p���ʃR�[�h�F�@@null
        //��Е�FX�V�X�e������Params�F��Е�FX�V�X�e������Params
        //���o���ꗗ����敪:�@@�@@FX�U�֏����}�X�^Params.���o���ꗗ����敪
        l_fXDataControlService.insertGFTTransferStatus(
            l_fXGftAskingTelegramUnit,
            l_request.fxAccInformationUnit.fxCourseDiv,
            l_strDeliveryDate,
            null,
            l_compFxConditionParams,
            l_fxTransferMasterParams.getIoListTradeDiv());

        //1.21�j createNewOrderId( )
       long l_lngNewOrderId = l_aioOrderManager.createNewOrderId();
        
        //1.22�j   createResponse( )
        WEB3FXTransFromFXAskingResponse 
              l_fxTransFromFXAskingResponse =
                  (WEB3FXTransFromFXAskingResponse) l_request.createResponse();  
        
        //1.23�j(*)�v���p�e�B�Z�b�g
        // ���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        // URL�F ��Е�FX�V�X�e������Params.URL
        l_fxTransFromFXAskingResponse.fxUrl = l_compFxConditionParams.getUrl();
        // GFT�˗��d�����ׁF�@@(*�@@��L�ŕҏW���s����GFT�˗��d������)
        l_fxTransFromFXAskingResponse.fxGftAskingTelegramUnit = l_fXGftAskingTelegramUnit;
        // ����ID�F�@@createNewOrderId()�̖߂�l
        l_fxTransFromFXAskingResponse.orderId = l_lngNewOrderId + "";
        // �m�F���������F�@@get������()�̖߂�l
        l_fxTransFromFXAskingResponse.checkDate = l_datOrderBizDate;
        log.exiting(STR_METHOD_NAME);
        return l_fxTransFromFXAskingResponse;
    }

    /**
     * (submit����) <BR>
     * �U�֒����̓o�^�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�iFX����U�ցjsubmit�����v�Q�ƁB <BR>
     * <BR>
     * ======================================================== <BR>
     * �V�[�P���X�}(�u(�ב֕ۏ؋��T�[�r�X���f��) / FX����U�� �v<BR>
     * �iFX����U�ցjsubmit����) <BR>
     * : 1.3 getGFT�U�֏�(String, String, String) <BR>
     * �߂�l��null�̏ꍇ�A��O��thorw����B <BR>
     * <BR>
     * class: WEB3SystemLayerException <BR>
     * tag: SYSTEM_ERROR_80005 <BR>
     * <BR>
     * ========================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * �V�[�P���X�}(�u(�ב֕ۏ؋��T�[�r�X���f��) / FX����U�� �v<BR>
     * �iFX����U�ցjsubmit���� <BR>
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
     * 00000205�i�ב֌����̏����Y�c���s���j:�u�����Y�c�����s�����Ă��܂��v<BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02672 <BR>
     * <BR>
     * 00000206�i�ב֌����̌����c���s���j�F�u�����c�����s�����Ă��܂��v<BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02673 <BR>
     * <BR>
     * 00000207�i�ב֌����Ƀ}�C�i�X�ʉ݂���j�F�u�ב֌����ɂ�����ʉ݌����c���ŁA�}�C�i�X�ʉ݂������܂��B<BR>
     * �ב֌����ŃR���o�[�W������ɍēx�U�ւ��������Ă������B�v<BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02674 <BR>
     * <BR>
     * 00000801(2�d���M�G���[)�̏ꍇ �F�u2�d���M�G���[�v <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01799 <BR>
     * <BR>
     * 00000501�i�Y������؋������������݂��Ȃ��j�̏ꍇ�@@�F[�؋����������J�݃G���[]<BR>
     * <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_02436<BR>
     * <BR>
     * 00000503�i�Y������ב֕ۏ؋����������݂��Ȃ��j�F�u�ב֌����w�̐U�ւł��B<BR>
     * ���̋@@�\�͈ב֌������J�݂��A���U�֓��ӏ��ɋL���E�����t������������ <BR>
     * ���q�邵�������p�����������Ƃ͂ǂ�������v<BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02675 <BR>
     * <BR>
     * �ȊO�̎�t���ʂ̏ꍇ �F�u���̑���FX�V�X�e���G���[�v <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01800 <BR>
     * <BR>
     * ========================================================== 
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3FXTransFromFXCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BE52D40238
     */
    protected WEB3FXTransFromFXCompleteResponse submitOrder(
        WEB3FXTransFromFXCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "submitOrder(WEB3FXTransFromFXCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1�j validate( )
        l_request.validate();
        
        //1.2�j insertGFT�d���ۑ�(GFT���ʒʒm�d������)
        //[�����̐ݒ�] 
        //GFT���ʒʒm�d�����ׁF�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������
        WEB3FXDataControlService l_fXDataControlService =
            (WEB3FXDataControlService) Services.getService(WEB3FXDataControlService.class);
        l_fXDataControlService.insertGFTMessage(
            l_request.fxGftResultNoticeTelegramUnit);
        
        //1.3�j getGFT�U�֏�(String, String, String)
        //[�����̐ݒ�] 
        //�،���ЃR�[�h�F�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������.��ЃR�[�h 
        //���X�R�[�h�F�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������.���X�R�[�h 
        //���ʃR�[�h�F�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������.���ʃR�[�h
        GftTransferStatusParams l_gftTransferStatusParams = 
            l_fXDataControlService.getGFTTransferStatus(
                l_request.fxGftResultNoticeTelegramUnit.institutionCode, 
                l_request.fxGftResultNoticeTelegramUnit.branchCode, 
                l_request.fxGftResultNoticeTelegramUnit.requestNumber);
        if (l_gftTransferStatusParams == null)
        {
            log.debug("GFT�U�֏󋵎擾�G���[�B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName()+ "." + STR_METHOD_NAME,
                "GFT�U�֏󋵎擾�G���[�B");
        }        

        //get�⏕����(SubAccountTypeEnum)
        //[����]
        //�⏕�����^�C�v�F 1�i������������i�a����j�j
        SubAccount l_subAccount =
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //get��Е�FX�V�X�e������(String, String, String)
        //[�����̐ݒ�]
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h
        //���X�R�[�h�F�@@�⏕����.get����X.getBranchCode()
        //FX�V�X�e���R�[�h�F�@@���N�G�X�g�f�[�^.FX�V�X�e���R�[�h
        String l_strInstitutionCode =
            l_subAccount.getInstitution().getInstitutionCode();
        String l_strBranchCode =
            l_subAccount.getMainAccount().getBranch().getBranchCode();
        String l_strFxSystemCode = l_request.fxSystemCode;
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
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //getFX�U�֏����}�X�^(long, String)
        //�y�����z
        //FX�V�X�e������ID�@@= ��Е�FX�V�X�e������Params.FX�V�X�e������ID
        //�U�֋敪 = 0�F����
        FxTransferMasterParams l_fxTransferMasterParams =
            l_fXDataControlService.getFxTransferMasterParams(
                l_compFxConditionParams.getFxSystemId(),
                WEB3AioTransferDivDef.CASHIN);

        boolean l_blnIsGFTTelegramSet = true;
        boolean l_blnIsGFTTelegramLengthPropSame = true;
        boolean l_blnIsGFTTelegramSendAndReceiveMailSame = true;
        //��Е�FX�V�X�e������Params.SOAP�ڑ����{�敪== 0�FSOAP�ڑ������{ or 2�F�����J�݂̂ݎ��{�̏ꍇ
        if (WEB3AioSoapConnectDivDef.SOAP_CONNECT_NOT_ENFORCEMENT.equals(l_compFxConditionParams.getSoapConnectDiv())
           || WEB3AioSoapConnectDivDef.TRANSFER_ENFORCEMENT.equals(l_compFxConditionParams.getSoapConnectDiv()))
        {
            //1.41�j isGFT�d�����ڐݒ�(GFT���ʒʒm�d������)
            //[�����̐ݒ�]
            //GFT���ʒʒm�d�����ׁF�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������
            WEB3FXTelegramProcessService l_fXTelegramProcessService =
                (WEB3FXTelegramProcessService) Services.getService(WEB3FXTelegramProcessService.class);
            l_blnIsGFTTelegramSet = 
                l_fXTelegramProcessService.isGFTTelegramSet(l_request.fxGftResultNoticeTelegramUnit);

            //1.42�j isGFT�d������������v(GFT���ʒʒm�d������)
            //[�����̐ݒ�]
            //GFT���ʒʒm�d�����ׁF�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������
            l_blnIsGFTTelegramLengthPropSame = 
                l_fXTelegramProcessService.isGFTTelegramLengthPropSame(
                    l_request.fxGftResultNoticeTelegramUnit);

            //isGFT�d�����ڐݒ�()=true and isGFT�d������������v()=true �̏ꍇ�A���{
            if (l_blnIsGFTTelegramSet && l_blnIsGFTTelegramLengthPropSame)
            {
                //1.43�j isGFT�d������M���ڈ�v(GFT���ʒʒm�d������)
                //[�����̐ݒ�]
                //GFT���ʒʒm�d�����ׁF�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������
                l_blnIsGFTTelegramSendAndReceiveMailSame = 
                    l_fXTelegramProcessService.isGFTTelegramSendAndReceiveValueSame(
                        l_request.fxGftResultNoticeTelegramUnit);
            }
        }

        //�ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�A���{����B
        // ����Е�FX�V�X�e������Params.SOAP�ڑ����{�敪== 1�FSOAP�ڑ����{�̏ꍇ�A
        //   �B�A�C�͏����Ɋ܂߂Ȃ�
        boolean l_blnFlag1 = WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(
            l_gftTransferStatusParams.getSendRcvDiv());
        boolean l_blnFlag2 = WEB3SendRcvDivDef.SEND_COMPLETE.equals(
            l_gftTransferStatusParams.getSendRcvDiv());
        boolean l_blnFlag3 =
            WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000.equals(
                l_request.fxGftResultNoticeTelegramUnit.resultCode);
        boolean l_blnIsResultNoticeTelegramPro = false;
        boolean l_blnIsTelegramSendAndReceiveSame = false;
        if (!WEB3AioSoapConnectDivDef.SOAP_CONNECT_ENFORCEMENT.equals(
            l_compFxConditionParams.getSoapConnectDiv()))
        {
            if (!(l_blnIsGFTTelegramSet && l_blnIsGFTTelegramLengthPropSame))
            {
                l_blnIsResultNoticeTelegramPro = true;
            }

            if (!l_blnIsGFTTelegramSendAndReceiveMailSame)
            {
                l_blnIsTelegramSendAndReceiveSame = true;
            }
        }

        //�@@����M�敪���s���̏ꍇ�i2�d��M�G���[�j
        //�igetGFT�U�֏�()�̖߂�l��GFT�U�֏�Params.����M�敪 �� 2(��M��)�j
        //�A����M�敪���s���̏ꍇ�i���̑��G���[�j
        //�igetGFT�U�֏�()�̖߂�l��GFT�U�֏�Params.����M�敪 != (1(���M��)�A2(��M��))
        //�B���ʒʒm�d�����e�̑Ó������s���̏ꍇ
        //�iisGFT�d�����ڐݒ�()��fasle�A�܂��́AisGFT�d������������v()��false�j
        //�C����M�d���̍��ڂ��s��v�̏ꍇ
        //�iisGFT�d������M���ڈ�v()��false�j
        //�D�U�ւ�����Ɏ�t���Ȃ������ꍇ
        //(���N�G�X�g�f�[�^.GFT���ʒʒm�d������.��t���� != 00000000�i����j�j
                
        if (l_blnFlag1
            || !(l_blnFlag1 || l_blnFlag2)
            || l_blnIsResultNoticeTelegramPro
            || l_blnIsTelegramSendAndReceiveSame
            || !l_blnFlag3)
        {
            if (!l_blnFlag1)
            {
                //1.7.1�j(*2)
                //�@@�ȊO�̏ꍇ�A���{����B
                //7.1.1 updateGFT�U�֏�(GFT�U�֏�Params, GFT���ʒʒm�d������, String, String)
                //[�����̐ݒ�] 
                //GFT�U�֏�Params�F�@@getGFT�U�֏�()�̖߂�l 
                //GFT���ʒʒm�d�����ׁF�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������ 
                //�X�V���n�\����F�@@null 
                //�G���[���R�R�[�h�F�@@ 
                //�A�̏ꍇ�F0001�i����M�敪�`�F�b�N�G���[�j 
                //�B�̏ꍇ�F0002�i�p�����[�^�Ó����`�F�b�N�G���[�j 
                //�C�̏ꍇ�F0003�i�p�����[�^��v�`�F�b�N�G���[�j 
                //�D�̏ꍇ�F0004�i��t���ʃR�[�h�`�F�b�N�G���[�j
                //�G���[���R�R�[�h
                String l_strErrorReasonCode = null;
                if (!(l_blnFlag1 || l_blnFlag2))
                {
                    l_strErrorReasonCode = WEB3GftErrorReasonCodeDef.SENDRCV_ERROR;
                }
                if (!(l_blnIsGFTTelegramSet && l_blnIsGFTTelegramLengthPropSame))
                {
                    l_strErrorReasonCode = WEB3GftErrorReasonCodeDef.PARAM_VALIDITY_ERROR;
                }
                if (!l_blnIsGFTTelegramSendAndReceiveMailSame)
                {
                    l_strErrorReasonCode = WEB3GftErrorReasonCodeDef.PARAM_MISMATCH_ERROR;
                }
                if (!l_blnFlag3)
                {
                    l_strErrorReasonCode = WEB3GftErrorReasonCodeDef.RESULT_CODE_ERROR;
                }
                l_fXDataControlService.updateGFTTransferStatus(
                    l_gftTransferStatusParams,
                    l_request.fxGftResultNoticeTelegramUnit,
                    null,
                    l_strErrorReasonCode);
            
            }
            if (l_blnFlag1)
            {
                // �@@�̏ꍇ�F�u2�d��M�G���[�v
                log.debug("2�d��M�G���[�B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01972,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "2�d��M�G���[�B");
            }
            if (!(l_blnFlag1 || l_blnFlag2)
                || !(l_blnIsGFTTelegramSet && l_blnIsGFTTelegramLengthPropSame)
                || !l_blnIsGFTTelegramSendAndReceiveMailSame)
            {
                // �A�B�C�̏ꍇ�F�u���̑���FX�V�X�e���G���[�v
                log.debug("���̑���FX�V�X�e���G���[�B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01800,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���̑���FX�V�X�e���G���[�B");
            }
            if (!l_blnFlag3)
            {
                //1.7.2�j(*)��O��throw
                //1.7.2.1�j(*)��O��throw����B�@@�@@
                //�D�̏ꍇ�FGFT���ʒʒm�d������.��t���ʂɉ����Ĉȉ��̗�O�ƂȂ�
                //�@@00000105(�z�X�g�������ԊO)�̏ꍇ   �F�u��t���ԊO�G���[�v
                //�@@00000199(�z�X�g�V�X�e���G���[)�̏ꍇ �F�u�ʐM�G���[�v
                //�@@00000204(�c���s���G���[)�̏ꍇ    �F�u�c���s���G���[�v
                //  00000205(�ב֌����̏����Y�c���s��)�̏ꍇ    �F�u�����Y�c�����s�����Ă��܂��v
                //  00000206(�ב֌����̌����c���s��)�̏ꍇ    �F�u�����c�����s�����Ă��܂��v
                //  00000207(�ב֌����Ƀ}�C�i�X�ʉ݂���)�̏ꍇ    �F�u�ב֌����ɂ�����ʉ݌����c���ŁA�}�C�i�X�ʉ݂������܂��B
                //  �ב֌����ŃR���o�[�W������ɍēx�U�ւ��������Ă������B�v
                //�@@00000801(2�d���M�G���[)�̏ꍇ    �F�u2�d���M�G���[�v
                //  00000501�i�Y������؋������������݂��Ȃ��j�̏ꍇ�@@�F[�؋����������J�݃G���[]
                //  00000503�i�Y������ב֕ۏ؋����������݂��Ȃ��j�F�u�ב֌����w�̐U�ւł��B
                // ���̋@@�\�͈ב֌������J�݂��A���U�֓��ӏ��ɋL���E�����t������������ 
                // ���q�邵�������p�����������Ƃ͂ǂ�������v
                //�@@�ȊO�̎�t���ʂ̏ꍇ      �F�u���̑���FX�V�X�e���G���[�v
                if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000105.equals(
                        l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    log.debug("��t���ԊO�G���[�B");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01801,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "��t���ԊO�G���[�B");
                }
                else if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000199.equals(
                            l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    log.debug("�ʐM�G���[");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01802,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�ʐM�G���[�B");
                }
                else if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000204.equals(
                            l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    log.debug("�c���s���G���[�B");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01803,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�c���s���G���[�B");
                }
                else if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000205.equals(
                            l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    log.debug("�����Y�c���s�����Ă��܂��B");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02672,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�����Y�c���s�����Ă��܂��B");
                }
                else if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000206.equals(
                            l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    log.debug("�����c�����s�����Ă��܂��B");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02673,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�����c�����s�����Ă��܂��B");
                }
                else if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000207.equals(
                            l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    log.debug("�ב֌����ɂ�����ʉ݌����c���ŁA�}�C�i�X�ʉ݂������܂��B" +
                            "�ב֌����ŃR���o�[�W������ɍēx�U�ւ��������Ă������B");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02674,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�ב֌����ɂ�����ʉ݌����c���ŁA�}�C�i�X�ʉ݂������܂��B" +
                        "�ב֌����ŃR���o�[�W������ɍēx�U�ւ��������Ă������B");
                }
                else if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000801.equals(
                            l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    log.debug("2�d���M�G���[�B");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01799,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "2�d���M�G���[�B");
                }
                else if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000501.equals(
                            l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    log.debug("�Y������؋������������݂��Ȃ��B");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02436,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�Y������؋������������݂��Ȃ��B");          
                }
                else if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000503.equals(
                            l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    log.debug("�ב֌����w�̐U�ւł��B" +
                            "���̋@@�\�͈ב֌������J�݂��A���U�֓��ӏ��ɋL���E�����t������������" +
                            "���q�邵�������p�����������Ƃ͂ǂ�������B");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02675,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�ב֌����w�̐U�ւł��B" +
                        "���̋@@�\�͈ב֌������J�݂��A���U�֓��ӏ��ɋL���E�����t������������" +
                        "���q�邵�������p�����������Ƃ͂ǂ�������B");          
                }
                else
                {
                    log.debug("���̑���FX�V�X�e���G���[�B");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01800,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���̑���FX�V�X�e���G���[�B");
                }
            }
        }

        //1.9�jget�㗝���͎�( )
        Trader l_trader = this.getTrader();

        //1.11�j get������( )
        Date l_datOrderBizDate =  
            WEB3GentradeTradingTimeManagement.getOrderBizDate(); 

        //get��n��(Date, �⏕����, String)
        //[�����̐ݒ�]
        //�������F get������()�̖߂�l
        //�⏕�����F�@@get�⏕����()�̖߂�l
        //��n���ݒ�敪�F�@@getFX�U�֏����}�X�^().��n���ݒ�敪
        Date l_datDeliveryDate =
            l_fXDataControlService.getDeliveryDate(
                l_datOrderBizDate,
                l_subAccount,
                l_fxTransferMasterParams.getDeliveryDateDiv());

        //1.13�j get���iID(Institution)
        //[����] 
        //�،���ЁF �⏕����.get����X().getInstitution()�̖߂�l 
        //============================FinApp==============================
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
        //���o�������}�l�[�W���N���X���擾����B
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        long l_lngProductId =
            l_aioOrderManager.getProductId(l_subAccount.getInstitution());
        
        //1.14. get���o���z�敪(GFT���ʒʒm�d������)
        //[����] 
        //GFT���ʒʒm�d�����ׁF�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������        
        String l_strCashInOutAmountDiv = 
            l_fXDataControlService.getCashInOutAmountDiv(l_request.fxGftResultNoticeTelegramUnit);        

        //get����( )
        //�ڋq�I�u�W�F�N�g���擾����B
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)this.getMainAccount();

        //is�M�p�����J��(�ٍϋ敪 : String)
        //�ڋq���A�M�p����������J�݂��Ă��邩�ǂ����𔻒肷��B
        //[�����̎w��]
        //�ٍϋ敪�F�@@"0"�i�w�薳���j
        boolean l_blnIsMarginAccountEstablished =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //������ == ��n���̏ꍇ�A�������s��
        //����t���[
        //������ == ��n���iget������() == get��n��()�j�̏ꍇ�A�������s��
        if (WEB3DateUtility.compareToDay(l_datOrderBizDate, l_datDeliveryDate) == 0)
        {
            //����t���[
            //�ڋq���M�p�������J�݂��Ă���iis�M�p�����J��()==TRUE�j�ꍇ�A�������s��
            if (l_blnIsMarginAccountEstablished)
            {
                //�����z�F  �Eget���o���z�敪()�̖߂�l��1�̏ꍇ�A
                //             ���N�G�X�g�f�[�^.GFT���ʒʒm�d������.���o���z
                //         �Eget���o���z�敪()�̖߂�l��2�̏ꍇ�A
                //             ���N�G�X�g�f�[�^.GFT���ʒʒm�d������.���o���z2
                //         �Eget���o���z�敪()�̖߂�l��3�̏ꍇ�A
                //             ���N�G�X�g�f�[�^.GFT���ʒʒm�d������.���o���z +
                //             ���N�G�X�g�f�[�^.GFT���ʒʒm�d������.���o���z2 �̍��v�z
                double l_dblCashinAmt = 0D;
                if (WEB3AioCashInOutAmountDivDef.CASHIN_OUT_AMOUNT.equals(l_strCashInOutAmountDiv))
                {
                    l_dblCashinAmt = Double.parseDouble(l_request.fxGftResultNoticeTelegramUnit.cashinoutAmt);
                }
                else if (WEB3AioCashInOutAmountDivDef.CASHIN_OUT_AMOUNT2.equals(l_strCashInOutAmountDiv))
                {
                    l_dblCashinAmt = Double.parseDouble(l_request.fxGftResultNoticeTelegramUnit.cashinoutAmt2);
                }
                else if (WEB3AioCashInOutAmountDivDef.CASHIN_OUT_AMOUNT_AND_AMOUNT2.equals(l_strCashInOutAmountDiv))
                {
                    BigDecimal l_bdCashinAmt = new BigDecimal(l_request.fxGftResultNoticeTelegramUnit.cashinoutAmt);
                    BigDecimal l_bdCashinAmt2 = new BigDecimal(l_request.fxGftResultNoticeTelegramUnit.cashinoutAmt2);

                    l_dblCashinAmt = l_bdCashinAmt.add(l_bdCashinAmt2).doubleValue();
                }

                //submit�ۏ؋��U��(�ڋq, Date, double, String)(�ۏ؋��ւ�
                //�U�փT�[�r�XImpl::submit�ۏ؋��U��)
                //�a�������M�p�ۏ؋��ւ̐U�ւ��s���B
                //[�����̐ݒ�]
                //�ڋq�F�@@get����()�̖߂�l
                //��n���F�@@get��n��()�̖߂�l
                //�����z�F  �Eget���o���z�敪()�̖߂�l��1�̏ꍇ�A
                //             ���N�G�X�g�f�[�^.GFT���ʒʒm�d������.���o���z
                //         �Eget���o���z�敪()�̖߂�l��2�̏ꍇ�A
                //             ���N�G�X�g�f�[�^.GFT���ʒʒm�d������.���o���z2
                //         �Eget���o���z�敪()�̖߂�l��3�̏ꍇ�A
                //             ���N�G�X�g�f�[�^.GFT���ʒʒm�d������.���o���z +
                //             ���N�G�X�g�f�[�^.GFT���ʒʒm�d������.���o���z2 �̍��v�z
                //�Ïؔԍ��F�@@���N�G�X�X�g�f�[�^.�Ïؔԍ�
                //�㗝���͎ҁF�@@get�㗝���͎�()�̖߂�l
                WEB3MarginTransferService l_marginTransferService =
                    (WEB3MarginTransferService)Services.getService(WEB3MarginTransferService.class);

                l_marginTransferService.submitMarginTransfer(
                    l_mainAccount,
                    l_datDeliveryDate,
                    l_dblCashinAmt,
                    l_request.password,
                    l_trader);
            }
        }

        //������ʁF FX�U�֏����}�X�^.�������
        OrderTypeEnum l_orderTypeEnum = l_fxTransferMasterParams.getOrderType();

        //���z�F�@@get���o���z�敪()�̖߂�l��1�A3�̏ꍇ�A 
        //�@@         ���N�G�X�g�f�[�^.GFT���ʒʒm�d������.���o���z 
        //�@@      get���o���z�敪()�̖߂�l��2�̏ꍇ�A 
        //�@@         ���N�G�X�g�f�[�^.GFT���ʒʒm�d������.���o���z2 
        double l_dblCashinoutAmt = 0D;
        if(WEB3AioCashInOutAmountDivDef.CASHIN_OUT_AMOUNT.equals(l_strCashInOutAmountDiv)
            || WEB3AioCashInOutAmountDivDef.CASHIN_OUT_AMOUNT_AND_AMOUNT2.equals(l_strCashInOutAmountDiv))
        {
            l_dblCashinoutAmt = Double.parseDouble(l_request.fxGftResultNoticeTelegramUnit.cashinoutAmt);
        }
        else if(WEB3AioCashInOutAmountDivDef.CASHIN_OUT_AMOUNT2.equals(l_strCashInOutAmountDiv))
        {
            l_dblCashinoutAmt = Double.parseDouble(l_request.fxGftResultNoticeTelegramUnit.cashinoutAmt2);
        }       

        //�E�v�R�[�h�F�@@get��Е�FX�V�X�e������()�̖߂�l.FX�V�X�e���敪�@@=�@@1�i��OP�V�X�e���j
        //  && get���o���z�敪()�̖߂�l��2�̏ꍇ�A71�i����؋����F���؁j
        //��L�ȊO�̏ꍇ�A FX�U�֏����}�X�^.�E�v�R�[�h
        String l_strRemarkCode = l_fxTransferMasterParams.getRemarkCode();
        String l_strFxSystemDiv = l_compFxConditionParams.getFxSystemDiv();
        if (WEB3FxSystemDivDef.FUOP_SYSTEM.equals(l_strFxSystemDiv) &&
            WEB3AioCashInOutAmountDivDef.CASHIN_OUT_AMOUNT2.equals(l_strCashInOutAmountDiv))
        {
            l_strRemarkCode = WEB3FxTransferMasterRemarkCodeDef.TOCK_FUTURES_MARGIN_TOKYO;
        }

        //���o���������e(Trader, OrderTypeEnum,AssetTransferTypeEnum,
        // long, double, String, Date, String, Long, String, String)
        //���o���������e�𐶐�����B 
        //[�����̐ݒ�] 
        //�㗝���͎ҁF�@@get�㗝���͎�()�̖߂�l 
        //������ʁF FX�U�֏����}�X�^.�������
        //�U�փ^�C�v�F�@@1�i�����j 
        //���iID�F�@@get���iID()�̖߂�l 
        //���z�F�@@get���o���z�敪()�̖߂�l��1�A3�̏ꍇ�A 
        //�@@         ���N�G�X�g�f�[�^.GFT���ʒʒm�d������.���o���z 
        //�@@      get���o���z�敪()�̖߂�l��2�̏ꍇ�A 
        //�@@         ���N�G�X�g�f�[�^.GFT���ʒʒm�d������.���o���z2 
        //�L�q�F�@@null
        //�U�֗\����F�@@get��n��()�̖߂�l 
        //���ϋ@@��ID�F�@@null 
        //����ID�F�@@null
        //�E�v�R�[�h�F�@@get��Е�FX�V�X�e������()�̖߂�l.FX�V�X�e���敪�@@=�@@1�i��OP�V�X�e���j
        //  && get���o���z�敪()�̖߂�l��2�̏ꍇ�A71�i����؋����F���؁j
        //��L�ȊO�̏ꍇ�A FX�U�֏����}�X�^.�E�v�R�[�h
        //�E�v���F�@@FX�U�֏����}�X�^.�E�v��
        WEB3AioNewOrderSpec l_aioNewOrderSpec = new WEB3AioNewOrderSpec(
            l_trader,
            l_orderTypeEnum,
            AssetTransferTypeEnum.CASH_IN,
            l_lngProductId,
            l_dblCashinoutAmt,
            null,
            l_datDeliveryDate,
            null,
            null,
            l_strRemarkCode,
            l_fxTransferMasterParams.getRemarkName());

        //1.16�j FX�U�֒����X�V�C���^�Z�v�^(���o���������e)
        //[�����̐ݒ�] 
        //���o���������e�F�@@���o���������e�I�u�W�F�N�g
        WEB3FXTransferOrderUpdateInterceptor l_fxTransferOrderUpdateInterceptor =
            new WEB3FXTransferOrderUpdateInterceptor(l_aioNewOrderSpec);
    
        //1.17�j (*)�v���p�e�B�Z�b�g
        //(*)�ȉ��̂Ƃ���Ƀv���p�e�B���Z�b�g����B
        //�������F�@@get������()�̖߂�l
        l_fxTransferOrderUpdateInterceptor.setOrderBizDate(l_datOrderBizDate);
        
        //��n���F�@@get��n��()�̖߂�l 
        l_fxTransferOrderUpdateInterceptor.setDeliveryDate(l_datDeliveryDate);
        
        //���ʃR�[�h�F�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������.���ʃR�[�h
        l_fxTransferOrderUpdateInterceptor.setOrderRequestNumber(
            l_request.fxGftResultNoticeTelegramUnit.requestNumber);
        //MQ�X�e�[�^�X�F�@@1(���M�ς�)
        l_fxTransferOrderUpdateInterceptor.setMQStatus(WEB3MqStatusDef.MAIL_SENDED);
        
        //1.18�j submit�U�֒���(SubAccount, ProductTypeEnum, OrderTypeEnum, 
        //NewOrderSpec, AioOrderManagerPersistenceInterceptor, long, String)(
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�����^�C�v�F 5�i�����j 
        //������ʁF FX�U�֏����}�X�^.�������
        //�������e�F ���o���������e�I�u�W�F�N�g 
        //�C���^�Z�v�^�F FX�U�֒����X�V�C���^�Z�v�^�I�u�W�F�N�g 
        //����ID�F ���N�G�X�g�f�[�^.����ID 
        //�p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ�        
        
        l_aioOrderManager.submitTransferOrder(
            l_subAccount,
            ProductTypeEnum.CASH,
            l_orderTypeEnum,
            l_aioNewOrderSpec,
            l_fxTransferOrderUpdateInterceptor,
            Long.parseLong(l_request.orderId),
            l_request.password);
            
        //1.19�j (*)get���o���z�敪()�̖߂�l��"3"�̏ꍇ
        if (WEB3AioCashInOutAmountDivDef.CASHIN_OUT_AMOUNT_AND_AMOUNT2.equals(l_strCashInOutAmountDiv))
        {
            //���o���������e(Trader, OrderTypeEnum, AssetTransferTypeEnum, long,
            //  double, String, Date, String, Long, String, String)
            //���o���������e�𐶐�����B 
            //[�����̐ݒ�] 
            //�㗝���͎ҁF�@@get�㗝���͎�()�̖߂�l 
            //������ʁF FX�U�֏����}�X�^.�������
            //�U�փ^�C�v�F�@@1�i�����j 
            //���iID�F�@@get���iID()�̖߂�l 
            //���z�F�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������.���o���z2 
            //�L�q�F�@@null
            //�U�֗\����F�@@get��n��()�̖߂�l 
            //���ϋ@@��ID�F�@@null 
            //����ID�F�@@null
            //�E�v�R�[�h�F�@@71�i����؋����F���؁j
            //�E�v���F�@@FX�U�֏����}�X�^.�E�v��
            WEB3AioNewOrderSpec l_aioNewOrderSpec1 = new WEB3AioNewOrderSpec(
                l_trader,
                l_orderTypeEnum,
                AssetTransferTypeEnum.CASH_IN,
                l_lngProductId,
                Double.parseDouble(l_request.fxGftResultNoticeTelegramUnit.cashinoutAmt2),
                null,
                l_datDeliveryDate,
                null,
                null,
                WEB3FxTransferMasterRemarkCodeDef.TOCK_FUTURES_MARGIN_TOKYO,
                l_fxTransferMasterParams.getRemarkName());

            //1.19.2�j FX�U�֒����X�V�C���^�Z�v�^(���o���������e)
            //FX�U�֒����X�V�C���^�Z�v�^�𐶐�����B 
            //[�����̐ݒ�] 
            //���o���������e�F�@@���o���������e�I�u�W�F�N�g
            WEB3FXTransferOrderUpdateInterceptor l_fxTransferOrderUpdateInterceptor1 =
                 new WEB3FXTransferOrderUpdateInterceptor(l_aioNewOrderSpec1);
    
            //1.19.3�j get�V�K���ʃR�[�h(�،���ЃR�[�h : String, ���X�R�[�h : String, �����^�C�v : ProductTypeEnum)
            //[����] 
            //�،���ЃR�[�h�F�⏕����.�،���ЃR�[�h 
            //���X�R�[�h�F�@@�⏕����.get����X�i�j.getBranchCode() 
            //�����^�C�v�F�@@5�i�����j
            WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService =
                (WEB3HostReqOrderNumberManageService)Services.getService(
                    WEB3HostReqOrderNumberManageService.class); 
            
            String l_strNewNumber1 = l_hostReqOrderNumberManageService.getNewNumber(
                l_subAccount.getInstitution().getInstitutionCode(), 
                l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                ProductTypeEnum.CASH);           
            
            //1.19.4�j (*2)�v���p�e�B�Z�b�g
            //�������F�@@get������()�̖߂�l
            l_fxTransferOrderUpdateInterceptor1.setOrderBizDate(l_datOrderBizDate);
            
            //��n���F�@@get��n��()�̖߂�l 
            l_fxTransferOrderUpdateInterceptor1.setDeliveryDate(l_datDeliveryDate);
            
            //���ʃR�[�h�F�@@get���ʃR�[�h()�̖߂�l 
            l_fxTransferOrderUpdateInterceptor1.setOrderRequestNumber(l_strNewNumber1);
            
            //MQ�X�e�[�^�X�F�@@1(���M�ς�)
            l_fxTransferOrderUpdateInterceptor1.setMQStatus(WEB3MqStatusDef.MAIL_SENDED);    
            
            //1.19.5�j createNewOrderId( )
            //�V�K����ID���̔Ԃ���B
            long l_lngNewOrderId = l_aioOrderManager.createNewOrderId();        
        
            //1.19.6�j submit�U�֒���(SubAccount, ProductTypeEnum, OrderTypeEnum, NewOrderSpec, 
            //AioOrderManagerPersistenceInterceptor, long, String)
            //�U�֒�����o�^����B 
            //[����] 
            //�⏕�����F get�⏕����()�̖߂�l 
            //�����^�C�v�F 5�i�����j 
            //������ʁF FX�U�֏����}�X�^.�������
            //�������e�F ���o���������e�I�u�W�F�N�g 
            //�C���^�Z�v�^�F FX�U�֒����X�V�C���^�Z�v�^�I�u�W�F�N�g 
            //����ID�F createNewOrderId()�̖߂�l 
            //�p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ� 
            l_aioOrderManager.submitTransferOrder(
                l_subAccount,
                ProductTypeEnum.CASH,
                l_orderTypeEnum,
                l_aioNewOrderSpec1,
                l_fxTransferOrderUpdateInterceptor1,
                l_lngNewOrderId,
                l_request.password);            
        }
        
        //1.20�j �]�͍Čv�Z(�⏕���� : �⏕����) 
        //[�����̐ݒ�] 
        //�⏕�����F�@@get�⏕����()�̖߂�l
         
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        WEB3GentradeSubAccount l_gentradeSubAccount = 
            (WEB3GentradeSubAccount)l_subAccount;
        l_service.reCalcTradingPower(l_gentradeSubAccount);
        
        //1.21�j updateGFT�U�֏�(GFT�U�֏�Params, GFT���ʒʒm�d������, String, String)
        //[�����̐ݒ�] 
        //GFT�U�֏�Params�F�@@getGFT�U�֏�()�̖߂�l 
        //GFT���ʒʒm�d�����ׁF�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������ 
        //�X�V���n�\����F�@@(*) 
        //�G���[���R�R�[�h�F�@@0000�i����j 
        //(*)�ȉ��̂Ƃ��� 
        //���N�G�X�g�f�[�^.�m�F�������� == get������()�̏ꍇ�Anull 
        //����ȊO�̏ꍇ�Aget��n��()�̖߂�l  

        if (WEB3DateUtility.compareToDay(l_request.checkDate, l_datOrderBizDate) == 0)
        {            
            l_fXDataControlService.updateGFTTransferStatus(
                l_gftTransferStatusParams, 
                l_request.fxGftResultNoticeTelegramUnit, 
                null,
                WEB3ErrorReasonCodeDef.NORMAL);
        }
        else
        {                      
            l_fXDataControlService.updateGFTTransferStatus(
                l_gftTransferStatusParams, 
                l_request.fxGftResultNoticeTelegramUnit, 
                WEB3DateUtility.formatDate(
                        l_datDeliveryDate, "yyyyMMdd"),
                WEB3ErrorReasonCodeDef.NORMAL);
        }
      
        //1.22�j getOrder(long)
        //[�����̐ݒ�] 
        //arg0�F�@@���N�G�X�g�f�[�^.����ID
        AioOrder l_aioOrder = null;
        try
        {
            l_aioOrder = 
                (AioOrder) l_aioOrderManager.getOrder(Long.parseLong(l_request.orderId));
        }
        catch (NotFoundException l_ex)
        {
            log.error("�������擾����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.23�j createResponse( )
        WEB3FXTransFromFXCompleteResponse 
              l_fxTransFromFXCompleteResponse =
                  (WEB3FXTransFromFXCompleteResponse) l_request.createResponse();  
        
        //1.24�j (*)�v���p�e�B�Z�b�g
        //���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        //�X�V���ԁF�@@����.�X�V����
        l_fxTransFromFXCompleteResponse.lastUpdatedTimestamp = 
            ((AioOrderRow)l_aioOrder.getDataSourceObject()).getLastUpdatedTimestamp();
        //���ʔԍ��F�@@���N�G�X�g�f�[�^.����ID
        l_fxTransFromFXCompleteResponse.orderActionId = l_request.orderId;

        //��n��: get��n��()�̖߂�l 
        l_fxTransFromFXCompleteResponse.deliveryDate = l_datDeliveryDate;
        
        log.exiting(STR_METHOD_NAME);
        return l_fxTransFromFXCompleteResponse;
    }

    /**
     * (submit�����iSOAP�ڑ��j)<BR>
     * �U�֒����̊����������s���B<BR>
     * ���ڑ��ɂčs���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iFX����̐U�ցjsubmit�����iSOAP�ڑ��j�v�Q�ƁB<BR>
     * <BR>
     * @@param l_request ���N�G�X�g�f�[�^
     * @@return WEB3FXTransFromFXCompleteSoapResponse
     * @@throws WEB3BaseException
     */
    protected WEB3FXTransFromFXCompleteSoapResponse submitOrderSoap(
        WEB3FXTransFromFXCompleteSoapRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrderSoap(WEB3FXTransFromFXCompleteSoapRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3FXDataControlService l_fxDataControlService =
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);
        
        //��Е�FX�V�X�e������Params���擾����B
        //
        //[�����̐ݒ�]
        //�،���ЃR�[�h�F get����.getInstitution().getInstitutionCode()
        //���X�R�[�h�F�@@get����.getBranch().getBranchCode()
        //FX�V�X�e���R�[�h�F�@@���N�G�X�g�f�[�^.FX�V�X�e���R�[�h
        CompFxConditionParams l_compFxConditionParams = null;
        try
        {
            l_compFxConditionParams = l_fxDataControlService.getCompFxCondition(
                this.getMainAccount().getInstitution().getInstitutionCode(),
                this.getMainAccount().getBranch().getBranchCode(),
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

        //start����(FX����U�ֈ˗����X�|���X)
        //[����]
        //���N�G�X�g�f�[�^�F����.���N�G�X�g�f�[�^��FX����U�ֈ˗����N�G�X�g�ɃL���X�g��������
        WEB3FXTransFromFXAskingRequest l_fxTransFromFXAskingRequest =
            new WEB3FXTransFromFXAskingRequest();
        l_fxTransFromFXAskingRequest.fxSystemCode = l_request.fxSystemCode;
        l_fxTransFromFXAskingRequest.fxAccInformationUnit =
            l_request.fxAccInformationUnit;
        l_fxTransFromFXAskingRequest.transferAmount =
            l_request.transferAmount;
        l_fxTransFromFXAskingRequest.password = l_request.password;
        l_fxTransFromFXAskingRequest.wolfSession = l_request.wolfSession;
        l_fxTransFromFXAskingRequest.wolfAid = l_request.wolfAid;
        l_fxTransFromFXAskingRequest.regetServiceId = l_request.regetServiceId;
        l_fxTransFromFXAskingRequest.wolfSsid = l_request.wolfSsid;
        l_fxTransFromFXAskingRequest.complianceInfo = l_request.complianceInfo;

        WEB3FXTransFromFXAskingResponse l_fxTransFromFXAskingResponse = null;
        try
        {
            //FX����U�ֈ˗�TransactionCallback(FX����U�ֈ˗����N�G�X�g)
            WEB3FXTransFromFXAskingTransactionCallback l_transactionCallback =
                new WEB3FXTransFromFXAskingTransactionCallback(l_fxTransFromFXAskingRequest);
    
            //getDefaultProcessor( )
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            //doTransaction(�g�����U�N�V�������� : int, �g�����U�N�V�����R�[���o�b�N : TransactionCallback)
            //[����]
            //�g�����U�N�V���������F�@@TX_CREATE_NEW
            //�g�����U�N�V�����R�[���o�b�N�F�@@FX����U�ֈ˗�TransactionCallback�C���X�^���X
            l_fxTransFromFXAskingResponse = (WEB3FXTransFromFXAskingResponse) l_queryProcessor.doTransaction(
                TransactionalInterceptor.TX_CREATE_NEW, l_transactionCallback);
        }
        catch (DataNetworkException l_ex)
        {
            log.error( "DB�A�N�Z�X�G���[�B" , l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�A�N�Z�X�G���[" , l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //do�U�֎��s
        WEB3FXTransConnection l_controlService =
            (WEB3FXTransConnection)Services.getService(WEB3FXTransConnection.class);
        WEB3FXGftResultNoticeTelegramUnit  l_fxGftResultNoticeTelegramUnit =
            l_controlService.doTransfer(l_compFxConditionParams, l_fxTransFromFXAskingResponse.fxGftAskingTelegramUnit);

        //�C���X�^���X����
        //�R���X�g���N�^�ɂăC���X�^���X��������B
        WEB3FXTransFromFXCompleteRequest l_fxTransFromFXCompleteRequest =
            new WEB3FXTransFromFXCompleteRequest();

        //�v���p�e�B�Z�b�g
        //FX����U�֊������N�G�X�g�̃v���p�e�B���Z�b�g����B
        //GFT���ʒʒm�d�����ׁF �����������ʒʒm�d������
        l_fxTransFromFXCompleteRequest.fxGftResultNoticeTelegramUnit =
            l_fxGftResultNoticeTelegramUnit;
        //����ID�F FX����U�ֈ˗����X�|���X.����ID
        l_fxTransFromFXCompleteRequest.orderId =
            l_fxTransFromFXAskingResponse.orderId;
        //�m�F���������F FX����U�ֈ˗����X�|���X.�m�F��������
        l_fxTransFromFXCompleteRequest.checkDate =
            l_fxTransFromFXAskingResponse.checkDate;
        //�Ïؔԍ��FFX����U�֊������N�G�X�g�iSOAP�ڑ��j.�Ïؔԍ�
        l_fxTransFromFXCompleteRequest.password = l_request.password;
        //FX�V�X�e���R�[�h�FFX����U�֊������N�G�X�g�iSOAP�ڑ��j.FX�V�X�e���R�[�h
        l_fxTransFromFXCompleteRequest.fxSystemCode = l_request.fxSystemCode;

        //submit����(FX����U�֊������N�G�X�g)
        //�U�֒��������������s���B
        //[����]
        //���N�G�X�g�f�[�^�F ��������FX����U�֊������N�G�X�g
        WEB3FXTransFromFXCompleteResponse l_fxTransFromFXCompleteResponse = null;
        try
        {
            //1.18 FX����U�֊���TransactionCallback(FX����U�֊������N�G�X�g)
            WEB3FXTransFromFXCompleteTransactionCallback l_transactionCallback =
                new WEB3FXTransFromFXCompleteTransactionCallback(l_fxTransFromFXCompleteRequest);
    
            //1.19 getDefaultProcessor( )
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            //1.20 doTransaction(�g�����U�N�V�������� : int, �g�����U�N�V�����R�[���o�b�N : TransactionCallback)
            //[����]
            //�g�����U�N�V���������F�@@TX_CREATE_NEW
            //�g�����U�N�V�����R�[���o�b�N�F�@@FX����U�֊���TransactionCallback�C���X�^���X
            l_fxTransFromFXCompleteResponse = (WEB3FXTransFromFXCompleteResponse) l_queryProcessor.doTransaction(
                TransactionalInterceptor.TX_CREATE_NEW, l_transactionCallback);
        }
        catch (DataNetworkException l_ex)
        {
            log.error( "DB�A�N�Z�X�G���[�B" , l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�A�N�Z�X�G���[" , l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //createResponse( )
        //���X�|���X�f�[�^�𐶐�����B
        WEB3FXTransFromFXCompleteSoapResponse l_response =
            (WEB3FXTransFromFXCompleteSoapResponse)l_request.createResponse();

        //�v���p�e�B�Z�b�g
        //�ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        //�X�V���ԁF FX����U�֊������X�|���X.�X�V����
        l_response.lastUpdatedTimestamp =
            l_fxTransFromFXCompleteResponse.lastUpdatedTimestamp;
        //���ʔԍ��F FX����U�֊������X�|���X.���ʔԍ�
        l_response.orderActionId = l_fxTransFromFXCompleteResponse.orderActionId;
        //��n���F FX����U�֊������X�|���X.��n��
        l_response.deliveryDate = l_fxTransFromFXCompleteResponse.deliveryDate;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�����敪)<BR>
     * �P�j�����DFX�V�X�e���R�[�h�F06�iHits��OP�U�ցj�̏ꍇ <BR> 
     * �@@�E05�i�����F��OP�j��ԋp����B <BR> 
     * <BR> 
     * �Q�j����ȊO�̏ꍇ�́A02�i�����FFX�j��ԋp����B<BR>  
     * ��������null�̏ꍇ���܂ށB <BR> 
     * <BR>
     * @@param l_strFxSystemCode - (FX�V�X�e���R�[�h)<BR>
     * FX�V�X�e���R�[�h
     * @@return string
     */
    public String getOperationDiv(String l_strFxSystemCode) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "getOperationDiv(String l_strFxSystemCode)";
        log.entering(STR_METHOD_NAME);
 
        //�P�j�����DFX�V�X�e���R�[�h�F06�iHits��OP�U�ցj�̏ꍇ 
        //�@@�E05�i�����F��OP�j��ԋp����B 
        if (WEB3FxSystemCodeDef.HITS_FUOP_TRANSFER.equals(l_strFxSystemCode))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3GftMessageOperationDef.CASH_IN_FUOP;
        }
        
        //�Q�j����ȊO�̏ꍇ�́A02�i�����FFX�j��ԋp����B 
        //��������null�̏ꍇ���܂ށB 
        log.exiting(STR_METHOD_NAME);
        return WEB3GftMessageOperationDef.CASH_IN_FX;        
    }
    
    /**
     * (is��n��)<BR>
     * ��n���Z�b�g�𔻒肷��B <BR> 
     * <BR>
     * [�߂�l] <BR> 
     * true�F��n�����Z�b�g����B <BR>
     * false�F��n�����Z�b�g���Ȃ��B <BR>
     * <BR>
     * �ȉ��̏����ŁA���X�p�v���t�@@�����X�e�[�u�����烌�R�[�h���擾����B<BR>  
     * <BR>
     * [����]  <BR>
     * ���XID = ������.���XID <BR> 
     * �v���t�@@�����X�� = "fx.deliverydate.insert.check"<BR>  
     * �v���t�@@�����X���̘A�� = 1  <BR>
     * <BR>
     * �Q�j�擾�������R�[�h.�v���t�@@�����X�̒l == �h��n�����Z�b�g����h �̏ꍇ�Atrue ��ԋp����B<BR>  
     * <BR>
     * �R�j����ȊO�̏ꍇ�́Afalse��ԋp����B<BR>  
     * �����R�[�h���擾�ł��Ȃ������ꍇ���܂ށB<BR>
     * <BR>
     * @@param l_lngBranchId - (���XID)<BR>
     * ���XID
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isDeliveryDate(long l_lngBranchId) throws WEB3BaseException 
    {
        String STR_METHOD_NAME = "isDeliveryDate(long l_lngBranchId)";
        log.entering(STR_METHOD_NAME);
        
        //�ȉ��̏����ŁA���X�p�v���t�@@�����X�e�[�u�����烌�R�[�h���擾����B  
        //[����]  
        //���XID = ������.���XID  
        //�v���t�@@�����X�� = "fx.deliverydate.insert.check"  
        //�v���t�@@�����X���̘A�� = 1 
        BranchPreferencesRow l_branchReferencesRow = null;
        try
        {
            l_branchReferencesRow = BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(              
                l_lngBranchId,
                WEB3BranchPreferencesNameDef.FX_DELIVERY_DATE_INSERT_CHECK,
                1);
        } 
        catch (DataNetworkException l_dqex) 
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���:", l_dqex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqex.getMessage(),
                l_dqex);
        }
        catch (DataQueryException l_ex) 
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }        
        
        //�Q�j�擾�������R�[�h.�v���t�@@�����X�̒l == �h��n�����Z�b�g����h �̏ꍇ�Atrue ��ԋp����B
        if (l_branchReferencesRow != null && 
            WEB3FxDeliveryDateInsertCheckDef.CHECK.equals(l_branchReferencesRow.getValue()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        //�R�j����ȊO�̏ꍇ�́Afalse��ԋp����B  
        //�����R�[�h���擾�ł��Ȃ������ꍇ���܂ށB
        log.exiting(STR_METHOD_NAME);
        return false;        
    }

    /**
     * (FX����U�ֈ˗�TransactionCallback�N���X)<BR>
     */
    public class WEB3FXTransFromFXAskingTransactionCallback implements TransactionCallback
    {
        /**
         * ���O���[�e�B���e�B<BR>
         */
        private WEB3LogUtility l_log = WEB3LogUtility.getInstance(WEB3FXTransFromFXAskingTransactionCallback.class);

        /**
         * �f�t�H���g�R���X�g���N�^<BR>
         * ����.���N�G�X�g�f�[�^���Y���̕ϐ��ɕۑ�����B<BR>
         * @@param l_request - (���N�G�X�g�f�[�^)<BR>
         * @@return WEB3FXTransFromFXAskingTransactionCallback
         * @@roseuid 415A6F3F0270
         */
        public WEB3FXTransFromFXAskingTransactionCallback(WEB3FXTransFromFXAskingRequest l_request)
        {
            this.l_fxTransFromFXAskingRequest = l_request;
        }

        /**
         * (FX����U�ֈ˗����N�G�X) <BR>
         * FX����U�ֈ˗����N�G�X <BR>
         */
        public WEB3FXTransFromFXAskingRequest l_fxTransFromFXAskingRequest;
        
        /**
         * �U�֒����̈˗��������s���B <BR>
         *  <BR>
         * �P�j���������b�N����B <BR>
         *  <BR>
         *    �g���A�J�E���g�}�l�[�W��.lock����()���R�[������B <BR>
         *  <BR>
         *    ��������OpLoginSecurityService���ҏW�B <BR>
         *  <BR>
         * �Q�jFX����U�փT�[�r�XImpl.start����()���R�[������B <BR>
         *  <BR>
         *    [����] <BR>
         *    ���N�G�X�g�f�[�^�F this.���N�G�X�g�f�[�^ <BR>
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 413C1FDC003F
         */
        public Object process() throws 
            DataNetworkException,
            DataQueryException,
            DataCallbackException
        {
            String STR_METHOD_NAME = " process()";
            l_log.entering(STR_METHOD_NAME);

            WEB3FXTransFromFXAskingResponse l_fxTransFromFXAskingResponse = null;
            //�U�֒����̈˗��������s���B
            //�P�j���������b�N����B
            //   �g���A�J�E���g�}�l�[�W��.lock����()���R�[������B
            //   ��������OpLoginSecurityService���ҏW�B
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
            OpLoginSecurityService l_opLoginSecurityService = 
                (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
            
            long l_lngAccountId = l_opLoginSecurityService.getAccountId();
            try
            {
                MainAccount l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);
                //�،���ЃR�[�h���擾����
                String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
                //���X�R�[�h���擾����
                String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
                //�����R�[�h���擾����
                String l_accountCode = l_mainAccount.getAccountCode(); 
                
                l_accountManager.lockAccount(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_accountCode);
            
                //�Q�jFX����U�փT�[�r�XImpl.start����()���R�[������B
                //   [����]
                //   ���N�G�X�g�f�[�^�F this.���N�G�X�g�f�[�^
                l_fxTransFromFXAskingResponse = startOrder(this.l_fxTransFromFXAskingRequest);
            }
            catch (NotFoundException l_ex)
            {
                log.error("error in l_accountManager.getMainAccount", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }            
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            l_log.entering(STR_METHOD_NAME);
            return l_fxTransFromFXAskingResponse;
        }
    }

    /**
     * (FX����U�֊���TransactionCallback�N���X)<BR>
     */
    public class WEB3FXTransFromFXCompleteTransactionCallback implements TransactionCallback
    {
        /**
         * ���O���[�e�B���e�B<BR>
         */
        private WEB3LogUtility l_log = WEB3LogUtility.getInstance(WEB3FXTransFromFXCompleteTransactionCallback.class);

        /**
         * �f�t�H���g�R���X�g���N�^<BR>
         * ����.���N�G�X�g�f�[�^���Y���̕ϐ��ɕۑ�����B<BR>
         * @@param l_request - (���N�G�X�g�f�[�^)<BR>
         * @@return WEB3FXTransFromFXCompleteTransactionCallback
         * @@roseuid 415A6F3F0270
         */
        public WEB3FXTransFromFXCompleteTransactionCallback(WEB3FXTransFromFXCompleteRequest l_request)
        {
            this.l_fxTransFromFXCompleteRequest = l_request;
        }

        /**
         * (FX����U�֊������N�G�X�g) <BR>
         * FX����U�֊������N�G�X�g <BR>
         */
        public WEB3FXTransFromFXCompleteRequest l_fxTransFromFXCompleteRequest;
        
        /**
         * �U�֒����̓o�^�������s���B <BR>
         *  <BR>
         * �P�j���������b�N����B <BR>
         *  <BR>
         *    �g���A�J�E���g�}�l�[�W��.lock����()���R�[������B <BR>
         *  <BR>
         *    ��������OpLoginSecurityService���ҏW�B <BR>
         *  <BR>
         * �Q�jFX����U�փT�[�r�XImpl.submit����()���R�[������B <BR>
         *  <BR>
         *    [����] <BR>
         *    ���N�G�X�g�f�[�^�F this.���N�G�X�g�f�[�^ <BR>
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 413C1FDC003F
         */
        public Object process() throws 
            DataNetworkException,
            DataQueryException,
            DataCallbackException
        {
            String STR_METHOD_NAME = " process()";
            l_log.entering(STR_METHOD_NAME);

            WEB3FXTransFromFXCompleteResponse l_fxTransFromFXCompleteResponse = null;
            //�U�֒����̓o�^�������s���B
            //�P�j���������b�N����B
            //   �g���A�J�E���g�}�l�[�W��.lock����()���R�[������B
            //   ��������OpLoginSecurityService���ҏW�B
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
            OpLoginSecurityService l_opLoginSecurityService = 
                (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
            
            long l_lngAccountId = l_opLoginSecurityService.getAccountId();
            try
            {
                MainAccount l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);
                //�،���ЃR�[�h���擾����
                String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
                //���X�R�[�h���擾����
                String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
                //�����R�[�h���擾����
                String l_accountCode = l_mainAccount.getAccountCode(); 
                
                l_accountManager.lockAccount(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_accountCode);
            
                //�Q�jFX����U�փT�[�r�XImpl.submit����()���R�[������B
                //   [����]
                //   ���N�G�X�g�f�[�^�F this.���N�G�X�g�f�[�^
                l_fxTransFromFXCompleteResponse = submitOrder(this.l_fxTransFromFXCompleteRequest);
            }
            catch (NotFoundException l_ex)
            {
                log.error("error in l_accountManager.getMainAccount", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }            
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            l_log.entering(STR_METHOD_NAME);
            return l_fxTransFromFXCompleteResponse;
        }
    }
}@
