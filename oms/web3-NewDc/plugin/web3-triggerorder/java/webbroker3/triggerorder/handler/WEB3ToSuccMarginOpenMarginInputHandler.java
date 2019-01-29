head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.58.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginOpenMarginInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p����V�K�����̓n���h��(WEB3ToSuccMarginOpenMarginInputHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/9 杊��](���u) �V�K�쐬
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenInputRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginOpenMarginInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���j�M�p����V�K�����̓n���h��)<BR>
 * �i�A���j�M�p����V�K�����̓n���h���N���X<BR>
 *   
 * @@author 杊��]
 * @@version 1.0
 */
public class WEB3ToSuccMarginOpenMarginInputHandler implements MessageHandler 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginOpenMarginInputHandler.class);
    
    /**
     * @@roseuid 4369ED3103C8
     */
    public WEB3ToSuccMarginOpenMarginInputHandler() 
    {
     
    }
    
    /**
     * (get���͉��)<BR>
     * �i�A���j�M�p����V�K�����͉�ʕ\���������s���B<BR>
     * <BR>
     * �i�A���j�M�p����V�K�����̓T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �i�A���j�M�p����V�K�����̓��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3SuccMarginOpenInputResponse
     * @@roseuid 4328FF4202BE
     */
    public WEB3SuccMarginOpenInputResponse getOpenMarginInputScreen(WEB3SuccMarginOpenInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " getOpenMarginInputScreen(WEB3SuccMarginOpenInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccMarginOpenInputResponse l_response = null;
        WEB3ToSuccMarginOpenMarginInputService l_service = null;
        
        try
        {            
            //get�i�A���j�M�p����V�K�����̓T�[�r�X
            l_service = (WEB3ToSuccMarginOpenMarginInputService)
                Services.getService(WEB3ToSuccMarginOpenMarginInputService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccMarginOpenInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(l_request,
                "�i�A���j�M�p����V�K�����̓T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3SuccMarginOpenInputResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccMarginOpenInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�i�A���j�M�p����V�K�����͕\�������̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccMarginOpenInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�i�A���j�M�p����V�K�����͕\�������̎��{�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
