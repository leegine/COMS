head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.23.04.50.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1cc4d897c1210b4;
filename	WEB3AdminMailInfoRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[�����o�^�T�[�r�XImpl(WEB3AdminMailInfoRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  鰊](���u) �V�K�쐬
*/
package webbroker3.mailinfo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeMailInfo;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.MailProcParams;
import webbroker3.gentrade.data.MailProcRow;
import webbroker3.mailinfo.WEB3AdminMailInfoClientRequestService;
import webbroker3.mailinfo.WEB3AdminMailInfoManager;
import webbroker3.mailinfo.message.WEB3AdminMailInfoRegistCompleteRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoRegistCompleteResponse;
import webbroker3.mailinfo.message.WEB3AdminMailInfoRegistConfirmRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoRegistConfirmResponse;
import webbroker3.mailinfo.service.delegate.WEB3AdminMailInfoRegistService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���[�����o�^�T�[�r�XImpl)<BR>
 * ���[�����o�^�T�[�r�X�����N���X<BR>
 * 
 * @@author 鰊]
 * @@version 1.0.1 
 */
public class WEB3AdminMailInfoRegistServiceImpl 
        extends WEB3AdminMailInfoClientRequestService 
            implements WEB3AdminMailInfoRegistService 
{    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static  WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3AdminMailInfoRegistServiceImpl.class);            

    /**
     * @@roseuid 416F1DC90271
     */
    public WEB3AdminMailInfoRegistServiceImpl() 
    {
     
    }
    
    /**
     * ���[�����o�^�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�ɂ���āAvalidate���[�����o�^( )�܂���<BR>
     * submit���[�����o�^( )���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C2C8003A9
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if(l_request instanceof WEB3AdminMailInfoRegistCompleteRequest)
        {
            l_response = this.submitMailInfoRegist((WEB3AdminMailInfoRegistCompleteRequest)l_request);
           
        }
        else if (l_request instanceof WEB3AdminMailInfoRegistConfirmRequest)
        {
            l_response = this.validateMailInfoRegist((WEB3AdminMailInfoRegistConfirmRequest)l_request);
        }
        else
        {
            log.error(STR_METHOD_NAME + 
                WEB3ErrorCatalog.SYSTEM_ERROR_80018.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);        
        return l_response;    
    }
    
    /**
     * (validate���[�����o�^)<BR>
     * ���[�����o�^�R���������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i���[�����o�^�jvalidate�v�Q��<BR>
     * <BR>
     * =============================================== <BR>
     *         �V�[�P���X�} :  �u�i���[�����o�^�jvalidate�v          <BR>
     *         ��̈ʒu    :  1.6 isDIR�Ǘ���( )                     <BR>
     *         ���O�C���Ǘ��҂�DIR�Ǘ��҂��ǂ����`�F�b�N����B<BR>
     *        �iisDIR�Ǘ���( )==false�̏ꍇ�A��O���X���[����B�j<BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00857           <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     *         �V�[�P���X�} :   �u�i���[�����o�^�jvalidate�v         <BR>
     *         ��̈ʒu    :  1.8 getMail(String, String, String)    <BR>
     *         �o�^�Ώۂ̃��[�������ɓo�^����Ă��Ȃ����`�F�b�N����B<BR>
     *         �iget���[��( )�̖߂�l!=null�̏ꍇ�A<BR>
     *          [�v���O����ID�E����ID�d���G���[]��O���X���[����B�j<BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00860          <BR>
     * =============================================== <BR>
     * @@param  l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return webbroker3.mailinfo.message.WEB3AdminMailInfoRegistConfirmResponse
     * @@roseuid 413C2CE601C5
     */
    protected WEB3AdminMailInfoRegistConfirmResponse validateMailInfoRegist(
                    WEB3AdminMailInfoRegistConfirmRequest  l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
             " validateMailInfoRegist(WEB3AdminMailInfoRegistConfirmRequest  l_request)";
        log.entering(STR_METHOD_NAME);         
        
        WEB3AdminMailInfoRegistConfirmResponse l_response = null;
        WEB3Administrator l_administartor = null;    
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.MAILINFO, true);
        
        //1.4 isMailAddress(���N�G�X�g�f�[�^.���o�l)
        if (l_request.mailFrom != null)
        {
            boolean l_blnAddress = WEB3StringTypeUtility.isMailAddress(l_request.mailFrom);
            if(!l_blnAddress)
            {
                log.error(STR_METHOD_NAME +
                    WEB3ErrorCatalog.BUSINESS_ERROR_00777.error_message);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00777, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }
       
        //1.5 ���N�G�X�g�f�[�^.���M�惁�[���A�h���X��null�̏ꍇ�Ƀ`�F�b�N������
        if (l_request.sendAddress != null )
        {
            //1.5.1 ��؂蕶���P�ʂ��Ƃ�String[]�ɕ�������B
            String l_strCharacter = ",";
            String[] l_lisSendAddress = l_request.sendAddress.split(l_strCharacter);
            
            //1.5.2 String[]��10���̏ꍇ�A��O���X���[����B
            if(l_lisSendAddress.length > 10)
            {
                log.error(STR_METHOD_NAME + 
                    WEB3ErrorCatalog.BUSINESS_ERROR_00877.error_message);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00877, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            //1.5.3 String[]�̗v�f�����A1�����Ƃɕ������`�F�b�N�A���[���A�h���X�`�F�b�N���s�Ȃ��B
            for(int i = 0; i < l_lisSendAddress.length; ++i)
            {
                //1.5.3.1 1���̕�������100byte�̏ꍇ�A��O���X���[����B
                if(WEB3StringTypeUtility.getByteLength(l_lisSendAddress[i]) > 100)
                {
                    log.error(STR_METHOD_NAME + 
                        WEB3ErrorCatalog.BUSINESS_ERROR_00877.error_message);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00877, 
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                
                //1.5.3.2 isMailAddress(String[index])
                boolean l_blnmailAddress = WEB3StringTypeUtility.isMailAddress(l_lisSendAddress[i]);
                if (!l_blnmailAddress)
                {
                    log.error(STR_METHOD_NAME + 
                        WEB3ErrorCatalog.BUSINESS_ERROR_00777.error_message);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00777, 
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
        }
        
        //1.6 isDIR�Ǘ���( )
        boolean l_blnDir = l_administartor.isDirAdministrator();
        if (!l_blnDir)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.7 get�،���ЃR�[�h( )
        String l_strInstCode = l_administartor.getInstitutionCode();
                       
        //1.8  get���[��(String, String, String)
        WEB3GentradeMailInfo l_mail = null;
        l_mail = WEB3AdminMailInfoManager.getMail(
            l_strInstCode, l_request.sendMailDiv, l_request.discernId);
        if (l_mail != null)
        {
            log.error(STR_METHOD_NAME +
                WEB3ErrorCatalog.BUSINESS_ERROR_00860.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00860,
                this.getClass().getName() + STR_METHOD_NAME);            
        }
        //1.9 create���X�|���X( )
        l_response = (WEB3AdminMailInfoRegistConfirmResponse)l_request.createResponse();
        
        //1.10 �x�����b�Z�[�W�̐ݒ�
        //     �擾�����Ǘ��҃I�u�W�F�N�g�Ƀ��[���A�h���X���o�^����Ă��Ȃ��ꍇ
        //     1���Z�b�g����B����ȊO��null���Z�b�g����B
        AdministratorParams l_adminParams =
            (AdministratorParams)l_administartor.getDataSourceObject();
        if(l_adminParams.getEmailAddress() == null)
        {
            l_response.warnMessage = "1";
        }
        else
        {
            l_response.warnMessage = null;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit���[�����o�^)<BR>
     * ���[�����o�^�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i���[�����o�^�jsubmit�v�Q��<BR>
     * <BR>
     * =============================================== <BR>
     *         �V�[�P���X�} :  �i���[�����o�^�jsubmit               <BR>
     *         ��̈ʒu    :  1.7 isDIR�Ǘ���( )                     <BR>
     *         ���O�C���Ǘ��҂�DIR�Ǘ��҂��ǂ����`�F�b�N����B  <BR>
     * �iisDIR�Ǘ���( )==false�̏ꍇ�A[�����G���[]�Ƃ��ė�O���X���[����B�j  <BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00857           <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     *         �V�[�P���X�} :  �i���[�����o�^�jsubmit               <BR>
     *         ��̈ʒu    :  1.9 get���[��(String, String, String)   <BR>
     *         �o�^�Ώۂ̃��[�������ɓo�^����Ă��Ȃ����`�F�b�N����B<BR>
     *         �iget���[��( )�̖߂�l!=null�̏ꍇ�A<BR>
     *          [�v���O����ID�E����ID�d���G���[]��O���X���[����B�j<BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00860          <BR>
     * =============================================== <BR>
     * @@param  l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return webbroker3.mailinfo.message.WEB3AdminMailInfoRegistCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C2CF0038A
     */
    protected WEB3AdminMailInfoRegistCompleteResponse submitMailInfoRegist(
                    WEB3AdminMailInfoRegistCompleteRequest  l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " submitMailInfoRegist(WEB3AdminMailInfoRegistCompleteRequest  l_request)";
        log.entering(STR_METHOD_NAME);
     
        WEB3AdminMailInfoRegistCompleteResponse l_response = null;
        WEB3Administrator l_administartor = null;
           
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.MAILINFO, true);
        
        //1.4 validate����p�X���[�h(�p�X���[�h : String)
        l_administartor.validateTradingPassword(l_request.password);
        
        //1.5 isMailAddress(���N�G�X�g�f�[�^.���o�l)
        if (l_request.mailFrom != null)
        {
            boolean l_blnAddress = WEB3StringTypeUtility.isMailAddress(l_request.mailFrom);
            if (!l_blnAddress)
            {
                log.error(STR_METHOD_NAME + 
                    WEB3ErrorCatalog.BUSINESS_ERROR_00777.error_message);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00777,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }

        //1.6 ���N�G�X�g�f�[�^.���M�惁�[���A�h���X��null�̏ꍇ�Ƀ`�F�b�N������
        if (l_request.sendAddress != null )
        {
            //1.6.1 ��؂蕶���P�ʂ��Ƃ�String[]�ɕ�������B
            String l_strCharacter = ",";
            String[] l_lisSendAddress = l_request.sendAddress.split(l_strCharacter);
            
            //1.6.2 String[]��10���̏ꍇ�A��O���X���[����B
            if(l_lisSendAddress.length > 10)
            {
                log.error(STR_METHOD_NAME + 
                    WEB3ErrorCatalog.BUSINESS_ERROR_00877.error_message);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00877, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            //1.6.3 String[]�̗v�f�����A1�����Ƃɕ������`�F�b�N�A���[���A�h���X�`�F�b�N���s�Ȃ��B
            for(int i = 0; i < l_lisSendAddress.length; ++i)
            {
                //1.6.3.1 1���̕�������100byte�̏ꍇ�A��O���X���[����B
                if(WEB3StringTypeUtility.getByteLength(l_lisSendAddress[i]) > 100)
                {
                    log.error(STR_METHOD_NAME + 
                        WEB3ErrorCatalog.BUSINESS_ERROR_00877.error_message);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00877, 
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                
                //1.6.3.2 isMailAddress(String[index])
                boolean l_mailAddress = WEB3StringTypeUtility.isMailAddress(l_lisSendAddress[i]);
                if (!l_mailAddress)
                {
                    log.error(STR_METHOD_NAME + 
                        WEB3ErrorCatalog.BUSINESS_ERROR_00777.error_message);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00777,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
        }
        
        //1.7 isDIR�Ǘ���( )
        boolean l_blnDir = l_administartor.isDirAdministrator();
        if (!l_blnDir)
        {
            log.error(STR_METHOD_NAME +
                WEB3ErrorCatalog.BUSINESS_ERROR_00857.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + STR_METHOD_NAME);            
        }
        
        //1.8 get�،���ЃR�[�h( )
        String l_strInstCode = l_administartor.getInstitutionCode();
        
        //1.9  get���[��(String, String, String)
        WEB3GentradeMailInfo l_mail = null;
        l_mail = WEB3AdminMailInfoManager.getMail(
            l_strInstCode, l_request.sendMailDiv, l_request.discernId);
        if (l_mail != null)
        {
            log.error(STR_METHOD_NAME +
                WEB3ErrorCatalog.BUSINESS_ERROR_00860.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00860,
                this.getClass().getName() + STR_METHOD_NAME);            
        }
        else
        {
            //1.10 createNew���[��(�،���ЃR�[�h : String, ���M���[���敪 : String, ����ID : String)
            WEB3GentradeMailInfo l_genMail = WEB3GentradeMailInfo.createNewMail(
                    l_strInstCode, l_request.sendMailDiv, l_request.discernId);
            
            //1.11 set���[������(���[������ : String)
            l_genMail.setMailName(l_request.mailName);
        
            //1.12 set���o�l(���o�l : String)
            l_genMail.setMailSender(l_request.mailFrom);
            
            //1.13 set����(���� : String)
            l_genMail.setSubject(l_request.mailSubject);
            
            //1.14 set���[���w�b�_�[(���[���w�b�_�[ : String)
            l_genMail.setMailHeader(l_request.mailHeader);
            
            //1.15 set���[���{��(���[���{�� : String)
            l_genMail.setMailText(l_request.mailBody);
            
            //1.16 set���[���w�b�_�[(���[���w�b�_�[ : String)
            l_genMail.setMailFooter(l_request.mailFooter);
            
            //1.17 set���M�惁�[���A�h���X(���[���A�h���X: String)
            l_genMail.setSendAddress(l_request.sendAddress);
            
            //1.18 saveNew���[��( )
            l_genMail.saveNewMail();
        }
        
        //1.19 �Ǘ��҃I�u�W�F�N�g�̃��[���A�h���X�I��null�̏ꍇ
        //     �m�F���[���𑗐M����B
        AdministratorParams l_adminParams =
            (AdministratorParams)l_administartor.getDataSourceObject();
        String l_strEmailAddress = l_adminParams.getEmailAddress();                
        if(l_strEmailAddress != null)
        {
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
 
                MailProcParams l_mailProcParams = new MailProcParams();
                
                l_mailProcParams.setInstitutionCode(l_strInstCode);
                l_mailProcParams.setBranchCode(l_administartor.getBranchCode());
                l_mailProcParams.setSendmailDiv(l_request.sendMailDiv);
                if (l_request.discernId != null)
                {
                    l_mailProcParams.setDiscernmentId(l_request.discernId);
                }
                else
                {
                    l_mailProcParams.setDiscernmentId("----");
                }                
                l_mailProcParams.setAccountCode("0000000");
                
                //Get MailID
                long l_lngMailId = 0;
                try
                {
                    QueryProcessor l_queryProcessorFind;
                    l_queryProcessorFind = Processors.getDefaultProcessor();
                    List l_lisVars = new ArrayList();
                    l_lisVars.add(l_request.sendMailDiv);
                    l_lisVars.add(l_mailProcParams.getDiscernmentId());
                    l_lisVars.add(l_mailProcParams.getAccountCode());
                    List l_lisMailProc = l_queryProcessorFind.doFindAllQuery(
                        MailProcRow.TYPE,
                        "sendmail_div = ? and discernment_id = ? and account_code = ?",
                        "mail_id DESC",
                        null,
                        l_lisVars.toArray(),
                        1,
                        0                        
                    );
                    if (l_lisMailProc.size() > 0)
                    {
                        MailProcParams l_lmpParams = (MailProcParams)l_lisMailProc.get(0);
                        l_lngMailId = l_lmpParams.getMailId();
                    }                    
                }
                catch (DataFindException l_ex)
                {
                    log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataNetworkException l_ex)
                {
                    log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                } 
                catch (DataQueryException l_ex)
                {
                    log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                l_mailProcParams.setMailId(l_lngMailId + 1);
                l_mailProcParams.setDate1(null);
                l_mailProcParams.setDate2(null);
                l_mailProcParams.setDate3(null);
                l_mailProcParams.setDate4(null);
                l_mailProcParams.setQuantity(null);
                l_mailProcParams.setAmount(null);
                l_mailProcParams.setOrderId(null);
                l_mailProcParams.setDivision(null);
                l_mailProcParams.setName1(null);
                l_mailProcParams.setName2(null);
                l_mailProcParams.setStatus("0");
                l_mailProcParams.setSendProcessDateTime(null);
                l_mailProcParams.setErrorCode(null);
                l_mailProcParams.setAdminMailDiv("1");
                l_mailProcParams.setResendStatus("0");                
                l_mailProcParams.setResendProcessDateTime(null);
               l_mailProcParams.setEmailAddress(l_strEmailAddress);
               
               if (l_request.mailFrom != null)
               {
                   l_mailProcParams.setSendEmailAddress(l_request.mailFrom);
               }
               else
               {
                   l_mailProcParams.setSendEmailAddress(l_strEmailAddress);   
               }
               
               l_mailProcParams.setSubject(null);
               l_mailProcParams.setMailText(null);
               l_mailProcParams.setDeleteFlag(BooleanEnum.FALSE);
               l_mailProcParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
               l_mailProcParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
               l_queryProcessor.doInsertQuery(l_mailProcParams);
           }
           catch (DataException de)
           {
               log.error(STR_METHOD_NAME, de);
               throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    de.getMessage(),
                    de);
           }
        }
        //1.20 create���X�|���X( )
        l_response = (WEB3AdminMailInfoRegistCompleteResponse)l_request.createResponse();
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
