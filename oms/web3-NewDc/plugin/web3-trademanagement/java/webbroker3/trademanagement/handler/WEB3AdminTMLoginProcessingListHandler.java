head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.23.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMLoginProcessingListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���O�C�������ꗗ�n���h�� (WEB3AdminTMLoginProcessingListHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/22 ������ (���u) �V�K�쐬 ���f��No.005
*/

package webbroker3.trademanagement.handler;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryListRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryListResponse;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMLoginProcessingListService;
import webbroker3.util.WEB3LogUtility;

/**
 * (���O�C�������ꗗ�n���h��)<BR>
 * �Ǘ��҃��O�C�������ꗗ�n���h���N���X�B<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3AdminTMLoginProcessingListHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMLoginProcessingListHandler.class);

    /**
     * @@roseuid 48D7511802BA
     */
    public WEB3AdminTMLoginProcessingListHandler()
    {

    }

    /**
     * (get�������͉��)<BR>
     * ���O�C�������ꗗ�̌������͉�ʂ̕\�����s���B <BR>
     * <BR>
     * ���O�C�������ꗗ�T�[�r�XImpl���擾���A <BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * �Ǘ��ҁE���O�C�������ꗗ�\�����̓��N�G�X�g�N���X�B<BR>
     * @@return WEB3AdminTraderAdminLoginHistoryInputResponse
     * @@roseuid 48BDF76C0311
     */
    public WEB3AdminTraderAdminLoginHistoryInputResponse getSearchInputScreen(
        WEB3AdminTraderAdminLoginHistoryInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getSearchInputScreen(WEB3AdminTraderAdminLoginHistoryInputRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminTraderAdminLoginHistoryInputResponse l_response = null;
        WEB3AdminTMLoginProcessingListService l_service = null;
        try
        {
            //���O�C�������ꗗ�T�[�r�X���擾����
            l_service =
                (WEB3AdminTMLoginProcessingListService)Services.getService(
                    WEB3AdminTMLoginProcessingListService.class);
        }
        catch(Exception l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminLoginHistoryInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҁE���O�C�������ꗗ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;
        }
        try
        {
            l_response = (WEB3AdminTraderAdminLoginHistoryInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminLoginHistoryInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "���O�C�������ꗗ�̌������͉�ʂ̕\���Ɏ��s���܂����B",
                l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminLoginHistoryInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "���O�C�������ꗗ�̌������͉�ʂ̕\���Ɏ��s���܂����B",
                l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;  
    }
    
    /**
     * (get�������ʉ��)<BR>
     * ���O�C�������ꗗ�̌������ʉ�ʂ̕\�����s���B <BR>
     * <BR>
     * ���O�C�������ꗗ�T�[�r�XImpl���擾���A <BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * �Ǘ��ҁE���O�C�������ꗗ�\���m�F���N�G�X�g�N���X�B<BR>
     * @@return WEB3AdminTraderAdminLoginHistoryListResponse
     * @@roseuid 48BE0D30001E
     */
    public WEB3AdminTraderAdminLoginHistoryListResponse getSearchResultScreen(
        WEB3AdminTraderAdminLoginHistoryListRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getSearchResultScreen(WEB3AdminTraderAdminLoginHistoryListRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminTraderAdminLoginHistoryListResponse l_response = null;
        WEB3AdminTMLoginProcessingListService l_service = null;
        try
        {
            l_service =
                (WEB3AdminTMLoginProcessingListService)Services.getService(
                    WEB3AdminTMLoginProcessingListService.class);
        }
        catch(Exception l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminLoginHistoryListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҁE���O�C�������ꗗ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;
        }
        try
        {
            l_response = (WEB3AdminTraderAdminLoginHistoryListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminLoginHistoryListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "���O�C�������ꗗ�̌������ʉ�ʂ̕\���Ɏ��s���܂����B",
                l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminLoginHistoryListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "���O�C�������ꗗ�̌������ʉ�ʂ̕\���Ɏ��s���܂����B",
                l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
