head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.12.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoAccEstablishSearchHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �V�K�����J�݌��������(WEB3AdminAccInfoAccEstablishSearchHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/09  �����q(���u) �V�K�쐬
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoAccEstablishSearchDLRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoAccEstablishSearchDLResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoAccEstablishSearchInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoAccEstablishSearchInputResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoAccEstablishSearchListRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoAccEstablishSearchListResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoAccEstablishSearchService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq���������)<BR>
 * �Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq���������<BR>
 * <BR>
 * @@author �����q
 * @@version 1.0
 */

public class WEB3AdminAccInfoAccEstablishSearchHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoAccEstablishSearchHandler.class);

    /**
     * @@roseuid 418F3A0F0251
     */
    public WEB3AdminAccInfoAccEstablishSearchHandler()
    {

    }

    /**
     * (���͉�ʕ\��)<BR>
     * �V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�������͉�ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�����T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�������̓��N�G�X�g�I�u�W�F�N�g
     * @@return WEB3AdminAccInfoAccOpenLockSearchInputResponse
     */
    public WEB3AdminAccInfoAccEstablishSearchInputResponse getInputScreen(
        WEB3AdminAccInfoAccEstablishSearchInputRequest l_request)
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoAccOpenLockSearchInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoAccEstablishSearchInputResponse l_response = null;
        WEB3AdminAccInfoAccEstablishSearchService l_service = null;

        try
        {
            l_service =
                (WEB3AdminAccInfoAccEstablishSearchService)Services.getService(
                        WEB3AdminAccInfoAccEstablishSearchService.class);
        }

        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoAccEstablishSearchInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�V�K�����J�݌������޽�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //�Ǘ��҂��q�l���ڋq��{���⍇���T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3AdminAccInfoAccEstablishSearchInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoAccEstablishSearchInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�V�K�����J�݌������޽�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }

    /**
     * (�ꗗ��ʕ\��)<BR>
     * �V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�����ꗗ��ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�����T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�����ꗗ���N�G�X�g�I�u�W�F�N�g
     * @@return WEB3AdminAccInfoAccOpenLockSearchListResponse
     */
    public WEB3AdminAccInfoAccEstablishSearchListResponse getListScreen(
        WEB3AdminAccInfoAccEstablishSearchListRequest l_request)
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoAccOpenLockSearchInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoAccEstablishSearchListResponse l_response = null;
        WEB3AdminAccInfoAccEstablishSearchService l_service = null;

        try
        {
            l_service =
                (WEB3AdminAccInfoAccEstablishSearchService)Services.getService(
                        WEB3AdminAccInfoAccEstablishSearchService.class);
        }

        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoAccEstablishSearchListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�V�K�����J�݌������޽�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�Ǘ��҂��q�l���ڋq��{���⍇���T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3AdminAccInfoAccEstablishSearchListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoAccEstablishSearchListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�V�K�����J�݌������޽�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }

    /**
     * (get�_�E�����[�h�t�@@�C�� )<BR>
     * �V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq����get�_�E�����[�h�t�@@�C���������s���B<BR>
     * <BR>
     * �Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�����T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�����_�E�����[�h���N�G�X�g�I�u�W�F�N�g
     * @@return WEB3AdminAccInfoAccOpenLockSearchDLResponse
     */
    public WEB3AdminAccInfoAccEstablishSearchDLResponse getDownLoadFile(
        WEB3AdminAccInfoAccEstablishSearchDLRequest l_request)
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoAccOpenLockSearchInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoAccEstablishSearchDLResponse l_response = null;
        WEB3AdminAccInfoAccEstablishSearchService l_service = null;

        try
        {
            l_service =
                (WEB3AdminAccInfoAccEstablishSearchService)Services.getService(
                        WEB3AdminAccInfoAccEstablishSearchService.class);
        }

        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoAccEstablishSearchDLResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�V�K�����J�݌������޽�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�Ǘ��҂��q�l���ڋq��{���⍇���T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3AdminAccInfoAccEstablishSearchDLResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoAccEstablishSearchDLResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�V�K�����J�݌������޽�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }

}
@
