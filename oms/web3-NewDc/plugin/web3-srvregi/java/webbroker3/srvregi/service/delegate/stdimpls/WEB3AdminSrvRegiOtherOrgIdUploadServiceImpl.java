head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.49.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdUploadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�޻��޽Impl(WEB3AdminSrvRegiOtherOrgIdUploadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/10 ���g (���u) �V�K�쐬 ���f��337
Revision History : 2008/03/19 ���g (���u) �d�l�ύX ���f��357
Revision History : 2008/04/10 ���g (���u) �d�l�ύX ���f��366
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.srvregi.WEB3AdminSrvRegiOtherOrgIdUploadCsv;
import webbroker3.srvregi.define.WEB3SrvRegiUploadStateDivDef;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadInputRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadInputResponse;
import webbroker3.srvregi.message.WEB3SrvRegiUploadHistoryInfoUnit;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiOtherOrgIdUploadService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiOtherOrgIdUploadUnitService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�޻��޽Impl)<BR>
 * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�޻��޽�����N���X<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdUploadServiceImpl implements WEB3AdminSrvRegiOtherOrgIdUploadService
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiOtherOrgIdUploadServiceImpl.class);

    /**
     * @@roseuid 47D11130013A
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadServiceImpl()
    {

    }

    /**
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ޏ������s���B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ޓ���ظ��Ă̏ꍇ<BR>
     * �@@�|get�A�b�v���[�h���()���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ފm�Fظ��Ă̏ꍇ<BR>
     * �@@�|validate�A�b�v���[�h�t�@@�C��()���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ފ���ظ��Ă̏ꍇ<BR>
     * �@@�|submit�A�b�v���[�h�t�@@�C��()���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ޒ��~ظ��Ă̏ꍇ<BR>
     * �@@�|undo�A�b�v���[�h()���R�[������B<BR>
     * <BR>
     * ���T�[�r�X���\�b�h�ɂė�O�����������ꍇ�́A<BR>
     * �@@��O�I�u�W�F�N�g�̒ǉ�������iWEB3BaseException.errorMessage�j<BR>
     * �@@�����X�|���X�f�[�^.errorMessage�ɃZ�b�g����B<BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47B9417F0031
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

        //�P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B
        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3AdminSrvRegiOtherOrgIdUploadInputRequest)
        {
            // �����̃��N�G�X�g�f�[�^���A�Ǘ��ҋ����@@��t�{���A�b�v���[�h���̓��N�G�X�g�̏ꍇ
            //�|get�A�b�v���[�h���()���R�[������B
            l_response = this.getUploadScreen(
                (WEB3AdminSrvRegiOtherOrgIdUploadInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest)
        {
            //�����̃��N�G�X�g�f�[�^���A�Ǘ��ҋ����@@��t�{���A�b�v���[�h�m�F���N�G�X�g�̏ꍇ
            //�|validate�A�b�v���[�h�t�@@�C��()���R�[������B
            l_response = this.validateUploadFile(
                (WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest)
        {
            //�����̃��N�G�X�g�f�[�^���A�Ǘ��ҋ����@@��t�{���A�b�v���[�h�������N�G�X�g�̏ꍇ
            //�|submit�A�b�v���[�h�t�@@�C��()���R�[������B
            l_response = this.submitUploadFile(
                (WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest)
        {
            //�����̃��N�G�X�g�f�[�^���A�Ǘ��ҋ����@@��t�{���A�b�v���[�h���~���N�G�X�g�̏ꍇ
            //�|undo�A�b�v���[�h()���R�[������B
            l_response = this.undoUpload(
                (WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest)l_request);
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
     * (get�A�b�v���[�h���)<BR>
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�މ�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�T�[�r�X���p)�O���A�gID�Ɖﱯ��۰�ށEget�A�b�v���[�h��ʁv�Q�ƁB<BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminSrvRegiOtherOrgIdUploadInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 47B941DD0332
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadInputResponse getUploadScreen(
        WEB3AdminSrvRegiOtherOrgIdUploadInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getUploadScreen(WEB3AdminSrvRegiOtherOrgIdUploadInputRequest)";
        log.entering(STR_METHOD_NAME);

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�@@�\�J�e�S���R�[�h�F�@@�\�J�e�S���R�[�h.�T�[�r�X���p�Ǘ�(�O���A�g)
        //is�X�V�Ftrue
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_OTHERORG, true);

        //isDIR�Ǘ���( )
        boolean l_blnIsDirAdministrator = l_admin.isDirAdministrator();

        //(*1) isDIR�Ǘ���()=false �̏ꍇ�A��t���ԃ`�F�b�N�����{
        if (!l_blnIsDirAdministrator)
        {
            //validate������t�\( )
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }

        //�O���A�gID�Ɖﱯ��۰��CSV(Long)
        //�A�b�v���[�h�h�c�F�@@null
        WEB3AdminSrvRegiOtherOrgIdUploadCsv l_adminSrvRegiOtherOrgIdUploadCsv =
            new WEB3AdminSrvRegiOtherOrgIdUploadCsv(null);

        //get�ŐV�A�b�v���[�h����(�f�[�^�L�[ : long)
        //�f�[�^�L�[�F0
        AdministratorUploadRow l_administratorUploadRow =
            l_adminSrvRegiOtherOrgIdUploadCsv.getLatestUploadAction(0);

        //(*2) �A�b�v���[�h���������݂���ꍇ�A�������{
        WEB3SrvRegiUploadHistoryInfoUnit l_srvRegiUploadHistoryInfoUnit = null;
        if (l_administratorUploadRow != null)
        {
            //�T�[�r�X���p�A�b�v���[�h���𖾍�( )
            l_srvRegiUploadHistoryInfoUnit =
                new WEB3SrvRegiUploadHistoryInfoUnit();
            //(*2.1) �v���p�e�B�Z�b�g
            //(*2.1)�T�[�r�X���p�A�b�v���[�h���𖾍׃��b�Z�[�W�I�u�W�F�N�g�v���p�e�B�ɒl���Z�b�g����B
            //�A�b�v���[�h������ԋ敪�F
            //�@@�A�b�v���[�h�s.�A�b�v���[�h�I��������null �̏ꍇ�́A�u�A�b�v���[�h���v
            if (l_administratorUploadRow.getUploadEndTimestamp() == null)
            {
                l_srvRegiUploadHistoryInfoUnit.uploadStateDiv =
                    WEB3SrvRegiUploadStateDivDef.UPLOADING;
            }
            //�A�A�b�v���[�h�s.�A�b�v���[�h�I��������null �̏ꍇ�́A�u�A�b�v���[�h�ρv
            else
            {
                l_srvRegiUploadHistoryInfoUnit.uploadStateDiv =
                    WEB3SrvRegiUploadStateDivDef.UPLOAD_COMPLETE;
            }

            //�A�b�v���[�h�����F�A�b�v���[�h�s.�A�b�v���[�h����
            l_srvRegiUploadHistoryInfoUnit.uploadNumber =
                l_administratorUploadRow.getUploadCount() + "";

            //�A�b�v���[�h�J�n�����F�A�b�v���[�h�s.�A�b�v���[�h�J�n����
            l_srvRegiUploadHistoryInfoUnit.uploadStartDate =
                l_administratorUploadRow.getUploadStartTimestamp();

            //�A�b�v���[�h�I�������F�A�b�v���[�h�s.�A�b�v���[�h�I������
            l_srvRegiUploadHistoryInfoUnit.uploadEndDate =
                l_administratorUploadRow.getUploadEndTimestamp();

            //�T�[�r�X���p�G���[�ԍ��F�A�b�v���[�h�s.���b�Z�[�W�R�[�h
            l_srvRegiUploadHistoryInfoUnit.srvRegiErrorId =
                l_administratorUploadRow.getMessageCode();
        }

        //�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ޓ���ڽ��ݽ( )
        WEB3AdminSrvRegiOtherOrgIdUploadInputResponse l_response =
            (WEB3AdminSrvRegiOtherOrgIdUploadInputResponse)l_request.createResponse();

        //(*3)���X�|���X�f�[�^�v���p�e�B�ɒl���Z�b�g����B
        //�A�b�v���[�h�����ꗗ�F
        //�A�b�v���[�h���������݂���ꍇ�A�T�[�r�X���p�A�b�v���[�h���𖾍׃I�u�W�F�N�g
        if (l_srvRegiUploadHistoryInfoUnit != null)
        {
            l_response.uploadHistoryUnit = l_srvRegiUploadHistoryInfoUnit;
        }
        else
        {
            //�A�b�v���[�h���������݂��Ȃ��ꍇ�Anull
            l_response.uploadHistoryUnit = null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�A�b�v���[�h�t�@@�C��)<BR>
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ފm�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�T�[�r�X���p)�O���A�gID�Ɖﱯ��۰�ށEvalidate�A�b�v���[�h�t�@@�C���v�Q�ƁB<BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 47B941DD0341
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse validateUploadFile(
        WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateUploadFile(WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�@@�\�J�e�S���R�[�h�F�@@�\�J�e�S���R�[�h.�T�[�r�X���p�Ǘ�(�O���A�g)
        //is�X�V�Ftrue
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_OTHERORG, true);

        //isDIR�Ǘ���( )
        boolean l_blnIsDirAdministrator = l_admin.isDirAdministrator();

        //(*1) isDIR�Ǘ���()=false �̏ꍇ�A��t���ԃ`�F�b�N�����{
        if (!l_blnIsDirAdministrator)
        {
            //validate������t�\( )
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }

        //�O���A�gID�Ɖﱯ��۰��CSV(Long)
        //�A�b�v���[�h�h�c�F�@@null
        WEB3AdminSrvRegiOtherOrgIdUploadCsv l_adminSrvRegiOtherOrgIdUploadCsv =
            new WEB3AdminSrvRegiOtherOrgIdUploadCsv(null);

        //set�A�b�v���[�h�敪(String, String)
        //�A�b�v���[�h�敪�F�@@null
        //���׍s������F�@@���N�G�X�g�f�[�^.�A�b�v���[�h�t�@@�C��[0]
        l_adminSrvRegiOtherOrgIdUploadCsv.setUploadDiv(null, l_request.lines[0]);

        //create�J�����w�b�_( )
        l_adminSrvRegiOtherOrgIdUploadCsv.createColumnHeader();

        //validate�����A�b�v���[�h(�A�b�v���[�h�h�c : Long)
        //�A�b�v���[�hID�Fnull
        l_adminSrvRegiOtherOrgIdUploadCsv.validateSameTimeUpload(null);

        //get�Ǘ��҃R�[�h( )
        String l_strAdministratorCode = l_admin.getAdministratorCode();

        //save�A�b�v���[�h�J�n(�f�[�^�L�[ : long, ���l�P : String, ���l�Q : String, ���l�R : String, �X�V�҃R�[�h : String)
        //�f�[�^�L�[�F�@@0
        //���l�P�F�@@null
        //���l�Q�F�@@null
        //���l�R�F�@@null
        //�X�V�҃R�[�h�F�@@�Ǘ���.get�Ǘ��҃R�[�h()
        l_adminSrvRegiOtherOrgIdUploadCsv.saveUpLoadStart(0, null, null, null, l_strAdministratorCode);

        //���N�G�X�g�f�[�^.�A�b�v���[�h�t�@@�C��[]�̊e�v�f����LOOP����
        for (int i = 0; i < l_request.lines.length; i++)
        {
            int l_intLineNumber = -1;
            //add���׍s(���׍s������ : String)
            //���׍s������F�@@���N�G�X�g�f�[�^.�A�b�v���[�h�t�@@�C��[index]
            try
            {
                l_intLineNumber =
                    l_adminSrvRegiOtherOrgIdUploadCsv.addRow(l_request.lines[i]);
            }
            catch (WEB3BaseException l_ex)
            {
                //add���׍s()�ŗ�O���X���[���ꂽ�ꍇ
                //�|�A�b�v���[�h�G���[���X�V����B
                //�|��ʂɗ�O���X���[����B
                //save�A�b�v���[�h�G���[(�G���[��� : ErrorInfo)
                //�G���[���F�@@�icatch������O�j.getErrorInfo()
                l_adminSrvRegiOtherOrgIdUploadCsv.saveUploadError(l_ex.getErrorInfo());

                log.error("add���׍s()�ɂė�O���X���[���ꂽ�ꍇ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00992,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_request.lines[i]);
            }

            //(*) ��s�̏ꍇ�iadd���׍s()�̖߂�l < 0�j�A���Y�v�f�̏����𒆒f
            if (l_intLineNumber < 0)
            {
                continue;
            }

            try
            {
                //validate���׍s(int)
                l_adminSrvRegiOtherOrgIdUploadCsv.validateDetailLine(l_intLineNumber);

                //validate�A�b�v���[�h�t�@@�C�����d��(int)
                l_adminSrvRegiOtherOrgIdUploadCsv.validateUploadFileInnerRepeat(l_intLineNumber);

                //is�A�b�v���[�h�V�K�o�^( )
                boolean l_blnIsUploadNewRegist = l_adminSrvRegiOtherOrgIdUploadCsv.isUploadNewRegist();

                //is�A�b�v���[�h�V�K�o�^( )=false �̏ꍇ
                if (!l_blnIsUploadNewRegist)
                {
                    //get���X�R�[�h(int)
                    String l_strBranchCode =
                        l_adminSrvRegiOtherOrgIdUploadCsv.getBranchCode(l_intLineNumber);

                    //get���X�R�[�h()��null �̏ꍇ�A���X�����`�F�b�N�����{
                    if (l_strBranchCode != null)
                    {
                        // validate���X����(���X�R�[�h)
                        //���X�R�[�h�Fget���X�R�[�h()�̖߂�l
                        l_admin.validateBranchPermission(l_strBranchCode);
                    }
                }

                //validate���R�[�h������(int, String)
                //�s�ԍ��F�@@add���׍s()�̖߂�l
                //�T�[�r�X�敪�F�@@���N�G�X�g�f�[�^.�T�[�r�X�敪
                l_adminSrvRegiOtherOrgIdUploadCsv.validateRecodeMatch(
                    l_intLineNumber, l_request.serviceDiv);
            }
            catch (WEB3BaseException l_ex)
            {
                //validate���׍s()�`validate���R�[�h������()�ɂė�O���X���[���ꂽ�ꍇ
                //�|�A�b�v���[�h�G���[���X�V����B
                //�|��ʂɗ�O���X���[����B
                //save�A�b�v���[�h�G���[(�G���[��� : ErrorInfo)
                //�G���[���F�@@�icatch������O�j.getErrorInfo()
                l_adminSrvRegiOtherOrgIdUploadCsv.saveUploadError(l_ex.getErrorInfo());

                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_adminSrvRegiOtherOrgIdUploadCsv.getSequenceNumber(l_intLineNumber)
                    + "," + l_adminSrvRegiOtherOrgIdUploadCsv.getId(l_intLineNumber));
            }
        }

        //get���׍s��( )
        int l_intRowCount = l_adminSrvRegiOtherOrgIdUploadCsv.getRowCount();

        //get�A�b�v���[�h�h�c( )
        long l_lngUploadID = l_adminSrvRegiOtherOrgIdUploadCsv.getAdministratorUploadId();

        //get�A�b�v���[�h�敪( )
        String l_strUploadDiv = l_adminSrvRegiOtherOrgIdUploadCsv.getUploadDiv();

        //save�A�b�v���[�hTemp( )
        l_adminSrvRegiOtherOrgIdUploadCsv.saveUploadTemp();

        //�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ފm�Fڽ��ݽ( )
        WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse l_response =
            (WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse)l_request.createResponse();

        //���X�|���X�v���p�e�B�ɒl���Z�b�g����B
        //�A�b�v���[�h�����Fget���׍s��()�̖߂�l
        l_response.lineCount = l_intRowCount + "";
        //�A�b�v���[�hID�Fget�A�b�v���[�hID()�̖߂�l
        l_response.uploadId = l_lngUploadID + "";
        //�A�b�v���[�h�敪�Fget�A�b�v���[�h��
        l_response.uploadDiv = l_strUploadDiv;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�A�b�v���[�h�t�@@�C��)<BR>
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ފ����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�T�[�r�X���p)�O���A�gID�Ɖﱯ��۰�ށEsubmit�A�b�v���[�h�t�@@�C���v�Q�ƁB<BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 47B941DD0343
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse submitUploadFile(
        WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitUploadFile(WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�@@�\�J�e�S���R�[�h�F�@@�\�J�e�S���R�[�h.�T�[�r�X���p�Ǘ�(�O���A�g)
        //is�X�V�Ftrue
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_OTHERORG, true);

        //isDIR�Ǘ���( )
        boolean l_blnIsDirAdministrator = l_admin.isDirAdministrator();

        //(*1) isDIR�Ǘ���()=false �̏ꍇ�A��t���ԃ`�F�b�N�����{
        if (!l_blnIsDirAdministrator)
        {
            //validate������t�\( )
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }

        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //�O���A�gID�Ɖﱯ��۰��CSV(Long)
        //�A�b�v���[�h�h�c�F�@@���N�G�X�g�̃A�b�v���[�h�h�c�iString�j��Long�^�ɕϊ����ăZ�b�g
        WEB3AdminSrvRegiOtherOrgIdUploadCsv l_adminSrvRegiOtherOrgIdUploadCsv =
            new WEB3AdminSrvRegiOtherOrgIdUploadCsv(
                new Long(l_request.uploadId));

        //set�A�b�v���[�h�敪(String, String)
        //�A�b�v���[�h�敪�F�@@���N�G�X�g�f�[�^.�A�b�v���[�h�敪
        //���׍s������F�@@null
        l_adminSrvRegiOtherOrgIdUploadCsv.setUploadDiv(l_request.uploadDiv, null);

        //create�J�����w�b�_( )
        l_adminSrvRegiOtherOrgIdUploadCsv.createColumnHeader();

        //validate�����A�b�v���[�h(�A�b�v���[�h�h�c : Long)
        //�A�b�v���[�hID�F�@@���N�G�X�g�̃A�b�v���[�h�h�c�iString�j��Long�^�ɕϊ����ăZ�b�g
        l_adminSrvRegiOtherOrgIdUploadCsv.validateSameTimeUpload(
            new Long(l_request.uploadId));

        //validate����p�X���[�h(�p�X���[�h : String)
        //�p�X���[�h�F�@@���N�G�X�g�f�[�^.�Ïؔԍ�
        l_admin.validateTradingPassword(l_request.password);

        //setDataFrom�A�b�v���[�hTemp(�A�b�v���[�h�h�c : long)
        //�A�b�v���[�h�h�c�F�@@���N�G�X�g�̃A�b�v���[�h�h�c�iString�j��long�^�ɕϊ����ăZ�b�g
        l_adminSrvRegiOtherOrgIdUploadCsv.setDataFromUploadTemp(Long.parseLong(l_request.uploadId));

        //���׍s�̐����i���׍s[0]����jLOOP����
        int l_intRowCount = l_adminSrvRegiOtherOrgIdUploadCsv.getRowCount();
        for (int i = 0; i < l_intRowCount; i++)
        {
            try
            {
                //get�ʔ�(int)
                long l_lngSequenceNumber = l_adminSrvRegiOtherOrgIdUploadCsv.getSequenceNumber(i);

                //get�X�e�[�^�X(int)
                String l_strStatus = l_adminSrvRegiOtherOrgIdUploadCsv.getStatus(i);

                //is�A�b�v���[�h�V�K�o�^( )
                boolean l_blnIsUploadNewRegist = l_adminSrvRegiOtherOrgIdUploadCsv.isUploadNewRegist();

                WEB3AdminSrvRegiOtherOrgIdUploadUnitService l_adminSrvRegiOtherOrgIdUploadUnitService =
                    (WEB3AdminSrvRegiOtherOrgIdUploadUnitService)Services.getService(
                        WEB3AdminSrvRegiOtherOrgIdUploadUnitService.class);
                //is�A�b�v���[�h�V�K�o�^( )=true �̏ꍇ
                if (l_blnIsUploadNewRegist)
                {
                    //getID(int)
                    String l_strId = l_adminSrvRegiOtherOrgIdUploadCsv.getId(i);

                    //get�p�X���[�h(int)
                    String l_strPassword = l_adminSrvRegiOtherOrgIdUploadCsv.getPassword(i);

                    //insert�O���A�g���(long, String, String, String, String, String)
                    //�ʔԁF�@@get�ʔ�()�̖߂�l
                    //�T�[�r�X�敪�F�@@���N�G�X�g�f�[�^.�T�[�r�X�敪
                    //ID�F�@@getID()�̖߂�l
                    //�p�X���[�h�F�@@get�p�X���[�h()�̖߂�l
                    //�X�e�[�^�X�F�@@get�X�e�[�^�X()�̖߂�l
                    //�،���ЃR�[�h�F�@@�Ǘ���.get�،���ЃR�[�h() �̖߂�l
                    l_adminSrvRegiOtherOrgIdUploadUnitService.insertOtherOrgInfo(
                        l_lngSequenceNumber,
                        l_request.serviceDiv,
                        l_strId,
                        l_strPassword,
                        l_strStatus,
                        l_strInstitutionCode);
                }
                else
                {
                    //get���X�R�[�h(int)
                    String l_strBranchCode =
                        l_adminSrvRegiOtherOrgIdUploadCsv.getBranchCode(i);

                    //get���X�R�[�h()��null �̏ꍇ�A���X�����`�F�b�N�����{
                    if (l_strBranchCode != null)
                    {
                        // validate���X����(���X�R�[�h)
                        //���X�R�[�h�Fget���X�R�[�h()�̖߂�l
                        l_admin.validateBranchPermission(l_strBranchCode);
                    }

                    //update�O���A�g���(long, String, String)
                    //�ʔԁF�@@get�ʔ�()�̖߂�l
                    //�T�[�r�X�敪�F�@@���N�G�X�g�f�[�^.�T�[�r�X�敪
                    //�X�e�[�^�X�F�@@get�X�e�[�^�X()�̖߂�l
                    l_adminSrvRegiOtherOrgIdUploadUnitService.updateOtherOrgInfo(
                        l_lngSequenceNumber,
                        l_request.serviceDiv,
                        l_strStatus);
                }
            }
            catch (WEB3BaseException l_ex)
            {
                //save�A�b�v���[�h�G���[(�G���[��� : ErrorInfo)
                //�G���[���F�@@�icatch������O�j.getErrorInfo()
                l_adminSrvRegiOtherOrgIdUploadCsv.saveUploadError(l_ex.getErrorInfo());
                String l_strId = l_adminSrvRegiOtherOrgIdUploadCsv.getId(i);
                long l_lngSequenceNumber = l_adminSrvRegiOtherOrgIdUploadCsv.getSequenceNumber(i);
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_lngSequenceNumber + "," + l_strId);
            }
        }

        //save�A�b�v���[�h�I��( )
        l_adminSrvRegiOtherOrgIdUploadCsv.saveUploadEnd();

        //delete�A�b�v���[�hTemp( )
        l_adminSrvRegiOtherOrgIdUploadCsv.deleteUploadTemp();

        //�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ފ���ڽ��ݽ( )
        WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse l_response =
            (WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse)l_request.createResponse();
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (undo�A�b�v���[�h)<BR>
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ޒ��~�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�T�[�r�X���p)�O���A�gID�Ɖﱯ��۰�ށEundo�A�b�v���[�h�v�Q�ƁB<BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse
     * @@throws WEB3BaseException
     * @@roseuid 47B941DD0345
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse undoUpload(
        WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "undoUpload(WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //�O���A�gID�Ɖﱯ��۰��CSV(Long)
        //�A�b�v���[�h�h�c�F�@@���N�G�X�g�̃A�b�v���[�h�h�c�iString�j��Long�^�ɕϊ����ăZ�b�g
        WEB3AdminSrvRegiOtherOrgIdUploadCsv l_adminSrvRegiOtherOrgIdUploadCsv =
            new WEB3AdminSrvRegiOtherOrgIdUploadCsv(new Long(l_request.uploadId));

        //delete�A�b�v���[�hTemp( )
        l_adminSrvRegiOtherOrgIdUploadCsv.deleteUploadTemp();

        //save�A�b�v���[�h���~( )
        l_adminSrvRegiOtherOrgIdUploadCsv.saveUploadStop();

        //createResponse( )
        WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse l_response =
            (WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
