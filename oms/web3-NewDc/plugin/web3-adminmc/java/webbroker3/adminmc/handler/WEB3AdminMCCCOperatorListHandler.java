head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.41.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����CC�I�y���[�^�ꗗ�n���h��(WEB3AdminMCCCOperatorListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/22 �͌d�� (���u) �V�K�쐬
*/

package webbroker3.adminmc.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.adminmc.service.delegate.WEB3AdminMCCCOperatorListService;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorListInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorListInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorListResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorListRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҃��j���[����CC�I�y���[�^�ꗗ�n���h��)<BR>
 * �Ǘ��҃��j���[����CC�I�y���[�^�ꗗ�n���h��<BR>
 * 
 * @@author �͌d��
 * @@version 1.0
 */
public class WEB3AdminMCCCOperatorListHandler implements MessageHandler 
{
    /**
     *���O�o�̓��[�e�B���e�B�B
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCCCOperatorListHandler.class);        

    /**
     * @@roseuid 4198642B0000
     */
    public WEB3AdminMCCCOperatorListHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * CC�I�y���[�^�ꗗ�������͉�ʕ\�����������{����B<BR>
     * <BR>
     * �Ǘ��҃��j���[����CC�I�y���[�^�ꗗ�T�[�r�X���擾���Aexecute()���\�b�h���R�[�����E
     * �B<BR>
     * <BR>
     * <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����CC���ڰ��ꗗ����ظ��ăf�[�^�I�u�W�F�N�g<BR>
     * <BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorListInputResponse
     * @@roseuid 417F776100BA
     */
    public WEB3AdminMCCCOperatorListInputResponse inputScreenDisplay(WEB3AdminMCCCOperatorListInputRequest l_request) 
    {
        final String STR_METHOD_NAME = "inputScreenDisplay(WEB3AdminMCCCOperatorListInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminMCCCOperatorListInputResponse l_response = null;
        WEB3AdminMCCCOperatorListService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCCCOperatorListService)Services.getService(WEB3AdminMCCCOperatorListService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCCCOperatorListInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�Ǘ��҃��j���[����CC�I�y���[�^�ꗗ�T�[�r�X�̎擾�Ɏ��s���܂���", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminMCCCOperatorListInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCCCOperatorListInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "���͉�ʕ\���Ɏ��s���܂���",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);             
        return l_response; 

    }
    
    /**
     * (CC�I�y���[�^�ꗗ�\��)<BR>
     * CC�I�y���[�^�ꗗ�\�����������{����B<BR>
     * <BR>
     * �Ǘ��҃��j���[����CC�I�y���[�^�ꗗ�T�[�r�X���擾���Aexecute()���\�b�h���R�[�����E
     * �B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����CC���ڰ��ꗗظ��ăf�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorListResponse
     * @@roseuid 417F776100CA
     */
    public WEB3AdminMCCCOperatorListResponse CCOperatorListDisplay(WEB3AdminMCCCOperatorListRequest l_request) 
    {
        final String STR_METHOD_NAME = "CCOperatorListDisplay(WEB3AdminMCCCOperatorListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminMCCCOperatorListResponse l_response = null;
        WEB3AdminMCCCOperatorListService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCCCOperatorListService)Services.getService(WEB3AdminMCCCOperatorListService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCCCOperatorListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�Ǘ��҃��j���[����CC�I�y���[�^�ꗗ�T�[�r�X�̎擾�Ɏ��s���܂���", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminMCCCOperatorListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCCCOperatorListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "CC�I�y���[�^�ꗗ�\���Ɏ��s���܂���",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);             
        return l_response; 
    }
    
}
@
