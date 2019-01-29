head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.58.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginChangeOpenMarginHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p��������V�K���n���h��(WEB3ToSuccMarginChangeOpenMarginHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/17�@@���@@��(���u) �V�K�쐬
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenChangeCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenChangeConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginChangeOpenMarginService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�i�A���j�M�p��������V�K���n���h��)<BR>
 * �i�A���j�M�p��������V�K���n���h���N���X<BR>
 * 
 * @@author ���@@��(���u)
 * @@version 1.0
 */
public class WEB3ToSuccMarginChangeOpenMarginHandler implements MessageHandler 
{
    
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginChangeOpenMarginHandler.class);
    
    /**
     * @@roseuid 4369ED320203
     */
    public WEB3ToSuccMarginChangeOpenMarginHandler() 
    {
     
    }
    
    /**
     * (confirm�����V�K��)<BR>
     * �i�A���j�M�p����̒����V�K�������R�����s���B<BR>
     * <BR>
     * �i�A���j�M�p��������V�K���T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �i�A���j�M�p������������V�K���m�F���N�G�X�g<BR>
     * @@return WEB3SuccMarginOpenChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 433BCD6903DE
     */
    public WEB3SuccMarginOpenChangeConfirmResponse confirmOpenMarginChange(
        WEB3SuccMarginOpenChangeConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " confirmOpenMarginChange(WEB3SuccMarginOpenChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
     
        WEB3SuccMarginOpenChangeConfirmResponse l_response = null;
        WEB3ToSuccMarginChangeOpenMarginService l_service = null;

        try
        {            
            //�i�A���j�M�p��������V�K���T�[�r�X���擾��
            l_service =
                (WEB3ToSuccMarginChangeOpenMarginService) Services.getService(
                    WEB3ToSuccMarginChangeOpenMarginService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccMarginOpenChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request,
                "�i�A���j�M�p��������V�K���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3SuccMarginOpenChangeConfirmResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccMarginOpenChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
            {
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.error(
                l_request, 
                "�i�A���j�M�p����̒����V�K�������R���̎��{�Ɏ��s���܂����B",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccMarginOpenChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�i�A���j�M�p����̒����V�K�������R���̎��{�Ɏ��s���܂����B",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete�����V�K��)<BR>
     * �i�A���j�M�p����̒����V�K��������o�^����B<BR>
     * <BR>
     * �i�A���j�M�p��������V�K���T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - �i�A���j�M�p������������V�K���������N�G�X�g
     * @@return WEB3SuccMarginOpenChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 433BCD6A0016
     */
    public WEB3SuccMarginOpenChangeCompleteResponse completeOpenMarginChange(
        WEB3SuccMarginOpenChangeCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " confirmOpenMarginChange(WEB3SuccMarginOpenChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3SuccMarginOpenChangeCompleteResponse l_response = null;
        WEB3ToSuccMarginChangeOpenMarginService l_service = null;

        try
        {            
            //�i�A���j�M�p��������V�K���T�[�r�X���擾��
            l_service =
                (WEB3ToSuccMarginChangeOpenMarginService) Services.getService(
                    WEB3ToSuccMarginChangeOpenMarginService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccMarginOpenChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request,
                "�i�A���j�M�p��������V�K���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3SuccMarginOpenChangeCompleteResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccMarginOpenChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
            {
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.error(
                l_request, 
                "�i�A���j�M�p����̒����V�K�������̓o�^�Ɏ��s���܂����B",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccMarginOpenChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�i�A���j�M�p����̒����V�K�������̓o�^�Ɏ��s���܂����B",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
