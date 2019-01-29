head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.44.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ������(WEB3AdminSrvRegiOtherOrgIdListHandler.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/03/10 �đo�g(���u) �V�K�쐬 ���f��No.335
*/

package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdListReferenceRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdListReferenceResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdListSearchRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdListSearchResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiOtherOrgIdListService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ������)<BR>
 * �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�����׃N���X<BR>
 * <BR>
 * @@author �đo�g
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdListHandler implements MessageHandler
{

    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminSrvRegiOtherOrgIdListHandler.class);

    /**
     * @@roseuid 47D11137039C
     */
    public WEB3AdminSrvRegiOtherOrgIdListHandler()
    {

    }

    /**
     * (�O���A�gID�ꗗ�Ɖ�)<BR>
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�����s���B<BR>
     * <BR>
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�N�G�X�g�@@�I�u�W�F�N�g<BR>
     * @@return WEB3AdminSrvRegiOtherOrgIdListReferenceResponse
     * @@roseuid 47B944EC0303
     */
    public WEB3AdminSrvRegiOtherOrgIdListReferenceResponse otherOrgIdListReference(
        WEB3AdminSrvRegiOtherOrgIdListReferenceRequest l_request)
    {
        final String STR_METHOD_NAME =
            "otherOrgIdListReference(WEB3AdminSrvRegiOtherOrgIdListReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminSrvRegiOtherOrgIdListReferenceResponse l_response = null;
        WEB3AdminSrvRegiOtherOrgIdListService l_service = null;

        try
        {
            l_service =
                (WEB3AdminSrvRegiOtherOrgIdListService)Services.getService(
                    WEB3AdminSrvRegiOtherOrgIdListService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdListReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖﻰ�޽���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdListReferenceResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdListReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.error(
                l_request,
                "�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ���ɃG���[���������܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdListReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ���ɃG���[���������܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (�O���A�gID�ꗗ����)<BR>
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�������͉�ʏ������s���B<BR>
     * <BR>
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�������̓��N�G�X�g�@@�I�u�W�F�N�g<BR>
     * @@return WEB3AdminSrvRegiOtherOrgIdListSearchResponse
     * @@roseuid 47B9458601C0
     */
    public WEB3AdminSrvRegiOtherOrgIdListSearchResponse otherOrgIdListSearch(
        WEB3AdminSrvRegiOtherOrgIdListSearchRequest l_request)
    {
        final String STR_METHOD_NAME =
            "otherOrgIdListSearch(WEB3AdminSrvRegiOtherOrgIdListSearchRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminSrvRegiOtherOrgIdListSearchResponse l_response = null;
        WEB3AdminSrvRegiOtherOrgIdListService l_service = null;

        try
        {
            l_service =
                (WEB3AdminSrvRegiOtherOrgIdListService)Services.getService(
                    WEB3AdminSrvRegiOtherOrgIdListService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdListSearchResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖﻰ�޽���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdListSearchResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdListSearchResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.error(
                l_request,
                "�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�������͉�ʕ\�������ɃG���[���������܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdListSearchResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�������͉�ʕ\�������ɃG���[���������܂����B",
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
