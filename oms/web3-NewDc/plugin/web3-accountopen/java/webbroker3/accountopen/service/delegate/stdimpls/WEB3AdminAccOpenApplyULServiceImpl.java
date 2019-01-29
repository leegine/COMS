head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.35.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplyULServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�ݐ\��UL�T�[�r�XImpl(WEB3AdminAccOpenApplyULServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/11/21 ���g (���u) �V�K�쐬 ���f�� No.148
Revision History : 2007/11/21 ���g (���u) DB�X�V�d�l No.032,No.033
Revision History : 2007/11/21 �Ӑ� (���u) �d�l�ύX ���f�� No.150,No.151,No.152,No.154
Revision History : 2007/12/11 �Ӑ� (���u) �d�l�ύX ���f�� No.155
Revision History : 2007/12/17 ���g (���u) �d�l�ύX ���f�� No.157
*/

package webbroker3.accountopen.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.accountopen.WEB3AccOpenExpAccountOpen;
import webbroker3.accountopen.WEB3AdminAccOpenApplyULCsv;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.define.WEB3AccOpenAccountCodeAutoFlagDef;
import webbroker3.accountopen.define.WEB3AccOpenUploadStateDivDef;
import webbroker3.accountopen.message.WEB3AccOpenUploadHistoryUnit;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadCancelRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadCancelResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadConfirmResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadInputRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadInputResponse;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenApplyULService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3ValidateTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��Ҍ����J�ݐ\��UL�T�[�r�XImpl)<BR>
 * �Ǘ��Ҍ����J�ݐ\��UL�T�[�r�X�����N���X<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminAccOpenApplyULServiceImpl implements WEB3AdminAccOpenApplyULService
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccOpenApplyULServiceImpl.class);

    /**
     * @@roseuid 4743EF52019E
     */
    public WEB3AdminAccOpenApplyULServiceImpl()
    {

    }

    /**
     * �����J�ݐ\���A�b�v���[�h�������s���B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݐ\��UL���̓��N�G�X�g�̏ꍇ<BR>
     * �@@�|get�A�b�v���[�h���()���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݐ\��UL�m�F���N�G�X�g�̏ꍇ<BR>
     * �@@�|validate�A�b�v���[�h�t�@@�C��()���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݐ\��UL�������N�G�X�g�̏ꍇ<BR>
     * �@@�|submit�A�b�v���[�h�t�@@�C��()���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݐ\��UL���~���N�G�X�g�̏ꍇ<BR>
     * �@@�|undo�A�b�v���[�h�t�@@�C��()���R�[������B<BR>
     * <BR>
     * ���T�[�r�X���\�b�h�ɂė�O�����������ꍇ�́A<BR>
     * �@@��O�I�u�W�F�N�g�̒ǉ�������iWEB3BaseException.errorMessage�j<BR>
     * �����X�|���X�f�[�^.errorMessage�ɃZ�b�g����B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47299B140055
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

        if (l_request instanceof WEB3AdminAccOpenApplyUploadInputRequest)
        {
            //�� �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݐ\��UL���̓��N�G�X�g�̏ꍇ
            //�@@�|get�A�b�v���[�h���()���R�[������B
            l_response = this.getUploadScreen((WEB3AdminAccOpenApplyUploadInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminAccOpenApplyUploadConfirmRequest)
        {
            //�� �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݐ\��UL�m�F���N�G�X�g�̏ꍇ
            //�@@�|validate�A�b�v���[�h�t�@@�C��()���R�[������B
            l_response = this.validateUploadFile((WEB3AdminAccOpenApplyUploadConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminAccOpenApplyUploadCompleteRequest)
        {
            //�� �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݐ\��UL�������N�G�X�g�̏ꍇ
            //�@@�|submit�A�b�v���[�h�t�@@�C��()���R�[������B
            l_response = this.submitUploadFile((WEB3AdminAccOpenApplyUploadCompleteRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminAccOpenApplyUploadCancelRequest)
        {
            //�� �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݐ\��UL���~���N�G�X�g�̏ꍇ
            //�@@�|undo�A�b�v���[�h�t�@@�C��()���R�[������B
            l_response = this.undoUploadFile((WEB3AdminAccOpenApplyUploadCancelRequest)l_request);
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
     * �����J�ݐ\���A�b�v���[�h���͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�����J�݁i�\��UL�jget�A�b�v���[�h��ʁv�Q�ƁB<BR>
     * <BR>
     * @@param l_request - (l_request)<BR>
     * �Ǘ��Ҍ����J�ݐ\��UL���̓��N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3AdminAccOpenApplyUploadInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 47299B4D008F
     */
    protected WEB3AdminAccOpenApplyUploadInputResponse getUploadScreen(
        WEB3AdminAccOpenApplyUploadInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getUploadScreen(WEB3AdminAccOpenApplyUploadInputRequest)";
        log.entering(STR_METHOD_NAME);

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(String, boolean)
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�����J�ݐ\���A�b�v���[�h�iA0403�j
        //is�X�V�F�@@true
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN_APPLY_UPLOAD, true);

        //validate������t�\()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //�����J�ݐ\��ULCSV()
        WEB3AdminAccOpenApplyULCsv l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsv();

        //get�A�b�v���[�h�ŐV����(long)
        //[get�A�b�v���[�h�ŐV����()�̈���]
        //�f�[�^�L�[�F0
        AdministratorUploadRow l_administratorUploadRow =
            (AdministratorUploadRow)l_adminAccOpenApplyULCsv.getUploadNewHistory(0);

        //�A�b�v���[�h���������݂���ꍇ�A�������{
        WEB3AccOpenUploadHistoryUnit l_accOpenUploadHistoryUnit = null;
        if (l_administratorUploadRow != null)
        {
            //�����J�݃A�b�v���[�h���𖾍�( )
            l_accOpenUploadHistoryUnit = new WEB3AccOpenUploadHistoryUnit();

            //(*1.1)�v���p�e�B�Z�b�g
            //�� �A�b�v���[�h�s   �F�@@get�A�b�v���[�h�ŐV�����i�f�[�^�L�[�Flong�j�̖߂�l
            //�A�b�v���[�h������ԋ敪    �F
            if (l_administratorUploadRow.getUploadEndTimestamp() == null)
            {
                //�i�A�b�v���[�h�s.�A�b�v���[�h�I������ == null�j�̏ꍇ�A�h�A�b�v���[�h���h
                l_accOpenUploadHistoryUnit.uploadStateDiv = WEB3AccOpenUploadStateDivDef.UPLOADING;
            }
            else
            {
                //�i�A�b�v���[�h�s.�A�b�v���[�h�I������ != null�j�̏ꍇ�A�h�A�b�v���[�h�ρh
                l_accOpenUploadHistoryUnit.uploadStateDiv = WEB3AccOpenUploadStateDivDef.UPLOAD_COMPLETE;
            }

            //�A�b�v���[�h����        �F�@@�A�b�v���[�h�s.�A�b�v���[�h����
            l_accOpenUploadHistoryUnit.uploadNumber = l_administratorUploadRow.getUploadCount() + "";
            //�A�b�v���[�h�J�n����  �F�@@�A�b�v���[�h�s.�A�b�v���[�h�J�n����
            l_accOpenUploadHistoryUnit.uploadStartDate = l_administratorUploadRow.getUploadStartTimestamp();
            //�A�b�v���[�h�I������  �F�@@�A�b�v���[�h�s.�A�b�v���[�h�I������
            l_accOpenUploadHistoryUnit.uploadEndDate = l_administratorUploadRow.getUploadEndTimestamp();
            //�����J�݃G���[�ԍ�   �F�@@�A�b�v���[�h�s.���b�Z�[�W�R�[�h
            l_accOpenUploadHistoryUnit.accOpenErrorId = l_administratorUploadRow.getMessageCode();
        }

        // createResponse( )
        WEB3AdminAccOpenApplyUploadInputResponse l_response =
            (WEB3AdminAccOpenApplyUploadInputResponse)l_request.createResponse();

        //(*2)�v���p�e�B�Z�b�g
        //�A�b�v���[�h�����ꗗ�F
        //�A�b�v���[�h���������݂���ꍇ�A�����J�݃A�b�v���[�h���𖾍׃I�u�W�F�N�g�B
        //�A�b�v���[�h���������݂��Ȃ��ꍇ�Anull�B
        if (l_accOpenUploadHistoryUnit != null)
        {
            l_response.uploadHistoryList = l_accOpenUploadHistoryUnit;
        }
        else
        {
            l_response.uploadHistoryList = null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�A�b�v���[�h�t�@@�C��)<BR>
     * �����J�ݐ\���A�b�v���[�h�m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�����J�݁i�\��UL�jvalidate�A�b�v���[�h�t�@@�C���v�Q�ƁB<BR>
     * <BR>
     * @@param l_request - (l_request)<BR>
     * �Ǘ��Ҍ����J�ݐ\��UL�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3AdminAccOpenApplyUploadConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 47299C3C0211
     */
    protected WEB3AdminAccOpenApplyUploadConfirmResponse validateUploadFile(
        WEB3AdminAccOpenApplyUploadConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateUploadFile(WEB3AdminAccOpenApplyUploadConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[validate����()�Ɏw�肷�����]
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�����J�ݐ\���A�b�v���[�h�iA0403�j
        //is�X�V�F�@@true
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN_APPLY_UPLOAD, true);

        //validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //�����J�ݐ\��ULCSV
        WEB3AdminAccOpenApplyULCsv l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsv();
        //validate�����A�b�v���[�h(�A�b�v���[�h�h�c : Long)
        //[validate�����A�b�v���[�h()�Ɏw�肷�����]
        //�A�b�v���[�h�h�c�F�@@null
        l_adminAccOpenApplyULCsv.validateSameTimeUpload(null);

        //get�Ǘ��҃R�[�h( )
        String l_strAdministratorCode = l_admin.getAdministratorCode();
        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //save�A�b�v���[�h�J�n(�f�[�^�L�[ : long, ���l�P : String,
        //      ���l�Q : String, ���l�R : String, �X�V�҃R�[�h : String)
        //[save�A�b�v���[�h�J�n()�Ɏw�肷�����]
        //�f�[�^�L�[�F�@@0
        //���l�P�F�@@null
        //���l�Q�F�@@null
        //���l�R�F�@@null
        //�X�V�҃R�[�h�F�@@�Ǘ���.get�Ǘ��҃R�[�h()
        l_adminAccOpenApplyULCsv.saveUpLoadStart(0, null, null, null, l_strAdministratorCode);

        // (*1) ���N�G�X�g�f�[�^.�A�b�v���[�h�t�@@�C��[]�̊e�v�f����LOOP����
        String[] l_strUploadFiles = l_request.uploadFile;

        for (int i = 0; i < l_strUploadFiles.length; i++)
        {
            int l_intLineNumber = -1;
            try
            {
                //add���׍s(���׍s������ : String)
                l_intLineNumber = l_adminAccOpenApplyULCsv.addRow(l_strUploadFiles[i]);
            }
            catch (WEB3BaseException l_ex)
            {
                //save�A�b�v���[�h�G���[(�G���[��� : ErrorInfo)
                //[save�A�b�v���[�h�G���[()�Ɏw�肷�����]
                //�G���[���F�@@�icatch������O�j.getErrorInfo()
                l_adminAccOpenApplyULCsv.saveUploadError(l_ex.getErrorInfo());

                log.debug(l_ex.getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getErrorMessage());
            }

            //��s�̏ꍇ�iadd���׍s()�̖߂�l < 0�j�A���Y�v�f�̏����𒆒f�icontinue;�j
            if (l_intLineNumber < 0)
            {
                continue;
            }

            try
            {
                //validate���R�[�h�ԍ�(int)
                //[validate���R�[�h�ԍ�()�Ɏw�肷�����]
                //�s�ԍ��F�@@add���׍s()�̖߂�l
                l_adminAccOpenApplyULCsv.validateRecordNumber(l_intLineNumber);
            }
            catch (WEB3BaseException l_ex)
            {
                //save�A�b�v���[�h�G���[(�G���[��� : ErrorInfo)
                //[save�A�b�v���[�h�G���[()�Ɏw�肷�����]
                //�G���[���F�@@�icatch������O�j.getErrorInfo()
                l_adminAccOpenApplyULCsv.saveUploadError(l_ex.getErrorInfo());

                log.debug(l_intLineNumber + "�@@" + l_ex.getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_intLineNumber + "�@@" + l_ex.getErrorMessage());
            }

            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen = null;
            try
            {
                //validate���ʃR�[�h(int)
                //�s�ԍ��F�@@add���׍s()�̖߂�l
                l_adminAccOpenApplyULCsv.validateRequestNumber(l_intLineNumber);

                //validate�����敪(int)
                //�s�ԍ��F�@@add���׍s()�̖߂�l
                l_adminAccOpenApplyULCsv.validateAccountType(l_intLineNumber);

                //validate������������(int)
                //�s�ԍ��F�@@add���׍s()�̖߂�l
                l_adminAccOpenApplyULCsv.validateInfomationClaimDatetime(l_intLineNumber);

                //to�����J�݌����q(int, String)
                //[to�����J�݌����q()�Ɏw�肷�����]
                //�s�ԍ��F�@@add���׍s()�̖߂�l
                //�،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
                l_accOpenExpAccountOpen =
                    l_adminAccOpenApplyULCsv.toExpAccountOpen(l_intLineNumber, l_strInstitutionCode);

                //validate�����J�ݐ\�����(String, String)
                //[validate�����J�ݐ\�����()�Ɏw�肷�����]
                //�`�F�b�N�^�C�v�F�`�F�b�N�^�C�v.�����J�݃A�b�v���[�h
                //�ڋq�R�[�h�����̔ԃ`�F�b�N�F0(�����̔Ԃ��s��Ȃ�)
                l_accOpenExpAccountOpen.validateAccountOpenRegistInfo(
                    WEB3ValidateTypeDef.ACCOUNT_OPEN_UPLOAD,
                    WEB3AccOpenAccountCodeAutoFlagDef.NOT_AUTO);
            }
            catch (WEB3BaseException l_ex)
            {
                //save�A�b�v���[�h�G���[(�G���[��� : ErrorInfo)
                //[save�A�b�v���[�h�G���[()�Ɏw�肷�����]
                //�G���[���F�@@�icatch������O�j.getErrorInfo()
                l_adminAccOpenApplyULCsv.saveUploadError(l_ex.getErrorInfo());

                String l_strRecordNumber = l_adminAccOpenApplyULCsv.getRecordNumber(l_intLineNumber);
                log.debug(l_strRecordNumber + "�@@" + l_ex.getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_strRecordNumber + "�@@" + l_ex.getErrorMessage());
            }

            try
            {
                //add���׍s()�̖߂�l > 0 �̏ꍇ�A�ȉ��̏������s��
                if (l_intLineNumber > 0)
                {
                    // validate�d���ڋq(int)
                    //[validate�d���ڋq()�Ɏw�肷�����]
                    //�s�ԍ��F�@@add���׍s()�̖߂�l
                    l_adminAccOpenApplyULCsv.validateDuplicateAccount(l_intLineNumber);
                }

                //validate�����J�݌����q�o�^(int, String)
                //[validate�����J�݌����q�o�^()�Ɏw�肷�����]
                //�s�ԍ��F�@@add���׍s()�̖߂�l
                //�،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
                l_adminAccOpenApplyULCsv.validateExpAccountOpenLogin(l_intLineNumber, l_strInstitutionCode);
            }
            catch (WEB3BaseException l_ex)
            {
                //save�A�b�v���[�h�G���[(�G���[��� : ErrorInfo)
                //[save�A�b�v���[�h�G���[()�Ɏw�肷�����]
                //�G���[���F�@@�icatch������O�j.getErrorInfo()
                l_adminAccOpenApplyULCsv.saveUploadError(l_ex.getErrorInfo());

                log.debug(l_ex.getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getErrorMessage());
            }
        }

        //get���׍s��( )
        int l_intRowCount = l_adminAccOpenApplyULCsv.getRowCount();

        //get�A�b�v���[�h�h�c( )
        String l_strUploadFileId = String.valueOf(l_adminAccOpenApplyULCsv.getAdministratorUploadId());

        //save�A�b�v���[�hTemp( )
        l_adminAccOpenApplyULCsv.saveUploadTemp();

        //createResponse( )
        WEB3AdminAccOpenApplyUploadConfirmResponse l_response =
            (WEB3AdminAccOpenApplyUploadConfirmResponse)l_request.createResponse();

        //�@@�A�b�v���[�h���� �F�@@get���׍s��()�̖߂�l
        l_response.uploadNumber = String.valueOf(l_intRowCount);
        //�@@�A�b�v���[�hID    �F�@@get�A�b�v���[�hID()�̖߂�l
        l_response.uploadID = l_strUploadFileId;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�A�b�v���[�h�t�@@�C��)<BR>
     * �����J�ݐ\���A�b�v���[�h�����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�����J�݁i�\��UL�jsubmit�A�b�v���[�h�t�@@�C���v�Q�ƁB<BR>
     * <BR>
     * @@param l_request - (l_request)<BR>
     * �Ǘ��Ҍ����J�ݐ\��UL�������N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3AdminAccOpenApplyUploadCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 47299C3D01D2
     */
    protected WEB3AdminAccOpenApplyUploadCompleteResponse submitUploadFile(
        WEB3AdminAccOpenApplyUploadCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitUploadFile(WEB3AdminAccOpenApplyUploadCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(String, boolean)
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�����J�ݐ\���A�b�v���[�h�iA0403�j
        //is�X�V�F�@@true
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN_APPLY_UPLOAD, true);

        //validate������t�\()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //validate����p�X���[�h(�p�X���[�h : String)
        //�p�X���[�h�F�@@���N�G�X�g�f�[�^.�Ïؔԍ�
        l_admin.validateTradingPassword(l_request.password);

        //�����J�ݐ\��ULCSV()
        WEB3AdminAccOpenApplyULCsv l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsv();

        //validate�����A�b�v���[�h(�A�b�v���[�h�h�c : Long)
        //[validate�����A�b�v���[�h()�Ɏw�肷�����]
        //�A�b�v���[�h�h�c�F�@@���N�G�X�g�f�[�^.�A�b�v���[�h�h�c
        l_adminAccOpenApplyULCsv.validateSameTimeUpload(new Long(l_request.uploadID));

        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //setDataFrom�A�b�v���[�hTemp(�A�b�v���[�h�h�c : long)
        //[setDataFrom�A�b�v���[�hTemp()�Ɏw�肷�����]
        //�A�b�v���[�h�h�c�F�@@���N�G�X�g�f�[�^.�A�b�v���[�h�h�c
        l_adminAccOpenApplyULCsv.setDataFromUploadTemp(Long.parseLong(l_request.uploadID));

        //get���׍s��( )
        int l_intRowCount = l_adminAccOpenApplyULCsv.getRowCount();

        //���׍s�̐���LOOP����
        for (int i = 0; i < l_intRowCount; i++)
        {
            //to�����J�݌����q(int, String)
            //[to�����J�݌����q()�Ɏw�肷�����]
            //�s�ԍ��F�@@index
            //�،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen =
                l_adminAccOpenApplyULCsv.toExpAccountOpen(i, l_strInstitutionCode);

            try
            {
                QueryProcessor l_queryProcessor =
                    Processors.getDefaultProcessor();

                ExpAccountOpenParams l_expAccountOpenParams =
                    (ExpAccountOpenParams)l_accOpenExpAccountOpen.getDataSourceObject();

                //�����J�݌����qParams.�،���ЃR�[�h
                l_expAccountOpenParams.setInstitutionCode(l_expAccountOpenParams.getInstitutionCode());

                AccountManager l_accountManager = GtlUtils.getAccountManager();
                Institution l_institution = l_accountManager.getInstitution(
                    l_expAccountOpenParams.getInstitutionCode());

                long l_lngBranchId = l_accountManager.getBranch(
                    l_institution, l_expAccountOpenParams.getBranchCode()).getBranchId();

                //�،����ID:�،���ЃR�[�h�ɊY������،����ID�B
                l_expAccountOpenParams.setInstitutionId(l_institution.getInstitutionId());

                //���X�h�c:�،���ЁC���X�R�[�h�ɊY�����镔�XID�B
                l_expAccountOpenParams.setBranchId(l_lngBranchId);

                //���������t���O
                l_expAccountOpenParams.setExAccountFlag(BooleanEnum.FALSE);

                //���������t���O
                l_expAccountOpenParams.setExperienceFlagEquity(BooleanEnum.FALSE);

                //�M�p����t���O
                l_expAccountOpenParams.setExperienceFlagMargin(BooleanEnum.FALSE);

                //���t���O
                l_expAccountOpenParams.setExperienceFlagBond(BooleanEnum.FALSE);

                //�]���Ѝt���O
                l_expAccountOpenParams.setExperienceFlagWt(BooleanEnum.FALSE);

                //�����M���i�����j�t���O
                l_expAccountOpenParams.setExperienceFlagFundSk(BooleanEnum.FALSE);

                //�����M���i���Ѝj�t���O
                l_expAccountOpenParams.setExperienceFlagFundBd(BooleanEnum.FALSE);

                //�敨�E�I�v�V�����t���O
                l_expAccountOpenParams.setExperienceFlagFo(BooleanEnum.FALSE);

                //�O���،��t���O
                l_expAccountOpenParams.setExperienceFlagFEquity(BooleanEnum.FALSE);

                //���̑��t���O
                l_expAccountOpenParams.setExperienceFlagEtc(BooleanEnum.FALSE);

                //�����~�j�����t���O
                l_expAccountOpenParams.setInterestFlagMinistock(BooleanEnum.FALSE);

                //���̑��t���O
                l_expAccountOpenParams.setInterestFlagEtc(BooleanEnum.FALSE);

                //�����p�敪
                l_expAccountOpenParams.setIdConfirmFlag(BooleanEnum.FALSE);

                //�X�V�҃R�[�h�Ǘ���.�Ǘ��҃R�[�h
                l_expAccountOpenParams.setLastUpdater(l_admin.getAdministratorCode());

                //�쐬������������
                l_expAccountOpenParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

                //�X�V������������
                l_expAccountOpenParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                //�쐬�҃R�[�h�Ǘ���.�Ǘ��҃R�[�h
                l_expAccountOpenParams.setCreator(l_admin.getAdministratorCode());

                //doInsertQuery(arg0 : Row)
                l_queryProcessor.doInsertQuery(l_expAccountOpenParams);
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
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //save�A�b�v���[�h�I��( )
        l_adminAccOpenApplyULCsv.saveUploadEnd();

        //delete�A�b�v���[�hTemp( )
        l_adminAccOpenApplyULCsv.deleteUploadTemp();

        //createResponse( )
        WEB3AdminAccOpenApplyUploadCompleteResponse l_response =
            (WEB3AdminAccOpenApplyUploadCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (undo�A�b�v���[�h�t�@@�C��)<BR>
     * �����J�ݐ\���A�b�v���[�h���~�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�����J�݁i�\��UL�jundo�A�b�v���[�h�t�@@�C���v�Q�ƁB<BR>
     * @@param l_request - (l_request)<BR>
     * �Ǘ��Ҍ����J�ݐ\��UL���~���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3AdminAccOpenApplyUploadCancelResponse
     * @@throws WEB3BaseException
     * @@roseuid 47299C3E01F2
     */
    protected WEB3AdminAccOpenApplyUploadCancelResponse undoUploadFile(
        WEB3AdminAccOpenApplyUploadCancelRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "undoUploadFile(WEB3AdminAccOpenApplyUploadCancelRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //�����J�ݐ\��ULCSV(long)
        WEB3AdminAccOpenApplyULCsv l_adminAccOpenApplyULCsv =
            new WEB3AdminAccOpenApplyULCsv(Long.parseLong(l_request.uploadID));

        //delete�A�b�v���[�hTemp( )
        l_adminAccOpenApplyULCsv.deleteUploadTemp();

        //update�A�b�v���[�h���~( )
        l_adminAccOpenApplyULCsv.updateUploadCancel();

        //createResponse( )
        WEB3AdminAccOpenApplyUploadCancelResponse l_response =
            (WEB3AdminAccOpenApplyUploadCancelResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
