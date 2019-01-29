head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToTradeStopInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�戵��~���̓n���h��(WEB3AdminToTradeStopInputHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04�@@�]�V�q(���u) �V�K�쐬
*/

package webbroker3.admintriggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopInputRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopInputResponse;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToTradeStopInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�g���K�[�����Ǘ��ҁE�戵��~���̓n���h��)<BR>
 * �g���K�[�����Ǘ��ҁE�戵��~���̓n���h���N���X<BR>
 * 
 * @@author �]�V�q
 * @@version 1.0
 */
public class WEB3AdminToTradeStopInputHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminToTradeStopInputHandler.class);
    
    /**
     * @@roseuid 4430DD36008C
     */
    public WEB3AdminToTradeStopInputHandler() 
    {
     
    }
    
    /**
     * (get���͉��)<BR>
     * �戵��~���͉�ʕ\���������s���B<BR>
     * <BR>
     * �g���K�[�����Ǘ��ҁE�戵��~���̓T�[�r�XImpl��<BR>
     * �擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE�戵��~���̓��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminToTradeStopInputResponse
     * @@roseuid 44192D370261
     */
    public WEB3AdminToTradeStopInputResponse getInputScreen(WEB3AdminToTradeStopInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminToTradeStopInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminToTradeStopInputResponse l_response = null;
        WEB3AdminToTradeStopInputService l_service = null;
        
        try
        {            
            //get�g���K�[�����Ǘ��ҁE�戵��~���̓T�[�r�X
            l_service = (WEB3AdminToTradeStopInputService) Services.getService(WEB3AdminToTradeStopInputService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminToTradeStopInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�g���K�[�����Ǘ��ҁE�戵��~���̓T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminToTradeStopInputResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminToTradeStopInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(�g���K�[�����Ǘ���)�戵��~���͉�ʕ\�������̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminToTradeStopInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(�g���K�[�����Ǘ���)�戵��~���͉�ʕ\�������̎��{�Ɏ��s���܂����B",
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
