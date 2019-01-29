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
filename	WEB3AdminFPTDocumentUpdateServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҋ����@@��t���ʍX�V�T�[�r�XImpl(WEB3AdminFPTDocumentUpdateServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/03 �g�C�� (���u) �V�K�쐬 ���f��No.039
*/
package webbroker3.docadmin.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.docadmin.WEB3AdminFPTBatoProductCodeManagement;
import webbroker3.docadmin.WEB3AdminFPTDocDivManagement;
import webbroker3.docadmin.define.WEB3AdminFPTProcessFlagDivDef;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateCompleteResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateConfirmResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateInputResponse;
import webbroker3.docadmin.message.WEB3FPTDocumentDivAdminInfoUnit;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTDocumentUpdateService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҋ����@@��t���ʍX�V�T�[�r�XImpl)<BR>
 * �Ǘ��ҋ����@@��t���ʍX�V�T�[�r�X�����N���X<BR>
 *
 * @@author �g�C��
 * @@version 1.0
 */
public class WEB3AdminFPTDocumentUpdateServiceImpl implements WEB3AdminFPTDocumentUpdateService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDocumentUpdateServiceImpl.class);

    /**
     * @@roseuid 47CBC5AD00CB
     */
    public WEB3AdminFPTDocumentUpdateServiceImpl()
    {

    }

    /**
     * (execute)<BR>
     * �����@@��t���ʍX�V���������{����B<BR>
     * <BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��ҋ����@@��t���ʍX�V���̓��N�G�X�g�̏ꍇ  <BR>
     * �@@�|get���͉��()���R�[������B  <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��ҋ����@@��t���ʍX�V�m�F���N�G�X�g�̏ꍇ  <BR>
     * �@@�|validate�ύX���()���R�[������B  <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��ҋ����@@��t���ʍX�V�������N�G�X�g�̏ꍇ  <BR>
     * �@@�|submit�ύX���()���R�[������B <BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47C25F800354
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
                this.getClass().getName() + "." +  STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        WEB3GenResponse l_response = null;
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��ҋ����@@��t���ʍX�V���̓��N�G�X�g�̏ꍇ 
        if (l_request instanceof WEB3AdminFPTDocumentUpdateInputRequest)
        {
            l_response = this.getInputScreen(
                (WEB3AdminFPTDocumentUpdateInputRequest)l_request);
        }
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��ҋ����@@��t���ʍX�V�m�F���N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminFPTDocumentUpdateConfirmRequest)
        {
            l_response = this.validateChangedScreen(
                (WEB3AdminFPTDocumentUpdateConfirmRequest)l_request);
        }
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��ҋ����@@��t���ʍX�V�������N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminFPTDocumentUpdateCompleteRequest)
        {
            l_response = this.submitChangedScreen(
                (WEB3AdminFPTDocumentUpdateCompleteRequest)l_request);
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
     * �����@@��t���ʍX�V���͉�ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �uget���͉�ʁv�Q�ƁB <BR>
     * @@param l_request - �Ǘ��ҋ����@@��t���ʍX�V���̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3AdminFPTDocumentUpdateInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 47C26DA3002A
     */
    protected WEB3AdminFPTDocumentUpdateInputResponse getInputScreen(
        WEB3AdminFPTDocumentUpdateInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminFPTDocumentUpdateInputRequest)";
        log.entering(STR_METHOD_NAME);

        //getInstanceFrom���O�C�����( )
        // ���O�C�������A�Ǘ��҃I�u�W�F�N�g���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[validate�����i�j�Ɏw�肷�����]
        //�@@�\�J�e�S���R�[�h�F"G0104"(�����@@��t���ʍX�V�Ɖ�)
        //is�X�V�Ftrue
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.FPT_UPLOAD, true);

        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        // get���X�R�[�h( )
        String l_strBranchCode = l_administrator.getBranchCode();

        //���ʋ敪�Ǘ�(String, String, String, String)(
        //[�w�肷�����]
        //�،���ЃR�[�h �F �Ǘ���.get�،���ЃR�[�h() �̖߂�l
        //���X�R�[�h �F �Ǘ���.get���X�R�[�h() �̖߂�l
        //���ʋ敪 �F null
        //���ʎ�ރR�[�h �F null
        WEB3AdminFPTDocDivManagement l_adminFPTDocDivManagement =
            new WEB3AdminFPTDocDivManagement(l_strInstitutionCode, l_strBranchCode, null, null);

       //get���ʋ敪�Ǘ��ꗗ()
        WEB3FPTDocumentDivAdminInfoUnit[] l_fptDocumentDivAdminInfoUnits =
            l_adminFPTDocDivManagement.getDocDivManagementLists();

        //createResponse( )
        WEB3AdminFPTDocumentUpdateInputResponse l_response =
            (WEB3AdminFPTDocumentUpdateInputResponse)l_request.createResponse();

        //�v���p�e�B�Z�b�g
        l_response.documentDivList = l_fptDocumentDivAdminInfoUnits;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�ύX���)<BR>
     * �����@@��t���ʍX�V�m�F��ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �uvalidate�ύX��ʁv�Q�ƁB<BR>
     * @@param l_request - �Ǘ��ҋ����@@��t���ʍX�V�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3AdminFPTDocumentUpdateConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 47C26DA6004D
     */
    protected WEB3AdminFPTDocumentUpdateConfirmResponse validateChangedScreen(
        WEB3AdminFPTDocumentUpdateConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateChangedScreen(WEB3AdminFPTDocumentUpdateConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        // validate( )(�Ǘ��ҋ����@@��t���ʍX�V�m�F���N�G�X�g)
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        // ���O�C�������A�Ǘ��҃I�u�W�F�N�g���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[validate�����i�j�Ɏw�肷�����]
        //�@@�\�J�e�S���R�[�h�F"G0104"(�����@@��t���ʍX�V�Ɖ�)
        //is�X�V�Ftrue
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.FPT_UPLOAD, true);

        //�d�q�������R�[�h�Ǘ��I�u�W�F�N�g�𐶐�����B
        //�d�q�������R�[�h�Ǘ�(�Ǘ���, ���ʍX�V���[], String)
        //[�w�肷�����]
        //�Ǘ��� = �Ǘ���.getInstanceFrom���O�C�����()�̖߂�l
        //���ʏ�� = ���N�G�X�g�f�[�^.���ʍX�V���
        //�����t���O = ���N�G�X�g�f�[�^.�X�V�����t���O
        WEB3AdminFPTBatoProductCodeManagement l_adminFPTBatoProductCodeManagement =
            new WEB3AdminFPTBatoProductCodeManagement(
                l_administrator,
                l_request.documentUpdateList,
                l_request.updateProcessFlag);

        //�d�q�������R�[�h�Ǘ����R�[�h�̑��݃`�F�b�N���s���B
        //validate�d�q�������R�[�h�Ǘ��s( )
        l_adminFPTBatoProductCodeManagement.validateBatoProductManagementParams();

        WEB3AdminFPTDocumentUpdateConfirmResponse l_response =
            (WEB3AdminFPTDocumentUpdateConfirmResponse)l_request.createResponse();

        if (WEB3AdminFPTProcessFlagDivDef.INSERT.equals(l_request.updateProcessFlag))
        {
            l_response.batoProductCode = l_request.documentUpdateList[0].documentCategory +
                l_request.documentUpdateList[0].documentNumber;
        }
        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (submit�ύX���)<BR>
     * �����@@��t���ʍX�V������ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �usubmit�ύX��ʁv�Q�ƁB<BR>
     * @@param l_request - �Ǘ��ҋ����@@��t���ʍX�V�������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3AdminFPTDocumentUpdateCompleteResponse
     * @@tthrows WEB3BaseException
     * @@roseuid 47C26DA800CB
     */
    protected WEB3AdminFPTDocumentUpdateCompleteResponse submitChangedScreen(
        WEB3AdminFPTDocumentUpdateCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_MENTHOD_NAME = "submitChangedScreen(WEB3AdminFPTRegistCompleteRequest)";

        log.entering(STR_MENTHOD_NAME);

        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        // ���O�C�������A�Ǘ��҃I�u�W�F�N�g���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //�Ǘ��Ҍ����`�F�b�N������B
        //[validate�����i�j�Ɏw�肷�����]
        //�@@�\�J�e�S���R�[�h�F"G0104"(�����@@��t���ʍX�V�Ɖ�)
        //is�X�V�Ftrue
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.FPT_UPLOAD, true);

        //validate����p�X���[�h(�p�X���[�h : String)
        // ����p�X���[�h�̃`�F�b�N���s���B
        // [����]
        // �p�X���[�h�F�@@���N�G�X�g�f�[�^.�Ïؔԍ�
        l_administrator.validateTradingPassword(l_request.password);

        //�d�q�������R�[�h�Ǘ�(�Ǘ���, ���ʍX�V���[], String)(
        //�d�q�������R�[�h�Ǘ��I�u�W�F�N�g�𐶐�����B
        //[�w�肷�����]
        //�Ǘ��� = �Ǘ���.getInstanceFrom���O�C�����()�̖߂�l
        //���ʏ�� = ���N�G�X�g�f�[�^.���ʍX�V���
        //�����t���O = ���N�G�X�g�f�[�^.�X�V�����t���O
        WEB3AdminFPTBatoProductCodeManagement l_adminFPTBatoProductCodeManagement =
            new WEB3AdminFPTBatoProductCodeManagement(
                l_administrator,
                l_request.documentUpdateList,
                l_request.updateProcessFlag);

        //�d�q�������R�[�h�Ǘ����R�[�h�̑��݃`�F�b�N���s���B
        //validate�d�q�������R�[�h�Ǘ��s( )
        l_adminFPTBatoProductCodeManagement.validateBatoProductManagementParams();

        //�o�^�����i���N�G�X�g�f�[�^.�X�V�����t���O == 0�j�̏ꍇ

        if (WEB3AdminFPTProcessFlagDivDef.INSERT.equals(l_request.updateProcessFlag))
        {
            //insert�d�q�������R�[�h�Ǘ�( )(
            l_adminFPTBatoProductCodeManagement.insertBatoProductManagement();
        }

        //�X�V�����i���N�G�X�g�f�[�^.�X�V�����t���O == 1�j�̏ꍇ
        if (WEB3AdminFPTProcessFlagDivDef.UPDATE.equals(l_request.updateProcessFlag))
        {
            //update�d�q�������R�[�h�Ǘ�( )
            l_adminFPTBatoProductCodeManagement.updateBatoProductManagement();
        }

        //�X�V�����i���N�G�X�g�f�[�^.�X�V�����t���O == 2�j�̏ꍇ
        if (WEB3AdminFPTProcessFlagDivDef.DELETE.equals(l_request.updateProcessFlag))
        {
            //delete�d�q�������R�[�h�Ǘ�( )
            l_adminFPTBatoProductCodeManagement.deleteBatoProductCodeAdmin();
        }

        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminFPTDocumentUpdateCompleteResponse l_response =
            (WEB3AdminFPTDocumentUpdateCompleteResponse)l_request.createResponse();

        log.exiting(STR_MENTHOD_NAME);
        return l_response;
    }
}
@
