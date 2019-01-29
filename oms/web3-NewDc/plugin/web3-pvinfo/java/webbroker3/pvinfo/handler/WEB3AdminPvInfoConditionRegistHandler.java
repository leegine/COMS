head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.09.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoConditionRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҕ\���ݒ�o�^�n���h��(WEB3AdminPvInfoConditionRegistHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2004/10/26 ���O�B(���u) �쐬
*/
package webbroker3.pvinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistCompleteRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistCompleteResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistConfirmRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistConfirmResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistInputRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistInputResponse;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoConditionRegistService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҕ\���ݒ�o�^�n���h��)<BR>
 * �Ǘ��ҕ\���ݒ�o�^�n���h���N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3AdminPvInfoConditionRegistHandler implements MessageHandler
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoConditionRegistHandler.class);

    /**
     * (get�\���ݒ�o�^���͉��)<BR>
     * �\���ݒ�o�^���͉�ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��ҕ\���ݒ�o�^�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�\���ݒ�o�^���̓��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistInputResponse
     * @@roseuid 415BFC8A01D3
     */
    public WEB3AdminPvInfoConditionRegistInputResponse getConditionRegistInputScreen(WEB3AdminPvInfoConditionRegistInputRequest l_request)
    {
        final String STR_METHOD_NAME = " getConditionRegistInputScreen(WEB3AdminPvInfoConditionRegistInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoConditionRegistInputResponse l_response = null;
        WEB3AdminPvInfoConditionRegistService l_service = null;
        
        //�Ǘ��ҕ\���ݒ�o�^�T�[�r�X���擾��
        try
        {
            l_service = (WEB3AdminPvInfoConditionRegistService)Services.getService(
                WEB3AdminPvInfoConditionRegistService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҕ\���ݒ�o�^�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;    
        }
        
        //�Ǘ��ҕ\���ݒ�o�^�T�[�r�X.execute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3AdminPvInfoConditionRegistInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminPvInfoConditionRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��ҕ\���ݒ�o�^�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;        
    }

    /**
     * (confirm�\���ݒ�o�^)<BR>
     * �\���ݒ�o�^�m�F�������s���B<BR>
     * <BR>
     * �Ǘ��ҕ\���ݒ�o�^�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�\���ݒ�o�^�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistConfirmResponse
     * @@roseuid 415BF93A0058
     */
    public WEB3AdminPvInfoConditionRegistConfirmResponse confirmConditionRegist(WEB3AdminPvInfoConditionRegistConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = " confirmConditionRegist(WEB3AdminPvInfoConditionRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoConditionRegistConfirmResponse l_response = null;
        WEB3AdminPvInfoConditionRegistService l_service = null;
        
        //�Ǘ��ҕ\���ݒ�o�^�T�[�r�X���擾��
        try
        {
            l_service = (WEB3AdminPvInfoConditionRegistService)Services.getService(
                WEB3AdminPvInfoConditionRegistService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҕ\���ݒ�o�^�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;    
        }
        
        //�Ǘ��ҕ\���ݒ�o�^�T�[�r�X.execute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3AdminPvInfoConditionRegistConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminPvInfoConditionRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��ҕ\���ݒ�o�^�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }

    /**
     * (complete�\���ݒ�o�^)<BR>
     * �\���ݒ�o�^�����������s���B<BR>
     * <BR>
     * �Ǘ��ҕ\���ݒ�o�^�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�\���ݒ�o�^�������N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistCompleteResponse
     * @@roseuid 415BF9DF03B1
     */
    public WEB3AdminPvInfoConditionRegistCompleteResponse completeConditionRegist(WEB3AdminPvInfoConditionRegistCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = " completeConditionRegist(WEB3AdminPvInfoConditionRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoConditionRegistCompleteResponse l_response = null;
        WEB3AdminPvInfoConditionRegistService l_service = null;
        
        //�Ǘ��ҕ\���ݒ�o�^�T�[�r�X���擾��
        try
        {
            l_service = (WEB3AdminPvInfoConditionRegistService)Services.getService(
                WEB3AdminPvInfoConditionRegistService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҕ\���ݒ�o�^�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;    
        }
        
        //�Ǘ��ҕ\���ݒ�o�^�T�[�r�X.execute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3AdminPvInfoConditionRegistCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminPvInfoConditionRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��ҕ\���ݒ�o�^�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
