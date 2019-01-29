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
filename	WEB3AdminToIfoOrderReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�n���h��(WEB3AdminToIfoOrderReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/15�@@�]�V�q(���u) �V�K�쐬
*/

package webbroker3.admintriggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefInpRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefInpResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefRefRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefRefResponse;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToIfoOrderReferenceService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�n���h��)<BR>
 * �g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�n���h���N���X<BR>
 * 
 * @@author �]�V�q
 * @@version 1.0
 */
public class WEB3AdminToIfoOrderReferenceHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToIfoOrderReferenceHandler.class);

    /**
     * �R���X�g���N�^<BR>
     * @@roseuid 43F1BBF800EA
     */
    public WEB3AdminToIfoOrderReferenceHandler() 
    {
     
    }
    
    /**
     * (get���͉��)<BR>
     * �敨OP�����Ɖ���͉�ʕ\���������s���B<BR> 
     * <BR>
     * �g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�T�[�r�XImpl���擾���A<BR> 
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (�g���K�[�����Ǘ��ҁE�敨OP�����Ɖ���̓��N�G�X�g)<BR>
     * �g���K�[�����Ǘ��ҁE�敨OP�����Ɖ���̓��N�G�X�g�N���X<BR>
     * @@return WEB3AdminToIfoOrderRefInpResponse
     * @@roseuid 43E3540D0299
     */
    public WEB3AdminToIfoOrderRefInpResponse getInputScreen(WEB3AdminToIfoOrderRefInpRequest l_request)
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminToIfoOrderRefInpRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminToIfoOrderRefInpResponse l_response = null;
        WEB3AdminToIfoOrderReferenceService l_service = null;
        
        try
        {
            //get�g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�T�[�r�X
            l_service = (WEB3AdminToIfoOrderReferenceService)
                Services.getService(WEB3AdminToIfoOrderReferenceService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminToIfoOrderRefInpResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B", 
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminToIfoOrderRefInpResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminToIfoOrderRefInpResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�敨OP�����Ɖ���͉�ʕ\�������̎��{�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminToIfoOrderRefInpResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�敨OP�����Ɖ���͉�ʕ\�������̎��{�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�Ɖ���)<BR>
     * �敨OP�����Ɖ�����s���B<BR> 
     * <BR>
     * �g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�T�[�r�XImpl���擾���A<BR> 
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (�g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�N�G�X�g)<BR>
     * �g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�N�G�X�g�N���X<BR>
     * @@return WEB3AdminToIfoOrderRefRefResponse
     * @@roseuid 43E354A90008
     */
    public WEB3AdminToIfoOrderRefRefResponse getReferenceScreen(WEB3AdminToIfoOrderRefRefRequest l_request) 
    {
        final String STR_METHOD_NAME = " getReferenceScreen(WEB3AdminToIfoOrderRefRefRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminToIfoOrderRefRefResponse l_response = null;
        WEB3AdminToIfoOrderReferenceService l_service = null;
        
        try
        {
            //get�g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�T�[�r�X
            l_service = (WEB3AdminToIfoOrderReferenceService)
                Services.getService(WEB3AdminToIfoOrderReferenceService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminToIfoOrderRefRefResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "�g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B", 
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminToIfoOrderRefRefResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminToIfoOrderRefRefResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�敨OP�����Ɖ���̎��{�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminToIfoOrderRefRefResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�敨OP�����Ɖ���̎��{�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
