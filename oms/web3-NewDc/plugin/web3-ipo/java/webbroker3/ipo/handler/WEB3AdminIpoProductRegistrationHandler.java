head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.49.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoProductRegistrationHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO�����o�^�n���h��(WEB3AdminIpoProductRegistrationHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/17 ���o�� �V�K�쐬
*/
package webbroker3.ipo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationConfirmResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationInputRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationInputResponse;
import webbroker3.ipo.service.delegate.WEB3AdminIpoProductRegistrationService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ���IPO�����o�^�n���h��)<BR>
 * �Ǘ���IPO�����o�^�n���h���N���X
 * 
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3AdminIpoProductRegistrationHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIpoProductRegistrationHandler.class);
    
    /**
     * @@roseuid 4112EE570299
     */
    public WEB3AdminIpoProductRegistrationHandler() 
    {
     
    }
    
    /**
     * (�����o�^�m�F)<BR>
     * �Ǘ���IPO�����V�K�o�^�m�F����<BR>
     * <BR>
     * �Ǘ���IPO�����o�^�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * IPO�����V�K�o�^�m�F���N�G�X�g
     * @@return webbroker3.ipo.message.WEB3AdminIPOProductRegistrationConfirmResponse
     * @@roseuid 40C56C660117
     */
    public WEB3AdminIPOProductRegistrationConfirmResponse productRegistrationConfirm(WEB3AdminIPOProductRegistrationConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " ProductRegistrationConfirm(WEB3AdminIPOProductRegistrationConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOProductRegistrationConfirmResponse l_response = null;
        WEB3AdminIpoProductRegistrationService l_service = null;
        
        //�Ǘ���IPO�����o�^�T�[�r�X���擾
        try
        {
            l_service =
                (WEB3AdminIpoProductRegistrationService)Services.getService(
                WEB3AdminIpoProductRegistrationService.class);
                
            log.debug("�Ǘ���IPO�����o�^�T�[�r�X���擾");    
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminIPOProductRegistrationConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ���IPO�����o�^�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�Ǘ���IPO�����o�^�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3AdminIPOProductRegistrationConfirmResponse)l_service.execute(
                    l_request);
            log.debug("�Ǘ���IPO�����o�^�T�[�r�X.execute()���\�b�h���R�[������");        
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminIPOProductRegistrationConfirmResponse)l_request.createResponse();
            // 2004/11/09 U00378 �r�W�l�X�G���[�����X�|���X�ɃZ�b�g����悤�ɏC��  ���@@SRA START
            l_response.errorInfo = l_ex.getErrorInfo();
//			l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			// 2004/11/09 U00378 �r�W�l�X�G���[�����X�|���X�ɃZ�b�g����悤�ɏC��  ���@@SRA END
            log.error(
                l_request,
                "�Ǘ���IPO�����o�^�T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (�����o�^����)<BR>
     * �Ǘ���IPO�����V�K�o�^��������<BR>
     * <BR>
     * �Ǘ���IPO�����o�^�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * IPO�����V�K�o�^�������N�G�X�g
     * @@return webbroker3.ipo.message.WEB3AdminIPOProductRegistrationCompleteResponse
     * @@roseuid 40C56D0C001D
     */
    public WEB3AdminIPOProductRegistrationCompleteResponse productRegistrationComplete(WEB3AdminIPOProductRegistrationCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " productRegistrationComplete(WEB3AdminIPOProductRegistrationCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOProductRegistrationCompleteResponse l_response = null;
        WEB3AdminIpoProductRegistrationService l_service = null;
        
        //�Ǘ���IPO�����o�^�T�[�r�X���擾
        try
        {
            l_service =
                (WEB3AdminIpoProductRegistrationService)Services.getService(
            WEB3AdminIpoProductRegistrationService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminIPOProductRegistrationCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ���IPO�����o�^�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�Ǘ���IPO�����o�^�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3AdminIPOProductRegistrationCompleteResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminIPOProductRegistrationCompleteResponse)l_request.createResponse();
			// 2004/11/09 U00378 �r�W�l�X�G���[�����X�|���X�ɃZ�b�g����悤�ɏC��  ���@@SRA START
			l_response.errorInfo = l_ex.getErrorInfo();
//			l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			// 2004/11/09 U00378 �r�W�l�X�G���[�����X�|���X�ɃZ�b�g����悤�ɏC��  ���@@SRA END
            log.error(
                l_request,
                "�Ǘ���IPO�����o�^�T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * �Ǘ���IPO�����V�K�o�^���͉�ʕ\������<BR>
     * <BR>
     * �Ǘ���IPO�����o�^�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * IPO�����V�K�o�^���̓��N�G�X�g
     * @@return webbroker3.ipo.message.WEB3AdminIPOProductRegistrationInputResponse
     * @@roseuid 40C56D1603E6
     */
    public WEB3AdminIPOProductRegistrationInputResponse inputScreenIndication(WEB3AdminIPOProductRegistrationInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " inputScreenIndication(WEB3AdminIPOProductRegistrationInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOProductRegistrationInputResponse l_response = null;
        WEB3AdminIpoProductRegistrationService l_service = null;
        
        //�Ǘ���IPO�����o�^�T�[�r�X���擾
        try
        {
            l_service =
                (WEB3AdminIpoProductRegistrationService)Services.getService(
                    WEB3AdminIpoProductRegistrationService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminIPOProductRegistrationInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ���IPO�����o�^�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�Ǘ���IPO�����o�^�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3AdminIPOProductRegistrationInputResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminIPOProductRegistrationInputResponse)l_request.createResponse();
			// 2004/11/09 U00378 �r�W�l�X�G���[�����X�|���X�ɃZ�b�g����悤�ɏC��  ���@@SRA START
			l_response.errorInfo = l_ex.getErrorInfo();
//			l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			// 2004/11/09 U00378 �r�W�l�X�G���[�����X�|���X�ɃZ�b�g����悤�ɏC��  ���@@SRA END
            log.error(
                l_request,
                "�Ǘ���IPO�����o�^�T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
     
    }
}
@
