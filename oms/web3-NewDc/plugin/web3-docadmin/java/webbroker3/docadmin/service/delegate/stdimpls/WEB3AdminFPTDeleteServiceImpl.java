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
filename	WEB3AdminFPTDeleteServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҋ����@@��t�{���폜�T�[�r�XImpl(WEB3AdminFPTDeleteServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/11/06 ���g (���u) �V�K�쐬 �d�l�ύX�E���f�� No.010
Revision History : 2008/01/28 ���n�m (���u) �d�l�ύX�E���f��No.026
Revision History : 2008/03/17 �g�C�� (���u) ���f��No.048
*/

package webbroker3.docadmin.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.docadmin.WEB3AdminFPTDocDeliveryManagement;
import webbroker3.docadmin.WEB3AdminFPTDocDeliveryManagementHistory;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTDeleteService;
import webbroker3.docadmin.message.WEB3AdminFPTDeleteConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDeleteCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDeleteConfirmResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDeleteCompleteResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.DocDeliveryManagementHistParams;
import webbroker3.gentrade.data.DocDeliveryManagementRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҋ����@@��t�{���폜�T�[�r�XImpl)<BR>
 * �Ǘ��ҋ����@@��t�{���폜�T�[�r�XImpl�N���X<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminFPTDeleteServiceImpl implements WEB3AdminFPTDeleteService
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDeleteServiceImpl.class);

    /**
     * @@roseuid 472FC5B400A4
     */
    public WEB3AdminFPTDeleteServiceImpl()
    {

    }

    /**
     * �����@@��t�{���폜���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��ҋ����@@��t�{���폜�m�F���N�G�X�g�̏ꍇ<BR>
     * �@@�|validate�ύX()���R�[������B<BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��ҋ����@@��t�{���폜�������N�G�X�g�̏ꍇ<BR>
     * �@@�|submit�ύX()���R�[������B<BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4726CEE5038E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B
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

        if (l_request instanceof WEB3AdminFPTDeleteConfirmRequest)
        {
            // �����̃��N�G�X�g�f�[�^���A�Ǘ��ҋ����@@��t�{���폜�m�F���N�G�X�g�̏ꍇ
            // validate�ύX()���R�[������B
            l_response =
                this.validateChangedScreen((WEB3AdminFPTDeleteConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFPTDeleteCompleteRequest)
        {
            // �����̃��N�G�X�g�f�[�^���A�Ǘ��ҋ����@@��t�{���폜�������N�G�X�g�̏ꍇ
            // submit�ύX()���R�[������B
            l_response =
                this.submitChangedScreen((WEB3AdminFPTDeleteCompleteRequest)l_request);
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
     * (validate�ύX���)<BR>
     * �����@@��t�{���폜�m�F��ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uvalidate�ύX��ʁv�Q�ƁB<BR>
     * =============================================== <BR>
     * �@@�@@��̈ʒu : ���ʌ�t�Ǘ��s���擾�ł��Ȃ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@class�@@�@@: WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@�@@�@@: BUSINESS_ERROR_02952<BR>
     * =============================================== <BR>
     * @@param l_request - (l_request)<BR>
     * �Ǘ��ҋ����@@��t�{���폜�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3AdminFPTDeleteConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4726CF2A01F0
     */
    protected WEB3AdminFPTDeleteConfirmResponse validateChangedScreen(
        WEB3AdminFPTDeleteConfirmRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME =
            "validateChangedScreen(WEB3AdminFPTDeleteConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        // validate( )
        // ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        // getInstanceFrom���O�C�����( )
        // ���O�C�������A�Ǘ��҃I�u�W�F�N�g���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        // �Ǘ��Ҍ����`�F�b�N������B
        // [validate�����i�j�Ɏw�肷�����]
        // �@@�\�J�e�S���R�[�h�F"G0102"(�����@@��t�{���폜)
        // is�X�V�Ftrue
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.FPT_REGIST_INQUIRY, true);

        // validate���X����(���X�R�[�h : String)
        // ���X�����`�F�b�N���s���B
        // [����]
        // ���N�G�X�g�f�[�^.�����@@��t�{���폜�s.���X�R�[�h
        l_administrator.validateBranchPermission(l_request.financialProductTradeDeleteRow.branchCode);

        // get�،���ЃR�[�h()
        // �،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        // create��������������( )
        // ����������������쐬����B
        String l_strQueryString = this.createQueryString();

        // ���������f�[�^�R���e�i���쐬����B
        // [����]
        // �،���ЃR�[�h�F �Ǘ���.get�،���ЃR�[�h()�̖߂�l
        // �������F ���N�G�X�g�f�[�^
        Object[] l_queryContainers =
            this.createQueryDataContainer(l_strInstitutionCode, l_request);

        // ���ʌ�t�Ǘ�( )
        // ���ʌ�t�Ǘ��I�u�W�F�N�g�𐶐�����B
        WEB3AdminFPTDocDeliveryManagement l_adminFPTDocDeliveryManagement =
            new WEB3AdminFPTDocDeliveryManagement();

        // get���ʌ�t�Ǘ�(String, Object[])
        // ���ʌ�t�Ǘ��e�[�u�����烌�R�[�h���擾����B
        // [����]
        // ����������F create��������������()�̖߂�l
        // �����f�[�^�R���e�i�F create���������f�[�^�R���e�i()�̖߂�l
        List l_lisDocDivManagements =
            l_adminFPTDocDeliveryManagement.getDocDivManagement(
                l_strQueryString,
                l_queryContainers);

        // ���ʌ�t�Ǘ��s���擾�ł��Ȃ��ꍇ�A��O���X���[����B
        // ���򏈗�
        // ���ʌ�t�Ǘ��s���擾�ł��Ȃ�
        // �iget���ʌ�t�Ǘ�()�̖߂�l���� == 0�j�ꍇ�A
        // ��O���X���[����B
        if (l_lisDocDivManagements.size() == 0)
        {
            log.debug("���ʌ�t�Ǘ��s���擾�ł��Ȃ��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02952,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ʌ�t�Ǘ��s���擾�ł��Ȃ��B");
        }

        // createResponse( )
        // ���X�|���X�f�[�^�����B
        WEB3AdminFPTDeleteConfirmResponse l_response =
            (WEB3AdminFPTDeleteConfirmResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�ύX���)<BR>
     * �����@@��t�{���폜������ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �usubmit�ύX��ʁv�Q�ƁB<BR>
     * =============================================== <BR>
     * �@@�@@��̈ʒu : ���ʌ�t�Ǘ��s���擾�ł��Ȃ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@class�@@�@@: WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@�@@�@@: BUSINESS_ERROR_02952<BR>
     * =============================================== <BR>
     * @@param l_request - (l_request)<BR>
     * �Ǘ��ҋ����@@��t�{���폜�������N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3AdminFPTDeleteCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4726CF2C026C
     */
    protected WEB3AdminFPTDeleteCompleteResponse submitChangedScreen(
        WEB3AdminFPTDeleteCompleteRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME =
            "submitChangedScreen(WEB3AdminFPTDeleteCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        // validate( )
        // ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        // getInstanceFrom���O�C�����( )
        // ���O�C�������A�Ǘ��҃I�u�W�F�N�g���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        // �Ǘ��Ҍ����`�F�b�N������B
        // [validate�����i�j�Ɏw�肷�����]
        // �@@�\�J�e�S���R�[�h�F"G0102"(�����@@��t�{���폜)
        // is�X�V�Ftrue
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.FPT_REGIST_INQUIRY, true);

        // validate���X����(���X�R�[�h : String)
        // ���X�����`�F�b�N���s���B
        // [����]
        // ���N�G�X�g�f�[�^.�����@@��t�{���폜�s.���X�R�[�h
        l_administrator.validateBranchPermission(
            l_request.financialProductTradeDeleteRow.branchCode);

        // validate����p�X���[�h(�p�X���[�h : String)
        // ����p�X���[�h�̃`�F�b�N���s���B
        // [����]
        // �p�X���[�h�F�@@���N�G�X�g�f�[�^.�Ïؔԍ�
        l_administrator.validateTradingPassword(l_request.password);

        // get�،���ЃR�[�h()
        // �،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        // create��������������( )
        // ����������������쐬����B
        String l_strQueryString = this.createQueryString();

        // ���������f�[�^�R���e�i���쐬����B
        // [����]
        // �،���ЃR�[�h�F �Ǘ���.get�،���ЃR�[�h()�̖߂�l
        // �������F ���N�G�X�g�f�[�^
        Object[] l_queryContainers =
            this.createQueryDataContainer(l_strInstitutionCode, l_request);

        // ���ʌ�t�Ǘ�( )
        // ���ʌ�t�Ǘ��I�u�W�F�N�g�𐶐�����B
        WEB3AdminFPTDocDeliveryManagement l_adminFPTDocDeliveryManagement =
            new WEB3AdminFPTDocDeliveryManagement();

        // get���ʌ�t�Ǘ�(String, Object[])
        // ���ʌ�t�Ǘ��e�[�u�����烌�R�[�h���擾����B
        // [����]
        // ����������F create��������������()�̖߂�l
        // �����f�[�^�R���e�i�F create���������f�[�^�R���e�i()�̖߂�l
        List l_lisDocDivManagements =
            l_adminFPTDocDeliveryManagement.getDocDivManagement(
                l_strQueryString,
                l_queryContainers);

        // ���ʌ�t�Ǘ��s���擾�ł��Ȃ��ꍇ�A��O���X���[����B
        // ���򏈗�
        // ���ʌ�t�Ǘ��s���擾�ł��Ȃ�
        // �iget���ʌ�t�Ǘ�()�̖߂�l���� == 0�j�ꍇ�A
        // ��O���X���[����B
        if (l_lisDocDivManagements.size() == 0)
        {
            log.debug("���ʌ�t�Ǘ��s���擾�ł��Ȃ��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02952,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ʌ�t�Ǘ��s���擾�ł��Ȃ��B");
        }

        DocDeliveryManagementRow l_docDeliveryManagementRow =
            (DocDeliveryManagementRow)l_lisDocDivManagements.get(0);

        // (*)�v���p�e�B�Z�b�g
        // ���ʌ�t�Ǘ�����Params�𐶐����A
        DocDeliveryManagementHistParams l_docDeliveryManagementHistParams =
            new DocDeliveryManagementHistParams();

        // get���ʌ�t�Ǘ��ꗗ()�̖߂�l�i�ȉ����ʌ�t�Ǘ��s�j��ݒ肷��B
        // ���ʌ�t�Ǘ������e�[�u��Params.����ID = ���ʌ�t�Ǘ��s.get����ID()
        l_docDeliveryManagementHistParams.setAccountId(
            l_docDeliveryManagementRow.getAccountId());

        // ���ʌ�t�Ǘ������e�[�u��Params.�،���ЃR�[�h =
        // ���ʌ�t�Ǘ��s.get�،���ЃR�[�h()
        l_docDeliveryManagementHistParams.setInstitutionCode(
            l_docDeliveryManagementRow.getInstitutionCode());

        // ���ʌ�t�Ǘ������e�[�u��Params.���X�R�[�h = ���ʌ�t�Ǘ��s.get���X�R�[�h()
        l_docDeliveryManagementHistParams.setBranchCode(
            l_docDeliveryManagementRow.getBranchCode());

        // ���ʌ�t�Ǘ������e�[�u��Params.�ڋq�R�[�h = ���ʌ�t�Ǘ��s.get�ڋq�R�[�h()
        l_docDeliveryManagementHistParams.setAccountCode(
            l_docDeliveryManagementRow.getAccountCode());

        // ���ʌ�t�Ǘ������e�[�u��Params.���ʋ敪 = ���ʌ�t�Ǘ��s.get���ʋ敪()
        l_docDeliveryManagementHistParams.setDocumentDiv(
            l_docDeliveryManagementRow.getDocumentDiv());

        // ���ʌ�t�Ǘ������e�[�u��Params.�����R�[�h = ���ʌ�t�Ǘ��s.get�����R�[�h()
        l_docDeliveryManagementHistParams.setProductCode(
            l_docDeliveryManagementRow.getProductCode());

        // ���ʌ�t�Ǘ������e�[�u��Params.���ʌ�t�� = ���ʌ�t�Ǘ��s.get���ʌ�t��()
        l_docDeliveryManagementHistParams.setDeliveryDate(
            l_docDeliveryManagementRow.getDeliveryDate());

        // ���ʌ�t�Ǘ������e�[�u��Params.�폜�t���O = ���ʌ�t�Ǘ��s.get�폜�t���O()
        l_docDeliveryManagementHistParams.setDeleteFlag(
            l_docDeliveryManagementRow.getDeleteFlag());

        // ���ʌ�t�Ǘ������e�[�u��Params.�X�V�҃R�[�h =
        // ���ʌ�t�Ǘ��s.get�X�V�҃R�[�h()
        l_docDeliveryManagementHistParams.setLastUpdater(
            l_docDeliveryManagementRow.getLastUpdater());

        // ���ʌ�t�Ǘ������e�[�u��Params.�쐬���t = ���ʌ�t�Ǘ��s.get�쐬���t()
        l_docDeliveryManagementHistParams.setCreatedTimestamp(
            l_docDeliveryManagementRow.getCreatedTimestamp());

        // ���ʌ�t�Ǘ������e�[�u��Params.�X�V���t = ���ʌ�t�Ǘ��s.get�X�V���t()
        l_docDeliveryManagementHistParams.setLastUpdatedTimestamp(
            l_docDeliveryManagementRow.getLastUpdatedTimestamp());

        // ���ʌ�t�Ǘ������e�[�u��Params.�폜�� = �Ǘ���.get�Ǘ��҃R�[�h()
        l_docDeliveryManagementHistParams.setDeleteUser(
            l_administrator.getAdministratorCode());

        // ���ʌ�t�Ǘ������e�[�u��Params.�폜���� = ���ݓ���
        l_docDeliveryManagementHistParams.setDeleteTimestamp(
            GtlUtils.getSystemTimestamp());

        // ���ʌ�t�Ǘ������e�[�u��Params.���ʎ�ރR�[�h = ���ʌ�t�Ǘ��s.get���ʎ�ރR�[�h()
        l_docDeliveryManagementHistParams.setDocumentCategory(
            l_docDeliveryManagementRow.getDocumentCategory());

        //���ʌ�t�Ǘ������e�[�u��Params.�݂Ȃ���t�� = ���ʌ�t�Ǘ��s.�݂Ȃ���t��
        l_docDeliveryManagementHistParams.setDeemedDeliveryDate(
            l_docDeliveryManagementRow.getDeemedDeliveryDate());

        // ���ʌ�t�Ǘ�����( )
        // ���ʌ�t�Ǘ������I�u�W�F�N�g�𐶐�����B
        WEB3AdminFPTDocDeliveryManagementHistory l_adminFPTDocDeliveryManagementHistory =
            new WEB3AdminFPTDocDeliveryManagementHistory();

        // insert���ʌ�t�Ǘ������e�[�u��(���ʌ�t�Ǘ������e�[�u��Params)
        // ���ʌ�t�Ǘ������e�[�u����Insert�����s����B
        // [����]
        // (*)�v���p�e�B�Z�b�g�ō쐬�������ʌ�t�Ǘ������e�[�u��Params
        l_adminFPTDocDeliveryManagementHistory.insertDocDeliveryManagementHist(
            l_docDeliveryManagementHistParams);

        // delete���ʌ�t�Ǘ�(long, String, String, Date)
        // ���ʌ�t�����e�[�u���̍폜�����s����B
        // [����]
        // ����ID�F get���ʌ�t�Ǘ��ꗗ()�̖߂�l.get����ID()
        // ���ʋ敪�Fget���ʌ�t�Ǘ��ꗗ()�̖߂�l.get���ʋ敪()
        // �����R�[�h�F get���ʌ�t�Ǘ��ꗗ()�̖߂�l.get�����R�[�h()
        // ���ʌ�t���F get���ʌ�t�Ǘ��ꗗ()�̖߂�l.get���ʌ�t��()
        // ���ʎ�ރR�[�h�F get���ʌ�t�Ǘ��ꗗ()�̖߂�l.get���ʎ�ރR�[�h()
        long l_lngAccountId = l_docDeliveryManagementRow.getAccountId();
        String l_strDocumentDiv = l_docDeliveryManagementRow.getDocumentDiv();
        String l_strProductCode = l_docDeliveryManagementRow.getProductCode();
        Date l_datDocuDeliDate = l_docDeliveryManagementRow.getDeliveryDate();
        String l_strDocumentCategory = l_docDeliveryManagementRow.getDocumentCategory();
        l_adminFPTDocDeliveryManagement.deleteDocDivManagement(
            l_lngAccountId,
            l_strDocumentDiv,
            l_strProductCode,
            l_datDocuDeliDate,
            l_strDocumentCategory);

        // createResponse( )
        // ���X�|���X�f�[�^�����B
        WEB3AdminFPTDeleteCompleteResponse l_response =
            (WEB3AdminFPTDeleteCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create��������������)<BR>
     * �擾�����̕�����𐶐�����B<BR>
     * <BR>
     * �P�j�@@��̕�����𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�،���ЃR�[�h���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@"institution_code = ?"<BR>
     * <BR>
     * �R�j�@@���X�R�[�h����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�������������� += "and branch_code = ? "<BR>
     * <BR>
     * �S�j�@@�ڋq�R�[�h����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�������������� += "and account_code like ? %"<BR>
     * <BR>
     * �T�j�@@���ʋ敪����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�������������� += "and document_div = ? "<BR>
     * <BR>
     * �U�j�@@�d�q�������R�[�h����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�������������� += "and product_code = ? "<BR>
     * <BR>
     * �V�j�@@���ʌ�t������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�������������� += "and delivery_date�@@=�@@?"<BR>
     * <BR>
     * �W�j�@@���ʎ�ރR�[�h����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�������������� += "document_category�@@=�@@?"<BR>
     * <BR>
     * �X�j�@@�쐬������������������C���X�^���X��ԋp����B<BR>
     * <BR>
     * <BR>
     * @@return String
     * @@roseuid 4726EFEA0290
     */
    private String createQueryString()
    {
        final String STR_METHOD_NAME = "createQueryString()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@��̕�����𐶐�����B
        StringBuffer l_sbQueryString = new StringBuffer();

        //�Q�j�@@�،���ЃR�[�h���P�j�̕�����ɒǉ�����B
        //"institution_code = ?"
        l_sbQueryString.append(" institution_code = ? ");

        //�R�j�@@���X�R�[�h����������������ɒǉ�����B
        //�������������� += "and branch_code = ? "
        l_sbQueryString.append(" and branch_code = ? ");

        //�S�j�@@�ڋq�R�[�h����������������ɒǉ�����B
        //�������������� += "and account_code like ? %"
        l_sbQueryString.append(" and account_code like ? || '%' ");

        //�T�j�@@���ʋ敪����������������ɒǉ�����B
        //�������������� += "and document_div = ? "
        l_sbQueryString.append(" and document_div = ? ");

        //�U�j�@@�d�q�������R�[�h����������������ɒǉ�����B
        //�������������� += "and product_code = ? "
        l_sbQueryString.append(" and product_code = ? ");

        //�V�j�@@���ʌ�t������������������ɒǉ�����B
        //�������������� += "and delivery_date�@@=�@@?"
        l_sbQueryString.append(" and delivery_date�@@=�@@? ");

        //�W�j�@@���ʎ�ރR�[�h����������������ɒǉ�����B
        //�������������� += "document_category�@@=�@@?"
        l_sbQueryString.append(" and document_category�@@=�@@? ");

        //�X�j�@@�쐬������������������C���X�^���X��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_sbQueryString.toString();
    }

    /**
     * (create���������f�[�^�R���e�i)<BR>
     * �擾�����ɃZ�b�g����l�̔z��𐶐�����B<BR>
     * <BR>
     * �P�j ���ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j �،���ЃR�[�h<BR>
     * <BR>
     * �@@�@@ ����.�،���ЃR�[�h ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �R�j ���X�R�[�h <BR>
     * <BR>
     * �@@�@@ ����:�������.�����@@��t�{���폜�s.���X�R�[�h ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �S�j �ڋq�R�[�h<BR>
     * <BR>
     * �@@�@@ ����:�������.�����@@��t�{���폜�s.�ڋq�R�[�h ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �T�j ���ʋ敪<BR>
     * <BR>
     * �@@�@@ ����:�������.�����@@��t�{���폜�s.���ʋ敪 ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �U�j �d�q�������R�[�h<BR>
     * <BR>
     * �@@�@@ ����:�������.�����@@��t�{���폜�s.�d�q�������R�[�h ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �V�j ���ʌ�t��<BR>
     * <BR>
     * �@@�@@ ����:�������.�����@@��t�{���폜�s.���ʌ�t�� ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �W�j ���ʎ�ރR�[�h<BR>
     * <BR>
     * �@@�@@ ����:�������.�����@@��t�{���폜�s.���ʎ�ރR�[�h ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �X�j �������ꂽList����z����擾���A�ԋp����B<BR>
     * <BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_request - (�������)<BR>
     * �������<BR>
     * @@return Object[]
     * @@roseuid 4726EFED0213
     */
    private Object[] createQueryDataContainer(
        String l_strInstitutionCode,
        WEB3GenRequest l_request)
    {
        final String STR_METHOD_NAME = "createQueryDataContainer(String, WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        //�P�j ���ArrayList�𐶐�����B
        List l_lisQueryDataContainers = new ArrayList();

        //�Q�j �،���ЃR�[�h
        //����.�،���ЃR�[�h ���P�j��List�ɒǉ�����B
        l_lisQueryDataContainers.add(l_strInstitutionCode);

        if (l_request instanceof WEB3AdminFPTDeleteConfirmRequest)
        {
            WEB3AdminFPTDeleteConfirmRequest l_deleteConfirmRequest =
                (WEB3AdminFPTDeleteConfirmRequest)l_request;
            //�R�j ���X�R�[�h
            //����:�������.�����@@��t�{���폜�s.���X�R�[�h ���P�j��List�ɒǉ�����B
            l_lisQueryDataContainers.add(l_deleteConfirmRequest.financialProductTradeDeleteRow.branchCode);

            //�S�j �ڋq�R�[�h
            //����:�������.�����@@��t�{���폜�s.�ڋq�R�[�h ���P�j��List�ɒǉ�����B
            l_lisQueryDataContainers.add(l_deleteConfirmRequest.financialProductTradeDeleteRow.acceptCode);

            //�T�j ���ʋ敪
            //����:�������.�����@@��t�{���폜�s.���ʋ敪 ���P�j��List�ɒǉ�����B
            l_lisQueryDataContainers.add(l_deleteConfirmRequest.financialProductTradeDeleteRow.documentDiv);

            //�U�j �d�q�������R�[�h
            //����:�������.�����@@��t�{���폜�s.�d�q�������R�[�h ���P�j��List�ɒǉ�����B
            l_lisQueryDataContainers.add(l_deleteConfirmRequest.financialProductTradeDeleteRow.batoProductCode);

            //�V�j ���ʌ�t��
            //����:�������.�����@@��t�{���폜�s.���ʌ�t�� ���P�j��List�ɒǉ�����B
            l_lisQueryDataContainers.add(l_deleteConfirmRequest.financialProductTradeDeleteRow.docuDeliDate);

            //�W�j ���ʎ�ރR�[�h
            //����:�������.�����@@��t�{���폜�s.���ʎ�ރR�[�h ���P�j��List�ɒǉ�����B
            l_lisQueryDataContainers.add(l_deleteConfirmRequest.financialProductTradeDeleteRow.documentCategory);
        }
        else if (l_request instanceof WEB3AdminFPTDeleteCompleteRequest)
        {
            WEB3AdminFPTDeleteCompleteRequest l_deleteCompleteRequest =
                (WEB3AdminFPTDeleteCompleteRequest)l_request;
            //�R�j ���X�R�[�h
            //����:�������.�����@@��t�{���폜�s.���X�R�[�h ���P�j��List�ɒǉ�����B
            l_lisQueryDataContainers.add(l_deleteCompleteRequest.financialProductTradeDeleteRow.branchCode);

            //�S�j �ڋq�R�[�h
            //����:�������.�����@@��t�{���폜�s.�ڋq�R�[�h ���P�j��List�ɒǉ�����B
            l_lisQueryDataContainers.add(l_deleteCompleteRequest.financialProductTradeDeleteRow.acceptCode);

            //�T�j ���ʋ敪
            //����:�������.�����@@��t�{���폜�s.���ʋ敪 ���P�j��List�ɒǉ�����B
            l_lisQueryDataContainers.add(l_deleteCompleteRequest.financialProductTradeDeleteRow.documentDiv);

            //�U�j �d�q�������R�[�h
            //����:�������.�����@@��t�{���폜�s.�d�q�������R�[�h ���P�j��List�ɒǉ�����B
            l_lisQueryDataContainers.add(l_deleteCompleteRequest.financialProductTradeDeleteRow.batoProductCode);

            //�V�j ���ʌ�t��
            //����:�������.�����@@��t�{���폜�s.���ʌ�t�� ���P�j��List�ɒǉ�����B
            l_lisQueryDataContainers.add(l_deleteCompleteRequest.financialProductTradeDeleteRow.docuDeliDate);

            //�W�j ���ʎ�ރR�[�h
            //����:�������.�����@@��t�{���폜�s.���ʎ�ރR�[�h ���P�j��List�ɒǉ�����B
            l_lisQueryDataContainers.add(l_deleteCompleteRequest.financialProductTradeDeleteRow.documentCategory);
        }

        //�W�j �������ꂽList����z����擾���A�ԋp����B
        Object[] l_queryDataContainers = new Object[l_lisQueryDataContainers.size()];
        l_lisQueryDataContainers.toArray(l_queryDataContainers);

        log.exiting(STR_METHOD_NAME);
        return l_queryDataContainers;
    }
}
@
