head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.27.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermGrpDeleteServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�폜�T�[�r�XImpl(WEB3AdminMCAdminPermGrpDeleteServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/01 �͌d�� (���u) �V�K�쐬
*/

package webbroker3.adminmc.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermGrpDeleteService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermUnitCreateService;
import webbroker3.adminmc.WEB3AdminMCAdminType;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDeleteConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDeleteCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDeleteConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDeleteCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminTypeUnit;
import webbroker3.adminmc.message.WEB3AdminMCTransactionCategoryUnit;

/**
 * (�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�폜�T�[�r�XImpl)<BR>
 * �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�폜�T�[�r�X�����N���X<BR>
 * 
 * @@author �͌d��
 * @@version 1.0
 */
public class WEB3AdminMCAdminPermGrpDeleteServiceImpl implements WEB3AdminMCAdminPermGrpDeleteService 
{
    /**         
     * ���O�o�̓��[�e�B���e�B�B         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCAdminPermGrpDeleteServiceImpl.class);
    
    /**
     * @@roseuid 419864130000
     */
    public WEB3AdminMCAdminPermGrpDeleteServiceImpl() 
    {
     
    }
    
    /**
     * �Ǘ��Ҍ����O���[�v�폜���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�폜�m�F���N�G�X�g�̏ꍇ <BR>
     * �@@�|validate�����O���[�v()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�폜�������N�G�X�g�̏ꍇ <BR>
     * �@@�|submit�����O���[�v()���R�[������B <BR>
     * <BR>
     * <BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4177365003C8
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminMCAdminPermGrpDeleteConfirmRequest)
        {            
            l_response = this.validatePermGrp((WEB3AdminMCAdminPermGrpDeleteConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminMCAdminPermGrpDeleteCompleteRequest)
        {
            l_response = this.submitPermGrp((WEB3AdminMCAdminPermGrpDeleteCompleteRequest)l_request);           
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, this.getClass().getName() + STR_METHOD_NAME);           
        }       
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�����O���[�v)<BR>
     * �����O���[�v�폜�m�F���������{����B<BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��҃O���[�v�폜�jvalidate�����O���[�v�v<BR>
     *         ��̈ʒu    :1.8.2(*2.1) ����t���[<BR>
     *         DIR�Ǘ��҂̊Ǘ��҃^�C�v�̏ꍇ�iisDIR�Ǘ���() == true�j�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :     BUSINESS_ERROR_01225          <BR>
     * =========================================================== <BR>
     * <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�폜�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDeleteConfirmResponse
     * @@roseuid 4177365003D8
     */
    protected WEB3AdminMCAdminPermGrpDeleteConfirmResponse validatePermGrp(WEB3AdminMCAdminPermGrpDeleteConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validatePermGrp(WEB3AdminMCAdminPermGrpDeleteConfirmRequest l_request)";         
        log.entering(STR_METHOD_NAME);

        //1.1 validate()
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();        
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h�i=�Ǘ��Ҍ����Ǘ��j : String, is�X�V�i=true�j : boolean)
        l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_PERMISSION,true);
        
        //1.4 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_web3Administrator.getInstitutionCode();  
        
        //1.5 �Ǘ��҃^�C�v(�،���ЃR�[�h : String, �������x�� : String)
        WEB3AdminMCAdminType l_web3AdminMCAdminType = new WEB3AdminMCAdminType(l_strInstitutionCode, l_request.permissionLevel);

        //1.6 validate�Ǘ��҃^�C�v�폜( )
        l_web3AdminMCAdminType.validateAdminTypeDelete();
        
        //1.7 isDIR�Ǘ���( )
        boolean l_blIsDirAdministrator = l_web3Administrator.isDirAdministrator();
        
        //1.8 DIR�Ǘ��҂łȂ��iisDIR�Ǘ���() == false�j�ꍇ�̂ݏ������{
        if (l_blIsDirAdministrator == false)
        {
            //1.8.1 isDIR�Ǘ���( )
            l_blIsDirAdministrator = l_web3AdminMCAdminType.isDIRAdministrator();
            
            //1.8.2 (*2.1) DIR�Ǘ��҂̊Ǘ��҃^�C�v�̏ꍇ�iisDIR�Ǘ���() == true�j�A��O���X���[����B
            if (l_blIsDirAdministrator == true)
            {
                log.error("DIR�Ǘ��҂̊Ǘ��҃^�C�v�̏ꍇ�̃G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01225,
                    this.getClass().getName() + STR_METHOD_NAME);                     
            }            
        }
        
        //1.9 create�Ǘ��҃^�C�v���(�Ǘ��҃^�C�v : �Ǘ��҃^�C�v)
        WEB3AdminMCAdminPermUnitCreateService l_service= (WEB3AdminMCAdminPermUnitCreateService)Services.getService(WEB3AdminMCAdminPermUnitCreateService.class); 
        WEB3AdminMCAdminTypeUnit l_web3AdminMCAdminTypeUnit = l_service.createAdminTypeUnit(l_web3AdminMCAdminType);
            
        //1.10 create�����\�@@�\�J�e�S���ꗗ(String, String, String[])
        WEB3AdminMCTransactionCategoryUnit[] l_AdminMCTransactionCategoryUnits = l_service.createOperatePossibleTransactionCategoryUnit(l_strInstitutionCode, l_request.permissionLevel, null);
            
        //1.11 �Ǘ��҃��j���[����Ǘ��҃O���[�v�폜�m�F���X�|���X(l_request : WEB3GenRequest)
        WEB3AdminMCAdminPermGrpDeleteConfirmResponse l_response = (WEB3AdminMCAdminPermGrpDeleteConfirmResponse)(l_request.createResponse());
        
        //(*3)���X�|���X�f�[�^�v���p�e�B�Ɉȉ��̒ʂ�l���Z�b�g����B
        l_response.adminTypeUnit = l_web3AdminMCAdminTypeUnit; 
        l_response.transactionCategoryUnits = l_AdminMCTransactionCategoryUnits; 
              
        log.exiting(STR_METHOD_NAME);        
        return l_response;
    }
    
    /**
     * (submit�����O���[�v)<BR>
     * �����O���[�v�폜�������������{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҃��j���[����i�Ǘ��҃O���[�v�폜�jsubmit�����O���[�v�v�Q�ƁB <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��҃O���[�v�폜�jsubmit�����O���[�v�v<BR>
     *         ��̈ʒu    :1.9.2(*2.1) ����t���[<BR>
     *         DIR�Ǘ��҂̊Ǘ��҃^�C�v�̏ꍇ�iisDIR�Ǘ���() == true�j�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :     BUSINESS_ERROR_01225          <BR>
     * =========================================================== <BR>
     * <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�폜�������N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDeleteCompleteResponse
     * @@roseuid 4177365003DA
     */
    protected WEB3AdminMCAdminPermGrpDeleteCompleteResponse submitPermGrp(WEB3AdminMCAdminPermGrpDeleteCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitPermGrp(WEB3AdminMCAdminPermGrpDeleteCompleteRequest l_request)";         
        log.entering(STR_METHOD_NAME);

        //1.1 validate()
        l_request.validate();   
        
        //1.2 getInstanceFrom���O�C�����
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h�i=�Ǘ��Ҍ����Ǘ��j : String, is�X�V�i=true�j : boolean)
        l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_PERMISSION,true);
        
        //1.4 validate����p�X���[�h(�p�X���[�h : String)
        l_web3Administrator.validateTradingPassword(l_request.password);
        
        //1.5 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_web3Administrator.getInstitutionCode();
        
        //1.6 �Ǘ��҃^�C�v(�،���ЃR�[�h : String, �������x�� : String)
        WEB3AdminMCAdminType l_web3AdminMCAdminType = new WEB3AdminMCAdminType(l_strInstitutionCode, l_request.permissionLevel);
         
        //1.7 validate�Ǘ��҃^�C�v�폜( )
        l_web3AdminMCAdminType.validateAdminTypeDelete(); 
        
        //1.8 isDIR�Ǘ���( )
        boolean l_blIsDirAdministrator = l_web3Administrator.isDirAdministrator();
        
        //1.9 (*2) DIR�Ǘ��҂łȂ��iisDIR�Ǘ���() == false�j�ꍇ�̂ݏ������{
        if (l_blIsDirAdministrator == false)
        {
            //1.9.1 isDIR�Ǘ���( )
            l_blIsDirAdministrator = l_web3AdminMCAdminType.isDIRAdministrator();
            
            //1.9.2 (*2.1) DIR�Ǘ��҂̊Ǘ��҃^�C�v�̏ꍇ�iisDIR�Ǘ���() == true�j�A��O���X���[����  
            if (l_blIsDirAdministrator == true)
            {
                log.error("DIR�Ǘ��҂̊Ǘ��҃^�C�v�̏ꍇ�̃G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01225,
                    this.getClass().getName() + STR_METHOD_NAME);                     
            }                                             
        }
        
        //1.10 get�Ǘ��҃R�[�h( )
        String l_strAdministratorCode = l_web3Administrator.getAdministratorCode();
        
        //1.11 saveDelete�Ǘ��҃^�C�v(String, String)
        WEB3AdminMCAdminType.saveDeleteAdminType(l_strInstitutionCode, l_request.permissionLevel);
        
        //1.12 update�����\�@@�\�J�e�S��
        WEB3AdminMCAdminPermUnitCreateService l_service= (WEB3AdminMCAdminPermUnitCreateService)Services.getService(WEB3AdminMCAdminPermUnitCreateService.class);
        l_service.updateOperatePossibleTransactionCategory(l_strInstitutionCode, l_request.permissionLevel, null, l_strAdministratorCode);
        
        //1.13 createResponse()
        WEB3AdminMCAdminPermGrpDeleteCompleteResponse l_response = (WEB3AdminMCAdminPermGrpDeleteCompleteResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
