head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.27.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����CC�I�y���[�^�ꗗ�T�[�r�XImpl(WEB3AdminMCCCOperatorListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/18 �͌d�� (���u) �V�K�쐬
*/

package webbroker3.adminmc.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.adminmc.WEB3AdminMCCCOperatorRegistUnitComparator;
import webbroker3.adminmc.service.delegate.WEB3AdminMCCCOperatorListService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCCCOperatorRegistUnitCreateService;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorListInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorListRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorListInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorListResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistUnit;


/**
 * (�Ǘ��҃��j���[����CC�I�y���[�^�ꗗ�T�[�r�XImpl)<BR>
 * �Ǘ��҃��j���[����CC�I�y���[�^�ꗗ�T�[�r�X�����N���X<BR>
 * 
 * @@author �͌d��
 * @@version 1.0
 */
public class WEB3AdminMCCCOperatorListServiceImpl implements WEB3AdminMCCCOperatorListService 
{
    /**         
     * ���O�o�̓��[�e�B���e�B�B         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCCCOperatorListServiceImpl.class);     

    /**
     * @@roseuid 4198640C01E4
     */
    public WEB3AdminMCCCOperatorListServiceImpl() 
    {
     
    }
    
    /**
     * CC�I�y���[�^�ꗗ���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����CC���ڰ��ꗗ����ظ��Ă̏ꍇ <BR>
     * �@@�|get���͉��()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����CC���ڰ��ꗗظ��Ă̏ꍇ <BR>
     * �@@�|getCC�I�y���[�^�ꗗ()���R�[������B <BR>
     * <BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 417F770902BE
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminMCCCOperatorListInputRequest)
        {
            
            l_response = this.getInputScreen((WEB3AdminMCCCOperatorListInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminMCCCOperatorListRequest)
        {
            l_response = this.getCCOperatorList((WEB3AdminMCCCOperatorListRequest)l_request);           
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
     * (get���͉��)<BR>
     * CC�I�y���[�^�ꗗ�������͉�ʕ\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҃��j���[����iCC�ꗗ�jget���͉�ʁv�Q�ƁB <BR>
     * <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����CC���ڰ��ꗗ����ظ��ăf�[�^�I�u�W�F�N�g<BR>
     * <BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorListInputResponse
     * @@roseuid 417F7435010E
     */
    protected WEB3AdminMCCCOperatorListInputResponse getInputScreen(WEB3AdminMCCCOperatorListInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminMCCCOperatorListInputRequest l_request)";         
        log.entering(STR_METHOD_NAME); 
        
        //1.1 getInstanceFrom���O�C�����
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2 validate����(�@@�\�J�e�S���R�[�h�i=�I�y���[�^�Ǘ��j : String, is�X�V�i=false�j : boolean)
        l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_OPERATOR,false);
        
        //1.3 createResponse( )
        WEB3AdminMCCCOperatorListInputResponse l_Response = (WEB3AdminMCCCOperatorListInputResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_Response;
    }
    
    /**
     * (getCC�I�y���[�^�ꗗ)<BR>
     * CC�I�y���[�^�ꗗ�\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҃��j���[����iCC�ꗗ�jgetCC�I�y���[�^�ꗗ�v�Q�ƁB <BR>
     * <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����CC���ڰ��ꗗظ��ăf�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorListResponse
     * @@roseuid 417F74350110
     */
    protected WEB3AdminMCCCOperatorListResponse getCCOperatorList(WEB3AdminMCCCOperatorListRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getCCOperatorList(WEB3AdminMCCCOperatorListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h�i=�I�y���[�^�Ǘ��j : String, is�X�V�i=false�j : boolean)
        l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_OPERATOR,false);
        
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
                
        //1.7 get����(�Ǘ���, String, String[], String, Integer
        Integer l_interrorcount;
        if (l_request.errorCount == null || l_request.errorCount.equals("0"))
        {
            l_interrorcount = null;
        }
        else
        {
            l_interrorcount = Integer.valueOf(l_request.errorCount);
        }
        WEB3GentradeTrader[] l_web3GentradeTraders =  WEB3GentradeTrader.getTraders(l_web3Administrator, l_strQueryString, l_strQueryDataContainers, null, l_interrorcount);
        
        //1.8 createCC�I�y���[�^�o�^���(����[])
        WEB3AdminMCCCOperatorRegistUnitCreateService l_service= (WEB3AdminMCCCOperatorRegistUnitCreateService)Services.getService(WEB3AdminMCCCOperatorRegistUnitCreateService.class); 
        WEB3AdminMCCCOperatorRegistUnit[] l_web3RegistUnits = l_service.createCCOperatorRegistUnit(l_web3GentradeTraders);

        if (l_web3RegistUnits == null || l_web3RegistUnits.length == 0)
        {
            log.debug("createCC�I�y���[�^�o�^���̌��ʂ��@@0���ł���");
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01948,
                     STR_METHOD_NAME);
        }   
        
        //1.9 (*) ���N�G�X�g�f�[�^.�\�[�g�L�[�����AComparator�𐶐�����B       
        int l_intLength = l_request.sortKeys.length;
        WEB3AdminMCCCOperatorRegistUnitComparator[] l_comprtors = new WEB3AdminMCCCOperatorRegistUnitComparator[l_intLength];
        for(int i = 0;i < l_intLength;i ++ )
        {
            //1.9.1 CC�I�y���[�^�o�^���Comparator(String, String)
            l_comprtors[i] = new WEB3AdminMCCCOperatorRegistUnitComparator(l_request.sortKeys[i].ascDesc, l_request.sortKeys[i].keyItem);
            
        }
        
        //1.10 WEB3ArraysUtility.sort()
        WEB3ArraysUtility.sort(l_web3RegistUnits, l_comprtors);

        //1.11 �Ǘ��҃��j���[����CC���ڰ��ꗗڽ��ݽ(WEB3GenRequest) 
        WEB3AdminMCCCOperatorListResponse l_reponse = (WEB3AdminMCCCOperatorListResponse)l_request.createResponse();
        
        //1.12 (*2) �v���p�e�B�Z�b�g
        int l_intPageSize = Integer.parseInt(l_request.pageSize);     
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);    
        WEB3PageIndexInfo l_pagiIndexInfo = new WEB3PageIndexInfo(l_web3RegistUnits, l_intPageIndex, l_intPageSize);

        l_reponse.ccOperatorRegistUnits = (WEB3AdminMCCCOperatorRegistUnit[])l_pagiIndexInfo.getArrayReturned(WEB3AdminMCCCOperatorRegistUnit.class);
        l_reponse.totalRecords = l_pagiIndexInfo.getTotalRecords() + "";
        l_reponse.totalPages = l_pagiIndexInfo.getTotalPages() + "";
        l_reponse.pageIndex = l_pagiIndexInfo.getPageIndex() + "";
        
        log.exiting(STR_METHOD_NAME);
        return l_reponse;
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
     * �R�j�@@���҃R�[�h�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�I�y���[�^�R�[�h != null�j�̏ꍇ�A���҃R�[�h������ǉ�����B <BR>
     * <BR>
     * �@@" and trader_code = ? "<BR>
     * <BR>
     * �S�j�@@���Җ������ǉ� ���w�肪����ꍇ�̂݁C�B������<BR>
     * �@@�i���N�G�X�g�f�[�^.�I�y���[�^�� != null�j�̏ꍇ�A���ҕc�������ilike�j��ǉ�����B <BR>
     * <BR>
     * �@@" and family_name like ? "<BR>
     * <BR>
     * �T�j�@@��s�����\�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.��s�����\�敪 != null�j�̏ꍇ�A��s�����ۃt���O������ǉ�����B <BR>
     * <BR>
     * �@@" and account_order_flag = ? "<BR>
     * <BR>
     * �U�j�@@�����R�[�h�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�����R�[�h != null�j�̏ꍇ�A�����R�[�h������ǉ�����B <BR>
     * <BR>
     * �@@" and department_code = ? "<BR>
     * <BR>
     * �V�j�@@������C���X�^���X��ԋp <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����CC���ڰ��ꗗظ��ăf�[�^�I�u�W�F�N�g<BR>
     * @@return String
     * @@roseuid 417F7435011D
     */
    protected String createQueryString(WEB3AdminMCCCOperatorListRequest l_request) 
    { 
        final String STR_METHOD_NAME = " createQueryString(WEB3AdminMCCCOperatorListRequest l_request)"; 
        
        log.entering(STR_METHOD_NAME); 
         
        // ���������������ҏW����B
        // �P�j�@@�߂�l���� 
        StringBuffer l_strQueryString = new StringBuffer();
 
        // �Q�j�i���N�G�X�g�f�[�^.���X�R�[�h != null�j�̏ꍇ�A���X�R�[�h������ǉ�����B 
        // �@@" and branch_code = ? "
        if (l_request.branchCode != null)
        {
            l_strQueryString.append("and branch_code = ? ");
        }        

        // �R�j�i���N�G�X�g�f�[�^.�I�y���[�^�R�[�h != null�j�̏ꍇ�A���҃R�[�h������ǉ�����B 
        // �@@" and trader_code = ? "
        if (l_request.operatorCode != null)
        {
            l_strQueryString.append(" and trader_code = ? ");
        }

        // �S�j�@@�i���N�G�X�g�f�[�^.�I�y���[�^�� != null�j�̏ꍇ�A���ҕc�������ilike�j��ǉ�����B <BR>
        // �@@" and family_name like ? "
        if (l_request.operatorName != null)
        {
            l_strQueryString.append(" and family_name like ? ");
        }

        // �T)�i���N�G�X�g�f�[�^.��s�����\�敪 != null�j�̏ꍇ�A��s�����ۃt���O������ǉ�����
        // �@@" and account_order_flag = ? "
        if (l_request.accountOrderDiv != null)
        {
            l_strQueryString.append(" and account_order_flag = ? ");
        }

        // �U�j�i���N�G�X�g�f�[�^.�����R�[�h != null�j�̏ꍇ�A�����R�[�h������ǉ�����B 
        // �@@" and department_code = ? "
        if (l_request.departmentCode != null)
        {
            l_strQueryString.append(" and department_code = ? ");
        }

        // �V�j�@@������C���X�^���X��ԋp
        String l_strQueryStringReturn = l_strQueryString.toString();
        
        if (l_strQueryStringReturn.length() == 0)
        {
            l_strQueryStringReturn = null;
        }
        
        log.exiting(STR_METHOD_NAME); 
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
     * �R�j�@@���҃R�[�h�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�I�y���[�^�R�[�h != null�j�̏ꍇ�A�߂�l�ҏW�p�C���X�^���X�ɊǗ��҃R�[�h������ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@���N�G�X�g�f�[�^.�I�y���[�^�R�[�h <BR>
     * <BR>
     * �S�j�@@���Җ������ǉ� ���w�肪����ꍇ�̂݁C�B������<BR>
     * �@@�i���N�G�X�g�f�[�^.�Ǘ��Җ� != null�j�̏ꍇ�A�߂�l�ҏW�p�C���X�^���X�Ɉ��ҕc���������ilike�j��ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@"%" + ���N�G�X�g�f�[�^.�I�y���[�^�� + "%"<BR>
     * <BR>
     * �T�j�@@��s�����\�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.��s�����\�敪 != null�j�̏ꍇ�A�߂�l�ҏW�p�C���X�^���X�ɑ�s�����ۃt���O������ǉ�����B<BR> 
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@���N�G�X�g�f�[�^.��s�����\�敪<BR>
     * <BR>
     * �U�j�@@�����R�[�h�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�����R�[�h != null�j�̏ꍇ�A�߂�l�ҏW�p�C���X�^���X�ɏ����R�[�h������ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@���N�G�X�g�f�[�^.�����R�[�h<BR>
     * <BR>
     * �V�j�@@�z���ԋp <BR>
     * �@@�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j.toArray()��ԋp����B <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����CC���ڰ��ꗗظ��ăf�[�^�I�u�W�F�N�g<BR>
     * @@return java.lang.String[]
     * @@roseuid 417F7435011F
     */
    protected String[] createQueryDataContainer(WEB3AdminMCCCOperatorListRequest l_request) 
    {
        final String STR_METHOD_NAME = " createQueryDataContainer(WEB3AdminMCCCOperatorListRequest l_request)"; 
        
        log.entering(STR_METHOD_NAME);

        // ���������f�[�^�R���e�i��ҏW����B
        // �P)�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐� 
        List l_lisContainers = new ArrayList(); 
        
        // �Q�j(���N�G�X�g�f�[�^.���X�R�[�h != null�j�̏ꍇ�A�߂�l�ҏW�p�C���X�^���X�ɕ��X�R�[�h��ǉ�����B
        if (l_request.branchCode != null)
        {
            l_lisContainers.add(l_request.branchCode);
        }

        // �R�j�i���N�G�X�g�f�[�^.�I�y���[�^�R�[�h != null�j�̏ꍇ,���N�G�X�g�f�[�^.�I�y���[�^�R�[�h��ǉ�����B
        if (l_request.operatorCode != null)
        {
            l_lisContainers.add(l_request.operatorCode);
        }

        // �S�j�i���N�G�X�g�f�[�^.�Ǘ��Җ� != null�j�̏ꍇ�A�߂�l�ҏW�p�C���X�^���X�Ɉ��ҕc���������ilike�j��ǉ�����B
        // �@@"%" + ���N�G�X�g�f�[�^.�I�y���[�^�� + "%"
        if (l_request.operatorName != null)
        {
            l_lisContainers.add("%" + l_request.operatorName + "%");
        }

        // �T�j�i���N�G�X�g�f�[�^.��s�����\�敪 != null�j�̏ꍇ�A���N�G�X�g�f�[�^.��s�����\�敪��ǉ�����B
        if (l_request.accountOrderDiv != null)
        {
            l_lisContainers.add(l_request.accountOrderDiv);
        }

        // �U�j�i���N�G�X�g�f�[�^.�����R�[�h != null�j�̏ꍇ�A�����R�[�h��ǉ�����B
        if (l_request.departmentCode != null)
        {
            l_lisContainers.add(l_request.departmentCode);
        }
        
        // �V�j�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j.toArray()��ԋp����B
        String[] l_strQueryDataContainers = new String[l_lisContainers.size()];
        l_lisContainers.toArray(l_strQueryDataContainers);

        log.exiting(STR_METHOD_NAME);         
        return l_strQueryDataContainers;
    }
    
}
@
