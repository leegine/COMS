head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.09.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoConditionChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҕ\���ݒ�ύX�n���h��(WEB3AdminPvInfoConditionChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2004/10/27 ������(���u) �쐬
*/
package webbroker3.pvinfo.handler;

import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeCompleteRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeCompleteResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeConfirmRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeConfirmResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeInputRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeInputResponse;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoConditionChangeService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҕ\���ݒ�ύX�n���h��)<BR>
 * �Ǘ��ҕ\���ݒ�ύX�n���h���N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3AdminPvInfoConditionChangeHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoConditionChangeHandler.class);
    
    /**
     * (get�\���ݒ�ύX���͉��)<BR>
     * �\���ݒ�ύX���͉�ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��ҕ\���ݒ�ύX�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�\���ݒ�ύX���̓��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeInputResponse
     * @@roseuid 415D11E602DF
     */
    public WEB3AdminPvInfoConditionChangeInputResponse getConditionChangeInputScreen(WEB3AdminPvInfoConditionChangeInputRequest l_request)
    {
        final String STR_METHOD_NAME = " getConditionChangeInputScreen(WEB3AdminPvInfoConditionChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoConditionChangeInputResponse l_response = null;
        WEB3AdminPvInfoConditionChangeService l_service = null;
        
        //�Ǘ��ҕ\���ݒ�ύX�T�[�r�XImpl���擾��
        try
        {
            l_service = (WEB3AdminPvInfoConditionChangeService)Services.getService(WEB3AdminPvInfoConditionChangeService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�Ǘ��ҕ\���ݒ�ύX�T�[�r�XImpl���擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //execute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3AdminPvInfoConditionChangeInputResponse)l_service.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (confirm�\���ݒ�ύX)<BR>
     * �\���ݒ�ύX�m�F�������s���B<BR>
     * <BR>
     * �Ǘ��ҕ\���ݒ�ύX�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�\���ݒ�ύX�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeConfirmResponse
     * @@roseuid 415D129203AA
     */
    public WEB3AdminPvInfoConditionChangeConfirmResponse confirmConditionChange(WEB3AdminPvInfoConditionChangeConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = " confirmConditionChange(WEB3AdminPvInfoConditionChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoConditionChangeConfirmResponse l_response = null;
        WEB3AdminPvInfoConditionChangeService l_service = null;
        
        //�Ǘ��ҕ\���ݒ�ύX�T�[�r�X���擾��
        try
        {
            l_service = (WEB3AdminPvInfoConditionChangeService)Services.getService(WEB3AdminPvInfoConditionChangeService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�Ǘ��ҕ\���ݒ�ύX�T�[�r�X���擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //execute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3AdminPvInfoConditionChangeConfirmResponse)l_service.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (complete�\���ݒ�ύX)<BR>
     * �\���ݒ�ύX�����������s���B<BR>
     * <BR>
     * �Ǘ��ҕ\���ݒ�ύX�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�\���ݒ�ύX�������N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeCompleteResponse
     * @@roseuid 415D12E302DF
     */
    public WEB3AdminPvInfoConditionChangeCompleteResponse completeConditionChange(WEB3AdminPvInfoConditionChangeCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = " completeConditionChange(WEB3AdminPvInfoConditionChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoConditionChangeCompleteResponse l_response = null;
        WEB3AdminPvInfoConditionChangeService l_service = null;
        
        //�Ǘ��ҕ\���ݒ�ύX�T�[�r�X���擾��
        try
        {
            l_service = (WEB3AdminPvInfoConditionChangeService)Services.getService(WEB3AdminPvInfoConditionChangeService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�Ǘ��ҕ\���ݒ�ύX�T�[�r�X���擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //execute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3AdminPvInfoConditionChangeCompleteResponse)l_service.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
