head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.23.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMLoginFrequencyListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IP�ʃ��O�C���񐔈ꗗ�n���h��(WEB3AdminTMLoginFrequencyListHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/22 ����(���u) �V�K�쐬 ���f�� 005,009
*/

package webbroker3.trademanagement.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountListRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountListResponse;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMLoginFrequencyListService;
import webbroker3.util.WEB3LogUtility;

/**
 * (IP�ʃ��O�C���񐔈ꗗ�n���h��)<BR>
 * �Ǘ���IP�ʃ��O�C���񐔈ꗗ�n���h���N���X�B<BR>
 *
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminTMLoginFrequencyListHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminTMLoginFrequencyListHandler.class);

    /**
     * @@roseuid 48D75CD80379
     */
    public WEB3AdminTMLoginFrequencyListHandler()
    {

    }

    /**
     * (get�������͉��)<BR>
     * IP�ʃ��O�C���񐔈ꗗ�̌������͉�ʂ̕\�����s���B <BR>
     * <BR>
     * IP�ʃ��O�C���񐔈ꗗ�T�[�r�XImpl���擾���A <BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * �Ǘ��ҁEIP�ʃ��O�C���񐔈ꗗ���̓��N�G�X�g�N���X�B<BR>
     * @@return WEB3AdminTraderAdminLoginCountInputResponse
     * @@roseuid 48C0E8E9011D
     */
    public WEB3AdminTraderAdminLoginCountInputResponse getSearchInputScreen(
        WEB3AdminTraderAdminLoginCountInputRequest l_request)
    {
        final String STR_METHOD_NAME = "getSearchInputScreen(WEB3AdminTraderAdminLoginCountInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTraderAdminLoginCountInputResponse l_response = null;
        WEB3AdminTMLoginFrequencyListService l_service = null;

        try
        {
            //IP�ʃ��O�C���񐔈ꗗ�T�[�r�X���擾���A
            l_service =
                (WEB3AdminTMLoginFrequencyListService)Services.getService(
                    WEB3AdminTMLoginFrequencyListService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminTraderAdminLoginCountInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�Ǘ��ҁEIP�ʃ��O�C���񐔈ꗗ�T�[�r�X�̎擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);

            return l_response;
        }

        try
        {
            l_response = (WEB3AdminTraderAdminLoginCountInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminTraderAdminLoginCountInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "IP�ʃ��O�C���񐔈ꗗ�̌������͉�ʂ̕\���Ɏ��s���܂����B", l_ex);

            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response = (WEB3AdminTraderAdminLoginCountInputResponse)l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            log.error(l_request, "IP�ʃ��O�C���񐔈ꗗ�̌������͉�ʂ̕\���Ɏ��s���܂����B", l_rex);

            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }

    /**
     * (get�������ʉ��)<BR>
     * IP�ʃ��O�C���񐔈ꗗ�̌������ʉ�ʂ̕\�����s���B <BR>
     * <BR>
     * IP�ʃ��O�C���񐔈ꗗ�T�[�r�XImpl���擾���A <BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * �Ǘ��ҁEIP�ʃ��O�C���񐔈ꗗ�m�F���N�G�X�g�N���X�B<BR>
     * @@return WEB3AdminTraderAdminLoginCountListResponse
     * @@roseuid 48C0E936015D
     */
    public WEB3AdminTraderAdminLoginCountListResponse getSearchResultScreen(
        WEB3AdminTraderAdminLoginCountListRequest l_request)
    {
        final String STR_METHOD_NAME = "getSearchResultScreen(WEB3AdminTraderAdminLoginCountListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTraderAdminLoginCountListResponse l_response = null;
        WEB3AdminTMLoginFrequencyListService l_service = null;

        try
        {
            //IP�ʃ��O�C���񐔈ꗗ�T�[�r�X���擾���A
            l_service =
                (WEB3AdminTMLoginFrequencyListService)Services.getService(
                    WEB3AdminTMLoginFrequencyListService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminTraderAdminLoginCountListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�Ǘ��ҁEIP�ʃ��O�C���񐔈ꗗ�T�[�r�X�̎擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);

            return l_response;
        }

        try
        {
            l_response = (WEB3AdminTraderAdminLoginCountListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminTraderAdminLoginCountListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "IP�ʃ��O�C���񐔈ꗗ�̌������ʉ�ʂ̕\���Ɏ��s���܂����B", l_ex);

            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response = (WEB3AdminTraderAdminLoginCountListResponse)l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            log.error(l_request, "IP�ʃ��O�C���񐔈ꗗ�̌������ʉ�ʂ̕\���Ɏ��s���܂����B", l_rex);

            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}@
