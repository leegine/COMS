head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.23.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressUploadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h�T�[�r�XImpl(WEB3AdminAccInfoMailAddressUploadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/14 ������ (���u) �V�K�쐬
                   2006/03/24 ������ (���u) ���f��No.102  
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AdminAccInfoMailAddressUploadCsv;
import webbroker3.accountinfo.define.WEB3AccInfoDeleteDivDef;
import webbroker3.accountinfo.define.WEB3AccInfoMailFlagDef;
import webbroker3.accountinfo.define.WEB3AccInfoUploadStateDivDef;
import webbroker3.accountinfo.message.WEB3AccInfoUploadHistoryUnit;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressUploadCancelRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressUploadCancelResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressUploadCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressUploadCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressUploadConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressUploadConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressUploadInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressUploadInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMailAddressUploadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h�T�[�r�XImpl)<BR>
 *  �Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h�T�[�r�X�����N���X<BR>
 * <BR>
 * @@author ������<BR>
 * @@version 1.0<BR>
 */
public class WEB3AdminAccInfoMailAddressUploadServiceImpl 
    extends WEB3AccInfoClientRequestService 
    implements WEB3AdminAccInfoMailAddressUploadService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        	WEB3AdminAccInfoMailAddressUploadServiceImpl.class);
	
    public WEB3AdminAccInfoMailAddressUploadServiceImpl()
    {

    }
    
    /**
     * ���[���A�h���X�A�b�v���[�h�������s���B<BR> 
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A���[���A�h���X����۰�ޓ���ظ��Ă̏ꍇ<BR> 
     * �@@�|get�A�b�v���[�h���()���R�[������B<BR>  
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A���[���A�h���X����۰�ފm�Fظ��Ă̏ꍇ<BR>
     * �@@�|validate�A�b�v���[�h�t�@@�C��()���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A���[���A�h���X����۰�ފ���ظ��Ă̏ꍇ<BR>
     * �@@�|submit�A�b�v���[�h�t�@@�C��()���R�[������B<BR> 
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A���[���A�h���X����۰�ޒ��~ظ��Ă̏ꍇ<BR>  
     *�@@�|undo�A�b�v���[�h()���R�[������B<BR>   
     *<BR>
     *���T�[�r�X���\�b�h�ɂė�O�����������ꍇ�́A <BR>
     *�@@��O�I�u�W�F�N�g�̒ǉ�������iWEB3BaseException.errorMessage�j<BR> 
     * �����X�|���X�f�[�^.errorMessage�ɃZ�b�g����B<BR> 
     * <BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse<BR>
     * @@throws WEB3BaseException<BR>
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        //�� �����̃��N�G�X�g�f�[�^���A���[���A�h���X����۰�ޓ���ظ��Ă̏ꍇ
        //�|get�A�b�v���[�h���()���R�[������B
        if (l_request instanceof WEB3AdminAccInfoMailAddressUploadInputRequest)
        {
            l_response = 
                this.getUploadScreen(
                    (WEB3AdminAccInfoMailAddressUploadInputRequest)l_request);
        }
        
		//�� �����̃��N�G�X�g�f�[�^���A���[���A�h���X����۰�ފm�Fظ��Ă̏ꍇ
		//�|validate�A�b�v���[�h�t�@@�C��()���R�[������B
        else if (l_request instanceof WEB3AdminAccInfoMailAddressUploadConfirmRequest)
        {
            l_response = 
                this.validateUploadFile(
                    (WEB3AdminAccInfoMailAddressUploadConfirmRequest)l_request);
        }
        
        // �� �����̃��N�G�X�g�f�[�^���A���[���A�h���X����۰�ފ���ظ��Ă̏ꍇ
        //�|submit�A�b�v���[�h�t�@@�C��()���R�[������B
        else if (l_request instanceof WEB3AdminAccInfoMailAddressUploadCompleteRequest)
        {
            l_response = 
                this.submitUploadFile(
                    (WEB3AdminAccInfoMailAddressUploadCompleteRequest)l_request);
        }
        
        //�� �����̃��N�G�X�g�f�[�^���A���[���A�h���X����۰�ޒ��~ظ��Ă̏ꍇ
        //�|undo�A�b�v���[�h()���R�[������B
        else if (l_request instanceof WEB3AdminAccInfoMailAddressUploadCancelRequest)
        {
            l_response = 
                this.undoUpload(
                    (WEB3AdminAccInfoMailAddressUploadCancelRequest)l_request);
        }
        else
        {
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
     * ���[���A�h���X�A�b�v���[�h��ʕ\���������s���B<BR> 
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i���[���A�h���X�t�k�jget�A�b�v���[�h��ʁv�Q�ƁB <BR>
     * <BR>
     * @@param l_request - �Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h���̓��N�G�X�g<BR>
     * @@throws WEB3BaseException
     * @@return WEB3AdminAccInfoMailAddressUploadInputResponse<BR>
     */
    protected WEB3AdminAccInfoMailAddressUploadInputResponse getUploadScreen(
        WEB3AdminAccInfoMailAddressUploadInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " getUploadScreen(WEB3AdminAccInfoMailAddressUploadInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[validate����()�Ɏw�肷�����] 
        //	�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�ڋq��{���i��{�j 
        //	is�X�V�F�@@true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, true);

        //1.3 validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.4 ���[���A�h���X�A�b�v���[�hCSV()
        WEB3AdminAccInfoMailAddressUploadCsv l_uploadCsv = 
            new WEB3AdminAccInfoMailAddressUploadCsv();

        //1.5 get�A�b�v���[�h�ŐV����(�i�Ǘ��ҋ��ʁj�A�b�v���[�h�s[])
        //�A�C�e���̒�`
        //	�擾�����A�b�v���[�h�����̈ꗗ����w���l1�x�Ɂw���~�x 
        //	���܂܂Ȃ��ŐV�̃f�[�^���擾����B
        AdministratorUploadRow l_uploadRow = l_uploadCsv.getUploadNewHistory(0L);

        //1.6(*1�j�A�b�v���[�h���������݂���ꍇ�A�������{)
        //1.7 �Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h���X�|���X()
    	WEB3AdminAccInfoMailAddressUploadInputResponse l_response = 
            (WEB3AdminAccInfoMailAddressUploadInputResponse) l_request.createResponse();

        if (l_uploadRow != null)
        {
        	//1.6.1 ���q�l���A�b�v���[�h���𖾍�()(
        	WEB3AccInfoUploadHistoryUnit l_historyUnit = 
        	    new WEB3AccInfoUploadHistoryUnit();
        	
        	//1.6.2 (*1.1)�v���p�e�B�Z�b�g
        	//(*1.1) ���q�l���A�b�v���[�h���𖾍׃��b�Z�[�W�I�u�W�F�N�g�v���p�e�B�Ɉȉ��̒ʂ�l���Z�b�g����B
        	//�@@�� �A�b�v���[�h�s�F�@@get�A�b�v���[�h�ŐV�����̖߂�l
        	//�@@�A�b�v���[�h������ԋ敪�F
        	if (l_uploadRow.getUploadEndTimestamp() == null)
        	{
            	//�i�A�b�v���[�h�s.�A�b�v���[�h�I������ == null�j�̏ꍇ�A�h�A�b�v���[�h���h
        		l_historyUnit.uploadStateDiv = 
        		    WEB3AccInfoUploadStateDivDef.UPOAD_CONFIRMING;
        	}
        	else
        	{
            	//�i�A�b�v���[�h�s.�A�b�v���[�h�I������ != null�j�̏ꍇ�A�h�A�b�v���[�h�ρh 
        		l_historyUnit.uploadStateDiv = 
        		    WEB3AccInfoUploadStateDivDef.UPLOAD_COMPLETE;
        	}
        	
        	//�@@�A�b�v���[�h�����F�@@�A�b�v���[�h�s.�A�b�v���[�h����
        	l_historyUnit.uploadNumber = "" + l_uploadRow.getUploadCount();
        	
        	//�@@�A�b�v���[�h�J�n�����F�@@�A�b�v���[�h�s.�A�b�v���[�h�J�n����
        	l_historyUnit.uploadStartDate = l_uploadRow.getUploadStartTimestamp();
        	
        	//�@@�A�b�v���[�h�I�������F�@@�A�b�v���[�h�s.�A�b�v���[�h�I������
        	l_historyUnit.uploadEndDate = l_uploadRow.getUploadEndTimestamp();
        	
        	//�@@���q�l�G���[�ԍ��F�A�b�v���[�h�s.���b�Z�[�W�R�[�h
        	l_historyUnit.accInfoErrorId = l_uploadRow.getMessageCode();
        	
        	//1.8 (*2)�v���p�e�B�Z�b�g
        	//(*2) ���X�|���X�f�[�^�v���p�e�B�ɒl���Z�b�g����B
        	//�@@�A�b�v���[�h�����ꗗ�F�@@
        	//�@@�@@�A�b�v���[�h���������݂���ꍇ�A���q�l���A�b�v���[�h���𖾍׃I�u�W�F�N�g�B
        	l_response.uploadHistoryList = l_historyUnit;
        }
        else
        {
        	// �A�b�v���[�h���������݂��Ȃ��ꍇ�Anull�B
        	l_response.uploadHistoryList = null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�A�b�v���[�h�t�@@�C��)<BR>
     * ���[���A�h���X�A�b�v���[�h�m�F�������s���B<BR> 
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i���[���A�h���X�t�k�jvalidate�A�b�v���[�h�t�@@�C���v�Q�ƁB<BR> 
     * <BR>
     * @@param l_request - �Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h�m�F���N�G�X�g<BR>
     * @@throws WEB3BaseException
     * @@return WEB3AdminAccInfoMailAddressUploadConfirmResponse<BR>
     */
    protected WEB3AdminAccInfoMailAddressUploadConfirmResponse validateUploadFile(
        WEB3AdminAccInfoMailAddressUploadConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " validateUploadFile(WEB3AdminAccInfoMailAddressUploadConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1  validate()
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[validate����()�Ɏw�肷�����] 
        //	�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�ڋq��{���i��{�j
        //	is�X�V�F�@@true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, true);

        //1.4 validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.5 ���[���A�h���X�A�b�v���[�hCSV()
        WEB3AdminAccInfoMailAddressUploadCsv l_uploadCsv = 
            new WEB3AdminAccInfoMailAddressUploadCsv();

        //1.6 validate�����A�b�v���[�h(�A�b�v���[�h�h�c : long)
        //[validate�����A�b�v���[�h()�Ɏw�肷�����]  
        //	�A�b�v���[�h�h�c�F�@@null
        l_uploadCsv.validateSameTimeUpload(null);
        
        try
        {
            //1.7 check���׍s��(l_request)
            l_uploadCsv.checkDetailLines(l_request);
        }
        catch (WEB3BaseException l_exp)
        {
            //1.8 check���׍s��()�ŗ�O���X���[���ꂽ�ꍇ
            //check���׍s��()�ɂė�O���X���[���ꂽ�ꍇ  
            //�@@�|�A�b�v���[�h�G���[���X�V����B  
            //�@@�|��ʂɗ�O���X���[����B
            log.error("check���׍s��()�ɂė�O���X���[���ꂽ�ꍇ ", l_exp);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            	l_exp.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exp.getErrorMessage());
        }

        //1.9 get�Ǘ��҃R�[�h()
        String l_strAdministratorCode = l_administrator.getAdministratorCode();
        
        //1.10 get�،���ЃR�[�h()
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.11 save�A�b�v���[�h�J�n(�f�[�^�L�[ : long, ���l�P : String, ���l�Q : String, ���l�R : String, �X�V�҃R�[�h : String)
        //[save�A�b�v���[�h�J�n()�Ɏw�肷�����]  
        //	�f�[�^�L�[�F�@@0  
        //	���l�P�F�@@null  
        //	���l�Q�F�@@null�@@  
        //	���l�R�F�@@null  
        //	�X�V�҃R�[�h�F�@@�Ǘ���.get�Ǘ��҃R�[�h()
        l_uploadCsv.saveUpLoadStart(
            0, null, null, null, l_strAdministratorCode);

        //1.12(*1) ���N�G�X�g�f�[�^.�A�b�v���[�h�t�@@�C��[]�̊e�v�f����LOOP����
        int l_intCount = 0;
        if (l_request.uploadFile != null)
        {
        	l_intCount = l_request.uploadFile.length;
        }

        for (int i = 0; i < l_intCount; i++)
        {
        	int l_intLineNumber = 0;
        	try
        	{
            	//1.12.1 add���׍s(���׍s������ : String)
            	//[add���׍s()�Ɏw�肷�����]  
            	//	���׍s������F�@@���N�G�X�g�f�[�^.�A�b�v���[�h�t�@@�C��[index]
        		l_intLineNumber = l_uploadCsv.addRow(l_request.uploadFile[i]);
        	}
        	catch (WEB3SystemLayerException l_exp)
        	{
            	//1.12.2(*1.1) add���׍s()�ŗ�O���X���[���ꂽ�ꍇ
        		//save�A�b�v���[�h�G���[(�G���[��� : ErrorInfo)
        		//[save�A�b�v���[�h�G���[()�Ɏw�肷�����]  
        		//	�G���[���F�@@�icatch������O�j.getErrorInfo()
        		l_uploadCsv.saveUploadError(l_exp.getErrorInfo());
        		
        		//1.12.2.2throw(��O)
                log.error("add���׍s()�ŗ�O���X���[���ꂽ�ꍇ ", l_exp);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                	l_exp.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_exp.getErrorMessage());
        	}
        	
        	//1.12.3 (*) ��s�̏ꍇ�iadd���׍s()�̖߂�l < 0�j�A���Y�v�f�̏����𒆒f�icontinue;�j
        	if (l_intLineNumber < 0)
        	{
        		continue;
        	}
        	
        	try
        	{
                //1.12.4  validate���׍s(long, String)(���[���A�h���X�A�b�v���[�hCSV::validate���׍s)
                //[validate���׍s()�Ɏw�肷�����]  
                //  �s�ԍ��F�@@�iadd���׍s()�̖߂�l�j  
                //  �،���ЃR�[�h�F�@@get�،���ЃR�[�h()
                l_uploadCsv.validateDetailLine(l_intLineNumber, l_strInstitutionCode);
                
            	//1.12.5 validate�d���ڋq(long)(���[���A�h���X�A�b�v���[�hCSV::validate�d���ڋq)
            	//[validate�d���ڋq()�Ɏw�肷�����]  
            	//	�s�ԍ��F�@@�iadd���׍s()�̖߂�l�j 
            	l_uploadCsv.validateDuplicateAccount(l_intLineNumber);
        	}
        	catch (WEB3BaseException l_exp)
        	{
            	//1.12.6(*1.2) validate���׍s()�Cvalidate�d���ڋq()�ɂė�O���X���[���ꂽ�ꍇ
        		//save�A�b�v���[�h�G���[(�G���[��� : ErrorInfo)
        		//[save�A�b�v���[�h�G���[()�Ɏw�肷�����]  
        		//	�G���[���F�@@�icatch������O�j.getErrorInfo()
        		l_uploadCsv.saveUploadError(l_exp.getErrorInfo());
        		
        		//���X�R�[�h
        		String l_strBranchCode = l_uploadCsv.getBranchCode(l_intLineNumber);
        		
        		//�ڋq�R�[�h
        		String l_strAccountCode = 
        		    l_uploadCsv.getAccountCode(l_intLineNumber);
        		
        		//1.12.6.2 throw�i��O�j
                log.error("validate���׍s()�Cvalidate�d���ڋq()�ɂė�O���X���[���ꂽ�ꍇ ", l_exp);
                log.exiting(STR_METHOD_NAME);
                log.error(l_exp.getErrorMessage(), l_exp);
                throw new WEB3BusinessLayerException(
                	l_exp.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_strBranchCode + "," + l_strAccountCode,
                    l_exp);
        	}
        }

        //1.13 get�A�b�v���[�h�h�c( )
        String l_strUploadId = "" + l_uploadCsv.getAdministratorUploadId();
        
        //1.14 save�A�b�v���[�hTemp()
        l_uploadCsv.saveUploadTemp();
        
        //1.15 createResponse()
        WEB3AdminAccInfoMailAddressUploadConfirmResponse l_response = 
            (WEB3AdminAccInfoMailAddressUploadConfirmResponse) l_request.createResponse();

        //1.16 (*)�v���p�e�B�Z�b�g
        l_response.uploadNumber = String.valueOf(l_uploadCsv.getRowCount());
        l_response.uploadID = l_strUploadId;

        log.exiting(STR_METHOD_NAME);
    	return l_response;
    }
    
    /**
     * (submit�A�b�v���[�h�t�@@�C��)<BR>
     * ���[���A�h���X�A�b�v���[�h�����������s���B<BR> 
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i���[���A�h���X�t�k�jsubmit�A�b�v���[�h�t�@@�C���v�Q�ƁB<BR> 
     * <BR>
     * @@param l_request - �Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h�������N�G�X�g<BR>
     * @@throws WEB3BaseException
     * @@return WEB3AdminAccInfoMailAddressUploadCompleteResponse<BR>
     */
    protected WEB3AdminAccInfoMailAddressUploadCompleteResponse submitUploadFile(
        WEB3AdminAccInfoMailAddressUploadCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " submitUploadFile(WEB3AdminAccInfoMailAddressUploadCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1  validate()
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[validate����()�Ɏw�肷�����] 
        //	�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�ڋq��{���i��{�j 
        //	is�X�V�F�@@true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, true);

        //1.4 validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.5 validate����p�X���[�h(�p�X���[�h : String)
        //[validate����p�X���[�h()�Ɏw�肷�����]  
        //	�p�X���[�h�F�@@���N�G�X�g�f�[�^.�Ïؔԍ�
        l_administrator.validateTradingPassword(l_request.password);

        //1.6 ���[���A�h���X�A�b�v���[�hCSV()
        WEB3AdminAccInfoMailAddressUploadCsv l_uploadCsv = 
            new WEB3AdminAccInfoMailAddressUploadCsv();
        
        //1.7 validate�����A�b�v���[�h(�A�b�v���[�h�h�c : long)
        //[validate�����A�b�v���[�h()�Ɏw�肷�����]  
        //	�A�b�v���[�h�h�c�F�@@���N�G�X�g�f�[�^.�A�b�v���[�h�h�c
        l_uploadCsv.validateSameTimeUpload(new Long(l_request.uploadID));
        
        //1.8 setDataFrom�A�b�v���[�hTemp(�A�b�v���[�h�h�c : long)
        //[setDataFrom�A�b�v���[�hTemp()�Ɏw�肷�����]  
        //	�A�b�v���[�h�h�c�F�@@���N�G�X�g�f�[�^.�A�b�v���[�h�h�c
        l_uploadCsv.setDataFromUploadTemp(Long.parseLong(l_request.uploadID));
        
        //1.9 get���׍s��( )
        int l_intLineNumber = l_uploadCsv.getRowCount();
        
        //1.10(*1) ���׍s�̐���LOOP����
        for (int i = 0; i < l_intLineNumber; i++)
        {
        	//1.10.1 get���X�R�[�h(int)(���[���A�h���X�A�b�v���[�hCSV::get���X�R�[�h)
        	//[get���X�R�[�h()�Ɏw�肷�����]  
        	//	�s�ԍ��F�@@index
        	String l_strBranchCode = l_uploadCsv.getBranchCode(i);
        	
        	//1.10.2 get�ڋq(int, String)(���[���A�h���X�A�b�v���[�hCSV::get�ڋq)
        	//[get�ڋq()�Ɏw�肷�����]  
        	//	�s�ԍ��F�@@index  
        	//	�،���ЃR�[�h�F�@@�Ǘ���.get�،���ЃR�[�h()
        	MainAccount l_mainAccount = 
        	    l_uploadCsv.getAccount(i, l_administrator.getInstitutionCode());
        	
        	//1.10.3 getAccountId()
        	l_mainAccount.getAccountId();
        	
        	//1.10.4 get���[���A�h���X(int)(���[���A�h���X�A�b�v���[�hCSV::get���[���A�h���X)
        	//[get���[���A�h���X()�Ɏw�肷�����]  
        	//	�s�ԍ��F�@@index
        	String l_strMailAddress = l_uploadCsv.getMailAddress(i);
        	
        	//1.10.5 get�ē����[�����M�t���O(int)(���[���A�h���X�A�b�v���[�hCSV::get�ē����[�����M�t���O)
        	//[get�ē����[�����M�t���O()�Ɏw�肷�����]  
        	//	�s�ԍ��F�@@index
        	String l_strMailSendFlag = 
        	    l_uploadCsv.getInformationMailFlag(i);

        	//1.10.6 get�폜�敪(int)(���[���A�h���X�A�b�v���[�hCSV::get�폜�敪)
        	//[get�����t���O()�Ɏw�肷�����]  
        	//	�s�ԍ��F�@@index
        	String l_strDeleteDiv = l_uploadCsv.getDeleteDiv(i);
        	
        	try
        	{
        		MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();       		
        		MainAccountParams l_mainAccountParams = 
        		    new MainAccountParams(l_mainAccountRow);

        		//1.10.7 doUpdateQuery(arg0 : Row)
            	QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            	if (WEB3AccInfoDeleteDivDef.INFORMATION_MAIL_FLAG_UPDATE.equals(l_strDeleteDiv))
            	{
            		if (l_strMailAddress != null)
            		{
                		//email�A�h���X
                		l_mainAccountParams.setEmailAddress(
                		    l_strMailAddress);

            			//email�A�h���X�X�V�҃R�[�h
                		l_mainAccountParams.setEmailLastUpdater(
                		    l_administrator.getAdministratorCode());
                		
                		//email�A�h���X�X�V����
                		l_mainAccountParams.setEmailLastUpdatedTimestamp(
                		    GtlUtils.getSystemTimestamp());
            		}

            		//�X�V����
            		l_mainAccountParams.setLastUpdatedTimestamp(
            		    GtlUtils.getSystemTimestamp());
            		
            		if (l_strMailSendFlag != null)
            		{
                		//�ē����[�����M�t���O
            			BooleanEnum l_blnEnum = BooleanEnum.TRUE;
            			if (WEB3AccInfoMailFlagDef.FALSE.equals(l_strMailSendFlag))
            			{
            				l_blnEnum = BooleanEnum.FALSE;
            			}
                		l_mainAccountParams.setInformationMailFlag(l_blnEnum);
            			
            			//�ē����[�����M�t���O�X�V�҃R�[�h
                		l_mainAccountParams.setInfMailFlgLastUpdater(
                		    l_administrator.getAdministratorCode());
                		
                		//�ē����[�����M�t���O�X�V����
                		l_mainAccountParams.setInfMailFlgUpdatedTimestamp(
                		    GtlUtils.getSystemTimestamp());
            		}
            	}
            	else if (WEB3AccInfoDeleteDivDef.INFORMATION_MAIL_FLAG_DELETE.equals(l_strDeleteDiv))
            	{
            		//�ē����[�����M�t���O = 0�F���M�s�v
            		l_mainAccountParams.setInformationMailFlag(BooleanEnum.FALSE);
            		
            		//email�A�h���X = null
            		l_mainAccountParams.setEmailAddress(null);
            		
            		//email�A�h���X�X�V�҃R�[�h
            		l_mainAccountParams.setEmailLastUpdater(
            		    l_administrator.getAdministratorCode());
            		
            		//email�A�h���X�X�V����
            		l_mainAccountParams.setEmailLastUpdatedTimestamp(
            		    GtlUtils.getSystemTimestamp());
            		
            		//�X�V����
            		l_mainAccountParams.setLastUpdatedTimestamp(
            		    GtlUtils.getSystemTimestamp());
            		
            		//�ē����[�����M�t���O�X�V�҃R�[�h
            		l_mainAccountParams.setInfMailFlgLastUpdater(
            		    l_administrator.getAdministratorCode());
            		
            		//�ē����[�����M�t���O�X�V����
            		l_mainAccountParams.setInfMailFlgUpdatedTimestamp(
            		    GtlUtils.getSystemTimestamp());
            	}

            	l_QueryProcessor.doUpdateQuery(l_mainAccountParams);
        	}
        	catch (DataNetworkException l_exp)
        	{
                log.error(l_exp.getMessage(), l_exp);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_exp.getMessage(),
                    l_exp);
        	}
        	catch (DataQueryException l_exp)
        	{
                log.error(l_exp.getMessage(), l_exp);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_exp.getMessage(),
                    l_exp);
        	}
        }
        
        //1.11 save�A�b�v���[�h�I��()
        l_uploadCsv.saveUploadEnd();

        //1.12 delete�A�b�v���[�hTemp()
        l_uploadCsv.deleteUploadTemp();

        //1.13 createResponse()
        WEB3AdminAccInfoMailAddressUploadCompleteResponse l_response = 
            (WEB3AdminAccInfoMailAddressUploadCompleteResponse) l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (undo�A�b�v���[�h)<BR>
     * ���[���A�h���X�A�b�v���[�h���~�������s���B<BR> 
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i���[���A�h���X�t�k�jundo�A�b�v���[�h�t�@@�C���v�Q�ƁB <BR>
     * <BR>
     * @@param l_request - �Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h���~���N�G�X�g<BR>
     * @@throws WEB3BaseException
     * @@return WEB3AdminAccInfoMailAddressUploadCancelResponse<BR>
     */
    protected WEB3AdminAccInfoMailAddressUploadCancelResponse undoUpload(
        WEB3AdminAccInfoMailAddressUploadCancelRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " undoUpload(WEB3AdminAccInfoMailAddressUploadCancelRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 ���[���A�h���X�A�b�v���[�hCSV(long)
        //[�R���X�g���N�^�̈���]  
        //	�A�b�v���[�h�h�c�F�@@���N�G�X�g�f�[�^.�A�b�v���[�h�h�c 
        WEB3AdminAccInfoMailAddressUploadCsv l_uploadCsv = 
            new WEB3AdminAccInfoMailAddressUploadCsv(
            	Long.parseLong(l_request.uploadID));
        
        //1.2 delete�A�b�v���[�hTemp()
        l_uploadCsv.deleteUploadTemp();
        
        //1.3 �A�b�v���[�h���~��DB�ɍX�V����B
        l_uploadCsv.updateUploadEnd();

        //1.4 createResponse()
        WEB3AdminAccInfoMailAddressUploadCancelResponse l_response = 
            (WEB3AdminAccInfoMailAddressUploadCancelResponse) l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
