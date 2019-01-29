head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.20.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCommissionRegistAccountInquiryServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇�����޽Impl(WEB3AdminAccInfoCommissionRegistAccountInquiryServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �d�� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AccInfoCommissionCourseRegist;
import webbroker3.accountinfo.message.WEB3AccInfoCommissionCourseChangeInfo;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountInquiryInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountInquiryResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoCommissionCourseRegistInfoCreatedService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCommissionRegistAccountInquiryService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (�Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇�����޽Impl)<BR>
 * �Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇�����޽�����N���X<BR>
 * 
 * @@author�@@�d��
 * @@version 1.0
 */
public class WEB3AdminAccInfoCommissionRegistAccountInquiryServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AdminAccInfoCommissionRegistAccountInquiryService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCommissionRegistAccountInquiryServiceImpl.class);
    
    /**
     * @@roseuid 418F3A07032C
     */
    public WEB3AdminAccInfoCommissionRegistAccountInquiryServiceImpl() 
    {
     
    }
    
    /**
     * �萔���ύX�\���ڋq�⍇���\���������s���B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���萔���ύX�\���ڋq<BR>
     * �⍇������ظ��Ă̏ꍇ <BR>
     * �@@�|get���͉��()���R�[������B<BR> 
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���萔���ύX�\���ڋq<BR>
     * �⍇��ظ��Ă̏ꍇ <BR>
     * �@@�|get�ύX�\���ꗗ()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4151406101E1
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇������ظ��ăf�[�^�̏ꍇ
        if(l_request instanceof WEB3AdminAccInfoCommissionChangeAccountInquiryInputRequest)
        {  
            l_response = this.getInputScreen((WEB3AdminAccInfoCommissionChangeAccountInquiryInputRequest)l_request);
        }
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇��ظ��ăf�[�^�̏ꍇ
        else if(l_request instanceof WEB3AdminAccInfoCommissionChangeAccountInquiryRequest)
        {
            l_response = this.getRegistList((WEB3AdminAccInfoCommissionChangeAccountInquiryRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (get���͉��)<BR>
     * �萔���ύX�\���ڋq�⍇�����͉�ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҂��q�l���i�萔���ύX�\���ڋq�⍇���jget���͉�ʁv�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇������ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B616A01D0
     */
    protected WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse getInputScreen(WEB3AdminAccInfoCommissionChangeAccountInquiryInputRequest l_request) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoCommissionChangeAccountInquiryInputRequest)";
        log.entering(STR_METHOD_NAME);
        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
          
        //validate����(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_COMMISSION, false);
        
        WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse l_response = 
            (WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse)l_request.createResponse();
    
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    

    /**
     * (get�ύX�\���ꗗ)<BR>
     * �萔���ύX�\���ڋq�⍇���\���������s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҂��q�l���i�萔���ύX�\���ڋq�⍇���jget�ύX�\���ꗗ�v�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇��ظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountInquiryResponse
     * @@roseuid 4151406101F1
     */
    protected WEB3AdminAccInfoCommissionChangeAccountInquiryResponse getRegistList(WEB3AdminAccInfoCommissionChangeAccountInquiryRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getRegistList(WEB3AdminAccInfoCommissionChangeAccountInquiryRequest)";
        log.entering(STR_METHOD_NAME);
        //validate()
        l_request.validate();
        
        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
          
        //validate����(String, boolean)   
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_COMMISSION, false);
        
        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //get�ڋq
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_gentradeMainAccount = null;
        try
        {
            l_gentradeMainAccount = l_gentradeAccountManager.getMainAccount(l_strInstitutionCode, l_request.branchCode, l_request.accountCode);
        }
        catch(WEB3SystemLayerException l_ex)
        {
            if (WEB3ErrorCatalog.SYSTEM_ERROR_80005.equals(l_ex.getErrorInfo()))
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq�R�[�h�̓��͂��s���ł��B");
            }
            else
            {
                throw l_ex;
            }
        }
        
            
            
        // validate���X����(String)
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //create��������������( )
        String l_strQueryString = this.createQueryString();
        
        //create���������f�[�^�R���e�i(String, String, String)
        String[] l_strQueryContainer = 
            this.createQueryContainer(
                l_strInstitutionCode, 
                l_request.branchCode, 
                l_request.accountCode);
        
        //create�\�[�g����( )
        String l_strSortCond = this.createSortCond();
        
        List l_lisCommissionCourseRegist = 
            WEB3AccInfoCommissionCourseRegist.getCommissionCourseRegist(
                l_strQueryString,
                l_strQueryContainer,
                l_strSortCond);
                
        WEB3AccInfoCommissionCourseChangeInfo[] l_commissionCourseRegistInfo = null;             
        if (l_lisCommissionCourseRegist == null || l_lisCommissionCourseRegist.size() == 0)
        {
            l_commissionCourseRegistInfo = new WEB3AccInfoCommissionCourseChangeInfo[0];  
        } 
        else if (l_lisCommissionCourseRegist != null)
        {
            int l_intSize = l_lisCommissionCourseRegist.size();
            
            WEB3AccInfoCommissionCourseRegist[] l_commissionCourseRegist = 
                new WEB3AccInfoCommissionCourseRegist[l_intSize];         
            l_lisCommissionCourseRegist.toArray(l_commissionCourseRegist);
        
            //create�����萔���R�[�X�ύX�\�����(�ϑ��萔���R�[�X�ύX�\��[])        
            WEB3AccInfoCommissionCourseRegistInfoCreatedService 
                l_accInfoCommissionCourseRegistInfoCreatedService = 
                    (WEB3AccInfoCommissionCourseRegistInfoCreatedService) Services.getService(
                        WEB3AccInfoCommissionCourseRegistInfoCreatedService.class);
            l_commissionCourseRegistInfo = 
                l_accInfoCommissionCourseRegistInfoCreatedService.createEquityCommissionCourseRegistInfo(l_commissionCourseRegist);                        
        } 

        //createResponse( )
        WEB3AdminAccInfoCommissionChangeAccountInquiryResponse l_response = 
            (WEB3AdminAccInfoCommissionChangeAccountInquiryResponse)l_request.createResponse();
            
        int l_intPageSize = Integer.parseInt(l_request.pageSize);//�y�[�W���\���s��
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);//�v���y�[�W�ԍ�
        
        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(l_commissionCourseRegistInfo, l_intPageIndex, l_intPageSize);
                    
        //�ύX�\���ڋq�ꗗ
        l_response.changeApplyInfoList = 
            (WEB3AccInfoCommissionCourseChangeInfo[])l_pageIndexInfo.getArrayReturned(
                WEB3AccInfoCommissionCourseChangeInfo.class);
                
        //�ڋq���i�����j 
        l_response.accountName = l_gentradeMainAccount.getDisplayAccountName();
        
        //���y�[�W��
        l_response.totalPages= l_pageIndexInfo.getTotalPages() + ""; 
        //(�\���y�[�W�ԍ�)
        l_response.pageIndex = l_pageIndexInfo.getPageIndex() + ""; 
        //�����R�[�h��
        l_response.totalRecords= l_pageIndexInfo.getTotalRecords() + "";  
        
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
     * �Q�j�@@�،���Џ����ǉ�<BR>
     * �@@�،���ЃR�[�h������ǉ�����B<BR>
     * <BR>
     * �@@" institution_code =  ? "<BR>
     * <BR>
     * �R�j�@@�ڋq�����ǉ� <BR>
     * �@@�ڋq������ǉ�����B<BR>
     * <BR>
     * �@@" and account_id = ? "<BR>
     * <BR>
     * �S�j�@@�폜�t���O�����ǉ�<BR>
     *�@@�폜�t���O������ǉ�����B<BR>
     *<BR>
     *�@@" and  delete_flag = 0 "<BR>
     * 5�j�@@������C���X�^���X��ԋp <BR>
     * @@return String
     * @@roseuid 4151434403C6
     */
    protected String createQueryString() 
    {
        final String STR_METHOD_NAME = " createQueryString() ";
        log.entering(STR_METHOD_NAME);
        String l_strSearchCond;
        l_strSearchCond 
            = " institution_code = ? ";
            
        l_strSearchCond
            += " and account_id = ? ";
        l_strSearchCond
            += " and  delete_flag = ? ";
            
        log.exiting(STR_METHOD_NAME);     
        return l_strSearchCond;
    }
    
    /**
     * (create���������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i��ҏW����B <BR>
     * <BR>
     * �P�j�@@�߂�l���� <BR>
     * �@@�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐� <BR>
     * <BR>
     * �Q�j�@@�،���Џ����ǉ�<BR>
     * �@@�،���ЃR�[�h�������ǉ�����B<BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����]<BR>
     * �@@�،���ЃR�[�h<BR>
     * <BR>
     * �R�j�@@�ڋq�R�[�h�����ǉ� <BR>
     * �@@�����h�c����������X�g�ɒǉ�����B<BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����]<BR>
     * �@@�����h�c��<BR>
     * <BR>
     * �@@���A�J�E���g�}�l�[�W��.get�ڋq(�،���ЃR�[�h�C���X�R�[�h�C<BR>
     * �ڋq�R�[�h).getAccountId()�ɂĎ擾����B<BR>
     * <BR>
     * �S�j�@@�z���ԋp <BR>
     * �@@�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j.toArray()��ԋp����B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@return String[]
     * @@roseuid 4151434403CA
     */
    protected String[] createQueryContainer(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            " createQueryContainer(String, String, String)";
        log.entering(STR_METHOD_NAME);
        //* �P�jArrayList�𐶐�����B<BR>
        List l_lisQueryContainer = new ArrayList();  
        
        l_lisQueryContainer.add(l_strInstitutionCode);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        //�g���A�J�E���g�}�l�[�W���擾����
        WEB3GentradeAccountManager l_accMgr = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        long l_lngAccountId = 
            l_accMgr.getMainAccount(
                l_strInstitutionCode, 
                l_strBranchCode, 
                l_strAccountCode).getAccountId();
        String l_strAccountId = l_lngAccountId + "";
        l_lisQueryContainer.add(l_strAccountId);
        l_lisQueryContainer.add(Integer.toString(BooleanEnum.FALSE.intValue()));
        
        String[] l_strQueryDataContainers = new String[l_lisQueryContainer.size()];
        l_lisQueryContainer.toArray(l_strQueryDataContainers);

        log.exiting(STR_METHOD_NAME);  
        return l_strQueryDataContainers;
    }
    
    /**
     * (create�\�[�g����)<BR>
     * �\�[�g�����������ҏW����B <BR>
     * <BR>
     * �e�[�u���񕨗������g�p���A�ȉ��̒ʂ�A�\�[�g����������iorder by��j��<BR>
     * �ҏW����B<BR>
     * <BR>
     * �@@�ϑ��萔���ύX�\���e�[�u��.�\�������i�����Fasc�j<BR>
     * @@return String
     * @@roseuid 4164CACA00E7
     */
    protected String createSortCond() 
    {
        final String STR_METHOD_NAME =" createSortCond()";
        log.entering(STR_METHOD_NAME);
        StringBuffer l_strReturn = new StringBuffer();  
        l_strReturn.append("regist_timestamp ASC");

        log.exiting(STR_METHOD_NAME);
        return l_strReturn.toString();

    }
}
@
