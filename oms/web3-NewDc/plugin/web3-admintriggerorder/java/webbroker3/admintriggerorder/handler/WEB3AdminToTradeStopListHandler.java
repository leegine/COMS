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
filename	WEB3AdminToTradeStopListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�戵��~�ꗗ�n���h��(WEB3AdminToTradeStopListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04�@@�A����(���u) �V�K�쐬
*/
package webbroker3.admintriggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopListRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopListResponse;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToTradeStopListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�g���K�[�����Ǘ��ҁE�戵��~�ꗗ�n���h��)<BR>
 * �g���K�[�����Ǘ��ҁE�戵��~�ꗗ�n���h���N���X<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public class WEB3AdminToTradeStopListHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminToTradeStopListHandler.class);
   
    /**
     * @@roseuid 4430D9A0000F
     */
    public WEB3AdminToTradeStopListHandler() 
    {
     
    }
    
    /**
     * (get�ꗗ���)<BR>
     * �戵��~�ꗗ�������s���B<BR>
     * <BR>
     * �g���K�[�����Ǘ��ҁE�戵��~�ꗗ�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE�戵��~�ꗗ���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminToTradeStopListResponse
     * @@roseuid 44101227004F
     */
    public WEB3AdminToTradeStopListResponse getListScreen(WEB3AdminToTradeStopListRequest l_request) 
    {
        final String STR_METHOD_NAME = " getListScreen(WEB3AdminToTradeStopListRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminToTradeStopListResponse l_response = null;
        WEB3AdminToTradeStopListService l_service = null;
        
        try
        {            
            //get�g���K�[�����Ǘ��ҁE�戵��~�ꗗ�T�[�r�X
            l_service = (WEB3AdminToTradeStopListService) Services.getService(WEB3AdminToTradeStopListService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminToTradeStopListResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�g���K�[�����Ǘ��ҁE�戵��~�ꗗ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminToTradeStopListResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminToTradeStopListResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(�g���K�[�����Ǘ��ҁE�戵��~�ꗗ)�戵��~�ꗗ�����̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminToTradeStopListResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(�g���K�[�����Ǘ��ҁE�戵��~�ꗗ)�戵��~�ꗗ�����̎��{�Ɏ��s���܂����B",
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
