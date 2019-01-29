head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityExecuteReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������������Ɖ�n���h��(WEB3EquityExecuteReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 �� �� (���u) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3EquityExecuteReferenceRequest;
import webbroker3.equity.message.WEB3EquityExecuteReferenceResponse;
import webbroker3.equity.message.WEB3EquityExecuteDetailsResponse;
import webbroker3.equity.message.WEB3EquityExecuteDetailsRequest;
import webbroker3.equity.message.WEB3EquityOrderHistoryResponse;
import webbroker3.equity.message.WEB3EquityOrderHistoryRequest;
import webbroker3.equity.service.delegate.WEB3EquityExecuteReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i���������������Ɖ�n���h���j�B<BR>
 * <BR>
 * ���������������Ɖ�n���h���N���X
 * @@version 1.0
 */
public class WEB3EquityExecuteReferenceHandler implements MessageHandler
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityExecuteReferenceHandler.class);

    /**
     * @@roseuid 40A287FA038A
     */
    public WEB3EquityExecuteReferenceHandler()
    {

    }

    /**
     * (search�������Ɖ�)<BR>
     * ���������������Ɖ�A����������������ꗗ�ɕ\������f�[�^��<BR>
     * �擾���s���B<BR>
     * <BR>
     * ���������������Ɖ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���������������Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.equity.message.WEB3EquityExecuteReferenceResponse
     * @@roseuid 4060DF2201C9
     */
    public WEB3EquityExecuteReferenceResponse searchExecuteReference(WEB3EquityExecuteReferenceRequest l_request)
    {
        // �����������Ɖ�T�[�r�X�I�u�W�F�N�g���擾����
        final String STR_METHOD_NAME = 
            "equityExecuteReferenceRequest(WEB3EquityExecuteReferenceRequest)";

        log.entering(STR_METHOD_NAME);

        WEB3EquityExecuteReferenceResponse l_response = null;
        WEB3EquityExecuteReferenceService l_service = null;

        // �����������Ɖ�T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        // �������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_service =
                (WEB3EquityExecuteReferenceService) Services.getService(
                    WEB3EquityExecuteReferenceService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception e)
        {
            l_response =
                (WEB3EquityExecuteReferenceResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����������Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3EquityExecuteReferenceResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3EquityExecuteReferenceResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "�����������Ɖ�\���Ɏ��s���܂����B", e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3EquityExecuteReferenceResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "�����������Ɖ�\���Ɏ��s���܂����B", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        // ���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }

    /**
     * (search�������ڍ�)<BR>
     * ���������������ڍ׉�ʂɕ\������f�[�^���擾����B<BR>
     * <BR>
     * ���������������Ɖ�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���������������ڍ׃��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.equity.message.WEB3EquityExecuteDetailsResponse
     * @@roseuid 406A8F750137
     */
    public WEB3EquityExecuteDetailsResponse searchExecuteDetails(WEB3EquityExecuteDetailsRequest l_request)
    {
        //�����������ڍ׃T�[�r�X�I�u�W�F�N�g���擾����B
        final String STR_METHOD_NAME = 
            "equityExecuteDetailsRequest(WEB3EquityExecuteDetailsResponse)";

        log.entering(STR_METHOD_NAME);

        WEB3EquityExecuteDetailsResponse l_response = null;
        WEB3EquityExecuteReferenceService l_service = null;

        // �����������Ɖ�T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        // �������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_service =
                (WEB3EquityExecuteReferenceService) Services.getService(
                    WEB3EquityExecuteReferenceService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception e)
        {
            l_response =
                (WEB3EquityExecuteDetailsResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����������Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3EquityExecuteDetailsResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3EquityExecuteDetailsResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "���������������ڍו\���Ɏ��s���܂����B", e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3EquityExecuteDetailsResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "���������������ڍו\���Ɏ��s���܂����B", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        // ���X�|���X�I�u�W�F�N�g��ԋp����       
        return l_response;
    }

    /**
     * (search������藚��)<BR>
     * ��������������藚����ʂɕ\������f�[�^���擾����B<BR>
     * <BR>
     * ���������������Ɖ�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ��������������藚�����N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.equity.message.WEB3EquityOrderHistoryResponse
     * @@roseuid 4076537500C2
     */
    public WEB3EquityOrderHistoryResponse searchOrderHistory(WEB3EquityOrderHistoryRequest l_request)
    {
        //�������������T�[�r�X�I�u�W�F�N�g���擾����B
        final String STR_METHOD_NAME =
            "equityOrderHistoryRequest(WEB3EquityOrderHistoryResponse)";

        log.entering(STR_METHOD_NAME);

        WEB3EquityOrderHistoryResponse l_response = null;
        WEB3EquityExecuteReferenceService l_service = null;

        // �����������Ɖ�T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        // �������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_service =
                (WEB3EquityExecuteReferenceService) Services.getService(
                    WEB3EquityExecuteReferenceService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception e)
        {
            l_response =
                (WEB3EquityOrderHistoryResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            //��Е��X�戵�\�s��Params���擾�ł��܂���ł���
            log.error(
                l_request,
                "�����������Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3EquityOrderHistoryResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3EquityOrderHistoryResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "����������藚��\���Ɏ��s���܂����B", e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3EquityOrderHistoryResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "����������藚��\���Ɏ��s���܂����B", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        // ���X�|���X�I�u�W�F�N�g��ԋp����       
        return l_response;
    }
}
@
