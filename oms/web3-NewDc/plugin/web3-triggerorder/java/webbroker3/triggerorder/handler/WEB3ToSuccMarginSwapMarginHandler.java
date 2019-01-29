head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.59.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginSwapMarginHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p����������n�n���h��(WEB3ToSuccMarginSwapMarginHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 ���(���u) �V�K�쐬
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccMarginSwapCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginSwapCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginSwapConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginSwapConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginSwapMarginService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���j�M�p����������n�n���h��)<BR>
 * �i�A���j�M�p����������n�n���h���N���X�B<BR>
 * 
 * @@author ���
 * @@version 1.0
 */
public class WEB3ToSuccMarginSwapMarginHandler implements MessageHandler 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginSwapMarginHandler.class);
    
    /**
     * @@roseuid 4369ED2F0290
     */
    public WEB3ToSuccMarginSwapMarginHandler() 
    {
     
    }
    
    /**
     * (confirm����)<BR>
     * �i�A���j�M�p����������n�����R�����s���B<BR>
     * <BR>
     * �i�A���j�M�p����������n�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �i�A���j�M�p����������n�����m�F���N�G�X�g�I�u�W�F�N�g�B<BR>
     * @@return WEB3SuccMarginSwapConfirmResponse
     * @@roseuid 43436A0E0292
     */
    public WEB3SuccMarginSwapConfirmResponse confirmOrder(WEB3SuccMarginSwapConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmOrder(WEB3SuccMarginSwapConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccMarginSwapConfirmResponse l_response = null;
        WEB3ToSuccMarginSwapMarginService l_service = null;

        try
        {            
            //�i�A���j�M�p����������n�T�[�r�XImpl���擾��
            l_service =
                (WEB3ToSuccMarginSwapMarginService) Services.getService(WEB3ToSuccMarginSwapMarginService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_e)
        {
            l_response = (WEB3SuccMarginSwapConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request,
                "�i�A���j�M�p����������n�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3SuccMarginSwapConfirmResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccMarginSwapConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�i�A���j�M�p����������n�����R���̎��{�Ɏ��s���܂����B",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccMarginSwapConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�i�A���j�M�p����������n�����R���̎��{�Ɏ��s���܂����B",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete����)<BR>
     * �i�A���j�M�p����������n�����o�^���s���B<BR>
     * <BR>
     * �i�A���j�M�p����������n�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �i�A���j�M�p����������n�����������N�G�X�g�I�u�W�F�N�g�B<BR>
     * @@return WEB3SuccMarginSwapCompleteResponse
     * @@roseuid 43436A0E02A2
     */
    public WEB3SuccMarginSwapCompleteResponse completeOrder(WEB3SuccMarginSwapCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " completeOrder(WEB3SuccMarginSwapCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccMarginSwapCompleteResponse l_response = null;
        WEB3ToSuccMarginSwapMarginService l_service = null;

        try
        {            
            //�i�A���j�M�p����������n�T�[�r�XImpl���擾��
            l_service =
                (WEB3ToSuccMarginSwapMarginService) Services.getService(WEB3ToSuccMarginSwapMarginService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_e)
        {
            l_response = (WEB3SuccMarginSwapCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request,
                "�i�A���j�M�p����������n�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3SuccMarginSwapCompleteResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccMarginSwapCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�i�A���j�M�p����������n�����o�^�̎��{�Ɏ��s���܂����B",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccMarginSwapCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�i�A���j�M�p����������n�����o�^�̎��{�Ɏ��s���܂����B",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
