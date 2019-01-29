head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.26.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name          : (�Ǘ��҃��j���[����Ǘ��ҕύX�T�[�r�XImpl(WEB3AdminMCAdminChangeServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/29 ���q (���u) �V�K�쐬
*/

package webbroker3.adminmc.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
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
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminChangeService;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeCompleteRequest;
import webbroker3.adminmc.WEB3AdminMCAdminType;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeCompleteResponse;


/**
 * (�Ǘ��҃��j���[����Ǘ��ҕύX�T�[�r�XImpl)<BR>
 * �Ǘ��҃��j���[����Ǘ��ҕύX�T�[�r�X�����N���X<BR>
 * @@author ���q
 * @@version 1.0
 * <BR>
 */
public class WEB3AdminMCAdminChangeServiceImpl implements WEB3AdminMCAdminChangeService
{
    /**
      * ���O�o�̓��[�e�B���e�B�B
      */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminChangeServiceImpl.class);

    /**
     * @@roseuid 41986415037A
     */
    public WEB3AdminMCAdminChangeServiceImpl()
    {

    }

    /**
     * �Ǘ��ҕύX���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����Ǘ��ҕύX���̓��N�G�X�g�̏ꍇ <BR>
     * �@@�|get���͉��()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����Ǘ��ҕύX�m�F���N�G�X�g�̏ꍇ <BR>
     * �@@�|validate�Ǘ���()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����Ǘ��ҕύX�������N�G�X�g�̏ꍇ <BR>
     * �@@�|submit�Ǘ���()���R�[������B <BR>
     * <BR>
     * <BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 417DC8A500E6
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        // �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����Ǘ��ҕύX���̓��N�G�X�g�̏ꍇ
        if (l_request instanceof WEB3AdminMCAdminChangeInputRequest)
        {
            // get���͉��()���R�[������
            l_response = this.getInputScreen((WEB3AdminMCAdminChangeInputRequest)l_request);
        }
        // �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����Ǘ��ҕύX�m�F���N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminMCAdminChangeConfirmRequest)
        {
            // validate�Ǘ���()���R�[������
            l_response = this.validateAdministrator((WEB3AdminMCAdminChangeConfirmRequest)l_request);
        }
        // �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����Ǘ��ҕύX�������N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminMCAdminChangeCompleteRequest)
        {
            // submit�Ǘ���()���R�[������
            l_response = this.submitAdministrator((WEB3AdminMCAdminChangeCompleteRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                                                                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get���͉��)<BR>
     * �Ǘ��ҕύX���͉�ʕ\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҃��j���[����i�Ǘ��ҕύX�jget���͉�ʁv�Q�ƁB <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��ҕύX�jget���͉�ʁv<BR>
     *         ��̈ʒu    :1.6  �Ǘ���(�،���� : �،����, <BR>
     *         �Ǘ��҃R�[�h : String)<BR>
     *         �� �����f�[�^�`�F�b�N<BR>
     *         ���͂��ꂽ�Ǘ��҃R�[�h�ɂāA<BR>
     *         �Ǘ��҃I�u�W�F�N�g�𐶐�����B<BR>
     *         �����ł��Ȃ��ꍇ�A�ύX�ΏۊǗ��҂��Ȃ��Ɣ��f���A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01222           <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��ҕύX�jget���͉�ʁv<BR>
     *         ��̈ʒu    : 1.7(*1) ����t���[<BR>
     *         �Ǘ��҂����݂��Ȃ��ꍇ�i�I�u�W�F�N�g�������ł��Ȃ��ꍇ�j�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01222           <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��ҕύX�jget���͉�ʁv<BR>
     *         ��̈ʒu    : 1.9.2(*2.1) �Ώۃf�[�^��DIR�Ǘ��҂̏ꍇ<BR>
     *         �iisDIR�Ǘ���() == true�j�A��O���X���[����B<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01223           <BR>
     * =========================================================== <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����Ǘ��ҕύX���̓��N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * <BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 417DC8A500E8
     */
    protected WEB3AdminMCAdminChangeInputResponse getInputScreen(WEB3AdminMCAdminChangeInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminMCAdminChangeInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //1.1validate()
        l_request.validate();

        //1.2 getInstanceFrom���O�C�����()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3 validate����(�@@�\�J�e�S���R�[�h�i=�Ǘ��ҊǗ��j)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_ADMINISTRATOR, true);

        //1.4 get�،����( )
        Institution l_institution = l_administartor.getInstitution();

        //1.5 validate���X����
        l_administartor.validateBranchPermission(l_request.branchCode);

        //1.6 �Ǘ���
        WEB3Administrator l_administartor2 = null;
        try
        {
              l_administartor2 = new WEB3Administrator(l_institution, l_request.administratorCode);
        }
        catch (WEB3SystemLayerException l_ex)
        {
        	//1.7
             log.error("�u�Ǘ��҂����݂��Ȃ��v�̗�O���X���[����B");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(
                                                   WEB3ErrorCatalog.BUSINESS_ERROR_01222,
                                                   this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.8 get�Ǘ��҃R�[�h()
        String l_strAdministratorCode = l_administartor.getAdministratorCode();
        
        //1.9 get�Ǘ��҃R�[�h()
        String l_strOtherAdministartorCode = l_administartor2.getAdministratorCode();
        
        //1.10 (*2) ���O�C�����̊Ǘ��҂̏ꍇ�i�I�y���[�^.get�Ǘ��҃R�[�h() == �폜�Ώ�.get�Ǘ��҃R�[�h()�j�̏ꍇ�A��O���X���[����
        if (l_strAdministratorCode.equals(l_strOtherAdministartorCode))
        {
            log.error("�u�I�y���[�^.get�Ǘ��҃R�[�h() == �폜�Ώ�.get�Ǘ��҃R�[�h()�v�̗�O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01227,
                this.getClass().getName() + STR_METHOD_NAME);                
        }
        
        //1.8 isDIR�Ǘ���( )
        boolean l_blnisdiradmn = l_administartor.isDirAdministrator();

        //1.9 ���O�C�����̊Ǘ��҂��ʏ�Ǘ��ҁiisDIR�Ǘ���() == false�j
        if (!l_blnisdiradmn)
        {
            //1.9.1 isDIR�Ǘ���( )
            boolean l_blnisdiradmn2 = l_administartor2.isDirAdministrator();
            if (l_blnisdiradmn2)
            {
                 //1.9.2 DIR�Ǘ��҂̊Ǘ��҃^�C�v�̏ꍇ�iisDIR�Ǘ���() == true�j
                 log.error("�uDIR�Ǘ��҂̊Ǘ��҃^�C�v�v�̗�O���X���[����B");
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BusinessLayerException(
                                                       WEB3ErrorCatalog.BUSINESS_ERROR_01223,
                                                       this.getClass().getName() + STR_METHOD_NAME);
            }
        }

        //1.10 get�Ǘ��҃^�C�v
        WEB3AdminMCAdminType[] l_admintype = WEB3AdminMCAdminType.getAdminType(l_institution.getInstitutionCode(), "permission_Level", l_blnisdiradmn);
        int l_recno = l_admintype.length;

        // 1.11 �Ǘ��҃��j���[����Ǘ��ҕύX���̓��X�|���X
        WEB3AdminMCAdminChangeInputResponse l_response = (WEB3AdminMCAdminChangeInputResponse)l_request.createResponse();
        //1.12 �v���p�e�B�Z�b�g
        l_response.permissionLevelList = new String[l_recno];
        l_response.permissionLevelNameList = new String[l_recno];
        
        for (int i = 0; i < l_recno; i++)
        {
            //�������x���R�[�h�ꗗ
            l_response.permissionLevelList[i] = l_admintype[i].getPermissionLevel();
            //�������x�����ꗗ
            l_response.permissionLevelNameList[i] = l_admintype[i].getPermissionLevelName();
        }


        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�Ǘ���)<BR>
     * �Ǘ��ҕύX�m�F���������{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҃��j���[����i�Ǘ��ҕύX�jvalidate�Ǘ��ҁv�Q�ƁB <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��ҕύX�jvalidate�Ǘ��ҁv<BR>
     *         ��̈ʒu    : 1.6 �Ǘ���(�،���� : �،����, <BR>
     *         �Ǘ��҃R�[�h : String)<BR>
     *         �� �����f�[�^�`�F�b�N<BR>
     *         ���͂��ꂽ�Ǘ��҃R�[�h�ɂāA<BR>
     *         �Ǘ��҃I�u�W�F�N�g�𐶐�����B<BR>
     *         �����ł��Ȃ��ꍇ�A�ύX�ΏۊǗ��҂��Ȃ��Ɣ��f���A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01222           <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��ҕύX�jvalidate�Ǘ��ҁv<BR>
     *         ��̈ʒu    : 1.7(*1) ����t���[<BR>
     *         �Ǘ��҂����݂��Ȃ��ꍇ�i�I�u�W�F�N�g�������ł��Ȃ��ꍇ�j�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01222           <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��ҕύX�jvalidate�Ǘ��ҁv<BR>
     *         ��̈ʒu    : 1.10(*2) ����t���[<BR>
     *         �����Ώۃf�[�^���I�y���[�^.get�Ǘ��҃R�[�h() == �ύX�Ώ�.get�Ǘ��҃R�[�h()�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01227 <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��ҕύX�jvalidate�Ǘ��ҁv<BR>
     *         ��̈ʒu    : 1.12.2(*2.1) ����t���[<BR>
     *         �����Ώۃf�[�^��DIR�Ǘ��҂̏ꍇ�iisDIR�Ǘ���() == true�j�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01223           <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��ҕύX�jvalidate�Ǘ��ҁv<BR>
     *         ��̈ʒu    : 1.12.5(*2.2) ����t���[<BR>
     *         ���͂��ꂽ�Ǘ��҃^�C�v��DIR�Ǘ��҂̏ꍇ�iisDIR�Ǘ���() == true�j�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01225           <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��ҕύX�jvalidate�Ǘ��ҁv<BR>
     *         ��̈ʒu    : 1.11(*3) ����t���[<BR>
     *         ���[���A�h���X���s���ȏꍇ�iisMailAddress() == false�j�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_00777           <BR>
     * =========================================================== <BR>
     * <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����Ǘ��ҕύX�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminChangeConfirmResponse
     * @@roseuid 417DC8A500F6
     */
    protected WEB3AdminMCAdminChangeConfirmResponse validateAdministrator(WEB3AdminMCAdminChangeConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAdministrator(WEB3AdminMCAdminChangeConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //1.1validate()
        l_request.validate();

        //1.2 getInstanceFrom���O�C�����()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3 validate����(�@@�\�J�e�S���R�[�h�i=�Ǘ��ҊǗ��j)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_ADMINISTRATOR, true);

        //1.4 get�،����( )
        Institution l_institution = l_administartor.getInstitution();

        //1.5 validate���X����
        l_administartor.validateBranchPermission(l_request.adminRegistUnit.branchCode);

        //1.6 �Ǘ���
        WEB3Administrator l_administartor2 = null;
        try
        {
              l_administartor2 = new WEB3Administrator(l_institution, l_request.adminRegistUnit.administratorCode);
        }
        catch (WEB3SystemLayerException l_ex)
        {
           //1.7
             log.error("�u�Ǘ��҂����݂��Ȃ��v�̗�O���X���[����B");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01222,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.8 get�Ǘ��҃R�[�h( )
        String l_strAdministratorCode = l_administartor.getAdministratorCode();

        //1.9 get�Ǘ��҃R�[�h( )
        String l_strOtherAdministartorCode = l_administartor2.getAdministratorCode();
         
        //1.10 ���O�C�����̊Ǘ��҂̏ꍇ�i�I�y���[�^.get�Ǘ��҃R�[�h() == �ύX�Ώ�.get�Ǘ��҃R�[�h()�j�̏ꍇ�A��O���X���[����B
        if (l_strAdministratorCode.equals(l_strOtherAdministartorCode))
        {
            log.error("�u�I�y���[�^.get�Ǘ��҃R�[�h() == �폜�Ώ�.get�Ǘ��҃R�[�h()�v�̗�O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01227,
                this.getClass().getName() + STR_METHOD_NAME);                
        }

        //1.11 isDIR�Ǘ���( )
        boolean l_blnisdiradmn = l_administartor.isDirAdministrator();

        //1.12 �Ǘ��҂��ʏ�Ǘ��ҁiisDIR�Ǘ���() == false�j�ꍇ
        if (!l_blnisdiradmn)
        {
             //1.12.1 isDIR�Ǘ���( )
             boolean l_blnisdiradmn2 = l_administartor2.isDirAdministrator();
             //1.12.2  DIR�Ǘ��҂̊Ǘ��҃^�C�v�̏ꍇ�iisDIR�Ǘ���() == true�j
             if (l_blnisdiradmn2)
             {
                 log.error("�uDIR�Ǘ��҂̊Ǘ��҃^�C�v�v�̗�O���X���[����B");
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BusinessLayerException(
                               WEB3ErrorCatalog.BUSINESS_ERROR_01223,
                               this.getClass().getName() + STR_METHOD_NAME);
             }

             //1.12.3 �Ǘ��҃^�C�v(String, String)
             WEB3AdminMCAdminType l_adminmcadmintype = new WEB3AdminMCAdminType(l_institution.getInstitutionCode(), l_request.adminRegistUnit.permissionLevel);

             //1.12.4 isDIR�Ǘ���( )
             boolean l_blnisdiradmntyp = l_adminmcadmintype.isDIRAdministrator();

             //1.12.5  DIR�Ǘ��҂̊Ǘ��҃^�C�v�̏ꍇ�iisDIR�Ǘ���() == true�j
             if (l_blnisdiradmntyp)
             {
                 log.error("�uDIR�Ǘ��҂̊Ǘ��҃^�C�v�v�̗�O���X���[����B");
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BusinessLayerException(
                               WEB3ErrorCatalog.BUSINESS_ERROR_01225,
                               this.getClass().getName() + STR_METHOD_NAME);
             }
        }

        if (l_request.adminRegistUnit.mailAddress != null)
        {
            //1.13 isMailAddress(String)
            boolean l_blnisMailAddress = WEB3StringTypeUtility.isMailAddress(l_request.adminRegistUnit.mailAddress);
            //1.14 ���[���A�h���X���s���ȏꍇ�iisMailAddress() == false�j
            if (!l_blnisMailAddress)
            {
                log.error("�u���[���A�h���X���s���v�̗�O���X���[����B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                              WEB3ErrorCatalog.BUSINESS_ERROR_00777,
                              this.getClass().getName() + STR_METHOD_NAME);
            }            
        }

        //1.15 createResponse( )
        WEB3AdminMCAdminChangeConfirmResponse l_response = (WEB3AdminMCAdminChangeConfirmResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�Ǘ���)<BR>
     * �Ǘ��ҕύX�������������{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҃��j���[����i�Ǘ��ҕύX�jsubmit�Ǘ��ҁv�Q�ƁB <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��ҕύX�jsubmit�Ǘ��ҁv<BR>
     *         ��̈ʒu    : 1.7 �Ǘ���(�،���� : �،����, <BR>
     *         �Ǘ��҃R�[�h : String)<BR>
     *         �� �����f�[�^�`�F�b�N<BR>
     *         ���͂��ꂽ�Ǘ��҃R�[�h�ɂāA<BR>
     *         �Ǘ��҃I�u�W�F�N�g�𐶐�����B<BR>
     *         �����ł��Ȃ��ꍇ�A�ύX�ΏۊǗ��҂��Ȃ��Ɣ��f���A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :     BUSINESS_ERROR_01222          <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��ҕύX�jsubmit�Ǘ��ҁv<BR>
     *         ��̈ʒu    : 1.8(*1) ����t���[<BR>
     *         �Ǘ��҂����݂��Ȃ��ꍇ�i�I�u�W�F�N�g�������ł��Ȃ��ꍇ�j�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :     BUSINESS_ERROR_01222          <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��ҕύX�jsubmit�Ǘ��ҁv<BR>
     *         ��̈ʒu    : 1.11(*) ����t���[<BR>
     *         �����Ώۃf�[�^�����O�C�����̊Ǘ��҂̏ꍇ<BR>
     *             �i�I�y���[�^.get�Ǘ��҃R�[�h() == �ύX�Ώ�.get�Ǘ��҃R�[�h()�j�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01227<BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��ҕύX�jsubmit�Ǘ��ҁv<BR>
     *         ��̈ʒu    : 1.13.2(*2.1) ����t���[<BR>
     *         �����Ώۃf�[�^��DIR�Ǘ��҂̏ꍇ�iisDIR�Ǘ���() == true�j�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :     BUSINESS_ERROR_01223          <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��ҕύX�jsubmit�Ǘ��ҁv<BR>
     *         ��̈ʒu    : 1.13.5(*2.2) ����t���[<BR>
     *         ���͂��ꂽ�Ǘ��҃^�C�v��DIR�Ǘ��҂̏ꍇ�iisDIR�Ǘ���() == true�j�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01225           <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��ҕύX�jsubmit�Ǘ��ҁv<BR>
     *         ��̈ʒu    : 1.15(*3) ����t���[<BR>
     *         ���[���A�h���X���s���ȏꍇ�iisMailAddress() == false�j�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_00777           <BR>
     * =========================================================== <BR>
     * <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����Ǘ��ҕύX�������N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminChangeCompleteResponse
     * @@roseuid 417DC8A500F8
     */
    protected WEB3AdminMCAdminChangeCompleteResponse submitAdministrator(WEB3AdminMCAdminChangeCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitAdministrator(WEB3AdminMCAdminChangeCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //1.1validate()
        l_request.validate();

        //1.2 getInstanceFrom���O�C�����()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3 validate����(�@@�\�J�e�S���R�[�h�i=�Ǘ��ҊǗ��j)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_ADMINISTRATOR, true);

        //1.4 validate���X����
        l_administartor.validateBranchPermission(l_request.adminRegistUnit.branchCode);

        //1.5 validate����p�X���[�h
        l_administartor.validateTradingPassword(l_request.password);

        //1.6 get�،����( )
        Institution l_institution = l_administartor.getInstitution();

        //1.7 �Ǘ���
        WEB3Administrator l_administartor2 = null;
        try
        {
              l_administartor2 = new WEB3Administrator(l_institution, l_request.adminRegistUnit.administratorCode);
        }
        catch (WEB3SystemLayerException l_ex)
        {
           //1.8
             log.error("�u�Ǘ��҂����݂��Ȃ��v�̗�O���X���[����B");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01222,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.9 get�Ǘ��҃R�[�h( )
        String l_strAdministratorCode = l_administartor.getAdministratorCode();

        //1.10 get�Ǘ��҃R�[�h( )
        String l_strOtherAdministartorCode = l_administartor2.getAdministratorCode();
         
        //1.11 ���O�C�����̊Ǘ��҂̏ꍇ�i�I�y���[�^.get�Ǘ��҃R�[�h() == �ύX�Ώ�.get�Ǘ��҃R�[�h()�j�̏ꍇ�A��O���X���[����B
        if (l_strAdministratorCode.equals(l_strOtherAdministartorCode))
        {
            log.error("�u�I�y���[�^.get�Ǘ��҃R�[�h() == �폜�Ώ�.get�Ǘ��҃R�[�h()�v�̗�O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01227,
                this.getClass().getName() + STR_METHOD_NAME);                
        }

        //1.12 isDIR�Ǘ���( )
        boolean l_blnisdiradmn = l_administartor.isDirAdministrator();

        //1.13 �Ǘ��҂��ʏ�Ǘ��ҁiisDIR�Ǘ���() == false�j�ꍇ
        if (!l_blnisdiradmn)
        {
             //1.13.1 isDIR�Ǘ���( )
             boolean l_blnisdiradmn2 = l_administartor2.isDirAdministrator();
             //1.13.2  DIR�Ǘ��҂̊Ǘ��҃^�C�v�̏ꍇ�iisDIR�Ǘ���() == true�j
             if (l_blnisdiradmn2)
             {
                 log.error("�uDIR�Ǘ��҂̊Ǘ��҃^�C�v�v�̗�O���X���[����B");
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BusinessLayerException(
                               WEB3ErrorCatalog.BUSINESS_ERROR_01223,
                               this.getClass().getName() + STR_METHOD_NAME);
             }

             //1.13.3 �Ǘ��҃^�C�v(String, String)
             WEB3AdminMCAdminType l_adminmcadmintype =  new WEB3AdminMCAdminType(l_institution.getInstitutionCode(), l_request.adminRegistUnit.permissionLevel);

             //1.13.4 isDIR�Ǘ���( )
             boolean l_blnisdiradmntyp = l_adminmcadmintype.isDIRAdministrator();

             //1.13.5  DIR�Ǘ��҂̊Ǘ��҃^�C�v�̏ꍇ�iisDIR�Ǘ���() == true�j
             if (l_blnisdiradmntyp)
             {
                 log.error("�uDIR�Ǘ��҂̊Ǘ��҃^�C�v�v�̗�O���X���[����B");
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BusinessLayerException(
                               WEB3ErrorCatalog.BUSINESS_ERROR_01225,
                               this.getClass().getName() + STR_METHOD_NAME);
             }
        }
        
        if (l_request.adminRegistUnit.mailAddress != null)
        {
            //1.14 isMailAddress(String)
            boolean l_blnisMailAddress = WEB3StringTypeUtility.isMailAddress(l_request.adminRegistUnit.mailAddress);
            //1.15 ���[���A�h���X���s���ȏꍇ�iisMailAddress() == false�j
            if (!l_blnisMailAddress)
            {
                log.error("�u���[���A�h���X���s���v�̗�O���X���[����B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                              WEB3ErrorCatalog.BUSINESS_ERROR_00777,
                              this.getClass().getName() + STR_METHOD_NAME);
            }            
        }

        //1.16 get�Ǘ��҂h�c�i�j
        long l_lnadmnid = l_administartor2.getAdministratorId();

        //1.17 get�Ǘ��҃R�[�h( )
        String l_admncode3 = l_administartor.getAdministratorCode();

        //1.18 doUpdateQuery(PrimaryKey, String, Object[], Map)
        Map l_changeMap = new HashMap();
        // �Ǘ��Җ�
        l_changeMap.put("name", l_request.adminRegistUnit.administratorName);
        // email_address
        l_changeMap.put("email_address", l_request.adminRegistUnit.mailAddress);            
        // �������x��
        l_changeMap.put("permission_level", l_request.adminRegistUnit.permissionLevel);
        // �X�V�҃R�[�h
        l_changeMap.put("last_updater", l_admncode3);
        //�X�V���� = ��������
        l_changeMap.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

        try
        {
             QueryProcessor l_processor = Processors.getDefaultProcessor();
             l_processor.doUpdateQuery(new AdministratorPK(l_lnadmnid),
                                                 l_changeMap);
        }
        catch (DataQueryException l_dqe)
        {
            String l_strMessage = "�Ǘ��҃^�C�v���X�V error";
            log.error(l_strMessage, l_dqe);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                                               WEB3AdminMCAdminType.class.getName() + STR_METHOD_NAME,
                                                               l_dqe.getMessage(),
                                                               l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            String l_strMessage = "�Ǘ��҃^�C�v���X�V  error";
            log.error(l_strMessage, l_dne);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                                               WEB3AdminMCAdminType.class.getName() + STR_METHOD_NAME,
                                                               l_dne.getMessage(),
                                                               l_dne);
        }

        //1.19 createResponse( )
        WEB3AdminMCAdminChangeCompleteResponse l_response = (WEB3AdminMCAdminChangeCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
