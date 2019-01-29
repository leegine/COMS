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
filename	WEB3AdminToManualExpireMainHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�蓮�������C���n���h��(WEB3AdminToManualExpireMainHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/17�@@�]�V�q(���u) �V�K�쐬
*/

package webbroker3.admintriggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseMainRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseMainResponse;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToManualExpireMainService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�g���K�[�����Ǘ��ҁE�蓮�������C���n���h��)<BR>
 * �g���K�[�����Ǘ��ҁE�蓮�������C���n���h���N���X<BR>
 * 
 * @@author �]�V�q
 * @@version 1.0
 */
public class WEB3AdminToManualExpireMainHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToManualExpireMainHandler.class);
    
    /**
     * @@roseuid 4419312B0290
     */
    public WEB3AdminToManualExpireMainHandler() 
    {
     
    }
    
    /**
     * (�蓮�������N�G�X�g)<BR>
     * �i�񓯊��j�蓮�����������N������B<BR>
     * <BR>
     * �g���K�[�����Ǘ��ҁE�蓮�������C���T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE�蓮�������C�����N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminToManualLapseMainResponse
     * @@roseuid 440BCE6B0066
     */
    public WEB3AdminToManualLapseMainResponse manualExpireRequest(WEB3AdminToManualLapseMainRequest l_request) 
    {
        final String STR_METHOD_NAME = " manualExpireRequest(WEB3AdminToManualLapseMainRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminToManualLapseMainResponse l_response = null;
        WEB3AdminToManualExpireMainService l_service = null;
        
        try
        {
            //get�g���K�[�����Ǘ��ҁE�蓮�������C���T�[�r�X
            l_service = (WEB3AdminToManualExpireMainService)
                Services.getService(WEB3AdminToManualExpireMainService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminToManualLapseMainResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�g���K�[�����Ǘ��ҁE�蓮�������C���T�[�r�X�̎擾�Ɏ��s���܂����B", 
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminToManualLapseMainResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminToManualLapseMainResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�i�񓯊��j�蓮���������̋N���Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminToManualLapseMainResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�i�񓯊��j�蓮���������̋N���Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
