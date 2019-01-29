head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.24.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioOnPaymentCooperationHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���A�g�n���h��(WEB3AioOnPaymentCooperationHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/16 ��O�� (���u) �V�K�쐬   
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioOnPaymentCooperationRequest;
import webbroker3.aio.message.WEB3AioOnPaymentCooperationResponse;
import webbroker3.aio.service.delegate.WEB3AioOnPaymentCooperationService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�o���A�g�n���h��) <BR>
 * �o���A�g�n���h���N���X <BR>
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3AioOnPaymentCooperationHandler implements MessageHandler
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioOnPaymentCooperationHandler.class);
    
    /**
     * @@roseuid 41E77F4A009C
     */
    public WEB3AioOnPaymentCooperationHandler()
    {
    }

    /**
     * (�o���A�g���N�G�X�g) <BR>
     * �o���A�g�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * 
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3AioOnPaymentCooperationResponse
     * @@roseuid 41BCF40E01DD
     */
    public WEB3AioOnPaymentCooperationResponse onPaymentCooperationRequest(
        WEB3AioOnPaymentCooperationRequest l_request)
    {
        final String STR_METHOD_NAME = 
            "onPaymentCooperationRequest(WEB3AioOnPaymentCooperationRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AioOnPaymentCooperationService l_service;
        WEB3AioOnPaymentCooperationResponse l_response;
        
        try
        {
            //FX�ւ̐U�փT�[�r�X���擾��
            l_service = 
                (WEB3AioOnPaymentCooperationService)Services.getService(
                    WEB3AioOnPaymentCooperationService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AioOnPaymentCooperationResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__�o���A�g�T�[�r�X�̎擾�Ɏ��s���܂���__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AioOnPaymentCooperationResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioOnPaymentCooperationResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call �o���A�g�T�[�r�X.execute__",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
}@
