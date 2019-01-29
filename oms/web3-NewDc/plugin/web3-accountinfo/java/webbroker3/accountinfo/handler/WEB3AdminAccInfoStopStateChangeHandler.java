head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.14.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoStopStateChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l����~�󋵕ύX�n���h��(WEB3AdminAccInfoStopStateChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 ���C�g (���u) �V�K�쐬
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoStopStateChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l����~�󋵕ύX�n���h��)<BR>
 * �Ǘ��҂��q�l����~�󋵕ύX�n���h��<BR>
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AdminAccInfoStopStateChangeHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoStopStateChangeHandler.class);
        
    /**
     * @@roseuid 418F3A1401A5
     */
    public WEB3AdminAccInfoStopStateChangeHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * ��~�󋵕ύX���͉�ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ��҂��q�l����~�󋵕ύX�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l����~�󋵕ύX���̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B32FB00FD
     */
    public WEB3AdminAccInfoStopStateChangeInputResponse inputScreenDisplay(WEB3AdminAccInfoStopStateChangeInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AdminAccInfoStopStateChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoStopStateChangeInputResponse l_response = null;
        WEB3AdminAccInfoStopStateChangeService l_service = null;
        
        try
        {
            l_service = 
                (WEB3AdminAccInfoStopStateChangeService)Services.getService(
                    WEB3AdminAccInfoStopStateChangeService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoStopStateChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l����~�󋵕ύX�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�Ǘ��҂��q�l����~�󋵕ύX�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3AdminAccInfoStopStateChangeInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoStopStateChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��҂��q�l����~�󋵕ύX�T�[�r�X�Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
    
    /**
     * (�ύX�m�F)<BR>
     * ��~�󋵕ύX�m�F�������s���B <BR>
     * <BR>
     * �Ǘ��҂��q�l����~�󋵕ύX�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l����~�󋵕ύX�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeConfirmResponse
     * @@roseuid 41634D26016F
     */
    public WEB3AdminAccInfoStopStateChangeConfirmResponse changeConfirm(WEB3AdminAccInfoStopStateChangeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " changeConfirm(WEB3AdminAccInfoStopStateChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoStopStateChangeConfirmResponse l_response = null;
        WEB3AdminAccInfoStopStateChangeService l_service = null;
        
        try
        {
            l_service = 
                (WEB3AdminAccInfoStopStateChangeService)Services.getService(
                    WEB3AdminAccInfoStopStateChangeService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoStopStateChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l����~�󋵕ύX�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�Ǘ��҂��q�l����~�󋵕ύX�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3AdminAccInfoStopStateChangeConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoStopStateChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��҂��q�l����~�󋵕ύX�T�[�r�X�Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
    
    /**
     * (�ύX����)<BR>
     * ��~�󋵕ύX�����������s���B <BR>
     * <BR>
     * �Ǘ��҂��q�l����~�󋵕ύX�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l����~�󋵕ύX�������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeCompleteResponse
     * @@roseuid 41634D26018E
     */
    public WEB3AdminAccInfoStopStateChangeCompleteResponse changeComplete(WEB3AdminAccInfoStopStateChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " changeComplete(WEB3AdminAccInfoStopStateChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoStopStateChangeCompleteResponse l_response = null;
        WEB3AdminAccInfoStopStateChangeService l_service = null;
        
        try
        {
            l_service = 
                (WEB3AdminAccInfoStopStateChangeService)Services.getService(
                WEB3AdminAccInfoStopStateChangeService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoStopStateChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l����~�󋵕ύX�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�Ǘ��҂��q�l����~�󋵕ύX�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3AdminAccInfoStopStateChangeCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoStopStateChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��҂��q�l����~�󋵕ύX�T�[�r�X�Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
