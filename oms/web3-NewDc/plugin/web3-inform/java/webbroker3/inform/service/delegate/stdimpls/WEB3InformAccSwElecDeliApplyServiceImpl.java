head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.46.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformAccSwElecDeliApplyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����ؑցE�d�q��t�\���T�[�r�XImpl(WEB3InformAccSwElecDeliApplyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/19 �����F (���u) �V�K�쐬 �d�l�ύX���f��098 �c�a�X�V�d�l 014
Revision History : 2007/08/13 ���^�] (���u) �d�l�ύX���f��104
Revision History : 2007/08/30 ���^�] (���u) �d�l�ύX���f��107 ���f��108
Revision History : 2007/09/19 ���^�] (���u) �d�l�ύX���f��111
Revision History : 2007/10/11 ���� (SCS) �d�l�ύX���f��120
Revision History : 2007/10/23 �����F (���u) ���f��121
Revision History : 2009/02/12 SCS�哈 �d�l�ύX���f��136
*/
package webbroker3.inform.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.accountopen.data.HostConditionRegVoucherParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3VoucherCreateStatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.inform.WEB3Inform;
import webbroker3.inform.WEB3InformClientRequestService;
import webbroker3.inform.WEB3InformConditionRegVoucher;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.data.VariousInformRow;
import webbroker3.inform.message.WEB3AdminInformAccSwitchElecDeliAppDtInfo;
import webbroker3.inform.message.WEB3AdminInformAccSwitchElecDeliApplyInfo;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyCmpRequest;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyCmpResponse;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyConfRequest;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyConfResponse;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyInpRequest;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyInpResponse;
import webbroker3.inform.service.delegate.WEB3InformAccSwElecDeliApplyCommonService;
import webbroker3.inform.service.delegate.WEB3InformAccSwElecDeliApplyService;
import webbroker3.inform.service.delegate.WEB3InformHostReqOrderNumberManageService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (�����ؑցE�d�q��t�\���T�[�r�XImpl)<BR>
 * �����ؑցE�d�q��t�\���T�[�r�X�����N���X<BR>
 *
 * @@author �����F
 * @@version 1.0
 */
public class WEB3InformAccSwElecDeliApplyServiceImpl extends WEB3InformClientRequestService
    implements WEB3InformAccSwElecDeliApplyService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3InformAccSwElecDeliApplyServiceImpl.class);

    /**
     * �����ؑցE�d�q��t�\���T�[�r�X�������s���B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        WEB3GenResponse l_response = null;

        //�����ؑցE�d�q��t�\�����̓��N�G�X�g
        if (l_request instanceof WEB3InformAccSwElecDeliApplyInpRequest)
        {
            l_response = this.getInputScreen((WEB3InformAccSwElecDeliApplyInpRequest)l_request);
        }
        //�����ؑցE�d�q��t�\���m�F���N�G�X�g
        else if (l_request instanceof WEB3InformAccSwElecDeliApplyConfRequest)
        {
            l_response = this.validateApply((WEB3InformAccSwElecDeliApplyConfRequest)l_request);
        }
        //�����ؑցE�d�q��t�\���������N�G�X�g
        else if (l_request instanceof WEB3InformAccSwElecDeliApplyCmpRequest)
        {
            l_response = this.submitApply((WEB3InformAccSwElecDeliApplyCmpRequest)l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get���͉��)<BR>
     * ���͉�ʂ̎擾���s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�����ؑցE�d�q��t�\���jget���͉�ʁv �Q�ƁB<BR>
     * =============================================== <BR>
     * �@@�@@�@@�@@��̈ʒu�@@�@@:get�ڋq()�ɂĊY���f�[�^���擾�ł��Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@��O��throw����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException  <BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_01035<BR>
     * =============================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3InformAccSwElecDeliApplyInpResponse
     * @@throws WEB3BaseException
     */
    protected WEB3InformAccSwElecDeliApplyInpResponse getInputScreen(
        WEB3InformAccSwElecDeliApplyInpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3InformAccSwElecDeliApplyInpRequest)";
        log.entering(STR_METHOD_NAME);

        //validate������t�\
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //validate( )(�����ؑցE�d�q��t�\�����̓��N�G�X�g::validate)
        l_request.validate();

        //getAccountId( )
        OpLoginSecurityService l_service =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        long l_lngAccountId = l_service.getAccountId();

        //get�ڋq()�ɂĊY���f�[�^���擾�ł��Ȃ��ꍇ�A��O��throw����
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            l_mainAccount =
                (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_lngAccountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y������ڋq�����݂��܂���B");
        }

        //getDataSourceObject
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();

        WEB3InformAccSwElecDeliApplyCommonService l_accSwElecDeliApplyCommonService =
            (WEB3InformAccSwElecDeliApplyCommonService)Services.getService(
                WEB3InformAccSwElecDeliApplyCommonService.class);

        //create�����ؑցE�d�q��t�\�����(�ڋq)
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo =
            l_accSwElecDeliApplyCommonService.createAccSwitchElecDeliApplyInfo(l_mainAccount);

        //createResponse
        WEB3InformAccSwElecDeliApplyInpResponse l_response =
            (WEB3InformAccSwElecDeliApplyInpResponse)l_request.createResponse();

        //�v���p�e�B�Z�b�g
        //(*1)���X�|���X�f�[�^�v���p�e�B�Ɉȉ��̒ʂ�l���Z�b�g����B
        //�@@ ���X�|���X�f�[�^.���X�R�[�h  �� �ڋq�s.���X�R�[�h
        l_response.branchCode = l_mainAccountRow.getBranchCode();
        // �@@���X�|���X�f�[�^.�ڋq�R�[�h  �� �ڋq�s.�����R�[�h
        l_response.accountCode = l_mainAccountRow.getAccountCode();
        // �@@���X�|���X�f�[�^.�ڋq��    �� �ڋq�s.���O�i�c���j
        l_response.accountName = l_mainAccountRow.getFamilyName();
        // �@@���X�|���X�f�[�^.�ύX�O���  �� create�����ؑցE�d�q��t�\�����()�̖߂�l
        l_response.beforeInfo = l_applyInfo;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�\��)<BR>
     * �����ؑցE�d�q��t�\���m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�����ؑցE�d�q��t�\���jvalidate�\���v �Q�ƁB<BR>
     * =============================================== <BR>
     * �@@�@@�@@�@@��̈ʒu�@@�@@:get�ڋq()�ɂĊY���f�[�^���擾�ł��Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@��O��throw����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException  <BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_01035<BR>
     * =============================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3InformAccSwElecDeliApplyConfResponse
     * @@throws WEB3BaseException
     */
    protected WEB3InformAccSwElecDeliApplyConfResponse validateApply(
        WEB3InformAccSwElecDeliApplyConfRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateApply(WEB3InformAccSwElecDeliApplyConfRequest)";
        log.entering(STR_METHOD_NAME);

        //validate������t�\
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        // validate( )(�����ؑցE�d�q��t�\���m�F���N�G�X�g::validate)
        l_request.validate();

        //getAccountId( )
        OpLoginSecurityService l_service =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        long l_lngAccountId = l_service.getAccountId();

        //get�ڋq()�ɂĊY���f�[�^���擾�ł��Ȃ��ꍇ�A��O��throw����
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            l_mainAccount =
                (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_lngAccountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y������ڋq�����݂��܂���B");
        }

        //getDataSourceObject( )
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();

        //get�e��A���ꗗ(String, String, String, String)
        //�e��A���ꗗ���擾����B
        //[����]
        //�،���ЃR�[�h�F�@@�ڋq�s.�،���ЃR�[�h
        //���X�R�[�h�F�@@�ڋq�s.���X�R�[�h
        //�A����ʁF�@@���N�G�X�g�f�[�^.�A�����
        //�ڋq�R�[�h�F�@@�ڋq�s.�����R�[�h
        WEB3InformAccSwElecDeliApplyCommonService l_accSwElecDeliApplyCommonService =
            (WEB3InformAccSwElecDeliApplyCommonService)Services.getService(
                WEB3InformAccSwElecDeliApplyCommonService.class);
        List l_lisVariousInforms = l_accSwElecDeliApplyCommonService.getVariousInformList(
            l_mainAccountRow.getInstitutionCode(),
            l_mainAccountRow.getBranchCode(),
            l_request.informType,
            l_mainAccountRow.getAccountCode());

        //get�e��A���ꗗ�̖߂�l() != null �̏ꍇ�A�ȉ��̏������s���B
        if (l_lisVariousInforms != null)
        {
            //is�g���K���s(�������� : String)
            //SONAR�փg���K�𔭍s�ł��鎞�ԑт��𔻒肷��B
            //[����]
            //���������F 0�iDEFAULT�j
            boolean l_blnIsSubmitMarketTrigger = WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
                WEB3OrderingConditionDef.DEFAULT);

            //validate�`�[�쐬(String, boolean)
            //�`�[�쐬���\���`�F�b�N���s���B
            //[����]
            //�쐬�󋵁F�@@get�e��A���ꗗ.get(0).get�쐬��()�̖߂�l
            //�g���K�[���s�敪�F�@@is�g���K�[���s()�̖߂�l
            l_accSwElecDeliApplyCommonService.validateVoucherMake(
                ((VariousInformRow)l_lisVariousInforms.get(0)).getStatus(),
                l_blnIsSubmitMarketTrigger);
        }

        //create�����ؑցE�d�q��t�\�����(�ڋq)
        //�����ؑցE�d�q��t�\����񃁃b�Z�[�W�f�[�^���쐬����B
        //[����]
        //�ڋq�F�@@get�ڋq()�̖߂�l
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo =
            l_accSwElecDeliApplyCommonService.createAccSwitchElecDeliApplyInfo(l_mainAccount);

        //validate�����ؑցE�d�q��t�\�����(�����ؑցE�d�q��t�\�����, �����ؑցE�d�q��t�\�����)
        //[����]
        //�ύX�O���F�@@create�����ؑցE�d�q��t�\�����()�̖߂�l
        //�ύX����F�@@���N�G�X�g�f�[�^.�ύX����
        l_accSwElecDeliApplyCommonService.validateAccSwitchElecDeliApplyInfo(
            l_applyInfo,
            l_request.changedInfo);

        WEB3InformAccSwElecDeliApplyConfResponse l_response =
            (WEB3InformAccSwElecDeliApplyConfResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�\��)<BR>
     * �����ؑցE�d�q��t�\���m�F�������s���B  <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�����ؑցE�d�q��t�\���jsubmit�\���v �Q�ƁB <BR>
     * =============================================== <BR>
     * �@@�@@�@@�@@��̈ʒu�@@�@@:get�ڋq()�ɂĊY���f�[�^���擾�ł��Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@��O��throw����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException  <BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_01035<BR>
     * =============================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3InformAccSwElecDeliApplyCmpResponse
     * @@throws WEB3BaseException
     */
    protected WEB3InformAccSwElecDeliApplyCmpResponse submitApply(
        WEB3InformAccSwElecDeliApplyCmpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitApply(WEB3InformAccSwElecDeliApplyCmpRequest)";
        log.entering(STR_METHOD_NAME);

        //validate������t�\
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //validate
        l_request.validate();

        //getAccountId( )
        OpLoginSecurityService l_service =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        long l_lngAccountId = l_service.getAccountId();

        //get�ڋq()�ɂĊY���f�[�^���擾�ł��Ȃ��ꍇ�A��O��throw����
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            l_mainAccount =
                (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_lngAccountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y������ڋq�����݂��܂���B");
        }

        //getDataSourceObject
        MainAccountParams l_mainAccountParams = new MainAccountParams(
            (MainAccountRow)l_mainAccount.getDataSourceObject());
        
        //lock����(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
        l_accountManager.lockAccount(
                l_mainAccountParams.getInstitutionCode(),
                l_mainAccountParams.getBranchCode(),
                l_mainAccountParams.getAccountCode());

        //get�⏕����
        SubAccount l_subAccount = this.getSubAccount();

        //get�㗝���͎�
        Trader l_trader = this.getTrader();

        //validate����p�X���[�h(�㗝���͎� : Trader, �⏕���� : SubAccount, �p�X���[�h : String)
        WEB3GentradeOrderValidator l_validator = new WEB3GentradeOrderValidator();
        OrderValidationResult l_result =
            l_validator.validateTradingPassword(l_trader, l_subAccount, l_request.password);
        if (l_result.getProcessingResult().isFailedResult())
        {
            log.debug(STR_METHOD_NAME + l_result.getProcessingResult().getErrorInfo().getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                l_result.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //get�e��A���ꗗ(String, String, String, String)
        //[����]
        //�،���ЃR�[�h�F�@@�ڋq�s.�،���ЃR�[�h
        //���X�R�[�h�F�@@�ڋq�s.���X�R�[�h
        //�A����ʁF�@@���N�G�X�g�f�[�^.�A�����
        //�ڋq�R�[�h�F�@@�ڋq�s.�����R�[�h
        WEB3InformAccSwElecDeliApplyCommonService l_accSwElecDeliApplyCommonService =
            (WEB3InformAccSwElecDeliApplyCommonService)Services.getService(
                WEB3InformAccSwElecDeliApplyCommonService.class);
        List l_lisVariousInforms = l_accSwElecDeliApplyCommonService.getVariousInformList(
            l_mainAccountParams.getInstitutionCode(),
            l_mainAccountParams.getBranchCode(),
            l_request.informType,
            l_mainAccountParams.getAccountCode());

        //is�g���K���s(�������� : String)
        //SONAR�փg���K�𔭍s�ł��鎞�ԑт��𔻒肷��B
        //[����]
        //���������F 0�iDEFAULT�j
        boolean l_blnIsSubmitMarketTrigger = WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
            WEB3OrderingConditionDef.DEFAULT);

        //get�e��A���ꗗ�̖߂�l() != null �̏ꍇ�A�ȉ��̏������s���B
        if (l_lisVariousInforms != null)
        {
            //validate�`�[�쐬(String, boolean)
            //�`�[�쐬���\���`�F�b�N���s���B
            //[����]
            //�쐬�󋵁F�@@get�e��A���ꗗ.get(0).get�쐬��()�̖߂�l
            //�g���K�[���s�敪�F�@@is�g���K�[���s()�̖߂�l
            l_accSwElecDeliApplyCommonService.validateVoucherMake(
                ((VariousInformRow)l_lisVariousInforms.get(0)).getStatus(),
                l_blnIsSubmitMarketTrigger);
        }

        //create�����ؑցE�d�q��t�\�����(�ڋq)
        //[����]
        //�ڋq�F�@@get�ڋq()�̖߂�l
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo =
            l_accSwElecDeliApplyCommonService.createAccSwitchElecDeliApplyInfo(l_mainAccount);

        //validate�����ؑցE�d�q��t�\�����(�����ؑցE�d�q��t�\�����, �����ؑցE�d�q��t�\�����)
        //�����ؑցE�d�q��t�\�����̕ύX���e���`�F�b�N����B
        //[����]
        //�ύX�O���F�@@create�����ؑցE�d�q��t�\�����()�̖߂�l
        //�ύX����F�@@���N�G�X�g�f�[�^.�ύX����
        l_accSwElecDeliApplyCommonService.validateAccSwitchElecDeliApplyInfo(
            l_applyInfo,
            l_request.changedInfo);

        //create�����ؑցE�d�q��t�\�����t���(String, String)
        WEB3AdminInformAccSwitchElecDeliAppDtInfo l_appDtInfo =
            l_accSwElecDeliApplyCommonService.createAccSwitchElecDeliAppDtInfo(
                l_request.changedInfo.taxType, l_request.changedInfo.marginTaxType);

        //create�e��A��(�ڋq, �����ؑցE�d�q��t�\�����, �����ؑցE�d�q��t�\�����t���, String)
        //[����]
        //�ڋq�F�@@get�ڋq()�̖߂�l
        //�ύX����F�@@���N�G�X�g�f�[�^.�ύX����
        //���t���F�@@create�����ؑցE�d�q��t�\�����t���()�̖߂�l
        //�A����ʁF�@@���N�G�X�g�f�[�^.�A�����
        WEB3Inform l_inform =
            l_accSwElecDeliApplyCommonService.createVariousInform(
                l_mainAccount,
                l_request.changedInfo,
                l_appDtInfo,
                l_request.informType);

        //get�V�K���ʃR�[�h(String, String)
        WEB3InformHostReqOrderNumberManageService l_hostReqOrderNumberManageService =
            (WEB3InformHostReqOrderNumberManageService)Services.getService(
                WEB3InformHostReqOrderNumberManageService.class);
        String l_strNewOrderRequestCode =
            l_hostReqOrderNumberManageService.getNewOrderRequestCode(
                l_mainAccountParams.getInstitutionCode(), l_request.informType);

        //saveNew�e��A��(String, String, String)
        //[����]
        //�X�V�҃R�[�h�F�@@�i�ȉ��̂Ƃ���j
        //  �|�㗝���͂̏ꍇ�A�㗝���͎�.���҃R�[�h
        //  �|�ȊO�̏ꍇ�Anull
        //���ʃR�[�h�F�@@get�V�K���ʃR�[�h()�̖߂�l
        //�쐬�󋵁F�@@3:��t����
        String l_traderCode = null;
        if (l_trader != null)
        {
            l_traderCode = l_trader.getTraderCode();
        }
        l_inform.saveNewInform(
            l_traderCode,
            l_strNewOrderRequestCode,
            WEB3VoucherCreateStatusDef.ACCEPT_COMPLETE);

        // get�V�K���ʃR�[�h(�،���ЃR�[�h : String, ���X�R�[�h : String, �����^�C�v : ProductTypeEnum)
        //SONAR�ʒm�L���[�ɏ������ލۂɕK�v�Ȏ��ʃR�[�h���̔Ԃ���B
        //[����]
        //�،���ЃR�[�h�F �ڋq�s.�،���ЃR�[�h
        //���X�R�[�h�F �ڋq�s.���X�R�[�h
        //�����^�C�v�F ProductTypeEnum.���̑�
        WEB3HostReqOrderNumberManageService l_reqOrderNumberManageService =
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);

        String l_strNewNumber = l_reqOrderNumberManageService.getNewNumber(
            l_mainAccountParams.getInstitutionCode(),
            l_mainAccountParams.getBranchCode(),
            ProductTypeEnum.OTHER);

        //create���E��c�d�q��t�E��������o�^�s(�ڋq, �e��A��params)
        //���E��c�d�q��t�E��������o�^�V�K�s�𐶐�����B
        //[����]
        //�ڋq�F�@@get�ڋq()�̖߂�l
        //�e��A���s�F�@@�e��A���I�u�W�F�N�g.getDataSourceObject()�̖߂�l
        VariousInformParams l_variousInformParams = (VariousInformParams)l_inform.getDataSourceObject();

        HostConditionRegVoucherParams l_hostConditionRegVoucherParams =
            l_accSwElecDeliApplyCommonService.createHostConditionRegVoucherParams(
                l_mainAccount,
                l_variousInformParams);

        //���E��c�d�q��t�E��������o�^(���E��c�d�q��t�E��������o�^Params, String)
        //�R���X�g���N�^
        //[����]
        //���E��c�d�q��t�E��������o�^�s : create���E��c�d�q��t�E��������o�^�s()�̖߂�l
        //�`�[���ʃR�[�h : �������ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h()�̖߂�l
        WEB3InformConditionRegVoucher l_informConditionRegVoucher = new WEB3InformConditionRegVoucher(
            l_hostConditionRegVoucherParams,
            l_strNewNumber);

        // is�`�[�쐬
        boolean l_blnIsVouchMake = l_informConditionRegVoucher.isVoucherMake();

        Map l_map = new HashMap();
        //is�`�[�쐬()�̖߂�l��true�̏ꍇ
        if (l_blnIsVouchMake)
        {
            //save���E��c�d�q��t�E��������o�^�`�[�L���[( )
            //���E��c�d�q��t�E��������o�^�iGI311�j�L���[�e�[�u���Ƀ��R�[�h���쐬����B
            l_informConditionRegVoucher.saveHostConditionRegVoucher();

            // is�g���K���s == true �̏ꍇ�A�ȉ��̏��������s
            if (l_blnIsSubmitMarketTrigger)
            {
                //�g���K���s(String, String)
                //�g���K�𔭍s����B
                //[����]
                //�،���ЃR�[�h�F�@@�ڋq�s.�،���ЃR�[�h
                //�f�[�^�R�[�h�F�@@�hGI843�h
                l_accSwElecDeliApplyCommonService.submitMarketTrigger(
                    l_mainAccountParams.getInstitutionCode(),
                    WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REGIST);
            }

            //MAP�I�u�W�F�N�g�̐���
            //�e��A���e�[�u�����X�V����ׂ�Map�I�u�W�F�N�g�𐶐�����B
            //�ȉ��̓��e��Map�I�u�W�F�N�g�𐶐�����
            //�`�[���ʃR�[�h�F    �������ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h�i�j�Ŏ擾�����l
            //�X�V�҃R�[�h�F �㗝���͂̏ꍇ�A�㗝���͎�.���҃R�[�h ���ȊO�̏ꍇ�Anull
            //�`�[�쐬�󋵁F 1�F�쐬��
            //�X�V�����F   ��������
            //�f�[�^�R�[�h�F �gGI843�h
            l_map.put("order_request_number", l_strNewNumber);
            l_map.put("last_updater", l_traderCode);
            l_map.put("status", WEB3VoucherCreateStatusDef.CREATE_COMPLETE);
            l_map.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
            l_map.put("request_code", WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REGIST);
        }
        //is�`�[�쐬()�̖߂�l��false�̏ꍇ
        else
        {
            //MAP�I�u�W�F�N�g�̐���
            //�e��A���e�[�u�����X�V����ׂ�Map�I�u�W�F�N�g�𐶐�����B
            //�ȉ��̓��e��Map�I�u�W�F�N�g�𐶐�����
            //�`�[���ʃR�[�h�F    null
            //�X�V�҃R�[�h�F �㗝���͂̏ꍇ�A�㗝���͎�.���҃R�[�h ���ȊO�̏ꍇ�Anull
            //�`�[�쐬�󋵁F 3�F��t����
            //�X�V�����F   ��������
            //�f�[�^�R�[�h�F null
            l_map.put("order_request_number", null);
            l_map.put("last_updater", l_traderCode);
            l_map.put("status", WEB3VoucherCreateStatusDef.ACCEPT_COMPLETE);
            l_map.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
            l_map.put("request_code", null);
        }

        // update�e��A��(Map)(�e��A��::update�e��A��)
        //�e��A���e�[�u���̃��R�[�h���X�V����B
        //[����]
        //��������Map�I�u�W�F�N�g
        l_inform.updateInform(l_map);

        //���o�C����p�����J�݋敪�ɕύX������ꍇ
        //�i���N�G�X�g�f�[�^.�ύX����.���o�C����p�����J�݋敪 !=
        //create�����ؑցE�d�q��t�\�����()�̖߂�l.���o�C����p�����J�݋敪�j�A�ȉ��̏������s���B
        if (!WEB3Toolkit.isEquals(l_request.changedInfo.mobileAccoutDiv,
            l_applyInfo.mobileAccoutDiv))
        {
            try
            {
                //getDefaultProcessor
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                //doUpdateQuery(arg0 : PrimaryKey, arg1 : Map)
                //�ڋq�}�X�^�e�[�u�����X�V����B
                //[doUpdateQuery()�Ɏw�肷�����]
                //arg0�iprimaryKey�j�F�@@�ڋq.get�ڋq�s.����ID
                //arg1�ichanges�j�F
                //�@@�@@�y��Trade�z�⑫����.DB�X�V�u�����ؑցE�d�q��t�\��_�ڋq�}�X�^�[�v�Q�ƁB
                //���o�C����p�����J�݋敪
                l_mainAccountParams.setOnlyMobileOpenDiv(l_request.changedInfo.mobileAccoutDiv);

                //���o�C����p�����J�݋敪�X�V�҃R�[�h
                //�ڋq���͂̏ꍇ�A�ڋq�}�X�^�e�[�u��.�����R�[�h
                l_mainAccountParams.setOnlyMblOpnDivLastUpdater(l_mainAccountParams.getAccountCode());

                //���o�C����p�����J�݋敪�X�V����
                l_mainAccountParams.setOnlyMblOpnDivTimestamp(GtlUtils.getSystemTimestamp());

                l_queryProcessor.doUpdateQuery(l_mainAccountParams);
            }
            catch (DataQueryException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3Inform.class.getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3Inform.class.getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //createResponse
        WEB3InformAccSwElecDeliApplyCmpResponse l_response =
            (WEB3InformAccSwElecDeliApplyCmpResponse)l_request.createResponse();

        //(*1)���X�|���X�f�[�^�v���p�e�B�Ɉȉ��̒ʂ�l���Z�b�g����B
        //�@@���X�|���X�f�[�^.���t���   �� create�����ؑցE�d�q��t�\�����t���()�̖߂�l
        l_response.dateInfo = l_appDtInfo;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
