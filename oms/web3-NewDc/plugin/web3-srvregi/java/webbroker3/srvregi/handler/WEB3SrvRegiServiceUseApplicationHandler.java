head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiServiceUseApplicationHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�\���n���h��(WEB3SrvRegiServiceUseApplicationHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/28 ���o�� �V�K�쐬
*/

package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3SrvRegiApplyCompleteRequest;
import webbroker3.srvregi.message.WEB3SrvRegiApplyCompleteResponse;
import webbroker3.srvregi.message.WEB3SrvRegiApplyConfirmRequest;
import webbroker3.srvregi.message.WEB3SrvRegiApplyConfirmResponse;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiServiceUseApplicationService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�T�[�r�X���p�\���n���h��)<BR>
 * �T�[�r�X���p�\���n���h���N���X<BR>
 * 
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3SrvRegiServiceUseApplicationHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiServiceUseApplicationHandler.class);
    
    /**
     * @@roseuid 416F415E004E
     */
    public WEB3SrvRegiServiceUseApplicationHandler() 
    {
     
    }
    
    /**
     * (confirm���p�\��)<BR>
     * �T�[�r�X���p�\���R���������s���B<BR>
     * <BR>
     * �T�[�r�X���p�\���T�[�r�X���擾���Aexecute( )���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�\���m�F���N�G�X�g�@@�I�u�W�F�N�g<BR>
     * @@return webbroker3.srvregi.message.WEB3SrvRegiApplyConfirmResponse
     * @@roseuid 40F5EFA901A7
     */
    public WEB3SrvRegiApplyConfirmResponse confirmUseAppli(WEB3SrvRegiApplyConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmUseAppli(WEB3SrvRegiApplyConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3SrvRegiApplyConfirmResponse l_response = null;
        WEB3SrvRegiServiceUseApplicationService l_service = null;
        
        //�T�[�r�X���p�\���T�[�r�X���擾
        try
        {
            l_service =
                (WEB3SrvRegiServiceUseApplicationService)Services.getService(
                WEB3SrvRegiServiceUseApplicationService.class);  //Exception
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3SrvRegiApplyConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�T�[�r�X���p�\���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;      
        }
        
        //�T�[�r�X���p�\���T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3SrvRegiApplyConfirmResponse)l_service.execute(
                    l_request);         //WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SrvRegiApplyConfirmResponse)l_request.createResponse();

            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�T�[�r�X���p�\���T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response; 
     
    }
    
    /**
     * (complete���p�\��)<BR>
     * �T�[�r�X���p�\���o�^�������s���B<BR>
     * <BR>
     * �T�[�r�X���p�\���T�[�r�X���擾���A�Aexecute( )���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�\���������N�G�X�g�@@�I�u�W�F�N�g<BR>
     * @@return webbroker3.srvregi.message.WEB3SrvRegiApplyCompleteResponse
     * @@roseuid 40F5EFB000DC
     */
    public WEB3SrvRegiApplyCompleteResponse completeUseAppli(WEB3SrvRegiApplyCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " completeUseAppli(WEB3SrvRegiApplyCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3SrvRegiApplyCompleteResponse l_response = null;
        WEB3SrvRegiServiceUseApplicationService l_service = null;
        
        //�T�[�r�X���p�\���T�[�r�X���擾
        try
        {
            l_service =
                (WEB3SrvRegiServiceUseApplicationService)Services.getService(
                WEB3SrvRegiServiceUseApplicationService.class);   //Exception
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3SrvRegiApplyCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�T�[�r�X���p�\���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;      
        }
        
        //�T�[�r�X���p�\���T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3SrvRegiApplyCompleteResponse)l_service.execute(
                    l_request);  //WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SrvRegiApplyCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            
            if (l_ex.getErrorMessage() != null && !"".equals(l_ex.getErrorMessage())
                && !WEB3ErrorCatalog.BUSINESS_ERROR_03027.equals(l_ex.getErrorInfo())
                && !WEB3ErrorCatalog.BUSINESS_ERROR_03019.equals(l_ex.getErrorInfo())
                && !WEB3ErrorCatalog.SYSTEM_ERROR_80006.equals(l_ex.getErrorInfo()))
            {
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.error(
                l_request,
                "�T�[�r�X���p�\���T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
}
@
