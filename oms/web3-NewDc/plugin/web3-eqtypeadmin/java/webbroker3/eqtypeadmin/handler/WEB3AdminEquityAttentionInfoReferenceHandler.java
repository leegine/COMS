head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityAttentionInfoReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ғ��ӏ��Ɖ�n���h��(WEB3AdminEquityAttentionInfoReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/30�@@���L���E(���u) �V�K�쐬 �d�l�ύX���f��No.216
*/
package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefInpRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefInpResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefRefRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefRefResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAttentionInfoReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��Ғ��ӏ��Ɖ�n���h��)<BR>
 * �Ǘ��Ғ��ӏ��Ɖ�n���h���N���X<BR>
 * <BR>
 * @@author ���L���E
 * @@version 1.0
 */
public class WEB3AdminEquityAttentionInfoReferenceHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityAttentionInfoReferenceHandler.class);
    /**
     * @@roseuid 49588AEB003E
     */
    public WEB3AdminEquityAttentionInfoReferenceHandler()
    {

    }

    /**
     * (get���͉��)<BR>
     * ���ӏ��Ɖ���͉�ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��Ғ��ӏ��Ɖ�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE���ӏ����̓��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminEqAttentionInfoRefInpResponse
     * @@roseuid 494227B502C3
     */
    public WEB3AdminEqAttentionInfoRefInpResponse getInputScreen(
        WEB3AdminEqAttentionInfoRefInpRequest l_request)
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminEqAttentionInfoRefInpRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEqAttentionInfoRefInpResponse l_response = null;
        WEB3AdminEquityAttentionInfoReferenceService l_service = null;

        try
        {
            //�Ǘ��Ғ��ӏ��Ɖ�T�[�r�X���擾��
            l_service = (WEB3AdminEquityAttentionInfoReferenceService)Services.getService(
                WEB3AdminEquityAttentionInfoReferenceService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminEqAttentionInfoRefInpResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��Ғ��ӏ��Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute
            l_response = (WEB3AdminEqAttentionInfoRefInpResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminEqAttentionInfoRefInpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "���ӏ��Ɖ���͉�ʕ\�����������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminEqAttentionInfoRefInpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                " ���ӏ��Ɖ���͉�ʕ\�����������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�Ɖ���)<BR>
     * ���ӏ��Ɖ��ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��Ғ��ӏ��Ɖ�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE���ӏ��Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminEqAttentionInfoRefRefResponse
     * @@roseuid 494228FF018C
     */
    public WEB3AdminEqAttentionInfoRefRefResponse getReferenceScreen(
        WEB3AdminEqAttentionInfoRefRefRequest l_request)
    {
        final String STR_METHOD_NAME = "getReferenceScreen(WEB3AdminEqAttentionInfoRefRefRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEqAttentionInfoRefRefResponse l_response = null;
        WEB3AdminEquityAttentionInfoReferenceService l_service = null;

        try
        {
            //�Ǘ��Ғ��ӏ��Ɖ�T�[�r�X���擾��
            l_service = (WEB3AdminEquityAttentionInfoReferenceService)Services.getService(
                WEB3AdminEquityAttentionInfoReferenceService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminEqAttentionInfoRefRefResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��Ғ��ӏ��Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute
            l_response = (WEB3AdminEqAttentionInfoRefRefResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminEqAttentionInfoRefRefResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "���ӏ��Ɖ��ʕ\�����������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminEqAttentionInfoRefRefResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                " ���ӏ��Ɖ��ʕ\�����������s���܂����B",
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
