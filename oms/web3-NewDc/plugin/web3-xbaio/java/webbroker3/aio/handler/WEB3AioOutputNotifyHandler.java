head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.25.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioOutputNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o�ɒʒm�n���h��(WEB3AioOutputNotifyHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/05 ��O�� (���u) �V�K�쐬   
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioOutputNotifyRequest;
import webbroker3.aio.message.WEB3AioOutputNotifyResponse;
import webbroker3.aio.service.delegate.WEB3AioOutputNotifyService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�o�ɒʒm�n���h��)<BR>
 * �o�ɒʒm�n���h���N���X 
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3AioOutputNotifyHandler implements MessageHandler 
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioOutputNotifyHandler.class);
    
    /**
     * (�o�ɒʒm���N�G�X�g)<BR>
     * �o�ɒʒm�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B 
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3AioOutputNotifyResponse
     * @@roseuid 4157981D0040
     */
    public WEB3AioOutputNotifyResponse outputNotifyRequest(WEB3AioOutputNotifyRequest l_request) 
    {
    		
        String STR_METHOD_NAME = 
            "outputNotifyRequest(WEB3AioOutputNotifyRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AioOutputNotifyService l_service;
        WEB3AioOutputNotifyResponse l_response;
        
        try
        {
            //�o�ɒʒm�T�[�r�X���擾��
            l_service = 
                (WEB3AioOutputNotifyService)Services.getService(
                    WEB3AioOutputNotifyService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AioOutputNotifyResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__(�o�ɒʒm�T�[�r�X�̎擾�Ɏ��s���܂���__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AioOutputNotifyResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioOutputNotifyResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call (�o�ɒʒm�T�[�r�X.execute()__",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;        
    }
}
@
