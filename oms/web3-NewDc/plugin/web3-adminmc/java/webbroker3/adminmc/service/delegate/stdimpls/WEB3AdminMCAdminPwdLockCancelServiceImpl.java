head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.26.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPwdLockCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����Ǘ��҃p�X���[�h���b�N�����T�[�r�XImpl(WEB3AdminMCAdminPwdLockCancelServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/24  �� �� �@@ (���u) �V�K�쐬
*/

package webbroker3.adminmc.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;

import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;

import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPwdLockCancelService;
import webbroker3.adminmc.message.WEB3AdminMCAdminPwdLockCancelConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPwdLockCancelCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPwdLockCancelConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPwdLockCancelCompleteResponse;

/**
 * (�Ǘ��҃��j���[����Ǘ��҃p�X���[�h���b�N�����T�[�r�XImpl)<BR>
 * �Ǘ��҃��j���[����Ǘ��҃p�X���[�h���b�N�����T�[�r�X�����N���X<BR>
 * @@author �����@@
 * @@version 1.0
 */

public class WEB3AdminMCAdminPwdLockCancelServiceImpl implements WEB3AdminMCAdminPwdLockCancelService 
{
    
    /**
     *���O�o�̓��[�e�B���e�B�B
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminPwdLockCancelServiceImpl.class);
        
    /**
     * @@roseuid 41986410036B
     */
    public WEB3AdminMCAdminPwdLockCancelServiceImpl() 
    {
    
    }
    
    /**
     * �Ǘ��҃p�X���[�h���b�N�������������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * 
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����Ǘ����߽ܰ��ۯ�މ����m�Fظ��Ă̏ꍇ <BR>
     * �@@�|validate����()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����Ǘ����߽ܰ��ۯ���������ظ��Ă̏ꍇ <BR>
     * �@@�|submit����()���R�[������B <BR>
     * <BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 417DE3BD01E0
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminMCAdminPwdLockCancelConfirmRequest)
        {
            l_response = this.validateCancel((WEB3AdminMCAdminPwdLockCancelConfirmRequest)l_request);
        
        }
        else if (l_request instanceof WEB3AdminMCAdminPwdLockCancelCompleteRequest)
        {
            l_response = this.submitCancel((WEB3AdminMCAdminPwdLockCancelCompleteRequest)l_request);
        
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                                      WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                                       STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate����)<BR>
     * �Ǘ��҃p�X���[�h���b�N�����m�F���������{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҃��j���[����i�Ǘ��҃p�X���[�h���b�N�����jvalidate�����v�Q�ƁB <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��҃p�X���[�h���b�N�����jvalidate�����v<BR>
     *         ��̈ʒu    : 1.6 �Ǘ���(�،���� : �،����, �Ǘ��҃R�[�h : String)<BR>
     *         �� �����f�[�^�`�F�b�N<BR>
     *         ���͂��ꂽ�Ǘ��҃R�[�h�ɂāA�Ǘ��҃I�u�W�F�N�g�𐶐�����B<BR>
     *         �����ł��Ȃ��ꍇ�A���b�N�����ΏۊǗ��҂��Ȃ��Ɣ��f���A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01222           <BR>
     * ========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��҃p�X���[�h���b�N�����jvalidate�����v<BR>
     *         ��̈ʒu    : 1.7(*1) ����t���[<BR>
     *         �Ǘ��҃R�[�h�����݂��Ȃ��ꍇ�i�I�u�W�F�N�g�������ł��Ȃ��ꍇ�j�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01222          <BR>
     * ========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��҃p�X���[�h���b�N�����jvalidate�����v<BR>
     *         ��̈ʒu    : 1.9.2 (*2.1) �Ώۃf�[�^��DIR�Ǘ��҂̏ꍇ<BR>
     *         �iisDIR�Ǘ���() == true�j�A��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01223          <BR>
     * ========================================================== <BR>
     * <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[�����߽ܰ�މ����m�Fظ��ăf�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPwdLockCancelConfirmResponse
     * @@roseuid 417DE3BD01E2
     */
    protected WEB3AdminMCAdminPwdLockCancelConfirmResponse validateCancel(WEB3AdminMCAdminPwdLockCancelConfirmRequest l_request) throws WEB3BaseException  
    {

        final String STR_METHOD_NAME = " validateCancel(WEB3AdminMCAdminPwdLockCancelConfirmRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        //1.2 getInstanceFrom���O�C�����()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        //1.3  validate����(�@@�\�J�e�S���R�[�h)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_ADMINISTRATOR,true);
        //1.4 get�،����
        Institution l_institution = l_administartor.getInstitution();
        //1.5 validate���X����
        l_administartor.validateBranchPermission(l_request.branchCode);
        //1.6 �Ǘ���(long)
        WEB3Administrator l_otheradministartor = null;
        try 
        {
            l_otheradministartor = new WEB3Administrator(l_institution, l_request.administratorCode);
        }
        catch (WEB3SystemLayerException l_ex)
        {
            //1.7 (*1) �Ǘ��҃R�[�h�����݂��Ȃ��ꍇ�i�I�u�W�F�N�g�������ł��Ȃ��ꍇ
            log.error("�Ǘ��҃R�[�h�����݂��Ȃ��ꍇ�i�I�u�W�F�N�g�������ł��Ȃ��ꍇ�j.");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01222,
                this.getClass().getName() + STR_METHOD_NAME);          
        }   
               
        //1.8  isDIR�Ǘ���()
        boolean l_blnDir = l_administartor.isDirAdministrator();
        // (*2) ���O�C�����̊Ǘ��҂��ʏ�Ǘ��ҁiisDIR�Ǘ���() == false�j�ꍇ
        if (!l_blnDir)
        {
             //1.9.1 isDIR�Ǘ���( )
             boolean l_otherblnDir = l_otheradministartor.isDirAdministrator();
             //1.9.2 (*2.1) �Ώۃf�[�^��DIR�Ǘ��҂̏ꍇ�iisDIR�Ǘ���() == true�j�A
             if (l_otherblnDir)
             {
                 log.error("�Ώۃf�[�^��DIR�Ǘ��҂̏ꍇ�iisDIR�Ǘ���() == true�j.");
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BusinessLayerException(
                     WEB3ErrorCatalog.BUSINESS_ERROR_01223,
                     this.getClass().getName() + STR_METHOD_NAME);          
             }        
            
        }
        
       //1.10 createResponse( )
       WEB3AdminMCAdminPwdLockCancelConfirmResponse l_response = (WEB3AdminMCAdminPwdLockCancelConfirmResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);  
        return l_response;

    }
    
    /**
     * (submit����)<BR>
     * �Ǘ��҃p�X���[�h���b�N�����������������{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҃��j���[����i�Ǘ��҃p�X���[�h���b�N�����jsubmit�����v�Q�ƁB<BR> 
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��҃p�X���[�h���b�N�����jsubmit�����v<BR>
     *         ��̈ʒu    : 1.7 �Ǘ���(�،���� : �،����, �Ǘ��҃R�[�h : String)<BR>
     *         �� �����f�[�^�`�F�b�N<BR>
     *         ���͂��ꂽ�Ǘ��҃R�[�h�ɂāA�Ǘ��҃I�u�W�F�N�g�𐶐�����B<BR>
     *         �����ł��Ȃ��ꍇ�A���b�N�����ΏۊǗ��҂��Ȃ��Ɣ��f���A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01222           <BR>
     * ========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��҃p�X���[�h���b�N�����jsubmit�����v<BR>
     *         ��̈ʒu    : 1.8(*1) ����t���[<BR>
     *         �Ǘ��҃R�[�h�����݂��Ȃ��ꍇ�i�I�u�W�F�N�g�������ł��Ȃ��ꍇ�j�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01222           <BR>
     * ========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��҃p�X���[�h���b�N�����jsubmit�����v<BR>
     *         ��̈ʒu    : 1.9.2 (*2.1) ����t���[<BR>
     *         �Ώۃf�[�^��DIR�Ǘ��҂̏ꍇ�iisDIR�Ǘ���() == true�j�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01223           <BR>
     * ========================================================== <BR>        
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[�����߽ܰ�މ�������ظ��ăf�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPwdLockCancelCompleteResponse
     * @@roseuid 417DE3BD01E4
     */
    protected WEB3AdminMCAdminPwdLockCancelCompleteResponse submitCancel(WEB3AdminMCAdminPwdLockCancelCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitCancel(WEB3AdminMCAdminPwdLockCancelCompleteRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        //1.2 getInstanceFrom���O�C�����()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        //1.3  validate����(�@@�\�J�e�S���R�[�h)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_ADMINISTRATOR, true);
        //1.4 get�،����
        Institution l_institution = l_administartor.getInstitution();
        //1.5 validate���X����
        l_administartor.validateBranchPermission(l_request.branchCode);
        //1.6 validate����p�X���[�h(String)
        l_administartor.validateTradingPassword(l_request.password);
        //1.7 �Ǘ���(long)
        WEB3Administrator l_otheradministartor = null;
        try
        {
            l_otheradministartor = new WEB3Administrator(l_institution, l_request.administratorCode);
        }
        catch (WEB3SystemLayerException l_ex)
        {
            //1.8 (*1) �Ǘ��҃R�[�h�����݂��Ȃ��ꍇ�i�I�u�W�F�N�g�������ł��Ȃ��ꍇ
            log.error("�Ǘ��҃R�[�h�����݂��Ȃ��ꍇ�i�I�u�W�F�N�g�������ł��Ȃ��ꍇ�j.");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01222,
                this.getClass().getName() + STR_METHOD_NAME);          
        }   
       
        //1.8  isDIR�Ǘ���()
        boolean l_blnDir = l_administartor.isDirAdministrator();
        //1.9 (*2) ���O�C�����̊Ǘ��҂��ʏ�Ǘ��ҁiisDIR�Ǘ���() == false�j�ꍇ
        if (!l_blnDir)
        {
             //1.9.1 isDIR�Ǘ���( )
             boolean l_otherblnDir = l_otheradministartor.isDirAdministrator();
             //1.9.2 (*2.1) �Ώۃf�[�^��DIR�Ǘ��҂̏ꍇ�iisDIR�Ǘ���() == true�j�A
             if (l_otherblnDir)
             {
                 log.error("�Ώۃf�[�^��DIR�Ǘ��҂̏ꍇ�iisDIR�Ǘ���() == true�j.");
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BusinessLayerException(
                     WEB3ErrorCatalog.BUSINESS_ERROR_01223,
                     this.getClass().getName() + STR_METHOD_NAME);          
             }        
            
        }
        //1.10  get���O�C���h�c()
        long l_loginId = l_otheradministartor.getLoginId();       
        //1.11  clearBadPassowrdHistory
        OpLoginAdminService l_opLoginAdminService =
            (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
        l_opLoginAdminService.clearBadPassowrdHistory(l_loginId);          
        //1.12  enableLogin(long)
        l_opLoginAdminService.enableLogin(l_loginId);
        
       //1.13 createResponse( )
       WEB3AdminMCAdminPwdLockCancelCompleteResponse l_response = (WEB3AdminMCAdminPwdLockCancelCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);  
        return l_response;

    }
}
@
