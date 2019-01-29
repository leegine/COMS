head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.51.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointPackageAdjustServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �|�C���g�ꊇ�����T�[�r�XImpl(WEB3AdminPointPackageAdjustServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.point.WEB3AdminPointPackageAdjustUploadCsv;
import webbroker3.point.WEB3PointAdjust;
import webbroker3.point.WEB3PointApplyManager;
import webbroker3.point.define.WEB3UploadStateDef;
import webbroker3.point.message.WEB3AdminPointUploadCancelRequest;
import webbroker3.point.message.WEB3AdminPointUploadCancelResponse;
import webbroker3.point.message.WEB3AdminPointUploadCompleteRequest;
import webbroker3.point.message.WEB3AdminPointUploadCompleteResponse;
import webbroker3.point.message.WEB3AdminPointUploadConfirmRequest;
import webbroker3.point.message.WEB3AdminPointUploadConfirmResponse;
import webbroker3.point.message.WEB3AdminPointUploadInputRequest;
import webbroker3.point.message.WEB3AdminPointUploadInputResponse;
import webbroker3.point.service.delegate.WEB3AdminPointPackageAdjustService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�|�C���g�ꊇ�����T�[�r�XImpl)<BR>
 * �|�C���g�ꊇ�����T�[�r�X�����N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminPointPackageAdjustServiceImpl implements WEB3AdminPointPackageAdjustService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminPointPackageAdjustServiceImpl.class);

    /**
     * �|�C���g�ꊇ�����T�[�r�X�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * <BR>
     *    get���͉��()<BR>
     *    validate����()<BR>
     *    submit����()<BR>
     *    undo����()<BR>
     * <BR>
     * ��L���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@roseuid 419845DF0105
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest )";
        log.entering(STR_METHOD_NAME);
        
        if (l_request instanceof WEB3AdminPointUploadInputRequest)
        {
            WEB3AdminPointUploadInputResponse l_response = 
                this.getInputScreen((WEB3AdminPointUploadInputRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminPointUploadConfirmRequest)
        {
			WEB3AdminPointUploadConfirmResponse l_response = new WEB3AdminPointUploadConfirmResponse();
			
			try {
				l_response = this.validatePackageAdjust((WEB3AdminPointUploadConfirmRequest)l_request);
			} catch (WEB3BaseException l_e) {
				log.error(STR_METHOD_NAME, l_e);
				log.exiting(STR_METHOD_NAME);
				l_response.errorInfo = l_e.getErrorInfo();
				l_response.errorMessage = l_e.getErrorMessage();
			}
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else if (l_request instanceof WEB3AdminPointUploadCompleteRequest)
        {
            WEB3AdminPointUploadCompleteResponse l_response = 
                this.submitPackageAdjust((WEB3AdminPointUploadCompleteRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else if (l_request instanceof WEB3AdminPointUploadCancelRequest)
        {
            WEB3AdminPointUploadCancelResponse l_response = 
                this.undoPackageAdjust((WEB3AdminPointUploadCancelRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
    }
    
    /**
     * (get���͉��)<BR>
     * ���͉�ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�|�C���g�ꊇ�����jget���͉�ʁv�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointUploadInputResponse
     * @@roseuid 419845DF0134
     */
    protected WEB3AdminPointUploadInputResponse getInputScreen(WEB3AdminPointUploadInputRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminPointUploadInputRequest )";
        log.entering(STR_METHOD_NAME);
        
        //1.1 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        if (l_admin == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.2 validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_PACKAGE_ADJUST, true);
        
        //1.3 validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.4 �|�C���g�ꊇ�����A�b�v���[�hCSV( )
        WEB3AdminPointPackageAdjustUploadCsv l_csv = new WEB3AdminPointPackageAdjustUploadCsv();
        
        //1.5 validate�����A�b�v���[�h(long)
        l_csv.validateSameTimeUpload(null);
        
        //1.6 get�ŐV�A�b�v���[�h����(long)
        AdministratorUploadRow l_row = l_csv.getLatestUploadAction(l_admin.getInstitution().getInstitutionId());
        
        //1.7 createResponse( )
        WEB3AdminPointUploadInputResponse l_response = (WEB3AdminPointUploadInputResponse)l_request.createResponse();
        
        if (l_response == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.8 (*)�v���p�e�B�Z�b�g
        if (l_row != null)
        {
            log.debug("l_row != null");
            
            //�A�b�v���[�h������ԋ敪
            if (l_row.getUploadEndTimestamp() == null)
            {
                log.debug("l_row.getUploadEndTimestamp() == null");
                l_response.uploadState = WEB3UploadStateDef.UPLOADING;
            }
            else
            {
                log.debug("l_row.getUploadEndTimestamp() != null");
                l_response.uploadState = WEB3UploadStateDef.UPLOAD_COMPLETE;
            }
            
            if (!l_row.getUploadCountIsNull())
            {
                //�A�b�v���[�h��������
                l_response.uploadCount = WEB3StringTypeUtility.formatNumber(l_row.getUploadCount());
            }
            
            //�A�b�v���[�h�J�n����
            l_response.uploadStartDate = l_row.getUploadStartTimestamp();
            
            //�A�b�v���[�h�I������
            l_response.uploadEndDate = l_row.getUploadEndTimestamp();
            
            //�A�b�v���[�h�G���[�ԍ�
            l_response.uploadErrorNo = l_row.getMessageCode();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�ꊇ����)<BR>
     * �A�b�v���[�h�f�[�^�̐R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�|�C���g�ꊇ�����jvalidate�ꊇ�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointUploadConfirmResponse
     * @@roseuid 419845DF0154
     */
    protected WEB3AdminPointUploadConfirmResponse validatePackageAdjust(WEB3AdminPointUploadConfirmRequest l_request)
        throws WEB3BaseException 
    {

        final String STR_METHOD_NAME = " validatePackageAdjust(WEB3AdminPointUploadConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        if (l_admin == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.2 validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_PACKAGE_ADJUST, true);
        
        //1.3 validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.4 �|�C���g�ꊇ�����A�b�v���[�hCSV( )
        WEB3AdminPointPackageAdjustUploadCsv l_csv = new WEB3AdminPointPackageAdjustUploadCsv();
        
        //1.5 validate�����A�b�v���[�h(long)
        l_csv.validateSameTimeUpload(null);
        
        //1.6 save�A�b�v���[�h�J�n(long, String, String, String, String)
        l_csv.saveUpLoadStart(l_admin.getInstitution().getInstitutionId(), null, null, null, l_admin.getAdministratorCode());
        
        //1.7 validate���׍s(�|�C���g�ꊇ�����A�b�v���[�hCSV, String[], �Ǘ���)
        this.validateDetailsLine(l_csv, l_request.uploadFile, l_admin);
        
        //1.8 get�A�b�v���[�h�h�c( )
        long l_lngUploadId = l_csv.getAdministratorUploadId();
        
        //1.9 save�A�b�v���[�hTemp( )
        int l_intLineCount  = l_csv.saveUploadTemp();
        
        //1.10 createResponse( )
        WEB3AdminPointUploadConfirmResponse l_response = (WEB3AdminPointUploadConfirmResponse)l_request.createResponse();
        
        if (l_response == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.11 (*)�v���p�e�B�Z�b�g
        l_response.lineCount = WEB3StringTypeUtility.formatNumber(l_intLineCount);
        l_response.uploadId = WEB3StringTypeUtility.formatNumber(l_lngUploadId);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit�ꊇ����)<BR>
     * �A�b�v���[�h�f�[�^�̓o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�|�C���g�ꊇ�����jsubmit�ꊇ�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointUploadCompleteResponse
     * @@roseuid 419845DF0163
     */
    protected WEB3AdminPointUploadCompleteResponse submitPackageAdjust(WEB3AdminPointUploadCompleteRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitPackageAdjust(WEB3AdminPointUploadCompleteRequest )";
        log.entering(STR_METHOD_NAME);
        
        //1.1 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        if (l_admin == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.2 validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_PACKAGE_ADJUST, true);
        
        //1.3 validate����p�X���[�h(String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.4 validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.5 �|�C���g�ꊇ�����A�b�v���[�hCSV( )
        WEB3AdminPointPackageAdjustUploadCsv l_csv = new WEB3AdminPointPackageAdjustUploadCsv(Long.parseLong(l_request.uploadId));
        
        //1.6 validate�����A�b�v���[�h( )
        l_csv.validateSameTimeUpload();
        
        //1.7 setDataFrom�A�b�v���[�hTemp(�A�b�v���[�h�h�c : long)
        l_csv.setDataFromUploadTemp();
        
        //1.8 get���׍s��( )
        int l_intRowCount = l_csv.getRowCount();

        WEB3PointApplyManager l_applyManager = (WEB3PointApplyManager)Services.getService(WEB3PointApplyManager.class);
        if (l_applyManager == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //1.9 (*)���׍s�̐���LOOP����
        for (int i = 0; i < l_intRowCount; i++)
        {
            String l_strBranchCode = null;
            String l_strAccountCode = null;
            int l_intAdjustPoint = 0;
            String l_strAccountCodeInDb = null;
            
            try
            {
                //1.9.1 get���X�R�[�h(int)
                l_strBranchCode = l_csv.getBranchCode(i);
            
                //1.9.2 get�ڋq�R�[�h(�s�ԍ� : int)
                l_strAccountCode = l_csv.getAccountCode(i);
            
                //1.9.3 get�����|�C���g(int)
                l_intAdjustPoint = (int)l_csv.getAdjustPoint(i);
                
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                if (l_finApp == null)
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                WEB3GentradeAccountManager l_accountManager = 
                    (WEB3GentradeAccountManager) l_finApp.getAccountManager();
                if (l_accountManager == null)
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + STR_METHOD_NAME); 
                }
                
                WEB3GentradeMainAccount l_mainAccount = l_accountManager.getMainAccount(
                    l_admin.getInstitutionCode(), l_strBranchCode, l_strAccountCode);//WEB3BaseException
                if (l_mainAccount == null)
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + STR_METHOD_NAME);
                }

                l_strAccountCodeInDb = l_mainAccount.getAccountCode();
            
                //1.9.4 get���p�\�|�C���g
                long l_lngUsePossiblePoint = l_applyManager.getUsePossiblePoint(
                    l_admin.getInstitutionCode(), 
                    l_strBranchCode, 
                    l_strAccountCodeInDb);//WEB3BaseException
            
                //1.9.5 validate�����|�C���g(String, long)
                l_applyManager.validateAdjustPoint(String.valueOf(l_intAdjustPoint), l_lngUsePossiblePoint);//WEB3BaseException
            }
            //1.9.6 (*1)
            catch (WEB3BaseException l_e)
            {
                //1.9.6.1 save�A�b�v���[�h�G���[
                l_csv.saveUploadError(l_e.getErrorInfo());//WEB3SystemLayerException
                                
                String l_strErrorMessage = 
                    l_csv.getBranchCode(i)
                    + "."
                    + l_csv.getAccountCode(i) 
                    + "." 
                    + l_csv.getAdjustPoint(i)
                    + "."
                    + l_e.getErrorMessage(); 
                log.error(l_strErrorMessage, l_e);                
                //1.9.6.2 throw�i��O�j
                throw new WEB3BaseException(
                     l_e.getErrorInfo(),
                     this.getClass().getName() + STR_METHOD_NAME,
                     l_strErrorMessage,
                     l_e);      
            }            

            //1.9.7 �|�C���g����(String, String, String, int)
            WEB3PointAdjust l_adjust = new WEB3PointAdjust(
                l_admin.getInstitutionCode(), 
                l_strBranchCode, 
                l_strAccountCodeInDb,
                l_intAdjustPoint);

            //1.9.8 saveNew����(�|�C���g����, �Ǘ���)
            l_applyManager.saveNewAdjust(l_adjust, l_admin);//WEB3BaseException
        }
        
        //1.10 save�A�b�v���[�h�I��( )
        l_csv.saveUploadEnd();
        
        //1.11 delete�A�b�v���[�hTemp( )
        l_csv.deleteUploadTemp();
        
        //1.12 createResponse( )
        WEB3AdminPointUploadCompleteResponse l_response = (WEB3AdminPointUploadCompleteResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (undo�ꊇ����)<BR>
     * �A�b�v���[�h�̒��~���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�|�C���g�ꊇ�����jundo�ꊇ�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointUploadCancelResponse
     * @@roseuid 419845DF0125
     */
    protected WEB3AdminPointUploadCancelResponse undoPackageAdjust(WEB3AdminPointUploadCancelRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " undoPackageAdjust(WEB3AdminPointUploadCancelRequest )";
        log.entering(STR_METHOD_NAME);

        //1.1 �|�C���g�ꊇ�����A�b�v���[�hCSV(long)
        WEB3AdminPointPackageAdjustUploadCsv l_csv = new WEB3AdminPointPackageAdjustUploadCsv(Long.parseLong(l_request.uploadId));
        
        //1.2 delete�A�b�v���[�hTemp( )
        l_csv.deleteUploadTemp();// throws WEB3SystemLayerException
        
        //1.3 save�A�b�v���[�h���~( )
        l_csv.saveUploadStop();// throws WEB3SystemLayerException
        
        //1.4 createResponse( )
        WEB3AdminPointUploadCancelResponse l_response = (WEB3AdminPointUploadCancelResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate���׍s)<BR>
     * ���׍s�̃`�F�b�N���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�|�C���g�ꊇ�����jvalidate���׍s�v �Q��<BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�|�C���g�ꊇ�����jvalidate���׍s�v): <BR>
     *         1.1.7get�ڋq(String, String, String)<BR>
     *          �ڋq�I�u�W�F�N�g���擾�ł��Ȃ������ꍇ�́A��O<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01035<BR>
     * ==========================================================<BR>
     * @@param l_uploadCsv - (�A�b�v���[�hCSV)<BR>
     * �|�C���g�ꊇ�����A�b�v���[�hCSV�I�u�W�F�N�g<BR>
     * 
     * @@param l_strDetailsLines - (���׍s)<BR>
     * ���׍s<BR>
     * 
     * @@param l_admin - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g<BR>
     * @@roseuid 4199DE2E03A9
     */
    protected void validateDetailsLine(
        WEB3AdminPointPackageAdjustUploadCsv l_uploadCsv, 
        String[] l_strDetailsLines, 
        WEB3Administrator l_admin)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateDetailsLine(WEB3AdminPointPackageAdjustUploadCsv ,String[] ,WEB3Administrator )";
        log.entering(STR_METHOD_NAME);
        
        if (l_uploadCsv == null || l_strDetailsLines == null || l_admin == null)
        {
            log.exiting(STR_METHOD_NAME);
            String l_strErrorMessage = "�A�b�v���[�h�f�[�^null!";
            log.debug(l_strErrorMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME);            
        }
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_finApp == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        if (l_accountManager == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        int l_intLineCount = l_strDetailsLines.length;
        //1.1 (*1)���׍s�e�v�f����Loop����
        for (int i = 0; i < l_intLineCount; i++)
        {
            log.debug("loop count:" + i);
            int l_intNewLineNumber = 0;
            String l_strBranchCode = null;
            String l_strAccountCode = null;
            long l_lngAdjustPoint = 0;
            try
            {
                //1.1.1 add���׍s(String)
                l_intNewLineNumber = l_uploadCsv.addRow(l_strDetailsLines[i]);//WEB3SystemLayerException
            }
            //1.1.2 (*2)
            catch (WEB3SystemLayerException l_e)
            {
                //��O�̒ǉ�������iWEB3BaseException.errorMessage�j��
                //���׍s������i���׍s[index]�j���Z�b�g����B
                String l_strErrorMessage = l_strDetailsLines[i];                               
                
                if (WEB3ErrorCatalog.SYSTEM_ERROR_80023.equals(l_e.getErrorInfo()))
                {
                    //1.1.2.1 save�A�b�v���[�h�G���[(ErrorInfo)
                    l_uploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_01721);
                    
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01721,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_strErrorMessage);
                }

                //1.1.2.1 save�A�b�v���[�h�G���[(ErrorInfo)
                l_uploadCsv.saveUploadError(l_e.getErrorInfo());                
                
                //1.1.2.2 ��O���X���[
                throw new WEB3SystemLayerException(
                     l_e.getErrorInfo(),
                     this.getClass().getName() + STR_METHOD_NAME,
                     l_strErrorMessage);  
            }
            
            try
            {
				//1.1.3.(*) ��s�̏ꍇ�iadd���׍s()�̖߂�l < 0�j�A���Y�v�f�̏����𒆒f�icontinue;�j
				if (l_intNewLineNumber < 0)
				{
					continue;
				}

                //1.1.4 get���X�R�[�h(int)
                l_strBranchCode = l_uploadCsv.getBranchCode(l_intNewLineNumber);
            
				//1.1.5 get�ڋq�R�[�h(int)
				l_strAccountCode = l_uploadCsv.getAccountCode(l_intNewLineNumber);

				//1.1.6 get�����|�C���g(int)
				l_lngAdjustPoint = l_uploadCsv.getAdjustPoint(l_intNewLineNumber);
            
                //1.1.7 validate���X�R�[�h(String)
                l_uploadCsv.validateBranchCode(l_strBranchCode);//WEB3BaseException
            
                //1.1.8 validate�ڋq�R�[�h(String)
                l_uploadCsv.validateAccountCode(l_strAccountCode);//WEB3BaseException
            
                //1.1.9 get�ڋq(String, String, String)                
                WEB3GentradeMainAccount l_mainAccount = null;
                
                try
                {                    
                    l_mainAccount = 
                        l_accountManager.getMainAccount(l_admin.getInstitutionCode(), l_strBranchCode, l_strAccountCode);//WEB3SystemLayerException
                }       
                catch(WEB3SystemLayerException l_ex)
                {
					if (WEB3ErrorCatalog.SYSTEM_ERROR_80005.error_code.equals(l_ex.getErrorInfo().error_code) ||
						WEB3ErrorCatalog.SYSTEM_ERROR_80006.error_code.equals(l_ex.getErrorInfo().error_code))
                    {
                        log.debug(getClass().getName() + STR_METHOD_NAME);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                            getClass().getName() + STR_METHOD_NAME,
                            "�Y������ڋq�����݂��܂���B");
                    }
                    else
                    {
                        log.debug(getClass().getName() + STR_METHOD_NAME);
                        log.exiting(STR_METHOD_NAME);
                        throw l_ex;
                    }
                }
                
                String l_strAccountCodeInDb = l_mainAccount.getAccountCode();
                
                //1.1.10 validate�d���ڋq(int)
                l_uploadCsv.validateDuplicateAccount(l_intNewLineNumber);// WEB3BaseException
            
                //1.1.11 get���p�\�|�C���g(String, String, String)
                WEB3PointApplyManager l_applyManager = (WEB3PointApplyManager)Services.getService(WEB3PointApplyManager.class);
                if (l_applyManager == null)
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                long l_lngUsePossiblePoint = l_applyManager.getUsePossiblePoint(l_admin.getInstitutionCode(), l_strBranchCode, l_strAccountCodeInDb);//WEB3BaseException
            
                //1.1.12 validate�����|�C���g(String, long)
                l_applyManager.validateAdjustPoint(String.valueOf(l_lngAdjustPoint), l_lngUsePossiblePoint);// WEB3BaseException
            }
            //1.1.13 (*3)
            catch (WEB3BaseException l_e)
            {
                log.debug("catch web3baseexception");
                //1.1.12.1 save�A�b�v���[�h�G���[(ErrorInfo)
                l_uploadCsv.saveUploadError(l_e.getErrorInfo());//WEB3SystemLayerException                

                //��O�̒ǉ�������iWEB3BaseException.errorMessage�j��
                //���׍s������i���׍s[index]�j���Z�b�g����B
                String l_strErrorMessage = l_strDetailsLines[i];             
                
				//1.1.12.2 ��O���X���[
				if (l_e instanceof  WEB3SystemLayerException)
				{
					throw new WEB3SystemLayerException(
						 l_e.getErrorInfo(),
						 this.getClass().getName() + STR_METHOD_NAME,
						 l_strErrorMessage);      
				}
				else if ( l_e instanceof WEB3BusinessLayerException)
				{
					throw new WEB3BusinessLayerException(
						 l_e.getErrorInfo(),
						 this.getClass().getName() + STR_METHOD_NAME,
						 l_strErrorMessage);      
				}
            }
        }        
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
