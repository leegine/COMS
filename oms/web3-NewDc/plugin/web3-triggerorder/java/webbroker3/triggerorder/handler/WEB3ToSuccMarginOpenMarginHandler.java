head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.00.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginOpenMarginHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p����V�K���n���h��(WEB3ToSuccMarginOpenMarginHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08�@@���@@��(���u) �V�K�쐬
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginOpenMarginService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�i�A���j�M�p����V�K���n���h��)<BR>
 * �i�A���j�M�p����V�K���n���h���N���X<BR>
 * 
 * @@author ���@@��(���u)
 * @@version 1.0
 */
public class WEB3ToSuccMarginOpenMarginHandler implements MessageHandler 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginOpenMarginHandler.class);

    /**
     * @@roseuid 4369ED3101A5
     */
    public WEB3ToSuccMarginOpenMarginHandler() 
    {
     
    }
    
    /**
     * (confirm����)<BR>
     * �i�A���j�M�p����V�K�������R�����s���B<BR>
     * <BR>
     * �i�A���j�M�p����V�K���T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �i�A���j�M�p����V�K�������m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3SuccMarginOpenConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 432F77550152
     */
    public WEB3SuccMarginOpenConfirmResponse confirmOrder(
        WEB3SuccMarginOpenConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = " confirmOrder(WEB3SuccMarginOpenConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3SuccMarginOpenConfirmResponse l_response = null;
        WEB3ToSuccMarginOpenMarginService l_service = null;

        try
        {            
            //�i�A���j�M�p����V�K���T�[�r�XImpl���擾��
            l_service =
                (WEB3ToSuccMarginOpenMarginService) Services.getService(WEB3ToSuccMarginOpenMarginService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccMarginOpenConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request,
                "�i�A���j�M�p����V�K���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3SuccMarginOpenConfirmResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccMarginOpenConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
            {
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.error(
                l_request, 
                "�i�A���j�M�p����V�K�������R���̎��{�Ɏ��s���܂����B",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccMarginOpenConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�i�A���j�M�p����V�K�������R���̎��{�Ɏ��s���܂����B",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete����)<BR>
     * �i�A���j�M�p����V�K�������o�^���s���B<BR>
     * <BR>
     * �i�A���j�M�p����V�K���T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �i�A���j�M�p����V�K�������������N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3SuccMarginOpenCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 432F77AD01B0
     */
    public WEB3SuccMarginOpenCompleteResponse completeOrder(
        WEB3SuccMarginOpenCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " completeOrder(WEB3SuccMarginOpenCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3SuccMarginOpenCompleteResponse l_response = null;
        WEB3ToSuccMarginOpenMarginService l_service = null;

        try
        {            
            //�i�A���j�M�p����V�K���T�[�r�XImpl���擾��
            l_service =
                (WEB3ToSuccMarginOpenMarginService) Services.getService(WEB3ToSuccMarginOpenMarginService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_e)
        {
            l_response = (WEB3SuccMarginOpenCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request,
                "�i�A���j�M�p����V�K���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3SuccMarginOpenCompleteResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccMarginOpenCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
            {
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.error(
                l_request, 
                "�i�A���j�M�p����V�K�������o�^�̎��{�Ɏ��s���܂����B",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccMarginOpenCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�i�A���j�M�p����V�K�������o�^�̎��{�Ɏ��s���܂����B",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
