head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҋ����@@��t�{���o�^�T�[�r�XImpl(WEB3AdminFPTRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 �đo�g (���u) �V�K�쐬
Revision History : 2007/10/15 �đo�g (���u) ���f��006
Revision History : 2007/10/18 Inomata (SCS) ���f��008
Revision History : 2007/10/19 Inomata (SCS) ���f��009
Revision History : 2007/01/28 ���g (���u) �d�l�ύX ���f�� No.024
Revision History : 2008/02/13 ���g (���u) �����̖��001
Revision History : 2008/03/17 �g�C�� (���u) �d�l�ύX�E���f��No.046
*/

package webbroker3.docadmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.docadmin.WEB3AdminFPTCommon;
import webbroker3.docadmin.WEB3AdminFPTDocDeliveryManagement;
import webbroker3.docadmin.message.WEB3AdminFPTRegistCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTRegistCompleteResponse;
import webbroker3.docadmin.message.WEB3AdminFPTRegistConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTRegistConfirmResponse;
import webbroker3.docadmin.message.WEB3AdminFPTRegistInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTRegistInputResponse;
import webbroker3.docadmin.message.WEB3FPTDocumentCategoryDetailsInfoUnit;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTRegistService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.DocDeliveryManagementParams;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҋ����@@��t�{���o�^�T�[�r�XImpl)<BR>
 * �Ǘ��ҋ����@@��t�{���o�^�T�[�r�X�����N���X<BR>
 * <BR>
 * @@author �đo�g
 * @@version 1.0
 */
public class WEB3AdminFPTRegistServiceImpl implements WEB3AdminFPTRegistService
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTRegistServiceImpl.class);

    /**
     * @@roseuid 46FDDD350222
     */
    public WEB3AdminFPTRegistServiceImpl()
    {

    }

    /**
     * �����@@��t�{���o�^���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��ҋ����@@��t�{���o�^���̓��N�G�X�g�̏ꍇ<BR>
     * �@@�|get���͉��()���R�[������B<BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��ҋ����@@��t�{���o�^�m�F���N�G�X�g�̏ꍇ<BR>
     * �@@�|validate�ύX()���R�[������B<BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��ҋ����@@��t�{���o�^�������N�G�X�g�̏ꍇ<BR>
     * �@@�|submit�ύX()���R�[������B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminFPTRegistInputRequest)
        {
            l_response = this.getInputScreen(
                (WEB3AdminFPTRegistInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFPTRegistConfirmRequest)
        {
            l_response = this.validateChangedScreen(
                (WEB3AdminFPTRegistConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFPTRegistCompleteRequest)
        {
            l_response = this.submitChangedScreen(
                (WEB3AdminFPTRegistCompleteRequest)l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get���͉��)<BR>
     * �����@@��t�{���o�^���͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uget���͉�ʁv�Q�ƁB<BR>
     * <BR>
     * @@param l_request - (�Ǘ��ҋ����@@��t�{���o�^���̓��N�G�X�g�f�[�^�I�u�W�F�N�g)<BR>
     * �Ǘ��ҋ����@@��t�{���o�^���̓��N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3AdminFPTRegistInputResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminFPTRegistInputResponse getInputScreen(
        WEB3AdminFPTRegistInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminFPTRegistInputRequest)";
        log.entering(STR_METHOD_NAME);

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[validate�����i�j�Ɏw�肷�����]
        //�@@�\�J�e�S���R�[�h�F"G0101"(�����@@��t�{���o�^)
        //is�X�V�Ftrue
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.FPT_REG,
            true);

        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //get���X�R�[�h( )
        String l_strBranchCode = l_administrator.getBranchCode();

        //get���ʎ�ޏڍ׏��
        //�،���ЃR�[�h�F �Ǘ���.get�،���ЃR�[�h()�̖߂�l
        //���X�R�[�h�F�Ǘ���.get���X�R�[�h()�̖߂�l
        //���ʋ敪�Fnull
        //���ʎ�ރR�[�h�F null
        WEB3FPTDocumentCategoryDetailsInfoUnit[] l_documentCategoryDetailsInfoUnits =
            WEB3AdminFPTCommon.getDocumentCategoryDetailsInfoUnit(
                l_strInstitutionCode,
                l_strBranchCode,
                null,
                null);

        //createResponse( )
        WEB3AdminFPTRegistInputResponse l_response =
            (WEB3AdminFPTRegistInputResponse)l_request.createResponse();

        //�v���p�e�B�Z�b�g
        //���ݓ��t�F�V�X�e�����ݓ���
        l_response.currentDate = GtlUtils.getSystemTimestamp();

        //���ʎ�ޏڍ׏��[]�F�����@@����.get������ޏڍ׏��()�̖߂�l
        l_response.documentCategoryList = l_documentCategoryDetailsInfoUnits;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�ύX���)<BR>
     * �����@@��t�{���o�^�m�F��ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uvalidate�ύX��ʁv�Q�ƁB<BR>
     * <BR>
     * @@param l_request - (�Ǘ��ҋ����@@��t�{���o�^�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g)<BR>
     * �Ǘ��ҋ����@@��t�{���o�^�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3AdminFPTRegistConfirmResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminFPTRegistConfirmResponse validateChangedScreen(
        WEB3AdminFPTRegistConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateChangedScreen(WEB3AdminFPTRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[validate�����i�j�Ɏw�肷�����]
        //�@@�\�J�e�S���R�[�h�F"G0101"(�����@@��t�{���o�^)
        //is�X�V�Ftrue
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.FPT_REG,
            true);

        //validate���X����(���X�R�[�h : String)
        //[����]
        //���N�G�X�g�f�[�^.���X�R�[�h
        l_administrator.validateBranchPermission(l_request.branchCode);

        //get�،����( )
        Institution l_institution = l_administrator.getInstitution();

        //get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
        //[����]
        // �،���ЃR�[�h�F�Ǘ���.get�،����().get�،���ЃR�[�h()�̖߂�l
        // ���X�R�[�h�F���N�G�X�g�f�[�^.���X�R�[�h
        // �����R�[�h�F���N�G�X�g�f�[�^.�ڋq�R�[�h
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount =
            l_accountManager.getMainAccount(
                l_institution.getInstitutionCode(),
                l_request.branchCode,
                l_request.acceptCode);

        //create��������������
        String l_strQueryString = this.createQueryString(l_request);

        //create���������f�[�^�R���e�i
        //����ID�F �ڋq.getAccountId()�̖߂�l
        //�������F �Ǘ��ҋ����@@��t�{���o�^�m�F���N�G�X�g
        Object[] l_queryDataContainers =
            this.createQueryDataContainer(l_mainAccount.getAccountId(), l_request);

        //get�ڋq�\����( )
        String l_strDisplayAccountName = l_mainAccount.getDisplayAccountName();

		//���ʌ�t�Ǘ�( )
		WEB3AdminFPTDocDeliveryManagement l_docDeliveryManagement =
			new WEB3AdminFPTDocDeliveryManagement();

        //get���ʌ�t�Ǘ�
        //����������F create��������������()�̖߂�l
        //�����f�[�^�R���e�i�F create���������f�[�^�R���e�i()�̖߂�l
        int l_intDocDivManagement = l_docDeliveryManagement.getDocDivManagement(
                l_strQueryString, l_queryDataContainers).size();

        //���ɓo�^�ς݂ł������ꍇ�iget���ʌ�t�Ǘ�() > 0�j�A��O�𐶐�����B
        //���ɓo�^�ς݂ł������ꍇ�i���ʌ�t�Ǘ�.get���ʌ�t�Ǘ�()�̖߂�l���� > 0�j�A��O���X���[����B
		if (l_intDocDivManagement > 0){
					log.debug("�w��̌ڋq�͊��ɓo�^����Ă��܂��B");
					log.exiting(STR_METHOD_NAME);
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_02950,
						this.getClass().getName() + STR_METHOD_NAME,
						"�w��̌ڋq�͊��ɓo�^����Ă��܂��B");
				}

        //createResponse( )
        WEB3AdminFPTRegistConfirmResponse l_response =
            (WEB3AdminFPTRegistConfirmResponse)l_request.createResponse();

        //�v���p�e�B�Z�b�g
        //�ڋq���F�ڋq.get�ڋq�\����()�̖߂�l
        l_response.acceptName = l_strDisplayAccountName;

        //�������F ""
        l_response.productName = "";

        log.entering(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�ύX���)<BR>
     * �����@@��t�{���o�^������ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �usubmit�ύX��ʁv�Q�ƁB<BR>
     * <BR>
     * @@param l_request - (�Ǘ��ҋ����@@��t�{���o�^�������N�G�X�g�f�[�^�I�u�W�F�N�g)<BR>
     * �Ǘ��ҋ����@@��t�{���o�^�������N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3AdminFPTRegistCompleteResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminFPTRegistCompleteResponse submitChangedScreen(
        WEB3AdminFPTRegistCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitChangedScreen(WEB3AdminFPTRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[validate�����i�j�Ɏw�肷�����]
        //�@@�\�J�e�S���R�[�h�F"G0101"(�����@@��t�{���o�^)
        //is�X�V�Ftrue
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.FPT_REG,
            true);

        //validate���X����(���X�R�[�h : String)
        //[����]
        //���N�G�X�g�f�[�^.���X�R�[�h
        l_administrator.validateBranchPermission(l_request.branchCode);

        //validate����p�X���[�h(�p�X���[�h : String)
        //[����]
        // �p�X���[�h�F�@@���N�G�X�g�f�[�^.�Ïؔԍ�
        l_administrator.validateTradingPassword(l_request.password);

        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //get�Ǘ��҃R�[�h( )
        String  l_strAdministratorCode = l_administrator.getAdministratorCode();

        //get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
        //[����]
        // �،���ЃR�[�h�F�Ǘ���.get�،���ЃR�[�h()�̖߂�l
        // ���X�R�[�h�F���N�G�X�g�f�[�^.���X�R�[�h
        // �����R�[�h�F���N�G�X�g�f�[�^.�ڋq�R�[�h
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount =
            l_accountManager.getMainAccount(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.acceptCode);

        //getAccountId( )
        long l_lngAccountId = l_mainAccount.getAccountId();

        //create��������������
        String l_strQueryString = this.createQueryString(l_request);

        //create���������f�[�^�R���e�i
        //����ID�F �ڋq.getAccountId()�̖߂�l
        //�������F �Ǘ��ҋ����@@��t�{���o�^�������N�G�X�g
        Object[] l_queryDataContainers =
            this.createQueryDataContainer(l_lngAccountId, l_request);

        //���ʌ�t�Ǘ�( )
        WEB3AdminFPTDocDeliveryManagement l_docDeliveryManagement =
            new WEB3AdminFPTDocDeliveryManagement();

        //get���ʌ�t�Ǘ�
        //����������F create��������������()�̖߂�l
        //�����f�[�^�R���e�i�F create���������f�[�^�R���e�i()�̖߂�l
        int l_intDocDivManagement = l_docDeliveryManagement.getDocDivManagement(
            l_strQueryString, l_queryDataContainers).size();

		//get���ʌ�t�Ǘ��e�[�u���s( )
		if (l_intDocDivManagement > 0){
					log.debug("�w��̌ڋq�͊��ɓo�^����Ă��܂��B");
					log.exiting(STR_METHOD_NAME);
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_02950,
						this.getClass().getName() + STR_METHOD_NAME,
						"�w��̌ڋq�͊��ɓo�^����Ă��܂��B");
				}

        //�v���p�e�B�Z�b�g
        //���N�G�X�g�f�[�^.���ʎ�ވꗗ�̒�����LOOP����
        int l_intDocumentCategoryList =
            l_request.documentCategoryList.length;
        for (int i = 0; i < l_intDocumentCategoryList; i++)
        {
            int l_intBatoProductCodeAdminInfo =
                l_request.documentCategoryList[i].batoProductCodeAdminInfo.length;
            for (int j = 0; j < l_intBatoProductCodeAdminInfo; j++)
            {
                DocDeliveryManagementParams l_docDeliveryManagementParams =
                    new DocDeliveryManagementParams();

                //���ʌ�t�Ǘ��e�[�u��Params.����ID = �ڋq.getAccountId() �̖߂�l
                l_docDeliveryManagementParams.setAccountId(l_lngAccountId);

                //���ʌ�t�Ǘ��e�[�u��Params.�،���ЃR�[�h = �Ǘ���.get�،���ЃR�[�h() �̖߂�l
                l_docDeliveryManagementParams.setInstitutionCode(l_strInstitutionCode);

                //���ʌ�t�Ǘ��e�[�u��Params.���X�R�[�h = ���N�G�X�g�f�[�^.���X�R�[�h
                l_docDeliveryManagementParams.setBranchCode(l_request.branchCode);

                //���ʌ�t�Ǘ��e�[�u��Params.�ڋq�R�[�h = �ڋq.getAccountCode() �̖߂�l
                l_docDeliveryManagementParams.setAccountCode(l_mainAccount.getAccountCode());

                //���ʌ�t�Ǘ��e�[�u��Params.���ʋ敪 = ���N�G�X�g�f�[�^.���ʎ�ވꗗ[index].���ʋ敪�Ǘ����.���ʋ敪
                l_docDeliveryManagementParams.setDocumentDiv(
                    l_request.documentCategoryList[i].documentDivList.documentDiv);

                //���ʌ�t�Ǘ��e�[�u��Params.�����R�[�h = ���N�G�X�g�f�[�^.���ʎ�ވꗗ[index].�d�q�������R�[�h�Ǘ����[idx].�d�q�������R�[�h
                l_docDeliveryManagementParams.setProductCode(
                    l_request.documentCategoryList[i].batoProductCodeAdminInfo[j].batoProductCode);

                //���ʌ�t�Ǘ��e�[�u��Params.���ʌ�t�� = ���N�G�X�g�f�[�^.���ʌ�t��
                l_docDeliveryManagementParams.setDeliveryDate(l_request.docuDeliDate);

                //���ʌ�t�Ǘ��e�[�u��Params.�폜�t���O = "0"
                l_docDeliveryManagementParams.setDeleteFlag(BooleanEnum.FALSE);

                //���ʌ�t�Ǘ��e�[�u��Params.�X�V�҃R�[�h = �Ǘ���.get�Ǘ��҃R�[�h()�̖߂�l
                l_docDeliveryManagementParams.setLastUpdater(l_strAdministratorCode);

                //���ʌ�t�Ǘ��e�[�u��Params.�쐬���t = ���ݓ���
                Timestamp l_tsCurrentTime = GtlUtils.getSystemTimestamp();
                l_docDeliveryManagementParams.setCreatedTimestamp(l_tsCurrentTime);

                //���ʌ�t�Ǘ��e�[�u��Params.�X�V���t = ���ݓ���
                l_docDeliveryManagementParams.setLastUpdatedTimestamp(l_tsCurrentTime);

                //���ʌ�t�Ǘ��e�[�u��Params.���ʎ�ރR�[�h = ���N�G�X�g�f�[�^.���ʎ�ވꗗ[index].���ʋ敪�Ǘ����.���ʎ�ރR�[�h
                l_docDeliveryManagementParams.setDocumentCategory(
                    l_request.documentCategoryList[i].documentDivList.documentCategory);

                //���ʌ�t�Ǘ��e�[�u��Params.�݂Ȃ���t�� = null
                l_docDeliveryManagementParams.setDeemedDeliveryDate(null);

                //insert���ʌ�t�Ǘ��e�[�u��(���ʌ�t�Ǘ��e�[�u��Params)
                l_docDeliveryManagement.insertDocDeliveryManagementParams(l_docDeliveryManagementParams);
            }
        }

        // createResponse( )
        WEB3AdminFPTRegistCompleteResponse l_response =
            (WEB3AdminFPTRegistCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create���������f�[�^�R���e�i)<BR>
     * �擾�����ɃZ�b�g����l�̔z��𐶐�����B<BR>
     * <BR>
     * �P�j�@@���ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@����ID<BR>
     * <BR>
     * �@@�@@�@@�@@����.����ID ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �R�j�@@���ʋ敪�E���ʎ�ރR�[�h�E�d�q�������R�[�h<BR>
     * �@@�@@�@@�@@����.�������.���ʎ�ވꗗ�̒�����LOOP�������s���B<BR>
     * <BR>
     * �@@�R-�P�j�@@����.�������.���ʎ�ވꗗ[index].���ʋ敪�Ǘ����.���ʋ敪��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�P�j ��List�ɒǉ�����B<BR>
     * <BR>
     * �@@�R-�Q�j�@@����.�������.���ʎ�ވꗗ[index].���ʋ敪�Ǘ����.���ʎ�ރR�[�h��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�P�j ��List�ɒǉ�����B<BR>
     * <BR>
     * �@@�R-�R�j�@@����.�������.���ʎ�ވꗗ[index].�d�q�������R�[�h�Ǘ����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�z����̑S�Ă̓d�q�������R�[�h�� �P�j ��List�ɒǉ�����B<BR>
     * <BR>
     * �S�j�@@���ʌ�t��<BR>
     * <BR>
     * �@@�@@�@@�@@����.���ʌ�t�� ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * <BR>
     * �T�j�@@�������ꂽList����z����擾���A�ԋp����B<BR>
     * <BR>
     * <BR>
     * @@param l_lngAccountId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_request - (�������)<BR>
     * �������<BR>
     * @@return Object[]
     * @@roseuid 4726EFED0213
     */
    private Object[] createQueryDataContainer(
        long l_lngAccountId,
        WEB3GenRequest l_request)
    {
        final String STR_METHOD_NAME = "createQueryDataContainer(String, WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        //�P�j ���ArrayList�𐶐�����B
        List l_lisQueryDataContainers = new ArrayList();

        //�Q�j�@@����ID
        //����.����ID ���P�j��List�ɒǉ�����B
        l_lisQueryDataContainers.add(new Long(l_lngAccountId));

        //�R�j�@@���ʋ敪�E���ʎ�ރR�[�h�E�d�q�������R�[�h
        //����.�������.���ʎ�ވꗗ�̒�����LOOP�������s���B
        if (l_request instanceof WEB3AdminFPTRegistConfirmRequest)
        {
            WEB3AdminFPTRegistConfirmRequest l_registConfirmRequest =
                (WEB3AdminFPTRegistConfirmRequest)l_request;
            int l_intDocumentCategoryList = l_registConfirmRequest.documentCategoryList.length;
            for (int i = 0; i < l_intDocumentCategoryList; i++)
            {
                //�R-�P�j�@@����.�������.���ʎ�ވꗗ[index].���ʋ敪�Ǘ����.���ʋ敪��
                //�P�j ��List�ɒǉ�����B
                l_lisQueryDataContainers.add(
                    l_registConfirmRequest.documentCategoryList[i].documentDivList.documentDiv);

                //�R-�Q�j�@@����.�������.���ʎ�ވꗗ[index].���ʋ敪�Ǘ����.���ʎ�ރR�[�h��
                //�P�j ��List�ɒǉ�����B
                l_lisQueryDataContainers.add(
                    l_registConfirmRequest.documentCategoryList[i].documentDivList.documentCategory);

                //�R-�R�j�@@����.�������.���ʎ�ވꗗ[index].�d�q�������R�[�h�Ǘ����
                //�z����̑S�Ă̓d�q�������R�[�h�� �P�j ��List�ɒǉ�����B
                int l_intBatoProductCodeAdminInfo =
                    l_registConfirmRequest.documentCategoryList[i].batoProductCodeAdminInfo.length;
                for (int j = 0; j < l_intBatoProductCodeAdminInfo; j++)
                {
                    l_lisQueryDataContainers.add(
                        l_registConfirmRequest.documentCategoryList[i].batoProductCodeAdminInfo[j].batoProductCode);
                }
            }

            //�S�j�@@���ʌ�t��
            //����.���ʌ�t�� ���P�j��List�ɒǉ�����B
            l_lisQueryDataContainers.add(l_registConfirmRequest.docuDeliDate);
        }
        else if (l_request instanceof WEB3AdminFPTRegistCompleteRequest)
        {
            WEB3AdminFPTRegistCompleteRequest l_registCompleteRequest =
                (WEB3AdminFPTRegistCompleteRequest)l_request;
            int l_intDocumentCategoryList = l_registCompleteRequest.documentCategoryList.length;
            for (int i = 0; i < l_intDocumentCategoryList; i++)
            {
                //�R-�P�j�@@����.�������.���ʎ�ވꗗ[index].���ʋ敪�Ǘ����.���ʋ敪��
                //�P�j ��List�ɒǉ�����B
                l_lisQueryDataContainers.add(
                    l_registCompleteRequest.documentCategoryList[i].documentDivList.documentDiv);

                //�R-�Q�j�@@����.�������.���ʎ�ވꗗ[index].���ʋ敪�Ǘ����.���ʎ�ރR�[�h��
                //�P�j ��List�ɒǉ�����B
                l_lisQueryDataContainers.add(
                    l_registCompleteRequest.documentCategoryList[i].documentDivList.documentCategory);

                //�R-�R�j�@@����.�������.���ʎ�ވꗗ[index].�d�q�������R�[�h�Ǘ����
                //�z����̑S�Ă̓d�q�������R�[�h�� �P�j ��List�ɒǉ�����B
                int l_intBatoProductCodeAdminInfo =
                    l_registCompleteRequest.documentCategoryList[i].batoProductCodeAdminInfo.length;
                for (int j = 0; j < l_intBatoProductCodeAdminInfo; j++)
                {
                    l_lisQueryDataContainers.add(
                        l_registCompleteRequest.documentCategoryList[i].batoProductCodeAdminInfo[j].batoProductCode);
                }
            }

            //�S�j�@@���ʌ�t��
            //����.���ʌ�t�� ���P�j��List�ɒǉ�����B
            l_lisQueryDataContainers.add(l_registCompleteRequest.docuDeliDate);
        }

        //�T�j�@@�������ꂽList����z����擾���A�ԋp����B
        Object[] l_queryDataContainers = new Object[l_lisQueryDataContainers.size()];
        l_lisQueryDataContainers.toArray(l_queryDataContainers);

        log.exiting(STR_METHOD_NAME);
        return l_queryDataContainers;
    }


    /**
     * (create��������������)<BR>
     * �擾�����̕�����𐶐�����B<BR>
     * <BR>
     * �P�j�@@��̕�����𐶐�����B<BR>
     * <BR>
     * �Q�j�@@����ID���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@"account_id = ? and ("<BR>
     * <BR>
     * �R�j�@@���ʋ敪�E���ʎ�ރR�[�h�E�d�q�������R�[�h<BR>
     * �@@�@@�@@����.�������.���ʎ�ވꗗ�̒�����LOOP�������s���B<BR>
     * <BR>
     * �@@�R-�P�j�@@���ʋ敪����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�������������� += " (document_div = ? "<BR>
     * <BR>
     * �@@�R-�Q�j�@@���ʎ�ރR�[�h����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�������������� += " and document_category = ? "<BR>
     * <BR>
     * �@@�R-�R�j�@@����.�������.���ʎ�ވꗗ[index].�d�q�������R�[�h�Ǘ����̒�����<BR>
     * �@@�@@�@@�@@�@@�@@�d�q�������R�[�h����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�������������� += " and product_code in (?, ?, ?,�c)) or "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��LOOP�I������ "or" �s�v <BR>
     * <BR>
     * �S�j�@@���ʌ�t������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�@@�������������� += ") and delivery_date = ?"<BR>
     * <BR>
     * �T�j�@@�쐬������������������C���X�^���X��ԋp����B<BR>
     * <BR>
     * <BR>
     * @@param l_request - (�������)<BR>
     * �������<BR>
     * @@return String
     * @@roseuid 4726EFEA0290
     */
    private String createQueryString(WEB3GenRequest l_request)
    {
        final String STR_METHOD_NAME = "createQueryString()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@��̕�����𐶐�����B
        StringBuffer l_sbQueryString = new StringBuffer();

        //�Q�j�@@����ID���P�j�̕�����ɒǉ�����B
        //"account_id = ? and "
        l_sbQueryString.append(" account_id = ? and (");

        //�R�j�@@���ʋ敪�E���ʎ�ރR�[�h�E�d�q�������R�[�h
        //����.�������.���ʎ�ވꗗ�̒�����LOOP�������s���B
        if (l_request instanceof WEB3AdminFPTRegistConfirmRequest)
        {
            WEB3AdminFPTRegistConfirmRequest l_registConfirmRequest =
                (WEB3AdminFPTRegistConfirmRequest)l_request;
            int l_intDocumentCategoryList = l_registConfirmRequest.documentCategoryList.length;
            for (int i = 0; i < l_intDocumentCategoryList; i++)
            {
                //�R-�P�j�@@���ʋ敪����������������ɒǉ�����B
                //�������������� += " (document_div = ? "
                l_sbQueryString.append(" (document_div = ? ");

                //�R-�Q�j�@@���ʎ�ރR�[�h����������������ɒǉ�����B
                //�������������� += " and document_category = ? "
                l_sbQueryString.append(" and document_category = ? ");

                //�R-�R�j�@@����.�������.���ʎ�ވꗗ[index].�d�q�������R�[�h�Ǘ����̒�����
                //�d�q�������R�[�h����������������ɒǉ�����B
                //�������������� += " and product_code in (?, ?, ?,�c)) or "
                //��LOOP�I������ "or" �s�v
                int l_intBatoProductCodeAdminInfo =
                    l_registConfirmRequest.documentCategoryList[i].batoProductCodeAdminInfo.length;
                String l_strBatoProductCodeAdminInfo = "";
                for (int j = 0; j < l_intBatoProductCodeAdminInfo; j++)
                {
                    if (j == l_intBatoProductCodeAdminInfo - 1)
                    {
                        l_strBatoProductCodeAdminInfo += " ? ";
                    }
                    else
                    {
                        l_strBatoProductCodeAdminInfo += " ?, ";
                    }
                }

                if (i == l_intDocumentCategoryList - 1)
                {
                    l_sbQueryString.append(
                        " and product_code in (" + l_strBatoProductCodeAdminInfo + ")) ");
                }
                else
                {
                    l_sbQueryString.append(
                        " and product_code in (" + l_strBatoProductCodeAdminInfo + ")) or ");
                }
            }
        }
        else if (l_request instanceof WEB3AdminFPTRegistCompleteRequest)
        {
            WEB3AdminFPTRegistCompleteRequest l_registCompleteRequest =
                (WEB3AdminFPTRegistCompleteRequest)l_request;
            int l_intDocumentCategoryList = l_registCompleteRequest.documentCategoryList.length;
            for (int i = 0; i < l_intDocumentCategoryList; i++)
            {
                //�R-�P�j�@@���ʋ敪����������������ɒǉ�����B
                //�������������� += " (document_div = ? "
                l_sbQueryString.append(" (document_div = ? ");

                //�R-�Q�j�@@���ʎ�ރR�[�h����������������ɒǉ�����B
                //�������������� += " and document_category = ? "
                l_sbQueryString.append(" and document_category = ? ");

                //�R-�R�j�@@����.�������.���ʎ�ވꗗ[index].�d�q�������R�[�h�Ǘ����̒�����
                //�d�q�������R�[�h����������������ɒǉ�����B
                //�������������� += " and product_code in (?, ?, ?,�c)) or "
                //��LOOP�I������ "or" �s�v
                int l_intBatoProductCodeAdminInfo =
                    l_registCompleteRequest.documentCategoryList[i].batoProductCodeAdminInfo.length;
                String l_strBatoProductCodeAdminInfo = "";
                for (int j = 0; j < l_intBatoProductCodeAdminInfo; j++)
                {
                    if (j == l_intBatoProductCodeAdminInfo - 1)
                    {
                        l_strBatoProductCodeAdminInfo += " ? ";
                    }
                    else
                    {
                        l_strBatoProductCodeAdminInfo += " ?, ";
                    }
                }

                if (i == l_intDocumentCategoryList - 1)
                {
                    l_sbQueryString.append(
                        " and product_code in (" + l_strBatoProductCodeAdminInfo + ")) ");
                }
                else
                {
                    l_sbQueryString.append(
                        " and product_code in (" + l_strBatoProductCodeAdminInfo + ")) or ");
                }
            }
        }

        //�S�j�@@���ʌ�t������������������ɒǉ�����B
        //�������������� += ") and delivery_date = ?"
        l_sbQueryString.append(") and delivery_date = ? ");

        //�T�j�@@�쐬������������������C���X�^���X��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_sbQueryString.toString();
    }
}
@
