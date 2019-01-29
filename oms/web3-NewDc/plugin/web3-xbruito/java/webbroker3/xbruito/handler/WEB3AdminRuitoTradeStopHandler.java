head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminRuitoTradeStopHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��җݓ������ʔ�����~�n���h�� (WEB3AdminRuitoTradeStopHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/06 ��O�� (���u) �V�K�쐬
*/
package webbroker3.xbruito.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopCompleteRequest;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopCompleteResponse;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopConfirmRequest;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopConfirmResponse;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopInputRequest;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopInputResponse;
import webbroker3.xbruito.service.delegate.WEB3AdminRuitoTradeStopService;

/**
 * (�Ǘ��җݓ������ʔ�����~�n���h��)<BR>
 * �Ǘ��җݓ������ʔ�����~�n���h���N���X
 */
public class WEB3AdminRuitoTradeStopHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminRuitoTradeStopHandler.class);
    
    /**
     * (get���͉��)<BR>
     * �ݓ������ʔ�����~���͉�ʎ擾���������{����B<BR>
     * �Ǘ��җݓ������ʔ�����~�T�[�r�X���擾���āAexecute()���\�b�h���R�[������B<BR>  
     * @@param l_request - �ݓ������ʔ�����~���͉�ʃ��N�G�X�g
     * @@return webbroker3.xbruito.message.WEB3AdminRuitoTradeStopInputResponse
     * @@roseuid 4058292B039C
     */
    public WEB3AdminRuitoTradeStopInputResponse handleGetInputScreen(
            WEB3AdminRuitoTradeStopInputRequest l_request)
    {
        String STR_METHOD_NAME = "handleGetInputScreen(" +
            "WEB3AdminRuitoTradeStopInputRequest l_request)";
        log.entering(STR_METHOD_NAME);       
        if (l_request == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }
    
        //�Ǘ��җݓ������ʔ�����~�T�[�r�X���擾��
        WEB3AdminRuitoTradeStopService l_service = null;
        WEB3AdminRuitoTradeStopInputResponse l_response = null;
        try
        {
            l_service = (WEB3AdminRuitoTradeStopService) 
                Services.getService(WEB3AdminRuitoTradeStopService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminRuitoTradeStopInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��җݓ������ʔ�����~�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminRuitoTradeStopInputResponse) 
                l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminRuitoTradeStopInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                      "�Ǘ��җݓ������ʔ�����~���͉�ʎ擾���������s���܂����B", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (confirm�����ʔ�����~)<BR>
     * �ݓ������ʔ�����~�m�F���������{����B<BR>
     * �Ǘ��җݓ������ʔ�����~�T�[�r�X���擾���āAexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �ݓ������ʔ�����~�m�F���N�G�X�g
     * @@return webbroker3.xbruito.message.WEB3AdminRuitoTradeStopConfirmResponse
     * @@roseuid 4058292B039C
     */
    public WEB3AdminRuitoTradeStopConfirmResponse handleComfirmTradeStop(
            WEB3AdminRuitoTradeStopConfirmRequest l_request)
    {
        String STR_METHOD_NAME = "handleComfirmTradeStop(" +
            "WEB3AdminRuitoTradeStopConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);       
    
        //�Ǘ��җݓ������ʔ�����~�T�[�r�X���擾��
        WEB3AdminRuitoTradeStopService l_service = null;
        WEB3AdminRuitoTradeStopConfirmResponse l_response = null;
        try
        {
            l_service = (WEB3AdminRuitoTradeStopService) 
                Services.getService(WEB3AdminRuitoTradeStopService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminRuitoTradeStopConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��җݓ������ʔ�����~�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminRuitoTradeStopConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminRuitoTradeStopConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��җݓ������ʔ�����~�m�F���������s���܂����B", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (complete�����ʔ�����~)<BR>
     * �ݓ������ʔ�����~�������������{����B<BR>
     * �Ǘ��җݓ������ʔ�����~�T�[�r�X���擾���āAexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �ݓ������ʔ�����~�������N�G�X�g
     * @@return webbroker3.WEB3AdminRuitoTradeStopCompleteResponse
     * @@roseuid 4058292B039C
     */
    public WEB3AdminRuitoTradeStopCompleteResponse handleCompleteTradeStop(
            WEB3AdminRuitoTradeStopCompleteRequest l_request)
    {
        String STR_METHOD_NAME = "handleCompleteTradeStop(" +
        "WEB3AdminRuitoTradeStopCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
    
        //�Ǘ��җݓ������ʔ�����~�T�[�r�X���擾
        WEB3AdminRuitoTradeStopService l_service = null;
        WEB3AdminRuitoTradeStopCompleteResponse l_response = null;
    
        try
        {
            l_service = (WEB3AdminRuitoTradeStopService) 
                Services.getService(WEB3AdminRuitoTradeStopService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminRuitoTradeStopCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��җݓ������ʔ�����~�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
    
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminRuitoTradeStopCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminRuitoTradeStopCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "�ݓ������ʔ�����~�o�^�����s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}@
