head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.58.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p�������n���h��(WEB3ToSuccMarginCancelHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 ���@@�_�O (���u) �V�K�쐬 
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccMarginCancelCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCancelCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginCancelConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCancelConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginCancelService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���j�M�p�������n���h��)<BR>
 * �i�A���j�M�p�������n���h���N���X<BR>
 * 
 * @@author �@@���@@�_�O(���u)
 * @@version 1.0 
 */
public class WEB3ToSuccMarginCancelHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginCancelHandler.class);
    
    /**
     * @@roseuid 4369ED30034B
     */
    public WEB3ToSuccMarginCancelHandler() 
    {
     
    }
    
    /**
     * (confirm���)<BR>
     * �i�A���j�M�p���������������R�����s��<BR>
     * <BR>
     * �i�A���j�M�p�������T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �i�A���j�M�p�����������m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3SuccMarginCancelConfirmResponse
     * @@roseuid 433A2CC70000
     */
    public WEB3SuccMarginCancelConfirmResponse confirmCancel(WEB3SuccMarginCancelConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME =
            " confirmCancel(WEB3SuccMarginCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //�i�A���j�M�p�������T�[�r�X
        WEB3ToSuccMarginCancelService l_service = null;
        //�i�A���j�M�p�����������m�F���X�|���X�I�u�W�F�N�g
        WEB3SuccMarginCancelConfirmResponse l_response = null;
        
        try
        {
            l_service =
                (WEB3ToSuccMarginCancelService) Services.getService(
                    WEB3ToSuccMarginCancelService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3SuccMarginCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�i�A���j�M�p�������T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3SuccMarginCancelConfirmResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SuccMarginCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�i�A���j�M�p�����������m�F�����̎��{�Ɏ��s���܂����B", 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccMarginCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�i�A���j�M�p�����������m�F�����̎��{�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
    
    /**
     * (complete���)<BR>
     * �i�A���j�M�p�����������������s��<BR>
     * <BR>
     * �i�A���j�M�p�������T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �i�A���j�M�p�����������������N�G�X�g�I�u�W�F�N�g
     * @@return WEB3SuccMarginCancelCompleteResponse
     * @@roseuid 433A2D3202EE
     */
    public WEB3SuccMarginCancelCompleteResponse completeCancel(WEB3SuccMarginCancelCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME =
            " confirmCancel(WEB3SuccMarginCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //�i�A���j�M�p�������T�[�r�X
        WEB3ToSuccMarginCancelService l_service = null;
        //�i�A���j�M�p�����������������X�|���X�I�u�W�F�N�g
        WEB3SuccMarginCancelCompleteResponse l_response = null;
        
        try
        {
            l_service =
                (WEB3ToSuccMarginCancelService) Services.getService(
                    WEB3ToSuccMarginCancelService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3SuccMarginCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�i�A���j�M�p�������T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3SuccMarginCancelCompleteResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SuccMarginCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�i�A���j�M�p�������������������̎��{�Ɏ��s���܂����B", 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccMarginCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�i�A���j�M�p�������������������̎��{�Ɏ��s���܂����B",
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
