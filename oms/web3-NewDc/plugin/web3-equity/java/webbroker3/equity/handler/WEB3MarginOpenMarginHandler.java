head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOpenMarginHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����V�K���n���h���N���X(WEB3MarginOpenMarginHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/27 ���Ō� (Sinocom) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.equity.message.WEB3MarginOpenMarginCompleteRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginCompleteResponse;
import webbroker3.equity.message.WEB3MarginOpenMarginConfirmRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginConfirmResponse;
import webbroker3.equity.service.delegate.WEB3MarginOpenMarginService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;

/**
 * �i�M�p����V�K���n���h���j�B<BR>
 * <BR>
 * �M�p����V�K���n���h���N���X
 * @@author ���Ō�
 * @@version 1.0
 */
public class WEB3MarginOpenMarginHandler implements MessageHandler 
{
    /**
    * ���O���[�e�B���e�B<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3MarginOpenMarginHandler.class);
            
    /**
     * @@roseuid 414184C4038A
     */
    public WEB3MarginOpenMarginHandler() 
    {
     
    }
    
    /**
     * (confirm����)<BR>
     * �M�p����V�K�������R�����s���B<BR>
     * <BR>
     * �M�p����V�K���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �M�p����V�K�������m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return WEB3MarginOpenMarginConfirmResponse
     * @@roseuid 40554D9A01CE
     */
    public WEB3MarginOpenMarginConfirmResponse confirmOrder(WEB3MarginOpenMarginConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = "confirmOrder(WEB3MarginOpenMarginConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginOpenMarginConfirmResponse l_response = null;
        WEB3MarginOpenMarginService l_service = null;

        //�M�p����V�K�������R�����s���B
        try
        {
            l_service = (WEB3MarginOpenMarginService)Services.getService(WEB3MarginOpenMarginService.class);

            //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        }
        catch (Exception l_ex)
        {

            l_response = (WEB3MarginOpenMarginConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�M�p����V�K���T�[�r�X�̎擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�M�p����V�K���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3MarginOpenMarginConfirmResponse) l_service.execute(l_request);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MarginOpenMarginConfirmResponse)l_request.createResponse();

            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, "�M�p����V�K�������R���Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3MarginOpenMarginConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "�M�p����V�K�������R���Ɏ��s���܂����B", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
    
    /**
     * (complete����)<BR>
     * �M�p����V�K�������o�^���s���B<BR>
     * <BR>
     * �M�p����V�K���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �M�p����V�K�������������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3MarginOpenMarginCompleteResponse
     * @@roseuid 40554DAE02D8
     */
    public WEB3MarginOpenMarginCompleteResponse completeOrder(WEB3MarginOpenMarginCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = "completeOrder(WEB3MarginOpenMarginCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginOpenMarginCompleteResponse l_response = null;
        WEB3MarginOpenMarginService l_service = null;

        //�M�p����V�K�������o�^���s���B
        try
        {
            l_service = (WEB3MarginOpenMarginService)Services.getService(WEB3MarginOpenMarginService.class);

            //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        }
        catch (Exception l_ex)
        {

            l_response =
                (WEB3MarginOpenMarginCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�M�p����V�K�������T�[�r�X�̎擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);

            return l_response;
        }

        //�M�p����V�K���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3MarginOpenMarginCompleteResponse) l_service.execute(l_request);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MarginOpenMarginCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, "�M�p����V�K�������Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3MarginOpenMarginCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "�M�p����V�K�������Ɏ��s���܂����B", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
