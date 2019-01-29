head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.28.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConAccountMngServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �O�������Ǘ��T�[�r�X�����N���X(WEB3AdminFEqConAccountMngServiceImpl)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/03/21 ����(���u) �V�K�쐬
 */

package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.aio.WEB3FEqConTransferDataControlService;
import webbroker3.aio.data.FeqAccountParams;
import webbroker3.aio.define.WEB3AioAccountOpenCompleteDef;
import webbroker3.aio.message.WEB3AdminFEqConAccountInfoSearchInputRequest;
import webbroker3.aio.message.WEB3AdminFEqConAccountInfoSearchInputResponse;
import webbroker3.aio.message.WEB3AdminFEqConAccountInfoSearchRequest;
import webbroker3.aio.message.WEB3AdminFEqConAccountInfoSearchResponse;
import webbroker3.aio.message.WEB3AdminFEqConAccountStateChangeCompleteRequest;
import webbroker3.aio.message.WEB3AdminFEqConAccountStateChangeCompleteResponse;
import webbroker3.aio.message.WEB3AdminFEqConAccountStateChangeConfirmRequest;
import webbroker3.aio.message.WEB3AdminFEqConAccountStateChangeConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AdminFEqConAccountMngService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.define.WEB3AccountOpenDef;
import webbroker3.common.define.WEB3AioAccountDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�������Ǘ��T�[�r�XImpl)<BR>
 * �O�������Ǘ��T�[�r�X�����N���X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3AdminFEqConAccountMngServiceImpl implements WEB3AdminFEqConAccountMngService 
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFEqConAccountMngServiceImpl.class);
    
    /**
     * �O�������Ǘ��������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���R�[������B<BR>
     * <BR>
     *   get���͉��()<BR>
     *   get�������()<BR>
     *   validate�󋵕ύX()<BR>
     *   submit�󋵕ύX()
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E3B66A008E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
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
        if (l_request instanceof WEB3AdminFEqConAccountInfoSearchInputRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u�O��������񌟍��������̓��N�G�X�g�v�̏ꍇ
            WEB3AdminFEqConAccountInfoSearchInputResponse l_inputResponse =
                this.getInputScreen((WEB3AdminFEqConAccountInfoSearchInputRequest) l_request);
            log.exiting(STR_METHOD_NAME);
            return l_inputResponse;
        }
        else if (l_request instanceof WEB3AdminFEqConAccountInfoSearchRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u�O��������񌟍����N�G�X�g�v�̏ꍇ
            WEB3AdminFEqConAccountInfoSearchResponse l_searchResponse =
                this.getSearchScreen((WEB3AdminFEqConAccountInfoSearchRequest) l_request);
            log.exiting(STR_METHOD_NAME);
            return l_searchResponse;
        }
        else if (l_request instanceof WEB3AdminFEqConAccountStateChangeConfirmRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u�O�������J�ݏ󋵕ύX���ʃ��N�G�X�g�v�̏ꍇ
            WEB3AdminFEqConAccountStateChangeConfirmResponse l_commonResponse =
                this.validateStateChange((WEB3AdminFEqConAccountStateChangeConfirmRequest) l_request);
            log.exiting(STR_METHOD_NAME);
            return l_commonResponse;
        }
        else if (l_request instanceof WEB3AdminFEqConAccountStateChangeCompleteRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u�O�������J�ݏ󋵕ύX�������N�G�X�g�v�̏ꍇ
            WEB3AdminFEqConAccountStateChangeCompleteResponse l_completeResponse =
                this.submitStateChange((WEB3AdminFEqConAccountStateChangeCompleteRequest) l_request);
            log.exiting(STR_METHOD_NAME);
            return l_completeResponse;
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s��");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }
    
    /**
     * (get���͉��)<BR>
     * ���͉�ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O�������Ǘ��jget���͉�ʁv �Q��
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3AdminFEqConAccountInfoSearchInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E3B9CF0338
     */
    protected WEB3AdminFEqConAccountInfoSearchInputResponse getInputScreen(
        WEB3AdminFEqConAccountInfoSearchInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "getInputScreen(WEB3AdminFEqConAccountInfoSearchInputRequest l_request";
        log.entering(STR_METHOD_NAME);
        //1.1 validate( )  ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( ) getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ACCOUNT_MANAGE, false);
        
        //1.4 validate���X����(String)
        l_admin.validateBranchPermission(l_request.branchCode);
        
        //1.5  createResponse( ) ���X�|���X�f�[�^�𐶐�����B
        WEB3AdminFEqConAccountInfoSearchInputResponse l_response =
            (WEB3AdminFEqConAccountInfoSearchInputResponse) l_request.createResponse();
            
        log.exiting(STR_METHOD_NAME); 
        return l_response;
    }
    
    /**
     * (get�������)<BR>
     * ������ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O�������Ǘ��jget������ʁv �Q��
     * ------------------------------------------------
     * 1.6 get�O�������ڋq(String, String, String)
     *  null���ԋp���ꂽ�ꍇ�A��O���X���[����B
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01944<BR>
     * <BR>
     * ------------------------------------------------
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3AdminFEqConAccountInfoSearchResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E3B9CF0357
     */
    protected WEB3AdminFEqConAccountInfoSearchResponse getSearchScreen(
        WEB3AdminFEqConAccountInfoSearchRequest l_request) 
            throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "getSearchScreen(WEB3AdminFEqConAccountInfoSearchRequest l_request)";
        log.entering(STR_METHOD_NAME);
       
        //1.1 validate( )  ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( ) getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
       
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ACCOUNT_MANAGE, false);
        
        //1.4 validate���X����(String)
        l_admin.validateBranchPermission(l_request.branchCode);
        
        //1.5 get�،���ЃR�[�h( ) �،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6 get�O�������ڋq(String, String, String)
        WEB3FEqConTransferDataControlService l_service =
            (WEB3FEqConTransferDataControlService) Services.getService(
                WEB3FEqConTransferDataControlService.class);
        FeqAccountParams l_feqAccountParams = null;
        try
        {
            l_feqAccountParams =
                l_service.getFeqAccountByFeqAccountCode(
                    l_strInstitutionCode,
                    l_request.branchCode,
                    l_request.fstkAccountCode);
        }
        catch (NotFoundException l_ex)
        {
			log.error("__NotFound FeqAccountParams__", l_ex);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01387,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
        }    
       
	   //1.7 get�ڋq() �ڋq���擾����B 
	    FinApp l_finApp = (FinApp) Services.getService(FinApp.class); 
	    WEB3GentradeAccountManager l_gentradeAccMgr =
		    (WEB3GentradeAccountManager) l_finApp.getAccountManager();
		    
	    String l_emailAddress;
	    boolean l_mainAccountFlg = true;
	    try
	    {
			MainAccount l_mainAccount = 
				l_gentradeAccMgr.getMainAccount(
				   l_feqAccountParams.getInstitutionCode(),
				   l_feqAccountParams.getBranchCode(),
				   l_feqAccountParams.getAccountCode());
				   
			MainAccountRow l_mainAccountRow = 
				(MainAccountRow)l_mainAccount.getDataSourceObject();
			l_emailAddress = l_mainAccountRow.getEmailAddress();			  	    	
	    }
	    catch(WEB3BaseException l_ex)
	    {
			l_emailAddress = null;
			l_mainAccountFlg = false;
	    }
         
        //1.8 createResponse( ) ���X�|���X�f�[�^�𐶐�����B
        WEB3AdminFEqConAccountInfoSearchResponse l_response =
            (WEB3AdminFEqConAccountInfoSearchResponse) l_request.createResponse();
        
        //1.9  (*)�v���p�e�B�Z�b�g
        // (*)���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        //���X�|���X�f�[�^.���X�R�[�h  ���@@�O�������ڋq.���X�R�[�h
        l_response.branchCode = l_feqAccountParams.getBranchCode();
        //���X�|���X�f�[�^.�ڋqID  ���@@�O�������ڋq.�O�������ڋqID
        l_response.accountId = l_feqAccountParams.getFeqAccountId() + "";
        //���X�|���X�f�[�^.�O�������ԍ�  ���@@�O�������ڋq.�O�����������ԍ�
        l_response.fstkAccountCode = l_feqAccountParams.getFeqAccountCode();
        //���X�|���X�f�[�^.���O(��)  ���@@�O�������ڋq.���O(��)
        l_response.familyName = l_feqAccountParams.getLastName();
        //���X�|���X�f�[�^.���O(��)  ���@@�O�������ڋq.���O(��)
        l_response.name = l_feqAccountParams.getFirstName();
        //���X�|���X�f�[�^.���[���A�h���X  ���@@�ڋq.email�A�h���X
        //get�ڋq()�ɂė�O�����������ꍇ�A�uNULL�v���Z�b�g
		l_response.mailAddress = l_emailAddress;
        //���X�|���X�f�[�^.�ڋq�R�[�h  ���@@�O�������ڋq.�ڋq�R�[�h�̏�6��
        l_response.accountCode = l_feqAccountParams.getAccountCode().substring(0, 6);
        //���X�|���X�f�[�^.�����J�ݏ󋵋敪 ���@@�O�������ڋq.�O�����������敪
        //get�ڋq()�ɂė�O�����������ꍇ�A�u99(��������)�v���Z�b�g
        if (l_mainAccountFlg)
        {
			l_response.accountOpenStatusDiv = l_feqAccountParams.getAccountOpenDiv();
        }
        else
        {
			l_response.accountOpenStatusDiv = WEB3AioAccountOpenCompleteDef.OPEN_DELETE;
        }
        
        log.exiting(STR_METHOD_NAME); 
        return l_response;
    }
    
    /**
     * (validate�󋵕ύX)<BR>
     * �����J�ݏ󋵕ύX�̊m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O�������Ǘ��jvalidate�󋵕ύX�v �Q��
     * ------------------------------------------------
     * 1.4 get�O�������ڋq(String)
     *  null���ԋp���ꂽ�ꍇ�A��O���X���[����B
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01944<BR>
     * <BR>
     * ------------------------------------------------
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3AdminFEqConAccountStateChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E3B9CF0376
     */
    protected WEB3AdminFEqConAccountStateChangeConfirmResponse validateStateChange(
        WEB3AdminFEqConAccountStateChangeConfirmRequest l_request) 
            throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "validateStateChange(" +
            "WEB3AdminFEqConAccountStateChangeConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )  ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( ) getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ACCOUNT_MANAGE, true);
       
        //1.4 get�O�������ڋq(String)
        WEB3FEqConTransferDataControlService l_service =
            (WEB3FEqConTransferDataControlService) Services.getService(
                WEB3FEqConTransferDataControlService.class);
        FeqAccountParams l_feqAccountParams = null;
        try
        {
            l_feqAccountParams = 
                l_service.getFeqAccountByAccountId(l_request.accountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFound FeqAccountParams__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }   

        //1.5 validate���X����(String[]) ���X�����̃`�F�b�N���s���B 
        l_admin.validateBranchPermission(l_feqAccountParams.getBranchCode());
        
        //1.6 createResponse( ) ���X�|���X�f�[�^�𐶐�����B
        WEB3AdminFEqConAccountStateChangeConfirmResponse l_response = 
            (WEB3AdminFEqConAccountStateChangeConfirmResponse) l_request.createResponse();
            
        log.exiting(STR_METHOD_NAME);    
        return l_response;
    }
    
    /**
     * (submit�󋵕ύX)<BR>
     * �����J�ݏ󋵕ύX�̊����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O�������Ǘ��jsubmit�󋵕ύX�v �Q��
     * ------------------------------------------------
     * 1.4 get�O�������ڋq(String)
     *  null���ԋp���ꂽ�ꍇ�A��O���X���[����B
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01944<BR>
     * <BR>
     * ------------------------------------------------
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3AdminFEqConAccountStateChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E3B9CF0386
     */
    protected WEB3AdminFEqConAccountStateChangeCompleteResponse submitStateChange(
        WEB3AdminFEqConAccountStateChangeCompleteRequest l_request) 
            throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "submitStateChange(" +
            "WEB3AdminFEqConAccountStateChangeCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )  ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( ) getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ACCOUNT_MANAGE, true);
        
        //1.4 get�O�������ڋq(String)
        WEB3FEqConTransferDataControlService l_service =
            (WEB3FEqConTransferDataControlService) Services.getService(
                WEB3FEqConTransferDataControlService.class);
        FeqAccountParams l_feqAccountParams = null;
        try
        {
            l_feqAccountParams = 
                l_service.getFeqAccountByAccountId(l_request.accountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFound FeqAccountParams__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }    
        //1.5 validate���X����(String[]) ���X�����̃`�F�b�N���s���B 
        l_admin.validateBranchPermission(l_feqAccountParams.getBranchCode());
        
        //1.6 validate����p�X���[�h(String) �Ïؔԍ��̃`�F�b�N���s���B
        l_admin.validateTradingPassword(l_request.password);
        
        //1.7 get�،���ЃR�[�h( ) �،���ЃR�[�h���擾����B
        
        //1.8 get�Ǘ��҃R�[�h( ) �Ǘ��҃R�[�h���擾����B
        String l_administratorcode = l_admin.getAdministratorCode();
        
        //1.9 update�O�������ڋq(�O�������ڋqParams, String, String)  
        //�O�������ڋq�e�[�u�����X�V����B
        l_service.updateFeqAccount(
            l_feqAccountParams, 
            l_request.updatedAccountOpenStatusDiv, 
            l_administratorcode);
            
        //1.10 (*)���N�G�X�g�f�[�^.�X�V������J�ݏ󋵋敪 == "�J�ݍ�"�̏ꍇ
        if (WEB3AioAccountDivDef.OPEN_COMPLETE.equals(l_request.updatedAccountOpenStatusDiv))
        {
            //1.10.1 get�ڋq() �ڋq�I�u�W�F�N�g���擾����B 
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class); 
            WEB3GentradeAccountManager l_gentradeAccMgr =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            MainAccount l_mainAccount =
                l_gentradeAccMgr.getMainAccount(
                    l_feqAccountParams.getInstitutionCode(),
                    l_feqAccountParams.getBranchCode(),
                    l_feqAccountParams.getAccountCode());
            MainAccountRow l_mainAccountRow = 
                (MainAccountRow)l_mainAccount.getDataSourceObject();                    
                        
            String l_strFeqAccOpenDiv = null;
            //MRF�������J��(get�ڋq()�̖߂�l.MRF�����J�݋敪 == "DEFAULT(�����Ȃ�)")�̏ꍇ�A"�J��" 
            if(WEB3AccountOpenDef.NOT_OPEN.equals(l_mainAccountRow.getMrfAccOpenDiv()))
            {
                l_strFeqAccOpenDiv = WEB3AccountOpenDef.OPEN;
            }
            //����ȊO�̏ꍇ�A"���J��" 
            else
            {
                l_strFeqAccOpenDiv = WEB3AccountOpenDef.NOT_OPEN;
            }
            //1.10.2 update�O�������J�݋敪(String, String, String, String, String) 
            //�ڋq�̊O���،������J�݋敪��"�J��"�ɍX�V����B
            //�،���ЃR�[�h�F�@@get�O�������ڋq()�̖߂�l.�،���ЃR�[�h 
            //���X�R�[�h�F�@@get�O�������ڋq()�̖߂�l.���X�R�[�h 
            //�ڋq�R�[�h�F�@@get�O�������ڋq()�̖߂�l.�ڋq�R�[�h 
            //�X�V��O�������J�݋敪�F�@@�i�ȉ��̂Ƃ���j 
            //MRF�������J��(get�ڋq()�̖߂�l.MRF�����J�݋敪 == "DEFAULT(�����Ȃ�)")�̏ꍇ�A"�J��" 
            //����ȊO�̏ꍇ�A"���J��" 
            //�X�V�҃R�[�h�F�@@get�Ǘ��҃R�[�h()�̖߂�l
            l_service.updateFeqAccountOpenDiv(
                l_feqAccountParams.getInstitutionCode(),
                l_feqAccountParams.getBranchCode(),
                l_feqAccountParams.getAccountCode(),
                l_strFeqAccOpenDiv,
                l_administratorcode);            
        }

        //1.11 (*)���N�G�X�g�f�[�^.�X�V������J�ݏ󋵋敪 == "����"�̏ꍇ
        if (WEB3AioAccountDivDef.DELETE.equals(l_request.updatedAccountOpenStatusDiv))
        {
            //1.11.1 update�O�������J�݋敪(String, String, String, String, String) 
            //�ڋq�̊O���،������J�݋敪��"���J��"�ɍX�V����
            l_service.updateFeqAccountOpenDiv(
                l_feqAccountParams.getInstitutionCode(),
                l_feqAccountParams.getBranchCode(),
                l_feqAccountParams.getAccountCode(),
                WEB3AccountOpenDef.DELETED,
                l_administratorcode);
        }

        //1.12 createResponse( ) ���X�|���X�f�[�^�𐶐�����B
        WEB3AdminFEqConAccountStateChangeCompleteResponse l_response =
            (WEB3AdminFEqConAccountStateChangeCompleteResponse) l_request.createResponse();      
            
        log.exiting(STR_METHOD_NAME);    
        return l_response;
    }
}
@
