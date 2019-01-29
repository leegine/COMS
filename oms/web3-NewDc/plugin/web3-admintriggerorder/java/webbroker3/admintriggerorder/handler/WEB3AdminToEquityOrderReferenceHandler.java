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
filename	WEB3AdminToEquityOrderReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE���������Ɖ�T�[�r�X�n���h��(WEB3AdminToEquityOrderReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/06�@@������(���u) �V�K�쐬
*/

package webbroker3.admintriggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.admintriggerorder.message.WEB3AdminToEquityOrderRefInpRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToEquityOrderRefInpResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToEquityOrderRefRefRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToEquityOrderRefRefResponse;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToEquityOrderReferenceService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�g���K�[�����Ǘ��ҁE���������Ɖ�T�[�r�X�n���h��)<BR>
 * �g���K�[�����Ǘ��ҁE���������Ɖ�T�[�r�X�n���h��<BR>
 * 
 * @@author ������
 * @@version 1.0
 */
public class WEB3AdminToEquityOrderReferenceHandler implements MessageHandler 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToEquityOrderReferenceHandler.class);
    
    /**
     * �R���X�g���N�^<BR>
     */
    public WEB3AdminToEquityOrderReferenceHandler() 
    {
     
    }
    
    /**
     * (get���͉��)<BR>
     * ���������Ɖ���͉�ʕ\���������s���B<BR>
     * <BR>
     * �g���K�[�����Ǘ��ҁE���������Ɖ�T�[�r�XImpl���擾���A<BR> 
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (�g���K�[�����Ǘ��ҁE���������Ɖ���̓��N�G�X�g)<BR>
     * @@return WEB3AdminToEquityOrderRefInpResponse
     */
    public WEB3AdminToEquityOrderRefInpResponse getInputScreen(WEB3AdminToEquityOrderRefInpRequest l_request)
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminToIfoOrderRefInpRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminToEquityOrderRefInpResponse l_response = null;
        WEB3AdminToEquityOrderReferenceService l_service = null;
        
        try
        {
            //get�g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�T�[�r�X
            l_service = (WEB3AdminToEquityOrderReferenceService)
                Services.getService(WEB3AdminToEquityOrderReferenceService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminToEquityOrderRefInpResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�g���K�[�����Ǘ��ҁE���������Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B", 
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminToEquityOrderRefInpResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminToEquityOrderRefInpResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "���������Ɖ���͉�ʕ\�������̎��{�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminToEquityOrderRefInpResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "���������Ɖ���͉�ʕ\�������̎��{�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�Ɖ���)<BR>
     * ���������Ɖ�����s���B<BR> 
     * <BR>
     * �g���K�[�����Ǘ��ҁE���������Ɖ�T�[�r�XImpl���擾���A<BR> 
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (�g���K�[�����Ǘ��ҁE���������Ɖ�N�G�X�g)<BR>
     * @@return WEB3AdminToEquityOrderRefRefResponse
     */
    public WEB3AdminToEquityOrderRefRefResponse getReferenceScreen(WEB3AdminToEquityOrderRefRefRequest l_request) 
    {
        final String STR_METHOD_NAME = " getReferenceScreen(WEB3AdminToEquityOrderRefRefRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminToEquityOrderRefRefResponse l_response = null;
        WEB3AdminToEquityOrderReferenceService l_service = null;
        
        try
        {
            //get�g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�T�[�r�X
            l_service = (WEB3AdminToEquityOrderReferenceService)
                Services.getService(WEB3AdminToEquityOrderReferenceService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminToEquityOrderRefRefResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�g���K�[�����Ǘ��ҁE���������Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B", 
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminToEquityOrderRefRefResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminToEquityOrderRefRefResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "���������Ɖ��ʕ\�������̎��{�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminToEquityOrderRefRefResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "���������Ɖ��ʕ\�������̎��{�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
