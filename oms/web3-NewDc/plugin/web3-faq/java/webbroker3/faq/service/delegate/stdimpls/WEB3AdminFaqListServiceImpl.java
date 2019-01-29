head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFaqListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Җ⍇���Ǘ����⍇���ꗗ�T�[�r�XImpl(WEB3AdminFaqListServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/23 ����� (���u) �V�K�쐬
*/

package webbroker3.faq.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.faq.WEB3Faq;
import webbroker3.faq.define.WEB3FaqKeyItemDef;
import webbroker3.faq.message.WEB3AdminFaqListInputRequest;
import webbroker3.faq.message.WEB3AdminFaqListInputResponse;
import webbroker3.faq.message.WEB3AdminFaqListRequest;
import webbroker3.faq.message.WEB3AdminFaqListResponse;
import webbroker3.faq.message.WEB3FaqInfo;
import webbroker3.faq.message.WEB3FaqSortKey;
import webbroker3.faq.service.delegate.WEB3AdminFaqListService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (�Ǘ��Җ⍇���Ǘ����⍇���ꗗ�T�[�r�XImpl)<BR>
 * �Ǘ��Җ⍇���Ǘ����⍇���ꗗ�T�[�r�X�����N���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminFaqListServiceImpl implements WEB3AdminFaqListService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFaqListServiceImpl.class);   
        
    /**
     * @@roseuid 41C25C8B01E4
     */
    public WEB3AdminFaqListServiceImpl() 
    {
     
    }
    
    /**
     * �⍇���ꗗ���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��Җ⍇���Ǘ����⍇���ꗗ���̓��N�G�X�g�̏ꍇ <BR>
     * �@@�|get���͉��()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��Җ⍇���Ǘ����⍇���ꗗ���N�G�X�g�̏ꍇ <BR>
     * �@@�|get�⍇���ꗗ()���R�[������B <BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41AC19D301F3
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminFaqListInputRequest)
        {
            l_response = getInputScreen((WEB3AdminFaqListInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFaqListRequest)
        {
            l_response = getFaqList((WEB3AdminFaqListRequest)l_request);
        }
        else
        {
            log.error("�p�����[�^�^�C�v�s���B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "[l_request] = " + l_request);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���͉��)<BR>
     * �⍇���ꗗ���͉�ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�⍇���Ǘ��i�⍇���ꗗ�jget���͉�ʁv�Q�ƁB <BR>
     * @@param l_request - �Ǘ��Җ⍇���Ǘ����⍇���ꗗ���̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * 
     * @@return webbroker3.faq.message.WEB3AdminFaqListInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 41AC19D30203
     */
    protected WEB3AdminFaqListInputResponse getInputScreen(WEB3AdminFaqListInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminFaqListInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //���O�C�������A�Ǘ��҃I�u�W�F�N�g���擾����B 
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //�Ǘ��Ҍ����`�F�b�N���s���B 
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.FAQ, false);
        
        //���X�|���X�f�[�^�𐶐�����B 
        WEB3AdminFaqListInputResponse l_response = (WEB3AdminFaqListInputResponse)l_request.createResponse();
       
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�⍇���ꗗ)<BR>
     * �⍇���ꗗ�\���������s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�⍇���Ǘ��i�⍇���ꗗ�jget�⍇���ꗗ�v�Q�ƁB <BR>
     * @@param l_request - �Ǘ��Җ⍇���Ǘ����⍇���ꗗ���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.faq.message.WEB3AdminFaqListResponse
     * @@roseuid 41AC19DE006C
     */
    protected WEB3AdminFaqListResponse getFaqList(WEB3AdminFaqListRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getFaqList(WEB3AdminFaqListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B 
        l_request.validate();

        //���O�C�������A�Ǘ��҃I�u�W�F�N�g���擾����B 
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //���N�G�X�g�f�[�^.�@@�\�J�e�S���R�[�h[]�̐����A�����`�F�b�N���s���B
        for (int i = 0; i < l_request.transactionCategoryCode.length; i++)
        {
            //�Ǘ��Ҍ����`�F�b�N���s���B 
            l_administrator.validateAuthority(l_request.transactionCategoryCode[i], false);            
        }
        
        //���X�������`�F�b�N����B
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //���������������ҏW����B
        String l_strQueryString = createQueryString(l_request);
        
        //�،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //���������f�[�^�R���e�i�𐶐�����B
        Object[] l_queryContainer = createQueryContainer(l_request, l_strInstitutionCode); 
                
        //�\�[�g������ҏW����B
        String l_strSortCond = createSortCond(l_request.sortKeys);
        
        //�⍇���f�[�^���擾����B
        WEB3Faq[] l_faqs = WEB3Faq.getFaq(l_strQueryString, l_queryContainer, l_strSortCond);
        
        //�y�[�W���\���s��
        int l_intPageSize = Integer.parseInt(l_request.pageSize);     
        //�v���y�[�W�ԍ�
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);  
        
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(l_faqs, l_intPageIndex, l_intPageSize);
        
        WEB3Faq[] l_pagedFaqs = (WEB3Faq[])l_pageIndexInfo.getArrayReturned(WEB3Faq.class);
        
        //���X�|���X�f�[�^�𐶐�����B 
        WEB3AdminFaqListResponse l_response = (WEB3AdminFaqListResponse)l_request.createResponse();
        
        l_response.faqInfo = new WEB3FaqInfo[l_pagedFaqs.length];

        for (int i = 0; i < l_pagedFaqs.length; i++)
        {
            WEB3FaqInfo l_faqInfo = new WEB3FaqInfo();
            
            //�⍇���R�[�h�F�@@�⍇��[index].get�⍇���R�[�h()
            l_faqInfo.faqCode = l_pagedFaqs[i].getFaqNumber();
            
            //���X�R�[�h�F�@@�⍇��[index].get���X�R�[�h()
            l_faqInfo.branchCode = l_pagedFaqs[i].getBranchCode();
            
            //�ڋq�R�[�h�F�@@�⍇��[index].get�����R�[�h()�̍�6byte
            if (l_pagedFaqs[i].getAccountCode() != null)
            {
                l_faqInfo.accountCode = l_pagedFaqs[i].getAccountCode().substring(0, 6);
            }
            
            //�ڋq���F�@@�⍇��[index].get�ڋq���i�����j()
            l_faqInfo.accountName = l_pagedFaqs[i].getName();
            
            //���[���A�h���X�F�@@�⍇��[index].get���[���A�h���X()
            l_faqInfo.mailAddress = l_pagedFaqs[i].getEmailAddress();
            
            //�⍇�������F�@@�⍇��[index].get�⍇������()
            l_faqInfo.faqDate = l_pagedFaqs[i].getFaqDatetime();
            
            //�����F�@@�⍇��[index].get����()
            l_faqInfo.subject = l_pagedFaqs[i].getSubject();
            
            //�@@�\�h�c�F�@@�⍇��[index].get�@@�\�h�c()
            l_faqInfo.transactionId = l_pagedFaqs[i].getTransactionId();
            
            l_response.faqInfo[i] = l_faqInfo;
        }
        
        //���y�[�W��
        l_response.totalPages = "" + l_pageIndexInfo.getTotalPages();
        
        //�����R�[�h��
        l_response.totalRecords = "" + l_pageIndexInfo.getTotalRecords();
        
        //�\���y�[�W�ԍ�
        l_response.pageIndex = "" + l_pageIndexInfo.getPageIndex();

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
     * �@@" institution_code = ? "<BR>
     * <BR>
     * �R�j�@@���X�����ǉ�<BR>
     * �@@���X�R�[�h�z��̗v�f�����A���X�R�[�h������ǉ�����B <BR>
     * <BR>
     * �@@" and branch_code in (?, ?, ���)" <BR>
     * <BR>
     * �S�j�@@�⍇���R�[�h�����ǉ��@@���w�肪����ꍇ�̂݁C�O����v����<BR>
     * �@@�i���N�G�X�g�f�[�^.�⍇���R�[�h != null�j�̏ꍇ�A�⍇���R�[�h������ǉ�����B<BR>
     * <BR>
     * �@@" and faq_number like "<BR>
     * <BR>
     * �T�j�@@�ڋq�R�[�h�����ǉ��@@���w�肪����ꍇ�̂� <BR>
     * �@@�i���N�G�X�g�f�[�^.�ڋq�R�[�h != null�j�̏ꍇ�A�����R�[�h������ǉ�����B <BR>
     * <BR>
     * �@@" and account_code like ? " <BR>
     * <BR>
     * �U�j�@@�ڋq�������ǉ��@@���w�肪����ꍇ�̂݁C�O���^�����v����<BR>
     * �@@�i���N�G�X�g�f�[�^.�ڋq���@@ != null�j�̏ꍇ�A�ڋq���i�����j������ǉ�����B <BR>
     * <BR>
     * �@@" and name like ? " <BR>
     * <BR>
     * �V�j�@@���������ǉ��@@���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�����@@ != null�j�̏ꍇ�A����������ǉ�����B <BR>
     * <BR>
     * �@@" and subject = ? "<BR>
     * <BR>
     * �W�j�@@�⍇�����i���j�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�⍇�����i���j != null�j�̏ꍇ�A�⍇����������ǉ�����B <BR>
     * <BR>
     * �@@" and created_timestamp >= ? " <BR>
     * <BR>
     * �X�j�@@�⍇�����i���j�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�⍇�����i���j != null�j�̏ꍇ�A�⍇����������ǉ�����B <BR>
     * <BR>
     * �@@" and created_timestamp <= ? " <BR>
     * <BR>
     * �P�O�j�@@�@@�\�h�c�����ǉ�<BR>
     * �@@�@@�\�h�c�z��̗v�f�����A�@@�\�h�c������ǉ�����B <BR>
     * <BR>
     * �@@" and transaction_id in (?, ?, ���)" <BR>
     * <BR>
     * �P�P�j�@@������C���X�^���X��ԋp <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��Җ⍇���Ǘ����⍇���ꗗ���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * 
     * @@return String
     * @@roseuid 41AC22610389
     */
    protected String createQueryString(WEB3AdminFaqListRequest l_request) 
    {
        final String STR_METHOD_NAME = "createQueryString(WEB3AdminFaqListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        StringBuffer l_sbQueryString = new StringBuffer();
        
        //�،���ЃR�[�h������ǉ�����B 
        l_sbQueryString.append("institution_code = ?");
        
        //���X�R�[�h�z��̗v�f�����A���X�R�[�h������ǉ�����B         
        if (l_request.branchCode.length > 0)
        {
            String l_strSubCond = null;

            for (int i = 0; i < l_request.branchCode.length; i++)
            {
                if (l_strSubCond == null)
                {
                    l_strSubCond = "?";
                }
                else
                {
                    l_strSubCond += ", ?";
                }
            }
        
            l_sbQueryString.append(" and branch_code in(" + l_strSubCond + ")");
        }
        
        //�⍇���R�[�h�����ǉ��@@���w�肪����ꍇ�̂݁C�O����v����
        //�i���N�G�X�g�f�[�^.�⍇���R�[�h != null�j�̏ꍇ�A�⍇���R�[�h������ǉ�����B 
        if (l_request.faqCode != null)
        {
            l_sbQueryString.append(" and faq_number like ? || '%'");
        }
        
        
        //�ڋq�R�[�h�����ǉ��@@���w�肪����ꍇ�̂� 
        //�i���N�G�X�g�f�[�^.�ڋq�R�[�h != null�j�̏ꍇ�A�����R�[�h������ǉ�����B 
        if (l_request.accountCode != null)
        {
            l_sbQueryString.append(" and account_code like ? || '_'");
        }
        
        //�ڋq�������ǉ��@@���w�肪����ꍇ�̂݁C�O���^�����v����
        //�i���N�G�X�g�f�[�^.�ڋq���@@ != null�j�̏ꍇ�A�ڋq���i�����j������ǉ�����B 
        if (l_request.accountName != null)
        {
            l_sbQueryString.append(" and name like '%' || ? || '%'");
        }
        
        //���������ǉ��@@���w�肪����ꍇ�̂� 
        //�i���N�G�X�g�f�[�^.�����@@ != null�j�̏ꍇ�A����������ǉ�����B 
        if (l_request.subject != null)
        {
            l_sbQueryString.append(" and subject = ?");
        }
        
        //�⍇�����i���j�����ǉ� ���w�肪����ꍇ�̂� 
        //�i���N�G�X�g�f�[�^.�⍇�����i���j != null�j�̏ꍇ�A�⍇����������ǉ�����B 
        if (l_request.faqDateFrom != null)
        {
            l_sbQueryString.append(" and created_timestamp >= ?");
        }
        
        //�⍇�����i���j�����ǉ� ���w�肪����ꍇ�̂� 
        //�i���N�G�X�g�f�[�^.�⍇�����i���j != null�j�̏ꍇ�A�⍇����������ǉ�����B
        if (l_request.faqDateTo != null)
        {
            l_sbQueryString.append(" and created_timestamp <= ?");
        }
        
        //�@@�\�h�c�����ǉ�
        //�@@�\�h�c�z��̗v�f�����A�@@�\�h�c������ǉ�����B
        if (l_request.transactionId.length > 0)
        {
            String l_strSubCond = null;

            for (int i = 0; i < l_request.transactionId.length; i++)
            {
                if (l_strSubCond == null)
                {
                    l_strSubCond = "?";
                }
                else
                {
                    l_strSubCond += ", ?";
                }
            }
        
            l_sbQueryString.append(" and transaction_id in(" + l_strSubCond + ")");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_sbQueryString.toString();
    }
    
    /**
     * (create���������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i��ҏW����B <BR>
     * <BR>
     * �P�j�@@�߂�l���� <BR>
     * �@@�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐� <BR>
     * <BR>
     * �Q�j�@@�،���Џ����ǉ�<BR>
     * �@@�߂�l�ҏW�p�C���X�^���X�ɁA�،���ЃR�[�h��ǉ�����B<BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@�،���ЃR�[�h<BR>
     * <BR>
     * �R�j�@@���X�����ǉ� ���w�肪����ꍇ�̂� <BR>
     * �@@�i���N�G�X�g�f�[�^.���X�R�[�h[] != null�j�̏ꍇ�A<BR>
     * �@@�߂�l�ҏW�p�C���X�^���X�ɁA���X�R�[�h�z��̗v�f�������X�R�[�h��ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@���N�G�X�g�f�[�^.���X�R�[�h[index]<BR>
     * <BR>
     * �S�j�@@�⍇���R�[�h�����ǉ��@@���w�肪����ꍇ�̂݁C�O����v����<BR>
     * �@@�i���N�G�X�g�f�[�^.�⍇���R�[�h != null�j�̏ꍇ�A�߂�l�ҏW�p<BR>
     * �C���X�^���X�ɖ⍇���R�[�h��ǉ�����B<BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@���N�G�X�g�f�[�^.�⍇���R�[�h + "%"<BR>
     * <BR>
     * �T�j�@@�ڋq�R�[�h�����ǉ��@@���w�肪����ꍇ�̂� <BR>
     * �@@�i���N�G�X�g�f�[�^.�ڋq�R�[�h != null�j�̏ꍇ�A�߂�l�ҏW�p<BR>
     * �C���X�^���X�Ɍ����R�[�h��ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@���N�G�X�g�f�[�^.�ڋq�R�[�h + "%"<BR>
     * <BR>
     * �U�j�@@�ڋq�������ǉ��@@���w�肪����ꍇ�̂݁C�O���^�����v����<BR>
     * �@@�i���N�G�X�g�f�[�^.�ڋq���@@ != null�j�̏ꍇ�A�߂�l�ҏW�p<BR>
     * �C���X�^���X�Ɍڋq���i�����j��ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@"%" + ���N�G�X�g�f�[�^.�ڋq���i�����j + "%"<BR>
     * <BR>
     * �V�j�@@���������ǉ��@@���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�����@@ != null�j�̏ꍇ�A�߂�l�ҏW�p<BR>
     * �C���X�^���X�Ɍ�����ǉ�����B<BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@���N�G�X�g�f�[�^.����<BR>
     * <BR>
     * �W�j�@@�⍇�����i���j�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�⍇�����i���j != null�j�̏ꍇ�A<BR>
     * �@@�߂�l�ҏW�p�C���X�^���X�ɖ⍇�����������ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@���N�G�X�g�f�[�^.�⍇�����i���j<BR>
     * <BR>
     * �X�j�@@�⍇�����i���j�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�⍇�����i���j != null�j�̏ꍇ�A<BR>
     * �@@�߂�l�ҏW�p�C���X�^���X�ɖ⍇�����������ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@���N�G�X�g�f�[�^.�⍇�����i���j�@@�����ԕ����� 23:59:59�@@�ɕҏW����B<BR>
     * <BR>
     * �P�O�j�@@�@@�\�h�c�����ǉ�<BR>
     * �@@�߂�l�ҏW�p�C���X�^���X�ɁA�@@�\�h�c�z��̗v�f�����@@�\�h�c��ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@���N�G�X�g�f�[�^.�@@�\�h�c[index]<BR>
     * <BR>
     * �P�P�j�@@�z���ԋp <BR>
     * �@@�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j.toArray()��ԋp����B <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��Җ⍇���Ǘ����⍇���ꗗ���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * 
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@return String[]
     * @@roseuid 41AC22610399
     */
    protected Object[] createQueryContainer(WEB3AdminFaqListRequest l_request, String l_strInstitutionCode) 
    {
        final String STR_METHOD_NAME = "createQueryContainer(WEB3AdminFaqListRequest, String)";
        log.entering(STR_METHOD_NAME);
        
        List l_lstQueryContainer = new ArrayList();
        
        //�،���Џ����ǉ�
        l_lstQueryContainer.add(l_strInstitutionCode);
        
        //���X�����ǉ� ���w�肪����ꍇ�̂� 
        //�߂�l�ҏW�p�C���X�^���X�ɁA���X�R�[�h�z��̗v�f�������X�R�[�h��ǉ�����B 
        if (l_request.branchCode.length > 0)
        {
            for (int i = 0; i < l_request.branchCode.length; i++)
            {
                l_lstQueryContainer.add(l_request.branchCode[i]);
            }
        }
        
        //�⍇���R�[�h�����ǉ��@@���w�肪����ꍇ�̂݁C�O����v����
        //�i���N�G�X�g�f�[�^.�⍇���R�[�h != null�j�̏ꍇ�A�߂�l�ҏW�p�C���X�^���X�ɖ⍇���R�[�h��ǉ�����B 
        if (l_request.faqCode != null)
        {
            l_lstQueryContainer.add(l_request.faqCode);
        }
        
        //�ڋq�R�[�h�����ǉ��@@���w�肪����ꍇ�̂� 
        //�i���N�G�X�g�f�[�^.�ڋq�R�[�h != null�j�̏ꍇ�A�߂�l�ҏW�p�C���X�^���X�Ɍ����R�[�h��ǉ�����B 
        if (l_request.accountCode != null)
        {
            l_lstQueryContainer.add(l_request.accountCode);
        }
        
        //�ڋq�������ǉ��@@���w�肪����ꍇ�̂݁C�O���^�����v����
        //�i���N�G�X�g�f�[�^.�ڋq���@@ != null�j�̏ꍇ�A�߂�l�ҏW�p�C���X�^���X�Ɍڋq���i�����j��ǉ�����B 
        if (l_request.accountName != null)
        {
            l_lstQueryContainer.add(l_request.accountName);
        }
        
        //���������ǉ��@@���w�肪����ꍇ�̂� 
        //�i���N�G�X�g�f�[�^.�����@@ != null�j�̏ꍇ�A�߂�l�ҏW�p�C���X�^���X�Ɍ�����ǉ�����B
        if (l_request.subject != null)
        {
            l_lstQueryContainer.add(l_request.subject);
        }
        
        //�⍇�����i���j�����ǉ� ���w�肪����ꍇ�̂�
        //�i���N�G�X�g�f�[�^.�⍇�����i���j != null�j�̏ꍇ�A
        //�߂�l�ҏW�p�C���X�^���X�ɖ⍇�����������ǉ�����B         
        if (l_request.faqDateFrom != null)
        {
            l_lstQueryContainer.add(l_request.faqDateFrom);
        }
        
        //�⍇�����i���j�����ǉ� ���w�肪����ꍇ�̂� 
        //�i���N�G�X�g�f�[�^.�⍇�����i���j != null�j�̏ꍇ�A 
        //�߂�l�ҏW�p�C���X�^���X�ɖ⍇�����������ǉ�����B 
        if (l_request.faqDateTo != null)
        {            
            Calendar gCalendar = new GregorianCalendar();
            gCalendar.setTime(l_request.faqDateTo);
            gCalendar.set(Calendar.HOUR_OF_DAY, 23);
            gCalendar.set(Calendar.MINUTE, 59);
            gCalendar.set(Calendar.SECOND, 59);
            l_lstQueryContainer.add(gCalendar.getTime());

        }
        
        //�@@�\�h�c�����ǉ� 
        //�߂�l�ҏW�p�C���X�^���X�ɁA�@@�\�h�c�z��̗v�f�����@@�\�h�c��ǉ�����B 
        if (l_request.transactionId.length > 0)
        {
            for (int i = 0; i < l_request.transactionId.length; i++)
            {
                l_lstQueryContainer.add(l_request.transactionId[i]);
            }
        }
        
        Object[] l_queryContainer = new Object[l_lstQueryContainer.size()];
        l_lstQueryContainer.toArray(l_queryContainer);
        
        log.exiting(STR_METHOD_NAME);
        return l_queryContainer;
    }
    
    /**
     * (create�\�[�g����)<BR>
     * �\�[�g�����������ҏW����B <BR>
     * <BR>
     * �����̃\�[�g�L�[���������ڂɊY������⍇���e�[�u���񕨗������g�p���A <BR>
     * �\�[�g�L�[�̎w��̒ʂ�A�\�[�g����������iorder by��j��ҏW���ԋp����B <BR>
     * <BR>
     * ���@@�\�[�g�L�[�Ɏw�肳��鍀�ڂ͈ȉ��̒ʂ�B<BR>
     * �@@ �⍇�����.�⍇���R�[�h �i�⍇���e�[�u��.�⍇���R�[�h�j<BR>
     * �@@ �⍇�����.���X�R�[�h �i�⍇���e�[�u��.���X�R�[�h�j<BR>
     * �@@ �⍇�����.�ڋq�R�[�h �i�⍇���e�[�u��.�����R�[�h�j<BR>
     * �@@ �⍇�����.�⍇�������i�����J�݌����q.�������������j<BR>
     * �@@ �⍇�����.�@@�\�h�c�i�⍇���e�[�u��.�@@�\�h�c�j<BR>
     * @@param l_sortKeys - (�\�[�g�L�[)<BR>
     * �⍇���Ǘ��\�[�g�L�[�̔z��<BR>
     * 
     * @@return String
     * @@roseuid 41AC2261039C
     */
    protected String createSortCond(WEB3FaqSortKey[] l_sortKeys) 
    {
        final String STR_METHOD_NAME = "createSortCond(WEB3FaqSortKey[] l_sortKeys)";
        log.entering(STR_METHOD_NAME);
        
        StringBuffer l_sbSortCond = new StringBuffer();
        
        for (int i = 0; i < l_sortKeys.length; i++)
        {
            String l_strSubCond = null;
            if (WEB3FaqKeyItemDef.FAQ_NUMBER.equals(l_sortKeys[i].keyItem))
            {
                //�⍇�����.�⍇���R�[�h �i�⍇���e�[�u��.�⍇���R�[�h�j 
                l_strSubCond = "faq_number";
            }
            else if (WEB3FaqKeyItemDef.BRANCH_CODE.equals(l_sortKeys[i].keyItem))
            {
                //�⍇�����.���X�R�[�h �i�⍇���e�[�u��.���X�R�[�h�j  
                l_strSubCond = "branch_code";
            }
            else if (WEB3FaqKeyItemDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
            {
                //�⍇�����.�ڋq�R�[�h �i�⍇���e�[�u��.�����R�[�h�j   
                l_strSubCond = "account_code";
            }
            else if (WEB3FaqKeyItemDef.FAQ_DATETIME.equals(l_sortKeys[i].keyItem))
            {
                //�⍇�����.�⍇�������i�����J�݌����q.�������������j    
                l_strSubCond = "created_timestamp";
            }
            else if (WEB3FaqKeyItemDef.TRANSACTION_ID.equals(l_sortKeys[i].keyItem))
            {
                //�⍇�����.�@@�\�h�c�i�⍇���e�[�u��.�@@�\�h�c�j 
                l_strSubCond = "transaction_id";
            }
            else
            {
                continue;
            }
            
            if (l_sbSortCond.length() != 0)
            {
                l_sbSortCond.append(", ");
            }
            l_sbSortCond.append(l_strSubCond);
            
            if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
            {
                l_sbSortCond.append(" desc");
            }
            else
            {
                l_sbSortCond.append(" asc");
            }
            
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_sbSortCond.toString();
    }
}
@
