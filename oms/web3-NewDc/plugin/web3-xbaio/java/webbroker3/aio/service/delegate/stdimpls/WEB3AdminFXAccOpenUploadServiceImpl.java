head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.31.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccOpenUploadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���FX�����J�݃A�b�v���[�h�T�[�r�XImpl(WEB3AdminFXAccOpenUploadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17 �A����(���u) �V�K�쐬
                 : 2006/03/01 ���iSRA�j �����QU02776�Ή�
                 : 2006/03/09 ���iSRA�j �G���[�����ꗗ�̃V���{�����ύX�Ή�
Revesion History : 2008/09/08 ���m�a (���u) �d�l�ύX�E���f��960~���f��964
Revesion History : 2009/03/21 �Ԑi (���u) �d�l�ύX�E���f��1132,�c�a�X�V�d�l215
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;

import webbroker3.aio.WEB3AdminFXAccOpenUploadCsv;
import webbroker3.aio.WEB3FXDataControlService;
import webbroker3.aio.data.GftAccountOpenStatusParams;
import webbroker3.aio.message.WEB3AdminFXAccOpenUploadCancelRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenUploadCancelResponse;
import webbroker3.aio.message.WEB3AdminFXAccOpenUploadCompleteRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenUploadCompleteResponse;
import webbroker3.aio.message.WEB3AdminFXAccOpenUploadConfirmRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenUploadConfirmResponse;
import webbroker3.aio.message.WEB3AdminFXAccOpenUploadInputRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenUploadInputResponse;
import webbroker3.aio.message.WEB3FXAccInformationUnit;
import webbroker3.aio.message.WEB3FXUploadHistoryUnit;
import webbroker3.aio.service.delegate.WEB3AdminFXAccOpenUploadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountOpenDef;
import webbroker3.common.define.WEB3AccountOpenStatusDivDef;
import webbroker3.common.define.WEB3AccTypeDef;
import webbroker3.common.define.WEB3GftTransStatusCourseDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccOpenDiv;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.aio.define.WEB3UploadStateDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ���FX�����J�݃A�b�v���[�h�T�[�r�XImpl)<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public class WEB3AdminFXAccOpenUploadServiceImpl implements WEB3AdminFXAccOpenUploadService 
{
    
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccOpenUploadServiceImpl.class);
    
    /**
     * @@roseuid 43F49A6D03A9
     */
    public WEB3AdminFXAccOpenUploadServiceImpl() 
    {
     
    }
    
    /**
     * �Ǘ���FX�����J�݃A�b�v���[�h���������{����B <BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɑΉ����郁�\�b�h���R�[������B <BR>
     * <BR>
     * �|get�A�b�v���[�h���() <BR>
     * �|validate�A�b�v���[�h�t�@@�C��() <BR>
     * �|submit�A�b�v���[�h() <BR>
     * �|undo�A�b�v���[�h() <BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 43E04257004B
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
        if (l_request instanceof WEB3AdminFXAccOpenUploadInputRequest)
        {
            l_response = this.getUploadScreen((WEB3AdminFXAccOpenUploadInputRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminFXAccOpenUploadConfirmRequest)
        {
            l_response = this.validateUploadFile((WEB3AdminFXAccOpenUploadConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminFXAccOpenUploadCompleteRequest)
        {
            l_response = this.submitUpload((WEB3AdminFXAccOpenUploadCompleteRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminFXAccOpenUploadCancelRequest)
        {
            l_response = this.undoUpload((WEB3AdminFXAccOpenUploadCancelRequest) l_request);
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
     * (get�A�b�v���[�h���)<BR>
     * �A�b�v���[�h��ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u(�Ǘ���FX�����J��UL�T�[�r�X)get�A�b�v���[�h��ʁv �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3AdminFXAccOpenUploadInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 43E0425903B6
     */
    protected WEB3AdminFXAccOpenUploadInputResponse getUploadScreen(WEB3AdminFXAccOpenUploadInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getUploadScreen(WEB3AdminFXAccOpenUploadInputRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FX_ACCOUNT_MANAGE, true);
        
        //1.3 validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.4 �Ǘ���FX�����J�݃A�b�v���[�hCSV( )
        WEB3AdminFXAccOpenUploadCsv l_fxAccOpenUploadCsv = new WEB3AdminFXAccOpenUploadCsv();
        
        //1.5 get�ŐV�A�b�v���[�h����(�f�[�^�L�[ : long)
        AdministratorUploadRow l_row = 
            l_fxAccOpenUploadCsv.getLatestUploadAction(l_admin.getInstitution().getInstitutionId());
        
        //1.6 �ŐV�A�b�v���[�h���������݂���ꍇ�iget�ŐV�A�b�v���[�h����() != null�j
        WEB3FXUploadHistoryUnit l_unit = null;
        if (l_row != null)
        {
            //1.6.1 FX�A�b�v���[�h���𖾍�( )
            l_unit = new WEB3FXUploadHistoryUnit();
            
            //(*)FX�A�b�v���[�h���𖾍׃v���p�e�B�Ɉȉ��̒ʂ�A�l���Z�b�g����B
            //�A�b�v���[�h������ԋ敪�F
            //�@@�i�A�b�v���[�h�s.�A�b�v���[�h�I������ == null�̏ꍇ�j�@@�h�A�b�v���[�h���h
            //�@@�i�A�b�v���[�h�s.�A�b�v���[�h�I������ != null�̏ꍇ�j�@@�h�A�b�v���[�h�ρh
            if (l_row.getUploadEndTimestamp() == null)
            {
                l_unit.uploadStateDiv = WEB3UploadStateDef.UPLOADING;
            }
            else
            {
                l_unit.uploadStateDiv = WEB3UploadStateDef.UPLOAD_COMPLETE;
            }
            
            //�A�b�v���[�h�����F�@@�A�b�v���[�h�s.�A�b�v���[�h����
            l_unit.uploadNumber = String.valueOf(l_row.getUploadCount());
            
            //�A�b�v���[�h�J�n�����F�@@�A�b�v���[�h�s.�A�b�v���[�h�J�n����
            l_unit.uploadStartDate = l_row.getUploadStartTimestamp();
            
            //�A�b�v���[�h�I�������F�@@�A�b�v���[�h�s.�A�b�v���[�h�I������
            l_unit.uploadEndDate = l_row.getUploadEndTimestamp();
            
            // FX�G���[�ԍ��F�@@�A�b�v���[�h�s.���b�Z�[�W�R�[�h
            l_unit.fxErrorId = l_row.getMessageCode();
        }
        
        //1.7 createResponse( )
        WEB3AdminFXAccOpenUploadInputResponse l_response = 
            (WEB3AdminFXAccOpenUploadInputResponse) l_request.createResponse();
        
        //1.8 (*)�v���p�e�B�Z�b�g
        //(*)���X�|���X�f�[�^�v���p�e�B�Ɉȉ��̒ʂ�A�l���Z�b�g����B
        //FX�A�b�v���[�h���𖾍ׁF�@@
        //�ŐV�A�b�v���[�h���������݂���ꍇ�AFX�A�b�v���[�h���𖾍׃I�u�W�F�N�g�B
        //�ŐV�A�b�v���[�h���������݂��Ȃ��ꍇ�Anull�B
        l_response.uploadHistoryUnit = l_unit;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�A�b�v���[�h�t�@@�C��)<BR>
     * �A�b�v���[�h�t�@@�C���m�F�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u(�Ǘ���FX�����J��UL�T�[�r�X)validate�A�b�v���[�h�t�@@�C���v �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3AdminFXAccOpenUploadConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 43E04268007A
     */
    protected WEB3AdminFXAccOpenUploadConfirmResponse validateUploadFile(
        WEB3AdminFXAccOpenUploadConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateUploadFile(WEB3AdminFXAccOpenUploadConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FX_ACCOUNT_MANAGE, true);
        
        //1.4 validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.5 �Ǘ���FX�����J�݃A�b�v���[�hCSV( )
        WEB3AdminFXAccOpenUploadCsv l_fxAccOpenUploadCsv = new WEB3AdminFXAccOpenUploadCsv();
        
        //1.6 validate�����A�b�v���[�h(�A�b�v���[�h�h�c : long)
        l_fxAccOpenUploadCsv.validateSameTimeUpload(null);
        
        //1.7 save�A�b�v���[�h�J�n(�f�[�^�L�[ : long, 
        //���l�P : String, ���l�Q : String, 
        //���l�R : String, �X�V�҃R�[�h : String)
        l_fxAccOpenUploadCsv.saveUpLoadStart(
            l_admin.getInstitution().getInstitutionId(), 
            null, 
            null, 
            null, 
            l_admin.getAdministratorCode());
        
        //1.8 ArrayList( )
        ArrayList l_lisDuplicateAccounts = new ArrayList();
        
        //1.9 ArrayList( )
        ArrayList l_lisErrorAccounts = new ArrayList();
        
        //1.10 ���N�G�X�g�f�[�^.�A�b�v���[�h�t�@@�C��[]�̊e�v�f����Loop���� 
        int l_uploadFilelength = l_request.uploadFile.length; 
        for (int i = 0; i < l_uploadFilelength; i++)
        {
            //1.10.1 add���׍s(���׍s������ : String)
            int l_intLineNumber = 0;
            try
            {
                l_intLineNumber = l_fxAccOpenUploadCsv.addRow(l_request.uploadFile[i]);
            }
            //1.10.2 add���׍s()����O���X���[�����ꍇ
            catch (WEB3SystemLayerException l_ex)
            {
                //1.10.2.1 save�A�b�v���[�h�G���[(�G���[��� : ErrorInfo)
                l_fxAccOpenUploadCsv.saveUploadError(l_ex.getErrorInfo());
                
                //1.10.2.2 catch������O����ʂɍăX���[���� 
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    l_ex.getErrorInfo(), 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    String.valueOf(i + 1), 
                    l_ex);
            }
            
            //1.10.3 ��s�̏ꍇ�iadd���׍s()�̖߂�l�j < 0�j�A
            //���Y�v�f�̏����𒆒f�icontinue;�j
            if (l_intLineNumber < 0)
            {
                continue;
            }
            
            //1.10.4 validate���׍s(int)
            try
            {
                l_fxAccOpenUploadCsv.validateDetailsLine(l_intLineNumber);
            }
            //1.10.5 validate���׍s()����O���X���[�����ꍇ
            catch (WEB3BaseException l_ex)
            {
                //1.10.5.1 get���p�҃R�[�h(int)
                String l_strUserCode = l_fxAccOpenUploadCsv.getUserCode(l_intLineNumber);
                
                //1.10.5.2 get���l(int)
                String l_strRemark = l_fxAccOpenUploadCsv.getRemark(l_intLineNumber);
                
                //1.10.5.3 save�A�b�v���[�h�G���[(�G���[��� : ErrorInfo)
                l_fxAccOpenUploadCsv.saveUploadError(l_ex.getErrorInfo());
                
                //1.10.5.4 catch������O����ʂɍăX���[����
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_ex.getErrorInfo(), 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_strUserCode + "," + l_strRemark, 
                    l_ex);
            }
            
            try
            {
                //1.10.6 validate�d���s(int)
                l_fxAccOpenUploadCsv.validateDuplicateLine(l_intLineNumber);
            }
            //1.10.7 validate�d���s()����O���X���[�����ꍇ�A�ǉ��������׍s���폜���A
            //���Y�v�f�̏����𒆒f�icontinue;�j
            catch (WEB3BaseException l_ex)
            {
                //1.10.7.1 get���p�҃R�[�h(int)
                String l_strUserCode = l_fxAccOpenUploadCsv.getUserCode(l_intLineNumber);
                
                //1.10.9.2 add(arg0 : Object)
                l_lisDuplicateAccounts.add(l_strUserCode);
                
                //1.10.7.3 delete���׍s(�s�ԍ� : int)
                l_fxAccOpenUploadCsv.deleteRow(l_intLineNumber);
                
                continue;
            }
         
            try
            {
                //1.10.8 getGFT�����J�ݏ�(int)
                l_fxAccOpenUploadCsv.getGFTAccountOpenStatus(l_intLineNumber);
            }
            //1.10.9 getGFT�����J�ݏ�()����O���X���[�����ꍇ�A�ǉ��������׍s���폜���A
            //���Y�v�f�̏����𒆒f�icontinue;�j
            catch (WEB3BaseException l_ex)
            {
                //1.10.9.1 get���p�҃R�[�h(int)
                String l_strUserCode = l_fxAccOpenUploadCsv.getUserCode(l_intLineNumber);
                
                //1.10.9.2 add(arg0 : Object)
                l_lisErrorAccounts.add(l_strUserCode);
                
                //1.10.9.3 delete���׍s(�s�ԍ� : int)
                l_fxAccOpenUploadCsv.deleteRow(l_intLineNumber);
                
                continue;
            }
        }
        
        //1.11 get���׍s��( )
        int l_intRowCount = l_fxAccOpenUploadCsv.getRowCount();
        
        //1.12 get�A�b�v���[�h�h�c( )
        long l_lngUploadId = l_fxAccOpenUploadCsv.getAdministratorUploadId();
                    
        //1.13 save�A�b�v���[�hTemp( )
        l_fxAccOpenUploadCsv.saveUploadTemp();
        
        //1.14 createResponse( )
        WEB3AdminFXAccOpenUploadConfirmResponse l_response = 
            (WEB3AdminFXAccOpenUploadConfirmResponse) l_request.createResponse();
        
        //1.15 (*)�v���p�e�B�Z�b�g
        //(*)���X�|���X�f�[�^�v���p�e�B�ɁA�ȉ��̒ʂ�l���Z�b�g����B
        //�A�b�v���[�h�����F�@@get���׍s��()�̖߂�l
        l_response.uploadNumber = String.valueOf(l_intRowCount);
        
        //�A�b�v���[�hID�F�@@get�A�b�v���[�hID()�̖߂�l
        l_response.uploadId = String.valueOf(l_lngUploadId);
        
        //�d�������ꗗ�F�@@�d������List��String�^�̔z��
        String[] l_strDuplicateAccounts = new String[l_lisDuplicateAccounts.size()];
        l_lisDuplicateAccounts.toArray(l_strDuplicateAccounts);
        l_response.duplicateAccountList = l_strDuplicateAccounts;
        
        //�G���[�����ꗗ�F�@@�G���[����List��String�^�̔z��
        String[] l_strErrorAccounts = new String[l_lisErrorAccounts.size()];
        l_lisErrorAccounts.toArray(l_strErrorAccounts);                                       
        l_response.errorAccountList = l_strErrorAccounts;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit�A�b�v���[�h)<BR>
     * �A�b�v���[�h�����������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u(�Ǘ���FX�����J��UL�T�[�r�X)submit�A�b�v���[�h�v �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3AdminFXAccOpenUploadCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 43E04274002C
     */
    protected WEB3AdminFXAccOpenUploadCompleteResponse submitUpload(WEB3AdminFXAccOpenUploadCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitUpload(WEB3AdminFXAccOpenUploadCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FX_ACCOUNT_MANAGE, true);
        
        //1.4 validate����p�X���[�h(�p�X���[�h : String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.5 validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.6 �Ǘ���FX�����J�݃A�b�v���[�hCSV( )
        WEB3AdminFXAccOpenUploadCsv l_fxAccOpenUploadCsv = new WEB3AdminFXAccOpenUploadCsv();
        
        //1.7 validate�����A�b�v���[�h(�A�b�v���[�h�h�c : long)
        
        l_fxAccOpenUploadCsv.validateSameTimeUpload(Long.valueOf(l_request.uploadId));
        
        //1.8 setDataFrom�A�b�v���[�hTemp(�A�b�v���[�h�h�c : long)
        l_fxAccOpenUploadCsv.setDataFromUploadTemp(Long.parseLong(l_request.uploadId));
        
        //1.9 get���׍s��( )
        int l_intRowCount = l_fxAccOpenUploadCsv.getRowCount();
        
        //1.10 FX�������ꗗ�̍쐬
        WEB3FXAccInformationUnit[] l_units = new WEB3FXAccInformationUnit[2];
        l_units[0] = new WEB3FXAccInformationUnit();
        l_units[0].fxCourseDiv = WEB3GftTransStatusCourseDivDef.ONE_COSE;
        l_units[0].fxAccountCode = "111111";
        l_units[1] = new WEB3FXAccInformationUnit();
        l_units[1].fxCourseDiv = WEB3GftTransStatusCourseDivDef.TEN_COSE;
        l_units[1].fxAccountCode = "222222";
        
        //1.11 ArrayList( )
        ArrayList l_lisErrorAccounts = new ArrayList();
        
        //1.12 ���׍s�̐���Loop����
        for (int i = 0; i < l_intRowCount; i++)
        {
            //1.12.1 FX�����J��1������
            try
            {
                //1.12.1.1 getGFT�����J�ݏ�(int)
                GftAccountOpenStatusParams l_params = l_fxAccOpenUploadCsv.getGFTAccountOpenStatus(i);
                
                //1.12.1.2 insertFX�ڋq(
                //GFT�����J�ݏ�Params : GFT�����J�ݏ�Params, 
                //�X�V�҃R�[�h : String)
                String l_strAdminCode = l_admin.getAdministratorCode();
                WEB3FXDataControlService l_fxDataControlService = 
                    (WEB3FXDataControlService) Services.getService(WEB3FXDataControlService.class);
                l_fxDataControlService.insertFXAccount(l_params, l_strAdminCode);
                
                //1.12.1.3 FX�������ꗗ�̗v�f����Loop����
                for (int j = 0; j < l_units.length; j++)
                {
                    //1.12.1.3.1 insertFX�����ԍ�(
                    //GFT�����J�ݏ�Params : GFT�����J�ݏ�Params, 
                    //FX������� : FX�������, �X�V�҃R�[�h : String)
                    l_fxDataControlService.insertFXAccountCode(l_params, l_units[j], l_strAdminCode);
                }
                
                //1.12.1.4 get�ڋq(�،���ЃR�[�h : String, 
                //���X�R�[�h : String, �����R�[�h : String)
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class); 
                WEB3GentradeAccountManager l_AccountManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
                String l_strInstitutionCode = l_params.getInstitutionCode();
                String l_strBranceCode = l_params.getBranchCode();
                String l_strAccountCode = l_params.getAccountCode();
                MainAccount l_mainAccount = l_AccountManager.getMainAccount(
                    l_strInstitutionCode, 
                    l_strBranceCode, 
                    l_strAccountCode);

                //updateFX�����J�݋敪(String, String, String, String, String)
                //[����]
                //�،���ЃR�[�h�F�@@getGFT�����J�ݏ�()�̖߂�l.get�،���ЃR�[�h()
                //���X�R�[�h�F�@@getGFT�����J�ݏ�()�̖߂�l.get���X�R�[�h()
                //�ڋq�R�[�h�F�@@getGFT�����J�ݏ�()�̖߂�l.get�ڋq�R�[�h()
                //FX�����J�݋敪�F�@@"�����J��"
                //�X�V�҃R�[�h�F�@@�Ǘ��҃I�u�W�F�N�g.get�Ǘ��҃R�[�h()
                l_fxDataControlService.updateFXAccountOpenDiv(
                    l_strInstitutionCode,
                    l_strBranceCode,
                    l_strAccountCode,
                    WEB3AccountOpenDef.OPEN,
                    l_strAdminCode);

                //insert�����J�݋敪(����ID : long, ������� : String,
                //  �����J�݋敪 : String, �X�V�҃R�[�h : String)
                //�����J�݋敪�e�[�u��(acc_open_div)�ɍs��insert���s��
                //[����]
                //����ID�F�ڋq�}�X�^�e�[�u�����A�����̏،���ЃR�[�h�A
                //        ���X�R�[�h�A�����R�[�h�ɊY���������ID���擾���ԋp����B
                //������ʁF"01�FFX"
                //�����J�݋敪�F"1:�J�ݍ�"
                //�X�V�҃R�[�h�F�Ǘ��҃I�u�W�F�N�g.get�Ǘ��҃R�[�h()
                long l_lngAccountId = l_mainAccount.getAccountId();
                WEB3GentradeAccOpenDiv l_genAccOpenDiv = new WEB3GentradeAccOpenDiv();
                l_genAccOpenDiv.insertAccOpenDiv(
                    l_lngAccountId,
                    WEB3AccTypeDef.FX,
                    WEB3AccountOpenDef.OPEN,
                    l_strAdminCode);

                //1.12.1.6 updateGFT�����J�ݏ�(GFT�����J�ݏ�Params : GFT�����J�ݏ�Params, 
                //�X�V��X�e�[�^�X�敪 : String, �X�V��FX�������ꗗ : FX�������[], 
                //�X�V�҃R�[�h : String, �X�V�������敪 : String)
                l_fxDataControlService.updateGFTAccountOpenStatus(
                    l_params, 
                    WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_COMPLETE, 
                    l_units, 
                    l_strAdminCode, 
                    null);
            }
            //1.12.2 FX�����J��1�������ŗ�O���X���[���ꂽ�ꍇ�A���Y���׍s���폜���A
            //�����𒆒f(continue;)
            catch (WEB3BaseException l_ex)
            {
                //1.12.2.1 get���p�҃R�[�h(int)
                String l_strUserCode = l_fxAccOpenUploadCsv.getUserCode(i);
                
                //1.12.2.2 add(arg0 : Object)
                l_lisErrorAccounts.add(l_strUserCode);
                
                //1.12.2.3 delete���׍s(�s�ԍ� : int)
                l_fxAccOpenUploadCsv.deleteRow(i);
                
                //1.12.2.4 ���[�v��index�ƃ��[�v���i���׍s���j���P�}�C�i�X����B
                i--;    
                l_intRowCount--;
                continue;
            }
        }
        
        //1.13 get���׍s��( )
        int l_intRowNumber = l_fxAccOpenUploadCsv.getRowCount();
        
        //1.14 save�A�b�v���[�h�I��( )
        l_fxAccOpenUploadCsv.saveUploadEnd();
                    
        //1.15 save�A�b�v���[�hTemp( )
        l_fxAccOpenUploadCsv.deleteUploadTemp();

        //1.16 createResponse( ) 
        WEB3AdminFXAccOpenUploadCompleteResponse l_response = 
            (WEB3AdminFXAccOpenUploadCompleteResponse) l_request.createResponse();
        
        //1.17 (*)���X�|���X�f�[�^�v���p�e�B�ɁA�ȉ��̒ʂ�l���Z�b�g����B
        //�A�b�v���[�h�����F�@@get���׍s��()�̖߂�l
        l_response.uploadNumber = String.valueOf(l_intRowNumber);
        
        //�G���[�����ꗗ�F�@@�G���[����List��String�^�̔z��
        String[] l_strErrorAccounts = new String[l_lisErrorAccounts.size()];
        l_lisErrorAccounts.toArray(l_strErrorAccounts);
        l_response.errorAccountList = l_strErrorAccounts;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (undo�A�b�v���[�h)<BR>
     * �A�b�v���[�h���~�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u(�Ǘ���FX�����J��UL�T�[�r�X)undo�A�b�v���[�h�v �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3AdminFXAccOpenUploadCancelResponse
     * @@throws WEB3BaseException
     * @@roseuid 43E0428100E8
     */
    protected WEB3AdminFXAccOpenUploadCancelResponse undoUpload(WEB3AdminFXAccOpenUploadCancelRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " undoUpload(WEB3AdminFXAccOpenUploadCancelRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 �Ǘ���FX�����J�݃A�b�v���[�hCSV(long)
        WEB3AdminFXAccOpenUploadCsv l_fxAccOpenUploadCsv = 
            new WEB3AdminFXAccOpenUploadCsv(Long.parseLong(l_request.uploadId));
        
        //1.3 delete�A�b�v���[�hTemp( )
        l_fxAccOpenUploadCsv.deleteUploadTemp();
        
        //1.4 save�A�b�v���[�h���~( )
        l_fxAccOpenUploadCsv.saveUploadStop();
        
        //1.5 createResponse( )
        log.exiting(STR_METHOD_NAME);
        return (WEB3AdminFXAccOpenUploadCancelResponse) l_request.createResponse();
    }
}
@
