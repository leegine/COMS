head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.46.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenCompleteMailSendHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�݊������[�����M�n���h��(WEB3AdminAccOpenCompleteMailSendHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 ���w�� �V�K�쐬
*/
package webbroker3.accountopen.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountopen.message.WEB3AdminAccOpenCompleteMailSendListRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenCompleteMailSendListResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenCompleteMailSendRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenCompleteMailSendResponse;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenCompleteMailSendService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��Ҍ����J�݊������[�����M�n���h��)<BR>
 * �Ǘ��Ҍ����J�݊������[�����M�n���h��
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminAccOpenCompleteMailSendHandler implements MessageHandler 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminAccOpenCompleteMailSendHandler.class);
    
    /**
     * @@roseuid 41B45E7400CB
     */
    public WEB3AdminAccOpenCompleteMailSendHandler() 
    {
     
    }
    
    /**
     * (���[�����M�ꗗ�\��)<BR>
     * �����J�݊������[�����M�ꗗ�\���������s���B <BR>
     * <BR>
     * �����J�݊������[�����M�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ��Ҍ����J�݊������[�����M�ꗗ���N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenCompleteMailSendListResponse
     * @@throws WEB3BaseException
     * @@roseuid 41A1958402D1
     */
    public WEB3AdminAccOpenCompleteMailSendListResponse mailSendListDisplay(WEB3AdminAccOpenCompleteMailSendListRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " mailSendListDisplay(WEB3AdminAccOpenCompleteMailSendListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenCompleteMailSendListResponse l_response = null;
        WEB3AdminAccOpenCompleteMailSendService l_service = null;
        
        try
        {
            l_service = (WEB3AdminAccOpenCompleteMailSendService)
                Services.getService(WEB3AdminAccOpenCompleteMailSendService.class);//Exception
        }        
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenCompleteMailSendListResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " �����J�݊������[�����M�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response = (WEB3AdminAccOpenCompleteMailSendListResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenCompleteMailSendListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " �Ǘ��Ҍ����J�݊������[�����M�ꗗ���N�G�X�g�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (���[�����M)<BR>
     * �����J�݊������[�����M�������s���B <BR>
     * <BR>
     * �����J�݊������[�����M�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ��Ҍ����J�݊������[�����M���N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenCompleteMailSendResponse
     * @@throws WEB3BaseException
     * @@roseuid 41A1958402E1
     */
    public WEB3AdminAccOpenCompleteMailSendResponse mailSend(WEB3AdminAccOpenCompleteMailSendRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " mailSend(WEB3AdminAccOpenCompleteMailSendRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenCompleteMailSendResponse l_response = null;
        WEB3AdminAccOpenCompleteMailSendService l_service = null;
        
        try
        {
            l_service = (WEB3AdminAccOpenCompleteMailSendService)
                Services.getService(WEB3AdminAccOpenCompleteMailSendService.class);//Exception
        }       
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenCompleteMailSendResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " �����J�݊������[�����M�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response = (WEB3AdminAccOpenCompleteMailSendResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenCompleteMailSendResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " �Ǘ��Ҍ����J�݊������[�����M���N�G�X�g�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
}
@
