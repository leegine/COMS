head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.25.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinSelectHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �I�����C�������I���n���h���N���X(WEB3AioCashinSelectHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ���z (���u) �V�K�쐬
                   2004/10/22 ���� (���u) ���r���[    
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioCashinSelectRequest;
import webbroker3.aio.message.WEB3AioCashinSelectResponse;
import webbroker3.aio.service.delegate.WEB3AioCashinSelectService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�I�����C�������I���n���h��)<BR>
 * �I�����C�������I���n���h���N���X
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioCashinSelectHandler implements MessageHandler 
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinSelectHandler.class);
        
    /**
     * (�I�����C�������I�����N�G�X�g)<BR>
     * �I�����C�������I����ʕ\���������s���B<BR> 
     * <BR>
     * �I�����C�������I���T�[�r�X���擾���A <BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3AioCashinSelectResponse
     * @@roseuid 40F1F23F0050
     */
    public WEB3AioCashinSelectResponse handleCashinSelectRequest(
        WEB3AioCashinSelectRequest l_request) 
    {
        final String l_strMethodName = 
            "handleCashinSelectRequest(WEB3AioCashinSelectRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3AioCashinSelectService l_service;
        WEB3AioCashinSelectResponse l_response;        
        try
        {
            //�I�����C�������I���T�[�r�X���擾��
            l_service = 
                (WEB3AioCashinSelectService)Services.getService(
                WEB3AioCashinSelectService.class);            
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AioCashinSelectResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "�I�����C�������I���T�[�r�X�̎擾�Ɏ��s���܂���",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AioCashinSelectResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioCashinSelectResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�I�����C�������I����ʕ\�������Ɏ��s���܂���",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);      
        
        return l_response; 
    }
}
@
