head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualRecruitOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M��W�����n���h���N���X(WEB3RecruitOrderHandle)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/29 ���� (���u) �V�K�쐬
*/

package webbroker3.mf.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3MutualApplyCompleteRequest;
import webbroker3.mf.message.WEB3MutualApplyCompleteResponse;
import webbroker3.mf.message.WEB3MutualApplyConfirmRequest;
import webbroker3.mf.message.WEB3MutualApplyConfirmResponse;
import webbroker3.mf.service.delegate.WEB3MutualRecruitOrderService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

/**
 * ���M��W�����n���h���N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3MutualRecruitOrderHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualRecruitOrderHandler.class);
    
    /**
     * (��W�����R��)<BR>
     * �����M���̕�W�����R���������s��<BR>
     * ���M��W�����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���M��W�����m�F���N�G�X�g
     * @@return webbroker3.mf.message.WEB3MutualApplyConfirmResponse
     * @@roseuid 40555E0E00E9
     */
    public WEB3MutualApplyConfirmResponse RecruitOrderValidation(
        WEB3MutualApplyConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "RecruitOrderValidation(WEB3MutualApplyConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("the parametor method in null ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3MutualRecruitOrderService l_service = null;
        WEB3MutualApplyConfirmResponse l_response = null;
        try
        {
            l_service = 
                (WEB3MutualRecruitOrderService) Services.getService(
                    WEB3MutualRecruitOrderService.class);
        }
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3MutualApplyConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���M��W�����T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response = 
                (WEB3MutualApplyConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = 
                (WEB3MutualApplyConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                " ���M��W�����m�F�̎擾�Ɏ��s���܂���",
                l_ex.getErrorInfo(),
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (��W�����o�^)<BR>
     * �����M���̕�W�����o�^�������s��<BR>
     * ���M��W�����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���M��W�����������N�G�X�g
     * @@return webbroker3.mf.message.WEB3MutualApplyCompleteResponse
     * @@roseuid 40555E0E00E9
     */
    public WEB3MutualApplyCompleteResponse RecruitOrderRegister(
        WEB3MutualApplyCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "RecruitOrderRegister(WEB3MutualApplyCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("the parametor method in null ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3MutualRecruitOrderService l_service = null;
        WEB3MutualApplyCompleteResponse l_response = null;
        try
        {
            l_service = 
                (WEB3MutualRecruitOrderService) Services.getService(
                    WEB3MutualRecruitOrderService.class);
        }
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3MutualApplyCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���M��W�������̓T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response = 
                (WEB3MutualApplyCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = 
                (WEB3MutualApplyCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                " ���M��W���������̎擾�Ɏ��s���܂���",
                l_ex.getErrorInfo(),
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
