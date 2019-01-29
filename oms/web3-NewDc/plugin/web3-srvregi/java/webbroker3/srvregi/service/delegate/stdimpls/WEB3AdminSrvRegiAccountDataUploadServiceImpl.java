head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.49.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountDataUploadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h�T�[�r�XImpl(WEB3AdminSrvRegiAccountDataUploadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 �A�C��(���u) �V�K�쐬
Revesion History : 2005/04/04 ���c ��(SRA) �T�[�r�X�敪�`�F�b�N�����̃o�O�Ή�
Revesion History : 2007/06/05 �Ј���(���u) �d�l�ύX���f��No.238 No.239
Revesion History : 2007/06/28 �����Q(���u) �d�l�ύX���f��No.275
Revesion History : 2007/07/11 �Ј���(���u) ���f��279
Revesion History : 2008/02/26 ���g �d�l�ύX ���f��324�C327,328
Revesion History : 2008/06/02 ���g (���u) �d�l�ύX ���f��No.382,No.388
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ConditionsValueDivDef;
import webbroker3.common.define.WEB3EffectiveDivDef;
import webbroker3.common.define.WEB3SpecialProcessDivDef;
import webbroker3.common.define.WEB3SrvRegiCancelDivDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSrvRegiApplication;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.srvregi.WEB3AdminSrvRegiAccountDataUploadCsv;
import webbroker3.srvregi.WEB3SrvRegiApplicationRequiredService;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.WEB3SrvRegiTradingTimeManagement;
import webbroker3.srvregi.data.SrvRegiMasterRow;
import webbroker3.srvregi.define.WEB3SrvRegiUploadStatusDef;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadCancelRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadCancelResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadConfirmResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadInputRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadInputResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountDataUploadService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountDataUploadUnitService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiOtherOrgService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiRegistService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h�T�[�r�XImpl)<BR>
 * �T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h�T�[�r�X�@@�����N���X<BR>
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiAccountDataUploadServiceImpl implements WEB3AdminSrvRegiAccountDataUploadService 
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiAccountDataUploadServiceImpl.class);
    
    /**
     * @@roseuid 416F392C004E
     */
    public WEB3AdminSrvRegiAccountDataUploadServiceImpl() 
    {
     
    }
    
    /**
     * �T�[�r�X���p�Ǘ��Ҍڋq�A�b�v���[�h�������s���B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�T�[�r�X���p�Ǘ��Ҍڋq����۰�ޓ���ظ��Ă̏ꍇ <BR>
     * �@@�|get�A�b�v���[�h���()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�T�[�r�X���p�Ǘ��Ҍڋq����۰�ފm�Fظ��Ă̏ꍇ <BR>
     * �@@�|validate�A�b�v���[�h�t�@@�C��()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�T�[�r�X���p�Ǘ��Ҍڋq����۰�ފ���ظ��Ă̏ꍇ <BR>
     * �@@�|submit�A�b�v���[�h�t�@@�C��()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�T�[�r�X���p�Ǘ��Ҍڋq����۰�ޒ��~ظ��Ă̏ꍇ <BR>
     * �@@�|undo�A�b�v���[�h()���R�[������B<BR>
     * <BR>
     * ���T�[�r�X���\�b�h�ɂė�O�����������ꍇ�́A <BR>
     * �@@��O�I�u�W�F�N�g�̒ǉ�������iWEB3BaseException.errorMessage�j<BR>
     * �����X�|���X�f�[�^.errorMessage�ɃZ�b�g����B <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 410F713E0066
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminSrvRegiUploadInputRequest)
        {
            //get�A�b�v���[�h���
            l_response = getUploadScreen(
                (WEB3AdminSrvRegiUploadInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminSrvRegiUploadConfirmRequest)
        {
        	//��Q�Ή� �G���[���b�Z�[�W�\���Ή�        	
        	l_response = new WEB3AdminSrvRegiUploadConfirmResponse();
        	
        	try
        	{
				//validate�A�b�v���[�h�t�@@�C��
				l_response = validateUploadFile(
					(WEB3AdminSrvRegiUploadConfirmRequest)l_request);
        	}
        	catch(WEB3BaseException ex)
        	{
        		l_response.errorInfo = ex.getErrorInfo();
        		l_response.errorMessage = ex.getErrorMessage();
        	}
            
        }
        else if (l_request instanceof WEB3AdminSrvRegiUploadCompleteRequest)
        {
			//��Q�Ή� �G���[���b�Z�[�W�\���Ή�        	
			l_response = new WEB3AdminSrvRegiUploadCompleteResponse();
        	
        	try
        	{
	        	//submit�A�b�v���[�h�t�@@�C��
	            l_response = submitUploadFile(
	                (WEB3AdminSrvRegiUploadCompleteRequest)l_request);
        	}
        	catch(WEB3BaseException ex)
        	{
				l_response.errorInfo = ex.getErrorInfo();
				l_response.errorMessage = ex.getErrorMessage();
        	}
        }
        else if (l_request instanceof WEB3AdminSrvRegiUploadCancelRequest)
        {
            //undo�A�b�v���[�h
            l_response = undoUpload(
                (WEB3AdminSrvRegiUploadCancelRequest)l_request);
        }
        else
        {
            String l_strErrorMessage = "�p�����[�^�̗ތ^���s���B";
            log.debug(l_strErrorMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);            
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�A�b�v���[�h���)<BR>
     * �T�[�r�X���p�ڋq�A�b�v���[�h��ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u(�T�[�r�X���p)�ڋq�A�b�v���[�h�Eget�A�b�v���[�h��ʁv�Q�ƁB <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiUploadInputResponse
     * @@roseuid 410F723D0110
     */
    public WEB3AdminSrvRegiUploadInputResponse getUploadScreen(
        WEB3AdminSrvRegiUploadInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " getUploadScreen(WEB3AdminSrvRegiUploadInputRequest)";
        log.entering(STR_METHOD_NAME );

		// validate
		l_request.validate();

        //2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //3 validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_ACCOUNT, true);
        
        //�d�l�ύX�Ή� NO_207
        //4 isDIR�Ǘ���()�̖߂�l��false�̏ꍇ�Avalidate������t�\()
		if (!l_admin.isDirAdministrator())
		{
			WEB3SrvRegiTradingTimeManagement.validateOrderAccept();
		}
         
        boolean l_blnSameTimeUpload = true;
        
        //�ڋq�f�[�^�A�b�v���[�hCSV( )
        WEB3AdminSrvRegiAccountDataUploadCsv l_accountDataUploadCsv = 
            new WEB3AdminSrvRegiAccountDataUploadCsv();
        //5 validate�����A�b�v���[�h(long)
        try
        {
            l_accountDataUploadCsv.validateSameTimeUpload(null);
        }
        //��Q�Ή� NO_2249
        catch (WEB3BaseException l_e)
        {
            l_blnSameTimeUpload = false;
        }
                    
        //6 get�ŐV�A�b�v���[�h����(long)
        AdministratorUploadRow l_adminRow = l_accountDataUploadCsv.getLatestUploadAction(
            Long.parseLong(l_request.serviceDiv));
        
        //7 �T�[�r�X���p�Ǘ��҃A�b�v���[�h���̓��X�|���X( )
        WEB3AdminSrvRegiUploadInputResponse l_response = 
            (WEB3AdminSrvRegiUploadInputResponse)l_request.createResponse();
        
        //8 (*)�v���p�e�B�Z�b�g
        if (!l_blnSameTimeUpload)
        {
            l_response.uploadStatus = WEB3SrvRegiUploadStatusDef.UPLOADING;
            l_response.endDate = null;
        }
        else
        {
            l_response.uploadStatus = WEB3SrvRegiUploadStatusDef.COMPLETE;
            if (l_adminRow != null)
            {
                l_response.endDate = l_adminRow.getUploadEndTimestamp();
            }
        }

        log.exiting(STR_METHOD_NAME);     
        return l_response;
    }
    
    /**
     * (validate�A�b�v���[�h�t�@@�C��)<BR>
     * �T�[�r�X���p�ڋq�A�b�v���[�h�m�F�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u(�T�[�r�X���p)�ڋq�A�b�v���[�h�Evalidate�A�b�v���[�h�t�@@�C���v�Q�ƁB <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiUploadConfirmResponse
     * @@roseuid 410F725F0064
     */
    public WEB3AdminSrvRegiUploadConfirmResponse validateUploadFile(
        WEB3AdminSrvRegiUploadConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateUploadFile(WEB3AdminSrvRegiUploadConfirmRequest )";
        log.entering(STR_METHOD_NAME );

		//1 validate
		l_request.validate();

        //3 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //4 validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_ACCOUNT, true);
        
        //5 isDIR�Ǘ���()�̖߂�l��false�̏ꍇ�A��t���ԃ`�F�b�N�����{����
        if (!l_admin.isDirAdministrator())//5.1 isDIR�Ǘ���( )
        {
            //5.2 validate������t�\( )
            WEB3SrvRegiTradingTimeManagement.validateOrderAccept();
        }
        
        //6 reset��t���ԋ敪()
		WEB3SrvRegiTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.SRVREGI);
        
        //7 �ڋq�f�[�^�A�b�v���[�hCSV( )
        WEB3AdminSrvRegiAccountDataUploadCsv l_accountDataUploadCsv = 
            new WEB3AdminSrvRegiAccountDataUploadCsv();

        //8 validate�����A�b�v���[�h(long)
        l_accountDataUploadCsv.validateSameTimeUpload(null);

        // getDataSourceObject
        AdministratorRow l_administratorRow =
            (AdministratorRow)l_admin.getDataSourceObject();

        //check���׍s��
        //�،���Ђh�c�F�Ǘ��ҍs.�،���Ђh�c
        //���׍s���F���N�G�X�g�f�[�^.�A�b�v���[�h�t�@@�C��.length() - 1
        l_accountDataUploadCsv.checkDetailLines(
            l_administratorRow.getInstitutionId(),
            l_request.lines.length - 1);

        //9 save�A�b�v���[�h�J�n(long, String, String, String, String)
        l_accountDataUploadCsv.saveUpLoadStart(
            Long.parseLong(l_request.serviceDiv),
            null,
            null,
            null,
            l_admin.getAdministratorCode());
        
        //��Q�Ή� NO_2164
        try
        {
            //10 validate�L�[�w�b�_(String)
        	l_accountDataUploadCsv.validateKeyHeader(l_request.lines[0]);
        }
        catch(WEB3BaseException ex)
        {
			log.error("�A�b�v���[�h�G���[�D�X�V����", ex);
			
			//��O�����������ꍇ�A�A�b�v���[�h�G���[���X�V����
			//save�A�b�v���[�h�G���[(ErrorInfo)
			l_accountDataUploadCsv.saveUploadError(ex.getErrorInfo());
                    
			//��Q�Ή� �G���[���b�Z�[�W�\���Ή�
			//throw�i��O�j
			throw new WEB3BaseException(
				 ex.getErrorInfo(),
				 this.getClass().getName() + STR_METHOD_NAME,
				 l_request.lines[0]);                    
        }
        
        //11 validate���׍s(�ڋq�f�[�^�A�b�v���[�hCSV, String[])
        String l_strInitializeAppliDiv = this.validateDetailsLine(l_accountDataUploadCsv ,l_request.lines, l_admin);
        
        //12 get���׍s��( )
        int l_intRowNumber = l_accountDataUploadCsv.getRowCount();
        
        //13 get�A�b�v���[�h�h�c( )
        long l_lngUploadId = l_accountDataUploadCsv.getAdministratorUploadId();
        
		//��Q�Ή� NO_2164
		try
		{
			//14 save�A�b�v���[�hTemp( )
			int l_intTempRowCount = l_accountDataUploadCsv.saveUploadTemp();
		}
		catch(WEB3BaseException ex)
		{
			log.error("�A�b�v���[�h�G���[�D�X�V����", ex);
			
			//��O�����������ꍇ�A�A�b�v���[�h�G���[���X�V����
			//save�A�b�v���[�h�G���[(ErrorInfo)
			l_accountDataUploadCsv.saveUploadError(ex.getErrorInfo());
                    
			//��Q�Ή� �G���[���b�Z�[�W�\���Ή�
			//throw�i��O�j
			throw new WEB3BaseException(
				 ex.getErrorInfo(),
				 this.getClass().getName() + STR_METHOD_NAME);                    
		}
		
        //15 �T�[�r�X���p�Ǘ��҃A�b�v���[�h�m�F���X�|���X( )
        WEB3AdminSrvRegiUploadConfirmResponse l_response = 
            (WEB3AdminSrvRegiUploadConfirmResponse)l_request.createResponse();
            
        //16 (*)�v���p�e�B�Z�b�g
        l_response.lineCount = WEB3StringTypeUtility.formatNumber(l_intRowNumber);
        l_response.uploadId = WEB3StringTypeUtility.formatNumber(l_lngUploadId);
        
        //�����\���敪
        l_response.firstApplyDiv = l_strInitializeAppliDiv;

        log.exiting(STR_METHOD_NAME);     
        return l_response;
    }
    
    /**
     * (submit�A�b�v���[�h�t�@@�C��)<BR>
     * �T�[�r�X���p�ڋq�A�b�v���[�h�����������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u(�T�[�r�X���p)�ڋq�A�b�v���[�h�Esubmit�A�b�v���[�h�t�@@�C���v�Q�ƁB <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiUploadCompleteResponse
     * @@roseuid 410F72720304
     */
    public WEB3AdminSrvRegiUploadCompleteResponse submitUploadFile(
        WEB3AdminSrvRegiUploadCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitUploadFile(WEB3AdminSrvRegiUploadCompleteRequest )";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiUploadCompleteResponse l_response = null;
        try
        {
			//1 validate
			l_request.validate();
            
            //3 getInstanceFrom���O�C�����( )
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            
            //4 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
            l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_ACCOUNT, true);
            
            //5 validate����p�X���[�h(�p�X���[�h : String)
            l_admin.validateTradingPassword(l_request.password);
            
            //6 isDIR�Ǘ���()�̖߂�l��false�̏ꍇ�A��t���ԃ`�F�b�N�����{����
            if (!l_admin.isDirAdministrator())//6.1 isDIR�Ǘ���( )
            {
                //6.2 validate������t�\( )
                WEB3SrvRegiTradingTimeManagement.validateOrderAccept();
            }
            
			//7 reset��t���ԋ敪()
			WEB3SrvRegiTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.SRVREGI);

            //8 �ڋq�f�[�^�A�b�v���[�hCSV( )
            WEB3AdminSrvRegiAccountDataUploadCsv l_accountDataUploadCsv = 
                new WEB3AdminSrvRegiAccountDataUploadCsv(Long.parseLong(l_request.uploadId));//WEB3-SRVREGI-A-UT-0086                               
            
            //9 validate�����A�b�v���[�h(�A�b�v���[�h�h�c : long)
            l_accountDataUploadCsv.validateSameTimeUpload(
                new Long(l_request.uploadId));
            
            //10 setDataFrom�A�b�v���[�hTemp(�A�b�v���[�h�h�c : long)
            l_accountDataUploadCsv.setDataFromUploadTemp(Long.parseLong(l_request.uploadId));
            
            //11 get���׍s��( )��
            int l_rowCount = l_accountDataUploadCsv.getRowCount();

            // getDataSourceObject
            AdministratorRow l_administratorRow =
                (AdministratorRow)l_admin.getDataSourceObject();

            //check���׍s��
            //�،���Ђh�c�F�Ǘ��ҍs.�،���Ђh�c
            //���׍s���Fget���׍s��()�̖߂�l
            l_accountDataUploadCsv.checkDetailLines(
                l_administratorRow.getInstitutionId(),
                l_rowCount);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
                            

            //12 (*)���׍s�̐����i���׍s[0]����jLOOP����
            for (int i=0 ; i < l_rowCount; i++)
            {
				//��Q�Ή� NO_2164
				try
				{
	                //12.1 get���X�R�[�h(int)
	                String l_strBranchCode = l_accountDataUploadCsv.getBranchCode(i);
	                
	                //12.2 validate���X����(String[])
	                l_admin.validateBranchPermission(l_strBranchCode);
	
	                //12.3 get�A�b�v���[�h�敪(�s�ԍ� : int)
	                String l_strUploadDiv = l_accountDataUploadCsv.getUploadDiv(i);
	                
	                //12.4 get�\���o�^�h�c(�s�ԍ� : int)
	                String l_strRegistId = l_accountDataUploadCsv.getRegistId(i);
	                
	                //12.5 get�،���ЃR�[�h(�s�ԍ� : int)
	                String l_strInstitutionCode = l_accountDataUploadCsv.getInstitutionCode(i);
	                
	                //12.6 get�T�[�r�X�敪(�s�ԍ� : int)
	                String l_strSrvDiv = l_accountDataUploadCsv.getSrvDiv(i);
	                
	                //12.7 get�ڋq�R�[�h(�s�ԍ� : int)
	                String l_strAccountCode = l_accountDataUploadCsv.getAccountCode(i);
	                
	                //12.8 get�\�����I�敪(�s�ԍ� : int)
	                String l_strAppliLotDiv = l_accountDataUploadCsv.getAppliLotDiv(i);
	                
	                //12.9 get�\����(�s�ԍ� : int)�ڋq
	                Date l_datAppliDate = l_accountDataUploadCsv.getAppliDate(i);
	                Timestamp l_tsAppliDate = null;
	                if (l_datAppliDate != null)
	                {
	                    l_tsAppliDate = new Timestamp(l_datAppliDate.getTime());
	                }
	
	                //12.10 get�K�p�J�n��(�s�ԍ� : int)
	                Date l_datAppliStartDate = l_accountDataUploadCsv.getAppliStartDate(i);
	                Timestamp l_tsAppliStartDate = null;
	                if (l_datAppliStartDate != null)
	                {
	                    l_tsAppliStartDate = new Timestamp(l_datAppliStartDate.getTime());
	                }
	                
	                //12.11 get�K�p�I����(�s�ԍ� : int)
	                Date l_datAppliEndDate = l_accountDataUploadCsv.getAppliEndDate(i);
	                Timestamp l_tsAppliEndDate  = null;
	                if (l_datAppliEndDate  != null)
	                {
	                    l_tsAppliEndDate  = new Timestamp(l_datAppliEndDate.getTime());
	                }
	                
	                //12.12 get�o�^�敪(�s�ԍ� : int)
	                String l_strPaymentDiv = l_accountDataUploadCsv.getPaymentDiv(i);
	                
	                //12.13 get���p����(�s�ԍ� : int)
	                Double l_dblUseAmt = l_accountDataUploadCsv.getUseAmt(i);
	                
	                //12.14 get�o����(�s�ԍ� : int)
	                Date l_datPaymentDate = l_accountDataUploadCsv.getPaymentDate(i);
	                Timestamp l_tsPaymentDate = null;
	                if (l_datPaymentDate != null)
	                {
	                    l_tsPaymentDate = new Timestamp(l_datPaymentDate .getTime());
	                }
	                
	                //12.15 get�ڋq(String, String, String)
	                WEB3GentradeMainAccount l_mainAccount = 
	                    l_accountManager.getMainAccount(l_strInstitutionCode, l_strBranchCode, l_strAccountCode);
	
	                //12.16 getAccountCode( )
	                String l_strAccountCodeInDb = l_mainAccount.getAccountCode();

                    //get�T�[�r�X�}�X�^�[(String, String, boolean)
                    //�،���ЃR�[�h=get�،���ЃR�[�h�i�j�̖߂�l
                    //�T�[�r�X�敪=get�T�[�r�X�敪()�̖߂�l
                    //is�s���b�N=false
                    WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement =
                        new WEB3SrvRegiServiceInfoManagement();
                    WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
                        l_srvRegiServiceInfoManagement.getSrvMaster(
                            l_strInstitutionCode, l_strSrvDiv, false);

                    //�����򏈗����A�b�v���[�h�敪���V�K�o�^�̏ꍇ
                    if (WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_NEW_REGIST_LABEL.equals(l_strUploadDiv))
                    {
                        //validate����\��(�T�[�r�X�}�X�^�[, String, String, String, boolean)
                        //�T�[�r�X�}�X�^�[ = get�T�[�r�X�}�X�^�[()�̖߂�l
                        //�،���ЃR�[�h = get�،���ЃR�[�h()�̖߂�l
                        //���X�R�[�h = get���X�R�[�h()�̖߂�l
                        //�����R�[�h = getAccountCode( )
                        //�V�K�\���敪 = true
                        l_srvRegiServiceInfoManagement.validateSpecialApply(
                            l_srvRegiServiceMaster,
                            l_strInstitutionCode,
                            l_strBranchCode,
                            l_strAccountCodeInDb,
                            true);
                    }

	                //12.17 �����򏈗����A�b�v���[�h�敪���ύX�o�^�̏ꍇ�A�K�p���Ԃ̐������`�F�b�N������
	                if (WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_CHANGE_REGIST_LABEL.equals(l_strUploadDiv))
	                {
	                    //10.17.1 validate�K�p����(...)
	                    WEB3SrvRegiRegistService l_appliRegiService = 
	                        (WEB3SrvRegiRegistService) Services.getService(WEB3SrvRegiRegistService.class);
	                    l_appliRegiService.validateAppliPeriod(
	                        l_strInstitutionCode,
	                        l_strSrvDiv,
	                        l_strBranchCode,
	                        l_strAccountCodeInDb,
	                        l_tsAppliStartDate,
	                        l_tsAppliEndDate,
	                        new Long(l_strRegistId));

                        //validate����\��(�T�[�r�X�}�X�^�[, String, String, String, boolean)
                        //�T�[�r�X�}�X�^�[ = get�T�[�r�X�}�X�^�[�̖߂�l
                        //�،���ЃR�[�h = get�،���ЃR�[�h�̖߂�l
                        //���X�R�[�h = get���X�R�[�h�̖߂�l
                        //�����R�[�h = getAccountCode( )
                        //�V�K�\���敪 = false
                        l_srvRegiServiceInfoManagement.validateSpecialApply(
                            l_srvRegiServiceMaster,
                            l_strInstitutionCode,
                            l_strBranchCode,
                            l_strAccountCodeInDb,
                            false);
	                }
	                
	                WEB3AdminSrvRegiAccountDataUploadUnitService l_uploadUnitService = 
	                    (WEB3AdminSrvRegiAccountDataUploadUnitService) Services.getService(
	                        WEB3AdminSrvRegiAccountDataUploadUnitService.class);
	                //12.18
	                WEB3GentradeSubAccount l_subAccount = 
	                    (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

                    //�����򏈗����i�A�b�v���[�h�敪 == �A�b�v���[�h�敪_�\������ ���� get�\�����I�敪 != �����폜�j �̏ꍇ
                    if (WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_ATTRIBUTE_LABEL.equals(l_strUploadDiv)
                        && !WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_ATTRIBUTE_DELETE_LABEL.equals(
                           l_strAppliLotDiv))
                    {
                        //validate�����o�^���t�ݒ�
                        l_accountDataUploadCsv.validatePaymentFreeRegiDate(l_datAppliStartDate, l_datAppliEndDate);

                        //get�T�[�r�X�\���������
                        List l_lisAttributeInfo = l_srvRegiServiceInfoManagement.getServiceAppliAttributeInfo(
                            l_strInstitutionCode,
                            l_strBranchCode,
                            l_strAccountCode,
                            l_strSrvDiv,
                            l_strUploadDiv);

                        //get�T�[�r�X�\���������I�u�W�F�N�g == null �̏ꍇ
                        if (l_lisAttributeInfo == null)
                        {
                            //insert�T�[�r�X�\������(String, String, String, String, String, Timestamp, Timestamp)
                            l_uploadUnitService.insertSrvApplyAttribute(
                                l_strInstitutionCode,
                                l_strBranchCode,
                                l_strAccountCode,
                                l_strSrvDiv,
                                l_strAppliLotDiv,
                                l_tsAppliStartDate,
                                l_tsAppliEndDate);
                        }
                        //get�T�[�r�X�\���������I�u�W�F�N�g != null �̏ꍇ
                        else
                        {
                            //update�T�[�r�X�\������(String, String, String, String, String, Timestamp, Timestamp)
                            l_uploadUnitService.updateSrvApplyAttribute(
                                l_strInstitutionCode,
                                l_strBranchCode,
                                l_strAccountCode,
                                l_strSrvDiv,
                                l_strAppliLotDiv,
                                l_tsAppliStartDate,
                                l_tsAppliEndDate);
                        }
                    }
                    //�����򏈗����i�A�b�v���[�h�敪 == �A�b�v���[�h�敪_�\������ ���� get�\�����I�敪 == �����폜�j �̏ꍇ
                    else if (WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_ATTRIBUTE_LABEL.equals(l_strUploadDiv)
                        && WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_ATTRIBUTE_DELETE_LABEL.equals(
                           l_strAppliLotDiv))
                    {
                        int l_intDeleteSrvApplyAttribute = l_uploadUnitService.deleteSrvApplyAttribute(
                            l_strInstitutionCode,
                            l_strBranchCode,
                            l_strAccountCode,
                            l_strSrvDiv);

                        if (l_intDeleteSrvApplyAttribute <= 0)
                        {
                            log.debug("�폜�Y�����R�[�h�Ȃ��B");
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_02784,
                                this.getClass().getName() + STR_METHOD_NAME,
                                "�폜�Y�����R�[�h�Ȃ��B");
                        }
                    }
                    //�����򏈗�����L�ȊO�̏ꍇ�@@�i�A�b�v���[�h�敪 != �A�b�v���[�h�敪_�\�������j �̏ꍇ�j
                    else
                    {
                        //12.19 update�\���o�^
                        l_uploadUnitService.updateAppliRegist(
                            l_subAccount,
                            l_strUploadDiv,
                            l_strRegistId == null ? null : new Long(l_strRegistId), 
                            l_strInstitutionCode,
                            l_strSrvDiv,
                            l_strBranchCode,
                            l_strAccountCodeInDb,
                            l_tsAppliStartDate,
                            l_tsAppliEndDate,
                            l_tsAppliDate,
                            l_strAppliLotDiv,
                            l_strPaymentDiv,
                            l_dblUseAmt,
                            l_tsPaymentDate ,
                            l_request.password);
                    }
	            }
				catch(WEB3BaseException ex)
				{
					log.error("�A�b�v���[�h�G���[�D�X�V����", ex);
			
					//��O�����������ꍇ�A�A�b�v���[�h�G���[���X�V����
					//save�A�b�v���[�h�G���[(ErrorInfo)
					l_accountDataUploadCsv.saveUploadError(ex.getErrorInfo());
					
					//�A�b�v���[�hTemp�e�[�u���̃��R�[�h���폜
					l_accountDataUploadCsv.deleteUploadTemp();
				
					StringBuffer l_sbErrorMessage = new StringBuffer( 
						l_accountDataUploadCsv.getBranchCode(i)
						+ ","
						+ l_accountDataUploadCsv.getSrvDiv(i) 
						+ "," 
						+ l_accountDataUploadCsv.getAccountCode(i));
					if(ex.getErrorMessage() != null && ex.getErrorMessage().trim().length() != 0) 
					{
						l_sbErrorMessage.append("," + ex.getErrorMessage());
					}
                
					String l_strErrorMessage = l_sbErrorMessage.toString();    
                    
					//��Q�Ή� �G���[���b�Z�[�W�\���Ή�
					//throw�i��O�j
					throw new WEB3BaseException(
						 ex.getErrorInfo(),
						 this.getClass().getName() + STR_METHOD_NAME,
						 l_strErrorMessage);
				}
            }

            //13 save�A�b�v���[�h�I��( )
            l_accountDataUploadCsv.saveUploadEnd();
            
            //14 delete�A�b�v���[�hTemp( )
            l_accountDataUploadCsv.deleteUploadTemp();
            
            //15 �T�[�r�X���p�Ǘ��҃A�b�v���[�h�������X�|���X( )
            l_response = 
                (WEB3AdminSrvRegiUploadCompleteResponse)l_request.createResponse();
        
        }
        catch (NotFoundException l_e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        log.exiting(STR_METHOD_NAME);     
        return l_response;
    }
    
    /**
     * (undo�A�b�v���[�h)<BR>
     * �T�[�r�X���p�ڋq�A�b�v���[�h���~�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u(�T�[�r�X���p)�ڋq�A�b�v���[�h�Eundo�A�b�v���[�h�v�Q�ƁB <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiUploadCancelResponse
     * @@roseuid 410F7292020A
     */
    public WEB3AdminSrvRegiUploadCancelResponse undoUpload(
        WEB3AdminSrvRegiUploadCancelRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " undoUpload(WEB3AdminSrvRegiUploadCancelRequest)";
        log.entering(STR_METHOD_NAME );

		// validate
		l_request.validate();
        
        //1 �ڋq�f�[�^�A�b�v���[�hCSV(long)
        WEB3AdminSrvRegiAccountDataUploadCsv l_accountDataUploadCsv = 
            new WEB3AdminSrvRegiAccountDataUploadCsv(Long.parseLong(l_request.uploadId));

        //2 delete�A�b�v���[�hTemp( )
        l_accountDataUploadCsv.deleteUploadTemp();
        
        //3 save�A�b�v���[�h���~( )
        l_accountDataUploadCsv.saveUploadStop();
        
        //4 �T�[�r�X���p�Ǘ��҃A�b�v���[�h���~���X�|���X( )
        WEB3AdminSrvRegiUploadCancelResponse l_response = 
            (WEB3AdminSrvRegiUploadCancelResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);     
        return l_response;
    }
    
    /**
     * (validate���׍s)<BR>
     * ���׍s�i�A�b�v���[�h�f�[�^�j�̃`�F�b�N���s���B <BR>
     * <BR>
     * [validate���׍s()�Ɏw�肷�����] <BR>
     * �ڋq�f�[�^�A�b�v���[�hCSV�F���������ڋq�f�[�^�A�b�v���[�hCSV�j<BR>
     * ���׍s[]�F�@@���N�G�X�g�f�[�^.�A�b�v���[�h�t�@@�C��[] <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u(�T�[�r�X���p)�ڋq�A�b�v���[�h�Evalidate���׍s�v�Q�ƁB <BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u(�T�[�r�X���p)�ڋq�A�b�v���[�h�Evalidate���׍s�v): <BR>
     *         1.1.2.2:throw�i��O�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00992<BR>
     * ==========================================================<BR>
     * ========================================================<BR>
     *�V�[�P���X�}(�T�[�r�X���p)�ڋq�A�b�v���[�h�Evalidate���׍s�v): <BR>
     *        1.1.14.1get�T�[�r�X�\���o�^<BR>
     *        ��������=0���i�߂�l��null�j�łȂ��ꍇ�A��O���X���[����B�i2�d�o�^�G���[�j<BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00894<BR>
     *==========================================================<BR>
     *========================================================<BR>
     *�V�[�P���X�}(�T�[�r�X���p)�ڋq�A�b�v���[�h�Evalidate���׍s�v): <BR>
     *        1.1.15.1get�T�[�r�X�\���o�^<BR>
     *        �P�j �������ʁ�0���̏ꍇ�inull���ԋp�j�A��O���X���[����B�i�Ώۃ��R�[�h�Ȃ��G���[�j <BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00908<BR>
     *        �Q�j �������ʁ�0���̏ꍇ�i��null�j�A�擾�����T�[�r�X�\���o�^�f�[�^�̐\�����I�敪��<BR>
     *        �u���p�\���v�Ȃ��O���X���[����B�i���p���R�[�h�ύX�s�G���[�j <BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00909<BR>
     *==========================================================<BR>
     * 
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u(�T�[�r�X���p)�ڋq�A�b�v���[�h�Evalidate���׍s�v): <BR>
     *         1.1.23.2.1.1:getSrvLotInfo(String, String, Timestamp, int)<BR>
     *         get�T�[�r�X���I���()�̖߂�l��null�̏ꍇ�́A�\�������\�����ԓ��ł͂Ȃ��Ɣ��f���A<BR>
     *         ��O���X���[����B�i�\�����ݒ�G���[�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00993<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u(�T�[�r�X���p)�ڋq�A�b�v���[�h�Evalidate���׍s�v): <BR>
     *         1.2.2:�w���[�J���ϐ��u�V�K�o�^�����v > <BR>
     *         get�O���A�g���g�p����(�T�[�r�敪 : String)�̖߂�l�x�̏ꍇ�G���[<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_03029<BR>
     * ==========================================================<BR>
     * @@param l_accountDataUploadCsv - (�ڋq�f�[�^�A�b�v���[�hCSV)<BR>
     * �ڋq�f�[�^�A�b�v���[�hCSV�I�u�W�F�N�g<BR>
     * @@param l_strDetailsLines - (���׍s)<BR>
     * @@param l_admin  - (�Ǘ���)<BR>
     * @@return String<BR>
     * @@roseuid 411087DF0022
     */
    public String validateDetailsLine(
        WEB3AdminSrvRegiAccountDataUploadCsv l_accountDataUploadCsv, String[] l_strDetailsLines, WEB3Administrator l_admin)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateDetailsLine(WEB3AdminSrvRegiAccountDataUploadCsv , String[], WEB3Administrator)";
        log.entering(STR_METHOD_NAME );
        
        if (l_strDetailsLines == null)
        {
            log.exiting(STR_METHOD_NAME);
            String l_strErrorMessage = "�A�b�v���[�h�f�[�^null!";
            log.debug(l_strErrorMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME);            
        }
        
		// get�����\���敪()�R�[���t���O�i��x�ł��R�[��������true���ݒ肳���j
		boolean l_blnInitializeAppliDivCall 	= false;
		// get�����\���敪()�̌��ʃt���O�i��x�ł��g���h���ԋp������true���ݒ肳���j
		boolean l_blnInitializeAppliDivHaveNot	= false;
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        //1 �����򏈗������׍s[1]�`�e�v�f����LOOP����
        int l_intLineCount = l_strDetailsLines.length;
        long l_lngNewRegist = 0;
        WEB3SrvRegiServiceMaster l_serviceMaster = null;
        String l_strSrvDiv = null;
        for (int i = 1; i < l_intLineCount; i++)//QA WEB3-SRVREGI-A-FT-0100.xls
        {
            //1.1 add���׍s(String)
            int l_intRowNumber = 0;
            try
            {
                l_intRowNumber = l_accountDataUploadCsv.addRow(l_strDetailsLines[i]);
            }
            catch (WEB3SystemLayerException l_e)
            {
                //1.2 (*)add���׍s()�ŗ�O�����������ꍇ�A�A�b�v���[�h�G���[���X�V����
                //1.2.1 save�A�b�v���[�h�G���[(ErrorInfo)
                l_accountDataUploadCsv.saveUploadError(l_e.getErrorInfo());//WEB3SystemLayerException
                
                log.error("add���׍s�G���[�B", l_e);
                
                //��Q�Ή� �G���[���b�Z�[�W�\���Ή�
                //1.2.2 throw�i��O�j
                throw new WEB3BusinessLayerException(
                     WEB3ErrorCatalog.BUSINESS_ERROR_00992,
                     this.getClass().getName() + STR_METHOD_NAME,
                     l_strDetailsLines[i]);                              
            }
            
            try
            {
            	//��Q�Ή� NO_2055
            	//1.3 ��s�̏ꍇ(add���׍s�̖߂�l<0)
            	if(l_intRowNumber < 0)
            	{
            		//���Y�v�f�̏����𒆒f
            		continue;
            	}
            	
				//1.4 validate���͕K�{����
				l_accountDataUploadCsv.validateDispensableItem(l_intRowNumber);
                
                //1.5 get���X�R�[�h(int)
                String l_strBranchCode = l_accountDataUploadCsv.getBranchCode(l_intRowNumber);
                
                //1.6 validate���X����(String)
                l_admin.validateBranchPermission(l_strBranchCode);
                
                //1.7 get�A�b�v���[�h�敪(int)
                String l_strUploadDiv = l_accountDataUploadCsv.getUploadDiv(l_intRowNumber);
                
                //1.8 validate�A�b�v���[�h�敪(String)
                l_accountDataUploadCsv.validateUploadDiv(l_strUploadDiv);
                
                //1.9 get�\���o�^�h�c(int)
                String l_strRegistId = l_accountDataUploadCsv.getRegistId(l_intRowNumber);
                
                //1.10 get�،���ЃR�[�h(int)
                String l_strInstitutionCode = l_accountDataUploadCsv.getInstitutionCode(l_intRowNumber);
                
                //1.11 get�ڋq�R�[�h(int)
                String l_strAccountCode = l_accountDataUploadCsv.getAccountCode(l_intRowNumber);
                
                //1.12 validate�ڋq(String, String, String)
                l_accountDataUploadCsv.validateAccount(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                    
                //1.13 get�ڋq(String, String, String)
                WEB3GentradeMainAccount l_mainAccount = 
                    l_accountManager.getMainAccount(l_strInstitutionCode, l_strBranchCode, l_strAccountCode);

                //1.14 getAccountCode( )
                String l_strAccountCodeInDb = l_mainAccount.getAccountCode();
                
                //1.15 get�T�[�r�X�敪(int) 
                l_strSrvDiv = l_accountDataUploadCsv.getSrvDiv(l_intRowNumber);
                
                //get�A�b�v���[�h�敪()�̖߂�l��'3'�ȊO�̏ꍇ�ȉ��̏������s���B          
                //1.16 validate�T�[�r�X�敪(String, String)                               
                if(!WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_ATTRIBUTE_LABEL.equals(l_strUploadDiv))                                                                              
                {                                                                         
                	l_accountDataUploadCsv.validateSrvDiv(l_strInstitutionCode, l_strSrvDiv);
                }                                                                         

                //1.17 get�K�p�J�n��(int)
                Date l_datAppliStartDate = l_accountDataUploadCsv.getAppliStartDate(l_intRowNumber);
                Timestamp l_tsAppliStartDate = null;
                if (l_datAppliStartDate != null)
                {
                    l_tsAppliStartDate = new Timestamp(l_datAppliStartDate.getTime());
                }
                
                //1.18 get�K�p�I����(int)
                Date l_datAppliEndDate = l_accountDataUploadCsv.getAppliEndDate(l_intRowNumber);
                Timestamp l_tsAppliEndDate  = null;
                if (l_datAppliEndDate  != null)
                {
                    l_tsAppliEndDate  = new Timestamp(l_datAppliEndDate.getTime());
                }
                
                //get�T�[�r�X���p�\���o�^�T�[�r�X
                WEB3SrvRegiRegistService l_appliRegiService = 
                    (WEB3SrvRegiRegistService) Services.getService(WEB3SrvRegiRegistService.class);

                //1.19 get�T�[�r�X�}�X�^�[(String, String, boolean)
                WEB3SrvRegiServiceInfoManagement l_serviceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
                l_serviceMaster = l_serviceInfoManagement.getSrvMaster(
                    l_strInstitutionCode,
                    l_strSrvDiv,
                    false);;
                
                //1.20 get�\���v�T�[�r�X( )
                WEB3SrvRegiApplicationRequiredService l_requiredService = l_serviceMaster.getAppliRequiredSrv(false);
                if (l_requiredService == null)
                {
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        getClass().getName() + STR_METHOD_NAME);
                }

                String l_strLotSetup = l_requiredService.getLotDiv();
                
                String l_strPaymentDiv = null;

                if (!WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_ATTRIBUTE_LABEL.equals(l_strUploadDiv))
                {
                    //��Q�Ή� NO_2140
                    //1.21 get�o�^�敪(int)
                    l_strPaymentDiv = l_accountDataUploadCsv.getPaymentDiv(l_intRowNumber);

                    //1.22 validate�o�^�敪(String)
                    l_accountDataUploadCsv.validatePaymentDiv(l_strPaymentDiv);
                }

                //�����򏈗����i�A�b�v���[�h�敪���A�b�v���[�h�敪_���I���ʃA�b�v���[�h�j && 
                //�i�A�b�v���[�h�敪���A�b�v���[�h�敪_�\�������j�̏ꍇ�ɓ��t�ݒ�̃`�F�b�N���s��
				if (!WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_LOT_RESULT_UPLOAD_LABEL.equals(l_strUploadDiv)
                    && !WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_ATTRIBUTE_LABEL.equals(l_strUploadDiv))
				{
					//1.23.1 get�\����(int)
					Date l_datAppliDate = l_accountDataUploadCsv.getAppliDate(l_intRowNumber);

					//1.23.2 validate���t�ݒ�(String, String, String, Date, Date, Date)
					l_accountDataUploadCsv.validateTimestampSetup(
						l_strInstitutionCode, 
						l_strSrvDiv, 
						l_requiredService.getLotDiv(), 
						l_datAppliDate, 
						l_datAppliStartDate, 
						l_datAppliEndDate);
    
					//1.23.3 get���p����(int)
				  Double l_dblUseAmt = l_accountDataUploadCsv.getUseAmt(l_intRowNumber);
                    
					//1.23.4 validate���p����(double, String)
					if (l_dblUseAmt != null)
					{
					  l_accountDataUploadCsv.validateUseAmt(
						  l_dblUseAmt.doubleValue(), l_strPaymentDiv);
					}
				}

                //�����򏈗����i�A�b�v���[�h�敪 == �A�b�v���[�h�敪_�\�������j �̏ꍇ�ɓ��t�ݒ�̃`�F�b�N���s��
                if (WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_ATTRIBUTE_LABEL.equals(l_strUploadDiv))
                {
                    //validate�����o�^���t�ݒ�(Date, Date)
                    l_accountDataUploadCsv.validatePaymentFreeRegiDate(l_datAppliStartDate, l_datAppliEndDate);
                }

                //��Q�Ή� NO_2142
                //1.24 �����򏈗����V�K�o�^���A�T�[�r�X�\���o�^�f�[�^�̓o�^�L�����`�F�b�N����
                if (WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_NEW_REGIST_LABEL.equals(l_strUploadDiv))
                {
		    //��Q�Ή�
                    //1.24.1 get�T�[�r�X�\���o�^(String, String, String, String, String, String, boolean)
                    //QA:WEB3-SRVREGI-A-FT-0115
                    WEB3GentradeSrvRegiApplication l_srvRegiApp = l_appliRegiService.getServiceRegist(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strSrvDiv,
                        l_strAccountCodeInDb,
						WEB3SrvRegiCancelDivDef.USUAL_DEFAULT,
                        WEB3EffectiveDivDef.EFFECTIVE,
                        false);
                    if (l_srvRegiApp != null)
                    {
                        String l_strErrorMessage = "2�d�o�^�G���[�B";
                        log.debug(l_strErrorMessage);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00894, 
                            this.getClass().getName() + STR_METHOD_NAME,
                            l_strErrorMessage);            
                    }
                    
                    Long l_registId = null;
                    if (l_strRegistId != null)
                    {
                        l_registId = new Long(l_strRegistId);
                    }
                    //1.24.2 validate�K�p����(String, String, String, String, Timestamp, Timestamp, Long)
                    l_appliRegiService.validateAppliPeriod(
                        l_strInstitutionCode,
                        l_strSrvDiv,
                        l_strBranchCode,
                        l_strAccountCodeInDb,
                        l_tsAppliStartDate,
                        l_tsAppliEndDate,
                        l_registId);

                    //validate����\��(�T�[�r�X�}�X�^�[, String, String, String, boolean)
                    //�T�[�r�X�}�X�^�[ = get�T�[�r�X�}�X�^()�[�̖߂�l
                    //�،���ЃR�[�h =get�،���ЃR�[�h()�̖߂�l
                    //���X�R�[�h = get���X�R�[�h()�̖߂�l
                    //�����R�[�h = getAccountCode()�̖߂�l
                    //�V�K�\���敪 = true
                    l_serviceInfoManagement.validateSpecialApply(
                        l_serviceMaster,
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strAccountCodeInDb,
                        true);

                    //�u���[�J���ϐ��u�V�K�o�^�����v = ���[�J���ϐ��u�V�K�o�^�����v + 1�v
                    l_lngNewRegist = l_lngNewRegist + 1;
                }
                
                //1.24 �����򏈗����ύX�o�^���A�T�[�r�X�\���o�^�f�[�^�̓K�p���Ԃ̑Ó������`�F�b�N����
                if (WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_CHANGE_REGIST_LABEL.equals(l_strUploadDiv))
                {
                    //1.25.1 get�T�[�r�X�\���o�^(String, String, String, String, long, )
                    WEB3GentradeSrvRegiApplication l_srvRegiApp = l_appliRegiService.getServiceRegist(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strSrvDiv,
                        l_strAccountCodeInDb,
                        Long.parseLong(l_strRegistId),
                        false);
                    if (l_srvRegiApp == null)
                    {
                        String l_strErrorMessage = "�Ώۃ��R�[�h�Ȃ��G���[�B";
                        log.debug(l_strErrorMessage);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00908, 
                            this.getClass().getName() + STR_METHOD_NAME,
                            l_strErrorMessage);            
                    }
                    else
                    {
                        if (WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_TRIAL_LABEL.equals(l_srvRegiApp.getAppliLotDiv()))
                        {
                            String l_strErrorMessage = "���p���R�[�h�ύX�s�G���[�B";
                            log.debug(l_strErrorMessage);
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00909, 
                                this.getClass().getName() + STR_METHOD_NAME,
                                l_strErrorMessage);            
                        }
                    }
                    
                    //1.25.2 validate�K�p����(String, String, String, String, Timestamp, Timestamp, Long)
                    l_appliRegiService.validateAppliPeriod(
                        l_strInstitutionCode,
                        l_strSrvDiv,
                        l_strBranchCode,
                        l_strAccountCodeInDb,
                        l_tsAppliStartDate,
                        l_tsAppliEndDate,
                        new Long(l_strRegistId));

                    //validate����\��(�T�[�r�X�}�X�^�[, String, String, String, boolean)
                    //�T�[�r�X�}�X�^�[ = get�T�[�r�X�}�X�^�[()�̖߂�l
                    //�،���ЃR�[�h = get�،���ЃR�[�h()�̖߂�l
                    //���X�R�[�h = get���X�R�[�h()�̖߂�l
                    //�����R�[�h = getAccountCode()�̖߂�l
                    //�V�K�\���敪 = false
                    l_serviceInfoManagement.validateSpecialApply(
                        l_serviceMaster,
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strAccountCodeInDb,
                        false);
                }
                else if (WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_LOT_RESULT_UPLOAD_LABEL.equals(l_strUploadDiv))
                {
                    //1.26 ���I���ʃA�b�v���[�h���A�T�[�r�X�\���o�^�f�[�^�̓o�^�L�����`�F�b�N����
                    //1.26.1 get�T�[�r�X�\���o�^(String, String, String, String, long, )
                    WEB3GentradeSrvRegiApplication l_srvRegiApp = l_appliRegiService.getServiceRegist(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strSrvDiv,
                        l_strAccountCodeInDb,
                        Long.parseLong(l_strRegistId),
                        false);
                    if (l_srvRegiApp == null)
                    {
                        String l_strErrorMessage = "�Ώۃ��R�[�h�Ȃ��G���[�B";
                        log.debug(l_strErrorMessage);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00908, 
                            this.getClass().getName() + STR_METHOD_NAME,
                            l_strErrorMessage);            
                    }
                }
    
                //1.27 get�\�����I�敪(int)
                String l_strAppliLotDiv = l_accountDataUploadCsv.getAppliLotDiv(l_intRowNumber);
                
                //1.28 validate�\�����I�敪(String, String, String)
                l_accountDataUploadCsv.validateAppliLotDiv(
                    l_strUploadDiv, 
                    l_requiredService.getLotDiv(), 
                    l_strAppliLotDiv);
    
                //1.29 �����򏈗����A�b�v���[�h�敪���V�K�o�^ & �o�^�敪���L���̏ꍇ�ɏo�����̃`�F�b�N������
                if (WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_NEW_REGIST_LABEL.equals(l_strUploadDiv)
                    && WEB3AdminSrvRegiAccountDataUploadCsv.PAYMENT_DIV_CHARGE_LABEL.equals(l_strPaymentDiv))//WEB3-SRVREGI-A-FT-0106.xls
                {
                    //1.29.1 get�o����(int)
                    Date l_datPaymentDate = l_accountDataUploadCsv.getPaymentDate(l_intRowNumber);
                    Timestamp l_tsPaymentDate = null;
                    if (l_datPaymentDate != null)
                    {
                        l_tsPaymentDate = new Timestamp(l_datPaymentDate.getTime());
                    }
                    
                    //1.29.2 validate�o����(String, Date)
                    l_accountDataUploadCsv.validatePaymentDate(l_datPaymentDate);
                    
                    //1.29.3 validate�o���]��(String, String, String, double)
                    l_accountDataUploadCsv.validatePaymentPower(
                        l_strInstitutionCode, 
                        l_strBranchCode, 
                        l_strAccountCodeInDb, 
                        l_accountDataUploadCsv.getUseAmt(l_intRowNumber).doubleValue(),
                        l_tsPaymentDate);
                }
                
                // �����򏈗����A�b�v���[�h�敪���V�K�o�^�̏ꍇ�A�����\���敪�̃`�F�b�N���s���B
				if (WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_NEW_REGIST_LABEL.equals(l_strUploadDiv))
				{
					//1.30.4 get�����\���敪(String, String, String, String)
					String l_strInitializeAppliDiv = l_appliRegiService.getInitializeAppliDiv(
						l_strInstitutionCode, 
						l_strBranchCode, 
						l_strSrvDiv, 
						l_strAccountCodeInDb);

					// get�����\���敪()�R�[���t���O�ݒ�
					l_blnInitializeAppliDivCall = true;
					
					if(l_strInitializeAppliDiv.equals(WEB3ConditionsValueDivDef.HAVE_NOT))
					{
						// get�����\���敪()�̌��ʃt���O�ݒ�
						l_blnInitializeAppliDivHaveNot = true;                    
					}
				}

            }
            catch (WEB3BaseException l_e)
            {
                //1.31 get�A�b�v���[�h�敪()�`validate�o����()�̎葱���ŗ�O�����������ꍇ�A�A�b�v���[�h�G���[���X�V����
                                
                //1.31.1 save�A�b�v���[�h�G���[(ErrorInfo)
                l_accountDataUploadCsv.saveUploadError(l_e.getErrorInfo());

                StringBuffer l_sbErrorMessage = new StringBuffer( 
                    l_accountDataUploadCsv.getBranchCode(l_intRowNumber)
                    + ","
                    + l_accountDataUploadCsv.getSrvDiv(l_intRowNumber) 
                    + "," 
                    + l_accountDataUploadCsv.getAccountCode(l_intRowNumber));
                if(l_e.getErrorMessage() != null && l_e.getErrorMessage().trim().length() != 0) 
                {
                	l_sbErrorMessage.append("," + l_e.getErrorMessage());
                }
                
				String l_strErrorMessage = l_sbErrorMessage.toString();     
                log.error(l_strErrorMessage, l_e);
               	
                //1.31.2 throw�i��O�j
                throw new WEB3BaseException(
                     l_e.getErrorInfo(),
                     this.getClass().getName() + STR_METHOD_NAME,
                     l_strErrorMessage,
                     l_e);      
            }
            
        }

        //�����򏈗����T�[�r�X�}�X�^�[�I�u�W�F�N�g.���ꏈ���敪 = 1 (�O���A�g�T�[�r�X)�̏ꍇ
        //�ŏI�s�̃T�[�r�X�}�X�^�[�̒l
        String l_strSpecialProcessDiv = null;
        if (l_serviceMaster != null)
        {
            SrvRegiMasterRow l_srvRegiMasterRow =
                (SrvRegiMasterRow)l_serviceMaster.getDataSourceObject();
            l_strSpecialProcessDiv = l_srvRegiMasterRow.getSpecialProcessDiv();
        }

        if (WEB3SpecialProcessDivDef.OTHER_ORG_SERVICE.equals(l_strSpecialProcessDiv))
        {
            //�O���A�g���Ǘ��e�[�u���̃X�e�[�^�X�����g�p�̃��R�[�h�̌�����ԋp
            //�T�[�r�X�敪 = get�T�[�r�X�敪()�̖߂�l
            //�ŏI�s���擾�����T�[�r�X�敪�̒l
            WEB3SrvRegiOtherOrgService l_srvRegiOtherOrgService =
                (WEB3SrvRegiOtherOrgService)Services.getService(WEB3SrvRegiOtherOrgService.class);
            Long l_otherOrgUnUsedCount =
                l_srvRegiOtherOrgService.getOtherOrgUnUsedCount(l_strSrvDiv);

            //�w���[�J���ϐ��u�V�K�o�^�����v > get�O���A�g���g�p����(�T�[�r�敪 : String)�̖߂�l�x�̏ꍇ�G���[
            if (l_lngNewRegist > l_otherOrgUnUsedCount.longValue())
            {
                log.debug("�V�K�o�^�������O���A�g���g�p�����𒴂��Ă��܂��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03029,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�V�K�o�^�������O���A�g���g�p�����𒴂��Ă��܂��B");
            }
        }
		// �����\���敪������s���B
		// 1.2 ��LOOP�����ɂāAget�����\���敪()����x���R�[������Ȃ������ꍇ��
		log.exiting(STR_METHOD_NAME);
		if (!l_blnInitializeAppliDivCall)
		{
			// 1.2.1 NULL�����^�[������B
			return null;
		}
		// 1.3 ��LOOP�����ɂāA�P���ł�get�����\���敪()����"��"���ԋp���ꂽ�ꍇ��
		else if(l_blnInitializeAppliDivHaveNot)
		{
			// 1.3.1 �g���h�����^�[������B
			return WEB3ConditionsValueDivDef.HAVE_NOT;
		}
		// 1.4 ��LOOP�����ɂāA�P����get�����\���敪()����"��"���ԋp����Ȃ������ꍇ��
		else
		{
			// 1.4.1 �g�L�h�����^�[������B
			return WEB3ConditionsValueDivDef.HAVE;
		}
    }
}
@
