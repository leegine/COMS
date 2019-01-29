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
filename	WEB3AdminFPTUploadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҋ����@@��t�{���A�b�v���[�h�T�[�r�XImpl(WEB3AdminFPTUploadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/07 ���g (���u) �V�K�쐬 ���f�� No.012,No.014
Revision History : 2007/12/11 ���g (���u) �d�l�ύX ���f�� No.017,No.018
Revision History : 2007/12/21 �Ӑ� (���u) �d�l�ύX ���f�� No.020
Revision History : 2007/12/25 �Ӑ� (���u) �d�l�ύX ���f�� No.021
Revision History : 2008/01/28 ���g (���u) �d�l�ύX ���f�� No.027,No.033
Revision History : 2008/03/26 �g�C�� (���u) �d�l�ύX  ���f��No.052
*/

package webbroker3.docadmin.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CategCodeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.docadmin.WEB3AdminFPTBatoProductCodeManagement;
import webbroker3.docadmin.WEB3AdminFPTDocDeliveryManagement;
import webbroker3.docadmin.WEB3AdminFPTDocDeliveryManagementHistory;
import webbroker3.docadmin.WEB3FPTUploadCsv;
import webbroker3.docadmin.define.WEB3FPTStatusDivDef;
import webbroker3.docadmin.define.WEB3FPTUploadStateDivDef;
import webbroker3.docadmin.message.WEB3AdminFPTUploadCancelRequest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadCancelResponse;
import webbroker3.docadmin.message.WEB3AdminFPTUploadCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadCompleteResponse;
import webbroker3.docadmin.message.WEB3AdminFPTUploadConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadConfirmResponse;
import webbroker3.docadmin.message.WEB3AdminFPTUploadInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadInputResponse;
import webbroker3.docadmin.message.WEB3FPTUploadHistoryInfoUnit;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTUploadService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.gentrade.data.DocDeliveryManagementHistParams;
import webbroker3.gentrade.data.DocDeliveryManagementParams;
import webbroker3.gentrade.data.DocDeliveryManagementRow;
import webbroker3.gentrade.define.WEB3GentradeDateAnalysisFlagDivDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҋ����@@��t�{���A�b�v���[�h�T�[�r�XImpl)<BR>
 * �Ǘ��ҋ����@@��t�{���A�b�v���[�h�T�[�r�XImpl<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminFPTUploadServiceImpl implements WEB3AdminFPTUploadService
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTUploadServiceImpl.class);

    /**
     * @@roseuid 4758B27801F4
     */
    public WEB3AdminFPTUploadServiceImpl()
    {

    }

    /**
     * �����@@��t�{���A�b�v���[�h�������s���B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��ҋ����@@��t�{���A�b�v���[�h���̓��N�G�X�g�̏ꍇ<BR>
     * �@@�|get�A�b�v���[�h���()���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��ҋ����@@��t�{���A�b�v���[�h�m�F���N�G�X�g�̏ꍇ<BR>
     * �@@�|validate�A�b�v���[�h�t�@@�C��()���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��ҋ����@@��t�{���A�b�v���[�h�������N�G�X�g�̏ꍇ<BR>
     * �@@�|submit�A�b�v���[�h�t�@@�C��()���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��ҋ����@@��t�{���A�b�v���[�h���~���N�G�X�g�̏ꍇ<BR>
     * �@@�|undo�A�b�v���[�h()���R�[������B<BR>
     * <BR>
     * ���T�[�r�X���\�b�h�ɂė�O�����������ꍇ�́A<BR>
     * �@@��O�I�u�W�F�N�g�̒ǉ�������iWEB3BaseException.errorMessage�j<BR>
     * �����X�|���X�f�[�^.errorMessage�ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4737F20E02C7
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

        if (l_request instanceof WEB3AdminFPTUploadInputRequest)
        {
            // �����̃��N�G�X�g�f�[�^���A�Ǘ��ҋ����@@��t�{���A�b�v���[�h���̓��N�G�X�g�̏ꍇ
            //�|get�A�b�v���[�h���()���R�[������B
            l_response = this.getUploadScreen((WEB3AdminFPTUploadInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFPTUploadConfirmRequest)
        {
            //�����̃��N�G�X�g�f�[�^���A�Ǘ��ҋ����@@��t�{���A�b�v���[�h�m�F���N�G�X�g�̏ꍇ
            //�|validate�A�b�v���[�h�t�@@�C��()���R�[������B
            l_response = this.validateUploadFile((WEB3AdminFPTUploadConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFPTUploadCompleteRequest)
        {
            //�����̃��N�G�X�g�f�[�^���A�Ǘ��ҋ����@@��t�{���A�b�v���[�h�������N�G�X�g�̏ꍇ
            //�|submit�A�b�v���[�h�t�@@�C��()���R�[������B
            l_response = this.submitUploadFile((WEB3AdminFPTUploadCompleteRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFPTUploadCancelRequest)
        {
            //�����̃��N�G�X�g�f�[�^���A�Ǘ��ҋ����@@��t�{���A�b�v���[�h���~���N�G�X�g�̏ꍇ
            //�|undo�A�b�v���[�h()���R�[������B
            l_response = this.undoUploadFile((WEB3AdminFPTUploadCancelRequest)l_request);
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
     * �����@@��t�{���A�b�v���[�h���͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uget�A�b�v���[�h��ʁv�Q�ƁB<BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminFPTUploadInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4737F5CD0351
     */
    protected WEB3AdminFPTUploadInputResponse getUploadScreen(
        WEB3AdminFPTUploadInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getUploadScreen(WEB3AdminFPTUploadInputRequest)";
        log.entering(STR_METHOD_NAME);

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�@@�\�J�e�S���R�[�h�FG0103(�����@@��t�{���A�b�v���[�h)
        //is�X�V�Ftrue
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FPT_HISTORY_INQUIRY, true);

        //validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //�����@@��t�{���A�b�v���[�hCSV( )
        WEB3FPTUploadCsv l_fPTUploadCsv = new WEB3FPTUploadCsv();

        //get�ŐV�A�b�v���[�h����(�f�[�^�L�[ : long)
        //�f�[�^�L�[�F�@@0
        AdministratorUploadRow l_administratorUploadRow = l_fPTUploadCsv.getLatestUploadAction(0);

        //�����@@��t�{���A�b�v���[�h���𖾍�
        WEB3FPTUploadHistoryInfoUnit l_fPTUploadHistoryInfoUnit = null;

        //(*1)�A�b�v���[�h���������݂���ꍇ�A�������{
        if (l_administratorUploadRow != null)
        {
            l_fPTUploadHistoryInfoUnit = new WEB3FPTUploadHistoryInfoUnit();

            //�A�b�v���[�h������ԋ敪
            if (l_administratorUploadRow.getUploadEndTimestamp() == null)
            {
                l_fPTUploadHistoryInfoUnit.uploadStateDiv = WEB3FPTUploadStateDivDef.UPLOADING;
            }
            else
            {
                l_fPTUploadHistoryInfoUnit.uploadStateDiv = WEB3FPTUploadStateDivDef.UPLOAD_COMPLETE;
            }

            //�A�b�v���[�h����
            l_fPTUploadHistoryInfoUnit.uploadNumber = String.valueOf(l_administratorUploadRow.getUploadCount());

            //�A�b�v���[�h�J�n����
            l_fPTUploadHistoryInfoUnit.uploadStartDate = l_administratorUploadRow.getUploadStartTimestamp();

            //�A�b�v���[�h�I������
            l_fPTUploadHistoryInfoUnit.uploadEndDate = l_administratorUploadRow.getUploadEndTimestamp();

            //�����@@��t�{���G���[�ԍ�
            l_fPTUploadHistoryInfoUnit.fptErrorId = l_administratorUploadRow.getMessageCode();

            //�����敪
            l_fPTUploadHistoryInfoUnit.statusDiv = l_administratorUploadRow.getNote2();
        }

        //createResponse
        WEB3AdminFPTUploadInputResponse l_response =
            (WEB3AdminFPTUploadInputResponse)l_request.createResponse();

        //(*2)�v���p�e�B�Z�b�g
        l_response.uploadHistoryUnit = l_fPTUploadHistoryInfoUnit;

        return l_response;
    }

    /**
     * (validate�A�b�v���[�h�t�@@�C��)<BR>
     * �����@@��t�{���A�b�v���[�h�m�F��ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uvalidate�A�b�v���[�h�t�@@�C���v�Q�ƁB<BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminFPTUploadConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4737F62901D6
     */
    protected WEB3AdminFPTUploadConfirmResponse validateUploadFile(
        WEB3AdminFPTUploadConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateUploadFile(WEB3AdminFPTUploadConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�@@�\�J�e�S���R�[�h�FG0103(�����@@��t�{���A�b�v���[�h)
        //is�X�V�Ftrue
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.FPT_HISTORY_INQUIRY, true);

        //validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //�����@@��t�{���A�b�v���[�hCSV( )
        WEB3FPTUploadCsv l_fPTUploadCsv = new WEB3FPTUploadCsv();

        //validate�����A�b�v���[�h(�A�b�v���[�h�h�c : Long)
        //�A�b�v���[�h�h�c�F�@@null
        l_fPTUploadCsv.validateSameTimeUpload(null);

        try
        {
            //check���׍s��
            //l_request�F ���N�G�X�g�f�[�^
            l_fPTUploadCsv.checkDetailLines(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("check���׍s��()�ɂė�O���X���[���ꂽ�ꍇ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getErrorMessage());
        }

        //get�Ǘ��҃R�[�h( )
        String l_strAdministratorCode = l_administrator.getAdministratorCode();
        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //save�A�b�v���[�h�J�n
        //�f�[�^�L�[�F�@@0
        //���l�P�F�@@null
        //���l�Q�F�@@���N�G�X�g�f�[�^.�����敪
        //���l�R�F�@@null
        //�X�V�҃R�[�h�F�@@�Ǘ���.get�Ǘ��҃R�[�h()
        l_fPTUploadCsv.saveUpLoadStart(
            0,
            null,
            l_request.statusDiv,
            null,
            l_strAdministratorCode);

        //���N�G�X�g�f�[�^.�A�b�v���[�h�t�@@�C��[]�̊e�v�f����LOOP����
        for (int i = 0; i < l_request.uploadFile.length; i++)
        {
            int l_intLineNumber = -1;
            try
            {
                //add���׍s(���׍s������ : String, ���t��̓t���O : String)
                //���׍s������F�@@���N�G�X�g�f�[�^.�A�b�v���[�h�t�@@�C��[index]
                //���t��̓t���O�F "1"�i�����ȉ�́j
                l_intLineNumber = l_fPTUploadCsv.addRow(
                    l_request.uploadFile[i],
                    WEB3GentradeDateAnalysisFlagDivDef.STRICTLY_ANALYSIS);
            }
            catch (WEB3BaseException l_ex)
            {
                //save�A�b�v���[�h�G���[(�G���[��� : ErrorInfo)
                //�G���[���F�@@�icatch������O�j.getErrorInfo()
                l_fPTUploadCsv.saveUploadError(l_ex.getErrorInfo());

                log.error("add���׍s()�ɂė�O���X���[���ꂽ�ꍇ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    (i + 1) + "," + l_request.uploadFile[i]);
            }

            //(*) ��s�̏ꍇ�iadd���׍s()�̖߂�l < 0�j�A���Y�v�f�̏����𒆒f
            if (l_intLineNumber < 0)
            {
                continue;
            }

            try
            {
                //validate���׍s(int, �Ǘ���)
                //�s�ԍ��F�@@�iadd���׍s()�̖߂�l�j
                //�Ǘ��ҁF�@@getInstanceFrom���O�C�����()�̖߂�l
                l_fPTUploadCsv.validateDetailLine(l_intLineNumber, l_administrator);

                //validate�d���ڋq(int, String, String)
                //�s�ԍ��F�@@�iadd���׍s()�̖߂�l�j
                //�،���ЃR�[�h�F �Ǘ���.get�،���ЃR�[�h()�̖߂�l
                //�����敪�F ���N�G�X�g�f�[�^.�����敪
                l_fPTUploadCsv.validateDuplicateAccount(
                    l_intLineNumber,
                    l_strInstitutionCode,
                    l_request.statusDiv);
            }
            catch (WEB3BaseException l_ex)
            {
                //save�A�b�v���[�h�G���[(�G���[��� : ErrorInfo)
                //�G���[���F�@@�icatch������O�j.getErrorInfo()
                l_fPTUploadCsv.saveUploadError(l_ex.getErrorInfo());

                String l_strBranchCode = "";
                String l_strAccountCode = "";
                String l_strBatoProductCode = "";
                String l_strDeliveryDate = "";

                if (l_fPTUploadCsv.getBranchCode(l_intLineNumber) != null)
                {
                    l_strBranchCode = l_fPTUploadCsv.getBranchCode(l_intLineNumber);
                }
                if (l_fPTUploadCsv.getAccountCode(l_intLineNumber) != null)
                {
                    l_strAccountCode = l_fPTUploadCsv.getAccountCode(l_intLineNumber);
                }
                if (l_fPTUploadCsv.getBatoProductCode(l_intLineNumber) != null)
                {
                    l_strBatoProductCode = l_fPTUploadCsv.getBatoProductCode(l_intLineNumber);
                }
                if (l_fPTUploadCsv.getDeliveryDate(l_intLineNumber) != null)
                {
                    l_strDeliveryDate = l_fPTUploadCsv.getDeliveryDate(l_intLineNumber);
                }

                log.error("validate���׍s()��validate�d���ڋq()�ɂė�O���X���[���ꂽ�ꍇ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    (l_intLineNumber + 1) + "," + l_strBranchCode + ","
                    + l_strAccountCode + ","
                    + l_strBatoProductCode + ","
                    + l_strDeliveryDate);
            }
        }

        //get�A�b�v���[�h�h�c( )
        String l_strUploadID = String.valueOf(l_fPTUploadCsv.getAdministratorUploadId());

        //save�A�b�v���[�hTemp( )
        l_fPTUploadCsv.saveUploadTemp();

        //get���׍s��( )
        String l_strRowCount = String.valueOf(l_fPTUploadCsv.getRowCount());

        //createResponse( )
        WEB3AdminFPTUploadConfirmResponse l_response =
            (WEB3AdminFPTUploadConfirmResponse)l_request.createResponse();

        //�@@�A�b�v���[�h���� �F�@@get���׍s��()�̖߂�l
        l_response.uploadNumber = l_strRowCount;
        //�@@�A�b�v���[�hID    �F�@@get�A�b�v���[�hID()�̖߂�l
        l_response.uploadId = l_strUploadID;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�A�b�v���[�h�t�@@�C��)<BR>
     * �����@@��t�{���A�b�v���[�h������ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �usubmit�A�b�v���[�h�t�@@�C���v�Q�ƁB<BR>
     * ======================================================== <BR>
     * ��̈ʒu�F���ɓo�^�ς݂ł������ꍇ�i���ʌ�t�Ǘ�.get���ʌ�t�Ǘ��s() != null�j�A
     * ��O���X���[����B
     * �@@�@@class: WEB3BaseException <BR>
     * �@@�@@tag�@@: BUSINESS_ERROR_02962 <BR>
     * ======================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * ��̈ʒu�F�폜�s�����݂��Ȃ��ꍇ�i���ʌ�t�Ǘ�.get���ʋ��t�Ǘ��s() == null�j�A
     * ��O���X���[����B
     * �@@�@@class: WEB3BaseException <BR>
     * �@@�@@tag�@@: BUSINESS_ERROR_02963 <BR>
     * ======================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * ��̈ʒu�Fis�d�q�������R�[�h()�̖߂�l==false�̏ꍇ�A
     * ��O���X���[����B
     * �@@�@@class: WEB3BaseException <BR>
     * �@@�@@tag�@@: BUSINESS_ERROR_02995 <BR>
     * ======================================================== <BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminFPTUploadCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4737F62A005E
     */
    protected WEB3AdminFPTUploadCompleteResponse submitUploadFile(
        WEB3AdminFPTUploadCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitUploadFile(WEB3AdminFPTUploadCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�@@�\�J�e�S���R�[�h�FG0103(�����@@��t�{���A�b�v���[�h)
        //is�X�V�Ftrue
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.FPT_HISTORY_INQUIRY, true);

        //validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //validate����p�X���[�h(�p�X���[�h : String)
        //�p�X���[�h�F�@@���N�G�X�g�f�[�^.�Ïؔԍ�
        l_administrator.validateTradingPassword(l_request.password);

        //�����@@��t�{���A�b�v���[�hCSV( )
        WEB3FPTUploadCsv l_fPTUploadCsv = new WEB3FPTUploadCsv();

        Long l_uploadId = Long.valueOf(l_request.uploadId);
        //validate�����A�b�v���[�h(�A�b�v���[�h�h�c : Long)
        //�A�b�v���[�h�h�c�F�@@���N�G�X�g�f�[�^.�A�b�v���[�h�h�c
        l_fPTUploadCsv.validateSameTimeUpload(l_uploadId);

        //setDataFrom�A�b�v���[�hTemp(�A�b�v���[�h�h�c : long)
        //�A�b�v���[�h�h�c�F�@@���N�G�X�g�f�[�^.�A�b�v���[�h�h�c
        l_fPTUploadCsv.setDataFromUploadTemp(l_uploadId.longValue());

        //get���׍s��( )
        int l_intRowCount = l_fPTUploadCsv.getRowCount();

        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //(*1) ���׍s�̐���LOOP����
        for (int i = 0; i < l_intRowCount; i++)
        {
            //get�ڋq(int, String)
            //�s�ԍ��F�@@index
            //�،���ЃR�[�h�F�@@�Ǘ���.get�،���ЃR�[�h()
            WEB3GentradeMainAccount l_gentradeMainAccount =
                l_fPTUploadCsv.getMainAccount(i, l_strInstitutionCode);

            //getAccountId( )
            long l_lngAccountId = l_gentradeMainAccount.getAccountId();

            //get���X�R�[�h(int)
            //�s�ԍ��F�@@index
            String l_strBranchCode = l_fPTUploadCsv.getBranchCode(i);

            //get�ڋq�R�[�h(int)
            //�s�ԍ��F�@@index
            l_fPTUploadCsv.getAccountCode(i);

            //get���ʌ�t��(int)
            //�s�ԍ��F�@@index
            String l_strDeliveryDate = l_fPTUploadCsv.getDeliveryDate(i);

            //get�d�q�������R�[�h
            //�s�ԍ��F�@@index
            String l_strBatoProductCode = l_fPTUploadCsv.getBatoProductCode(i);

            //�d�q�������R�[�h�Ǘ�
            //�،���ЃR�[�h�F �Ǘ���.get�،���ЃR�[�h()�̖߂�l
            //���X�R�[�h�F �����@@��t�{���A�b�v���[�hCSV.get���X�R�[�h()�̖߂�l
            //���ʋ敪�F "010"
            //�d�q�������R�[�h�F �����@@��t�{���A�b�v���[�hCSV.get�d�q�������R�[�h()�̖߂�l
            WEB3AdminFPTBatoProductCodeManagement l_batoProductCodeManagement =
                new WEB3AdminFPTBatoProductCodeManagement(
                    l_administrator.getInstitutionCode(),
                    l_strBranchCode,
                    WEB3CategCodeDef.DOCUMENT_DELIVERY,
                    l_strBatoProductCode);

            //is�d�q�������R�[�h
            //is�d�q�������R�[�h()�̖߂�l==false�̏ꍇ�A��O���X���[����B
            //is�d�q�������R�[�h()�̖߂�l==false�̏ꍇ�A�A�u�d�q�������R�[�h���s���ł��B�v �iBUSINESS_ERROR_XXXXX�j��O���X���[����B
            if (!l_batoProductCodeManagement.isBatoProductCode())
            {
                log.debug("���ɓo�^�ς݂ł������ꍇ�A�o�^�A�b�v���[�h�����s�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02995,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    (i + 1) + "," + l_fPTUploadCsv.getBranchCode(i) + ","
                    + l_fPTUploadCsv.getAccountCode(i) + ","
                    + l_strBatoProductCode + ","
                    + l_fPTUploadCsv.getDeliveryDate(i));
            }

            //validate���X����(���X�R�[�h : String)
            //���X�R�[�h�F get���X�R�[�h()�̖߂�l
            l_administrator.validateBranchPermission(l_strBranchCode);

            //get���ʌ�t�Ǘ��s(long, String, String, Date,String)
            //����ID�F �ڋq.getAccountId()�̖߂�l
            //���ʋ敪�F "010"
            //�����R�[�h�F get�d�q�������R�[�h()�̖߂�l
            //���ʌ�t���F get���ʌ�t��()�̖߂�l
            //���ʎ�ރR�[�h�F get�d�q�������R�[�h()�̖߂�l���R��
            WEB3AdminFPTDocDeliveryManagement l_adminFPTDocDeliveryManagement =
                new WEB3AdminFPTDocDeliveryManagement();
            DocDeliveryManagementRow l_docDeliveryManagementRow =
                l_adminFPTDocDeliveryManagement.getDocDivManagementParams(
                    l_lngAccountId,
                    WEB3CategCodeDef.DOCUMENT_DELIVERY,
                    l_strBatoProductCode,
                    WEB3DateUtility.getDate(l_strDeliveryDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD),
                    l_strBatoProductCode.substring(0, 3));

            //�o�^�A�b�v���[�h�����i���N�G�X�g�f�[�^.�����敪=="0"�j�̏ꍇ
            if (WEB3FPTStatusDivDef.LOGIN.equals(l_request.statusDiv))
            {
                //���ɓo�^�ς݂ł������ꍇ�i���ʌ�t�Ǘ�.get���ʌ�t�Ǘ��s() != null�j�A��O���X���[����B
                if (l_docDeliveryManagementRow != null)
                {
                    log.debug("���ɓo�^�ς݂ł������ꍇ�A�o�^�A�b�v���[�h�����s�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02962,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        (i + 1) + "," + l_fPTUploadCsv.getBranchCode(i) + ","
                        + l_fPTUploadCsv.getAccountCode(i) + ","
                        + l_strBatoProductCode + ","
                        + l_fPTUploadCsv.getDeliveryDate(i));
                }

                //(*1.2)�v���p�e�B�Z�b�g
                //���ʌ�t�Ǘ��e�[�u��Params�𐶐����A�ȉ���ݒ肷��B
                DocDeliveryManagementParams l_docDeliveryManagementParams = new DocDeliveryManagementParams();
                //���ʌ�t�Ǘ��e�[�u��Params.����ID = �ڋq.getAccountId()�̖߂�l
                l_docDeliveryManagementParams.setAccountId(l_lngAccountId);
                //���ʌ�t�Ǘ��e�[�u��Params.�،���ЃR�[�h = �Ǘ���.get�،���ЃR�[�h()�̖߂�l
                l_docDeliveryManagementParams.setInstitutionCode(l_strInstitutionCode);
                //���ʌ�t�Ǘ��e�[�u��Params.���X�R�[�h = �����@@��t�{���A�b�v���[�hCSV.get���X�R�[�h()�̖߂�l
                l_docDeliveryManagementParams.setBranchCode(l_fPTUploadCsv.getBranchCode(i));
                //���ʌ�t�Ǘ��e�[�u��Params.�ڋq�R�[�h = �ڋq.getAccountCode()�̖߂�l
                l_docDeliveryManagementParams.setAccountCode(l_gentradeMainAccount.getAccountCode());
                //���ʌ�t�Ǘ��e�[�u��Params.���ʋ敪 = "010"
                l_docDeliveryManagementParams.setDocumentDiv(WEB3CategCodeDef.DOCUMENT_DELIVERY);
                //���ʌ�t�Ǘ��e�[�u��Params.�����R�[�h = �����@@��t�{���A�b�v���[�hCSV.get�d�q�������R�[�h()�̖߂�l
                l_docDeliveryManagementParams.setProductCode(l_strBatoProductCode);
                //���ʌ�t�Ǘ��e�[�u��Params.���ʌ�t�� = �����@@��t�{���A�b�v���[�hCSV.get���ʌ�t��()�̖߂�l
                l_docDeliveryManagementParams.setDeliveryDate(
                    WEB3DateUtility.getDate(l_strDeliveryDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD));
                //���ʌ�t�Ǘ��e�[�u��Params.�폜�t���O = "0"
                l_docDeliveryManagementParams.setDeleteFlag(BooleanEnum.FALSE);
                //���ʌ�t�Ǘ��e�[�u��Params.�X�V�҃R�[�h = �Ǘ���.get�Ǘ��҃R�[�h()�̖߂�l
                l_docDeliveryManagementParams.setLastUpdater(l_administrator.getAdministratorCode());
                //���ʌ�t�Ǘ��e�[�u��Params.�쐬���t = ���ݓ���
                l_docDeliveryManagementParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                //���ʌ�t�Ǘ��e�[�u��Params.�X�V���t = ���ݓ���
                l_docDeliveryManagementParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                //���ʌ�t�Ǘ��e�[�u��Params.���ʎ�ރR�[�h = �����@@��t�{���A�b�v���[�hCSV.get�d�q�������R�[�h()�̖߂�l���R��
                l_docDeliveryManagementParams.setDocumentCategory(l_strBatoProductCode.substring(0, 3));
                //���ʌ�t�Ǘ��e�[�u��Params.�݂Ȃ���t�� = null
                l_docDeliveryManagementParams.setDeemedDeliveryDate(null);
                //insert���ʌ�t�Ǘ��e�[�u��(���ʌ�t�Ǘ��e�[�u��Params)
                //(*)�v���p�e�B�Z�b�g�ō쐬�������ʌ�t�Ǘ��e�[�u��Params
                l_adminFPTDocDeliveryManagement.insertDocDeliveryManagementParams(l_docDeliveryManagementParams);
            }

            //�폜�A�b�v���[�h�����i���N�G�X�g�f�[�^.�����敪=="1"�j�̏ꍇ
            if (WEB3FPTStatusDivDef.DELETE.equals(l_request.statusDiv))
            {
                //�폜�s�����݂��Ȃ��ꍇ�i���ʌ�t�Ǘ�.get���ʌ�t�Ǘ��s() == null�j�A��O���X���[����B
                if (l_docDeliveryManagementRow == null)
                {
                    log.debug("�폜�s�����݂��Ȃ��ꍇ�A�폜�A�b�v���[�h�����s�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02963,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        (i + 1) + "," + l_fPTUploadCsv.getBranchCode(i) + ","
                        + l_fPTUploadCsv.getAccountCode(i) + ","
                        + l_strBatoProductCode + ","
                        + l_fPTUploadCsv.getDeliveryDate(i));
                }

                //(*1.3)�v���p�e�B�Z�b�g
                //���ʌ�t�Ǘ�����Params�𐶐����A
                //get���ʌ�t�Ǘ��ꗗ()�̖߂�l�i�ȉ����ʌ�t�Ǘ��s�j��ݒ肷��B
                DocDeliveryManagementHistParams l_docDeliveryManagementHistParams =
                    new DocDeliveryManagementHistParams();
                //���ʌ�t�Ǘ������e�[�u��Params.����ID = ���ʌ�t�Ǘ��s.get����ID()
                l_docDeliveryManagementHistParams.setAccountId(l_docDeliveryManagementRow.getAccountId());
                //���ʌ�t�Ǘ������e�[�u��Params.�،���ЃR�[�h = ���ʌ�t�Ǘ��s.get�،���ЃR�[�h()
                l_docDeliveryManagementHistParams.setInstitutionCode(l_docDeliveryManagementRow.getInstitutionCode());
                //���ʌ�t�Ǘ������e�[�u��Params.���X�R�[�h = ���ʌ�t�Ǘ��s.get���X�R�[�h()
                l_docDeliveryManagementHistParams.setBranchCode(l_docDeliveryManagementRow.getBranchCode());
                //���ʌ�t�Ǘ������e�[�u��Params.�ڋq�R�[�h = ���ʌ�t�Ǘ��s.get�ڋq�R�[�h()
                l_docDeliveryManagementHistParams.setAccountCode(l_docDeliveryManagementRow.getAccountCode());
                //���ʌ�t�Ǘ������e�[�u��Params.���ʋ敪 = ���ʌ�t�Ǘ��s.get���ʋ敪()
                l_docDeliveryManagementHistParams.setDocumentDiv(l_docDeliveryManagementRow.getDocumentDiv());
                //���ʌ�t�Ǘ������e�[�u��Params.�����R�[�h = ���ʌ�t�Ǘ��s.get�����R�[�h()
                l_docDeliveryManagementHistParams.setProductCode(l_docDeliveryManagementRow.getProductCode());
                //���ʌ�t�Ǘ������e�[�u��Params.���ʌ�t�� = ���ʌ�t�Ǘ��s.get���ʌ�t��()
                l_docDeliveryManagementHistParams.setDeliveryDate(l_docDeliveryManagementRow.getDeliveryDate());
                //���ʌ�t�Ǘ������e�[�u��Params.�폜�t���O = ���ʌ�t�Ǘ��s.get�폜�t���O()
                l_docDeliveryManagementHistParams.setDeleteFlag(l_docDeliveryManagementRow.getDeleteFlag());
                //���ʌ�t�Ǘ������e�[�u��Params.�X�V�҃R�[�h = ���ʌ�t�Ǘ��s.get�X�V�҃R�[�h()
                l_docDeliveryManagementHistParams.setLastUpdater(l_docDeliveryManagementRow.getLastUpdater());
                //���ʌ�t�Ǘ������e�[�u��Params.�쐬���t = ���ʌ�t�Ǘ��s.get�쐬���t()
                l_docDeliveryManagementHistParams.setCreatedTimestamp(l_docDeliveryManagementRow.getCreatedTimestamp());
                //���ʌ�t�Ǘ������e�[�u��Params.�X�V���t = ���ʌ�t�Ǘ��s.get�X�V���t()
                l_docDeliveryManagementHistParams.setLastUpdatedTimestamp(
                    l_docDeliveryManagementRow.getLastUpdatedTimestamp());
                //���ʌ�t�Ǘ������e�[�u��Params.�폜�� = �Ǘ���.get�Ǘ��҃R�[�h()
                l_docDeliveryManagementHistParams.setDeleteUser(l_administrator.getAdministratorCode());
                //���ʌ�t�Ǘ������e�[�u��Params.�폜���� = ���ݓ���
                l_docDeliveryManagementHistParams.setDeleteTimestamp(GtlUtils.getSystemTimestamp());
                //���ʌ�t�Ǘ������e�[�u��Params.���ʎ�ރR�[�h = ���ʌ�t�Ǘ��s.get���ʎ�ރR�[�h()
                l_docDeliveryManagementHistParams.setDocumentCategory(
                    l_docDeliveryManagementRow.getDocumentCategory());
                //���ʌ�t�Ǘ������e�[�u��Params.�݂Ȃ���t�� = ���ʌ�t�Ǘ��s.get�݂Ȃ���t��()
                l_docDeliveryManagementHistParams.setDeemedDeliveryDate(
                    l_docDeliveryManagementRow.getDeemedDeliveryDate());

                //���ʌ�t�Ǘ�����( )
                WEB3AdminFPTDocDeliveryManagementHistory l_adminFPTDocDeliveryManagementHistory =
                    new WEB3AdminFPTDocDeliveryManagementHistory();

                //insert���ʌ�t�Ǘ������e�[�u��(���ʌ�t�Ǘ������e�[�u��Params)
                l_adminFPTDocDeliveryManagementHistory.insertDocDeliveryManagementHist(
                    l_docDeliveryManagementHistParams);

                //delete���ʌ�t�Ǘ�(long, String, String, Date, String)
                //����ID�F get���ʌ�t�Ǘ��ꗗ()�̖߂�l.get����ID()
                //���ʋ敪�Fget���ʌ�t�Ǘ��ꗗ()�̖߂�l.get���ʋ敪()
                //�����R�[�h�F get���ʌ�t�Ǘ��ꗗ()�̖߂�l.get�����R�[�h()
                //���ʌ�t���F get���ʌ�t�Ǘ��ꗗ()�̖߂�l.get���ʌ�t��()
                //���ʎ�ރR�[�h�F get���ʌ�t�Ǘ��ꗗ()�̖߂�l.get���ʎ�ރR�[�h()�̖߂�l
                l_adminFPTDocDeliveryManagement.deleteDocDivManagement(
                    l_docDeliveryManagementRow.getAccountId(),
                    l_docDeliveryManagementRow.getDocumentDiv(),
                    l_docDeliveryManagementRow.getProductCode(),
                    l_docDeliveryManagementRow.getDeliveryDate(),
                    l_docDeliveryManagementRow.getDocumentCategory());
            }
        }

        //save�A�b�v���[�h�I��( )
        l_fPTUploadCsv.saveUploadEnd();

        //delete�A�b�v���[�hTemp( )
        l_fPTUploadCsv.deleteUploadTemp();

        //createResponse( )
        WEB3AdminFPTUploadCompleteResponse l_response =
            (WEB3AdminFPTUploadCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (undo�A�b�v���[�h�t�@@�C��)<BR>
     * �����@@��t�{���A�b�v���[�h���~��ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uundo�A�b�v���[�h�t�@@�C���Q�ƁB<BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminFPTUploadCancelResponse
     * @@throws WEB3BaseException
     * @@roseuid 4737F62A0233
     */
    protected WEB3AdminFPTUploadCancelResponse undoUploadFile(
        WEB3AdminFPTUploadCancelRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "undoUploadFile(WEB3AdminFPTUploadCancelRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //�����@@��t�{���A�b�v���[�hCSV(long)
        //�A�b�v���[�h�h�c�F�@@���N�G�X�g�f�[�^.�A�b�v���[�h�h�c
        WEB3FPTUploadCsv l_fPTUploadCsv = new WEB3FPTUploadCsv(Long.parseLong(l_request.uploadId));

        //delete�A�b�v���[�hTemp( )
        l_fPTUploadCsv.deleteUploadTemp();

        //update�A�b�v���[�h���~( )
        l_fPTUploadCsv.updateUploadCancel();

        //createResponse( )
        WEB3AdminFPTUploadCancelResponse l_response =
            (WEB3AdminFPTUploadCancelResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
