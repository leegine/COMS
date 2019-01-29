head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.00.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginChangeOpenMarginInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p��������V�K�����̓n���h��(WEB3ToSuccMarginChangeOpenMarginInputHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/17�@@���@@��(���u) �V�K�쐬
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenChangeInputRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenChangeInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginChangeOpenMarginInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���j�M�p��������V�K�����̓n���h��)<BR>
 * �i�A���j�M�p��������V�K�����̓n���h���N���X<BR>
 * 
 * @@author  ���@@��(���u)
 * @@version 1.0
 */
public class WEB3ToSuccMarginChangeOpenMarginInputHandler implements MessageHandler 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginChangeOpenMarginInputHandler.class);
    
    /**
     * @@roseuid 4369ED33009C
     */
    public WEB3ToSuccMarginChangeOpenMarginInputHandler() 
    {
     
    }
    
    /**
     * (get�����V�K�����͉��)<BR>
     * �i�A���j�M�p��������V�K���̓��͉�ʕ\���������s���B<BR>
     * <BR>
     * �i�A���j�M�p��������V�K�����̓T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3SuccMarginOpenChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 433B841A0073
     */
    public WEB3SuccMarginOpenChangeInputResponse getOpenMarginChangeInputScreen(
        WEB3SuccMarginOpenChangeInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getOpenMarginChangeInputScreen(WEB3SuccMarginOpenChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3SuccMarginOpenChangeInputResponse l_response = null;
        WEB3ToSuccMarginChangeOpenMarginInputService l_service = null;
        
        try
        {            
            //�i�A���j�M�p��������V�K�����̓T�[�r�X���擾��
            l_service = (WEB3ToSuccMarginChangeOpenMarginInputService)
                Services.getService(WEB3ToSuccMarginChangeOpenMarginInputService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccMarginOpenChangeInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(l_request,
                "�i�A���j�M�p��������V�K�����̓T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3SuccMarginOpenChangeInputResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccMarginOpenChangeInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�i�A���j�M�p��������V�K���̓��͉�ʕ\�������̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccMarginOpenChangeInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�i�A���j�M�p��������V�K���̓��͉�ʕ\�������̎��{�Ɏ��s���܂����B",
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
