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
filename	WEB3AdminFaqDetailsServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Җ₢���킹�Ǘ����⍇���ڍ׃T�[�r�XImpl(WEB3AdminFaqDetailsServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/23 ����� (���u) �V�K�쐬
*/

package webbroker3.faq.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.faq.WEB3Faq;
import webbroker3.faq.message.WEB3AdminFaqDetailsRequest;
import webbroker3.faq.message.WEB3AdminFaqDetailsResponse;
import webbroker3.faq.message.WEB3FaqInfo;
import webbroker3.faq.service.delegate.WEB3AdminFaqDetailsService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��Җ₢���킹�Ǘ����⍇���ڍ׃T�[�r�XImpl)<BR>
 * �Ǘ��Җ₢���킹�Ǘ����⍇���ڍ׃T�[�r�X�����N���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminFaqDetailsServiceImpl implements WEB3AdminFaqDetailsService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFaqDetailsServiceImpl.class);
           
    /**
     * @@roseuid 41C25C8B038A
     */
    public WEB3AdminFaqDetailsServiceImpl() 
    {
     
    }
    
    /**
     * �⍇���ڍו\�����������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR> 
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��Җ⍇���Ǘ����⍇���ڍ׃��N�G�X�g�̏ꍇ <BR>
     * �@@�|get�⍇���ڍ�()���R�[������B <BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41AC2E9501BC
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminFaqDetailsRequest)
        {
            l_response = getFaqDetails((WEB3AdminFaqDetailsRequest)l_request);
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
     * (get�⍇���ڍ�)<BR>
     * �⍇���ڍו\���������s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�⍇���Ǘ��i�⍇���ڍׁjget�⍇���ڍׁv�Q�ƁB <BR>
     * @@param l_request - �Ǘ��Җ⍇���Ǘ����⍇���ڍ׃��N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.faq.message.WEB3AdminFaqDetailsResponse
     * @@roseuid 41AC2E9501CC
     */
    protected WEB3AdminFaqDetailsResponse getFaqDetails(WEB3AdminFaqDetailsRequest l_request) throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = " getFaqDetails(WEB3AdminFaqDetailsRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B 
        l_request.validate();
        
        //���O�C�������A�Ǘ��҃I�u�W�F�N�g���擾����B 
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //�Ǘ��Ҍ����`�F�b�N���s���B 
        String l_strTransactionCategory = null;
        if (l_request.transactionCategoryCode != null)
        {
            l_strTransactionCategory = l_request.transactionCategoryCode;
        }
        else
        {
            l_strTransactionCategory = WEB3TransactionCategoryDef.FAQ;
        }
        
        l_administrator.validateAuthority(l_strTransactionCategory, false);
        
        //�،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        WEB3Faq l_faq = null;
        
        try
        {
            l_faq = new WEB3Faq(l_strInstitutionCode, l_request.faqCode);
        }
        catch (NotFoundException e)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + STR_METHOD_NAME,
                "�Y���f�[�^�����B" + 
                " [�،���ЃR�[�h] = " + l_strInstitutionCode + 
                " [�⍇���R�[�h] = " + l_request.faqCode);
        }
        
        //���X�R�[�h���擾����B
        String l_strBranchCode = l_faq.getBranchCode();
        
        //���X�������`�F�b�N����B
        l_administrator.validateBranchPermission(l_strBranchCode);
        
        WEB3FaqInfo l_faqInfo = new WEB3FaqInfo();
        
        //�⍇����񃁃b�Z�[�W�f�[�^�v���p�e�B�Ɉȉ��̒ʂ�A�l���Z�b�g����B
        
        //�⍇���R�[�h�F�@@�⍇��.get�⍇���R�[�h()
        l_faqInfo.faqCode = l_faq.getFaqNumber();
            
        //���X�R�[�h�F�@@�⍇��.get���X�R�[�h()
        l_faqInfo.branchCode = l_faq.getBranchCode();
            
        //�ڋq�R�[�h�F�@@�⍇��et�����R�[�h()�̍�6byte
        String l_strAccountCode = l_faq.getAccountCode();
        if (l_strAccountCode != null)
        {
            l_faqInfo.accountCode = l_strAccountCode.substring(0, 6);
        }
            
        //�ڋq���F�@@�⍇��.get�ڋq���i�����j()
        l_faqInfo.accountName = l_faq.getName();
            
        //���[���A�h���X�F�@@�⍇��.get���[���A�h���X()
        l_faqInfo.mailAddress = l_faq.getEmailAddress();
            
        //�⍇�������F�@@�⍇��.get�⍇������()
        l_faqInfo.faqDate = l_faq.getFaqDatetime();
            
        //�����F�@@�⍇��.get����()
        l_faqInfo.subject = l_faq.getSubject();
            
        //�@@�\�h�c�F�@@�⍇��.get�@@�\�h�c()
        l_faqInfo.transactionId = l_faq.getTransactionId();
        
        //�⍇�����e�F�@@�⍇��.get�⍇�����e()
        l_faqInfo.faqText = l_faq.getFaqText();
        
        //���X�|���X�f�[�^�𐶐�����B 
        WEB3AdminFaqDetailsResponse l_response = (WEB3AdminFaqDetailsResponse)l_request.createResponse();
        
        l_response.faqInfo = l_faqInfo;
       
        log.exiting(STR_METHOD_NAME);
        return l_response;
        
    }
}
@
