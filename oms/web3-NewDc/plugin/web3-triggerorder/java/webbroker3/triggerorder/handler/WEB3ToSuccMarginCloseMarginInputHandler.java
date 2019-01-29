head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.59.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginCloseMarginInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p����ԍϓ��̓n���h���N���X(WEB3ToSuccMarginCloseMarginInputHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/16�@@�A����(���u) �V�K�쐬
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseInputRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginCloseMarginInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�i�A���j�M�p����ԍϓ��̓n���h��)<BR>
 * �i�A���j�M�p����ԍϓ��̓n���h���N���X<BR>
 * 
 * @@author �A����
 * @@version 1.0 
 */
public class WEB3ToSuccMarginCloseMarginInputHandler implements MessageHandler 
{
    
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginCloseMarginInputHandler.class);

    /**
     * @@roseuid 4369ED350196
     */
    public WEB3ToSuccMarginCloseMarginInputHandler() 
    {
     
    }
    
    /**
     * (get�ԍϓ��͉��)<BR>
     * �i�A���j�M�p����ԍϓ��͉�ʕ\���������s���B<BR>
     * <BR>
     * �i�A���j�M�p����ԍϓ��̓T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �i�A���j�M�p����ԍϒ������̓��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3SuccMarginCloseInputResponse
     * @@roseuid 43294821002D
     */
    public WEB3SuccMarginCloseInputResponse getCloseMarginChangeInputScreen(WEB3SuccMarginCloseInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " getCloseMarginChangeInputScreen(WEB3SuccMarginCloseInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccMarginCloseInputResponse l_response = null;
        WEB3ToSuccMarginCloseMarginInputService l_service = null;
        
        try
        {            
            //get�i�A���j�M�p����ԍϓ��̓T�[�r�X
            l_service = (WEB3ToSuccMarginCloseMarginInputService)
                Services.getService(WEB3ToSuccMarginCloseMarginInputService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccMarginCloseInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(l_request, "�i�A���j�M�p����ԍϓ��̓T�[�r�X�̎擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3SuccMarginCloseInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccMarginCloseInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�i�A���j�M�p����ԍϓ��͉�ʕ\�������̎��{�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccMarginCloseInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�i�A���j�M�p����ԍϓ��͉�ʕ\�������̎��{�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
