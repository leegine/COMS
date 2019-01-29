head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.23.04.50.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1cc4d897c1210b4;
filename	WEB3AdminMailInfoReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[�����ꗗ�T�[�r�XImpl(WEB3AdminMailInfoReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  鰊](���u) �V�K�쐬
*/
package webbroker3.mailinfo.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeMailInfo;
import webbroker3.mailinfo.WEB3AdminMailInfoClientRequestService;
import webbroker3.mailinfo.WEB3AdminMailInfoManager;
import webbroker3.mailinfo.define.WEB3AdminMailInfoKeyItemDef;
import webbroker3.mailinfo.message.WEB3AdminMailInfoGroup;
import webbroker3.mailinfo.message.WEB3AdminMailInfoReferenceRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoReferenceResponse;
import webbroker3.mailinfo.message.WEB3AdminMailInfoSortKey;
import webbroker3.mailinfo.service.delegate.WEB3AdminMailInfoReferenceService;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3LogUtility;


/**
 * (���[�����ꗗ�T�[�r�XImpl)<BR>
 * ���[�����ꗗ�T�[�r�X�����N���X<BR>
 * 
 * @@author 鰊]
 * @@version 1.0.1
 */
public class WEB3AdminMailInfoReferenceServiceImpl extends WEB3AdminMailInfoClientRequestService implements WEB3AdminMailInfoReferenceService 
{    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static  WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMailInfoReferenceServiceImpl.class);
    
    /**
     * @@roseuid 416F1DCB03D8
     */
    public WEB3AdminMailInfoReferenceServiceImpl() 
    {
     
    }
    
    /**
     * ���[�����ꗗ�Ɖ�����s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i���[�����ꗗ�jexecute�v�Q��<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C46DF005B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if(!(l_request instanceof WEB3AdminMailInfoReferenceRequest))
        {
            String l_strErrorMessage = "�p�����[�^�̗ތ^���s��";
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80018.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);
        }
        
        WEB3AdminMailInfoReferenceRequest l_referenceRequest = (WEB3AdminMailInfoReferenceRequest)l_request;
        String l_strSortCond = null;
        String l_strInstitutionCode = null;
              
        // (1.1)validate
        l_referenceRequest.validate();

        // (1.2)getInstanceFrom���O�C�����()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();

        //(1.3)validate����()
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.MAILINFO, false);

        //(1.4)get�،���ЃR�[�h( )
        l_strInstitutionCode = l_administartor.getInstitutionCode();

        //(1.5)create�\�[�g����(���[�����\�[�g�L�[[ ])
        l_strSortCond = createSortCond(l_referenceRequest.sortKeys);        

        //(1.6)get���[���ꗗ(String, String)
        WEB3GentradeMailInfo[] l_genMails = WEB3AdminMailInfoManager.getMailList(l_strInstitutionCode, l_strSortCond);
        WEB3AdminMailInfoReferenceResponse l_response = null;   
        
            //(1.7)�J��Ԃ���
            int l_intSizeCnt = l_genMails.length;
            WEB3AdminMailInfoGroup[] l_mailGroups = new WEB3AdminMailInfoGroup[l_intSizeCnt];
            for (int i = 0; i < l_intSizeCnt; i ++)
            {
                WEB3AdminMailInfoGroup l_mailGroup = new WEB3AdminMailInfoGroup();
                l_mailGroup.sendMailDiv = l_genMails[i].getSendmailDiv();
                l_mailGroup.discernId = l_genMails[i].getDiscernmentId();
                l_mailGroup.mailName = l_genMails[i].getMailName();
                l_mailGroup.mailFrom = l_genMails[i].getMailSender();
                l_mailGroup.mailSubject = l_genMails[i].getSubject();     
                l_mailGroups[i] = l_mailGroup;    
            }
            
            //1.8 create���X�|���X( )
            l_response = (WEB3AdminMailInfoReferenceResponse) l_referenceRequest.createResponse();
            
            //1.9 <�y�[�W���O����>
            //�y�[�W���\���s��
            int l_intPageSize = Integer.parseInt(l_referenceRequest.pageSize);
            
            //�v���y�[�W�ԍ�
            int l_intPageIndex = Integer.parseInt(l_referenceRequest.pageIndex);
                       
            //�y�[�W���O����
            WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(l_mailGroups, l_intPageIndex, l_intPageSize);
            
            //�v���p�e�B�E�Z�b�g
            //(�\���y�[�W�ԍ�)
            l_response.pageIndex = l_pageIndexInfo.getPageIndex() + "";
            //���y�[�W��
            l_response.totalPages = l_pageIndexInfo.getTotalPages() + "";  
            //�����R�[�h��
            l_response.totalRecords = l_pageIndexInfo.getTotalRecords() +"";
              
            Object[] l_temps = l_pageIndexInfo.getArrayReturned(WEB3AdminMailInfoGroup.class);
            if (l_temps != null)
            {
                l_response.mailInfoList = (WEB3AdminMailInfoGroup[])l_temps;             
            }

        
        log.exiting(STR_METHOD_NAME);                
        return l_response;
    }
    
    /**
     * (create�\�[�g����)<BR>
     * ���N�G�X�g���ꂽ�\�[�g�L�[����A�\�[�g�����Ƃ��Ďg�p�\��<BR>
     * ��������쐬���A�ԋp����B<BR>
     * <BR>
     * 1) �\�[�g�����̍쐬<BR>
     * �@@����.�\�[�g�����̌������A�ȉ����J��Ԃ��B<BR>
     *  1-1) �Ή�����e�[�u���̗񕨗����������^�~���w���t�����Z�b�g����B <BR>
     * <BR>
     * �@@�@@���L�[���ڂƃe�[�u���̗񖼏̂Ƃ̑Ή��͈ȉ��̒ʂ�B <BR>
     * �@@�@@�@@���L�[���ڕ�����(�V���{����)�́A���b�Z�[�W��`�����Q�ƁB <BR>
     * �@@�@@�@@���e�[�u���̗񕨗����́A�e�[�u�����C�A�E�g���Q�ƁB <BR>
     * <BR>
     * �@@�@@�@@�@@�E�v���O����ID=���[��.�v���O����ID<BR>
     * �@@�@@�@@�@@�E����ID =���[��.����ID<BR>
     * <BR>
     * �@@�@@�������^�~���w��́A�\�[�g�L�[.�����^�~�� �w��ɏ]���ݒ肷��B <BR>
     * <BR>
     * 2) 1)�ō쐬�����������ԋp����B<BR>
     * @@param l_sortKey - (�\�[�g�L�[)<BR>
     * �\�[�g�L�[<BR>
     * @@return String
     * @@roseuid 413C48CC015B
     */
    protected  String createSortCond(WEB3AdminMailInfoSortKey[] l_sortKey) 
    {
        final String STR_METHOD_NAME = " createSortCond(WEB3AdminMailInfoSortKey[] l_sortKey)";
        log.entering(STR_METHOD_NAME);
        
        StringBuffer l_sbReturn = new StringBuffer();
        
        for (int i = 0; i < l_sortKey.length; i++)
        {
            if(WEB3AdminMailInfoKeyItemDef.DISCERN_ID.equals(l_sortKey[i].keyItem ))
            {
                if (l_sbReturn.length() != 0) 
                {
                    l_sbReturn.append(", ");
                }
                l_sbReturn.append("discernment_id");
            }
            else if(WEB3AdminMailInfoKeyItemDef.SENDMAIL_DIV.equals(l_sortKey[i].keyItem))
            {
                if (l_sbReturn.length() != 0) 
                {
                    l_sbReturn.append(", ");
                }
                l_sbReturn.append("sendmail_div");
            }
            else
            {
                continue;
            }
            
            if (WEB3AscDescDef.ASC.equals(l_sortKey[i].ascDesc))
            {
                l_sbReturn.append(" ASC");
            }
            else if (WEB3AscDescDef.DESC.equals(l_sortKey[i].ascDesc)) 
            {
                l_sbReturn.append(" DESC");
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_sbReturn.toString();
    }
}
@
