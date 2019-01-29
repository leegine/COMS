head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.46.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformSwElecDeliApplyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X�����N���X�iWEB3AdminInformSwElecDeliApplyServiceImpl.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/19 �����i���u�j�V�K�쐬 ���f��No.099
Revision History : 2007/07/19 �����i���u�j�V�K�쐬 ���f��No.102
Revision History : 2007/08/13 �����i���u�j�d�l�ύX���f��104
Revision History : 2007/08/30 �g�E�N�|�i���u�j�d�l�ύX���f��107
Revision History : 2007/09/19 ��іQ�i���u�j�d�l�ύX���f��112
Revision History : 2007/09/19 �g�E�N�|�i���u�j�d�l�ύX���f��112
Revision History : 2007/09/25 ��іQ�i���u�j�d�l�ύX���f��116,�c�a�X�V�d�l017
Revision History : 2007/10/03 �����iSCS�j�d�l�ύX �����̖��006
Revision History : 2007/10/23 �����F (���u) ���f��121 �c�a�X�V�d�l 018 019
*/
package webbroker3.inform.service.delegate.stdimpls;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountPK;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.accountopen.data.HostConditionRegVoucherParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3VoucherCreateStatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.inform.WEB3Inform;
import webbroker3.inform.WEB3InformConditionRegVoucher;
import webbroker3.inform.data.VariousInformDao;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.data.VariousInformRow;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyCmpRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyCmpResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyConfRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyConfResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyRefRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyRefResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliChangeCmpRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliChangeCmpResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliChangeConfRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliChangeConfResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteCmpResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyInpRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyInpResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplySrcRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplySrcResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteCmpRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteConfRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteConfResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwitchElecDeliAppDtInfo;
import webbroker3.inform.message.WEB3AdminInformAccSwitchElecDeliApplyInfo;
import webbroker3.inform.service.delegate.WEB3AdminInformSwElecDeliApplyService;
import webbroker3.inform.service.delegate.WEB3InformAccSwElecDeliApplyCommonService;
import webbroker3.inform.service.delegate.WEB3InformHostReqOrderNumberManageService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (�Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�XImpl) <BR>
 * �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X�����N���X <BR>
 *
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminInformSwElecDeliApplyServiceImpl implements WEB3AdminInformSwElecDeliApplyService
{

    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminInformSwElecDeliApplyServiceImpl.class);

    /**
     * �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X�������s���B
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3GenResponse l_response = null;

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        if (l_request instanceof WEB3AdminInformAccSwElecDeliApplySrcRequest)
        {
            // get�������
            l_response = getSearchScreen((WEB3AdminInformAccSwElecDeliApplySrcRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformAccSwElecDeliApplyInpRequest)
        {
            // get���͉��
            l_response = getInputScreen((WEB3AdminInformAccSwElecDeliApplyInpRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformAccSwElecDeliApplyConfRequest)
        {
            // validate�\��
            l_response = validateApply((WEB3AdminInformAccSwElecDeliApplyConfRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformAccSwElecDeliApplyCmpRequest)
        {
            // submit�\��
            l_response = submitApply((WEB3AdminInformAccSwElecDeliApplyCmpRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformAccSwElecDeliApplyRefRequest)
        {
            // get�Ɖ���
            l_response = getReferenceScreen((WEB3AdminInformAccSwElecDeliApplyRefRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformAccSwElecDeliChangeConfRequest)
        {
            // validate�ύX
            l_response = validateChange((WEB3AdminInformAccSwElecDeliChangeConfRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformAccSwElecDeliChangeCmpRequest)
        {
            // submit�ύX
            l_response = submitChange((WEB3AdminInformAccSwElecDeliChangeCmpRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformAccSwElecDeliDeleteConfRequest)
        {
            // validate���
            l_response = validateCancel((WEB3AdminInformAccSwElecDeliDeleteConfRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformAccSwElecDeliDeleteCmpRequest)
        {
            // submit���
            l_response = submitCancel((WEB3AdminInformAccSwElecDeliDeleteCmpRequest)l_request);
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
     * �iget������ʁj<BR>
     * ������ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�Ǘ��ҁi�����ؑցE�d�q��t�\���jget������ʁv �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�����ؑցE�d�q��t�\���������N�G�X�g
     * @@return WEB3AdminInformAccSwElecDeliApplySrcResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminInformAccSwElecDeliApplySrcResponse getSearchScreen(
        WEB3AdminInformAccSwElecDeliApplySrcRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSearchScreen(WEB3AdminInformAccSwElecDeliApplySrcRequest)";
        log.entering(STR_METHOD_NAME);
        // ���O�C�������A�Ǘ��҃I�u�W�F�N�g���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        // �����`�F�b�N���s���B
        // [����]
        //  �@@�\�J�e�S���R�[�h�F "A0106"
        //  is�X�V�F false
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCOUNT_SWITCH_ELECTRON_DELI_APPLY, false);
        // ���X�|���X�f�[�^�𐶐�����B
        WEB3AdminInformAccSwElecDeliApplySrcResponse l_response =
            (WEB3AdminInformAccSwElecDeliApplySrcResponse)l_request.createResponse();
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * �iget���͉�ʁj<BR>
     * ���͉�ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�Ǘ��ҁi�����ؑցE�d�q��t�\���jget���͉�ʁv �Q�ƁB<BR>
     * =============================================== <BR>
     * �@@�@@�@@�@@��̈ʒu�@@:�@@get�ڋq()�ɂĊY���f�[�^���擾�ł��Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@��O��throw����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException  <BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_01035<BR>
     * =============================================== <BR>
     * =============================================== <BR>
     * �@@�@@�@@�@@��̈ʒu�@@:�@@�e��A���s���擾�ł��Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@class�@@:  WEB3BusinessLayerException  <BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_01037<BR>
     * =============================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�����ؑցE�d�q��t�\�����̓��N�G�X�g
     * @@return WEB3AdminInformAccSwElecDeliApplyInpResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminInformAccSwElecDeliApplyInpResponse getInputScreen(
        WEB3AdminInformAccSwElecDeliApplyInpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminInformAccSwElecDeliApplyInpRequest)";
        log.entering(STR_METHOD_NAME);

        //validate������t�\( )
        //������t�\�����`�F�b�N����B
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        // ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        // �Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        // �Ǘ��Ҍ����`�F�b�N���s���B
        // [����]
        //  �@@�\�J�e�S���R�[�h�F "A0106"
        //  is�X�V�F false
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCOUNT_SWITCH_ELECTRON_DELI_APPLY, true);

        // ���X�����̃`�F�b�N���s���B
        l_administrator.validateBranchPermission(l_request.branchCode);

        // �،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_administrator.getInstitutionCode();


        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            // get�ڋq()
            // �،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
            // ���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h
            // �����R�[�h�F�@@���N�G�X�g�f�[�^.�ڋq�R�[�h
            l_mainAccount = l_gentradeAccountManager.getMainAccount(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.accountCode);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("�Y������ڋq�����݂��܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        // �ڋq.getDataSourceObject()���R�[�����A�ڋqRow���擾����B
        MainAccountRow l_mainAcountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();

        // �����ؑցE�d�q��t�\�����ʃT�[�r���擾����B
        WEB3InformAccSwElecDeliApplyCommonService l_informAccSwElecDeliApplyCommonService =
            (WEB3InformAccSwElecDeliApplyCommonService)Services.getService(
                WEB3InformAccSwElecDeliApplyCommonService.class);

        //get�e��A���ꗗ(String, String, String, String)
        //�e��A���ꗗ���擾����B
        //[����]
        //�،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
        //���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h
        //�A����ʁF�@@���N�G�X�g�f�[�^.�A�����
        //�ڋq�R�[�h�F�@@���N�G�X�g�f�[�^.�ڋq�R�[�h
        List l_lisVariousInforms = l_informAccSwElecDeliApplyCommonService.getVariousInformList(
            l_strInstitutionCode, l_request.branchCode, l_request.informType, l_request.accountCode);

        //get�e��A���ꗗ�̖߂�l() != null �̏ꍇ�A�ȉ��̏������s���B
        if (l_lisVariousInforms != null)
        {
            //is�g���K���s(�������� : String)
            //���������F 0�iDEFAULT�j
            boolean l_blnIsSubmitMarketTrigger =
                WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT);

            //validate�`�[�쐬(String, boolean)
            //�`�[�쐬���\���`�F�b�N���s���B
            //[����]
            //�쐬�󋵁F�@@get�e��A���ꗗ.get(0).get�쐬��()�̖߂�l
            //�g���K�[���s�敪�F�@@is�g���K�[���s()�̖߂�l
            String l_strStatus = ((VariousInformRow)l_lisVariousInforms.get(0)).getStatus();

            l_informAccSwElecDeliApplyCommonService.validateVoucherMake(
                l_strStatus, l_blnIsSubmitMarketTrigger);
        }

        // create�����ؑցE�d�q��t�\�����(�ڋq)
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_elecApplyInfoBefore =
            l_informAccSwElecDeliApplyCommonService.createAccSwitchElecDeliApplyInfo(l_mainAccount);

        // ���X�|���X�f�[�^�𐶐�����B
        WEB3AdminInformAccSwElecDeliApplyInpResponse l_response =
            (WEB3AdminInformAccSwElecDeliApplyInpResponse)l_request.createResponse();

        // ���X�|���X�f�[�^.���X�R�[�h  �� �ڋq�s.���X�R�[�h
        l_response.branchCode = l_mainAcountRow.getBranchCode();
        // ���X�|���X�f�[�^.�ڋq�R�[�h   �� �ڋq�s.�����R�[�h
        l_response.accountCode = l_mainAcountRow.getAccountCode();
        // ���X�|���X�f�[�^.�ڋq��    �� �ڋq�s.���O�i�c���j
        l_response.accountName = l_mainAcountRow.getFamilyName();
        // ���X�|���X�f�[�^.�ύX�O���  �� create�����ؑցE�d�q��t�\�����(�j�̖߂�l
        l_response.beforeInfo = l_elecApplyInfoBefore;
        // ���X�|���X�f�[�^.�ύX����  �� create�����ؑցE�d�q��t�\�����(�j�̖߂�l
        l_response.changedInfo = l_elecApplyInfoBefore;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * �ivalidate�\���j<BR>
     * �����ؑցE�d�q��t�\���m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�Ǘ��ҁi�����ؑցE�d�q��t�\���jvalidate�\���v �Q�ƁB<BR>
     * =============================================== <BR>
     * �@@�@@�@@�@@��̈ʒu�@@:�@@get�ڋq()�ɂĊY���f�[�^���擾�ł��Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@��O��throw����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException  <BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_01035<BR>
     * =============================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�����ؑցE�d�q��t�\���m�F���N�G�X�g
     * @@return WEB3AdminInformAccSwElecDeliApplyConfResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminInformAccSwElecDeliApplyConfResponse validateApply(
        WEB3AdminInformAccSwElecDeliApplyConfRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateApply(WEB3AdminInformAccSwElecDeliApplyConfRequest)";
        log.entering(STR_METHOD_NAME);

        //validate������t�\( )
        //������t�\�����`�F�b�N����B
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        // ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        // �Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // �����`�F�b�N���s���B
        // [����]
        //  �@@�\�J�e�S���R�[�h�F "A0106"
        //  is�X�V�F true
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCOUNT_SWITCH_ELECTRON_DELI_APPLY, true);

        // ���X�����̃`�F�b�N���s���B
        l_administrator.validateBranchPermission(l_request.branchCode);

        // �،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            // get�ڋq()
            // �،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
            // ���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h
            // �����R�[�h�F�@@���N�G�X�g�f�[�^.�ڋq�R�[�h
            l_mainAccount = l_gentradeAccountManager.getMainAccount(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.accountCode);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("�Y������ڋq�����݂��܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // �����ؑցE�d�q��t�\�����ʃT�[�r���擾����B
        WEB3InformAccSwElecDeliApplyCommonService l_informAccSwElecDeliApplyCommonService =
            (WEB3InformAccSwElecDeliApplyCommonService)Services.getService(
                WEB3InformAccSwElecDeliApplyCommonService.class);

        //get�e��A���ꗗ(String, String, String, String)
        //[����]
        //�،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
        //���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h
        //�A����ʁF�@@���N�G�X�g�f�[�^.�A�����
        //�ڋq�R�[�h�F�@@���N�G�X�g�f�[�^.�ڋq�R�[�h
        List l_lisVariousInforms = l_informAccSwElecDeliApplyCommonService.getVariousInformList(
            l_strInstitutionCode, l_request.branchCode, l_request.informType, l_request.accountCode);

        //get�e��A���ꗗ�̖߂�l() != null �̏ꍇ�A�ȉ��̏������s���B
        if (l_lisVariousInforms != null)
        {
            //is�g���K���s(�������� : String)
            //���������F 0�iDEFAULT�j
            boolean l_blnIsSubmitMarketTrigger =
                WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT);

            //validate�`�[�쐬(String, boolean)
            //�`�[�쐬���\���`�F�b�N���s���B
            //[����]
            //�쐬�󋵁F�@@get�e��A���ꗗ.get(0).get�쐬��()�̖߂�l
            //�g���K�[���s�敪�F�@@is�g���K�[���s()�̖߂�l
            //�J�n�\����F�@@get�e��A���ꗗ.get(0).get�e�L�X�g�Q()�̖߂�l�@@��Date�^�ɕϊ�
            String l_strStatus = ((VariousInformRow)l_lisVariousInforms.get(0)).getStatus();
            String l_strExtText2 = ((VariousInformRow)l_lisVariousInforms.get(0)).getExtText2();
            Date l_datBegin = WEB3DateUtility.getDate(
                l_strExtText2,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS);

            l_informAccSwElecDeliApplyCommonService.validateVoucherMake(
                l_strStatus, l_blnIsSubmitMarketTrigger, l_datBegin);
        }

        //create�����ؑցE�d�q��t�\�����(�ڋq)
        //[����]
        //�ڋq�F�@@get�ڋq()�̖߂�l
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo =
            l_informAccSwElecDeliApplyCommonService.createAccSwitchElecDeliApplyInfo(l_mainAccount);

        //validate�����ؑցE�d�q��t�\�����(�����ؑցE�d�q��t�\�����, �����ؑցE�d�q��t�\�����)
        //[����]
        //�ύX�O���F�@@create�����ؑցE�d�q��t�\�����()�̖߂�l
        //�ύX����F�@@���N�G�X�g�f�[�^.�ύX����
        l_informAccSwElecDeliApplyCommonService.validateAccSwitchElecDeliApplyInfo(
            l_applyInfo, l_request.changedInfo);

        //createResponse( )
        WEB3AdminInformAccSwElecDeliApplyConfResponse l_response =
            (WEB3AdminInformAccSwElecDeliApplyConfResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * �isubmit�\���j<BR>
     * �����ؑցE�d�q��t�\���m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�Ǘ��ҁi�����ؑցE�d�q��t�\���jsubmit�\���v �Q�ƁB<BR>
     * =============================================== <BR>
     * �@@�@@�@@�@@��̈ʒu�@@:�@@get�ڋq()�ɂĊY���f�[�^���擾�ł��Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@��O��throw����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException  <BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_01035<BR>
     * =============================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�����ؑցE�d�q��t�\���������N�G�X�g
     * @@return WEB3AdminInformAccSwElecDeliApplyCmpResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminInformAccSwElecDeliApplyCmpResponse submitApply(
        WEB3AdminInformAccSwElecDeliApplyCmpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitApply(WEB3AdminInformAccSwElecDeliApplyCmpRequest)";
        log.entering(STR_METHOD_NAME);

        //validate������t�\( )
        //������t�\�����`�F�b�N����B
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        // ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        // �Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // �����`�F�b�N���s���B
        // [����]
        //  �@@�\�J�e�S���R�[�h�F "A0106"
        //  is�X�V�F true
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCOUNT_SWITCH_ELECTRON_DELI_APPLY, true);

        // ���X�����̃`�F�b�N���s���B
        l_administrator.validateBranchPermission(l_request.branchCode);

        // ����p�X���[�h�����������̃`�F�b�N���s���B
        l_administrator.validateTradingPassword(l_request.password);

        // �،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        // �Ǘ��҃R�[�h���擾����B
        String l_strAdministratorCode = l_administrator.getAdministratorCode();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            // get�ڋq()
            // �،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
            // ���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h
            // �����R�[�h�F�@@���N�G�X�g�f�[�^.�ڋq�R�[�h
            l_mainAccount = l_gentradeAccountManager.getMainAccount(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.accountCode);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("�Y������ڋq�����݂��܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // �����ؑցE�d�q��t�\�����ʃT�[�r���擾����B
        WEB3InformAccSwElecDeliApplyCommonService l_informAccSwElecDeliApplyCommonService =
            (WEB3InformAccSwElecDeliApplyCommonService)Services.getService(
                WEB3InformAccSwElecDeliApplyCommonService.class);

        //get�e��A���ꗗ(String, String, String, String)
        //�،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
        //���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h
        //�A����ʁF�@@���N�G�X�g�f�[�^.�A�����
        //�ڋq�R�[�h�F�@@���N�G�X�g�f�[�^.�ڋq�R�[�h
        List l_lisVariousInformList = l_informAccSwElecDeliApplyCommonService.getVariousInformList(
            l_strInstitutionCode,
            l_request.branchCode,
            l_request.informType,
            l_request.accountCode);

        //is�g���K���s(�������� : String)
        //���������F 0�iDEFAULT�j
        boolean l_blnIsSubmitMarketTrigger =
            WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
                WEB3OrderingConditionDef.DEFAULT);

        //get�e��A���ꗗ�̖߂�l() != null �̏ꍇ�A�ȉ��̏������s���B
        if (l_lisVariousInformList != null)
        {
            //�쐬�󋵁F�@@get�e��A���ꗗ.get(0).get�쐬��()�̖߂�l
            VariousInformRow l_variousInformRow =
                (VariousInformRow)l_lisVariousInformList.get(0);

            //�J�n�\����F�@@get�e��A���ꗗ.get(0).get�e�L�X�g�Q()�̖߂�l�@@��Date�^�ɕϊ�
            Date l_datStartScheduleDate = WEB3DateUtility.getDate(
                l_variousInformRow.getExtText2(),
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS);

            //validate�`�[�쐬(String, boolean, Date)
            l_informAccSwElecDeliApplyCommonService.validateVoucherMake(
                l_variousInformRow.getStatus(),
                l_blnIsSubmitMarketTrigger,
                l_datStartScheduleDate);
        }

        //create�����ؑցE�d�q��t�\�����(�ڋq)
        //�ڋq�F�@@get�ڋq()�̖߂�l
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_adminInformAccSwitchElecDeliApplyInfo =
            l_informAccSwElecDeliApplyCommonService.createAccSwitchElecDeliApplyInfo(l_mainAccount);

        //validate�����ؑցE�d�q��t�\�����(�����ؑցE�d�q��t�\�����, �����ؑցE�d�q��t�\�����)
        // [����]
        //�ύX�O���F�@@create�����ؑցE�d�q��t�\�����()�̖߂�l
        //�ύX����F�@@���N�G�X�g�f�[�^.�ύX����
        l_informAccSwElecDeliApplyCommonService.validateAccSwitchElecDeliApplyInfo(
            l_adminInformAccSwitchElecDeliApplyInfo,
            l_request.changedInfo);

        // create�����ؑցE�d�q��t�\�����t���
        // [����]
         // �ŋ敪�F�@@���N�G�X�g�f�[�^.�ŋ敪
         // �M�p����ŋ敪�F�@@���N�G�X�g�f�[�^.�M�p����ŋ敪
        WEB3AdminInformAccSwitchElecDeliAppDtInfo l_elecDeliAppDtInfo =
            l_informAccSwElecDeliApplyCommonService.createAccSwitchElecDeliAppDtInfo(
                l_request.changedInfo.taxType, l_request.changedInfo.marginTaxType);

        // create�e��A��
        // [����]
         // �ڋq�F�@@get�ڋq()�̖߂�l
         // �ύX����F�@@���N�G�X�g�f�[�^.�ύX����
         // ���t���F�@@create�����ؑցE�d�q��t�\�����t���()�̖߂�l
         // �A����ʁF�@@���N�G�X�g�f�[�^.�A�����
        WEB3Inform l_inform = l_informAccSwElecDeliApplyCommonService.createVariousInform(
            l_mainAccount, l_request.changedInfo, l_elecDeliAppDtInfo, l_request.informType);

        // �A���Ǘ����ʃR�[�h�̔ԃT�[�r�X���擾����B
        WEB3InformHostReqOrderNumberManageService l_informHostReqOrderNumberManageService =
            (WEB3InformHostReqOrderNumberManageService)Services.getService(
                WEB3InformHostReqOrderNumberManageService.class);

        // get�V�K���ʃR�[�h
        //  �،���ЃR�[�h�F get�،���ЃR�[�h( )�̖߂�l
        //  �A����ʁF ���N�G�X�g�f�[�^.�A�����
        String l_strRequestNumber = l_informHostReqOrderNumberManageService.getNewOrderRequestCode(
            l_strInstitutionCode, l_request.informType);

        // saveNew�e��A��
        // �X�V�҃R�[�h�F�@@get�Ǘ��҃R�[�h()�̖߂�l
        // ���ʃR�[�h�F�@@get�V�K���ʃR�[�h()�̖߂�l
        // �쐬�󋵁F�@@3:��t����
        l_inform.saveNewInform(
            l_strAdministratorCode, l_strRequestNumber, WEB3VoucherCreateStatusDef.ACCEPT_COMPLETE);

        //get�V�K���ʃR�[�h(�،���ЃR�[�h : String, ���X�R�[�h : String, �����^�C�v : ProductTypeEnum)
        //�،���ЃR�[�h�F get�،���ЃR�[�h()�̖߂�l
        //���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h
        //�����^�C�v�F ProductTypeEnum.���̑�
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService =
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);

        String l_strNewNumber = l_hostReqOrderNumberManageService.getNewNumber(
            l_strInstitutionCode,
            l_request.branchCode,
            ProductTypeEnum.OTHER);

        //create���E��c�d�q��t�E��������o�^�s(�ڋq, �e��A��params)
        //�ڋq�F�@@get�ڋq()�̖߂�l
        //�e��A���s�F�@@�e��A���I�u�W�F�N�g.getDataSourceObject()�̖߂�l
        HostConditionRegVoucherParams l_hostConditionRegVoucherParams =
            l_informAccSwElecDeliApplyCommonService.createHostConditionRegVoucherParams(
                l_mainAccount,
                (VariousInformParams)l_inform.getDataSourceObject());

        //���E��c�d�q��t�E��������o�^(���E��c�d�q��t�E��������o�^Params, String)
        //���E��c�d�q��t�E��������o�^�s : create���E��c�d�q��t�E��������o�^�s()�̖߂�l
        //�`�[���ʃR�[�h : �������ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h()�̖߂�l
        WEB3InformConditionRegVoucher l_informConditionRegVoucher =
            new WEB3InformConditionRegVoucher(
                l_hostConditionRegVoucherParams,
                l_strNewNumber);

        //is�`�[�쐬
        boolean l_blnVoucherMake = l_informConditionRegVoucher.isVoucherMake();

        Map l_updateInformMap = new HashMap();
        //is�`�[�쐬()�̖߂�l��true�̏ꍇ
        if (l_blnVoucherMake)
        {
            //save���E��c�d�q��t�E��������o�^�`�[�L���[
            //���E��c�d�q��t�E��������o�^�iGI311�j�L���[�e�[�u���Ƀ��R�[�h���쐬����B
            l_informConditionRegVoucher.saveHostConditionRegVoucher();

            //is�g���K���s == true �̏ꍇ�A�ȉ��̏��������s
            if (l_blnIsSubmitMarketTrigger)
            {
                //�g���K���s(String, String)
                //�،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
                //�f�[�^�R�[�h�F�@@�hGI843�h
                l_informAccSwElecDeliApplyCommonService.submitMarketTrigger(
                    l_strInstitutionCode,
                    WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REGIST);
            }

            //MAP�I�u�W�F�N�g�̐���

            //�`�[���ʃR�[�h�F    �������ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h�i�j�Ŏ擾�����l
            l_updateInformMap.put("order_request_number", l_strNewNumber);

            //�X�V�҃R�[�h�F get�Ǘ��҃R�[�h()�̖߂�l
            l_updateInformMap.put("last_updater", l_strAdministratorCode);

            //�`�[�쐬�󋵁F 1�F�쐬��
            l_updateInformMap.put("status", WEB3VoucherCreateStatusDef.CREATE_COMPLETE);

            //�X�V�����F   ��������
            l_updateInformMap.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

            //�f�[�^�R�[�h�F �gGI843�h
            l_updateInformMap.put("request_code", WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REGIST);
        }
        else
        {
            //MAP�I�u�W�F�N�g�̐���

            //�`�[���ʃR�[�h�F  null
            l_updateInformMap.put("order_request_number", null);

            //�X�V�҃R�[�h�F get�Ǘ��҃R�[�h()�̖߂�l
            l_updateInformMap.put("last_updater", l_strAdministratorCode);

            //�`�[�쐬�󋵁F3�F��t����
            l_updateInformMap.put("status", WEB3VoucherCreateStatusDef.ACCEPT_COMPLETE);

            //�X�V�����F   ��������
            l_updateInformMap.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

            //�f�[�^�R�[�h�Fnull
            l_updateInformMap.put("request_code", null);
        }

        //update�e��A��(Map)
        //DB�X�V�d�l�u���z���U�֌����o�^_�e��A���e�[�u��.xls�v
        //���z���U�֌����o�^_update_DB�X�V�d�l�Q��
        l_inform.updateInform(l_updateInformMap);

        // ���o�C����p�����J�݋敪�ɕύX������ꍇ
        // ���N�G�X�g�f�[�^.�ύX����.���o�C����p�����J�݋敪 !=
        //create�����ؑցE�d�q��t�\�����()�̖߂�l.���o�C����p�����J�݋敪
        if (!WEB3Toolkit.isEquals(l_adminInformAccSwitchElecDeliApplyInfo.mobileAccoutDiv,
            l_request.changedInfo.mobileAccoutDiv))
        {
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                // [doUpdateQuery()�Ɏw�肷�����]
                // arg0�iprimaryKey�j�F�@@�ڋq.get�ڋq�s.����ID
                // arg1�ichanges�j�F
                //�@@�@@�y��Trade�z�⑫����.DB�X�V�u�����ؑցE�d�q��t�\��_�ڋq�}�X�^�[�v�Q�ƁB
                MainAccountPK l_mainAccountPK =
                    new MainAccountPK(l_mainAccount.getMainAccountRow().getAccountId());

                Map l_mainAccountMap = new HashMap();

                //���o�C����p�����J�݋敪
                l_mainAccountMap.put("only_mobile_open_div", l_request.changedInfo.mobileAccoutDiv);

                //���o�C����p�����J�݋敪�X�V�҃R�[�h
                l_mainAccountMap.put("only_mbl_opn_div_last_updater", l_strAdministratorCode);

                //���o�C����p�����J�݋敪�X�V����
                l_mainAccountMap.put(
                    "only_mbl_opn_div_timestamp", GtlUtils.getTradingSystem().getSystemTimestamp());

                l_queryProcessor.doUpdateQuery(l_mainAccountPK, l_mainAccountMap);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        // ���X�|���X�f�[�^�𐶐�����B
        WEB3AdminInformAccSwElecDeliApplyCmpResponse l_response =
            (WEB3AdminInformAccSwElecDeliApplyCmpResponse)l_request.createResponse();

        // �����ؑցE�d�q��t�\�����t���  ���@@create�����ؑցE�d�q��t�\�����t���()�̖߂�l
        l_response.dateInfo = l_elecDeliAppDtInfo;
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�Ɖ���)<BR>
     * �����ؑցE�d�q��t�\���Q�Ɖ�ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��ҁi�����ؑցE�d�q��t�\���jget�Q�Ɖ�ʁv �Q�ƁB<BR>
     * ========================================================<BR>
     * �V�[�P���X�} (�u�Ǘ��ҁi�����ؑցE�d�q��t�\���jget�Q�Ɖ�ʁv)<BR>
     * �@@�@@��̈ʒu�Fget�ڋq()�ɂĊY���f�[�^���擾�ł��Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@��O��throw����B<BR>
     * �@@�@@class �@@: WEB3BusinessLayerException<BR>
     * �@@�@@tag  �@@ : BUSINESS_ERROR_01035<BR>
     * ========================================================<BR>
     * ========================================================<BR>
     * �V�[�P���X�} (�u�Ǘ��ҁi�����ؑցE�d�q��t�\���jget�Q�Ɖ�ʁv)<BR>
     * �@@�@@��̈ʒu�F�e��A���s���擾�ł��Ȃ��ꍇ�i�߂�l == null�j�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@class �@@: WEB3BusinessLayerException<BR>
     * �@@�@@tag  �@@ : BUSINESS_ERROR_01037<BR>
     * ========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminInformAccSwElecDeliApplyRefResponse
     * @@throws WEB3BaseException
     */

    protected WEB3AdminInformAccSwElecDeliApplyRefResponse getReferenceScreen(
        WEB3AdminInformAccSwElecDeliApplyRefRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getReferenceScreen(WEB3AdminInformAccSwElecDeliApplyRefRequest)";
        log.entering(STR_METHOD_NAME);

        //validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[����]
        //�@@�\�J�e�S���R�[�h�F "A0106"
        //is�X�V�F false
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCOUNT_SWITCH_ELECTRON_DELI_APPLY, false);

        //validate���X����(���X�R�[�h : String)
        //[����]
        //���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h
        l_administrator.validateBranchPermission(l_request.branchCode);

        //get�،����( )
        Institution l_institution = l_administrator.getInstitution();

        //get�ڋq()�ɂĊY���f�[�^���擾�ł��Ȃ��ꍇ�A��O��throw����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        try
        {
            //get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
            //[����]
            // �،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
            // ���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h
            // �����R�[�h�F�@@���N�G�X�g�f�[�^.�ڋq�R�[�h
            l_gentradeAccountManager.getMainAccount(
                l_institution.getInstitutionCode(),
                l_request.branchCode,
                l_request.accountCode);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("�Y������ڋq�����݂��܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y������ڋq�����݂��܂���B");
        }

        VariousInformParams l_variousInformParams = null;
        //get�e��A���s(String, String, String, String)
        //[get�e��A���s()�Ɏw�肷�����]
        // �،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
        // ���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h
        // ���ʃR�[�h�F�@@���N�G�X�g�f�[�^.���ʃR�[�h
        // �A����ʁF�@@���N�G�X�g�f�[�^.�A�����
        l_variousInformParams = WEB3Inform.getVariousInform(
            l_institution.getInstitutionCode(),
            l_request.branchCode,
            l_request.requestNumber,
            l_request.informType);

        //�e��A���s���擾�ł��Ȃ��ꍇ�i�߂�l == null�j�A��O���X���[����B
        if (l_variousInformParams == null)
        {
            log.debug("�����ɊY������f�[�^�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����ɊY������f�[�^�����݂��Ȃ��B");
        }

        //�����ؑցE�d�q��t�\�����ʃT�[�r�X�擾
        WEB3InformAccSwElecDeliApplyCommonService l_service =
            (WEB3InformAccSwElecDeliApplyCommonService)Services.getService(
                WEB3InformAccSwElecDeliApplyCommonService.class);

        //create�����ؑցE�d�q��t�\�����(�e��A��Params)
        //[����]
        // �e��A���s�F get�e��A���s()�̖߂�l
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo =
            l_service.createAccSwitchElecDeliApplyInfo(l_variousInformParams);

        //createResponse( )
        WEB3AdminInformAccSwElecDeliApplyRefResponse l_response =
            (WEB3AdminInformAccSwElecDeliApplyRefResponse)l_request.createResponse();

        //���X�|���X�f�[�^.���X�R�[�h    �� �e��A���s.���X�R�[�h
        l_response.branchCode = l_variousInformParams.getBranchCode();
        //���X�|���X�f�[�^.�ڋq�R�[�h    �� �e��A���s.�ڋq�R�[�h
        l_response.accountCode = l_variousInformParams.getAccountCode();
        //���X�|���X�f�[�^.�ڋq��  �� �e��A���s.�ڋq��
        l_response.accountName = l_variousInformParams.getAccountName();
        //���X�|���X�f�[�^.�ύX�O���    �� create�����ؑցE�d�q��t�\�����()�̖߂�l
        l_response.beforeInfo = l_applyInfo;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�ύX)<BR>
     * �����ؑցE�d�q��t�\���ύX�m�F��ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��ҁi�����ؑցE�d�q��t�\���jvalidate�ύX�v �Q�ƁB<BR>
     * ========================================================<BR>
     * �V�[�P���X�} (�u�Ǘ��ҁi�����ؑցE�d�q��t�\���jvalidate�ύX�v)<BR>
     * �@@�@@��̈ʒu�Fget�ڋq()�ɂĊY���f�[�^���擾�ł��Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@��O��throw����B<BR>
     * �@@�@@class �@@: WEB3BusinessLayerException<BR>
     * �@@�@@tag  �@@ : BUSINESS_ERROR_01035<BR>
     * ========================================================<BR>
     * ========================================================<BR>
     * �V�[�P���X�} (�u�Ǘ��ҁi�����ؑցE�d�q��t�\���jvalidate�ύX�v)<BR>
     * �@@�@@��̈ʒu�F�e��A���s���擾�ł��Ȃ��ꍇ�i�߂�l == null�j�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@class �@@: WEB3BusinessLayerException<BR>
     * �@@�@@tag  �@@ : BUSINESS_ERROR_01037<BR>
     * ========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminInformAccSwElecDeliChangeConfResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminInformAccSwElecDeliChangeConfResponse validateChange(
        WEB3AdminInformAccSwElecDeliChangeConfRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateChange(WEB3AdminInformAccSwElecDeliChangeConfRequest)";
        log.entering(STR_METHOD_NAME);

        //validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[����]
        //�@@�\�J�e�S���R�[�h�F "A0106"
        //is�X�V�F true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCOUNT_SWITCH_ELECTRON_DELI_APPLY, true);

        //validate���X����(���X�R�[�h : String)
        //[����]
        //���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h
        l_administrator.validateBranchPermission(l_request.branchCode);

        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //get�ڋq()�ɂĊY���f�[�^���擾�ł��Ȃ��ꍇ�A��O��throw����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        try
        {
            //get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
            //[����]
            // �،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
            // ���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h
            // �����R�[�h�F�@@���N�G�X�g�f�[�^.�ڋq�R�[�h
            l_gentradeAccountManager.getMainAccount(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.accountCode);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("�Y������ڋq�����݂��܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y������ڋq�����݂��܂���B");
        }

        VariousInformParams l_variousInformParams = null;
        //get�e��A���s(String, String, String, String)
        //[get�e��A���s()�Ɏw�肷�����]
        // �،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
        // ���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h
        // ���ʃR�[�h�F�@@���N�G�X�g�f�[�^.���ʃR�[�h
        // �A����ʁF�@@���N�G�X�g�f�[�^.�A�����
        l_variousInformParams = WEB3Inform.getVariousInform(
            l_strInstitutionCode,
            l_request.branchCode,
            l_request.requestNumber,
            l_request.informType);

        //�e��A���s���擾�ł��Ȃ��ꍇ�i�߂�l == null�j�A��O���X���[����B
        if (l_variousInformParams == null)
        {
            log.debug("�����ɊY������f�[�^�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����ɊY������f�[�^�����݂��Ȃ��B");
        }

        // is�g���K���s(�������� : String)
        //[����]
        // ���������F 0�iDEFAULT�j
        boolean l_blnIsSubmitMarketTrigger =
            WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT);

        //�����ؑցE�d�q��t�\�����ʃT�[�r�X�擾
        WEB3InformAccSwElecDeliApplyCommonService l_service =
            (WEB3InformAccSwElecDeliApplyCommonService)Services.getService(
                WEB3InformAccSwElecDeliApplyCommonService.class);

        //validate�`�[�ύX(String, boolean)
        //�쐬�󋵁F�@@get�e��A���s().get�쐬��()�̖߂�l
        //�g���K�[���s�敪�F�@@is�g���K�[���s()�̖߂�l
        l_service.validateVoucherChange(l_variousInformParams.getStatus(), l_blnIsSubmitMarketTrigger);

        //create�����ؑցE�d�q��t�\�����(�e��A��Params)
        //[����]
        //�e��A���s�F get�e��A���s()�̖߂�l
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo =
            l_service.createAccSwitchElecDeliApplyInfo(l_variousInformParams);

        //validate�����ؑցE�d�q��t�\�����(�����ؑցE�d�q��t�\�����, �����ؑցE�d�q��t�\�����)
        //[����]
        //�ύX�O���F�@@create�����ؑցE�d�q��t�\�����()�̖߂�l
        //�ύX����F�@@���N�G�X�g�f�[�^.�ύX����
        l_service.validateAccSwitchElecDeliApplyInfo(l_applyInfo, l_request.changedInfo);

        //createResponse( )
        WEB3AdminInformAccSwElecDeliChangeConfResponse l_response =
            (WEB3AdminInformAccSwElecDeliChangeConfResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�ύX)<BR>
     * �����ؑցE�d�q��t�\���ύX������ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�Ǘ��ҁi�����ؑցE�d�q��t�\���jsubmit�ύX�v �Q�ƁB<BR>
     * ========================================================<BR>
     * �V�[�P���X�} (�u�Ǘ��ҁi�����ؑցE�d�q��t�\���jvalidate�ύX�v)<BR>
     * �@@�@@��̈ʒu�Fget�ڋq()�ɂĊY���f�[�^���擾�ł��Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@��O��throw����B<BR>
     * �@@�@@class �@@: WEB3BusinessLayerException<BR>
     * �@@�@@tag  �@@ : BUSINESS_ERROR_01035<BR>
     * ========================================================<BR>
     * ========================================================<BR>
     * �V�[�P���X�} (�u�Ǘ��ҁi�����ؑցE�d�q��t�\���jvalidate�ύX�v)<BR>
     * �@@�@@��̈ʒu�F�e��A���s���擾�ł��Ȃ��ꍇ�i�߂�l == null�j�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@class �@@: WEB3BusinessLayerException<BR>
     * �@@�@@tag  �@@ : BUSINESS_ERROR_01037<BR>
     * ========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminInformAccSwElecDeliChangeCmpResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminInformAccSwElecDeliChangeCmpResponse submitChange(
        WEB3AdminInformAccSwElecDeliChangeCmpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitChange(WEB3AdminInformAccSwElecDeliChangeCmpRequest)";
        log.entering(STR_METHOD_NAME);

        //validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[����]
        //�@@�\�J�e�S���R�[�h�F "A0106"
        //is�X�V�F true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCOUNT_SWITCH_ELECTRON_DELI_APPLY, true);

        //validate���X����(���X�R�[�h : String)
        //[����]
        //���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h
        l_administrator.validateBranchPermission(l_request.branchCode);

        //validate����p�X���[�h(�p�X���[�h : String)
        //[validate����p�X���[�h()�Ɏw�肷�����]
        // �p�X���[�h�F�@@���N�G�X�g�f�[�^.�Ïؔԍ�
        l_administrator.validateTradingPassword(l_request.password);

        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //get�ڋq()�ɂĊY���f�[�^���擾�ł��Ȃ��ꍇ�A��O��throw����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        MainAccount l_mainAccount = null;
        try
        {
            //get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
            //[����]
            // �،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
            // ���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h
            // �����R�[�h�F�@@���N�G�X�g�f�[�^.�ڋq�R�[�h
            l_mainAccount = l_gentradeAccountManager.getMainAccount(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.accountCode);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("�Y������ڋq�����݂��܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y������ڋq�����݂��܂���B");
        }

        VariousInformParams l_variousInformParams = null;
        //get�e��A���s(String, String, String, String)
        //[get�e��A���s()�Ɏw�肷�����]
        // �،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
        // ���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h
        // ���ʃR�[�h�F�@@���N�G�X�g�f�[�^.���ʃR�[�h
        // �A����ʁF�@@���N�G�X�g�f�[�^.�A�����
        l_variousInformParams = WEB3Inform.getVariousInform(
            l_strInstitutionCode,
            l_request.branchCode,
            l_request.requestNumber,
            l_request.informType);

        //�e��A���s���擾�ł��Ȃ��ꍇ�i�߂�l == null�j�A��O���X���[����B
        if (l_variousInformParams == null)
        {
            log.debug("�����ɊY������f�[�^�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����ɊY������f�[�^�����݂��Ȃ��B");
        }

        // is�g���K���s(�������� : String)
        //[����]
        // ���������F 0�iDEFAULT�j
        boolean l_blnIsSubmitMarketTrigger =
            WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT);

        //�����ؑցE�d�q��t�\�����ʃT�[�r�X�擾
        WEB3InformAccSwElecDeliApplyCommonService l_service =
            (WEB3InformAccSwElecDeliApplyCommonService)Services.getService(
                WEB3InformAccSwElecDeliApplyCommonService.class);

        //validate�`�[�ύX(String, boolean)
        //�쐬�󋵁F�@@get�e��A���s().get�쐬��()�̖߂�l
        //�g���K�[���s�敪�F�@@is�g���K�[���s()�̖߂�l
        l_service.validateVoucherChange(l_variousInformParams.getStatus(), l_blnIsSubmitMarketTrigger);

        //create�����ؑցE�d�q��t�\�����(�e��A��Params)
        //[����]
        // �e��A���s�F get�e��A���s()�̖߂�l
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo =
            l_service.createAccSwitchElecDeliApplyInfo(l_variousInformParams);

        //validate�����ؑցE�d�q��t�\�����(�����ؑցE�d�q��t�\�����, �����ؑցE�d�q��t�\�����)
        //[����]
        //�ύX�O���F�@@create�����ؑցE�d�q��t�\�����()�̖߂�l
        //�ύX����F�@@���N�G�X�g�f�[�^.�ύX����
        l_service.validateAccSwitchElecDeliApplyInfo(l_applyInfo, l_request.changedInfo);

        //create�����ؑցE�d�q��t�\�����t���(String, String)
        //[����]
        //�ŋ敪�F�@@���N�G�X�g�f�[�^.�ύX����.�ŋ敪
        //�M�p����ŋ敪�F�@@���N�G�X�g�f�[�^.�ύX����.�M�p����ŋ敪
        WEB3AdminInformAccSwitchElecDeliAppDtInfo l_deliAppDtInfo =
            l_service.createAccSwitchElecDeliAppDtInfo(
                l_request.changedInfo.taxType, l_request.changedInfo.marginTaxType);

        //create�e��A��(�ڋq, �����ؑցE�d�q��t�\�����, �����ؑցE�d�q��t�\�����t���, String)
        //[����]
        //�ڋq�F�@@get�ڋq()�̖߂�l
        //�ύX����F�@@���N�G�X�g�f�[�^.�ύX����
        //���t���F�@@create�����ؑցE�d�q��t�\�����t���()�̖߂�l
        //�A����ʁF�@@���N�G�X�g�f�[�^.�A�����
        WEB3Inform l_inform = l_service.createVariousInform(
            l_mainAccount, l_request.changedInfo, l_deliAppDtInfo, l_request.informType);

        //get�Ǘ��҃R�[�h( )
        String l_strAdminCode = l_administrator.getAdministratorCode();

        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService =
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);

        //get�V�K���ʃR�[�h(�،���ЃR�[�h : String, ���X�R�[�h : String, �����^�C�v : ProductTypeEnum)
        //[����]
        // �،���ЃR�[�h�F get�،���ЃR�[�h( )�̖߂�l
        // ���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h
        // �����^�C�v�F ProductTypeEnum.���̑�
        String l_strNewNumber = l_hostReqOrderNumberManageService.getNewNumber(
            l_strInstitutionCode,
            l_request.branchCode,
            ProductTypeEnum.OTHER);

        //create���E��c�d�q��t�E��������o�^�s(�ڋq, �e��A��params)
        //[����]
        // �ڋq�F�@@get�ڋq()�̖߂�l
        // �e��A���s�F�@@�e��A���I�u�W�F�N�g.getDataSourceObject()�̖߂�l
        HostConditionRegVoucherParams l_hostConditionRegVoucherParams =
            l_service.createHostConditionRegVoucherParams(
                l_mainAccount, (VariousInformParams)l_inform.getDataSourceObject());

        //���E��c�d�q��t�E��������o�^(�e��A��Params)
        //[����]
        //�e��A���s�F�@@get�e��A���s()�̖߂�l
        WEB3InformConditionRegVoucher l_informConditionRegVoucher =
            new WEB3InformConditionRegVoucher(l_variousInformParams);

        //delete���E��c�d�q��t�E��������o�^�`�[�L���[( )
        l_informConditionRegVoucher.deleteHostConditionRegVoucher();

        //���E��c�d�q��t�E��������o�^(���E��c�d�q��t�E��������o�^Params, String)
        //[����]
        //���E��c�d�q��t�E��������o�^�s�F�@@create���E��c�d�q��t�E��������o�^�s()�̖߂�l
        //�`�[���ʃR�[�h�F�@@�������ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h()�̖߂�l
        WEB3InformConditionRegVoucher l_informConditionRegVoucherNew =
            new WEB3InformConditionRegVoucher(l_hostConditionRegVoucherParams, l_strNewNumber);

        //is�`�[�쐬
        boolean l_blnVoucherMake = l_informConditionRegVoucherNew.isVoucherMake();

        //is�`�[�쐬()�̖߂�l��true�̏ꍇ
        if (l_blnVoucherMake)
        {
            //save���E��c�d�q��t�E��������o�^�`�[�L���[( )
            l_informConditionRegVoucherNew.saveHostConditionRegVoucher();

            //is�g���K���s == true �̏ꍇ�A�ȉ��̏��������s
            if (l_blnIsSubmitMarketTrigger)
            {
                //�g���K���s(String, String)
                //[����]
                // �،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
                // �f�[�^�R�[�h�F�@@�hGI843�h
                l_service.submitMarketTrigger(
                    l_strInstitutionCode, WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REGIST);
            }

            //update�e��A���ύX���(�e��A��Params, String, String)
            //�m�����n
            //�e��A���s�F create�e��A��.getDataSourceObject()�̖߂�l
            //���ʃR�[�h�F ���N�G�X�g�f�[�^.���ʃR�[�h
            //�`�[���ʃR�[�h�F �������ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h()�Ŏ擾�����l
            //�f�[�^�R�[�h�F�@@�hGI843�h
            //�`�[�쐬�󋵁F �h1�F�쐬�ρh
            updateVariousInformChangeInfo(
                (VariousInformParams)l_inform.getDataSourceObject(),
                l_request.requestNumber,
                l_strNewNumber,
                WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REGIST,
                WEB3VoucherCreateStatusDef.CREATE_COMPLETE);
        }
        //is�`�[�쐬()�̖߂�l��false�̏ꍇ
        else
        {
            //update�e��A���ύX���(�e��A��Params, String, String)
            //�m�����n
            //�e��A���s�F create�e��A��.getDataSourceObject()�̖߂�l
            //���ʃR�[�h�F ���N�G�X�g�f�[�^.���ʃR�[�h
            //�`�[���ʃR�[�h�F null
            //�f�[�^�R�[�h�F�@@null
            //�`�[�쐬�󋵁F �h3�F��t�����h
            updateVariousInformChangeInfo(
                (VariousInformParams)l_inform.getDataSourceObject(),
                l_request.requestNumber,
                null,
                null,
                WEB3VoucherCreateStatusDef.ACCEPT_COMPLETE);
        }

        //���o�C����p�����J�݋敪�ɕύX������ꍇ
        //�i���N�G�X�g�f�[�^.�ύX����.���o�C����p�����J�݋敪 !=
        // create�����ؑցE�d�q��t�\�����()�̖߂�l.���o�C����p�����J�݋敪�j�A
        // �ȉ��̏������s���B
        if (!WEB3Toolkit.isEquals(l_request.changedInfo.mobileAccoutDiv, l_applyInfo.mobileAccoutDiv))
        {
            try
            {
                //getDefaultProcessor( )
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                //�ڋq�}�X�^�e�[�u�����X�V����B
                //[doUpdateQuery()�Ɏw�肷�����]
                // arg0�iprimaryKey�j�F�@@�ڋq.get�ڋq�s.����ID
                // arg1�ichanges�j�F
                // �@@�@@�y��Trade�z�⑫����.DB�X�V�u�����ؑցE�d�q��t�\��_�ڋq�}�X�^�[�v�Q�ƁB
                MainAccountPK l_mainAccountPK = new MainAccountPK(l_mainAccount.getAccountId());

                Map l_map = new HashMap();
                l_map.put("only_mobile_open_div", l_request.changedInfo.mobileAccoutDiv);
                l_map.put("only_mbl_opn_div_last_updater", l_strAdminCode);
                l_map.put("only_mbl_opn_div_timestamp", GtlUtils.getTradingSystem().getSystemTimestamp());

                l_queryProcessor.doUpdateQuery(l_mainAccountPK, l_map);

            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //createResponse( )
        WEB3AdminInformAccSwElecDeliChangeCmpResponse l_response =
            (WEB3AdminInformAccSwElecDeliChangeCmpResponse)l_request.createResponse();

        l_response.dateInfo = l_deliAppDtInfo;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate���)<BR>
     * �����ؑցE�d�q��t�\������m�F��ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�Ǘ��ҁi�����ؑցE�d�q��t�\���jvalidate����v �Q�ƁB<BR>
     * =============================================== <BR>
     * �@@�V�[�P���X�}�@@:validate���<BR>
     * �@@��̈ʒu�@@:�@@get�ڋq()�ɂĊY���f�[�^���擾�ł��Ȃ��ꍇ�A��O��throw����B<BR>
     * �@@class�@@:�@@WEB3BusinessLayerException  <BR>
     * �@@tag�@@�@@:�@@BUSINESS_ERROR_01035<BR>
     * =============================================== <BR>
     * ===============================================<BR>
     * �@@�V�[�P���X�}�@@:validate���<BR>
     * �@@��̈ʒu�@@�@@�@@:�e��A���s���擾�ł��Ȃ��ꍇ�i�߂�l == null�j�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@class:�@@WEB3BusinessLayerException<BR>
     * �@@tag  :�@@BUSINESS_ERROR_01037<BR>
     * ===============================================<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminInformAccSwElecDeliDeleteConfResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminInformAccSwElecDeliDeleteConfResponse validateCancel(
        WEB3AdminInformAccSwElecDeliDeleteConfRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateCancel(WEB3AdminInformAccSwElecDeliDeleteConfRequest)";
        log.entering(STR_METHOD_NAME);

        //validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�@@�\�J�e�S���R�[�h�F "A0106"
        //is�X�V�F true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCOUNT_SWITCH_ELECTRON_DELI_APPLY,
            true);

        //validate���X����(���X�R�[�h : String)
        //���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h
        l_administrator.validateBranchPermission(l_request.branchCode);

        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        try
        {
            //get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
            // �،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
            // ���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h
            // �����R�[�h�F�@@���N�G�X�g�f�[�^.�ڋq�R�[�h
            l_gentradeAccountManager.getMainAccount(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.accountCode);
        }
        //get�ڋq()�ɂĊY���f�[�^���擾�ł��Ȃ��ꍇ�A��O��throw����B
        catch (WEB3BaseException l_ex)
        {
            log.error("�Y������ڋq�����݂��܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //get�e��A���s(String, String, String, String)
        //�m�����n
        //�،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
        //���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h
        //���ʃR�[�h�F�@@���N�G�X�g�f�[�^.���ʃR�[�h
        //�A����ʁF�@@���N�G�X�g�f�[�^.�A�����
        VariousInformParams l_variousInformParams =
            WEB3Inform.getVariousInform(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.requestNumber,
                l_request.informType);

        //�e��A���s���擾�ł��Ȃ��ꍇ�i�߂�l == null�j�A��O���X���[����B
        if (l_variousInformParams == null)
        {
            log.debug("�����ɊY������f�[�^�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����ɊY������f�[�^�����݂��Ȃ��B");
        }

        //is�g���K���s(�������� : String)
        //���������F 0�iDEFAULT�j
        boolean l_blnIsSubmitMarketTrigger =
            WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
                WEB3OrderingConditionDef.DEFAULT);

        // �����ؑցE�d�q��t�\�����ʃT�[�r���擾����B
        WEB3InformAccSwElecDeliApplyCommonService l_informAccSwElecDeliApplyCommonService =
            (WEB3InformAccSwElecDeliApplyCommonService)Services.getService(
                WEB3InformAccSwElecDeliApplyCommonService.class);

        //validate�`�[���(String, boolean)
        //�쐬�󋵁F�@@get�e��A���s().get�쐬��()�̖߂�l
        //�g���K�[���s�敪�F�@@is�g���K�[���s()�̖߂�l
        l_informAccSwElecDeliApplyCommonService.validateVoucherCancel(
            l_variousInformParams.getStatus(),
            l_blnIsSubmitMarketTrigger);

        //create�����ؑցE�d�q��t�\�����(�e��A��Params)
        //�e��A���s�F get�e��A���s()�̖߂�l
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_adminInformAccSwitchElecDeliApplyInfo =
            l_informAccSwElecDeliApplyCommonService.createAccSwitchElecDeliApplyInfo(
                l_variousInformParams);

        //createResponse( )
        WEB3AdminInformAccSwElecDeliDeleteConfResponse l_response =
            (WEB3AdminInformAccSwElecDeliDeleteConfResponse)l_request.createResponse();

        //)���X�|���X�f�[�^�v���p�e�B�Ɉȉ��̒ʂ�l���Z�b�g����B
        //���X�|���X�f�[�^.���X�R�[�h   �� �e��A���s.���X�R�[�h
        l_response.branchCode = l_variousInformParams.getBranchCode();

        //���X�|���X�f�[�^.�ڋq�R�[�h  �� �e��A���s.�ڋq�R�[�h
        l_response.accountCode = l_variousInformParams.getAccountCode();

        //���X�|���X�f�[�^.�ڋq��    �� �e��A���s.�ڋq��
        l_response.accountName = l_variousInformParams.getAccountName();

        //���X�|���X�f�[�^.�ύX����  �� create�����ؑցE�d�q��t�\�����()�̖߂�l
        l_response.changedInfo = l_adminInformAccSwitchElecDeliApplyInfo;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit���)<BR>
     * �����ؑցE�d�q��t�\�����������ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�Ǘ��ҁi�����ؑցE�d�q��t�\���jsubmit����v �Q�ƁB<BR>
     * =============================================== <BR>
     * �@@�V�[�P���X�}�@@:submit���<BR>
     * �@@��̈ʒu�@@:�@@get�ڋq()�ɂĊY���f�[�^���擾�ł��Ȃ��ꍇ�A��O��throw����B<BR>
     * �@@class�@@:�@@WEB3BusinessLayerException  <BR>
     * �@@tag�@@�@@:�@@BUSINESS_ERROR_01035<BR>
     * =============================================== <BR>
     * ===============================================<BR>
     * �@@�V�[�P���X�}�@@:submit���<BR>
     * �@@��̈ʒu�@@�@@�@@:�e��A���s���擾�ł��Ȃ��ꍇ�i�߂�l == null�j�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@class:�@@WEB3BusinessLayerException<BR>
     * �@@tag  :�@@BUSINESS_ERROR_01037<BR>
     * ===============================================<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminInformAccSwElecDeliApplyDeleteResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminInformAccSwElecDeliDeleteCmpResponse submitCancel(
        WEB3AdminInformAccSwElecDeliDeleteCmpRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitCancel(WEB3AdminInformAccSwElecDeliDeleteCmpRequest)";
        log.entering(STR_METHOD_NAME);

        //validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�@@�\�J�e�S���R�[�h�F "A0106"
        //is�X�V�F true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCOUNT_SWITCH_ELECTRON_DELI_APPLY,
            true);

        //validate���X����(���X�R�[�h : String)
        //���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h
        l_administrator.validateBranchPermission(l_request.branchCode);

        // validate����p�X���[�h(�p�X���[�h : String)
        //�p�X���[�h�F�@@���N�G�X�g�f�[�^.�Ïؔԍ�
        l_administrator.validateTradingPassword(l_request.password);

        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = null;

        try
        {
            //get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
            // �،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
            // ���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h
            // �����R�[�h�F�@@���N�G�X�g�f�[�^.�ڋq�R�[�h
            l_mainAccount = l_gentradeAccountManager.getMainAccount(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.accountCode);
        }
        //get�ڋq()�ɂĊY���f�[�^���擾�ł��Ȃ��ꍇ�A��O��throw����B
        catch (WEB3BaseException l_ex)
        {
            log.error("�Y������ڋq�����݂��܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //get�e��A���s(String, String, String, String)
        //�m�����n
        //�،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
        //���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h
        //���ʃR�[�h�F�@@���N�G�X�g�f�[�^.���ʃR�[�h
        //�A����ʁF�@@���N�G�X�g�f�[�^.�A�����
        VariousInformParams l_variousInformParams =
            WEB3Inform.getVariousInform(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.requestNumber,
                l_request.informType);

        //�e��A���s���擾�ł��Ȃ��ꍇ�i�߂�l == null�j�A��O���X���[����B
        if (l_variousInformParams == null)
        {
            log.debug("�����ɊY������f�[�^�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����ɊY������f�[�^�����݂��Ȃ��B");
        }

        //is�g���K���s(�������� : String)
        //���������F 0�iDEFAULT�j
        boolean l_blnIsSubmitMarketTrigger =
            WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
                WEB3OrderingConditionDef.DEFAULT);

        // �����ؑցE�d�q��t�\�����ʃT�[�r���擾����B
        WEB3InformAccSwElecDeliApplyCommonService l_informAccSwElecDeliApplyCommonService =
            (WEB3InformAccSwElecDeliApplyCommonService)Services.getService(
                WEB3InformAccSwElecDeliApplyCommonService.class);

        //validate�`�[���(String, boolean)
        //�쐬�󋵁F�@@get�e��A���s().get�쐬��()�̖߂�l
        //�g���K�[���s�敪�F�@@is�g���K�[���s()�̖߂�l
        l_informAccSwElecDeliApplyCommonService.validateVoucherCancel(
            l_variousInformParams.getStatus(),
            l_blnIsSubmitMarketTrigger);

        //���E��c�d�q��t�E��������o�^(�e��A��Params)
        //�e��A���s�F�@@get�e��A���s()�̖߂�l
        WEB3InformConditionRegVoucher l_informHostConditionRegVoucher =
            new WEB3InformConditionRegVoucher(l_variousInformParams);

        // delete���E��c�d�q��t�E��������o�^�`�[�L���[( )
        //���E��c�d�q��t�E��������o�^�iGI311�j�L���[�e�[�u���̃��R�[�h���폜����B
        l_informHostConditionRegVoucher.deleteHostConditionRegVoucher();

        //get�Ǘ��҃R�[�h( )
        String l_strAdministratorCode = l_administrator.getAdministratorCode();

        //�e��A��(�e��A�����)
        //�e��A���s�Fget�e��A���s�i�j�̖߂�l
        WEB3Inform l_inform = new WEB3Inform(l_variousInformParams);

        //�ȉ��̓��e��Map�I�u�W�F�N�g�𐶐�����
        //�X�V�҃R�[�h�F get�Ǘ��҃R�[�h�i�j�̖߂�l
        //�`�[�쐬�󋵁F 0�F���쐬
        //�X�V�����F   ��������
        Map l_updateInformMap = new HashMap();
        l_updateInformMap.put("last_updater", l_strAdministratorCode);
        l_updateInformMap.put("status", WEB3VoucherCreateStatusDef.NOT_CREATE);
        l_updateInformMap.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

        //update�e��A��(Map)
        //DB�X�V�d�l�u���z���U�֌����`�[���_�e��A���e�[�u��.xls�v
        //���z���U�֌����`�[���_update�Q��
        //�G���[���R�R�[�h      null
        l_updateInformMap.put("error_reason_code", null);
        //�`�[���ʃR�[�h  null
        l_updateInformMap.put("order_request_number", null);
        //�f�[�^�R�[�h null
        l_updateInformMap.put("request_code", null);
        //�`�[���M���� null
        l_updateInformMap.put("send_timestamp", null);
        //�`�[��M���� null
        l_updateInformMap.put("receipt_timestamp", null);

        l_inform.updateInform(l_updateInformMap);

        //���o�C����p�����J�݋敪������������B
        try
        {
            //getDefaultProcessor( )
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //�ڋq�}�X�^�e�[�u�����X�V����B
            //[doUpdateQuery()�Ɏw�肷�����]
            //arg0�iprimaryKey�j�F�@@�ڋq.get�ڋq�s.����ID
            //arg1�ichanges�j�F
            //�y��Trade�z�⑫����.DB�X�V�u�����ؑցE�d�q��t�\�����_�ڋq�}�X�^�[�v�Q�ƁB
            MainAccountPK l_mainAccountPK =
                new MainAccountPK(l_mainAccount.getMainAccountRow().getAccountId());

            Map l_mainAccountMap = new HashMap();

            //���o�C����p�����J�݋敪
            l_mainAccountMap.put("only_mobile_open_div", l_variousInformParams.getExtDiv12());

            //���o�C����p�����J�݋敪�X�V�҃R�[�h
            l_mainAccountMap.put("only_mbl_opn_div_last_updater", l_variousInformParams.getExtText5());

            //���o�C����p�����J�݋敪�X�V����
            l_mainAccountMap.put(
                "only_mbl_opn_div_timestamp",
                WEB3DateUtility.getDate(
                    l_variousInformParams.getExtText6(),
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS));

            l_queryProcessor.doUpdateQuery(l_mainAccountPK, l_mainAccountMap);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //createResponse( )
        WEB3AdminInformAccSwElecDeliDeleteCmpResponse l_response =
            (WEB3AdminInformAccSwElecDeliDeleteCmpResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (update�e��A���ύX���)<BR>
     * �e��A���̕ύX�����X�V����B<BR>
     * <BR>
     * DB�X�V�d�l�u�����ؑցE�d�q��t�\���ύX_�e��A���e�[�u��.xls�v�Q��<BR>
     * <BR>
     * �m�X�V�����n<BR>
     * �،���ЃR�[�h = (����)�e��A���s.get�،���ЃR�[�h�i�j<BR>
     * �A����� = (����)�e��A���s.get�A����ʁi�j<BR>
     * ���ʃR�[�h = (����)���ʃR�[�h<BR>
     * ���X�R�[�h = (����)�e��A���s.get���X�R�[�h�i�j<BR>
     * <BR>
     * @@param l_variousInformParams - (�e��A���s)<BR>
     * �e��A���s<BR>
     * @@param l_strRequestNumber - (���ʃR�[�h)<BR>
     * ���ʃR�[�h<BR>
     * @@param l_strOrderRequestNumber - (�`�[���ʃR�[�h)<BR>
     * �`�[���ʃR�[�h<BR>
     * @@param l_strRequestCode - (�f�[�^�R�[�h)<BR>
     * �f�[�^�R�[�h<BR>
     * @@param l_strVoucherMake - (�`�[�쐬��)<BR>
     * �`�[�쐬��<BR>
     * @@throws WEB3BaseException
     */
    private void updateVariousInformChangeInfo(
        VariousInformParams l_variousInformParams,
        String l_strRequestNumber,
        String l_strOrderRequestNumber,
        String l_strRequestCode,
        String l_strVoucherMake) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "updateVariousInformChangeInfo(VariousInformParams, String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        if (l_variousInformParams == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        try
        {
            //get�ύX�O�e��A���s
            //�،���ЃR�[�h = (����)�e��A���s.get�،���ЃR�[�h�i�j
            //�A����� = (����)�e��A���s.get�A����ʁi�j
            //���ʃR�[�h = (����)���ʃR�[�h
            //���X�R�[�h = (����)�e��A���s.get���X�R�[�h�i�j
            VariousInformRow l_beforeVariousInformRow =
                VariousInformDao.findRowByPk(
                    l_variousInformParams.getInstitutionCode(),
                    l_variousInformParams.getInformDiv(),
                    l_strRequestNumber,
                    l_variousInformParams.getBranchCode());

            //�e��A���̕ύX�����X�V����B
            //DB�X�V�d�l�u�����ؑցE�d�q��t�\���ύX_�e��A���e�[�u��.xls�v�Q��
            //���ʃR�[�h
            //�����l
            l_variousInformParams.setRequestNumber(l_beforeVariousInformRow.getRequestNumber());

            //�ڋq�R�[�h
            //�����l
            l_variousInformParams.setAccountCode(l_beforeVariousInformRow.getAccountCode());

            //���҃R�[�h
            //�����l
            l_variousInformParams.setTraderCode(l_beforeVariousInformRow.getTraderCode());

            //�ڋq��
            //�����l
            l_variousInformParams.setAccountName(l_beforeVariousInformRow.getAccountName());

            //�ڋq���[���A�h���X
            //�����l
            l_variousInformParams.setEmailAddress(l_beforeVariousInformRow.getEmailAddress());

            //�敪�P�Q
            //�����l
            l_variousInformParams.setExtDiv12(l_beforeVariousInformRow.getExtDiv12());

            //�e�L�X�g�T
            //�����l
            l_variousInformParams.setExtText5(l_beforeVariousInformRow.getExtText5());

            //�e�L�X�g�U
            //�����l
            l_variousInformParams.setExtText6(l_beforeVariousInformRow.getExtText6());

            //�X�V�҃R�[�h
            //�Ǘ��҃R�[�h
            WEB3Administrator l_administrator =
                WEB3Administrator.getInstanceFromLoginInfo();

            String l_strAdministratorCode = l_administrator.getAdministratorCode();
            l_variousInformParams.setLastUpdater(l_strAdministratorCode);

            //�쐬����
            //�����l
            l_variousInformParams.setCreatedTimestamp(l_beforeVariousInformRow.getCreatedTimestamp());

            //�X�V����
            //��������
            l_variousInformParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            //�����R�[�h
            //�����l
            l_variousInformParams.setFundCode(l_beforeVariousInformRow.getFundCode());

            //���҃R�[�h�iSONAR�j
            //�����l
            l_variousInformParams.setSonarTraderCode(l_beforeVariousInformRow.getSonarTraderCode());

            //�`�[�쐬��
            //1�F�쐬��
            l_variousInformParams.setStatus(l_strVoucherMake);

            //�G���[���R�R�[�h
            //null
            l_variousInformParams.setErrorReasonCode(null);

            //�`�[���ʃR�[�h
            //����:�`�[���ʃR�[�h
            l_variousInformParams.setOrderRequestNumber(l_strOrderRequestNumber);

            //�f�[�^�R�[�h
            //����:�f�[�^�R�[�h
            l_variousInformParams.setRequestCode(l_strRequestCode);

            //�`�[���M����
            //null
            l_variousInformParams.setSendTimestamp(null);

            //�`�[��M����
            //null
            l_variousInformParams.setReceiptTimestamp(null);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_variousInformParams);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
