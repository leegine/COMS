head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.00.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginChangeCloseMarginHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p��������ԍσn���h��(WEB3ToSuccMarginChangeCloseMarginHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/17 杊��](���u) �V�K�쐬
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseChangeCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseChangeConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginChangeCloseMarginService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�i�A���j�M�p��������ԍσn���h��)<BR>
 * �i�A���j�M�p��������ԍσn���h���N���X<BR>
 *   
 * @@author 杊��]
 * @@version 1.0
 */
public class WEB3ToSuccMarginChangeCloseMarginHandler implements MessageHandler 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginChangeCloseMarginHandler.class);

    /**
     * @@roseuid 4369ED33029F
     */
    public WEB3ToSuccMarginChangeCloseMarginHandler() 
    {
     
    }
    
    /**
     * (confirm�����ԍ�)<BR>
     * �M�p����̒����ԍϔ����R�����s���B<BR>
     * <BR>
     * �i�A���j�M�p��������ԍσT�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �i�A���j�M�p������������ԍϊm�F���N�G�X�g�I�u�W�F�N�g�B<BR>
     * @@return WEB3SuccMarginCloseChangeConfirmResponse
     * @@roseuid 433CFF7A01FD
     */
    public WEB3SuccMarginCloseChangeConfirmResponse confirmCloseMarginChange(
        WEB3SuccMarginCloseChangeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmCloseMarginChange(WEB3SuccMarginCloseChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccMarginCloseChangeConfirmResponse l_response = null;
        WEB3ToSuccMarginChangeCloseMarginService l_service = null;
        
        try
        {            
            //get�i�A���j�M�p��������ԍσT�[�r�X
            l_service = (WEB3ToSuccMarginChangeCloseMarginService)
                Services.getService(WEB3ToSuccMarginChangeCloseMarginService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccMarginCloseChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(l_request,
                "�i�A���j�M�p��������ԍσT�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute
            l_response = (WEB3SuccMarginCloseChangeConfirmResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccMarginCloseChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
            {
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.error(
                l_request, 
                "�M�p����̒����ԍϔ����R���̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccMarginCloseChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�M�p����̒����ԍϔ����R���̎��{�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete�����ԍ�)<BR>
     * �M�p����̒����ԍϒ�����o�^����B<BR>
     * <BR>
     * �i�A���j�M�p��������ԍσT�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �i�A���j�M�p������������ԍϊ������N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3SuccMarginCloseChangeCompleteResponse
     * @@roseuid 433CFF7A021C
     */
    public WEB3SuccMarginCloseChangeCompleteResponse completeCloseMarginChange(
        WEB3SuccMarginCloseChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " completeCloseMarginChange(WEB3SuccMarginCloseChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccMarginCloseChangeCompleteResponse l_response = null;
        WEB3ToSuccMarginChangeCloseMarginService l_service = null;
        
        try
        {            
            //get�i�A���j�M�p��������ԍσT�[�r�X
            l_service = (WEB3ToSuccMarginChangeCloseMarginService)
                Services.getService(WEB3ToSuccMarginChangeCloseMarginService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccMarginCloseChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(l_request,
                "�i�A���j�M�p��������ԍσT�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute
            l_response = (WEB3SuccMarginCloseChangeCompleteResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccMarginCloseChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
            {
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.error(
                l_request, 
                "�M�p����̒����ԍϒ����̓o�^�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccMarginCloseChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�M�p����̒����ԍϒ����̓o�^�Ɏ��s���܂����B",
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
