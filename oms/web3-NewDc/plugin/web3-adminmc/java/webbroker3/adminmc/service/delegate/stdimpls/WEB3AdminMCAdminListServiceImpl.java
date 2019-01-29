head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.27.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����Ǘ��҈ꗗ�T�[�r�XImpl(WEB3AdminMCAdminListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/24  �� �� �@@ (���u) �V�K�쐬
*/

package webbroker3.adminmc.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.adminmc.WEB3AdminMCAdminRegistUnitComparator;
import webbroker3.adminmc.WEB3AdminMCAdminType;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminListService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminRegistUnitCreateService;
import webbroker3.adminmc.message.WEB3AdminMCAdminListInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminListRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminListInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminListResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminRegistUnit;


/**
 * (�Ǘ��҃��j���[����Ǘ��҈ꗗ�T�[�r�XImpl)<BR>
 * �Ǘ��҃��j���[����Ǘ��҈ꗗ�T�[�r�X�����N���X<BR>
 * @@author �����@@
 * @@version 1.0
 */

public class WEB3AdminMCAdminListServiceImpl implements WEB3AdminMCAdminListService 
{
    
    /**
     *���O�o�̓��[�e�B���e�B�B
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminListServiceImpl.class);
    
    /**
     * @@roseuid 419864110290
     */
    public WEB3AdminMCAdminListServiceImpl() 
    {
     
    }
    
    /**
     * �Ǘ��҈ꗗ���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����Ǘ��҈ꗗ���̓��N�G�X�g�̏ꍇ <BR>
     * �@@�|get���͉��()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����Ǘ��҈ꗗ���N�G�X�g�̏ꍇ <BR>
     * �@@�|get�Ǘ��҈ꗗ()���R�[������B <BR>
     * <BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 417DEEC703B5
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminMCAdminListInputRequest)
        {
            l_response = this.getInputScreen((WEB3AdminMCAdminListInputRequest)l_request);
        
        }
        else if (l_request instanceof WEB3AdminMCAdminListRequest)
        {
            l_response = this.getAdminList((WEB3AdminMCAdminListRequest)l_request);
        
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
     * �Ǘ��҈ꗗ�������͉�ʕ\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҃��j���[����i�Ǘ��҈ꗗ�jget���͉�ʁv�Q�ƁB <BR>
     * <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����Ǘ��҈ꗗ���̓��N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminListResponse
     * @@roseuid 417DEEC703B9
     */
    protected WEB3AdminMCAdminListInputResponse getInputScreen(WEB3AdminMCAdminListInputRequest l_request)   throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminMCAdminListInputRequest l_request) ";
        log.entering(STR_METHOD_NAME); 
        
        //1.1 getInstanceFrom���O�C�����()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        //1.2  validate����(�@@�\�J�e�S���R�[�h)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_ADMINISTRATOR,false);
        //1.3 get�،���ЃR�[�h
        String l_strInstitutionCode = l_administartor.getInstitutionCode();
        //1.4  isDIR�Ǘ���()
        boolean l_blnDir = l_administartor.isDirAdministrator();
        //1.5  get�Ǘ��҃^�C�v
        String l_strSortCond = "permission_Level";        
        WEB3AdminMCAdminType[] l_strAdminMCAdminType = WEB3AdminMCAdminType.getAdminType(l_strInstitutionCode, l_strSortCond, l_blnDir);
        //1.6  ���b�Z�[�W �Ǘ��҃��j���[����Ǘ��҈ꗗ���̓��X�|���X
        WEB3AdminMCAdminListInputResponse l_response = (WEB3AdminMCAdminListInputResponse)l_request.createResponse();
        //1.7 (*1) �v���p�e�B�Z�b�g
        //���X�|���X�f�[�^�v���p�e�B�Ɉȉ��̒ʂ�l���Z�b�g����B
        //�������x���R�[�h�ꗗ�F�@@get�Ǘ��҃^�C�v()�̖߂�l���A�������x���̔z����쐬���Z�b�g����B
        //�����x�����ꗗ�F�@@get�Ǘ��҃^�C�v()�̖߂�l���A�������x����
        int l_intLength = l_strAdminMCAdminType.length;
        String[] l_permissionLevel =new String[l_intLength];
        String[] l_permissionLevelName =new String[l_intLength];
        for (int i = 0; i < l_intLength; i++)
        {       
           l_permissionLevel[i]  = l_strAdminMCAdminType[i].getPermissionLevel();
           l_permissionLevelName[i]  = l_strAdminMCAdminType[i].getPermissionLevelName();
        }
        l_response.permissionLevelList = l_permissionLevel;
        l_response.permissionLevelNameList = l_permissionLevelName;

        log.exiting(STR_METHOD_NAME);  
        return l_response;


    }
    
    /**
     * (get�Ǘ��҈ꗗ)<BR>
     * �Ǘ��҈ꗗ�\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҃��j���[����i�Ǘ��҈ꗗ�jget�Ǘ��҈ꗗ�v�Q�ƁB <BR>
     * <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����Ǘ��҈ꗗ���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminListResponse
     * @@roseuid 417DEEC703B7
     */
    protected WEB3AdminMCAdminListResponse getAdminList(WEB3AdminMCAdminListRequest l_request)  throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getAdminList(WEB3AdminMCAdminListRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h�i=�@@�\�J�e�S���R�[�h.�Ǘ��ҊǗ��j : String, is�X�V�i=false�j : boolean)
        l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_ADMINISTRATOR,false);
        
        //1.4 (*1) ���X�w�肪����ꍇ�i���N�G�X�g�f�[�^.���X�R�[�h != null�j
        if (l_request.branchCode != null)
        {
            //1.4.1 validate���X����(String)
            l_web3Administrator.validateBranchPermission(l_request.branchCode);
        }
        
        //1.5 create��������������
        String l_strQueryString = this.createQueryString(l_request);
        
        //1.6 create���������f�[�^�R���e�i
        String[] l_strQueryDataContainers = this.createQueryDataContainer(l_request);
              
        //1.7 get�Ǘ���(�Ǘ���, String, String[], String, Integer )
        Integer l_integerValue;
        if (l_request.errorCount == null || l_request.errorCount.equals("0"))
        {
            l_integerValue = null;
        }
        else
        {
            l_integerValue = Integer.valueOf(l_request.errorCount);
        }
        WEB3Administrator[] l_administrator =  WEB3Administrator.getAdministrators(l_web3Administrator, l_strQueryString, l_strQueryDataContainers, null, l_integerValue);
                
        if (l_administrator == null || l_administrator.length == 0)
        {
            log.debug("get�Ǘ��҂̌��ʂ��@@0���ł���");
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01948,
                     STR_METHOD_NAME);
        }        
        
        //1.8 create�Ǘ��ғo�^���(�Ǘ���[])
        WEB3AdminMCAdminRegistUnitCreateService l_service= (WEB3AdminMCAdminRegistUnitCreateService)Services.getService(WEB3AdminMCAdminRegistUnitCreateService.class);
        WEB3AdminMCAdminRegistUnit[] l_web3RegistUnits = l_service.createAdminRegistUnit(l_administrator);     
        
        //1.9 ���N�G�X�g�f�[�^.�\�[�g�L�[�����AComparator�𐶐�����B
        int l_intSortKeyLength = l_request.sortKeys.length; 
        WEB3AdminMCAdminRegistUnitComparator[] l_comparator = new WEB3AdminMCAdminRegistUnitComparator[l_intSortKeyLength];
        for (int i = 0; i < l_intSortKeyLength; i ++)        
        {
            //1.9.1 �Ǘ��ғo�^���Comparator(String, String)�B
            l_comparator[i] = new WEB3AdminMCAdminRegistUnitComparator(l_request.sortKeys[i].ascDesc, l_request.sortKeys[i].keyItem);
        }        
        //1.10 sort(obj�i=�Ǘ��ғo�^���[]�j : Object[], com�i=�Ǘ��ғo�^���Comparator[]�j : Comparator[])
        WEB3ArraysUtility.sort(l_web3RegistUnits, l_comparator);        
           
        //1.11 ���b�Z�[�W �Ǘ��҃��j���[����Ǘ��҈ꗗ���X�|���X(WEB3GenRequest) 
        WEB3AdminMCAdminListResponse l_response = (WEB3AdminMCAdminListResponse)l_request.createResponse();
        
        //1.12 �v���p�e�B�Z�b�g
        //get�Ǘ���()�̖߂�l�̔z��̂����A�\���Ώۍs�ifromIndex�`toIndex�j�̃I�u�W�F�N�g�z����w�肷��B
        
        int l_intPageSize = Integer.parseInt(l_request.pageSize);     
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);    
        
        WEB3PageIndexInfo l_pagiIndexInfo = new WEB3PageIndexInfo(l_web3RegistUnits, l_intPageIndex, l_intPageSize);
        
        l_response.adminRegistUnits = (WEB3AdminMCAdminRegistUnit[])l_pagiIndexInfo.getArrayReturned(WEB3AdminMCAdminRegistUnit.class);
        l_response.totalRecords = WEB3StringTypeUtility.formatNumber(l_pagiIndexInfo.getTotalRecords());
        l_response.totalPages = WEB3StringTypeUtility.formatNumber(l_pagiIndexInfo.getTotalPages());
        l_response.pageIndex = WEB3StringTypeUtility.formatNumber(l_pagiIndexInfo.getPageIndex());
        
        log.exiting(STR_METHOD_NAME);  
        return l_response;        
    }
    
    /**
     * (create��������������)<BR>
     * ���������������ҏW����B <BR>
     * <BR>
     * �P�j�@@�߂�l���� <BR>
     * �@@�߂�l�̌�������������C���X�^���X�i�FString�j�𐶐� <BR>
     * <BR>
     * �Q�j�@@���X�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.���X�R�[�h != null�j�̏ꍇ�A���X�R�[�h������ǉ�����B <BR>
     * <BR>
     * �@@" and branch_code = ? "<BR>
     * <BR>
     * �R�j�@@�Ǘ��҃R�[�h�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�Ǘ��҃R�[�h != null�j�̏ꍇ�A�Ǘ��҃R�[�h������ǉ�����B <BR>
     * <BR>
     * �@@" and administrator_code = ? "<BR>
     * <BR>
     * �S�j�@@�Ǘ��Җ������ǉ� ���w�肪����ꍇ�̂݁C�B������<BR>
     * �@@�i���N�G�X�g�f�[�^.�Ǘ��Җ� != null�j�̏ꍇ�A�Ǘ��Җ������ilike�j��ǉ�����B<BR> 
     * <BR>
     * �@@" and name like ? "<BR>
     * <BR>
     * �T�j�@@�������x�������ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�������x���R�[�h != null�j�̏ꍇ�A�������x��������ǉ�����B <BR>
     * <BR>
     * �@@" and permission_level = ? "<BR>
     * <BR>
     * �U�j�@@������C���X�^���X��ԋp <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����Ǘ��҈ꗗ���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return String
     * @@roseuid 417F3A1201E0
     */
    protected String createQueryString(WEB3AdminMCAdminListRequest l_request) 
    {
        final String STR_METHOD_NAME = " createQueryString(WEB3AdminMCAdminListRequest l_request)"; 
        
        log.entering(STR_METHOD_NAME);  
        
        // �P�j�@@�߂�l���� <BR>
        // �@@�߂�l�̌�������������C���X�^���X�i�FString�j�𐶐� <BR>
        StringBuffer l_strQueryString = new StringBuffer();
        // �Q�j�@@���X�����ǉ� ���w�肪����ꍇ�̂�<BR>
        // �@@�i���N�G�X�g�f�[�^.���X�R�[�h != null�j�̏ꍇ�A���X�R�[�h������ǉ�����B <BR>
        // <BR>
        // �@@" and branch_code = ? "<BR>
        if (l_request.branchCode != null)
        {
             l_strQueryString.append(" and branch_code = ? ");
        }
        
        // �R�j�@@�Ǘ��҃R�[�h�����ǉ� ���w�肪����ꍇ�̂�<BR>
        // �@@�i���N�G�X�g�f�[�^.�Ǘ��҃R�[�h != null�j�̏ꍇ�A�Ǘ��҃R�[�h������ǉ�����B <BR>
        // <BR>
        // �@@" and administrator_code = ? "<BR>
        if (l_request.administratorCode != null)
        {
             l_strQueryString.append(" and administrator_code = ? ");
        }

        // �S�j�@@�Ǘ��Җ������ǉ� ���w�肪����ꍇ�̂݁C�B������<BR>
        // �@@�i���N�G�X�g�f�[�^.�Ǘ��Җ� != null�j�̏ꍇ�A�Ǘ��Җ������ilike�j��ǉ�����B<BR> 
        // <BR>
        // �@@" and name like ? "<BR>
        if (l_request.administratorName != null)
        {
             l_strQueryString.append(" and name like ? ");
        }

        // �T�j�@@�������x�������ǉ� ���w�肪����ꍇ�̂�<BR>
        // �@@�i���N�G�X�g�f�[�^.�������x���R�[�h != null�j�̏ꍇ�A�������x��������ǉ�����B <BR>
        // <BR>
        // �@@" and permission_level = ? "<BR>
        if (l_request.permissionLevel != null)
        {
             l_strQueryString.append(" and permission_level = ? ");
        }

        // �U�j�@@������C���X�^���X��ԋp <BR>        
        log.exiting(STR_METHOD_NAME);
        String l_strQueryStringReturn = l_strQueryString.toString();
        return l_strQueryStringReturn;

    }
    
    /**
     * (create���������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i��ҏW����B <BR>
     * <BR>
     * �P�j�@@�߂�l���� <BR>
     * �@@�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐� <BR>
     * <BR>
     * �Q�j�@@���X�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.���X�R�[�h != null�j�̏ꍇ�A�߂�l�ҏW�p�C���X�^���X�ɕ��X�R�[�h��ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@���N�G�X�g�f�[�^.���X�R�[�h <BR>
     * <BR>
     * �R�j�@@�Ǘ��҃R�[�h�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�Ǘ��҃R�[�h != null�j�̏ꍇ�A�߂�l�ҏW�p�C���X�^���X�ɊǗ��҃R�[�h������ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@���N�G�X�g�f�[�^.�Ǘ��҃R�[�h <BR>
     * <BR>
     * �S�j�@@�Ǘ��Җ������ǉ� ���w�肪����ꍇ�̂݁C�B������<BR>
     * �@@�i���N�G�X�g�f�[�^.�Ǘ��Җ� != null�j�̏ꍇ�A�߂�l�ҏW�p�C���X�^���X�ɊǗ��Җ������ilike�j��ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@"%" + ���N�G�X�g�f�[�^.�Ǘ��Җ� + "%"<BR>
     * <BR>
     * �T�j�@@�������x�������ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�������x���R�[�h != null�j�̏ꍇ�A�߂�l�ҏW�p�C���X�^���X�Ɍ������x��������ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@���N�G�X�g�f�[�^.�������x���R�[�h <BR>
     * <BR>
     * �U�j�@@�z���ԋp <BR>
     * �@@�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j.toArray()��ԋp����B <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����Ǘ��҈ꗗ���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return java.lang.String[]
     * @@roseuid 417F42DB01C0
     */
    protected String[] createQueryDataContainer(WEB3AdminMCAdminListRequest l_request) 
    {
        final String STR_METHOD_NAME = "createQueryDataContainer(WEB3AdminMCAdminListRequest l_request) "; 
        
        log.entering(STR_METHOD_NAME);  
        
        // �P�j�@@�߂�l���� <BR>
        // �@@�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐� <BR>
        List l_lisContainers = new ArrayList();  
        
        // �Q�j�@@���X�����ǉ� ���w�肪����ꍇ�̂�<BR>
        // �@@�i���N�G�X�g�f�[�^.���X�R�[�h != null�j�̏ꍇ�A�߂�l�ҏW�p�C���X�^���X�ɕ��X�R�[�h��ǉ�����B <BR>
        // <BR>
        // �@@[add()�Ɏw�肷�����] <BR>
        // �@@���N�G�X�g�f�[�^.���X�R�[�h <BR>
        if (l_request.branchCode != null)
        {
             l_lisContainers.add(l_request.branchCode);
        }

        // �R�j�@@�Ǘ��҃R�[�h�����ǉ� ���w�肪����ꍇ�̂�<BR>
        // �@@�i���N�G�X�g�f�[�^.�Ǘ��҃R�[�h != null�j�̏ꍇ�A�߂�l�ҏW�p�C���X�^���X�ɊǗ��҃R�[�h������ǉ�����B <BR>
        // <BR>
        // �@@[add()�Ɏw�肷�����] <BR>
        // �@@���N�G�X�g�f�[�^.�Ǘ��҃R�[�h <BR>
        if (l_request.administratorCode != null)
        {
             l_lisContainers.add(l_request.administratorCode);
        }

        // �S�j�@@�Ǘ��Җ������ǉ� ���w�肪����ꍇ�̂݁C�B������<BR>
        // �@@�i���N�G�X�g�f�[�^.�Ǘ��Җ� != null�j�̏ꍇ�A�߂�l�ҏW�p�C���X�^���X�ɊǗ��Җ������ilike�j��ǉ�����B <BR>
        // <BR>
        // �@@[add()�Ɏw�肷�����] <BR>
        // �@@"%" + ���N�G�X�g�f�[�^.�Ǘ��Җ� + "%"<BR>
        if (l_request.administratorName != null)
        {
             String l_administratorName = null;
             l_administratorName = "%" + l_request.administratorName + "%";
             l_lisContainers.add(l_administratorName);
        }

        // �T�j�@@�������x�������ǉ� ���w�肪����ꍇ�̂�<BR>
        // �@@�i���N�G�X�g�f�[�^.�������x���R�[�h != null�j�̏ꍇ�A�߂�l�ҏW�p�C���X�^���X�Ɍ������x��������ǉ�����B <BR>
        // <BR>
        // �@@[add()�Ɏw�肷�����] <BR>
        // �@@���N�G�X�g�f�[�^.�������x���R�[�h <BR>
        if (l_request.permissionLevel != null)
        {
             l_lisContainers.add(l_request.permissionLevel);
        }

        // �U�j�@@�z���ԋp <BR>
        // �@@�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j.toArray()��ԋp����B <BR>        
        String[] l_strQueryDataContainers = new String[l_lisContainers.size()];
        l_lisContainers.toArray(l_strQueryDataContainers);
        log.exiting(STR_METHOD_NAME);         
        return l_strQueryDataContainers;
                        
    }
    

}
@
