head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.26.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminDeleteServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����Ǘ��ҍ폜�T�[�r�XImpl(WEB3AdminMCAdminDeleteServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/30  �Ɍ��t (���u) �V�K�쐬
*/
package webbroker3.adminmc.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
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
import webbroker3.gentrade.data.AdministratorPK;
import webbroker3.util.WEB3LogUtility;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminDeleteService;
import webbroker3.adminmc.message.WEB3AdminMCAdminDeleteCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminDeleteConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminDeleteCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminDeleteConfirmResponse;

/**
 * (�Ǘ��҃��j���[����Ǘ��ҍ폜�T�[�r�XImpl)<BR>
 * �Ǘ��҃��j���[����Ǘ��ҍ폜�T�[�r�X�����N���X<BR>
 * 
 * @@author �Ɍ��t
 * @@version 1.0
 */
public class WEB3AdminMCAdminDeleteServiceImpl implements WEB3AdminMCAdminDeleteService 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private  static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminDeleteServiceImpl.class);
    
    /**
     * @@roseuid 41986414030D
     */
    public WEB3AdminMCAdminDeleteServiceImpl() 
    {
     
    }
    
    /**
     * �Ǘ��ҍ폜���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����Ǘ��ҍ폜�m�F���N�G�X�g�̏ꍇ <BR>
     * �@@�|validate�Ǘ���()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����Ǘ��ҍ폜�������N�G�X�g�̏ꍇ <BR>
     * �@@�|submit�Ǘ���()���R�[������B <BR>
     * <BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 417DAE98004E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminMCAdminDeleteConfirmRequest)
        {
            l_response = this.validateAdministrator((WEB3AdminMCAdminDeleteConfirmRequest)l_request);
        
        }
        else if(l_request instanceof WEB3AdminMCAdminDeleteCompleteRequest)
        {
            l_response = this.submitAdministrator((WEB3AdminMCAdminDeleteCompleteRequest)l_request);
        
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
     * (validate�Ǘ���)<BR>
     * �Ǘ��ҍ폜�m�F���������{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҃��j���[����i�Ǘ��ҍ폜�jvalidate�Ǘ��ҁv�Q�ƁB <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��ҍ폜�jvalidate�Ǘ��ҁv<BR>
     *         ��̈ʒu    : 1.6 �Ǘ���(�،���� : �،����,<BR>
     *         �Ǘ��҃R�[�h : String)<BR>
     *         �� �����f�[�^�`�F�b�N<BR>
     *         ���͂��ꂽ�Ǘ��҃R�[�h�ɂāA<BR>
     *         �Ǘ��҃I�u�W�F�N�g�𐶐�����B<BR>
     *         �����ł��Ȃ��ꍇ�A�폜�ΏۊǗ��҂��Ȃ��Ɣ��f���A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01222           <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��ҍ폜�jvalidate�Ǘ��ҁv<BR>
     *         ��̈ʒu    : 1.7(*1) ����t���[<BR>
     *         �Ǘ��҃R�[�h�����݂��Ȃ��ꍇ�i�I�u�W�F�N�g�������ł��Ȃ��ꍇ�j�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01222           <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��ҍ폜�jvalidate�Ǘ��ҁv<BR>
     *         ��̈ʒu    : 1.8 1.9 get�Ǘ��҃R�[�h( )<BR>
     *         �� �I�y���[�^�̊Ǘ��ҏ��͍폜�ł��Ȃ�<BR>
     *         ���O�C�����̊Ǘ��҂̏ꍇ<BR>
     *         �i�I�y���[�^.get�Ǘ��҃R�[�h() == �폜�Ώ�.get�Ǘ��҃R�[�h()�j�̏ꍇ�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :     BUSINESS_ERROR_01227          <BR>        
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��ҍ폜�jvalidate�Ǘ��ҁv<BR>
     *         ��̈ʒu    : 1.10(*2) ����t���[<BR>
     *         ���O�C�����̊Ǘ��҂̏ꍇ<BR>
     *         �i�I�y���[�^.get�Ǘ��҃R�[�h() == �폜�Ώ�.get�Ǘ��҃R�[�h()�j�̏ꍇ�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :   BUSINESS_ERROR_01227            <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��ҍ폜�jvalidate�Ǘ��ҁv<BR>
     *         ��̈ʒu    : 1.12.2(*3.1) ����t���[<BR>
     *         �Ώۃf�[�^��DIR�Ǘ��҂̏ꍇ�iisDIR�Ǘ���() == true�j�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01223           <BR>
     * =========================================================== <BR>
     * <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����Ǘ��ҍ폜�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminDeleteConfirmResponse
     * @@roseuid 417DAE980052
     */
    protected WEB3AdminMCAdminDeleteConfirmResponse validateAdministrator(WEB3AdminMCAdminDeleteConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateAdministrator(WEB3AdminMCAdminDeleteConfirmRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����()
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h�i=�Ǘ��ҊǗ��j : String, is�X�V�i=true�j : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_ADMINISTRATOR, true);
        
        //1.4 get�،����()
        Institution l_institution = l_administrator.getInstitution();
        
        //1.5 validate���X����
        l_administrator.validateBranchPermission(l_request.branchCode);
        
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
        
        //1.8 get�Ǘ��҃R�[�h()
        String l_administratorCode = l_administrator.getAdministratorCode();
        
        //1.9 get�Ǘ��҃R�[�h()
        String l_otheradministartorCode = l_otheradministartor.getAdministratorCode();
        
        //1.10 (*2) ���O�C�����̊Ǘ��҂̏ꍇ�i�I�y���[�^.get�Ǘ��҃R�[�h() == �폜�Ώ�.get�Ǘ��҃R�[�h()�j�̏ꍇ�A��O���X���[����
        if (l_administratorCode.equals(l_otheradministartorCode))
        {
            log.error("�u�I�y���[�^.get�Ǘ��҃R�[�h() == �폜�Ώ�.get�Ǘ��҃R�[�h()�v�̗�O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01227,
                this.getClass().getName() + STR_METHOD_NAME);                
        }
        
        //1.11 isDIR�Ǘ���()
        boolean l_blnisdiradmn = l_administrator.isDirAdministrator();
        
        //(*3) ���O�C�����̊Ǘ��҂��ʏ�Ǘ��ҁiisDIR�Ǘ���() == false�j�ꍇ�̂ݏ������{
        if (!l_blnisdiradmn)
        {
            //1.12.1 isDIR�Ǘ���()
            boolean l_otherBlnisdiradmn = l_otheradministartor.isDirAdministrator();
            if (l_otherBlnisdiradmn)
            {
                log.error("�Ώۃf�[�^��DIR�Ǘ��҂̏ꍇ�iisDIR�Ǘ���() == true�j�A��O���X���[����B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01223,
                    this.getClass().getName() + STR_METHOD_NAME);                                
            }
        }
        
        //1.13 createResponse()
        WEB3AdminMCAdminDeleteConfirmResponse l_response = 
            (WEB3AdminMCAdminDeleteConfirmResponse)l_request.createResponse();
            
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit�Ǘ���)<BR>
     * �Ǘ��ҍ폜�������������{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҃��j���[����i�Ǘ��ҍ폜�jsubmit�Ǘ��ҁv�Q�ƁB <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��ҍ폜�jsubmit�Ǘ��ҁv<BR>
     *         ��̈ʒu    : 1.7 �Ǘ���(�،���� : �،����, <BR>
     *         �Ǘ��҃R�[�h : String)<BR>
     *         �� �����f�[�^�`�F�b�N<BR>
     *         ���͂��ꂽ�Ǘ��҃R�[�h�ɂāA<BR>
     *         �Ǘ��҃I�u�W�F�N�g�𐶐�����B<BR>
     *         �����ł��Ȃ��ꍇ�A�폜�ΏۊǗ��҂��Ȃ��Ɣ��f���A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01222           <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��ҍ폜�jsubmit�Ǘ��ҁv<BR>
     *         ��̈ʒu    : 1.8(*1) ����t���[<BR>
     *         �Ǘ��҃R�[�h�����݂��Ȃ��ꍇ�i�I�u�W�F�N�g�������ł��Ȃ��ꍇ�j�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01222           <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��ҍ폜�jsubmit�Ǘ��ҁv<BR>
     *         ��̈ʒu    : 1.9 1.10 get�Ǘ��҃R�[�h( )<BR>
     *         �� �I�y���[�^�̊Ǘ��ҏ��͍폜�ł��Ȃ�<BR>
     *         ���O�C�����̊Ǘ��҂̏ꍇ<BR>
     *         �i�I�y���[�^.get�Ǘ��҃R�[�h() == �폜�Ώ�.get�Ǘ��҃R�[�h()�j�̏ꍇ�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01227           <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��ҍ폜�jsubmit�Ǘ��ҁv<BR>
     *         ��̈ʒu    : 1.11(*2) ����t���[<BR>
     *         ���O�C�����̊Ǘ��҂̏ꍇ<BR>
     *         �i�I�y���[�^.get�Ǘ��҃R�[�h() == �폜�Ώ�.get�Ǘ��҃R�[�h()�j�̏ꍇ�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :   BUSINESS_ERROR_01227            <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��ҍ폜�jsubmit�Ǘ��ҁv<BR>
     *         ��̈ʒu    : 1.13.2(*3.1) ����t���[<BR>
     *         �Ώۃf�[�^��DIR�Ǘ��҂̏ꍇ�iisDIR�Ǘ���() == true�j�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :   BUSINESS_ERROR_01223            <BR>
     * =========================================================== <BR>
     * <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����Ǘ��ҍ폜�������N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDeleteCompleteResponse
     * @@roseuid 417DAE98005D
     */
    protected WEB3AdminMCAdminDeleteCompleteResponse submitAdministrator(WEB3AdminMCAdminDeleteCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitAdministrator(WEB3AdminMCAdminDeleteCompleteRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����()
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h�i=�Ǘ��ҊǗ��j : String, is�X�V�i=true�j : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_ADMINISTRATOR, true);
        
        //1.4 validate���X����(String[])
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //1.5 validate����p�X���[�h(String)
        l_administrator.validateTradingPassword(l_request.password);
        
        //1.6 get�،����()
        Institution l_institution = l_administrator.getInstitution();
        
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

        //1.9 get�Ǘ��҃R�[�h()
        String l_administratorCode = l_administrator.getAdministratorCode();
        
        //1.10 get�Ǘ��҃R�[�h()
        String l_otheradministartorCode = l_otheradministartor.getAdministratorCode();
        
        //1.11 (*2) ���O�C�����̊Ǘ��҂̏ꍇ�i�I�y���[�^.get�Ǘ��҃R�[�h() == �폜�Ώ�.get�Ǘ��҃R�[�h()�j�̏ꍇ�A��O���X���[����B
        if (l_administratorCode.equals(l_otheradministartorCode))
        {
            log.error("�u�I�y���[�^.get�Ǘ��҃R�[�h() == �폜�Ώ�.get�Ǘ��҃R�[�h()�v�̗�O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01227,
                this.getClass().getName() + STR_METHOD_NAME);               
        }
        
        //1.12 isDIR�Ǘ���()
        boolean l_blnisdiradmn = l_administrator.isDirAdministrator(); 
        
        //1.13 (*3) ���O�C�����̊Ǘ��҂��ʏ�Ǘ��ҁiisDIR�Ǘ���() == false�j�ꍇ�̂ݏ������{       
        if (!l_blnisdiradmn)
        {
            //1.13.1 isDIR�Ǘ���()
            boolean l_otherBlnisdiradmn = l_otheradministartor.isDirAdministrator();
            if (l_otherBlnisdiradmn)
            {
                log.error("�Ώۃf�[�^��DIR�Ǘ��҂̏ꍇ�iisDIR�Ǘ���() == true�j�A��O���X���[����B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01223,
                    this.getClass().getName() + STR_METHOD_NAME);                                
            }            
        }
        
        //1.14 get���O�C���h�c()
        long l_lngLoginId = l_otheradministartor.getLoginId();
        
        //1.15 removeLogin(long)
        OpLoginAdminService l_opLoginAdminService =
            (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
        l_opLoginAdminService.removeLogin(l_lngLoginId);
        
        //1.16 setLoginAttributes(long, Map)
        l_opLoginAdminService.setLoginAttributes(l_lngLoginId, null);
        
        //1.17 get�Ǘ��҂h�c()
        long l_lngAdministratorId = l_otheradministartor.getAdministratorId();
        
        //1.18 doDeleteQuery(PrimaryKey, String, Object[])
        try
        {        
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteQuery(new AdministratorPK(l_lngAdministratorId));
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
        
        //1.19 createResponse()
        WEB3AdminMCAdminDeleteCompleteResponse l_reponse = 
            (WEB3AdminMCAdminDeleteCompleteResponse)l_request.createResponse();        
        
        log.exiting(STR_METHOD_NAME);
        return l_reponse;
        
    }
}
@
