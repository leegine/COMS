head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.32.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioVirtualAccCashinULServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o�[�`������������UL�T�[�r�XImpl(WEB3AdminAioVirtualAccCashinULServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/09 �юu�� (���u) �V�K�쐬
                 : 2006/06/12 ������ (���u) ���f��No.592     
                 : 2006/06/22 �R�c���a (SCS) ���f��No.596
                 : 2006/08/31 �R�c���a (SCS) ���f��No.638
                 : 2006/10/18 �Ԑi �@@(���u)  �c�a�X�V�d�l�@@No.123
                 : 2006/10/20 �Ԑi �@@(���u)  ���f���@@No.669
                 : 2006/10/18 �Ԑi �@@(���u)  ���f���@@No.671
                 : 2006/11/14 ���G�� (���u)  �c�a�X�V�d�l�@@No.130
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.aio.WEB3AdminAioVirtualAccCashinULCsv;
import webbroker3.aio.WEB3FXDataControlService;
import webbroker3.aio.data.BankDepositNotifyParams;
import webbroker3.aio.data.BankDepositNotifyRow;
import webbroker3.aio.define.WEB3AioBankDepositNotifyCashTransferDivDef;
import webbroker3.aio.define.WEB3AioBankDepositNotifyTradeDivDef;
import webbroker3.aio.define.WEB3UploadStateDef;
import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULCancelRequest;
import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULCancelResponse;
import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULCompleteRequest;
import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULCompleteResponse;
import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULConfirmRequest;
import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULConfirmResponse;
import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULInputRequest;
import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULInputResponse;
import webbroker3.aio.message.WEB3AioUploadHistoryUnit;
import webbroker3.aio.service.delegate.WEB3AdminAioVirtualAccCashinULService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DataLoadDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.util.WEB3LogUtility;


/**
 * (�o�[�`������������UL�T�[�r�XImpl)<BR>
 * �o�[�`������������UL�T�[�r�X�����N���X<BR>
 * 
 * @@author �юu��(���u)
 * @@version 1.0
 */
public class WEB3AdminAioVirtualAccCashinULServiceImpl 
    implements WEB3AdminAioVirtualAccCashinULService 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioVirtualAccCashinULServiceImpl.class);    
    
    /**
     * �o�[�`�������������A�b�v���[�h�������s���B<BR>  
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>  
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�o�[�`�����������౯��۰�ޓ���ظ��Ă̏ꍇ<BR>  
     * �@@�|get�A�b�v���[�h���()���R�[������B  <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�o�[�`�����������౯��۰�ފm�Fظ��Ă̏ꍇ<BR>  
     * �@@�|validate�A�b�v���[�h�t�@@�C��()���R�[������B  <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�o�[�`�����������౯��۰�ފ���ظ��Ă̏ꍇ<BR>  
     * �@@�|submit�A�b�v���[�h�t�@@�C��()���R�[������B  <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�o�[�`�����������౯��۰�ޒ��~ظ��Ă̏ꍇ<BR>  
     * �@@�|undo�A�b�v���[�h()���R�[������B  <BR>
     * <BR>
     * ���T�[�r�X���\�b�h�ɂė�O�����������ꍇ�́A<BR>  
     * �@@��O�I�u�W�F�N�g�̒ǉ�������iWEB3BaseException.errorMessage�j<BR>  
     * �����X�|���X�f�[�^.errorMessage�ɃZ�b�g����B <BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4455B4FC03D6
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (l_request instanceof WEB3AdminAioVirtualAccCashinULInputRequest)
        {
            WEB3AdminAioVirtualAccCashinULInputResponse l_response = 
                getUploadScreen((WEB3AdminAioVirtualAccCashinULInputRequest)l_request);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminAioVirtualAccCashinULConfirmRequest)
        {
            WEB3AdminAioVirtualAccCashinULConfirmResponse l_response =
                validateUploadFile((WEB3AdminAioVirtualAccCashinULConfirmRequest)l_request);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminAioVirtualAccCashinULCompleteRequest)
        {
            WEB3AdminAioVirtualAccCashinULCompleteResponse l_response =
                submitUploadFile((WEB3AdminAioVirtualAccCashinULCompleteRequest)l_request);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminAioVirtualAccCashinULCancelRequest)
        {
            WEB3AdminAioVirtualAccCashinULCancelResponse l_response = 
                undoUploadFile((WEB3AdminAioVirtualAccCashinULCancelRequest)l_request);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else
        {
            log.debug("Error[���͒l���s���ł�]");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        

    }
    
    /**
     * (get�A�b�v���[�h���)<BR>
     * �o�[�`�������������A�b�v���[�h��ʕ\���������s���B<BR> 
     * <BR>
     * �V�[�P���X�}<BR> 
     * �u���o���T�[�r�X���f���i�Ǘ��ҁj�i�o�[�`�������������t�k�j<BR>
     * get�A�b�v���[�h��ʁv�Q�ƁB<BR>
     * @@param l_request - (�o�[�`���������������̓��N�G�X�g)<BR>
     * �o�[�`���������������̓��N�G�X�g
     * @@return WEB3AdminAioVirtualAccCashinULInputResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4455B35D01B6
     */
    protected WEB3AdminAioVirtualAccCashinULInputResponse getUploadScreen(
        WEB3AdminAioVirtualAccCashinULInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getUploadScreen(" +
        "WEB3AdminAioVirtualAccCashinULInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1)getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2)validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[validate����()�Ɏw�肷�����] 
        //�@@�\�J�e�S���R�[�h �F �o�������i�o�[�`�������������A�b�v���[�h�iB0103�j�j 
        //is�X�V �F true 
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.VIRTUAL_ACC_CASHIN_UPLOAD, true);
        
        //1.3)validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.4)CSV�f�[�^���f��( )
        //�A�b�v���[�h�t�@@�C�����f���𐶐�����B
        WEB3AdminAioVirtualAccCashinULCsv l_aioVirtualAccCashinULCsv =
            new WEB3AdminAioVirtualAccCashinULCsv();
        
        //1.5)get�A�b�v���[�h�ŐV����(Stirng, String, String, long)
        //�擾�����A�b�v���[�h�����̈ꗗ����w���l1�x�Ɂw���~�x 
        //���܂܂Ȃ��ŐV�̃f�[�^���擾����B 
        //
        //[get�A�b�v���[�h�ŐV����()�̈���] 
        //�،���ЃR�[�h �F this.get�،���ЃR�[�h() 
        //�A�b�v���[�h�t�@@�C���h�c �F this.get�A�b�v���[�h�t�@@�C���h�c() 
        //�����^�C�v �F this.get�����^�C�v() 
        //�f�[�^�L�[�F0
        WEB3FXDataControlService l_service = 
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);
        AdministratorUploadRow l_row =
            (AdministratorUploadRow)l_service.getUploadNewHistory(
            l_aioVirtualAccCashinULCsv.getInstitutionCode(),
            l_aioVirtualAccCashinULCsv.getUploadFileId(),
            l_aioVirtualAccCashinULCsv.getProductType().intValue() + "",
            0);

        //1.6)*1�A�b�v���[�h���������݂���ꍇ�A�������{
        WEB3AioUploadHistoryUnit l_unit = null;       
        
        if (l_row != null)
        {           
            l_unit = new WEB3AioUploadHistoryUnit();
            
            //1.6.1)�v���p�e�B�Z�b�g
            //�@@�@@�i�A�b�v���[�h�s.�A�b�v���[�h�I������ == null�j�̏ꍇ�A"�A�b�v���[�h��"
            //�@@�@@�i�A�b�v���[�h�s.�A�b�v���[�h�I������ != null�j�̏ꍇ�A"�A�b�v���[�h�ς�"
            if (l_row.getUploadEndTimestamp() == null)
            {
                l_unit.uploadStateDiv = WEB3UploadStateDef.UPLOADING;
            }
            else
            {
                l_unit.uploadStateDiv = WEB3UploadStateDef.UPLOAD_COMPLETE;
            }          
            
            //�@@�A�b�v���[�h�����F�@@�A�b�v���[�h�s.�A�b�v���[�h����
            l_unit.uploadNumber = String.valueOf(l_row.getUploadCount());
            
            //�@@�A�b�v���[�h�J�n�����F�@@�A�b�v���[�h�s.�A�b�v���[�h�J�n����
            l_unit.uploadStartDate = l_row.getUploadStartTimestamp();
            //�@@�A�b�v���[�h�I�������F�@@�A�b�v���[�h�s.�A�b�v���[�h�I������
            l_unit.uploadEndDate = l_row.getUploadEndTimestamp();
            
            //�@@���q�l�G���[�ԍ��F�@@�A�b�v���[�h�s.�G���[���b�Z�[�W
            l_unit.aioErrorId = l_row.getMessageCode();                       
                                   
        }
        
        //1.7)createResponse()
        WEB3AdminAioVirtualAccCashinULInputResponse l_response = 
            (WEB3AdminAioVirtualAccCashinULInputResponse) l_request.createResponse();
        
        //1.8)�v���p�e�B�Z�b�g
        //(*2)���X�|���X�f�[�^�v���p�e�B�ɒl���Z�b�g����B
        //�A�b�v���[�h�����ꗗ�F
        //�@@�A�b�v���[�h���������݂���ꍇ�A�o�[�`�������������A�b�v���[�h���𖾍׃I�u�W�F�N�g 
        //�A�b�v���[�h���������݂��Ȃ��ꍇ�Anull
        l_response.uploadHistoryList = l_unit;        
               
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�A�b�v���[�h�t�@@�C��)<BR>
     * �o�[�`�������������A�b�v���[�h�m�F�������s���B <BR>
     * <BR>
     * �V�[�P���X�}<BR> 
     * �u���o���T�[�r�X���f���i�Ǘ��ҁj�i�o�[�`�������������t�k�j<BR>
     * validate�A�b�v���[�h�t�@@�C���v�Q�ƁB<BR>
     * @@param l_request - (�o�[�`������������UL�m�F���N�G�X�g)<BR>
     * �o�[�`������������UL�m�F���N�G�X�g
     * @@return WEB3AdminAioVirtualAccCashinULConfirmResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4455B55B0257
     */
    protected WEB3AdminAioVirtualAccCashinULConfirmResponse validateUploadFile(
        WEB3AdminAioVirtualAccCashinULConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateUploadFile(" +
        "WEB3AdminAioVirtualAccCashinULConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1)���N�G�X�g�f�[�^�̐������`�F�b�N���s���B
        l_request.validate();
        
        //1.2)getInstanceFrom���O�C�����( )
        //���O�C�������A�Ǘ��҃I�u�W�F�N�g���擾����B
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3)validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.VIRTUAL_ACC_CASHIN_UPLOAD, true);
        
        //1.4)validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.5)�o�[�`������������CSV( )
        WEB3AdminAioVirtualAccCashinULCsv l_aioVirtualAccCashinULCsv =
            new WEB3AdminAioVirtualAccCashinULCsv();
        
        //1.6)validate�����A�b�v���[�h(�A�b�v���[�h�h�c : long)
        l_aioVirtualAccCashinULCsv.validateSameTimeUpload(null);
        
        //1.7)get�Ǘ��҃R�[�h( )
        String l_strAdministratorCode = l_admin.getAdministratorCode();
        
        //1.8)save�A�b�v���[�h�J�n(�f�[�^�L�[ : long, ���l�P : String, 
        //���l�Q : String, ���l�R : String, �X�V�҃R�[�h : String)
        l_aioVirtualAccCashinULCsv.saveUpLoadStart(0, null, null, null,l_strAdministratorCode);
        
        //1.9)���N�G�X�g�f�[�^.�A�b�v���[�h�t�@@�C��[]�̊e�v�f����LOOP����
        int l_intUploadFilelength = l_request.uploadFile.length;
        for (int i = 0; i < l_intUploadFilelength; i++)
        {
            //1.9.1)��s�̏ꍇ�i���N�G�X�g�f�[�^.�A�b�v���[�h�t�@@�C��.length < 0�j�A
            //���Y�v�f�̏����𒆒f�icontinue;�j
            if (l_request.uploadFile[i] == null || "".equals(l_request.uploadFile[i]))
            {
                continue;
            }
            
            //1.9.2)�A�b�v���[�h�t�@@�C���̃`�F�b�N���s���A�����Ώۂ����ʂ���B 
            boolean l_blnIsSkipReadRecord = 
                l_aioVirtualAccCashinULCsv.isSkipReadRecord(i, l_request.financialInstitutionCode, l_request.uploadFile);
            
            //1.9.3)is�ǂݔ�΂����R�[�h�i�j�̖߂�l = false�̏ꍇ�A���Y�v�f�̏����𒆒f�icontinue;�j���܂��B
            if (!l_blnIsSkipReadRecord)
            {
                continue;
            }
            
            //1.9.4)check�f�[�^�敪
            int l_intDataDiv = l_aioVirtualAccCashinULCsv.checkDataDiv(l_request.uploadFile[i].substring(0,1));
            
            try
            {
                //check�f�[�^�敪�̖߂�l��1�̏ꍇ�A�w�b�_���R�[�h��validate���s���B 
                if (l_intDataDiv == 1)
                {
                    //1.9.5)validate�w�b�_�[���R�[�h()
                    l_aioVirtualAccCashinULCsv.validateHeaderRecord(i, l_request);
                }
            }
            catch (WEB3BaseException l_ex)
            {
                //1.9.6.1)save�A�b�v���[�h�G���[(�G���[��� : ErrorInfo)
                l_aioVirtualAccCashinULCsv.saveUploadError(l_ex.getErrorInfo());
                
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getErrorMessage(),
                    l_ex);
            } 
            try
            {
                //1.9.7check�f�[�^�敪�̖߂�l��2�̏ꍇ�A�f�[�^���R�[�h��validate���s���B           
                if (l_intDataDiv == 2)
                {
                    l_aioVirtualAccCashinULCsv.validateDataRecord(i, l_request);
                }
            }
            catch (WEB3BaseException l_ex)
            {
                //1.9.8.1)save�A�b�v���[�h�G���[(�G���[��� : ErrorInfo)
                l_aioVirtualAccCashinULCsv.saveUploadError(l_ex.getErrorInfo());
                
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getErrorMessage(),
                    l_ex);
            } 
            
            //1.9.9)check�f�[�^�敪�̖߂�l��9�̏ꍇ�Acheck�f�[�^���R�[�h����( )
            try 
            {
                                
                if (l_intDataDiv == 9)
                {
                    l_aioVirtualAccCashinULCsv.checkDataRecordCount();
                }
            }
            catch (WEB3BaseException l_ex)
            {
                //1.9.10.1)save�A�b�v���[�h�G���[(�G���[��� : ErrorInfo)
                l_aioVirtualAccCashinULCsv.saveUploadError(l_ex.getErrorInfo());
                
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getErrorMessage(),
                    l_ex);
            }
        }  
        
        //1.10)calc�ǂݔ�΂�����()
        l_aioVirtualAccCashinULCsv.calcSkipReadCount();
        
        //1.11)get�A�b�v���[�h�h�c
        long l_lngUploadId = l_aioVirtualAccCashinULCsv.getAdministratorUploadId();
        
        //1.12)save�A�b�v���[�h�t�@@�C��()
        l_aioVirtualAccCashinULCsv.saveUploadFile(l_request.uploadFile);
        
        //1.13)createResponse( )
        WEB3AdminAioVirtualAccCashinULConfirmResponse l_response =
            (WEB3AdminAioVirtualAccCashinULConfirmResponse) l_request.createResponse();
        
        //1.14)�v���p�e�B�Z�b�g
        //�A�b�v���[�h���� �F this.�g�[�^������
        l_response.uploadNumber = l_aioVirtualAccCashinULCsv.totalCount + "";
        
        //�w�b�_���R�[�h���� �F this.�w�b�_���R�[�h����
        l_response.headerNumber = l_aioVirtualAccCashinULCsv.headerRecordCount + "";
        
        //�f�[�^���R�[�h���� �F this.�f�[�^���R�[�h����
        l_response.dataNumber = l_aioVirtualAccCashinULCsv.dataRecordCount + "";
        
        //�g���[���[���R�[�h���� �F this.�g���[���[���R�[�h����
        l_response.trailerNumber = l_aioVirtualAccCashinULCsv.trailerRecordCount + "";
        
        //�G���h���R�[�h���� �F this.�G���h���R�[�h����
        l_response.endNumber = l_aioVirtualAccCashinULCsv.endRecordCount + "";
        
        //�ǂݔ�΂������R�[�h���� �F this.�ǂݔ�΂������R�[�h����
        l_response.skipOverNumber = l_aioVirtualAccCashinULCsv.skipReadRecordCount + "";
        
        //�A�b�v���[�hID �F get�A�b�v���[�hID()�̖߂�l
        l_response.uploadID = l_lngUploadId + "";

        log.exiting(STR_METHOD_NAME);
        return l_response;           
               
    }
    
    /**
     * (submit�A�b�v���[�h�t�@@�C��)<BR>
     * �o�[�`�������������A�b�v���[�h�����������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�����T�[�r�X���f���i�Ǘ��ҁj�i�o�[�`�������������A�h���X�t�k�j<BR>
     * submit�A�b�v���[�h�t�@@�C���v�Q�ƁB <BR>
     * @@param l_request - (�o�[�`���������������͊m�F���N�G�X�g)<BR>
     * �o�[�`������������UL�m�F���N�G�X�g
     * @@return WEB3AdminAioVirtualAccCashinULCompleteResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4455B7590133
     */
    protected WEB3AdminAioVirtualAccCashinULCompleteResponse submitUploadFile(
            WEB3AdminAioVirtualAccCashinULCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "submitUploadFile(" +
        "WEB3AdminAioVirtualAccCashinULCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1)���N�G�X�g�f�[�^�̐������`�F�b�N���s���B
        l_request.validate();
        
        //1.2)getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3)validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.VIRTUAL_ACC_CASHIN_UPLOAD, true);
        
        //1.4)validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.5)�o�[�`������������CSV(long)        
        //  [����]
        //�@@�A�b�v���[�hID�F�@@���N�G�X�g�f�[�^.�A�b�v���[�hID
        WEB3AdminAioVirtualAccCashinULCsv l_aioVirtualAccCashinULCsv =
            new WEB3AdminAioVirtualAccCashinULCsv(Long.parseLong(l_request.uploadID));
        
        try
        {
            //1.6) check��������()
            l_aioVirtualAccCashinULCsv.checkCashinTransaction();
        }
        catch (WEB3BaseException l_ex)
        {
            //1.7.1)save�A�b�v���[�h�G���[(�G���[��� : ErrorInfo)
            l_aioVirtualAccCashinULCsv.saveUploadError(l_ex.getErrorInfo());
            
            log.error(l_ex.getErrorMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getErrorMessage(),
                l_ex);
        }

        //1.8)validate����p�X���[�h(�p�X���[�h : String)
        l_admin.validateTradingPassword(l_request.password);

        //1.9)stop�����f�[����()
        l_aioVirtualAccCashinULCsv.stopCashinDaemon();
        
        //1.10)validate�����A�b�v���[�h(�A�b�v���[�h�h�c : long)
        l_aioVirtualAccCashinULCsv.validateSameTimeUpload(new Long(l_request.uploadID));
 
        //1.11)setDataFrom�A�b�v���[�hTemp(�A�b�v���[�h�h�c : long)
        l_aioVirtualAccCashinULCsv.setDataFromUploadTemp(Long.parseLong(l_request.uploadID));
        
        //1.12)get���׍s��()
        int l_intRowCount = l_aioVirtualAccCashinULCsv.getRowCount();
        
        //1.13)get���׍s()
        String[] l_strRows = l_aioVirtualAccCashinULCsv.getRows();        
        
        //[��s�����ʒm�e�[�u��ID�̍ő�l]
        List l_lisRows = null;
        long l_lngBankDepositNotifyId = 0;
        try 
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    BankDepositNotifyRow.TYPE,
                    null,
                    "bank_deposit_notify_id desc",
                    null,
                    null); 
                    
        } 
        catch (DataException l_ex) 
        {
            log.debug("__DataException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        if (l_lisRows != null && l_lisRows.size() != 0)
        {
            BankDepositNotifyRow l_row = (BankDepositNotifyRow)l_lisRows.get(0);
            l_lngBankDepositNotifyId = l_row.getBankDepositNotifyId();
        }
        
        //1.14)(*1)���׍s�̐���LOOP����
        for (int i = 0; i < l_intRowCount; i++) 
        {            
            //1.14.1)is�ǂݔ�΂����R�[�h(int, String, String[])

            //�ǂݔ�΂����R�[�h���ǂ����`�F�b�N����B 

            //[�����̐ݒ�]  
            //�s�ԍ��F�@@index 
            //��s�R�[�h�F�@@�o�[�`������������UL�������N�G�X�g�f�[�^.��s�R�[�h 
            //���׍s�F�@@get���׍s()�̖߂�l 
            boolean l_blnIsSkipReadRecord = 
                l_aioVirtualAccCashinULCsv.isSkipReadRecord(i, l_request.financialInstitutionCode, l_strRows);
            
            //1.14.2) (*1.2) is�ǂݔ�΂����R�[�h�i�j�̖߂�l = false�̏ꍇ�A���Y�v�f�̏����𒆒f�icontinue;�j

            if (!l_blnIsSkipReadRecord)
            {
                continue;
            }
            
            //1.14.3)check�f�[�^�敪
            int l_intDataDiv = l_aioVirtualAccCashinULCsv.checkDataDiv(l_strRows[i].substring(0, 1));
            
            //1.14.4)(*3)check�f�[�^�敪�̖߂�l()��1�̏ꍇ
            if (l_intDataDiv == 1)
            {
                //1.14.4.1) set�w�b�_�[���R�[�h(int)
                l_aioVirtualAccCashinULCsv.setHeaderRecord(i);               
            }
            
            //1.14.5)(*4)check�f�[�^�敪()�̖߂�l��2�̏ꍇ
            else if (l_intDataDiv == 2)
            {
                BankDepositNotifyParams l_params = new BankDepositNotifyParams();               
                
                //1.14.5.1)get��s�x�X�R�[�h(int)                
                String l_strBankBranchCode = l_aioVirtualAccCashinULCsv.getBankBranchCode(
                    i, l_request.financialInstitutionCode);
                l_params.setBankBranchCode(l_strBankBranchCode);

                //1.14.5.2)get�����(int)
                String l_strSettlementDate = l_aioVirtualAccCashinULCsv.getSettlementDate(i);
                l_params.setDepositDataAccountDate(l_strSettlementDate);
                
                //1.14.5.3)get�N�Z��(int)
                String l_strBaseDate = l_aioVirtualAccCashinULCsv.getBaseDate(i);
                l_params.setDepositDataBaseDate(l_strBaseDate);
                
                //1.14.5.4)get���z(int, String)
                String l_strAmount = l_aioVirtualAccCashinULCsv.getAmount(
                    i, l_request.financialInstitutionCode);
                l_params.setDepositDataDepositAmount(l_strAmount);
                
                //1.14.5.5) get�˗��l�R�[�h(int)
                String l_strPersonCode = l_aioVirtualAccCashinULCsv.getPersonCode(
                        i, l_request.financialInstitutionCode);
                l_params.setDepositDataTransPersonCode(l_strPersonCode);
                
                //1.14.5.6)get�˗��l��(int, String)
                String l_strPersonName = l_aioVirtualAccCashinULCsv.getPersonName(
                    i, l_request.financialInstitutionCode);
                l_params.setDepositDataTransPersonName(l_strPersonName);
                
                //1.14.5.7)get�d����s��(int)
                String l_strBankName = l_aioVirtualAccCashinULCsv.getDeliveredBankName(i);
                l_params.setDeliveredBankName(l_strBankName);
                
                //1.14.5.8)get�d���X��(int)
                String l_strBankBranchName = l_aioVirtualAccCashinULCsv.getDeliveredBranchName(i);
                l_params.setDeliveredBankBranchName(l_strBankBranchName);
                
                //1.14.5.9)getEDI���(int, String)
                String l_strEDIInfo = l_aioVirtualAccCashinULCsv.getEDIInfo(
                    i, l_request.financialInstitutionCode);
                l_params.setEdiInfo(l_strEDIInfo);
                
                //�A�b�v���[�h�t�@@�C���̓��e����s�����ʒm�e�[�u���ɍX�V����B 
                //���X�V���e�́yXTrade�z�⑫����.DB�X�V�u�o�[�`������������UL_��s�����ʒm�e�[�u���X�V�d�l.xls�v���Q�Ƃ���B                
                //�ʔԁi[��s�����ʒm�e�[�u��ID�̍ő�l]�@@+�@@1�j
                l_lngBankDepositNotifyId = l_lngBankDepositNotifyId + 1;
                l_params.setBankDepositNotifyId(l_lngBankDepositNotifyId);
                
                //�Ǘ��҃I�u�W�F�N�g.�،���ЃR�[�h
                l_params.setInstitutionCode(l_admin.getInstitutionCode());
                
                //��s�R�[�h
                l_params.setBankCode(l_aioVirtualAccCashinULCsv.bankCode);
                
                //�����ԍ�
                l_params.setBankAccountNo(l_aioVirtualAccCashinULCsv.accountCode);
                
                //�Ɖ�ԍ�
                l_params.setDepositDataReferenceNo(Long.toString(l_lngBankDepositNotifyId));
                
                //�a�����
                l_params.setDepositDataBankAccountType(l_aioVirtualAccCashinULCsv.depositBankAccountType);
                
                //�����敪
                l_params.setCashTransferDiv(WEB3AioBankDepositNotifyCashTransferDivDef.CASH_IN);
                
                //����敪
                l_params.setTradeDiv(WEB3AioBankDepositNotifyTradeDivDef.PAY_BY_TRANSFER);
                
                //�����敪
                l_params.setStatus(WEB3StatusDef.NOT_DEAL);
                
                //�G���[�����ŏI�ʔ�
                l_params.setLastErrorHistorySerialNo(0);
                
                //�쐬���t
                l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                
                //�X�V���t
                l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                //�f�[�^�捞�敪
                l_params.setDataLoadDiv(WEB3DataLoadDivDef.BAATYARU_ACCOUNT_CHAHIN_UL);
                    
                //1.14.5.11)doInsertQuery(arg0 : Row)
                try 
                {
                    QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                    l_queryProcessor.doInsertQuery(l_params);
                }
                catch (DataQueryException l_ex)
                {
                    log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                }
                catch (DataNetworkException l_ex)
                {
                    log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                }
            
            }                          
        }
        //1.15)start�����f�[����()
        l_aioVirtualAccCashinULCsv.startCashinDaemon();
        
        //1.16)save�A�b�v���[�h�I��()
        l_aioVirtualAccCashinULCsv.saveUploadEnd();
        
        //1.17)delete�A�b�v���[�hTemp()
        l_aioVirtualAccCashinULCsv.deleteUploadTemp();
        
        //1.18)createResponse()
        WEB3AdminAioVirtualAccCashinULCompleteResponse l_response =
            (WEB3AdminAioVirtualAccCashinULCompleteResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (undo�A�b�v���[�h�t�@@�C��)<BR>
     * �o�[�`�����������������̃A�b�v���[�h���~�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���o���T�[�r�X���f���i�Ǘ��ҁj�i�o�[�`�������������A�h���X�t�k�j<BR>
     * undo�A�b�v���[�h�t�@@�C���v�Q�ƁB<BR>
     * @@param l_request - (�o�[�`���������������~���N�G�X�g)<BR>
     * �o�[�`���������������~���N�G�X�g
     * @@return WEB3AdminAioVirtualAccCashinULCancelResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4455B899006C
     */
    protected WEB3AdminAioVirtualAccCashinULCancelResponse undoUploadFile(
        WEB3AdminAioVirtualAccCashinULCancelRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "undoUploadFile(" +
        "WEB3AdminAioVirtualAccCashinULCancelRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1)validate()
        l_request.validate();
        
        //1.2)�o�[�`������������CSV(long)
        WEB3AdminAioVirtualAccCashinULCsv l_aioVirtualAccCashinULCsv =
            new WEB3AdminAioVirtualAccCashinULCsv(Long.parseLong(l_request.uploadID));
        
        //1.3)delete�A�b�v���[�hTemp()
        l_aioVirtualAccCashinULCsv.deleteUploadTemp();
        
        //1.4)save�A�b�v���[�h���~()
        l_aioVirtualAccCashinULCsv.saveUploadStop();
        
        //1.5)createResponse()
        WEB3AdminAioVirtualAccCashinULCancelResponse l_response =
            (WEB3AdminAioVirtualAccCashinULCancelResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
        
    }


}
@
