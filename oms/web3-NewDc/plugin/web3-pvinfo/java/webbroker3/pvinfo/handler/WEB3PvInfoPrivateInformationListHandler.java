head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.08.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoPrivateInformationListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��ײ�ްĲ�̫Ұ��݈ꗗ�n���h��(WEB3PvInfoPrivateInformationListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2004/10/25 ���O�B(���u) �쐬
*/
package webbroker3.pvinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.pvinfo.message.WEB3PvInfoAccountConnectionRequest;
import webbroker3.pvinfo.message.WEB3PvInfoAccountConnectionResponse;
import webbroker3.pvinfo.message.WEB3PvInfoInstitutionConnectionRequest;
import webbroker3.pvinfo.message.WEB3PvInfoInstitutionConnectionResponse;
import webbroker3.pvinfo.message.WEB3PvInfoOrderExecStateRequest;
import webbroker3.pvinfo.message.WEB3PvInfoOrderExecStateResponse;
import webbroker3.pvinfo.service.delegate.WEB3PvInfoPrivateInformationListService;
import webbroker3.util.WEB3LogUtility;

/**
 * (��ײ�ްĲ�̫Ұ��݈ꗗ�n���h��)<BR>
 * ��ײ�ްĲ�̫Ұ��݈ꗗ�n���h���N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3PvInfoPrivateInformationListHandler implements MessageHandler
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PvInfoPrivateInformationListHandler.class);
    
    /**
     * (get�ڋq�A�����)<BR>
     * �ڋq�A����ʕ\���������s���B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �ڋq�A�����N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoAccountConnectionResponse
     * @@roseuid 414526840260
     */
    public WEB3PvInfoAccountConnectionResponse getAccountConnectionScreen(WEB3PvInfoAccountConnectionRequest l_request)
    {
        final String STR_METHOD_NAME = " getAccountConnectionScreen(WEB3PvInfoAccountConnectionRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3PvInfoAccountConnectionResponse l_response = null;
        WEB3PvInfoPrivateInformationListService l_service = null;
        
        //��ײ�ްĲ�̫Ұ��݈ꗗ�T�[�r�X���擾����
        try
        {
            l_service = (WEB3PvInfoPrivateInformationListService)Services.getService(WEB3PvInfoPrivateInformationListService.class);
        }
        catch (Exception l_ex)
        {
            l_response =(WEB3PvInfoAccountConnectionResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "��ײ�ްĲ�̫Ұ��݈ꗗ�T�[�r�X�̎擾�Ɏ��s���܂����B", l_response.errorInfo,l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //��ײ�ްĲ�̫Ұ��݈ꗗ�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3PvInfoAccountConnectionResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3PvInfoAccountConnectionResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "��ײ�ްĲ�̫Ұ��݈ꗗ�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;       
    }

    /**
     * (get�،���ИA�����)<BR>
     * �،���ИA����ʕ\���������s���B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �،���ИA�����N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoInstitutionConnectionResponse
     * @@roseuid 414526C300AA
     */
    public WEB3PvInfoInstitutionConnectionResponse getInstitutionConnectionScreen(WEB3PvInfoInstitutionConnectionRequest l_request)
    {
        
        final String STR_METHOD_NAME = " getInstitutionConnectionScreen(WEB3PvInfoInstitutionConnectionRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3PvInfoInstitutionConnectionResponse l_response = null;
        WEB3PvInfoPrivateInformationListService l_service = null;
        
        //��ײ�ްĲ�̫Ұ��݈ꗗ�T�[�r�X���擾����
        try
        {
            l_service = (WEB3PvInfoPrivateInformationListService)Services.getService(WEB3PvInfoPrivateInformationListService.class);
        }
        catch (Exception l_ex)
        {
            l_response =(WEB3PvInfoInstitutionConnectionResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "��ײ�ްĲ�̫Ұ��݈ꗗ�T�[�r�X�̎擾�Ɏ��s���܂����B", l_response.errorInfo,l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //��ײ�ްĲ�̫Ұ��݈ꗗ�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3PvInfoInstitutionConnectionResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3PvInfoInstitutionConnectionResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "��ײ�ްĲ�̫Ұ��݈ꗗ�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;  
        
    }

    /**
     * (get�������󋵉��)<BR>
     * �������󋵉�ʕ\���������s���B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �������󋵃��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoOrderExecStateResponse
     * @@roseuid 414526ED01A4
     */
    public WEB3PvInfoOrderExecStateResponse getOrderExecStateScreen(WEB3PvInfoOrderExecStateRequest l_request)
    {
        final String STR_METHOD_NAME = " getOrderExecStateScreen(WEB3PvInfoOrderExecStateRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3PvInfoOrderExecStateResponse l_response = null;
        WEB3PvInfoPrivateInformationListService l_service = null;
        
        //��ײ�ްĲ�̫Ұ��݈ꗗ�T�[�r�X���擾����
        try
        {
            l_service = (WEB3PvInfoPrivateInformationListService)Services.getService(WEB3PvInfoPrivateInformationListService.class);
        }
        catch (Exception l_ex)
        {
            l_response =(WEB3PvInfoOrderExecStateResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "��ײ�ްĲ�̫Ұ��݈ꗗ�T�[�r�X�̎擾�Ɏ��s���܂����B", l_response.errorInfo,l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //��ײ�ްĲ�̫Ұ��݈ꗗ�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3PvInfoOrderExecStateResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3PvInfoOrderExecStateResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "��ײ�ްĲ�̫Ұ��݈ꗗ�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;  
        
    }
}
@
