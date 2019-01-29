head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.09.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoConditionDelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҕ\���ݒ�폜�n���h��(WEB3AdminPvInfoConditionDelHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2004/10/27 ������(���u) �쐬
*/
package webbroker3.pvinfo.handler;

import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDelCompleteRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDelCompleteResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDelConfirmRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDelConfirmResponse;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoConditionDelService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҕ\���ݒ�폜�n���h��)<BR>
 * �Ǘ��ҕ\���ݒ�폜�n���h���N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3AdminPvInfoConditionDelHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoConditionDelHandler.class);
    
    /**
     * (confirm�\���ݒ�폜)<BR>
     * �\���ݒ�폜�m�F�������s���B<BR>
     * <BR>
     * �Ǘ��ҕ\���ݒ�폜�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�\���ݒ�폜�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDelConfirmResponse
     * @@roseuid 415D299F006E
     */
    public WEB3AdminPvInfoConditionDelConfirmResponse confirmConditionDel(WEB3AdminPvInfoConditionDelConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = " confirmConditionDel(WEB3AdminPvInfoConditionDelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoConditionDelConfirmResponse l_response = null;
        WEB3AdminPvInfoConditionDelService l_service = null;
        
        //�Ǘ��ҕ\���ݒ�폜�T�[�r�X���擾��
        try
        {
            l_service = (WEB3AdminPvInfoConditionDelService)Services.getService(WEB3AdminPvInfoConditionDelService.class);           
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionDelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�Ǘ��ҕ\���ݒ�폜�T�[�r�X���擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        //execute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3AdminPvInfoConditionDelConfirmResponse)l_service.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionDelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (complete�\���ݒ�폜)<BR>
     * �\���ݒ�폜�����������s���B<BR>
     * <BR>
     * �Ǘ��ҕ\���ݒ�폜�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�\���ݒ�폜�������N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDelCompleteResponse
     * @@roseuid 415D29EF0252
     */
    public WEB3AdminPvInfoConditionDelCompleteResponse completeConditionDel(WEB3AdminPvInfoConditionDelCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = " completeConditionDel(WEB3AdminPvInfoConditionDelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoConditionDelCompleteResponse l_response = null;
        WEB3AdminPvInfoConditionDelService l_service = null;
        
        //�Ǘ��ҕ\���ݒ�폜�T�[�r�X���擾���A
        try
        {
            l_service = (WEB3AdminPvInfoConditionDelService)Services.getService(WEB3AdminPvInfoConditionDelService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionDelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�Ǘ��ҕ\���ݒ�폜�T�[�r�X���擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //execute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3AdminPvInfoConditionDelCompleteResponse)l_service.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionDelCompleteResponse)l_request.createResponse();
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
