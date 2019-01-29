head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.21.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��U�֓��̓n���h��(WEB3AioSecurityTransferInputHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/08 ���z (���u) �V�K�쐬   
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioSecurityTransferInputRequest;
import webbroker3.aio.message.WEB3AioSecurityTransferInputResponse;
import webbroker3.aio.message.WEB3AioSecurityTransferListRequest;
import webbroker3.aio.message.WEB3AioSecurityTransferListResponse;
import webbroker3.aio.service.delegate.WEB3AioSecurityTransferInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�،��U�֓��̓n���h��)<BR>
 * �،��U�֓��̓n���h���N���X
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferInputHandler implements MessageHandler 
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecurityTransferInputHandler.class);
    
    /**
     * (�،��U�ֈꗗ���N�G�X�g)<BR>
     * �،��U�֓��̓T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.aio.message.WEB3AioSecurityTransferListResponse
     * @@roseuid 41577261007C
     */
    public WEB3AioSecurityTransferListResponse handleSecurityTransferListRequest(
            WEB3AioSecurityTransferListRequest l_request) 
    {
        String l_strMethodName = 
            "handleSecurityTransferListRequest(WEB3AioSecurityTransferListRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3AioSecurityTransferInputService l_service;
        WEB3AioSecurityTransferListResponse l_response;
        
        try
        {
            //�،��U�֓��̓T�[�r�X���擾��
            l_service = 
                (WEB3AioSecurityTransferInputService)Services.getService(
                    WEB3AioSecurityTransferInputService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AioSecurityTransferListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__�،��U�֓��̓T�[�r�X�̎擾�Ɏ��s���܂���__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AioSecurityTransferListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioSecurityTransferListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call �،��U�֓��̓T�[�r�X.execute__",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (�،��U�֓��̓��N�G�X�g)<BR>
     * �،��U�֓��̓T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.aio.message.WEB3AioSecurityTransferInputResponse
     * @@roseuid 4157731E006C
     */
    public WEB3AioSecurityTransferInputResponse handleSecurityTransferInputRequest(
            WEB3AioSecurityTransferInputRequest l_request) 
    {
        String l_strMethodName = 
            "handleSecurityTransferInputRequest(WEB3AioSecurityTransferInputRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3AioSecurityTransferInputService l_service;
        WEB3AioSecurityTransferInputResponse l_response;
        
        try
        {
            //�،��U�֓��̓T�[�r�X���擾��
            l_service = 
                (WEB3AioSecurityTransferInputService)Services.getService(
                    WEB3AioSecurityTransferInputService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AioSecurityTransferInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__�،��U�֓��̓T�[�r�X�̎擾�Ɏ��s���܂���__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AioSecurityTransferInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioSecurityTransferInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call �،��U�֓��̓T�[�r�X.execute__",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
}
@
