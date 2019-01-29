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
filename	WEB3AdminFaqListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Җ⍇���Ǘ����⍇���ꗗ�n���h��(WEB3AdminFaqListHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/23 ����� (���u) �V�K�쐬
*/

package webbroker3.faq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.faq.message.WEB3AdminFaqListInputRequest;
import webbroker3.faq.message.WEB3AdminFaqListInputResponse;
import webbroker3.faq.message.WEB3AdminFaqListRequest;
import webbroker3.faq.message.WEB3AdminFaqListResponse;
import webbroker3.faq.service.delegate.WEB3AdminFaqListService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��Җ⍇���Ǘ����⍇���ꗗ�n���h��)<BR>
 * �Ǘ��Җ⍇���Ǘ����⍇���ꗗ�n���h��<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminFaqListHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFaqListHandler.class);
            
    /**
     * @@roseuid 41C25BF3034B
     */
    public WEB3AdminFaqListHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * �⍇���ꗗ���͉�ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ��Җ⍇���Ǘ����⍇���ꗗ�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ��Җ⍇���Ǘ����⍇���ꗗ���̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * 
     * @@return webbroker3.faq.message.WEB3AdminFaqListInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 41AC1B020118
     */
    public WEB3AdminFaqListInputResponse inputScreenDisplay(WEB3AdminFaqListInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AdminFaqListInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFaqListInputResponse l_response = null;
        WEB3AdminFaqListService l_service = null;

        try
        {
            l_service = (WEB3AdminFaqListService)Services.getService(WEB3AdminFaqListService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFaqListInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��Җ⍇���Ǘ����⍇���ꗗ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminFaqListInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFaqListInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��Җ⍇���Ǘ����⍇���ꗗ�̖⍇���ꗗ���͉�ʕ\�������Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
    
    /**
     * (�⍇���ꗗ�\��)<BR>
     * �⍇���ꗗ�\���������s���B<BR>
     * <BR>
     * �Ǘ��Җ⍇���Ǘ����⍇���ꗗ�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ��Җ⍇���Ǘ����⍇���ꗗ���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.faq.message.WEB3AdminFaqListResponse
     * @@roseuid 41AC1B020137
     */
    public WEB3AdminFaqListResponse faqListDisplay(WEB3AdminFaqListRequest l_request) 
    {
        final String STR_METHOD_NAME = " faqListDisplay(WEB3AdminFaqListRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFaqListResponse l_response = null;
        WEB3AdminFaqListService l_service = null;

        try
        {
            l_service = (WEB3AdminFaqListService)Services.getService(WEB3AdminFaqListService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFaqListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��Җ⍇���Ǘ����⍇���ꗗ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminFaqListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFaqListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��Җ⍇���Ǘ����⍇���ꗗ�̖⍇���ꗗ�\�������Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
