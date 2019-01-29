head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.58.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginChangeCloseMarginInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p��������ԍϓ��̓n���h��(WEB3ToSuccMarginChangeCloseMarginInputHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/16 杊��](���u) �V�K�쐬
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseChangeInputRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseChangeInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginChangeCloseMarginInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�i�A���j�M�p��������ԍϓ��̓n���h��)<BR>
 * �i�A���j�M�p��������ԍϓ��̓n���h���N���X<BR>
 *   
 * @@author 杊��]
 * @@version 1.0
 */
public class WEB3ToSuccMarginChangeCloseMarginInputHandler implements MessageHandler 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginChangeCloseMarginInputHandler.class);

    /**
     * @@roseuid 4369ED340128
     */
    public WEB3ToSuccMarginChangeCloseMarginInputHandler() 
    {
     
    }
    
    /**
     * (get�����ԍϓ��͉��)<BR>
     * �M�p��������ԍς̓��͉�ʕ\���������s���B<BR>
     * <BR>
     * �i�A���j�M�p��������ԍϓ��̓T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3SuccMarginCloseChangeInputResponse
     * @@roseuid 433BBB5F01DB
     */
    public WEB3SuccMarginCloseChangeInputResponse getCloseMarginChangeInputScreen(
        WEB3SuccMarginCloseChangeInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " getCloseMarginChangeInputScreen(WEB3SuccMarginCloseChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccMarginCloseChangeInputResponse l_response = null;
        WEB3ToSuccMarginChangeCloseMarginInputService l_service = null;
        
        try
        {            
            //get�i�A���j�M�p��������ԍϓ��̓T�[�r�X
            l_service = (WEB3ToSuccMarginChangeCloseMarginInputService)
                Services.getService(WEB3ToSuccMarginChangeCloseMarginInputService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccMarginCloseChangeInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(l_request,
                "�i�A���j�M�p��������ԍϓ��̓T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute
            l_response = (WEB3SuccMarginCloseChangeInputResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccMarginCloseChangeInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�i�A���j�M�p��������ԍϓ��͕\�������̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccMarginCloseChangeInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�i�A���j�M�p��������ԍϓ��͕\�������̎��{�Ɏ��s���܂����B",
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
