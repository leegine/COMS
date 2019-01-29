head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.23.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinCooperationServiceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A�g�n���h��(WEB3AioCashinCooperationServiceHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/1/11 ��O�� (���u) �V�K�쐬   
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioCashinCooperationNotifyRequest;
import webbroker3.aio.message.WEB3AioCashinCooperationNotifyResponse;
import webbroker3.aio.service.delegate.WEB3AioCashinCooperationService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����A�g�n���h��) <BR>
 * �����A�g�n���h���N���X <BR>
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3AioCashinCooperationServiceHandler implements MessageHandler
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinCooperationServiceHandler.class);
    
    /**
     * @@roseuid 41E77F4A009C
     */
    public WEB3AioCashinCooperationServiceHandler()
    {
    }

    /**
     * (�����A�g���N�G�X�g) <BR>
     * �����A�g�������s���B  <BR>
     * <BR>
     * �����A�g�T�[�r�X���擾���A  <BR>
     * execute()���\�b�h���R�[������B <BR>
     * <BR>
     * �T�[�r�X�ŗ�O�����������ꍇ�́A <BR>
     * ���X�|���X���b�Z�[�W�𐶐��� <BR>
     * �G���[����ݒ肵�ԋp����B  <BR>
     * 
     * @@param l_request - �����A�g�ʒm���N�G�X�g 
     * @@return WEB3AioCashinCooperationNotifyResponse
     * @@roseuid 41BCF40E01DD
     */
    public WEB3AioCashinCooperationNotifyResponse cashinCooperationRequest(
        WEB3AioCashinCooperationNotifyRequest l_request)
    {
        final String STR_METHOD_NAME = 
            "cashinCooperationRequest(WEB3AioCashinCooperationNotifyRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AioCashinCooperationService l_service;
        WEB3AioCashinCooperationNotifyResponse l_response;
        
        try
        {
            //�����A�g�T�[�r�X���擾��
            l_service = 
                (WEB3AioCashinCooperationService)Services.getService(
                    WEB3AioCashinCooperationService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AioCashinCooperationNotifyResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__�����A�g�T�[�r�X�̎擾�Ɏ��s���܂���__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AioCashinCooperationNotifyResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioCashinCooperationNotifyResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call �����A�g�T�[�r�X.execute__",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
}@
