head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.22.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSpsecTransferForceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������U�֋����n���h��(WEB3AioSpsecTransferForceHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/2/6 ��O�� (���u) �V�K�쐬   
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioSpsecTransferForceRequest;
import webbroker3.aio.message.WEB3AioSpsecTransferForceResponse;
import webbroker3.aio.service.delegate.WEB3AioSpsecTransferForceService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (��������U�֋����n���h��)<BR>
 * ��������U�֋����n���h���N���X 
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3AioSpsecTransferForceHandler implements MessageHandler 
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSpsecTransferForceHandler.class);
    
    /**
     * (��������U�֋������N�G�X�g )<BR>
     * ��������U�֋����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B 
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3AioSpsecTransferForceResponse
     * @@roseuid 4157981D0040
     */
    public WEB3AioSpsecTransferForceResponse handleSpsecTransferForceRequest(WEB3AioSpsecTransferForceRequest l_request) 
    {
    		
        String STR_METHOD_NAME = 
            "handleSpsecTransferForceRequest(WEB3AioSpsecTransferForceRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AioSpsecTransferForceService l_service;
        WEB3AioSpsecTransferForceResponse l_response;
        
        try
        {
            //��������U�֋����T�[�r�X���擾��
            l_service = 
                (WEB3AioSpsecTransferForceService)Services.getService(
                        WEB3AioSpsecTransferForceService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AioSpsecTransferForceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__(��������U�֋����T�[�r�X�̎擾�Ɏ��s���܂���__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AioSpsecTransferForceResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioSpsecTransferForceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call (��������U�֋����T�[�r�X.execute()__",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;        
        
    }
}
@
