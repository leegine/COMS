head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.20.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCommissionRegistAccountListInquiryServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇�����޽Impl(WEB3AdminAccInfoCommissionRegistAccountListInquiryServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �d�� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AccInfoCommissionCourseMaster;
import webbroker3.accountinfo.WEB3AccInfoCommissionCourseRegist;
import webbroker3.accountinfo.message.WEB3AccInfoCommissionCourseChangeInfo;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountListInquiryInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountListInquiryInputResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountListInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountListInquiryResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoCommissionCourseRegistInfoCreatedService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCommissionRegistAccountListInquiryService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (�Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇�����޽Impl)<BR>
 * �Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇�����޽�����N���X<BR>
 * 
 * @@author�@@�d��
 * @@version 1.0
 */
public class WEB3AdminAccInfoCommissionRegistAccountListInquiryServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AdminAccInfoCommissionRegistAccountListInquiryService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCommissionRegistAccountListInquiryServiceImpl.class);
    
    /**
     * @@roseuid 418F3A080138
     */
    public WEB3AdminAccInfoCommissionRegistAccountListInquiryServiceImpl() 
    {
     
    }
    
    /**
     * �萔���ύX�\���ڋq�ꗗ�⍇���������s���B <BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���萔���ύX�\��<BR>
     * �ڋq�ꗗ�⍇������ظ��Ă̏ꍇ <BR>
     * �@@�|get���͉��()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���萔���ύX�\��<BR>
     * �ڋq�ꗗ�⍇��ظ��Ă̏ꍇ <BR>
     * �@@�|get�ύX�\���ڋq�ꗗ()���R�[������B <BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41510350032A
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇������ظ��ăf�[�^�̏ꍇ
        if(l_request instanceof WEB3AdminAccInfoCommissionChangeAccountListInquiryInputRequest)
        {  
            l_response = this.getInputScreen((WEB3AdminAccInfoCommissionChangeAccountListInquiryInputRequest)l_request);
        }
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇��ظ��ăf�[�^�̏ꍇ
        else if(l_request instanceof WEB3AdminAccInfoCommissionChangeAccountListInquiryRequest)
        {
            l_response = this.getRegistAccountList((WEB3AdminAccInfoCommissionChangeAccountListInquiryRequest)l_request);
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
     * �萔���ύX�\���ڋq�ꗗ�⍇�����͉�ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҂��q�l���i�萔���ύX�\���ڋq�ꗗ�⍇���jget���͉�ʁv�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇������ظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountListInquiryInputResponse
     * @@roseuid 41510AF8024F
     */
    protected WEB3AdminAccInfoCommissionChangeAccountListInquiryInputResponse getInputScreen(WEB3AdminAccInfoCommissionChangeAccountListInquiryInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoCommissionChangeAccountListInquiryInputRequest)";
        log.entering(STR_METHOD_NAME);
        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
          
        //validate����(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_COMMISSION, false);
        
        // get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        // get�戵�\�ϑ��萔���R�[�X(String, String)
        WEB3AccInfoCommissionCourseMaster[] l_possibleCommissionCourse = 
            WEB3AccInfoCommissionCourseMaster.getHandlingPossibleCommissionCourse(
                l_strInstitutionCode, WEB3CommisionProductCodeDef.LISTING_STOCK);
        int l_intSize = 0;
        if(l_possibleCommissionCourse != null)  
        {
            l_intSize = l_possibleCommissionCourse.length;
        }        
         
        //�Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇������ڽ��ݽ(WEB3GenRequest)
        
        WEB3AdminAccInfoCommissionChangeAccountListInquiryInputResponse l_response = 
            (WEB3AdminAccInfoCommissionChangeAccountListInquiryInputResponse)l_request.createResponse();
            
        l_response.commissionCourseList =
            new String[l_intSize];
        for (int i = 0; i < l_intSize; i++)
        {

            l_response.commissionCourseList[i] = l_possibleCommissionCourse[i].getCommissionCourseCode();
        }   
         
        Date l_datTrialStartDate = GtlUtils.getSystemTimestamp(); 
        Calendar l_calendar = new GregorianCalendar();
        l_calendar.setTime(l_datTrialStartDate); 
        l_calendar.add(Calendar.MONTH, 1);
        l_calendar.set(Calendar.DATE, l_calendar.getActualMinimum(Calendar.DATE));
        l_response.trialStartDate = WEB3GentradeUtils.getBizDate(l_calendar.getTime(), 0);        

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�ύX�\���ڋq�ꗗ)<BR>
     * �萔���ύX�\���ڋq�ꗗ�\���������s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i�萔���ύX�\���ڋq�ꗗ�⍇���jget�ύX�\���ڋq�ꗗ�v�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇��ظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountListInquiryResponse
     * @@roseuid 415103500339
     */
    protected WEB3AdminAccInfoCommissionChangeAccountListInquiryResponse getRegistAccountList(WEB3AdminAccInfoCommissionChangeAccountListInquiryRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getRegistAccountList(WEB3AdminAccInfoCommissionChangeAccountListInquiryRequest)";
        log.entering(STR_METHOD_NAME);
        //validate()
        l_request.validate();
        
        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //validate����(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_COMMISSION, false);
             
        //validate���X����(String[])
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //create��������������(String, String[], String, Date)
        String l_strQueryString = this.createQueryString(
            l_strInstitutionCode,
            l_request.branchCode,
            l_request.commissionCourse,
            l_request.trialStartDate);
            
       //create���������f�[�^�R���e�i(String, String[], String, Date)
        String[] l_strQueryContainer = this.createQueryContainer(
            l_strInstitutionCode,
            l_request.branchCode,
            l_request.commissionCourse,
            l_request.trialStartDate);
            
        //create�\�[�g����( )
        String l_strSortCond = this.createSortCond();
        
        //get�ϑ��萔���R�[�X�ύX�\��(String, String[], String)
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
        else 
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
        WEB3AdminAccInfoCommissionChangeAccountListInquiryResponse l_response = 
            (WEB3AdminAccInfoCommissionChangeAccountListInquiryResponse)l_request.createResponse();
            
        int l_intPageSize = Integer.parseInt(l_request.pageSize);//�y�[�W���\���s��
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);//�v���y�[�W�ԍ�
        
        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(l_commissionCourseRegistInfo, l_intPageIndex, l_intPageSize);
        
        //�ύX�\���ڋq�ꗗ
        l_response.changeApplyAccountList =             
            (WEB3AccInfoCommissionCourseChangeInfo[])l_pageIndexInfo.getArrayReturned(
                WEB3AccInfoCommissionCourseChangeInfo.class);
  
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
     * �R�j�@@���X�����ǉ�<BR>
     * �@@���X������ǉ�����B���X�R�[�h[]�̗v�f����"?"��ҏW����B<BR>
     * <BR>
     * �@@" and branch_id in (?, ?,,,) "<BR>
     * <BR>
     * �S�j�@@�萔���R�[�X�R�[�h�����ǉ� <BR>
     * �@@�萔���R�[�X�w��̏ꍇ�i�萔���R�[�X�R�[�h != null�j�A<BR>
     * �萔���R�[�X�R�[�h�w�蕶�������������������ɒǉ�����B<BR>
     * <BR>
     * �@@" and commission_course_div = ? "<BR>
     * <BR>
     * �T�j�@@�K�p�J�n�������ǉ�<BR>
     * �@@�K�p�J�n��������ǉ�����B<BR>
     * <BR>
     * �@@" and appli_start_datetime like ? "<BR>
     * <BR>
     * �U�j�@@�폜�t���O�����ǉ�<BR>
     *�@@�폜�t���O������ǉ�����B<BR>
     *�@@" and  delete_flag = 0 "<BR>
     * �V�j�@@������C���X�^���X��ԋp <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCodes - ���X�R�[�h�z��
     * 
     * @@param l_strCommissionCourseCode - �萔���R�[�X�R�[�h<BR>
     * <BR>
     * 02�F�@@��z�萔���i�X�^���_�[�h�j<BR>
     * 12�F�@@��z�萔���i�n�C�p�[�{�b�N�X�j<BR>
     * 03�F�@@��������v<BR>
     * 04�F�@@����<BR>
     * 05�F�@@�����z��<BR>
     * 
     * @@param l_datAppliStartDate - �K�p�J�n��
     * @@return String
     * @@roseuid 41510350033B
     */
    protected String createQueryString(String l_strInstitutionCode, String[] l_strBranchCodes, String l_strCommissionCourseCode, Date l_datAppliStartDate) 
    { 
        final String STR_METHOD_NAME = 
            " createQueryString(String, String[], String, Date)";
        log.entering(STR_METHOD_NAME);
        
        //�߂�l�̌�������������C���X�^���X�i�FString�j�𐶐� 
        String l_strSearchCond;
        
        //�@@�،���ЃR�[�h������ǉ�����B
        l_strSearchCond 
            = " institution_code =  ? ";
            
        //���X������ǉ�����B���X�R�[�h[]�̗v�f����"?"��ҏW����B
        int l_intBranchCodesCnt = l_strBranchCodes.length;
        
        if (l_intBranchCodesCnt > 0)
        {
            StringBuffer l_sbQueryBranchCodes = new StringBuffer();
            
            for (int i = 0; i < l_intBranchCodesCnt; i++)
            {
                if (l_sbQueryBranchCodes.length() != 0)
                {
                    l_sbQueryBranchCodes.append(", ");
                }
                l_sbQueryBranchCodes.append("?");
                
            }                
            l_strSearchCond += " and branch_id in (" + l_sbQueryBranchCodes.toString() + ")";             
        }
       
        //�萔���R�[�X�w��̏ꍇ�i�萔���R�[�X�R�[�h != null�j�A<BR>
        // �萔���R�[�X�R�[�h�w�蕶�������������������ɒǉ�����B
        if (l_strCommissionCourseCode != null)
        {
            l_strSearchCond
                += " and commission_course_div = ? "; 
        }
        
        //�K�p�J�n��������ǉ�����B
        l_strSearchCond
            += " and to_char(appli_start_datetime, 'YYYYMMDD') = ? ";
        //�U�j�@@�폜�t���O�����ǉ�
        //  �폜�t���O������ǉ�����B
        //
        //" and  delete_flag = 0 "
        //l_strSearchCond
        //    += " and delete_flag = 0 ";
        l_strSearchCond
            += " and delete_flag = ? ";
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
     * �R�j�@@���X�����ǉ�<BR>
     * �@@���X�R�[�h[]�ɊY�����镔�X�h�c�����ׂĒǉ�����B<BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����]�@@<BR>
     * �@@���X�h�c��<BR>
     * <BR>
     * �@@���A�J�E���g�}�l�[�W��.getBranch(�،���ЁC���X�R�[�h)�ɂĎ擾����B<BR>
     * �@@���،���ЃI�u�W�F�N�g�́A�A�J�E���g�}�l�[�W��.getInstitution(�،���ЃR�[�h)<BR>
     * �ɂĎ擾����B<BR>
     * <BR>
     * �S�j�@@�萔���R�[�X�R�[�h�����ǉ� <BR>
     * �@@�萔���R�[�X�w��̏ꍇ�i�萔���R�[�X�R�[�h != null�j�A�萔���R�[�X�R�[�h<BR>
     * �w�蕶��������X�g�ɒǉ�����B<BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����]<BR>
     * �@@�萔���R�[�X�R�[�h<BR>
     * <BR>
     * �T�j�@@�K�p�J�n�������ǉ�<BR>
     * �@@�K�p�J�n��������ǉ�����B<BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����]<BR>
     * �@@�K�p�J�n����YYYYMMDD + '%'<BR>
     * <BR>
     * �U�j�@@�z���ԋp <BR>
     * �@@�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j.toArray()��ԋp����B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCodes - ���X�R�[�h�z��
     * 
     * @@param l_strCommissionCourseCode - �萔���R�[�X�R�[�h<BR>
     * <BR>
     * 02�F�@@��z�萔���i�X�^���_�[�h�j<BR>
     * 12�F�@@��z�萔���i�n�C�p�[�{�b�N�X�j<BR>
     * 03�F�@@��������v<BR>
     * 04�F�@@����<BR>
     * 05�F�@@�����z��<BR>
     * 
     * 
     * @@param l_datAppliStartDate - �K�p�J�n��
     * @@return String[]
     * @@roseuid 415103500340
     */
    protected String[] createQueryContainer(
        String l_strInstitutionCode, 
        String[] l_strBranchCodes, 
        String l_strCommissionCourseCode, 
        Date l_datAppliStartDate)  throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            " createQueryContainer(" +
                "String l_strInstitutionCode, " +
                "String[] l_strBranchCodes, " +
                "String l_strCommissionCourseCode, " +
                "Date l_datAppliStartDate) ";
        log.entering(STR_METHOD_NAME);
                
        //* �P�jArrayList�𐶐�����B<BR>
        List l_lisQueryContainer = new ArrayList();  
        
        //�،���ЃR�[�h�������ǉ�����B
        l_lisQueryContainer.add(l_strInstitutionCode);
        
        //���X�R�[�h[]�ɊY�����镔�X�h�c�����ׂĒǉ�����B
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        //�g���A�J�E���g�}�l�[�W���擾����
        WEB3GentradeAccountManager l_accMgr = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
        long l_lngBranchId = 0;
        Institution l_institution;
        try
        {
            l_institution = l_accMgr.getInstitution(l_strInstitutionCode);
            
            for (int i = 0; i < l_strBranchCodes.length; i++)
            {
                Branch l_branch = l_accMgr.getBranch(l_institution, l_strBranchCodes[i]);
                l_lngBranchId = l_branch.getBranchId();
                String l_strBranchId = l_lngBranchId + "";
                l_lisQueryContainer.add(l_strBranchId);
         
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error in ���X�����ǉ�....... ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
      
        // �@@�萔���R�[�X�w��̏ꍇ�i�萔���R�[�X�R�[�h != null�j�A�萔���R�[�X�R�[�h<BR>
        // �w�蕶��������X�g�ɒǉ�����B
        if (l_strCommissionCourseCode != null)
        {
            l_lisQueryContainer.add(l_strCommissionCourseCode);
        }
        
        //�K�p�J�n��������ǉ�����
        l_lisQueryContainer.add(WEB3DateUtility.formatDate(l_datAppliStartDate, "yyyyMMdd"));
        
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
     * �@@�ϑ��萔���ύX�\���e�[�u��.���X�h�c�i�����Fasc�j<BR>
     * �@@substr(�ϑ��萔���ύX�\���e�[�u��.�����h�c, 9, 6)�i�����Fasc�j<BR>
     * @@return String
     * @@roseuid 4164C934006A
     */
    protected String createSortCond() 
    {
        final String STR_METHOD_NAME =" createSortCond()";
        log.entering(STR_METHOD_NAME);
        StringBuffer l_strReturn = new StringBuffer();  
        
        l_strReturn.append(" branch_id ASC,");
       
        l_strReturn.append(" substr(account_id , 9 , 6) ASC");

        log.exiting(STR_METHOD_NAME);
        return l_strReturn.toString();
    }
}
@
