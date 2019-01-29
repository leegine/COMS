head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.23.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��U�փn���h��(WEB3AioSecurityTransferHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/08 ���z (���u) �V�K�쐬   
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioSecurityTransferCompleteRequest;
import webbroker3.aio.message.WEB3AioSecurityTransferCompleteResponse;
import webbroker3.aio.message.WEB3AioSecurityTransferConfirmRequest;
import webbroker3.aio.message.WEB3AioSecurityTransferConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AioSecurityTransferService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�،��U�փn���h��)<BR>
 * �،��U�փn���h���N���X
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferHandler implements MessageHandler 
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecurityTransferHandler.class);
    
    /**
     * (confirm����)<BR>
     * �،��U�ւ̔����R�����s���B <BR>
     * <BR>
     * �،��U�փT�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3AioSecurityTransferConfirmResponse
     * @@roseuid 41577AF0002E
     */
    public WEB3AioSecurityTransferConfirmResponse handleSecurityTransferConfirmRequest(
            WEB3AioSecurityTransferConfirmRequest l_request) 
    {
        String l_strMethodName = 
            "handleSecurityTransferConfirmRequest(WEB3AioSecurityTransferConfirmRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3AioSecurityTransferService l_service;
        WEB3AioSecurityTransferConfirmResponse l_response;
        
        try
        {
            //�،��U�֓��̓T�[�r�X���擾��
            l_service = 
                (WEB3AioSecurityTransferService)Services.getService(
                    WEB3AioSecurityTransferService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AioSecurityTransferConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__�،��U�փT�[�r�X�̎擾�Ɏ��s���܂���__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AioSecurityTransferConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioSecurityTransferConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call �،��U�փT�[�r�X.execute__",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (complete����)<BR>
     * �،��U�ւ̓o�^���s���B<BR> 
     * <BR>
     * �،��U�փT�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3AioSecurityTransferCompleteResponse
     * @@roseuid 41577AF00030
     */
    public WEB3AioSecurityTransferCompleteResponse handleSecurityTransferCompleteRequest(
            WEB3AioSecurityTransferCompleteRequest l_request) 
    {
        String l_strMethodName = 
            "handleSecurityTransferCompleteRequest(WEB3AioSecurityTransferCompleteRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3AioSecurityTransferService l_service;
        WEB3AioSecurityTransferCompleteResponse l_response;
        
        try
        {
            //�،��U�֓��̓T�[�r�X���擾��
            l_service = 
                (WEB3AioSecurityTransferService)Services.getService(
                    WEB3AioSecurityTransferService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AioSecurityTransferCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__�،��U�փT�[�r�X�̎擾�Ɏ��s���܂���__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AioSecurityTransferCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioSecurityTransferCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call �،��U�փT�[�r�X.execute__",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
}
@
