head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.22.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSonarCashTransHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : SONAR���o���n���h��(WEB3AioSonarCashTransHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ���� (���u) �V�K�쐬
                   2004/10/26 ���z (���u) ���r���[
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioSonarCashTransRequest;
import webbroker3.aio.message.WEB3AioSonarCashTransResponse;
import webbroker3.aio.service.delegate.WEB3AioSonarCashTransService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (SONAR���o���n���h��)<BR>
 * SONAR���o���n���h���N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0  
 */

public class WEB3AioSonarCashTransHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioSonarCashTransHandler.class);
    
    /**
     * (SONAR���o�����N�G�X�g)<BR>
     * SONAR���o���������s���B<BR> 
     * <BR>
     * SONAR���o���T�[�r�X���擾���A <BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3AioSonarCashTransResponse
     * @@roseuid 40FF6D7103D8
     */
    public WEB3AioSonarCashTransResponse handleAioCashTransRequest(WEB3AioSonarCashTransRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "handleAioCashTransRequest(WEB3AioSonarCashTransRequest l_request)"; 
        log.entering(STR_METHOD_NAME);
        
        //SONAR���o���T�[�r�X�C���^�[�t�F�C�X
        WEB3AioSonarCashTransService l_service = null;          
         
        //SONAR���o�����X�|���X�N���X
        WEB3AioSonarCashTransResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3AioSonarCashTransService) Services.getService(
                    WEB3AioSonarCashTransService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3AioSonarCashTransResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "SONAR���o���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AioSonarCashTransResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioSonarCashTransResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "SONAR���o���Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}

@
