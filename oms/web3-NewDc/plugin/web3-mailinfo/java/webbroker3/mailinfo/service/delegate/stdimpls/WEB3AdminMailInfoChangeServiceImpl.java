head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.16.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[�����ύX�T�[�r�XImpl(WEB3AdminMailInfoChangeServiceImpl.java) 
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  鰊](���u) �V�K�쐬
Revesion History : 2004/10/19  ����(���u) �쐬
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
import webbroker3.mailinfo.message.WEB3AdminMailInfoChangeCompleteRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoChangeCompleteResponse;
import webbroker3.mailinfo.message.WEB3AdminMailInfoChangeConfirmRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoChangeConfirmResponse;
import webbroker3.mailinfo.message.WEB3AdminMailInfoChangeInputRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoCommonResponse;
import webbroker3.mailinfo.service.delegate.WEB3AdminMailInfoChangeService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * ( ���[�����ύX�T�[�r�XImpl )<BR>
 * <BR>
 * ���[�����ύX�T�[�r�X�����N���X<BR>
 * 
 * @@author 鰊]
 * @@version 1.0.1
 */
public class WEB3AdminMailInfoChangeServiceImpl 
        extends WEB3AdminMailInfoClientRequestService 
            implements WEB3AdminMailInfoChangeService 
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */
     private  static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3AdminMailInfoChangeServiceImpl.class);
    
    /**
     * @@roseuid 416F1DCB003E
     */
    public WEB3AdminMailInfoChangeServiceImpl() 
    {
     
    }
    
    /**
     * ���[�����ύX�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�ɂ���āAvalidate���[�����ύX( )�܂��́A<BR>
     * submit���[�����ύX( )���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C34E70119
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final  String  STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null ;
        
        //���[�����ύX�T�[�r�XImpl
        if(l_request instanceof WEB3AdminMailInfoChangeInputRequest) 
        {
            // l_request instanceof ���[�����ύX���͉�ʃ��N�G�X�g�̏ꍇ  
            WEB3AdminMailInfoChangeInputRequest l_mailInfoChangeInputRequest =(
                WEB3AdminMailInfoChangeInputRequest)l_request; 
            l_response = getMailInfoChangeInputScreen(l_mailInfoChangeInputRequest);
        }
        else if(l_request instanceof WEB3AdminMailInfoChangeConfirmRequest)
        {
            //l_request instanceof ���[�����ύX�m�F���N�G�X�g�̏ꍇ
            WEB3AdminMailInfoChangeConfirmRequest l_mailInfoChangeConfirmRequest =(
                WEB3AdminMailInfoChangeConfirmRequest)l_request; 
            l_response = validateMailInfoChange(l_mailInfoChangeConfirmRequest);            
            
        }
        else if(l_request instanceof WEB3AdminMailInfoChangeCompleteRequest)
        {
            //l_request instanceof ���[�����ύX�������N�G�X�g�̏ꍇ
            WEB3AdminMailInfoChangeCompleteRequest l_mailInfoChangeCompleteRequest =(
                WEB3AdminMailInfoChangeCompleteRequest)l_request; 
            l_response = submitMailInfoChange(l_mailInfoChangeCompleteRequest);
        }
        else 
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80018.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return  l_response ;
    }
    
    /**
     * ( get���[�����ύX���͉�� )<BR>
     * <BR>
     * ���[�����ύX���͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i���[�����ύX�jget���͉�ʁv�Q��<BR>
     * <BR>
     * =============================================== <BR>
     *         �V�[�P���X�} :  �i���[�����ύX�jget���͉��        <BR>
     *         ��̈ʒu    :  1.4 get���[��(String, String, String)   <BR>
     *        �ύX�Ώۂ̃��[�������݂��邩�`�F�b�N����B<BR>
     *         �iget���[��( )==null�̏ꍇ�A[�f�[�^�s����]�Ƃ��ė�O���X���[����B�j<BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00859           <BR>
     * =============================================== <BR>
     * @@param l_request - ( ���N�G�X�g�f�[�^ )<BR>
     * <BR>
     * ���[�����ύX���͉�ʃ��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.mailinfo.message.WEB3AdminMailInfoCommonResponse
     * @@throws WEB3BaseException
     * @@roseuid 413D560F02B0
     */
    protected WEB3AdminMailInfoCommonResponse getMailInfoChangeInputScreen(
            WEB3AdminMailInfoChangeInputRequest l_request) 
                throws WEB3BaseException 
    {
        final  String  STR_METHOD_NAME = " getMailInfoChangeInputScreen(WEB3AdminMailInfoChangeInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeMailInfo l_mail = null; 
        
        //(1.1)validate( )���̓`�F�b�N
        l_request.validate();
        
        // (1.2)�Ǘ��Ҍ����`�F�b�N
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
       
        // (1.3)validate����(WEB3TransactionCategoryDef.MAILINFO,true)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.MAILINFO, true);
        
        // (1.4)getMail()
        String l_strInstCode = l_administartor.getInstitutionCode();
        l_mail = WEB3AdminMailInfoManager.getMail(
            l_strInstCode, l_request.sendMailDiv, l_request.discernId);
       
        //get���[��( )==null�̏ꍇ�A[�f�[�^�s����]�Ƃ��ė�O���X���[����
        if(l_mail == null)
        {
            log.error(STR_METHOD_NAME + 
                WEB3ErrorCatalog.BUSINESS_ERROR_00859.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
            WEB3ErrorCatalog.BUSINESS_ERROR_00859, 
                this.getClass().getName() + STR_METHOD_NAME); 
                
        }
        
        // (1.6)createResponse()
        WEB3AdminMailInfoCommonResponse l_mailInfoCommonResponse =(
                WEB3AdminMailInfoCommonResponse)l_request.createResponse(); 
       
        // (1.5)���X�|���X�E�Z�b�g
        l_mailInfoCommonResponse.sendMailDiv = l_mail.getSendmailDiv();
        l_mailInfoCommonResponse.discernId = l_mail.getDiscernmentId();
        l_mailInfoCommonResponse.mailName = l_mail.getMailName();
        l_mailInfoCommonResponse.mailFrom = l_mail.getMailSender();
        l_mailInfoCommonResponse.sendAddress = l_mail.getSendAddress();
        l_mailInfoCommonResponse.mailSubject = l_mail.getSubject();
        l_mailInfoCommonResponse.mailHeader =  l_mail.getMailHeader();
        l_mailInfoCommonResponse.mailBody =  l_mail.getMailText();
        l_mailInfoCommonResponse.mailFooter = l_mail.getMailFooter();
        
        log.exiting(STR_METHOD_NAME);
        return l_mailInfoCommonResponse;
    }
    
    /**
     * ( validate���[�����ύX )<BR>
     * <BR>
     * ���[�����ύX�R���������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i���[�����ύX�jvalidate�v�Q�l<BR>
     * <BR>
     * =============================================== <BR>
     *         �V�[�P���X�} :  �i���[�����ύX�jvalidate             <BR>
     *         ��̈ʒu    :  1.7 get���[��(String, String, String)  <BR>
     *         �ύX�Ώۂ̃��[�������݂��邩�`�F�b�N����B<BR>
     *        �iget���[��( )==null�̏ꍇ�A[�f�[�^�s����]�Ƃ��ė�O���X���[����B�j<BR>
     *         class         :  WEB3BusinessLayerException      <BR>
     *         tag            :  BUSINESS_ERROR_00859          <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     *         �V�[�P���X�} :  �i���[�����ύX�jvalidate             <BR>
     *         ��̈ʒu    :  1.8 isDIR�Ǘ���( )                     <BR>
     *         ���O�C���Ǘ��҂�DIR�Ǘ��҂ł͂Ȃ��ꍇ<BR>
     *         �iisDIR�Ǘ���( )==false�̏ꍇ�j<BR>
     *         get���[���{��( )�̖߂�l�ƃ��N�G�X�g�f�[�^.���[���{����<BR>
     *         class         :  WEB3BusinessLayerException      <BR>
     *         tag            :  BUSINESS_ERROR_00857          <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     *         �V�[�P���X�} :  �i���[�����ύX�jvalidate             <BR>
     *         ��̈ʒu    :  1.9 <���򏈗�>                         <BR>
     *         ���O�C���Ǘ��҂�DIR�Ǘ��҂ł͂Ȃ��ꍇ<BR>
     *         �iisDIR�Ǘ���( )==false�̏ꍇ�j<BR>
     *         get���[���{��( )�̖߂�l�ƃ��N�G�X�g�f�[�^.���[���{����<BR>
     *         �قȂ�ꍇ�A[�����G���[]�Ƃ��ė�O���X���[����B<BR>
     *         class         :  WEB3BusinessLayerException      <BR>
     *         tag            :  BUSINESS_ERROR_00857          <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     *         �V�[�P���X�} :  �i���[�����ύX�jvalidate             <BR>
     *         ��̈ʒu    :  1.9.1 get���[���{��( )                  <BR>
     *         ���O�C���Ǘ��҂�DIR�Ǘ��҂ł͂Ȃ��ꍇ<BR>
     *         �iisDIR�Ǘ���( )==false�̏ꍇ�j<BR>
     *         get���[���{��( )�̖߂�l�ƃ��N�G�X�g�f�[�^.���[���{����<BR>
     *         �قȂ�ꍇ�A[�����G���[]�Ƃ��ė�O���X���[����B<BR>
     *         class         :  WEB3BusinessLayerException      <BR>
     *         tag            :  BUSINESS_ERROR_00857          <BR>
     * =============================================== <BR>
     * @@param l_request - ( ���N�G�X�g�f�[�^ )<BR>
     * <BR>
     * ���[�����ύX�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.mailinfo.message.WEB3AdminMailInfoChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C353602BF
     */
    protected WEB3AdminMailInfoChangeConfirmResponse validateMailInfoChange(
            WEB3AdminMailInfoChangeConfirmRequest l_request) throws WEB3BaseException 
    {
        final  String  STR_METHOD_NAME = " validateMailInfoChange(WEB3AdminMailInfoChangeConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeMailInfo l_mail = null; 
        
        //(1.1)validate( )���̓`�F�b�N
        l_request.validate();
        
        // (1.2)�Ǘ��Ҍ����`�F�b�N
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        // (1.3)validate����(WEB3TransactionCategoryDef.MAILINFO,true)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.MAILINFO, true);
        
        // (1.4)isMailAddress(���N�G�X�g�f�[�^.���o�l)
        if (l_request.mailFrom != null)
        {
            boolean l_blnMailAddress = WEB3StringTypeUtility.isMailAddress(l_request.mailFrom);
            if(!(l_blnMailAddress))
            {
                log.error(STR_METHOD_NAME + 
                    WEB3ErrorCatalog.BUSINESS_ERROR_00777.error_message);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00777, 
                    this.getClass().getName() + STR_METHOD_NAME);  
            }
        }
               
        //(1.5)�����򏈗������N�G�X�g�f�[�^.���M�惁�[���A�h���X��null�̏ꍇ�Ƀ`�F�b�N������
        if(l_request.sendAddress != null)
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
                boolean l_blnMailAddress1 = WEB3StringTypeUtility.isMailAddress(l_lisSendAddress[i]);
                if(!(l_blnMailAddress1))
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
      
        //(1.6)get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administartor.getInstitutionCode();
        
        //(1.7)getMail(String, String, String)
        l_mail = WEB3AdminMailInfoManager.getMail(
            l_strInstitutionCode, l_request.sendMailDiv,  l_request.discernId);
        
        //get���[��( )==null�̏ꍇ�A[�f�[�^�s����]�Ƃ��ė�O���X���[����
        if(l_mail == null)
        {
            log.error(STR_METHOD_NAME + 
                WEB3ErrorCatalog.BUSINESS_ERROR_00859.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00859, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }
        
        //(1.8)isDIR�Ǘ���( )       
        if(!l_administartor.isDirAdministrator())
        {
            
           //(1.9)���򏈗�
           String l_strMailText = l_mail.getMailText();
           if (l_request.mailBody == null)
           {
               if(l_strMailText != null)
               {
                   log.error(STR_METHOD_NAME + 
                        WEB3ErrorCatalog.BUSINESS_ERROR_00857.error_message);              
                   log.exiting(STR_METHOD_NAME);
                   throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_00857, 
                       this.getClass().getName() + STR_METHOD_NAME); 
               }
           }            
           else if(!(l_request.mailBody.equals(l_strMailText)))
           {
              log.error(STR_METHOD_NAME + 
                  WEB3ErrorCatalog.BUSINESS_ERROR_00857.error_message);              
              log.exiting(STR_METHOD_NAME);
              throw new WEB3BusinessLayerException(
                  WEB3ErrorCatalog.BUSINESS_ERROR_00857, 
                  this.getClass().getName() + STR_METHOD_NAME); 
            }
        }
              
       //(1.10)createResponse( )
       WEB3AdminMailInfoChangeConfirmResponse l_adminMailInfoChangeConfirmResponse =(
                WEB3AdminMailInfoChangeConfirmResponse)l_request.createResponse( );
                
       //(1.11)�x�����b�Z�[�W�̐ݒ�
       //      �擾�����Ǘ��҃I�u�W�F�N�g�Ƀ��[���A�h���X���o�^����Ă��Ȃ��ꍇ
       //      1���Z�b�g����B����ȊO��null���Z�b�g����B
       AdministratorParams l_adminParams = 
                (AdministratorParams) l_administartor.getDataSourceObject(); 
       if(l_adminParams.getEmailAddress() == null)
       {
           l_adminMailInfoChangeConfirmResponse.warnMessage = "1";
       }
       else
       {
           l_adminMailInfoChangeConfirmResponse.warnMessage = null;
       }

       log.exiting(STR_METHOD_NAME);      
       return l_adminMailInfoChangeConfirmResponse; 
    }

    /**
     * ( submit���[�����ύX ) <BR>
     * <BR>
     * ���[�����ύX�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i���[�����ύX�jsubmit�v�Q��<BR>
     * <BR>
     * =============================================== <BR>
     *         �V�[�P���X�} :  �i���[�����ύX�jsubmit              <BR>
     *         ��̈ʒu    :  1.8  get���[��(String, String, String) <BR>
     *         �Ώۂ̃��[�������݂��邩�`�F�b�N����B<BR>
     *         �iget���[��( )==null�̏ꍇ�A[�f�[�^�s����]�Ƃ��ė�O���X���[����B�j<BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00859           <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     *         �V�[�P���X�} :  �i���[�����ύX�jsubmit              <BR>
     *         ��̈ʒu    :  1.9  isDIR�Ǘ���( )                    <BR>
     *         ���O�C���Ǘ��҂�DIR�Ǘ��҂ł͂Ȃ��ꍇ<BR>
     *         �iisDIR�Ǘ���( )==false�̏ꍇ�j <BR>
     *         get���[���{��( )�̖߂�l�ƃ��N�G�X�g�f�[�^.���[���{����<BR>
     *         �قȂ�ꍇ�A[�����G���[]�Ƃ��ė�O���X���[����B<BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00857           <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     *         �V�[�P���X�} :  �i���[�����ύX�jsubmit               <BR>
     *         ��̈ʒu    :  1.10  <���򏈗�>                       <BR>
     *          ���O�C���Ǘ��҂�DIR�Ǘ��҂ł͂Ȃ��ꍇ<BR>
     *         �iisDIR�Ǘ���( )==false�̏ꍇ�j<BR>
     *         get���[���{��( )�̖߂�l�ƃ��N�G�X�g�f�[�^.���[���{����<BR>
     *         �قȂ�ꍇ�A[�����G���[]�Ƃ��ė�O���X���[����B<BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00857           <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     *         �V�[�P���X�} :  �i���[�����ύX�jsubmit               <BR>
     *         ��̈ʒu    :  1.10.1 get���[���{��( )                 <BR>
     *        ���O�C���Ǘ��҂�DIR�Ǘ��҂ł͂Ȃ��ꍇ<BR>
     *        �iisDIR�Ǘ���( )==false�̏ꍇ�j<BR>
     *         get���[���{��( )�̖߂�l�ƃ��N�G�X�g�f�[�^.���[���{����<BR>
     *         �قȂ�ꍇ�A[�����G���[]�Ƃ��ė�O���X���[����B<BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00857           <BR>
     * =============================================== <BR>
     * @@param l_request - ( ���N�G�X�g�f�[�^ ) <BR>
     * <BR>
     * ���[�����ύX�������N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.mailinfo.message.WEB3AdminMailInfoChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C353E0109
     */
    protected WEB3AdminMailInfoChangeCompleteResponse submitMailInfoChange(
            WEB3AdminMailInfoChangeCompleteRequest l_request) throws WEB3BaseException 
    {
                
        final  String  STR_METHOD_NAME = " submitMailInfoChange(WEB3AdminMailInfoChangeCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeMailInfo l_mail = null; 
       
        //(1.1)validate( )���̓`�F�b�N
        l_request.validate();
        
        // (1.2)�Ǘ��Ҍ����`�F�b�N
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        // (1.3)validate����(WEB3TransactionCategoryDef.MAILINFO,true)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.MAILINFO, true);
        
        // (1.4)validate����p�X���[�h(�p�X���[�h : String)
        l_administartor.validateTradingPassword(l_request.password);
        
        // (1.5)isMailAddress(���N�G�X�g�f�[�^.���o�l)
        if(l_request.mailFrom != null)
        {
            boolean l_blnMailAddress  = WEB3StringTypeUtility.isMailAddress(l_request.mailFrom);
            if(!(l_blnMailAddress))
            {
                log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00777.error_message);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00777,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }
       
        //(1.6)�����򏈗������N�G�X�g�f�[�^.���M�惁�[���A�h���X��null�̏ꍇ�Ƀ`�F�b�N������
        if(l_request.sendAddress != null)
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
                boolean l_blnMailAddress1  = WEB3StringTypeUtility.isMailAddress(l_lisSendAddress[i]);
                if(!(l_blnMailAddress1))
                {
                    log.error(STR_METHOD_NAME + 
                        WEB3ErrorCatalog.BUSINESS_ERROR_00777.error_message);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00777,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
        }
        //(1.7)get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administartor.getInstitutionCode();
        
        //(1.8)getMail(String, String, String)
        l_mail = WEB3AdminMailInfoManager.getMail(
                    l_strInstitutionCode, l_request.sendMailDiv,  l_request.discernId);
        
        //get���[��( )==null�̏ꍇ�A[�f�[�^�s����]�Ƃ��ė�O���X���[����
        if(l_mail == null)
        {
            log.error(STR_METHOD_NAME +
                WEB3ErrorCatalog.BUSINESS_ERROR_00859.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00859,
                this.getClass().getName() + STR_METHOD_NAME);
                
        }
        //(1.9)isDIR�Ǘ���( )
        if(!l_administartor.isDirAdministrator())
        {
            //(1.10)���򏈗�
           String l_strMailText = l_mail.getMailText();
           if (l_request.mailBody == null)
           {
               if(l_strMailText != null)
               {
                   log.error(STR_METHOD_NAME +
                        WEB3ErrorCatalog.BUSINESS_ERROR_00857.error_message);              
                   log.exiting(STR_METHOD_NAME);
                   throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_00857, 
                       this.getClass().getName() + STR_METHOD_NAME); 
               }
           }            
           else if(!(l_request.mailBody.equals(l_strMailText)))
           {
              log.error(STR_METHOD_NAME +
                  WEB3ErrorCatalog.BUSINESS_ERROR_00857.error_message);              
              log.exiting(STR_METHOD_NAME);
              throw new WEB3BusinessLayerException(
                  WEB3ErrorCatalog.BUSINESS_ERROR_00857, 
                  this.getClass().getName() + STR_METHOD_NAME); 
           }
        }
        //(1.11)createForUpdateParams
        l_mail.createForUpdateParams();
        
        //set���[������(���[������ : String) :add by �����F
        l_mail.setMailName(l_request.mailName);
        
        //(1.12)set���o�l(���o�l : String)
        l_mail.setMailSender(l_request.mailFrom);
        
        //set���M�惁�[���A�h���X(���[���A�h���X: String)
        l_mail.setSendAddress(l_request.sendAddress);
        
        //(1.13)set����(���� : String)
        l_mail.setSubject(l_request.mailSubject);
        
        //(1.14)set���[���w�b�_�[(���[���w�b�_�[ : String)
        l_mail.setMailHeader(l_request.mailHeader);
        
        //(1.15)set���[���{��(���[���{�� : String)
        l_mail.setMailText(l_request.mailBody);
         
        //(1.16)set���[���t�b�^�[(���[���t�b�^�[ : String)
        l_mail.setMailFooter(l_request.mailFooter);  
              
        //(1.17)save���[��( )
        l_mail.saveMail();
        
        //1.18 �Ǘ��҃I�u�W�F�N�g�̃��[���A�h���X�I��null�̏ꍇ
        //     �m�F���[���𑗐M����B
        AdministratorParams l_adminParams = (AdministratorParams)l_administartor.getDataSourceObject();
        String l_strEmailAddress = l_adminParams.getEmailAddress();                
        if (l_strEmailAddress != null)
        {
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                MailProcParams l_mailProcParams = new MailProcParams();
                
                l_mailProcParams.setInstitutionCode(l_strInstitutionCode);
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
        //(1.18)createResponse( )
        WEB3AdminMailInfoChangeCompleteResponse l_mailInfoChangeCompleteResponse =(
                WEB3AdminMailInfoChangeCompleteResponse)l_request.createResponse( ); 
        
        log.exiting(STR_METHOD_NAME);      
        return l_mailInfoChangeCompleteResponse;
    }    
}
@
