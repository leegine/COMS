head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.10.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoDirectRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃_�C���N�g�w��o�^�T�[�r�XImpl(WEB3AdminPvInfoDirectRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2004/10/27 ������(���u) �쐬
Revesion History : 2004/11/02 ����(���u) �C��
Revesion History : 2007/06/27 ���G��(���u) ���f��078�A�����̖��002
Revesion History : 2009/07/07 ���g(���u) ���f��117,118,119
*/
package webbroker3.pvinfo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.pvinfo.WEB3PvInfoDataManager;
import webbroker3.pvinfo.WEB3PvInfoRegistTargetAccountUploadCsv;
import webbroker3.pvinfo.define.WEB3PvInfoUploadStateDivDef;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistCancelRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistCancelResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistCompleteRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistCompleteResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistConfirmRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistConfirmResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistInputRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistInputResponse;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayContentsUnit;
import webbroker3.pvinfo.message.WEB3PvInfoUploadHistoryUnit;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoDirectRegistService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��҃_�C���N�g�w��o�^�T�[�r�XImpl)<BR>
 * �Ǘ��҃_�C���N�g�w��o�^�T�[�r�X�����N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3AdminPvInfoDirectRegistServiceImpl extends WEB3ClientRequestService implements WEB3AdminPvInfoDirectRegistService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoDirectRegistServiceImpl.class);
        
    /**
     * �_�C���N�g�w��o�^�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * ���Ǘ��ҁE�_�C���N�g�w��o�^���̓��N�G�X�g�̏ꍇ<BR>
     * �@@this.get�_�C���N�g�w��o�^���͉��()���\�b�h���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE�_�C���N�g�w��o�^�m�F���N�G�X�g�̏ꍇ<BR>
     * �@@this.validate�_�C���N�g�w��o�^()���\�b�h���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE�_�C���N�g�w��o�^�������N�G�X�g�̏ꍇ<BR>
     * �@@this.submit�_�C���N�g�w��o�^()���\�b�h���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE�_�C���N�g�w��o�^�A�b�v���[�h���~���N�G�X�g�̏ꍇ<BR>
     * �@@this.undo�_�C���N�g�w��o�^�A�b�v���[�h()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 415D40FE00EA
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        //�_�C���N�g�w��o�^�������s���B
        if(l_request instanceof WEB3AdminPvInfoDirectRegistInputRequest)
        {
            //this.get�_�C���N�g�w��o�^���͉��()���\�b�h���R�[������B
            l_response = this.getDirectRegistInputScreen((WEB3AdminPvInfoDirectRegistInputRequest)l_request);
        }
        else if(l_request instanceof WEB3AdminPvInfoDirectRegistConfirmRequest)
        {
            //this.validate�_�C���N�g�w��o�^()���\�b�h���R�[������B
            l_response = this.validateDirectRegist((WEB3AdminPvInfoDirectRegistConfirmRequest)l_request);
        }
        else if(l_request instanceof WEB3AdminPvInfoDirectRegistCompleteRequest)
        {
            //this.submit�_�C���N�g�w��o�^()���\�b�h���R�[������B
            l_response = this.submitDirectRegist((WEB3AdminPvInfoDirectRegistCompleteRequest)l_request);
        }
        else if(l_request instanceof WEB3AdminPvInfoDirectRegistCancelRequest)
        {
            //this.undo�_�C���N�g�w��o�^�A�b�v���[�h()���\�b�h���R�[������B
            l_response = this.undoDirectRegistUpload((WEB3AdminPvInfoDirectRegistCancelRequest)l_request);
        }
        else
        {
            String l_strErrorInfo = "�p�����[�^�̗ތ^���s���A�Y������.WEB3AdminPvInfoDirectRegistInputRequest," + 
                "WEB3AdminPvInfoDirectRegistConfirmRequest, " + "WEB3AdminPvInfoDirectRegistCompleteRequest, " +
                "WEB3AdminPvInfoDirectRegistCancelRequest�ތ^�B";
            log.error(l_strErrorInfo);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strErrorInfo); 
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    /**
     * (get�_�C���N�g�w��o�^���͉��)<BR>
     * �_�C���N�g�w��o�^���͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��҃_�C���N�g�w��o�^�T�[�r�X)get�_�C���N�g<BR>�w��o�^���͉�ʁv�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�_�C���N�g�w��o�^���̓��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistInputResponse
     * @@roseuid 415D419C03C7
     */
    protected WEB3AdminPvInfoDirectRegistInputResponse getDirectRegistInputScreen(WEB3AdminPvInfoDirectRegistInputRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDirectRegistInputScreen(WEB3AdminPvInfoDirectRegistInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoDirectRegistInputResponse l_response = null;

        //1.1 getInstanceFrom���O�C�����()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        log.debug("getInstanceFrom���O�C���������s���܂�" );
        
        //1.2  validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.PRIVATE_INFO, true);
        log.debug("validate���������s���܂�" );
        
        //1.3  �o�^�Ώیڋq�A�b�v���[�hCSV()
        WEB3PvInfoRegistTargetAccountUploadCsv l_registTargetAccountUploadCsv = new WEB3PvInfoRegistTargetAccountUploadCsv();
        
        //1.4 get�ŐV�A�b�v���[�h����(long)         
        AdministratorUploadRow l_adminUploadRow = l_registTargetAccountUploadCsv.getLatestUploadAction(0);
        log.debug("get�ŐV�A�b�v���[�h���������s���܂�" );
        
        //1.6 create Response()
        l_response = (WEB3AdminPvInfoDirectRegistInputResponse)l_request.createResponse();
        
                
        //1.5 �A�b�v���[�h���������݂���ꍇ�A�������{
        if(l_adminUploadRow != null)
        {

            //1.5.1 ��ײ�ްĲ�̫Ұ��݃A�b�v���[�h���𖾍�()
            WEB3PvInfoUploadHistoryUnit l_uploadHistoryUnit = new WEB3PvInfoUploadHistoryUnit();
            
            //1.5.2 (*)�v���p�e�B�Z�b�g
            log.debug("�A�b�v���[�h�I������" + "=" + l_adminUploadRow.getUploadEndTimestamp());
            if(l_adminUploadRow.getUploadEndTimestamp() == null)
            {
                l_uploadHistoryUnit.uploadStateDiv = WEB3PvInfoUploadStateDivDef.UPLOAD_STATUS_PROCESS;
                log.debug("�A�b�v���[�h������ԋ敪" + "=" + l_uploadHistoryUnit.uploadStateDiv);
            }
            else
            {
                l_uploadHistoryUnit.uploadStateDiv = WEB3PvInfoUploadStateDivDef.UPLOAD_STATUS_COMPLETE;
                log.debug("�A�b�v���[�h������ԋ敪" + "=" + l_uploadHistoryUnit.uploadStateDiv);
            }
            
            l_uploadHistoryUnit.uploadNumber = WEB3StringTypeUtility.formatNumber(l_adminUploadRow.getUploadCount());
            log.debug("�A�b�v���[�h����" + "=" + l_uploadHistoryUnit.uploadNumber);
            
            l_uploadHistoryUnit.uploadStartDate = l_adminUploadRow.getUploadStartTimestamp();
            log.debug("�A�b�v���[�h�J�n����" + "=" + l_uploadHistoryUnit.uploadStartDate);
            
            l_uploadHistoryUnit.uploadEndDate = l_adminUploadRow.getUploadEndTimestamp();
            log.debug("�A�b�v���[�h�I������" + "=" + l_uploadHistoryUnit.uploadEndDate);
            
            l_uploadHistoryUnit.pvInfoErrorId = l_adminUploadRow.getMessageCode(); 
            log.debug("��ײ�ްĲ�̫Ұ��݃G���[�ԍ�" + "=" + l_uploadHistoryUnit.pvInfoErrorId);
            
            //1.7 (*)�v���p�e�B�Z�b�g
            //�A�b�v���[�h���������݂���ꍇ�A��ײ�ްĲ�̫Ұ��݃A�b�v���[�h���𖾍׃I�u�W�F�N�g�B
            l_response.uploadHistoryUnit = l_uploadHistoryUnit; 
            log.debug("�A�b�v���[�h���������݂���ꍇ�A��ײ�ްĲ�̫Ұ��݃A�b�v���[�h���𖾍׃I�u�W�F�N�g�����s���܂�");
        }
        else
        {
            //�@@�A�b�v���[�h���������݂��Ȃ��ꍇ�Anull�B
            l_response.uploadHistoryUnit = null;
            log.debug("�A�b�v���[�h���������݂��Ȃ��ꍇ�Anull�����s���܂�");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�_�C���N�g�w��o�^)<BR>
     * �_�C���N�g�w��o�^�m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��҃_�C���N�g�w��o�^�T�[�r�X)validate�_�C���N�g�w��o�^�v�Q��<BR>
     * ========================================================<BR>
     * get���X(�،���ЃR�[�h:String, ���X�R�[�h:String)<BR>
     * <BR>
     * ���Âꂩ����O���X���[�����ꍇ�́A<BR>�u�ڋq�����݃G���[�v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01035<BR>
     * ========================================================<BR>
     * ========================================================<BR>
     * get�ڋq(�،���ЃR�[�h:String, ���X�R�[�h:String)<BR>
     * <BR>
     * ���Âꂩ����O���X���[�����ꍇ�́A<BR>�u�ڋq�����݃G���[�v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01035<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�_�C���N�g�w��o�^�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistConfirmResponse
     * @@roseuid 415D419C03E7
     */
    protected WEB3AdminPvInfoDirectRegistConfirmResponse validateDirectRegist(WEB3AdminPvInfoDirectRegistConfirmRequest l_request) throws WEB3BaseException
     
    {
        final String STR_METHOD_NAME = " validateDirectRegist(WEB3AdminPvInfoDirectRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoDirectRegistConfirmResponse l_response = null;
        //1.1 validate()
        l_request.validate();
        log.debug(" validate�����s���܂�");
        
        //1.2 getInstanceFrom���O�C�����()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        log.debug(" getInstanceFrom���O�C���������s���܂�");
        
        //1.3 validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.PRIVATE_INFO, true);
        log.debug("validate���������s���܂�");
       
        //1.4 (*1)����t���[
        int l_intRowCnt = 0;
        long l_lngUploadId = 0;
        log.debug("�A�b�v���[�h�t�@@�C��" + "=" + l_request.uploadFile);
        if(l_request.uploadFile != null)     
        {      
            //1.4.1 reset��t���ԋ敪(String)                  
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.UPLOAD_DAYLONG);
            log.debug("reset��t���ԋ敪�����s���܂�");
            
            //1.4.2 reset������t�g�����U�N�V����(String)              
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.DEFAULT);
            log.debug("reset������t�g�����U�N�V���������s���܂�");
            
            //1.4.3 validate������t�\()
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            log.debug("validate������t�\�����s���܂�");
            
            //1.4.4 * �A�b�v���[�hTemp�e�[�u���o�^����
            //1.4.4.1 �o�^�Ώیڋq�A�b�v���[�hCSV()
            WEB3PvInfoRegistTargetAccountUploadCsv l_registTargetAccountUploadCsv = new WEB3PvInfoRegistTargetAccountUploadCsv();
            log.debug("�o�^�Ώیڋq�A�b�v���[�hCSV�����s���܂�");
            
            //1.4.4.2 validate�����A�b�v���[�h(long)
            l_registTargetAccountUploadCsv.validateSameTimeUpload(null);
            log.debug("validate�����A�b�v���[�h�����s���܂�");
            
            //1.4.4.3 get�Ǘ��҃R�[�h()
            String l_strAdminCode = l_admin.getAdministratorCode();
            log.debug("get�Ǘ��҃R�[�h�����s���܂�");
            
            //1.4.4.4 save�A�b�v���[�h�J�n(long, String, String, String, String)               
            l_registTargetAccountUploadCsv.saveUpLoadStart(0, null, null, null, l_strAdminCode);
            log.debug("save�A�b�v���[�h�J�n�����s���܂�");
            
            //1.4.4.5 (*1) ���N�G�X�g�f�[�^.�A�b�v���[�h�t�@@�C��[]�̊e�v�f����LOOP����
            int l_intUploadFileCnt = l_request.uploadFile.length;
            for(int i = 0; i < l_intUploadFileCnt; i++)
            {
                int l_intCnt = 0;
                try
                {
                    //1.4.4.5.1 add���׍s(String)(
                    l_intCnt = l_registTargetAccountUploadCsv.addRow(l_request.uploadFile[i]);
                    log.debug(" add���׍s�����s���܂�");
                }
                //1.4.4.5.2 (*1.1) add���׍s()�ŗ�O���X���[���ꂽ�ꍇ
                catch (WEB3SystemLayerException l_ex)
                {             
                    //1.4.4.5.2.1 save�A�b�v���[�h�G���[(ErrorInfo)                  
                    l_registTargetAccountUploadCsv.saveUploadError(l_ex.getErrorInfo());
                        
                    //1.4.4.5.2.2 throw �i��O�j
                    log.error(STR_METHOD_NAME + l_ex.getErrorInfo().error_message);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00856,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getErrorMessage(),
                        l_ex);
                }
                
                //��s�̏ꍇ�iadd���׍s()�̖߂�l < 0�j�A���Y�v�f�̏����𒆒f�icontinue;�j
                if (l_intCnt < 0)
                {
                    continue;
                }
                                             
                try
                {
                    //1.4.4.5.3 validate���׍s(long)          
                    l_registTargetAccountUploadCsv.validateDetailsLine(l_intCnt);
                    log.debug(" validate���׍s�����s���܂�");
                }
                catch (WEB3BaseException l_ex)
                {
                    //1.4.4.5.5.1 save�A�b�v���[�h�G���[(ErrorInfo)
                    l_registTargetAccountUploadCsv.saveUploadError(l_ex.getErrorInfo());
                    
                    //1.4.4.5.5.2 throw(��O�j
                    log.error(getClass().getName() + "." + STR_METHOD_NAME 
                        + l_registTargetAccountUploadCsv.getBranchCode(l_intCnt) +  "," 
                        + l_registTargetAccountUploadCsv.getAccountCode(l_intCnt) );
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00856,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        l_registTargetAccountUploadCsv.getBranchCode(l_intCnt) + ","
                            + l_registTargetAccountUploadCsv.getAccountCode(l_intCnt));
                }

            }
            //1.4.4.6 get���׍s��()
            l_intRowCnt = l_registTargetAccountUploadCsv.getRowCount();
            log.debug("get���׍s��" + "=" + l_intRowCnt);
            
            //1.4.4.7 get�A�b�v���[�h�h�c()
            l_lngUploadId = l_registTargetAccountUploadCsv.getAdministratorUploadId();
            log.debug("get�A�b�v���[�h�h�c" + "=" + l_lngUploadId);
            
            //1.4.4.8 save�A�b�v���[�hTemp()
            l_registTargetAccountUploadCsv.saveUploadTemp();
            log.debug(" save�A�b�v���[�hTemp�����s���܂�");
        }
        //1.5 (*2)����t���[,�A�b�v���[�h�Ȃ��̏ꍇ
        else
        {
            try
            {
                
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_gentradeAccountManager = 
                                      (WEB3GentradeAccountManager)l_finApp.getAccountManager();

                //1.5.1  get���X(String, String)                   
                String l_strInstitutionCode = l_admin.getInstitutionCode();
                log.debug(" get�،���ЃR�[�h" + "=" + l_strInstitutionCode);
                
                WEB3GentradeBranch l_branch = l_gentradeAccountManager.getWeb3GenBranch(l_strInstitutionCode, l_request.branchCode);
                log.debug(" get���X" + "=" + l_branch);
                
                if (l_branch == null)
                {
                    log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01035.error_message);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                        getClass().getName() + "." + STR_METHOD_NAME);
                }
               
                //1.5.2 get�ڋq(String, String)
                String l_strCode = l_branch.getInstitution().getInstitutionCode();
                log.debug("�،���ЃR�[�h" + "=" + l_strCode);
                
                String l_strBranchCode = l_branch.getBranchCode();
                log.debug("���X�R�[�h" + "=" + l_strBranchCode);
                
                MainAccount l_mainAccount = l_gentradeAccountManager.getMainAccount(l_strCode, l_strBranchCode, l_request.accountCode);
                log.debug(" get�ڋq" + "=" + l_mainAccount);
                
                if(l_mainAccount == null)
                {
                    log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01035.error_message);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                        getClass().getName() + "." + STR_METHOD_NAME);
                } 
            }
            catch (NotFoundException l_ex)
            {
                log.error(getClass().getName() + "." + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01035.error_message);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);  
            }
            catch(WEB3SystemLayerException l_ex)
            {
                log.error(getClass().getName() + "." + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01035.error_message);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);  
            }
        }
          
        //1.6 get�V�K�\�����eID()
        WEB3PvInfoDataManager l_pvInfoDataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        long l_lntNewDisplayContentsId = l_pvInfoDataManager.getNewDisplayContentsId();
        log.debug("get�V�K�\�����eID" + "=" + l_lntNewDisplayContentsId);
        
        //1.7 createResponse()
        l_response = (WEB3AdminPvInfoDirectRegistConfirmResponse)l_request.createResponse();
        
        //1.8 (*) �v���p�e�B�Z�b�g
        l_response.displayContentsId = WEB3StringTypeUtility.formatNumber(l_lntNewDisplayContentsId);
        log.debug("�\�����eID" + "=" + l_response.displayContentsId);
        log.debug("�A�b�v���[�h�t�@@�C��" + "=" + l_request.uploadFile);
        if(l_request.uploadFile == null)
        {
            l_response.uploadNumber = null;
            log.debug("�A�b�v���[�h����" + "=" + l_response.uploadNumber);
            
            l_response.uploadId = null;
            log.debug("�A�b�v���[�h�h�c" + "=" + l_response.uploadId);
        }
        else
        {
            l_response.uploadNumber = WEB3StringTypeUtility.formatNumber(l_intRowCnt);
            log.debug("�A�b�v���[�h����" + "=" + l_response.uploadNumber);
            
            l_response.uploadId = WEB3StringTypeUtility.formatNumber(l_lngUploadId);
            log.debug("�A�b�v���[�h�h�c" + "=" + l_response.uploadId);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit�_�C���N�g�w��o�^)<BR>
     * �_�C���N�g�w��o�^�����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��҃_�C���N�g�w��o�^�T�[�r�X)submit�_�C���N�g�w��o�^�v�Q��<BR>
     * ========================================================<BR>
     * get���X(�،���ЃR�[�h:String, ���X�R�[�h:String)<BR>
     * <BR>
     * ���Âꂩ����O���X���[�����ꍇ�́A<BR>�u�ڋq�����݃G���[�v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01035<BR>
     * ========================================================<BR>
     * ========================================================<BR>
     * get�ڋq(�،���ЃR�[�h:String, ���X�R�[�h:String)<BR>
     * <BR>
     * ���Âꂩ����O���X���[�����ꍇ�́A<BR>�u�ڋq�����݃G���[�v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01035<BR>
     * ==========================================================<BR>
     * ========================================================<BR>
     * �d���`�F�b�N<BR>
     * <BR>
     * �ȉ��@@�ƇA���r���A�����l�̏ꍇ��O���X���[<BR>
     * �@@(*1)�̒l<BR>
     * �A[get�ڋq�R�[�h()�̖߂�l]��[get���X�R�[�h()�̖߂�l]�����������l<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00517<BR>
     * =========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�_�C���N�g�w��o�^�������N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistCompleteResponse
     * @@roseuid 415D419D000E
     */
    protected WEB3AdminPvInfoDirectRegistCompleteResponse submitDirectRegist(WEB3AdminPvInfoDirectRegistCompleteRequest l_request) 
        throws WEB3BaseException, WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = " submitDirectRegist(WEB3AdminPvInfoDirectRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoDirectRegistCompleteResponse l_response = null;
        
        //1.1 validate()
        l_request.validate();
        log.debug(" validate�����s���܂�");
        
        //1.2 getInstanceFrom���O�C�����()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        log.debug(" getInstanceFrom���O�C���������s���܂�");
        
        //1.3  validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.PRIVATE_INFO, true);
        log.debug("validate���������s���܂�");
        
        //1.4 validate����p�X���[�h(String)          
        l_admin.validateTradingPassword(l_request.password);
        log.debug(" validate����p�X���[�h�����s���܂�");
        
        WEB3PvInfoDataManager l_pvInfoDataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);

        try
        {
            //�Ǘ��҃_�C���N�g�w��o�^TransactionCallback(�Ǘ��� : �Ǘ���, �\�����e��� : �\�����e���)
            WEB3AdminPvInfoDirectRegistTransactionCallback l_transactionCallback =
                new WEB3AdminPvInfoDirectRegistTransactionCallback(
                    l_admin, l_request.displayContentsUnit);

            //getDefaultProcessor( )
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //doTransaction(arg0 : int, arg1 : TransactionCallback)
            //[����]
            //�g�����U�N�V���������F�@@TX_CREATE_NEW
            //�g�����U�N�V�����R�[���o�b�N�F�@@�Ǘ��҃_�C���N�g�w��ύXTransactionCallback�C���X�^���X
            l_queryProcessor.doTransaction(
                TransactionalInterceptor.TX_CREATE_NEW, l_transactionCallback);
        }
        catch (DataNetworkException l_ex)
        {
            log.error( "DB�ւ̃A�N�Z�X�Ɏ��s���܂����B" , l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B" , l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.6 (*1)����t���[
        String l_strInstiCode = l_admin.getInstitutionCode();
        log.debug("�،���ЃR�[�h" + "=" + l_strInstiCode);
        log.debug("�A�b�v���[�hID" + "=" + l_request.uploadId);
        if(l_request.uploadId != null)
        {
            //1.6.1 reset��t���ԋ敪(String)
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.UPLOAD_DAYLONG);
            log.debug("  reset��t���ԋ敪�����s���܂�");
            
            //1.6.2 reset������t�g�����U�N�V����(String)
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.DEFAULT);
            log.debug(" reset������t�g�����U�N�V���������s���܂�");
            
            //1.6.3 validate������t�\()
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            log.debug(" validate������t�\�����s���܂�");
            
            //1.6.4  ( * CSV�ꊇ�ڋq�o�^����)
            //1.6.4.1 �o�^�Ώیڋq�A�b�v���[�hCSV(long)
            long l_lngUploadId = Long.parseLong(l_request.uploadId);
            WEB3PvInfoRegistTargetAccountUploadCsv l_registTargetAccountUploadCsv = new WEB3PvInfoRegistTargetAccountUploadCsv(l_lngUploadId);
            log.debug(" �o�^�Ώیڋq�A�b�v���[�hCSV�����s���܂�");
    
            //1.6.4.2 validate�����A�b�v���[�h(long)
            l_registTargetAccountUploadCsv.validateSameTimeUpload(new Long(l_request.uploadId));
            log.debug(" validate�����A�b�v���[�h�����s���܂�");
    
            //1.6.4.3 setDataFrom�A�b�v���[�hTemp(long)
            l_registTargetAccountUploadCsv.setDataFromUploadTemp(l_lngUploadId);
            log.debug(" setDataFrom�A�b�v���[�hTemp�����s���܂�");
            
            //1.6.4.4 get���׍s��()
            int l_intRegistTargetAccountUploadCsvCnt = l_registTargetAccountUploadCsv.getRowCount();
            log.debug("get���׍s��" + "=" + l_intRegistTargetAccountUploadCsvCnt);

            //(*1)�C���X�^���X�𐶐�
            String l_strAccountAndBranchCode = null;

            //1.6.4.5 (*1) ���׍s�̐���LOOP����
            for(int i = 0; i < l_intRegistTargetAccountUploadCsvCnt; i++)
            {
                //1.6.4.5.1 get���X�R�[�h(int)
                String l_strBranchCode = l_registTargetAccountUploadCsv.getBranchCode(i);
                log.debug("���X�R�[�h" + "=" + l_strBranchCode);
                
                //1.6.4.5.2 get�ڋq�R�[�h(int)
                String l_strAccountCode = l_registTargetAccountUploadCsv.getAccountCode(i);
                log.debug("�ڋq�R�[�h" + "=" + l_strAccountCode);

                try
                {
                    //(*1)��null�ȊO�̏ꍇ
                    if (l_strAccountAndBranchCode != null)
                    {
                        //(*4)�d���`�F�b�N
                        //�ȉ��@@�ƇA���r���A�����l�̏ꍇ��O���X���[
                        //�@@(*1)�̒l
                        //�A[get�ڋq�R�[�h()�̖߂�l]��[get���X�R�[�h()�̖߂�l]�����������l
                        if (l_strAccountAndBranchCode.equals(l_strAccountCode + l_strBranchCode))
                        {
                            log.debug("�d���ڋq�`�F�b�N�G���[�i�����l�̍s�����݂���j�B");
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00517,
                                this.getClass().getName() + STR_METHOD_NAME,
                                l_strBranchCode + "," + l_strAccountCode);
                        }
                    }

                    //1.6.4.5.3 insert�{������(String, String, String, long, boolean)               
                    l_pvInfoDataManager.insertBrowseHistory(l_strInstiCode, l_strBranchCode,
                        l_strAccountCode, Long.parseLong(l_request.displayContentsUnit.displayContentsId), false);
                    log.debug(" insert�{�����������s���܂�");      
                }
                catch (WEB3BaseException l_ex)
                {
                    //(*4)�d���`�F�b�N�Cinsert�{������()�ɂė�O���X���[���ꂽ�ꍇ
                    //�@@�|�A�b�v���[�h�G���[���X�V����B
                    //�@@�|��ʂɗ�O���X���[����B
                    //save�A�b�v���[�h�G���[(�G���[��� : ErrorInfo)
                    //�A�b�v���[�h�G���[���A�b�v���[�h�e�[�u���ɍX�V����
                    //[save�A�b�v���[�h�G���[()�Ɏw�肷�����]
                    //�G���[���F�@@�icatch������O�j.getErrorInfo()
                    l_registTargetAccountUploadCsv.saveUploadError(l_ex.getErrorInfo());
                    log.debug(l_ex.getMessage());
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseException(
                        l_ex.getErrorInfo(),
                        getClass().getName() + STR_METHOD_NAME,
                        l_strBranchCode + "," + l_strAccountCode);
                }

                //(*1)�Ɉȉ��̒l���Z�b�g
                //[get�ڋq�R�[�h()�̖߂�l]��[get���X�R�[�h()�̖߂�l]�����������l���Z�b�g
                l_strAccountAndBranchCode = l_strAccountCode + l_strBranchCode;
            }
            
            //1.6.4.6  save�A�b�v���[�h�I��()
            l_registTargetAccountUploadCsv.saveUploadEnd();
            log.debug(" save�A�b�v���[�h�I�������s���܂�"); 
            
            //1.6.4.7 delete�A�b�v���[�hTemp()
            l_registTargetAccountUploadCsv.deleteUploadTemp();
            log.debug(" delete�A�b�v���[�hTemp�����s���܂�"); 
        }
        //1.7 (*2)����t���[
        else
        {
            try
            {
                //1.7.1 get���X(String, String)
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_gentradeAccountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                
                WEB3GentradeBranch l_branch = l_gentradeAccountManager.getWeb3GenBranch(l_strInstiCode, l_request.branchCode);
                log.debug("get���X =" + l_branch);
                if (l_branch == null)
                 {
                     log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01035.error_message);
                     log.exiting(STR_METHOD_NAME);
                     throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                         getClass().getName() + "." + STR_METHOD_NAME);
                 }
                
                //1.7.2 get�ڋq(String, String)
                String l_strCode = l_branch.getInstitution().getInstitutionCode();
                log.debug("�،���ЃR�[�h =" + l_strCode);
                
                String l_strBranchCode = l_branch.getBranchCode();
                log.debug("���X�R�[�h =" + l_strCode);
                
                MainAccount l_mainAccount = l_gentradeAccountManager.getMainAccount(l_strCode, l_strBranchCode, l_request.accountCode);
                log.debug("get�ڋq =" + l_mainAccount);
                if(l_mainAccount == null)
                {
                    log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01035.error_message);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                        getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
            catch (NotFoundException l_ex)
            {
                log.error(getClass().getName() + "." + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01035.error_message);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex); 
            }
            catch (WEB3SystemLayerException l_ex)
            {
                log.error(getClass().getName() + "." + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01035.error_message);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex); 
            }
            
            //1.7.3 insert�{������(String, String, String, long, boolean)
            l_pvInfoDataManager.insertBrowseHistory(l_strInstiCode, l_request.branchCode,
                 l_request.accountCode, Long.parseLong(l_request.displayContentsUnit.displayContentsId), false);
            
            log.debug("insert�{������ �����s���܂�");               
        }
        //1.8 createResponse()
        l_response = (WEB3AdminPvInfoDirectRegistCompleteResponse)l_request.createResponse();
     
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (undo�_�C���N�g�w��o�^�A�b�v���[�h)<BR>
     * �_�C���N�g�w��o�^�A�b�v���[�h���~�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��҃_�C���N�g�w��o�^�T�[�r�X)undo�_�C���N�g�w��o�^<BR>�A�b�v���[�h�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�_�C���N�g�w��o�^�A�b�v���[�h���~���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistCancelResponse
     * @@roseuid 415D419D002E
     */
    protected WEB3AdminPvInfoDirectRegistCancelResponse undoDirectRegistUpload(WEB3AdminPvInfoDirectRegistCancelRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " undoDirectRegistUpload(WEB3AdminPvInfoDirectRegistCancelRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoDirectRegistCancelResponse l_response = null;
        
        //1.1 validate()
        l_request.validate();
        log.debug("validate�����s���܂�");
        
        //1.2 �o�^�Ώیڋq�A�b�v���[�hCSV(long)
        WEB3PvInfoRegistTargetAccountUploadCsv l_registTargetAccountUploadCsv = new WEB3PvInfoRegistTargetAccountUploadCsv(Long.parseLong(l_request.uploadId));
        log.debug("�o�^�Ώیڋq�A�b�v���[�hCSV�����s���܂�");
        
        //1.3 delete�A�b�v���[�hTemp()
        l_registTargetAccountUploadCsv.deleteUploadTemp();
        log.debug("delete�A�b�v���[�hTemp�����s���܂�");
        
        //1.4 save�A�b�v���[�h���~()
        l_registTargetAccountUploadCsv.saveUploadStop();
        log.debug("save�A�b�v���[�h���~�����s���܂�");
        
        //1.5 createResponse()
        l_response = (WEB3AdminPvInfoDirectRegistCancelResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (�Ǘ��҃_�C���N�g�w��o�^TransactionCallback)<BR>
     * �Ǘ��҃_�C���N�g�w��o�^TransactionCallback�N���X<BR>
     */
    public class WEB3AdminPvInfoDirectRegistTransactionCallback implements TransactionCallback
    {
        /**
         * ���O���[�e�B���e�B
         */
        private  WEB3LogUtility l_log =
            WEB3LogUtility.getInstance(WEB3AdminPvInfoDirectRegistTransactionCallback.class);

        /**
         * (�Ǘ���)<BR>
         * �Ǘ���<BR>
         */
        private WEB3Administrator administrator;

        /**
         * (�\�����e���)<BR>
         * �\�����e���<BR>
         */
        private WEB3PvInfoDisplayContentsUnit displayContentsUnit;

        /**
         * (�Ǘ��҃_�C���N�g�w��o�^TransactionCallback )<BR>
         * �R���X�g���N�^<BR>
         * <BR>
         * ����.�Ǘ��҂ƈ���.�\�����e�����Y���̕ϐ��ɕۑ�����B<BR>
         * @@param l_administrator - (�Ǘ���)<BR>
         * �Ǘ���<BR>
         * @@param l_displayContentsUnit - (�\�����e���)<BR>
         * �\�����e���<BR>
         */
        public WEB3AdminPvInfoDirectRegistTransactionCallback(
            WEB3Administrator l_administrator,
            WEB3PvInfoDisplayContentsUnit l_displayContentsUnit)
        {
            final String STR_METHOD_NAME = "WEB3AdminPvInfoDirectRegistTransactionCallback("
                + "WEB3Administrator, WEB3PvInfoDisplayContentsUnit)";
            log.entering(STR_METHOD_NAME);

            //����.�Ǘ��҂ƈ���.�\�����e�����Y���̕ϐ��ɕۑ�����B
            this.administrator = l_administrator;
            this.displayContentsUnit = l_displayContentsUnit;

            log.exiting(STR_METHOD_NAME);
        }

        /**
         * �\�����e�o�^�������s���B<BR>
         * <BR>
         * �P�j��ײ�ްĲ�̫Ұ����ް��Ȱ�ެ.insert�\�����e()���R�[������B<BR>
         * <BR>
         * [����]<BR>
         * �Ǘ��ҁF�@@this.�Ǘ���<BR>
         * �\�����e���F this.�\�����e���<BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         */
        public Object process()
            throws
            DataNetworkException,
            DataQueryException,
            DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            try
            {
                //�P�j��ײ�ްĲ�̫Ұ����ް��Ȱ�ެ.insert�\�����e()���R�[������B
                WEB3PvInfoDataManager l_pvInfoDataManager =
                    (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
                l_pvInfoDataManager.insertDisplayContents(
                    this.administrator,
                    this.displayContentsUnit);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
