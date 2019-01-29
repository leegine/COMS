head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.27.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermGrpRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�o�^�T�[�r�XImpl(WEB3AdminMCAdminPermGrpRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/23  �� �� �@@ (���u) �V�K�쐬
*/

package webbroker3.adminmc.service.delegate.stdimpls;


import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;

import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;

import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermGrpRegistService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermUnitCreateService;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistConfirmResponse;
import webbroker3.adminmc.WEB3AdminMCAdminType;

/**
 * (�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�o�^�T�[�r�XImpl)<BR>
 * �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�o�^�T�[�r�X�����N���X<BR>
 * @@author �����@@
 * @@version 1.0
 */

public class WEB3AdminMCAdminPermGrpRegistServiceImpl implements WEB3AdminMCAdminPermGrpRegistService 
{
    
    /**
     *���O�o�̓��[�e�B���e�B�B
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminPermGrpRegistServiceImpl.class);
    
    /**
     * @@roseuid 419864130203
     */
    public WEB3AdminMCAdminPermGrpRegistServiceImpl() 
    {
     
    }
    
    /**
     * �Ǘ��Ҍ����O���[�v�o�^���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�o�^���̓��N�G�X�g�̏ꍇ <BR>
     * �@@�|get���͉��()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�o�^�m�F���N�G�X�g�̏ꍇ <BR>
     * �@@�|validate�����O���[�v()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�o�^�������N�G�X�g�̏ꍇ <BR>
     * �@@�|submit�����O���[�v()���R�[������B <BR>
     * <BR>
     * <BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4175EE3F00FC
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminMCAdminPermGrpRegistInputRequest)
        {
            l_response = this.getInputScreen((WEB3AdminMCAdminPermGrpRegistInputRequest)l_request);
        
        }
        else if (l_request instanceof WEB3AdminMCAdminPermGrpRegistConfirmRequest)
        {
            l_response = this.validatePermGrp((WEB3AdminMCAdminPermGrpRegistConfirmRequest)l_request);
        
        }
        else if (l_request instanceof WEB3AdminMCAdminPermGrpRegistCompleteRequest)
        {
            l_response = this.submitPermGrp((WEB3AdminMCAdminPermGrpRegistCompleteRequest)l_request);
        
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
     * (get���͉��)<BR>
     * �Ǘ��Ҍ����O���[�v���͉�ʕ\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҃��j���[����i�Ǘ��҃O���[�v�o�^�jget���͉�ʁv�Q�ƁB <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����Ǘ��҃O���[�v�o�^���̓��N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * <BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4175EE3F011B
     */
    protected WEB3AdminMCAdminPermGrpRegistInputResponse getInputScreen(WEB3AdminMCAdminPermGrpRegistInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminMCAdminPermGrpRegistInputRequest l_request)";         
        log.entering(STR_METHOD_NAME);
        
        //1.1 getInstanceFrom���O�C�����
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
          
        //1.2 validate����(�@@�\�J�e�S���R�[�h�i=�Ǘ��Ҍ����Ǘ� �j : String, is�X�V�i=true�j : boolean)
        l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_PERMISSION,true);
         
        
        //1.3 �Ǘ��҃��j���[����Ǘ��҃O���[�v�o�^���̓��X�|���X(WEB3GenRequest)
        WEB3AdminMCAdminPermGrpRegistInputResponse l_response = new WEB3AdminMCAdminPermGrpRegistInputResponse(l_request);
                 
        log.exiting(STR_METHOD_NAME);        
        return l_response;
    }
    
    /**
     * (validate�����O���[�v)<BR>
     * �����O���[�v�o�^�m�F���������{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҃��j���[����i�Ǘ��҃O���[�v�o�^�jvalidate�����O���[�v�v�Q�ƁB <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��҃O���[�v�o�^�jvalidate�����O���[�v�v<BR>
     *         ��̈ʒu    : 1.8(*1) ����t���[<BR>
     *         �Y���s�����ɑ��݂���ꍇ�i�Ǘ��҃^�C�v() != null�j�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01226           <BR>
     * =========================================================== <BR>
     * <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����Ǘ��҃O���[�v�o�^�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistConfirmResponse
     * @@roseuid 4175EE3F013B
     */
    protected WEB3AdminMCAdminPermGrpRegistConfirmResponse validatePermGrp(WEB3AdminMCAdminPermGrpRegistConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validatePermGrp(WEB3AdminMCAdminPermGrpRegistConfirmRequest l_request) ";
        log.entering(STR_METHOD_NAME); 
        
        //1.1 validate()
        l_request.validate();
        //1.2 getInstanceFrom���O�C�����()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        //1.3  validate����(�@@�\�J�e�S���R�[�h)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_PERMISSION, true);
        //1.4 get�،���ЃR�[�h
        String l_strInstitutionCode = l_administartor.getInstitutionCode();
        //1.5  isDIR�Ǘ���()
        boolean l_blnDir = l_administartor.isDirAdministrator();
        //1.6  validateDIR�Ǘ���
        l_request.adminTypeUnit.validateDIRAdmin(l_blnDir);
        //1.7  get�Ǘ��҃^�C�v   
        WEB3AdminMCAdminType l_adminMCAdminType = null; 

        //1.10 ���b�Z�[�W �Ǘ��҃��j���[����Ǘ��҈ꗗ���X�|���X(WEB3GenRequest) 
        try
        {          
            l_adminMCAdminType = new WEB3AdminMCAdminType(l_strInstitutionCode, l_request.adminTypeUnit.permissionLevel);
        }
        catch (WEB3BusinessLayerException l_ex)
        {        
            WEB3AdminMCAdminPermGrpRegistConfirmResponse l_response = (WEB3AdminMCAdminPermGrpRegistConfirmResponse)l_request.createResponse();

            log.exiting(STR_METHOD_NAME);  
            return l_response;           
        }
        //1.8 (*1) �Y���s�����ɑ��݂���ꍇ�i�Ǘ��҃^�C�v() != null�j�A��O���X���[����B        
        log.error("�Y���s�����ɑ��݂���ꍇ.");
        log.exiting(STR_METHOD_NAME);
        throw new WEB3BusinessLayerException(
        WEB3ErrorCatalog.BUSINESS_ERROR_01226,
           this.getClass().getName() + STR_METHOD_NAME);            
        
    }
    
    /**
     * (submit�����O���[�v)<BR>
     * �����O���[�v�o�^�������������{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҃��j���[����i�Ǘ��҃O���[�v�o�^�jsubmit�����O���[�v�v�Q�ƁB <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��҃O���[�v�o�^�jsubmit�����O���[�v�v<BR>
     *         ��̈ʒu    : 1.9(*1) ����t���[<BR>
     *         �Y���s�����ɑ��݂���ꍇ�i�Ǘ��҃^�C�v() != null�j�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01226           <BR>
     * =========================================================== <BR>
     * <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����Ǘ��҃O���[�v�o�^�������N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistCompleteResponse
     * @@roseuid 4175EF03033E
     */
    protected WEB3AdminMCAdminPermGrpRegistCompleteResponse submitPermGrp(WEB3AdminMCAdminPermGrpRegistCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validatePermGrp(WEB3AdminMCAdminPermGrpRegistConfirmRequest l_request) ";
        log.entering(STR_METHOD_NAME); 
        
        //1.1 validate()
        l_request.validate();
        //1.2 getInstanceFrom���O�C�����()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        //1.3  validate����(�@@�\�J�e�S���R�[�h)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_PERMISSION,true);
        //1.4 validate����p�X���[�h(String)
        l_administartor.validateTradingPassword(l_request.password);
        //1.5 get�،���ЃR�[�h
        String l_strInstitutionCode = l_administartor.getInstitutionCode();
        //1.6  isDIR�Ǘ���()
        boolean l_blnDir = l_administartor.isDirAdministrator();
        //1.6  validateDIR�Ǘ���
        l_request.adminTypeUnit.validateDIRAdmin(l_blnDir);
        //1.7  get�Ǘ��҃^�C�v   
        WEB3AdminMCAdminType l_adminMCAdminType = null;

        try
        {          
            l_adminMCAdminType = new WEB3AdminMCAdminType(l_strInstitutionCode, l_request.adminTypeUnit.permissionLevel);
        }
        catch (WEB3BusinessLayerException l_ex)
        {        
            //1.10  get�Ǘ��҃R�[�h()
            String l_admncode = l_administartor.getAdministratorCode();
            //1.11  saveNew�Ǘ��҃^�C�v(String, String, String, boolean, boolean, String)
            WEB3AdminMCAdminType.saveNewAdminType(l_strInstitutionCode, l_request.adminTypeUnit.permissionLevel, l_request.adminTypeUnit.permissionLevelName,
                                                l_request.adminTypeUnit.dirAdminFlag, l_request.adminTypeUnit.allBranchPermissionFlag, l_admncode);

           //1.12update�����\�@@�\�J�e�S��(String, String, �@@�\�J�e�S�����[], String)
           WEB3AdminMCAdminPermUnitCreateService l_adminPermUnitCreateServiceImpl = (WEB3AdminMCAdminPermUnitCreateService)Services.getService(WEB3AdminMCAdminPermUnitCreateService.class);
           l_adminPermUnitCreateServiceImpl.updateOperatePossibleTransactionCategory(l_strInstitutionCode, l_request.adminTypeUnit.permissionLevel,
                                                            l_request.transactionCategoryUnits, l_admncode);


            //1.13 ���b�Z�[�W �Ǘ��҃��j���[����Ǘ��҈ꗗ���X�|���X(WEB3GenRequest) 
            WEB3AdminMCAdminPermGrpRegistCompleteResponse l_response = (WEB3AdminMCAdminPermGrpRegistCompleteResponse)l_request.createResponse();

            log.exiting(STR_METHOD_NAME);  
            return l_response;         
          
        }
        //1.9 (*1) �Y���s�����ɑ��݂���ꍇ�i�Ǘ��҃^�C�v() != null�j�A��O���X���[����B        
        log.error("�Y���s�����ɑ��݂���ꍇ.");
        log.exiting(STR_METHOD_NAME);
        throw new WEB3BusinessLayerException(
        WEB3ErrorCatalog.BUSINESS_ERROR_01226,
            this.getClass().getName() + STR_METHOD_NAME);            
        
        
    }
}
@
