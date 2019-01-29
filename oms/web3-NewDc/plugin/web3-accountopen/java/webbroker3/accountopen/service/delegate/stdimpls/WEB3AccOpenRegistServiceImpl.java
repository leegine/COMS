head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.36.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݐ\���T�[�r�XImpl(WEB3AccOpenRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 �A�C��(���u) �V�K�쐬
                 : 2006/08/14 �����F(���u) �d�l�ύX ���f�� 090
Revesion History : 2009/08/10 �����F(���u) �d�l�ύX ���f��167
Revesion History : 2009/09/02 �����F(���u) �d�l�ύX ���f��205 208 �c�a�X�V�d�l 052
*/

package webbroker3.accountopen.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.IllegalSessionStateException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.accountopen.WEB3AccOpenExpAccountOpen;
import webbroker3.accountopen.data.MailAddressRegiDao;
import webbroker3.accountopen.data.MailAddressRegiParams;
import webbroker3.accountopen.data.MailAddressRegiRow;
import webbroker3.accountopen.define.WEB3AccOpenAccountCodeAutoFlagDef;
import webbroker3.accountopen.define.WEB3AccOpenAdminDivDef;
import webbroker3.accountopen.define.WEB3MailSendDivDef;
import webbroker3.accountopen.message.WEB3AccOpenApplyCompleteRequest;
import webbroker3.accountopen.message.WEB3AccOpenApplyCompleteResponse;
import webbroker3.accountopen.message.WEB3AccOpenApplyConfirmRequest;
import webbroker3.accountopen.message.WEB3AccOpenApplyConfirmResponse;
import webbroker3.accountopen.message.WEB3AccOpenApplyInputRequest;
import webbroker3.accountopen.message.WEB3AccOpenApplyInputResponse;
import webbroker3.accountopen.service.delegate.WEB3AccOpenInfoCreatedService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenRegistService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenRequestNumberService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccOpenAccountDivDef;
import webbroker3.common.define.WEB3EmailStatusDef;
import webbroker3.common.define.WEB3InstitutionPreferencesNameDef;
import webbroker3.common.define.WEB3SendmailDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3ValidateTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeMailInfo;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.InstitutionPreferencesRow;
import webbroker3.gentrade.data.MailProcParams;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����J�ݐ\���T�[�r�XImpl)<BR>
 * �����J�ݐ\���T�[�r�X�����N���X<BR>
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AccOpenRegistServiceImpl implements WEB3AccOpenRegistService 
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AccOpenRegistServiceImpl.class);
    
    /**
     * @@roseuid 41B45E7302FD
     */
    public WEB3AccOpenRegistServiceImpl() 
    {
     
    }
    
    /**
     * �����J�ݐ\�����������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�����J�ݐ\�����̓��N�G�X�g�̏ꍇ <BR>
     * �@@�|get���͉��()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�����J�ݐ\���m�F���N�G�X�g�̏ꍇ <BR>
     * �@@�|validate�\��()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�����J�ݐ\���������N�G�X�g�̏ꍇ<BR> 
     * �@@�|submit�\��()���R�[������B <BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 419C900600ED
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);        
        
        if (l_request instanceof WEB3AccOpenApplyInputRequest)
        {
            WEB3AccOpenApplyInputResponse l_response = 
                getInputScreen((WEB3AccOpenApplyInputRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AccOpenApplyConfirmRequest)
        {
            WEB3AccOpenApplyConfirmResponse l_response = 
                validateRegist((WEB3AccOpenApplyConfirmRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else if (l_request instanceof WEB3AccOpenApplyCompleteRequest)
        {
            WEB3AccOpenApplyCompleteResponse l_response = 
                submitRegist((WEB3AccOpenApplyCompleteRequest)l_request);
        
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
     * �����J�ݐ\�����͉�ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�����J�݁i�\���jget���͉�ʁv�Q�ƁB <BR>
     * ======================================================== <BR>
     * �V�[�P���X�} �F�����J�ݐ\�� / �����J�݁i�\���jget���͉�� <BR>
     * ��̈ʒu�F���[���A�h���X�o�^�s.email�A�h���X�I=���N�G�X�g.���[���A�h���X�̏ꍇ�A�G���[���X���[����B<BR>
     * class: WEB3BusinessLayerException <BR>
     * �@@tag: BUSINESS_ERROR_01789 <BR>
     * ========================================================== <BR>
     * ======================================================== <BR>
     * �V�[�P���X�} �F�����J�ݐ\�� / �����J�݁i�\���jget���͉�� <BR>
     * ��̈ʒu�F���N�G�X�g.���[���A�h���X�o�^ID�@@=�@@null ���́@@���N�G�X�g.���[���A�h���X=�@@null �̏ꍇ�A�G���[���X���[����B<BR>
     * class: WEB3BusinessLayerException <BR>
     * �@@tag: BUSINESS_ERROR_03183 <BR>
     * ========================================================== <BR>
     * @@param l_request - �����J�ݐ\�����̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * 
     * @@return webbroker3.accountopen.message.WEB3AccOpenApplyInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 419C8F7301B8
     */
    protected WEB3AccOpenApplyInputResponse getInputScreen(WEB3AccOpenApplyInputRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AccOpenApplyInputRequest)";
        log.entering(STR_METHOD_NAME );
        
        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        OpLoginSecurityService l_opLoginSec = (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
        long l_lngLoginId = 0;
        try
        {
            //1.2 getLoginId( )
            log.debug("getLoginId");
            
            l_lngLoginId = l_opLoginSec.getLoginInfo().getLoginId();//IllegalSessionStateException
        }
        catch (IllegalSessionStateException l_e)
        {
            log.debug("can't get login info.");
        }
        if (l_lngLoginId != 0)
        {
            //1.3 ���O�C���h�c���擾�ł����ꍇ�A�Ǘ��ғ��͂Ɣ��f�������`�F�b�N�����{����B
            //1.3.1 getInstanceFrom���O�C�����( )
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException
            
            //1.3.2 validate����(String, boolean)
            log.debug("validate����");
            l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN, true);
            
            //1.3.3 validate���X����(String)
            log.debug("validate���X����");
            l_admin.validateBranchPermission(l_request.branchCode);//WEB3BaseException
        }

        //1.4 validate������t�\( )
        log.debug("validate������t�\");
        WEB3GentradeTradingTimeManagement.validateOrderAccept();//WEB3BaseException
        
        //1.5 createResponse( )
        WEB3AccOpenApplyInputResponse l_response = (WEB3AccOpenApplyInputResponse)l_request.createResponse();

        //is���[���A�h���X�o�^�`�F�b�N���{
        boolean l_blnIsMailAddressRegiCheck = this.isMailAddressRegiCheck(l_request.institutionCode);

        if (l_blnIsMailAddressRegiCheck &&  !WEB3AccOpenAdminDivDef.ADMIN.equals(l_request.adminDiv))
        {
            // ���N�G�X�g.���[���A�h���X�o�^ID�@@=�@@null ���́@@���N�G�X�g.���[���A�h���X=�@@null �̏ꍇ
            if (WEB3StringTypeUtility.isEmpty(l_request.mailAddressID) || WEB3StringTypeUtility.isEmpty(l_request.mailAddress))
            {
                log.debug("���[���A�h���X�o�^ID ���̓��[���A�h���X�������͂ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                     WEB3ErrorCatalog.BUSINESS_ERROR_03183,
                     this.getClass().getName() + "." + STR_METHOD_NAME,
                     "���[���A�h���X�o�^ID ���̓��[���A�h���X�������͂ł��B");
            }
            //get���[���A�h���X�o�^�s
            MailAddressRegiParams l_mailAddressRegiParams =
                this.getMailAddressRegiParams(l_request.mailAddressID);

            //���[���A�h���X�o�^�s.email�A�h���X�I=���N�G�X�g.���[���A�h���X�̏ꍇ�A�G���[���X���[����B
            if (!l_request.mailAddress.equals(l_mailAddressRegiParams.getEmailAddress()))
            {
                log.debug("���[���A�h���X���m�F�p�̂��̂ƈ�v���Ă���܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                     WEB3ErrorCatalog.BUSINESS_ERROR_01789,
                     this.getClass().getName() + "." + STR_METHOD_NAME,
                     "���[���A�h���X���m�F�p�̂��̂ƈ�v���Ă���܂���B");
            }
            //���[���A�h���X�o�^�`�F�b�N���{�̏ꍇ
            //  ���@@�@@�@@�@@�@@�@@�@@�@@�@@���@@get���[���A�h���X�o�^�s()�̖߂�l.�ڋq���i�����j
            l_response.accountFamilyName = l_mailAddressRegiParams.getFamilyName();
            //  ���@@�@@�@@�@@�@@�@@�@@�@@�@@���@@get���[���A�h���X�o�^�s()�̖߂�l.�ڋq���i�����j
            l_response.accountName = l_mailAddressRegiParams.getGivenName();
            //  ���[���A�h���X�@@�@@�@@���@@get���[���A�h���X�o�^�s()�̖߂�l.email�A�h���X
            l_response.mailAddress = l_mailAddressRegiParams.getEmailAddress();
            //  ����ƈ��҃R�[�h�@@�@@���@@get���[���A�h���X�o�^�s()�̖߂�l.����ƈ��҃R�[�h
            l_response.brokerageCode = l_mailAddressRegiParams.getBrokerageTraderCode();
            //  �����敪�@@�@@�@@�@@�@@�@@���@@get���[���A�h���X�o�^�s()�̖߂�l.�����敪
            l_response.accountType = l_mailAddressRegiParams.getAccountDiv();
            //  �����N�����ʃR�[�h�@@���@@get���[���A�h���X�o�^�s()�̖߂�l.�����N�����ʃR�[�h
            l_response.linkCode = l_mailAddressRegiParams.getLinkDistinctionCode();
        }
        //��L�ȊO�̏ꍇ
        //�@@���@@�@@�@@�@@�@@�@@�@@�@@�@@���@@null
        //�@@���@@�@@�@@�@@�@@�@@�@@�@@�@@���@@null
        //�@@���[���A�h���X�@@�@@�@@���@@null
        //�@@����ƈ��҃R�[�h�@@�@@���@@null
        //�@@�����敪�@@�@@�@@�@@�@@�@@���@@null
        //�@@�����N�����ʃR�[�h�@@���@@null
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�\��)<BR>
     * �����J�ݐ\���m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�����J�݁i�\���jvalidate�\���v�Q�ƁB <BR>
     * @@param l_request - �����J�ݐ\���m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountopen.message.WEB3AccOpenApplyConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 419C8F7301C8
     */
    protected WEB3AccOpenApplyConfirmResponse validateRegist(WEB3AccOpenApplyConfirmRequest l_request)
        throws  WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateRegist(WEB3AccOpenApplyConfirmRequest)";
        log.entering(STR_METHOD_NAME );
        
        WEB3Administrator l_admin = null;
        
        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        OpLoginSecurityService l_opLoginSec = (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
        long l_lngLoginId = 0;
        try
        {
            //1.2 getLoginId( )
            log.debug("getLoginId");
            
            l_lngLoginId = l_opLoginSec.getLoginInfo().getLoginId();//IllegalSessionStateException            
        }
        catch (IllegalSessionStateException l_e)
        {
            log.debug("can't get login info.");
        }
        if (l_lngLoginId != 0)
        {
            //1.3 ���O�C���h�c���擾�ł����ꍇ�A�Ǘ��ғ��͂Ɣ��f�������`�F�b�N�����{����B
            //1.3.1 getInstanceFrom���O�C�����( )
            l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException
            
            //1.3.2 validate����(�@@�\�J�e�S���R�[�h�i=�����J�݁j : String, is�X�V�i=true�j : boolean)
            log.debug("validate����");
            l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN, true);
            
            //1.3.3 validate���X����(String)
            log.debug("validate���X����");
            l_admin.validateBranchPermission(l_request.accoutOpenApplyInfo.branchCode);//WEB3BaseException
        }
        
        //1.4 validate������t�\( )
        log.debug("validate������t�\");
        WEB3GentradeTradingTimeManagement.validateOrderAccept();//WEB3BaseException
        
        WEB3AccOpenInfoCreatedService l_infoCreatedService = 
            (WEB3AccOpenInfoCreatedService)Services.getService(WEB3AccOpenInfoCreatedService.class);        
        
        //1.5 to�����J�݌����q(�����J�ݐ\�����)
        WEB3AccOpenExpAccountOpen l_expAccountOpen = l_infoCreatedService.toAccOpenExpAccountOpen(l_request.accoutOpenApplyInfo);
        
        //1.6 validate�����J�ݐ\�����(String,String)
        if (l_admin != null)
        {
            l_expAccountOpen.validateAccountOpenRegistInfo(
                WEB3ValidateTypeDef.ADMINISTRATOR_REGIST,
                WEB3AccOpenAccountCodeAutoFlagDef.NOT_AUTO);
        }
        else
        {
            l_expAccountOpen.validateAccountOpenRegistInfo(
                WEB3ValidateTypeDef.DEFAULT,
                WEB3AccOpenAccountCodeAutoFlagDef.NOT_AUTO);
        }
        
        //1.7 createResponse( )
        WEB3AccOpenApplyConfirmResponse l_response = (WEB3AccOpenApplyConfirmResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit�\��)<BR>
     * �����J�ݐ\�������������s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�����J�݁i�\���jsubmit�\���v�Q�ƁB <BR>
     * @@param l_request - �����J�ݐ\���������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountopen.message.WEB3AccOpenApplyCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 419C8F7301CA
     */
    protected WEB3AccOpenApplyCompleteResponse submitRegist(WEB3AccOpenApplyCompleteRequest l_request) 
        throws  WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " submitRegist(WEB3AccOpenApplyCompleteRequest)";
        log.entering(STR_METHOD_NAME );
        
        WEB3Administrator l_admin = null;
        
        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        OpLoginSecurityService l_opLoginSec = (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
        long l_lngLoginId = 0;
        try
        {
            //1.2 getLoginId( )
            log.debug("getLoginId");
            
            l_lngLoginId = l_opLoginSec.getLoginInfo().getLoginId();//IllegalSessionStateException            
        }
        catch (IllegalSessionStateException l_e)
        {
            log.debug("can't get login info.");
        }
        if (l_lngLoginId != 0)
        {
            //1.3 ���O�C���h�c���擾�ł����ꍇ�A�Ǘ��ғ��͂Ɣ��f�������`�F�b�N�����{����B
            //1.3.1 getInstanceFrom���O�C�����( )
            l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException
        
            //1.3.2 validate����(�@@�\�J�e�S���R�[�h�i=�����J�݁j : String, is�X�V�i=true�j : boolean)
            log.debug("validate����");
            l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN, true);
        
            //1.3.3 validate���X����(String)
            log.debug("validate���X����");
            l_admin.validateBranchPermission(l_request.accoutOpenApplyInfo.branchCode);//WEB3BaseException

            //1.3.4 validate����p�X���[�h(String)
            l_admin.validateTradingPassword(l_request.password);//WEB3BaseException
        }

        
        //1.4 validate������t�\( )
        log.debug("validate������t�\");
        WEB3GentradeTradingTimeManagement.validateOrderAccept();//WEB3BaseException
        
        WEB3AccOpenInfoCreatedService l_infoCreatedService = 
            (WEB3AccOpenInfoCreatedService)Services.getService(WEB3AccOpenInfoCreatedService.class);        
        //1.5 to�����J�݌����q(�����J�ݐ\�����)
        WEB3AccOpenExpAccountOpen l_expAccountOpen = l_infoCreatedService.toAccOpenExpAccountOpen(l_request.accoutOpenApplyInfo);

        //1.6 validate�����J�ݐ\�����(String,String)
        if (l_admin != null)
        {
            l_expAccountOpen.validateAccountOpenRegistInfo(
                WEB3ValidateTypeDef.ADMINISTRATOR_REGIST,
                WEB3AccOpenAccountCodeAutoFlagDef.NOT_AUTO);
        }
        else
        {
            l_expAccountOpen.validateAccountOpenRegistInfo(
                WEB3ValidateTypeDef.DEFAULT,
                WEB3AccOpenAccountCodeAutoFlagDef.NOT_AUTO);
        }

        WEB3AccOpenRequestNumberService l_requestNumberService = 
            (WEB3AccOpenRequestNumberService)Services.getService(WEB3AccOpenRequestNumberService.class);
            
        //1.7 get�V�K���ʃR�[�h(String)
        String l_strNewRequestNumber = l_requestNumberService.getNewRequestNumber(l_request.accoutOpenApplyInfo.institutionCode);
        
        //1.8 saveNew�����J�݌����q(String, String, String, String)
        if (l_admin != null)
        {
            l_expAccountOpen.saveNewExpAccountOpen(WEB3ValidateTypeDef.ADMINISTRATOR_REGIST,
                 l_admin.getAdministratorCode(), l_admin.getAdministratorCode(), l_strNewRequestNumber);
        }        
        else if (l_request.accoutOpenApplyInfo.creatorCode != null)
        {
            l_expAccountOpen.saveNewExpAccountOpen(WEB3ValidateTypeDef.DEFAULT, 
                l_request.accoutOpenApplyInfo.creatorCode, l_request.accoutOpenApplyInfo.creatorCode, l_strNewRequestNumber);
        }
        else
        {
            l_expAccountOpen.saveNewExpAccountOpen(WEB3ValidateTypeDef.DEFAULT, null, null, l_strNewRequestNumber);
        }
        
        try
        {
            //1.9 ���[��(String, String, String)
            String l_strDiscernmentId = null;
            String l_strMailText = null;
            String l_strCorporateMailSendDiv = this.getCorporateMailSendDiv(l_request.accoutOpenApplyInfo.institutionCode);
            if (WEB3MailSendDivDef.SENDED.equals(l_strCorporateMailSendDiv)
                && WEB3AccOpenAccountDivDef.CORPORATE_ACCOUNT.equals(l_request.accoutOpenApplyInfo.accountType))
            {
                l_strDiscernmentId = "0";
                l_strMailText = l_request.accoutOpenApplyInfo.extItemText6;
            }
            else
            {
                l_strDiscernmentId = "----";
            }
            WEB3GentradeMailInfo l_mail = new WEB3GentradeMailInfo(
                l_expAccountOpen.getInstitutionCode(), 
                WEB3SendmailDivDef.ACCOPEN_APPLICATION_MAIL, 
                l_strDiscernmentId);//WEB3BaseException

            //1.10 ���[���I�u�W�F�N�g���擾�ł����ꍇ�̂ݏ������{
            MailProcParams l_mailProcParams = new MailProcParams();
            l_mailProcParams.setInstitutionCode(l_expAccountOpen.getInstitutionCode());
            l_mailProcParams.setBranchCode(l_expAccountOpen.getBranchCode());
            l_mailProcParams.setSendmailDiv(WEB3SendmailDivDef.ACCOPEN_APPLICATION_MAIL);
            l_mailProcParams.setDiscernmentId(l_strDiscernmentId);
            l_mailProcParams.setAccountCode("----");
            l_mailProcParams.setMailId(Long.parseLong(l_strNewRequestNumber));
            l_mailProcParams.setDate1(GtlUtils.getTradingSystem().getBizDate());
            l_mailProcParams.setName1(l_request.accoutOpenApplyInfo.accountFamilyName);
            l_mailProcParams.setName2(l_request.accoutOpenApplyInfo.accountName);
            l_mailProcParams.setStatus(WEB3EmailStatusDef.EMAIL_NOT_SEND);
            l_mailProcParams.setEmailAddress(l_request.accoutOpenApplyInfo.mailAddress);
            l_mailProcParams.setMailText(l_strMailText);
            l_mailProcParams.setDeleteFlag(BooleanEnum.FALSE);
            l_mailProcParams.setCreatedTimestamp(GtlUtils.getTradingSystem().getSystemTimestamp());
            l_mailProcParams.setLastUpdatedTimestamp(GtlUtils.getTradingSystem().getSystemTimestamp());

            //1.10.1 doInsertQuery(Row)
            Processors.getDefaultProcessor().doInsertQuery(l_mailProcParams);//DataException
        }
        catch (WEB3BusinessLayerException l_e)
        {
            log.debug("���[���I�u�W�F�N�g���擾�Ȃ�." + l_e.getErrorMessage());
        }
        catch (DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        } 
        
        //1.11 createResponse( )
        WEB3AccOpenApplyCompleteResponse l_response = 
            (WEB3AccOpenApplyCompleteResponse)l_request.createResponse();
            
        //1.12 �v���p�e�B�Z�b�g
        //���ʃR�[�h
        l_response.requestNumber = l_strNewRequestNumber;
        
        //���ݓ���
        l_response.currentDate = GtlUtils.getTradingSystem().getSystemTimestamp();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get���[���A�h���X�o�^�s)<BR>
     * ���[���A�h���X�o�^�f�[�^���擾����B <BR>
     * <BR>
     * �P�j�@@�ȉ��̏����ŁA���[���A�h���X�o�^�e�[�u���e�[�u������������B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���[���A�h���X�o�^ID �� ����.���[���A�h���X�o�^ID <BR>
     * <BR>
     * �������ʂ��擾�ł����ꍇ�A���[���A�h���X�o�^�s��ԋp����B <BR>
     * �ȊO�̏ꍇ�A�G���[���X���[����B<BR>
     * @@param l_strMailAddressRegiID - (���[���A�h���X�o�^ID)<BR>
     * ���[���A�h���X�o�^ID<BR>
     * @@return MailAddressRegiParams
     * @@throws WEB3BaseException
     */
    private MailAddressRegiParams getMailAddressRegiParams(String l_strMailAddressRegiID) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =  "getMailAddressRegiParams(String)";
        log.entering(STR_METHOD_NAME );
        
        String l_strWhere1 = " mail_address_regi_id = ? and delete_flag = ? ";
        Object[] l_objConds1 =  new Object[]{l_strMailAddressRegiID, BooleanEnum.FALSE};
        QueryProcessor l_queryProcessor = null;
        List l_lisRecordexcs1 = null;
        try
        {
        	l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecordexcs1 = l_queryProcessor.doFindAllQuery(
                MailAddressRegiRow.TYPE,
                l_strWhere1,
                l_objConds1);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." +STR_METHOD_NAME,
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
        
        //�Y���s�����݂���ꍇ�A�G���[���X���[����B
        if (l_lisRecordexcs1.isEmpty())
        {
        	log.error("���[���A�h���X���m�F�p�̂��̂ƈ�v���Ă���܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_01789,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "���[���A�h���X���m�F�p�̂��̂ƈ�v���Ă���܂���B");
        }

        log.exiting(STR_METHOD_NAME);
        return new MailAddressRegiParams((MailAddressRegiRow)l_lisRecordexcs1.get(0));
    }

    /**
     * (is���[���A�h���X�o�^�`�F�b�N���{)<BR>
     * ���[���A�h���X�o�^�`�F�b�N���s�����𔻒肷��B <BR>
     * <BR>
     * �P�j����.�،���ЃR�[�h�ɊY������،����ID���擾����B<BR>
     * <BR>
     * �Q�j�ȉ��̏����ŁA�،���Ѓv���t�@@�����X�e�[�u�����烌�R�[�h���擾����B<BR>
     * �@@�@@[����] <BR>
     * �@@�@@�،����ID = �P�j�Ŏ擾�����،����ID <BR>
     * �@@�@@�v���t�@@�����X�� = "accountopen.mailadd.reg.check.div" <BR>
     * �@@�@@�v���t�@@�����X�̒l = "1" <BR>
     * <BR>
     * �@@�@@���R�[�h���擾�ł����ꍇ�Atrue��ԋp����B<BR>
     * �@@�@@�ȊO�ꍇ�Afalse��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    private boolean isMailAddressRegiCheck(String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =  "isMailAddressRegiCheck(String)";
        log.entering(STR_METHOD_NAME );

        //�P�j����.�،���ЃR�[�h�ɊY������،����ID���擾����B
        long l_lngInstitutionId = 0;
        AccountManager l_accountManager = GtlUtils.getAccountManager();
        try
        {
            Institution l_institution = l_accountManager.getInstitution(l_strInstitutionCode);
            l_lngInstitutionId = l_institution.getInstitutionId();
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

        //�Q�j�ȉ��̏����ŁA�،���Ѓv���t�@@�����X�e�[�u�����烌�R�[�h���擾����B
        //�@@�@@[����]
        //�@@�@@�،����ID = �P�j�Ŏ擾�����،����ID
        //�@@�@@�v���t�@@�����X�� = "accountopen.mailadd.reg.check.div"
        //�@@�@@�v���t�@@�����X�̒l = "1"
        String l_strWhere = " institution_id = ? and name = ? and value = ? ";
        Object[] l_obj =  new Object[]{
            new Long(l_lngInstitutionId), WEB3InstitutionPreferencesNameDef.ACCOUNTOPEN_MAILADD_REG_CHECK_DIV, "1"};
        List l_lisRows = null;
        try
        {
            l_lisRows = Processors.getDefaultProcessor().doFindAllQuery(
                InstitutionPreferencesRow.TYPE,
                l_strWhere,
                l_obj);
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

        //���R�[�h���擾�ł����ꍇ�Atrue��ԋp����B
        if (!l_lisRows.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get�@@�l�p���[�����M�敪)<BR>
     * �@@�l�p���[�����M�̋敪���擾����B <BR>
     * <BR>
     * �P�j����.�،���ЃR�[�h�ɊY������،����ID���擾����B<BR>
     * <BR>
     * �Q�j�ȉ��̏����ŁA�،���Ѓv���t�@@�����X�e�[�u�����烌�R�[�h���擾����B<BR>
     * �@@�@@[����] <BR>
     * �@@�@@�،����ID = �P�j�Ŏ擾�����،����ID <BR>
     * �@@�@@�v���t�@@�����X�� = "accountopen.mail.send.div" <BR>
     * �@@�@@�v���t�@@�����X�̒l = "1" <BR>
     * <BR>
     * �@@�@@���R�[�h���擾�ł����ꍇ�A"1" ��ԋp����B<BR>
     * �@@�@@�ȊO�ꍇ�Anull��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    private String getCorporateMailSendDiv(String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =  "getCorporateMailSendDiv(String)";
        log.entering(STR_METHOD_NAME );

        //�P�j����.�،���ЃR�[�h�ɊY������،����ID���擾����B
        long l_lngInstitutionId = 0;
        AccountManager l_accountManager = GtlUtils.getAccountManager();
        try
        {
            Institution l_institution = l_accountManager.getInstitution(l_strInstitutionCode);
            l_lngInstitutionId = l_institution.getInstitutionId();
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

        //�Q�j�ȉ��̏����ŁA�،���Ѓv���t�@@�����X�e�[�u�����烌�R�[�h���擾����B
        //�@@�@@[����]
        //�@@�@@�،����ID = �P�j�Ŏ擾�����،����ID
        //�@@�@@�v���t�@@�����X�� = "accountopen.mail.send.div"
        //�@@�@@�v���t�@@�����X�̒l = "1"
        String l_strWhere = " institution_id = ? and name = ? and value = ? ";
        Object[] l_obj =  new Object[]{
            new Long(l_lngInstitutionId), WEB3InstitutionPreferencesNameDef.ACCOUNTOPEN_MAIL_SEND_DIV, "1"};
        List l_lisRows = null;
        try
        {
            l_lisRows = Processors.getDefaultProcessor().doFindAllQuery(
                InstitutionPreferencesRow.TYPE,
                l_strWhere,
                l_obj);
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

        //���R�[�h���擾�ł����ꍇ�A"1" ��ԋp����B
        if (!l_lisRows.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3MailSendDivDef.SENDED;
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }
}
@
