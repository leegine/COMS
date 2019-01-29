head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.23.04.51.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1cc4d897c1210b4;
filename	WEB3AdminMailInfoDeleteServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[�����폜�T�[�r�XImpl(WEB3AdminMailInfoDeleteServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  鰊](���u) �V�K�쐬
*/
package webbroker3.mailinfo.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeMailInfo;
import webbroker3.mailinfo.WEB3AdminMailInfoClientRequestService;
import webbroker3.mailinfo.WEB3AdminMailInfoManager;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDeleteCompleteRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDeleteCompleteResponse;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDeleteConfirmRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDeleteConfirmResponse;
import webbroker3.mailinfo.service.delegate.WEB3AdminMailInfoDeleteService;
import webbroker3.util.WEB3LogUtility;

/**
 * ( ���[�����폜�T�[�r�XImpl )<BR>
 * <BR>
 * ���[�����폜�T�[�r�X�����N���X<BR>
 * 
 * @@author 鰊]
 * @@version 1.0
 */
public class WEB3AdminMailInfoDeleteServiceImpl extends WEB3AdminMailInfoClientRequestService implements WEB3AdminMailInfoDeleteService 
{    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static  WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMailInfoDeleteServiceImpl.class);      

    /**
     * @@roseuid 416F1DCC035B
     */
    public WEB3AdminMailInfoDeleteServiceImpl() 
    {
     
    }
    
    /**
     * ���[�����폜�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�ɂ���āAvalidate���[�����폜( )<BR>
     * �܂���submit���[�����폜( )���R�[������B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C3E83030D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if(l_request instanceof WEB3AdminMailInfoDeleteCompleteRequest)
        {
            l_response = this.submitMailInfoDelete((WEB3AdminMailInfoDeleteCompleteRequest)l_request);
           
        }
        else if (l_request instanceof WEB3AdminMailInfoDeleteConfirmRequest)
        {
            l_response = this.validateMailInfoDelete((WEB3AdminMailInfoDeleteConfirmRequest)l_request);
        }
        else
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80018.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);        
        return l_response;
    }
    
    /**
     * ( validate���[�����폜 )<BR>
     * ���[�����폜�R���������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i���[�����폜�jvalidate�v�Q��<BR>
     * <BR>
     * =============================================== <BR>
     *         �V�[�P���X�} :  �i���[�����폜�jvalidate             <BR>
     *         ��̈ʒu    :  1.4  isDIR�Ǘ���( )                    <BR>
     *        ���O�C���Ǘ��҂�DIR�Ǘ��҂��ǂ����`�F�b�N����B <BR>
     *       �iisDIR�Ǘ���( )==false�̏ꍇ�A[�����G���[]�Ƃ��ė�O���X���[����B�j <BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00857           <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     *         �V�[�P���X�} :  �i���[�����폜�jvalidate             <BR>
     *         ��̈ʒu    :  1.5  get���[��(String, String, String) <BR>
     *         �폜�Ώۂ̃��[�������݂��邩�ǂ����`�F�b�N����B<BR>
     *        �iget���[��( )==null�̏ꍇ�A[�f�[�^�s����]�Ƃ��ė�O���X���[����B�j<BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00859          <BR>
     * =============================================== <BR>
     * @@param l_request - ( ���N�G�X�g�f�[�^ )<BR>
     * <BR>
     * ���[�����폜�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.mailinfo.message.WEB3AdminMailInfoDeleteConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C3EA302EE
     */
    protected WEB3AdminMailInfoDeleteConfirmResponse validateMailInfoDelete(WEB3AdminMailInfoDeleteConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateMailInfoDelete(WEB3AdminMailInfoDeleteConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
                
        WEB3AdminMailInfoDeleteConfirmResponse l_response = null;
        
        // (1.1)validate        
        l_request.validate();
        
        //(1.2)getInstanceFrom���O�C�����()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //(1.3)validate����()        
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.MAILINFO,true);
        
        //1.4 isDIR�Ǘ���( )        
        boolean l_blnDir = l_administartor.isDirAdministrator();
        if (l_blnDir == false)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00857.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00857, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        String l_institutionCode = null;
        l_institutionCode = l_administartor.getInstitutionCode();
        
        //1.5 get���[��(String, String, String)
        WEB3GentradeMailInfo l_mail = null;
        l_mail = WEB3AdminMailInfoManager.getMail(l_institutionCode, l_request.sendMailDiv, l_request.discernId);
        if (l_mail == null)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00859.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00859, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.6 create���X�|���X( )
        l_response = (WEB3AdminMailInfoDeleteConfirmResponse) l_request.createResponse();
        
        //1.7 �v���p�e�B�E�Z�b�g
        l_response.mailName = l_mail.getMailName();
        l_response.mailFrom = l_mail.getMailSender();
        l_response.sendAddress = l_mail.getSendAddress();
        l_response.mailSubject = l_mail.getSubject();
        l_response.mailHeader = l_mail.getMailHeader();
        l_response.mailBody = l_mail.getMailText();
        l_response.mailFooter = l_mail.getMailFooter();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * ( submit���[�����폜 ) <BR>
     * <BR>
     * ���[�����폜�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i���[�����폜�jsubmit�v�Q��<BR>
     * <BR>
     * =============================================== <BR>
     *         �V�[�P���X�} :  �i���[�����폜�jsubmit               <BR>
     *         ��̈ʒu    :  1.5  isDIR�Ǘ���( )                     <BR>
     *         ���O�C���Ǘ��҂�DIR�Ǘ��҂��ǂ����`�F�b�N����B  <BR>
     *        �iisDIR�Ǘ���( )==false�̏ꍇ�A[�����G���[]�Ƃ��ė�O���X���[����B  <BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00857
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     *         �V�[�P���X�} :  �i���[�����폜�jsubmit               <BR>
     *         ��̈ʒu    :  1.6 getMail(String, String, String)    <BR>
     *         �폜�Ώۂ̃��[�������݂��邩�ǂ����`�F�b�N����B<BR>
     *        �iget���[��( )==null�̏ꍇ�A[�f�[�^�s����]�Ƃ��ė�O���X���[����B�j<BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00859           <BR>
     * =============================================== <BR>
     * @@param l_request - ( ���N�G�X�g�f�[�^ )<BR>
     * <BR>
     * ���[�����폜�������N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.mailinfo.message.WEB3AdminMailInfoDeleteCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C3EE502AF
     */
    protected WEB3AdminMailInfoDeleteCompleteResponse submitMailInfoDelete(WEB3AdminMailInfoDeleteCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitMailInfoDelete(WEB3AdminMailInfoDeleteCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminMailInfoDeleteCompleteResponse l_response = null;
        
        // (1.1)validate        
        l_request.validate();
        
        //(1.2)getInstanceFrom���O�C�����()        
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //(1.3)validate����()
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.MAILINFO,true);
        
        //1.4 validate����p�X���[�h(�p�X���[�h : String)
        l_administartor.validateTradingPassword(l_request.password);
        
        //1.5 isDIR�Ǘ���()
        boolean l_blnDir = l_administartor.isDirAdministrator();
        if (!l_blnDir)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00857.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00857, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        String l_institutionCode = null;
        l_institutionCode = l_administartor.getInstitutionCode();
        
        //1.6 get���[��(String, String, String)
        WEB3GentradeMailInfo l_mail = null;
        l_mail = WEB3AdminMailInfoManager.getMail(l_institutionCode,l_request.sendMailDiv,l_request.discernId);
        if (l_mail == null)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00859.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00859, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.7 remove���[��(�،���ЃR�[�h : String, ���M���[���敪 : String, ����ID : String)
        WEB3GentradeMailInfo.removeMail(l_institutionCode,l_request.sendMailDiv,l_request.discernId);
        
        //1.8 create���X�|���X( )
        l_response = (WEB3AdminMailInfoDeleteCompleteResponse) l_request.createResponse();
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }    
}
@
