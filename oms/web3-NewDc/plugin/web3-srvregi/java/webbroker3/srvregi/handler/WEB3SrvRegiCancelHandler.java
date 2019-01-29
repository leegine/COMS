head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.46.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p����n���h��(WEB3SrvRegiCancelHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/25 ���o�� �V�K�쐬
*/

package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3SrvRegiCancelCompleteRequest;
import webbroker3.srvregi.message.WEB3SrvRegiCancelCompleteResponse;
import webbroker3.srvregi.message.WEB3SrvRegiCancelConfirmRequest;
import webbroker3.srvregi.message.WEB3SrvRegiCancelConfirmResponse;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiCancelService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�T�[�r�X���p����n���h��)<BR>
 * �T�[�r�X���p����n���h���N���X<BR>
 * 
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3SrvRegiCancelHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiCancelHandler.class);
    
    /**
     * @@roseuid 416F415D0232
     */
    public WEB3SrvRegiCancelHandler() 
    {
     
    }
    
    /**
     * (confirm���)<BR>
     * �T�[�r�X���p����m�F�R���������s���B<BR>
     * <BR>
     * �T�[�r�X���p����T�[�r�X���擾���Aexecute( )���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p����m�F���N�G�X�g�@@�I�u�W�F�N�g<BR>
     * @@return webbroker3.srvregi.message.WEB3SrvRegiCancelConfirmResponse
     * @@roseuid 40F5ED4702B0
     */
    public WEB3SrvRegiCancelConfirmResponse confirmCancel(WEB3SrvRegiCancelConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmCancel(WEB3SrvRegiCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3SrvRegiCancelConfirmResponse l_response = null;
        WEB3SrvRegiCancelService l_service = null;
        
        //�T�[�r�X���p����T�[�r�X���擾
        try
        {
            l_service =
                (WEB3SrvRegiCancelService)Services.getService(
                WEB3SrvRegiCancelService.class);       //Exception
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3SrvRegiCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�T�[�r�X���p����T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            log.exiting(STR_METHOD_NAME);    
            return l_response;      
        }
        
        //�T�[�r�X���p����T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3SrvRegiCancelConfirmResponse)l_service.execute(
                l_request);                    //WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SrvRegiCancelConfirmResponse)l_request.createResponse();

            l_response.errorInfo = l_ex.getErrorInfo();
            
            log.error(
                l_request,
                "�T�[�r�X���p����T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;      
    }
    
    /**
     * (complete���)<BR>
     * �T�[�r�X���p����������s���B<BR>
     * <BR>
     * �T�[�r�X���p����T�[�r�X���擾���Aexecute( )���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p����������N�G�X�g�@@�I�u�W�F�N�g<BR>
     * @@return webbroker3.srvregi.message.WEB3SrvRegiCancelCompleteResponse
     * @@roseuid 40F5ED8B00CC
     */
    public WEB3SrvRegiCancelCompleteResponse completeCancel(WEB3SrvRegiCancelCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " completeCancel(WEB3SrvRegiCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3SrvRegiCancelCompleteResponse l_response = null;
        WEB3SrvRegiCancelService l_service = null;
        
        //�T�[�r�X���p����T�[�r�X���擾
        try
        {
            l_service =
                (WEB3SrvRegiCancelService)Services.getService(
                WEB3SrvRegiCancelService.class);     //Exception
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3SrvRegiCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�T�[�r�X���p����T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;      
        }
        
        //�T�[�r�X���p����T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3SrvRegiCancelCompleteResponse)l_service.execute(
                l_request);               //WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SrvRegiCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�T�[�r�X���p����T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;     
    }
}
@
