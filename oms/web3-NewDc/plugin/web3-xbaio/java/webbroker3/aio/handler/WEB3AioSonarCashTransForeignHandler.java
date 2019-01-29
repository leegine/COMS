head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.25.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSonarCashTransForeignHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : SONAR���o���i�O�݁j�n���h��(WEB3AioSonarCashTransForeignHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/28 ���G�� (���u) �V�K�쐬
*/
package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioSonarCashTransForeignRequest;
import webbroker3.aio.message.WEB3AioSonarCashTransForeignResponse;
import webbroker3.aio.service.delegate.WEB3AioSonarCashTransForeignService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (SONAR���o���i�O�݁j�n���h��)<BR>
 * SONAR���o���i�O�݁j�n���h���N���X<BR>
 * <BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AioSonarCashTransForeignHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioSonarCashTransForeignHandler.class);
    
    /**
     * (SONAR���o���i�O�݁j���N�G�X�g)<BR>
     * SONAR���o���i�O�݁j�������s���B<BR> 
     * <BR>
     * SONAR���o���i�O�݁j�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AioSonarCashTransForeignResponse
     */
    public WEB3AioSonarCashTransForeignResponse sonarCashTransForeignRequest
        (WEB3AioSonarCashTransForeignRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "sonarCashTransForeignRequest(WEB3AioSonarCashTransForeignRequest l_request)"; 
        log.entering(STR_METHOD_NAME);
        
        //SONAR���o���i�O�݁j�T�[�r�X�C���^�[�t�F�C�X
        WEB3AioSonarCashTransForeignService l_service = null;          
         
        //SONAR���o���i�O�݁j���X�|���X
        WEB3AioSonarCashTransForeignResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3AioSonarCashTransForeignService)Services.getService(
                    WEB3AioSonarCashTransForeignService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3AioSonarCashTransForeignResponse)l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "SONAR���o���i�O�݁j�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AioSonarCashTransForeignResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioSonarCashTransForeignResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "SONAR���o���i�O�݁j�����Ɏ��s���܂����B", 
                l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
