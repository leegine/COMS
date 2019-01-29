head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.26.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermGrpListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ꗗ�T�[�r�XImpl(WEB3AdminMCAdminPermGrpListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/25 Tongwei(���u) �V�K�쐬
*/

package webbroker3.adminmc.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.adminmc.message.WEB3AdminMCAdminTypeUnit;
import webbroker3.adminmc.define.WEB3AdminMCAdminTypeUnitDef;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermGrpListService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermUnitCreateService;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpListRequest;
import webbroker3.adminmc.message.WEB3AdminMCSortKeyUnit;
import webbroker3.adminmc.WEB3AdminMCAdminType;
import webbroker3.adminmc.message.WEB3AdminMCTransactionCategoryUnit;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDetailsRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpListResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDetailsResponse;


/**
 * (�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ꗗ�T�[�r�XImpl)<BR>
 * �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ꗗ�T�[�r�X�����N���X<BR>
 * 
 * @@author Tongwei
 * @@version 1.0 
 */
public class WEB3AdminMCAdminPermGrpListServiceImpl implements WEB3AdminMCAdminPermGrpListService 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminPermGrpListServiceImpl.class);      
    
    /**
     * @@roseuid 419864120186
     */
    public WEB3AdminMCAdminPermGrpListServiceImpl() 
    {
     
    }
    
    /**
     * �Ǘ��Ҍ����O���[�v�ꗗ�^�ڍ׏��������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ꗗ���N�G�X�g�̏ꍇ <BR>
     * �@@�|get�����O���[�v�ꗗ()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ڍ׃��N�G�X�g�̏ꍇ <BR>
     * �@@�|get�����O���[�v�ڍ�()���R�[������B <BR>
     * <BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41774A6A0290
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null; 
        
        //�P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B
        if (l_request instanceof WEB3AdminMCAdminPermGrpListRequest)
        {
           //�� �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ꗗ���N�G�X�g�̏ꍇ 
           //�@@�|get�����O���[�v�ꗗ()���R�[������B�B 
            l_response = this.getPermGrpList((WEB3AdminMCAdminPermGrpListRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminMCAdminPermGrpDetailsRequest)
        {
            //�� �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ڍ׃��N�G�X�g�̏ꍇ 
            // �@@�|get�����O���[�v�ڍ�()���R�[������B
            l_response = this.getPermGrpDetail((WEB3AdminMCAdminPermGrpDetailsRequest)l_request);
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
     * (get�����O���[�v�ꗗ)<BR>
     * �����O���[�v�ꗗ�\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҃��j���[����i�Ǘ��҃O���[�v�ꗗ�jget�����O���[�v�ꗗ�v�Q�ƁB <BR>
     * <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ꗗ���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpListResponse
     * @@roseuid 41774A6A0292
     */
    protected WEB3AdminMCAdminPermGrpListResponse getPermGrpList(WEB3AdminMCAdminPermGrpListRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getPermGrpList(WEB3AdminMCAdminPermGrpListRequest l_request)";
            log.entering(STR_METHOD_NAME); 
       
        //1.1 validate()
        l_request.validate();
        
        //1.2getInstanceFrom���O�C�����
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h�i=�Ǘ��Ҍ����Ǘ��j : String, is�X�V�i=false�j : boolean)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_PERMISSION, false);
        
        //1.4 get�،���ЃR�[�h()
        String l_strInstitutionCode = l_administartor.getInstitutionCode();
        
        //1.5 create�\�[�g����(�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ꗗ�T�[�r�XImpl::create�\�[�g����)
        String l_strSortCond = this.createSortCond(l_request.sortKeys);
        
        //1.6 isDIR�Ǘ���()
        boolean l_blnDir =  l_administartor.isDirAdministrator();
        
        //1.7 get�Ǘ��҃^�C�v(String, String, boolean)
        WEB3AdminMCAdminType[] l_adminMCAdminType = WEB3AdminMCAdminType.getAdminType(l_strInstitutionCode, l_strSortCond, l_blnDir);
        
        if (l_adminMCAdminType == null || l_adminMCAdminType.length == 0)
        {
            log.debug("get�Ǘ��҃^�C�v�̌��ʂ��@@0���ł���");
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01948,
                     STR_METHOD_NAME);
        }   
        
        //get����()�̖߂�l�̔z��̂����A�\���Ώۍs�ifromIndex�`toIndex�j�̃I�u�W�F�N�g�z����w�肷��B
        int l_intPageSize = Integer.parseInt(l_request.pageSize);     
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);    
 
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(l_adminMCAdminType, l_intPageIndex, l_intPageSize);
        l_adminMCAdminType = (WEB3AdminMCAdminType[])l_pageIndexInfo.getArrayReturned(WEB3AdminMCAdminType.class);
        
        //1.8 create�Ǘ��҃^�C�v���(�Ǘ��҃^�C�v)
        WEB3AdminMCAdminPermUnitCreateService l_permUnitCreateService = 
            (WEB3AdminMCAdminPermUnitCreateService)Services.getService(WEB3AdminMCAdminPermUnitCreateService.class);
            
        //1.8.1 create�Ǘ��҃^�C�v���(�Ǘ��҃^�C�v)
        WEB3AdminMCAdminTypeUnit[] l_adminTypeUnit = l_permUnitCreateService.createAdminTypeUnit(l_adminMCAdminType);
        
        //1.9 �Ǘ��҃��j���[����Ǘ��҃O���[�v�ꗗ���X�|���X(WEB3GenRequest)
        WEB3AdminMCAdminPermGrpListResponse l_response = 
            (WEB3AdminMCAdminPermGrpListResponse)l_request.createResponse();
    
        //1.10 �v���p�e�B�Z�b�g
        l_response.adminTypeUnits = l_adminTypeUnit;
        l_response.totalRecords = WEB3StringTypeUtility.formatNumber(l_pageIndexInfo.getTotalRecords());
        l_response.totalPages = WEB3StringTypeUtility.formatNumber(l_pageIndexInfo.getTotalPages());
        l_response.pageIndex = WEB3StringTypeUtility.formatNumber(l_pageIndexInfo.getPageIndex());
       
        log.exiting(STR_METHOD_NAME);  
        return l_response;
    }
    
    /**
     * (get�����O���[�v�ڍ�)<BR>
     * �����O���[�v�ڍו\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҃��j���[����i�Ǘ��҃O���[�v�ڍׁjget�����O���[�v�ڍׁv�Q�ƁB <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��҃O���[�v�ڍׁjget�����O���[�v�ڍׁv<BR>
     *         ��̈ʒu    :1.6(*1) ����t���[<BR>
     *         �����s�����݂��Ȃ��ꍇ�i�Ǘ��҃^�C�v() == null�j�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_00398           <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��҃O���[�v�ڍׁjget�����O���[�v�ڍׁv<BR>
     *         ��̈ʒu    : 1.8.2(*2.1) ����t���[<BR>
     *         DIR�Ǘ��҂̊Ǘ��҃^�C�v�̏ꍇ�iisDIR�Ǘ���() == true�j�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01225           <BR>
     * =========================================================== <BR>
     * <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ڍ׃��N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDetailsResponse
     * @@roseuid 41774A6A0294
     */
    protected WEB3AdminMCAdminPermGrpDetailsResponse getPermGrpDetail(WEB3AdminMCAdminPermGrpDetailsRequest l_request) 
        throws WEB3BaseException     
    {
        final String STR_METHOD_NAME = " getPermGrpDetail(WEB3AdminMCAdminPermGrpDetailsRequest l_request)";
            log.entering(STR_METHOD_NAME); 
       
        //1.1 validate()
        l_request.validate();
   
        //1.2getInstanceFrom���O�C�����
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h�i=�Ǘ��Ҍ����Ǘ��j : String, is�X�V�i=false�j : boolean)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_PERMISSION, false);
        
        //1.4 get�،���ЃR�[�h()
        String l_strInstitutionCode = l_administartor.getInstitutionCode();     
        
        //1.5 �Ǘ��҃^�C�v(String, String)
        String l_strPermissionLevel = l_request.permissionLevel;
        WEB3AdminMCAdminType l_adminMCAdminType =
            new WEB3AdminMCAdminType(l_strInstitutionCode, l_strPermissionLevel); 
            
        //1.7  isDIR�Ǘ���()
        boolean l_blnIsDir1 = l_administartor.isDirAdministrator();
        
        //1.8 DIR�Ǘ��҂łȂ��iisDIR�Ǘ���() == false�j�ꍇ�̂ݏ������{
        if (!l_blnIsDir1)
        {
             //  1.8.1 isDIR�Ǘ���( )
            boolean l_blnIsDir2 = l_adminMCAdminType.isDIRAdministrator();
            if (l_blnIsDir2)
            {
                log.error("DIR�Ǘ��҂̊Ǘ��҃^�C�v�̏ꍇ�̃G���[");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01225,
                    this.getClass().getName() + "." + STR_METHOD_NAME); 
            }
        }
        //1.9 create�Ǘ��҃^�C�v���(�Ǘ��҃^�C�v)
        WEB3AdminMCAdminPermUnitCreateService l_permUnitCreateService = 
            (WEB3AdminMCAdminPermUnitCreateService)Services.getService(WEB3AdminMCAdminPermUnitCreateService.class);
            
        WEB3AdminMCAdminTypeUnit l_adminTypeUnit = l_permUnitCreateService.createAdminTypeUnit(l_adminMCAdminType);
        
        //1.10 create�����\�@@�\�J�e�S���ꗗ(String, String, String[])
        WEB3AdminMCTransactionCategoryUnit[] l_transactionCategoryUnits = 
            l_permUnitCreateService.createOperatePossibleTransactionCategoryUnit(l_strInstitutionCode, 
            l_strPermissionLevel, null);

        //1.11 �Ǘ��҃��j���[����Ǘ��҃O���[�v�ڍ׃��X�|���X(WEB3GenRequest)
        WEB3AdminMCAdminPermGrpDetailsResponse l_response = 
            (WEB3AdminMCAdminPermGrpDetailsResponse)l_request.createResponse();
   
        //1.12 �v���p�e�B�Z�b�g
        l_response.adminTypeUnit = l_adminTypeUnit;
        l_response.transactionCategoryUnits = l_transactionCategoryUnits;
       
        log.exiting(STR_METHOD_NAME);  
        return l_response;
    }
    
    /**
     * (create�\�[�g����)<BR>
     * �\�[�g�����������ҏW����B<BR> 
     * <BR>
     * �Ǘ��҃^�C�v�e�[�u���񕨗������g�p���A<BR>
     * �����̃\�[�g�L�[�������ʂ�̃\�[�g����������iorder by��j��ҏW���ԋp����B<BR>
     * <BR>
     * @@param l_sortKey - (�\�[�g�L�[)<BR>
     * �\�[�g�L�[<BR>
     * @@return String
     * @@roseuid 4177526502CE
     */
    protected String createSortCond(WEB3AdminMCSortKeyUnit[] l_sortKey) 
    {
        final String STR_METHOD_NAME = " createSortCond(WEB3AdminMCSortKeyUnit[] l_sortKey) "; 
        
        log.entering(STR_METHOD_NAME);
        
        if (l_sortKey == null)
        {
            log.debug("l_sortKey = null");
            return null;
        }
        
        StringBuffer l_strSortCond = new StringBuffer();
        
        int l_intSortKeyLength = l_sortKey.length;
        for (int i = 0; i < l_intSortKeyLength; i ++)
        {
            if (WEB3AdminMCAdminTypeUnitDef.PERMISSION_LEVEL.equals(l_sortKey[i].keyItem)) 
            {
                if (l_strSortCond.length() != 0) 
                {
                    l_strSortCond.append(", ");
                }
                l_strSortCond.append("permission_Level");
            }
            else if (WEB3AdminMCAdminTypeUnitDef.PERMISSION_LEVEL_NAME.equals(l_sortKey[i].keyItem))
            {
                if (l_strSortCond.length() != 0) 
                {
                    l_strSortCond.append(", ");
                }
                l_strSortCond.append("permission_Level_Name");
            }
            else if (WEB3AdminMCAdminTypeUnitDef.DIR_ADMIN_FLAG.equals(l_sortKey[i].keyItem))
            {
                if (l_strSortCond.length() != 0) 
                {
                    l_strSortCond.append(", ");
                }
                l_strSortCond.append("dir_Admin_Flag");
            }
            else if (WEB3AdminMCAdminTypeUnitDef.ALL_BRANCH_PERMISSION_FLAG.equals(l_sortKey[i].keyItem))
            {
                if (l_strSortCond.length() != 0) 
                {
                    l_strSortCond.append(", ");
                }
                l_strSortCond.append("all_Branch_Permission_Flag");
            }
            else
            {
                continue;   
            }

            if(WEB3AscDescDef.ASC.equals(l_sortKey[i].ascDesc))
            {
                 l_strSortCond.append( " asc");
            }
            else if (WEB3AscDescDef.DESC.equals(l_sortKey[i].ascDesc))
            {
                l_strSortCond.append(" desc");
            }                           
        }   
        
        log.exiting(STR_METHOD_NAME);  
        return l_strSortCond.toString();
    }
}
@
